package org.mo.com.system;

import org.mo.com.lang.FObject;
import org.mo.com.lang.FObjects;

//============================================================
// <T>进程组。</T>
//============================================================
public class FProcessGroup
      extends FObject
{
   // 进程集合
   protected FObjects<FProcess> _processes = new FObjects<FProcess>(FProcess.class);

   //============================================================
   // <T>构造进程组。</T>
   //============================================================
   public FProcessGroup(){
   }

   //============================================================
   // <T>获得进程集合。</T>
   //
   // @return 进程集合
   //============================================================
   public FObjects<FProcess> processes(){
      return _processes;
   }

   //============================================================
   // <T>启动进程。</T>
   //
   // @return 进程集合
   //============================================================
   public void start(){
   }
}
