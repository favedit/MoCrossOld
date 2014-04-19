package org.mo.web.core.webtree.common;

import org.mo.com.lang.FAttributes;
import org.mo.com.xml.EXmlConfig;
import org.mo.com.xml.FXmlNode;
import org.mo.web.core.webtree.base.XBaseTreeNodeType;

public class XTreeNodeType
      extends XBaseTreeNodeType
{

   private FAttributes _attributes = new FAttributes();

   public String get(String name){
      return _attributes.get(name);
   }

   public void set(String name,
                   String value){
      _attributes.set(name, value);
   }

   @Override
   public FXmlNode toNode(EXmlConfig config){
      FXmlNode node = super.toNode(config);
      node.attributes().append(_attributes);
      return node;
   }

}
