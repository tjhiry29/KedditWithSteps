package rtjhie.kedditwithsteps

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import org.jetbrains.anko.*
import org.jetbrains.anko.appcompat.v7.themedToolbar
import org.jetbrains.anko.appcompat.v7.toolbar
import org.jetbrains.anko.support.v4.drawerLayout
import org.jetbrains.anko.verticalLayout
import rtjhie.kedditwithsteps.features.posts.SubredditFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mainActivityUi = MainActivityUi().createView(AnkoContext.create(this, true))
        supportFragmentManager.beginTransaction().replace(R.id.main_container, SubredditFragment()).commit() // move fragment into the above vertical layout.
        setSupportActionBar(mainActivityUi.find(R.id.toolbar))
    }
}

class MainActivityUi : AnkoComponent<Context> {
    override fun createView(ui: AnkoContext<Context>): View {
        return with(ui) {
            drawerLayout {
                verticalLayout {
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
                    lparams(240, matchParent, gravity = left)
                    // Empty for sidebar.
                    textView {
                        text = "Test text"
                    }
                }
            }
        }
    }
}
