package org.mo.com.data;

import org.mo.com.lang.generic.MStringNamedObjects;

//============================================================
// <T>数据存储过程集合。</T>
//============================================================
public class FSqlProcedures
      extends MStringNamedObjects<FSqlProcedure>
{
   //============================================================
   // <T>构造数据存储过程集合。</T>
   //============================================================
   public FSqlProcedures(){
      super(FSqlProcedure.class);
   }
}
