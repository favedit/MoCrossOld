package org.mo.eng.parser.plsql;

import org.mo.com.lang.FStrings;

public class FSourcePlsqlPackage
{
   private final FStrings _heads = new FStrings();

   private final FStrings _bodies = new FStrings();

   public FStrings bodies(){
      return _bodies;
   }

   public FStrings heads(){
      return _heads;
   }
}
