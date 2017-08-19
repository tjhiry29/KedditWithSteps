package rtjhie.kedditwithsteps.api

import android.util.JsonReader
import android.util.Log
import com.squareup.moshi.Moshi
import org.json.JSONObject
import rtjhie.kedditwithsteps.common.RedditPost

/**
 * Created by Ryan Tjhie on 5/28/2017.
 */

class RedditListResponse(val data: RedditDataResponse)

class RedditDataResponse(
        val children: List<RedditChildrenResponse>,
        val after: String?,
        val before: String?
)

class RedditChildrenResponse(val kind : String, val data : Map<String, Any>) {}

class RedditPostDataResponse(
        val author: String,
        val title: String,
        val num_comments: Int,
        val created: Long,
        val thumbnail: String,
        val url: String?,
        val subreddit: String,
        val upvotes: Int,
        val downvotes: Int
)

class RedditSubredditDataResponse(
        val display_name: String,
        val title: String
)

class RedditModel(val kind : String, val data : Map<String, Any>) {
    var postData : RedditPostDataResponse? = null
    var subredditData : RedditSubredditDataResponse? = null

    init {
        val moshi = Moshi.Builder().build()
        val jsonAdapter = moshi.adapter(Map::class.java)
        val json = jsonAdapter.toJson(data)
        when (kind) {
            "t5" -> {
                subredditData = moshi.adapter(RedditSubredditDataResponse::class.java).fromJson(json)
            }
            else -> {
                postData = moshi.adapter(RedditPostDataResponse::class.java).fromJson(json)
            }
        }
    }
}