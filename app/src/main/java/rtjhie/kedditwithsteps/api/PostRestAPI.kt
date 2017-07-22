package rtjhie.kedditwithsteps.api

import retrofit2.Call

/**
 * Created by Ryan Tjhie on 5/28/2017.
 */

class PostRestAPI(private val redditApi: RedditApi) : PostAPI {

    override fun getPosts(after: String, limit: String): Call<RedditNewsResponse> {
        return redditApi.getTop(after, limit)
    }
}