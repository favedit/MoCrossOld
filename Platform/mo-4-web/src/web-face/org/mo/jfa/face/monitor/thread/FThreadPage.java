package org.mo.jfa.face.monitor.thread;

import org.mo.com.lang.FObjectId;
import org.mo.com.lang.FObjects;

public class FThreadPage
      extends FObjectId
{

   private final FThreadInfo _info = new FThreadInfo();

   private final FObjects<FThreadInfo> _infos = new FObjects<FThreadInfo>(FThreadInfo.class);

   public FThreadInfo getInfo(){
      return _info;
   }

   public FObjects<FThreadInfo> getInfos(){
      return _infos;
   }

}
