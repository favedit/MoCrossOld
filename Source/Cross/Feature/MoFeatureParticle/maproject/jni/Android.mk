LOCAL_PATH := $(call my-dir)

#------------------------------------------------------------
include $(CLEAR_VARS)

# MO_COMPILER_FLAG       := -g -D_MO_DEBUG -D_MO_ANDROID -D_MO_X86
MO_COMPILER_FLAG       := -D_MO_ANDROID -D_MO_X86

LOCAL_MODULE           := MoFeatureParticle
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

# Core
LOCAL_SRC_FILES += \
   ../../FParticle.cpp \
   ../../FParticleConsole.cpp \
   ../../FParticleController.cpp \
   ../../FParticleMove.cpp \
   ../../FParticlePool.cpp \

# Library
LOCAL_SRC_FILES += \
   ../../MoFeatureParticle.cpp \
#............................................................
include $(BUILD_STATIC_LIBRARY)
