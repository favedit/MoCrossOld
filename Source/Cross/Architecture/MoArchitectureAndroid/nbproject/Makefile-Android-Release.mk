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
CND_CONF=Android-Release
CND_DISTDIR=dist
CND_BUILDDIR=build

# Include project Makefile
include Makefile

# Object Directory
OBJECTDIR=${CND_BUILDDIR}/${CND_CONF}/${CND_PLATFORM}

# Object Files
OBJECTFILES= \
	${OBJECTDIR}/FAnsySql.o \
	${OBJECTDIR}/FAnsySqlCatcher.o \
	${OBJECTDIR}/FAnsySqlPool.o \
	${OBJECTDIR}/FAnsySqlRecord.o \
	${OBJECTDIR}/FAnsySqlRecordPool.o \
	${OBJECTDIR}/FAnsySqlThread.o \
	${OBJECTDIR}/FAnsyThreadMeta.o \
	${OBJECTDIR}/FBufferedLoggerWriter.o \
	${OBJECTDIR}/FConfigurationConsole.o \
	${OBJECTDIR}/FCsvFile.o \
	${OBJECTDIR}/FCsvFooter.o \
	${OBJECTDIR}/FCsvFooters.o \
	${OBJECTDIR}/FCsvHead.o \
	${OBJECTDIR}/FCsvHeads.o \
	${OBJECTDIR}/FCsvLine.o \
	${OBJECTDIR}/FCsvLines.o \
	${OBJECTDIR}/FCsvSegment.o \
	${OBJECTDIR}/FDatabaseModule.o \
	${OBJECTDIR}/FDatasetStatement.o \
	${OBJECTDIR}/FDatasetStatementDelete.o \
	${OBJECTDIR}/FDatasetStatementExecute.o \
	${OBJECTDIR}/FDatasetStatementFetch.o \
	${OBJECTDIR}/FDatasetStatementFind.o \
	${OBJECTDIR}/FDatasetStatementInsert.o \
	${OBJECTDIR}/FDatasetStatementUpdate.o \
	${OBJECTDIR}/FInterruptListener.o \
	${OBJECTDIR}/FLoggerModule.o \
	${OBJECTDIR}/FMemoryPoolObject.o \
	${OBJECTDIR}/FMemoryPoolObjectService.o \
	${OBJECTDIR}/FMessageLogic.o \
	${OBJECTDIR}/FModule.o \
	${OBJECTDIR}/FModuleConsole.o \
	${OBJECTDIR}/FMonitorService.o \
	${OBJECTDIR}/FMySqlConnection.o \
	${OBJECTDIR}/FNetBufferedClientQueueModule.o \
	${OBJECTDIR}/FNetBufferedPipe.o \
	${OBJECTDIR}/FNetBufferedQueue.o \
	${OBJECTDIR}/FNetBufferedQueueConnection.o \
	${OBJECTDIR}/FNetBufferedQueueModule.o \
	${OBJECTDIR}/FNetBufferedSocket.o \
	${OBJECTDIR}/FNetBufferedSocketModule.o \
	${OBJECTDIR}/FNetClientBufferedSocketModule.o \
	${OBJECTDIR}/FNetEpoll.o \
	${OBJECTDIR}/FNetEpollTransferReceiveThread.o \
	${OBJECTDIR}/FNetEpollTransferSendThread.o \
	${OBJECTDIR}/FNetEpollTransferServerThread.o \
	${OBJECTDIR}/FNetEpollTransferService.o \
	${OBJECTDIR}/FNetIocpQueryThread.o \
	${OBJECTDIR}/FNetIocpSendThread.o \
	${OBJECTDIR}/FNetIocpServer.o \
	${OBJECTDIR}/FNetIocpServerFactory.o \
	${OBJECTDIR}/FNetIocpServerSocket.o \
	${OBJECTDIR}/FNetIocpServerThread.o \
	${OBJECTDIR}/FNetIocpSocket.o \
	${OBJECTDIR}/FNetMessageCatcher.o \
	${OBJECTDIR}/FNetMessageConnection.o \
	${OBJECTDIR}/FNetMessageConsole.o \
	${OBJECTDIR}/FNetMessageHandle.o \
	${OBJECTDIR}/FNetMessageHandles.o \
	${OBJECTDIR}/FNetMessageMachine.o \
	${OBJECTDIR}/FNetMessageProcessor.o \
	${OBJECTDIR}/FNetMessageReceiveThread.o \
	${OBJECTDIR}/FNetMessageService.o \
	${OBJECTDIR}/FNetMessageStatisticsConsole.o \
	${OBJECTDIR}/FNetMessageStatisticsMachine.o \
	${OBJECTDIR}/FNetPipeBlockPoolModule.o \
	${OBJECTDIR}/FNetQueueBlockPoolModule.o \
	${OBJECTDIR}/FNetRouterHandle.o \
	${OBJECTDIR}/FNetRouterHandles.o \
	${OBJECTDIR}/FNetRouterProcessor.o \
	${OBJECTDIR}/FNetSocketLinkerModule.o \
	${OBJECTDIR}/FNetSocketLinkerStorage.o \
	${OBJECTDIR}/FNetTransferCatcher.o \
	${OBJECTDIR}/FNetTransferCommander.o \
	${OBJECTDIR}/FNetTransferHandle.o \
	${OBJECTDIR}/FNetTransferHandles.o \
	${OBJECTDIR}/FNetTransferProcessor.o \
	${OBJECTDIR}/FNetTransferReceiveThread.o \
	${OBJECTDIR}/FNetTransferSendThread.o \
	${OBJECTDIR}/FNetTransferServerThread.o \
	${OBJECTDIR}/FNetTransferService.o \
	${OBJECTDIR}/FRecordAllocator.o \
	${OBJECTDIR}/FServer.o \
	${OBJECTDIR}/FService.o \
	${OBJECTDIR}/FServiceConsole.o \
	${OBJECTDIR}/FSharedModule.o \
	${OBJECTDIR}/FSharedModuleConsole.o \
	${OBJECTDIR}/FSharedNetPipe.o \
	${OBJECTDIR}/FSharedNetQueue.o \
	${OBJECTDIR}/FSharedNetQueueStorage.o \
	${OBJECTDIR}/FSharedNetSocketClient.o \
	${OBJECTDIR}/FSqlConnectionPool.o \
	${OBJECTDIR}/FSqlDatasetMeta.o \
	${OBJECTDIR}/FStatisticsConsole.o \
	${OBJECTDIR}/FStatisticsMonitor.o \
	${OBJECTDIR}/FTimerConsole.o \
	${OBJECTDIR}/FTplConfigObject.o \
	${OBJECTDIR}/FTplDocumentObject.o \
	${OBJECTDIR}/FTplObject.o \
	${OBJECTDIR}/FXmlContainerConsole.o \
	${OBJECTDIR}/MDatabaseWorker.o \
	${OBJECTDIR}/MNetSocketLinker.o \
	${OBJECTDIR}/MoCore.o \
	${OBJECTDIR}/MoCrConstant.o \
	${OBJECTDIR}/MoCrStruct.o \
	${OBJECTDIR}/RTimerManager.o \
	${OBJECTDIR}/TCmdParser.o \
	${OBJECTDIR}/TDatasetLogic.o \
	${OBJECTDIR}/TFsGroupId.o \
	${OBJECTDIR}/TFsLinkId.o \
	${OBJECTDIR}/TFsObjectId.o \
	${OBJECTDIR}/TFsRecordId.o \
	${OBJECTDIR}/TFsResourceId.o \
	${OBJECTDIR}/TFsTemplateId.o \
	${OBJECTDIR}/TFsUniqueId.o \
	${OBJECTDIR}/TNetData.o \
	${OBJECTDIR}/TNetHead.o \
	${OBJECTDIR}/TNetMessage.o \
	${OBJECTDIR}/TNetMessageBuffer.o \
	${OBJECTDIR}/TNetMessageHead.o \
	${OBJECTDIR}/TNetPackage.o \
	${OBJECTDIR}/TNetPackageHead.o \
	${OBJECTDIR}/TNetRouter.o \
	${OBJECTDIR}/TNetRouterHead.o \
	${OBJECTDIR}/TNetTransfer.o \
	${OBJECTDIR}/TNetTransferHead.o \
	${OBJECTDIR}/TPhysics.o \
	${OBJECTDIR}/TPhysicsCircleTrack.o \
	${OBJECTDIR}/TPhysicsJump.o \
	${OBJECTDIR}/TPhysicsSquareTrack.o


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
	"${MAKE}"  -f nbproject/Makefile-${CND_CONF}.mk ${CND_DISTDIR}/${CND_CONF}/${CND_PLATFORM}/libmocore.a

${CND_DISTDIR}/${CND_CONF}/${CND_PLATFORM}/libmocore.a: ${OBJECTFILES}
	${MKDIR} -p ${CND_DISTDIR}/${CND_CONF}/${CND_PLATFORM}
	${RM} ${CND_DISTDIR}/${CND_CONF}/${CND_PLATFORM}/libmocore.a
	${AR} -rv ${CND_DISTDIR}/${CND_CONF}/${CND_PLATFORM}/libmocore.a ${OBJECTFILES} 
	$(RANLIB) ${CND_DISTDIR}/${CND_CONF}/${CND_PLATFORM}/libmocore.a

${OBJECTDIR}/FAnsySql.o: FAnsySql.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CR_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_NET_MASK -D_MO_X64 -I../MoCommon -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FAnsySql.o FAnsySql.cpp

${OBJECTDIR}/FAnsySqlCatcher.o: FAnsySqlCatcher.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CR_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_NET_MASK -D_MO_X64 -I../MoCommon -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FAnsySqlCatcher.o FAnsySqlCatcher.cpp

${OBJECTDIR}/FAnsySqlPool.o: FAnsySqlPool.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CR_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_NET_MASK -D_MO_X64 -I../MoCommon -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FAnsySqlPool.o FAnsySqlPool.cpp

${OBJECTDIR}/FAnsySqlRecord.o: FAnsySqlRecord.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CR_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_NET_MASK -D_MO_X64 -I../MoCommon -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FAnsySqlRecord.o FAnsySqlRecord.cpp

${OBJECTDIR}/FAnsySqlRecordPool.o: FAnsySqlRecordPool.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CR_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_NET_MASK -D_MO_X64 -I../MoCommon -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FAnsySqlRecordPool.o FAnsySqlRecordPool.cpp

${OBJECTDIR}/FAnsySqlThread.o: FAnsySqlThread.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CR_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_NET_MASK -D_MO_X64 -I../MoCommon -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FAnsySqlThread.o FAnsySqlThread.cpp

${OBJECTDIR}/FAnsyThreadMeta.o: FAnsyThreadMeta.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CR_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_NET_MASK -D_MO_X64 -I../MoCommon -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FAnsyThreadMeta.o FAnsyThreadMeta.cpp

${OBJECTDIR}/FBufferedLoggerWriter.o: FBufferedLoggerWriter.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CR_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_NET_MASK -D_MO_X64 -I../MoCommon -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FBufferedLoggerWriter.o FBufferedLoggerWriter.cpp

${OBJECTDIR}/FConfigurationConsole.o: FConfigurationConsole.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CR_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_NET_MASK -D_MO_X64 -I../MoCommon -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FConfigurationConsole.o FConfigurationConsole.cpp

${OBJECTDIR}/FCsvFile.o: FCsvFile.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CR_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_NET_MASK -D_MO_X64 -I../MoCommon -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FCsvFile.o FCsvFile.cpp

${OBJECTDIR}/FCsvFooter.o: FCsvFooter.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CR_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_NET_MASK -D_MO_X64 -I../MoCommon -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FCsvFooter.o FCsvFooter.cpp

${OBJECTDIR}/FCsvFooters.o: FCsvFooters.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CR_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_NET_MASK -D_MO_X64 -I../MoCommon -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FCsvFooters.o FCsvFooters.cpp

${OBJECTDIR}/FCsvHead.o: FCsvHead.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CR_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_NET_MASK -D_MO_X64 -I../MoCommon -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FCsvHead.o FCsvHead.cpp

${OBJECTDIR}/FCsvHeads.o: FCsvHeads.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CR_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_NET_MASK -D_MO_X64 -I../MoCommon -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FCsvHeads.o FCsvHeads.cpp

${OBJECTDIR}/FCsvLine.o: FCsvLine.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CR_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_NET_MASK -D_MO_X64 -I../MoCommon -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FCsvLine.o FCsvLine.cpp

${OBJECTDIR}/FCsvLines.o: FCsvLines.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CR_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_NET_MASK -D_MO_X64 -I../MoCommon -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FCsvLines.o FCsvLines.cpp

${OBJECTDIR}/FCsvSegment.o: FCsvSegment.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CR_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_NET_MASK -D_MO_X64 -I../MoCommon -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FCsvSegment.o FCsvSegment.cpp

${OBJECTDIR}/FDatabaseModule.o: FDatabaseModule.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CR_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_NET_MASK -D_MO_X64 -I../MoCommon -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FDatabaseModule.o FDatabaseModule.cpp

${OBJECTDIR}/FDatasetStatement.o: FDatasetStatement.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CR_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_NET_MASK -D_MO_X64 -I../MoCommon -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FDatasetStatement.o FDatasetStatement.cpp

${OBJECTDIR}/FDatasetStatementDelete.o: FDatasetStatementDelete.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CR_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_NET_MASK -D_MO_X64 -I../MoCommon -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FDatasetStatementDelete.o FDatasetStatementDelete.cpp

${OBJECTDIR}/FDatasetStatementExecute.o: FDatasetStatementExecute.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CR_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_NET_MASK -D_MO_X64 -I../MoCommon -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FDatasetStatementExecute.o FDatasetStatementExecute.cpp

${OBJECTDIR}/FDatasetStatementFetch.o: FDatasetStatementFetch.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CR_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_NET_MASK -D_MO_X64 -I../MoCommon -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FDatasetStatementFetch.o FDatasetStatementFetch.cpp

${OBJECTDIR}/FDatasetStatementFind.o: FDatasetStatementFind.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CR_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_NET_MASK -D_MO_X64 -I../MoCommon -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FDatasetStatementFind.o FDatasetStatementFind.cpp

${OBJECTDIR}/FDatasetStatementInsert.o: FDatasetStatementInsert.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CR_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_NET_MASK -D_MO_X64 -I../MoCommon -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FDatasetStatementInsert.o FDatasetStatementInsert.cpp

${OBJECTDIR}/FDatasetStatementUpdate.o: FDatasetStatementUpdate.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CR_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_NET_MASK -D_MO_X64 -I../MoCommon -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FDatasetStatementUpdate.o FDatasetStatementUpdate.cpp

${OBJECTDIR}/FInterruptListener.o: FInterruptListener.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CR_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_NET_MASK -D_MO_X64 -I../MoCommon -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FInterruptListener.o FInterruptListener.cpp

${OBJECTDIR}/FLoggerModule.o: FLoggerModule.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CR_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_NET_MASK -D_MO_X64 -I../MoCommon -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FLoggerModule.o FLoggerModule.cpp

${OBJECTDIR}/FMemoryPoolObject.o: FMemoryPoolObject.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CR_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_NET_MASK -D_MO_X64 -I../MoCommon -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FMemoryPoolObject.o FMemoryPoolObject.cpp

${OBJECTDIR}/FMemoryPoolObjectService.o: FMemoryPoolObjectService.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CR_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_NET_MASK -D_MO_X64 -I../MoCommon -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FMemoryPoolObjectService.o FMemoryPoolObjectService.cpp

${OBJECTDIR}/FMessageLogic.o: FMessageLogic.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CR_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_NET_MASK -D_MO_X64 -I../MoCommon -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FMessageLogic.o FMessageLogic.cpp

${OBJECTDIR}/FModule.o: FModule.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CR_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_NET_MASK -D_MO_X64 -I../MoCommon -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FModule.o FModule.cpp

${OBJECTDIR}/FModuleConsole.o: FModuleConsole.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CR_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_NET_MASK -D_MO_X64 -I../MoCommon -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FModuleConsole.o FModuleConsole.cpp

${OBJECTDIR}/FMonitorService.o: FMonitorService.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CR_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_NET_MASK -D_MO_X64 -I../MoCommon -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FMonitorService.o FMonitorService.cpp

${OBJECTDIR}/FMySqlConnection.o: FMySqlConnection.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CR_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_NET_MASK -D_MO_X64 -I../MoCommon -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FMySqlConnection.o FMySqlConnection.cpp

${OBJECTDIR}/FNetBufferedClientQueueModule.o: FNetBufferedClientQueueModule.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CR_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_NET_MASK -D_MO_X64 -I../MoCommon -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FNetBufferedClientQueueModule.o FNetBufferedClientQueueModule.cpp

${OBJECTDIR}/FNetBufferedPipe.o: FNetBufferedPipe.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CR_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_NET_MASK -D_MO_X64 -I../MoCommon -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FNetBufferedPipe.o FNetBufferedPipe.cpp

${OBJECTDIR}/FNetBufferedQueue.o: FNetBufferedQueue.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CR_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_NET_MASK -D_MO_X64 -I../MoCommon -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FNetBufferedQueue.o FNetBufferedQueue.cpp

${OBJECTDIR}/FNetBufferedQueueConnection.o: FNetBufferedQueueConnection.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CR_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_NET_MASK -D_MO_X64 -I../MoCommon -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FNetBufferedQueueConnection.o FNetBufferedQueueConnection.cpp

${OBJECTDIR}/FNetBufferedQueueModule.o: FNetBufferedQueueModule.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CR_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_NET_MASK -D_MO_X64 -I../MoCommon -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FNetBufferedQueueModule.o FNetBufferedQueueModule.cpp

${OBJECTDIR}/FNetBufferedSocket.o: FNetBufferedSocket.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CR_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_NET_MASK -D_MO_X64 -I../MoCommon -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FNetBufferedSocket.o FNetBufferedSocket.cpp

${OBJECTDIR}/FNetBufferedSocketModule.o: FNetBufferedSocketModule.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CR_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_NET_MASK -D_MO_X64 -I../MoCommon -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FNetBufferedSocketModule.o FNetBufferedSocketModule.cpp

${OBJECTDIR}/FNetClientBufferedSocketModule.o: FNetClientBufferedSocketModule.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CR_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_NET_MASK -D_MO_X64 -I../MoCommon -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FNetClientBufferedSocketModule.o FNetClientBufferedSocketModule.cpp

${OBJECTDIR}/FNetEpoll.o: FNetEpoll.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CR_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_NET_MASK -D_MO_X64 -I../MoCommon -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FNetEpoll.o FNetEpoll.cpp

${OBJECTDIR}/FNetEpollTransferReceiveThread.o: FNetEpollTransferReceiveThread.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CR_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_NET_MASK -D_MO_X64 -I../MoCommon -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FNetEpollTransferReceiveThread.o FNetEpollTransferReceiveThread.cpp

${OBJECTDIR}/FNetEpollTransferSendThread.o: FNetEpollTransferSendThread.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CR_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_NET_MASK -D_MO_X64 -I../MoCommon -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FNetEpollTransferSendThread.o FNetEpollTransferSendThread.cpp

${OBJECTDIR}/FNetEpollTransferServerThread.o: FNetEpollTransferServerThread.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CR_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_NET_MASK -D_MO_X64 -I../MoCommon -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FNetEpollTransferServerThread.o FNetEpollTransferServerThread.cpp

${OBJECTDIR}/FNetEpollTransferService.o: FNetEpollTransferService.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CR_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_NET_MASK -D_MO_X64 -I../MoCommon -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FNetEpollTransferService.o FNetEpollTransferService.cpp

${OBJECTDIR}/FNetIocpQueryThread.o: FNetIocpQueryThread.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CR_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_NET_MASK -D_MO_X64 -I../MoCommon -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FNetIocpQueryThread.o FNetIocpQueryThread.cpp

${OBJECTDIR}/FNetIocpSendThread.o: FNetIocpSendThread.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CR_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_NET_MASK -D_MO_X64 -I../MoCommon -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FNetIocpSendThread.o FNetIocpSendThread.cpp

${OBJECTDIR}/FNetIocpServer.o: FNetIocpServer.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CR_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_NET_MASK -D_MO_X64 -I../MoCommon -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FNetIocpServer.o FNetIocpServer.cpp

${OBJECTDIR}/FNetIocpServerFactory.o: FNetIocpServerFactory.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CR_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_NET_MASK -D_MO_X64 -I../MoCommon -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FNetIocpServerFactory.o FNetIocpServerFactory.cpp

${OBJECTDIR}/FNetIocpServerSocket.o: FNetIocpServerSocket.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CR_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_NET_MASK -D_MO_X64 -I../MoCommon -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FNetIocpServerSocket.o FNetIocpServerSocket.cpp

${OBJECTDIR}/FNetIocpServerThread.o: FNetIocpServerThread.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CR_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_NET_MASK -D_MO_X64 -I../MoCommon -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FNetIocpServerThread.o FNetIocpServerThread.cpp

${OBJECTDIR}/FNetIocpSocket.o: FNetIocpSocket.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CR_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_NET_MASK -D_MO_X64 -I../MoCommon -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FNetIocpSocket.o FNetIocpSocket.cpp

${OBJECTDIR}/FNetMessageCatcher.o: FNetMessageCatcher.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CR_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_NET_MASK -D_MO_X64 -I../MoCommon -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FNetMessageCatcher.o FNetMessageCatcher.cpp

${OBJECTDIR}/FNetMessageConnection.o: FNetMessageConnection.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CR_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_NET_MASK -D_MO_X64 -I../MoCommon -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FNetMessageConnection.o FNetMessageConnection.cpp

${OBJECTDIR}/FNetMessageConsole.o: FNetMessageConsole.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CR_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_NET_MASK -D_MO_X64 -I../MoCommon -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FNetMessageConsole.o FNetMessageConsole.cpp

${OBJECTDIR}/FNetMessageHandle.o: FNetMessageHandle.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CR_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_NET_MASK -D_MO_X64 -I../MoCommon -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FNetMessageHandle.o FNetMessageHandle.cpp

${OBJECTDIR}/FNetMessageHandles.o: FNetMessageHandles.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CR_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_NET_MASK -D_MO_X64 -I../MoCommon -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FNetMessageHandles.o FNetMessageHandles.cpp

${OBJECTDIR}/FNetMessageMachine.o: FNetMessageMachine.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CR_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_NET_MASK -D_MO_X64 -I../MoCommon -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FNetMessageMachine.o FNetMessageMachine.cpp

${OBJECTDIR}/FNetMessageProcessor.o: FNetMessageProcessor.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CR_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_NET_MASK -D_MO_X64 -I../MoCommon -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FNetMessageProcessor.o FNetMessageProcessor.cpp

${OBJECTDIR}/FNetMessageReceiveThread.o: FNetMessageReceiveThread.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CR_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_NET_MASK -D_MO_X64 -I../MoCommon -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FNetMessageReceiveThread.o FNetMessageReceiveThread.cpp

${OBJECTDIR}/FNetMessageService.o: FNetMessageService.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CR_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_NET_MASK -D_MO_X64 -I../MoCommon -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FNetMessageService.o FNetMessageService.cpp

${OBJECTDIR}/FNetMessageStatisticsConsole.o: FNetMessageStatisticsConsole.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CR_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_NET_MASK -D_MO_X64 -I../MoCommon -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FNetMessageStatisticsConsole.o FNetMessageStatisticsConsole.cpp

${OBJECTDIR}/FNetMessageStatisticsMachine.o: FNetMessageStatisticsMachine.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CR_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_NET_MASK -D_MO_X64 -I../MoCommon -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FNetMessageStatisticsMachine.o FNetMessageStatisticsMachine.cpp

${OBJECTDIR}/FNetPipeBlockPoolModule.o: FNetPipeBlockPoolModule.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CR_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_NET_MASK -D_MO_X64 -I../MoCommon -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FNetPipeBlockPoolModule.o FNetPipeBlockPoolModule.cpp

${OBJECTDIR}/FNetQueueBlockPoolModule.o: FNetQueueBlockPoolModule.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CR_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_NET_MASK -D_MO_X64 -I../MoCommon -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FNetQueueBlockPoolModule.o FNetQueueBlockPoolModule.cpp

${OBJECTDIR}/FNetRouterHandle.o: FNetRouterHandle.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CR_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_NET_MASK -D_MO_X64 -I../MoCommon -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FNetRouterHandle.o FNetRouterHandle.cpp

${OBJECTDIR}/FNetRouterHandles.o: FNetRouterHandles.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CR_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_NET_MASK -D_MO_X64 -I../MoCommon -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FNetRouterHandles.o FNetRouterHandles.cpp

${OBJECTDIR}/FNetRouterProcessor.o: FNetRouterProcessor.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CR_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_NET_MASK -D_MO_X64 -I../MoCommon -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FNetRouterProcessor.o FNetRouterProcessor.cpp

${OBJECTDIR}/FNetSocketLinkerModule.o: FNetSocketLinkerModule.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CR_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_NET_MASK -D_MO_X64 -I../MoCommon -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FNetSocketLinkerModule.o FNetSocketLinkerModule.cpp

${OBJECTDIR}/FNetSocketLinkerStorage.o: FNetSocketLinkerStorage.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CR_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_NET_MASK -D_MO_X64 -I../MoCommon -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FNetSocketLinkerStorage.o FNetSocketLinkerStorage.cpp

${OBJECTDIR}/FNetTransferCatcher.o: FNetTransferCatcher.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CR_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_NET_MASK -D_MO_X64 -I../MoCommon -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FNetTransferCatcher.o FNetTransferCatcher.cpp

${OBJECTDIR}/FNetTransferCommander.o: FNetTransferCommander.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CR_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_NET_MASK -D_MO_X64 -I../MoCommon -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FNetTransferCommander.o FNetTransferCommander.cpp

${OBJECTDIR}/FNetTransferHandle.o: FNetTransferHandle.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CR_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_NET_MASK -D_MO_X64 -I../MoCommon -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FNetTransferHandle.o FNetTransferHandle.cpp

${OBJECTDIR}/FNetTransferHandles.o: FNetTransferHandles.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CR_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_NET_MASK -D_MO_X64 -I../MoCommon -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FNetTransferHandles.o FNetTransferHandles.cpp

${OBJECTDIR}/FNetTransferProcessor.o: FNetTransferProcessor.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CR_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_NET_MASK -D_MO_X64 -I../MoCommon -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FNetTransferProcessor.o FNetTransferProcessor.cpp

${OBJECTDIR}/FNetTransferReceiveThread.o: FNetTransferReceiveThread.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CR_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_NET_MASK -D_MO_X64 -I../MoCommon -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FNetTransferReceiveThread.o FNetTransferReceiveThread.cpp

${OBJECTDIR}/FNetTransferSendThread.o: FNetTransferSendThread.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CR_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_NET_MASK -D_MO_X64 -I../MoCommon -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FNetTransferSendThread.o FNetTransferSendThread.cpp

${OBJECTDIR}/FNetTransferServerThread.o: FNetTransferServerThread.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CR_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_NET_MASK -D_MO_X64 -I../MoCommon -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FNetTransferServerThread.o FNetTransferServerThread.cpp

${OBJECTDIR}/FNetTransferService.o: FNetTransferService.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CR_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_NET_MASK -D_MO_X64 -I../MoCommon -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FNetTransferService.o FNetTransferService.cpp

${OBJECTDIR}/FRecordAllocator.o: FRecordAllocator.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CR_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_NET_MASK -D_MO_X64 -I../MoCommon -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FRecordAllocator.o FRecordAllocator.cpp

${OBJECTDIR}/FServer.o: FServer.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CR_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_NET_MASK -D_MO_X64 -I../MoCommon -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FServer.o FServer.cpp

${OBJECTDIR}/FService.o: FService.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CR_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_NET_MASK -D_MO_X64 -I../MoCommon -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FService.o FService.cpp

${OBJECTDIR}/FServiceConsole.o: FServiceConsole.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CR_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_NET_MASK -D_MO_X64 -I../MoCommon -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FServiceConsole.o FServiceConsole.cpp

${OBJECTDIR}/FSharedModule.o: FSharedModule.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CR_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_NET_MASK -D_MO_X64 -I../MoCommon -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FSharedModule.o FSharedModule.cpp

${OBJECTDIR}/FSharedModuleConsole.o: FSharedModuleConsole.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CR_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_NET_MASK -D_MO_X64 -I../MoCommon -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FSharedModuleConsole.o FSharedModuleConsole.cpp

${OBJECTDIR}/FSharedNetPipe.o: FSharedNetPipe.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CR_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_NET_MASK -D_MO_X64 -I../MoCommon -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FSharedNetPipe.o FSharedNetPipe.cpp

${OBJECTDIR}/FSharedNetQueue.o: FSharedNetQueue.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CR_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_NET_MASK -D_MO_X64 -I../MoCommon -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FSharedNetQueue.o FSharedNetQueue.cpp

${OBJECTDIR}/FSharedNetQueueStorage.o: FSharedNetQueueStorage.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CR_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_NET_MASK -D_MO_X64 -I../MoCommon -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FSharedNetQueueStorage.o FSharedNetQueueStorage.cpp

${OBJECTDIR}/FSharedNetSocketClient.o: FSharedNetSocketClient.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CR_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_NET_MASK -D_MO_X64 -I../MoCommon -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FSharedNetSocketClient.o FSharedNetSocketClient.cpp

${OBJECTDIR}/FSqlConnectionPool.o: FSqlConnectionPool.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CR_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_NET_MASK -D_MO_X64 -I../MoCommon -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FSqlConnectionPool.o FSqlConnectionPool.cpp

${OBJECTDIR}/FSqlDatasetMeta.o: FSqlDatasetMeta.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CR_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_NET_MASK -D_MO_X64 -I../MoCommon -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FSqlDatasetMeta.o FSqlDatasetMeta.cpp

${OBJECTDIR}/FStatisticsConsole.o: FStatisticsConsole.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CR_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_NET_MASK -D_MO_X64 -I../MoCommon -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FStatisticsConsole.o FStatisticsConsole.cpp

${OBJECTDIR}/FStatisticsMonitor.o: FStatisticsMonitor.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CR_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_NET_MASK -D_MO_X64 -I../MoCommon -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FStatisticsMonitor.o FStatisticsMonitor.cpp

${OBJECTDIR}/FTimerConsole.o: FTimerConsole.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CR_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_NET_MASK -D_MO_X64 -I../MoCommon -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FTimerConsole.o FTimerConsole.cpp

${OBJECTDIR}/FTplConfigObject.o: FTplConfigObject.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CR_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_NET_MASK -D_MO_X64 -I../MoCommon -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FTplConfigObject.o FTplConfigObject.cpp

${OBJECTDIR}/FTplDocumentObject.o: FTplDocumentObject.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CR_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_NET_MASK -D_MO_X64 -I../MoCommon -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FTplDocumentObject.o FTplDocumentObject.cpp

${OBJECTDIR}/FTplObject.o: FTplObject.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CR_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_NET_MASK -D_MO_X64 -I../MoCommon -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FTplObject.o FTplObject.cpp

${OBJECTDIR}/FXmlContainerConsole.o: FXmlContainerConsole.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CR_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_NET_MASK -D_MO_X64 -I../MoCommon -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/FXmlContainerConsole.o FXmlContainerConsole.cpp

${OBJECTDIR}/MDatabaseWorker.o: MDatabaseWorker.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CR_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_NET_MASK -D_MO_X64 -I../MoCommon -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/MDatabaseWorker.o MDatabaseWorker.cpp

${OBJECTDIR}/MNetSocketLinker.o: MNetSocketLinker.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CR_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_NET_MASK -D_MO_X64 -I../MoCommon -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/MNetSocketLinker.o MNetSocketLinker.cpp

${OBJECTDIR}/MoCore.o: MoCore.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CR_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_NET_MASK -D_MO_X64 -I../MoCommon -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/MoCore.o MoCore.cpp

${OBJECTDIR}/MoCrConstant.o: MoCrConstant.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CR_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_NET_MASK -D_MO_X64 -I../MoCommon -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/MoCrConstant.o MoCrConstant.cpp

${OBJECTDIR}/MoCrStruct.o: MoCrStruct.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CR_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_NET_MASK -D_MO_X64 -I../MoCommon -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/MoCrStruct.o MoCrStruct.cpp

${OBJECTDIR}/RTimerManager.o: RTimerManager.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CR_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_NET_MASK -D_MO_X64 -I../MoCommon -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/RTimerManager.o RTimerManager.cpp

${OBJECTDIR}/TCmdParser.o: TCmdParser.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CR_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_NET_MASK -D_MO_X64 -I../MoCommon -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/TCmdParser.o TCmdParser.cpp

${OBJECTDIR}/TDatasetLogic.o: TDatasetLogic.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CR_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_NET_MASK -D_MO_X64 -I../MoCommon -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/TDatasetLogic.o TDatasetLogic.cpp

${OBJECTDIR}/TFsGroupId.o: TFsGroupId.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CR_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_NET_MASK -D_MO_X64 -I../MoCommon -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/TFsGroupId.o TFsGroupId.cpp

${OBJECTDIR}/TFsLinkId.o: TFsLinkId.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CR_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_NET_MASK -D_MO_X64 -I../MoCommon -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/TFsLinkId.o TFsLinkId.cpp

${OBJECTDIR}/TFsObjectId.o: TFsObjectId.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CR_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_NET_MASK -D_MO_X64 -I../MoCommon -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/TFsObjectId.o TFsObjectId.cpp

${OBJECTDIR}/TFsRecordId.o: TFsRecordId.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CR_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_NET_MASK -D_MO_X64 -I../MoCommon -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/TFsRecordId.o TFsRecordId.cpp

${OBJECTDIR}/TFsResourceId.o: TFsResourceId.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CR_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_NET_MASK -D_MO_X64 -I../MoCommon -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/TFsResourceId.o TFsResourceId.cpp

${OBJECTDIR}/TFsTemplateId.o: TFsTemplateId.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CR_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_NET_MASK -D_MO_X64 -I../MoCommon -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/TFsTemplateId.o TFsTemplateId.cpp

${OBJECTDIR}/TFsUniqueId.o: TFsUniqueId.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CR_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_NET_MASK -D_MO_X64 -I../MoCommon -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/TFsUniqueId.o TFsUniqueId.cpp

${OBJECTDIR}/TNetData.o: TNetData.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CR_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_NET_MASK -D_MO_X64 -I../MoCommon -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/TNetData.o TNetData.cpp

${OBJECTDIR}/TNetHead.o: TNetHead.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CR_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_NET_MASK -D_MO_X64 -I../MoCommon -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/TNetHead.o TNetHead.cpp

${OBJECTDIR}/TNetMessage.o: TNetMessage.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CR_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_NET_MASK -D_MO_X64 -I../MoCommon -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/TNetMessage.o TNetMessage.cpp

${OBJECTDIR}/TNetMessageBuffer.o: TNetMessageBuffer.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CR_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_NET_MASK -D_MO_X64 -I../MoCommon -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/TNetMessageBuffer.o TNetMessageBuffer.cpp

${OBJECTDIR}/TNetMessageHead.o: TNetMessageHead.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CR_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_NET_MASK -D_MO_X64 -I../MoCommon -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/TNetMessageHead.o TNetMessageHead.cpp

${OBJECTDIR}/TNetPackage.o: TNetPackage.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CR_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_NET_MASK -D_MO_X64 -I../MoCommon -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/TNetPackage.o TNetPackage.cpp

${OBJECTDIR}/TNetPackageHead.o: TNetPackageHead.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CR_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_NET_MASK -D_MO_X64 -I../MoCommon -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/TNetPackageHead.o TNetPackageHead.cpp

${OBJECTDIR}/TNetRouter.o: TNetRouter.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CR_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_NET_MASK -D_MO_X64 -I../MoCommon -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/TNetRouter.o TNetRouter.cpp

${OBJECTDIR}/TNetRouterHead.o: TNetRouterHead.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CR_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_NET_MASK -D_MO_X64 -I../MoCommon -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/TNetRouterHead.o TNetRouterHead.cpp

${OBJECTDIR}/TNetTransfer.o: TNetTransfer.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CR_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_NET_MASK -D_MO_X64 -I../MoCommon -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/TNetTransfer.o TNetTransfer.cpp

${OBJECTDIR}/TNetTransferHead.o: TNetTransferHead.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CR_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_NET_MASK -D_MO_X64 -I../MoCommon -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/TNetTransferHead.o TNetTransferHead.cpp

${OBJECTDIR}/TPhysics.o: TPhysics.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CR_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_NET_MASK -D_MO_X64 -I../MoCommon -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/TPhysics.o TPhysics.cpp

${OBJECTDIR}/TPhysicsCircleTrack.o: TPhysicsCircleTrack.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CR_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_NET_MASK -D_MO_X64 -I../MoCommon -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/TPhysicsCircleTrack.o TPhysicsCircleTrack.cpp

${OBJECTDIR}/TPhysicsJump.o: TPhysicsJump.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CR_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_NET_MASK -D_MO_X64 -I../MoCommon -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/TPhysicsJump.o TPhysicsJump.cpp

${OBJECTDIR}/TPhysicsSquareTrack.o: TPhysicsSquareTrack.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} "$@.d"
	$(COMPILE.cc) -g -Wall -D_MO_CR_EXPORTS -D_MO_DEBUG -D_MO_LINUX -D_MO_NET_MASK -D_MO_X64 -I../MoCommon -MMD -MP -MF "$@.d" -o ${OBJECTDIR}/TPhysicsSquareTrack.o TPhysicsSquareTrack.cpp

# Subprojects
.build-subprojects:

# Clean Targets
.clean-conf: ${CLEAN_SUBPROJECTS}
	${RM} -r ${CND_BUILDDIR}/${CND_CONF}
	${RM} ${CND_DISTDIR}/${CND_CONF}/${CND_PLATFORM}/libmocore.a

# Subprojects
.clean-subprojects:

# Enable dependency checking
.dep.inc: .depcheck-impl

include .dep.inc
