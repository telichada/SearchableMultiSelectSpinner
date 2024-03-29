### Android Searchable Multi Select Spinner

>Searchable Multi Select Spinner dialog for android. It takes complex model objects list instead of just string list to make select-able dialog. It reduces the task of creating multi select spinner manually.

![Screenshot](https://i.ibb.co/rp02c84/Screenshot-20200615-171919-Multi-Select-Searchable-Spinner.jpg)

#### Add following dependencies in project label build.gradle

```
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
#### Add following dependencies in app label build.gradle
```
	dependencies {
	        implementation 'com.github.telichada:SearchableMultiSelectSpinner:2.0'
	}
```

#### Sample searchable list by which list will be created.
```
        for (i in 0..20) {
            items.add(SearchableItem("Item $i", "$i"))
        }
```
#### Pre select some item.
> If you want to pre select some items then do like below-
```
items[yourIndex].isSelected=true

```
#### How to call
```   
    //SearchableMultiSelectSpinner.show() method params:
    1.context
    2.Dialog title
    3.dialog's positive button text
    4.Searchable Item array list
    5.A listener(SelectionCompleteListener) by which selected item will be listened
    
    SearchableMultiSelectSpinner.show(this, "Select Items","Done", items, object :
	SelectionCompleteListener {
	override fun onCompleteSelection(selectedItems: ArrayList<SearchableItem>) {
	    Log.e("data", selectedItems.toString())
	}

    })
    //for single selection use 2.0 version of this library
    SearchableSingleSelectSpinner.show(this, "Select Item", items, object :
        SingleSelectionCompleteListener {
        override fun onCompleteSelection(selectedItem: SearchableItem) {
             Log.e("data", selectedItem.toString())
        }

     })
```
