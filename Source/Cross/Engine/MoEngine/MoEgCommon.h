#ifndef __MO_EG_COMMON_H__
#define __MO_EG_COMMON_H__
//************************************************************

#ifndef __MO_EG_PUBLIC_H__
#include "MoEgPublic.h"
#endif // __MO_EG_PUBLIC_H__

#define MO_ENG_TAIL_MAXCOUNT 64

#define MO_ENG_GEOM_PI2         (MO_PI_FLOAT * 2)
#define MO_ENG_GEOM_RADIAN_RATE (180.0f / MO_PI_FLOAT)
#define MO_ENG_GEOM_DEGREE_RATE (MO_PI_FLOAT / 180.0f)

MO_NAMESPACE_BEGIN

//============================================================
// <T>改变大小事件。</T>
//============================================================
class SResizeEvent : public SEvent{
public:
   SIntSize2 size;
public:
   //------------------------------------------------------------
   // <T>构造改变大小事件。</T>
   SResizeEvent(TAny* pSender, TInt width, TInt heigh) : SEvent(pSender){
      size.Set(width, heigh);
   }
};
//------------------------------------------------------------
typedef MO_EG_DECLARE TListeners<SResizeEvent> TResizeListeners;

//============================================================
// <T>渲染项目信息。</T>
//============================================================
struct MO_EG_DECLARE SRenderableItem{
public:
   // 坐标
   SFloatPoint3 location;
   // 尺寸
   SFloatSize3 size;
   // 旋转
   SFloatVector3 rotation;
   // 背景颜色
   SFloatColor4 groundColor;
   // 纹理坐标
   SFloatCoord coord;
   // 矩阵
   SFloatMatrix3d matrix;
public:
   //------------------------------------------------------------
   // <T>构造浮点数颜色。</T>
   MO_INLINE SRenderableItem(){
   }
};
//------------------------------------------------------------
typedef MO_EG_DECLARE TFixVector<SRenderableItem, 64> SRenderableItems;

//============================================================
// <T>渲染信息。</T>
//============================================================
struct MO_EG_DECLARE SRenderable{
public:
   SRenderableItems items;
public:
   //------------------------------------------------------------
   // <T>构造浮点数颜色。</T>
   MO_INLINE SRenderable(){
   }
public:
   //------------------------------------------------------------
   // <T>收集一个项目。</T>
   MO_INLINE SRenderableItem& Alloc(){
      TInt index = items.Count();
      items.SetCount(index + 1);
      return items.Get(index);
   }
   //------------------------------------------------------------
   // <T>增加要给项目。</T>
   MO_INLINE void Push(SRenderableItem& item){
      items.Push(item);
   }
};

//============================================================
// <T>空间定义。</T>
//============================================================
struct MO_EG_DECLARE SFloatSpace{
public:
   SFloatPoint3 location;
   SFloatSize3 size;
   SFloatVector3 rotation;
   SFloatColor4 color;
public:
   //------------------------------------------------------------
   // <T>构造矩形定义。</T>
   SFloatSpace(){
   }
public:
   //------------------------------------------------------------
   // <T>设置坐标。</T>
   void SetLocation(TFloat x = 0.0f, TFloat y = 0.0f, TFloat z = 0.0f){
      location.Set(x, y, z);
   }
   //------------------------------------------------------------
   // <T>设置尺寸。</T>
   void SetSize(TFloat width = 0.0f, TFloat height = 0.0f, TFloat deep = 0.0f){
      size.Set(width, height, deep);
   }
   //------------------------------------------------------------
   // <T>设置旋转。</T>
   void SetRotation(TFloat x = 0.0f, TFloat y = 0.0f, TFloat z = 0.0f){
      rotation.Set(x, y, z);
   }
};
//------------------------------------------------------------
typedef MO_EG_DECLARE TFixVector<SFloatSpace, MO_ENG_TAIL_MAXCOUNT> TFloatSpaceTails;

MO_NAMESPACE_END
      
//************************************************************
#endif // __MO_EG_COMMON_H__
