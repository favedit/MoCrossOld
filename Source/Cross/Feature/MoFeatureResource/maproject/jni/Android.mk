LOCAL_PATH := $(call my-dir)

#------------------------------------------------------------
include $(CLEAR_VARS)

# MO_COMPILER_FLAG       := -g -D_MO_DEBUG -D_MO_ANDROID -D_MO_X86
MO_COMPILER_FLAG       := -D_MO_ANDROID -D_MO_X86

LOCAL_MODULE           := MoFeatureResource
LOCAL_ARM_MODE         := arm # thumb(2字节指令)/arm(4字节指令)
LOCAL_CFLAGS           := $(MO_COMPILER_FLAG)
LOCAL_CXXFLAGS         := $(MO_COMPILER_FLAG)
LOCAL_CPPFLAGS         := $(MO_COMPILER_FLAG)
LOCAL_CPP_EXTENSION    := .cpp
#............................................................
LOCAL_C_INCLUDES       := \
   $(LOCAL_PATH)/../../../../Library/MoJpeg \
   $(LOCAL_PATH)/../../../../Library/MoLzma \
   $(LOCAL_PATH)/../../../../Library/MoFreeType \
   $(LOCAL_PATH)/../../../../Common/MoCommon \
   $(LOCAL_PATH)/../../../../Common/MoCore \
   $(LOCAL_PATH)/../../../../Common/MoMath \

#............................................................
LOCAL_SRC_FILES :=

# Common
LOCAL_SRC_FILES += \
   ../../RLzma.cpp \

# Loader
LOCAL_SRC_FILES += \
   ../../FFileLoader.cpp \
   ../../FLoader.cpp \
   ../../FLoaderConsole.cpp \
   ../../FLoaderWorker.cpp \

# Content2d
LOCAL_SRC_FILES += \
   ../../FAnimationClip.cpp \
   ../../FAnimationFrame.cpp \
   ../../FAnimationResource.cpp \
   ../../FGroundResource.cpp \
   ../../FPictureResource.cpp \
   ../../FResource.cpp \
   ../../FResourceConsole.cpp \
   ../../FResourceGroup.cpp \
   ../../FResourceLoader.cpp \
   ../../FResourceWorker.cpp \

# Content3d
LOCAL_SRC_FILES += \
   ../../FResource3d.cpp \
   ../../FResource3dConsole.cpp \
   ../../SMaterialInfo.cpp \
   \
   ../../FRs3dMaterial.cpp \
   ../../FRs3dMaterialConsole.cpp \
   ../../FRs3dMaterialTexture.cpp \
   \
   ../../FRs3dAnimation.cpp \
   ../../FRs3dBone.cpp \
   ../../FRs3dFrame.cpp \
   ../../FRs3dGeometry.cpp \
   ../../FRs3dIndexBuffer.cpp \
   ../../FRs3dModel.cpp \
   ../../FRs3dModelConsole.cpp \
   ../../FRs3dMovie.cpp \
   ../../FRs3dSkeleton.cpp \
   ../../FRs3dTrack.cpp \
   ../../FRs3dVertexBuffer.cpp \
   ../../FRs3dVertexStream.cpp \
   ../../SRs3dFrameInfo.cpp \
   \
   ../../FRs3dScene.cpp \
   ../../FRs3dSceneCamera.cpp \
   ../../FRs3dSceneConsole.cpp \
   ../../FRs3dSceneDisplay.cpp \
   ../../FRs3dSceneLight.cpp \
   ../../FRs3dSceneMap.cpp \
   ../../FRs3dSceneMaterial.cpp \
   ../../FRs3dSceneMovie.cpp \
   ../../FRs3dScenePass.cpp \
   ../../FRs3dSceneRegion.cpp \
   ../../FRs3dSceneRenderable.cpp \
   ../../FRs3dSceneSky.cpp \
   ../../FRs3dSceneSpace.cpp \
   ../../FRs3dSceneTechnique.cpp \
   ../../FRs3dSceneViewport.cpp \
   \
   ../../FRs3dTemplate.cpp \
   ../../FRs3dTemplateConsole.cpp \
   ../../FRs3dTemplateRenderable.cpp \
   \
   ../../FRs3dTextureBitmap.cpp \
   ../../FRs3dTexture.cpp \
   ../../FRs3dTextureConsole.cpp \
   \
   ../../FRs3dTheme.cpp \
   ../../FRs3dThemeConsole.cpp \

# Library
LOCAL_SRC_FILES += \
   ../../MoFeatureResource.cpp \
#............................................................
include $(BUILD_STATIC_LIBRARY)
