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

class CantonentViewModel : ViewModel() {
    var indiaLiveData: MutableLiveData<List<List<String>>?> = MutableLiveData()
    var usaLiveData: MutableLiveData<List<List<String>>?> = MutableLiveData()
    var russiaLiveData: MutableLiveData<List<List<String>>?> = MutableLiveData()
    var pakistanLiveData: MutableLiveData<List<List<String>>?> = MutableLiveData()
    var chinaLiveData: MutableLiveData<List<List<String>>?> = MutableLiveData()
    var germanyLiveData: MutableLiveData<List<List<String>>?> = MutableLiveData()
    var turkeyLiveData: MutableLiveData<List<List<String>>?> = MutableLiveData()
    var videoLiveData: MutableLiveData<List<List<String>>?> = MutableLiveData()
    private var context: Context? = null
    var compositeDisposable: CompositeDisposable? = null

    fun loadData(){
        Log.d("TAG", "loadData: ")
        compositeDisposable = CompositeDisposable()
        fetchIndia()
        fetchUsa()
        fetchRussia()
        fetchPakistan()
        fetchChina()
        fetchGermany()
        fetchTurkey()
        fetchVideo()
    }

    private fun fetchIndia(){
        Log.d("TAG", "fetchIndia: ")
        val singleton: Singleton? = Singleton.get()
        val dataService: DataService? = singleton!!.getDataService()


        val disposable: Disposable?
        disposable = dataService?.fetchAllApps(DataFactory().URL_AFRICAN, DataFactory().KEY)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.doOnError(Consumer { t ->
                Log.d("TAG", "fetchIndia Error ${t.localizedMessage}")
            })
            ?.subscribe(Consumer { t ->
                Log.d("TAG", "fetchIndia Response ${t.getValues()}")
                changeIndiaDataSet(t.getValues())
            })

        if (disposable != null) {
            compositeDisposable?.add(disposable)
        }
    }

    private fun fetchUsa(){
        Log.d("TAG", "fetchUsa: ")

        val singleton: Singleton? = Singleton.get()
        val dataService: DataService? = singleton!!.getDataService()

        val disposable: Disposable?
        disposable = dataService?.fetchAllApps(DataFactory().URL_ANTARCTICA, DataFactory().KEY)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.doOnError(Consumer { t ->
                Log.d("TAG", "fetchUsa Error ${t.localizedMessage}")
            })
            ?.subscribe(Consumer { t ->
                Log.d("TAG", "fetchUsa Response ${t.getValues()}")
                changeUsaDataSet(t.getValues())
            })

        if (disposable != null) {
            compositeDisposable?.add(disposable)
        }
    }

    private fun fetchRussia(){
        Log.d("TAG", "fetchRussia: ")

        val singleton: Singleton? = Singleton.get()
        val dataService: DataService? = singleton!!.getDataService()

        val disposable: Disposable?
        disposable = dataService?.fetchAllApps(DataFactory().URL_ASIAN, DataFactory().KEY)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.doOnError(Consumer { t ->
                Log.d("TAG", "fetchRussia Error ${t.localizedMessage}")
            })
            ?.subscribe(Consumer { t ->
                Log.d("TAG", "fetchRussia Response ${t.getValues()}")
                changeRussiaDataSet(t.getValues())
            })

        if (disposable != null) {
            compositeDisposable?.add(disposable)
        }
    }

    private fun fetchPakistan(){
        Log.d("TAG", "fetchPakistan: ")

        val singleton: Singleton? = Singleton.get()
        val dataService: DataService? = singleton!!.getDataService()

        val disposable: Disposable?
        disposable = dataService?.fetchAllApps(DataFactory().URL_EUROPIAN, DataFactory().KEY)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.doOnError(Consumer { t ->
                Log.d("TAG", "fetchPaistan Error ${t.localizedMessage}")
            })
            ?.subscribe(Consumer { t ->
                Log.d("TAG", "fetchPakistan Response ${t.getValues()}")
                changePakistanDataSet(t.getValues())
            })

        if (disposable != null) {
            compositeDisposable?.add(disposable)
        }
    }

    private fun fetchChina(){
        Log.d("TAG", "fetchChina: ")

        val singleton: Singleton? = Singleton.get()
        val dataService: DataService? = singleton!!.getDataService()

        val disposable: Disposable?
        disposable = dataService?.fetchAllApps(DataFactory().URL_NORTH_AMERICA, DataFactory().KEY)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.doOnError(Consumer { t ->
                Log.d("TAG", "fetchChina Error ${t.localizedMessage}")
            })
            ?.subscribe(Consumer { t ->
                Log.d("TAG", "fetchChina Response ${t.getValues()}")
                changeChinaDataSet(t.getValues())
            })

        if (disposable != null) {
            compositeDisposable?.add(disposable)
        }
    }

    private fun fetchGermany(){
        Log.d("TAG", "fetchGermany: ")

        val singleton: Singleton? = Singleton.get()
        val dataService: DataService? = singleton!!.getDataService()

        val disposable: Disposable?
        disposable = dataService?.fetchAllApps(DataFactory().URL_SOUTH_AMERICA, DataFactory().KEY)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.doOnError(Consumer { t ->
                Log.d("TAG", "fetchGermany Error ${t.localizedMessage}")
            })
            ?.subscribe(Consumer { t ->
                Log.d("TAG", "fetchGermany Response ${t.getValues()}")
                changeGermanyDataSet(t.getValues())
            })

        if (disposable != null) {
            compositeDisposable?.add(disposable)
        }
    }

    private fun fetchTurkey(){
        Log.d("TAG", "fetchTurkey: ")

        val singleton: Singleton? = Singleton.get()
        val dataService: DataService? = singleton!!.getDataService()

        val disposable: Disposable?
        disposable = dataService?.fetchAllApps(DataFactory().URL_AUSTRALIAN, DataFactory().KEY)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.doOnError(Consumer { t ->
                Log.d("TAG", "fetchTurkey Error ${t.localizedMessage}")
            })
            ?.subscribe(Consumer { t ->
                Log.d("TAG", "fetchTurkey Response ${t.getValues()}")
                changeTurkeyDataSet(t.getValues())
            })

        if (disposable != null) {
            compositeDisposable?.add(disposable)
        }
    }
    private fun fetchVideo(){
        Log.d("TAG", "fetchTurkey: ")

        val singleton: Singleton? = Singleton.get()
        val dataService: DataService? = singleton!!.getDataService()

        val disposable: Disposable?
        disposable = dataService?.fetchAllApps(DataFactory().URL_VIDEO, DataFactory().KEY)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.doOnError(Consumer { t ->
                Log.d("TAG", "fetchTurkey Error ${t.localizedMessage}")
            })
            ?.subscribe(Consumer { t ->
                Log.d("TAG", "fetchTurkey Response ${t.getValues()}")
                changeVideoDataSet(t.getValues())
            })

        if (disposable != null) {
            compositeDisposable?.add(disposable)
        }
    }


    fun changeIndiaDataSet(allAppsList: List<List<String>>?){
        indiaLiveData.value = allAppsList
    }

    fun changeUsaDataSet(allAppsList: List<List<String>>?){
        usaLiveData.value = allAppsList
    }

    fun changeRussiaDataSet(allAppsList: List<List<String>>?){
        russiaLiveData.value = allAppsList
    }

    fun changePakistanDataSet(allAppsList: List<List<String>>?){
        pakistanLiveData.value = allAppsList
    }

    fun changeChinaDataSet(allAppsList: List<List<String>>?){
        chinaLiveData.value = allAppsList
    }

    fun changeGermanyDataSet(allAppsList: List<List<String>>?){
        germanyLiveData.value = allAppsList
    }

    fun changeTurkeyDataSet(allAppsList: List<List<String>>?){
        turkeyLiveData.value = allAppsList
    }
    fun changeVideoDataSet(allAppsList: List<List<String>>?){
        videoLiveData.value = allAppsList
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