package connect.com.credr.connect.ui.franchiselogistics.coordinator.adapters.holder

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * Created by Keerti on 18-06-2020.
 */
abstract class BaseViewHolder<T>(itemView: View) :RecyclerView.ViewHolder(itemView) {
    //private var mCurrentPosition = 0



    protected abstract fun clear()
    /*open fun onBind(position: Int) {
        mCurrentPosition = position
        clear()
    }*/
    abstract fun bind(item: T,contex:Context)


    /*open fun getCurrentPosition(): Int {
        return mCurrentPosition
    }*/
}