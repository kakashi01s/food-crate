package food.order.delivery.online.offers.deals.coupons.utils

import android.content.Context
import android.content.Intent
import android.util.Log
import com.onesignal.OSNotificationAction
import com.onesignal.OSNotificationOpenedResult
import com.onesignal.OneSignal
import food.order.delivery.online.offers.deals.coupons.view.MainActivity
import food.order.delivery.online.offers.deals.coupons.view.SplashActivity

class AppNotificationOpenHandler(val context: Context): OneSignal.OSNotificationOpenedHandler {
    override fun notificationOpened(result: OSNotificationOpenedResult?) {
//        val actionId = result.action.actionId
//            val type: String =
//                result.action.type.toString() // "ActionTaken" | "Opened"
//            val title = result.notification.title

//            val intent: Intent? = Intent(this, WebActivity::class.java)
//                    intent!!.putExtra("url",result.notification.additionalData.getJSONObject("website").toString())
//                    startActivity(intent)

        Log.d(
            "OSNotification",
            "result.notification.toJSONObject(): " + result!!.notification.toJSONObject()
        )

        val data = result!!.notification.additionalData
        if (data != null) {
            val customKey = data.optString("website", null)
            if (customKey != null) {
                Log.d("OneSignalExample", "customkey set with value: $customKey")
                val intent = Intent(
                    context,
                    MainActivity::class.java
                )
                intent.flags =
                    Intent.FLAG_ACTIVITY_REORDER_TO_FRONT or Intent.FLAG_ACTIVITY_NEW_TASK
                intent.putExtra("route","website")
                intent.putExtra("url",customKey)
                context.startActivity(intent)
            }
            else
            {
                val intent = Intent(
                    context,
                    SplashActivity::class.java
                )
                intent.flags =
                    Intent.FLAG_ACTIVITY_REORDER_TO_FRONT or Intent.FLAG_ACTIVITY_NEW_TASK
                // intent.putExtra("route","website")
                //intent.putExtra("url",customKey)
                context.startActivity(intent)
            }
        }
        else{
            val intent = Intent(
                context,
                SplashActivity::class.java
            )
            intent.flags =
                Intent.FLAG_ACTIVITY_REORDER_TO_FRONT or Intent.FLAG_ACTIVITY_NEW_TASK
            // intent.putExtra("route","website")
            //intent.putExtra("url",customKey)
            context.startActivity(intent)

        }

        val actionType = result!!.action.type
        if (actionType == OSNotificationAction.ActionType.ActionTaken) {
            Log.d(
                "OneSignalExample",
                "Button pressed with id: " + result!!.action.actionId
            )
        }

//            val data: JSONObject = result.notification.additionalData
//
//            if(result.notification.additionalData!=null){
//                Log.d("TAG", "onCreate: oneSignal notification "+data.getString("website"))
////
//            }
    }
}