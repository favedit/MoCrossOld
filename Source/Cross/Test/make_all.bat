SET MO_DRIVER=E:
SET MO_BUILD=NDK_DEBUG=0
SET NDK_ROOT=D:\Studio\SDK\android-ndk

SET MO_ROOT=%MO_DRIVER%\ZW-MoCross\Source
%MO_DRIVER%

CD   %MO_ROOT%\Library\LibFreeType\maproject\

CALL %MO_ROOT%\Library\LibLzma\maproject\make.bat
CALL %MO_ROOT%\Library\LibJpeg\maproject\make.bat
CALL %MO_ROOT%\Library\LibFreeType\maproject\make.bat

CD   %MO_ROOT%\Cross\Test\

CALL %MO_ROOT%\Cross\Common\MoCommon\maproject\make.bat
CALL %MO_ROOT%\Cross\Common\MoCore\maproject\make.bat
CALL %MO_ROOT%\Cross\Common\MoMath\maproject\make.bat

CALL %MO_ROOT%\Cross\Feature\MoFeatureInput\maproject\make.bat
CALL %MO_ROOT%\Cross\Feature\MoFeatureLogger\maproject\make.bat
CALL %MO_ROOT%\Cross\Feature\MoFeatureGraphic\maproject\make.bat
CALL %MO_ROOT%\Cross\Feature\MoFeatureParticle\maproject\make.bat
CALL %MO_ROOT%\Cross\Feature\MoFeaturePhysics\maproject\make.bat
CALL %MO_ROOT%\Cross\Feature\MoFeatureResource\maproject\make.bat
CALL %MO_ROOT%\Cross\Feature\MoFeatureSound\maproject\make.bat

CALL %MO_ROOT%\Cross\Engine\MoEngine\maproject\make.bat
CALL %MO_ROOT%\Cross\Engine\MoEngine2d\maproject\make.bat
CALL %MO_ROOT%\Cross\Engine\MoEngine3d\maproject\make.bat
CALL %MO_ROOT%\Cross\Engine\MoEngineFace\maproject\make.bat
CALL %MO_ROOT%\Cross\Engine\MoEngineRender\maproject\make.bat

CALL %MO_ROOT%\Cross\Platform\MoPlatformAndroid\maproject\make.bat
CALL %MO_ROOT%\Cross\Platform\MoPlatformOpenGLES2\maproject\make.bat

CALL %MO_ROOT%\Cross\Game\MoGameEngine\maproject\make.bat

CALL %MO_ROOT%\Cross\Test\MpTestDemo\maproject\make.bat

CALL %MO_ROOT%\Cross\Test\MpTestInstance\maproject\make.bat

CALL %MO_ROOT%\Cross\Test\MpTest003\maproject\make.bat



CALL %MO_ROOT%\Cross\Engine\MoEngineOpenGL\maproject\make.bat
CALL %MO_ROOT%\Cross\Test\MpTestDemo\maproject\make.bat
