#include "Mo3dsDefine.h"
#include "Mo3dsExporter.h"

MO_NAMESPACE_BEGIN;

//============================================================
void R3dsExporter::StoreColor(FXmlNode* pNode, Color& color){
   pNode->SetFloat(TC("r"), color.r);
   pNode->SetFloat(TC("g"), color.g);
   pNode->SetFloat(TC("b"), color.b);
}

//============================================================
void R3dsExporter::StorePoint2(FXmlNode* pNode, Point2& point){
   pNode->SetFloat(TC("x"), point.x);
   pNode->SetFloat(TC("y"), point.y);
}

//============================================================
void R3dsExporter::StorePoint3(FXmlNode* pNode, Point3& point){
   pNode->SetFloat(TC("x"), point.x);
   pNode->SetFloat(TC("y"), point.y);
   pNode->SetFloat(TC("z"), point.z);
}

//============================================================
void R3dsExporter::StorePoint4(FXmlNode* pNode, Point4& point){
   pNode->SetFloat(TC("x"), point.x);
   pNode->SetFloat(TC("y"), point.y);
   pNode->SetFloat(TC("z"), point.z);
   pNode->SetFloat(TC("w"), point.w);
}

//============================================================
void R3dsExporter::StoreAngAxis(FXmlNode* pNode, AngAxis& axis){
   pNode->SetFloat(TC("angle"), axis.angle);
   pNode->SetFloat(TC("x"), axis.axis.x);
   pNode->SetFloat(TC("y"), axis.axis.y);
   pNode->SetFloat(TC("z"), axis.axis.z);
}

//============================================================
void R3dsExporter::StoreQuat(FXmlNode* pNode, Quat& quat){
   pNode->SetFloat(TC("x"), quat.x);
   pNode->SetFloat(TC("y"), quat.y);
   pNode->SetFloat(TC("z"), quat.z);
   pNode->SetFloat(TC("w"), quat.w);
}

//============================================================
void R3dsExporter::StoreScale(FXmlNode* pNode, ScaleValue& scale){
   pNode->SetFloat(TC("x"), scale.s.x);
   pNode->SetFloat(TC("y"), scale.s.y);
   pNode->SetFloat(TC("z"), scale.s.z);
   pNode->SetFloat(TC("axis_x"), scale.q.x);
   pNode->SetFloat(TC("axis_y"), scale.q.y);
   pNode->SetFloat(TC("axis_z"), scale.q.z);
   pNode->SetFloat(TC("axis_w"), scale.q.w);
}

//============================================================
void R3dsExporter::StoreMatrix3(FXmlNode* pNode, TCharC* pName, Matrix3& matrix){
   TFsText text;
   text.AppendFormat(TC("%f,%f,%f,"), matrix.GetRow(0).x, matrix.GetRow(0).y, matrix.GetRow(0).z);
   text.AppendFormat(TC("%f,%f,%f,"), matrix.GetRow(1).x, matrix.GetRow(1).y, matrix.GetRow(1).z);
   text.AppendFormat(TC("%f,%f,%f"), matrix.GetRow(2).x, matrix.GetRow(2).y, matrix.GetRow(2).z);
   pNode->Set(pName, text.MemoryC());
}

//============================================================
// <T>保存矩阵信息。</T>
//============================================================
void R3dsExporter::StoreGameMatrix(FXmlNode* pNode, TCharC* pName, GMatrix& matrix){
   TFsText text;
   text.AppendFormat(TC("%f,%f,%f,"), matrix.GetRow(0).x, matrix.GetRow(0).y, matrix.GetRow(0).z);
   text.AppendFormat(TC("%f,%f,%f,"), matrix.GetRow(1).x, matrix.GetRow(1).y, matrix.GetRow(1).z);
   text.AppendFormat(TC("%f,%f,%f"), matrix.GetRow(2).x, matrix.GetRow(2).y, matrix.GetRow(2).z);
   pNode->Set(pName, text.MemoryC());
}

//============================================================
// <T>保存矩阵简要信息。</T>
//============================================================
void R3dsExporter::StoreGameMatrixSimple(FXmlNode* pNode, GMatrix& matrix){
   // 保存位移变换
   Point3& translation = matrix.Translation();
   pNode->SetFloat(TC("tx"), translation.x);
   pNode->SetFloat(TC("ty"), translation.y);
   pNode->SetFloat(TC("tz"), translation.z);
   // 保存四元数旋转信息
   Quat& rotation = matrix.Rotation();
   pNode->SetFloat(TC("qx"), rotation.x);
   pNode->SetFloat(TC("qy"), rotation.y);
   pNode->SetFloat(TC("qz"), rotation.z);
   pNode->SetFloat(TC("qw"), rotation.w);
   // 保存欧拉角旋转信息
   TFloat rx;
   TFloat ry;
   TFloat rz;
   rotation.GetEuler(&rx, &ry, &rz);
   pNode->SetFloat(TC("rx"), rx * 180 / 3.1415926f);
   pNode->SetFloat(TC("ry"), ry * 180 / 3.1415926f);
   pNode->SetFloat(TC("rz"), rz * 180 / 3.1415926f);
   // 保存缩放信息
   Point3& scaling = matrix.Scaling();
   pNode->SetFloat(TC("sx"), scaling.x);
   pNode->SetFloat(TC("sy"), scaling.y);
   pNode->SetFloat(TC("sz"), scaling.z);
}

//============================================================
// <T>保存矩阵详细信息。</T>
//============================================================
void R3dsExporter::StoreGameMatrixFull(FXmlNode* pNode, GMatrix& matrix){
   // 保存位移变换
   StorePoint3(pNode->CreateNode(TC("Translation")), matrix.Translation());
   // 保存四元数旋转信息
   Quat rotation = matrix.Rotation();
   TFloat rx;
   TFloat ry;
   TFloat rz;
   StoreQuat(pNode->CreateNode(TC("Rotation")), rotation);
   // 保存欧拉角旋转信息
   rotation.GetEuler(&rx, &ry, &rz);
   FXmlNode* pEulerNode = pNode->CreateNode(TC("Euler"));
   pEulerNode->SetFloat(TC("x"), rx);
   pEulerNode->SetFloat(TC("y"), ry);
   pEulerNode->SetFloat(TC("z"), rz);
   pEulerNode->SetFloat(TC("ax"), rx * 180 / 3.1415926f);
   pEulerNode->SetFloat(TC("ay"), ry * 180 / 3.1415926f);
   pEulerNode->SetFloat(TC("az"), rz * 180 / 3.1415926f);
   //rotation.x = -rotation.x;
   //rotation.y = -rotation.y;
   //rotation.z = -rotation.z;
   //pEulerNode->SetFloat("ax", atan2(2*(rotation.w * rotation.x + rotation.y * rotation.z), 1 - 2 * (rotation.x * rotation.x + rotation.y * rotation.y)));
   //pEulerNode->SetFloat("ay", asin(2*(rotation.w * rotation.y - rotation.z * rotation.x)));
   //pEulerNode->SetFloat("az", atan2(2*(rotation.w * rotation.z + rotation.x * rotation.y), 1 - 2 * (rotation.y * rotation.y + rotation.z * rotation.z)));
   // 保存缩放信息
   StorePoint3(pNode->CreateNode(TC("Scale")), matrix.Scaling());
}

//============================================================
TBool R3dsExporter::StoreColorProperty(FXmlNode* pNode, IGameProperty* piProperty){
   // 判断变量
   if(NULL == pNode || NULL == piProperty){
      return EFalse;
   }
   // 根据类型处理
   switch(piProperty->GetType()){
      case IGAME_POINT3_PROP:{
         Point3 value;
         piProperty->GetPropertyValue(value);
         pNode->SetFloat(TC("r"), value.x);
         pNode->SetFloat(TC("g"), value.y);
         pNode->SetFloat(TC("b"), value.z);
         return ETrue;
      }
      case IGAME_POINT4_PROP:{
         Point4 value;
         piProperty->GetPropertyValue(value);
         pNode->SetFloat(TC("a"), value.w);
         pNode->SetFloat(TC("r"), value.x);
         pNode->SetFloat(TC("g"), value.y);
         pNode->SetFloat(TC("b"), value.z);
         return ETrue;
      }
   }
   return EFalse;
}

//============================================================
// <T>存储属性信息。</T>
//
// @param pNode 节点
// @param pName 名称
// @param piProperty 属性对象
// @return 处理结果
//============================================================
TBool R3dsExporter::StoreProperty(FXmlNode* pNode, TCharC* pName, IGameProperty* piProperty){
   // 检查参数
   if((pNode == NULL) || (piProperty == NULL)){
      return EFalse;
   }
   // 存储属性
   PropType typeCd = piProperty->GetType();
   switch(typeCd){
      case IGAME_INT_PROP:{
         TInt32 value;
         pNode->Set(TC("type"), TC("int"));
         if(piProperty->GetPropertyValue(value)){
            pNode->SetInt(pName, value);
         }
         return ETrue;
      }
      case IGAME_FLOAT_PROP:{
         TFloat value;
         pNode->Set(TC("type"), TC("float"));
         if(piProperty->GetPropertyValue(value)){
            pNode->SetFloat(pName, value);
         }
         return ETrue;
      }
      case IGAME_POINT3_PROP:{
         Point3 value;
         pNode->Set(TC("type"), TC("point3"));
         if(piProperty->GetPropertyValue(value)){
            StorePoint3(pNode, value);
         }
         return ETrue;
      }
      case IGAME_POINT4_PROP:{
         Point4 value;
         pNode->Set(TC("type"), TC("point4"));
         if(piProperty->GetPropertyValue(value)){
            StorePoint4(pNode, value);
         }
         return ETrue;
      }
      case IGAME_STRING_PROP:{
         TCharC* pValue;
         pNode->Set(TC("type"), TC("string"));
         if(piProperty->GetPropertyValue(pValue)){
            pNode->Set(pName, pValue);
         }
         return ETrue;
      }
      default:{
         pNode->Set(TC("type"), TC("unkown"));
         return ETrue;
      }
   }
   return EFalse;
}

//============================================================
// <T>存储属性集合。</T>
//
// @param pNode 节点
// @param pName 名称
// @param piContainer 属性集合
// @return 处理结果
//============================================================
TBool R3dsExporter::StoreProperties(FXmlNode* pNode, TCharC* pName, IPropertyContainer* piContainer){
   // 检查参数
   if((pNode == NULL) || (piContainer == NULL)){
      return EFalse;
   }
   // 存储属性集合
   int count = piContainer->GetNumberOfProperties();
   if(count > 0){
      FXmlNode* pPropertiesNode = pNode->CreateNode(TC("Properties"));
      pPropertiesNode->SetInt(TC("count"), count);
      for(TInt32 n = 0; n < count; n++){
         IGameProperty* piProperty = piContainer->GetProperty(n);
         FXmlNode* pPropertyNode = pPropertiesNode->CreateNode(TC("Property"));
         pPropertyNode->SetInt(TC("index"), n);
         if (piProperty != NULL){
            pPropertyNode->Set(TC("name"), piProperty->GetName());
            StoreProperty(pPropertyNode, TC("value"), piProperty);
         }
      }
   }
   return ETrue;
}

//============================================================
// <T>保存矩阵简要信息。</T>
//============================================================
void R3dsExporter::SerializeMatrix(IDataOutput* pOutput, GMatrix& matrix){
   // 保存位移变换
   Point3 translation = matrix.Translation();
   pOutput->WriteFloat(translation.x);
   pOutput->WriteFloat(translation.y);
   pOutput->WriteFloat(translation.z);
   // 保存欧拉角旋转信息
   Quat& rotation = matrix.Rotation();
   TFloat rx;
   TFloat ry;
   TFloat rz;
   rotation.GetEuler(&rx, &ry, &rz);
   pOutput->WriteFloat(rx * 180 / 3.1415926f);
   pOutput->WriteFloat(ry * 180 / 3.1415926f);
   pOutput->WriteFloat(rz * 180 / 3.1415926f);
   pOutput->WriteFloat(rotation.x);
   pOutput->WriteFloat(rotation.y);
   pOutput->WriteFloat(rotation.z);
   pOutput->WriteFloat(rotation.w);
   // 保存缩放信息
   Point3 scaling = matrix.Scaling();
   pOutput->WriteFloat(scaling.x);
   pOutput->WriteFloat(scaling.y);
   pOutput->WriteFloat(scaling.z);
}

MO_NAMESPACE_END;
