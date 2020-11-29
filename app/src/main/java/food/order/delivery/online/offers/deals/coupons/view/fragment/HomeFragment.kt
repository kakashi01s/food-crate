package food.order.delivery.online.offers.deals.coupons.view.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdLoader
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.formats.MediaView
import com.google.android.gms.ads.formats.UnifiedNativeAd
import com.google.android.gms.ads.formats.UnifiedNativeAdView
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.synnapps.carouselview.CarouselView
import com.synnapps.carouselview.ImageClickListener
import com.synnapps.carouselview.ImageListener
import kotlinx.android.synthetic.main.fragment_home.*
import food.order.delivery.online.offers.deals.coupons.buy.R
import food.order.delivery.online.offers.deals.coupons.base.BaseFragment
import food.order.delivery.online.offers.deals.coupons.utils.Constants
import food.order.delivery.online.offers.deals.coupons.view.MainActivity
import food.order.delivery.online.offers.deals.coupons.view.WebActivity
import food.order.delivery.online.offers.deals.coupons.view.adapter.home.AllAppsAdapter
import food.order.delivery.online.offers.deals.coupons.view.adapter.home.TrendingAdapter
import food.order.delivery.online.offers.deals.coupons.view.listener.AllAppsItemClickListener
import food.order.delivery.online.offers.deals.coupons.view.listener.home.TrendingItemClickListener
import food.order.delivery.online.offers.deals.coupons.viewmodel.HomeViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentHome.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentHome : BaseFragment(), AllAppsItemClickListener<List<String>>,
    TrendingItemClickListener<List<String>> {
    // TODO: Rename and change types of parameters
    private var param1: Int? = null
    private var param2: String? = null

    var carouselView: CarouselView? = null

    var rvAllApps: RecyclerView? = null
    var allAppsAdapter: AllAppsAdapter? = null
    var homeViewModel: HomeViewModel? = null

    var rvTrending: RecyclerView? = null
    var trendingAdapter: TrendingAdapter? = null

    var firebaseRemoteConfig: FirebaseRemoteConfig? = null
    var firebaseAnalytics: FirebaseAnalytics? = null

    var nativeAdHome1: UnifiedNativeAd? = null
    var nativeAdHome2: UnifiedNativeAd? = null

    var carouselImagesList: ArrayList<List<String>>? = ArrayList()
//
//    var nativeAdHome1: UnifiedNativeAd? = null
//    var nativeAdHome2: UnifiedNativeAd? = null

    override val bindingVariable: Int
        get() = 0
    override val layoutId: Int
        get() = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getInt(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(view)

        firebaseRemoteConfig = FirebaseRemoteConfig.getInstance()

        setRecyclerView()

        homeViewModel = ViewModelProvider(activity!!).get(HomeViewModel::class.java)

        homeViewModel?.loadData()

        homeViewModel!!.allAppsLiveData.observe(this, Observer { t ->
            Log.d("TAG", "HomeFragment Live allAppsLiveData$t")
            allAppsAdapter?.setItems(t)
        })

        homeViewModel!!.carouselImagesLiveData.observe(this, Observer { t ->
            Log.d("TAG", "HomeFragment Live carousel $t")
            carouselImagesList!!.addAll(t!!)
            onLoadCarouselImages()
        })

        homeViewModel!!.trendingLiveData.observe(this, Observer { t ->
            Log.d("TAG", "HomeFragment Live trendingLiveData $t")
            trendingAdapter!!.setItems(t)
        })


        if(firebaseRemoteConfig!!.getBoolean(Constants().SHOW_ADS)){
            onLoadNativeAd1()
            onLoadNativeAd2()
        }

    }

    fun initViews(view: View) {
        firebaseAnalytics = FirebaseAnalytics.getInstance(activity!!)
        carouselView = view.findViewById(R.id.cvHome)
        rvAllApps = view.findViewById(R.id.rvAllApps)
        rvTrending = view.findViewById(R.id.rvTrending)
    }

    fun setRecyclerView() {
        allAppsAdapter = AllAppsAdapter(context)
        allAppsAdapter!!.setListener(this)
        rvAllApps.apply {
            rvAllApps?.layoutManager = GridLayoutManager(activity, 3)
            rvAllApps?.adapter = allAppsAdapter
        }

        trendingAdapter = TrendingAdapter(context)
        trendingAdapter!!.setListener(this)
        rvTrending.apply {
            rvTrending?.layoutManager = LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false)
            rvTrending?.adapter = trendingAdapter
        }

    }

    fun onLoadCarouselImages(){
        Log.d("TAG", "onLoadCarouselImages: " + carouselImagesList!!.size)

        carouselView!!.setImageListener(imageListener)

        carouselView!!.setImageClickListener(imageClickListener)

        carouselView!!.pageCount = carouselImagesList!!.size

    }

    var imageClickListener: ImageClickListener = ImageClickListener { position ->
        val intent: Intent? = Intent(activity, WebActivity::class.java)
            intent?.putExtra("title", carouselImagesList!![position][1])
            intent?.putExtra("url", carouselImagesList!![position][2])
            intent?.putExtra("app_icon", carouselImagesList!![position][4])

            val bundle = Bundle()
            bundle.putString("title", carouselImagesList!![position][1])
            bundle.putString("url", carouselImagesList!![position][2])

            (activity as MainActivity?)!!.onUpdateLogEvent(bundle, "carousel_images_visited", true)


            startActivity(intent)
    }


    var imageListener: ImageListener = ImageListener { position, imageView ->
        Log.d("TAG", "onLoadCarouselImages: position " + position +" data "+ carouselImagesList!![position][3])
        Glide.with(imageView.context)
            .load(carouselImagesList!![position][3])
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(imageView)
    }


    fun onLoadNativeAd1() {

        val builder = AdLoader.Builder(context, Constants().getNativeHome1())

        builder.forUnifiedNativeAd { unifiedNativeAd ->
            // OnUnifiedNativeAdLoadedListener implementation.
            val adView = layoutInflater
                .inflate(R.layout.native_ads_layout, null) as UnifiedNativeAdView
            nativeAdHome1?.destroy()
            nativeAdHome1 = unifiedNativeAd
            populateUnifiedNativeAdView(unifiedNativeAd, adView)
            ad_frame.removeAllViews()
            ad_frame.addView(adView)
        }

        val adLoader = builder.withAdListener(object : AdListener() {
            override fun onAdFailedToLoad(errorCode: Int) {
                Log.d("TAG", "onAdFailedToLoad: Failed to load native ad: $errorCode")
            }
        }).build()

        adLoader.loadAd(AdRequest.Builder().build())

    }

    fun onLoadNativeAd2() {

        val builder = AdLoader.Builder(context, Constants().getNativeHome2())

        builder.forUnifiedNativeAd { unifiedNativeAd ->
            // OnUnifiedNativeAdLoadedListener implementation.
            val adView = layoutInflater
                .inflate(R.layout.native_ads_layout, null) as UnifiedNativeAdView
            nativeAdHome2?.destroy()
            nativeAdHome2 = unifiedNativeAd
            populateUnifiedNativeAdView(unifiedNativeAd, adView)
            ad_frame_2.removeAllViews()
            ad_frame_2.addView(adView)
        }

        val adLoader = builder.withAdListener(object : AdListener() {
            override fun onAdFailedToLoad(errorCode: Int) {
                Log.d("TAG", "onAdFailedToLoad: Failed to load native ad: $errorCode")
            }
        }).build()

        adLoader.loadAd(AdRequest.Builder().build())

    }


    fun populateUnifiedNativeAdView(nativeAd: UnifiedNativeAd, adView: UnifiedNativeAdView) {
//        val headlineView = adView.findViewById<TextView>(R.id.ad_headline)
//        headlineView.text = nativeAd.headline
//        adView.headlineView = headlineView
//
//        val bodyView = adView.findViewById<TextView>(R.id.ad_body)
//        bodyView.text = nativeAd.body
//        adView.bodyView = bodyView

        adView.mediaView = adView.findViewById<MediaView>(R.id.ad_media)

        // Set other ad assets.
        adView.headlineView = adView.findViewById(R.id.ad_headline)
        adView.bodyView = adView.findViewById(R.id.ad_body)
        adView.callToActionView = adView.findViewById(R.id.ad_call_to_action)
        adView.iconView = adView.findViewById(R.id.ad_app_icon)
        adView.priceView = adView.findViewById(R.id.ad_price)
        adView.starRatingView = adView.findViewById(R.id.ad_stars)
        adView.storeView = adView.findViewById(R.id.ad_store)
        adView.advertiserView = adView.findViewById(R.id.ad_advertiser)

        // The headline and media content are guaranteed to be in every UnifiedNativeAd.
        (adView.headlineView as TextView).text = nativeAd.headline
        adView.mediaView.setImageScaleType(ImageView.ScaleType.FIT_XY)
        adView.mediaView.setMediaContent(nativeAd.mediaContent)

        // These assets aren't guaranteed to be in every UnifiedNativeAd, so it's important to
        // check before trying to display them.
        if (nativeAd.body == null) {
            adView.bodyView.visibility = View.INVISIBLE
        } else {
            adView.bodyView.visibility = View.VISIBLE
            (adView.bodyView as TextView).text = nativeAd.body
        }

        if (nativeAd.callToAction == null) {
            adView.callToActionView.visibility = View.INVISIBLE
        } else {
            adView.callToActionView.visibility = View.VISIBLE
            (adView.callToActionView as Button).text = nativeAd.callToAction
        }

        if (nativeAd.icon == null) {
            adView.iconView.visibility = View.GONE
        } else {
            (adView.iconView as ImageView).setImageDrawable(
                nativeAd.icon.drawable
            )
            adView.iconView.visibility = View.VISIBLE
        }

        if (nativeAd.price == null) {
            adView.priceView.visibility = View.INVISIBLE
        } else {
            adView.priceView.visibility = View.VISIBLE
            (adView.priceView as TextView).text = nativeAd.price
        }

        if (nativeAd.store == null) {
            adView.storeView.visibility = View.INVISIBLE
        } else {
            adView.storeView.visibility = View.VISIBLE
            (adView.storeView as TextView).text = nativeAd.store
        }

        if (nativeAd.starRating == null) {
            adView.starRatingView.visibility = View.INVISIBLE
        } else {
            (adView.starRatingView as RatingBar).rating = nativeAd.starRating!!.toFloat()
            adView.starRatingView.visibility = View.VISIBLE
        }

        if (nativeAd.advertiser == null) {
            adView.advertiserView.visibility = View.INVISIBLE
        } else {
            (adView.advertiserView as TextView).text = nativeAd.advertiser
            adView.advertiserView.visibility = View.VISIBLE
        }

        // This method tells the Google Mobile Ads SDK that you have finished populating your
        // native ad view with this native ad.
        adView.setNativeAd(nativeAd)

    }

    override fun onDestroy() {
        nativeAdHome1?.destroy()
        nativeAdHome2?.destroy()
        homeViewModel?.reset()
        Log.d("TAG", "onDestroy: ")
        super.onDestroy()
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentHome.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: Int, param2: String) =
            FragmentHome().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onAllCardClick(item: List<String>) {
        Log.d("TAG", "onAllCardClick: " + item.get(1))
        val intent: Intent? = Intent(activity, WebActivity::class.java)
        intent?.putExtra("title", item.get(1))
        intent?.putExtra("url", item.get(2))
        intent?.putExtra("app_icon", item.get(3))

        val bundle = Bundle()
        bundle.putString("title", item.get(1))
        bundle.putString("url", item.get(2))

        (activity as MainActivity?)!!.onUpdateLogEvent(bundle, "all_apps_visited", true)


        startActivity(intent)
    }

    override fun onTrendingClickListener(item: List<String>) {
        val intent: Intent? = Intent(activity, WebActivity::class.java)
        intent?.putExtra("title", item.get(1))
        intent?.putExtra("url", item.get(2))
        intent?.putExtra("app_icon", item.get(4))

        val bundle = Bundle()
        bundle.putString("title", item.get(1))
        bundle.putString("url", item.get(2))

        (activity as MainActivity?)!!.onUpdateLogEvent(bundle, "trending_visited", true)


        startActivity(intent)
    }
}
