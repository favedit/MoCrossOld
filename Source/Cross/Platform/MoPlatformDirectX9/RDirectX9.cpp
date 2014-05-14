#include "MoPd9Core.h"

MO_NAMESPACE_BEGIN
//
////============================================================
//// <T>转换填充模式类型。</T>
////
//// @param fillCd 填充模式
//// @return 处理结果
////============================================================
//D3D10_FILL_MODE RDirectX10::ConvertFillMode(ERenderFillMode fillCd){
//   switch(fillCd){
//      case ERenderFillMode_Line:
//         return D3D10_FILL_WIREFRAME;
//      case ERenderFillMode_Fill:
//         return D3D10_FILL_SOLID;
//   }
//   MO_STATIC_FATAL("Convert fill mode failure. (fill=%d)", fillCd);
//   return D3D10_FILL_SOLID;
//}
//
////============================================================
//// <T>转换剔除模式类型。</T>
////
//// @param cullCd 剔除模式
//// @return 处理结果
////============================================================
//D3D10_CULL_MODE RDirectX10::ConvertCullMode(ERenderCullMode cullCd){
//   switch(cullCd){
//      case ERenderCullMode_Front:
//         return D3D10_CULL_FRONT;
//      case ERenderCullMode_Back:
//         return D3D10_CULL_BACK;
//      case ERenderCullMode_Both:
//         return D3D10_CULL_NONE;
//   }
//   MO_STATIC_FATAL("Convert cull mode failure. (cull=%d)", cullCd);
//   return D3D10_CULL_NONE;
//}

////============================================================
//// <T>转换索引宽度类型。</T>
////
//// @param strideCd 索引宽度
//// @return 处理结果
////============================================================
//DXGI_FORMAT RDirectX9::ConvertIndexStride(ERenderIndexStride strideCd){
//   switch(strideCd){
//      case ERenderIndexStride_Uint16:
//         return DXGI_FORMAT_R16_UINT;
//      case ERenderIndexStride_Uint32:
//         return DXGI_FORMAT_R32_UINT;
//   }
//   MO_STATIC_FATAL("Convert index stride failure. (stride=%d)", strideCd);
//   return DXGI_FORMAT_UNKNOWN;
//}
//
////============================================================
//// <T>解析属性类型。</T>
////
//// @param componentType 组件宽度
//// @param mask 遮蔽描述
//// @return 处理结果
////============================================================
//ERenderShaderAttributeFormat RDirectX9::ParseAttrbuteFormat(D3D_REGISTER_COMPONENT_TYPE componentType, TInt mask){
//   // 判断位数
//   TInt count = 0;
//   if(mask & 0x01){
//      count++;
//   }
//   if(mask & 0x02){
//      count++;
//   }
//   if(mask & 0x04){
//      count++;
//   }
//   if(mask & 0x08){
//      count++;
//   }
//   // 解析类型
//   switch(componentType){
//      case D3D_REGISTER_COMPONENT_UINT32:
//         break;
//      case D3D_REGISTER_COMPONENT_SINT32:
//         break;
//      case D3D_REGISTER_COMPONENT_FLOAT32:{
//         if(count == 1){
//            return ERenderShaderAttributeFormat_Float1;
//         }else if(count == 2){
//            return ERenderShaderAttributeFormat_Float2;
//         }else if(count == 3){
//            return ERenderShaderAttributeFormat_Float3;
//         }else if(count == 4){
//            return ERenderShaderAttributeFormat_Float4;
//         }
//      }
//   }
//   MO_STATIC_FATAL("Parse attribtue format failure. (component_type=%d)", componentType);
//   return ERenderShaderAttributeFormat_Unknown;
//}

//============================================================
// <T>转换属性格式。</T>
//
// @param formatCd 格式
// @return 属性格式
//============================================================
D3DDECLTYPE RDirectX9::ConvertAttrbuteFormat(ERenderShaderAttributeFormat formatCd){
   switch(formatCd){
      case ERenderShaderAttributeFormat_Float1:
         return D3DDECLTYPE_FLOAT1;
      case ERenderShaderAttributeFormat_Float2:
         return D3DDECLTYPE_FLOAT2;
      case ERenderShaderAttributeFormat_Float3:
         return D3DDECLTYPE_FLOAT3;
      case ERenderShaderAttributeFormat_Float4:
         return D3DDECLTYPE_FLOAT4;
      case ERenderShaderAttributeFormat_Byte4:
         return D3DDECLTYPE_UBYTE4;
      case ERenderShaderAttributeFormat_Byte4Normal:
         return D3DDECLTYPE_UBYTE4N;
   }
   MO_STATIC_FATAL("Convert attribute format failure. (format=%d)", formatCd);
   return D3DDECLTYPE_UNUSED;
}

//============================================================
// <T>转换属性格式。</T>
//
// @param formatCd 格式
// @return 属性格式
//============================================================
TResult RDirectX9::ConvertAttrbuteUsage(TInt index, D3DDECLUSAGE* pUsageCd, TInt* pUsageIndex){
   switch(index){
      case 0:
         *pUsageCd = D3DDECLUSAGE_POSITION;
         *pUsageIndex = 0;
         return ESuccess;
      case 1:
         *pUsageCd = D3DDECLUSAGE_TEXCOORD;
         *pUsageIndex = 0;
         return ESuccess;
      case 2:
         *pUsageCd = D3DDECLUSAGE_TEXCOORD;
         *pUsageIndex = 1;
         return ESuccess;
      case 3:
         *pUsageCd = D3DDECLUSAGE_TEXCOORD;
         *pUsageIndex = 2;
         return ESuccess;
      case 4:
         *pUsageCd = D3DDECLUSAGE_TEXCOORD;
         *pUsageIndex = 3;
         return ESuccess;
      case 5:
         *pUsageCd = D3DDECLUSAGE_TEXCOORD;
         *pUsageIndex = 4;
         return ESuccess;
      case 6:
         *pUsageCd = D3DDECLUSAGE_TEXCOORD;
         *pUsageIndex = 5;
         return ESuccess;
      case 7:
         *pUsageCd = D3DDECLUSAGE_TEXCOORD;
         *pUsageIndex = 6;
         return ESuccess;
      case 8:
         *pUsageCd = D3DDECLUSAGE_TEXCOORD;
         *pUsageIndex = 7;
         return ESuccess;
   }
   MO_STATIC_FATAL("Convert attribute usage failure. (index=%d)", index);
   *pUsageCd = D3DDECLUSAGE_POSITION;
   *pUsageIndex = 0;
   return EFailure;
}

MO_NAMESPACE_END
