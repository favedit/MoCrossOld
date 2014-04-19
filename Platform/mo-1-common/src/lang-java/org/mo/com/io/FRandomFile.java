package org.mo.com.io;

import java.io.RandomAccessFile;
import org.mo.com.lang.FError;
import org.mo.com.lang.FFatalError;

public class FRandomFile
{
   protected RandomAccessFile _file = null;

   protected long _length = 0;

   protected long _position = 0;

   public FRandomFile(){
   }

   public FRandomFile(String filename){
      try{
         _file = new RandomAccessFile(filename, "rws");
         _length = _file.length();
      }catch(Exception e){
         throw new FFatalError(e);
      }
   }

   public void close(){
      try{
         _file.close();
      }catch(Exception e){
         throw new FFatalError(e);
      }
   }

   public void erasure(int position,
                       int length){
      seek(EFileOffset.BEGINNING, position);
      write(new byte[length]);
   }

   public boolean isBof(){
      return (_position == -1);
   }

   public boolean isEof(){
      return (_position >= _length);
   }

   public int length(){
      try{
         return (int)_file.length();
      }catch(Exception e){
         throw new FFatalError(e);
      }
   }

   public long position(){
      return _position;
   }

   public int read(byte[] data){
      try{
         return _file.read(data, 0, data.length);
      }catch(Exception e){
         throw new FFatalError(e);
      }
   }

   public int read(byte[] data,
                   int length){
      return read(data, 0, length);
   }

   public int read(byte[] data,
                   int offset,
                   int length){
      try{
         return _file.read(data, offset, length);
      }catch(Exception e){
         throw new FFatalError(e);
      }
   }

   public int read(int position,
                   byte[] data){
      try{
         seek(EFileOffset.BEGINNING, position);
         return _file.read(data, 0, data.length);
      }catch(Exception e){
         throw new FFatalError(e);
      }
   }

   public int read(int position,
                   byte[] data,
                   int offset,
                   int length){
      try{
         seek(EFileOffset.BEGINNING, position);
         return _file.read(data, offset, length);
      }catch(Exception e){
         throw new FFatalError(e);
      }
   }

   public byte readByte(int position){
      try{
         seek(EFileOffset.BEGINNING, position);
         return _file.readByte();
      }catch(Exception e){
         throw new FFatalError(e);
      }
   }

   public byte[] readBytes(int length){
      try{
         byte[] data = new byte[length];
         int read = _file.read(data, 0, data.length);
         if(read != data.length){
            byte[] alloc = new byte[read];
            System.arraycopy(data, 0, alloc, 0, read);
            return alloc;
         }
         return data;
      }catch(Exception e){
         throw new FFatalError(e);
      }
   }

   public byte[] readBytes(int position,
                           int length){
      seek(EFileOffset.BEGINNING, position);
      return readBytes(length);
   }

   public int readInteger(int position){
      try{
         seek(EFileOffset.BEGINNING, position);
         byte[] data = new byte[4];
         _file.read(data, 0, data.length);
         return (data[0] & 0xFF) + ((data[1] & 0xFF) << 8) + ((data[2] & 0xFF) << 16) + ((data[3] & 0xFF) << 24);
      }catch(Exception e){
         throw new FFatalError(e);
      }
   }

   public short readShort(int position){
      try{
         seek(EFileOffset.BEGINNING, position);
         byte[] data = new byte[2];
         _file.read(data, 0, data.length);
         return (short)((data[0] & 0xFF) + ((data[1] & 0xFF) << 8));
      }catch(Exception e){
         throw new FFatalError(e);
      }
   }

   public void seek(EFileOffset offset) throws FError{
      seek(offset, 0);
   }

   public void seek(EFileOffset offset,
                    long position) throws FError{
      if(offset == EFileOffset.BOF){
         position = -1;
      }else if(offset == EFileOffset.CURRENT){
         position += _position;
      }else if(offset == EFileOffset.END){
         position = _length - position;
      }else if(offset == EFileOffset.EOF){
         position = _length;
      }
      //if(_position != position){
      try{
         _file.seek(position);
         _position = position;
      }catch(Exception e){
         throw new FFatalError(e);
      }
      //}
   }

   public void seek(long position){
      seek(EFileOffset.BEGINNING, position);
   }

   public void write(byte[] data){
      write(data, 0, data.length);
   }

   public void write(byte[] data,
                     int length){
      write(data, 0, length);
   }

   public void write(byte[] data,
                     int offset,
                     int length){
      try{
         _file.write(data, offset, length);
      }catch(Exception e){
         throw new FFatalError(e);
      }
   }

   public void write(int position,
                     byte[] data){
      seek(EFileOffset.BEGINNING, position);
      write(data, 0, data.length);
   }

   public void write(int position,
                     byte[] data,
                     int offset,
                     int length){
      try{
         seek(EFileOffset.BEGINNING, position);
         _file.write(data, offset, length);
      }catch(Exception e){
         throw new FFatalError(e);
      }
   }

   public void writeByte(int value){
      try{
         _file.writeByte(value);
      }catch(Exception e){
         throw new FFatalError(e);
      }
   }

   public void writeByte(int position,
                         int value){
      try{
         seek(EFileOffset.BEGINNING, position);
         _file.writeByte(value);
      }catch(Exception e){
         throw new FFatalError(e);
      }
   }
}
