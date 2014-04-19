package org.mo.jfa.face.inc.form;

import org.mo.com.collections.FDataset;
import org.mo.com.collections.FRow;
import org.mo.com.data.ISqlConnection;
import org.mo.com.lang.FString;
import org.mo.com.lang.RString;
import org.mo.com.xml.EXmlConfig;
import org.mo.com.xml.FXmlNode;
import org.mo.com.xml.IXmlObject;
import org.mo.com.xml.IXmlObjects;
import org.mo.core.aop.face.ALink;
import org.mo.eng.data.IDatabaseConsole;
import org.mo.web.core.action.IActionConsole;
import org.mo.web.core.webform.IWebFormConsole;
import org.mo.web.core.webform.control.XColumnEditFace;
import org.mo.web.protocol.context.IWebContext;

public class FDataFormAction
      implements
         IDataFormAction
{

   @ALink
   protected IActionConsole _actionConsole;

   @ALink
   protected IDatabaseConsole _databaseConsole;

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

   public void buildTableData(IXmlObject form,
                              FXmlNode dsNode){
      FString sql = new FString();
      sql.append("SELECT ");
      IXmlObjects children = form.children();
      int count = children.count();
      boolean first = true;
      for(int n = 0; n < count; n++){
         IXmlObject child = children.get(n);
         if(child instanceof XColumnEditFace){
            if(first){
               first = false;
            }else{
               sql.append(",");
            }
            sql.append(child.innerGet("data_name"));
         }
      }
      sql.append(" FROM ");
      sql.append(form.innerGet("table_name"));
      // Sql
      ISqlConnection sqlCnn = null;
      try{
         sqlCnn = _databaseConsole.alloc();
         FDataset ds = sqlCnn.fetchDataset(sql.toString());
         if(null != ds){
            for(FRow row : ds){
               FXmlNode rowNode = dsNode.createNode("Row");
               rowNode.attributes().append(row);
            }
            System.out.println(ds.dump());
         }
      }finally{
         _databaseConsole.free(sqlCnn);
      }
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
         String formType = form.name();
         if(formType.equals("WebTable")){
            FXmlNode dsNode = new FXmlNode("Dataset");
            buildTableData(form, dsNode);
            page.setFormDataset(dsNode.xml());
            System.out.println(page.getFormDataset());
         }
         page.setAction("show");
         page.setForm(form);
         page.setFormConfig(_formConsole.build(form.name(), EXmlConfig.Simple).xml());
         // Action
         String action = form.innerGet("action");
         if(RString.isNotBlank(action)){
            redirect = _actionConsole.execute(context, action);
         }
      }
      return RString.nvl(redirect, "DataShow");
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
