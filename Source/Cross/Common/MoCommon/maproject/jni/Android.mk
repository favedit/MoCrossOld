LOCAL_PATH := $(call my-dir)

#------------------------------------------------------------
include $(CLEAR_VARS)

#............................................................
# MO_COMPILER_FLAG       := -g -D_MO_DEBUG -D_MO_ANDROID -D_MO_X86
MO_COMPILER_FLAG       := -D_MO_ANDROID -D_MO_X86

#............................................................
LOCAL_MODULE           := MoCommon
LOCAL_ARM_MODE         := arm
LOCAL_CFLAGS           := $(MO_COMPILER_FLAG)
LOCAL_CXXFLAGS         := $(MO_COMPILER_FLAG)
LOCAL_CPPFLAGS         := $(MO_COMPILER_FLAG)

#............................................................
LOCAL_SRC_FILES := \

# Runtime
LOCAL_SRC_FILES += \
   ../../FBase.cpp \
   ../../FFree.cpp \
   ../../FObject.cpp \
   ../../FManagedObject.cpp \
   ../../RLogger.cpp \
   ../../RMemory.cpp \
   \
   ../../RBool.cpp \
   ../../RByte.cpp \
   ../../RChar8.cpp \
   ../../RChar16.cpp \
   ../../RChar32.cpp \
   ../../RDouble.cpp \
   ../../RFloat.cpp \
   ../../RInt8.cpp \
   ../../RInt16.cpp \
   ../../RInt32.cpp \
   ../../RInt64.cpp \
   ../../RUint8.cpp \
   ../../RUint16.cpp \
   ../../RUint32.cpp \
   ../../RUint64.cpp \

# Language
LOCAL_SRC_FILES += \
   ../../FClass.cpp \
   ../../FClassConsole.cpp \
   ../../FClassFactory.cpp \
   ../../FClassInstanceFactory.cpp \
   ../../FInstance.cpp \
   ../../RClassManager.cpp \
   ../../TClassInfo.cpp \
   \
   ../../RInt.cpp \
   ../../RUint.cpp \
   ../../RDateTime.cpp \
   ../../RTimeTick.cpp \
   ../../RRandom.cpp \
   ../../RTypeConverter.cpp \
   \
   ../../TFsDateTime.cpp \
   ../../TFsDouble.cpp \
   ../../TFsFloat.cpp \
   ../../TFsInteger.cpp \
   ../../TFsInteger64.cpp \
   \
   ../../TThreadSection.cpp \
   ../../TThreadMutex.cpp \
   ../../TThreadCondition.cpp \
   \
   ../../FAllocatorStorage.cpp \
   ../../FBlockAllocator.cpp \
   ../../FMemoryAllocator.cpp \
   ../../FMemoryLockAllocator.cpp \
   ../../FShareMemory.cpp \
   ../../FThreadMemory.cpp \
   ../../RActivator.cpp \
   ../../RAllocator.cpp \
   ../../RMemoryCapacity.cpp \
   ../../RShareMemory.cpp \
   ../../SMemoryEntry.cpp \
   ../../TShareSegment.cpp \
   \
   ../../FPack.cpp \
   ../../MPack.cpp \
   ../../TProperty.cpp \
   \
   ../../FSharedConsole.cpp \
   ../../FSharedGroup.cpp \
   ../../FSharedListAllocator.cpp \
   ../../MShared.cpp \
   ../../TSharedList.cpp \
   ../../TSharedListIterator.cpp \
   \
   ../../FConsole.cpp \
   ../../FSingletonConsole.cpp \
   ../../MSingleton.cpp \
   ../../RSingletonManager.cpp \
   \
   ../../FByteStream.cpp \
   ../../RInput.cpp \
   ../../ROutput.cpp \
   ../../TDataInput.cpp \
   ../../TDataOutput.cpp \
   ../../TDataReader.cpp \
   ../../TDataWriter.cpp \
   ../../TLinkInput.cpp \
   ../../TLinkOutput.cpp \
   \
   ../../MString8.cpp \
   ../../TString8.cpp \
   ../../FString8.cpp \
   ../../TStringBuffer8.cpp \
   ../../FStringBuffer8.cpp \
   ../../MString8s.cpp \
   ../../MProperties8.cpp \
   ../../RString8.cpp \
   ../../MString16.cpp \
   ../../TString16.cpp \
   ../../FString16.cpp \
   ../../TStringBuffer16.cpp \
   ../../FStringBuffer16.cpp \
   ../../MString16s.cpp \
   ../../MProperties16.cpp \
   ../../RString16.cpp \
   ../../MString32.cpp \
   ../../TString32.cpp \
   ../../FString32.cpp \
   ../../TStringBuffer32.cpp \
   ../../FStringBuffer32.cpp \
   ../../MString32s.cpp \
   ../../MProperties32.cpp \
   ../../RString32.cpp \
   ../../RCharEncoding.cpp \
   ../../RCharSet.cpp \
   \
   ../../FConfigBuilder.cpp \
   ../../FConfigParser.cpp \
   ../../FXmlNode.cpp \
   ../../FXmlNodes.cpp \
   ../../FXmlDocument.cpp \
   ../../FXmlBuilder.cpp \
   ../../FXmlParser.cpp \
   ../../RXml.cpp \
   ../../TXmlNodeIteratorC.cpp \

# Stream
LOCAL_SRC_FILES += \
   ../../FByteFile.cpp \
   ../../FByteFileStream.cpp \
   ../../FFileLines.cpp \
   ../../FFileMapping.cpp \
   ../../FFileStream.cpp \
   ../../FFileString16.cpp \
   ../../FFileString32.cpp \
   ../../FFileString8.cpp \
   ../../FFileStringStream.cpp \
   ../../TFileFinder.cpp \
   ../../TFileInfo.cpp \
   ../../RFile.cpp \

# System
LOCAL_SRC_FILES += \
   ../../FApplicationParameter.cpp \
   ../../FApplicationParameters.cpp \
   ../../FApplication.cpp \
   ../../FApplicationListener.cpp \
   ../../FListener.cpp \
   ../../MApplicationListeners.cpp \
   ../../MListeners.cpp \
   ../../RApplication.cpp \
   ../../RCpu.cpp \
   ../../REnvironment.cpp \
   ../../RProcess.cpp \
   ../../RSystem.cpp \
   ../../TSpeedTest.cpp \
   \
   ../../FLoggerConsole.cpp \
   ../../FLoggerThread.cpp \
   ../../FLoggerWriter.cpp \
   \
   ../../FMonitor.cpp \
   ../../FMonitorCatcher.cpp \
   ../../FMonitorConsole.cpp \
   ../../FMonitorMachine.cpp \
   ../../FMonitorThread.cpp \
   ../../FMonitorTrigger.cpp \
   \
   ../../FTask.cpp \
   ../../FTaskConsole.cpp \
   ../../FTaskInvoker.cpp \
   ../../FTaskThread.cpp \
   \
   ../../FThread.cpp \
   ../../FThreadConsole.cpp \
   ../../FThreadGroup.cpp \
   ../../FThreadPool.cpp \
   ../../FThreadRunable.cpp \
   ../../FThreadWorker.cpp \
   ../../RThread.cpp \
   \
   ../../FCatcher.cpp \
   ../../FCatcherConsole.cpp \
   ../../FThreadCatcher.cpp \
   ../../FThreadTrap.cpp \
   ../../FTrap.cpp \
   ../../RTrap.cpp \
   ../../TTrapper.cpp \

# Net
LOCAL_SRC_FILES += \
   ../../MNetSocket.cpp \
   ../../FNetSocket.cpp \
   ../../FNetClientSocket.cpp \
   ../../FNetServerSocket.cpp \
   ../../FNetUdpClientSocket.cpp \
   ../../FNetUdpServerSocket.cpp \
   ../../FNetSocketStorage.cpp \
   ../../RNetSocket.cpp \
   \
   ../../MoCommon.cpp \

#............................................................
include $(BUILD_STATIC_LIBRARY)
