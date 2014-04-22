LOCAL_PATH := $(call my-dir)

#------------------------------------------------------------
include $(CLEAR_VARS)

#............................................................
# MO_COMPILER_FLAG       := -g -D_MO_DEBUG -D_MO_ANDROID -D_MO_X86
MO_COMPILER_FLAG       := -D_MO_ANDROID -D_MO_X86

#............................................................
LOCAL_MODULE           := MoMath
LOCAL_ARM_MODE         := arm
LOCAL_CFLAGS           := $(MO_COMPILER_FLAG)
LOCAL_CXXFLAGS         := $(MO_COMPILER_FLAG)
LOCAL_CPPFLAGS         := $(MO_COMPILER_FLAG)
#............................................................
LOCAL_C_INCLUDES       := \
   $(LOCAL_PATH)/../../../MoCommon \

#............................................................
LOCAL_SRC_FILES :=

# Core
LOCAL_SRC_FILES += \
   ../../SFloat4.cpp \

# 4-Geom
LOCAL_SRC_FILES += \
   ../../SFloatColor4.cpp \
   \
   ../../SDoubleMatrix3d.cpp \
   ../../SDoubleMatrix4x4.cpp \
   ../../SFloatMatrix3d.cpp \
   ../../SFloatMatrix4x4.cpp \
   \
   ../../SDoublePadding.cpp \
   ../../SFloatPadding.cpp \
   ../../SIntPadding.cpp \
   \
   ../../SDoublePoint2.cpp \
   ../../SDoublePoint3.cpp \
   ../../SDoublePoint4.cpp \
   ../../SFloatPoint2.cpp \
   ../../SFloatPoint3.cpp \
   ../../SFloatPoint4.cpp \
   ../../SIntPoint2.cpp \
   ../../SIntPoint3.cpp \
   ../../SIntPoint4.cpp \
   \
   ../../SDoubleRange.cpp \
   ../../SFloatRange.cpp \
   ../../SIntRange.cpp \
   \
   ../../SDoubleRectangle.cpp \
   ../../SFloatRectangle.cpp \
   ../../SIntRectangle.cpp \
   \
   ../../SDoubleSize2.cpp \
   ../../SDoubleSize3.cpp \
   ../../SFloatSize2.cpp \
   ../../SFloatSize3.cpp \
   ../../SIntSize2.cpp \
   ../../SIntSize3.cpp \
   \
   ../../SDoubleVector2.cpp \
   ../../SDoubleVector3.cpp \
   ../../SDoubleVector4.cpp \
   ../../SFloatVector2.cpp \
   ../../SFloatVector3.cpp \
   ../../SFloatVector4.cpp \
   ../../SIntVector2.cpp \
   ../../SIntVector3.cpp \
   ../../SIntVector4.cpp \

# Format
LOCAL_SRC_FILES += \
   ../../TFsFloatPoint3.cpp \
   ../../TFsFloatRange.cpp \
   ../../TFsFloatSize.cpp \
   ../../TFsFloatVector3.cpp \
   ../../TFsIntPoint2.cpp \
   ../../TFsIntPoint3.cpp \
   ../../TFsIntSize.cpp \

# Library
LOCAL_SRC_FILES += \
   ../../MoMath.cpp \

#............................................................
include $(BUILD_STATIC_LIBRARY)
