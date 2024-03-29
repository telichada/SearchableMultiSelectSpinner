package com.devstune.multiselectsearchablespinner

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.devstune.searchablemultiselectspinner.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var items: MutableList<SearchableItem> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        for (i in 0..20) {
            items.add(SearchableItem("Item $i", "$i"))
        }
        button.setOnClickListener {
            SearchableMultiSelectSpinner.show(this, "Select Items","Done", items, object :
                SelectionCompleteListener {
                override fun onCompleteSelection(selectedItems: ArrayList<SearchableItem>) {
                    Log.e("data", selectedItems.toString())
                }

            })
        }
        buttonSingle.setOnClickListener {
            SearchableSingleSelectSpinner.show(this, "Select Item", items, object :
                SingleSelectionCompleteListener {
                override fun onCompleteSelection(selectedItem: SearchableItem) {
                    Log.e("data", selectedItem.toString())
                }

            })
        }


    }
}
