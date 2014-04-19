LOCAL_PATH := $(call my-dir)

#------------------------------------------------------------

#------------------------------------------------------------
include $(CLEAR_VARS)

# MO_COMPILER_FLAG       := -g -D_MO_DEBUG -D_MO_ANDROID -D_MO_X86
MO_COMPILER_FLAG       := -D_MO_ANDROID -D_MO_X86

LOCAL_MODULE           := MoEngine3d
LOCAL_ARM_MODE         := arm # thumb(2字节指令)/arm(4字节指令)
LOCAL_CFLAGS           := $(MO_COMPILER_FLAG)
LOCAL_CXXFLAGS         := $(MO_COMPILER_FLAG)
LOCAL_CPPFLAGS         := $(MO_COMPILER_FLAG)
LOCAL_CPP_EXTENSION    := .cpp
#............................................................
LOCAL_C_INCLUDES       := \
   $(LOCAL_PATH)/../../../../../Library/LibJpeg \
   $(LOCAL_PATH)/../../../../../Library/LibLzma \
   $(LOCAL_PATH)/../../../../Common/MoCommon \
   $(LOCAL_PATH)/../../../../Common/MoCore \
   $(LOCAL_PATH)/../../../../Common/MoMath \
   $(LOCAL_PATH)/../../../../Feature/MoFeatureInput \
   $(LOCAL_PATH)/../../../../Feature/MoFeatureGraphic \
   $(LOCAL_PATH)/../../../../Feature/MoFeatureParticle \
   $(LOCAL_PATH)/../../../../Feature/MoFeaturePhysics \
   $(LOCAL_PATH)/../../../../Feature/MoFeatureResource \
   $(LOCAL_PATH)/../../../../Feature/MoFeatureSound \
   $(LOCAL_PATH)/../../../../Engine/MoEngine \

#............................................................
LOCAL_SRC_FILES :=

# Display
LOCAL_SRC_FILES += \
   ../../FBone3d.cpp \
   ../../FDisplay3d.cpp \
   ../../FRenderable3d.cpp \
   ../../FAnimation3d.cpp \

# Instance
LOCAL_SRC_FILES += \
   ../../FMaterial3d.cpp \
   ../../FMaterial3dConsole.cpp \
   ../../FMaterial3dTexture.cpp \
   \
   ../../FDynamicModel3d.cpp \
   ../../FDynamicModel3dGeometry.cpp \
   ../../FModel3d.cpp \
   ../../FModel3dConsole.cpp \
   ../../FModel3dGeometry.cpp \
   ../../FModel3dRenderable.cpp \
   \
   ../../FScene3d.cpp \
   ../../FScene3dConsole.cpp \
   ../../FScene3dDisplay.cpp \
   ../../FScene3dFrame.cpp \
   ../../FScene3dMaterial.cpp \
   ../../FScene3dMovie.cpp \
   ../../FScene3dMovieRotation.cpp \
   \
   ../../FTemplate3d.cpp \
   ../../FTemplate3dConsole.cpp \
   ../../FTemplate3dRenderable.cpp \
   \
   ../../FTexture3dBitmap.cpp \
   ../../FTexture3dConsole.cpp \
   ../../FTexture3dTexture.cpp \
   \
   ../../FInstance3dConsole.cpp \

# Library
LOCAL_SRC_FILES += \
   ../../MoEngine3d.cpp \
#............................................................
include $(BUILD_STATIC_LIBRARY)
