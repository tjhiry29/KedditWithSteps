package rtjhie.kedditwithsteps.features.posts.adapters

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import rtjhie.kedditwithsteps.common.adapter.ViewType
import rtjhie.kedditwithsteps.common.adapter.ViewTypeDelegateAdapter
import org.jetbrains.anko.*

/**
 * Created by Ryan Tjhie on 5/17/2017.
 */
class LoadingDelegateAdapter : ViewTypeDelegateAdapter{
    override fun onCreateViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder {
        return LoadingViewHolder(LoadingView().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {

    }

}

class LoadingViewHolder(loadingView: View) : RecyclerView.ViewHolder(loadingView){}

class LoadingView : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>) : View {
        return with(ui) {
            relativeLayout {
                progressBar {
                    lparams(width = matchParent, height = wrapContent) {
                        centerInParent()
                    }
                }
            }
        }
    }
}