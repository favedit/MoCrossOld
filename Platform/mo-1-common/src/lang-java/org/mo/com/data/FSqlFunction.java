package org.mo.com.data;

//============================================================
// <T>数据函数。</T>
//============================================================
public class FSqlFunction
      extends FSqlMethod
{
   // 返回参数对象
   protected FSqlParameter _return;

   //============================================================
   // <T>构造数据函数。</T>
   //
   // @param name 名称
   //============================================================
   public FSqlFunction(String name){
      super(name);
   }

   //============================================================
   // <T>获得返回参数对象。</T>
   //
   // @return 返回参数对象
   //============================================================
   public FSqlParameter parameterReturn(){
      if(_return == null){
         _return = new FSqlParameter();
      }
      return _return;
   }

   //============================================================
   // <T>设置返回参数类型。</T>
   //
   // @param typeCd 返回参数类型
   //============================================================
   public void setReturnType(ESqlDataType typeCd){
      parameterReturn().setType(typeCd);
   }

   //============================================================
   // <T>获得返回参数内容。</T>
   //
   // @return 返回参数内容
   //============================================================
   public String returnAsString(){
      return parameterReturn().asString();
   }
}
