# mobile-automation
Mobile automation using Appium Java client

## Prerequisites
1. Install Java 8 - android tools are not working with newer version yet
2. Download appium
3. Download android studio
4. brew install node      # get node.js
5. npm install -g appium  # get appium
6. npm install -g appium-doctor
7. npm install wd         # get appium client
8. appium &               # start appium
9. node your-appium-test.js
10. Install Carthage
11. Set environment variables:
  export JAVA_HOME=$(/usr/libexec/java_home -v 1.8)
  export PATH=$PATH:$JAVA_HOME/bin
  export ANDROID_HOME=/Users/<USERNAME>/Library/Android/sdk
  export PATH=$PATH:$ANDROID_HOME/emulator:$ANDROID_HOME/tools:$ANDROID_HOME/platform-tools:$ANDROID_HOME/build-   tools:$ANDROID_HOME/tools/bin
12. Create emulator with pixel2 and API level-28
  
  
## Execute Test suite
`mvn clean test`
