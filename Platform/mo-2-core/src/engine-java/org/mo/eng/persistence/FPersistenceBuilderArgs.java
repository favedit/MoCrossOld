package org.mo.eng.persistence;

import org.mo.com.collections.FObjectDictionary;
import org.mo.com.lang.FString;
import org.mo.com.xml.IXmlObject;
import org.mo.eng.persistence.common.XPersistence;

public class FPersistenceBuilderArgs
{
   private EPersistenceBuildAction _action;

   private EPersistenceSourceType _type;

   private FString _source;

   private FObjectDictionary _attributes;

   private XPersistence _persistence;

   private IXmlObject _component;

   public FPersistenceBuilderArgs(){
   }

   public FPersistenceBuilderArgs(XPersistence persistence){
      _persistence = persistence;
   }

   public EPersistenceBuildAction action(){
      return _action;
   }

   public FObjectDictionary attributes(){
      if(null == _attributes){
         _attributes = new FObjectDictionary();
      }
      return _attributes;
   }

   public IXmlObject component(){
      return _component;
   }

   public XPersistence persistence(){
      return _persistence;
   }

   public void setAction(EPersistenceBuildAction action){
      _action = action;
   }

   public void setComponent(IXmlObject component){
      _component = component;
   }

   public void setPersistence(XPersistence persistence){
      _persistence = persistence;
   }

   public void setSource(FString source){
      _source = source;
   }

   public void setType(EPersistenceSourceType type){
      _type = type;
   }

   public FString source(){
      if(null == _source){
         _source = new FString();
      }
      return _source;
   }

   public EPersistenceSourceType type(){
      return _type;
   }
}
