package org.mo.core.persistent.common;

public class FPersistentMeta
{
   private long _createDate;

   private int _createUser;

   private long _updateDate;

   private int _updateUser;

   public FPersistentMeta(){
   }

   public long createDate(){
      return _createDate;
   }

   public int createUser(){
      return _createUser;
   }

   public long updateDate(){
      return _updateDate;
   }

   public int updateUser(){
      return _updateUser;
   }

   public void setCreateDate(long date){
      _createDate = date;
   }

   public void setCreateUser(int user){
      _createUser = user;
   }

   public void setUpdateDate(long date){
      _updateDate = date;
   }

   public void setUpdateUser(int user){
      _updateUser = user;
   }
}
