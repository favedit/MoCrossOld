package org.mo.eng.cache;

public class FCache
      implements
         ICache
{
   private Object _instance;

   private long _lastUsed;

   private long _timeout;

   //private int _validCount = 0;

   public FCache(){
   }

   public FCache(Object instance){
      setInstance(instance);
   }

   @Override
   public Object instance(){
      return _instance;
   }

   @Override
   public long lastUsed(){
      return _lastUsed;
   }

   @Override
   public void refresh(){
      _lastUsed = System.currentTimeMillis();
      //_validCount++;
   }

   @Override
   public void setInstance(Object instance){
      _instance = instance;
      refresh();
   }

   @Override
   public void setTimeout(long timeout){
      _timeout = timeout;
   }

   @Override
   public boolean testTimeout(long time){
      return (time - _lastUsed) > _timeout;
   }

   @Override
   public long timeout(){
      return _timeout;
   }
   //   @Override
   //   public String toString(){
   //      FString info = new FString();
   //      RClass.dump(info, this);
   //      info.append("[timeout=" + _timeout);
   //      info.append(",vaild=", System.currentTimeMillis() - _lastUsed);
   //      info.append("]");
   //      return info.toString();
   //   }
}
