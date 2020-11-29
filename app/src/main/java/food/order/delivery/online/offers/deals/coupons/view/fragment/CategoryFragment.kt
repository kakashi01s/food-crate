package food.order.delivery.online.offers.deals.coupons.view.fragment

import android.app.Dialog
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
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdLoader
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.formats.MediaView
import com.google.android.gms.ads.formats.UnifiedNativeAd
import com.google.android.gms.ads.formats.UnifiedNativeAdView
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import kotlinx.android.synthetic.main.fragment_category.*
import food.order.delivery.online.offers.deals.coupons.buy.R
import food.order.delivery.online.offers.deals.coupons.base.BaseFragment
import food.order.delivery.online.offers.deals.coupons.model.category.Calculator
import food.order.delivery.online.offers.deals.coupons.model.category.TechnicalChart
import food.order.delivery.online.offers.deals.coupons.utils.Constants
import food.order.delivery.online.offers.deals.coupons.view.MainActivity
import food.order.delivery.online.offers.deals.coupons.view.WebActivity
import food.order.delivery.online.offers.deals.coupons.view.adapter.CategoryStoresAdapter
import food.order.delivery.online.offers.deals.coupons.view.listener.CategoryStoresItemClickListener
import food.order.delivery.online.offers.deals.coupons.view.viewholder.CalculatorViewHolder
import food.order.delivery.online.offers.deals.coupons.view.viewholder.TechnicalChartViewHolder
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

    var rvCalculator: RecyclerView? = null
    var rvTechnicalChart: RecyclerView? = null

    var chartReference: DatabaseReference? = null
    var calculatorReference: DatabaseReference? = null
    var database: FirebaseDatabase? = null
    var calculateRecyclerAdapter: FirebaseRecyclerAdapter<Calculator, CalculatorViewHolder>? = null
    var chartRecyclerAdapter: FirebaseRecyclerAdapter<TechnicalChart, TechnicalChartViewHolder>? = null

    var rvCategoryStores: RecyclerView? = null
    var categoryStoresAdapter: CategoryStoresAdapter? = null
    var categoryViewModel: CategoryViewModel? = null

    var firebaseRemoteConfig: FirebaseRemoteConfig? = null
    var firebaseAnalytics: FirebaseAnalytics? = null

    var nativeAdCat1: UnifiedNativeAd? = null
    var nativeAdCat2: UnifiedNativeAd? = null

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
            onShowStores(superMartList!!)
        }
        llGroceries!!.setOnClickListener {
            onShowStores(groceriesList!!)
        }
        llMedicines!!.setOnClickListener {
            onShowStores(medicinesList!!)
        }
        llSupplements!!.setOnClickListener {
            onShowStores(supplementsList!!)
        }
        llElectronics!!.setOnClickListener {
            onShowStores(electronicsList!!)
        }
        llBeauty!!.setOnClickListener {
            onShowStores(beautyList!!)
        }
        llJewellery!!.setOnClickListener {
            onShowStores(jewelleryList!!)
        }
        llKitchenAppliances!!.setOnClickListener {
            onShowStores(kitchenAppliancesList!!)
        }
        llKidsLifestyle!!.setOnClickListener {
            onShowStores(kidsLifestyleList!!)
        }
        llBabyToys!!.setOnClickListener {
            onShowStores(babyToysList!!)
        }
        llLingerie!!.setOnClickListener {
            onShowStores(lingerieList!!)
        }
        llMenInnerWear!!.setOnClickListener {
            onShowStores(menInnerWearList!!)
        }
        llBooks!!.setOnClickListener {
            onShowStores(booksList!!)
        }
        llFootwear!!.setOnClickListener {
            onShowStores(footwearList!!)
        }



        if(firebaseRemoteConfig!!.getBoolean(Constants().SHOW_ADS)){
            onLoadNativeAd1()
            onLoadNativeAd2()
        }

    }

    fun initViews(view: View){
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

    fun setRecyclerView(){
        categoryStoresAdapter = CategoryStoresAdapter(context)
        categoryStoresAdapter!!.setListener(this)
        rvCategoryStores.apply {
            rvCategoryStores?.layoutManager = GridLayoutManager(activity, 3)
            rvCategoryStores?.adapter = categoryStoresAdapter
        }
    }

    fun onShowStores(list: ArrayList<List<String>>){
        dialog!!.setContentView(R.layout.dialog_show_stores)

        dialog!!.window!!.setLayout(WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT);

        rvCategoryStores = dialog!!.findViewById(R.id.rvCategoryStores)

        setRecyclerView()

        categoryStoresAdapter!!.setItems(list)

        dialog!!.show()

    }

    fun onLoadNativeAd1(){

        val builder = AdLoader.Builder(context, Constants().getNativeCat1())

        builder.forUnifiedNativeAd { unifiedNativeAd ->
            // OnUnifiedNativeAdLoadedListener implementation.
            val adView = layoutInflater
                .inflate(R.layout.native_ads_layout, null) as UnifiedNativeAdView
            nativeAdCat1?.destroy()
            nativeAdCat1 = unifiedNativeAd
            populateUnifiedNativeAdView(unifiedNativeAd, adView)
            ad_frame_cat_1.removeAllViews()
            ad_frame_cat_1.addView(adView)
        }

        val adLoader = builder.withAdListener(object : AdListener() {
            override fun onAdFailedToLoad(errorCode: Int) {
                Log.d("TAG", "onAdFailedToLoad: Failed to load native ad: $errorCode")
            }
        }).build()

        adLoader.loadAd(AdRequest.Builder().build())

    }

    fun onLoadNativeAd2(){

        val builder = AdLoader.Builder(context, Constants().getNativeCat2())

        builder.forUnifiedNativeAd { unifiedNativeAd ->
            // OnUnifiedNativeAdLoadedListener implementation.
            val adView = layoutInflater
                .inflate(R.layout.native_ads_layout, null) as UnifiedNativeAdView
            nativeAdCat2?.destroy()
            nativeAdCat2 = unifiedNativeAd
            populateUnifiedNativeAdView(unifiedNativeAd, adView)
            ad_frame_cat_2.removeAllViews()
            ad_frame_cat_2.addView(adView)
        }

        val adLoader = builder.withAdListener(object : AdListener() {
            override fun onAdFailedToLoad(errorCode: Int) {
                Log.d("TAG", "onAdFailedToLoad: Failed to load native ad: $errorCode")
            }
        }).build()

        adLoader.loadAd(AdRequest.Builder().build())

    }

    fun populateUnifiedNativeAdView(nativeAd: UnifiedNativeAd, adView: UnifiedNativeAdView){
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
        nativeAdCat1?.destroy()
        nativeAdCat2?.destroy()
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