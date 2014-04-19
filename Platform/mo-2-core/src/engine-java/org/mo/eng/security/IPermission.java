package org.mo.eng.security;

import org.mo.com.lang.FStrings;

public interface IPermission
{
   void addPermission(String moduleId,
                      String actionId);

   boolean contains(String source);

   boolean contains(String moduleName,
                    String actionName);

   boolean hasPermission(String permission);

   /**
    * <T>判断当前用户中是否拥有该角色代码的权限。</T>
    * 
    * @param role 角色代码
    * @return 是否拥有权限
    * @history 090604 MOACY 创建
    */
   boolean hasRole(String role);

   boolean inRoles(FStrings roles);

   void setGroups(String groups);

   /**
    * <T>设置角色的代码集合。</T>
    * 
    * @param roles 角色代码集合
    * @history 090604 MOACY 创建
    */
   void setRoleCodes(String roleCodes);

   /**
    * <T>设置角色的标识集合。</T>
    * 
    * @param roles 角色标识集合
    * @history 090604 MOACY 创建
    */
   void setRoles(String roles);

   void setUserName(String userName);
}
