package org.mo.jfa.face.inc.form;

import org.mo.com.lang.RString;
import org.mo.com.xml.EXmlConfig;
import org.mo.com.xml.IXmlObject;
import org.mo.core.aop.face.ALink;
import org.mo.web.core.action.IActionConsole;
import org.mo.web.core.webform.IWebFormConsole;
import org.mo.web.protocol.context.IWebContext;

public class FFormAction
      implements
         IFormAction
{

   @ALink
   protected IActionConsole _actionConsole;

   @ALink
   protected IWebFormConsole _formConsole;

   @Override
   public String back(IWebContext context,
                      FFormPage page){
      String formId = RString.nvl(context.parameter("fid"), page.getFormName());
      IXmlObject form = _formConsole.find(formId);
      if(null != form){
         page.setAction("back");
         page.setForm(form);
         //page.setFields(form.controls());
      }
      return null;
   }

   @Override
   public String delete(IWebContext context,
                        FFormPage page){
      String formId = RString.nvl(context.parameter("fid"), page.getFormName());
      IXmlObject form = _formConsole.find(formId);
      if(null != form){
         page.setAction("delete");
         page.setForm(form);
         //page.setFields(form.controls());
      }
      return null;
   }

   @Override
   public String deleteSave(IWebContext context,
                            FFormPage page){
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public String insert(IWebContext context,
                        FFormPage page){
      String formId = RString.nvl(context.parameter("fid"), page.getFormName());
      IXmlObject form = _formConsole.find(formId);
      if(null != form){
         page.setAction("insert");
         page.setForm(form);
         //page.setFields(form.controls());
      }
      return null;
   }

   @Override
   public String insertSave(IWebContext context,
                            FFormPage page){
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public String search(IWebContext context,
                        FFormPage page){
      String formId = RString.nvl(context.parameter("fid"), page.getFormName());
      IXmlObject form = _formConsole.find(formId);
      if(null != form){
         page.setAction("search");
         page.setForm(form);
         //page.setFields(form.controls());
      }
      return null;
   }

   @Override
   public String show(IWebContext context,
                      FFormPage page){
      String redirect = null;
      String formId = RString.nvl(context.parameter("form_name"), page.getFormName());
      IXmlObject form = _formConsole.find(formId);
      if(null != form){
         page.setAction("show");
         page.setForm(form);
         page.setFormConfig(_formConsole.build(form.name(), EXmlConfig.Simple).xml());
         // Action
         String action = form.innerGet("action");
         if(RString.isNotBlank(action)){
            redirect = _actionConsole.execute(context, action);
         }
      }
      return RString.nvl(redirect, "Show");
   }

   @Override
   public String update(IWebContext context,
                        FFormPage page){
      String redirect = null;
      String formId = RString.nvl(context.parameter("form_name"), page.getFormName());
      IXmlObject form = _formConsole.find(formId);
      if(null != form){
         page.setAction("update");
         page.setForm(form);
         //page.setFields(form.controls());
         // Action -> fill data
         page.setFormConfig(_formConsole.build(form.name(), EXmlConfig.Simple).xml());
         // Action
         String action = form.innerGet("action");
         if(RString.isNotBlank(action)){
            redirect = _actionConsole.execute(context, action);
         }
      }
      return RString.nvl(redirect, "Update");
   }

   @Override
   public String updateSave(IWebContext context,
                            FFormPage page){
      String redirect = null;
      IXmlObject form = page.getForm();
      if(null != form){
         page.setAction("updateSave");
         // Action -> fill data
         String action = form.innerGet("action");
         if(RString.isNotBlank(action)){
            redirect = _actionConsole.execute(context, action);
         }
      }
      return redirect;
   }

}
