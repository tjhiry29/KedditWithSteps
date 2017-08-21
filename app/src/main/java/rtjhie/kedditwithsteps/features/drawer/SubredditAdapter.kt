package rtjhie.kedditwithsteps.features.drawer

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import org.jetbrains.anko.*
import rtjhie.kedditwithsteps.R
import rtjhie.kedditwithsteps.common.Subreddit
import java.util.*

/**
 * Created by Ryan on 8/20/2017.
 */
class SubredditAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var items : ArrayList<Subreddit>

    init {
        items = ArrayList()
        Log.d("debug", "init subreddit adapter")
    }

    fun addItems(items : List<Subreddit>) {
        val startPos = items.size - 1
        this.items.addAll(items)
        notifyItemRangeChanged(startPos, items.size)
    }

    fun clearItems() {
        val endPos = items.size - 1
        this.items.clear();
        notifyItemRangeChanged(0, endPos)
    }

    fun clearAndAddItems(items: List<Subreddit>) {
        val endPos = items.size - 1
        this.items.clear();
        notifyItemRangeRemoved(0, items.lastIndex)

        this.items.addAll(items);
        notifyItemRangeInserted(0, items.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        Log.d("debug", "create view holder")
        return SubredditViewHolder(SubredditView().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        holder as SubredditViewHolder
        holder.bind(this.items[position])
    }

}

class SubredditViewHolder(subredditView: View) : RecyclerView.ViewHolder(subredditView) {

    val subredditTitleView : TextView = subredditView.find<TextView>(R.id.subreddit_list_title)

    fun bind(subreddit: Subreddit) {
        subredditTitleView.text = subreddit.displayName
        Log.d("debug", subreddit.displayName)
    }
}

class SubredditView : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui) {
            verticalLayout{
                lparams(matchParent, wrapContent)
                textView {
                    id = R.id.subreddit_list_title
                    textSize = 20f
                }
            }
        }
    }
}