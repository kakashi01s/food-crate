package food.order.delivery.online.offers.deals.coupons.view.listener.home

import food.order.delivery.online.offers.deals.coupons.base.listener.BaseRecyclerListener

interface TrendingItemClickListener <T> : BaseRecyclerListener {
    fun onTrendingClickListener(item: T)
}