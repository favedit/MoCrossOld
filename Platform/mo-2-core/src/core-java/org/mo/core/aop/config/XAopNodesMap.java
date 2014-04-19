package org.mo.core.aop.config;

import org.mo.com.lang.FDictionary;

//============================================================
// <T>节点哈希表。</T>
//============================================================

@SuppressWarnings("rawtypes")
public class XAopNodesMap
      extends FDictionary<IAopNodes>
{
   //============================================================
   // <T>构造节点哈希表。</T>
   //============================================================
   public XAopNodesMap(){
      super(IAopNodes.class);
   }
}
