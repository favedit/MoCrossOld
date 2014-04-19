package org.mo.game.editor.face.apl.logic.form;

import org.mo.game.editor.web.apl.form.FAbstractWebFormAction;
import org.mo.logic.session.ISqlSessionContext;
import org.mo.web.protocol.context.IWebContext;

public class FWebFormAction
      extends FAbstractWebFormAction
      implements
         IWebFormAction{

   public final String PAGE_DIALOG = "Dialog";

   public final String PAGE_FORM = "Form";

   public final String PAGE_INNER_FORM = "InnerForm";

   /* (non-Javadoc)
    * @see com.linekong.euis.face.apl.logic.form.IWebFormAction#back(org.mo.web.protocol.context.IWebContext, org.mo.logic.session.ISqlSessionContext, com.linekong.euis.face.apl.logic.form.FWebFormPage)
    */
   @Override
   public String back(IWebContext context,
                      ISqlSessionContext sqlContext,
                      FWebFormPage page){
      return back(context, sqlContext, page, PAGE_INNER_FORM);
   }

   /* (non-Javadoc)
    * @see com.linekong.euis.face.apl.form.IWebFormAction#delete(org.mo.web.protocol.context.IWebContext, org.mo.eng.data.common.ISqlContext, com.linekong.euis.face.apl.form.FWebFormPage)
    */
   @Override
   public String delete(IWebContext context,
                        ISqlSessionContext sqlContext,
                        FWebFormPage page){
      return delete(context, sqlContext, page, PAGE_INNER_FORM);
   }

   @Override
   public String display(IWebContext context,
                         ISqlSessionContext sqlContext,
                         FWebFormPage page){
      //return display(context, sqlContext, page, PAGE_INNER_FORM);
      return PAGE_INNER_FORM;
   }

   /* (non-Javadoc)
    * @see com.linekong.euis.face.apl.form.IWebFormAction#insert(org.mo.web.protocol.context.IWebContext, org.mo.eng.data.common.ISqlContext, com.linekong.euis.face.apl.form.FWebFormPage)
    */
   @Override
   public String insert(IWebContext context,
                        ISqlSessionContext sqlContext,
                        FWebFormPage page){
      return insert(context, sqlContext, page, PAGE_INNER_FORM);
   }

   @Override
   public String list(IWebContext context,
                      ISqlSessionContext sqlContext,
                      FWebFormPage page){
      return list(context, sqlContext, page, "List");
   }

   @Override
   public String onUpdate(){
      System.out.println("-----------" + this);
      return null;
   }

   /* (non-Javadoc)
    * @see com.linekong.euis.face.apl.logic.form.IWebFormAction#show(org.mo.web.protocol.context.IWebContext, org.mo.logic.session.ISqlSessionContext, com.linekong.euis.face.apl.logic.form.FWebFormPage)
    */
   @Override
   public String show(IWebContext context,
                      ISqlSessionContext sqlContext,
                      FWebFormPage page){
      return display(context, sqlContext, page, PAGE_INNER_FORM);
   }

   /* (non-Javadoc)
    * @see com.linekong.euis.face.apl.logic.form.IWebFormAction#update(org.mo.web.protocol.context.IWebContext, org.mo.logic.session.ISqlSessionContext, com.linekong.euis.face.apl.logic.form.FWebFormPage)
    */
   @Override
   public String update(IWebContext context,
                        ISqlSessionContext sqlContext,
                        FWebFormPage page){
      return update(context, sqlContext, page, PAGE_INNER_FORM);
   }

   /* (non-Javadoc)
    * @see com.linekong.euis.face.apl.logic.form.IWebFormAction#zoom(org.mo.web.protocol.context.IWebContext, org.mo.eng.data.common.ISqlContext, com.linekong.euis.face.apl.logic.form.FWebFormPage)
    */
   @Override
   public String zoom(IWebContext context,
                      ISqlSessionContext sqlContext,
                      FWebFormPage page){
      return zoom(context, sqlContext, page, PAGE_DIALOG);
   }

}
