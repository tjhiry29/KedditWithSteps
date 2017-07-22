package rtjhie.kedditwithsteps.features.posts.adapters

import android.support.v4.util.SparseArrayCompat
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import rtjhie.kedditwithsteps.common.RedditPost
import rtjhie.kedditwithsteps.common.adapter.AdapterConstants
import rtjhie.kedditwithsteps.common.adapter.ViewType
import rtjhie.kedditwithsteps.common.adapter.ViewTypeDelegateAdapter
import java.util.*

/**
 * Created by Ryan Tjhie on 5/17/2017.
 */
class PostAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items : ArrayList<ViewType>
    private var delegateAdapters = SparseArrayCompat<ViewTypeDelegateAdapter>()
    private val loadingItem = object : ViewType {
        override fun getViewType() = AdapterConstants.LOADING
    }

    init {
        delegateAdapters.put(AdapterConstants.LOADING, LoadingDelegateAdapter())
        delegateAdapters.put(AdapterConstants.POST, PostDelegateAdapter())
        items = ArrayList()
        items.add(loadingItem)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        delegateAdapters.get(getItemViewType(position)).onBindViewHolder(holder, this.items[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return delegateAdapters.get(viewType).onCreateViewHolder(parent)
    }

    override fun getItemViewType(position: Int): Int {
        return this.items.get(position).getViewType()
    }

    fun addPosts(news: List<RedditPost>) {
        val initPosition = items.size - 1
        items.removeAt(initPosition)
        notifyItemRemoved(initPosition)

        items.addAll(news)
        items.add(loadingItem)
        notifyItemRangeChanged(initPosition, items.size + 1)
    }

    fun clearAndAddPosts(news: List<RedditPost>) {
        items.clear()
        notifyItemRangeRemoved(0, getLastPosition())

        items.addAll(news)
        items.add(loadingItem)
        notifyItemRangeInserted(0, items.size)
    }

    fun getPosts(): List<RedditPost> {
        return items
                .filter { it.getViewType() == AdapterConstants.POST }
                .map { it as RedditPost }
    }

    private fun getLastPosition() : Int {
        return if (items.lastIndex == -1) {
            0
        } else {
            items.lastIndex
        }
    }

}