package rtjhie.kedditwithsteps.features.posts.adapters

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*
import rtjhie.kedditwithsteps.R
import rtjhie.kedditwithsteps.common.RedditPost
import rtjhie.kedditwithsteps.common.adapter.ViewType
import rtjhie.kedditwithsteps.common.adapter.ViewTypeDelegateAdapter

/**
 * Created by Ryan Tjhie on 5/17/2017.
 */
class PostDelegateAdapter() : ViewTypeDelegateAdapter {

    interface onViewSelectedListener {
        fun onItemSelected(url: String?)
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return PostViewHolder(PostView().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
        holder as PostViewHolder
        holder.bind(item as RedditPost)
    }

}

class PostViewHolder(postView: View) : RecyclerView.ViewHolder(postView), PostDelegateAdapter.onViewSelectedListener {

    val thumbnailView : ImageView
    val titleView : TextView
    val subredditView : TextView
    val numCommentsView: TextView

    init {
        this.thumbnailView = postView.find(R.id.post_image_thumbnail)
        this.titleView = postView.find(R.id.post_title_text)
        this.subredditView = postView.find(R.id.post_subreddit_text)
        this.numCommentsView = postView.find(R.id.post_num_comments)
    }

    fun bind(item: RedditPost)  {
        titleView.setText(item.title)
        subredditView.setText("/r/" + item.subreddit)
        numCommentsView.setText(item.numComments.toString() + "comments")
        Picasso.with(thumbnailView.context).load(item.thumbnail).into(thumbnailView)
    }

    override fun onItemSelected(url: String?) {
        // TODO: Create an intent to start an external activity. (Preferably the browser)
    }
}


class PostView : AnkoComponent<ViewGroup> {

    override fun createView(ui: AnkoContext<ViewGroup>) : View {
        return with(ui) {
            verticalLayout {
//                backgroundDrawable = some drawable background
                linearLayout {
                    padding = dip(5)
                    orientation = LinearLayout.HORIZONTAL
                    imageView {
                        id = R.id.post_image_thumbnail
                    }.lparams(width = dip(50), height = dip(50)) {

                    }
                    textView {
                        id = R.id.post_title_text
                        textSize = 20f
                    }.lparams(width = wrapContent) {
                        leftMargin = dip(10)
                        verticalMargin = dip(5)
                    }
                }
                relativeLayout {
                    textView {
                        id = R.id.post_subreddit_text
                    }.lparams {
                        alignParentLeft()
                    }
                    textView {
                        id = R.id.post_num_comments
                    }.lparams {
                        alignParentRight()
                    }
                }
            }
        }
    }
}