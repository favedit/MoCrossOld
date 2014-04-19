package org.mo.eng.format;

import org.mo.com.lang.FString;
import org.mo.com.lang.RString;
import org.mo.com.xml.FXmlNode;

public class FAbstractSyntaxMaker
{
   protected FString[] _colors;

   protected String _commentColor;

   protected String _splitter;

   protected String _stringColor;

   protected int _wordCount;

   protected FString[] _words;

   public void initialize(FXmlNode config){
      _splitter = config.nodeText("Splitter");
      _splitter = RString.replace(_splitter, "\\r", "\r");
      _splitter = RString.replace(_splitter, "\\n", "\n");
      _splitter = RString.replace(_splitter, "\\t", "\t");
      FXmlNode formatNode = config.syncNode("Format");
      _commentColor = formatNode.syncNode("Comment").get("color");
      _stringColor = formatNode.syncNode("String").get("color");
      FXmlNode oWordNode = config.findNode("Word");
      _wordCount = oWordNode.nodes().count();
      FXmlNode oWordItemNode = null;
      _words = new FString[_wordCount];
      _colors = new FString[_wordCount];
      for(int n = 0; n < _wordCount; n++){
         oWordItemNode = oWordNode.node(n);
         _colors[n] = new FString(formatNode.findNode(oWordItemNode.name()).get("color"));
         _words[n] = new FString(makeWordString(oWordItemNode.text()));
      }
   }

   public String makeWordString(String sWords){
      FString sWordString = new FString(" ");
      String[] arLines = RString.split(sWords, '\n');
      for(String sLine : arLines){
         sWordString.append(sLine.trim());
         sWordString.append(" ");
      }
      return sWordString.toString();
   }

   // 格式替换函数
   protected void replaceFormat(FString result,
                                FString sub){
      //      for(int n = 0; n < _wordCount; n++){
      //         if(_words[n].indexOf(' ', sub, ' ') >= 0){
      //            result.append("<FONT color='");
      //            result.append(_colors[n]);
      //            result.append("'>");
      //            result.append(sub);
      //            result.append("</FONT>");
      //            return;
      //         }
      //      }
      //      result.append(sub);
   }
}
