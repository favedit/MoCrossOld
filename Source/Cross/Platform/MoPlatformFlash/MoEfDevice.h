#ifndef __MO_EF_DEVICE_H__
#define __MO_EF_DEVICE_H__
//************************************************************

#ifndef __MO_EF_COMMON_H__
#include "MoEfCommon.h"
#endif // __MO_EF_COMMON_H__

MO_NAMESPACE_BEGIN

//============================================================
// <T>舞台对象。</T>
//============================================================
class FEfKeybord : public FEgKeybord{
protected:
   // 尺寸
   SIntSize2 _size;
public:
   FEfKeybord();
   MO_ABSTRACT ~FEfKeybord();
public:
   //------------------------------------------------------------
   // <T>获得大小。</T>
   MO_INLINE SIntSize2& Size(){
      return _size;
   }
};

//============================================================
// <T>舞台对象。</T>
//============================================================
class FEfStage : public FEgStage{
protected:
   // 尺寸
   SIntSize2 _size;
   // 舞台
   flash::display::Stage _pStage;
public:
   FEfStage();
   MO_ABSTRACT ~FEfStage();
public:
   //------------------------------------------------------------
   // <T>获得大小。</T>
   MO_INLINE SIntSize2& Size(){
      return _size;
   }
public:
   MO_OVERRIDE void Setup();
};

//============================================================
// <T>设备管理器。</T>
//============================================================
class FEfDeviceConsole : public FEgDeviceConsole{
public:
   FEfDeviceConsole();
   MO_ABSTRACT ~FEfDeviceConsole();
public:
   MO_OVERRIDE void Setup();
};

MO_NAMESPACE_END

//************************************************************
#endif // __MO_EF_DEVICE_H__
