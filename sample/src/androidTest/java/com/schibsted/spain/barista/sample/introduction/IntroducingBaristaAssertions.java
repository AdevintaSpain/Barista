package com.schibsted.spain.barista.sample.introduction;

import android.support.test.runner.AndroidJUnit4;
import com.schibsted.spain.barista.sample.R;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.schibsted.spain.barista.BaristaAssertions.assertDisabled;
import static com.schibsted.spain.barista.BaristaAssertions.assertDisplayed;
import static com.schibsted.spain.barista.BaristaAssertions.assertDrawerIsClosed;
import static com.schibsted.spain.barista.BaristaAssertions.assertDrawerIsOpen;
import static com.schibsted.spain.barista.BaristaAssertions.assertEnabled;
import static com.schibsted.spain.barista.BaristaAssertions.assertNotDisplayed;
import static com.schibsted.spain.barista.BaristaAssertions.assertNotExist;
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
    assertDisplayed("Hello world");
    assertDisplayed(R.string.hello_world);
    assertViewIsDisplayed(R.id.button);

    // ...or not?
    assertNotDisplayed("Hello world");
    assertNotDisplayed(R.string.hello_world);
    assertViewIsNotDisplayed(R.id.button);

    // Is this view enabled?
    assertEnabled("Hello world");
    assertEnabled(R.string.hello_world);
    assertViewIsEnabled(R.id.button);

    // ...or not?
    assertDisabled("Hello world");
    assertDisabled(R.string.hello_world);
    assertViewIsDisabled(R.id.button);

    // Hope this view doesn't exist!
    assertNotExist("Hello world");
    assertNotExist(R.string.hello_world);
    assertViewDoesNotExist(R.id.button);

    // What's the state of the Drawer
    assertDrawerIsOpen(R.id.drawer);
    assertDrawerIsClosed(R.id.drawer);

    // And another tricky feature
    assertThatBackButtonClosesTheApp();
  }
}
