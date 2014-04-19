//============================================================
// <T>{label}颜色。</T>
//============================================================
struct MO_CM_DECLARE S{type_name}Color4{
public:
   {type} red;
   {type} green;
   {type} blue;
   {type} alpha;
public:
   //------------------------------------------------------------
   // <T>构造{label}颜色。</T>
   MO_INLINE S{type_name}Color4({type} redValue = 0.0f, {type} greenValue = 0.0f, {type} blueValue = 0.0f, {type} alphaValue = 1.0f){
      red = redValue;
      green = greenValue;
      blue = blueValue;
      alpha = alphaValue;
   }
public:
   //------------------------------------------------------------
   // <T>设置颜色。</T>
   MO_INLINE void Set({type} redValue = 0.0f, {type} greenValue = 0.0f, {type} blueValue = 0.0f, {type} alphaValue = 1.0f){
      red = redValue;
      green = greenValue;
      blue = blueValue;
      alpha = alphaValue;
   }
   //------------------------------------------------------------
   // <T>设置全部颜色。</T>
   MO_INLINE void SetAll({type} value = 0.0f){
      red = value;
      green = value;
      blue = value;
      alpha = value;
   }
public:
   //------------------------------------------------------------
   // <T>接收内容。</T>
   MO_INLINE void Assign(const S{type_name}Color4& value){
      red = value.red;
      green = value.green;
      blue = value.blue;
      alpha = value.alpha;
   }
public:
   TResult Serialize(IDataOutput* pOutput);
   TResult Unserialize(IDataInput* pInput);
};