package org.mo.core.parser;

public interface IParserConsole
{
   IParser find(String type);

   Object parse(String filename);

   void register(String type,
                 Class<?> clazz);
}
