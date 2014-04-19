package org.mo.com.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import org.mo.com.lang.FFatalError;

public class FObjectFile
      extends FByteFile
{
   public FObjectFile(){
   }

   public FObjectFile(File file){
      _fileName = file.getAbsolutePath();
   }

   public FObjectFile(String filename){
      _fileName = filename;
   }

   @SuppressWarnings("unchecked")
   public <V> V readObject(){
      //super.loadFromFile(_fileName);
      ByteArrayInputStream byteStream = new ByteArrayInputStream(_memory, 0, _length);
      try{
         ObjectInputStream objectStream = new ObjectInputStream(byteStream);
         return (V)objectStream.readObject();
      }catch(Exception e){
         throw new FFatalError(e, "Read object from file error. (file={0})", _fileName);
      }
   }

   public void writeObject(Object item){
      clear();
      try{
         ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
         ObjectOutputStream objectStream = new ObjectOutputStream(byteStream);
         objectStream.writeObject(item);
         objectStream.close();
         append(byteStream.toByteArray());
      }catch(Exception e){
         throw new FFatalError(e, "Write object to file error. (file={0}, object={1})", _fileName, item);
      }
      super.saveToFile(_fileName);
   }
}
