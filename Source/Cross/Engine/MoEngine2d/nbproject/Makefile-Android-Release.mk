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
CND_PLATFORM=arm-linux-androideabi-4.7-Windows
CND_DLIB_EXT=dll
CND_CONF=Android-Release
CND_DISTDIR=dist
CND_BUILDDIR=build

# Include project Makefile
include Makefile

# Object Directory
OBJECTDIR=${CND_BUILDDIR}/${CND_CONF}/${CND_PLATFORM}

# Object Files
OBJECTFILES= \
	${OBJECTDIR}/FAnimationClip.o \
	${OBJECTDIR}/FAnimationFrame.o \
	${OBJECTDIR}/FAnimationResource.o \
	${OBJECTDIR}/FBitmapBlockIndexDecoder.o \
	${OBJECTDIR}/FBitmapDecoder.o \
	${OBJECTDIR}/FBitmapIndexDecoder.o \
	${OBJECTDIR}/FBitmapJpegLayer1Decoder.o \
	${OBJECTDIR}/FBitmapJpegLayer2Decoder.o \
	${OBJECTDIR}/FCompressResource.o \
	${OBJECTDIR}/FDecoder.o \
	${OBJECTDIR}/FDecoderConsole.o \
	${OBJECTDIR}/FDecoderWorker.o \
	${OBJECTDIR}/FEgParticle.o \
	${OBJECTDIR}/FEgParticleConsole.o \
	${OBJECTDIR}/FEgParticleController.o \
	${OBJECTDIR}/FEgParticleMove.o \
	${OBJECTDIR}/FEgStage.o \
	${OBJECTDIR}/FEnvironment.o \
	${OBJECTDIR}/FEnvironmentConsole.o \
	${OBJECTDIR}/FFileLoader.o \
	${OBJECTDIR}/FGmMap.o \
	${OBJECTDIR}/FGmMapCell.o \
	${OBJECTDIR}/FGmMapConsole.o \
	${OBJECTDIR}/FGmMapData.o \
	${OBJECTDIR}/FGmMapFinding.o \
	${OBJECTDIR}/FGmMapLoader.o \
	${OBJECTDIR}/FGmPathController.o \
	${OBJECTDIR}/FGmPathNode.o \
	${OBJECTDIR}/FGroundResource.o \
	${OBJECTDIR}/FLoader.o \
	${OBJECTDIR}/FLoaderConsole.o \
	${OBJECTDIR}/FLoaderWorker.o \
	${OBJECTDIR}/FNetConsole.o \
	${OBJECTDIR}/FNetReceiveThread.o \
	${OBJECTDIR}/FNetSendThread.o \
	${OBJECTDIR}/FPictureResource.o \
	${OBJECTDIR}/FResource.o \
	${OBJECTDIR}/FResourceConsole.o \
	${OBJECTDIR}/FResourceGroup.o \
	${OBJECTDIR}/FResourceLoader.o \
	${OBJECTDIR}/FResourceWorker.o \
	${OBJECTDIR}/FWorker.o \
	${OBJECTDIR}/FWorkerConsole.o \
	${OBJECTDIR}/FWorkerThread.o \
	${OBJECTDIR}/MoEngine.o \
	${OBJECTDIR}/RBitmap.o \
	${OBJECTDIR}/RBitmapIndex.o \
	${OBJECTDIR}/RLzma.o


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
	"${MAKE}"  -f nbproject/Makefile-${CND_CONF}.mk ${CND_DISTDIR}/${CND_CONF}/${CND_PLATFORM}/libmoengine.a

${CND_DISTDIR}/${CND_CONF}/${CND_PLATFORM}/libmoengine.a: ${OBJECTFILES}
	${MKDIR} -p ${CND_DISTDIR}/${CND_CONF}/${CND_PLATFORM}
	${RM} ${CND_DISTDIR}/${CND_CONF}/${CND_PLATFORM}/libmoengine.a
	${AR} -rv ${CND_DISTDIR}/${CND_CONF}/${CND_PLATFORM}/libmoengine.a ${OBJECTFILES} 
	$(RANLIB) ${CND_DISTDIR}/${CND_CONF}/${CND_PLATFORM}/libmoengine.a

${OBJECTDIR}/FAnimationClip.o: FAnimationClip.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} $@.d
	$(COMPILE.cc) -g -D_MO_ANDROID -D_MO_DEBUG -I../../../Studio/SDK/android-ndk/platforms/android-14/arch-arm/usr/include -I../../Library/MoLzma -I../../Library/MoJpeg -I../../1-Common/MoCommon -I../../1-Common/MoNet -MMD -MP -MF $@.d -o ${OBJECTDIR}/FAnimationClip.o FAnimationClip.cpp

${OBJECTDIR}/FAnimationFrame.o: FAnimationFrame.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} $@.d
	$(COMPILE.cc) -g -D_MO_ANDROID -D_MO_DEBUG -I../../../Studio/SDK/android-ndk/platforms/android-14/arch-arm/usr/include -I../../Library/MoLzma -I../../Library/MoJpeg -I../../1-Common/MoCommon -I../../1-Common/MoNet -MMD -MP -MF $@.d -o ${OBJECTDIR}/FAnimationFrame.o FAnimationFrame.cpp

${OBJECTDIR}/FAnimationResource.o: FAnimationResource.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} $@.d
	$(COMPILE.cc) -g -D_MO_ANDROID -D_MO_DEBUG -I../../../Studio/SDK/android-ndk/platforms/android-14/arch-arm/usr/include -I../../Library/MoLzma -I../../Library/MoJpeg -I../../1-Common/MoCommon -I../../1-Common/MoNet -MMD -MP -MF $@.d -o ${OBJECTDIR}/FAnimationResource.o FAnimationResource.cpp

${OBJECTDIR}/FBitmapBlockIndexDecoder.o: FBitmapBlockIndexDecoder.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} $@.d
	$(COMPILE.cc) -g -D_MO_ANDROID -D_MO_DEBUG -I../../../Studio/SDK/android-ndk/platforms/android-14/arch-arm/usr/include -I../../Library/MoLzma -I../../Library/MoJpeg -I../../1-Common/MoCommon -I../../1-Common/MoNet -MMD -MP -MF $@.d -o ${OBJECTDIR}/FBitmapBlockIndexDecoder.o FBitmapBlockIndexDecoder.cpp

${OBJECTDIR}/FBitmapDecoder.o: FBitmapDecoder.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} $@.d
	$(COMPILE.cc) -g -D_MO_ANDROID -D_MO_DEBUG -I../../../Studio/SDK/android-ndk/platforms/android-14/arch-arm/usr/include -I../../Library/MoLzma -I../../Library/MoJpeg -I../../1-Common/MoCommon -I../../1-Common/MoNet -MMD -MP -MF $@.d -o ${OBJECTDIR}/FBitmapDecoder.o FBitmapDecoder.cpp

${OBJECTDIR}/FBitmapIndexDecoder.o: FBitmapIndexDecoder.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} $@.d
	$(COMPILE.cc) -g -D_MO_ANDROID -D_MO_DEBUG -I../../../Studio/SDK/android-ndk/platforms/android-14/arch-arm/usr/include -I../../Library/MoLzma -I../../Library/MoJpeg -I../../1-Common/MoCommon -I../../1-Common/MoNet -MMD -MP -MF $@.d -o ${OBJECTDIR}/FBitmapIndexDecoder.o FBitmapIndexDecoder.cpp

${OBJECTDIR}/FBitmapJpegLayer1Decoder.o: FBitmapJpegLayer1Decoder.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} $@.d
	$(COMPILE.cc) -g -D_MO_ANDROID -D_MO_DEBUG -I../../../Studio/SDK/android-ndk/platforms/android-14/arch-arm/usr/include -I../../Library/MoLzma -I../../Library/MoJpeg -I../../1-Common/MoCommon -I../../1-Common/MoNet -MMD -MP -MF $@.d -o ${OBJECTDIR}/FBitmapJpegLayer1Decoder.o FBitmapJpegLayer1Decoder.cpp

${OBJECTDIR}/FBitmapJpegLayer2Decoder.o: FBitmapJpegLayer2Decoder.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} $@.d
	$(COMPILE.cc) -g -D_MO_ANDROID -D_MO_DEBUG -I../../../Studio/SDK/android-ndk/platforms/android-14/arch-arm/usr/include -I../../Library/MoLzma -I../../Library/MoJpeg -I../../1-Common/MoCommon -I../../1-Common/MoNet -MMD -MP -MF $@.d -o ${OBJECTDIR}/FBitmapJpegLayer2Decoder.o FBitmapJpegLayer2Decoder.cpp

${OBJECTDIR}/FCompressResource.o: FCompressResource.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} $@.d
	$(COMPILE.cc) -g -D_MO_ANDROID -D_MO_DEBUG -I../../../Studio/SDK/android-ndk/platforms/android-14/arch-arm/usr/include -I../../Library/MoLzma -I../../Library/MoJpeg -I../../1-Common/MoCommon -I../../1-Common/MoNet -MMD -MP -MF $@.d -o ${OBJECTDIR}/FCompressResource.o FCompressResource.cpp

${OBJECTDIR}/FDecoder.o: FDecoder.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} $@.d
	$(COMPILE.cc) -g -D_MO_ANDROID -D_MO_DEBUG -I../../../Studio/SDK/android-ndk/platforms/android-14/arch-arm/usr/include -I../../Library/MoLzma -I../../Library/MoJpeg -I../../1-Common/MoCommon -I../../1-Common/MoNet -MMD -MP -MF $@.d -o ${OBJECTDIR}/FDecoder.o FDecoder.cpp

${OBJECTDIR}/FDecoderConsole.o: FDecoderConsole.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} $@.d
	$(COMPILE.cc) -g -D_MO_ANDROID -D_MO_DEBUG -I../../../Studio/SDK/android-ndk/platforms/android-14/arch-arm/usr/include -I../../Library/MoLzma -I../../Library/MoJpeg -I../../1-Common/MoCommon -I../../1-Common/MoNet -MMD -MP -MF $@.d -o ${OBJECTDIR}/FDecoderConsole.o FDecoderConsole.cpp

${OBJECTDIR}/FDecoderWorker.o: FDecoderWorker.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} $@.d
	$(COMPILE.cc) -g -D_MO_ANDROID -D_MO_DEBUG -I../../../Studio/SDK/android-ndk/platforms/android-14/arch-arm/usr/include -I../../Library/MoLzma -I../../Library/MoJpeg -I../../1-Common/MoCommon -I../../1-Common/MoNet -MMD -MP -MF $@.d -o ${OBJECTDIR}/FDecoderWorker.o FDecoderWorker.cpp

${OBJECTDIR}/FEgParticle.o: FEgParticle.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} $@.d
	$(COMPILE.cc) -g -D_MO_ANDROID -D_MO_DEBUG -I../../../Studio/SDK/android-ndk/platforms/android-14/arch-arm/usr/include -I../../Library/MoLzma -I../../Library/MoJpeg -I../../1-Common/MoCommon -I../../1-Common/MoNet -MMD -MP -MF $@.d -o ${OBJECTDIR}/FEgParticle.o FEgParticle.cpp

${OBJECTDIR}/FEgParticleConsole.o: FEgParticleConsole.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} $@.d
	$(COMPILE.cc) -g -D_MO_ANDROID -D_MO_DEBUG -I../../../Studio/SDK/android-ndk/platforms/android-14/arch-arm/usr/include -I../../Library/MoLzma -I../../Library/MoJpeg -I../../1-Common/MoCommon -I../../1-Common/MoNet -MMD -MP -MF $@.d -o ${OBJECTDIR}/FEgParticleConsole.o FEgParticleConsole.cpp

${OBJECTDIR}/FEgParticleController.o: FEgParticleController.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} $@.d
	$(COMPILE.cc) -g -D_MO_ANDROID -D_MO_DEBUG -I../../../Studio/SDK/android-ndk/platforms/android-14/arch-arm/usr/include -I../../Library/MoLzma -I../../Library/MoJpeg -I../../1-Common/MoCommon -I../../1-Common/MoNet -MMD -MP -MF $@.d -o ${OBJECTDIR}/FEgParticleController.o FEgParticleController.cpp

${OBJECTDIR}/FEgParticleMove.o: FEgParticleMove.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} $@.d
	$(COMPILE.cc) -g -D_MO_ANDROID -D_MO_DEBUG -I../../../Studio/SDK/android-ndk/platforms/android-14/arch-arm/usr/include -I../../Library/MoLzma -I../../Library/MoJpeg -I../../1-Common/MoCommon -I../../1-Common/MoNet -MMD -MP -MF $@.d -o ${OBJECTDIR}/FEgParticleMove.o FEgParticleMove.cpp

${OBJECTDIR}/FEgStage.o: FEgStage.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} $@.d
	$(COMPILE.cc) -g -D_MO_ANDROID -D_MO_DEBUG -I../../../Studio/SDK/android-ndk/platforms/android-14/arch-arm/usr/include -I../../Library/MoLzma -I../../Library/MoJpeg -I../../1-Common/MoCommon -I../../1-Common/MoNet -MMD -MP -MF $@.d -o ${OBJECTDIR}/FEgStage.o FEgStage.cpp

${OBJECTDIR}/FEnvironment.o: FEnvironment.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} $@.d
	$(COMPILE.cc) -g -D_MO_ANDROID -D_MO_DEBUG -I../../../Studio/SDK/android-ndk/platforms/android-14/arch-arm/usr/include -I../../Library/MoLzma -I../../Library/MoJpeg -I../../1-Common/MoCommon -I../../1-Common/MoNet -MMD -MP -MF $@.d -o ${OBJECTDIR}/FEnvironment.o FEnvironment.cpp

${OBJECTDIR}/FEnvironmentConsole.o: FEnvironmentConsole.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} $@.d
	$(COMPILE.cc) -g -D_MO_ANDROID -D_MO_DEBUG -I../../../Studio/SDK/android-ndk/platforms/android-14/arch-arm/usr/include -I../../Library/MoLzma -I../../Library/MoJpeg -I../../1-Common/MoCommon -I../../1-Common/MoNet -MMD -MP -MF $@.d -o ${OBJECTDIR}/FEnvironmentConsole.o FEnvironmentConsole.cpp

${OBJECTDIR}/FFileLoader.o: FFileLoader.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} $@.d
	$(COMPILE.cc) -g -D_MO_ANDROID -D_MO_DEBUG -I../../../Studio/SDK/android-ndk/platforms/android-14/arch-arm/usr/include -I../../Library/MoLzma -I../../Library/MoJpeg -I../../1-Common/MoCommon -I../../1-Common/MoNet -MMD -MP -MF $@.d -o ${OBJECTDIR}/FFileLoader.o FFileLoader.cpp

${OBJECTDIR}/FGmMap.o: FGmMap.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} $@.d
	$(COMPILE.cc) -g -D_MO_ANDROID -D_MO_DEBUG -I../../../Studio/SDK/android-ndk/platforms/android-14/arch-arm/usr/include -I../../Library/MoLzma -I../../Library/MoJpeg -I../../1-Common/MoCommon -I../../1-Common/MoNet -MMD -MP -MF $@.d -o ${OBJECTDIR}/FGmMap.o FGmMap.cpp

${OBJECTDIR}/FGmMapCell.o: FGmMapCell.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} $@.d
	$(COMPILE.cc) -g -D_MO_ANDROID -D_MO_DEBUG -I../../../Studio/SDK/android-ndk/platforms/android-14/arch-arm/usr/include -I../../Library/MoLzma -I../../Library/MoJpeg -I../../1-Common/MoCommon -I../../1-Common/MoNet -MMD -MP -MF $@.d -o ${OBJECTDIR}/FGmMapCell.o FGmMapCell.cpp

${OBJECTDIR}/FGmMapConsole.o: FGmMapConsole.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} $@.d
	$(COMPILE.cc) -g -D_MO_ANDROID -D_MO_DEBUG -I../../../Studio/SDK/android-ndk/platforms/android-14/arch-arm/usr/include -I../../Library/MoLzma -I../../Library/MoJpeg -I../../1-Common/MoCommon -I../../1-Common/MoNet -MMD -MP -MF $@.d -o ${OBJECTDIR}/FGmMapConsole.o FGmMapConsole.cpp

${OBJECTDIR}/FGmMapData.o: FGmMapData.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} $@.d
	$(COMPILE.cc) -g -D_MO_ANDROID -D_MO_DEBUG -I../../../Studio/SDK/android-ndk/platforms/android-14/arch-arm/usr/include -I../../Library/MoLzma -I../../Library/MoJpeg -I../../1-Common/MoCommon -I../../1-Common/MoNet -MMD -MP -MF $@.d -o ${OBJECTDIR}/FGmMapData.o FGmMapData.cpp

${OBJECTDIR}/FGmMapFinding.o: FGmMapFinding.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} $@.d
	$(COMPILE.cc) -g -D_MO_ANDROID -D_MO_DEBUG -I../../../Studio/SDK/android-ndk/platforms/android-14/arch-arm/usr/include -I../../Library/MoLzma -I../../Library/MoJpeg -I../../1-Common/MoCommon -I../../1-Common/MoNet -MMD -MP -MF $@.d -o ${OBJECTDIR}/FGmMapFinding.o FGmMapFinding.cpp

${OBJECTDIR}/FGmMapLoader.o: FGmMapLoader.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} $@.d
	$(COMPILE.cc) -g -D_MO_ANDROID -D_MO_DEBUG -I../../../Studio/SDK/android-ndk/platforms/android-14/arch-arm/usr/include -I../../Library/MoLzma -I../../Library/MoJpeg -I../../1-Common/MoCommon -I../../1-Common/MoNet -MMD -MP -MF $@.d -o ${OBJECTDIR}/FGmMapLoader.o FGmMapLoader.cpp

${OBJECTDIR}/FGmPathController.o: FGmPathController.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} $@.d
	$(COMPILE.cc) -g -D_MO_ANDROID -D_MO_DEBUG -I../../../Studio/SDK/android-ndk/platforms/android-14/arch-arm/usr/include -I../../Library/MoLzma -I../../Library/MoJpeg -I../../1-Common/MoCommon -I../../1-Common/MoNet -MMD -MP -MF $@.d -o ${OBJECTDIR}/FGmPathController.o FGmPathController.cpp

${OBJECTDIR}/FGmPathNode.o: FGmPathNode.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} $@.d
	$(COMPILE.cc) -g -D_MO_ANDROID -D_MO_DEBUG -I../../../Studio/SDK/android-ndk/platforms/android-14/arch-arm/usr/include -I../../Library/MoLzma -I../../Library/MoJpeg -I../../1-Common/MoCommon -I../../1-Common/MoNet -MMD -MP -MF $@.d -o ${OBJECTDIR}/FGmPathNode.o FGmPathNode.cpp

${OBJECTDIR}/FGroundResource.o: FGroundResource.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} $@.d
	$(COMPILE.cc) -g -D_MO_ANDROID -D_MO_DEBUG -I../../../Studio/SDK/android-ndk/platforms/android-14/arch-arm/usr/include -I../../Library/MoLzma -I../../Library/MoJpeg -I../../1-Common/MoCommon -I../../1-Common/MoNet -MMD -MP -MF $@.d -o ${OBJECTDIR}/FGroundResource.o FGroundResource.cpp

${OBJECTDIR}/FLoader.o: FLoader.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} $@.d
	$(COMPILE.cc) -g -D_MO_ANDROID -D_MO_DEBUG -I../../../Studio/SDK/android-ndk/platforms/android-14/arch-arm/usr/include -I../../Library/MoLzma -I../../Library/MoJpeg -I../../1-Common/MoCommon -I../../1-Common/MoNet -MMD -MP -MF $@.d -o ${OBJECTDIR}/FLoader.o FLoader.cpp

${OBJECTDIR}/FLoaderConsole.o: FLoaderConsole.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} $@.d
	$(COMPILE.cc) -g -D_MO_ANDROID -D_MO_DEBUG -I../../../Studio/SDK/android-ndk/platforms/android-14/arch-arm/usr/include -I../../Library/MoLzma -I../../Library/MoJpeg -I../../1-Common/MoCommon -I../../1-Common/MoNet -MMD -MP -MF $@.d -o ${OBJECTDIR}/FLoaderConsole.o FLoaderConsole.cpp

${OBJECTDIR}/FLoaderWorker.o: FLoaderWorker.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} $@.d
	$(COMPILE.cc) -g -D_MO_ANDROID -D_MO_DEBUG -I../../../Studio/SDK/android-ndk/platforms/android-14/arch-arm/usr/include -I../../Library/MoLzma -I../../Library/MoJpeg -I../../1-Common/MoCommon -I../../1-Common/MoNet -MMD -MP -MF $@.d -o ${OBJECTDIR}/FLoaderWorker.o FLoaderWorker.cpp

${OBJECTDIR}/FNetConsole.o: FNetConsole.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} $@.d
	$(COMPILE.cc) -g -D_MO_ANDROID -D_MO_DEBUG -I../../../Studio/SDK/android-ndk/platforms/android-14/arch-arm/usr/include -I../../Library/MoLzma -I../../Library/MoJpeg -I../../1-Common/MoCommon -I../../1-Common/MoNet -MMD -MP -MF $@.d -o ${OBJECTDIR}/FNetConsole.o FNetConsole.cpp

${OBJECTDIR}/FNetReceiveThread.o: FNetReceiveThread.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} $@.d
	$(COMPILE.cc) -g -D_MO_ANDROID -D_MO_DEBUG -I../../../Studio/SDK/android-ndk/platforms/android-14/arch-arm/usr/include -I../../Library/MoLzma -I../../Library/MoJpeg -I../../1-Common/MoCommon -I../../1-Common/MoNet -MMD -MP -MF $@.d -o ${OBJECTDIR}/FNetReceiveThread.o FNetReceiveThread.cpp

${OBJECTDIR}/FNetSendThread.o: FNetSendThread.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} $@.d
	$(COMPILE.cc) -g -D_MO_ANDROID -D_MO_DEBUG -I../../../Studio/SDK/android-ndk/platforms/android-14/arch-arm/usr/include -I../../Library/MoLzma -I../../Library/MoJpeg -I../../1-Common/MoCommon -I../../1-Common/MoNet -MMD -MP -MF $@.d -o ${OBJECTDIR}/FNetSendThread.o FNetSendThread.cpp

${OBJECTDIR}/FPictureResource.o: FPictureResource.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} $@.d
	$(COMPILE.cc) -g -D_MO_ANDROID -D_MO_DEBUG -I../../../Studio/SDK/android-ndk/platforms/android-14/arch-arm/usr/include -I../../Library/MoLzma -I../../Library/MoJpeg -I../../1-Common/MoCommon -I../../1-Common/MoNet -MMD -MP -MF $@.d -o ${OBJECTDIR}/FPictureResource.o FPictureResource.cpp

${OBJECTDIR}/FResource.o: FResource.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} $@.d
	$(COMPILE.cc) -g -D_MO_ANDROID -D_MO_DEBUG -I../../../Studio/SDK/android-ndk/platforms/android-14/arch-arm/usr/include -I../../Library/MoLzma -I../../Library/MoJpeg -I../../1-Common/MoCommon -I../../1-Common/MoNet -MMD -MP -MF $@.d -o ${OBJECTDIR}/FResource.o FResource.cpp

${OBJECTDIR}/FResourceConsole.o: FResourceConsole.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} $@.d
	$(COMPILE.cc) -g -D_MO_ANDROID -D_MO_DEBUG -I../../../Studio/SDK/android-ndk/platforms/android-14/arch-arm/usr/include -I../../Library/MoLzma -I../../Library/MoJpeg -I../../1-Common/MoCommon -I../../1-Common/MoNet -MMD -MP -MF $@.d -o ${OBJECTDIR}/FResourceConsole.o FResourceConsole.cpp

${OBJECTDIR}/FResourceGroup.o: FResourceGroup.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} $@.d
	$(COMPILE.cc) -g -D_MO_ANDROID -D_MO_DEBUG -I../../../Studio/SDK/android-ndk/platforms/android-14/arch-arm/usr/include -I../../Library/MoLzma -I../../Library/MoJpeg -I../../1-Common/MoCommon -I../../1-Common/MoNet -MMD -MP -MF $@.d -o ${OBJECTDIR}/FResourceGroup.o FResourceGroup.cpp

${OBJECTDIR}/FResourceLoader.o: FResourceLoader.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} $@.d
	$(COMPILE.cc) -g -D_MO_ANDROID -D_MO_DEBUG -I../../../Studio/SDK/android-ndk/platforms/android-14/arch-arm/usr/include -I../../Library/MoLzma -I../../Library/MoJpeg -I../../1-Common/MoCommon -I../../1-Common/MoNet -MMD -MP -MF $@.d -o ${OBJECTDIR}/FResourceLoader.o FResourceLoader.cpp

${OBJECTDIR}/FResourceWorker.o: FResourceWorker.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} $@.d
	$(COMPILE.cc) -g -D_MO_ANDROID -D_MO_DEBUG -I../../../Studio/SDK/android-ndk/platforms/android-14/arch-arm/usr/include -I../../Library/MoLzma -I../../Library/MoJpeg -I../../1-Common/MoCommon -I../../1-Common/MoNet -MMD -MP -MF $@.d -o ${OBJECTDIR}/FResourceWorker.o FResourceWorker.cpp

${OBJECTDIR}/FWorker.o: FWorker.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} $@.d
	$(COMPILE.cc) -g -D_MO_ANDROID -D_MO_DEBUG -I../../../Studio/SDK/android-ndk/platforms/android-14/arch-arm/usr/include -I../../Library/MoLzma -I../../Library/MoJpeg -I../../1-Common/MoCommon -I../../1-Common/MoNet -MMD -MP -MF $@.d -o ${OBJECTDIR}/FWorker.o FWorker.cpp

${OBJECTDIR}/FWorkerConsole.o: FWorkerConsole.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} $@.d
	$(COMPILE.cc) -g -D_MO_ANDROID -D_MO_DEBUG -I../../../Studio/SDK/android-ndk/platforms/android-14/arch-arm/usr/include -I../../Library/MoLzma -I../../Library/MoJpeg -I../../1-Common/MoCommon -I../../1-Common/MoNet -MMD -MP -MF $@.d -o ${OBJECTDIR}/FWorkerConsole.o FWorkerConsole.cpp

${OBJECTDIR}/FWorkerThread.o: FWorkerThread.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} $@.d
	$(COMPILE.cc) -g -D_MO_ANDROID -D_MO_DEBUG -I../../../Studio/SDK/android-ndk/platforms/android-14/arch-arm/usr/include -I../../Library/MoLzma -I../../Library/MoJpeg -I../../1-Common/MoCommon -I../../1-Common/MoNet -MMD -MP -MF $@.d -o ${OBJECTDIR}/FWorkerThread.o FWorkerThread.cpp

${OBJECTDIR}/MoEngine.o: MoEngine.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} $@.d
	$(COMPILE.cc) -g -D_MO_ANDROID -D_MO_DEBUG -I../../../Studio/SDK/android-ndk/platforms/android-14/arch-arm/usr/include -I../../Library/MoLzma -I../../Library/MoJpeg -I../../1-Common/MoCommon -I../../1-Common/MoNet -MMD -MP -MF $@.d -o ${OBJECTDIR}/MoEngine.o MoEngine.cpp

${OBJECTDIR}/RBitmap.o: RBitmap.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} $@.d
	$(COMPILE.cc) -g -D_MO_ANDROID -D_MO_DEBUG -I../../../Studio/SDK/android-ndk/platforms/android-14/arch-arm/usr/include -I../../Library/MoLzma -I../../Library/MoJpeg -I../../1-Common/MoCommon -I../../1-Common/MoNet -MMD -MP -MF $@.d -o ${OBJECTDIR}/RBitmap.o RBitmap.cpp

${OBJECTDIR}/RBitmapIndex.o: RBitmapIndex.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} $@.d
	$(COMPILE.cc) -g -D_MO_ANDROID -D_MO_DEBUG -I../../../Studio/SDK/android-ndk/platforms/android-14/arch-arm/usr/include -I../../Library/MoLzma -I../../Library/MoJpeg -I../../1-Common/MoCommon -I../../1-Common/MoNet -MMD -MP -MF $@.d -o ${OBJECTDIR}/RBitmapIndex.o RBitmapIndex.cpp

${OBJECTDIR}/RLzma.o: RLzma.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} $@.d
	$(COMPILE.cc) -g -D_MO_ANDROID -D_MO_DEBUG -I../../../Studio/SDK/android-ndk/platforms/android-14/arch-arm/usr/include -I../../Library/MoLzma -I../../Library/MoJpeg -I../../1-Common/MoCommon -I../../1-Common/MoNet -MMD -MP -MF $@.d -o ${OBJECTDIR}/RLzma.o RLzma.cpp

# Subprojects
.build-subprojects:

# Clean Targets
.clean-conf: ${CLEAN_SUBPROJECTS}
	${RM} -r ${CND_BUILDDIR}/${CND_CONF}
	${RM} ${CND_DISTDIR}/${CND_CONF}/${CND_PLATFORM}/libmoengine.a

# Subprojects
.clean-subprojects:

# Enable dependency checking
.dep.inc: .depcheck-impl

include .dep.inc
