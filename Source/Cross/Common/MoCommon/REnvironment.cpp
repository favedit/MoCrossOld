#include "MoCmSystem.h"

#define MO_ARG_HOME TC("-home")

MO_NAMESPACE_BEGIN

//============================================================
// <T>根据相对路径计算绝对路径。</T>
// <P>根路径是由-home参数传入的。</P>
//
// @param pPath 相对路径
// @param ... 参数
// @return 绝对路径
//============================================================
TFsPath REnvironment::MappingPath(TCharC* pPath, ...){
   MO_ASSERT(pPath);
   TFsPath path;
   // 获得主目录
   TCharC* pHome = RApplication::Instance().Parameters()->FindValue(MO_ARG_HOME);
   if(NULL == pHome){
#ifdef _MO_WINDOWS
      path = RApplication::GetCurrentDirectory();
#else
      MO_ASSERT(pHome);
#endif
   }else{
      path = pHome;
   }
   // 生成路径
   va_list params;
   va_start(params, pPath);
   path.AppendFormatParameters(pPath, params);
   va_end(params);
   // 格式化
   path.Replace('\\', '/');
   TFileInfo fileInfo = (TCharC*)path;
   path.Assign(fileInfo.FullName());
   return path;
}

MO_NAMESPACE_END
