#include "MoCmString8.h"
#include "MoCmClassBase.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造类信息定义。</T>
//============================================================
TClassInfo::TClassInfo(){
}

//============================================================
// <T>构造类信息定义。</T>
//============================================================
TClassInfo::TClassInfo(TChar8C* pValue){
#ifdef _MO_WINDOWS
#ifdef _UNICODE
   TFsLabel16 name;
   RString8::ConvertToString16((TChar16*)name, name.Size(), pValue);
   Parse((TChar16C*)name);
#else
   Parse(pValue);
#endif // _UNICODE
#endif // _MO_WINDOWS
#ifdef _MO_LINUX
   Parse(pValue);
#endif // _MO_LINUX
#ifdef _MO_ANDROID
   Parse(pValue);
#endif // _MO_ANDROID
}

//============================================================
// <T>构造类信息定义。</T>
//============================================================
TClassInfo::TClassInfo(TChar16C* pValue){
#ifdef _MO_WINDOWS
#ifdef _UNICODE
   Parse(pValue);
#else
   TFsLabel8 name;
   // RString16::ConvertToString8((TChar8*)name, name.Size(), pValue);
   Parse((TChar8C*)name);
#endif // _UNICODE
#endif // _MO_WINDOWS
#ifdef _MO_LINUX
   //Parse(pValue);
#endif // _MO_LINUX
#ifdef _MO_ANDROID
   //Parse(pValue);
#endif // _MO_ANDROID
}

//============================================================
// <T>析构类信息定义。</T>
//============================================================
TClassInfo::~TClassInfo(){
}

//============================================================
// <T>解析字符串。</T>
//
// @return 全称
//============================================================
TBool TClassInfo::Parse(TCharC* pValue){
#ifdef _MO_WINDOWS
   TFsName fullName = pValue;
   TInt length = fullName.Length();
   TInt spaceStart = fullName.IndexOf(' ') + 1;
   TInt spaceEnd = fullName.IndexOf(':');
   if(spaceEnd == -1){
      _name = fullName;
   }else{
      _space = fullName.SubStrC(spaceStart, spaceEnd);
      _name = fullName.SubStrC(spaceEnd + 2, length);
      _fullName = fullName.SubStrC(spaceStart, length);
   }
#endif // _WINDOWS
#ifdef _MO_LINUX
   TInt position = 0;
   TInt level = 0;
   TInt deep = 0;
   TInt deepCount = 0;
   TInt length = RString::Length(pValue);
   while(position < length){
      //............................................................
      // 判断指针层数
      TInt pointCount = 0;
      for(; position < length; position++){
         if(pValue[position] == 'P'){
            pointCount++;
         }else{
            break;
         }
      }
      //............................................................
      TChar ch = pValue[position];
      if(ch == 'c'){
         _fullName.Append("TInt8");
         if(deep > 0){
            deepCount++;
         }
      }else if(ch == 's'){
         _fullName.Append("TInt16");
         if(deep > 0){
            deepCount++;
         }
      }else if(ch == 'i'){
         _fullName.Append("TInt32");
         if(deep > 0){
            deepCount++;
         }
      }else if(ch == 'x'){
         _fullName.Append("TInt64");
         if(deep > 0){
            deepCount++;
         }
      }else if(ch == 'h'){
         _fullName.Append("TUint8");
         if(deep > 0){
            deepCount++;
         }
      }else if(ch == 't'){
         _fullName.Append("TUint16");
         if(deep > 0){
            deepCount++;
         }
      }else if(ch == 'j'){
         _fullName.Append("TUint32");
         if(deep > 0){
            deepCount++;
         }
      }else if(ch == 'y'){
         _fullName.Append("TUint64");
         if(deep > 0){
            deepCount++;
         }
      }else if(ch == 'N'){
         level++;
         if(pValue[position + 1] != 'S'){
            //............................................................
            // 获得命名空间
            TFsInteger spaceLengthString;
            for(position++; position < length; position++){
               if(RChar::IsDigit(pValue[position])){
                  spaceLengthString.Append(pValue[position]);
               }else{
                  break;
               }
            }
            TInt spaceLength = spaceLengthString.Parse();
            _space.Append(pValue + position, spaceLength);
            _fullName.Append(pValue + position, spaceLength);
            _fullName.Append("::");
            position += spaceLength;
            // 获得类名
            TFsInteger nameLengthString;
            for(; position < length; position++){
               if(RChar::IsDigit(pValue[position])){
                  nameLengthString.Append(pValue[position]);
               }else{
                  break;
               }
            }
            TInt nameLength = nameLengthString.Parse();
            _name.Append(pValue + position, nameLength);
            _fullName.Append(pValue + position, nameLength);
            position += nameLength;
         }else{
            //............................................................
            // 获得命名空间
            if(pValue[position + 2] == '_'){
               // 同命名空间
               position += 3;
            }
            // 获得名称
            TFsInteger nameLengthString;
            for(; position < length; position++){
               if(RChar::IsDigit(pValue[position])){
                  nameLengthString.Append(pValue[position]);
               }else{
                  break;
               }
            }
            TInt nameLength = nameLengthString.Parse();
            if(deepCount > 0){
               _fullName.Append(',');
            }
            _fullName.Append(pValue + position, nameLength);
            _fullName.AppendRepeat("*", pointCount);
            position += nameLength;
            // 读取结尾
            if(pValue[position] == 'E'){
               position++;
            }
            deepCount++;
         }
         continue;
      }else if(ch == 'I'){
         _fullName.Append('<');
         deep++;
      }else if(ch == 'E'){
         if(deep > 0){
            _fullName.Append('>');
            deep--;
         }
         level--;
         if(0 == level){
            break;
         }
      }
      position++;
   }
#endif // _LINUX
#ifdef _MO_ANDROID
   _space = pValue;
   _name = pValue;
   _fullName = pValue;
#endif // _MO_ANDROID
   return ETrue;
}

MO_NAMESPACE_END
