#include "MoCmFile.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造文件信息。</T>
//============================================================
TFileInfo::TFileInfo(){
}

//============================================================
// <T>构造文件信息。</T>
//
// @param pFileName 文件名称
//============================================================
TFileInfo::TFileInfo(TCharC* pFileName){
   _fileName = pFileName;
   Parse(pFileName);
}

//============================================================
// <T>析构文件信息。</T>
//============================================================
TFileInfo::~TFileInfo(){
}

//============================================================
// <T>获得文件大小。</T>
//
// @return 文件大小
//============================================================
TInt TFileInfo::Size(){
   return RFile::FileSize(_fileName);
}

//============================================================
// <T>获得文件大小。</T>
//
// @return 文件大小
//============================================================
TInt64 TFileInfo::LargeSize(){
   return (TUint64)RFile::FileSize(_fileName);
}

//============================================================
// <T>获得文件创建日期。</T>
//
// @return 创建日期
//============================================================
TDateTime TFileInfo::CreateDate(){
   return 0;
}

//============================================================
// <T>获得文件更新日期。</T>
//
// @return 更新日期
//============================================================
TDateTime TFileInfo::UpdateDate(){
   return 0;
}

//============================================================
// <T>判断是否为文件。</T>
//
// @return 是否为文件
//============================================================
TBool TFileInfo::IsFile(){
   return RFile::ExistFile(_fileName);
}

//============================================================
// <T>判断是否为目录。</T>
//
// @return 是否为目录
//============================================================
TBool TFileInfo::IsDirectory(){
   return RFile::ExistPath(_fileName.MemoryC());
}

//============================================================
// <T>解析文件信息。</T>
//
// @param pFileName 文件名称
// @return 处理结果
//============================================================
TResult TFileInfo::Parse(TCharC* pFileName){
   MO_CHECK(pFileName, return ENull);
   // 替换字符
#ifdef _MO_WINDOWS
   _fileName.Replace('\\', '/');
#endif // _MO_WINDOWS
   // 取盘符
   TInt driverEnd = _fileName.IndexOf(':');
   if(ENotFound != driverEnd){
      _driver = _fileName.LeftStrC(driverEnd);
   }
   // 取路径和文件名
	TFsName oneDirs[MoDirMaxLayer];
	TInt count = 0;
	TInt lastLayerEnd = _fileName.IndexOf('/');
	if(ENotFound != lastLayerEnd){
		// 相对路径（/不在开始且没有盘符）
		if(0 != lastLayerEnd && ENotFound == driverEnd){
			oneDirs[count] = _fileName.LeftStrC(lastLayerEnd);
			count++;
		}
		for(TInt n = 0 ; n < MoDirMaxLayer; n++){
			TInt curLayerEnd = _fileName.IndexOf('/', lastLayerEnd + 1);
			if(ENotFound != curLayerEnd){
				oneDirs[count] = _fileName.SubStrC(lastLayerEnd + 1, curLayerEnd);
				if(oneDirs[count].Equals(TC(".."))){
					--count;
				}else{
					++count;
				}
				lastLayerEnd = curLayerEnd;
			}else{
				break;
			}	
		}
		_name = _fileName.SubStrC(lastLayerEnd + 1, _fileName.Length());
		MO_ASSERT(count >=0 && count < MoDirMaxLayer);
		_path.Clear();
		for(TInt n = 0; n < count; n++){
			_path.Append('/');
			_path.Append((TCharC*)oneDirs[n]);
		}
		_path.Append('/');
	}else{
		_path.Assign(TC("."));
		_name = _fileName;
	}
   // 取扩展名
   TInt extensionStart = _name.LastIndexOf('.');
   if(extensionStart != ENotFound){
      _code = _name.LeftStrC(extensionStart);
      _extension = _name.RightStrC(_name.Length() - extensionStart - 1);
   }else{
      _code = _name;
   }
   // 获得全名称
	_fullName.Clear();
	if(!_driver.IsEmpty()){
		_fullName.Assign(_driver);
		_fullName.Append(':');
	}
	_fullName.Append(_path);
	_fullName.Append(_name);
   return ESuccess;
}

MO_NAMESPACE_END
