package rtjhie.kedditwithsteps.common

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by Ryan Tjhie on 2/25/2017.
 */
fun ViewGroup.inflate(layoutId: Int) : View {
    return LayoutInflater.from(context).inflate(layoutId, this, false);
}