package food.order.delivery.online.offers.deals.coupons

import android.app.Application
import android.util.Log
import com.facebook.ads.AdSettings
import com.facebook.ads.AudienceNetworkAds
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.onesignal.OneSignal
import food.order.delivery.online.offers.deals.coupons.data.DataFactory
import food.order.delivery.online.offers.deals.coupons.data.DataService
import food.order.delivery.online.offers.deals.coupons.utils.Constants
import food.order.delivery.online.offers.deals.coupons.utils.ForceUpdateChecker
import io.reactivex.Scheduler
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import food.order.delivery.online.offers.deals.coupons.buy.BuildConfig
import food.order.delivery.online.offers.deals.coupons.utils.AppNotificationOpenHandler
import java.util.*

class Singleton : Application() {
    private var dataService: DataService? = null
    private var scheduler: Scheduler? = null
    private val ONESIGNAL_APP_ID ="494ef3c7-6cd4-4aea-9c6b-87423f88a051"
    companion object{

        var application: Singleton? = null

        fun get(): Singleton? {
            return application
        }
    }


    override fun onCreate() {
        super.onCreate()
        application = this

        RxJavaPlugins.setErrorHandler { throwable: Throwable ->
            Log.e(
                "TAG",
                "onCreate: " + throwable.message
            )
        }
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE)

        // OneSignal Initialization
        OneSignal.initWithContext(this)
        OneSignal.setAppId(ONESIGNAL_APP_ID)
        OneSignal.setNotificationOpenedHandler(AppNotificationOpenHandler(this))



        AudienceNetworkAds.initialize(this);
        if(BuildConfig.DEBUG){
            AdSettings.setIntegrationErrorMode(AdSettings.IntegrationErrorMode.INTEGRATION_ERROR_CRASH_DEBUG_MODE);
        }

        MobileAds.initialize(this){}

        if (BuildConfig.DEBUG){
            AdSettings.setTestMode(true);
        }else{
            AdSettings.setTestMode(false);
        }
        val firebaseRemoteConfig: FirebaseRemoteConfig = FirebaseRemoteConfig.getInstance()

        // set in-app defaults

        // set in-app defaults

        // set in-app defaults
        val remoteConfigDefaults = TreeMap<String?, Any?>()
        remoteConfigDefaults[Constants().SHOW_ADS] = false
        remoteConfigDefaults[ForceUpdateChecker().KEY_UPDATE_REQUIRED] = false
        remoteConfigDefaults[ForceUpdateChecker().KEY_CURRENT_VERSION] = "1.0"
        remoteConfigDefaults[ForceUpdateChecker().KEY_UPDATE_URL] =
            "https://play.google.com/store/apps/details?id="+applicationContext.packageName

        firebaseRemoteConfig.setDefaults(remoteConfigDefaults)
        firebaseRemoteConfig.fetch(300) // fetch every minutes
            .addOnCompleteListener(OnCompleteListener<Void?> { task ->
                if (task.isSuccessful) {
                    Log.d("TAG", "remote config is fetched.$task")
                    firebaseRemoteConfig.activateFetched()
                    Log.d(
                        "TAG",
                        "onComplete: " + firebaseRemoteConfig.getString("banner_facebook_ads")
                    )
                }
            })


    }


    fun getDataService(): DataService? {
        val dataService by lazy {
            DataFactory.create()
        }
        return dataService
    }

    fun subscribeScheduler(): Scheduler? {
        if (scheduler == null) {
            scheduler = Schedulers.io()
        }
        return scheduler
    }


}