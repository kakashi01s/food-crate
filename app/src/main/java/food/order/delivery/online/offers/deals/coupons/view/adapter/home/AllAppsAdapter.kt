package food.order.delivery.online.offers.deals.coupons.view.adapter.home

import android.content.Context
import android.view.ViewGroup
import food.order.delivery.online.offers.deals.coupons.buy.R
import food.order.delivery.online.offers.deals.coupons.base.adapter.GenericRecyclerAdapter
import food.order.delivery.online.offers.deals.coupons.view.listener.AllAppsItemClickListener
import food.order.delivery.online.offers.deals.coupons.view.viewholder.AllAppsViewHolder

class AllAppsAdapter(context: Context?) :
    GenericRecyclerAdapter<List<String>, AllAppsItemClickListener<List<String>>, AllAppsViewHolder>(context) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllAppsViewHolder {
        return AllAppsViewHolder(inflate(R.layout.card_all_apps_portal_layout,parent))
    }
}