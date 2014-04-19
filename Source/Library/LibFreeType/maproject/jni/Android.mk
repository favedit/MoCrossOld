LOCAL_PATH := $(call my-dir)

#------------------------------------------------------------
include $(CLEAR_VARS)

# MO_COMPILER_FLAG       := -g -D_MO_DEBUG -D_MO_ANDROID -D_MO_X86
MO_COMPILER_FLAG       := -D_MO_ANDROID -D_MO_X86

MO_COMPILER_FLAG       += -D__LINUX_CPL__ -DUNICODE -DFT2_BUILD_LIBRARY

MO_COMPILER_FLAG       += $(MO_COMPILER_FLAG) \
   -I.. \
   -I../freetype/include \
   -I../freetype/include/freetype/ \
   -I../freetype/include/freetype/config/ \
   -I../freetype/include/freetype/internal/ \
   -I../freetype/include/freetype/internal/services/ \
   -I../source/autofit/ \
   -I../source/base/ \
   -I../source/bdf/ \
   -I../source/cff/ \
   -I../source/cid/ \
   -I../source/gxvalid/ \
   -I../source/gzip/ \
   -I../source/lzw/ \
   -I../source/otvalid/ \
   -I../source/pcf/ \
   -I../source/pfr/ \
   -I../source/psaux/ \
   -I../source/pshinter/ \
   -I../source/psnames/ \
   -I../source/raster/ \
   -I../source/sfnt/ \
   -I../source/smooth/ \
   -I../source/tools/ \
   -I../source/tools/docmaker/ \
   -I../source/tools/ftrandom/ \
   -I../source/truetype/ \
   -I../source/type1/ \
   -I../source/type42/ \
   -I../source/winfonts/ \

LOCAL_MODULE           := MoFreeType
LOCAL_ARM_MODE         := arm # thumb(2字节指令)/arm(4字节指令)
LOCAL_CFLAGS           := $(MO_COMPILER_FLAG)
LOCAL_CXXFLAGS         := $(MO_COMPILER_FLAG)
LOCAL_CPPFLAGS         := $(MO_COMPILER_FLAG)
#............................................................
LOCAL_C_INCLUDES       := \

#............................................................
LOCAL_SRC_FILES := 

# FT_MODULES
LOCAL_SRC_FILES += \
   ../../source/base/ftbbox.c \
   ../../source/base/ftgxval.c \
   ../../source/base/ftlcdfil.c \
   ../../source/base/ftmm.c \
   ../../source/base/ftotval.c \
   ../../source/base/ftpatent.c \
   ../../source/base/ftpfr.c \
   ../../source/base/ftsynth.c \
   ../../source/base/fttype1.c \
   ../../source/base/ftwinfnt.c \
   ../../source/base/ftxf86.c \
   ../../source/pcf/pcf.c \
   ../../source/pfr/pfr.c \
   ../../source/psaux/psaux.c \
   ../../source/pshinter/pshinter.c \
   ../../source/psnames/psmodule.c \
   ../../source/raster/raster.c \
   ../../source/sfnt/sfnt.c \
   ../../source/truetype/truetype.c \
   ../../source/type1/type1.c \
   ../../source/cid/type1cid.c \
   ../../source/type42/type42.c \
   ../../source/winfonts/winfnt.c \

LOCAL_SRC_FILES += \
   ../../source/autofit/autofit.c \
   ../../source/bdf/bdf.c \
   ../../source/cff/cff.c \
   ../../source/base/ftbase.c \
   ../../source/base/ftbitmap.c \
   ../../source/cache/ftcache.c \
   ../../source/base/ftfstype.c \
   ../../source/base/ftgasp.c \
   ../../source/base/ftglyph.c \
   ../../source/gzip/ftgzip.c \
   ../../source/base/ftinit.c \
   ../../source/lzw/ftlzw.c \
   ../../source/base/ftstroke.c \
   ../../source/base/ftsystem.c \
   ../../source/smooth/smooth.c \

#............................................................
include $(BUILD_STATIC_LIBRARY)
