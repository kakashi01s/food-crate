package food.order.delivery.online.offers.deals.coupons.data

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class DataFactory {

    val URL_ALL_APPS = BASE_URL + "1x50W8k0Yq_wcO4EZKVbxA0DC0qqLcx0nxbOPvsREbpY/values/Sheet1!A2:E/"
    val URL_CAROUSEL_IMAGES = BASE_URL + "1Vd_yCaox6qx14CZtM3H-N2dUnGXYSCleTpGZPCb2qRA/values/Sheet1!A2:E/"
    val URL_TRENDING_DATA = BASE_URL + "1GNLz_mZREGuxEuKGEG_x9BtRgyl4jgX20xozEoLrL1g/values/Sheet1!A2:E/"
    val URL_DEALS_DATA = BASE_URL + "1PWqbWIil-YoQ5DNmPvRtUeUAkbXGiiYtgFtaWUMjvyc/values/Sheet1!A2:F/"
    val URL_CHINESE = BASE_URL + "18lgCNyZbjE0NxDzJ7M7uUFT5auE1TJAVYHyukQH7WVY/values/Sheet1!A2:E/"
    val URL_THALI = BASE_URL + "1-7iUmKXou4Btdf8ni3AFy1XzpGElxx4wZYaWhsYcRzs/values/Sheet1!A2:E/"
    val URL_DESSERT = BASE_URL + "1LNibcHywN3cjWUJb2kfDgy5JmaRPwWYr_B-IZJ8YZVo/values/Sheet1!A2:E/"
    val URL_NON_VEG = BASE_URL + "1ajFvTcFpSIZNZcB9PZUgcWosCB23z4oUbavr_5UJ1zY/values/Sheet1!A2:E/"
    val URL_SOUTH_INDIAN = BASE_URL + "1NTfFyMAvndalSJRQOkjnxiHINku3nRSbBJUY1DrELjs/values/Sheet1!A2:E/"
    val URL_FAST_FOOD = BASE_URL + "1cfRTDZ1m0NMDnredrrT0KizvWxNvZXWy55HNpWRRp-o/values/Sheet1!A2:E/"
    val URL_BAKERY = BASE_URL + "1_Ac7gycRIkNDY9ye6n-hgRgW_Lo4Dj2kTQ-u1LJGWIY/values/Sheet1!A2:E/"
    val URL_ITALIAN = BASE_URL + "1iOOoVLR7Osw6bkvJNnjhIN580RgrbytmiTo852IyY6k/values/Sheet1!A2:E/"
    val URL_BEVERAGES = BASE_URL + "1QnIHnqV_Ag7I5dlgwgVaqpm9DCR64jXfCvZ44QxztV8/values/Sheet1!A2:E/"
    val URL_VEGETARIAN = BASE_URL + "1Hc57g2ndDUpeV8I1yecwU42VIUIg0jvgAQhJwIES9XI/values/Sheet1!A2:E/"
    val URL_SEAFOOD = BASE_URL + "1nqjBqmpY_yxyHy4leZVuFZGkEZtgV2twbfAMberPaAI/values/Sheet1!A2:E/"
    val URL_NORTH_INDIAN = BASE_URL + "1KN5lepRvE3H5nO7wZ5SJmkIrUGii6-Nd2OLPk22Z-E4/values/Sheet1!A2:E/"
    val URL_MEXICAN = BASE_URL + "1usrl_RWgReSBbwR8hXS2KZ9UJ9VTsmIkBvZibv4QgCs/values/Sheet1!A2:E/"
    val URL_THAI = BASE_URL + "1sqmDx8Mgw8uddYRu8qnzu8dsPetAmJeghkwKLNVIGW0/values/Sheet1!A2:E/"
    val URL_USEFUL_APP = BASE_URL + "1rJWZ1hzbKKhm9p_2RSGLNHca2ep4GlFp6qG_r0x4w3Y/values/Sheet1!A2:D/"

    //Contonent
    val URL_AFRICAN = BASE_URL + "1EhnlUgrvcL-TlM1NfWGo-USAg7iDgzQEBWbbnIJCoRY/values/Sheet1!A2:E/"
    val URL_ANTARCTICA = BASE_URL + "1OJoifJuedoqiTqvClCQyDZItJMn0KH3hQ6wqUmXcxV0/values/Sheet1!A2:E/"
    val URL_ASIAN = BASE_URL + "17TBnUKKo5pCl0uVXHPOUpHXWy40M2JJukO3iAHkxL9w/values/Sheet1!A2:E/"
    val URL_EUROPIAN = BASE_URL + "1GiL2_tiThlEol2af8Tt0HqRCAmqq4FzeEByKWK9jHBw/values/Sheet1!A2:E/"
    val URL_NORTH_AMERICA = BASE_URL + "16U1AgWc4ce-Lux3h78izQOZ9pgVRp-ZRbR-tukvmnQk/values/Sheet1!A2:E/"
    val URL_SOUTH_AMERICA = BASE_URL + "1CzDy8xos1xZux6aigvzJLmbVzGNoAI1WGtoCAYYQAAk/values/Sheet1!A2:E/"
    val URL_AUSTRALIAN = BASE_URL + "1_HsCzLGiCoVGGdgT321Kmej7OY91AA5JkBENQP53pwg/values/Sheet1!A2:E/"
    val URL_VIDEO = BASE_URL + "1e01vtf6X1ueM3tppq70MeqSmAxz4A1rAiDJE6ZRMpHo/values/Sheet1!A2:E/"




    val URL_INDIA = BASE_URL + "1mWxgvXe-CTLs8Ubu96hEK5mNv45qMehIGm9J_4Xf3uY/values/Sheet1!A2:E/"
    val URL_USA = BASE_URL + "1yw8vcHgHbX4FsjJ3-CVHBrHuNE2KBegDGsfRHXki5xg/values/Sheet1!A2:E/"
    val URL_RUSSIA = BASE_URL + "1ZPgv8XOxgPK1pyqidh2YgbGXqA5NE-yaniQLSVXme_Q/values/Sheet1!A2:E/"
    val URL_PAKISTAN = BASE_URL + "1q9UgCbJ6upR0QBa68jLEtax-GmoydTSxHs19AvzeY7A/values/Sheet1!A2:E/"
    val URL_NEWZEALAND = BASE_URL + "1j4V1_uH94zp-ZvQ8wUl_FOaSNct2LuV2nUX0K7L9S24/values/Sheet1!A2:E/"
    val URL_GERMANY = BASE_URL + "1rs05OEKR73SJZz0zfjJfdRUDc5Wf-CULyba-9nvExhc/values/Sheet1!A2:E/"
    val URL_TURKEY = BASE_URL + "1wQ97jPkeBQLy7P3G1yMt67Igshe9ak2wVEqboJ42s04/values/Sheet1!A2:E/"
    val URL_UAE = BASE_URL + "1otEjwznt7h_OJt8pyaVqax7mTjo0IRG-1c4ntAYnLnA/values/Sheet1!A2:E/"
    val URL_ITALY = BASE_URL + "1Ldz48RvmBP2g7dpQpdDBsCHan39cXny6bLKW61k4v1M/values/Sheet1!A2:E/"
    val URL_SWITZERLAND = BASE_URL + "1zI7rRaXYl6OxbNembmHW0Oq1Y2XV165hZwUtI0K6OkQ/values/Sheet1!A2:E/"
    val URL_CANADA = BASE_URL + "1fxvM5X6Bwv51mVHKKD1zm3aK4I_QPwyrQ7cbRr2Q4uM/values/Sheet1!A2:E/"
    val URL_SINGAPORE = BASE_URL + "1DAcg8OXnUHz05rCcV6Pn0z-hSjKbWxT17icFYnFkkZM/values/Sheet1!A2:E/"
    val URL_SOUTH_AFRICA = BASE_URL + "1XyiwwzBAsgHao6BRZRb30u2_SWZ0DxSjGI_UmWMxoWY/values/Sheet1!A2:E/"
    val URL_FRANCE = BASE_URL + "1eh-CQQLqwvEsaIQU2JpiopBlNqxgxmQrJbxB424UYtE/values/Sheet1!A2:E/"
    val URL_INDONECIA = BASE_URL + "1xLSP-LFq_Ky9hPbILNyaEoJan2AVIs3NPJ8bNHarF_A/values/Sheet1!A2:E/"
    val URL_UK = BASE_URL + "1wz0Y2DX-wYaTGKd9lhn4OUBDHj9VeRTl0tPW_BULKDY/values/Sheet1!A2:E/"
    val URL_JAPAN = BASE_URL + "1VFNDfH4vZALFwVD_d_ydkDKYXcZ79W4aCxBUcS0Oe0g/values/Sheet1!A2:E/"
    val URL_BRAZIL = BASE_URL + "1OkSpKILsV5e1mCXAVtSW2nynogfMEfgW7_mQUxk8GkQ/values/Sheet1!A2:E/"
    val URL_NIGERIA = BASE_URL + "1RJqcac5eJU13m-q5LDKI1-7dkTx5gk6oAVNI8YR5UQk/values/Sheet1!A2:E/"
    val URL_PORTUGAL = BASE_URL + "1Wkt6x1zxYOBqRzLDjJdOKnGTCWhwsL0_TsWsZmUyixs/values/Sheet1!A2:E/"
    val URL_GREECE = BASE_URL + "1CZnAjgaHk7gUS_c5SYpbEkErbvMSErcT0AZQGjHIFio/values/Sheet1!A2:E/"
    val URL_AUSTRALIA = BASE_URL + "13-UOxJrJWkTdwVfc-waYUSaQUvA-UdHWlQmvifKzffc/values/Sheet1!A2:E/"
    val KEY = "AIzaSyDOMN71LUwroDWmIy-nImxWikJ2vWgCz9Y"

    companion object{
        private val BASE_URL = "https://sheets.googleapis.com/v4/spreadsheets/"

        fun create(): DataService? {
            val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
            return retrofit.create(DataService::class.java)
        }
    }
}