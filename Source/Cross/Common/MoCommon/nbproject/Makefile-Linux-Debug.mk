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
CND_PLATFORM=Cygwin-x86-Windows
CND_DLIB_EXT=dll
CND_CONF=Linux-Debug
CND_DISTDIR=dist
CND_BUILDDIR=build

# Include project Makefile
include Makefile

# Object Directory
OBJECTDIR=${CND_BUILDDIR}/${CND_CONF}/${CND_PLATFORM}

# Object Files
OBJECTFILES= \
	${OBJECTDIR}/FAllocatorStorage.o \
	${OBJECTDIR}/FApplication.o \
	${OBJECTDIR}/FApplicationListener.o \
	${OBJECTDIR}/FBase.o \
	${OBJECTDIR}/FBlockAllocator.o \
	${OBJECTDIR}/FBufferedPipe.o \
	${OBJECTDIR}/FBufferedPipeBlock.o \
	${OBJECTDIR}/FBufferedQueue.o \
	${OBJECTDIR}/FBufferedQueueBlock.o \
	${OBJECTDIR}/FByteFile.o \
	${OBJECTDIR}/FByteFileStream.o \
	${OBJECTDIR}/FByteStream.o \
	${OBJECTDIR}/FCatcher.o \
	${OBJECTDIR}/FCatcherConsole.o \
	${OBJECTDIR}/FConsole.o \
	${OBJECTDIR}/FDataset.o \
	${OBJECTDIR}/FDatasetMeta.o \
	${OBJECTDIR}/FField.o \
	${OBJECTDIR}/FFileLines.o \
	${OBJECTDIR}/FFileMapping.o \
	${OBJECTDIR}/FFileStream.o \
	${OBJECTDIR}/FFileString16.o \
	${OBJECTDIR}/FFileString32.o \
	${OBJECTDIR}/FFileString8.o \
	${OBJECTDIR}/FFileStringStream.o \
	${OBJECTDIR}/FFree.o \
	${OBJECTDIR}/FListener.o \
	${OBJECTDIR}/FLoggerConsole.o \
	${OBJECTDIR}/FLoggerThread.o \
	${OBJECTDIR}/FLoggerWriter.o \
	${OBJECTDIR}/FMemoryAllocator.o \
	${OBJECTDIR}/FMemoryLockAllocator.o \
	${OBJECTDIR}/FMonitor.o \
	${OBJECTDIR}/FMonitorCatcher.o \
	${OBJECTDIR}/FMonitorConsole.o \
	${OBJECTDIR}/FMonitorMachine.o \
	${OBJECTDIR}/FMonitorThread.o \
	${OBJECTDIR}/FMonitorTrigger.o \
	${OBJECTDIR}/FNetClientSocket.o \
	${OBJECTDIR}/FNetServerSocket.o \
	${OBJECTDIR}/FNetSocket.o \
	${OBJECTDIR}/FNetSocketStorage.o \
	${OBJECTDIR}/FNetUdpClientSocket.o \
	${OBJECTDIR}/FNetUdpServerSocket.o \
	${OBJECTDIR}/FObject.o \
	${OBJECTDIR}/FPack.o \
	${OBJECTDIR}/FPipe.o \
	${OBJECTDIR}/FQueue.o \
	${OBJECTDIR}/FRow.o \
	${OBJECTDIR}/FShareMemory.o \
	${OBJECTDIR}/FSharedConsole.o \
	${OBJECTDIR}/FSharedGroup.o \
	${OBJECTDIR}/FSharedListAllocator.o \
	${OBJECTDIR}/FSharedPipe.o \
	${OBJECTDIR}/FSharedQueue.o \
	${OBJECTDIR}/FSharedQueueConnection.o \
	${OBJECTDIR}/FSingletonConsole.o \
	${OBJECTDIR}/FSql.o \
	${OBJECTDIR}/FSqlConnection.o \
	${OBJECTDIR}/FSqlPool.o \
	${OBJECTDIR}/FStreamBlock.o \
	${OBJECTDIR}/FStreamBlockAllocator.o \
	${OBJECTDIR}/FStreamBlockPool.o \
	${OBJECTDIR}/FString16.o \
	${OBJECTDIR}/FString32.o \
	${OBJECTDIR}/FString8.o \
	${OBJECTDIR}/FStringBuffer16.o \
	${OBJECTDIR}/FStringBuffer32.o \
	${OBJECTDIR}/FStringBuffer8.o \
	${OBJECTDIR}/FThread.o \
	${OBJECTDIR}/FThreadCatcher.o \
	${OBJECTDIR}/FThreadConsole.o \
	${OBJECTDIR}/FThreadGroup.o \
	${OBJECTDIR}/FThreadMemory.o \
	${OBJECTDIR}/FThreadPool.o \
	${OBJECTDIR}/FThreadRunable.o \
	${OBJECTDIR}/FThreadRunablePool.o \
	${OBJECTDIR}/FThreadRunableWorker.o \
	${OBJECTDIR}/FThreadTrap.o \
	${OBJECTDIR}/FThreadWorker.o \
	${OBJECTDIR}/FTrap.o \
	${OBJECTDIR}/FXmlDocument.o \
	${OBJECTDIR}/FXmlNode.o \
	${OBJECTDIR}/FXmlNodes.o \
	${OBJECTDIR}/MApplicationListeners.o \
	${OBJECTDIR}/MListeners.o \
	${OBJECTDIR}/MNetSocket.o \
	${OBJECTDIR}/MPack.o \
	${OBJECTDIR}/MPipe.o \
	${OBJECTDIR}/MProperties16.o \
	${OBJECTDIR}/MProperties32.o \
	${OBJECTDIR}/MProperties8.o \
	${OBJECTDIR}/MShared.o \
	${OBJECTDIR}/MSingleton.o \
	${OBJECTDIR}/MSql.o \
	${OBJECTDIR}/MString16.o \
	${OBJECTDIR}/MString16s.o \
	${OBJECTDIR}/MString32.o \
	${OBJECTDIR}/MString32s.o \
	${OBJECTDIR}/MString8.o \
	${OBJECTDIR}/MString8s.o \
	${OBJECTDIR}/MoCommon.o \
	${OBJECTDIR}/RActivator.o \
	${OBJECTDIR}/RAllocator.o \
	${OBJECTDIR}/RApplication.o \
	${OBJECTDIR}/RBool.o \
	${OBJECTDIR}/RByte.o \
	${OBJECTDIR}/RChar16.o \
	${OBJECTDIR}/RChar32.o \
	${OBJECTDIR}/RChar8.o \
	${OBJECTDIR}/RCharEncoding.o \
	${OBJECTDIR}/RCharSet.o \
	${OBJECTDIR}/RCompress.o \
	${OBJECTDIR}/RCpu.o \
	${OBJECTDIR}/RDateTime.o \
	${OBJECTDIR}/RDouble.o \
	${OBJECTDIR}/REnvironment.o \
	${OBJECTDIR}/RFile.o \
	${OBJECTDIR}/RFloat.o \
	${OBJECTDIR}/RGuid.o \
	${OBJECTDIR}/RInput.o \
	${OBJECTDIR}/RInt.o \
	${OBJECTDIR}/RInt16.o \
	${OBJECTDIR}/RInt32.o \
	${OBJECTDIR}/RInt64.o \
	${OBJECTDIR}/RInt8.o \
	${OBJECTDIR}/RLogger.o \
	${OBJECTDIR}/RMemory.o \
	${OBJECTDIR}/RMemoryCapacity.o \
	${OBJECTDIR}/RNetHost.o \
	${OBJECTDIR}/RNetSocket.o \
	${OBJECTDIR}/ROutput.o \
	${OBJECTDIR}/RProcess.o \
	${OBJECTDIR}/RRandom.o \
	${OBJECTDIR}/RShareMemory.o \
	${OBJECTDIR}/RSingletonManager.o \
	${OBJECTDIR}/RString16.o \
	${OBJECTDIR}/RString32.o \
	${OBJECTDIR}/RString8.o \
	${OBJECTDIR}/RSystem.o \
	${OBJECTDIR}/RThread.o \
	${OBJECTDIR}/RTimeTick.o \
	${OBJECTDIR}/RTrap.o \
	${OBJECTDIR}/RTypeConverter.o \
	${OBJECTDIR}/RUint.o \
	${OBJECTDIR}/RUint16.o \
	${OBJECTDIR}/RUint32.o \
	${OBJECTDIR}/RUint64.o \
	${OBJECTDIR}/RUint8.o \
	${OBJECTDIR}/RXml.o \
	${OBJECTDIR}/SDoublePoint2.o \
	${OBJECTDIR}/SDoublePoint3.o \
	${OBJECTDIR}/SDoublePoint4.o \
	${OBJECTDIR}/SDoubleRange.o \
	${OBJECTDIR}/SDoubleSize2.o \
	${OBJECTDIR}/SDoubleSize3.o \
	${OBJECTDIR}/SDoubleVector2.o \
	${OBJECTDIR}/SDoubleVector3.o \
	${OBJECTDIR}/SDoubleVector4.o \
	${OBJECTDIR}/SFloatPoint2.o \
	${OBJECTDIR}/SFloatPoint3.o \
	${OBJECTDIR}/SFloatPoint4.o \
	${OBJECTDIR}/SFloatRange.o \
	${OBJECTDIR}/SFloatSize2.o \
	${OBJECTDIR}/SFloatSize3.o \
	${OBJECTDIR}/SFloatVector2.o \
	${OBJECTDIR}/SFloatVector3.o \
	${OBJECTDIR}/SFloatVector4.o \
	${OBJECTDIR}/SIntPoint2.o \
	${OBJECTDIR}/SIntPoint3.o \
	${OBJECTDIR}/SIntPoint4.o \
	${OBJECTDIR}/SIntRange.o \
	${OBJECTDIR}/SIntSize2.o \
	${OBJECTDIR}/SIntSize3.o \
	${OBJECTDIR}/SIntVector2.o \
	${OBJECTDIR}/SIntVector3.o \
	${OBJECTDIR}/SIntVector4.o \
	${OBJECTDIR}/SMemoryEntry.o \
	${OBJECTDIR}/TClassInfo.o \
	${OBJECTDIR}/TDataInput.o \
	${OBJECTDIR}/TDataOutput.o \
	${OBJECTDIR}/TDataReader.o \
	${OBJECTDIR}/TDataWriter.o \
	${OBJECTDIR}/TFileFinder.o \
	${OBJECTDIR}/TFileInfo.o \
	${OBJECTDIR}/TFsDateTime.o \
	${OBJECTDIR}/TFsDouble.o \
	${OBJECTDIR}/TFsFloat.o \
	${OBJECTDIR}/TFsFloatPoint3.o \
	${OBJECTDIR}/TFsFloatRange.o \
	${OBJECTDIR}/TFsFloatSize.o \
	${OBJECTDIR}/TFsFloatVector3.o \
	${OBJECTDIR}/TFsIntPoint2.o \
	${OBJECTDIR}/TFsIntPoint3.o \
	${OBJECTDIR}/TFsIntSize.o \
	${OBJECTDIR}/TFsInteger.o \
	${OBJECTDIR}/TFsInteger64.o \
	${OBJECTDIR}/TLinkInput.o \
	${OBJECTDIR}/TLinkOutput.o \
	${OBJECTDIR}/TProperty.o \
	${OBJECTDIR}/TShareSegment.o \
	${OBJECTDIR}/TSharedList.o \
	${OBJECTDIR}/TSharedListIterator.o \
	${OBJECTDIR}/TSpeedTest.o \
	${OBJECTDIR}/TString16.o \
	${OBJECTDIR}/TString32.o \
	${OBJECTDIR}/TString8.o \
	${OBJECTDIR}/TStringBuffer16.o \
	${OBJECTDIR}/TStringBuffer32.o \
	${OBJECTDIR}/TStringBuffer8.o \
	${OBJECTDIR}/TThreadCondition.o \
	${OBJECTDIR}/TThreadMutex.o \
	${OBJECTDIR}/TThreadSection.o \
	${OBJECTDIR}/TTrapper.o \
	${OBJECTDIR}/TXmlNodeIteratorC.o


# C Compiler Flags
CFLAGS=

# CC Compiler Flags
CCFLAGS=-pg
CXXFLAGS=-pg

# Fortran Compiler Flags
FFLAGS=

# Assembler Flags
ASFLAGS=

# Link Libraries and Options
LDLIBSOPTIONS=

# Build Targets
.build-conf: ${BUILD_SUBPROJECTS}
	"${MAKE}"  -f nbproject/Makefile-${CND_CONF}.mk ${CND_DISTDIR}/${CND_CONF}/${CND_PLATFORM}/libmocommon.a

${CND_DISTDIR}/${CND_CONF}/${CND_PLATFORM}/libmocommon.a: ${OBJECTFILES}
	${MKDIR} -p ${CND_DISTDIR}/${CND_CONF}/${CND_PLATFORM}
	${RM} ${CND_DISTDIR}/${CND_CONF}/${CND_PLATFORM}/libmocommon.a
	${AR} -rv ${CND_DISTDIR}/${CND_CONF}/${CND_PLATFORM}/libmocommon.a ${OBJECTFILES} 
	$(RANLIB) ${CND_DISTDIR}/${CND_CONF}/${CND_PLATFORM}/libmocommon.a

${OBJECTDIR}/FAllocatorStorage.o: FAllocatorStorage.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FAllocatorStorage.o FAllocatorStorage.cpp

${OBJECTDIR}/FApplication.o: FApplication.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FApplication.o FApplication.cpp

${OBJECTDIR}/FApplicationListener.o: FApplicationListener.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FApplicationListener.o FApplicationListener.cpp

${OBJECTDIR}/FBase.o: FBase.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FBase.o FBase.cpp

${OBJECTDIR}/FBlockAllocator.o: FBlockAllocator.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FBlockAllocator.o FBlockAllocator.cpp

${OBJECTDIR}/FBufferedPipe.o: FBufferedPipe.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FBufferedPipe.o FBufferedPipe.cpp

${OBJECTDIR}/FBufferedPipeBlock.o: FBufferedPipeBlock.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FBufferedPipeBlock.o FBufferedPipeBlock.cpp

${OBJECTDIR}/FBufferedQueue.o: FBufferedQueue.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FBufferedQueue.o FBufferedQueue.cpp

${OBJECTDIR}/FBufferedQueueBlock.o: FBufferedQueueBlock.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FBufferedQueueBlock.o FBufferedQueueBlock.cpp

${OBJECTDIR}/FByteFile.o: FByteFile.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FByteFile.o FByteFile.cpp

${OBJECTDIR}/FByteFileStream.o: FByteFileStream.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FByteFileStream.o FByteFileStream.cpp

${OBJECTDIR}/FByteStream.o: FByteStream.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FByteStream.o FByteStream.cpp

${OBJECTDIR}/FCatcher.o: FCatcher.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FCatcher.o FCatcher.cpp

${OBJECTDIR}/FCatcherConsole.o: FCatcherConsole.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FCatcherConsole.o FCatcherConsole.cpp

${OBJECTDIR}/FConsole.o: FConsole.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FConsole.o FConsole.cpp

${OBJECTDIR}/FDataset.o: FDataset.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FDataset.o FDataset.cpp

${OBJECTDIR}/FDatasetMeta.o: FDatasetMeta.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FDatasetMeta.o FDatasetMeta.cpp

${OBJECTDIR}/FField.o: FField.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FField.o FField.cpp

${OBJECTDIR}/FFileLines.o: FFileLines.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FFileLines.o FFileLines.cpp

${OBJECTDIR}/FFileMapping.o: FFileMapping.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FFileMapping.o FFileMapping.cpp

${OBJECTDIR}/FFileStream.o: FFileStream.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FFileStream.o FFileStream.cpp

${OBJECTDIR}/FFileString16.o: FFileString16.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FFileString16.o FFileString16.cpp

${OBJECTDIR}/FFileString32.o: FFileString32.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FFileString32.o FFileString32.cpp

${OBJECTDIR}/FFileString8.o: FFileString8.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FFileString8.o FFileString8.cpp

${OBJECTDIR}/FFileStringStream.o: FFileStringStream.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FFileStringStream.o FFileStringStream.cpp

${OBJECTDIR}/FFree.o: FFree.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FFree.o FFree.cpp

${OBJECTDIR}/FListener.o: FListener.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FListener.o FListener.cpp

${OBJECTDIR}/FLoggerConsole.o: FLoggerConsole.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FLoggerConsole.o FLoggerConsole.cpp

${OBJECTDIR}/FLoggerThread.o: FLoggerThread.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FLoggerThread.o FLoggerThread.cpp

${OBJECTDIR}/FLoggerWriter.o: FLoggerWriter.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FLoggerWriter.o FLoggerWriter.cpp

${OBJECTDIR}/FMemoryAllocator.o: FMemoryAllocator.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FMemoryAllocator.o FMemoryAllocator.cpp

${OBJECTDIR}/FMemoryLockAllocator.o: FMemoryLockAllocator.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FMemoryLockAllocator.o FMemoryLockAllocator.cpp

${OBJECTDIR}/FMonitor.o: FMonitor.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FMonitor.o FMonitor.cpp

${OBJECTDIR}/FMonitorCatcher.o: FMonitorCatcher.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FMonitorCatcher.o FMonitorCatcher.cpp

${OBJECTDIR}/FMonitorConsole.o: FMonitorConsole.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FMonitorConsole.o FMonitorConsole.cpp

${OBJECTDIR}/FMonitorMachine.o: FMonitorMachine.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FMonitorMachine.o FMonitorMachine.cpp

${OBJECTDIR}/FMonitorThread.o: FMonitorThread.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FMonitorThread.o FMonitorThread.cpp

${OBJECTDIR}/FMonitorTrigger.o: FMonitorTrigger.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FMonitorTrigger.o FMonitorTrigger.cpp

${OBJECTDIR}/FNetClientSocket.o: FNetClientSocket.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FNetClientSocket.o FNetClientSocket.cpp

${OBJECTDIR}/FNetServerSocket.o: FNetServerSocket.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FNetServerSocket.o FNetServerSocket.cpp

${OBJECTDIR}/FNetSocket.o: FNetSocket.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FNetSocket.o FNetSocket.cpp

${OBJECTDIR}/FNetSocketStorage.o: FNetSocketStorage.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FNetSocketStorage.o FNetSocketStorage.cpp

${OBJECTDIR}/FNetUdpClientSocket.o: FNetUdpClientSocket.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FNetUdpClientSocket.o FNetUdpClientSocket.cpp

${OBJECTDIR}/FNetUdpServerSocket.o: FNetUdpServerSocket.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FNetUdpServerSocket.o FNetUdpServerSocket.cpp

${OBJECTDIR}/FObject.o: FObject.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FObject.o FObject.cpp

${OBJECTDIR}/FPack.o: FPack.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FPack.o FPack.cpp

${OBJECTDIR}/FPipe.o: FPipe.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FPipe.o FPipe.cpp

${OBJECTDIR}/FQueue.o: FQueue.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FQueue.o FQueue.cpp

${OBJECTDIR}/FRow.o: FRow.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FRow.o FRow.cpp

${OBJECTDIR}/FShareMemory.o: FShareMemory.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FShareMemory.o FShareMemory.cpp

${OBJECTDIR}/FSharedConsole.o: FSharedConsole.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FSharedConsole.o FSharedConsole.cpp

${OBJECTDIR}/FSharedGroup.o: FSharedGroup.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FSharedGroup.o FSharedGroup.cpp

${OBJECTDIR}/FSharedListAllocator.o: FSharedListAllocator.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FSharedListAllocator.o FSharedListAllocator.cpp

${OBJECTDIR}/FSharedPipe.o: FSharedPipe.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FSharedPipe.o FSharedPipe.cpp

${OBJECTDIR}/FSharedQueue.o: FSharedQueue.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FSharedQueue.o FSharedQueue.cpp

${OBJECTDIR}/FSharedQueueConnection.o: FSharedQueueConnection.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FSharedQueueConnection.o FSharedQueueConnection.cpp

${OBJECTDIR}/FSingletonConsole.o: FSingletonConsole.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FSingletonConsole.o FSingletonConsole.cpp

${OBJECTDIR}/FSql.o: FSql.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FSql.o FSql.cpp

${OBJECTDIR}/FSqlConnection.o: FSqlConnection.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FSqlConnection.o FSqlConnection.cpp

${OBJECTDIR}/FSqlPool.o: FSqlPool.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FSqlPool.o FSqlPool.cpp

${OBJECTDIR}/FStreamBlock.o: FStreamBlock.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FStreamBlock.o FStreamBlock.cpp

${OBJECTDIR}/FStreamBlockAllocator.o: FStreamBlockAllocator.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FStreamBlockAllocator.o FStreamBlockAllocator.cpp

${OBJECTDIR}/FStreamBlockPool.o: FStreamBlockPool.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FStreamBlockPool.o FStreamBlockPool.cpp

${OBJECTDIR}/FString16.o: FString16.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FString16.o FString16.cpp

${OBJECTDIR}/FString32.o: FString32.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FString32.o FString32.cpp

${OBJECTDIR}/FString8.o: FString8.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FString8.o FString8.cpp

${OBJECTDIR}/FStringBuffer16.o: FStringBuffer16.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FStringBuffer16.o FStringBuffer16.cpp

${OBJECTDIR}/FStringBuffer32.o: FStringBuffer32.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FStringBuffer32.o FStringBuffer32.cpp

${OBJECTDIR}/FStringBuffer8.o: FStringBuffer8.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FStringBuffer8.o FStringBuffer8.cpp

${OBJECTDIR}/FThread.o: FThread.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FThread.o FThread.cpp

${OBJECTDIR}/FThreadCatcher.o: FThreadCatcher.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FThreadCatcher.o FThreadCatcher.cpp

${OBJECTDIR}/FThreadConsole.o: FThreadConsole.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FThreadConsole.o FThreadConsole.cpp

${OBJECTDIR}/FThreadGroup.o: FThreadGroup.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FThreadGroup.o FThreadGroup.cpp

${OBJECTDIR}/FThreadMemory.o: FThreadMemory.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FThreadMemory.o FThreadMemory.cpp

${OBJECTDIR}/FThreadPool.o: FThreadPool.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FThreadPool.o FThreadPool.cpp

${OBJECTDIR}/FThreadRunable.o: FThreadRunable.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FThreadRunable.o FThreadRunable.cpp

${OBJECTDIR}/FThreadRunablePool.o: FThreadRunablePool.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FThreadRunablePool.o FThreadRunablePool.cpp

${OBJECTDIR}/FThreadRunableWorker.o: FThreadRunableWorker.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FThreadRunableWorker.o FThreadRunableWorker.cpp

${OBJECTDIR}/FThreadTrap.o: FThreadTrap.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FThreadTrap.o FThreadTrap.cpp

${OBJECTDIR}/FThreadWorker.o: FThreadWorker.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FThreadWorker.o FThreadWorker.cpp

${OBJECTDIR}/FTrap.o: FTrap.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FTrap.o FTrap.cpp

${OBJECTDIR}/FXmlDocument.o: FXmlDocument.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FXmlDocument.o FXmlDocument.cpp

${OBJECTDIR}/FXmlNode.o: FXmlNode.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FXmlNode.o FXmlNode.cpp

${OBJECTDIR}/FXmlNodes.o: FXmlNodes.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FXmlNodes.o FXmlNodes.cpp

${OBJECTDIR}/MApplicationListeners.o: MApplicationListeners.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/MApplicationListeners.o MApplicationListeners.cpp

${OBJECTDIR}/MListeners.o: MListeners.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/MListeners.o MListeners.cpp

${OBJECTDIR}/MNetSocket.o: MNetSocket.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/MNetSocket.o MNetSocket.cpp

${OBJECTDIR}/MPack.o: MPack.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/MPack.o MPack.cpp

${OBJECTDIR}/MPipe.o: MPipe.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/MPipe.o MPipe.cpp

${OBJECTDIR}/MProperties16.o: MProperties16.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/MProperties16.o MProperties16.cpp

${OBJECTDIR}/MProperties32.o: MProperties32.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/MProperties32.o MProperties32.cpp

${OBJECTDIR}/MProperties8.o: MProperties8.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/MProperties8.o MProperties8.cpp

${OBJECTDIR}/MShared.o: MShared.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/MShared.o MShared.cpp

${OBJECTDIR}/MSingleton.o: MSingleton.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/MSingleton.o MSingleton.cpp

${OBJECTDIR}/MSql.o: MSql.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/MSql.o MSql.cpp

${OBJECTDIR}/MString16.o: MString16.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/MString16.o MString16.cpp

${OBJECTDIR}/MString16s.o: MString16s.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/MString16s.o MString16s.cpp

${OBJECTDIR}/MString32.o: MString32.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/MString32.o MString32.cpp

${OBJECTDIR}/MString32s.o: MString32s.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/MString32s.o MString32s.cpp

${OBJECTDIR}/MString8.o: MString8.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/MString8.o MString8.cpp

${OBJECTDIR}/MString8s.o: MString8s.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/MString8s.o MString8s.cpp

${OBJECTDIR}/MoCommon.o: MoCommon.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/MoCommon.o MoCommon.cpp

${OBJECTDIR}/RActivator.o: RActivator.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/RActivator.o RActivator.cpp

${OBJECTDIR}/RAllocator.o: RAllocator.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/RAllocator.o RAllocator.cpp

${OBJECTDIR}/RApplication.o: RApplication.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/RApplication.o RApplication.cpp

${OBJECTDIR}/RBool.o: RBool.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/RBool.o RBool.cpp

${OBJECTDIR}/RByte.o: RByte.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/RByte.o RByte.cpp

${OBJECTDIR}/RChar16.o: RChar16.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/RChar16.o RChar16.cpp

${OBJECTDIR}/RChar32.o: RChar32.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/RChar32.o RChar32.cpp

${OBJECTDIR}/RChar8.o: RChar8.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/RChar8.o RChar8.cpp

${OBJECTDIR}/RCharEncoding.o: RCharEncoding.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/RCharEncoding.o RCharEncoding.cpp

${OBJECTDIR}/RCharSet.o: RCharSet.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/RCharSet.o RCharSet.cpp

${OBJECTDIR}/RCompress.o: RCompress.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/RCompress.o RCompress.cpp

${OBJECTDIR}/RCpu.o: RCpu.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/RCpu.o RCpu.cpp

${OBJECTDIR}/RDateTime.o: RDateTime.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/RDateTime.o RDateTime.cpp

${OBJECTDIR}/RDouble.o: RDouble.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/RDouble.o RDouble.cpp

${OBJECTDIR}/REnvironment.o: REnvironment.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/REnvironment.o REnvironment.cpp

${OBJECTDIR}/RFile.o: RFile.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/RFile.o RFile.cpp

${OBJECTDIR}/RFloat.o: RFloat.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/RFloat.o RFloat.cpp

${OBJECTDIR}/RGuid.o: RGuid.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/RGuid.o RGuid.cpp

${OBJECTDIR}/RInput.o: RInput.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/RInput.o RInput.cpp

${OBJECTDIR}/RInt.o: RInt.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/RInt.o RInt.cpp

${OBJECTDIR}/RInt16.o: RInt16.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/RInt16.o RInt16.cpp

${OBJECTDIR}/RInt32.o: RInt32.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/RInt32.o RInt32.cpp

${OBJECTDIR}/RInt64.o: RInt64.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/RInt64.o RInt64.cpp

${OBJECTDIR}/RInt8.o: RInt8.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/RInt8.o RInt8.cpp

${OBJECTDIR}/RLogger.o: RLogger.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/RLogger.o RLogger.cpp

${OBJECTDIR}/RMemory.o: RMemory.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/RMemory.o RMemory.cpp

${OBJECTDIR}/RMemoryCapacity.o: RMemoryCapacity.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/RMemoryCapacity.o RMemoryCapacity.cpp

${OBJECTDIR}/RNetHost.o: RNetHost.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/RNetHost.o RNetHost.cpp

${OBJECTDIR}/RNetSocket.o: RNetSocket.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/RNetSocket.o RNetSocket.cpp

${OBJECTDIR}/ROutput.o: ROutput.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/ROutput.o ROutput.cpp

${OBJECTDIR}/RProcess.o: RProcess.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/RProcess.o RProcess.cpp

${OBJECTDIR}/RRandom.o: RRandom.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/RRandom.o RRandom.cpp

${OBJECTDIR}/RShareMemory.o: RShareMemory.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/RShareMemory.o RShareMemory.cpp

${OBJECTDIR}/RSingletonManager.o: RSingletonManager.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/RSingletonManager.o RSingletonManager.cpp

${OBJECTDIR}/RString16.o: RString16.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/RString16.o RString16.cpp

${OBJECTDIR}/RString32.o: RString32.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/RString32.o RString32.cpp

${OBJECTDIR}/RString8.o: RString8.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/RString8.o RString8.cpp

${OBJECTDIR}/RSystem.o: RSystem.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/RSystem.o RSystem.cpp

${OBJECTDIR}/RThread.o: RThread.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/RThread.o RThread.cpp

${OBJECTDIR}/RTimeTick.o: RTimeTick.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/RTimeTick.o RTimeTick.cpp

${OBJECTDIR}/RTrap.o: RTrap.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/RTrap.o RTrap.cpp

${OBJECTDIR}/RTypeConverter.o: RTypeConverter.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/RTypeConverter.o RTypeConverter.cpp

${OBJECTDIR}/RUint.o: RUint.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/RUint.o RUint.cpp

${OBJECTDIR}/RUint16.o: RUint16.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/RUint16.o RUint16.cpp

${OBJECTDIR}/RUint32.o: RUint32.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/RUint32.o RUint32.cpp

${OBJECTDIR}/RUint64.o: RUint64.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/RUint64.o RUint64.cpp

${OBJECTDIR}/RUint8.o: RUint8.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/RUint8.o RUint8.cpp

${OBJECTDIR}/RXml.o: RXml.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/RXml.o RXml.cpp

${OBJECTDIR}/SDoublePoint2.o: SDoublePoint2.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/SDoublePoint2.o SDoublePoint2.cpp

${OBJECTDIR}/SDoublePoint3.o: SDoublePoint3.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/SDoublePoint3.o SDoublePoint3.cpp

${OBJECTDIR}/SDoublePoint4.o: SDoublePoint4.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/SDoublePoint4.o SDoublePoint4.cpp

${OBJECTDIR}/SDoubleRange.o: SDoubleRange.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/SDoubleRange.o SDoubleRange.cpp

${OBJECTDIR}/SDoubleSize2.o: SDoubleSize2.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/SDoubleSize2.o SDoubleSize2.cpp

${OBJECTDIR}/SDoubleSize3.o: SDoubleSize3.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/SDoubleSize3.o SDoubleSize3.cpp

${OBJECTDIR}/SDoubleVector2.o: SDoubleVector2.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/SDoubleVector2.o SDoubleVector2.cpp

${OBJECTDIR}/SDoubleVector3.o: SDoubleVector3.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/SDoubleVector3.o SDoubleVector3.cpp

${OBJECTDIR}/SDoubleVector4.o: SDoubleVector4.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/SDoubleVector4.o SDoubleVector4.cpp

${OBJECTDIR}/SFloatPoint2.o: SFloatPoint2.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/SFloatPoint2.o SFloatPoint2.cpp

${OBJECTDIR}/SFloatPoint3.o: SFloatPoint3.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/SFloatPoint3.o SFloatPoint3.cpp

${OBJECTDIR}/SFloatPoint4.o: SFloatPoint4.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/SFloatPoint4.o SFloatPoint4.cpp

${OBJECTDIR}/SFloatRange.o: SFloatRange.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/SFloatRange.o SFloatRange.cpp

${OBJECTDIR}/SFloatSize2.o: SFloatSize2.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/SFloatSize2.o SFloatSize2.cpp

${OBJECTDIR}/SFloatSize3.o: SFloatSize3.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/SFloatSize3.o SFloatSize3.cpp

${OBJECTDIR}/SFloatVector2.o: SFloatVector2.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/SFloatVector2.o SFloatVector2.cpp

${OBJECTDIR}/SFloatVector3.o: SFloatVector3.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/SFloatVector3.o SFloatVector3.cpp

${OBJECTDIR}/SFloatVector4.o: SFloatVector4.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/SFloatVector4.o SFloatVector4.cpp

${OBJECTDIR}/SIntPoint2.o: SIntPoint2.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/SIntPoint2.o SIntPoint2.cpp

${OBJECTDIR}/SIntPoint3.o: SIntPoint3.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/SIntPoint3.o SIntPoint3.cpp

${OBJECTDIR}/SIntPoint4.o: SIntPoint4.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/SIntPoint4.o SIntPoint4.cpp

${OBJECTDIR}/SIntRange.o: SIntRange.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/SIntRange.o SIntRange.cpp

${OBJECTDIR}/SIntSize2.o: SIntSize2.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/SIntSize2.o SIntSize2.cpp

${OBJECTDIR}/SIntSize3.o: SIntSize3.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/SIntSize3.o SIntSize3.cpp

${OBJECTDIR}/SIntVector2.o: SIntVector2.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/SIntVector2.o SIntVector2.cpp

${OBJECTDIR}/SIntVector3.o: SIntVector3.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/SIntVector3.o SIntVector3.cpp

${OBJECTDIR}/SIntVector4.o: SIntVector4.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/SIntVector4.o SIntVector4.cpp

${OBJECTDIR}/SMemoryEntry.o: SMemoryEntry.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/SMemoryEntry.o SMemoryEntry.cpp

${OBJECTDIR}/TClassInfo.o: TClassInfo.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/TClassInfo.o TClassInfo.cpp

${OBJECTDIR}/TDataInput.o: TDataInput.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/TDataInput.o TDataInput.cpp

${OBJECTDIR}/TDataOutput.o: TDataOutput.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/TDataOutput.o TDataOutput.cpp

${OBJECTDIR}/TDataReader.o: TDataReader.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/TDataReader.o TDataReader.cpp

${OBJECTDIR}/TDataWriter.o: TDataWriter.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/TDataWriter.o TDataWriter.cpp

${OBJECTDIR}/TFileFinder.o: TFileFinder.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/TFileFinder.o TFileFinder.cpp

${OBJECTDIR}/TFileInfo.o: TFileInfo.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/TFileInfo.o TFileInfo.cpp

${OBJECTDIR}/TFsDateTime.o: TFsDateTime.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/TFsDateTime.o TFsDateTime.cpp

${OBJECTDIR}/TFsDouble.o: TFsDouble.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/TFsDouble.o TFsDouble.cpp

${OBJECTDIR}/TFsFloat.o: TFsFloat.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/TFsFloat.o TFsFloat.cpp

${OBJECTDIR}/TFsFloatPoint3.o: TFsFloatPoint3.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/TFsFloatPoint3.o TFsFloatPoint3.cpp

${OBJECTDIR}/TFsFloatRange.o: TFsFloatRange.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/TFsFloatRange.o TFsFloatRange.cpp

${OBJECTDIR}/TFsFloatSize.o: TFsFloatSize.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/TFsFloatSize.o TFsFloatSize.cpp

${OBJECTDIR}/TFsFloatVector3.o: TFsFloatVector3.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/TFsFloatVector3.o TFsFloatVector3.cpp

${OBJECTDIR}/TFsIntPoint2.o: TFsIntPoint2.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/TFsIntPoint2.o TFsIntPoint2.cpp

${OBJECTDIR}/TFsIntPoint3.o: TFsIntPoint3.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/TFsIntPoint3.o TFsIntPoint3.cpp

${OBJECTDIR}/TFsIntSize.o: TFsIntSize.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/TFsIntSize.o TFsIntSize.cpp

${OBJECTDIR}/TFsInteger.o: TFsInteger.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/TFsInteger.o TFsInteger.cpp

${OBJECTDIR}/TFsInteger64.o: TFsInteger64.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/TFsInteger64.o TFsInteger64.cpp

${OBJECTDIR}/TLinkInput.o: TLinkInput.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/TLinkInput.o TLinkInput.cpp

${OBJECTDIR}/TLinkOutput.o: TLinkOutput.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/TLinkOutput.o TLinkOutput.cpp

${OBJECTDIR}/TProperty.o: TProperty.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/TProperty.o TProperty.cpp

${OBJECTDIR}/TShareSegment.o: TShareSegment.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/TShareSegment.o TShareSegment.cpp

${OBJECTDIR}/TSharedList.o: TSharedList.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/TSharedList.o TSharedList.cpp

${OBJECTDIR}/TSharedListIterator.o: TSharedListIterator.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/TSharedListIterator.o TSharedListIterator.cpp

${OBJECTDIR}/TSpeedTest.o: TSpeedTest.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/TSpeedTest.o TSpeedTest.cpp

${OBJECTDIR}/TString16.o: TString16.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/TString16.o TString16.cpp

${OBJECTDIR}/TString32.o: TString32.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/TString32.o TString32.cpp

${OBJECTDIR}/TString8.o: TString8.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/TString8.o TString8.cpp

${OBJECTDIR}/TStringBuffer16.o: TStringBuffer16.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/TStringBuffer16.o TStringBuffer16.cpp

${OBJECTDIR}/TStringBuffer32.o: TStringBuffer32.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/TStringBuffer32.o TStringBuffer32.cpp

${OBJECTDIR}/TStringBuffer8.o: TStringBuffer8.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/TStringBuffer8.o TStringBuffer8.cpp

${OBJECTDIR}/TThreadCondition.o: TThreadCondition.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/TThreadCondition.o TThreadCondition.cpp

${OBJECTDIR}/TThreadMutex.o: TThreadMutex.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/TThreadMutex.o TThreadMutex.cpp

${OBJECTDIR}/TThreadSection.o: TThreadSection.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/TThreadSection.o TThreadSection.cpp

${OBJECTDIR}/TTrapper.o: TTrapper.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/TTrapper.o TTrapper.cpp

${OBJECTDIR}/TXmlNodeIteratorC.o: TXmlNodeIteratorC.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CM_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_X64 -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/TXmlNodeIteratorC.o TXmlNodeIteratorC.cpp

# Subprojects
.build-subprojects:

# Clean Targets
.clean-conf: ${CLEAN_SUBPROJECTS}
	${RM} -r ${CND_BUILDDIR}/${CND_CONF}
	${RM} ${CND_DISTDIR}/${CND_CONF}/${CND_PLATFORM}/libmocommon.a

# Subprojects
.clean-subprojects:

# Enable dependency checking
.dep.inc: .depcheck-impl

include .dep.inc
