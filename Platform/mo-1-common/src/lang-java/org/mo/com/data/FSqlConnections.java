package org.mo.com.data;

import org.mo.com.lang.FDictionary;

//============================================================
// <T>数据库链接集合。</T>
//============================================================
public class FSqlConnections
      extends FDictionary<ISqlConnection>
{
   //============================================================
   // <T>构造数据库链接集合。</T>
   //============================================================
   public FSqlConnections(){
      super(ISqlConnection.class);
   }
}
