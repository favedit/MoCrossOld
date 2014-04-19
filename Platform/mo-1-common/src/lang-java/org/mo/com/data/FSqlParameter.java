package org.mo.com.data;

import org.mo.com.lang.FAttributes;
import org.mo.com.lang.FObject;
import org.mo.com.lang.FStrings;
import org.mo.com.lang.IAttributes;
import org.mo.com.lang.RBoolean;
import org.mo.com.lang.RDump;
import org.mo.com.lang.RFloat;
import org.mo.com.lang.RInteger;
import org.mo.com.lang.RString;
import org.mo.com.lang.generic.IStringNamed;
import org.mo.com.lang.generic.TDumpInfo;

//============================================================
// <T>数据参数信息。</T>
//============================================================
public class FSqlParameter
      extends FObject
      implements
         IStringNamed
{
   // 数据名称
   protected String _name;

   // 数据类型
   protected ESqlDataType _typeCd = ESqlDataType.String;

   // 数据库类型信息
   protected String _databaseType;

   // 方向类型
   protected ESqlDataDirection _directionCd = ESqlDataDirection.In;

   // 数据内容
   protected Object _value;

   // 参数描述信息
   protected String _description;

   //============================================================
   // <T>构造数据参数信息。</T>
   //============================================================
   public FSqlParameter(){
   }

   //============================================================
   // <T>构造数据参数信息。</T>
   //
   // @param name 参数名称
   //============================================================
   public FSqlParameter(String name){
      _name = name;
   }

   //============================================================
   // <T>构造数据参数信息。</T>
   //
   // @param name 参数名称
   // @param value 参数内容
   //============================================================
   public FSqlParameter(String name,
                        Object value){
      _name = name;
      set(value);
   }

   //============================================================
   // <T>构造数据参数信息。</T>
   //
   // @param name 参数名称
   // @param value 参数内容
   // @param typeCd 参数类型
   //============================================================
   public FSqlParameter(String name,
                        Object value,
                        ESqlDataType typeCd){
      _name = name;
      set(value);
      setType(typeCd);
   }

   //============================================================
   // <T>构造数据参数信息。</T>
   //
   // @param name 参数名称
   // @param value 参数内容
   // @param typeCd 参数类型
   // @param directionCd 方向类型
   //============================================================
   public FSqlParameter(String name,
                        Object value,
                        ESqlDataType typeCd,
                        ESqlDataDirection directionCd){
      _name = name;
      set(value);
      setType(typeCd);
      setDirection(directionCd);
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
   // <T>判断是否为指定数据类型。</T>
   //
   // @return TRUE：是<br>FALSE：否
   //============================================================
   public boolean isType(ESqlDataType type){
      return _typeCd == type;
   }

   //============================================================
   // <T>设置数据类型。</T>
   //
   // @param type 数据类型
   //============================================================
   public void setType(ESqlDataType type){
      _typeCd = type;
   }

   //============================================================
   // <T>获得数据类型。</T>
   //
   // @return 数据类型
   //============================================================
   public ESqlDataType type(){
      return _typeCd;
   }

   //============================================================
   // <T>获得数据库类型信息。</T>
   //
   // @return 获得数据库类型信息
   //============================================================
   public String databaseType(){
      return _databaseType;
   }

   //============================================================
   // <T>设置数据库类型信息。</T>
   //
   // @param databaseType 数据库类型信息
   //============================================================
   public void setDatabaseType(String databaseType){
      _databaseType = databaseType;
   }

   //============================================================
   // <T>判断是否为输入类型。</T>
   //
   // @return TRUE：是<br>FALSE：否
   //============================================================
   public boolean isDirectionIn(){
      return (_directionCd == ESqlDataDirection.InOut) || (_directionCd == ESqlDataDirection.In);
   }

   //============================================================
   // <T>判断是否为输出类型。</T>
   //
   // @return TRUE：是<br>FALSE：否
   //============================================================
   public boolean isDirectionOut(){
      return (_directionCd == ESqlDataDirection.InOut) || (_directionCd == ESqlDataDirection.Out);
   }

   //============================================================
   // <T>判断是否为输入输出类型。</T>
   //
   // @return TRUE：是<br>FALSE：否
   //============================================================
   public boolean isDirectionInOut(){
      return _directionCd == ESqlDataDirection.InOut;
   }

   //============================================================
   // <T>获得方向类型。</T>
   //
   // @return 方向类型
   //============================================================
   public ESqlDataDirection direction(){
      return _directionCd;
   }

   //============================================================
   // <T>设置方向类型。</T>
   //
   // @param direction 方向类型
   //============================================================
   public void setDirection(ESqlDataDirection directionCd){
      _directionCd = directionCd;
   }

   //============================================================
   // <T>增加方向类型。</T>
   //
   // @param value 方向类型
   //============================================================
   public void addDirection(ESqlDataDirection directionCd){
      if((_directionCd == ESqlDataDirection.In) && (directionCd == ESqlDataDirection.Out)){
         _directionCd = ESqlDataDirection.InOut;
      }else if((_directionCd == ESqlDataDirection.Out) && (directionCd == ESqlDataDirection.In)){
         _directionCd = ESqlDataDirection.InOut;
      }
   }

   //============================================================
   // <T>获得参数描述信息。</T>
   //
   // @return 参数描述信息
   //============================================================
   public String description(){
      return _description;
   }

   //============================================================
   // <T>设置参数描述信息。</T>
   //
   // @param description 参数描述信息
   //============================================================
   public void setDescription(String description){
      _description = description;
   }

   //============================================================
   // <T>判断参数是否为空，如果是长度为零的字符串也认为是空。</T>
   //
   // @return true：是<br>false：否
   //============================================================
   public boolean isEmpty(){
      return (_value instanceof String) ? RString.isEmpty((String)_value) : (_value == null);
   }

   //============================================================
   // <T>以布尔值方式，获得数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean asBoolean(){
      return RBoolean.parse(_value);
   }

   //============================================================
   // <T>以整数方式，获得数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int asInt(){
      return RInteger.parse(_value);
   }

   //============================================================
   // <T>以浮点方式，获得数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public float asFloat(){
      return RFloat.parse(_value);
   }

   //============================================================
   // <T>以字符串方式，获得数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public String asString(){
      String value = null;
      if(_value instanceof IAttributes){
         value = ((IAttributes)_value).pack();
      }else{
         value = RString.parse(_value);
      }
      return value;
   }

   //============================================================
   // <T>对包字符串进行解包，获得数组内容。</T>
   //
   // @return 数组内容
   //============================================================
   public FStrings asStrings(){
      FStrings attributes = new FStrings();
      attributes.unpack((String)_value);
      return attributes;
   }

   //============================================================
   // <T>对包字符串进行解包，获得数据内容列表。</T>
   //
   // @return 数据内容列表
   //============================================================
   public IAttributes asAttributes(){
      FAttributes attributes = new FAttributes();
      attributes.unpack((String)_value);
      return attributes;
   }

   //============================================================
   // <T>获得数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public Object asObject(){
      return _value;
   }

   //============================================================
   // <T>以布尔值方式，设置数据内容。</T>
   //
   // @param value 布尔值
   //============================================================
   public void set(boolean value){
      _value = new Boolean(value);
   }

   //============================================================
   // <T>以整数方式，设置数据内容。</T>
   //
   // @param value 整数
   //============================================================
   public void set(int value){
      _value = new Integer(value);
   }

   //============================================================
   // <T>以字符串方式，设置数据内容。</T>
   //
   // @param value 字符串
   //============================================================
   public void set(String value){
      _value = value;
   }

   //============================================================
   // <T>以对象方式，设置数据内容。</T>
   //
   // @param value 对象
   //============================================================
   public void set(Object value){
      _value = value;
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
      info.append(" [ ");
      info.append(name());
      info.append(" ");
      info.append(type());
      info.append(" ");
      info.append(direction());
      info.append(" ");
      info.append(description());
      info.append(" ]");
      return info;
   }
}
