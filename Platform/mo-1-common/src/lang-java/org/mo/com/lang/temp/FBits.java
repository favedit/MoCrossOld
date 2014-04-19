package org.mo.com.lang.temp;

import org.mo.com.lang.FString;

//============================================================
//<T>BIT集合。</T>
//============================================================
public class FBits
      extends MBits
{
   public FBits(){
   }

   public FBits(int capacity){
      super(capacity);
   }

   //   @Override
   //   public TDumpInfo dump(){
   //      return dump(new TDumpInfo(this));
   //   }
   //
   //   @Override
   //   public TDumpInfo dump(TDumpInfo info){
   //      int index = 0;
   //      int n = -1;
   //      RDump.dump(info, this);
   //      info.append(" Length:").appendInt(_length).append(" [ ");
   //      int loop = (_length >> 5) + 1;
   //      while(++n < loop){
   //         int p = -1;
   //         while(++p < MASKS_LENGTH){
   //            if(index == _length){
   //               break;
   //            }
   //            info.append((_memory[n] & MASKS[p]) == MASKS[p] ? "1" : "0");
   //            index++;
   //         }
   //         if(n != loop - 1){
   //            info.append(" ");
   //         }
   //      }
   //      info.append(" ]");
   //      return info;
   //   }
   public int find(boolean find,
                   int offset,
                   boolean fill){
      int index = 0;
      int n = offset - 1;
      int loop = (_length >> 5) + 1;
      while(++n < loop){
         int p = -1;
         while(++p < MASKS_LENGTH){
            if(index == _length){
               break;
            }
            if(find){
               if((_memory[n] & MASKS[p]) != MASKS[p]){
                  _memory[n] |= MASKS[p];
                  return index;
               }
            }else{
               if((_memory[n] & MASKS[p]) == MASKS[p]){
                  _memory[n] &= MASKS[p];
                  return index;
               }
            }
            index++;
         }
      }
      //append(fill);
      return _length - 1;
   }

   public void fromBytes(byte[] data){
      int i = 0, n = 0;
      int length = data.length;
      ensureSize(length << 3);
      int loop = length + (4 - length & 3);
      while(n < loop){
         if(n < length){
            _memory[i] |= data[n] & 0xFF;
         }
         if(++n % 4 == 0){
            i++;
         }else{
            _memory[i] <<= 8;
         }
      }
      _length = length << 3;
   }

   public byte[] toBytes(){
      int length = _length >> 3;
      byte[] result = new byte[length];
      for(int i = 0, n = 0; n < length; i++, n += 4){
         result[n++] = (byte)(_memory[i] >>> 24);
         if(n < length){
            result[n++] = (byte)(_memory[i] >>> 16);
         }
         if(n < length){
            result[n++] = (byte)(_memory[i] >>> 8);
         }
         if(n < length){
            result[n++] = (byte)_memory[i];
         }
      }
      return result;
   }

   @Override
   public String toString(){
      FString value = new FString();
      int index = 0;
      int n = -1;
      int loop = (_length >> 5) + 1;
      while(++n < loop){
         int p = -1;
         while(++p < MASKS_LENGTH){
            if(index == _length){
               break;
            }
            value.append((_memory[n] & MASKS[p]) == MASKS[p] ? "1" : "0");
            index++;
         }
      }
      return value.toString();
   }
}
