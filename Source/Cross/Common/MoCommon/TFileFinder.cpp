#ifdef _MO_LINUX
#include <dirent.h>
#endif
#include "MoCmFile.h"

MO_NAMESPACE_BEGIN

//============================================================
TFileFinder::TFileFinder(TCharC* pDirectory){
	_path = pDirectory;
	_cpath = pDirectory;
	_cfile = TC("");
	_cfull = TC("");
#ifdef _MO_WINDOWS
   _dir = NULL;
#endif // _MO_WINDOWS
#ifdef _MO_LINUX
	_cur = -1;
	_dcount = 0;
	_dents = 0;
#endif // _MO_LINUX
}

//============================================================
TFileFinder::~TFileFinder(){
	OsClose();
}

//============================================================
TCharC* TFileFinder::FullName(){
	return _cfull.MemoryC();
}

//============================================================
TCharC* TFileFinder::FileName(){
	return _cfile.MemoryC();
}

//============================================================
TCharC* TFileFinder::Path(){
	return _cpath.MemoryC();
}

//============================================================
TBool TFileFinder::Reset(){
	OsClose();
	_cpath = _path;
	_cfile = TC("");
	_cfull = TC("");
	return ETrue;
}

//============================================================
void TFileFinder::Restart(){
	_cpath = _path;
	_cfile = TC("");
	_cfull = TC("");
#ifdef _MO_LINUX
	_cur = -1;
#endif
}

//============================================================
TBool TFileFinder::Next(){
	return OsWalk();
}

#ifdef _MO_LINUX
//============================================================
TBool TFileFinder::OsWalk(){
	TChar buf[MO_FS_PATH_LENGTH];
	/*if(!_dir && !(_dir = opendir(_path.MemoryC()))) {
		return EFalse;
	}
	struct dirent ent, *ret;
	while(!(readdir_r(_dir, &ent, &ret))) {
		if(!ret)
			break;
		strcpy(buf, _path.MemoryC());
		strcat(buf, "/");
		strcat(buf, ent.d_name);
		if(ent.d_type == DT_REG)
			break;
	}
	if(ret) {
		_cfile = ent.d_name;
		_cfull = buf;
		return ETrue;
	}
	closedir(_dir);
	_dir = 0;*/
	if(!_dents) {
		if((_dcount = scandir(_path.MemoryC(), &_dents, Filter, alphasort)) < 0) {
			perror("scandir");
			_dents = 0;
			return EFalse;
		}
	}
	if(++_cur < _dcount) {
		struct dirent *dirp = _dents[_cur];
		strcpy(buf, _path.MemoryC());
		strcat(buf, "/");
		strcat(buf, dirp->d_name);
		_cfile = dirp->d_name;
		_cfull = buf;
		return ETrue;
	}
	return EFalse;
}
#endif

#ifdef _MO_WINDOWS
//============================================================
TBool TFileFinder::OsWalk(){
   //TChar buf[1024];
   //WIN32_FIND_DATA wfd;
   //if(!_dir) {
	  // TUint32 fileAttributes = GetFileAttributes(_path);
	  // if(fileAttributes == INVALID_FILE_ATTRIBUTES) {
		 //  return EFalse;
	  // } else if(!(fileAttributes & FILE_ATTRIBUTE_DIRECTORY)) {
		 //  return EFalse;
	  // }
   //   MO_LIB_STRING_COPY(buf, sizeof(buf), (TCharC*)_path);
	  // TInt len = MO_LIB_STRING_LENGTH(buf);
	  // if(buf[len-1] == '\\') {
		 //  strcat_s(buf, sizeof(buf), "*");
	  // } else {
		 //  strcat_s(buf, sizeof(buf), "\\*");
	  // }
	  // if((_dir = FindFirstFile(buf, &wfd)) == INVALID_HANDLE_VALUE)
		 //  return EFalse;
	  // if(!(wfd.dwFileAttributes & FILE_ATTRIBUTE_DIRECTORY)) {
		 //  _cfile = wfd.cFileName;
		 //  _cfull = buf;
		 //  return ETrue;
	  // }
   //}
   //BOOL ret;
   //while(ret = FindNextFile(_dir, &wfd)) {
   //   if(wfd.cFileName[0] == '.'){
		 //  continue;
   //   }
   //   MO_LIB_STRING_COPY(buf, sizeof(buf), (TCharC*)_path);
	  // strcat_s(buf, sizeof(buf), TC("\\"));
	  // strcat_s(buf, sizeof(buf), wfd.cFileName);
	  // if(!(wfd.dwFileAttributes & FILE_ATTRIBUTE_DIRECTORY)) {
		 //  break;
	  // }
   //}
   //if(ret) {
	  // _cfile = wfd.cFileName;
	  // _cfull = buf;
	  // return ETrue;
   //}
   //FindClose(_dir);
   //_dir = 0;
   return EFalse;
}
#endif

//============================================================
void TFileFinder::OsClose(){
#ifdef _MO_WINDOWS
   if(_dir){
		FindClose(_dir);
   }
#endif // _MO_WINDOWS
#ifdef _MO_LINUX
	if(_dcount > 0 && _dents) {
		for(int i = 0; i < _dcount; ++i)
			free(_dents[i]);
		free(_dents);
	}
	_dcount = 0;
	_cur = -1;
	_dents = 0;
#endif
}

#ifdef _MO_LINUX
int TFileFinder::Filter(const struct dirent *dirp){
	if(dirp->d_type == DT_REG)
		return 1;
	return 0;
}
#endif

MO_NAMESPACE_END
