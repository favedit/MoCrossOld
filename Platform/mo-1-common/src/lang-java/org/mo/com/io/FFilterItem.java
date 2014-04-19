package org.mo.com.io;

import org.mo.com.lang.FFatalError;
import org.mo.com.lang.FObject;

//============================================================
// <T>过滤项目。</T>
//============================================================
public class FFilterItem
      extends FObject
{
   // 模式
   protected EFilterMode _modeCd;

   // 内容
   protected String _value;

   //============================================================
   // <T>构造过滤项目。</T>
   //============================================================
   public FFilterItem(){
   }

   //============================================================
   // <T>构造过滤项目。</T>
   //
   // @param modeCd 模式
   // @param value 内容
   //============================================================
   public FFilterItem(EFilterMode modeCd,
                      String value){
      _modeCd = modeCd;
      _value = value;
   }

   //============================================================
   // <T>获得过滤模式。</T>
   //
   // @return 过滤模式
   //============================================================
   public EFilterMode modeCd(){
      return _modeCd;
   }

   //============================================================
   // <T>设置过滤模式。</T>
   //
   // @param modeCd 过滤模式
   //============================================================
   public void setModeCd(EFilterMode modeCd){
      _modeCd = modeCd;
   }

   //============================================================
   // <T>获得数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public String value(){
      return _value;
   }

   //============================================================
   // <T>设置数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setValue(String value){
      _value = value;
   }

   //============================================================
   // <T>是否符合过滤内容。</T>
   //
   // @param source 来源
   // @return 是否符合
   //============================================================
   public boolean filter(String source){
      // 检查参数
      if(source == null){
         return false;
      }
      // 判断模式
      switch(_modeCd){
         case Begin:
            // 开始
            return source.startsWith(_value);
         case End:
            // 结束
            return source.endsWith(_value);
         case Constains:
            // 含有
            return source.contains(_value);
         default:
            throw new FFatalError("Unknown filter mode. (mode={1})");
      }
   }
}
