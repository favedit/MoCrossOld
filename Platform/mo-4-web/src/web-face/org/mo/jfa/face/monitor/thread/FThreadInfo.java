package org.mo.jfa.face.monitor.thread;

import java.io.Serializable;

public class FThreadInfo
      implements
         Serializable
{

   private static final long serialVersionUID = 1L;

   private String _id;

   private String _name;

   public String getId(){
      return _id;
   }

   public String getName(){
      return _name;
   }

   public void setId(String id){
      _id = id;
   }

   public void setName(String name){
      _name = name;
   }

}
