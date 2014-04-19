package org.mo.eng.entity;

public class FEntitySequence
{
   private int _value;

   public FEntitySequence(int value){
      _value = value;
   }

   public int current(){
      return _value;
   }

   public int next(){
      return ++_value;
   }
}
