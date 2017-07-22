package rtjhie.kedditwithsteps.common

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.LinearLayoutManager
import android.util.Log

/**
 * Created by Ryan Tjhie on 7/15/2017.
 */

class InfiniteScrollListener(
        val func: () -> Unit,
        val layoutManager: LinearLayoutManager) : RecyclerView.OnScrollListener() {

    private var previousTotal = 0
    private var loading = true
    private var visibleThreshold = 2
    private var firstVisibleItem = 0
    private var visibleItemCount = 0
    private var totalItemCount = 0

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        if (dy > 0) {
            visibleItemCount = recyclerView.childCount
            totalItemCount = layoutManager.itemCount
            firstVisibleItem = layoutManager.findFirstVisibleItemPosition()

            if (loading) { // if loading and new item count is higher than previous count, stop loading and update total.
                if (totalItemCount > previousTotal) {
                    loading = false;
                    previousTotal = totalItemCount;
                }
            }
            // If not loading and we the first visible item is at the last first visible item possible then load new set.
            if (!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold)) {
                Log.i("InfiniteScrollListener", "End reached")
                func()
                loading = true
            }
        }
    }
}
