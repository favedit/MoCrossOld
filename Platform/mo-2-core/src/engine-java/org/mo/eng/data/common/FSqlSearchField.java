package org.mo.eng.data.common;

import org.mo.com.data.FSql;
import org.mo.com.lang.FObject;
import org.mo.com.lang.RString;
import org.mo.com.lang.generic.MString;
import org.mo.com.lang.generic.TDumpInfo;

public class FSqlSearchField
      extends FObject
{
   private String _format;

   private String _name;

   private ESqlSearchType _type = ESqlSearchType.Equals;

   private String _value;

   public FSqlSearchField(){
   }

   public FSqlSearchField(String name,
                          String value){
      _name = name;
      _value = value;
      _type = ESqlSearchType.Equals;
   }

   public FSqlSearchField(String name,
                          String value,
                          ESqlSearchType type){
      _name = name;
      _value = value;
      _type = type;
   }

   public FSqlSearchField(String name,
                          String value,
                          String type){
      _name = name;
      _value = value;
      setType(type);
   }

   public FSqlSearchField(String name,
                          String value,
                          String type,
                          String format){
      _name = name;
      _value = value;
      setType(type);
      _format = format;
   }

   @Override
   public TDumpInfo dump(TDumpInfo info){
      makeSearchSql(info);
      return info;
   }

   public String format(){
      return _format;
   }

   public void makeSearchSql(MString sql){
      if(null != _name){
         makeSearchSql(sql, _name.toUpperCase());
      }else{
         makeSearchSql(sql, null);
      }
   }

   public void makeSearchSql(MString sql,
                             String name){
      // 追加条件
      if(RString.isEmpty(_value)){
         sql.append(name);
         sql.append(" IS NULL");
      }else{
         switch(_type){
            case Equals:
               // 相等
               sql.append(name, "='", _value, "'");
               break;
            case Begin:
               // 开始部分相等
               sql.append(name, " LIKE '", _value, "%'");
               break;
            case End:
               // 结尾部分相等
               sql.append(name, " LIKE '%", _value, "'");
               break;
            case Like:
               // 部分相等
               sql.append("LOWER(", name, ")", " LIKE '%", RString.toLower(_value), "%'");
               break;
            case In:
               // 包含
               sql.append(name, " IN (", _value, ")");
               break;
            case Date:
               // 时间判断
               sql.append("TO_CHAR(", name, ",'", _format, "')='", _value, "'");
               break;
            case Source:
               // 代码
               sql.append(_value);
               break;
            default:
               break;
         }
      }
   }

   public String name(){
      return _name;
   }

   public String searchSql(){
      FSql sql = new FSql();
      makeSearchSql(sql);
      return sql.toString();
   }

   public void setFormat(String format){
      _format = format;
   }

   public void setName(String name){
      _name = name;
   }

   public void setType(ESqlSearchType type){
      _type = type;
   }

   public void setType(String type){
      if("E".equals(type)){
         _type = ESqlSearchType.Equals;
      }else if("B".equals(type)){
         _type = ESqlSearchType.Begin;
      }else if("N".equals(type)){
         _type = ESqlSearchType.End;
      }else if("L".equals(type)){
         _type = ESqlSearchType.Like;
      }else if("C".equals(type)){
         _type = ESqlSearchType.Source;
      }else if("I".equals(type)){
         _type = ESqlSearchType.In;
      }else if("D".equals(type)){
         _type = ESqlSearchType.Date;
      }
   }

   public void setValue(String value){
      _value = value;
   }

   public ESqlSearchType type(){
      return _type;
   }

   public String value(){
      return _value;
   }
}
