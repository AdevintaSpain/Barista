[![Build Status](https://travis-ci.org/SchibstedSpain/Barista.svg?branch=master)](https://travis-ci.org/SchibstedSpain/Barista)
[![Download](https://api.bintray.com/packages/schibstedspain/maven/barista/images/download.svg)](https://bintray.com/schibstedspain/maven/barista/_latestVersion)

# Barista
**The guy who serves a great Espresso**

<img src="https://github.com/SchibstedSpain/Barista/blob/update-logo/art/barista-logo.svg" width="275" />

Espresso is a great tool to test our Android apps via instrumental tests. With them, we can mimic user actions like clicking a button, scrolling a list, selecting an item on a spinner or swiping on a pager. Then, we can assert that a text appears in the screen, an image is visible or invisible, or a button is enabled or not.

On the other hand, if you tried Espresso, you’ll agree that its API is not discoverable.

Barista introduces a discoverable API for the Espresso features. So, you and all the Android team will write instrumental tests with no effort.

### Barista’s Actions API
```java
// Click widgets
click(R.id.button);
click(R.string.button_text);
click("Next");
clickBack();

// Click menu items, also overflowed ones
clickMenu(R.id.menu_item);

// Writing into widgets
writeToEditText(R.id.edittext, "A great text");
writeToAutoCompleteTextView(R.id.autocomplete, "Another great text");

// Select items on AdapterViews
clickListViewItem(R.id.listview, 4);
clickListViewItem(R.id.listview, 4, 5, 6);
clickRecyclerViewItem(R.id.recycler, 2);
clickRecyclerViewItem(R.id.recycler, 2, 3, 4);
clickRecyclerViewItemChild(R.id.recycler, 3, R.id.button);
clickRecyclerViewItemChild(R.id.recycler, 3, "Button");

clickSpinnerItem(R.id.spinner, 1);

// Scroll on AdapterViews
scrollTo(R.id.recycler, 42);

// Select items on RadioButtons and CheckBoxes
clickCheckBoxItem(R.id.checkbox_item);
clickCheckBoxItem("The checkbox text");
clickRadioButtonItem(R.id.radiogroup, R.id.radio_item);
clickRadioButtonItem(R.id.radiogroup, "The radio text");
clickRadioButtonPosition(R.id.radiogroup, 42);

// Pick data on pickers
setDateOnPicker(1986, 03, 23);

// Interact with dialogs
clickDialogPositiveButton();
clickDialogNeutralButton();
clickDialogNegativeButton();

// Scroll on scrolls and pagers
scrollTo(R.id.button);
scrollTo("A widget with this text");
swipeViewPagerForward();
swipeViewPagerForward(R.id.pager);
swipeViewPagerBack();
swipeViewPagerBack(R.id.pager);

// Interact with the navigation drawer
openDrawer(R.id.drawer);
closeDrawer(R.id.drawer);

// Pull to refresh in SwipeRefreshLayout
refresh(R.id.swiperefresh);

// And another tricky feature
sleep(2000);
sleep(2, SECONDS);
```

### Barista’s Assertions API
```java
// Is this view displayed?
assertDisplayed("Hello world");
assertDisplayed(R.string.hello_world);
assertDisplayed(R.id.button);

// ...or not?
assertNotDisplayed("Hello world");
assertNotDisplayed(R.string.hello_world);
assertNotDisplayed(R.id.button);

// Is this view enabled?
assertEnabled("Hello world");
assertEnabled(R.string.hello_world);
assertEnabled(R.id.button);

// ...or not?
assertDisabled("Hello world");
assertDisabled(R.string.hello_world);
assertDisabled(R.id.button);

// Hope this view doesn't exist!
assertNotExist("Hello world");
assertNotExist(R.string.hello_world);
assertNotExist(R.id.button);

// Is the expected checkbox checked?
assertChecked("Checked checkbox");
assertChecked(R.string.checked_checkbox);
assertChecked(R.id.checked_checkbox);

// ...And the other checkbox unchecked?
assertUnchecked("Unchecked checkbox");
assertUnchecked(R.string.unchecked_checkbox);
assertUnchecked(R.id.unchecked_checkbox);

// What's the state of the Drawer?
assertDrawerIsOpen(R.id.drawer);
assertDrawerIsClosed(R.id.drawer);

// Check EditText's hints
assertHint(R.id.edittext, R.string.hint);
assertHint(R.id.edittext, "Hint");

// Check recyclerView item count against expected item count
assertRecyclerViewItemCount(R.id.recycler, 10);

// And another tricky feature
assertThatBackButtonClosesTheApp();
```

## Dealing with the runtime permissions dialog

The new Marshmallow permissions system requires checking for permissions at runtime. As Espresso can't interact with the system dialog, Barista offers a way to allow permissions when needed.

```java
PermissionGranter.allowPermissionsIfNeeded(Manifest.permission.GET_ACCOUNTS);
```

## Resetting the app's data before running each test

As tests should be isolated, they need to set the environment before running. Espresso doesn't help achieving it but Barista offers a set of rules to clear the app's data before running each test.

```java
@Rule public ClearPreferencesRule clearPreferencesRule = new ClearPreferencesRule(); // Clear all app's SharedPreferences
@Rule public ClearDatabaseRule clearDatabaseRule = new ClearDatabaseRule(); // Delete all tables from all the app's SQLite Databases
@Rule public ClearFilesRule clearFilesRule = new ClearFilesRule(); // Delete all files in getFilesDir() and getCacheDir()
```

## Dealing with Flaky tests

We should try to write deterministic tests, but when everything else fails Barista helps you deal with flaky tests using a specific ActivityTestRule and a couple of annotations that repeat your tests multiple times.

```java
// Use this rule instead of Espresso's ActivityTestRule
@Rule
public FlakyActivityTestRule<FlakyActivity> activityRule = new FlakyActivityTestRule<>(FlakyActivity.class, true, false);

// Use @AllowFlaky to let flaky tests pass if they pass any time.
@Test
@AllowFlaky(attempts = 5)
public void some_flaky_test() throws Exception {
  // ...
}

// Use @Repeat to avoid flaky tests from passing if any repetition fails.
@Test
@Repeat(times = 5)
public void some_important_test() throws Exception {
  // ...
}
```

## One rule to rule them all

All previous rules can be added at the same time by just adding the BaristaRule.
```java
@Rule
public BaristaRule<MyActivity> baristaRule = BaristaRule.create(MyActivity.class);

//...

baristaRule.launchActivity();
```

The rule assumes some sane defaults:
- Retry flaky tests: 10 attempts
- Launch activity automatically: false
- Initial touch mode enabled: true
- Clear preferences
- Clear databases
- Clear files


## Magic that Barista does for you

In order to speed up testing, Barista keeps in mind some considerations.
- **Scrolls when needed**: Interacting with Espresso in a `ScrollView` requires you to scroll to each view, which sometimes doesn't work the first time. Also trying to scroll outside a `ScrollView` produces an `Exception`, forcing you to change the test depending on the layout. To keep tests simpler, Barista scrolls automatically before interacting with any `View`, and only does it if needed.
- **Scrolls on all views**: Barista scrolls on all scrollable views, including `NestedScrollView`. Espresso only handles `ScrollView` and `HorizontalScrollView`, so people need to open questions on [StackOverflow like this](https://stackoverflow.com/questions/35272953/espresso-scrolling-not-working-when-nestedscrollview-or-recyclerview-is-in-coor). Or... just use **Barista**.
- **Just interacts with displayed Views**: Interacting with `View`s inside a `ViewPager` throws `AmbiguousViewMatcherException`, cos the views you interact with will be potentially repeated on different pages. Barista only interacts with displayed widgets, so you can focus on the behavior instead of wasting time on details.

# Download

```gradle
androidTestCompile('com.schibsted.spain:barista:1.5.1') {
  exclude group: 'com.android.support'
}
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
