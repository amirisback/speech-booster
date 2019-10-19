package com.frogobox.speechbooster.util.helper

import android.content.Context
import android.util.Log
import com.frogobox.speechbooster.R
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.reward.RewardedVideoAd
import com.google.android.gms.ads.reward.RewardedVideoAdListener


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

    object Publisher {

        fun setupPublisher(context: Context) {
            MobileAds.initialize(context, context.getString(R.string.admob_publisher_id))
        }

    }

    object Interstitial {

        fun setupInterstitial(context: Context, mInterstitialAd: InterstitialAd){
            mInterstitialAd.adUnitId = context.getString(R.string.admob_interstitial)
        }

        fun showInterstitial(mInterstitialAd: InterstitialAd){
            mInterstitialAd.loadAd(AdRequest.Builder().build())
            mInterstitialAd.adListener = object : AdListener() {
                override fun onAdClosed() {
                    mInterstitialAd.loadAd(AdRequest.Builder().build())
                }
            }
            checkLoadedInterstitial(mInterstitialAd)
        }

        private fun checkLoadedInterstitial(mInterstitialAd: InterstitialAd){
            if (mInterstitialAd.isLoaded) {
                mInterstitialAd.show()
            } else {
                Log.d("TAG", "The interstitial wasn't loaded yet.")
            }
        }

    }

    object Video {

        fun setupVideo(context: Context, rewardedVideoAdListener: RewardedVideoAdListener, mRewardedVideoAd: RewardedVideoAd){
            mRewardedVideoAd.rewardedVideoAdListener = rewardedVideoAdListener
            mRewardedVideoAd.loadAd(context.getString(R.string.admob_video), AdRequest.Builder().build())
        }

        fun showVideo(mRewardedVideoAd: RewardedVideoAd){
            if (mRewardedVideoAd.isLoaded) {
                mRewardedVideoAd.show()
            }
        }

    }

}