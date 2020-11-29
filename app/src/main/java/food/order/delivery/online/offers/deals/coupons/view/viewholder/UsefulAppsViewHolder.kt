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
import food.order.delivery.online.offers.deals.coupons.view.listener.UsefulAppsClickListener
import kotlin.math.log

class  UsefulAppsViewHolder(itemView: View?) :
    BaseViewHolder<List<String>,UsefulAppsClickListener<List<String>>>(itemView) {

    var ivPortalImage: ImageView? = null
    var tvPortalName: TextView? = null
    var cvPortal: CardView? = null

    init {
        ivPortalImage = itemView?.findViewById(R.id.ivAllAppsPortal)
        tvPortalName = itemView?.findViewById(R.id.tvPortalName)
        cvPortal = itemView?.findViewById(R.id.cvAllAppsPortal)
    }

    override fun onBind(item: List<String>, listener: UsefulAppsClickListener<List<String>>?) {
        Log.d("TAG", "HomeFragment Live BindCheck")
        Glide.with(ivPortalImage!!.context)
            .load(item.get(3))
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(ivPortalImage!!)

        tvPortalName?.setText(item.get(1))

        cvPortal?.setOnClickListener {
            Log.d("TAG", "onAllCardClick: " + item.get(1))
            listener?.UsefulAppsCardClick(item)

        }

    }
}