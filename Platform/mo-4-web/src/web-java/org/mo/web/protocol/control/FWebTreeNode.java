package org.mo.web.protocol.control;

import org.mo.com.lang.RBoolean;
import org.mo.com.lang.generic.TDumpInfo;
import org.mo.com.xml.FXmlNode;

public class FWebTreeNode
      extends FXmlNode
{
   public FWebTreeNode(){
      setName("Node");
   }

   public FWebTreeNode(String sType,
                       String sCaption,
                       boolean bHasChildren,
                       String sIcon){
      setName("Node");
      setType(sType);
      setCaption(sCaption);
      setChild(bHasChildren);
      setIcon(sIcon);
   }

   //   private FStdTreeNodes m_oChildren = null;
   //
   //   public boolean isEmpty() {
   //      return (m_oChildren != null) ? m_oChildren.isEmpty() : true;
   //   }
   //
   //   public FStdTreeNodes getNodes() {
   //      if (m_oChildren == null) {
   //         m_oChildren = new FStdTreeNodes();
   //      }
   //      return m_oChildren;
   //   }

   public String caption(){
      return super.get(FWebTreeView.NODE_CAPTION);
   }

   public boolean child(){
      return RBoolean.parse(super.get(FWebTreeView.NODE_CHILD));
   }

   @Override
   public TDumpInfo dump(TDumpInfo info){
      info.append("FStdTreeNode [ ");
      info.append(caption());
      info.append(" ");
      info.append(child());
      info.append(" ");
      info.append(icon());
      info.append(" ] ");
      info.append(super.dump());
      return info;
   }

   public String icon(){
      return super.get(FWebTreeView.NODE_ICON);
   }

   public void setCaption(String sValue){
      super.set(FWebTreeView.NODE_CAPTION, sValue);
   }

   public void setChild(boolean bValue){
      super.set(FWebTreeView.NODE_CHILD, RBoolean.toString(bValue));
   }

   public void setIcon(String sValue){
      super.set(FWebTreeView.NODE_ICON, sValue);
   }

   public void setType(String sValue){
      super.set(FWebTreeView.NODE_TYPE, sValue);
   }

   //   public boolean captionFormat() {
   //      return m_bCaptionFormat;
   //   }
   //
   //   public void setCaptionFormat(boolean bValue) {
   //      m_bCaptionFormat = bValue;
   //   }

   //   public String type(){
   //      return super.get(FWebTreeView.NODE_TYPE);
   //   }
}
// ************************************************************
