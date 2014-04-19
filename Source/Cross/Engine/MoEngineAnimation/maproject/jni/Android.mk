LOCAL_PATH := $(call my-dir)

#------------------------------------------------------------
include $(CLEAR_VARS)

# MO_COMPILER_FLAG       := -g -D_MO_DEBUG -D_MO_ANDROID -D_MO_X86
MO_COMPILER_FLAG       := -D_MO_ANDROID -D_MO_X86

LOCAL_MODULE           := MoEngineRender
LOCAL_ARM_MODE         := arm # thumb(2字节指令)/arm(4字节指令)
LOCAL_CFLAGS           := $(MO_COMPILER_FLAG)
LOCAL_CXXFLAGS         := $(MO_COMPILER_FLAG)
LOCAL_CPPFLAGS         := $(MO_COMPILER_FLAG)
LOCAL_CPP_EXTENSION    := .cpp
#............................................................
LOCAL_C_INCLUDES       := \
   $(LOCAL_PATH)/../../../../../Library/LibJpeg \
   $(LOCAL_PATH)/../../../../../Library/LibLzma \
   $(LOCAL_PATH)/../../../../../Library/LibFreeType \
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

# Render
LOCAL_SRC_FILES += \
   ../../FDeferredAutomaticEffect.cpp \
   \
   ../../FShadowColorAutomaticEffect.cpp \
   ../../FShadowColorPass.cpp \
   ../../FShadowDepthAutomaticEffect.cpp \
   ../../FShadowDepthPass.cpp \
   \
   ../../FSampleColorAutomaticEffect.cpp \
   ../../FSampleColorSkeletonEffect.cpp \
   ../../FSimpleColorPass.cpp \
   \
   ../../FBlurPass.cpp \
   \
   ../../FDeferredPipeline.cpp \
   ../../FRenderPipelinePass.cpp \
   ../../FShadowPipeline.cpp \
   ../../FSimplePipeline.cpp \
   \
   ../../FAutomaticEffect.cpp \
   ../../FBlurEffect.cpp \
   ../../TEffectAttributeDescriptors.cpp \
   ../../TEffectConstDescriptors.cpp \
   ../../TEffectSamplerDescriptors.cpp \

# Library
LOCAL_SRC_FILES += \
   ../../MoEngineRender.cpp \
#............................................................
include $(BUILD_STATIC_LIBRARY)
