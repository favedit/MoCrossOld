//============================================================
// <T>{label}边距。</T>
//============================================================
struct MO_CM_DECLARE S{type_name}Padding{
public:
   {type} left;
   {type} top;
   {type} right;
   {type} bottom;
public:
   //------------------------------------------------------------
   // <T>构造{label}边距。</T>
   S{type_name}Padding(){
      left = {default};
      top = {default};
      right = {default};
      bottom = {default};
   }
   //------------------------------------------------------------
   // <T>构造{label}边距。</T>
   S{type_name}Padding({type} leftValue, {type} topValue, {type} rightValue, {type} bottomValue){
      left = leftValue;
      top = topValue;
      right = rightValue;
      bottom = bottomValue;
   }
public:
   //------------------------------------------------------------
   // <T>设置内容。</T>
   MO_INLINE void Set({type} leftValue, {type} topValue, {type} rightValue, {type} bottomValue){
      left = leftValue;
      top = topValue;
      right = rightValue;
      bottom = bottomValue;
   }
   //------------------------------------------------------------
   // <T>重置处理。</T>
   MO_INLINE void Reset(){
      left = {default};
      top = {default};
      right = {default};
      bottom = {default};
   }
public:
   TResult Serialize(IDataOutput* pOutput);
   TResult Unserialize(IDataInput* pInput);

};