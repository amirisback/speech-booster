package com.frogobox.speechbooster.util.helper

import android.content.Context
import android.util.Log
import com.frogobox.speechbooster.R
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd
import com.google.android.gms.ads.MobileAds



/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * SpeechBooster
 * Copyright (C) 07/10/2019.
 * All rights reserved
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * LinkedIn : linkedin.com/in/faisalamircs
 * -----------------------------------------
 * FrogoBox Software Industries
 * com.frogobox.speechbooster.util.helper
 *
 */
class AdmobHelper {

    object General {

        fun setupAds(context: Context) {
            MobileAds.initialize(context, context.getString(R.string.admob_publisher_id))
        }

    }

    object Interstitial {

        fun setupInterstitial(context: Context, mInterstitialAd: InterstitialAd){
            mInterstitialAd.adUnitId = context.getString(R.string.admob_interstitial)
            mInterstitialAd.loadAd(AdRequest.Builder().build())
        }

        fun showInterstitial(mInterstitialAd: InterstitialAd){
            if (mInterstitialAd.isLoaded) {
                mInterstitialAd.show()
            } else {
                Log.d("TAG", "The interstitial wasn't loaded yet.")
            }
        }

    }

}