package org.mo.game.editor.face.apl.logic.form;

import org.mo.eng.data.common.ISqlContext;
import org.mo.game.editor.web.apl.form.FAbstractWebFormAction;
import org.mo.logic.session.ISqlSessionContext;
import org.mo.web.protocol.context.IWebContext;

public class FInnerFormAction
      extends FAbstractWebFormAction
      implements
         IInnerFormAction{

   public final String PAGE_INNER_FORM = "InnerForm";

   public final String PAGE_INNER_INSERT = "InnerInsert";

   @Override
   public String display(IWebContext context,
                         ISqlSessionContext sqlContext,
                         FWebFormPage page){
      setEnvironment(context, page);
      return PAGE_INNER_FORM;
   }

   @Override
   public String singleInsert(IWebContext context,
                              ISqlContext sqlContext,
                              FWebFormPage page){
      return PAGE_INNER_INSERT;
   }

   @Override
   public String update(IWebContext context,
                        ISqlSessionContext sqlContext,
                        FWebFormPage page){
      setEnvironment(context, page);
      return PAGE_INNER_FORM;
   }

}
