package rtjhie.kedditwithsteps.features.posts

import retrofit2.Call
import rtjhie.kedditwithsteps.api.*
import rtjhie.kedditwithsteps.common.RedditPost
import rtjhie.kedditwithsteps.common.RedditPosts
import rx.Observable

/**
 * Created by Ryan Tjhie on 2/26/2017.
 */

class SubredditManager(private val api: PostAPI) {

    fun getPosts(after: String, limit: String = "10", subreddit: String? = null): Observable<RedditPosts> {
        return Observable.create {
            subscriber ->
            var callResponse : Call<RedditNewsResponse>? = null
            if (subreddit == null) {
                callResponse = api.getPosts(after, limit)
            } else {
                callResponse = api.getPosts(subreddit, after, limit)
            }
            val response = callResponse.execute()

            if (response.isSuccessful) {
                val dataResponse = response.body().data
                val posts = dataResponse.children.map {
                    val item = it.data
                    RedditPost(item.author, item.title, item.num_comments, item.created, item.thumbnail,
                            item.url, item.subreddit, item.upvotes, item.downvotes)
                }
                val redditPosts = RedditPosts(
                        dataResponse.after ?: "",
                        dataResponse.before ?: "",
                        posts
                )
                subscriber.onNext(redditPosts)
                subscriber.onCompleted()
            } else {
                subscriber.onError(Throwable(response.message()))
            }

        }
    }


}