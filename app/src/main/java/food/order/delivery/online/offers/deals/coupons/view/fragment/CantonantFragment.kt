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
import androidx.recyclerview.widget.RecyclerView
import com.facebook.ads.*
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import food.order.delivery.online.offers.deals.coupons.base.BaseFragment
import food.order.delivery.online.offers.deals.coupons.utils.Constants
import food.order.delivery.online.offers.deals.coupons.view.adapter.CantonentStoresAdapter
import food.order.delivery.online.offers.deals.coupons.view.listener.CantonentStoresItemClickListener
import food.order.delivery.online.offers.deals.coupons.viewmodel.CantonentViewModel
import food.order.delivery.online.offers.deals.coupons.buy.R
import food.order.delivery.online.offers.deals.coupons.view.MainActivity
import food.order.delivery.online.offers.deals.coupons.view.WebActivity

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CategoryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CantonantFragment : BaseFragment(), CantonentStoresItemClickListener<List<String>> {
    // TODO: Rename and change types of parameters
    private var param1: Int? = null
    private var param2: String? = null


    var rvCategoryStores: RecyclerView? = null
    var categoryStoresAdapter: CantonentStoresAdapter? = null
    var categoryViewModel: CantonentViewModel? = null

    var firebaseRemoteConfig: FirebaseRemoteConfig? = null
    var firebaseAnalytics: FirebaseAnalytics? = null


    private var nativeAdFB1: NativeAd? = null
    private var nativeAdFB2: NativeAd? = null
    private var nativeAdFB3: NativeAd? = null
    private var nativeAdFB4: NativeAd? = null
    private var nativeAdFB5: NativeAd? = null

    private var nativeAdLayout: NativeAdLayout? = null
    private var adView: LinearLayout? = null


    //var llSuperMarts: LinearLayout? = null
    var llC1: LinearLayout? = null
    var llC2: LinearLayout? = null
    var llC3: LinearLayout? = null
    var llC4: LinearLayout? = null
    var llC5: LinearLayout? = null
    var llC6: LinearLayout? = null
    var llC7: LinearLayout? = null
    var llVideo: LinearLayout? = null

    var c1List: ArrayList<List<String>>? = ArrayList()
    var c2List: ArrayList<List<String>>? = ArrayList()
    var c3List: ArrayList<List<String>>? = ArrayList()
    var c4List: ArrayList<List<String>>? = ArrayList()
    var c5List: ArrayList<List<String>>? = ArrayList()
    var c6List: ArrayList<List<String>>? = ArrayList()
    var c7List: ArrayList<List<String>>? = ArrayList()
    var videoList: ArrayList<List<String>>? = ArrayList()

    var dialog: Dialog? = null
//
//    var nativeAdCat1: UnifiedNativeAd? = null
//    var nativeAdCat2: UnifiedNativeAd? = null

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
        return inflater.inflate(R.layout.fragment_contonent, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(view)

        firebaseRemoteConfig = FirebaseRemoteConfig.getInstance()

        dialog = Dialog(context!!)

        categoryViewModel = ViewModelProvider(activity!!).get(CantonentViewModel::class.java)
        categoryViewModel?.loadData()

        categoryViewModel!!.indiaLiveData.observe(this, Observer { t ->
            Log.d("TAG", "onViewCreated: indiaLiveData $t")
            c1List!!.addAll(t!!)
        })

        categoryViewModel!!.usaLiveData.observe(this, Observer { t ->
            Log.d("TAG", "onViewCreated: usaLiveData $t")
            c2List!!.addAll(t!!)
        })
        categoryViewModel!!.russiaLiveData.observe(this, Observer { t ->
            Log.d("TAG", "onViewCreated: russiaLiveData $t")
            c3List!!.addAll(t!!)
        })

        categoryViewModel!!.pakistanLiveData.observe(this, Observer { t ->
            Log.d("TAG", "onViewCreated: pakistanLiveData $t")
            c4List!!.addAll(t!!)
        })
        categoryViewModel!!.chinaLiveData.observe(this, Observer { t ->
            Log.d("TAG", "onViewCreated: chinaLiveData $t")
            c5List!!.addAll(t!!)
        })

        categoryViewModel!!.germanyLiveData.observe(this, Observer { t ->
            Log.d("TAG", "onViewCreated: germanyLiveData $t")
            c6List!!.addAll(t!!)
        })
        categoryViewModel!!.turkeyLiveData.observe(this, Observer { t ->
            Log.d("TAG", "onViewCreated: turkeyLiveData $t")
            c7List!!.addAll(t!!)
        })
        categoryViewModel!!.videoLiveData.observe(this, Observer { t ->
            Log.d("TAG", "onViewCreated: turkeyLiveData $t")
            videoList!!.addAll(t!!)
        })


        llC1!!.setOnClickListener {
            onShowStores(c1List!!,view)
        }
        llC2!!.setOnClickListener {
            onShowStores(c2List!!,view)
        }
        llC3!!.setOnClickListener {
            onShowStores(c3List!!,view)
        }
        llC4!!.setOnClickListener {
            onShowStores(c4List!!,view)
        }
        llC5!!.setOnClickListener {
            onShowStores(c5List!!,view)
        }
        llC6!!.setOnClickListener {
            onShowStores(c6List!!,view)
        }
        llC7!!.setOnClickListener {
            onShowStores(c7List!!,view)
        }

        llVideo!!.setOnClickListener {
            onShowStores(videoList!!,view)
        }


    }
    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)

        if(isVisibleToUser){
            if(firebaseRemoteConfig == null){
                firebaseRemoteConfig = FirebaseRemoteConfig.getInstance()
            }
            if (firebaseRemoteConfig!!.getBoolean(Constants().SHOW_ADS)) {
                onLoadFBNativeAd1(view!!, context!!)
                onLoadFBNativeAd2(view!!, context!!)
            }
        }

    }



    fun initViews(view: View){
        firebaseAnalytics = FirebaseAnalytics.getInstance(activity!!)
        llC1 = view.findViewById(R.id.llC1)
        llC2 = view.findViewById(R.id.llC2)
        llC3 = view.findViewById(R.id.llC3)
        llC4 = view.findViewById(R.id.llC4)
        llC5 = view.findViewById(R.id.llC5)
        llC6 = view.findViewById(R.id.llC6)
        llC7 = view.findViewById(R.id.llc7)
        llVideo = view.findViewById(R.id.llVideo)

    }

    fun setRecyclerView(){
        categoryStoresAdapter = CantonentStoresAdapter(context)
        categoryStoresAdapter!!.setListener(this)
        rvCategoryStores.apply {
            rvCategoryStores?.layoutManager = GridLayoutManager(activity, 3)
            rvCategoryStores?.adapter = categoryStoresAdapter
        }
    }

    fun onShowStores(list: ArrayList<List<String>>, view: View){
        dialog!!.setContentView(R.layout.continental_dialog_show_stores)

        dialog!!.window!!.setLayout(WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT);

        rvCategoryStores = dialog!!.findViewById(R.id.rvCategoryStores)

        setRecyclerView()

        categoryStoresAdapter!!.setItems(list)
        if(firebaseRemoteConfig!!.getBoolean(Constants().SHOW_ADS)){
            onLoadFBNativeAdDialog(view, context!!, dialog!!)
        }


        dialog!!.show()

    }


    fun onLoadFBNativeAd1(view: View, context: Context) {
        nativeAdFB1 = NativeAd(context, Constants().getFbNativeCantonment1())
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
                nativeAdLayout = view.findViewById(R.id.native_ad_container_cantonment_1)
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
        nativeAdFB2 = NativeAd(context, Constants().getFbNativeCantonment2())
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
                nativeAdLayout = view.findViewById(R.id.native_ad_container_cantonment_3)
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
        nativeAdFB5 = NativeAd(context, Constants().getFbNativeTopicDailog())
        val nativeAdListener: NativeAdListener = object : NativeAdListener {
            override fun onError(p0: Ad?, p1: AdError?) {
                Log.d("TAG", "onError: onLoadFBNativeAd1 " + p1!!.errorMessage)
            }

            override fun onAdLoaded(ad: Ad?) {

                // Race condition, load() called again before last ad was displayed
                if (nativeAdFB5 == null || nativeAdFB5 !== ad) {
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

                inflateAd(nativeAdFB5!!, adView!!)

                val adChoicesContainer: LinearLayout = dialog.findViewById(R.id.ad_choices_container)
                val adOptionsView = AdOptionsView(context, nativeAdFB5, nativeAdLayout)
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

        nativeAdFB5!!.loadAd(
            nativeAdFB5!!.buildLoadAdConfig()
                .withAdListener(nativeAdListener)
                .build()
        );
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



    override fun onDestroy() {
        categoryViewModel?.reset()
        super.onDestroy()
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CategoryFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: Int, param2: String) =
            CantonantFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }


    override fun CantonantStoresCardClick(item: List<String>) {
        Log.d("TAG", "onAllBrokersCardClick: " + item.get(1))

        val bundle = Bundle()
        bundle.putString("title", item.get(1))
        bundle.putString("url", item.get(2))
        (activity as MainActivity?)!!.onUpdateLogEvent(bundle,"brokers_visited",true)

        val intent: Intent? = Intent(activity, WebActivity::class.java)
        intent?.putExtra("title", item.get(1))
        intent?.putExtra("url", item.get(2))
        startActivity(intent)
    }
}