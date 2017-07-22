package rtjhie.kedditwithsteps.common.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

/**
 * Created by Ryan Tjhie on 5/17/2017.
 */
interface ViewTypeDelegateAdapter {

    fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder

    fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType)
}