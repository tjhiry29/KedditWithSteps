package rtjhie.kedditwithsteps.features.drawer

import android.content.Context
import android.view.View
import android.view.ViewGroup
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.textView
import org.jetbrains.anko.verticalLayout
import rtjhie.kedditwithsteps.features.posts.SubredditFragment

/**
 * Created by Ryan on 8/13/2017.
 */
class DrawerManager(drawerContainer : ViewGroup, subredditFragment: SubredditFragment?) {
    val drawerContainer : ViewGroup
    val drawerView : View
    val subredditFragment : SubredditFragment?


    init {
        this.drawerView = DrawerUi().createView(AnkoContext.create(drawerContainer.context, drawerContainer, false))
        this.drawerContainer = drawerContainer
        this.subredditFragment = subredditFragment
    }

    fun initDrawer() {
        drawerContainer.removeAllViewsInLayout()
        drawerContainer.addView(drawerView)
    }

    class DrawerUi : AnkoComponent<View> {

        override fun createView(ui: AnkoContext<View>): View {
            return with(ui) {
                verticalLayout {
                    // TODO: fill out a listView here somehow.
                    textView {
                        text = "Test"
                    }
                }
            }
        }

    }
}