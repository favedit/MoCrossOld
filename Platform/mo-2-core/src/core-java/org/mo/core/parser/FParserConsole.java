package org.mo.core.parser;

import org.mo.com.io.RFile;
import org.mo.com.lang.FDictionary;
import org.mo.com.lang.reflect.RClass;

public class FParserConsole
      implements
         IParserConsole
{
   private final FDictionary<IParser> _parsers = new FDictionary<IParser>(IParser.class);

   @Override
   public IParser find(String type){
      return _parsers.get(type);
   }

   @Override
   public Object parse(String filename){
      String type = RFile.extension(filename);
      IParser parser = find(type);
      if(parser != null){
         return parser.parser(filename);
      }
      return null;
   }

   @Override
   public void register(String type,
                        Class<?> clazz){
      _parsers.set(type, (IParser)RClass.newInstance(clazz));
   }
}
