package org.mo.web.core.action.common;

import org.mo.core.aop.config.XAopNodeCollection;

public class XAopActionsCollection
      extends XAopNodeCollection<XAopActions>
{

   public XAopActionsCollection(){
      super(XAopActions.class);
   }

   public XAopAction matchByFace(String face){
      for(int n = 0; n < _count; n++){
         XAopActions xactions = _items[n];
         if(xactions.match(face)){
            return xactions.createAction(face);
         }
      }
      return null;
   }

}
