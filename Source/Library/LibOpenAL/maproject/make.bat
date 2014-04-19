SET MO_HOME=%MO_ROOT%\Cross\Library\MoLzma\maproject

DEL /S /Q %MO_HOME%\libs
DEL /S /Q %MO_HOME%\obj

SET NDK_ROOT=D:\Studio\SDK\android-ndk
SET NDK_PROJECT_PATH=%MO_HOME%
SET NDK_TOOLCHAIN=arm-linux-androideabi-4.8

"%NDK_ROOT%\ndk-build" NDK_DEBUG=0
