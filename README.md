### Android Searchable Multi Select Spinner

>Searchable Multi Select Spinner dialog for android. It takes complex model objects list instead of just string list to make select-able dialog. It reduces the task of creating multi select spinner manually.


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
	        implementation 'com.github.telichada:SearchableMultiSelectSpinner:1.0'
	}
```

#### Sample searchable list by which list will be created.
```
        for (i in 0..20) {
            items.add(SearchableItem("Item $i", "$i"))
        }
```
#### How to call
```
        
            SearchableMultiSelectSpinner.show(this, "Select Items","Done", items, object :
                SelectionCompleteListener {
                override fun onCompleteSelection(selectedItems: ArrayList<SearchableItem>) {
                    Log.e("data", selectedItems.toString())
                }

            })
        
```
