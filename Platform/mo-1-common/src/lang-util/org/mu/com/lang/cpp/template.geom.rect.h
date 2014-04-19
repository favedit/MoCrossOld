//============================================================
// <T>矩形定义。</T>
//============================================================
class S{type_name}Rect{
public:
   {type} left;
   {type} top;
   {type} right;
   {type} bottom;
public:
   //------------------------------------------------------------
   // <T>构造矩形定义。</T>
   S{type_name}Rect(){
      this->left = 0;
      this->top = 0;
      this->right = 0;
      this->bottom = 0;
   }
   //------------------------------------------------------------
   // <T>构造矩形定义。</T>
   S{type_name}Rect({type} left, {type} top, {type} right, {type} bottom){
      this->left = left;
      this->top = top;
      this->right = right;
      this->bottom = bottom;
   }
public:
   //------------------------------------------------------------
   void Set({type} left, {type} top, {type} right, {type} bottom){
      this->left = left;
      this->top = top;
      this->right = right;
      this->bottom = bottom;
   }
};