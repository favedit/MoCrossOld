package org.mo.data.datainfo;

import org.mo.com.lang.FObjects;
import org.mo.com.xml.IXmlObject;
import org.mo.core.aop.face.ALink;
import org.mo.data.datainfo.common.XPackage;
import org.mo.data.datainfo.common.XTable;
import org.mo.data.datainfo.common.XView;
import org.mo.eng.data.IDatabaseConsole;
import org.mo.eng.store.FXmlConfigConsole;

public class FDataInfoConsole
      extends FXmlConfigConsole<IXmlObject>
      implements
         IDataInfoConsole
{
   @ALink
   protected IDatabaseConsole _databaseConsole;

   @Override
   protected FObjects<IXmlObject> createCollection(){
      return new FObjects<IXmlObject>(IXmlObject.class);
   }

   //   @Override
   //   public XPackage findPackage(String name){
   //      if(RString.isNotEmpty(name)){
   //         return (XPackage) super.find("package." + name.toLowerCase());
   //      }
   //      return null;
   //   }
   //
   //   @Override
   //   public XTable findTable(String name){
   //      if(RString.isNotEmpty(name)){
   //         return (XTable) super.find("table." + name);
   //      }
   //      return null;
   //   }
   //
   //   @Override
   //   public XView findView(String name){
   //      if(RString.isNotEmpty(name)){
   //         return (XView) super.find("view." + name);
   //      }
   //      return null;
   //   }
   @Override
   protected String makeName(Object instance){
      IXmlObject xobject = (IXmlObject)instance;
      String prex = "";
      if(xobject instanceof XTable){
         prex = "table.";
      }else if(xobject instanceof XView){
         prex = "view.";
      }else if(xobject instanceof XPackage){
         prex = "package.";
      }
      String name = xobject.innerGet("name");
      if(name.startsWith(prex)){
         return name;
      }
      return prex + name;
   }
   //   @Override
   //   public void store(XPackage xpackage){
   //      super.store(xpackage);
   //   }
   //
   //   @Override
   //   public void store(XTable xtable){
   //      super.store(xtable);
   //   }
   //
   //   @Override
   //   public void store(XView xview){
   //      super.store(xview);
   //   }
   //
   //   @Override
   //   public XTable syncTable(String name){
   //      XTable table = null;
   //      if(RString.isNotEmpty(name)){
   //         name = "table." + name;
   //         FXmlConfig config = super.synchronizeConfig(name);
   //         table = (XTable) config.instance();
   //         if(null == table){
   //            table = new XTable();
   //            table.setName(name);
   //            config.setInstance(table);
   //         }
   //      }
   //      return table;
   //   }
}
