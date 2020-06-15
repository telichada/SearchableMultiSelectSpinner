package com.devstune.searchablemultiselectspinner

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.row_multi_select_item.view.*
import java.util.*

class SearchableAdapter(
    internal var context: Context,
    private val mValues: List<SearchableItem>,
    private var filteredList: List<SearchableItem>,
    clickListener: ItemClickListener
) : Filterable, RecyclerView.Adapter<SearchableAdapter.ViewHolder>() {
    private var itemClickListener: ItemClickListener = clickListener

    inner class ViewHolder(val mView: View) :
        RecyclerView.ViewHolder(mView) {

        internal var titleTextView = mView.titleTextView
        internal var checkBox = mView.checkBox

        var mItem: SearchableItem? = null

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_multi_select_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.mItem = filteredList[holder.adapterPosition]

        holder.checkBox.setOnCheckedChangeListener(null)
        holder.titleTextView.text = holder.mItem!!.text
        holder.checkBox.isChecked = holder.mItem!!.isSelected

        var productPosition = 0
        for (i in mValues.indices) {
            if (mValues[i].code.equals(holder.mItem!!.code)) {
                productPosition = i
            }
        }

        holder.checkBox.setOnCheckedChangeListener { buttonView, isChecked ->
            itemClickListener.onItemClicked(
                filteredList[holder.adapterPosition],
                productPosition,
                isChecked
            )
        }

        holder.mView.setOnClickListener { view ->
            holder.checkBox.isChecked = !holder.checkBox.isChecked
        }

    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): Filter.FilterResults {
                val charString = charSequence.toString()
                if (charString.isEmpty()) {
                    filteredList = mValues
                } else {
                    val tempList = ArrayList<SearchableItem>()
                    for (row in mValues) {
                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.text.toLowerCase(Locale.getDefault())
                                .contains(charString.toLowerCase(Locale.getDefault()))
                        ) {
                            tempList.add(row)
                        }
                    }

                    filteredList = tempList
                }

                val filterResults = Filter.FilterResults()
                filterResults.values = filteredList
                return filterResults
            }

            override fun publishResults(
                charSequence: CharSequence,
                filterResults: Filter.FilterResults
            ) {
                filteredList = filterResults.values as ArrayList<SearchableItem>

                // refresh the list with filtered data
                notifyDataSetChanged()
            }
        }
    }

    override fun getItemCount(): Int {
        return filteredList.size
    }

    companion object {
        lateinit var itemClickListener: ItemClickListener
    }


    interface ItemClickListener {
        fun onItemClicked(item:SearchableItem,position: Int, b: Boolean)
    }
}