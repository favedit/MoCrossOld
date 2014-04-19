package org.mo.data.logicunit;

import org.mo.com.lang.FObjects;
import org.mo.com.xml.FXmlNodes;
import org.mo.com.xml.IXmlObject;
import org.mo.core.aop.face.ALink;
import org.mo.data.codelist.common.XCodeList;
import org.mo.eng.data.IDatabaseConsole;
import org.mo.eng.store.FXmlConfigConsole;

public class FLogicUnitConsole
      extends FXmlConfigConsole<IXmlObject>
      implements
         ILogicUnitConsole
{
   //private static ILogger _logger = RLogger.find(FCodeListConsole.class);
   //public final static String SQL_CODE_LIST = "code.list";
   @ALink
   protected IDatabaseConsole _databaseConsole;

   public XCodeList build(String name){
      //XCodeList list = buildItems(get(name));
      //return list;
      return null;
   }

   @Override
   public FXmlNodes buildItemNodes(String name){
      FXmlNodes nodes = new FXmlNodes();
      //      if(name.startsWith("{") && name.endsWith("}")){
      //         // 去数据库去CodeList
      //         name = name.substring(1, name.length() - 1);
      //         ISqlConnection cnn = null;
      //         try{
      //            cnn = _databaseConsole.alloc();
      //            _logger.debug(this, "buildItemNodes", "Find item nodes. (codelist={0})", name);
      //            FSqlQuery sql = new FSqlQuery(cnn, getClass(), SQL_CODE_LIST);
      //            sql.bindString("name", name);
      //            FDataset dataset = sql.fetchDataset();
      //            for(FRow row : dataset){
      //               FXmlNode itemNode = nodes.create(XListItem.NAME);
      //               itemNode.set(XListItem.PTY_VALUE, row.get(XListItem.PTY_VALUE));
      //               itemNode.set(XListItem.PTY_LABEL, row.get(XListItem.PTY_LABEL));
      //            }
      //         }finally{
      //            if(null != cnn){
      //               _databaseConsole.free(cnn);
      //            }
      //         }
      //      }else{
      //         //         // 通过列表取数据
      //         //         XList xlist = get(name);
      //         //         if(xlist.hasChild()){
      //         //            IXmlObjects xitems = xlist.children();
      //         //            int count = xitems.count();
      //         //            for(int n = 0; n < count; n++){
      //         //               XListItem xitem = (XListItem) xitems.get(n);
      //         //               if(null != xitem){
      //         //                  FXmlNode itemNode = nodes.create(XItem.NAME);
      //         //                  itemNode.set(XListItem.PTY_VALUE, xitem.getValue());
      //         //                  itemNode.set(XListItem.PTY_LABEL, xitem.getLabel());
      //         //               }
      //         //            }
      //         //         }
      //      }
      return nodes;
   }

   //
   //   protected XCodeList buildItems(XCodeList list){
   //      ISqlConnection cnn = null;
   //      XCodeList xlist = new XCodeList();
   //      try{
   //         int count = list.children().count();
   //         for(int n = 0; n < count; n++){
   //            IXmlObject xobject = list.children().get(n);
   //            String type = xobject.innerGet("type");
   //            if(XListItem.TYPE_SQL.equals(type)){
   //               if(null == cnn){
   //                  cnn = _databaseConsole.alloc();
   //               }
   //               FDataset ds = cnn.fetchDataset(xobject.innerGet("value"));
   //               for(FRow row : ds){
   //                  XListItem item = new XListItem();
   //                  item.innerSet("value", row.value(0));
   //                  item.innerSet("label", row.value(1));
   //                  xlist.children().push(item);
   //               }
   //            }else{
   //               XListItem item = new XListItem();
   //               item.innerSet("value", xobject.innerGet("value"));
   //               item.innerSet("label", xobject.innerGet("label"));
   //               xlist.children().push(item);
   //            }
   //         }
   //      }finally{
   //         if(null != cnn){
   //            _databaseConsole.free(cnn);
   //         }
   //      }
   //      return xlist;
   //   }
   @Override
   protected FObjects<IXmlObject> createCollection(){
      return new FObjects<IXmlObject>(IXmlObject.class);
   }
}
