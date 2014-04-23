LOCAL_PATH := $(call my-dir)

#------------------------------------------------------------
include $(CLEAR_VARS)

#............................................................
# MO_COMPILER_FLAG       := -g -D_MO_DEBUG -D_MO_ANDROID -D_MO_X86
MO_COMPILER_FLAG       := -D_MO_ANDROID -D_MO_X86

#............................................................
LOCAL_MODULE           := MoCore
LOCAL_ARM_MODE         := arm
LOCAL_CFLAGS           := $(MO_COMPILER_FLAG)
LOCAL_CXXFLAGS         := $(MO_COMPILER_FLAG)
LOCAL_CPPFLAGS         := $(MO_COMPILER_FLAG)
#............................................................
LOCAL_C_INCLUDES       := \
   $(LOCAL_PATH)/../../../MoCommon \

#............................................................
LOCAL_SRC_FILES :=

# Common
LOCAL_SRC_FILES += \
   ../../TFsGroupId.cpp \
   ../../TFsLinkId.cpp \
   ../../TFsObjectId.cpp \
   ../../TFsRecordId.cpp \
   ../../TFsResourceId.cpp \
   ../../TFsTemplateId.cpp \
   ../../TFsUniqueId.cpp \

# Core
LOCAL_SRC_FILES += \
   ../../FConfigurationConsole.cpp \
   ../../FXmlContainerConsole.cpp \
   \
   ../../FModule.cpp \
   ../../FModuleConsole.cpp \
   \
   ../../FSharedModule.cpp \
   ../../FSharedModuleConsole.cpp \
   \
   ../../FServer.cpp \
   \
   ../../FService.cpp \
   ../../FServiceConsole.cpp \
   \
   ../../FStatistics.cpp \
   ../../FStatisticsMonitor.cpp \
   ../../FStatisticsConsole.cpp \
   \
   ../../MoCrConstant.cpp \

# Device
LOCAL_SRC_FILES += \
   ../../FDevice.cpp \
   ../../FDeviceConsole.cpp \
   ../../FTimerDevice.cpp \

# Logic
LOCAL_SRC_FILES += \
   ../../FEnvironment.cpp \
   ../../FEnvironmentConsole.cpp \
   \
   ../../FInterruptListener.cpp \
   ../../FMessageLogic.cpp \
   ../../FSharedNetSocketClient.cpp \
   \
   ../../FMonitorService.cpp \
   \
   ../../FFeature.cpp \
   ../../FFeatureConsole.cpp \
   \
   ../../FTemplate.cpp \
   ../../FTemplateBuilder.cpp \
   ../../FTemplateConsole.cpp \
   ../../FTemplateContext.cpp \
   ../../FTemplateParser.cpp \
   ../../FTemplateSource.cpp \
   ../../FTemplateTag.cpp \
   ../../FTemplateTagEquals.cpp \
   ../../FTemplateTagFalse.cpp \
   ../../FTemplateTagInclude.cpp \
   ../../FTemplateTagText.cpp \
   ../../FTemplateTagTrue.cpp \
   ../../FTemplateTagWrite.cpp \
   \
   ../../FTimerConsole.cpp \
   ../../RTimerManager.cpp \
   \
   ../../FWorker.cpp \
   ../../FWorkerConsole.cpp \
   ../../FWorkerThread.cpp \

# Net
LOCAL_SRC_FILES += \
   ../../FNetMessageCatcher.cpp \
   ../../FNetMessageConnection.cpp \
   ../../FNetMessageConsole.cpp \
   ../../FNetMessageHandle.cpp \
   ../../FNetMessageHandles.cpp \
   ../../FNetMessageMachine.cpp \
   ../../FNetMessageProcessor.cpp \
   ../../FNetMessageReceiveThread.cpp \
   ../../FNetMessageStatisticsConsole.cpp \
   ../../FNetMessageStatisticsMachine.cpp \
   ../../FNetRouterHandle.cpp \
   ../../FNetRouterHandles.cpp \
   ../../FNetRouterProcessor.cpp \
   ../../FNetTransferHandle.cpp \
   ../../FNetTransferHandles.cpp \
   ../../FNetTransferProcessor.cpp \
   \
   ../../FNetSocketLinkerModule.cpp \
   ../../FNetSocketLinkerStorage.cpp \
   ../../MNetSocketLinker.cpp \
   \
   ../../TNetData.cpp \
   ../../TNetHead.cpp \
   ../../TNetMessage.cpp \
   ../../TNetMessageBuffer.cpp \
   ../../TNetMessageHead.cpp \
   ../../TNetPackage.cpp \
   ../../TNetPackageHead.cpp \
   ../../TNetRouter.cpp \
   ../../TNetRouterHead.cpp \
   ../../TNetTransfer.cpp \
   ../../TNetTransferHead.cpp \
   \
   ../../FNetBufferedClientQueueModule.cpp \
   ../../FNetBufferedPipe.cpp \
   ../../FNetBufferedQueue.cpp \
   ../../FNetBufferedQueueConnection.cpp \
   ../../FNetBufferedQueueModule.cpp \
   ../../FNetPipeBlockPoolModule.cpp \
   ../../FNetQueueBlockPoolModule.cpp \
   ../../FSharedNetPipe.cpp \
   ../../FSharedNetQueue.cpp \
   ../../FSharedNetQueueStorage.cpp \
   \
   ../../FNetBufferedSocket.cpp \
   ../../FNetBufferedSocketModule.cpp \
   ../../FNetClientBufferedSocketModule.cpp \

# Content
LOCAL_SRC_FILES += \
   ../../FAsset.cpp \
   ../../FAssetConsole.cpp \
   ../../FAssetDirectory.cpp \
   ../../FAssetSpace.cpp \
   ../../FAssetStream.cpp \
   \
   ../../TCmdParser.cpp \
   \
   ../../FCsvFile.cpp \
   ../../FCsvFooter.cpp \
   ../../FCsvFooters.cpp \
   ../../FCsvHead.cpp \
   ../../FCsvHeads.cpp \
   ../../FCsvLine.cpp \
   ../../FCsvLines.cpp \
   ../../FCsvSegment.cpp \

# Logic
LOCAL_SRC_FILES += \
   ../../MoCore.cpp \

#............................................................
include $(BUILD_STATIC_LIBRARY)
