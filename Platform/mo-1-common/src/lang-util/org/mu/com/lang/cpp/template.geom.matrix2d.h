//============================================================
// <T>3x3{label}矩阵。</T>
//============================================================
struct S{type_name}Matrix2d{
public:
   {type} m[3][3];
public:
   //------------------------------------------------------------
   // <T>构造3x3{label}矩阵。</T>
   S{type_name}Matrix2d(){
      Reset();
   }
public:
   //------------------------------------------------------------
   S{type_name}Matrix2d operator*(S{type_name}Matrix2d& v){
      return Multiply(v);
   }
public:
   //------------------------------------------------------------
   void Reset(){
      for(TInt j=0; j<3; j++){
         for(TInt i=0; i<3; i++){
            m[j][i] = 0;
         }
      }
   }
public:
   //------------------------------------------------------------
   // <T>获得矩阵的模。</T>
   {type} Absolute(){
      {type} v = 0;
      for(TInt j=0; j<3; j++){
         for(TInt i=0; i<3; i++){
            v += m[j][i] * m[j][i];
         }
      }
      return sqrt(v);
   }
   //------------------------------------------------------------
   // <T>点乘(内积)。</T>
   void Cross(S{type_name}Matrix2d& v){
   }
   //------------------------------------------------------------
   // <T>叉乘(外积)。</T>
   S{type_name}Point3 Multiply(S{type_name}Point3& v){
      S{type_name}Point3 r;
      r.x = v.x*m[0][0] + v.y*m[0][1] + v.z*m[0][2];
      r.y = v.x*m[1][0] + v.y*m[1][1] + v.z*m[1][2];
      r.z = v.x*m[2][0] + v.y*m[2][1] + v.z*m[2][2];
      return r;
   }
   //------------------------------------------------------------
   // <T>叉乘(外积)。</T>
   S{type_name}Matrix2d Multiply(S{type_name}Matrix2d& v){
      S{type_name}Matrix2d r;
      for(TInt j = 0; j < 3; j++){
         for(TInt i = 0; i < 3; i++){
            for(TInt k = 0; k < 3; k++){
               r.m[j][i] += m[j][k] * v.m[k][i];
            }
         }
      }
      return r;
   }
   //------------------------------------------------------------
   // <T>单位化矩阵。</T>
   void Normalize(){
      {type} v = Absolute();
      for(TInt j = 0; j < 3; j++){
         for(TInt i = 0; i < 3; i++){
            m[j][i] /= v;
         }
      }
   }
   //------------------------------------------------------------
   {type} ToValue(){
      // 加数部分
      {type} a1 = m[0][0] * m[1][1] * m[2][2];
      {type} a2 = m[0][1] * m[1][2] * m[2][0];
      {type} a3 = m[0][2] * m[1][0] * m[2][1];
      // 减数部分
      {type} b1 = m[0][0] * m[1][2] * m[2][1];
      {type} b2 = m[0][1] * m[1][0] * m[2][2];
      {type} b3 = m[0][2] * m[1][1] * m[2][0];
      // 结果部分
      {type} v = (a1 + a2 + a3) - (b1 + b2 + b3);
      return v;
   }
};