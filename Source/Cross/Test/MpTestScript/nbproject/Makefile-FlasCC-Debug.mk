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
CND_PLATFORM=FlasCC-Windows
CND_DLIB_EXT=dll
CND_CONF=FlasCC-Debug
CND_DISTDIR=dist
CND_BUILDDIR=build

# Include project Makefile
include Makefile

# Object Directory
OBJECTDIR=${CND_BUILDDIR}/${CND_CONF}/${CND_PLATFORM}

# Object Files
OBJECTFILES= \
	${OBJECTDIR}/FEfDeviceConsole.o \
	${OBJECTDIR}/FEfKeybord.o \
	${OBJECTDIR}/FEfRenderDevice.o \
	${OBJECTDIR}/FEfRenderProgrom.o \
	${OBJECTDIR}/FEfStage.o \
	${OBJECTDIR}/MoEngineFlash.o


# C Compiler Flags
CFLAGS=

# CC Compiler Flags
CCFLAGS=-jvmopt=-Xmx2G -emit-llvm
CXXFLAGS=-jvmopt=-Xmx2G -emit-llvm

# Fortran Compiler Flags
FFLAGS=

# Assembler Flags
ASFLAGS=

# Link Libraries and Options
LDLIBSOPTIONS=

# Build Targets
.build-conf: ${BUILD_SUBPROJECTS}
	"${MAKE}"  -f nbproject/Makefile-${CND_CONF}.mk ${CND_DISTDIR}/${CND_CONF}/${CND_PLATFORM}/libmoengineflash.a

${CND_DISTDIR}/${CND_CONF}/${CND_PLATFORM}/libmoengineflash.a: ${OBJECTFILES}
	${MKDIR} -p ${CND_DISTDIR}/${CND_CONF}/${CND_PLATFORM}
	${RM} ${CND_DISTDIR}/${CND_CONF}/${CND_PLATFORM}/libmoengineflash.a
	${AR} -rv ${CND_DISTDIR}/${CND_CONF}/${CND_PLATFORM}/libmoengineflash.a ${OBJECTFILES} 
	$(RANLIB) ${CND_DISTDIR}/${CND_CONF}/${CND_PLATFORM}/libmoengineflash.a

${OBJECTDIR}/FEfDeviceConsole.o: FEfDeviceConsole.cpp 
	${MKDIR} -p ${OBJECTDIR}
	$(COMPILE.cc) -g -Wall -D_MO_DEBUG -D_MO_EF_EXPORTS -D_MO_FLASCC -D_MO_FLASH -D_MO_X86 -I../../../Studio/SDK/flascc_1.0.0/sdk/usr/include -I../../Library/MoLzma -I../../Library/MoJpeg -I../../Common/MoCommon -I../MoEngine -o ${OBJECTDIR}/FEfDeviceConsole.o FEfDeviceConsole.cpp

${OBJECTDIR}/FEfKeybord.o: FEfKeybord.cpp 
	${MKDIR} -p ${OBJECTDIR}
	$(COMPILE.cc) -g -Wall -D_MO_DEBUG -D_MO_EF_EXPORTS -D_MO_FLASCC -D_MO_FLASH -D_MO_X86 -I../../../Studio/SDK/flascc_1.0.0/sdk/usr/include -I../../Library/MoLzma -I../../Library/MoJpeg -I../../Common/MoCommon -I../MoEngine -o ${OBJECTDIR}/FEfKeybord.o FEfKeybord.cpp

${OBJECTDIR}/FEfRenderDevice.o: FEfRenderDevice.cpp 
	${MKDIR} -p ${OBJECTDIR}
	$(COMPILE.cc) -g -Wall -D_MO_DEBUG -D_MO_EF_EXPORTS -D_MO_FLASCC -D_MO_FLASH -D_MO_X86 -I../../../Studio/SDK/flascc_1.0.0/sdk/usr/include -I../../Library/MoLzma -I../../Library/MoJpeg -I../../Common/MoCommon -I../MoEngine -o ${OBJECTDIR}/FEfRenderDevice.o FEfRenderDevice.cpp

${OBJECTDIR}/FEfRenderProgrom.o: FEfRenderProgrom.cpp 
	${MKDIR} -p ${OBJECTDIR}
	$(COMPILE.cc) -g -Wall -D_MO_DEBUG -D_MO_EF_EXPORTS -D_MO_FLASCC -D_MO_FLASH -D_MO_X86 -I../../../Studio/SDK/flascc_1.0.0/sdk/usr/include -I../../Library/MoLzma -I../../Library/MoJpeg -I../../Common/MoCommon -I../MoEngine -o ${OBJECTDIR}/FEfRenderProgrom.o FEfRenderProgrom.cpp

${OBJECTDIR}/FEfStage.o: FEfStage.cpp 
	${MKDIR} -p ${OBJECTDIR}
	$(COMPILE.cc) -g -Wall -D_MO_DEBUG -D_MO_EF_EXPORTS -D_MO_FLASCC -D_MO_FLASH -D_MO_X86 -I../../../Studio/SDK/flascc_1.0.0/sdk/usr/include -I../../Library/MoLzma -I../../Library/MoJpeg -I../../Common/MoCommon -I../MoEngine -o ${OBJECTDIR}/FEfStage.o FEfStage.cpp

${OBJECTDIR}/MoEngineFlash.o: MoEngineFlash.cpp 
	${MKDIR} -p ${OBJECTDIR}
	$(COMPILE.cc) -g -Wall -D_MO_DEBUG -D_MO_EF_EXPORTS -D_MO_FLASCC -D_MO_FLASH -D_MO_X86 -I../../../Studio/SDK/flascc_1.0.0/sdk/usr/include -I../../Library/MoLzma -I../../Library/MoJpeg -I../../Common/MoCommon -I../MoEngine -o ${OBJECTDIR}/MoEngineFlash.o MoEngineFlash.cpp

# Subprojects
.build-subprojects:
	cd ../../Common/MoCommon && ${MAKE}  -f Makefile CONF=FlasCC-Debug
	cd ../../Library/MoJpeg && ${MAKE}  -f Makefile CONF=FlasCC-Debug
	cd ../../Library/MoLzma && ${MAKE}  -f Makefile CONF=FlasCC-Debug

# Clean Targets
.clean-conf: ${CLEAN_SUBPROJECTS}
	${RM} -r ${CND_BUILDDIR}/${CND_CONF}
	${RM} ${CND_DISTDIR}/${CND_CONF}/${CND_PLATFORM}/libmoengineflash.a

# Subprojects
.clean-subprojects:
	cd ../../Common/MoCommon && ${MAKE}  -f Makefile CONF=FlasCC-Debug clean
	cd ../../Library/MoJpeg && ${MAKE}  -f Makefile CONF=FlasCC-Debug clean
	cd ../../Library/MoLzma && ${MAKE}  -f Makefile CONF=FlasCC-Debug clean
