package com.schibsted.spain.barista.sample.introduction;

import android.support.test.runner.AndroidJUnit4;
import com.schibsted.spain.barista.sample.R;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.schibsted.spain.barista.assertion.BaristaAssertions.assertThatBackButtonClosesTheApp;
import static com.schibsted.spain.barista.assertion.BaristaCheckedAssertions.assertChecked;
import static com.schibsted.spain.barista.assertion.BaristaCheckedAssertions.assertUnchecked;
import static com.schibsted.spain.barista.assertion.BaristaDrawerAssertions.assertDrawerIsClosed;
import static com.schibsted.spain.barista.assertion.BaristaDrawerAssertions.assertDrawerIsOpen;
import static com.schibsted.spain.barista.assertion.BaristaEnabledAssertions.assertDisabled;
import static com.schibsted.spain.barista.assertion.BaristaEnabledAssertions.assertEnabled;
import static com.schibsted.spain.barista.assertion.BaristaHintAssertions.assertHint;
import static com.schibsted.spain.barista.assertion.BaristaImageViewAssertions.assertDrawable;
import static com.schibsted.spain.barista.assertion.BaristaRecyclerViewAssertions.assertRecyclerViewItemCount;
import static com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed;
import static com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertNotDisplayed;
import static com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertNotExist;

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
    assertDrawerIsOpen();
    assertDrawerIsClosed();

    // Check EditText's hints
    assertHint(R.id.edittext, R.string.hint);
    assertHint(R.id.edittext, "Hint");

    // Check recyclerView item count against expected item count
    assertRecyclerViewItemCount(R.id.recycler, 10);

    // And another tricky feature
    assertThatBackButtonClosesTheApp();

    // Is this ImageView showing a drawable?
    assertDrawable(R.id.image_view);
    assertDrawable(R.id.image_view, R.drawable.ic_barista);
  }
}
