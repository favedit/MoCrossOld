package org.mo.core.persistent.io;

import org.mo.com.io.FRandomFile;
import org.mo.com.lang.FBytes;
import org.mo.com.lang.RByte;

public class FNameBlockFile
{
   private int _allocPosition = 0;

   private final int _headLength = 6;

   private int _blockLength;

   private int _dataLength;

   private final FRandomFile _file;

   private int _start;

   private int _length;

   public FNameBlockFile(FRandomFile file){
      _file = file;
   }

   private int allocIndex(){
      int end = _start + _length;
      while(_allocPosition < end){
         if(_file.readByte(_allocPosition) == 0){
            _allocPosition += _blockLength;
            return (_allocPosition - _start) / _blockLength - 1;
         }else{
            _allocPosition += _blockLength;
         }
      }
      return -1;
   }

   public int append(int index,
                     int key,
                     String name){
      int alloc = allocIndex();
      if(alloc != -1){
         byte[] bytes = name.getBytes();
         byte[] data = new byte[_blockLength];
         data[0] = (byte)name.length();
         RByte.setInteger(data, 1, index);
         RByte.setInteger(data, 5, key);
         System.arraycopy(bytes, 0, data, 9, Math.min(bytes.length, _blockLength - 9));
         _file.seek(_start + _blockLength * alloc);
         _file.write(data, 0, data.length);
      }
      return alloc;
   }

   public int blockLength(){
      return _blockLength;
   }

   public void erasure(int index,
                       boolean full){
      _file.erasure(_start + _blockLength * index, full ? _blockLength : 1);
   }

   public void erasureBlock(int index){
      _file.erasure(_start + _blockLength * index, _blockLength);
   }

   public boolean hasData(int index){
      return readBlock(index, false) != null;
   }

   public void modify(int index,
                      byte[] data,
                      int offset,
                      int length){
      erasure(index, false);
      int allocPosition = _allocPosition;
      _allocPosition = _start + _blockLength * index;
      //append(data, offset, length);
      _allocPosition = allocPosition;
   }

   public FDataBlock nextBlock(FDataBlock block,
                               boolean full){
      int next = block.next();
      if(next != 0){
         block.setIndex(next);
         _file.seek(_start + _blockLength * next);
         _file.read(block.data(), 0, full ? _blockLength : _headLength);
         return (block.length() != 0) ? block : null;
      }
      return null;
   }

   public byte[] read(int index){
      FBytes bytes = new FBytes();
      FDataBlock block = readBlock(index, true);
      while(block != null){
         bytes.append(block.data(), _headLength, Math.min(block.length(), _dataLength));
         block = nextBlock(block, true);
      }
      return bytes.toArray();
   }

   public FDataBlock readBlock(int index,
                               boolean full){
      FDataBlock block = new FDataBlock(_blockLength);
      block.setIndex(index);
      _file.seek(_start + _blockLength * index);
      _file.read(block.data(), 0, full ? _blockLength : _headLength);
      return (block.length() != 0) ? block : null;
   }

   public void setBlock(int block){
      _blockLength = block;
      _dataLength = block - _headLength;
   }

   public void setLength(int length){
      _length = length;
   }

   public void setStart(int start){
      _start = start;
      _allocPosition = start;
   }

   public int start(){
      return _start;
   }

   public void writeBlock(int index,
                          int next,
                          byte[] data,
                          int offset,
                          int length){
      byte[] memory = new byte[_blockLength];
      RByte.setShort(memory, 0, (short)length);
      RByte.setInteger(memory, 2, next);
      System.arraycopy(data, offset, memory, _headLength, length);
      _file.seek(_start + _blockLength * index);
      _file.write(memory, 0, _blockLength);
   }
}
