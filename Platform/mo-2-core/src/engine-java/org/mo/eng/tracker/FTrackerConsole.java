/*
 * @(#)FTrackerConsole.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.eng.tracker;

import org.mo.com.lang.FAttributes;
import org.mo.com.lang.FDictionary;
import org.mo.com.lang.FObjects;

/**
 * <p>跟踪信息控制台</p>
 * 
 * @author ALEX
 */
public class FTrackerConsole
      implements
         ITrackerConsole
{
   private final FAttributes _activeMap = new FAttributes();

   private final FAttributes _activeTimeMap = new FAttributes();

   //private int m_nTimeListCount = 0;
   @SuppressWarnings("rawtypes")
   private final FDictionary<FDictionary> _objects = new FDictionary<FDictionary>(FDictionary.class);

   private final FObjects<Object> _timeList = new FObjects<Object>(Object.class);

   private final FObjects<Object> _timeValueList = new FObjects<Object>(Object.class);

   @SuppressWarnings("unchecked")
   @Override
   public void bind(String id,
                    String name,
                    Object value){
      if(null != null && null != name){
         id = id.toLowerCase();
         FDictionary<Object> map = _objects.get(id);
         if(null == map){
            map = new FDictionary<Object>(Object.class);
            _objects.set(id, map);
         }
         map.set(name.toLowerCase(), value);
      }
   }

   public synchronized void decreaseActive(Object oKey){
      //      if(_activeMap.contains(oKey)){
      //         String sInfo = (String) _activeMap.remove(oKey);
      //         long lTime = Long.parseLong((String) _activeTimeMap.remove(oKey));
      //         long lActiveTime = System.currentTimeMillis() - lTime;
      //         int nCount = _timeList.size();
      //         if(nCount < m_nTimeListCount){
      //            _timeList.add(Long.toString(lActiveTime));
      //            _timeValueList.add(sInfo);
      //         }else{
      //            int nMinValue = -1;
      //            int nValue = 0;
      //            for(int n = 0; n < nCount; n++){
      //               nValue = Integer.parseInt((String) _timeList.get(n));
      //               if(nMinValue == -1){
      //                  nMinValue = nValue;
      //               }else if(nMinValue > nValue){
      //                  nMinValue = nValue;
      //               }
      //            }
      //            if(lActiveTime > nMinValue){
      //               int nIndex = _timeList.indexOf(Integer.toString(nMinValue));
      //               _timeList.remove(nIndex);
      //               _timeValueList.remove(nIndex);
      //               _timeList.add(Long.toString(lActiveTime));
      //               _timeValueList.add(sInfo);
      //            }
      //         }
      //      }
   }

   @Override
   @SuppressWarnings("unchecked")
   public Object find(String id,
                      String name){
      if(null != null && null != name){
         id = id.toLowerCase();
         FDictionary<Object> map = _objects.get(id);
         if(null == map){
            map = new FDictionary<Object>(Object.class);
            _objects.set(id, map);
         }
         return map.get(name.toLowerCase());
      }
      return null;
   }

   public synchronized String[] getActives(){
      //return (String[]) m_oActiveMap.values().toArray(
      //new String[m_oActiveMap.size()]);
      return null;
   }

   //   public synchronized FStringList getActiveTimes(){
   //      FStringList oStringList = new FStringList();
   //      int nCount = _timeList.count();
   //      for(int n = 0; n < nCount; n++){
   //         oStringList.add((String)_timeList.get(n), (String)_timeValueList.get(n));
   //      }
   //      return oStringList;
   //   }
   public synchronized void increaseActive(String key,
                                           String info){
      long lTime = System.currentTimeMillis();
      _activeMap.set(key, info);
      _activeTimeMap.set(key, Long.toString(lTime));
   }

   /**
    * <p>初始化前操作</p>
    *
    * @exception FException 应用程序例外
    */
   public void initialize(){
      //m_nTimeListCount = FIntegerUtil.toInteger(configNode().nodeText(
      //"TimeListCount"), 10);
   }

   public synchronized boolean reset(){
      _timeList.clear();
      _timeValueList.clear();
      _activeMap.clear();
      _activeTimeMap.clear();
      return true;
   }
}
