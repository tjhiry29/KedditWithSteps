package rtjhie.kedditwithsteps.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Ryan Tjhie on 5/28/2017.
 */
interface RedditApi {
    @GET("/top.json")
    fun getTop(@Query("after") after: String,
               @Query("limit") limit: String): Call<RedditNewsResponse>

}

