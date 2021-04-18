package food.order.delivery.online.offers.deals.coupons.view.listener

import food.order.delivery.online.offers.deals.coupons.base.listener.BaseRecyclerListener

interface CantonentStoresItemClickListener<T> : BaseRecyclerListener {
    fun CantonantStoresCardClick(item: T)
}