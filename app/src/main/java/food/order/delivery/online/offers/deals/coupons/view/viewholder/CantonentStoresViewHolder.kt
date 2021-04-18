package food.order.delivery.online.offers.deals.coupons.view.viewholder

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import food.order.delivery.online.offers.deals.coupons.base.viewholder.BaseViewHolder
import food.order.delivery.online.offers.deals.coupons.buy.R
import food.order.delivery.online.offers.deals.coupons.view.listener.CantonentStoresItemClickListener

class CantonentStoresViewHolder(itemView: View) : BaseViewHolder<List<String>, CantonentStoresItemClickListener<List<String>>>(itemView) {

    var ivPortalImage: ImageView? = null
    var tvPortalName: TextView? = null
    var cvPortal: CardView? = null

    init {
        ivPortalImage = itemView.findViewById(R.id.ivAllAppsPortal)
        tvPortalName = itemView.findViewById(R.id.tvPortalName)
        cvPortal = itemView.findViewById(R.id.cvAllAppsPortal)
    }


    override fun onBind(item: List<String>, listener: CantonentStoresItemClickListener<List<String>>?) {

        Glide.with(ivPortalImage!!.context)
            .load(item[3])
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(ivPortalImage!!)

        tvPortalName?.setText(item.get(1))

        cvPortal?.setOnClickListener {
            Log.d("TAG", "onAllCardClick: "+item.get(1))
            listener?.CantonantStoresCardClick(item)
        }

    }
}