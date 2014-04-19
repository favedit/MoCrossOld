package org.mo.com.net.http;

import org.mo.com.io.FByteStream;

//============================================================
// <T>网络数据流。</T>
//============================================================
public class FHttpStream
      extends FByteStream
{
   //============================================================
   // <T>构造网络数据流。</T>
   //============================================================
   public FHttpStream(){
   }

   //============================================================
   // <T>构造网络数据流。</T>
   //
   // @param data 数据
   //============================================================
   public FHttpStream(byte[] data){
      append(data);
   }
   //   public void flip(){
   //      flip(_position);
   //   }
   //
   //   public void flip(int position){
   //      _position = position;
   //      if(_position > 0){
   //         System.arraycopy(_memory, _position, _memory, 0, _length - _position);
   //         _length -= _position;
   //         _position = 0;
   //      }
   //   }
   //
   //   public int readInteger(){
   //      //int value = RByte.toInteger(_memory, _position);
   //      //_position += RInteger.BYTE_LENGTH;
   //      //return value;
   //      return 0;
   //   }
   //
   //   public FString readLine(){
   //      FString result = new FString();
   //      for(; _position < _length; _position++){
   //         char value = (char) _memory[_position];
   //         if(value == '\r'){
   //            continue;
   //         }
   //         if(value == '\n'){
   //            _position++;
   //            break;
   //         }
   //         result.append(value);
   //      }
   //      return result;
   //   }
   //
   //   public short readShort(){
   //      //      short value = RByte.toShort(_memory, _position);
   //      //      _position += RShort.BYTE_LENGTH;
   //      //      return value;
   //      return 0;
   //   }
   //
   //   public void reset(){
   //      _position = 0;
   //   }
   //
   //   public int testInteger(){
   //      //return RByte.toInteger(_memory, _position);
   //      return 0;
   //   }
   //
   //   public int testInteger(int offset){
   //      //return RByte.toInteger(_memory, _position + offset);
   //      return 0;
   //   }
   //
   //   public void writeInteger(int value){
   //      //      RByte.toInteger(_memory, _position, value);
   //      //      _position += RInteger.BYTE_LENGTH;
   //      //      if(_position > _length){
   //      //         _length = _position;
   //      //      }
   //   }
   //
   //   public void writeShort(short value){
   //      //      RByte.toShort(_memory, _position, value);
   //      //      _position += RShort.BYTE_LENGTH;
   //      //      if(_position > _length){
   //      //         _length = _position;
   //      //      }
   //   }
}
