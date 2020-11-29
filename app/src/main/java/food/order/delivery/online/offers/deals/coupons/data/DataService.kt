package food.order.delivery.online.offers.deals.coupons.data

import food.order.delivery.online.offers.deals.coupons.model.AllAppsModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface DataService {
    @GET
    fun fetchAllApps(@Url url: String, @Query("key") key: String): Observable<AllAppsModel>

}