package org.mo.eng.security;

import org.mo.com.lang.FStrings;

public interface IPermissionConsole
{
   IPermission createPermission();

   TPermissionDescriptors descriptors();

   FStrings fetchRoles(String moduleName,
                       String actionName);

   TPermissionDescriptor findDescriptor(String module,
                                        String action);

   boolean hasPermission(String userId,
                         String permission);

   boolean hasPermission(String userId,
                         String moduleId,
                         String actionId);

   void pushPermission(String userId,
                       FPermission permission);

   void setDescriptors(TPermissionDescriptors _descriptors);
}
