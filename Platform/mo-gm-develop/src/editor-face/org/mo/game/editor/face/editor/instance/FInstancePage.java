package org.mo.game.editor.face.editor.instance;

import org.mo.com.lang.FString;
import org.mo.game.editor.core.instance.common.XInstanceGroup;
import org.mo.jfa.common.xmlobjects.FAbsXmlObjectPage;

public class FInstancePage
      extends FAbsXmlObjectPage<XInstanceGroup>
{

   private static final long serialVersionUID = 1L;

   private FString _source;

   // 代码类型
   private String _sourceType;

   // 代码名称
   private String _sourceName;

   public FString getSource(){
      return _source;
   }

   public String getSourceName(){
      return _sourceName;
   }

   public String getSourceType(){
      return _sourceType;
   }

   public void setSource(FString source){
      _source = source;
   }

   public void setSourceName(String sourceName){
      _sourceName = sourceName;
   }

   public void setSourceType(String sourceType){
      _sourceType = sourceType;
   }
}
