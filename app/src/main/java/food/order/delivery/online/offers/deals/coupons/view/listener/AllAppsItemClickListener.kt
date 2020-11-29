package food.order.delivery.online.offers.deals.coupons.view.listener

import food.order.delivery.online.offers.deals.coupons.base.listener.BaseRecyclerListener

interface AllAppsItemClickListener<T> : BaseRecyclerListener {
    fun onAllCardClick(item: T)
}