#include "MoCmGeom.h"
#include "MoCmLanguage.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>从输入流里反序列化信息内容</T>
//
// @param pInput 输入流
// @return 处理结果
//============================================================
TResult S{type_name}Matrix3d::Serialize(IDataOutput* pOutput){
   // 写入平移信息
   pOutput->Write{type_name}(tx);
   pOutput->Write{type_name}(ty);
   pOutput->Write{type_name}(tz);
   // 写入旋转弧度
   pOutput->Write{type_name}(rx);
   pOutput->Write{type_name}(ry);
   pOutput->Write{type_name}(rz);
   // 读取缩放信息
   pOutput->Write{type_name}(sx);
   pOutput->Write{type_name}(sy);
   pOutput->Write{type_name}(sz);
   return ESuccess;
}

//============================================================
// <T>从输入流里反序列化信息内容</T>
//
// @param pInput 输入流
// @return 处理结果
//============================================================
TResult S{type_name}Matrix3d::Unserialize(IDataInput* pInput){
   // 读取平移信息
   tx = pInput->Read{type_name}();
   ty = pInput->Read{type_name}();
   tz = pInput->Read{type_name}();
   // 读取旋转弧度
   rx = pInput->Read{type_name}();
   ry = pInput->Read{type_name}();
   rz = pInput->Read{type_name}();
   // 读取缩放信息
   sx = pInput->Read{type_name}();
   sy = pInput->Read{type_name}();
   sz = pInput->Read{type_name}();
   // 更新处理
   Update();
   return ESuccess;
}

MO_NAMESPACE_END
