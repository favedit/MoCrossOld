package org.mo.core.persistent.io;

import org.mo.com.lang.RByte;

public class FDataBlock
{
   private final byte[] _data;

   private int _index = -1;

   public FDataBlock(int length){
      _data = new byte[length];
   }

   public byte[] data(){
      return _data;
   }

   public int index(){
      return _index;
   }

   public int length(){
      return RByte.getShort(_data, 0);
   }

   public int next(){
      return RByte.getInteger(_data, 2);
   }

   public void setIndex(int index){
      _index = index;
   }

   public void setLength(int length){
      RByte.setShort(_data, 0, (short)length);
   }

   public void setNext(int next){
      RByte.setInteger(_data, 0, next);
   }
}
