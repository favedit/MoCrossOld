package org.mo.com.data;

import java.io.File;
import org.mo.com.lang.FObject;
import org.mo.com.lang.IPack;
import org.mo.com.lang.RBoolean;
import org.mo.com.lang.RDump;
import org.mo.com.lang.RString;
import org.mo.com.lang.generic.IStringNamed;
import org.mo.com.lang.generic.TDumpInfo;

//============================================================
// <T>数据方法。</T>
//============================================================
public class FSqlMethod
      extends FObject
      implements
         IStringNamed
{
   // 函数名称
   protected String _name;

   // 逻辑名称
   protected String _logicName;

   // 参数列表
   protected FSqlParameters _parameters = new FSqlParameters();

   // 描述信息
   protected String _description;

   //============================================================
   // <T>构造数据方法。</T>
   //============================================================
   public FSqlMethod(){
   }

   //============================================================
   // <T>构造数据方法。</T>
   //
   // @param name 名称
   //============================================================
   public FSqlMethod(String name){
      _name = name;
   }

   //============================================================
   // <T>获得名称。</T>
   //
   // @return 名称
   //============================================================
   @Override
   public String name(){
      return _name;
   }

   //============================================================
   // <T>设置名称。</T>
   //
   // @param name 名称
   //============================================================
   public void setName(String name){
      _name = name;
   }

   //============================================================
   // <T>获得逻辑名称。</T>
   //
   // @return 逻辑名称
   //============================================================
   public String logicName(){
      return _logicName;
   }

   //============================================================
   // <T>设置逻辑名称。</T>
   //
   // @param logicName 逻辑名称
   //============================================================
   public void setLogicName(String logicName){
      _logicName = logicName;
   }

   //============================================================
   // <T>获得全称。</T>
   //
   // @return 全称
   //============================================================
   public String fullName(){
      if(RString.isEmpty(_logicName)){
         return _name;
      }
      if(_name.indexOf('.') > 0){
         return _name;
      }
      return _logicName + "." + _name;
   }

   //============================================================
   // <T>获得描述信息。</T>
   //
   // @return 描述信息
   //============================================================
   public String description(){
      return _description;
   }

   //============================================================
   // <T>设置描述信息。</T>
   //
   // @param description 描述信息
   //============================================================
   public void setDescription(String description){
      _description = description;
   }

   //============================================================
   // <T>构造数据方法。</T>
   //
   // @param name 名称
   // @param value 参数内容
   // @param typeCd 参数类型
   // @param directionCd 参数方向
   //============================================================
   public void createParameter(String name,
                               boolean value,
                               ESqlDataType typeCd,
                               ESqlDataDirection directionCd){
      createParameter(name, RBoolean.toString(value), typeCd, directionCd);
   }

   //============================================================
   // <T>构造数据方法。</T>
   //
   // @param name 名称
   // @param value 参数内容
   // @param typeCd 参数类型
   // @param directionCd 参数方向
   //============================================================
   public void createParameter(String name,
                               int value,
                               ESqlDataType typeCd,
                               ESqlDataDirection directionCd){
      createParameter(name, Integer.toString(value), typeCd, directionCd);
   }

   //============================================================
   // <T>构造数据方法。</T>
   //
   // @param name 名称
   // @param value 参数内容
   // @param typeCd 参数类型
   // @param directionCd 参数方向
   //============================================================
   public void createParameter(String name,
                               long value,
                               ESqlDataType typeCd,
                               ESqlDataDirection directionCd){
      createParameter(name, Long.toString(value), typeCd, directionCd);
   }

   //============================================================
   // <T>构造数据方法。</T>
   //
   // @param name 名称
   // @param value 参数内容
   // @param typeCd 参数类型
   // @param directionCd 参数方向
   //============================================================
   public void createParameter(String name,
                               float value,
                               ESqlDataType typeCd,
                               ESqlDataDirection directionCd){
      createParameter(name, Float.toString(value), typeCd, directionCd);
   }

   //============================================================
   // <T>构造数据方法。</T>
   //
   // @param name 名称
   // @param value 参数内容
   // @param typeCd 参数类型
   // @param directionCd 参数方向
   //============================================================
   public void createParameter(String name,
                               Object value,
                               ESqlDataType typeCd,
                               ESqlDataDirection directionCd){
      FSqlParameter parameter = new FSqlParameter(name);
      parameter.setType(typeCd);
      if(null != value){
         if(value instanceof IPack){
            parameter.set(((IPack)value).pack());
         }else if(value instanceof File){
            parameter.set(value);
         }else if(value instanceof String){
            parameter.set((String)value);
         }else{
            parameter.set(value.toString());
         }
      }else{
         parameter.set(null);
      }
      parameter.setDirection(directionCd);
      _parameters.set(parameter.name(), parameter);
   }

   //============================================================
   // <T>获得参数集合。</T>
   //
   // @return 参数集合
   //============================================================
   public FSqlParameters parameters(){
      return _parameters;
   }

   //============================================================
   // <T>获得指定名称的参数对象。</T>
   //
   // @param name 名称
   // @return 参数对象
   //============================================================
   public FSqlParameter parameter(String name){
      return _parameters.get(name);
   }

   //============================================================
   // <T>增加一个参数对象。</T>
   //
   // @param parameter 参数对象
   //============================================================
   public void push(FSqlParameter parameter){
      _parameters.set(parameter.name(), parameter);
   }

   //============================================================
   // <T>生成运行信息。</T>
   //
   // @param info 运行信息
   // @return 运行信息
   //============================================================
   @Override
   public TDumpInfo dump(TDumpInfo info){
      RDump.dump(info, this);
      info.append(" [");
      info.append(_name);
      info.append("]");
      info.append(" [");
      info.append(_description);
      info.append("]\n");
      for(FSqlParameter param : parameters().values()){
         info.append(" -> ");
         info.append(param.dump());
         info.append("\n");
      }
      return info;
   }
}
