package rtjhie.kedditwithsteps.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Ryan Tjhie on 5/28/2017.
 */
interface RedditApi {
    @GET("/top.json")
    fun getPosts(@Query("after") after: String,
               @Query("limit") limit: String): Call<RedditListResponse>

    @GET("/r/{subreddit}.json")
    fun getPosts(@Path("subreddit") subreddit: String,
                          @Query("after") after: String,
                          @Query("limit") limit: String) : Call<RedditListResponse>

    @GET("/subreddits.json")
    fun getSubreddits(@Query("after") after: String,
                      @Query("limit") limit: String) : Call<RedditListResponse>
}

