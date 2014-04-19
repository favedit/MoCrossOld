LOCAL_PATH := $(call my-dir)

#------------------------------------------------------------
include $(CLEAR_VARS)

# MO_COMPILER_FLAG       := -g -D_MO_DEBUG -D_MO_ANDROID -D_MO_X86
MO_COMPILER_FLAG       := -D_MO_ANDROID -D_MO_X86

LOCAL_MODULE           := MoFeatureGraphic
LOCAL_ARM_MODE         := arm # thumb(2字节指令)/arm(4字节指令)
LOCAL_CFLAGS           := $(MO_COMPILER_FLAG)
LOCAL_CXXFLAGS         := $(MO_COMPILER_FLAG)
LOCAL_CPPFLAGS         := $(MO_COMPILER_FLAG)
LOCAL_CPP_EXTENSION    := .cpp
#............................................................
LOCAL_C_INCLUDES       := \
   $(LOCAL_PATH)/../../../../Library/MoJpeg \
   $(LOCAL_PATH)/../../../../Library/MoLzma \
   $(LOCAL_PATH)/../../../../Library/MoFreeType \
   $(LOCAL_PATH)/../../../../Common/MoCommon \
   $(LOCAL_PATH)/../../../../Common/MoCore \
   $(LOCAL_PATH)/../../../../Common/MoMath \

#............................................................
LOCAL_SRC_FILES :=

# Common
LOCAL_SRC_FILES += \
   ../../SMatrix3d.cpp \
   ../../SPerspectiveMatrix3d.cpp \
   \
   ../../FDataStream.cpp \
   \
   ../../FGraphicObject.cpp \

# Core
LOCAL_SRC_FILES += \
   ../../FCamera.cpp \
   ../../FOrthoCamera.cpp \
   ../../FPerspectiveCamera.cpp \
   ../../SFloatPlane.cpp \
   ../../SFrustum.cpp \
   ../../SFrustumPlanes.cpp \
   \
   ../../FDirectionalLight.cpp \
   ../../FLight.cpp \
   ../../FPointLight.cpp \
   ../../FSpotLight.cpp \
   \
   ../../FMaterial.cpp \
   ../../FMaterialConsole.cpp \
   ../../FTexture.cpp \
   \
   ../../FOrthoProjection.cpp \
   ../../FPerspectiveProjection.cpp \
   ../../FProjection.cpp \
   ../../FViewport.cpp \

# Graphic
LOCAL_SRC_FILES += \
   ../../FRenderable.cpp \
   ../../FRenderableInfo.cpp \
   ../../REffectSampler.cpp \
   ../../RRenderBlendMode.cpp \
   ../../RRenderCullMode.cpp \
   ../../RRenderDepthMode.cpp \
   ../../RRenderFillMode.cpp \
   ../../RRenderVertexBuffer.cpp \
   ../../RRenderVertexFormat.cpp \
   ../../RRenderSampler.cpp \
   ../../SRenderableDescriptor.cpp \
   \
   ../../FVisualConsole.cpp \
   ../../FVisualMaterial.cpp \
   ../../FVisualNode.cpp \
   ../../FVisualRegion.cpp \
   ../../FVisualThread.cpp \
   ../../FVisualView.cpp \
   \
   ../../FPipelinePass.cpp \
   ../../FPipeline.cpp \
   ../../FPipelineConsole.cpp \
   \
   ../../FRenderCapability.cpp \
   ../../FRenderCubeTexture.cpp \
   ../../FRenderDevice.cpp \
   ../../FRenderFlatTexture.cpp \
   ../../FRenderFragmentShader.cpp \
   ../../FRenderIndexBuffer.cpp \
   ../../FRenderInstance.cpp \
   ../../FRenderObject.cpp \
   ../../FRenderProgram.cpp \
   ../../FRenderRegion.cpp \
   ../../FRenderShader.cpp \
   ../../FRenderSource.cpp \
   ../../FRenderStatistics.cpp \
   ../../FRenderTarget.cpp \
   ../../FRenderTexture.cpp \
   ../../FRenderTextures.cpp \
   ../../FRenderVertexBuffer.cpp \
   ../../FRenderVertexShader.cpp \
   ../../FRenderVertexStream.cpp \
   ../../FRenderVertexStreams.cpp \
   ../../FRenderView.cpp \
   ../../RRenderUtil.cpp \
   \
   ../../FEffect.cpp \
   ../../FEffectConsole.cpp \
   ../../FEffectContext.cpp \
   ../../FStageEffect.cpp \
   ../../FTechnique.cpp \
   ../../FTechniqueConsole.cpp \
   ../../FTechniqueContext.cpp \
   ../../FTechniquePass.cpp \
   ../../SEffectDescriptor.cpp \

# Bitmap
LOCAL_SRC_FILES += \
   ../../FBitmap2d.cpp \
   ../../FBitmapCube.cpp \
   ../../FBitmapData.cpp \
   ../../FDynamicBitmap.cpp \
   ../../FDynamicBitmapCell.cpp \
   ../../FImage.cpp \
   ../../RBitmap.cpp \

# Display
LOCAL_SRC_FILES += \
   ../../FRenderRectangle.cpp \
   ../../FRenderCube.cpp \
   ../../FInstanceRenderable.cpp \
   ../../FDynamicRenderable.cpp \

# Library
LOCAL_SRC_FILES += \
   ../../MoFeatureGraphic.cpp \
#............................................................
include $(BUILD_STATIC_LIBRARY)
