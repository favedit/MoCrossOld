package org.mo.eng.format;

public interface IFormatConsole
{
   ISyntaxMaker find(ESyntax type);

   ISyntaxMaker find(String type);
}
