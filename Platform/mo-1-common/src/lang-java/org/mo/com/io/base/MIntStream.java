package org.mo.com.io.base;

import org.mo.com.lang.generic.MInts;

//============================================================
// <T>整数数据流基类。</T>
//============================================================
public abstract class MIntStream
      extends MInts
{
   // 数据位置
   protected int _position = 0;

   //============================================================
   // <T>构造整数数据流基类。</T>
   //============================================================
   public MIntStream(){
   }

   //============================================================
   // <T>构造整数数据流基类。</T>
   //
   // @param capacity 容量
   //============================================================
   public MIntStream(int capacity){
      super(capacity);
   }

   //============================================================
   // <T>判断是否存在下个数据。</T>
   //
   // @return 是否存在
   //============================================================
   public boolean hasNext(){
      return _position < _length - 1;
   }

   //============================================================
   // <T>获得位置。</T>
   //
   // @return 位置
   //============================================================
   public int position(){
      return _position;
   }

   //============================================================
   // <T>获得位置。</T>
   //
   // @return 位置
   //============================================================
   public void setPosition(int position){
      if(position < 0){
         position = 0;
      }
      if(position > _length - 1){
         position = _length - 1;
      }
      _position = position;
   }

   //============================================================
   // <T>获得位置。</T>
   //
   // @return 位置
   //============================================================
   public int read(){
      return (_position < _length - 1) ? _memory[_position++] : _memory[_position];
   }

   //============================================================
   // <T>获得位置。</T>
   //
   // @return 位置
   //============================================================
   public int read(int[] data){
      return read(data, 0, data.length);
   }

   //============================================================
   // <T>获得位置。</T>
   //
   // @return 位置
   //============================================================
   public int read(int[] data,
                   int offset,
                   int length){
      int copy = Math.min(length, _length - _position);
      System.arraycopy(_memory, _position, data, offset, copy);
      _position += copy;
      return copy;
   }

   //============================================================
   // <T>写入一个字节。</T>
   //
   // @param data 数据
   //============================================================
   public int write(int data){
      if(_position < _length - 1){
         _memory[_position] = data;
      }else{
         append(data);
      }
      _position++;
      return 1;
   }

   //============================================================
   // <T>写入一个字节。</T>
   //
   // @param data 数据
   //============================================================
   public int write(int[] data){
      return write(data, 0, data.length);
   }

   //============================================================
   // <T>写入字节集合。</T>
   //
   // @param data 数据
   //============================================================
   public int write(int[] data,
                    int offset,
                    int length){
      if(length > _length - _position){
         ensureSize(_position + length);
         System.arraycopy(data, offset, _memory, _position, length);
         _position += length;
         _length = _position;
      }else{
         System.arraycopy(data, offset, _memory, _position, length);
         _position += length;
      }
      return length;
   }
}
