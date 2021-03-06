package rtjhie.kedditwithsteps.api

import retrofit2.Call

/**
 * Created by Ryan Tjhie on 5/28/2017.
 */

interface PostAPI {
    fun getPosts(after: String, limit: String): Call<RedditListResponse>
    fun getPosts(subreddit: String, after: String, limit: String): Call<RedditListResponse>
    fun getSubreddits(after: String, limit: String) : Call<RedditListResponse>
}
