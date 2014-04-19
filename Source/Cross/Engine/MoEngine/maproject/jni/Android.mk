LOCAL_PATH := $(call my-dir)

#------------------------------------------------------------

#------------------------------------------------------------
include $(CLEAR_VARS)

# MO_COMPILER_FLAG       := -g -D_MO_DEBUG -D_MO_ANDROID -D_MO_X86
MO_COMPILER_FLAG       := -D_MO_ANDROID -D_MO_X86

LOCAL_MODULE           := MoEngine
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

#............................................................
LOCAL_SRC_FILES :=

# Core
LOCAL_SRC_FILES += \
   ../../FFont.cpp \
   ../../FFontConsole.cpp \
   \
   ../../FProcessor.cpp \
   ../../FProcessorConsole.cpp \
   ../../FProcessorThread.cpp \

# Platform
LOCAL_SRC_FILES += \
   ../../FBitmapBlockIndexDecoder.cpp \
   ../../FBitmapDecoder.cpp \
   ../../FBitmapIndexDecoder.cpp \
   ../../FBitmapJpegLayer1Decoder.cpp \
   ../../FBitmapJpegLayer2Decoder.cpp \
   ../../FDecoder.cpp \
   ../../FDecoderConsole.cpp \
   ../../FDecoderWorker.cpp \
   \
   ../../FScreenDevice.cpp \
   ../../FTimerDevice.cpp \
   \
   ../../FBitmap.cpp \
   ../../FBitmapCanvas.cpp \
   ../../FComponent.cpp \
   ../../FComponentProperties.cpp \
   ../../FComponentProperty.cpp \
   ../../FDisplay.cpp \
   ../../FDisplayLayer.cpp \
   ../../FDisplayPool.cpp \
   ../../FDrawable.cpp \
   ../../FFocusConsole.cpp \
   ../../FFocusTester.cpp \
   ../../FSpatial.cpp \
   ../../FStage.cpp \
   ../../FStageConsole.cpp \
   ../../FStageFrame.cpp \
   ../../FTailController.cpp \
   \
   ../../FNetConsole.cpp \
   ../../FNetReceiveThread.cpp \
   ../../FNetSendThread.cpp \

# Instance
LOCAL_SRC_FILES += \
   ../../FEntity.cpp \
   ../../FEntityConsole.cpp \
   ../../FEntityPool.cpp \
   ../../FSpriteEntity.cpp \
   ../../FStillEntity.cpp \

# Stage
LOCAL_SRC_FILES += \
   ../../FEngineStatistics.cpp \
   ../../FEngineConsole.cpp \

# Library
LOCAL_SRC_FILES += \
   ../../MoEngine.cpp \
#............................................................
include $(BUILD_STATIC_LIBRARY)
