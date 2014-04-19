#include "MoFrContent3dScene.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FRs3dSceneMap, FInstance);

//============================================================
// <T>构造场景地图资源。</T>
//============================================================
FRs3dSceneMap::FRs3dSceneMap(){
}

//============================================================
// <T>析构场景地图资源。</T>
//============================================================
FRs3dSceneMap::~FRs3dSceneMap(){
}

//============================================================
// <T>从输入流里反序列化信息内容</T>
//
// @param pInput 输入流
// @return 处理结果
//============================================================
TResult FRs3dSceneMap::Unserialize(IDataInput* pInput){
   // 读取属性
   _name.UnserializeAutomatic(pInput);
   _typeName.UnserializeAutomatic(pInput);
   // 读取显示集合
   TInt displayCount = pInput->ReadInt32();
   for(TInt n = 0; n < displayCount; n++){
      FRs3dSceneDisplay* pDisplay = FRs3dSceneDisplay::InstanceCreate();
      pDisplay->Unserialize(pInput);
      _displays.Push(pDisplay);
   }
   return ESuccess;
}

MO_NAMESPACE_END
