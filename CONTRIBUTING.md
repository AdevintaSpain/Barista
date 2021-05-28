# Contributing to Barista

Thanks a lot for taking your time to contribute ☕

These are mostly guidelines, not rules. Use your best judgment, and feel free to propose changes to this document in a pull request.

## Formatting
We use our company's IntelliJ code style for the project, which is very similar to the official Kotlin Android code style. When submitting code please make sure you use the proper format. You can install the code style into Android Studio by running the script in `./config/androidstudio/install-codestyle.sh`. Then restart Android Studio and pick the "BaristaAndroid" schema in preferences.

## Prefer Java-written test classes
As most parts of Barista are Java-compatible, please do write Java tests when possible. Writing them in Kotlin might lead to using Kotlin-only shortcuts, breaking the Java compatibility Barista aims for.
