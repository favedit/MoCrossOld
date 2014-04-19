LOCAL_PATH := $(call my-dir)

#------------------------------------------------------------
include $(CLEAR_VARS)

# MO_COMPILER_FLAG       := -g -D_MO_DEBUG -D_MO_ANDROID
MO_COMPILER_FLAG       := -O3 -D_MO_ANDROID

LOCAL_MODULE           := MoLzma
LOCAL_ARM_MODE         := arm # thumb(2字节指令)/arm(4字节指令)
LOCAL_CFLAGS           := $(MO_COMPILER_FLAG)
LOCAL_CXXFLAGS         := $(MO_COMPILER_FLAG)
LOCAL_CPPFLAGS         := $(MO_COMPILER_FLAG)
LOCAL_CPP_EXTENSION    := .cpp
#............................................................
LOCAL_C_INCLUDES       := \
   $(LOCAL_PATH)/../../../../1-Common/MoCmBase \
#............................................................
LOCAL_SRC_FILES        := \
   ../../Alloc.c \
   ../../Bra.c \
   \
   ../../LzFind.c \
   ../../LzmaDec.c \
   ../../Lzma2Dec.c \
   ../../Lzma86Dec.c \
   ../../LzmaLib.c \
#............................................................
include $(BUILD_STATIC_LIBRARY)
