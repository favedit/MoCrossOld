package org.mo.eng.list;

import org.mo.com.collections.FDataset;
import org.mo.com.collections.FRow;
import org.mo.com.data.ISqlConnect;
import org.mo.com.data.ISqlConnection;
import org.mo.com.lang.FObjects;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.com.xml.FXmlNode;
import org.mo.com.xml.FXmlNodes;
import org.mo.com.xml.IXmlObject;
import org.mo.com.xml.IXmlObjects;
import org.mo.core.aop.face.ALink;
import org.mo.core.aop.face.AProperty;
import org.mo.eng.cache.FCache;
import org.mo.eng.cache.ICache;
import org.mo.eng.cache.ICacheConsole;
import org.mo.eng.data.IDatabaseConsole;
import org.mo.eng.data.common.FSqlQuery;
import org.mo.eng.list.common.XList;
import org.mo.eng.list.common.XListItem;
import org.mo.eng.store.FXmlConfigConsole;

public class FListConsole
      extends FXmlConfigConsole<XList>
      implements
         IListConsole
{
   private static ILogger _logger = RLogger.find(FListConsole.class);

   public final static String SQL_CODE_LIST = "code.list";

   @ALink
   protected ICacheConsole _cacheConsole;

   @AProperty
   protected long _cacheTimeout;

   @ALink
   protected IDatabaseConsole _databaseConsole;

   @Override
   public XList build(String name){
      XList list = buildItems(get(name));
      return list;
   }

   @Override
   public FXmlNodes buildItemNodes(String name){
      FXmlNodes nodes = new FXmlNodes();
      if(name.startsWith("{") && name.endsWith("}")){
         name = name.substring(1, name.length() - 1);
         // 去数据库去CodeList
         ISqlConnection cnn = null;
         try{
            cnn = _databaseConsole.alloc();
            innerBuildCodeList(nodes, name, cnn);
         }finally{
            if(null != cnn){
               _databaseConsole.free(cnn);
            }
         }
      }else{
         // 通过列表取数据
         innerBuildList(nodes, name);
      }
      return nodes;
   }

   @Override
   public FXmlNodes buildItemNodes(TListArgs args){
      FXmlNodes nodes = new FXmlNodes();
      String name = args.name();
      if(name.startsWith("{") && name.endsWith("}")){
         name = name.substring(1, name.length() - 1);
         // 去数据库去CodeList
         if(null == args.sqlContext()){
            ISqlConnection cnn = null;
            try{
               cnn = _databaseConsole.alloc();
               innerBuildCodeList(nodes, name, cnn);
            }finally{
               if(null != cnn){
                  _databaseConsole.free(cnn);
               }
            }
         }else{
            innerBuildCodeList(nodes, name, args.sqlContext());
         }
      }else{
         // 通过列表取数据
         innerBuildList(nodes, name);
      }
      return nodes;
   }

   protected XList buildItems(XList list){
      ISqlConnection cnn = null;
      XList xlist = new XList();
      try{
         int count = list.children().count();
         for(int n = 0; n < count; n++){
            IXmlObject xobject = list.children().get(n);
            String type = xobject.innerGet("type");
            if(XListItem.TYPE_SQL.equals(type)){
               if(null == cnn){
                  cnn = _databaseConsole.alloc();
               }
               FDataset ds = cnn.fetchDataset(xobject.innerGet("value"));
               for(FRow row : ds){
                  XListItem item = new XListItem();
                  item.innerSet("value", row.value(0));
                  item.innerSet("label", row.value(1));
                  xlist.children().push(item);
               }
            }else{
               XListItem item = new XListItem();
               item.innerSet("value", xobject.innerGet("value"));
               item.innerSet("label", xobject.innerGet("label"));
               xlist.children().push(item);
            }
         }
      }finally{
         if(null != cnn){
            _databaseConsole.free(cnn);
         }
      }
      return xlist;
   }

   @Override
   public FXmlNode buildListConfig(TListArgs args){
      String name = args.name();
      // 从缓冲管理器中查找列表对象 
      ICache cache = _cacheConsole.find(IListConsole.class, name);
      if(null != cache){
         return (FXmlNode)cache.instance();
      }
      // 创建节点
      FXmlNode config = new FXmlNode("List");
      config.set("name", name);
      if(name.startsWith("{") && name.endsWith("}")){
         String code = name.substring(1, name.length() - 1);
         // 去数据库去CodeList
         if(null == args.sqlContext()){
            ISqlConnection cnn = null;
            try{
               cnn = _databaseConsole.alloc();
               innerBuildCodeList(config.nodes(), code, cnn);
            }finally{
               if(null != cnn){
                  _databaseConsole.free(cnn);
               }
            }
         }else{
            innerBuildCodeList(config.nodes(), code, args.sqlContext());
         }
      }else{
         // 通过列表取数据
         innerBuildList(config.nodes(), name);
      }
      // 向缓冲管理器中注册列表对象 
      cache = new FCache(config);
      cache.setTimeout(_cacheTimeout);
      _cacheConsole.register(IListConsole.class, name, cache);
      return config;
   }

   @Override
   protected FObjects<XList> createCollection(){
      return new FObjects<XList>(XList.class);
   }

   protected void innerBuildCodeList(FXmlNodes config,
                                     String name,
                                     ISqlConnect connect){
      // 去数据库去CodeList
      if(_logger.debugAble()){
         _logger.debug(this, "buildItemNodes", "Find item nodes. (codelist={0})", name);
      }
      FSqlQuery sql = new FSqlQuery(connect, getClass(), SQL_CODE_LIST);
      sql.bindString("name", name);
      FDataset dataset = sql.fetchDataset();
      for(FRow row : dataset){
         FXmlNode itemNode = config.create(XListItem.NAME);
         itemNode.set(XListItem.PTY_VALUE, row.get(XListItem.PTY_VALUE));
         itemNode.set(XListItem.PTY_LABEL, row.get(XListItem.PTY_LABEL));
      }
   }

   protected void innerBuildList(FXmlNodes config,
                                 String name){
      // 通过列表取数据
      XList xlist = get(name);
      if(xlist.hasChild()){
         IXmlObjects xitems = xlist.children();
         int count = xitems.count();
         for(int n = 0; n < count; n++){
            XListItem xitem = (XListItem)xitems.get(n);
            if(null != xitem){
               FXmlNode itemNode = config.create(XListItem.NAME);
               itemNode.set(XListItem.PTY_VALUE, xitem.getValue());
               itemNode.set(XListItem.PTY_LABEL, xitem.getLabel());
            }
         }
      }
   }
}
