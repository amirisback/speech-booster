package com.frogobox.speechbooster.mvvm.record

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.graphics.Matrix
import android.graphics.RectF
import android.graphics.SurfaceTexture
import android.hardware.camera2.*
import android.media.MediaRecorder
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.util.Log
import android.util.Size
import android.util.SparseIntArray
import android.view.*
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import com.frogobox.frogosdk.core.FrogoFunc.createFolderPictureVideo
import com.frogobox.frogosdk.core.FrogoFunc.getVideoFilePath
import com.frogobox.speechbooster.R
import com.frogobox.speechbooster.core.BaseFragment
import com.frogobox.speechbooster.databinding.FragmentRecordBinding
import com.frogobox.speechbooster.util.ConstHelper
import com.frogobox.speechbooster.util.CompareSizesByArea
import com.frogobox.speechbooster.util.DialogConfirmation
import com.frogobox.speechbooster.util.DialogError
import com.frogobox.speechbooster.source.model.RepositoryScript
import com.frogobox.speechbooster.source.model.FavoriteScript
import com.frogobox.speechbooster.source.model.Script
import com.frogobox.speechbooster.util.ConstHelper.Arg.ARGUMENTS_EXAMPLE_SCRIPT
import com.frogobox.speechbooster.util.ConstHelper.Arg.ARGUMENTS_FAVORITE_SCRIPT
import com.frogobox.speechbooster.util.ConstHelper.Arg.ARGUMENTS_SCRIPT
import com.frogobox.speechbooster.mvvm.video.VideoScriptRecordViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.IOException
import java.util.*
import java.util.concurrent.Semaphore
import java.util.concurrent.TimeUnit
import kotlin.collections.ArrayList


class RecordFragment : BaseFragment<FragmentRecordBinding>(), View.OnClickListener,
    ActivityCompat.OnRequestPermissionsResultCallback {

    private val mViewModel: VideoScriptRecordViewModel by viewModel()
    private lateinit var previewSize: Size
    private lateinit var videoSize: Size
    private lateinit var previewRequestBuilder: CaptureRequest.Builder

    private var cameraDevice: CameraDevice? = null
    private var captureSession: CameraCaptureSession? = null
    private var isRecordingVideo = false
    private var backgroundThread: HandlerThread? = null
    private var backgroundHandler: Handler? = null

    private var sensorOrientation = 0
    private var nextVideoAbsolutePath: String? = null
    private var mediaRecorder: MediaRecorder? = null
    private val cameraOpenCloseLock = Semaphore(1)

    private val DEFAULT_ORIENTATIONS = SparseIntArray().apply {
        append(Surface.ROTATION_0, 90)
        append(Surface.ROTATION_90, 0)
        append(Surface.ROTATION_180, 270)
        append(Surface.ROTATION_270, 180)
    }
    private val INVERSE_ORIENTATIONS = SparseIntArray().apply {
        append(Surface.ROTATION_0, 270)
        append(Surface.ROTATION_90, 180)
        append(Surface.ROTATION_180, 90)
        append(Surface.ROTATION_270, 0)
    }
    private val stateCallback = object : CameraDevice.StateCallback() {

        override fun onOpened(cameraDevice: CameraDevice) {
            cameraOpenCloseLock.release()
            this@RecordFragment.cameraDevice = cameraDevice
            startPreview()
            binding.textureView.apply {
                configureTransform(width, height)
            }
        }

        override fun onDisconnected(cameraDevice: CameraDevice) {
            cameraOpenCloseLock.release()
            cameraDevice.close()
            this@RecordFragment.cameraDevice = null
        }

        override fun onError(cameraDevice: CameraDevice, error: Int) {
            cameraOpenCloseLock.release()
            cameraDevice.close()
            this@RecordFragment.cameraDevice = null
            activity?.finish()
        }

    }
    private val surfaceTextureListener = object : TextureView.SurfaceTextureListener {

        override fun onSurfaceTextureAvailable(texture: SurfaceTexture, width: Int, height: Int) {
            openCamera(width, height)
        }

        override fun onSurfaceTextureSizeChanged(texture: SurfaceTexture, width: Int, height: Int) {
            configureTransform(width, height)
        }

        override fun onSurfaceTextureDestroyed(surfaceTexture: SurfaceTexture) = true

        override fun onSurfaceTextureUpdated(surfaceTexture: SurfaceTexture) = Unit

    }

    override fun setupViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup
    ): FragmentRecordBinding {
        return FragmentRecordBinding.inflate(inflater, container, false)
    }

    override fun setupUI(savedInstanceState: Bundle?) {
        binding.imgRecordMenu.setOnClickListener(this)
        setupRoleView()
        finishRecord()
    }

    private fun finishRecord(){
        binding.imgToolbarHome.setOnClickListener {
            frogoActivity.finish()
        }
    }

    override fun onResume() {
        super.onResume()
        startBackgroundThread()

        // When the screen is turned off and turned back on, the SurfaceTexture is already
        // available, and "onSurfaceTextureAvailable" will not be called. In that case, we can open
        // a camera and start preview from here (otherwise, we wait until the surface is ready in
        // the SurfaceTextureListener).
        binding.textureView.apply {
            if (isAvailable) {
                openCamera(width, height)
            } else {
                surfaceTextureListener = surfaceTextureListener
            }
        }
    }

    override fun onPause() {
        closeCamera()
        stopBackgroundThread()
        super.onPause()
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.img_record_menu -> if (isRecordingVideo) stopRecordingVideo() else startRecordingVideo()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (requestCode == ConstHelper.Code.CODE_REQUEST_VIDEO_PERMISSIONS) {
            if (grantResults.size == ConstHelper.Code.CODE_VIDEO_PERMISSIONS.size) {
                for (result in grantResults) {
                    if (result != PackageManager.PERMISSION_GRANTED) {
                        DialogError.newInstance(getString(R.string.permission_request))
                            .show(childFragmentManager, ConstHelper.Const.FRAGMENT_DIALOG)
                        break
                    }
                }
            } else {
                DialogError.newInstance(getString(R.string.permission_request))
                    .show(childFragmentManager, ConstHelper.Const.FRAGMENT_DIALOG)
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }

    override fun setupViewModel() {
        mViewModel.apply {

        }
    }

    private fun setupRoleView(){

        if (arguments != null) {
            if (checkArgument(ARGUMENTS_SCRIPT)) {
                val argumentsScript = baseGetInstance<Script>(ARGUMENTS_SCRIPT)
                setupViewElement(argumentsScript.title!!, argumentsScript.description!!)
            } else if (checkArgument(ARGUMENTS_EXAMPLE_SCRIPT)) {
                val argumentsExampleScript = baseGetInstance<RepositoryScript>(ARGUMENTS_EXAMPLE_SCRIPT)
                setupViewElement(argumentsExampleScript.title!!, argumentsExampleScript.description!!)
            } else if (checkArgument(ARGUMENTS_FAVORITE_SCRIPT)) {
                val argumentsFavoriteScript = baseGetInstance<FavoriteScript>(ARGUMENTS_FAVORITE_SCRIPT)
                setupViewElement(argumentsFavoriteScript.title!!, argumentsFavoriteScript.description!!)
            }

        }

    }

    private fun setupViewElement(title: String, desc: String){
        binding.apply {
            tvTitle.text = title
            tvDescription.text = desc
        }
    }

    private fun startBackgroundThread() {
        backgroundThread = HandlerThread("CameraBackground")
        backgroundThread?.start()
        backgroundHandler = backgroundThread?.looper?.let { Handler(it) }
    }

    private fun stopBackgroundThread() {
        backgroundThread?.quitSafely()
        try {
            backgroundThread?.join()
            backgroundThread = null
            backgroundHandler = null
        } catch (e: InterruptedException) {
            Log.e(ConstHelper.Tag.TAG_CAMERA_VIDEO, e.toString())
        }
    }

    private fun shouldShowRequestPermissionRationale(permissions: Array<String>) =
        permissions.any { shouldShowRequestPermissionRationale(it) }

    private fun requestVideoPermissions() {
        if (shouldShowRequestPermissionRationale(ConstHelper.Code.CODE_VIDEO_PERMISSIONS)) {
            DialogConfirmation()
                .show(childFragmentManager, ConstHelper.Const.FRAGMENT_DIALOG)
        } else {
            requestPermissions(
                ConstHelper.Code.CODE_VIDEO_PERMISSIONS,
                ConstHelper.Code.CODE_REQUEST_VIDEO_PERMISSIONS
            )
        }
    }


    private fun hasPermissionsGranted(permissions: Array<String>) =
        permissions.none {
            ContextCompat.checkSelfPermission(
                (activity as FragmentActivity),
                it
            ) != PackageManager.PERMISSION_GRANTED
        }

    @SuppressLint("MissingPermission")
    private fun openCamera(width: Int, height: Int) {
        if (!hasPermissionsGranted(ConstHelper.Code.CODE_VIDEO_PERMISSIONS)) {
            requestVideoPermissions()
            return
        }

        val cameraActivity = activity
        if (cameraActivity == null || cameraActivity.isFinishing) return

        val manager = cameraActivity.getSystemService(Context.CAMERA_SERVICE) as CameraManager
        try {
            if (!cameraOpenCloseLock.tryAcquire(2500, TimeUnit.MILLISECONDS)) {
                throw RuntimeException("Time out waiting to lock camera opening.")
            }

            // Camera Rear
            // val cameraId = manager.cameraIdList[0]

            // Camera Front
            val cameraId = manager.cameraIdList[1]

            // Choose the sizes for camera preview and video recording
            val characteristics = manager.getCameraCharacteristics(cameraId)
            val map = characteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP)
                ?: throw RuntimeException("Cannot get available preview/video sizes")
            sensorOrientation = characteristics.get(CameraCharacteristics.SENSOR_ORIENTATION)!!
            videoSize = chooseVideoSize(map.getOutputSizes(MediaRecorder::class.java))
            previewSize = chooseOptimalSize(
                map.getOutputSizes(SurfaceTexture::class.java),
                width, height, videoSize
            )

            binding.textureView.apply {
                if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    setAspectRatio(previewSize.width, previewSize.height)
                } else {
                    setAspectRatio(previewSize.height, previewSize.width)
                }
            }
            configureTransform(width, height)
            mediaRecorder = MediaRecorder()
            manager.openCamera(cameraId, stateCallback, null)
        } catch (e: CameraAccessException) {
            showToast("Cannot access the camera.")
            cameraActivity.finish()
        } catch (e: NullPointerException) {
            // Currently an NPE is thrown when the Camera2API is used but not supported on the
            // device this code runs.
            DialogError.newInstance(getString(R.string.camera_error))
                .show(childFragmentManager, ConstHelper.Const.FRAGMENT_DIALOG)
        } catch (e: InterruptedException) {
            throw RuntimeException("Interrupted while trying to lock camera opening.")
        }
    }

    private fun closeCamera() {
        try {
            cameraOpenCloseLock.acquire()
            closePreviewSession()
            cameraDevice?.close()
            cameraDevice = null
            mediaRecorder?.release()
            mediaRecorder = null
        } catch (e: InterruptedException) {
            throw RuntimeException("Interrupted while trying to lock camera closing.", e)
        } finally {
            cameraOpenCloseLock.release()
        }
    }

    private fun startPreview() {
        binding.textureView.apply {
            if (cameraDevice == null || !isAvailable) return

            try {
                closePreviewSession()
                val texture = surfaceTexture
                texture?.setDefaultBufferSize(previewSize.width, previewSize.height)
                previewRequestBuilder =
                    cameraDevice!!.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW)

                val previewSurface = Surface(texture)
                previewRequestBuilder.addTarget(previewSurface)

                cameraDevice?.createCaptureSession(
                    listOf(previewSurface),
                    object : CameraCaptureSession.StateCallback() {

                        override fun onConfigured(session: CameraCaptureSession) {
                            captureSession = session
                            updatePreview()
                        }

                        override fun onConfigureFailed(session: CameraCaptureSession) {
                            if (activity != null) showToast("Failed")
                        }
                    }, backgroundHandler
                )
            } catch (e: CameraAccessException) {
                Log.e(ConstHelper.Tag.TAG_CAMERA_VIDEO, e.toString())
            }

        }
    }

    private fun updatePreview() {
        if (cameraDevice == null) return

        try {
            setUpCaptureRequestBuilder(previewRequestBuilder)
            HandlerThread("CameraPreview").start()
            captureSession?.setRepeatingRequest(
                previewRequestBuilder.build(),
                null, backgroundHandler
            )
        } catch (e: CameraAccessException) {
            Log.e(ConstHelper.Tag.TAG_CAMERA_VIDEO, e.toString())
        }

    }

    private fun setUpCaptureRequestBuilder(builder: CaptureRequest.Builder?) {
        builder?.set(CaptureRequest.CONTROL_MODE, CameraMetadata.CONTROL_MODE_AUTO)
    }

    private fun configureTransform(viewWidth: Int, viewHeight: Int) {
        activity ?: return
        val rotation =
            (activity as FragmentActivity).windowManager.defaultDisplay.rotation
        val matrix = Matrix()
        val viewRect = RectF(0f, 0f, viewWidth.toFloat(), viewHeight.toFloat())
        val bufferRect = RectF(0f, 0f, previewSize.height.toFloat(), previewSize.width.toFloat())
        val centerX = viewRect.centerX()
        val centerY = viewRect.centerY()

        if (Surface.ROTATION_90 == rotation || Surface.ROTATION_270 == rotation) {
            bufferRect.offset(centerX - bufferRect.centerX(), centerY - bufferRect.centerY())
            matrix.setRectToRect(viewRect, bufferRect, Matrix.ScaleToFit.FILL)
            val scale = Math.max(
                viewHeight.toFloat() / previewSize.height,
                viewWidth.toFloat() / previewSize.width
            )
            with(matrix) {
                postScale(scale, scale, centerX, centerY)
                postRotate((90 * (rotation - 2)).toFloat(), centerX, centerY)
            }
        }
        binding.textureView.setTransform(matrix)
    }

    @Throws(IOException::class)
    private fun setUpMediaRecorder() {
        val cameraActivity = activity ?: return
        createFolderPictureVideo()

        if (nextVideoAbsolutePath.isNullOrEmpty()) {
            nextVideoAbsolutePath = getVideoFilePath()
        }

        val rotation = cameraActivity.windowManager.defaultDisplay.rotation
        when (sensorOrientation) {
            ConstHelper.Value.VALUE_SENSOR_ORIENTATION_DEFAULT_DEGREES ->
                mediaRecorder?.setOrientationHint(DEFAULT_ORIENTATIONS.get(rotation))
            ConstHelper.Value.VALUE_SENSOR_ORIENTATION_INVERSE_DEGREES ->
                mediaRecorder?.setOrientationHint(INVERSE_ORIENTATIONS.get(rotation))
        }

        mediaRecorder?.apply {
            setAudioSource(MediaRecorder.AudioSource.MIC)
            setVideoSource(MediaRecorder.VideoSource.SURFACE)
            setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
            setOutputFile(nextVideoAbsolutePath)
            setVideoEncodingBitRate(10000000)
            setVideoFrameRate(30)
            setVideoSize(videoSize.width, videoSize.height)
            setVideoEncoder(MediaRecorder.VideoEncoder.H264)
            setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
            prepare()
        }
    }

    private fun startRecordingVideo() {
        binding.apply {
            if (cameraDevice == null || !textureView.isAvailable) return

            try {
                closePreviewSession()
                setUpMediaRecorder()
                val texture = textureView.surfaceTexture?.apply {
                    setDefaultBufferSize(previewSize.width, previewSize.height)
                }

                val previewSurface = Surface(texture)
                val recorderSurface = mediaRecorder!!.surface
                val surfaces = ArrayList<Surface>().apply {
                    add(previewSurface)
                    add(recorderSurface)
                }
                previewRequestBuilder =
                    cameraDevice!!.createCaptureRequest(CameraDevice.TEMPLATE_RECORD).apply {
                        addTarget(previewSurface)
                        addTarget(recorderSurface)
                    }

                cameraDevice?.createCaptureSession(
                    surfaces,
                    object : CameraCaptureSession.StateCallback() {

                        override fun onConfigured(cameraCaptureSession: CameraCaptureSession) {
                            captureSession = cameraCaptureSession
                            updatePreview()
                            activity?.runOnUiThread {
                                imgRecordMenu.setImageResource(R.drawable.ic_toolbar_record_stop)
                                isRecordingVideo = true
                                mediaRecorder?.start()
                            }
                        }

                        override fun onConfigureFailed(cameraCaptureSession: CameraCaptureSession) {
                            if (activity != null) showToast("Failed")
                        }
                    }, backgroundHandler
                )
            } catch (e: CameraAccessException) {
                Log.e(ConstHelper.Tag.TAG_CAMERA_VIDEO, e.toString())
            } catch (e: IOException) {
                Log.e(ConstHelper.Tag.TAG_CAMERA_VIDEO, e.toString())
            }
        }

    }

    private fun closePreviewSession() {
        captureSession?.close()
        captureSession = null
    }

    private fun stopRecordingVideo() {
        isRecordingVideo = false
        binding.imgRecordMenu.setImageResource(R.drawable.ic_toolbar_record)
        mediaRecorder?.apply {
            stop()
            reset()
        }

        if (activity != null) {
            showToast("Video saved: $nextVideoAbsolutePath")
            nextVideoAbsolutePath?.let { Log.d("TAG PATH VIDEO", it) }
        }
        nextVideoAbsolutePath = null
        startPreview()
    }

    private fun chooseVideoSize(choices: Array<Size>) = choices.firstOrNull {
        it.width == it.height * 4 / 3 && it.width <= 1080
    } ?: choices[choices.size - 1]

    private fun chooseOptimalSize(choices: Array<Size>, width: Int, height: Int, aspectRatio: Size): Size {

        // Collect the supported resolutions that are at least as big as the preview Surface
        val w = aspectRatio.width
        val h = aspectRatio.height
        val bigEnough = choices.filter {
            it.height == it.width * h / w && it.width >= width && it.height >= height
        }

        // Pick the smallest of those, assuming we found any
        return if (bigEnough.isNotEmpty()) {
            Collections.min(bigEnough, CompareSizesByArea())
        } else {
            choices[0]
        }
    }

}

