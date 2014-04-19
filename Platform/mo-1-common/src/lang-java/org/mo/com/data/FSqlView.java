package org.mo.com.data;

import org.mo.com.lang.RDump;
import org.mo.com.lang.RString;
import org.mo.com.lang.generic.IStringNamed;
import org.mo.com.lang.generic.TDumpInfo;

//============================================================
// <T>数据视图。</T>
//============================================================
public class FSqlView
      implements
         IStringNamed
{
   // 名称
   protected String _name;

   //============================================================
   // <T>构造数据视图。</T>
   //============================================================
   public FSqlView(){
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
   // <T>生成运行信息。</T>
   //
   // @param info 运行信息
   // @return 运行信息
   //============================================================
   public TDumpInfo dump(TDumpInfo info){
      RDump.dump(info, this);
      info.append(" [ " + RString.rightPad(name(), 40) + " ");
      info.append(" ]");
      return info;
   }
}
