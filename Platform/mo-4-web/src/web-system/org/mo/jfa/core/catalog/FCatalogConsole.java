package org.mo.jfa.core.catalog;

import org.mo.com.collections.FRow;
import org.mo.com.io.RFile;
import org.mo.com.xml.FXmlDocument;
import org.mo.com.xml.FXmlNode;
import org.mo.core.aop.face.AProperty;
import org.mo.eng.data.common.FSqlQuery;
import org.mo.eng.data.common.ISqlContext;

public class FCatalogConsole
      implements
         ICatalogConsole
{

   @AProperty
   private String _configPath;

   public FXmlNode findTree(String name){
      String fullname = RFile.makeFilename(_configPath, name) + ".xml";
      FXmlDocument doc = new FXmlDocument(fullname);
      return doc.root();
   }

   public FCatalogNodes loadNodes(ISqlContext sqlContext){
      String sql = "SELECT * FROM COM_NAV_NODE";
      FCatalogNodes nodes = new FCatalogNodes();
      FSqlQuery query = new FSqlQuery(sqlContext, sql);
      for(FRow row : query.fetchDataset()){
         FCatalogNode node = new FCatalogNode();
         node.set("group_id", row.get("obj_id"));
         node.set("name", row.get("name"));
         node.set("label", row.get("label"));
         nodes.push(node);
      }
      System.out.println(nodes.dump());
      return nodes;
   }

   public FCatalogGroups loadGroups(ISqlContext sqlContext){
      String sql = "SELECT * FROM COM_NAV_GROUP";
      FCatalogGroups groups = new FCatalogGroups();
      FSqlQuery query = new FSqlQuery(sqlContext, sql);
      for(FRow row : query.fetchDataset()){
         FCatalogGroup group = new FCatalogGroup();
         group.set("group_id", row.get("obj_id"));
         group.set("name", row.get("name"));
         group.set("label", row.get("label"));
         group.set("note", row.get("note"));
         groups.push(group);
      }
      return groups;
   }

}
