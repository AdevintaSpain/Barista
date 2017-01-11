package com.schibsted.spain.barista.sample.rule;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.rule.UiThreadTestRule;
import android.util.Log;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import static android.support.test.internal.util.Checks.checkNotNull;

/**
 * This rule is a copy of ActivityTestRule but performing each test 10 times. Warning! It doesn't
 * work on tests that navigate through different Activities.
 */
public class TenRepetitionsActivityTestRule<T extends Activity> extends UiThreadTestRule {

  public static final int NUMBER_OF_REPETITIONS = 10;

  private static final String TAG = "ActivityTestRule";

  private final Class<T> mActivityClass;

  private Instrumentation mInstrumentation;

  private boolean mInitialTouchMode = false;

  private boolean mLaunchActivity = false;

  private T mActivity;

  /**
   * Similar to {@link #TenRepetitionsActivityTestRule(Class, boolean, boolean)} but with "touch mode" disabled.
   *
   * @param activityClass The activity under test. This must be a class in the instrumentation
   * targetPackage specified in the AndroidManifest.xml
   * @see ActivityTestRule#ActivityTestRule(Class, boolean, boolean)
   */
  public TenRepetitionsActivityTestRule(Class<T> activityClass) {
    this(activityClass, false);
  }

  /**
   * Similar to {@link #TenRepetitionsActivityTestRule(Class, boolean, boolean)} but defaults to launch the
   * activity under test once per
   * <a href="http://junit.org/javadoc/latest/org/junit/Test.html"><code>Test</code></a> method.
   * It is launched before the first
   * <a href="http://junit.sourceforge.net/javadoc/org/junit/Before.html"><code>Before</code></a>
   * method, and terminated after the last
   * <a href="http://junit.sourceforge.net/javadoc/org/junit/After.html"><code>After</code></a>
   * method.
   *
   * @param activityClass The activity under test. This must be a class in the instrumentation
   * targetPackage specified in the AndroidManifest.xml
   * @param initialTouchMode true if the Activity should be placed into "touch mode" when started
   * @see ActivityTestRule#ActivityTestRule(Class, boolean, boolean)
   */
  public TenRepetitionsActivityTestRule(Class<T> activityClass, boolean initialTouchMode) {
    this(activityClass, initialTouchMode, true);
  }

  /**
   * Creates an {@link ActivityTestRule} for the Activity under test.
   *
   * @param activityClass The activity under test. This must be a class in the instrumentation
   * targetPackage specified in the AndroidManifest.xml
   * @param initialTouchMode true if the Activity should be placed into "touch mode" when started
   * @param launchActivity true if the Activity should be launched once per
   * <a href="http://junit.org/javadoc/latest/org/junit/Test.html">
   * <code>Test</code></a> method. It will be launched before the first
   * <a href="http://junit.sourceforge.net/javadoc/org/junit/Before.html">
   * <code>Before</code></a> method, and terminated after the last
   * <a href="http://junit.sourceforge.net/javadoc/org/junit/After.html">
   * <code>After</code></a> method.
   */
  public TenRepetitionsActivityTestRule(Class<T> activityClass, boolean initialTouchMode,
      boolean launchActivity) {
    mActivityClass = activityClass;
    mInitialTouchMode = initialTouchMode;
    mLaunchActivity = launchActivity;
    mInstrumentation = InstrumentationRegistry.getInstrumentation();
  }

  /**
   * Override this method to set up Intent as if supplied to
   * {@link android.content.Context#startActivity}.
   * <p>
   * The default Intent (if this method returns null or is not overwritten) is:
   * action = {@link Intent#ACTION_MAIN}
   * flags = {@link Intent#FLAG_ACTIVITY_NEW_TASK}
   * All other intent fields are null or empty.
   *
   * @return The Intent as if supplied to {@link android.content.Context#startActivity}.
   */
  protected Intent getActivityIntent() {
    return new Intent(Intent.ACTION_MAIN);
  }

  /**
   * Override this method to execute any code that should run before your {@link Activity} is
   * created and launched.
   * This method is called before each test method, including any method annotated with
   * <a href="http://junit.sourceforge.net/javadoc/org/junit/Before.html"><code>Before</code></a>.
   */
  protected void beforeActivityLaunched() {
    // empty by default
  }

  /**
   * Override this method to execute any code that should run after your {@link Activity} is
   * launched, but before any test code is run including any method annotated with
   * <a href="http://junit.sourceforge.net/javadoc/org/junit/Before.html"><code>Before</code></a>.
   * <p>
   * Prefer
   * <a href="http://junit.sourceforge.net/javadoc/org/junit/Before.html"><code>Before</code></a>
   * over this method. This method should usually not be overwritten directly in tests and only be
   * used by subclasses of ActivityTestRule to get notified when the activity is created and
   * visible but test runs.
   */
  protected void afterActivityLaunched() {
    // empty by default
  }

  /**
   * Override this method to execute any code that should run after your {@link Activity} is
   * finished.
   * This method is called after each test method, including any method annotated with
   * <a href="http://junit.sourceforge.net/javadoc/org/junit/After.html"><code>After</code></a>.
   */
  protected void afterActivityFinished() {
    // empty by default
  }

  /**
   * @return The activity under test.
   */
  public T getActivity() {
    if (mActivity == null) {
      Log.w(TAG, "Activity wasn't created yet");
    }
    return mActivity;
  }

  @Override
  public Statement apply(final Statement base, Description description) {
    return new TenRepetitionsActivityTestRule.ActivityStatement(super.apply(base, description));
  }

  /**
   * Launches the Activity under test.
   * <p>
   * Don't call this method directly, unless you explicitly requested not to lazily launch the
   * Activity manually using the launchActivity flag in
   * {@link ActivityTestRule#ActivityTestRule(Class, boolean, boolean)}.
   * <p>
   * Usage:
   * <pre>
   *    &#064;Test
   *    public void customIntentToStartActivity() {
   *        Intent intent = new Intent(Intent.ACTION_PICK);
   *        mActivity = mActivityRule.launchActivity(intent);
   *    }
   * </pre>
   *
   * @param startIntent The Intent that will be used to start the Activity under test. If
   * {@code startIntent} is null, the Intent returned by
   * {@link ActivityTestRule#getActivityIntent()} is used.
   * @return the Activity launched by this rule.
   * @see ActivityTestRule#getActivityIntent()
   */
  public T launchActivity(@Nullable Intent startIntent) {
    Intent intent = startIntent;

    // set initial touch mode
    mInstrumentation.setInTouchMode(mInitialTouchMode);

    final String targetPackage = mInstrumentation.getTargetContext().getPackageName();
    // inject custom intent, if provided
    if (null == intent) {
      intent = getActivityIntent();
      if (null == intent) {
        Log.w(TAG, "getActivityIntent() returned null using default: " + "Intent(Intent.ACTION_MAIN)");
        intent = new Intent(Intent.ACTION_MAIN);
      }
    }
    intent.setClassName(targetPackage, mActivityClass.getName());
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    Log.d(TAG, String.format("Launching activity %s",
        mActivityClass.getName()));

    beforeActivityLaunched();
    // The following cast is correct because the activity we're creating is of the same type as
    // the one passed in
    mActivity = mActivityClass.cast(mInstrumentation.startActivitySync(intent));

    mInstrumentation.waitForIdleSync();

    if (mActivity != null) {
      // Notify that Activity was successfully launched
      afterActivityLaunched();
    } else {
      // Log an error message to logcat/instrumentation, that the Activity failed to launch
      String errorMessage = String.format("Activity %s, failed to launch",
          mActivityClass.getName());
      Bundle bundle = new Bundle();
      bundle.putString(Instrumentation.REPORT_KEY_STREAMRESULT, TAG + " " + errorMessage);
      mInstrumentation.sendStatus(0, bundle);
      Log.e(TAG, errorMessage);
    }

    return mActivity;
  }

  @VisibleForTesting
  void setInstrumentation(Instrumentation instrumentation) {
    mInstrumentation = checkNotNull(instrumentation, "instrumentation cannot be null!");
  }

  void finishActivity() {
    if (mActivity != null) {
      mActivity.finish();
      afterActivityFinished();
      mActivity = null;
    }
  }

  /**
   * <a href="http://junit.org/apidocs/org/junit/runners/model/Statement.html">
   * <code>Statement</code></a> that finishes the activity after the test was executed
   */
  private class ActivityStatement extends Statement {

    private final Statement mBase;

    ActivityStatement(Statement base) {
      mBase = base;
    }

    @Override
    public void evaluate() throws Throwable {
      for (int i = 0; i < NUMBER_OF_REPETITIONS; i++) {
        try {
          if (mLaunchActivity) {
            mActivity = launchActivity(getActivityIntent());
          }
          mBase.evaluate();
        } finally {
          finishActivity();
        }
      }
    }
  }
}
