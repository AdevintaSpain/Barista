# Barista
**The guy who serves great Espresso**

Espresso is a great tool to test our Android apps via instrumental tests. With them, we can mimic user actions like clicking a button, scrolling a list, selecting an item on a spinner or swiping on a pager. Then, we can assert that a text appears in the screen, an image is visible or invisible, or a button is enabled or not.

On the other hand, if you tried Espresso, you’ll agree that its API is not discoverable.

Barista introduces a discoverable API for the Espresso features. So, you and all the Android team will write instrumental tests with no effort. 

### Barista’s Actions API
```java
// Click widgets
click(R.id.button);
click("Next");
clickBack();

// Writing into widgets
writeToEditText(R.id.edittext, "A great text");
writeToAutoCompleteTextView(R.id.autocomplete, "Another great text");

// Select items on AdapterViews
clickListViewItem(R.id.listview, 4);
clickListViewItem(R.id.listview, 4, 5, 6);
clickRecyclerViewItem(R.id.recycler, 2);
clickRecyclerViewItem(R.id.recycler, 2, 3, 4);
clickSpinnerItem(R.id.spinner, 1);

// Select items on RadioButtons and CheckBoxes
clickCheckBoxItem(R.id.first_item);
clickCheckBoxItem("The checkbox text");
clickRadioButtonItem(R.id.radiogroup, R.id.first_item);
clickRadioButtonItem(R.id.radiogroup, "The radio text");

// Pick data on pickers
setDateOnPicker(1986, 03, 23);

// Scroll on scrolls and pagers
scrollTo(R.id.button);
scrollTo("A widget with this text");
swipePagerForward(R.id.viewPager);
swipePagerBack(R.id.viewPager);
```

### Barista’s Assertions API
```java
// Is this view displayed?
assertTextIsDisplayed("Hello world");
assertTextIsDisplayed(R.string.hello_world);
assertViewIsDisplayed(R.id.button);

// ...or not?
assertTextIsNotDisplayed("Hello world");
assertTextIsNotDisplayed(R.string.hello_world);
assertViewIsNotDisplayed(R.id.button);

// Is this view enabled?
assertTextIsEnabled("Hello world");
assertTextIsEnabled(R.string.hello_world);
assertViewIsEnabled(R.id.button);

// ...or not?
assertTextIsDisabled("Hello world");
assertTextIsDisabled(R.string.hello_world);
assertViewIsDisabled(R.id.button);

// Hope this view doesn't exist!
assertTextDoesNotExist("Hello world");
assertTextDoesNotExist(R.string.hello_world);
assertViewDoesNotExist(R.id.button);

// And another tricky feature
assertThatBackButtonClosesTheApp();
```
# License

```
Copyright 2017 Schibsted Classified Media Spain S.L.


Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
