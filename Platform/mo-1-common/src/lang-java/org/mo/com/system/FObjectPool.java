package org.mo.com.system;

import java.util.ArrayList;
import java.util.List;
import org.mo.com.lang.FError;
import org.mo.com.lang.FFatalError;

public abstract class FObjectPool
{
   private class FObject
   {
      private boolean m_bIsBusy = false;

      private Object m_oObject = null;

      public FObject(Object oObject) throws FError{
         m_oObject = oObject;
         if(m_oObject == null){
            throw new FFatalError("Pooled object can't be null.");
         }
      }

      @SuppressWarnings("unused")
      public boolean beginBusy(){
         return m_bIsBusy = true;
      }

      public boolean endBusy(){
         return m_bIsBusy = false;
      }

      public Object getObject(){
         return m_oObject;
      }

      public boolean isBusy(){
         return m_bIsBusy;
      }
   }

   private List<Object> m_oPoolList = new ArrayList<Object>();

   public FObjectPool(){
   }

   protected synchronized Object allocObject() throws FError{
      FObject oObject = null;
      FObject oAllocObject = null;
      int nSize = m_oPoolList.size();
      for(int n = 0; n < nSize; n++){
         oObject = (FObject)m_oPoolList.get(n);
         if(!oObject.isBusy()){
            oAllocObject = oObject;
         }
      }
      if(oAllocObject == null){
         oAllocObject = new FObject(createObject());
         m_oPoolList.add(oAllocObject);
      }
      return oAllocObject.getObject();
   }

   protected abstract Object createObject() throws FError;

   protected synchronized boolean freeObject(Object oReleaseObject) throws FError{
      FObject oObject = null;
      int nSize = m_oPoolList.size();
      for(int n = 0; n < nSize; n++){
         oObject = (FObject)m_oPoolList.get(n);
         if(oObject.getObject() == oReleaseObject){
            oObject.endBusy();
         }
      }
      return true;
   }

   protected synchronized boolean releaseObject(Object oReleaseObject) throws FError{
      List<Object> releaseList = new ArrayList<Object>();
      FObject oObject = null;
      int nSize = m_oPoolList.size();
      for(int n = 0; n < nSize; n++){
         oObject = (FObject)m_oPoolList.get(n);
         if(oObject.getObject() == oReleaseObject){
            releaseList.add(oObject);
         }
      }
      nSize = releaseList.size();
      for(int n = 0; n < nSize; n++){
         m_oPoolList.remove(releaseList.get(n));
      }
      return true;
   }
}
