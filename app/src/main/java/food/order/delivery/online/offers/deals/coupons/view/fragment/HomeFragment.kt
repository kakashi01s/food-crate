package food.order.delivery.online.offers.deals.coupons.view.fragment

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.facebook.ads.*
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
import food.order.delivery.online.offers.deals.coupons.view.adapter.CategoryStoresAdapter
import food.order.delivery.online.offers.deals.coupons.view.adapter.home.AllAppsAdapter
import food.order.delivery.online.offers.deals.coupons.view.adapter.home.TrendingAdapter
import food.order.delivery.online.offers.deals.coupons.view.listener.AllAppsItemClickListener
import food.order.delivery.online.offers.deals.coupons.view.listener.home.TrendingItemClickListener
import food.order.delivery.online.offers.deals.coupons.viewmodel.CategoryViewModel
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

    var rvCategoryStores: RecyclerView? = null
    var categoryStoresAdapter: CategoryStoresAdapter? = null
    var categoryViewModel: CategoryViewModel? = null

    var rvAllApps: RecyclerView? = null
    var allAppsAdapter: AllAppsAdapter? = null
    var homeViewModel: HomeViewModel? = null

    var rvTrending: RecyclerView? = null
    var trendingAdapter: TrendingAdapter? = null

    var firebaseRemoteConfig: FirebaseRemoteConfig? = null
    var firebaseAnalytics: FirebaseAnalytics? = null

    private var nativeAdFB1: NativeAd? = null
    private var nativeAdFB2: NativeAd? = null


    var dialog: Dialog? = null

    private var nativeAdFB3: NativeAd? = null
    private var nativeAdLayout: NativeAdLayout? = null
    private var adView: LinearLayout? = null


    var carouselImagesList: ArrayList<List<String>>? = ArrayList()
    var foodsList: ArrayList<List<String>>? = ArrayList()
    var shoppingList: ArrayList<List<String>>? = ArrayList()
    var dealsList: ArrayList<List<String>>? = ArrayList()
    var groceryList: ArrayList<List<String>>? = ArrayList()
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

        dialog = Dialog(context!!)

        firebaseRemoteConfig = FirebaseRemoteConfig.getInstance()

        setRecyclerView()

        homeViewModel = ViewModelProvider(activity!!).get(HomeViewModel::class.java)

        homeViewModel?.loadData()

        homeViewModel!!.foodLiveData.observe(this, Observer { t ->
            Log.d("TAG", "HomeFragment Live allAppsLiveData$t")
            foodsList!!.addAll(t!!)
        })

        homeViewModel!!.carouselImagesLiveData.observe(this, Observer { t ->
            Log.d("TAG", "HomeFragment Live carousel $t")
            carouselImagesList!!.addAll(t!!)
            onLoadCarouselImages()
        })

        homeViewModel!!.shoppingLiveData.observe(this, Observer { t ->
            Log.d("TAG", "HomeFragment Live trendingLiveData $t")
            shoppingList!!.addAll(t!!)
        })

        homeViewModel!!.groceryLiveData.observe(this, Observer { t ->
            Log.d("TAG", "HomeFragment Live trendingLiveData $t")
            groceryList!!.addAll(t!!)
        })

        homeViewModel!!.dealsLiveData.observe(this, Observer { t ->
            Log.d("TAG", "HomeFragment Live trendingLiveData $t")
            dealsList!!.addAll(t!!)
        })
        card_shopping!!.setOnClickListener {
            onShowStores(shoppingList!!,view)
        }

        card_food!!.setOnClickListener {
            onShowStores(foodsList!!,view)
        }
        card_grocery!!.setOnClickListener {
            onShowStores(groceryList!!,view)
        }
        card_deals!!.setOnClickListener {
            onShowStores(dealsList!!,view)
        }


        if (firebaseRemoteConfig!!.getBoolean(Constants().SHOW_ADS)) {
            onLoadFBNativeAd1(view!!, context!!)
            onLoadFBNativeAd2(view!!, context!!)

        }
    }



    fun initViews(view: View) {
        firebaseAnalytics = FirebaseAnalytics.getInstance(activity!!)
        carouselView = view.findViewById(R.id.cvHome)

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
    fun onShowStores(list: ArrayList<List<String>>, view: View) {
        dialog!!.setContentView(R.layout.dialog_show_stores)

        dialog!!.window!!.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT
        );

        rvCategoryStores = dialog!!.findViewById(R.id.rvCategoryStores)

        setRecyclerView()

        categoryStoresAdapter!!.setItems(list)
        if(firebaseRemoteConfig!!.getBoolean(Constants().SHOW_ADS)){
            onLoadFBNativeAdDialog(view, context!!, dialog!!)
        }

        dialog!!.show()

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
    fun onLoadFBNativeAd1(view: View, context: Context) {
        nativeAdFB1 = NativeAd(context, Constants().getFbNativeHome1())
        val nativeAdListener: NativeAdListener = object : NativeAdListener {
            override fun onError(p0: Ad?, p1: AdError?) {
                Log.d("TAG", "onError: onLoadFBNativeAd1 " + p1!!.errorMessage)
            }

            override fun onAdLoaded(ad: Ad?) {

                // Race condition, load() called again before last ad was displayed
                if (nativeAdFB1 == null || nativeAdFB1 !== ad) {
                    return
                }
                // Inflate Native Ad into Container

                // Add the Ad view into the ad container.
                nativeAdLayout = view.findViewById(R.id.native_ad_container_home_1)
                val inflater = LayoutInflater.from(context)
                // Inflate the Ad view.  The layout referenced should be the one you created in the last step.
                adView =
                    inflater.inflate(
                        R.layout.native_ad_layout,
                        nativeAdLayout,
                        false
                    ) as LinearLayout
                nativeAdLayout!!.addView(adView)

                inflateAd(nativeAdFB1!!, adView!!)

                val adChoicesContainer: LinearLayout = view.findViewById(R.id.ad_choices_container)
                val adOptionsView = AdOptionsView(context, nativeAdFB1, nativeAdLayout)
                adChoicesContainer.removeAllViews()
                adChoicesContainer.addView(adOptionsView, 0)
            }

            override fun onAdClicked(p0: Ad?) {
                Log.d("TAG", "onAdClicked: onLoadFBNativeAd1")
            }

            override fun onLoggingImpression(p0: Ad?) {
                Log.d("TAG", "onLoggingImpression: onLoadFBNativeAd1")
            }

            override fun onMediaDownloaded(p0: Ad?) {
                Log.d("TAG", "onMediaDownloaded: onLoadFBNativeAd1")
            }
        }

        nativeAdFB1!!.loadAd(
            nativeAdFB1!!.buildLoadAdConfig()
                .withAdListener(nativeAdListener)
                .build()
        );
    }

    fun onLoadFBNativeAd2(view: View,context: Context) {
        nativeAdFB2 = NativeAd(context, Constants().getFbNativeHome2())
        val nativeAdListener: NativeAdListener = object : NativeAdListener {
            override fun onError(p0: Ad?, p1: AdError?) {
                Log.d("TAG", "onError: onLoadFBNativeAd1 " + p1!!.errorMessage)
            }

            override fun onAdLoaded(ad: Ad?) {

                // Race condition, load() called again before last ad was displayed
                if (nativeAdFB2 == null || nativeAdFB2 !== ad) {
                    return
                }
                // Inflate Native Ad into Container

                // Add the Ad view into the ad container.
                nativeAdLayout = view.findViewById(R.id.native_ad_container_home_2)
                val inflater = LayoutInflater.from(context)
                // Inflate the Ad view.  The layout referenced should be the one you created in the last step.
                adView =
                    inflater.inflate(
                        R.layout.native_ad_layout,
                        nativeAdLayout,
                        false
                    ) as LinearLayout
                nativeAdLayout!!.addView(adView)

                inflateAd(nativeAdFB2!!, adView!!)

                val adChoicesContainer: LinearLayout = view.findViewById(R.id.ad_choices_container)
                val adOptionsView = AdOptionsView(context, nativeAdFB2, nativeAdLayout)
                adChoicesContainer.removeAllViews()
                adChoicesContainer.addView(adOptionsView, 0)
            }

            override fun onAdClicked(p0: Ad?) {
                Log.d("TAG", "onAdClicked: onLoadFBNativeAd3")
            }

            override fun onLoggingImpression(p0: Ad?) {
                Log.d("TAG", "onLoggingImpression: onLoadFBNativeAd1")
            }

            override fun onMediaDownloaded(p0: Ad?) {
                Log.d("TAG", "onMediaDownloaded: onLoadFBNativeAd1")
            }
        }

        nativeAdFB2!!.loadAd(
            nativeAdFB2!!.buildLoadAdConfig()
                .withAdListener(nativeAdListener)
                .build()
        );
    }
    fun onLoadFBNativeAdDialog(view: View, context: Context,dialog: Dialog) {
        nativeAdFB3 = NativeAd(context, Constants().getFbNativeTopicDailog())
        val nativeAdListener: NativeAdListener = object : NativeAdListener {
            override fun onError(p0: Ad?, p1: AdError?) {
                Log.d("TAG", "onError: onLoadFBNativeAd1 " + p1!!.errorMessage)
            }

            override fun onAdLoaded(ad: Ad?) {

                // Race condition, load() called again before last ad was displayed
                if (nativeAdFB3 == null || nativeAdFB3 !== ad) {
                    return
                }
                // Inflate Native Ad into Container

                // Add the Ad view into the ad container.
                nativeAdLayout = dialog.findViewById(R.id.native_ad_container_dialog)
                val inflater = LayoutInflater.from(context)
                // Inflate the Ad view.  The layout referenced should be the one you created in the last step.
                adView =
                    inflater.inflate(
                        R.layout.native_ad_layout,
                        nativeAdLayout,
                        false
                    ) as LinearLayout
                nativeAdLayout!!.addView(adView)

                inflateAd(nativeAdFB3!!, adView!!)

                val adChoicesContainer: LinearLayout = dialog.findViewById(R.id.ad_choices_container)
                val adOptionsView = AdOptionsView(context, nativeAdFB3, nativeAdLayout)
                adChoicesContainer.removeAllViews()
                adChoicesContainer.addView(adOptionsView, 0)
            }

            override fun onAdClicked(p0: Ad?) {
                Log.d("TAG", "onAdClicked: onLoadFBNativeAd1")
            }

            override fun onLoggingImpression(p0: Ad?) {
                Log.d("TAG", "onLoggingImpression: onLoadFBNativeAd1")
            }

            override fun onMediaDownloaded(p0: Ad?) {
                Log.d("TAG", "onMediaDownloaded: onLoadFBNativeAd1")
            }
        }

        nativeAdFB3!!.loadAd(
            nativeAdFB3!!.buildLoadAdConfig()
                .withAdListener(nativeAdListener)
                .build()
        );
    }

    override fun onDestroy() {
        homeViewModel?.reset()
        Log.d("TAG", "onDestroy: ")
        super.onDestroy()
    }
    private fun inflateAd(nativeAd: NativeAd, adView: LinearLayout) {
        nativeAd.unregisterView()

        // Add the AdOptionsView

        // Create native UI using the ad metadata.
        val nativeAdIcon: com.facebook.ads.MediaView = adView.findViewById(R.id.native_ad_icon)
        val nativeAdTitle: TextView = adView.findViewById(R.id.native_ad_title)
        val nativeAdMedia: com.facebook.ads.MediaView = adView.findViewById(R.id.native_ad_media)
        val nativeAdSocialContext: TextView = adView.findViewById(R.id.native_ad_social_context)
        val nativeAdBody: TextView = adView.findViewById(R.id.native_ad_body)
        val sponsoredLabel: TextView = adView.findViewById(R.id.native_ad_sponsored_label)
        val nativeAdCallToAction: Button = adView.findViewById(R.id.native_ad_call_to_action)

        // Set the Text.
        nativeAdTitle.text = nativeAd.advertiserName
        nativeAdBody.text = nativeAd.adBodyText
        nativeAdSocialContext.text = nativeAd.adSocialContext
        nativeAdCallToAction.visibility =
            if (nativeAd.hasCallToAction()) View.VISIBLE else View.INVISIBLE
        nativeAdCallToAction.text = nativeAd.adCallToAction
        sponsoredLabel.text = nativeAd.sponsoredTranslation

        // Create a list of clickable views
        val clickableViews: ArrayList<View> = ArrayList()
        clickableViews.add(nativeAdTitle)
        clickableViews.add(nativeAdCallToAction)

        // Register the Title and CTA button to listen for clicks.
        nativeAd.registerViewForInteraction(
            adView, nativeAdMedia, nativeAdIcon, clickableViews
        )
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
