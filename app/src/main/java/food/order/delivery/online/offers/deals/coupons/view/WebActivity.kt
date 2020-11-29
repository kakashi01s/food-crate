package food.order.delivery.online.offers.deals.coupons.view

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.*
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import food.order.delivery.online.offers.deals.coupons.buy.R
import food.order.delivery.online.offers.deals.coupons.utils.Constants
import kotlinx.android.synthetic.main.activity_web.*


class WebActivity : AppCompatActivity() {

    var webView: WebView? = null
    var appTitle: String? = null
    var appUrl: String? = null
    var appIcon: String? = null

    var rlWebSplash: RelativeLayout? = null
    var ivAppIcon: ImageView? = null

    var firebaseRemoteConfig: FirebaseRemoteConfig? = null

    private lateinit var mInterstitialAd: InterstitialAd

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)

        initViews()
        initData()

        firebaseRemoteConfig = FirebaseRemoteConfig.getInstance()

        loadWebSplash()

        webViewSettings()

        webView?.loadUrl(appUrl)

        if(firebaseRemoteConfig!!.getBoolean(Constants().SHOW_ADS)){
            val adRequest = AdRequest.Builder()
                .build()

            adView.loadAd(adRequest)


            mInterstitialAd = InterstitialAd(this)
            mInterstitialAd.adUnitId = Constants().getInterstitialWebExit()
            mInterstitialAd.loadAd(AdRequest.Builder().build())
        }

    }

    fun initViews(){
        webView = findViewById(R.id.webViewMain)
        rlWebSplash = findViewById(R.id.rlWebSplash)
        ivAppIcon = findViewById(R.id.ivAppIcon)
    }

    fun initData(){
        val bundle: Bundle? = intent.extras
        appUrl = bundle?.getString("url")
        appIcon = bundle?.getString("app_icon")
        Log.d("TAG", "initData: " + bundle?.getString("url"))

    }

    fun webViewSettings(){
        webView!!.settings.loadsImagesAutomatically = true
        webView!!.settings.javaScriptEnabled = true
        webView!!.settings.allowContentAccess = true

        webView!!.settings.useWideViewPort = true
        webView!!.settings.loadWithOverviewMode = true
        webView!!.settings.domStorageEnabled = true
        webView!!.clearView()
        webView!!.isHorizontalScrollBarEnabled = false
        webView!!.settings.setAppCacheEnabled(true)
        webView!!.settings.databaseEnabled = true
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            webView!!.settings.databasePath = "/data/data/" + this.packageName + "/databases/"
        }
        webView!!.isVerticalScrollBarEnabled = false
        webView!!.settings.builtInZoomControls = true
        webView!!.settings.displayZoomControls = false
        webView!!.settings.allowFileAccess = true
        webView!!.settings.pluginState = WebSettings.PluginState.OFF
        webView!!.isScrollbarFadingEnabled = false
        webView!!.settings.cacheMode = WebSettings.LOAD_NO_CACHE
        webView!!.settings.defaultZoom = WebSettings.ZoomDensity.FAR
        webView!!.webViewClient = WebViewClient()
        webView!!.settings.setRenderPriority(WebSettings.RenderPriority.HIGH)
        webView!!.setInitialScale(1)

        webView!!.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(
                view: WebView,
                request: WebResourceRequest
            ): Boolean {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    view.loadUrl(request.url.toString())
                }
                return false
            }
        }

        webView!!.webChromeClient = object : WebChromeClient(){
            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                super.onProgressChanged(view, newProgress)
                if(newProgress >= 80){
                    rlWebSplash!!.visibility = View.GONE
                }
            }
        }
    }

    override fun onBackPressed() {
        if(webView!!.canGoBack()){
            webView!!.goBack()
        }
        else{
            if(::mInterstitialAd.isInitialized){
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                    mInterstitialAd.adListener = object: AdListener() {
                        override fun onAdClosed() {
                            super.onAdClosed()
                            finish()
                        }
                    }
                } else {
                    super.onBackPressed()
                }
            }
            else{
                super.onBackPressed()
            }
        }

    }

    fun loadWebSplash(){
        Glide.with(ivAppIcon!!.context)
            .load(appIcon)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(ivAppIcon!!)
    }

    public override fun onPause() {
        adView.pause()
        super.onPause()
    }

    // Called when returning to the activity
    public override fun onResume() {
        super.onResume()
        adView.resume()
    }

    // Called before the activity is destroyed
    public override fun onDestroy() {
        adView.destroy()
        super.onDestroy()
    }

}