package food.order.delivery.online.offers.deals.coupons.view.adapter

import android.content.Context
import android.view.ViewGroup
import food.order.delivery.online.offers.deals.coupons.buy.R
import food.order.delivery.online.offers.deals.coupons.base.adapter.GenericRecyclerAdapter
import food.order.delivery.online.offers.deals.coupons.view.listener.CategoryStoresItemClickListener
import food.order.delivery.online.offers.deals.coupons.view.viewholder.CategoryStoresViewHolder

class CategoryStoresAdapter(context: Context?) :
    GenericRecyclerAdapter<List<String>, CategoryStoresItemClickListener<List<String>>, CategoryStoresViewHolder>(context) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryStoresViewHolder {
        return CategoryStoresViewHolder(inflate(R.layout.card_all_apps_portal_layout,parent))
    }
}