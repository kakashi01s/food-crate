package food.order.delivery.online.offers.deals.coupons.view

import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.firebase.analytics.FirebaseAnalytics
import food.order.delivery.online.offers.deals.coupons.base.BaseActivity
import food.order.delivery.online.offers.deals.coupons.buy.BuildConfig
import food.order.delivery.online.offers.deals.coupons.buy.R
import food.order.delivery.online.offers.deals.coupons.utils.CustomViewPager
import food.order.delivery.online.offers.deals.coupons.utils.ForceUpdateChecker
import food.order.delivery.online.offers.deals.coupons.view.adapter.UsefulAppsAdapter
import food.order.delivery.online.offers.deals.coupons.view.listener.UsefulAppsClickListener
import food.order.delivery.online.offers.deals.coupons.viewmodel.CategoryViewModel
import food.order.delivery.online.offers.deals.coupons.viewmodel.DealsViewModel
import food.order.delivery.online.offers.deals.coupons.viewmodel.HomeViewModel
import food.order.delivery.online.offers.deals.coupons.viewpager.AppPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.trending_app_fragment.*

class MainActivity : BaseActivity(), ForceUpdateChecker.OnUpdateNeededListener,
    UsefulAppsClickListener<List<String>> {

    var viewPager: CustomViewPager? = null
    var viewPagerTab: TabLayout? =null
    var fragmentPagerAdapter: FragmentPagerAdapter ?= null
    var homeViewModel: HomeViewModel? = null
    var dealsViewModel: DealsViewModel? = null
    var categoryViewModel: CategoryViewModel? = null
    var firebaseAnalytics: FirebaseAnalytics? = null

    var rvUsefullApps: RecyclerView? = null
    var usefulAppsAdapter: UsefulAppsAdapter? = null
    var usefulAppsList: ArrayList<List<String>>? = ArrayList()

    override val bindingVariable: Int
        get() = 0
    override val layoutId: Int
        get() = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()

        setupViewPager()

        ForceUpdateChecker().with(this)!!.onUpdateNeeded(this).check()

        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        dealsViewModel = ViewModelProvider(this).get(DealsViewModel::class.java)
        categoryViewModel = ViewModelProvider(this).get(CategoryViewModel::class.java)

        homeViewModel!!.loadData()

        homeViewModel!!.usefulappsLiveData.observe(this, Observer { t ->
            Log.d("TAG", "HomeFragment Live UsefulAppsLiveData$t")
            usefulAppsList?.clear()
            usefulAppsList?.addAll(t!!)
        })

        viewPagerTab!!.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                Log.d("TAG", "onTabSelected: " + tab.position)
                val bundleAppUsage = Bundle()
                bundleAppUsage.putString("tab_click", tab.text.toString())
                onUpdateLogEvent(bundleAppUsage,"app_usage",false)
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })

    }

    fun initViews(){
        firebaseAnalytics = FirebaseAnalytics.getInstance(this)
        viewPager = findViewById(R.id.vpPager)
        viewPagerTab = findViewById(R.id.view_pager_tab)
    }

    fun usefulAppsButton(view: View) {
        val dialog = Dialog(this, R.style.DialogTheme)
        dialog.setContentView(R.layout.trending_app_fragment)
        rvUsefullApps = dialog.findViewById(R.id.rvUsefulApps)
        setRecyclerView()
        usefulAppsAdapter!!.setItems(usefulAppsList)
        dialog.show()
    }

    fun setRecyclerView() {
        Log.d("TAG", "HomeFragment Live RecyclerView")
        usefulAppsAdapter = UsefulAppsAdapter(this@MainActivity)
        usefulAppsAdapter!!.setListener(this)
        rvUsefullApps.apply {
            Log.d("Dialog", "ApplyOK")
            rvUsefullApps?.layoutManager = GridLayoutManager(this@MainActivity, 3)
            rvUsefullApps?.adapter = usefulAppsAdapter
        }
    }
    fun setupViewPager(){
        fragmentPagerAdapter = AppPagerAdapter(supportFragmentManager)
        viewPager!!.adapter = fragmentPagerAdapter
        val limit = if ((fragmentPagerAdapter as AppPagerAdapter).getCount() > 1) (fragmentPagerAdapter as AppPagerAdapter).getCount() - 1 else 1
        viewPager!!.offscreenPageLimit = limit;
        viewPager!!.currentItem = 1;

        viewPager!!.setSwipePagingEnabled(false)

        viewPagerTab!!.setupWithViewPager(viewPager)

    }

    override fun onDestroy() {
        homeViewModel?.reset()
        dealsViewModel?.reset()
        categoryViewModel?.reset()
        super.onDestroy()
    }

    override fun onUpdateNeeded(updateUrl: String?) {
        val dialog = AlertDialog.Builder(this)
            .setTitle("New version available")
            .setMessage("Please, update app to new version to continue shopping.")
            .setPositiveButton(
                "Update"
            ) { dialog, which -> redirectStore(updateUrl!!) }.setNegativeButton(
                "No, thanks"
            ) { dialog, which -> finish() }.create()
        dialog.show()

    }

    private fun redirectStore(updateUrl: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(updateUrl))
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }

    fun onUpdateLogEvent(bundle: Bundle, eventName: String, isUrlVisited: Boolean){
        Log.d("TAG", "onUpdateLogEvent: ")
        if(BuildConfig.DEBUG){
            return
        }
        else{
            if(isUrlVisited){
                firebaseAnalytics!!.logEvent(eventName, bundle)
                firebaseAnalytics!!.logEvent("url_visited", bundle)
            }
            else
                firebaseAnalytics!!.logEvent(eventName, bundle)
        }
    }

    override fun UsefulAppsCardClick(item: List<String>) {
        Log.d("TAG", "onAllBrokersCardClick: " + item.get(1))

        val bundle = Bundle()
        bundle.putString("title", item.get(1))
        bundle.putString("url", item.get(2))
        (this as MainActivity?)!!.onUpdateLogEvent(bundle,"brokers_visited",true)

        val intent: Intent? = Intent(this, WebActivity::class.java)
        intent?.putExtra("title", item.get(1))
        intent?.putExtra("url", item.get(2))
        startActivity(intent)
    }

}
