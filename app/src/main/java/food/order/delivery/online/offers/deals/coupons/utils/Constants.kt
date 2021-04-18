package food.order.delivery.online.offers.deals.coupons.utils

import food.order.delivery.online.offers.deals.coupons.buy.BuildConfig


class Constants {

    val SHOW_ADS = "show_ads"


    val FB_ADS_TEST = "VID_HD_9_16_39S_APP_INSTALL#YOUR_PLACEMENT_ID"
    val FB_BANNER_TEST = "IMG_16_9_APP_INSTALL#YOUR_PLACEMENT_ID"
    val FB_NATIVE_HOME_1 = "304322210863531_304322814196804"
    val FB_NATIVE_HOME_2 = "304322210863531_304331164195969"
    val FB_NATIVE_TOPIC_DIALOG = "304322210863531_358215122140906"
    val FB_NATIVE_CANT_1 = "304322210863531_358214318807653"
    val FB_NATIVE_CANT_2 = "304322210863531_358214762140942"
    val FB_NATIVE_CAT_1 = "304322210863531_304331314195954"
    val FB_NATIVE_CAT_2 = "304322210863531_304331800862572"
    val FB_NATIVE_INVEST = "304322210863531_358215318807553"

    val FB_BANNER_WEB = "304322210863531_304332194195866"
    val FB_INTERSTITIAL_WEB_EXIT = "304322210863531_304332067529212"

    fun getFbNativeHome1(): String {
        return if (BuildConfig.DEBUG)
            FB_ADS_TEST
        else
            FB_NATIVE_HOME_1
    }
    fun getFbNativeHome2(): String {
        return if (BuildConfig.DEBUG)
            FB_ADS_TEST
        else
            FB_NATIVE_HOME_2
    }
    fun getFbNativeCat1(): String {
        return if (BuildConfig.DEBUG)
            FB_ADS_TEST
        else
            FB_NATIVE_CAT_1
    }

    fun getFbNativeCat2(): String {
        return if (BuildConfig.DEBUG)
            FB_ADS_TEST
        else
            FB_NATIVE_CAT_2
    }

    fun getFbNativeCantonment1(): String {
        return if (BuildConfig.DEBUG)
            FB_ADS_TEST
        else
            FB_NATIVE_CANT_1
    }
    fun getFbNativeCantonment2(): String {
        return if (BuildConfig.DEBUG)
            FB_ADS_TEST
        else
            FB_NATIVE_CANT_2
    }
    fun getFbNativeInvest(): String {
        return if (BuildConfig.DEBUG)
            FB_ADS_TEST
        else
            FB_NATIVE_INVEST
    }

    fun getFbNativeTopicDailog(): String {
        return if (BuildConfig.DEBUG)
            FB_ADS_TEST
        else
            FB_NATIVE_TOPIC_DIALOG
    }
    fun getFbBannerWeb(): String {
        return if (BuildConfig.DEBUG)
            FB_BANNER_TEST
        else
            FB_BANNER_WEB
    }

    fun getFbInterstitialWebExit(): String {
        return if (BuildConfig.DEBUG)
            FB_ADS_TEST
        else
            FB_INTERSTITIAL_WEB_EXIT
    }




}