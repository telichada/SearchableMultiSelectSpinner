package com.devstune.searchablemultiselectspinner

import android.content.Context
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
class SearchableSingleSelectSpinner {
    companion object {
        fun show(
            context: Context,
            title: String,
            searchableItems: MutableList<SearchableItem>,
            selectionCompleteListener: SingleSelectionCompleteListener
        ) {
            val alertDialogBuilder = AlertDialog.Builder(context)
            val inflater = LayoutInflater.from(context)
            val convertView = inflater.inflate(R.layout.searchable_list_layout, null)

            alertDialogBuilder.setView(convertView)
            alertDialogBuilder.setTitle(title)
            val dialog=alertDialogBuilder.create()

            val searchView = convertView.findViewById<SearchView>(R.id.searchView)
            val recyclerView =
                convertView.findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.recyclerView)
            val mLayoutManager = LinearLayoutManager(context)
            val adapter =
                SearchableAdapter(
                    context,
                    searchableItems,
                    searchableItems,
                    object : SearchableAdapter.ItemClickListener {
                        override fun onItemClicked(
                            item: SearchableItem,
                            position: Int,
                            b: Boolean
                        ) {
                            for (i in searchableItems.indices) {
                                if (searchableItems[i].code == item.code) {
                                    selectionCompleteListener.onCompleteSelection(searchableItems[i])
                                    dialog.dismiss()
                                }
                            }
                        }

                    },true)
            recyclerView.itemAnimator = null
            recyclerView.layoutManager = mLayoutManager
            recyclerView.adapter = adapter

            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String): Boolean {
                    // do something on text submit
                    return false
                }

                override fun onQueryTextChange(newText: String): Boolean {
                    // do something when text changes
                    adapter.filter.filter(newText)
                    return false
                }
            })
            dialog.show()
        }
    }

}