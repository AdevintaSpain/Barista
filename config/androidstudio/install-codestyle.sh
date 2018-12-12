#!/bin/bash
# Based on Square's codestyle installer (https://github.com/square/java-code-styles/blob/master/install.sh)

echo "Installing Schibsted IntelliJ configs..."

CONFIGS="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"

for i in $HOME/Library/Preferences/IntelliJIdea*  \
         $HOME/Library/Preferences/IdeaIC*        \
         $HOME/Library/Preferences/AndroidStudio* \
         $HOME/.IntelliJIdea*/config              \
         $HOME/.IdeaIC*/config                    \
         $HOME/.AndroidStudio*/config
do
  if [[ -d $i ]]; then
    # create codestyle folders
    mkdir -p $i/codestyles
    # copy xmls
    cp -frv "$CONFIGS/codestyle"/* $i/codestyles
  fi
done

echo "Done."
echo ""
echo "Restart IntelliJ and/or AndroidStudio, go to Preferences, Editor, Code Style, and select 'SchibstedAndroid-InfoJobs' as scheme."
