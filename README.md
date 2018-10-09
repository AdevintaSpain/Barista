# Barista
**The guy who serves a great Espresso**

[![Travis](https://img.shields.io/travis/rust-lang/rust.svg?label=Travis+CI)](https://travis-ci.org/SchibstedSpain/Barista)
[![Download](https://api.bintray.com/packages/schibstedspain/maven/barista/images/download.svg)](https://bintray.com/schibstedspain/maven/barista/_latestVersion)
[![Hex.pm](https://img.shields.io/hexpm/l/plug.svg)](LICENSE.md)

<img src="art/barista-logo.svg" width="30%"/>

Barista makes developing UI test faster, easier and more predictable. Built on top of Espresso, it provides a simple an discoverable API, removing most of the boilerplate and verbosity of common Espresso tasks. You and your Android team will write tests with no effort.

 - [Download](#download)
 - [API Overview](#api-overview)
    - [Interactions](#baristas-interactions-api)
    - [Assertions](#baristas-assertions-api)
    - [Intents](#baristas-intents-api)
    - [Runtime Permissions](#runtime-permissions)
    - [Useful test rules](#useful-test-rules)
      - [Reseting app data](#reseting-app-data)
      - [Dealing with Flaky tests](#dealing-with-flaky-tests)
      - [One rule to rule them all](#one-rule-to-rule-them-all)
  - [Magic that Barista does for you](#magic-that-barista-does-for-you)
  - [License](#license)

# Download

_Psst, hey. Migrating to Barista 2? [Check out this guide](MIGRATION-2.md) to help you with the transition._

Import Barista as a testing dependency:
```gradle
androidTestImplementation('com.schibsted.spain:barista:2.7.0') {
  exclude group: 'com.android.support'
  exclude group: 'org.jetbrains.kotlin' // Only if you already use Kotlin in your project
}
```

You might need to include the Google Maven repository, required by Espresso 3:
```gradle
repositories {
    google()
}
```


Barista already includes `espresso-core` and `espresso-contrib`. If you need [any other Espresso package](https://developer.android.com/topic/libraries/testing-support-library/packages.html#atsl-dependencies) you can add them yourself.

# API Overview

## Barista’s Interactions API
```java
// Click widgets
clickOn(R.id.button);
clickOn(R.string.button_text);
clickOn("Next");
clickBack();

// Long click widgets
longClickOn(R.id.button);
longClickOn(R.string.button_text);
longClickOn("Next");

// Click menu items, with or without overflow
clickMenu(R.id.menu_item);

// Writing into widgets
writeTo(R.id.edittext, "A great text");
writeToAutoComplete(R.id.autocomplete, "Another great text");
clearText(R.id.edittext)

// Operate on ListViews and RecyclerViews indistinctly by position
clickListItem(R.id.list, 4);
clickListItemChild(R.id.list, 3, R.id.row_button);
scrollListToPosition(R.id.list, 4);
assertListItemCount(R.id.listId, 5)
assertDisplayedAtPosition(R.id.recycler, 0, "text");
assertDisplayedAtPosition(R.id.listId, 0, R.id.text_field, "text");

clickSpinnerItem(R.id.spinner, 1);

// Select items on RadioButtons
clickRadioButtonItem(R.id.radiogroup, R.id.radio_item);
clickRadioButtonItem(R.id.radiogroup, "The radio text");
clickRadioButtonPosition(R.id.radiogroup, 42);

// Pick data on pickers
setDateOnPicker(1986, 03, 23);
setTimeOnPicker(17, 2);

// Interact with dialogs
clickDialogPositiveButton();
clickDialogNeutralButton();
clickDialogNegativeButton();

// Scroll on scrolls and pagers
scrollTo(R.id.far_away_widget);
scrollTo(R.string.text);
scrollTo("A widget with this text");
swipeViewPagerForward();
swipeViewPagerBack();

// Interact with the navigation drawer
openDrawer();
openDrawerWithGravity(Gravity.RIGHT);
closeDrawer();
closeDrawerWithGravity(Gravity.RIGHT);

// Interact with SeekBars
setProgressTo(R.id.seek_bar, 5);
setProgressToMin(R.id.seek_bar);
setProgressToMax(R.id.seek_bar);


// Pull to refresh in SwipeRefreshLayout
refresh(R.id.swipe_refresh);
refresh(); // Id is optional! We'll find it for you :D

// Close or press ime actions on the Keyboard
closeKeyboard()
pressImeActionButton()

// And another tricky feature, but try not to use it
sleep(2000);
sleep(2, SECONDS);
```

## Barista’s Assertions API
```java
// Is this view displayed?
assertDisplayed("Hello world");
assertDisplayed(R.string.hello_world);
assertDisplayed(R.id.button);
assertDisplayed(R.id.button, "Hello world")

// ...or not?
assertNotDisplayed("Hello world");
assertNotDisplayed(R.string.hello_world);
assertNotDisplayed(R.id.button);
assertNotDisplayed(R.id.button, "Hello world")

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

// ...and the other checkbox unchecked?
assertUnchecked("Unchecked checkbox");
assertUnchecked(R.string.unchecked_checkbox);
assertUnchecked(R.id.unchecked_checkbox);

// Does this view have the focus?
assertFocused(R.id.focused_view)
assertFocused("Button")

// ... or not?
assertNotFocused(R.id.focused_view)
assertNotFocused("Button")

// Is this ImageView showing a drawable?
assertHasAnyDrawable(R.id.image_view);
assertHasDrawable(R.id.image_view, R.drawable.ic_barista);

// ...or not?
assertHasNoDrawable(R.id.image_view);

// Does this View have a background?
assertHasAnyBackground(R.id.view);
assertHasBackground(R.id.view, R.drawable.ic_barista);

// ...or not?
assertHasNoBackground(R.id.view);

// What's the state of the Drawer?
assertDrawerIsOpen();
assertDrawerIsOpenWithGravity(Gravity.RIGHT);
assertDrawerIsClosed();
assertDrawerIsClosedWithGravity(Gravity.RIGHT);

// Check TextInputLayout and EditText's hints
assertHint(R.id.edittext, R.string.hint);
assertHint(R.id.edittext, "Hint");

// Check TextInputLayout and EditText's errors
assertError(R.id.edittext, R.string.error);
assertError(R.id.edittext, "Error message");

// Check if text on screen contains given text
assertContains("text");
assertContains(R.id.textview, "text");

// ...or not?
assertNotContains("text");
assertNotContains(R.id.textview, "text");

// Check text is given color
assertTextColorIs(R.id.someRedText, R.color.red);
assertTextColorIs(R.id.someColorListText, R.color.state_list);

// ...or not?
assertTextColorIsNot(R.id.someRedText, R.color.blue);
assertTextColorIsNot(R.id.someColorListText, R.color.another_state_list);

// Check recyclerView item count against expected item count
assertRecyclerViewItemCount(R.id.recycler, 10);

// And another tricky feature
assertThatBackButtonClosesTheApp();

// Is this ImageView showing a drawable?
assertHasAnyDrawable(R.id.image_view);
assertHasDrawable(R.id.image_view, R.drawable.ic_barista);

// ...or not?
assertHasNoDrawable(R.id.image_view);


// Is this ProgressBar/SeekBar progress?
assertProgress(R.id.seek_bar, 5)
assertProgressIsMin(R.id.seek_bar)
assertProgressIsMax(R.id.seek_bar)


```

### Custom assertions

If you have a special case not covered by the given assertions API, we encourage you to assert these special cases with our custom assertions API. It's a convenient way to replace plain `Matcher`s with complex assertions. With Barista, you can match any kind of view by knowing its type and passing its `viewId`, `text`, or a `Matcher<View>`. Once you matched it, you will be able to assert all its properties without adding any complex `Matcher` to your project.

```kotlin

// Matching a Button by text
assertAny<Button>("Save") {
    it.enabled == true
}

// Matching a RadioGroup by id
assertAny<RadioGroup>(R.id.radioGroup) {
    it.checkedRadioButtonId == R.id.option1
}

// Matching a Progressbar by a Matcher
assertAny<Progressbar>(withId(R.id.progressBar)) {
    it.progress == 42
}

// You can also define the assertion description that will be shown if the assertion fails
assertAny<RadioGroup>(R.id.radioGroup, "selected option is the second one") {
    it.checkedRadioButtonId == R.id.option1
}
assertAny<Progressbar>(withId(R.id.progressBar, "progress is 42")) {
    it.progress == 42
}
```

## Barista’s Intents API
```java
// Creates a Bitmap on a camera provided URI
mockAndroidCamera();
```

## Runtime Permissions
The new Marshmallow permissions system requires checking for permissions at runtime. As Espresso can't interact with the system dialog, Barista offers a way to allow permissions when needed.

```java
PermissionGranter.allowPermissionsIfNeeded(Manifest.permission.GET_ACCOUNTS);
```

## Useful test rules
Barista includes a set of useful test rules to help you:

### Reseting app data

As tests should be isolated, they need to set the environment before running. Espresso doesn't help achieving it but Barista offers a set of rules to clear the app's data before running each test.

```java
// Clear all app's SharedPreferences
@Rule public ClearPreferencesRule clearPreferencesRule = new ClearPreferencesRule();

// Delete all tables from all the app's SQLite Databases
@Rule public ClearDatabaseRule clearDatabaseRule = new ClearDatabaseRule();

// Delete all files in getFilesDir() and getCacheDir()
@Rule public ClearFilesRule clearFilesRule = new ClearFilesRule();
```


### Dealing with Flaky tests

We should try to write deterministic tests, but when everything else fails Barista helps you deal with flaky tests using a specific ActivityTestRule and a couple of annotations that repeat your tests multiple times.

```java
// Use a RuleChain to wrap your ActivityTestRule with a FlakyTestRule
private ActivityTestRule<FlakyActivity> activityRule = new ActivityTestRule<>(FlakyActivity.class);
private FlakyTestRule flakyRule = new FlakyTestRule();

@Rule
public RuleChain chain = RuleChain.outerRule(flakyRule)
    .around(activityRule);


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

### One rule to rule them all

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

### Writing tests in Kotlin?
[Check this link](https://github.com/SchibstedSpain/Barista/issues/219) to know how to use `@Rule` in Kotlin.

# Magic that Barista does for you

In order to speed up testing, Barista keeps in mind some considerations.
- **Scrolls when needed**: Interacting with Espresso in a `ScrollView` requires you to scroll to each view, which sometimes doesn't work the first time. Also trying to scroll outside a `ScrollView` produces an `Exception`, forcing you to change the test depending on the layout. To keep tests simpler, Barista scrolls automatically before interacting with any `View`, and only does it if needed.
- **Scrolls on all views**: Barista scrolls on all scrollable views, including `NestedScrollView`. Espresso only handles `ScrollView` and `HorizontalScrollView`, so people need to open questions on [StackOverflow like this](https://stackoverflow.com/questions/35272953/espresso-scrolling-not-working-when-nestedscrollview-or-recyclerview-is-in-coor). Or... just use **Barista**.
- **Just interacts with displayed Views**: Interacting with `View`s inside a `ViewPager` throws `AmbiguousViewMatcherException`, because the views you interact with will be potentially repeated on different pages. Barista only interacts with displayed widgets, so you can focus on the behavior instead of wasting time on details.

# License
**[Apache License, Version 2.0 (the "License")](LICENSE)**
