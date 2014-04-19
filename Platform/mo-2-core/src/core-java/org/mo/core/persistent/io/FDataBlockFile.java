package org.mo.core.persistent.io;

import org.mo.com.io.FRandomFile;
import org.mo.com.lang.FBytes;
import org.mo.com.lang.RByte;

public class FDataBlockFile
{
   private int _allocPosition = 0;

   private final int _headLength = 6;

   private int _blockLength;

   private int _dataLength;

   private final FRandomFile _file;

   private int _start;

   public FDataBlockFile(FRandomFile file){
      _file = file;
   }

   private int allocIndex(){
      byte[] bytes = new byte[2];
      int filelength = _file.length();
      while(_allocPosition < filelength){
         _file.read(_allocPosition, bytes);
         _allocPosition += _blockLength;
         if(bytes[0] == 0 && bytes[1] == 0){
            return ((_allocPosition - _start) / _blockLength) - 1;
         }
      }
      _file.write(filelength, new byte[_blockLength * 4]);
      _allocPosition += _blockLength;
      return ((_allocPosition - _start) / _blockLength) - 1;
   }

   public int append(byte[] data,
                     int offset,
                     int length){
      int next;
      int start = allocIndex();
      int index = start;
      int indexWrite = start;
      FBytes bytes = new FBytes(length);
      while(length > _dataLength){
         next = allocIndex();
         bytes.appendShort(_dataLength);
         bytes.appendInteger(next);
         bytes.append(data, offset, _dataLength);
         if(index + 1 != next){
            _file.seek(_start + _blockLength * indexWrite);
            _file.write(bytes.memory(), 0, bytes.length());
            bytes.clear();
            indexWrite = next;
         }
         index = next;
         offset += _dataLength;
         length -= _dataLength;
      }
      if(!bytes.isEmpty()){
         _file.seek(_start + _blockLength * indexWrite);
         _file.write(bytes.memory(), 0, bytes.length());
      }
      if(length > 0){
         writeBlock(index, 0, data, offset, length);
      }
      return start;
   }

   public int blockLength(){
      return _blockLength;
   }

   public void erasure(int index,
                       boolean full){
      FDataBlock block = readBlock(index, false);
      while(block != null){
         _file.erasure(_start + _blockLength * block.index(), full ? _blockLength : 2);
         block = nextBlock(block, false);
      }
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
      append(data, offset, length);
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

   public void setStart(int start){
      _start = start;
      _allocPosition = start;
   }

   public int start(){
      return _start;
   }

   private void writeBlock(int index,
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
