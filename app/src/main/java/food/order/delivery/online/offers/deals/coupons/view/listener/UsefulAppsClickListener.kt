package food.order.delivery.online.offers.deals.coupons.view.listener

import food.order.delivery.online.offers.deals.coupons.base.listener.BaseRecyclerListener

interface UsefulAppsClickListener <T> : BaseRecyclerListener {
    fun UsefulAppsCardClick(iusefulAppsClickListenertem: T)
}