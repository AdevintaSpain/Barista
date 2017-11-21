package com.schibsted.spain.barista.sample.introduction;

import android.support.test.runner.AndroidJUnit4;
import com.schibsted.spain.barista.sample.R;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.schibsted.spain.barista.interaction.BaristaAutoCompleteTextViewInteractions.writeToAutoComplete;
import static com.schibsted.spain.barista.interaction.BaristaClickInteractions.clickBack;
import static com.schibsted.spain.barista.interaction.BaristaClickInteractions.clickOn;
import static com.schibsted.spain.barista.interaction.BaristaDialogInteractions.clickDialogNegativeButton;
import static com.schibsted.spain.barista.interaction.BaristaDialogInteractions.clickDialogNeutralButton;
import static com.schibsted.spain.barista.interaction.BaristaDialogInteractions.clickDialogPositiveButton;
import static com.schibsted.spain.barista.interaction.BaristaEditTextInteractions.writeTo;
import static com.schibsted.spain.barista.interaction.BaristaListInteractions.clickListItem;
import static com.schibsted.spain.barista.interaction.BaristaListInteractions.clickListItemChild;
import static com.schibsted.spain.barista.interaction.BaristaListInteractions.scrollListToPosition;
import static com.schibsted.spain.barista.interaction.BaristaMenuClickInteractions.clickMenu;
import static com.schibsted.spain.barista.interaction.BaristaDrawerInteractions.closeDrawer;
import static com.schibsted.spain.barista.interaction.BaristaDrawerInteractions.openDrawer;
import static com.schibsted.spain.barista.interaction.BaristaPickerInteractions.setDateOnPicker;
import static com.schibsted.spain.barista.interaction.BaristaRadioButtonInteractions.clickRadioButtonItem;
import static com.schibsted.spain.barista.interaction.BaristaRadioButtonInteractions.clickRadioButtonPosition;
import static com.schibsted.spain.barista.interaction.BaristaScrollInteractions.scrollTo;
import static com.schibsted.spain.barista.interaction.BaristaSleepInteractions.sleep;
import static com.schibsted.spain.barista.interaction.BaristaSpinnerInteractions.clickSpinnerItem;
import static com.schibsted.spain.barista.interaction.BaristaSwipeRefreshInteractions.refresh;
import static com.schibsted.spain.barista.interaction.BaristaViewPagerInteractions.swipeViewPagerBack;
import static com.schibsted.spain.barista.interaction.BaristaViewPagerInteractions.swipeViewPagerForward;
import static java.util.concurrent.TimeUnit.SECONDS;

@RunWith(AndroidJUnit4.class)
public class IntroducingBaristaActions {

  @Test
  @Ignore
  public void letsIntroduceBarista() {
    // Click widgets
    clickOn(R.id.button);
    clickOn("Next");
    clickBack();

    // Click menu items, also overflowed ones
    clickMenu(R.id.menu_action_1);

    // Writing into widgets
    writeTo(R.id.edittext, "A great text");
    writeToAutoComplete(R.id.autocomplete, "Another great text");

    // Select items on AdapterViews
    clickListItem(4);
    clickListItem(R.id.listview, 4);
    clickListItemChild(R.id.recycler, 3, R.id.button);
    clickSpinnerItem(R.id.spinner, 1);

    // Scroll on AdapterViews
    scrollListToPosition(R.id.recycler, 42);

    // Select items on RadioButtons
    clickRadioButtonItem(R.id.radiogroup, R.id.first_item);
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
  }
}
