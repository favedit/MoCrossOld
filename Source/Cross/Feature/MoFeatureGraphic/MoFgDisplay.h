#ifndef __MO_FG_DISPLAY_H__
#define __MO_FG_DISPLAY_H__
//************************************************************

#ifndef __MO_FG_COMMON_H__
#include "MoFgCommon.h"
#endif // __MO_FG_COMMON_H__

#ifndef __MO_FG_GRAPHIC_H__
#include "MoFgGraphic.h"
#endif // __MO_FG_GRAPHIC_H__

MO_NAMESPACE_BEGIN

//============================================================
// <T>实体3D几何体。</T>
//============================================================
class MO_FG_DECLARE FRenderRectangle : public FRenderable
{
   MO_CLASS_DECLARE_INHERITS(FRenderRectangle, FRenderable);
protected:
   SFloatPoint2 _location;
   SFloatSize2 _size;
   FRenderVertexBuffer* _pVertexBuffer;
public:
   FRenderRectangle();
   MO_ABSTRACT ~FRenderRectangle();
public:
   //------------------------------------------------------------
   // <T>获得位置。</T>
   MO_INLINE SFloatPoint2& Location(){
      return _location;
   }
   //------------------------------------------------------------
   // <T>获得大小。</T>
   MO_INLINE SFloatSize2& Size(){
      return _size;
   }
   //------------------------------------------------------------
   // <T>获得渲染顶点缓冲。</T>
   MO_INLINE FRenderVertexBuffer* VertexBuffer(){
      return _pVertexBuffer;
   }
public:
   MO_OVERRIDE TResult Setup();
};

//============================================================
// <T>实体3D几何体。</T>
//============================================================
class MO_FG_DECLARE FRenderCube : public FRenderable
{
   MO_CLASS_DECLARE_INHERITS(FRenderCube, FRenderable);
public:
   FRenderCube();
   MO_ABSTRACT ~FRenderCube();
public:
   MO_OVERRIDE TResult Setup();
};

//============================================================
// <T>实例渲染对象。</T>
//============================================================
class MO_FG_DECLARE FInstanceRenderable : public FRenderable
{
   MO_CLASS_DECLARE_INHERITS(FInstanceRenderable, FRenderable);
protected:
   GPtr<FRenderVertexBuffer> _vertexBuffer;
   GPtr<FRenderable> _renderable;
   TInt _instanceCount;
public:
   FInstanceRenderable();
   MO_ABSTRACT ~FInstanceRenderable();
public:
   //------------------------------------------------------------
   // <T>获得渲染对象。</T>
   MO_INLINE FRenderable* Renderable(){
      return _renderable;
   }
   //------------------------------------------------------------
   // <T>设置渲染对象。</T>
   MO_INLINE void SetRenderable(FRenderable* pRenderable){
      _renderable = pRenderable;
   }
   //------------------------------------------------------------
   // <T>获得实例个数。</T>
   MO_INLINE TInt InstanceCount(){
      return _instanceCount;
   }
   //------------------------------------------------------------
   // <T>设置实例个数。</T>
   MO_INLINE void SetInstanceCount(TInt instanceCount){
      _instanceCount = instanceCount;
   }
public:
   MO_OVERRIDE TResult Setup();
};
//------------------------------------------------------------
typedef MO_FG_DECLARE FDictionary<FInstanceRenderable*> FInstanceRenderableDictionary;

//============================================================
// <T>动态渲染对象。</T>
//============================================================
class MO_FG_DECLARE FDynamicRenderable : public FRenderable
{
   MO_CLASS_DECLARE_INHERITS(FDynamicRenderable, FRenderable);
protected:
   TInt _vertexTotal;
   TInt _indexTotal;
   GPtrs<FRenderable> _renderables;
public:
   FDynamicRenderable();
   MO_ABSTRACT ~FDynamicRenderable();
public:
   //------------------------------------------------------------
   // <T>获得渲染对象集合。</T>
   MO_INLINE GPtrs<FRenderable>& Renderables(){
      return _renderables;
   }
public:
   FRenderVertexBuffer* SyncVertexBuffer(FRenderVertexBuffer* pBuffer);
   FRenderVertexStream* SyncVertexStream(FRenderVertexStream* pStream);
public:
   TBool TestMergeable(FRenderable* pRenderable);
   TResult Push(FRenderable* pRenderable);
public:
   MO_OVERRIDE TResult Setup();
};
//------------------------------------------------------------
typedef MO_FG_DECLARE GPtrs<FDynamicRenderable> GDynamicRenderablePtrs;

MO_NAMESPACE_END

//************************************************************
#endif // __MO_FG_DISPLAY_H__
