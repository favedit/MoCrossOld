package org.mo.com.xml;

import org.mo.com.lang.FDictionary;

//============================================================
// <T>节点字典。</T>
//
// @param 051025 MAOCY 创建
//============================================================
public class FXmlNodeDictionary
      extends FDictionary<FXmlNode>
{
   //============================================================
   // <T>构造节点字典。</T>
   //============================================================
   public FXmlNodeDictionary(){
      super(FXmlNode.class);
   }
}
