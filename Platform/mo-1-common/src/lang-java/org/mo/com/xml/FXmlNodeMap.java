package org.mo.com.xml;

import org.mo.com.lang.FDictionary;

//============================================================
// <T>XML节点哈希表。</T>
//============================================================
public class FXmlNodeMap
      extends FDictionary<FXmlNode>
{
   //============================================================
   // <T>构造XML节点哈希表。</T>
   //============================================================
   public FXmlNodeMap(){
      super(FXmlNode.class);
   }
}
