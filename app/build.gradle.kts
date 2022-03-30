plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {

    compileSdk = ProjectSetting.PROJECT_COMPILE_SDK

    defaultConfig {
        applicationId = ProjectSetting.PROJECT_APP_ID
        minSdk = ProjectSetting.PROJECT_MIN_SDK
        targetSdk = ProjectSetting.PROJECT_TARGET_SDK
        versionCode = ProjectSetting.PROJECT_VERSION_CODE
        versionName = ProjectSetting.PROJECT_VERSION_NAME

        multiDexEnabled = true
        vectorDrawables.useSupportLibrary = true

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        // Naming APK // AAB
        setProperty("archivesBaseName", "${ProjectSetting.NAME_APK}(${versionName})")

        // Declaration build config
        buildConfigField("String", "DATABASE_NAME", ProjectSetting.DB)

        // Declaration apps name debug mode
        val debugAttribute = "Development"
        val nameAppDebug = "${ProjectSetting.NAME_APP} $debugAttribute"

        resourceConfigurations += setOf("en", "id")

        // Inject app name for debug
        resValue("string", "app_name", nameAppDebug)

        // Inject admob id for debug
        resValue("string", "admob_publisher_id", AdmobValue.debugAdmobPublisherId)
        resValue("string", "admob_banner", AdmobValue.debugAdmobBanner)
        resValue("string", "admob_interstitial", AdmobValue.debugAdmobInterstitial)
        resValue("string", "admob_interstitial_video", AdmobValue.debugAdmobInterstitialVideo)
        resValue("string", "admob_rewarded", AdmobValue.debugAdmobRewarded)
        resValue("string", "admob_rewarded_interstitial", AdmobValue.debugAdmobRewardedInterstitial)
        resValue("string", "admob_native_advanced", AdmobValue.debugAdmobNativeAdvanced)
        resValue("string", "admob_native_advanced_video", AdmobValue.debugAdmobNativeAdvancedVideo)
    }

    signingConfigs {
        create("release") {
            // You need to specify either an absolute path or include the
            // keystore file in the same directory as the build.gradle file.
            // [PROJECT FOLDER NAME/app/[COPY YOUT KEY STORE] .jks in here
            storeFile = file(ProjectSetting.PROJECT_JKS_STORE_FILE)
            storePassword = ProjectSetting.PROJECT_JKS_STORE_PASS
            keyAlias = ProjectSetting.PROJECT_JKS_KEY_ALIAS
            keyPassword = ProjectSetting.PROJECT_JKS_KEY_PASS
        }
    }

    buildTypes {

        getByName("release") {
            isMinifyEnabled = false

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )

            // Generated Signed APK / AAB
            signingConfig = signingConfigs.getByName("release")

            // Inject app name for release
            resValue("string", "app_name", ProjectSetting.APP_NAME)

            // Inject admob id for release
            resValue("string", "admob_publisher_id", AdmobValue.releaseAdmobPublisherId)
            resValue("string", "admob_banner", AdmobValue.releaseAdmobBanner)
            resValue("string", "admob_interstitial", AdmobValue.releaseAdmobInterstitial)
            resValue("string", "admob_interstitial_video", AdmobValue.releaseAdmobInterstitialVideo)
            resValue("string", "admob_rewarded", AdmobValue.releaseAdmobRewarded)
            resValue("string", "admob_rewarded_interstitial", AdmobValue.releaseAdmobRewardedInterstitial)
            resValue("string", "admob_native_advanced", AdmobValue.releaseAdmobNativeAdvanced)
            resValue("string", "admob_native_advanced_video", AdmobValue.releaseAdmobNativeAdvancedVideo)

        }
    }

    buildFeatures {
        viewBinding = true
        compose = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Dependency.COMPOSE_VERSION
    }

    packagingOptions {
        resources {
            excludes += setOf("META-INF/AL2.0", "META-INF/LGPL2.1")
        }
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            jvmTarget = JavaVersion.VERSION_11.toString()
        }
    }

}

dependencies {

    implementation(Androidx.appCompat)
    implementation(Androidx.preferenceKtx)
    implementation(Androidx.constraintLayout)
    implementation(Androidx.activityKtx)
    implementation(Androidx.fragmentKtx)
    implementation(Androidx.Core.ktx)

    implementation(Androidx.Lifecycle.viewmodelKtx)
    implementation(Androidx.Lifecycle.runtimeKtx)
    implementation(Androidx.Lifecycle.livedataKtx)

    implementation(Androidx.Room.runtime)
    implementation(Androidx.Room.ktx)
    implementation(Androidx.Room.rxJava3)

    implementation(Google.gson)
    implementation(Google.material)
    implementation(Google.admob)

    implementation(Square.OkHttp.okhttp)
    implementation(Square.OkHttp.loggingInterceptor)

    implementation(Square.Retrofit2.retrofit)
    implementation(Square.Retrofit2.converterGson)
    implementation(Square.Retrofit2.adapterRxJava3)

    implementation(Reactivex.rxJava3)
    implementation(Reactivex.rxKotlin3)
    implementation(Reactivex.rxAndroid3)

    implementation(Frogo.sdk)
    implementation(Frogo.ui)
    implementation(Frogo.recyclerView)
    implementation(Frogo.admob)
    implementation(Frogo.consumeApi)

    implementation(GitHub.glide)
    implementation(GitHub.chucker)

    implementation(Koin.core)
    implementation(Koin.android)
    implementation(Koin.androidCompat)
    implementation(Koin.androidxWorkManager)
    implementation(Koin.androidxCompose)
    implementation(Koin.ktor)

    implementation("com.github.javiersantos:PiracyChecker:1.2.8")

    api(JetBrains.coroutinesCore)
    api(JetBrains.coroutinesAndroid)

    kapt(Androidx.Lifecycle.compiler)
    kapt(Androidx.Room.compiler)
    kapt(GitHub.glideCompiler)

}
