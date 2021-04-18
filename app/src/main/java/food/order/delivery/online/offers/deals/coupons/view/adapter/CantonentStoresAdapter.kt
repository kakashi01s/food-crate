package food.order.delivery.online.offers.deals.coupons.view.adapter

import android.content.Context
import android.view.ViewGroup
import food.order.delivery.online.offers.deals.coupons.base.adapter.GenericRecyclerAdapter
import food.order.delivery.online.offers.deals.coupons.buy.R
import food.order.delivery.online.offers.deals.coupons.view.listener.CantonentStoresItemClickListener
import food.order.delivery.online.offers.deals.coupons.view.viewholder.CantonentStoresViewHolder

class CantonentStoresAdapter(context: Context?) :
    GenericRecyclerAdapter<List<String>, CantonentStoresItemClickListener<List<String>>, CantonentStoresViewHolder>(context) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CantonentStoresViewHolder {
        return CantonentStoresViewHolder(inflate(R.layout.card_all_apps_portal_layout,parent))
    }
}