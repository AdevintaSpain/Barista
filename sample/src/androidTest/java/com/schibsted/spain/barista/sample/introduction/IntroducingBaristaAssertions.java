package com.schibsted.spain.barista.sample.introduction;

import android.support.test.runner.AndroidJUnit4;
import com.schibsted.spain.barista.sample.R;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.schibsted.spain.barista.BaristaAssertions.assertChecked;
import static com.schibsted.spain.barista.BaristaAssertions.assertDisabled;
import static com.schibsted.spain.barista.BaristaAssertions.assertDisplayed;
import static com.schibsted.spain.barista.BaristaAssertions.assertDrawerIsClosed;
import static com.schibsted.spain.barista.BaristaAssertions.assertDrawerIsOpen;
import static com.schibsted.spain.barista.BaristaAssertions.assertEnabled;
import static com.schibsted.spain.barista.BaristaAssertions.assertHint;
import static com.schibsted.spain.barista.BaristaAssertions.assertNotDisplayed;
import static com.schibsted.spain.barista.BaristaAssertions.assertNotExist;
import static com.schibsted.spain.barista.BaristaAssertions.assertRecyclerViewItemCount;
import static com.schibsted.spain.barista.BaristaAssertions.assertThatBackButtonClosesTheApp;
import static com.schibsted.spain.barista.BaristaAssertions.assertUnchecked;

@RunWith(AndroidJUnit4.class)
public class IntroducingBaristaAssertions {

  @Test
  @Ignore
  public void letsIntroduceBarista() {
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

    // What's the state of the Drawer
    assertDrawerIsOpen(R.id.drawer);
    assertDrawerIsClosed(R.id.drawer);

    // Check EditText's hints
    assertHint(R.id.edittext, R.string.hint);
    assertHint(R.id.edittext, "Hint");

    // Check recyclerView item count against expected item count
    assertRecyclerViewItemCount(R.id.recycler, 10);

    // And another tricky feature
    assertThatBackButtonClosesTheApp();
  }
}
