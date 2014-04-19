LOCAL_PATH := $(call my-dir)

#------------------------------------------------------------
include $(CLEAR_VARS)

MO_COMPILER_FLAG       := -D_MO_ANDROID -D_MO_X86

LOCAL_MODULE           := LibJpeg
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
   ../../jaricom.c \
   ../../jcapimin.c \
   ../../jcapistd.c \
   ../../jcarith.c \
   ../../jccoefct.c \
   ../../jccolor.c \
   ../../jcdctmgr.c \
   ../../jchuff.c \
   ../../jcinit.c \
   ../../jcmainct.c \
   ../../jcmarker.c \
   ../../jcmaster.c\
   ../../jcomapi.c \
   ../../jcparam.c \
   ../../jcprepct.c \
   ../../jcsample.c \
   ../../jctrans.c \
   ../../jdapimin.c \
   ../../jdapistd.c \
   ../../jdarith.c \
   ../../jdatadst.c \
   ../../jdatasrc.c \
   ../../jdcoefct.c \
   ../../jdcolor.c \
   ../../jddctmgr.c \
   ../../jdhuff.c \
   ../../jdinput.c \
   ../../jdmainct.c \
   ../../jdmarker.c \
   ../../jdmaster.c \
   ../../jdmerge.c \
   ../../jdpostct.c \
   ../../jdsample.c \
   ../../jdtrans.c \
   ../../jerror.c \
   ../../jfdctflt.c \
   ../../jfdctfst.c \
   ../../jfdctint.c \
   ../../jidctflt.c \
   ../../jidctfst.c \
   ../../jidctint.c \
   ../../jmemmgr.c \
   ../../jmemnobs.c \
   ../../jquant1.c \
   ../../jquant2.c \
   ../../jutils.c \
#............................................................
include $(BUILD_STATIC_LIBRARY)
