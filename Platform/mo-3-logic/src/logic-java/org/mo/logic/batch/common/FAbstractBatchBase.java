package org.mo.logic.batch.common;

import org.mo.logic.batch.IBatchConsole;

public class FAbstractBatchBase
{
   protected IBatchConsole _console;

   protected String _name;

   public FAbstractBatchBase(IBatchConsole console){
      _console = console;
   }

   public void setName(String name){
      _name = name;
   }

   public String name(){
      return _name;
   }
}
