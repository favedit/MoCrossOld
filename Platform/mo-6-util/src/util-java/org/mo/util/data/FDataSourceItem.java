package org.mo.util.data;

import org.mo.com.lang.FString;

public class FDataSourceItem{

   private String _dataLogic;

   private FString _sourceBody;

   private FString _sourceHead;

   private FString _targetBody;

   private FString _targetHead;

   public FDataSourceItem(String dataLogic){
      _dataLogic = dataLogic;
   }

   public String dataLogic(){
      return _dataLogic;
   }

   public void setDataLogic(String _dataLogic){
      this._dataLogic = _dataLogic;
   }

   public void setSourceBody(FString _sourceBody){
      this._sourceBody = _sourceBody;
   }

   public void setSourceHead(FString _sourceHead){
      this._sourceHead = _sourceHead;
   }

   public void setTargetBody(FString _targetBody){
      this._targetBody = _targetBody;
   }

   public void setTargetHead(FString _targetHead){
      this._targetHead = _targetHead;
   }

   public FString sourceBody(){
      return _sourceBody;
   }

   public FString sourceHead(){
      return _sourceHead;
   }

   public FString targetBody(){
      return _targetBody;
   }

   public FString targetHead(){
      return _targetHead;
   }

   public boolean test(){
      return (null != _sourceBody) && (null != _sourceHead) && (null != _targetBody) && (null != _targetHead);
   }
}
