package org.mo.eng.security;

import java.io.Serializable;
import org.mo.com.lang.FDictionary;
import org.mo.com.lang.FFatalError;
import org.mo.com.lang.FObject;
import org.mo.com.lang.FStrings;
import org.mo.com.lang.IDump;
import org.mo.com.lang.RBoolean;
import org.mo.com.lang.RDump;
import org.mo.com.lang.RString;
import org.mo.com.lang.generic.TDumpInfo;
import org.mo.core.aop.RAop;

public class FPermission
      extends FObject
      implements
         Serializable,
         IDump,
         IPermission
{
   public final static String ACTION_SPLITTER = "@";

   public final static String ALL_FLAG = "*";

   // 序列化版本号
   private static final long serialVersionUID = 1L;

   public final static char SPLITTER_AND_CHAR = '&';

   public final static char SPLITTER_CHAR = '.';

   public final static char SPLITTER_OR_CHAR = '|';

   public String[] _groups;

   private final FDictionary<Boolean> _maList = new FDictionary<Boolean>(Boolean.class);

   private final FDictionary<String> _maMap = new FDictionary<String>(String.class);

   private transient IPermissionConsole _permissionConsole;

   public FStrings _roleCodes;

   public FStrings _roles;

   private String _userName;

   public FPermission(IPermissionConsole permissionConsole){
      _permissionConsole = permissionConsole;
   }

   //   public String[] m_arRoles = null;
   @Override
   public void addPermission(String moduleId,
                             String actionId){
      String ma = String.valueOf(moduleId + "|" + actionId).toLowerCase();
      _maList.set(ma, Boolean.TRUE);
   }

   @Override
   public boolean contains(String source){
      String[] str = RString.splitTwo(source, "@", true);
      return contains(str[1], str[0]);
   }

   @Override
   public boolean contains(String moduleName,
                           String actionName){
      String ma = moduleName + "|" + actionName;
      if(!RString.isEmpty(_userName)){
         if(_userName.equals("system")){
            return true;
         }
      }
      if(_maList.contains(ma)){
         return _maList.get(ma);
      }else{
         TPermissionDescriptor descriptor = permissionConsole().findDescriptor(moduleName, actionName);
         FStrings roles = descriptor.roles();
         for(int m = 0; m < roles.count(); m++){
         }
         boolean inroles = inRoles(roles);
         if(inroles){
            _maList.set(ma, Boolean.TRUE);
            return true;
         }else{
            _maList.set(ma, Boolean.FALSE);
         }
      }
      return false;
   }

   @Override
   public TDumpInfo dump(TDumpInfo info){
      RDump.dump(info);
      info.append(_maList);
      return info;
   }

   @Override
   public boolean hasPermission(String permission){
      if(permission == null){
         return false;
      }
      boolean result = false;
      char chChar = 0;
      String[] mas = null;
      char[] permissions = permission.toCharArray();
      int count = permissions.length;
      StringBuffer buffer = new StringBuffer();
      for(int n = 0; n < count; n++){
         chChar = permissions[n];
         if(chChar == SPLITTER_OR_CHAR){
            mas = buffer.toString().split(ACTION_SPLITTER);
            buffer.setLength(0);
            if(mas.length == 2){
               result = hasPermission(mas[1], mas[0]);
               if(result){
                  return true;
               }
            }
         }else if(chChar == SPLITTER_AND_CHAR){
            mas = buffer.toString().split(ACTION_SPLITTER);
            buffer.setLength(0);
            if(mas.length == 2){
               result = hasPermission(mas[1], mas[0]);
               if(!result){
                  return false;
               }
            }
         }else{
            buffer.append(chChar);
         }
      }
      mas = buffer.toString().split(ACTION_SPLITTER);
      buffer.setLength(0);
      if(mas.length == 2){
         return hasPermission(mas[1], mas[0]);
      }
      return false;
   }

   public boolean hasPermission(String moduleId,
                                String actionId){
      if(RString.isEmpty(moduleId, actionId)){
         throw new FFatalError("Invalid permission! (Module:{0} Action:{1})", moduleId, actionId);
      }
      moduleId = moduleId.toLowerCase();
      actionId = actionId.toLowerCase();
      // 权限命令的缓冲
      String ma = moduleId + "|" + actionId;
      if(_maMap.contains(ma)){
         return RBoolean.parse(_maMap.get(ma));
      }
      // 检查权限命令内容
      boolean connectMode = actionId.equals("connect");
      int count = _maList.count();
      String item = null;
      String itemModuleId = null;
      String itemActionId = null;
      for(int n = 0; n < count; n++){
         item = _maList.name(n);
         int position = item.indexOf("|");
         if(position > 0){
            itemModuleId = item.substring(0, position);
            itemActionId = item.substring(position + 1);
            if(connectMode){
               // 处理Connect模式
               if(itemModuleId.equals(ALL_FLAG) || itemModuleId.startsWith(moduleId) || moduleId.startsWith(itemModuleId)){
                  _maMap.set(ma, RBoolean.TRUE_STR);
                  return true;
               }
            }else{
               // 处理其他权限模式
               if(itemModuleId.equals(ALL_FLAG) || moduleId.equals(itemModuleId) || moduleId.startsWith(itemModuleId + SPLITTER_CHAR)){
                  if(itemActionId.equals(ALL_FLAG) || actionId.equals(itemActionId) || actionId.startsWith(itemActionId + SPLITTER_CHAR)){
                     _maMap.set(ma, RBoolean.TRUE_STR);
                     return true;
                  }
               }
            }
         }
      }
      _maMap.set(ma, RBoolean.FALSE_STR);
      return false;
   }

   /* (non-Javadoc)
    * @see org.mo.eng.security.IPermission#hasRole(java.lang.String)
    */
   @Override
   public boolean hasRole(String roles){
      FStrings ownRoles = roleCodes();
      // 排除系统角色
      if(ownRoles.contains("A") || ownRoles.contains("S")){
         return true;
      }
      // 检查指定角色
      if(null != roles){
         String[] roleList = RString.split(roles.toUpperCase(), ',');
         for(String role : roleList){
            role = role.trim();
            if(!RString.isEmpty(role)){
               if(ownRoles.contains(role.trim())){
                  return true;
               }
            }
         }
      }
      return false;
   }

   @Override
   public boolean inRoles(FStrings roles){
      for(String item : roles){
         for(String mrole : roles()){
            if(!RString.isEmpty(item, mrole)){
               if(item.equalsIgnoreCase(mrole)){
                  return true;
               }
            }
         }
      }
      return false;
   }

   public IPermissionConsole permissionConsole(){
      if(null == _permissionConsole){
         _permissionConsole = RAop.find(IPermissionConsole.class);
      }
      return _permissionConsole;
   }

   protected FStrings roleCodes(){
      if(null == _roleCodes){
         _roleCodes = new FStrings();
      }
      return _roleCodes;
   }

   protected FStrings roles(){
      if(null == _roles){
         _roles = new FStrings();
      }
      return _roles;
   }

   @Override
   public void setGroups(String groups){
      _groups = RString.split(groups, ',');
   }

   /* (non-Javadoc)
    * @see org.mo.eng.security.IPermission#setRoleCodes(java.lang.String)
    * 
    * @history 090611 MAOCY 不管参数是否为空，一定清除以前的角色信息  
    */
   @Override
   public void setRoleCodes(String roleCodes){
      roleCodes().clear();
      if(!RString.isEmpty(roleCodes)){
         roleCodes().unpack(roleCodes.toUpperCase());
      }
   }

   /* (non-Javadoc)
    * @see org.mo.eng.security.IPermission#setRoles(java.lang.String)
    * 
    * @history 090611 MAOCY 不管参数是否为空，一定清除以前的角色信息  
    */
   @Override
   public void setRoles(String roles){
      roles().clear();
      if(!RString.isEmpty(roles)){
         roles().unpack(roles);
      }
   }

   @Override
   public void setUserName(String userName){
      if(!RString.isEmpty(userName)){
         _userName = userName;
      }
   }
}
