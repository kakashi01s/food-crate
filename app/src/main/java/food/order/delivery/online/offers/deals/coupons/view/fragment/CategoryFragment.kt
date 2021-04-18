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
import com.google.android.gms.ads.formats.UnifiedNativeAd
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import food.order.delivery.online.offers.deals.coupons.buy.R
import food.order.delivery.online.offers.deals.coupons.base.BaseFragment
import food.order.delivery.online.offers.deals.coupons.utils.Constants
import food.order.delivery.online.offers.deals.coupons.view.MainActivity
import food.order.delivery.online.offers.deals.coupons.view.WebActivity
import food.order.delivery.online.offers.deals.coupons.view.adapter.CategoryStoresAdapter
import food.order.delivery.online.offers.deals.coupons.view.listener.CategoryStoresItemClickListener
import food.order.delivery.online.offers.deals.coupons.viewmodel.CategoryViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CategoryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CategoryFragment : BaseFragment(), CategoryStoresItemClickListener<List<String>> {
    // TODO: Rename and change types of parameters
    private var param1: Int? = null
    private var param2: String? = null


    var rvCategoryStores: RecyclerView? = null
    var categoryStoresAdapter: CategoryStoresAdapter? = null
    var categoryViewModel: CategoryViewModel? = null

    var firebaseAnalytics: FirebaseAnalytics? = null
    var firebaseRemoteConfig: FirebaseRemoteConfig? = null

    private var nativeAdFB1: NativeAd? = null
    private var nativeAdFB2: NativeAd? = null
    private var nativeAdFB3: NativeAd? = null
    private var nativeAdLayout: NativeAdLayout? = null
    private var adView: LinearLayout? = null

    var nativeAdCat1: UnifiedNativeAd? = null
    var nativeAdCat2: UnifiedNativeAd? = null
    var nativeAdCatDailog: UnifiedNativeAd? = null

    var llSuperMarts: LinearLayout? = null
    var llGroceries: LinearLayout? = null
    var llMedicines: LinearLayout? = null
    var llSupplements: LinearLayout? = null
    var llElectronics: LinearLayout? = null
    var llBeauty: LinearLayout? = null
    var llJewellery: LinearLayout? = null
    var llKitchenAppliances: LinearLayout? = null
    var llKidsLifestyle: LinearLayout? = null
    var llBabyToys: LinearLayout? = null
    var llLingerie: LinearLayout? = null
    var llMenInnerWear: LinearLayout? = null
    var llBooks: LinearLayout? = null
    var llFootwear: LinearLayout? = null

    var superMartList: ArrayList<List<String>>? = ArrayList()
    var groceriesList: ArrayList<List<String>>? = ArrayList()
    var medicinesList: ArrayList<List<String>>? = ArrayList()
    var supplementsList: ArrayList<List<String>>? = ArrayList()
    var electronicsList: ArrayList<List<String>>? = ArrayList()
    var beautyList: ArrayList<List<String>>? = ArrayList()
    var jewelleryList: ArrayList<List<String>>? = ArrayList()
    var kitchenAppliancesList: ArrayList<List<String>>? = ArrayList()
    var kidsLifestyleList: ArrayList<List<String>>? = ArrayList()
    var babyToysList: ArrayList<List<String>>? = ArrayList()
    var lingerieList: ArrayList<List<String>>? = ArrayList()
    var menInnerWearList: ArrayList<List<String>>? = ArrayList()
    var booksList: ArrayList<List<String>>? = ArrayList()
    var footwearList: ArrayList<List<String>>? = ArrayList()


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
        return inflater.inflate(R.layout.fragment_category, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(view)

        firebaseRemoteConfig = FirebaseRemoteConfig.getInstance()

        dialog = Dialog(context!!)

        categoryViewModel = ViewModelProvider(activity!!).get(CategoryViewModel::class.java)
        categoryViewModel?.loadData()

        categoryViewModel!!.superMartLiveData.observe(this, Observer { t ->
            Log.d("TAG", "onViewCreated: superMartLiveData $t")
            superMartList!!.addAll(t!!)
        })

        categoryViewModel!!.groceriesLiveData.observe(this, Observer { t ->
            Log.d("TAG", "onViewCreated: groceriesLiveData $t")
            groceriesList!!.addAll(t!!)
        })
        categoryViewModel!!.medicinesLiveData.observe(this, Observer { t ->
            Log.d("TAG", "onViewCreated: medicinesLiveData $t")
            medicinesList!!.addAll(t!!)
        })

        categoryViewModel!!.supplementsLiveData.observe(this, Observer { t ->
            Log.d("TAG", "onViewCreated: supplementsLiveData $t")
            supplementsList!!.addAll(t!!)
        })
        categoryViewModel!!.electronicsLiveData.observe(this, Observer { t ->
            Log.d("TAG", "onViewCreated: electronicsLiveData $t")
            electronicsList!!.addAll(t!!)
        })

        categoryViewModel!!.beautyLiveData.observe(this, Observer { t ->
            Log.d("TAG", "onViewCreated: beautyLiveData $t")
            beautyList!!.addAll(t!!)
        })
        categoryViewModel!!.jewelleryLiveData.observe(this, Observer { t ->
            Log.d("TAG", "onViewCreated: jewelleryLiveData $t")
            jewelleryList!!.addAll(t!!)
        })

        categoryViewModel!!.kitchenAppliancesLiveData
            .observe(this, Observer { t ->
                Log.d("TAG", "onViewCreated: kitchenAppliancesLiveData $t")
                kitchenAppliancesList!!.addAll(t!!)
            })
        categoryViewModel!!.kidsLifestyleLiveData.observe(this, Observer { t ->
            Log.d("TAG", "onViewCreated: kidsLifestyleLiveData $t")
            kidsLifestyleList!!.addAll(t!!)
        })

        categoryViewModel!!.babyToysLiveData.observe(this, Observer { t ->
            Log.d("TAG", "onViewCreated: babyToysLiveData $t")
            babyToysList!!.addAll(t!!)
        })
        categoryViewModel!!.lingerieLiveData.observe(this, Observer { t ->
            Log.d("TAG", "onViewCreated: lingerieLiveData $t")
            lingerieList!!.addAll(t!!)
        })

        categoryViewModel!!.menInnerWearLiveData.observe(this, Observer { t ->
            Log.d("TAG", "onViewCreated: menInnerWearLiveData $t")
            menInnerWearList!!.addAll(t!!)
        })
        categoryViewModel!!.booksLiveData.observe(this, Observer { t ->
            Log.d("TAG", "onViewCreated: booksLiveData $t")
            booksList!!.addAll(t!!)
        })

        categoryViewModel!!.footwearLiveData.observe(this, Observer { t ->
            Log.d("TAG", "onViewCreated: footwearLiveData $t")
            footwearList!!.addAll(t!!)
        })


        llSuperMarts!!.setOnClickListener {
            onShowStores(superMartList!!,view)
        }
        llGroceries!!.setOnClickListener {
            onShowStores(groceriesList!!,view)
        }
        llMedicines!!.setOnClickListener {
            onShowStores(medicinesList!!,view)
        }
        llSupplements!!.setOnClickListener {
            onShowStores(supplementsList!!,view)
        }
        llElectronics!!.setOnClickListener {
            onShowStores(electronicsList!!,view)
        }
        llBeauty!!.setOnClickListener {
            onShowStores(beautyList!!,view)
        }
        llJewellery!!.setOnClickListener {
            onShowStores(jewelleryList!!,view)
        }
        llKitchenAppliances!!.setOnClickListener {
            onShowStores(kitchenAppliancesList!!,view)
        }
        llKidsLifestyle!!.setOnClickListener {
            onShowStores(kidsLifestyleList!!,view)
        }
        llBabyToys!!.setOnClickListener {
            onShowStores(babyToysList!!,view)
        }
        llLingerie!!.setOnClickListener {
            onShowStores(lingerieList!!,view)
        }
        llMenInnerWear!!.setOnClickListener {
            onShowStores(menInnerWearList!!,view)
        }
        llBooks!!.setOnClickListener {
            onShowStores(booksList!!,view)
        }
        llFootwear!!.setOnClickListener {
            onShowStores(footwearList!!,view)
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

    fun initViews(view: View) {
        firebaseAnalytics = FirebaseAnalytics.getInstance(activity!!)
        llSuperMarts = view.findViewById(R.id.llSuperMarts)
        llGroceries = view.findViewById(R.id.llGroceries)
        llMedicines = view.findViewById(R.id.llMedicines)
        llSupplements = view.findViewById(R.id.llSupplements)
        llElectronics = view.findViewById(R.id.llElectronics)
        llBeauty = view.findViewById(R.id.llBeauty)
        llJewellery = view.findViewById(R.id.llJewellery)
        llKitchenAppliances = view.findViewById(R.id.llKitchenAppliances)
        llKidsLifestyle = view.findViewById(R.id.llKidsLifestyle)
        llBabyToys = view.findViewById(R.id.llBabyToys)
        llLingerie = view.findViewById(R.id.llLingerie)
        llMenInnerWear = view.findViewById(R.id.llMenInnerWear)
        llBooks = view.findViewById(R.id.llBooks)
        llFootwear = view.findViewById(R.id.llFootwear)

    }

    fun setRecyclerView() {
        categoryStoresAdapter = CategoryStoresAdapter(context)
        categoryStoresAdapter!!.setListener(this)
        rvCategoryStores.apply {
            rvCategoryStores?.layoutManager = GridLayoutManager(activity, 3)
            rvCategoryStores?.adapter = categoryStoresAdapter
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

    fun onLoadFBNativeAd1(view: View, context: Context) {
        nativeAdFB1 = NativeAd(context, Constants().getFbNativeCat1())
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
                nativeAdLayout = view.findViewById(R.id.native_ad_container_cat_1)
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

    fun onLoadFBNativeAd2(view: View, context: Context) {
        nativeAdFB2 = NativeAd(context, Constants().getFbNativeCat2())
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
                nativeAdLayout = view.findViewById(R.id.native_ad_container_cat_2)
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
        nativeAdCatDailog?.destroy()
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
            CategoryFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun CategoryStoresCardClick(item: List<String>) {
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