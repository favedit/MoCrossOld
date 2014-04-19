package org.mo.mime.csv;

public abstract class FAbsCsvContainer
      implements
         ICsvContainer
{
   protected FCsvColumns _columns = new FCsvColumns();

   @Override
   public FCsvColumns columns(){
      return _columns;
   }

   public ICsvLine createLine(){
      FCsvLine line = new FCsvLine();
      line._container = this;
      return line;
   }
}
