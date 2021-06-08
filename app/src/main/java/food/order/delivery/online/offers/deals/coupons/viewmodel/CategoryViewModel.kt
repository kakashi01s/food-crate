package food.order.delivery.online.offers.deals.coupons.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import food.order.delivery.online.offers.deals.coupons.Singleton
import food.order.delivery.online.offers.deals.coupons.data.DataFactory
import food.order.delivery.online.offers.deals.coupons.data.DataService

class CategoryViewModel : ViewModel() {
    var indiaLiveData: MutableLiveData<List<List<String>>?> = MutableLiveData()
    var usaLiveData: MutableLiveData<List<List<String>>?> = MutableLiveData()
    var russiaLiveData: MutableLiveData<List<List<String>>?> = MutableLiveData()
    var pakistanLiveData: MutableLiveData<List<List<String>>?> = MutableLiveData()
    var newzealandLiveData: MutableLiveData<List<List<String>>?> = MutableLiveData()
    var germanyLiveData: MutableLiveData<List<List<String>>?> = MutableLiveData()
    var turkeyLiveData: MutableLiveData<List<List<String>>?> = MutableLiveData()
    var uaeLiveData: MutableLiveData<List<List<String>>?> = MutableLiveData()
    var italyLiveData: MutableLiveData<List<List<String>>?> = MutableLiveData()
    var switzerlandLiveData: MutableLiveData<List<List<String>>?> = MutableLiveData()
    var canadaLiveData: MutableLiveData<List<List<String>>?> = MutableLiveData()
    var singaporeLiveData: MutableLiveData<List<List<String>>?> = MutableLiveData()
    var southAfricaLiveData: MutableLiveData<List<List<String>>?> = MutableLiveData()
    var franceLiveData: MutableLiveData<List<List<String>>?> = MutableLiveData()

    var indonesiaLiveData: MutableLiveData<List<List<String>>?> = MutableLiveData()
    var ukLiveData: MutableLiveData<List<List<String>>?> = MutableLiveData()
    var japanLiveData: MutableLiveData<List<List<String>>?> = MutableLiveData()
    var brazilLiveData: MutableLiveData<List<List<String>>?> = MutableLiveData()
    var nigeriaLiveData: MutableLiveData<List<List<String>>?> = MutableLiveData()
    var portugalLiveData: MutableLiveData<List<List<String>>?> = MutableLiveData()
    var australiaLiveData: MutableLiveData<List<List<String>>?> = MutableLiveData()
    var greeceLiveData: MutableLiveData<List<List<String>>?> = MutableLiveData()

    private var context: Context? = null
    var compositeDisposable: CompositeDisposable? = null

    fun loadData(){
        Log.d("TAG", "loadData: ")
        compositeDisposable = CompositeDisposable()
        fetchIndia()
        fetchUsa()
        fetchRussia()
        fetchPakistan()
        fetchnewzealand()
        fetchGermany()
        fetchTurkey()
        fetchUae()
        fetchItaly()
        fetchSwitzerland()
        fetchCanada()
        fetchSingapore()
        fetchSouthAfrica()
        fetchFrance()
        fetchIndonesia()
        fetchUk()
        fetchJapan()
        fetchBrazil()
        fetchNigeria()
        fetchPortugal()
        fetchAustralia()
        fetchGreece()
    }

    private fun fetchIndia(){
        Log.d("TAG", "fetchIndia: ")
        val singleton: Singleton? = Singleton.get()
        val dataService: DataService? = singleton!!.getDataService()


        val disposable: Disposable?
        disposable = dataService?.fetchAllApps(DataFactory().URL_INDIA, DataFactory().KEY)
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
        disposable = dataService?.fetchAllApps(DataFactory().URL_USA, DataFactory().KEY)
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
        disposable = dataService?.fetchAllApps(DataFactory().URL_RUSSIA, DataFactory().KEY)
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
        disposable = dataService?.fetchAllApps(DataFactory().URL_PAKISTAN, DataFactory().KEY)
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

    private fun fetchnewzealand(){
        Log.d("TAG", "fetchChina: ")

        val singleton: Singleton? = Singleton.get()
        val dataService: DataService? = singleton!!.getDataService()

        val disposable: Disposable?
        disposable = dataService?.fetchAllApps(DataFactory().URL_NEWZEALAND, DataFactory().KEY)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.doOnError(Consumer { t ->
                Log.d("TAG", "fetchChina Error ${t.localizedMessage}")
            })
            ?.subscribe(Consumer { t ->
                Log.d("TAG", "fetchChina Response ${t.getValues()}")
                changenewzealandaDataSet(t.getValues())
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
        disposable = dataService?.fetchAllApps(DataFactory().URL_GERMANY, DataFactory().KEY)
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
        disposable = dataService?.fetchAllApps(DataFactory().URL_TURKEY, DataFactory().KEY)
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

    private fun fetchUae(){
        Log.d("TAG", "fetchUae: ")

        val singleton: Singleton? = Singleton.get()
        val dataService: DataService? = singleton!!.getDataService()

        val disposable: Disposable?
        disposable = dataService?.fetchAllApps(
            DataFactory().URL_UAE,
            DataFactory().KEY
        )
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.doOnError(Consumer { t ->
                Log.d("TAG", "fetchUae Error ${t.localizedMessage}")
            })
            ?.subscribe(Consumer { t ->
                Log.d("TAG", "fetchUae Response ${t.getValues()}")
                changeUaeDataSet(t.getValues())
            })

        if (disposable != null) {
            compositeDisposable?.add(disposable)
        }
    }

    private fun fetchItaly(){
        Log.d("TAG", "fetchItaly: ")

        val singleton: Singleton? = Singleton.get()
        val dataService: DataService? = singleton!!.getDataService()

        val disposable: Disposable?
        disposable = dataService?.fetchAllApps(DataFactory().URL_ITALY, DataFactory().KEY)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.doOnError(Consumer { t ->
                Log.d("TAG", "fetchItaly Error ${t.localizedMessage}")
            })
            ?.subscribe(Consumer { t ->
                Log.d("TAG", "fetchItaly Response ${t.getValues()}")
                changeItalyDataSet(t.getValues())
            })

        if (disposable != null) {
            compositeDisposable?.add(disposable)
        }
    }

    private fun fetchSwitzerland(){
        Log.d("TAG", "fetchSwitzerland: ")

        val singleton: Singleton? = Singleton.get()
        val dataService: DataService? = singleton!!.getDataService()

        val disposable: Disposable?
        disposable = dataService?.fetchAllApps(DataFactory().URL_SWITZERLAND, DataFactory().KEY)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.doOnError(Consumer { t ->
                Log.d("TAG", "fetchSwitzerland Error ${t.localizedMessage}")
            })
            ?.subscribe(Consumer { t ->
                Log.d("TAG", "fetchSwitzerland Response ${t.getValues()}")
                changeSwitzerlandDataSet(t.getValues())
            })

        if (disposable != null) {
            compositeDisposable?.add(disposable)
        }
    }

    private fun fetchCanada(){
        Log.d("TAG", "fetchCanada: ")

        val singleton: Singleton? = Singleton.get()
        val dataService: DataService? = singleton!!.getDataService()

        val disposable: Disposable?
        disposable = dataService?.fetchAllApps(DataFactory().URL_CANADA, DataFactory().KEY)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.doOnError(Consumer { t ->
                Log.d("TAG", "fetchCanada Error ${t.localizedMessage}")
            })
            ?.subscribe(Consumer { t ->
                Log.d("TAG", "fetchCanada Response ${t.getValues()}")
                changeCanadaDataSet(t.getValues())
            })

        if (disposable != null) {
            compositeDisposable?.add(disposable)
        }
    }

    private fun fetchSingapore(){
        Log.d("TAG", "fetchSingapore: ")

        val singleton: Singleton? = Singleton.get()
        val dataService: DataService? = singleton!!.getDataService()

        val disposable: Disposable?
        disposable = dataService?.fetchAllApps(DataFactory().URL_SINGAPORE, DataFactory().KEY)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.doOnError(Consumer { t ->
                Log.d("TAG", "fetchSingapore Error ${t.localizedMessage}")
            })
            ?.subscribe(Consumer { t ->
                Log.d("TAG", "fetchSingapore Response ${t.getValues()}")
                changeSingaporeDataSet(t.getValues())
            })

        if (disposable != null) {
            compositeDisposable?.add(disposable)
        }
    }

    private fun fetchSouthAfrica(){
        Log.d("TAG", "fetchSouthAfrica: ")

        val singleton: Singleton? = Singleton.get()
        val dataService: DataService? = singleton!!.getDataService()

        val disposable: Disposable?
        disposable = dataService?.fetchAllApps(DataFactory().URL_SOUTH_AFRICA, DataFactory().KEY)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.doOnError(Consumer { t ->
                Log.d("TAG", "fetchSouthAfrica Error ${t.localizedMessage}")
            })
            ?.subscribe(Consumer { t ->
                Log.d("TAG", "fetchSouthAfrica Response ${t.getValues()}")
                changeSouthAfricaDataSet(t.getValues())
            })

        if (disposable != null) {
            compositeDisposable?.add(disposable)
        }
    }

    private fun fetchFrance(){
        Log.d("TAG", "fetchFrance: ")

        val singleton: Singleton? = Singleton.get()
        val dataService: DataService? = singleton!!.getDataService()

        val disposable: Disposable?
        disposable = dataService?.fetchAllApps(DataFactory().URL_FRANCE, DataFactory().KEY)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.doOnError(Consumer { t ->
                Log.d("TAG", "fetchFrance Error ${t.localizedMessage}")
            })
            ?.subscribe(Consumer { t ->
                Log.d("TAG", "fetchFrance Response ${t.getValues()}")
                changeFranceDataSet(t.getValues())
            })

        if (disposable != null) {
            compositeDisposable?.add(disposable)
        }
    }

    private fun fetchIndonesia(){
        Log.d("TAG", "fetchIndonesia: ")

        val singleton: Singleton? = Singleton.get()
        val dataService: DataService? = singleton!!.getDataService()

        val disposable: Disposable?
        disposable = dataService?.fetchAllApps(DataFactory().URL_INDONECIA, DataFactory().KEY)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.doOnError(Consumer { t ->
                Log.d("TAG", "fetchIndonesia Error ${t.localizedMessage}")
            })
            ?.subscribe(Consumer { t ->
                Log.d("TAG", "fetchIndonesia Response ${t.getValues()}")
                changeIndonesiaDataSet(t.getValues())
            })

        if (disposable != null) {
            compositeDisposable?.add(disposable)
        }
    }
    private fun fetchUk(){
        Log.d("TAG", "fetchUk: ")

        val singleton: Singleton? = Singleton.get()
        val dataService: DataService? = singleton!!.getDataService()

        val disposable: Disposable?
        disposable = dataService?.fetchAllApps(DataFactory().URL_UK, DataFactory().KEY)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.doOnError(Consumer { t ->
                Log.d("TAG", "fetchUk Error ${t.localizedMessage}")
            })
            ?.subscribe(Consumer { t ->
                Log.d("TAG", "fetchUk Response ${t.getValues()}")
                changeUkDataSet(t.getValues())
            })

        if (disposable != null) {
            compositeDisposable?.add(disposable)
        }
    }
    private fun fetchJapan(){
        Log.d("TAG", "fetchJapan: ")

        val singleton: Singleton? = Singleton.get()
        val dataService: DataService? = singleton!!.getDataService()

        val disposable: Disposable?
        disposable = dataService?.fetchAllApps(DataFactory().URL_JAPAN, DataFactory().KEY)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.doOnError(Consumer { t ->
                Log.d("TAG", "fetchJapan Error ${t.localizedMessage}")
            })
            ?.subscribe(Consumer { t ->
                Log.d("TAG", "fetchJapan Response ${t.getValues()}")
                changeJapanDataSet(t.getValues())
            })

        if (disposable != null) {
            compositeDisposable?.add(disposable)
        }
    }
    private fun fetchBrazil(){
        Log.d("TAG", "fetchBrazil: ")

        val singleton: Singleton? = Singleton.get()
        val dataService: DataService? = singleton!!.getDataService()

        val disposable: Disposable?
        disposable = dataService?.fetchAllApps(DataFactory().URL_BRAZIL, DataFactory().KEY)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.doOnError(Consumer { t ->
                Log.d("TAG", "fetchBrazil Error ${t.localizedMessage}")
            })
            ?.subscribe(Consumer { t ->
                Log.d("TAG", "fetchBrazil Response ${t.getValues()}")
                changeBrazilDataSet(t.getValues())
            })

        if (disposable != null) {
            compositeDisposable?.add(disposable)
        }
    }
    private fun fetchNigeria(){
        Log.d("TAG", "fetchNigeria: ")

        val singleton: Singleton? = Singleton.get()
        val dataService: DataService? = singleton!!.getDataService()

        val disposable: Disposable?
        disposable = dataService?.fetchAllApps(DataFactory().URL_NIGERIA, DataFactory().KEY)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.doOnError(Consumer { t ->
                Log.d("TAG", "fetchNigeria Error ${t.localizedMessage}")
            })
            ?.subscribe(Consumer { t ->
                Log.d("TAG", "fetchNigeria Response ${t.getValues()}")
                changeNigeriaDataSet(t.getValues())
            })

        if (disposable != null) {
            compositeDisposable?.add(disposable)
        }
    }
    private fun fetchPortugal(){
        Log.d("TAG", "fetchPortugal: ")

        val singleton: Singleton? = Singleton.get()
        val dataService: DataService? = singleton!!.getDataService()

        val disposable: Disposable?
        disposable = dataService?.fetchAllApps(DataFactory().URL_PORTUGAL, DataFactory().KEY)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.doOnError(Consumer { t ->
                Log.d("TAG", "fetchPortugal Error ${t.localizedMessage}")
            })
            ?.subscribe(Consumer { t ->
                Log.d("TAG", "fetchPortugal Response ${t.getValues()}")
                changePortugalDataSet(t.getValues())
            })

        if (disposable != null) {
            compositeDisposable?.add(disposable)
        }
    }
    private fun fetchAustralia(){
        Log.d("TAG", "fetchAustralia: ")

        val singleton: Singleton? = Singleton.get()
        val dataService: DataService? = singleton!!.getDataService()

        val disposable: Disposable?
        disposable = dataService?.fetchAllApps(DataFactory().URL_AUSTRALIA, DataFactory().KEY)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.doOnError(Consumer { t ->
                Log.d("TAG", "fetchAustralia Error ${t.localizedMessage}")
            })
            ?.subscribe(Consumer { t ->
                Log.d("TAG", "fetchAustralia Response ${t.getValues()}")
                changeAustraliaDataSet(t.getValues())
            })

        if (disposable != null) {
            compositeDisposable?.add(disposable)
        }
    }
    private fun fetchGreece(){
        Log.d("TAG", "fetchGreece: ")

        val singleton: Singleton? = Singleton.get()
        val dataService: DataService? = singleton!!.getDataService()

        val disposable: Disposable?
        disposable = dataService?.fetchAllApps(DataFactory().URL_GREECE, DataFactory().KEY)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.doOnError(Consumer { t ->
                Log.d("TAG", "fetchGreece Error ${t.localizedMessage}")
            })
            ?.subscribe(Consumer { t ->
                Log.d("TAG", "fetchGreece Response ${t.getValues()}")
                changeGreeceDataSet(t.getValues())
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

    fun changenewzealandaDataSet(allAppsList: List<List<String>>?){
        newzealandLiveData.value = allAppsList
    }

    fun changeGermanyDataSet(allAppsList: List<List<String>>?){
        germanyLiveData.value = allAppsList
    }

    fun changeTurkeyDataSet(allAppsList: List<List<String>>?){
        turkeyLiveData.value = allAppsList
    }

    fun changeUaeDataSet(allAppsList: List<List<String>>?){
        uaeLiveData.value = allAppsList
    }

    fun changeItalyDataSet(allAppsList: List<List<String>>?){
        italyLiveData.value = allAppsList
    }

    fun changeSwitzerlandDataSet(allAppsList: List<List<String>>?){
        switzerlandLiveData.value = allAppsList
    }

    fun changeCanadaDataSet(allAppsList: List<List<String>>?){
        canadaLiveData.value = allAppsList
    }
    fun changeSingaporeDataSet(allAppsList: List<List<String>>?){
        singaporeLiveData.value = allAppsList
    }

    fun changeSouthAfricaDataSet(allAppsList: List<List<String>>?){
        southAfricaLiveData.value = allAppsList
    }

    fun changeFranceDataSet(allAppsList: List<List<String>>?){
        franceLiveData.value = allAppsList
    }

    fun changeIndonesiaDataSet(allAppsList: List<List<String>>?){
        indonesiaLiveData.value = allAppsList
    }

    fun changeUkDataSet(allAppsList: List<List<String>>?){
        ukLiveData.value = allAppsList
    }

    fun changeJapanDataSet(allAppsList: List<List<String>>?){
        japanLiveData.value = allAppsList
    }

    fun changeBrazilDataSet(allAppsList: List<List<String>>?){
        brazilLiveData.value = allAppsList
    }

    fun changeNigeriaDataSet(allAppsList: List<List<String>>?){
        nigeriaLiveData.value = allAppsList
    }

    fun changePortugalDataSet(allAppsList: List<List<String>>?){
        portugalLiveData.value = allAppsList
    }

    fun changeAustraliaDataSet(allAppsList: List<List<String>>?){
        australiaLiveData.value = allAppsList
    }

    fun changeGreeceDataSet(allAppsList: List<List<String>>?){
        greeceLiveData.value = allAppsList
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