#include "MoCmLanguage.h"
#include "MoCmFormat.h"
#include "MoCmProperty.h"

#define MO_CM_PACK_SIGN              TC("MP#")
#define MO_CM_PACK_SIGN_LENGTH       3
#define MO_CM_PACK_BASE              '0'
#define MO_CM_PACK_VERSION           'A'
#define MO_CM_PACK_BEGIN             '['
#define MO_CM_PACK_END               ']'
#define MO_CM_PACK_ATTRIBUTE_BEGIN   '('
#define MO_CM_PACK_ATTRIBUTE_END     ')'
#define MO_CM_PACK_PROPERTY_SPLITE   ','
#define MO_CM_PACK_PROPERTY_EMPTY    '0'
#define MO_CM_PACK_NODE_BEGIN        '{'
#define MO_CM_PACK_NODE_END          '}'
#define MO_CM_PACK_COLLECTION_BEGIN  '{'
#define MO_CM_PACK_COLLECTION_END    '}'

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造打包字符串。</T>
//============================================================
MPack::MPack(){
   _versionCd = EPropertyVersion_A;
   _packCd = EPropertyPack_Name;
   _optionEmpty = EFalse;
   _position = 0;
}

//============================================================
// <T>析构打包字符串。</T>
//============================================================
MPack::~MPack(){
}

//============================================================
// <T>读取一个字符。</T>
//
// @return 字符
//============================================================
TInt MPack::ReadChar(){
   if(_position + 1 <= _length){
      return _pMemory[_position++];
   }
   return -1;
}

//============================================================
// <T>读取一个字符串。</T>
//
// @param pValue 字符串
// @param length 长度
// @return 读取长度
//============================================================
TInt MPack::ReadString(TChar* pValue, TInt length){
   if(length > 0){
      if(_position + length <= _length){
         // 读取内容
         MO_LIB_MEMORY_COPY(pValue, length, _pMemory + _position, length);
         pValue[length] = 0;
         _position += length;
         return length;
      }
   }
   return 0;
}

//============================================================
// <T>追加一个字符到当前字符串对象的末尾。</T>
//
// @param value 字符串
//============================================================
void MPack::WriteChar(TChar value){
   // 确保内存
   if((_position + 1) >= _capacity){
      EnsureSize(_position + 2);
   }
   // 设置数据
   _pMemory[_position++] = value;
   // 调整长度
   if(_position > _length){
      _length = _position;
      _pMemory[_length] = 0;
   }
}

//============================================================
// <T>追加一个字符串到当前字符串对象的末尾。</T>
//
// @param pValue 字符串
// @param length 长度
//============================================================
void MPack::WriteString(TCharC* pValue, TInt length){
   // 获得字符串长度
   if(length < 0){
      length = RTypes<TChar>::IndexOf(pValue, 0);
   }
   if(length > 0){
      // 确保内存
      if((_position + length) >= _capacity){
         EnsureSize(_position + length + 1);
      }
      // 复制数据
      RTypes<TChar>::Copy(_pMemory + _position, pValue, length);
      _position += length;
      // 调整长度
      if(_position > _length){
         _length = _position;
         _pMemory[_length] = 0;
      }
   }
}

//============================================================
// <T>析构打包字符串。</T>
//============================================================
TBool MPack::TestAttribute(){
   return ('A' == _pMemory[_position]);
}

//============================================================
// <T>析构打包字符串。</T>
//============================================================
TBool MPack::TestNode(){
   return ('N' == _pMemory[_position]);
}

//============================================================
// <T>析构打包字符串。</T>
//
// @param [out] count 个数
// @return 处理结果
//============================================================
TBool MPack::ReadAttributeBegin(){
   // 检查开头
   if(_pMemory[_position] != MO_CM_PACK_ATTRIBUTE_BEGIN){
      return EFalse;
   }
   // 解析个数
   _position++;
   return ETrue;
}

//============================================================
// <T>析构打包字符串。</T>
//
// @return 处理结果
//============================================================
TBool MPack::ReadAttributeEnd(){
   _position++;
   return ETrue;
}

//============================================================
// <T>析构打包字符串。</T>
//
// @return 处理结果
//============================================================
TBool MPack::ReadNodeBegin(const SPropertyInfo* pInfo){
   // 检查开头
   if(MO_CM_PACK_NODE_BEGIN != _pMemory[_position]){
      return EFalse;
   }
   _position++;
   return ETrue;
}

//============================================================
// <T>析构打包字符串。</T>
//
// @return 处理结果
//============================================================
TBool MPack::ReadNodeEnd(){
   // 读取结束符
   if(MO_CM_PACK_NODE_END != _pMemory[_position]){
      return EFalse;
   }
   _position++;
   // 读取分隔
   if(MO_CM_PACK_PROPERTY_SPLITE == _pMemory[_position]){
      _position++;
   }
   return ETrue;
}

//============================================================
// <T>析构打包字符串。</T>
//
// @param [out] count 个数
// @return 处理结果
//============================================================
TBool MPack::ReadCollectionBegin(TInt* pCount){
   // 解析个数
   if(NULL != pCount){
      *pCount = RInt16::ParseHex(_pMemory + _position, 4);
   }
   _position += 5;
   return ETrue;
}

//============================================================
// <T>析构打包字符串。</T>
//
// @return 处理结果
//============================================================
TBool MPack::ReadCollectionEnd(){
   _position++;
   return ETrue;
}

//============================================================
// <T>读取属性头信息。</T>
//
// @param pHead 头信息
// @return 处理结果
//============================================================
TBool MPack::ReadPropertyHead(SPropertyHead* pHead){
   // 检查长度
   if(_position >= _length){
      return EFalse;
   }
   // 检查结束符
   TChar flag = _pMemory[_position];
   if(MO_CM_PACK_NODE_BEGIN == flag){
      MO_DEBUG_WARN(TC("Unpack skip unused object.\n   pack  =%s\n   object=%s)"), _pMemory, _pMemory + _position);
      // 跳过未读取的对象或集合
      TInt level = 0;
      for(; _position < _length; _position++){
         TChar value = _pMemory[_position];
         if(MO_CM_PACK_NODE_BEGIN == value){
            level++;
         }else if(MO_CM_PACK_NODE_END == value){
            level--;
            if(0 == level){
               _position++;
               break;
            }
         }
      }
      flag = _pMemory[_position];
   }
   // 跳过属性结束符号
   if(MO_CM_PACK_ATTRIBUTE_END == flag){
      return EFalse;
   }
   // 测试上一个字符是否属性开始
   TChar priorFlag = _pMemory[_position - 1];
   if(!((MO_CM_PACK_ATTRIBUTE_BEGIN == priorFlag) || (MO_CM_PACK_PROPERTY_SPLITE == priorFlag))){
      // 跳过未读取的属性
      MO_DEBUG_WARN(TC("Unpack skip unused property.\n   pack    =%s\n   property=%s)"), _pMemory, _pMemory + _position);
      // 跳过未读取的对象或集合
      for(; _position < _length; _position++){
         TChar value = _pMemory[_position];
         if(MO_CM_PACK_ATTRIBUTE_END == value){
            return EFalse;
         }else if(MO_CM_PACK_PROPERTY_SPLITE == value){
            _position++;
            break;
         }
      }
   }
   // 读取名称长度
   TInt length = RInt8::Parse2(_pMemory + _position);
   if((length <= 0) || (_position + length > _length)){
      return EFalse;
   }
   _position += 2;
   // 读取名称
   TFsName name;
   MO_LIB_MEMORY_COPY(name.Memory(), name.Size(), _pMemory + _position, length);
   name.SetLength(length);
   _readHead.SetName(name);
   _position += length;
   // 读取类型
   TInt typeCd = RInt8::Parse2(_pMemory + _position);
   _readHead.SetTypeCd((EType)typeCd);
   _position += 2;
   // 设置输出
   if(NULL != pHead){
      pHead->SetName(name);
      pHead->SetTypeCd((EType)typeCd);
   }
   return ETrue;
}

//============================================================
// <T>读取布尔属性内容。</T>
//
// @return 属性内容
//============================================================
TBool MPack::ReadPropertyBool(const SPropertyInfo* pInfo){
   TFsInteger buffer;
   TInt length = ReadPropertyFixString(pInfo, buffer.Memory(), 1);
   return (length > 0) ? RBool::IsTrue(buffer) : EFalse;
}

//============================================================
// <T>读取字节属性内容。</T>
//
// @return 属性内容
//============================================================
TByte MPack::ReadPropertyByte(const SPropertyInfo* pInfo){
   TFsInteger buffer;
   TInt length = ReadPropertyString(pInfo, buffer.Memory(), buffer.Size());
   return (length > 0) ? buffer.Parse() : 0;
}

//============================================================
// <T>读取字符属性内容。</T>
//
// @return 属性内容
//============================================================
TChar MPack::ReadPropertyChar(const SPropertyInfo* pInfo){
   TFsInteger buffer;
   TInt length = ReadPropertyString(pInfo, buffer.Memory(), buffer.Size());
   return (length > 0) ? buffer.Parse() : 0;
}

//============================================================
// <T>读取有符号8位整数属性内容。</T>
//
// @return 属性内容
//============================================================
TInt8 MPack::ReadPropertyInt8(const SPropertyInfo* pInfo){
   TFsNumber buffer;
   TInt length = ReadPropertyString(pInfo, buffer.Memory(), buffer.Size());
   return (length > 0) ? RInt8::Parse(buffer) : 0;
}

//============================================================
// <T>读取有符号16位整数属性内容。</T>
//
// @return 属性内容
//============================================================
TInt16 MPack::ReadPropertyInt16(const SPropertyInfo* pInfo){
   TFsNumber buffer;
   TInt length = ReadPropertyString(pInfo, buffer.Memory(), buffer.Size());
   return (length > 0) ? RInt16::Parse(buffer) : 0;
}

//============================================================
// <T>读取有符号32位整数属性内容。</T>
//
// @return 属性内容
//============================================================
TInt32 MPack::ReadPropertyInt32(const SPropertyInfo* pInfo){
   TFsNumber buffer;
   TInt length = ReadPropertyString(pInfo, buffer.Memory(), buffer.Size());
   return (length > 0) ? RInt32::Parse(buffer) : 0;
}

//============================================================
// <T>读取有符号64位整数属性内容。</T>
//
// @return 属性内容
//============================================================
TInt64 MPack::ReadPropertyInt64(const SPropertyInfo* pInfo){
   TFsNumber buffer;
   TInt length = ReadPropertyString(pInfo, buffer.Memory(), buffer.Size());
   return (length > 0) ? RInt64::Parse(buffer) : 0;
}

//============================================================
// <T>读取无符号8位整数属性内容。</T>
//
// @return 属性内容
//============================================================
TUint8 MPack::ReadPropertyUint8(const SPropertyInfo* pInfo){
   TFsNumber buffer;
   TInt length = ReadPropertyString(pInfo, buffer.Memory(), buffer.Size());
   return (length > 0) ? RUint8::Parse(buffer) : 0;
}

//============================================================
// <T>读取无符号16位整数属性内容。</T>
//
// @return 属性内容
//============================================================
TUint16 MPack::ReadPropertyUint16(const SPropertyInfo* pInfo){
   TFsNumber buffer;
   TInt length = ReadPropertyString(pInfo, buffer.Memory(), buffer.Size());
   return (length > 0) ? RUint16::Parse(buffer) : 0;
}

//============================================================
// <T>读取无符号32位整数属性内容。</T>
//
// @return 属性内容
//============================================================
TUint32 MPack::ReadPropertyUint32(const SPropertyInfo* pInfo){
   TFsNumber buffer;
   TInt length = ReadPropertyString(pInfo, buffer.Memory(), buffer.Size());
   return (length > 0) ? RUint32::Parse(buffer) : 0;
}

//============================================================
// <T>读取无符号64位整数属性内容。</T>
//
// @return 属性内容
//============================================================
TUint64 MPack::ReadPropertyUint64(const SPropertyInfo* pInfo){
   TFsNumber buffer;
   TInt length = ReadPropertyString(pInfo, buffer.Memory(), buffer.Size());
   return (length > 0) ? RUint64::Parse(buffer) : 0;
}

//============================================================
// <T>读取单精度浮点数属性内容。</T>
//
// @return 属性内容
//============================================================
TFloat MPack::ReadPropertyFloat(const SPropertyInfo* pInfo){
   TFsFloat buffer;
   TInt length = ReadPropertyString(pInfo, buffer.Memory(), buffer.Size());
   return (length > 0) ? buffer.Parse() : 0;
}

//============================================================
// <T>读取双精度浮点数属性内容。</T>
//
// @return 属性内容
//============================================================
TDouble MPack::ReadPropertyDouble(const SPropertyInfo* pInfo){
   TFsDouble buffer;
   TInt length = ReadPropertyString(pInfo, buffer.Memory(), buffer.Size());
   return (length > 0) ? buffer.Parse() : 0;
}

//============================================================
// <T>读取时刻属性内容。</T>
//
// @return 属性内容
//============================================================
TTimeTick MPack::ReadPropertyTimeTick(const SPropertyInfo* pInfo){
   TFsNumber buffer;
   TInt length = ReadPropertyString(pInfo, buffer.Memory(), buffer.Size());
   return (length > 0) ? RInt64::Parse(buffer) : 0;
}

//============================================================
// <T>读取时间段属性内容。</T>
//
// @return 属性内容
//============================================================
TTimeSpan MPack::ReadPropertyTimeSpan(const SPropertyInfo* pInfo){
   TFsNumber buffer;
   TInt length = ReadPropertyString(pInfo, buffer.Memory(), buffer.Size());
   return (length > 0) ? RInt64::Parse(buffer) : 0;
}

//============================================================
// <T>读取时间属性内容。</T>
//
// @return 属性内容
//============================================================
TTimeSpan MPack::ReadPropertyDateTime(const SPropertyInfo* pInfo){
   TFsNumber buffer;
   TInt length = ReadPropertyString(pInfo, buffer.Memory(), buffer.Size());
   return (length > 0) ? RInt64::Parse(buffer) : 0;
}

//============================================================
// <T>读取枚举属性内容。</T>
//
// @return 属性内容
//============================================================
TInt MPack::ReadPropertyEnum(const SPropertyInfo* pInfo){
   TFsNumber buffer;
   TInt length = ReadPropertyString(pInfo, buffer.Memory(), buffer.Size());
   return (length > 0) ? RInt::Parse(buffer) : 0;
}

//============================================================
// <T>读取集合属性内容。</T>
//
// @return 属性内容
//============================================================
TInt MPack::ReadPropertySet(const SPropertyInfo* pInfo){
   TFsNumber buffer;
   TInt length = ReadPropertyString(pInfo, buffer.Memory(), buffer.Size());
   return (length > 0) ? RInt::Parse(buffer) : 0;
}

//============================================================
// <T>读取字符串属性内容。</T>
//
// @return 属性内容
//============================================================
TInt MPack::ReadPropertyString(const SPropertyInfo* pInfo, TChar* pValue, TInt capacity){
   TInt length = 0;
   // 读取长度的长度
   TInt lengthLength = ReadChar();
   if(-1 != lengthLength){
      lengthLength -= '0';
   }
   // 读取内容
   if(lengthLength > 0){
      // 读取长度
      TFsInteger lengthString;
      if(0 == ReadString(lengthString.Memory(), lengthLength)){
         return -1;
      }
      length = lengthString.Parse();
      // 读取内容
      if(0 == ReadString(pValue, length)){
         return -1;
      }
   }
   // 读取分隔
   if(MO_CM_PACK_PROPERTY_SPLITE == _pMemory[_position]){
      _position++;
   }
   return length;
}

//============================================================
// <T>读取字符串属性内容。</T>
//
// @return 属性内容
//============================================================
TInt MPack::ReadPropertyFixString(const SPropertyInfo* pInfo, TChar* pValue, TInt length){
   TInt result = ReadString(pValue, length);
   // 读取分隔
   if(MO_CM_PACK_PROPERTY_SPLITE == _pMemory[_position]){
      _position++;
   }
   return result;
}

//============================================================
// <T>读取类型属性内容。</T>
//
// @return 属性内容
//============================================================
TCharC* MPack::ReadPropertyType(const SPropertyInfo* pInfo, TChar* pValue, TInt capacity){
   ReadPropertyString(pInfo, pValue, capacity);
   return pValue;
}

//============================================================
// <T>开始解包。</T>
//
// @return 处理结果
//============================================================
TBool MPack::UnpackBegin(){
   // 重置位置
   _position = 0;
   // 读取标识
   TFsCode sign;
   TInt length = ReadString(sign.Memory(), MO_CM_PACK_SIGN_LENGTH);
   if(MO_CM_PACK_SIGN_LENGTH != length){
      MO_FATAL(TC("Unpack sign length invalid. (length=%d, pack=%d:%s)"), length, _length, _pMemory);
      return EFalse;
   }
   sign.SetLength(length);
   if(!sign.Equals(MO_CM_PACK_SIGN)){
      MO_FATAL(TC("Unpack sign invalid. (sign=%s, pack=%d:%s)"), (TCharC*)sign, _length, _pMemory);
      return EFalse;
   }
   // 读取版本
   TInt versionCd = ReadChar();
   if(versionCd < 0){
      MO_FATAL(TC("Unpack version code invalid. (version_cd=%d, pack=%d:%s)"), versionCd, _length, _pMemory);
      return EFalse;
   }
   _versionCd = (EPropertyVersion)(versionCd - MO_CM_PACK_VERSION);
   // 读取打包方式
   TInt packCd = ReadChar();
   if(packCd < 0){
      MO_FATAL(TC("Unpack pack code invalid. (pack_cd=%d, pack=%d:%s)"), packCd, _length, _pMemory);
      return EFalse;
   }
   _packCd = (EPropertyPack)(packCd - MO_CM_PACK_BASE);
   // 读取解包开始
   TInt packBegin = ReadChar();
   if(MO_CM_PACK_BEGIN != packBegin){
      MO_FATAL(TC("Unpack begin invalid. (pack_begin=%d, pack=%d:%s)"), packBegin, _length, _pMemory);
      return EFalse;
   }
   return ETrue;
}

//============================================================
// <T>结束解包。</T>
//============================================================
TBool MPack::UnpackEnd(){
   // 读取解包结束
   TInt packEnd = ReadChar();
   if(MO_CM_PACK_END != packEnd){
      MO_FATAL(TC("Unpack end invalid. (pack_end=%d, pack=%d:%s)"), packEnd, _length, _pMemory);
      return EFalse;
   }
   return ETrue;
}

//============================================================
// <T>写入属性信息。</T>
//
// @param pInfo 属性信息
// @return 处理结果
//============================================================
TBool MPack::WriteInfo(const SPropertyInfo* pInfo){
   // 获得名称和内容
   TFsName name;
   if(EPropertyPack_Id == _packCd){
      RInt16::ToHexString(pInfo->Id(), name.Memory(), 4);
      name.SetLength(4);
   }else if(EPropertyPack_Name == _packCd){
      RInt8::ToString2(RString::Length(pInfo->Name()), name.Memory(), 2);
      name.SetLength(2);
      name.Append(pInfo->Name());
   }else{
      MO_FATAL_UNSUPPORT();
   }
   // 写入名称
   WriteString(name);
   // 写入类型
   TFsName type;
   RInt8::ToString2(pInfo->TypeCd(), type.Memory(), 2);
   type.SetLength(2);
   WriteString(type);
   // 返回结果
   return ETrue;
}

//============================================================
// <T>写入开始属性集合。</T>
//
// @param count 总数
// @return 处理结果
//============================================================
TBool MPack::WriteAttributeBegin(){
   WriteChar(MO_CM_PACK_ATTRIBUTE_BEGIN);
   return ETrue;
}

//============================================================
// <T>写入结束属性集合。</T>
//
// @return 处理结果
//============================================================
TBool MPack::WriteAttributeEnd(){
   if(_pMemory[_position - 1] == MO_CM_PACK_PROPERTY_SPLITE){
      _pMemory[_position - 1] = MO_CM_PACK_ATTRIBUTE_END;
   }else{
      WriteChar(MO_CM_PACK_ATTRIBUTE_END);
   }
   return ETrue;
}

//============================================================
// <T>写入开始节点集合。</T>
//
// @param count 总数
//============================================================
TBool MPack::WriteNodeBegin(const SPropertyInfo* pInfo){
   // 写入属性信息
   WriteInfo(pInfo);
   // 写入节点标志
   WriteChar(MO_CM_PACK_NODE_BEGIN);
   return ETrue;
}

//============================================================
// <T>写入开始节点集合。</T>
//
// @param count 总数
//============================================================
TBool MPack::WriteNodeBegin(const SPropertyInfo* pInfo, TInt count){
   // 检查是否为空
   TBool valid = EFalse;
   if(_optionEmpty){
      valid = ETrue;
   }else if(count > 0){
      valid = ETrue;
   }
   // 写入属性
   if(valid){
      // 写入属性信息
      WriteInfo(pInfo);
      // 写入节点标志
      WriteChar(MO_CM_PACK_NODE_BEGIN);
      return ETrue;
   }
   return EFalse;
}

//============================================================
// <T>写入结束节点集合。</T>
//============================================================
TBool MPack::WriteNodeEnd(){
   // 写入节点标志
   WriteChar(MO_CM_PACK_NODE_END);
   WriteChar(MO_CM_PACK_PROPERTY_SPLITE);
   return ETrue;
}

//============================================================
// <T>写入结束节点集合。</T>
//============================================================
TBool MPack::WriteCollectionBegin(TInt count){
   // 确保标志
   EnsureSize(_position + 6);
   // 写入数据
   RInt16::ToHexString(count, _pMemory + _position, 4);
   _pMemory[_position + 4] = '{';
   _pMemory[_position + 5] = 0;
   _position += 5;
   // 调整长度
   if(_length < _position){
      _length = _position;
   }
   return ETrue;
}

//============================================================
// <T>写入结束节点集合。</T>
//============================================================
TBool MPack::WriteCollectionEnd(){
   // 确保内存
   EnsureSize(_position + 2);
   // 写入数据
   _pMemory[_position    ] = '}';
   _pMemory[_position + 1] = 0;
   _position += 1;
   // 调整长度
   if(_length < _position){
      _length = _position;
   }
   return ETrue;
}

//============================================================
// <T>写入布尔属性。</T>
//
// @param pInfo 属性信息
// @param value 内容
//============================================================
void MPack::WritePropertyBool(const SPropertyInfo* pInfo, TBool value){
   // 检查有效性
   TBool valid = EFalse;
   if(_optionEmpty){
      valid = ETrue;
   }else if(value){
      valid = ETrue;
   }
   // 写入数据
   if(valid){
      if(value){
         WritePropertyFixString(pInfo, RBool::ToString(value), 1);
      }else{
         WritePropertyString(pInfo, NULL);
      }
   }
}

//============================================================
// <T>写入字节属性。</T>
//
// @param pInfo 属性信息
// @param value 内容
//============================================================
void MPack::WritePropertyByte(const SPropertyInfo* pInfo, TByte value){
   // 检查有效性
   TBool valid = EFalse;
   if(_optionEmpty){
      valid = ETrue;
   }else if(0 != value){
      valid = ETrue;
   }
   // 写入数据
   if(valid){
      if(0 != value){
         WritePropertyString(pInfo, TFsInteger(value).ToString());
      }else{
         WritePropertyString(pInfo, NULL);
      }
   }
}

//============================================================
// <T>写入字符属性。</T>
//
// @param pInfo 属性信息
// @param value 内容
//============================================================
void MPack::WritePropertyChar(const SPropertyInfo* pInfo, TChar value){
   // 检查有效性
   TBool valid = EFalse;
   if(_optionEmpty){
      valid = ETrue;
   }else if(0 != value){
      valid = ETrue;
   }
   // 写入数据
   if(valid){
      if(0 != value){
         WritePropertyString(pInfo, TFsInteger(value).ToString());
      }else{
         WritePropertyString(pInfo, NULL);
      }
   }
}

//============================================================
// <T>写入有符号8位整数属性。</T>
//
// @param pInfo 属性信息
// @param value 内容
//============================================================
void MPack::WritePropertyInt8(const SPropertyInfo* pInfo, TInt8 value){
   // 检查有效性
   TBool valid = EFalse;
   if(_optionEmpty){
      valid = ETrue;
   }else if(0 != value){
      valid = ETrue;
   }
   // 写入数据
   if(valid){
      if(0 != value){
         WritePropertyString(pInfo, TFsInteger(value).ToString());
      }else{
         WritePropertyString(pInfo, NULL);
      }
   }
}

//============================================================
// <T>写入有符号16位整数属性。</T>
//
// @param pInfo 属性信息
// @param value 内容
//============================================================
void MPack::WritePropertyInt16(const SPropertyInfo* pInfo, TInt16 value){
   // 检查有效性
   TBool valid = EFalse;
   if(_optionEmpty){
      valid = ETrue;
   }else if(0 != value){
      valid = ETrue;
   }
   // 写入数据
   if(valid){
      if(0 != value){
         WritePropertyString(pInfo, TFsInteger(value).ToString());
      }else{
         WritePropertyString(pInfo, NULL);
      }
   }
}

//============================================================
// <T>写入有符号32位整数属性。</T>
//
// @param pInfo 属性信息
// @param value 内容
//============================================================
void MPack::WritePropertyInt32(const SPropertyInfo* pInfo, TInt32 value){
   // 检查有效性
   TBool valid = EFalse;
   if(_optionEmpty){
      valid = ETrue;
   }else if(0 != value){
      valid = ETrue;
   }
   // 写入数据
   if(valid){
      if(0 != value){
         WritePropertyString(pInfo, TFsInteger(value).ToString());
      }else{
         WritePropertyString(pInfo, NULL);
      }
   }
}

//============================================================
// <T>写入有符号64位整数属性。</T>
//
// @param pInfo 属性信息
// @param value 内容
//============================================================
void MPack::WritePropertyInt64(const SPropertyInfo* pInfo, TInt64 value){
   // 检查有效性
   TBool valid = EFalse;
   if(_optionEmpty){
      valid = ETrue;
   }else if(0 != value){
      valid = ETrue;
   }
   // 写入数据
   if(valid){
      if(0 != value){
         WritePropertyString(pInfo, TFsInteger64(value).ToString());
      }else{
         WritePropertyString(pInfo, NULL);
      }
   }
}

//============================================================
// <T>写入无符号8位整数属性。</T>
//
// @param pInfo 属性信息
// @param value 内容
//============================================================
void MPack::WritePropertyUint8(const SPropertyInfo* pInfo, TUint8 value){
   // 检查有效性
   TBool valid = EFalse;
   if(_optionEmpty){
      valid = ETrue;
   }else if(0 != value){
      valid = ETrue;
   }
   // 写入数据
   if(valid){
      if(0 != value){
         WritePropertyString(pInfo, TFsInteger(value).ToString());
      }else{
         WritePropertyString(pInfo, NULL);
      }
   }
}

//============================================================
// <T>写入无符号16位整数属性。</T>
//
// @param pInfo 属性信息
// @param value 内容
//============================================================
void MPack::WritePropertyUint16(const SPropertyInfo* pInfo, TUint16 value){
   // 检查有效性
   TBool valid = EFalse;
   if(_optionEmpty){
      valid = ETrue;
   }else if(0 != value){
      valid = ETrue;
   }
   // 写入数据
   if(valid){
      if(0 != value){
         WritePropertyString(pInfo, TFsInteger(value).ToString());
      }else{
         WritePropertyString(pInfo, NULL);
      }
   }
}

//============================================================
// <T>写入无符号32位整数属性。</T>
//
// @param pInfo 属性信息
// @param value 内容
//============================================================
void MPack::WritePropertyUint32(const SPropertyInfo* pInfo, TUint32 value){
   // 检查有效性
   TBool valid = EFalse;
   if(_optionEmpty){
      valid = ETrue;
   }else if(0 != value){
      valid = ETrue;
   }
   // 写入数据
   if(valid){
      if(0 != value){
         WritePropertyString(pInfo, TFsInteger(value).ToString());
      }else{
         WritePropertyString(pInfo, NULL);
      }
   }
}

//============================================================
// <T>写入无符号64位整数属性。</T>
//
// @param pInfo 属性信息
// @param value 内容
//============================================================
void MPack::WritePropertyUint64(const SPropertyInfo* pInfo, TUint64 value){
   // 检查有效性
   TBool valid = EFalse;
   if(_optionEmpty){
      valid = ETrue;
   }else if(0 != value){
      valid = ETrue;
   }
   // 写入数据
   if(valid){
      if(0 != value){
         WritePropertyString(pInfo, TFsInteger64(value).ToString());
      }else{
         WritePropertyString(pInfo, NULL);
      }
   }
}

//============================================================
// <T>写入单精度浮点数属性。</T>
//
// @param pInfo 属性信息
// @param value 内容
//============================================================
void MPack::WritePropertyFloat(const SPropertyInfo* pInfo, TFloat value){
   // 检查有效性
   TBool valid = EFalse;
   if(_optionEmpty){
      valid = ETrue;
   }else if(0 != value){
      valid = ETrue;
   }
   // 写入数据
   if(valid){
      if(0 != value){
         WritePropertyString(pInfo, TFsFloat(value).ToString());
      }else{
         WritePropertyString(pInfo, NULL);
      }
   }
}

//============================================================
// <T>写入双精度浮点数属性。</T>
//
// @param pInfo 属性信息
// @param value 内容
//============================================================
void MPack::WritePropertyDouble(const SPropertyInfo* pInfo, TDouble value){
   // 检查有效性
   TBool valid = EFalse;
   if(_optionEmpty){
      valid = ETrue;
   }else if(0 != value){
      valid = ETrue;
   }
   // 写入数据
   if(valid){
      if(0 != value){
         WritePropertyString(pInfo, TFsDouble(value).ToString());
      }else{
         WritePropertyString(pInfo, NULL);
      }
   }
}

//============================================================
// <T>写入时刻属性。</T>
//
// @param pInfo 属性信息
// @param value 内容
//============================================================
void MPack::WritePropertyTimeTick(const SPropertyInfo* pInfo, TTimeTick value){
   // 检查有效性
   TBool valid = EFalse;
   if(_optionEmpty){
      valid = ETrue;
   }else if(0 != value){
      valid = ETrue;
   }
   // 写入数据
   if(valid){
      if(0 != value){
         WritePropertyString(pInfo, TFsInteger64(value).ToString());
      }else{
         WritePropertyString(pInfo, NULL);
      }
   }
}

//============================================================
// <T>写入时间段属性。</T>
//
// @param pInfo 属性信息
// @param value 内容
//============================================================
void MPack::WritePropertyTimeSpan(const SPropertyInfo* pInfo, TTimeSpan value){
   // 检查有效性
   TBool valid = EFalse;
   if(_optionEmpty){
      valid = ETrue;
   }else if(0 != value){
      valid = ETrue;
   }
   // 写入数据
   if(valid){
      if(0 != value){
         WritePropertyString(pInfo, TFsInteger64(value).ToString());
      }else{
         WritePropertyString(pInfo, NULL);
      }
   }
}

//============================================================
// <T>写入时间属性。</T>
//
// @param pInfo 属性信息
// @param value 内容
//============================================================
void MPack::WritePropertyDateTime(const SPropertyInfo* pInfo, TDateTime value){
   // 检查有效性
   TBool valid = EFalse;
   if(_optionEmpty){
      valid = ETrue;
   }else if(0 != value){
      valid = ETrue;
   }
   // 写入数据
   if(valid){
      if(0 != value){
         WritePropertyString(pInfo, TFsInteger64(value).ToString());
      }else{
         WritePropertyString(pInfo, NULL);
      }
   }
}

//============================================================
// <T>写入枚举属性。</T>
//
// @param pInfo 属性信息
// @param value 内容
//============================================================
void MPack::WritePropertyEnum(const SPropertyInfo* pInfo, TInt value){
   // 检查有效性
   TBool valid = EFalse;
   if(_optionEmpty){
      valid = ETrue;
   }else if(0 != value){
      valid = ETrue;
   }
   // 写入数据
   if(valid){
      if(0 != value){
         WritePropertyString(pInfo, TFsInteger(value).ToString());
      }else{
         WritePropertyString(pInfo, NULL);
      }
   }
}

//============================================================
// <T>写入集合属性。</T>
//
// @param pInfo 属性信息
// @param value 内容
//============================================================
void MPack::WritePropertySet(const SPropertyInfo* pInfo, TInt value){
   // 检查有效性
   TBool valid = EFalse;
   if(_optionEmpty){
      valid = ETrue;
   }else if(0 != value){
      valid = ETrue;
   }
   // 写入数据
   if(valid){
      if(0 != value){
         WritePropertyString(pInfo, TFsInteger(value).ToString());
      }else{
         WritePropertyString(pInfo, NULL);
      }
   }
}

//============================================================
// <T>写入字符串属性。</T>
//
// @param pInfo 属性信息
// @param pValue 字符串内容
//============================================================
void MPack::WritePropertyString(const SPropertyInfo* pInfo, TCharC* pValue, TInt length){
   // 检查是否为空
   TBool valid = EFalse;
   if(-1 == length){
      if(NULL != pValue){
         length = RString::Length(pValue);
      }else{
         length = 0;
      }
   }else{
      MO_ASSERT(pValue);
   }
   if(_optionEmpty){
      valid = ETrue;
   }else if(length > 0){
      valid = ETrue;
   }
   // 写入属性
   if(valid){
      // 写入属性信息
      WriteInfo(pInfo);
      // 写入内容
      if(0 == length){
         WriteChar(MO_CM_PACK_PROPERTY_EMPTY);
      }else{
         TFsInteger valueLength = RString::Length(pValue);
         TFsInteger valueLengthLength = valueLength.Length();
         WriteString(valueLengthLength);
         WriteString(valueLength);
         WriteString(pValue);
      }
      // 写入属性分割
      WriteChar(MO_CM_PACK_PROPERTY_SPLITE);
   }
}

//============================================================
// <T>写入字符串属性。</T>
//
// @param pInfo 属性信息
// @param pValue 字符串内容
//============================================================
void MPack::WritePropertyFixString(const SPropertyInfo* pInfo, TCharC* pValue, TInt length){
   MO_ASSERT(pValue);
   // 检查是否为空
   TBool valid = EFalse;
   if(_optionEmpty){
      valid = ETrue;
   }else if(length > 0){
      valid = ETrue;
   }
   // 写入属性
   if(valid){
      // 写入属性信息
      WriteInfo(pInfo);
      // 写入内容
      if(0 == length){
         WriteChar(MO_CM_PACK_PROPERTY_EMPTY);
      }else{
         WriteString(pValue, length);
      }
      // 写入属性分割
      WriteChar(MO_CM_PACK_PROPERTY_SPLITE);
   }
}

//============================================================
// <T>写入类型属性。</T>
//
// @param pInfo 属性信息
// @param pValue 字符串内容
//============================================================
void MPack::WritePropertyType(const SPropertyInfo* pInfo, TCharC* pValue){
   WritePropertyString(pInfo, pValue);
}

//============================================================
// <T>开始打包。</T>
// <P>1 - 打包标识(MP#)。</P>
// <P>1 - 版本标识(A)。</P>
//============================================================
TBool MPack::PackBegin(){
   _position = 0;
   Clear();
   // 写入标识
   WriteString(MO_CM_PACK_SIGN);
   // 写入版本
   WriteChar(MO_CM_PACK_VERSION + _versionCd);
   // 写入打包方式
   WriteChar(MO_CM_PACK_BASE + _packCd);
   // 写入打包开始
   WriteChar(MO_CM_PACK_BEGIN);
   return ETrue;
}

//============================================================
// <T>结束打包。</T>
//============================================================
TBool MPack::PackEnd(){
   // 写入打包结束
   WriteChar(MO_CM_PACK_END);
   return ETrue;
}

MO_NAMESPACE_END
