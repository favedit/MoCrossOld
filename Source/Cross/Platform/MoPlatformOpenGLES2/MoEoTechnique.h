#ifndef __MO_EO_TECHNIQUE_H__
#define __MO_EO_TECHNIQUE_H__
//************************************************************

#ifndef __MO_EO_COMMON_H__
#include "MoEoCommon.h"
#endif // __MO_EO_COMMON_H__

#ifndef __MO_EO_RENDER_H__
#include "MoEoRender.h"
#endif // __MO_EO_RENDER_H__

MO_NAMESPACE_BEGIN

//============================================================
// <T>显示颜色渲染器。</T>
//============================================================
class MO_EO_DECLARE FEo2dColorEffect : public FEffect{
protected:
   TInt _limitCount;
   TInt _count;
   SFloatMatrix3d _mvpMatrix;
   FEoRenderVertexBuffer* _pVertexPositionBuffer;
   FDataStream* _pPositionData;
   FEoRenderVertexBuffer* _pVertexColorBuffer;
   FDataStream* _pColorData;
   FEoRenderIndexBuffer* _pIndexBuffer;
public:
   FEo2dColorEffect();
   MO_ABSTRACT ~FEo2dColorEffect();
public:
   //------------------------------------------------------------
   // <T>获得限制个数。</T>
   MO_INLINE TInt LimitCount(){
      return _limitCount;
   }
   //------------------------------------------------------------
   // <T>获得顶点位置缓冲。</T>
   MO_INLINE FEoRenderVertexBuffer* VertexPositionBuffer(){
      return _pVertexPositionBuffer;
   }
   //------------------------------------------------------------
   // <T>获得顶点颜色缓冲。</T>
   MO_INLINE FEoRenderVertexBuffer* VertexColorBuffer(){
      return _pVertexColorBuffer;
   }
   //------------------------------------------------------------
   // <T>获得索引缓冲。</T>
   MO_INLINE FEoRenderIndexBuffer* IndexBuffer(){
      return _pIndexBuffer;
   }
public:
   MO_OVERRIDE TResult Setup();
public:
   MO_ABSTRACT TResult UpdateBegin();
   MO_ABSTRACT TResult WriteRenderable(FRenderable* pRenderable);
   MO_ABSTRACT TResult UpdateEnd();
public:
   MO_OVERRIDE TResult Resize(TInt width, TInt height);
   MO_OVERRIDE TResult Draw();
};

//============================================================
// <T>显示渲染技术。</T>
//============================================================
class MO_EO_DECLARE FEo2dTextureEffect : public FEffect{
protected:
   TInt _limitCount;
   TInt _count;
   SFloatMatrix3d _mvpMatrix;
   GLint _effectAttributes[EEffectVertexAttribute_Count];
   GLint _effectSamplers[EEffectSampler_Count];
   FEoRenderVertexBuffer* _pVertexPositionBuffer;
   FDataStream* _pPositionData;
   FEoRenderVertexBuffer* _pVertexColorBuffer;
   FDataStream* _pColorData;
   FEoRenderVertexBuffer* _pVertexCoordBuffer;
   FDataStream* _pCoordData;
   FEoRenderIndexBuffer* _pIndexBuffer;
public:
   FEo2dTextureEffect();
   MO_ABSTRACT ~FEo2dTextureEffect();
public:
   //------------------------------------------------------------
   // <T>获得顶点位置缓冲。</T>
   MO_INLINE FEoRenderVertexBuffer* VertexPositionBuffer(){
      return _pVertexPositionBuffer;
   }
   //------------------------------------------------------------
   // <T>获得顶点颜色缓冲。</T>
   MO_INLINE FEoRenderVertexBuffer* VertexColorBuffer(){
      return _pVertexColorBuffer;
   }
   //------------------------------------------------------------
   // <T>获得顶点坐标缓冲。</T>
   MO_INLINE FEoRenderVertexBuffer* VertexCoordBuffer(){
      return _pVertexCoordBuffer;
   }
   //------------------------------------------------------------
   // <T>获得索引缓冲。</T>
   MO_INLINE FEoRenderIndexBuffer* IndexBuffer(){
      return _pIndexBuffer;
   }
public:
   MO_OVERRIDE TResult Setup();
public:
   MO_ABSTRACT TResult UpdateBegin();
   MO_ABSTRACT TResult WriteRenderable(FRenderable* pRenderable);
   MO_ABSTRACT TResult UpdateEnd();
public:
   MO_OVERRIDE TResult Resize(TInt width, TInt height);
   MO_OVERRIDE TResult Draw();
};

//============================================================
// <T>显示渲染技术。</T>
//============================================================
class MO_EO_DECLARE FEo3dTextureEffect : public FEffect{
public:
   FEo3dTextureEffect();
   MO_ABSTRACT ~FEo3dTextureEffect();
public:
   MO_OVERRIDE TResult Setup();
public:
   MO_OVERRIDE TResult Resize(TInt width, TInt height);
   MO_OVERRIDE TResult Draw(FRenderable* pRenderable);
   MO_OVERRIDE TResult DrawGroup(FRenderRegion* pRegion, TInt offset, TInt count);
};

//============================================================
// <T>显示渲染程序。</T>
//============================================================
class MO_EO_DECLARE FEo3dSkeleton4Program : public FEoRenderProgram{
protected:
   TInt _boneCount;
   TInt _weightCount;
	GLint _vertexConstMvpMatrix;
   GLint _vertexConstBoneMatrixs;
   GLint _attributePosition;
   GLint _attributeColor;
   GLint _attributeCoord;
   GLint _attributeBoneIndex;
   GLint _attributeBoneWeight;
   GLint _samplerColor;
   FByteStream* _pBoneStream;
public:
   FEo3dSkeleton4Program();
   MO_ABSTRACT ~FEo3dSkeleton4Program();
public:
   //------------------------------------------------------------
   // <T>获得骨头数量。</T>
   MO_INLINE TInt BoneCount(){
      return _boneCount;
   }
   //------------------------------------------------------------
   // <T>设置骨头数量。</T>
   MO_INLINE void SetBoneCount(TInt boneCount){
      _boneCount = boneCount;
   }
   //------------------------------------------------------------
   // <T>获得权重数量。</T>
   MO_INLINE TInt WeightCount(){
      return _weightCount;
   }
   //------------------------------------------------------------
   // <T>设置权重数量。</T>
   MO_INLINE void SetWeightCount(TInt weightCount){
      _weightCount = weightCount;
   }
public:
   MO_OVERRIDE TResult MakeVertexSource(FRenderSource* pSource);
   MO_OVERRIDE TResult MakeFragmentSource(FRenderSource* pSource);
public:
   MO_OVERRIDE TResult Build();
};
//------------------------------------------------------------
typedef MO_EO_DECLARE FVector<FEo3dSkeleton4Program*> FEo3dSkeleton4ProgramVector;

//============================================================
// <T>显示渲染技术。</T>
//============================================================
class MO_EO_DECLARE FEo3dSkeleton4Effect : public FEffect{
protected:
   FEo3dSkeleton4ProgramVector* _pPrograms;
   SFloatMatrix3d _mvpMatrix;
   SFloatMatrix3d _vpMatrix;
   GLint _effectAttributes[EEffectVertexAttribute_Count];
   GLint _effectSamplers[EEffectSampler_Count];
   FByteStream* _pBoneStream;
public:
   FEo3dSkeleton4Effect();
   MO_ABSTRACT ~FEo3dSkeleton4Effect();
public:
   MO_OVERRIDE TResult Setup();
public:
   MO_OVERRIDE TResult Resize(TInt width, TInt height);
   MO_OVERRIDE TResult Draw(FRenderRegion* pRegion, FRenderable* pRenderable);
   MO_OVERRIDE TResult DrawGroup(FRenderRegion* pRegion, TInt offset, TInt count);
};

MO_NAMESPACE_END

//************************************************************
#endif // __MO_EG_TECHNIQUE_H__
