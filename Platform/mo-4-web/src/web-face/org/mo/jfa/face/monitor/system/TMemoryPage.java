package org.mo.jfa.face.monitor.system;

import org.mo.com.lang.FObjectId;

public class TMemoryPage
      extends FObjectId
{

   private String _totalMemory;

   private String _totalMemoryM;

   private String _freeMemory;

   private String _freeMemoryM;

   private String _maxMemory;

   private String _maxMemoryM;

   private String _usePercent;

   private String _availableProcessors;

   public String getAvailableProcessors(){
      return _availableProcessors;
   }

   public String getFreeMemory(){
      return _freeMemory;
   }

   public String getFreeMemoryM(){
      return _freeMemoryM;
   }

   public String getMaxMemory(){
      return _maxMemory;
   }

   public String getMaxMemoryM(){
      return _maxMemoryM;
   }

   public String getTotalMemory(){
      return _totalMemory;
   }

   public String getTotalMemoryM(){
      return _totalMemoryM;
   }

   public String getUsePercent(){
      return _usePercent;
   }

   public void setAvailableProcessors(String availableProcessors){
      _availableProcessors = availableProcessors;
   }

   public void setFreeMemory(String freeMemory){
      _freeMemory = freeMemory;
   }

   public void setFreeMemoryM(String freeMemoryM){
      _freeMemoryM = freeMemoryM;
   }

   public void setMaxMemory(String maxMemory){
      _maxMemory = maxMemory;
   }

   public void setMaxMemoryM(String maxMemoryM){
      _maxMemoryM = maxMemoryM;
   }

   public void setTotalMemory(String totalMemory){
      _totalMemory = totalMemory;
   }

   public void setTotalMemoryM(String totalMemoryM){
      _totalMemoryM = totalMemoryM;
   }

   public void setUsePercent(String usePercent){
      _usePercent = usePercent;
   }
}
