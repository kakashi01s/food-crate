package food.order.delivery.online.offers.deals.coupons.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import food.order.delivery.online.offers.deals.coupons.Singleton
import food.order.delivery.online.offers.deals.coupons.data.DataFactory
import food.order.delivery.online.offers.deals.coupons.data.DataService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

class UsefulAppsViewModel : ViewModel() {

    private var context: Context? = null
    var compositeDisposable: CompositeDisposable? = null

    fun loadData(){
        Log.d("TAG", "loadData: ")
        compositeDisposable = CompositeDisposable()

    }




    private fun unSubscribeFromObservable() {
        if (compositeDisposable != null && !compositeDisposable!!.isDisposed) {
            compositeDisposable!!.dispose()
        }
    }

    fun reset() {
        unSubscribeFromObservable()
        compositeDisposable = null
        context = null
    }

}