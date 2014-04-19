LOCAL_PATH := $(call my-dir)

#------------------------------------------------------------
include $(CLEAR_VARS)

# MO_COMPILER_FLAG       := -g -D_MO_DEBUG -D_MO_ANDROID -D_MO_X86
MO_COMPILER_FLAG       := -D_MO_ANDROID -D_MO_X86

LOCAL_MODULE           := MoEngineFace
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
   $(LOCAL_PATH)/../../../../Feature/MoFeatureInput \
   $(LOCAL_PATH)/../../../../Feature/MoFeatureGraphic \
   $(LOCAL_PATH)/../../../../Feature/MoFeatureParticle \
   $(LOCAL_PATH)/../../../../Feature/MoFeaturePhysics \
   $(LOCAL_PATH)/../../../../Feature/MoFeatureResource \
   $(LOCAL_PATH)/../../../../Feature/MoFeatureSound \
   $(LOCAL_PATH)/../../../../Engine/MoEngine \

#............................................................
LOCAL_SRC_FILES :=

# Core
LOCAL_SRC_FILES += \
   ../../SUiLine.cpp \
   ../../SUiBorder.cpp \
   ../../SUiFont.cpp \
   ../../SUiPicture.cpp \
   \
   ../../FUiCanvas.cpp \
   ../../FUiControl.cpp \
   ../../FUiControlContainer.cpp \
   ../../FUiControlPool.cpp \
   ../../FUiControlConsole.cpp \
   ../../FUiFrame.cpp \
   ../../FUiFrameConsole.cpp \
   ../../FUiFaceConsole.cpp \
   \
   ../../RUiUtil.cpp \

# Stage
LOCAL_SRC_FILES += \
   ../../FUiLabel.cpp \
   ../../FUiButton.cpp \
   ../../FUiEdit.cpp \
   \
   ../../FUiPanel.cpp \
   ../../FUiPage.cpp \
   ../../FUiPageControl.cpp \
   \
   ../../FUiBar.cpp \
   ../../FUiForm.cpp \
   ../../FUiWindow.cpp \

# Runtime
LOCAL_SRC_FILES += \
   ../../FFmRuntimeBar.cpp \
   ../../FFmRuntimeWindow.cpp \
   ../../FRuntimeFrameConsole.cpp \

# Library
LOCAL_SRC_FILES += \
   ../../MoEngineFace.cpp \
#............................................................
include $(BUILD_STATIC_LIBRARY)
