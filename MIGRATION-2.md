# Migration guide from Barista 1.x

You just updated Barista dependency from 1.x and everything is in red?
We did some breaking changes to improve our public API, and that's why we increased our major version.

But fear not, here are a list of breaking changes and how to solve them:

## Packages and classes
We changed our Action classes to Interaction, to avoid confusion with Espresso ViewAction. ([#132](https://github.com/SchibstedSpain/Barista/issues/132))

We moved most classes to different packages for better code organisation. You will find your missing imports in one of these public packages:
```
com.schibsted.spain.barista.assertion
com.schibsted.spain.barista.interaction
com.schibsted.spain.barista.rule
```

## Changed methods
- `click()` methods have been renamed to `clickOn()`. ([#100](https://github.com/SchibstedSpain/Barista/issues/100))
- `writeToEditText()` method has been renamed to `writeTo()`. ([#140](https://github.com/SchibstedSpain/Barista/issues/140))
- `writeToAutoCompleteTextView()` method has been renamed to `writeToAutoComplete()`. ([#140](https://github.com/SchibstedSpain/Barista/issues/140))
- `clickCheckBoxItem()` methods have been deleted. Use `clickOn()` instead. ([#140](https://github.com/SchibstedSpain/Barista/issues/140))
- `ListView` and `RecyclerView` actions have been merged into `ListInteractions` with these common methods ([#146](https://github.com/SchibstedSpain/Barista/issues/146)):
  - `clickListItem(listId, position)`
  - `clickListItemChild(listId, position, childId)`
  - `scrollListToPosition(listId, position)`

## New features
- `ListView` and `RecyclerView` methods are the same. No need to use different method depending on the implementation. ([#146](https://github.com/SchibstedSpain/Barista/pull/146))
- `ListView` or `RecyclerView` id is optional when only one instance of `ListView` or `RecyclerView` is displayed in the hierarchy. ([#146](https://github.com/SchibstedSpain/Barista/pull/146))
- Drawer id on drawer interactions and assertions is optional when only one DrawerLayout is present in the hierarchy. ([#161](https://github.com/SchibstedSpain/Barista/pull/161))
- Drawer interactions and assertions allow using non-default gravity with `openDrawerWithGravity()`, `closeDrawerWithGravity()`, `assertDrawerIsOpenWithGravity()` and `assertDrawerIsClosedWithGravity()`. ([#161](https://github.com/SchibstedSpain/Barista/pull/161))
- `SwipeRefreshLayout` id is now optional in `refresh()` method when only one `SwipeRefreshLayout` is present in the hierarchy. ([#150](https://github.com/SchibstedSpain/Barista/pull/150))
- Added `assertFocused()` and `assertNotFocused()` methods.
([#157](https://github.com/SchibstedSpain/Barista/pull/157))
- Added `assertDisplayed(id, text)` to check some text in a specific view. ([#150](https://github.com/SchibstedSpain/Barista/pull/150))
- Added `FlakyTestRule` which enables the functionalities from `FlakyActivityTestRule` without forcing the inheritance dependency. ([#160](https://github.com/SchibstedSpain/Barista/pull/160))

## Behaviour changes

#### Error handling
- Espresso's FailureHandler is not invoked anymore while Barista attempts to do its magic. It will only receive errors that actually make your test fail. This means you can use a custom FailureHandler for things like taking screenshots upon failures. ([#134](https://github.com/SchibstedSpain/Barista/pull/134))
- We also improved some error messages when things go wrong, so you don't need to decipher what they mean. If you find any message that doesn't make much sense, open an issue!
