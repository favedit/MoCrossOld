SET MO_HOME=%MO_ROOT%\Cross\Test\MpTestDemo\maproject

DEL /S /Q %MO_HOME%\libs
DEL /S /Q %MO_HOME%\obj

COPY /Y %MO_ROOT%\Library\LibLzma\maproject\obj\local\armeabi\libLibLzma.a                                %MO_HOME%\jni\libLibLzma.a
COPY /Y %MO_ROOT%\Library\LibJpeg\maproject\obj\local\armeabi\libLibJpeg.a                                %MO_HOME%\jni\libLibJpeg.a
COPY /Y %MO_ROOT%\Library\LibFreeType\maproject\obj\local\armeabi\libLibFreeType.a                        %MO_HOME%\jni\libLibFreeType.a

COPY /Y %MO_ROOT%\Cross\Common\MoCommon\maproject\obj\local\armeabi\libMoCommon.a                         %MO_HOME%\jni\libMoCommon.a
COPY /Y %MO_ROOT%\Cross\Common\MoCore\maproject\obj\local\armeabi\libMoCore.a                             %MO_HOME%\jni\libMoCore.a
COPY /Y %MO_ROOT%\Cross\Common\MoMath\maproject\obj\local\armeabi\libMoMath.a                             %MO_HOME%\jni\libMoMath.a

COPY /Y %MO_ROOT%\Cross\Feature\MoFeatureLogger\maproject\obj\local\armeabi\libMoFeatureLogger.a          %MO_HOME%\jni\libMoFeatureLogger.a
COPY /Y %MO_ROOT%\Cross\Feature\MoFeatureInput\maproject\obj\local\armeabi\libMoFeatureInput.a            %MO_HOME%\jni\libMoFeatureInput.a
COPY /Y %MO_ROOT%\Cross\Feature\MoFeatureGraphic\maproject\obj\local\armeabi\libMoFeatureGraphic.a        %MO_HOME%\jni\libMoFeatureGraphic.a
COPY /Y %MO_ROOT%\Cross\Feature\MoFeatureParticle\maproject\obj\local\armeabi\libMoFeatureParticle.a      %MO_HOME%\jni\libMoFeatureParticle.a
COPY /Y %MO_ROOT%\Cross\Feature\MoFeaturePhysics\maproject\obj\local\armeabi\libMoFeaturePhysics.a        %MO_HOME%\jni\libMoFeaturePhysics.a
COPY /Y %MO_ROOT%\Cross\Feature\MoFeatureResource\maproject\obj\local\armeabi\libMoFeatureResource.a      %MO_HOME%\jni\libMoFeatureResource.a
COPY /Y %MO_ROOT%\Cross\Feature\MoFeatureSound\maproject\obj\local\armeabi\libMoFeatureSound.a            %MO_HOME%\jni\libMoFeatureSound.a

COPY /Y %MO_ROOT%\Cross\Engine\MoEngine\maproject\obj\local\armeabi\libMoEngine.a                         %MO_HOME%\jni\libMoEngine.a
COPY /Y %MO_ROOT%\Cross\Engine\MoEngine2d\maproject\obj\local\armeabi\libMoEngine2d.a                     %MO_HOME%\jni\libMoEngine2d.a
COPY /Y %MO_ROOT%\Cross\Engine\MoEngine3d\maproject\obj\local\armeabi\libMoEngine3d.a                     %MO_HOME%\jni\libMoEngine3d.a
COPY /Y %MO_ROOT%\Cross\Engine\MoEngineFace\maproject\obj\local\armeabi\libMoEngineFace.a                 %MO_HOME%\jni\libMoEngineFace.a
COPY /Y %MO_ROOT%\Cross\Engine\MoEngineRender\maproject\obj\local\armeabi\libMoEngineRender.a             %MO_HOME%\jni\libMoEngineRender.a

COPY /Y %MO_ROOT%\Cross\Platform\MoPlatformAndroid\maproject\obj\local\armeabi\libMoPlatformAndroid.a     %MO_HOME%\jni\libMoPlatformAndroid.a
COPY /Y %MO_ROOT%\Cross\Platform\MoPlatformOpenGLES2\maproject\obj\local\armeabi\libMoPlatformOpenGLES2.a %MO_HOME%\jni\libMoPlatformOpenGLES2.a

COPY /Y %MO_ROOT%\Cross\Game\MoGameEngine\maproject\obj\local\armeabi\libMoGameEngine.a                   %MO_HOME%\jni\libMoGameEngine.a

SET NDK_ROOT=D:\Studio\SDK\android-ndk
SET NDK_PROJECT_PATH=%MO_HOME%
SET NDK_TOOLCHAIN=arm-linux-androideabi-4.8

call "%NDK_ROOT%\ndk-build" NDK_DEBUG=0

ECHO COPY /Y %MO_HOME%\libs\armeabi\libMpCoolLight.so %MO_ROOT%\..\Demo\Android\MpTestDemo\libs\armeabi\libMpCoolLight.so
COPY /Y %MO_HOME%\libs\armeabi\libMpCoolLight.so %MO_ROOT%\..\Demo\Android\MpTestDemo\libs\armeabi\libMpCoolLight.so
