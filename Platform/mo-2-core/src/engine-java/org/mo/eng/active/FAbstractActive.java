package org.mo.eng.active;

public abstract class FAbstractActive
      implements
         IActive
{
   protected boolean _active = false;

   protected boolean _status;

   @Override
   public void resume(){
      _active = true;
   }

   @Override
   public boolean isActive(){
      return _active;
   }

   @Override
   public void release(){
   }
}
