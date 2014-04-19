/*
 * @(#)FPermissionManager.java
 *
 * Copyright 2005 microbject, All Rights Reserved.
 *
 */
package org.mo.eng.security;

import org.mo.com.data.ESqlDataDirection;
import org.mo.com.data.ESqlDataType;
import org.mo.com.data.FSqlFunction;
import org.mo.com.data.ISqlConnection;
import org.mo.com.lang.FStrings;
import org.mo.com.lang.RDateTime;
import org.mo.com.lang.RString;
import org.mo.com.lang.type.TDateTime;
import org.mo.com.lang.type.TTimeSpan;
import org.mo.core.aop.RAop;
import org.mo.core.aop.face.AProperty;
import org.mo.core.context.FContext;
import org.mo.eng.data.IDatabaseConsole;

/**
 * <p>权限控制台</p>
 * 
 * @author ALEX
 * @version 1.00 - 2005/10/25
 */
public class FPermissionConsole
      implements
         IPermissionConsole
{
   //   private FDictionary<IPermission> _permissionMap = new FDictionary<IPermission>(IPermission.class);
   protected IDatabaseConsole _databaseConsole;

   private TPermissionDescriptors _descriptors = new TPermissionDescriptors();

   @AProperty
   int _interval;

   @AProperty
   int _count;

   int _invalidCount = 0;

   public FPermissionConsole(){
   }

   public FPermission buildPermission(FContext context,
                                      String userId){
      return null;
   }

   public boolean canLogin(FContext context,
                           String userId,
                           String password){
      return false;
   }

   public void compressDescriptors(){
      for(int n = 0; n < _descriptors.count(); n++){
         TDateTime dateTime = RDateTime.currentDateTime();
         TTimeSpan timeSpan = new TTimeSpan(dateTime.get() - _descriptors.value(n).fetchDate());
         if(_interval <= timeSpan.milliseconds()){
            _descriptors.setValue(n, null);
            _descriptors.setInvalidDescriptorCount(_descriptors.invalidDescriptorCount() + 1);
         }
         if(_count <= _descriptors.invalidDescriptorCount()){
            //_descriptors.compress();
         }
      }
   }

   @Override
   public IPermission createPermission(){
      return new FPermission(this);
   }

   @Override
   public TPermissionDescriptors descriptors(){
      return _descriptors;
   }

   @Override
   public FStrings fetchRoles(String moduleName,
                              String actionName){
      ISqlConnection connection = null;
      try{
         /// 获得数据库连接，按SQL语句查询数据库
         _databaseConsole = RAop.find(IDatabaseConsole.class);
         connection = _databaseConsole.alloc();
         FSqlFunction function = new FSqlFunction("Get_Role_Pack");
         function.setLogicName("PM_ROLE_MODULE_ACTION_DI");
         /// 创建所需参数
         function.createParameter("module_name_", RString.toUpper(moduleName), ESqlDataType.String, ESqlDataDirection.InOut);
         function.createParameter("action_name_", RString.toUpper(actionName), ESqlDataType.String, ESqlDataDirection.InOut);
         connection.execute(function);
         FStrings results = function.parameterReturn().asStrings();
         return results;
      }finally{
         if(null != connection){
            _databaseConsole.free(connection);
         }
      }
   }

   @Override
   public TPermissionDescriptor findDescriptor(String module,
                                               String action){
      String ma = module + "|" + action;
      TPermissionDescriptor descriptor = _descriptors.get(ma);
      compressDescriptors();
      if(null == descriptor){
         FStrings roles = fetchRoles(module, action);
         TDateTime date = RDateTime.currentDateTime();
         TPermissionDescriptor desc = new TPermissionDescriptor();
         desc.setAction(action);
         desc.setModule(module);
         desc.setFetchDate(date.get());
         desc.setRoles(roles);
         _descriptors.set(ma, desc);
         return desc;
      }
      return descriptor;
   }

   @Override
   public boolean hasPermission(String userId,
                                String permission){
      //      userId = userId.toLowerCase();
      //      if(_permissionMap.contains(userId)){
      //         return _permissionMap.get(userId).hasPermission(permission);
      //      }
      return false;
   }

   @Override
   public boolean hasPermission(String userId,
                                String moduleId,
                                String actionId){
      userId = userId.toLowerCase();
      //      if(_permissionMap.contains(userId)){
      //         return _permissionMap.get(userId)actionId.coonhasPermission(moduleId, actionId);
      //      }
      return false;
   }

   @Override
   public void pushPermission(String userId,
                              FPermission permission){
      //      _permissionMap.set(userId.toLowerCase(), permission);
   }

   @Override
   public void setDescriptors(TPermissionDescriptors _descriptors){
      this._descriptors = _descriptors;
   }
}
