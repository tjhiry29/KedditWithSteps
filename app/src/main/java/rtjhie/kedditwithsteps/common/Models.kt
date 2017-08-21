package rtjhie.kedditwithsteps.common

import rtjhie.kedditwithsteps.common.adapter.ViewType
import rtjhie.kedditwithsteps.common.adapter.AdapterConstants


/**
 * Created by Ryan Tjhie on 2/25/2017.
 */

data class RedditPost (
        val author: String,
        val title: String,
        val numComments: Int,
        val created: Long,
        val thumbnail: String,
        val url: String?,
        val subreddit: String,
        val downvotes: Int,
        val upvotes: Int
) : ViewType {
    override fun getViewType() = AdapterConstants.POST
}

data class RedditPosts (
        val after: String,
        val before: String,
        val children: List<RedditPost>) {

}

data class Subreddit(
        val displayName: String,
        val title: String) {
}

data class RedditSubreddits(
        val after: String,
        val children: List<Subreddit>)

