package rtjhie.kedditwithsteps

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.widget.DrawerLayout
import android.view.Gravity
import android.view.View
import org.jetbrains.anko.*
import org.jetbrains.anko.appcompat.v7.themedToolbar
import org.jetbrains.anko.appcompat.v7.toolbar
import org.jetbrains.anko.support.v4.drawerLayout
import org.jetbrains.anko.verticalLayout
import rtjhie.kedditwithsteps.api.RedditApi
import rtjhie.kedditwithsteps.api.RestAPI
import rtjhie.kedditwithsteps.features.drawer.DrawerManager
import rtjhie.kedditwithsteps.features.posts.SubredditFragment

class MainActivity : AppCompatActivity() {

    private lateinit var subredditFragment : SubredditFragment
    private lateinit var drawerManager : DrawerManager
    private lateinit var mainActivityUi : View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        subredditFragment = SubredditFragment()

        mainActivityUi = MainActivityUi().createView(AnkoContext.create(this, this, true))
        setContentView(mainActivityUi)

        supportFragmentManager.beginTransaction().replace(R.id.main_container, subredditFragment).commit() // move fragment into the above vertical layout.
        setSupportActionBar(mainActivityUi.find(R.id.toolbar))

        drawerManager = DrawerManager(mainActivityUi.find(R.id.drawer_container), subredditFragment, RestAPI())
        drawerManager.initDrawer()

    }


    class MainActivityUi : AnkoComponent<MainActivity> {
        override fun createView(ui: AnkoContext<MainActivity>): View {
            return with(ui) {
                drawerLayout {
                    id = R.id.drawer
                    verticalLayout {
                        lparams(matchParent, matchParent, Gravity.NO_GRAVITY)
                        themedToolbar(R.style.AppTheme_PopupOverlay) {
                            lparams(matchParent, wrapContent)
                            id = R.id.toolbar
                            popupTheme = R.style.AppTheme_PopupOverlay
                            backgroundResource = R.color.colorAccent
                        }
                        frameLayout {
                            id = R.id.main_container
                        }
                    }
                    verticalLayout {
                        lparams(R.dimen.design_navigation_max_width, matchParent, Gravity.START)
                        frameLayout {
                            id = R.id.drawer_container
                        }
                    }
                }
            }
        }
    }
}

