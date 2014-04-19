package org.mo.web.core.format;

public interface IFormatConsole
{

   ISyntaxMaker find(ESyntax type);

   ISyntaxMaker find(String type);

}
