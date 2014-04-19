//============================================================
// <T>三维{label}大小。</T>
//============================================================
struct S{type_name}Size3{
public:
   {type} width;
   {type} height;
   {type} deep;
public:
   //------------------------------------------------------------
   // <T>构造三维{label}大小。</T>
   S{type_name}Size3(){
      width = 0;
      height = 0;
   }
   //------------------------------------------------------------
   // <T>构造三维{label}大小。</T>
   S{type_name}Size3({type} value){
      width = value;
      height = value;
      deep = value;
   }
   //------------------------------------------------------------
   // <T>构造三维{label}大小。</T>
   S{type_name}Size3({type} widthValue, {type} heightValue, {type} deepValue){
      width = widthValue;
      height = heightValue;
      deep = deepValue;
   }
   //------------------------------------------------------------
   // <T>构造三维{label}大小。</T>
   S{type_name}Size3(const S{type_name}Size3& value){
      width = value.width;
      height = value.height;
      deep = value.deep;
   }
public:
   //------------------------------------------------------------
   // <T>判断是否相等。</T>
   MO_INLINE TBool operator ==(const S{type_name}Size3& value){
      return ((width == value.width) && (height == value.height) && (deep == value.deep));
   }
   //------------------------------------------------------------
   // <T>判断是否不相等。</T>
   MO_INLINE TBool operator !=(const S{type_name}Size3& value){
      return ((width != value.width) || (height != value.height) || (deep != value.deep));
   }
public:
   //------------------------------------------------------------
   // <T>设置数据内容。</T>
   MO_INLINE void operator=({type} value){
      width = value;
      height = value;
      deep = value;
   }
   //------------------------------------------------------------
   // <T>设置数据内容。</T>
   MO_INLINE void operator=(const S{type_name}Size3& value){
      width = value.width;
      height = value.height;
      deep = value.deep;
   }
public:
   //------------------------------------------------------------
   // <T>设置数据内容。</T>
   MO_INLINE void Set({type} widthValue = {default}, {type} heightValue = {default}, {type} deepValue = {default}){
      width = widthValue;
      height = heightValue;
      deep = deepValue;
   }
   //------------------------------------------------------------
   // <T>设置数据内容。</T>
   MO_INLINE void Assign(const S{type_name}Size3& value){
      width = value.width;
      height = value.height;
      deep = value.deep;
   }
public:
   //------------------------------------------------------------
   MO_INLINE {type} Square(){
      return width * height * deep;
   }
public:
   TBool Parse(TCharC* pValue);
   TCharC* Format(TChar* pBuffer, TInt capacity);
   TCharC* ToString(TChar* pBuffer, TInt capacity);
};