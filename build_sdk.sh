source build/envsetup.sh
lunch aosp_blueline-userdebug #change this depending on the phone you are using

#After you have flashed your updated images to the phone, run this script to rebuild your updated SDK for use in Android Studio to develop apps.

#These binaries are missing when you first try to do "m sdk". So, build each of these explicitly.
m dmtracedump && m etc1tool && m aapt && m aapt2 && m aidl && m split-select && m zipalign && m llvm-rs-cc && m bcc_compat && m apksigner && m dx && m libaapt2_jni && m libclang_android
#Now build the SDK for real. It will output to out/host/linux-x86/sdk/aosp_blueline/android-sdk_eng.kevin_linux-x86 and will have a folder structure similar to the SDK bundled with Android Studio.
#Note that your SDK path may be different as it probably won't contain "kevin" in it.
m sdk

#Once you have built the SDK, you should open Android Studio, right-click on your "app" (module) folder, go to "Open Module Settings", and set the location of the Android SDK to be at
# the generated SDK path, specified earlier.

#Now, you will notice a few things once you try to Gradle Sync.
#1. It will complain about inconsistent folder names. That is why we must rename the folders with these commands.
#Note that your folder names probably won't contain the name "kevin" in them, so fix the commands accordingly.
mv ./out/host/linux-x86/sdk/aosp_blueline/android-sdk_eng.kevin_linux-x86/build-tools/android-11 ./out/host/linux-x86/sdk/aosp_blueline/android-sdk_eng.kevin_linux-x86/build-tools/30.0.3
mv ./out/host/linux-x86/sdk/aosp_blueline/android-sdk_eng.kevin_linux-x86/platforms/android-11 ./out/host/linux-x86/sdk/aosp_blueline/android-sdk_eng.kevin_linux-x86/platforms/android-30
mv ./out/host/linux-x86/sdk/aosp_blueline/android-sdk_eng.kevin_linux-x86/system-images/android-11 ./out/host/linux-x86/sdk/aosp_blueline/android-sdk_eng.kevin_linux-x86/system-images/android-30

#2. Once you have renamed folders, ensure your module's build.gradle is set with a compile SDK version of 30, a target SDK version of 30, and a minimum SDK version of 30. After, you should Gradle Sync and 
#it should finish sync without errors.

#3. Note that if you attempt to use the newly created API methods in BatteryManager, they will show up in autocomplete, but when you type them in, Android Studio will complain
# that these methods require API level 31. This is fine; just ignore this message. Your code should still compile and you should now be able to use the new BatteryManager methods in your apps.
