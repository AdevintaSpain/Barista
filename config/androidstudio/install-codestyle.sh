#!/bin/bash
# Based on Square's codestyle installer (https://github.com/square/java-code-styles/blob/master/install.sh)

echo "Installing Barista IntelliJ configs..."

CONFIGS="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"

for i in $HOME/Library/Preferences/IntelliJIdea*  \
         $HOME/Library/Preferences/IdeaIC*        \
         $HOME/Library/Preferences/AndroidStudio* \
         $HOME/Library/Application\ Support/JetBrains/IntelliJIdea* \
         $HOME/.IntelliJIdea*/config              \
         $HOME/.IdeaIC*/config                    \
         $HOME/.AndroidStudio*/config \
         $HOME/Library/Application\ Support/Google/AndroidStudio* \
         $HOME/Library/Application\ Support/JetBrains/IdeaIC*
do
  if [[ -d "$i" ]]; then
    mkdir -p "$i/codestyles"
    cp -frv "$CONFIGS/codestyle"/* "$i/codestyles"
  fi
done

echo "Done."
echo ""
echo "Restart IntelliJ and/or AndroidStudio, go to Preferences, Editor, Code Style, and select 'BaristaAndroid' as scheme."
