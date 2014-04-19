package org.mo.mime.csv;

public class FCsvCommandComment
      extends FAbsCsvCommand
{
   private String _description;

   public FCsvCommandComment(){
      _type = ECsvCommand.Comment;
   }

   public String description(){
      return _description;
   }

   public void setDescription(String description){
      _description = description;
   }
}
