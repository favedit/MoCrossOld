package org.mo.data.codelist;

import org.mo.com.collections.FObjectDictionary;
import org.mo.com.lang.FString;

public class FCodeListBuilderArgs
{
   private ECodeListBuildAction _action;

   private ECodeListSource _type;

   private FString _source;

   private Object _instance;

   private FObjectDictionary _attributes;

   public FCodeListBuilderArgs(){
   }

   public FCodeListBuilderArgs(Object instance){
      _instance = instance;
   }

   public FCodeListBuilderArgs(Object instance,
                               ECodeListBuildAction action,
                               ECodeListSource type){
      _instance = instance;
      _action = action;
      _type = type;
   }

   public ECodeListBuildAction action(){
      return _action;
   }

   public FObjectDictionary attributes(){
      if(null == _attributes){
         _attributes = new FObjectDictionary();
      }
      return _attributes;
   }

   @SuppressWarnings("unchecked")
   public <V> V instance(){
      return (V)_instance;
   }

   public void setAction(ECodeListBuildAction action){
      _action = action;
   }

   public void setInstance(Object instance){
      _instance = instance;
   }

   public void setSource(FString source){
      _source = source;
   }

   public void setType(ECodeListSource type){
      _type = type;
   }

   public FString source(){
      return _source;
   }

   public ECodeListSource type(){
      return _type;
   }
}
