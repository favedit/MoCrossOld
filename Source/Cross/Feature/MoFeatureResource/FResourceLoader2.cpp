#include "MoFrContent2d.h"

MO_NAMESPACE_BEGIN

////============================================================
//// <T>构造文件加载器。</T>
////============================================================
//FResourceLoader::FResourceLoader(){
//   //_typeCd = EResource_Unknown;
//   //_code = 0;
//}
//
////============================================================
//// <T>析构文件加载器。</T>
////============================================================
//FResourceLoader::~FResourceLoader(){
//}
//
////============================================================
//// <T>数据处理。</T>
////
//// @return 处理结果
////============================================================
//TBool FResourceLoader::Process(){
//   //// 生成文件名称
//   //TFsFileName fileName;
//   //TCharC* pResourceRoot = REnvironmentManager::Instance().FindValue(MO_GM_ENV_RESOURCE_ROOT);
//   //switch(_typeCd){
//   //   case EResource_Picture:
//   //   case EResource_Animation:{
//   //      fileName.AppendFormat("%s/rs/r/%d.swf", pResourceRoot, _code);
//   //      break;
//   //   }
//   //   case EResource_Ground:{
//   //      TFsCode code;
//   //      code.AppendFormat("%d", _code);
//   //      TFsCode mapId = code.MidStrC(1, 3);
//   //      TFsCode layer = code.MidStrC(4, 1);
//   //      TFsCode locationX = code.MidStrC(5, 2);
//   //      TFsCode locationY = code.MidStrC(7, 2);
//   //      fileName.AppendFormat("%s/rs/m/%s/%s_%s_%s.swf", pResourceRoot, (TCharC*)mapId, (TCharC*)layer, (TCharC*)locationX, (TCharC*)locationY);
//   //      break;
//   //   }
//   //   default:{
//   //      MO_STATIC_ERROR(TC("Invalid rsource type. (type_cd=%d, code=%d)"), _typeCd, _code);
//   //      break;
//   //   }
//   //}
//   ////............................................................
//   //// 加载文件数据
//   //FByteFile* pFile = MO_CREATE(FByteFile);
//   //TBool result = pFile->LoadFile(fileName);
//   ////............................................................
//   //if(result){
//   //   MO_DEBUG("Load resource file succress. (code=%d, file_name=%s)", _code, (TCharC*)fileName);
//   //   // 创建资源
//   //   FResource* pResource = NULL;
//   //   switch(_typeCd){
//   //      case EResource_Picture:{
//   //         pResource = MO_CREATE(FPictureResource);
//   //         break;
//   //      }
//   //      case EResource_Ground:{
//   //         pResource = MO_CREATE(FGroundResource);
//   //         break;
//   //      }
//   //      case EResource_Animation:{
//   //         pResource = MO_CREATE(FAnimationResource);
//   //         break;
//   //      }
//   //      default:{
//   //         MO_STATIC_ERROR(TC("Invalid rsource type. (type_cd=%d, code=%d)"), _typeCd, _code);
//   //         break;
//   //      }
//   //   }
//   //   //............................................................
//   //   // 放入资源处理
//   //   if(NULL != pResource){
//   //      pResource->SetCode(_code);
//   //      //pResource->LoadData(pFile->Memory(), pFile->Length());
//   //      RResourceManager::Instance().PushWaitResource(pResource);
//   //   }
//   //}else{
//   //   MO_ERROR("Load resource file failure. (code=%d, file_name=%s)", _code, (TCharC*)fileName);
//   //}
//   ////............................................................
//   //// 释放资源
//   //MO_DELETE(pFile);
//   //return result;
//   return EFalse;
//}

MO_NAMESPACE_END
