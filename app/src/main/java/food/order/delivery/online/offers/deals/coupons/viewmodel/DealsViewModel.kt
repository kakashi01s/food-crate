package food.order.delivery.online.offers.deals.coupons.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import food.order.delivery.online.offers.deals.coupons.Singleton
import food.order.delivery.online.offers.deals.coupons.data.DataFactory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import food.order.delivery.online.offers.deals.coupons.data.DataService

class DealsViewModel : ViewModel() {
    var dealsLiveData: MutableLiveData<List<List<String>>?> = MutableLiveData()
    private var context: Context? = null
    var compositeDisposable: CompositeDisposable? = null

    fun loadData(){
        Log.d("TAG", "loadData: Deals ")
        compositeDisposable = CompositeDisposable()
        fetchDealsData()
    }

    private fun fetchDealsData(){
        Log.d("TAG", "fetchDealsData: ")
        val singleton: Singleton? = Singleton.get()
        val dataService: DataService? = singleton!!.getDataService()


        val disposable: Disposable?
        disposable = dataService?.fetchAllApps(DataFactory().URL_DEALS_DATA, DataFactory().KEY)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.doOnError(Consumer { t ->
                Log.d("TAG", "fetchDealsData Error ${t.localizedMessage}")
            })
            ?.subscribe(Consumer { t ->
                Log.d("TAG", "fetchDealsData Response ${t.getValues()}")
                changeAllAppsDataSet(t.getValues())
            })

        if (disposable != null) {
            compositeDisposable?.add(disposable)
        }
    }

    fun changeAllAppsDataSet(allAppsList: List<List<String>>?){
        dealsLiveData.value = allAppsList
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