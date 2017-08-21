package rtjhie.kedditwithsteps.features.drawer

import android.content.Context
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.view.ViewGroup
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import rtjhie.kedditwithsteps.R
import rtjhie.kedditwithsteps.api.PostAPI
import rtjhie.kedditwithsteps.api.RedditModel
import rtjhie.kedditwithsteps.api.RedditSubredditDataResponse
import rtjhie.kedditwithsteps.common.RedditSubreddits
import rtjhie.kedditwithsteps.common.Subreddit
import rtjhie.kedditwithsteps.features.posts.SubredditFragment
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by Ryan on 8/13/2017.
 */
class DrawerManager(val drawerContainer : ViewGroup, val subredditFragment: SubredditFragment?, private val api : PostAPI) {
    val drawerView : View
    var subreddits : RedditSubreddits? = null
    var subredditListView : RecyclerView? = null

    init {
        this.drawerView = DrawerUi().createView(AnkoContext.create(drawerContainer.context, drawerContainer, false))
    }

    fun initDrawer() {
        drawerContainer.removeAllViewsInLayout()
        drawerContainer.addView(drawerView)
        subredditListView = drawerView.find<RecyclerView>(R.id.drawer_subreddit_list)
        subredditListView?.layoutManager = LinearLayoutManager(drawerView.context)
        subredditListView?.adapter = SubredditAdapter()
        val subscription = requestSubreddits()
        subscription.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            retrievedsubreddits ->
                            subreddits = retrievedsubreddits
                            (subredditListView?.adapter as SubredditAdapter).addItems(retrievedsubreddits.children)
                            Log.d("debug", "received subreddits")
                        },
                        {
                            e ->
                            Snackbar.make(subredditListView as View, e.message ?: "", Snackbar.LENGTH_LONG).show()
                        }
                )
    }

    private fun requestSubreddits() : Observable<RedditSubreddits> {
        return Observable.create {
            subscriber ->
                val callResponse = api.getSubreddits(subreddits?.after ?: "", "10")
                val response = callResponse.execute()
                if (response.isSuccessful) {
                    val dataResponse = response.body().data
                    val subreddits = dataResponse.children.map {
                        val item = RedditModel(it.kind, it.data).subredditData as RedditSubredditDataResponse
                        Subreddit(item.display_name, item.title)
                    }
                    subscriber.onNext(RedditSubreddits(dataResponse.after ?: "", subreddits))
                    subscriber.onCompleted()
                } else {
                    subscriber.onError(Throwable(response.message()))
                }
        }

    }

    class DrawerUi : AnkoComponent<View> {
        override fun createView(ui: AnkoContext<View>): View {
            return with(ui) {
                verticalLayout {
                    recyclerView {
                        id = R.id.drawer_subreddit_list
                    }.lparams(matchParent, matchParent)
                }
            }
        }
    }
}