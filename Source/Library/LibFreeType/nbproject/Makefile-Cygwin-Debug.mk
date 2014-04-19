#
# Generated Makefile - do not edit!
#
# Edit the Makefile in the project folder instead (../Makefile). Each target
# has a -pre and a -post target defined where you can add customized code.
#
# This makefile implements configuration specific macros and targets.


# Environment
MKDIR=mkdir
CP=cp
GREP=grep
NM=nm
CCADMIN=CCadmin
RANLIB=ranlib
CC=gcc
CCC=g++
CXX=g++
FC=gfortran
AS=as

# Macros
CND_PLATFORM=Cygwin-Windows
CND_DLIB_EXT=dll
CND_CONF=Cygwin-Debug
CND_DISTDIR=dist
CND_BUILDDIR=build

# Include project Makefile
include Makefile

# Object Directory
OBJECTDIR=${CND_BUILDDIR}/${CND_CONF}/${CND_PLATFORM}

# Object Files
OBJECTFILES= \
	${OBJECTDIR}/source/autofit/autofit.o \
	${OBJECTDIR}/source/base/ftbase.o \
	${OBJECTDIR}/source/base/ftbbox.o \
	${OBJECTDIR}/source/base/ftbitmap.o \
	${OBJECTDIR}/source/base/ftfstype.o \
	${OBJECTDIR}/source/base/ftgasp.o \
	${OBJECTDIR}/source/base/ftglyph.o \
	${OBJECTDIR}/source/base/ftgxval.o \
	${OBJECTDIR}/source/base/ftinit.o \
	${OBJECTDIR}/source/base/ftlcdfil.o \
	${OBJECTDIR}/source/base/ftmm.o \
	${OBJECTDIR}/source/base/ftotval.o \
	${OBJECTDIR}/source/base/ftpatent.o \
	${OBJECTDIR}/source/base/ftpfr.o \
	${OBJECTDIR}/source/base/ftstroke.o \
	${OBJECTDIR}/source/base/ftsynth.o \
	${OBJECTDIR}/source/base/ftsystem.o \
	${OBJECTDIR}/source/base/fttype1.o \
	${OBJECTDIR}/source/base/ftwinfnt.o \
	${OBJECTDIR}/source/base/ftxf86.o \
	${OBJECTDIR}/source/bdf/bdf.o \
	${OBJECTDIR}/source/cache/ftcache.o \
	${OBJECTDIR}/source/cff/cff.o \
	${OBJECTDIR}/source/cid/type1cid.o \
	${OBJECTDIR}/source/gzip/ftgzip.o \
	${OBJECTDIR}/source/lzw/ftlzw.o \
	${OBJECTDIR}/source/pcf/pcf.o \
	${OBJECTDIR}/source/pfr/pfr.o \
	${OBJECTDIR}/source/psaux/psaux.o \
	${OBJECTDIR}/source/pshinter/pshinter.o \
	${OBJECTDIR}/source/raster/raster.o \
	${OBJECTDIR}/source/sfnt/sfnt.o \
	${OBJECTDIR}/source/smooth/smooth.o \
	${OBJECTDIR}/source/truetype/truetype.o \
	${OBJECTDIR}/source/type1/type1.o \
	${OBJECTDIR}/source/type42/type42.o \
	${OBJECTDIR}/source/winfonts/winfnt.o


# C Compiler Flags
CFLAGS=

# CC Compiler Flags
CCFLAGS=
CXXFLAGS=

# Fortran Compiler Flags
FFLAGS=

# Assembler Flags
ASFLAGS=

# Link Libraries and Options
LDLIBSOPTIONS=

# Build Targets
.build-conf: ${BUILD_SUBPROJECTS}
	"${MAKE}"  -f nbproject/Makefile-${CND_CONF}.mk ${CND_DISTDIR}/${CND_CONF}/${CND_PLATFORM}/libmofreetype.a

${CND_DISTDIR}/${CND_CONF}/${CND_PLATFORM}/libmofreetype.a: ${OBJECTFILES}
	${MKDIR} -p ${CND_DISTDIR}/${CND_CONF}/${CND_PLATFORM}
	${RM} ${CND_DISTDIR}/${CND_CONF}/${CND_PLATFORM}/libmofreetype.a
	${AR} -rv ${CND_DISTDIR}/${CND_CONF}/${CND_PLATFORM}/libmofreetype.a ${OBJECTFILES} 
	$(RANLIB) ${CND_DISTDIR}/${CND_CONF}/${CND_PLATFORM}/libmofreetype.a

${OBJECTDIR}/source/autofit/autofit.o: source/autofit/autofit.c 
	${MKDIR} -p ${OBJECTDIR}/source/autofit
	${RM} $@.d
	$(COMPILE.c) -g -D_DEBUG -D_MO_LINUX -D_MO_X64 -I../MoFreeType -MMD -MP -MF $@.d -o ${OBJECTDIR}/source/autofit/autofit.o source/autofit/autofit.c

${OBJECTDIR}/source/base/ftbase.o: source/base/ftbase.c 
	${MKDIR} -p ${OBJECTDIR}/source/base
	${RM} $@.d
	$(COMPILE.c) -g -D_DEBUG -D_MO_LINUX -D_MO_X64 -I../MoFreeType -MMD -MP -MF $@.d -o ${OBJECTDIR}/source/base/ftbase.o source/base/ftbase.c

${OBJECTDIR}/source/base/ftbbox.o: source/base/ftbbox.c 
	${MKDIR} -p ${OBJECTDIR}/source/base
	${RM} $@.d
	$(COMPILE.c) -g -D_DEBUG -D_MO_LINUX -D_MO_X64 -I../MoFreeType -MMD -MP -MF $@.d -o ${OBJECTDIR}/source/base/ftbbox.o source/base/ftbbox.c

${OBJECTDIR}/source/base/ftbitmap.o: source/base/ftbitmap.c 
	${MKDIR} -p ${OBJECTDIR}/source/base
	${RM} $@.d
	$(COMPILE.c) -g -D_DEBUG -D_MO_LINUX -D_MO_X64 -I../MoFreeType -MMD -MP -MF $@.d -o ${OBJECTDIR}/source/base/ftbitmap.o source/base/ftbitmap.c

${OBJECTDIR}/source/base/ftfstype.o: source/base/ftfstype.c 
	${MKDIR} -p ${OBJECTDIR}/source/base
	${RM} $@.d
	$(COMPILE.c) -g -D_DEBUG -D_MO_LINUX -D_MO_X64 -I../MoFreeType -MMD -MP -MF $@.d -o ${OBJECTDIR}/source/base/ftfstype.o source/base/ftfstype.c

${OBJECTDIR}/source/base/ftgasp.o: source/base/ftgasp.c 
	${MKDIR} -p ${OBJECTDIR}/source/base
	${RM} $@.d
	$(COMPILE.c) -g -D_DEBUG -D_MO_LINUX -D_MO_X64 -I../MoFreeType -MMD -MP -MF $@.d -o ${OBJECTDIR}/source/base/ftgasp.o source/base/ftgasp.c

${OBJECTDIR}/source/base/ftglyph.o: source/base/ftglyph.c 
	${MKDIR} -p ${OBJECTDIR}/source/base
	${RM} $@.d
	$(COMPILE.c) -g -D_DEBUG -D_MO_LINUX -D_MO_X64 -I../MoFreeType -MMD -MP -MF $@.d -o ${OBJECTDIR}/source/base/ftglyph.o source/base/ftglyph.c

${OBJECTDIR}/source/base/ftgxval.o: source/base/ftgxval.c 
	${MKDIR} -p ${OBJECTDIR}/source/base
	${RM} $@.d
	$(COMPILE.c) -g -D_DEBUG -D_MO_LINUX -D_MO_X64 -I../MoFreeType -MMD -MP -MF $@.d -o ${OBJECTDIR}/source/base/ftgxval.o source/base/ftgxval.c

${OBJECTDIR}/source/base/ftinit.o: source/base/ftinit.c 
	${MKDIR} -p ${OBJECTDIR}/source/base
	${RM} $@.d
	$(COMPILE.c) -g -D_DEBUG -D_MO_LINUX -D_MO_X64 -I../MoFreeType -MMD -MP -MF $@.d -o ${OBJECTDIR}/source/base/ftinit.o source/base/ftinit.c

${OBJECTDIR}/source/base/ftlcdfil.o: source/base/ftlcdfil.c 
	${MKDIR} -p ${OBJECTDIR}/source/base
	${RM} $@.d
	$(COMPILE.c) -g -D_DEBUG -D_MO_LINUX -D_MO_X64 -I../MoFreeType -MMD -MP -MF $@.d -o ${OBJECTDIR}/source/base/ftlcdfil.o source/base/ftlcdfil.c

${OBJECTDIR}/source/base/ftmm.o: source/base/ftmm.c 
	${MKDIR} -p ${OBJECTDIR}/source/base
	${RM} $@.d
	$(COMPILE.c) -g -D_DEBUG -D_MO_LINUX -D_MO_X64 -I../MoFreeType -MMD -MP -MF $@.d -o ${OBJECTDIR}/source/base/ftmm.o source/base/ftmm.c

${OBJECTDIR}/source/base/ftotval.o: source/base/ftotval.c 
	${MKDIR} -p ${OBJECTDIR}/source/base
	${RM} $@.d
	$(COMPILE.c) -g -D_DEBUG -D_MO_LINUX -D_MO_X64 -I../MoFreeType -MMD -MP -MF $@.d -o ${OBJECTDIR}/source/base/ftotval.o source/base/ftotval.c

${OBJECTDIR}/source/base/ftpatent.o: source/base/ftpatent.c 
	${MKDIR} -p ${OBJECTDIR}/source/base
	${RM} $@.d
	$(COMPILE.c) -g -D_DEBUG -D_MO_LINUX -D_MO_X64 -I../MoFreeType -MMD -MP -MF $@.d -o ${OBJECTDIR}/source/base/ftpatent.o source/base/ftpatent.c

${OBJECTDIR}/source/base/ftpfr.o: source/base/ftpfr.c 
	${MKDIR} -p ${OBJECTDIR}/source/base
	${RM} $@.d
	$(COMPILE.c) -g -D_DEBUG -D_MO_LINUX -D_MO_X64 -I../MoFreeType -MMD -MP -MF $@.d -o ${OBJECTDIR}/source/base/ftpfr.o source/base/ftpfr.c

${OBJECTDIR}/source/base/ftstroke.o: source/base/ftstroke.c 
	${MKDIR} -p ${OBJECTDIR}/source/base
	${RM} $@.d
	$(COMPILE.c) -g -D_DEBUG -D_MO_LINUX -D_MO_X64 -I../MoFreeType -MMD -MP -MF $@.d -o ${OBJECTDIR}/source/base/ftstroke.o source/base/ftstroke.c

${OBJECTDIR}/source/base/ftsynth.o: source/base/ftsynth.c 
	${MKDIR} -p ${OBJECTDIR}/source/base
	${RM} $@.d
	$(COMPILE.c) -g -D_DEBUG -D_MO_LINUX -D_MO_X64 -I../MoFreeType -MMD -MP -MF $@.d -o ${OBJECTDIR}/source/base/ftsynth.o source/base/ftsynth.c

${OBJECTDIR}/source/base/ftsystem.o: source/base/ftsystem.c 
	${MKDIR} -p ${OBJECTDIR}/source/base
	${RM} $@.d
	$(COMPILE.c) -g -D_DEBUG -D_MO_LINUX -D_MO_X64 -I../MoFreeType -MMD -MP -MF $@.d -o ${OBJECTDIR}/source/base/ftsystem.o source/base/ftsystem.c

${OBJECTDIR}/source/base/fttype1.o: source/base/fttype1.c 
	${MKDIR} -p ${OBJECTDIR}/source/base
	${RM} $@.d
	$(COMPILE.c) -g -D_DEBUG -D_MO_LINUX -D_MO_X64 -I../MoFreeType -MMD -MP -MF $@.d -o ${OBJECTDIR}/source/base/fttype1.o source/base/fttype1.c

${OBJECTDIR}/source/base/ftwinfnt.o: source/base/ftwinfnt.c 
	${MKDIR} -p ${OBJECTDIR}/source/base
	${RM} $@.d
	$(COMPILE.c) -g -D_DEBUG -D_MO_LINUX -D_MO_X64 -I../MoFreeType -MMD -MP -MF $@.d -o ${OBJECTDIR}/source/base/ftwinfnt.o source/base/ftwinfnt.c

${OBJECTDIR}/source/base/ftxf86.o: source/base/ftxf86.c 
	${MKDIR} -p ${OBJECTDIR}/source/base
	${RM} $@.d
	$(COMPILE.c) -g -D_DEBUG -D_MO_LINUX -D_MO_X64 -I../MoFreeType -MMD -MP -MF $@.d -o ${OBJECTDIR}/source/base/ftxf86.o source/base/ftxf86.c

${OBJECTDIR}/source/bdf/bdf.o: source/bdf/bdf.c 
	${MKDIR} -p ${OBJECTDIR}/source/bdf
	${RM} $@.d
	$(COMPILE.c) -g -D_DEBUG -D_MO_LINUX -D_MO_X64 -I../MoFreeType -MMD -MP -MF $@.d -o ${OBJECTDIR}/source/bdf/bdf.o source/bdf/bdf.c

${OBJECTDIR}/source/cache/ftcache.o: source/cache/ftcache.c 
	${MKDIR} -p ${OBJECTDIR}/source/cache
	${RM} $@.d
	$(COMPILE.c) -g -D_DEBUG -D_MO_LINUX -D_MO_X64 -I../MoFreeType -MMD -MP -MF $@.d -o ${OBJECTDIR}/source/cache/ftcache.o source/cache/ftcache.c

${OBJECTDIR}/source/cff/cff.o: source/cff/cff.c 
	${MKDIR} -p ${OBJECTDIR}/source/cff
	${RM} $@.d
	$(COMPILE.c) -g -D_DEBUG -D_MO_LINUX -D_MO_X64 -I../MoFreeType -MMD -MP -MF $@.d -o ${OBJECTDIR}/source/cff/cff.o source/cff/cff.c

${OBJECTDIR}/source/cid/type1cid.o: source/cid/type1cid.c 
	${MKDIR} -p ${OBJECTDIR}/source/cid
	${RM} $@.d
	$(COMPILE.c) -g -D_DEBUG -D_MO_LINUX -D_MO_X64 -I../MoFreeType -MMD -MP -MF $@.d -o ${OBJECTDIR}/source/cid/type1cid.o source/cid/type1cid.c

${OBJECTDIR}/source/gzip/ftgzip.o: source/gzip/ftgzip.c 
	${MKDIR} -p ${OBJECTDIR}/source/gzip
	${RM} $@.d
	$(COMPILE.c) -g -D_DEBUG -D_MO_LINUX -D_MO_X64 -I../MoFreeType -MMD -MP -MF $@.d -o ${OBJECTDIR}/source/gzip/ftgzip.o source/gzip/ftgzip.c

${OBJECTDIR}/source/lzw/ftlzw.o: source/lzw/ftlzw.c 
	${MKDIR} -p ${OBJECTDIR}/source/lzw
	${RM} $@.d
	$(COMPILE.c) -g -D_DEBUG -D_MO_LINUX -D_MO_X64 -I../MoFreeType -MMD -MP -MF $@.d -o ${OBJECTDIR}/source/lzw/ftlzw.o source/lzw/ftlzw.c

${OBJECTDIR}/source/pcf/pcf.o: source/pcf/pcf.c 
	${MKDIR} -p ${OBJECTDIR}/source/pcf
	${RM} $@.d
	$(COMPILE.c) -g -D_DEBUG -D_MO_LINUX -D_MO_X64 -I../MoFreeType -MMD -MP -MF $@.d -o ${OBJECTDIR}/source/pcf/pcf.o source/pcf/pcf.c

${OBJECTDIR}/source/pfr/pfr.o: source/pfr/pfr.c 
	${MKDIR} -p ${OBJECTDIR}/source/pfr
	${RM} $@.d
	$(COMPILE.c) -g -D_DEBUG -D_MO_LINUX -D_MO_X64 -I../MoFreeType -MMD -MP -MF $@.d -o ${OBJECTDIR}/source/pfr/pfr.o source/pfr/pfr.c

${OBJECTDIR}/source/psaux/psaux.o: source/psaux/psaux.c 
	${MKDIR} -p ${OBJECTDIR}/source/psaux
	${RM} $@.d
	$(COMPILE.c) -g -D_DEBUG -D_MO_LINUX -D_MO_X64 -I../MoFreeType -MMD -MP -MF $@.d -o ${OBJECTDIR}/source/psaux/psaux.o source/psaux/psaux.c

${OBJECTDIR}/source/pshinter/pshinter.o: source/pshinter/pshinter.c 
	${MKDIR} -p ${OBJECTDIR}/source/pshinter
	${RM} $@.d
	$(COMPILE.c) -g -D_DEBUG -D_MO_LINUX -D_MO_X64 -I../MoFreeType -MMD -MP -MF $@.d -o ${OBJECTDIR}/source/pshinter/pshinter.o source/pshinter/pshinter.c

${OBJECTDIR}/source/raster/raster.o: source/raster/raster.c 
	${MKDIR} -p ${OBJECTDIR}/source/raster
	${RM} $@.d
	$(COMPILE.c) -g -D_DEBUG -D_MO_LINUX -D_MO_X64 -I../MoFreeType -MMD -MP -MF $@.d -o ${OBJECTDIR}/source/raster/raster.o source/raster/raster.c

${OBJECTDIR}/source/sfnt/sfnt.o: source/sfnt/sfnt.c 
	${MKDIR} -p ${OBJECTDIR}/source/sfnt
	${RM} $@.d
	$(COMPILE.c) -g -D_DEBUG -D_MO_LINUX -D_MO_X64 -I../MoFreeType -MMD -MP -MF $@.d -o ${OBJECTDIR}/source/sfnt/sfnt.o source/sfnt/sfnt.c

${OBJECTDIR}/source/smooth/smooth.o: source/smooth/smooth.c 
	${MKDIR} -p ${OBJECTDIR}/source/smooth
	${RM} $@.d
	$(COMPILE.c) -g -D_DEBUG -D_MO_LINUX -D_MO_X64 -I../MoFreeType -MMD -MP -MF $@.d -o ${OBJECTDIR}/source/smooth/smooth.o source/smooth/smooth.c

${OBJECTDIR}/source/truetype/truetype.o: source/truetype/truetype.c 
	${MKDIR} -p ${OBJECTDIR}/source/truetype
	${RM} $@.d
	$(COMPILE.c) -g -D_DEBUG -D_MO_LINUX -D_MO_X64 -I../MoFreeType -MMD -MP -MF $@.d -o ${OBJECTDIR}/source/truetype/truetype.o source/truetype/truetype.c

${OBJECTDIR}/source/type1/type1.o: source/type1/type1.c 
	${MKDIR} -p ${OBJECTDIR}/source/type1
	${RM} $@.d
	$(COMPILE.c) -g -D_DEBUG -D_MO_LINUX -D_MO_X64 -I../MoFreeType -MMD -MP -MF $@.d -o ${OBJECTDIR}/source/type1/type1.o source/type1/type1.c

${OBJECTDIR}/source/type42/type42.o: source/type42/type42.c 
	${MKDIR} -p ${OBJECTDIR}/source/type42
	${RM} $@.d
	$(COMPILE.c) -g -D_DEBUG -D_MO_LINUX -D_MO_X64 -I../MoFreeType -MMD -MP -MF $@.d -o ${OBJECTDIR}/source/type42/type42.o source/type42/type42.c

${OBJECTDIR}/source/winfonts/winfnt.o: source/winfonts/winfnt.c 
	${MKDIR} -p ${OBJECTDIR}/source/winfonts
	${RM} $@.d
	$(COMPILE.c) -g -D_DEBUG -D_MO_LINUX -D_MO_X64 -I../MoFreeType -MMD -MP -MF $@.d -o ${OBJECTDIR}/source/winfonts/winfnt.o source/winfonts/winfnt.c

# Subprojects
.build-subprojects:

# Clean Targets
.clean-conf: ${CLEAN_SUBPROJECTS}
	${RM} -r ${CND_BUILDDIR}/${CND_CONF}
	${RM} ${CND_DISTDIR}/${CND_CONF}/${CND_PLATFORM}/libmofreetype.a

# Subprojects
.clean-subprojects:

# Enable dependency checking
.dep.inc: .depcheck-impl

include .dep.inc
