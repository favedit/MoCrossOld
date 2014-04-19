SET MO_HOME=D:\ZW-Mobile-Work\Cross\Library\MoZLib\maproject

DEL /S /Q %MO_HOME%\libs
DEL /S /Q %MO_HOME%\obj

SET NDK_ROOT=D:\Studio\SDK\android-ndk
SET NDK_PROJECT_PATH=%MO_HOME%
SET NDK_TOOLCHAIN=arm-linux-androideabi-4.8

ndk-build NDK_DEBUG=1
