package org.mo.eng.security;

import org.mo.com.lang.FStrings;

public class TPermissionDescriptor
{
   private String _action;

   private long _fetchDate;

   private String _module;

   private FStrings _roles;

   public String action(){
      return _action;
   }

   public long fetchDate(){
      return _fetchDate;
   }

   public String module(){
      return _module;
   }

   public FStrings roles(){
      return _roles;
   }

   public void setAction(String _action){
      this._action = _action;
   }

   public void setFetchDate(long _fetchDate){
      this._fetchDate = _fetchDate;
   }

   public void setModule(String _module){
      this._module = _module;
   }

   public void setRoles(FStrings _roles){
      this._roles = _roles;
   }
}
