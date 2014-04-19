package org.mo.jfa.common.page;

import org.mo.com.lang.FFatalError;
import org.mo.com.xml.FXmlNode;
import org.mo.com.xml.FXmlObjects;
import org.mo.com.xml.IXmlObject;
import org.mo.com.xml.IXmlObjects;
import org.mo.web.protocol.context.IWebInput;

public class RXmlObjects
{

   public static boolean sortChildren(IWebInput input,
                                      IXmlObject xparent){
      FXmlNode config = input.config().nodes().findNode("Data", "List", "lstOrder");
      if(null != config && null != xparent){
         // Store
         IXmlObjects xobjects = xparent.children();
         FXmlObjects xstores = new FXmlObjects();
         for(int n = 0; n < xobjects.count(); n++){
            xstores.push(xobjects.get(n));
         }
         xobjects.clear();
         // Sort
         for(FXmlNode node : config.nodes()){
            if(node.isName("ListItem")){
               String name = node.get("name");
               IXmlObject xstore = xstores.find("name", name);
               xobjects.push(xstore);
            }
         }
         return true;
      }
      return false;
   }

   public static boolean sortChildrenByNode(IXmlObject xparent,
                                            FXmlNode config){
      if(null != config && null != xparent){
         // 存储要排序的对象
         IXmlObjects xobjects = xparent.children();
         FXmlObjects xstores = new FXmlObjects();
         for(int n = 0; n < xobjects.count(); n++){
            xstores.push(xobjects.get(n));
         }
         // 根据对象名称进行排序
         FXmlObjects xsort = new FXmlObjects();
         for(FXmlNode node : config.nodes()){
            String name = node.get("name");
            IXmlObject xstore = xstores.find("name", name);
            if(null == xstore){
               throw new FFatalError("Can't find child xml object. (uuid={0})", name);
            }
            xstore.innerSet("nowrap", node.get("nowrap"));
            xsort.push(xstore);
         }
         // 存储对象
         xobjects.clear();
         for(IXmlObject xchild : xsort){
            xobjects.push(xchild);
         }
         return true;
      }
      return false;
   }

   public static boolean sortNameByNode(IXmlObject xparent,
                                        FXmlNode config){
      if(null != config && null != xparent){
         // 存储要排序的对象
         IXmlObjects xobjects = xparent.children();
         FXmlObjects xstores = new FXmlObjects();
         for(int n = 0; n < xobjects.count(); n++){
            xstores.push(xobjects.get(n));
         }
         // 根据对象名称进行排序
         FXmlObjects xsort = new FXmlObjects();
         for(FXmlNode node : config.nodes()){
            String name = node.get("name");
            IXmlObject xstore = xstores.find("name", name);
            if(null == xstore){
               throw new FFatalError("Can't find xobject by name (name={0})", name);
            }
            if(node.contains("nowrap")){
               xstore.innerSet("nowrap", node.get("nowrap"));
            }
            xsort.push(xstore);
         }
         // 存储对象
         xobjects.clear();
         for(IXmlObject xchild : xsort){
            xobjects.push(xchild);
         }
         return true;
      }
      return false;
   }

   public static boolean sortUuidByNode(IXmlObject xparent,
                                        FXmlNode config,
                                        boolean nowrap){
      if(null != config && null != xparent){
         // 存储要排序的对象
         IXmlObjects xobjects = xparent.children();
         FXmlObjects xstores = new FXmlObjects();
         for(int n = 0; n < xobjects.count(); n++){
            xstores.push(xobjects.get(n));
         }
         // 根据对象名称进行排序
         FXmlObjects xsort = new FXmlObjects();
         for(FXmlNode node : config.nodes()){
            String name = node.get("name");
            IXmlObject xstore = xstores.get(name);
            if(null == xstore){
               throw new FFatalError("Can't find xobject by uuid (uuid={0})", name);
            }
            if(nowrap && node.contains("nowrap")){
               xstore.innerSet("nowrap", node.get("nowrap"));
            }
            xsort.push(xstore);
         }
         // 存储对象
         xobjects.clear();
         for(IXmlObject xchild : xsort){
            xobjects.push(xchild);
         }
         return true;
      }
      return false;
   }
}
