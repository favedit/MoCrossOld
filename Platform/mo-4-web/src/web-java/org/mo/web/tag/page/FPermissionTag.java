package org.mo.web.tag.page;

import org.mo.com.lang.RString;
import org.mo.eng.security.IPermission;
import org.mo.web.tag.base.FBasePermissionTag;

public class FPermissionTag
      extends FBasePermissionTag
{

   @Override
   public int onEnd(){
      return EVAL_PAGE;
   }

   @Override
   public int onStart(){
      IPermission permission = _context.session().permission();
      boolean flag = false;
      if(!RString.isEmpty(_role)){
         // 根据角色代码判断权限
         flag = permission.hasRole(_role);
      }else if(!RString.isEmpty(_source)){
         // 根据内容判断权限
         flag = permission.contains(_source);
      }else{
         // 根据模块和命令判断权限
         flag = permission.contains(_module, _action);
      }
      return flag ? EVAL_BODY_INCLUDE : SKIP_BODY;
   }

}
