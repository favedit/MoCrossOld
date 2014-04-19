package org.mo.com.xml;

//============================================================
// <T>XML数据节点。</T>
//============================================================
public class FXmlData
      extends FXmlNode
{
   //============================================================
   // <T>构造XML数据节点。</T>
   //============================================================
   public FXmlData(){
      _typeCd = EXmlNode.Data;
   }

   //============================================================
   // <T>构造XML数据节点。</T>
   //
   // @parma name 名称
   //============================================================
   public FXmlData(String name){
      super(name);
      _typeCd = EXmlNode.Data;
   }
}