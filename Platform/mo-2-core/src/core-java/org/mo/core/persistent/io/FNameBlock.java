package org.mo.core.persistent.io;

import org.mo.com.lang.RByte;

public class FNameBlock
{
   private final byte[] _data;

   public FNameBlock(int length){
      _data = new byte[length];
   }

   public byte[] data(){
      return _data;
   }

   public int index(){
      return RByte.getInteger(_data, 1);
   }

   public int key(){
      return RByte.getInteger(_data, 5);
   }

   public int length(){
      return _data[0] & 0xFF;
   }

   public String name(){
      return new String(_data, 9, _data[0] & 0xFF);
   }

   public void setIndex(int index){
      RByte.setInteger(_data, 1, index);
   }

   public void setKey(int next){
      RByte.setInteger(_data, 5, next);
   }

   public void setName(String name){
      byte[] bytes = name.getBytes();
      _data[0] = (byte)bytes.length;
      System.arraycopy(bytes, 0, _data, 9, Math.min(bytes.length, _data.length - 9));
   }
}
