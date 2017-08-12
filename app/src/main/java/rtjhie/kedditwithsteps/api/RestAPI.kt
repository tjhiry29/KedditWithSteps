package rtjhie.kedditwithsteps.api

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Created by Ryan Tjhie on 6/25/2017.
 */

class RestAPI() : PostAPI{
    private val redditApi: RedditApi

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://www.reddit.com")
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
        redditApi = retrofit.create(RedditApi::class.java)
    }

    override fun getPosts(after: String, limit: String): Call<RedditNewsResponse> {
        return redditApi.getPosts(after, limit)
    }

    override fun getPosts(subreddit: String, after: String, limit: String): Call<RedditNewsResponse> {
        return redditApi.getPosts(subreddit, after, limit)
    }
}
