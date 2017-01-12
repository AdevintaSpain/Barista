package com.schibsted.spain.barista.sample.introduction;

import android.support.test.runner.AndroidJUnit4;
import com.schibsted.spain.barista.sample.R;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.schibsted.spain.barista.BaristaAssertions.assertDrawerIsClosed;
import static com.schibsted.spain.barista.BaristaAssertions.assertDrawerIsOpen;
import static com.schibsted.spain.barista.BaristaAssertions.assertTextDoesNotExist;
import static com.schibsted.spain.barista.BaristaAssertions.assertTextIsDisabled;
import static com.schibsted.spain.barista.BaristaAssertions.assertTextIsDisplayed;
import static com.schibsted.spain.barista.BaristaAssertions.assertTextIsEnabled;
import static com.schibsted.spain.barista.BaristaAssertions.assertTextIsNotDisplayed;
import static com.schibsted.spain.barista.BaristaAssertions.assertThatBackButtonClosesTheApp;
import static com.schibsted.spain.barista.BaristaAssertions.assertViewDoesNotExist;
import static com.schibsted.spain.barista.BaristaAssertions.assertViewIsDisabled;
import static com.schibsted.spain.barista.BaristaAssertions.assertViewIsDisplayed;
import static com.schibsted.spain.barista.BaristaAssertions.assertViewIsEnabled;
import static com.schibsted.spain.barista.BaristaAssertions.assertViewIsNotDisplayed;

@RunWith(AndroidJUnit4.class)
public class IntroducingBaristaAssertions {

  @Test
  @Ignore
  public void letsIntroduceBarista() {

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

    // What's the state of the Drawer
    assertDrawerIsOpen(R.id.drawer_layout);
    assertDrawerIsClosed(R.id.drawer_layout);

    // And another tricky feature
    assertThatBackButtonClosesTheApp();
  }
}
