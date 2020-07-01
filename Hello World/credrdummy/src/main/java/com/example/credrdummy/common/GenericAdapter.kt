package connect.com.credr.connect.common

import android.app.Activity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filterable
import connect.com.credr.connect.ui.franchiselogistics.coordinator.adapters.holder.BaseViewHolder

/**
 * Created by Keerti on 13-06-2020.
 */
abstract class GenericAdapter<T>(private var listItems: MutableList<T>, private var context: Activity) : RecyclerView.Adapter<BaseViewHolder<T>>() {


     fun updateList(lead: Int) {
        listItems.removeAt(lead)
        notifyItemRemoved(lead)
        notifyItemRangeChanged(lead, listItems.size)

    }

    fun addList(_list: MutableList<T>) {
        listItems.addAll(_list)
        notifyDataSetChanged()
    }
    fun changeList(list: MutableList<T>) {
        this.listItems = list
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<T> {
        return getViewHolder(LayoutInflater.from(parent.context)
                .inflate(viewType, parent, false)
                , viewType)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        val element = listItems[position]
        ( holder ).bind(element,context)
    }

    override fun getItemCount(): Int {
        return listItems.size
    }

    override fun getItemViewType(position: Int): Int {
        return getLayoutId(position, listItems[position])
    }

    protected abstract fun getLayoutId(position: Int, obj: T): Int

    abstract fun getViewHolder(view: View, viewType: Int): BaseViewHolder<T>


}