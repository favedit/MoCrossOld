SET MO_HOME=%MO_ROOT%\Cross\Engine\MoEngine\maproject

DEL /S /Q %MO_HOME%\libs
DEL /S /Q %MO_HOME%\obj

SET NDK_PROJECT_PATH=%MO_HOME%
SET NDK_TOOLCHAIN=arm-linux-androideabi-4.8

"%NDK_ROOT%\ndk-build" %MO_BUILD%
