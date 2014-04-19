package org.mo.com.data;

import org.mo.com.lang.FDictionary;

//============================================================
// <T>数据参数信息集合。</T>
//============================================================
public class FSqlParameters
      extends FDictionary<FSqlParameter>
{
   //============================================================
   // <T>构造数据参数信息集合。</T>
   //============================================================
   public FSqlParameters(){
      super(FSqlParameter.class);
   }

   //============================================================
   // <T>创建参数。</T>
   //
   // @param name 名称
   //============================================================
   public FSqlParameter create(String name){
      FSqlParameter parameter = new FSqlParameter(name);
      set(name, parameter);
      return parameter;
   }

   //============================================================
   // <T>创建参数。</T>
   //
   // @param name 名称
   // @param value 内容
   //============================================================
   public FSqlParameter create(String name,
                               Object value){
      FSqlParameter parameter = new FSqlParameter(name, value);
      set(name, parameter);
      return parameter;
   }

   //============================================================
   // <T>创建参数。</T>
   //
   // @param name 名称
   // @param value 内容
   // @param typeCd 类型
   //============================================================
   public FSqlParameter create(String name,
                               Object value,
                               ESqlDataType typeCd){
      FSqlParameter parameter = new FSqlParameter(name, value, typeCd);
      set(name, parameter);
      return parameter;
   }

   //============================================================
   // <T>创建参数。</T>
   //
   // @param name 名称
   // @param value 内容
   // @param typeCd 类型
   // @param directionCd 方向
   //============================================================
   public FSqlParameter create(String name,
                               Object value,
                               ESqlDataType typeCd,
                               ESqlDataDirection directionCd){
      FSqlParameter parameter = new FSqlParameter(name, value, typeCd, directionCd);
      set(name, parameter);
      return parameter;
   }
}
