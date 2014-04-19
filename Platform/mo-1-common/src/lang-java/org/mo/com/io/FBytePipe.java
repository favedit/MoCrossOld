package org.mo.com.io;

import java.util.Arrays;
import org.mo.com.lang.FBytes;

//============================================================
// <T>字节管道</T>
//============================================================
public class FBytePipe
      extends FBytes
{
   // 管道头
   protected int _first;

   // 管道尾
   protected int _last;

   // 管道数据长度
   public int _dataLength;

   //============================================================
   // <T>构造字节管道</T>
   //============================================================
   public FBytePipe(){
   }

   //============================================================
   // <T>构造字节管道</T>
   //
   // @param capacity 初始化大小
   //============================================================
   public FBytePipe(int capacity){
      super(capacity);
   }

   //============================================================
   // <T>获得管道中数据长度</T>
   //============================================================
   public int dataLength(){
      if(_first < _last){
         return _last - _first;
      }else if(_first > _last){
         return _memory.length - _first + _last;
      }
      return 0;
   }

   //============================================================
   // <T>将指定长度的数据保存到指定数组的指定位置。</T>
   //
   // @param memory 目标数组 
   // @param offset 偏移量
   // @param length 要保存的字节数
   // @return 实际保存的数组长度
   //============================================================
   public int test(byte[] memory,
                   int offset,
                   int length){
      if(_first < _last){
         if(length <= _last - _first){
            System.arraycopy(_memory, _first, memory, offset, length);
            return length;
         }else{
            memory = Arrays.copyOfRange(_memory, _first, _last - _first);
            System.arraycopy(_memory, _first, memory, offset, _last - _first);
            return _last - _first;
         }
      }else if(_first > _last){
         if(length <= _memory.length - _first + _last){
            if(length <= _memory.length - _first){
               System.arraycopy(_memory, _first, memory, offset, length);
               return length;
            }else{
               System.arraycopy(_memory, _first, memory, offset, _memory.length - _first);
               System.arraycopy(_memory, 0, memory, offset + _memory.length - _first, length - (_memory.length - _first));
               return length;
            }
         }else{
            System.arraycopy(_memory, _first, memory, offset, _memory.length - _first);
            System.arraycopy(_memory, 0, memory, offset + _memory.length - _first, _last);
            return _memory.length - _first + _last;
         }
      }
      return 0;
   }

   //============================================================
   // <T>将指定长度的数据读取到指定数组的指定位置，并将管道头移到已
   // 读取数据的后面</T>
   //
   // @param memory 目标数组
   // @param offset 偏移量
   // @param length 要保存的字节数
   // @return 处理结果
   //============================================================
   public boolean read(byte[] memory,
                       int offset,
                       int length){
      if(_first < _last){
         if(length <= _last - _first){
            System.arraycopy(_memory, _first, memory, offset, length);
            if(null != _memory){
               _first += length;
            }
            return true;
         }else{
            return false;
         }
      }else if(_first > _last){
         if(length <= _memory.length - _first + _last){
            if(length <= _memory.length - _first){
               System.arraycopy(_memory, _first, memory, offset, length);
               _first += length;
               return true;
            }else{
               System.arraycopy(_memory, _first, memory, offset, _memory.length - _first);
               System.arraycopy(_memory, 0, memory, offset + _memory.length - _first, length - (_memory.length - _first));
               _first = length - (_memory.length - _first);
               return true;
            }
         }
      }
      return false;
   }

   //============================================================
   // <T>将指定数组中指定位置和长度的数据写入管道，并把管道尾移到
   // 写入数据的后面</T>
   //
   // @param memory 目标数组
   // @param offset 偏移量
   // @param length 要保存的字节数
   // @return 处理结果
   //============================================================
   public boolean write(byte[] memory,
                        int offset,
                        int length){
      if(0 == memory.length || 0 == length){
         return false;
      }
      if(_first < _last){
         if(length >= _memory.length - _last + _first){
            return false;
         }else{
            if(length <= _memory.length - _last){
               System.arraycopy(memory, offset, _memory, _last, length);
               _last += length;
               return true;
            }else{
               System.arraycopy(memory, offset, _memory, _last, _memory.length - _last);
               System.arraycopy(memory, offset + _memory.length - _last, _memory, 0, length - (_memory.length - _last));
               _last = length - (_memory.length - _last);
               return true;
            }
         }
      }else if(_first > _last){
         if(length >= _first - _last){
            return false;
         }else{
            System.arraycopy(memory, offset, _memory, _last, length);
            _last += length;
            return true;
         }
      }else{
         if(length >= _memory.length){
            return false;
         }else{
            if(length <= _memory.length - _last){
               _memory = Arrays.copyOfRange(memory, offset, length);
               _last += length;
               return true;
            }else{
               System.arraycopy(memory, offset, _memory, _last, _memory.length - _last);
               System.arraycopy(memory, offset + _memory.length - _last, _memory, 0, length - (_memory.length - _last));
               _last = length - (_memory.length - _last);
               return true;
            }
         }
      }
   }
}
