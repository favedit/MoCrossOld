package org.mo.core.parser.java;

import org.mo.com.io.FStringFile;
import org.mo.core.parser.IParser;

public class FJavaParser
      implements
         IParser
{
   public Object parser(String filename){
      FStringFile file = new FStringFile(filename);
      FJavaParserWorker worker = new FJavaParserWorker(file);
      worker.parse();
      return worker.result();
   }
}
