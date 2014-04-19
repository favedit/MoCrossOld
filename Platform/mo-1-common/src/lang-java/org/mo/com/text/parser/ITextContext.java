package org.mo.com.text.parser;

public interface ITextContext
{
   FTextLines lines();

   boolean isLine(char value);

   boolean isLineComment(String line);

   boolean isTokenBegin(char value);

   boolean isTokenEnd(char value);

   boolean isSplitter(char value);
}
