//============================================================
// <T>{label}矩形。</T>
//============================================================
struct S{type_name}Rectangle{
public:
   {type} left;
   {type} top;
   {type} width;
   {type} height;
public:
   //------------------------------------------------------------
   // <T>构造{label}矩形。</T>
   S{type_name}Rectangle(){
      left = {default};
      top = {default};
      width = {default};
      height = {default};
   }
   //------------------------------------------------------------
   // <T>构造{label}矩形。</T>
   S{type_name}Rectangle({type} leftValue, {type} topValue, {type} widthValue, {type} heightValue){
      left = leftValue;
      top = topValue;
      width = widthValue;
      height = heightValue;
   }
public:
   //------------------------------------------------------------
   // <T>设置位置。</T>
   MO_INLINE void SetLocation({type} leftValue, {type} topValue){
      left = leftValue;
      top = topValue;
   }
   //------------------------------------------------------------
   // <T>设置尺寸。</T>
   MO_INLINE void SetSize({type} widthValue, {type} heightValue){
      width = widthValue;
      height = heightValue;
   }
   //------------------------------------------------------------
   // <T>设置内容。</T>
   MO_INLINE void Set({type} leftValue, {type} topValue, {type} widthValue, {type} heightValue){
      left = leftValue;
      top = topValue;
      width = widthValue;
      height = heightValue;
   }
   //------------------------------------------------------------
   // <T>重置处理。</T>
   MO_INLINE void Reset(){
      left = {default};
      top = {default};
      width = {default};
      height = {default};
   }
};