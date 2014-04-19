package org.mo.com.text.parser;

import org.mo.com.lang.FObject;
import org.mo.com.lang.RInteger;

public class FTextLine
      extends FObject
{
   protected int _lineNumber;

   protected String _source;

   protected String _text;

   public FTextLine(){
   }

   public int lineNumber(){
      return _lineNumber;
   }

   public void setLineNumber(int lineNumber){
      _lineNumber = lineNumber;
   }

   public String source(){
      return _source;
   }

   public void setSource(String source){
      _source = source;
   }

   public String text(){
      return _text;
   }

   public void setText(String text){
      _text = text;
   }

   public void load(int lineNumber,
                    String line){
      _lineNumber = lineNumber;
      _source = line;
      _text = line.trim();
   }

   public String toString(){
      return RInteger.format(_lineNumber, 4) + " - " + _source + " [" + _text + "]";
   }
}
