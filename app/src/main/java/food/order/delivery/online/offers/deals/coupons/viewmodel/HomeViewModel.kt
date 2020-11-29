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

class HomeViewModel : ViewModel() {

    var allAppsLiveData: MutableLiveData<List<List<String>>?> = MutableLiveData()
    var carouselImagesLiveData: MutableLiveData<List<List<String>>?> = MutableLiveData()
    var trendingLiveData: MutableLiveData<List<List<String>>?> = MutableLiveData()
    var usefulappsLiveData: MutableLiveData<List<List<String>>?> = MutableLiveData()
    private var context: Context? = null
    var compositeDisposable: CompositeDisposable? = null

    fun loadData(){
        Log.d("TAG", "loadData: ")
        compositeDisposable = CompositeDisposable()
        fetchCarouselImages()
        fetchTrendingData()
        fetchAllApps()
        fetchUsefulApps()
    }

    private fun fetchAllApps(){
        Log.d("TAG", "fetchAllApps: ")
        val singleton: Singleton? = Singleton.get()
        val dataService: DataService? = singleton!!.getDataService()

        val disposable: Disposable?
        disposable = dataService?.fetchAllApps(DataFactory().URL_ALL_APPS, DataFactory().KEY)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.doOnError(Consumer { t ->
                Log.d("TAG", "fetchAllApps Error ${t.localizedMessage}")
            })
            ?.subscribe(Consumer { t ->
                Log.d("TAG", "fetchAllApps Response ${t.getValues()}")
                changeAllAppsDataSet(t.getValues())
            })

        if (disposable != null) {
            compositeDisposable?.add(disposable)
        }
    }

    private fun fetchCarouselImages(){
        Log.d("TAG", "fetchCarouselImages: ")

        val dataService by lazy {
            DataFactory.create()
        }

        val disposable: Disposable?
        disposable = dataService?.fetchAllApps(DataFactory().URL_CAROUSEL_IMAGES, DataFactory().KEY)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.doOnError(Consumer { t ->
                Log.d("TAG", "fetchCarouselImages Error ${t.localizedMessage}")
            })
            ?.subscribe(Consumer { t ->
                Log.d("TAG", "fetchCarouselImages Response ${t.getValues()}")
                changeCarouselDataSet(t.getValues())
            })

        if (disposable != null) {
            compositeDisposable?.add(disposable)
        }
    }

    private fun fetchUsefulApps(){
        Log.d("TAG", "fetchUsefulApps: ")

        val dataService by lazy {
            DataFactory.create()
        }

        val disposable: Disposable?
        disposable = dataService?.fetchAllApps(DataFactory().URL_USEFUL_APP, DataFactory().KEY)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.doOnError(Consumer { t ->
                Log.d("TAG", "fetchUsefulApps Error ${t.localizedMessage}")
            })
            ?.subscribe(Consumer { t ->
                Log.d("TAG", "fetchUsefulApps Response ${t.getValues()}")
                changeUsefulAppsDataSet(t.getValues())
            })

        if (disposable != null) {
            compositeDisposable?.add(disposable)
        }
    }

    fun changeUsefulAppsDataSet(trendingList: List<List<String>>?){
        usefulappsLiveData.value = trendingList
    }

    private fun fetchTrendingData(){
        Log.d("TAG", "fetchTrendingData: ")

        val dataService by lazy {
            DataFactory.create()
        }

        val disposable: Disposable?
        disposable = dataService?.fetchAllApps(DataFactory().URL_TRENDING_DATA, DataFactory().KEY)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.doOnError(Consumer { t ->
                Log.d("TAG", "fetchTrendingData Error ${t.localizedMessage}")
            })
            ?.subscribe(Consumer { t ->
                Log.d("TAG", "fetchTrendingData Response ${t.getValues()}")
                changeTrendingDataSet(t.getValues())
            })

        if (disposable != null) {
            compositeDisposable?.add(disposable)
        }
    }


    fun changeAllAppsDataSet(allAppsList: List<List<String>>?){
        allAppsLiveData.value = allAppsList
    }

    fun changeCarouselDataSet(carouselList: List<List<String>>?){
        carouselImagesLiveData.value = carouselList
    }

    fun changeTrendingDataSet(trendingList: List<List<String>>?){
        trendingLiveData.value = trendingList
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