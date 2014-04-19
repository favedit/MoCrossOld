package org.mo.mime.csv;

public class FAbsCsvCommand
      implements
         ICsvCommand
{
   protected ECsvCommand _type;

   @Override
   public ECsvCommand type(){
      return _type;
   }
}
