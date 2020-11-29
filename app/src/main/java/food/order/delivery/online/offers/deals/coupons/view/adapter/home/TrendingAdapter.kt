package food.order.delivery.online.offers.deals.coupons.view.adapter.home

import android.content.Context
import android.view.ViewGroup
import food.order.delivery.online.offers.deals.coupons.buy.R
import food.order.delivery.online.offers.deals.coupons.base.adapter.GenericRecyclerAdapter
import food.order.delivery.online.offers.deals.coupons.view.listener.home.TrendingItemClickListener
import food.order.delivery.online.offers.deals.coupons.view.viewholder.TrendingViewHolder

class TrendingAdapter (context: Context?) :
    GenericRecyclerAdapter<List<String>, TrendingItemClickListener<List<String>>, TrendingViewHolder>(context) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendingViewHolder {
        return TrendingViewHolder(inflate(R.layout.card_trending_layout,parent))
    }
}