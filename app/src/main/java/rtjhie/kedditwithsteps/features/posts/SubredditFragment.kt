package rtjhie.kedditwithsteps.features.posts

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.UI
import org.jetbrains.anko.support.v4.ctx
import rtjhie.kedditwithsteps.R
import rtjhie.kedditwithsteps.api.RestAPI
import rtjhie.kedditwithsteps.common.InfiniteScrollListener
import rtjhie.kedditwithsteps.common.RedditPosts
import rtjhie.kedditwithsteps.common.RxBaseFragment
import rtjhie.kedditwithsteps.features.posts.adapters.PostAdapter
import rtjhie.kedditwithsteps.features.posts.adapters.PostDelegateAdapter
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by Ryan Tjhie on 2/25/2017.
 */
class SubredditFragment : RxBaseFragment(), PostDelegateAdapter.onViewSelectedListener {
    private var redditPosts: RedditPosts? = null
    private var postList: RecyclerView? = null
    private val postManager: SubredditManager = SubredditManager(RestAPI())

    override fun onItemSelected(url: String?) {
        // TODO load comments if no url (text post) or load url.
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val linearLayout = LinearLayoutManager(context)
        postList?.layoutManager = linearLayout
        postList?.clearOnScrollListeners()
        postList?.addOnScrollListener(InfiniteScrollListener({ requestNews() }, linearLayout))

        initAdapter()
        if (savedInstanceState == null){
            requestNews()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = SubredditFragmentUi<Fragment>().createView(AnkoContext.create(activity, this))
        postList = view.find(R.id.recycler_view)
        return view
    }

    private fun requestNews() {
        val subscription = postManager.getPosts(redditPosts?.after ?: "", "", "globaloffensive")
        .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe (
                        {
                            retrievedPosts ->
                                redditPosts = retrievedPosts
                                (postList?.adapter as PostAdapter).addPosts(retrievedPosts.children)
                        },
                        {
                            e ->
                                Snackbar.make(postList as View, e.message ?: "", Snackbar.LENGTH_LONG).show()
                        }
                )
        subscriptions.add(subscription)
    }

    private fun initAdapter() {
        if (postList?.adapter == null) {
            postList?.adapter = PostAdapter()
        }
    }

    class SubredditFragmentUi<T> : AnkoComponent<T> {

        override fun createView(ui: AnkoContext<T>) = with(ui) {
            verticalLayout {
                lparams(matchParent, matchParent)
                recyclerView {
                    lparams(matchParent, matchParent)
                    id = R.id.recycler_view
                    val orientation = LinearLayoutManager.VERTICAL
                    layoutManager = LinearLayoutManager(context, orientation, true)
                    overScrollMode = View.OVER_SCROLL_NEVER
                }
            }
        }
    }

}

