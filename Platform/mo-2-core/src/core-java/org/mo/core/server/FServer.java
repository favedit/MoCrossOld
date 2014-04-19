package org.mo.core.server;

import org.mo.com.lang.FObjects;

public abstract class FServer
      implements
         IServer
{
   class FWorker
   {
      public IClientSession session;

      public long startTime;

      public boolean using;
   }

   public class FWorkers
         extends FObjects<FWorker>
   {
      public FWorkers(){
         super(FWorker.class);
      }

      public IClientSession alloc(){
         FWorker worker = null;
         synchronized(this){
            for(int n = 0; n < _count; n++){
               if(!_items[n].using){
                  worker = _items[n];
                  worker.using = true;
                  break;
               }
            }
         }
         if(worker == null){
            worker = new FWorker();
            worker.using = true;
            worker.session = CreateSession();
            synchronized(this){
               push(worker);
            }
         }
         worker.startTime = System.currentTimeMillis();
         return worker.session;
      }

      public void free(IClientSession session){
         FWorker worker = null;
         synchronized(this){
            for(int n = 0; n < _count; n++){
               if(_items[n].session == session){
                  worker = _items[n];
                  worker.using = false;
                  break;
               }
            }
         }
      }
   }

   private final FWorkers _works = new FWorkers();

   public IClientSession allocSession(){
      return _works.alloc();
   }

   public void freeSession(IClientSession session){
      _works.free(session);
   }
}
