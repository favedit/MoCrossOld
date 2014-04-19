//============================================================
// <T>二维{label}大小。</T>
//============================================================
struct S{type_name}Size2{
public:
   {type} width;
   {type} height;
public:
   //------------------------------------------------------------
   // <T>构造二维{label}大小。</T>
   S{type_name}Size2(){
      width = {default};
      height = {default};
   }
   //------------------------------------------------------------
   // <T>构造二维{label}大小。</T>
   S{type_name}Size2({type} value){
      width = value;
      height = value;
   }
   //------------------------------------------------------------
   // <T>构造二维{label}大小。</T>
   S{type_name}Size2({type} widthValue, {type} heightValue){
      width = widthValue;
      height = heightValue;
   }
   //------------------------------------------------------------
   // <T>构造二维{label}大小。</T>
   S{type_name}Size2(const S{type_name}Size2& value){
      width = value.width;
      height = value.height;
   }
public:
   //------------------------------------------------------------
   // <T>判断是否相等。</T>
   MO_INLINE TBool operator ==(const S{type_name}Size2& value){
      return ((width == value.width) && (height == value.height));
   }
   //------------------------------------------------------------
   // <T>判断是否不相等。</T>
   MO_INLINE TBool operator !=(const S{type_name}Size2& value){
      return ((width != value.width) || (height != value.height));
   }
public:
   //------------------------------------------------------------
   // <T>设置数据内容。</T>
   MO_INLINE void operator=({type} value){
      width = value;
      height = value;
   }
   //------------------------------------------------------------
   // <T>设置数据内容。</T>
   MO_INLINE void operator=(const S{type_name}Size2& value){
      width = value.width;
      height = value.height;
   }
public:
   //------------------------------------------------------------
   // <T>设置数据内容。</T>
   MO_INLINE void SetAll({type} value = {default}){
      width = value;
      height = value;
   }
   //------------------------------------------------------------
   // <T>设置数据内容。</T>
   MO_INLINE void Set({type} widthValue = {default}, {type} heightValue = {default}){
      width = widthValue;
      height = heightValue;
   }
public:
   //------------------------------------------------------------
   // <T>设置数据内容。</T>
   MO_INLINE void Assign(const S{type_name}Size2& value){
      width = value.width;
      height = value.height;
   }
   //------------------------------------------------------------
   MO_INLINE {type} Square(){
      return width * height;
   }
public:
   TBool Parse(TCharC* pValue);
   TCharC* Format(TChar* pBuffer, TInt capacity);
   TCharC* ToString(TChar* pBuffer, TInt capacity);
};