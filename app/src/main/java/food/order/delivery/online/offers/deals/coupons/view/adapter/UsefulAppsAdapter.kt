package food.order.delivery.online.offers.deals.coupons.view.adapter

import android.content.Context
import android.view.ViewGroup
import food.order.delivery.online.offers.deals.coupons.base.adapter.GenericRecyclerAdapter
import food.order.delivery.online.offers.deals.coupons.buy.R
import food.order.delivery.online.offers.deals.coupons.view.listener.UsefulAppsClickListener
import food.order.delivery.online.offers.deals.coupons.view.viewholder.UsefulAppsViewHolder


class UsefulAppsAdapter(context: Context?) :
        GenericRecyclerAdapter<List<String>, UsefulAppsClickListener<List<String>>, UsefulAppsViewHolder>(context) {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsefulAppsViewHolder {
            return UsefulAppsViewHolder(inflate(R.layout.card_all_apps_portal_layout,parent))
        }
    }