package org.mo.mime.csv;

public class FCsvCommandProperty
      extends FAbsCsvCommand
{
   private String _name;

   private String _value;

   public FCsvCommandProperty(){
      _type = ECsvCommand.Property;
   }

   public String name(){
      return _name;
   }

   public void setName(String name){
      _name = name;
   }

   public void setValue(String value){
      _value = value;
   }

   public String value(){
      return _value;
   }
}
