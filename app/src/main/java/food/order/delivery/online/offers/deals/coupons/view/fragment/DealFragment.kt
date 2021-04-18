package food.order.delivery.online.offers.deals.coupons.view.fragment

import android.content.Context
import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.facebook.ads.*
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import food.order.delivery.online.offers.deals.coupons.buy.R
import food.order.delivery.online.offers.deals.coupons.utils.Constants
import food.order.delivery.online.offers.deals.coupons.view.MainActivity
import food.order.delivery.online.offers.deals.coupons.view.WebActivity
import food.order.delivery.online.offers.deals.coupons.view.adapter.DealsAdapter
import food.order.delivery.online.offers.deals.coupons.view.listener.deals.CouponInfoClickListener
import food.order.delivery.online.offers.deals.coupons.view.listener.deals.DealClickListener
import food.order.delivery.online.offers.deals.coupons.viewmodel.DealsViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DealFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DealFragment : Fragment(), CouponInfoClickListener, DealClickListener {
    // TODO: Rename and change types of parameters
    private var param1: Int? = null
    private var param2: String? = null

    var rvInvest: RecyclerView? = null
    var dealsAdapter: DealsAdapter? = null
    var dealsViewModel: DealsViewModel? = null
    var investDataList: ArrayList<List<String>>? = ArrayList()
    var firebaseAnalytics: FirebaseAnalytics? = null
    var firebaseRemoteConfig: FirebaseRemoteConfig? = null
    private var nativeAdFB1: NativeAd? = null
    private var nativeAdLayout: NativeAdLayout? = null
    private var adView: LinearLayout? = null

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
        return inflater.inflate(R.layout.fragment_invest, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(view)

        setRecyclerView()

        dealsViewModel = ViewModelProvider(activity!!).get(DealsViewModel::class.java)

        dealsViewModel?.loadData()

        dealsViewModel!!.dealsLiveData.observe(this, Observer { t ->
            Log.d("TAG", "onViewCreated: Deals ${t?.size}")
            investDataList?.addAll(t!!)
            dealsAdapter?.notifyDataSetChanged()
        })

    }
    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)

        if(isVisibleToUser){
            if(firebaseRemoteConfig == null){
                firebaseRemoteConfig = FirebaseRemoteConfig.getInstance()
            }
            if (firebaseRemoteConfig!!.getBoolean(Constants().SHOW_ADS)) {
                onLoadFBNativeAd1(view!!, context!!)
                }
        }

    }


    fun initViews(view: View){
        firebaseAnalytics = FirebaseAnalytics.getInstance(activity!!)
        rvInvest = view.findViewById(R.id.rvInvest)
    }

    fun setRecyclerView(){
        dealsAdapter = DealsAdapter(context, investDataList!!, this, this)
        rvInvest.apply {
            rvInvest?.layoutManager = GridLayoutManager(context, 2)
            rvInvest!!.addItemDecoration(SpacesItemDecoration(20))
            rvInvest?.adapter = dealsAdapter
        }
    }

    private class SpacesItemDecoration(private val space: Int) : ItemDecoration() {
        override fun getItemOffsets(
            outRect: Rect, view: View,
            parent: RecyclerView, state: RecyclerView.State
        ) {
            outRect.top = space
            outRect.bottom = space

            // Add top margin only for the first item to avoid double space between items
            if (parent.getChildLayoutPosition(view) % 2 == 0) {
                outRect.left = space
                outRect.right = space
            } else {
                outRect.left = space
                outRect.right = 0
            }
        }
    }
    fun onLoadFBNativeAd1(view: View, context: Context) {
        nativeAdFB1 = NativeAd(context, Constants().getFbNativeInvest())
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
                nativeAdLayout = view.findViewById(R.id.native_ad_container_invest)
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
        dealsViewModel?.reset()
        super.onDestroy()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment InvestFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: Int, param2: String) =
            DealFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onClickDeal(item: List<String>) {

        val bundle = Bundle()
        bundle.putString("title", item.get(1))
        bundle.putString("url", item.get(3))
        (activity as MainActivity?)!!.onUpdateLogEvent(bundle, "deals_visited", true)

        val intent: Intent? = Intent(activity, WebActivity::class.java)
        intent?.putExtra("title", item.get(1))
        intent?.putExtra("url", item.get(3))
        intent?.putExtra("app_icon", item.get(2))
        startActivity(intent)
    }

    override fun onClickCoupon(item: List<String>) {

        val bundle = Bundle()
        bundle.putString("title", item.get(1))
        bundle.putString("url", item.get(4))
        (activity as MainActivity?)!!.onUpdateLogEvent(bundle, "coupons_visited", true)

        val intent: Intent? = Intent(activity, WebActivity::class.java)
        intent?.putExtra("title", item.get(1))
        intent?.putExtra("url", item.get(4))
        intent?.putExtra("app_icon", item.get(2))
        startActivity(intent)
    }

}