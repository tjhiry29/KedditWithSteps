package rtjhie.kedditwithsteps

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import org.jetbrains.anko.verticalLayout
import rtjhie.kedditwithsteps.features.posts.SubredditFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        verticalLayout {
            verticalLayout {
                id = 1
            }
            supportFragmentManager.beginTransaction().replace(1, SubredditFragment()).commit() // move fragment into the above vertical layout.
        }
    }
}

