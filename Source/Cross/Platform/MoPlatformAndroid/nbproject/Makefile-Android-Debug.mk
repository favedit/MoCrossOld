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
CND_PLATFORM=Android-Windows
CND_DLIB_EXT=dll
CND_CONF=Android-Debug
CND_DISTDIR=dist
CND_BUILDDIR=build

# Include project Makefile
include Makefile

# Object Directory
OBJECTDIR=${CND_BUILDDIR}/${CND_CONF}/${CND_PLATFORM}

# Object Files
OBJECTFILES= \
	${OBJECTDIR}/FEaAsset.o \
	${OBJECTDIR}/FEaAssetConsole.o \
	${OBJECTDIR}/FEaAssetDirectory.o \
	${OBJECTDIR}/MoEngineAndroid.o


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
	"${MAKE}"  -f nbproject/Makefile-${CND_CONF}.mk ${CND_DISTDIR}/${CND_CONF}/${CND_PLATFORM}/libmoengineandroid.a

${CND_DISTDIR}/${CND_CONF}/${CND_PLATFORM}/libmoengineandroid.a: ${OBJECTFILES}
	${MKDIR} -p ${CND_DISTDIR}/${CND_CONF}/${CND_PLATFORM}
	${RM} ${CND_DISTDIR}/${CND_CONF}/${CND_PLATFORM}/libmoengineandroid.a
	${AR} -rv ${CND_DISTDIR}/${CND_CONF}/${CND_PLATFORM}/libmoengineandroid.a ${OBJECTFILES} 
	$(RANLIB) ${CND_DISTDIR}/${CND_CONF}/${CND_PLATFORM}/libmoengineandroid.a

${OBJECTDIR}/FEaAsset.o: FEaAsset.cpp 
	${MKDIR} -p ${OBJECTDIR}
	$(COMPILE.cc) -g -Wall -D_MO_ANDROID -D_MO_DEBUG -D_MO_EF_EXPORTS -D_MO_X86 -I../../../../Studio/SDK/android-ndk/platforms/android-18/arch-arm/usr/include -I../../Library/MoLzma -I../../Library/MoJpeg -I../../Common/MoCommon -I../MoEngine -o ${OBJECTDIR}/FEaAsset.o FEaAsset.cpp

${OBJECTDIR}/FEaAssetConsole.o: FEaAssetConsole.cpp 
	${MKDIR} -p ${OBJECTDIR}
	$(COMPILE.cc) -g -Wall -D_MO_ANDROID -D_MO_DEBUG -D_MO_EF_EXPORTS -D_MO_X86 -I../../../../Studio/SDK/android-ndk/platforms/android-18/arch-arm/usr/include -I../../Library/MoLzma -I../../Library/MoJpeg -I../../Common/MoCommon -I../MoEngine -o ${OBJECTDIR}/FEaAssetConsole.o FEaAssetConsole.cpp

${OBJECTDIR}/FEaAssetDirectory.o: FEaAssetDirectory.cpp 
	${MKDIR} -p ${OBJECTDIR}
	$(COMPILE.cc) -g -Wall -D_MO_ANDROID -D_MO_DEBUG -D_MO_EF_EXPORTS -D_MO_X86 -I../../../../Studio/SDK/android-ndk/platforms/android-18/arch-arm/usr/include -I../../Library/MoLzma -I../../Library/MoJpeg -I../../Common/MoCommon -I../MoEngine -o ${OBJECTDIR}/FEaAssetDirectory.o FEaAssetDirectory.cpp

${OBJECTDIR}/MoEngineAndroid.o: MoEngineAndroid.cpp 
	${MKDIR} -p ${OBJECTDIR}
	$(COMPILE.cc) -g -Wall -D_MO_ANDROID -D_MO_DEBUG -D_MO_EF_EXPORTS -D_MO_X86 -I../../../../Studio/SDK/android-ndk/platforms/android-18/arch-arm/usr/include -I../../Library/MoLzma -I../../Library/MoJpeg -I../../Common/MoCommon -I../MoEngine -o ${OBJECTDIR}/MoEngineAndroid.o MoEngineAndroid.cpp

# Subprojects
.build-subprojects:

# Clean Targets
.clean-conf: ${CLEAN_SUBPROJECTS}
	${RM} -r ${CND_BUILDDIR}/${CND_CONF}
	${RM} ${CND_DISTDIR}/${CND_CONF}/${CND_PLATFORM}/libmoengineandroid.a

# Subprojects
.clean-subprojects:
