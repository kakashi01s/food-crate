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
    var foodLiveData: MutableLiveData<List<List<String>>?> = MutableLiveData()
    var shoppingLiveData: MutableLiveData<List<List<String>>?> = MutableLiveData()
    var groceryLiveData: MutableLiveData<List<List<String>>?> = MutableLiveData()
    var dealsLiveData: MutableLiveData<List<List<String>>?> = MutableLiveData()
    private var context: Context? = null
    var compositeDisposable: CompositeDisposable? = null

    fun loadData(){
        Log.d("TAG", "loadData: ")
        compositeDisposable = CompositeDisposable()
        fetchCarouselImages()
        fetchfoodData()
        fetchshopping()
        fetchgrocery()
        fetchdeals()
    }

    private fun fetchfoodData(){
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
                changefoodDataSet(t.getValues())
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

    private fun fetchshopping(){
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
                changeshoppingDataSet(t.getValues())
            })

        if (disposable != null) {
            compositeDisposable?.add(disposable)
        }
    }


    private fun fetchgrocery(){
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
                changegroceryDataSet(t.getValues())
            })

        if (disposable != null) {
            compositeDisposable?.add(disposable)
        }
    }

    private fun fetchdeals(){
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
                changedealsDataSet(t.getValues())
            })

        if (disposable != null) {
            compositeDisposable?.add(disposable)
        }
    }

    private fun changedealsDataSet(dealsList: List<List<String>>?) {
    dealsLiveData.value = dealsList
    }

    fun changeshoppingDataSet(shoppingList: List<List<String>>?){
        shoppingLiveData.value = shoppingList
    }

    fun changeCarouselDataSet(carouselList: List<List<String>>?){
        carouselImagesLiveData.value = carouselList
    }

    fun changegroceryDataSet(groceryList: List<List<String>>?){
        groceryLiveData.value = groceryList
    }
    fun changefoodDataSet(foodList: List<List<String>>?){
        foodLiveData.value = foodList
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