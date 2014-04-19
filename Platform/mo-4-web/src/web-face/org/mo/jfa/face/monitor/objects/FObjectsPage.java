package org.mo.jfa.face.monitor.objects;

import org.mo.com.collections.FObjectDictionary;
import org.mo.com.lang.FObjectId;
import org.mo.com.lang.face.ATransient;

@ATransient
public class FObjectsPage
      extends FObjectId
{
   transient private Object _object;

   transient private String _objectName;

   transient private FObjectDictionary _objects;

   public Object getObject(){
      return _object;
   }

   public String getObjectName(){
      return _objectName;
   }

   public FObjectDictionary getObjects(){
      if(null == _objects){
         _objects = new FObjectDictionary();
      }
      return _objects;
   }

   public void setObject(Object object){
      _object = object;
   }

   public void setObjectName(String name){
      _objectName = name;
   }

}
