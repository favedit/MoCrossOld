package org.mo.jfa.face;

import org.mo.com.lang.FObjectId;

public class FIndexPage
      extends FObjectId
{

   private String _password;

   private String _userid;

   public String getPassword(){
      return _password;
   }

   public String getUserid(){
      return _userid;
   }

   public void setPassword(String password){
      _password = password;
   }

   public void setUserid(String userid){
      _userid = userid;
   }

}
