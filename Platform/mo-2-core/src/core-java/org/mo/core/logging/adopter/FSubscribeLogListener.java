package org.mo.core.logging.adopter;

public class FSubscribeLogListener
{
   //   // 缓冲列表
   //   private FLoggerCaches m_oCaches = new FLoggerCaches();
   //
   //   @SuppressWarnings("unchecked")
   //   protected List m_oSubscribeList = new ArrayList();
   //   @SuppressWarnings("unchecked")
   //   public FLoggerSubscribe allocSubscribe(){
   //      synchronized(m_oSubscribeList){
   //         FLoggerSubscribe oSubscribe = new FLoggerSubscribe();
   //         m_oSubscribeList.add(oSubscribe);
   //         //FLogger.debug(this, "FLogger.allocSubscribe", "Alloc Subscribe: " + oSubscribe);
   //         return oSubscribe;
   //      }
   //   }
   //   // 发布订阅消息
   //   if(!m_oSubscribeList.isEmpty()){
   //      synchronized(m_oSubscribeList){
   //         Iterator oIterator = m_oSubscribeList.iterator();
   //         while(oIterator.hasNext()){
   //            //((FLoggerSubscribe) oIterator.next()).pushMessage(sMessage);
   //         }
   //      }
   //   }
   //   public boolean release(FLoggerSubscribe oSubscribe){
   //      synchronized(m_oSubscribeList){
   //         //FLogger.debug(this, "FLogger.releaseSubscribe", "Release Subscribe: " + oSubscribe);
   //         m_oSubscribeList.remove(oSubscribe);
   //         return true;
   //      }
   //   }
   //   public void refresh(){
   //      synchronized(m_oCaches){
   //         for(FLoggerCache oCache : m_oCaches){
   //            oCache.execute();
   //         }
   //         m_oCaches.clear();
   //      }
   //   }
}
