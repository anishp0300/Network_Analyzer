# Syncing repo files
repo sync

# You are almost done! Building environment setup
source build/envsetup.sh
lunch <Your_android_flavor>

# After lunch, select which flavor of android you want. For example aosp_barbet-userdebug. Then we will make.
m -j$(nproc)

# Make sure that you have USB debugging enabled and OEM unlocking is enabled.
adb reboot bootloader

# Remove -w if you do not want to wipe the data!
fastboot flashall -w
