package org.mo.com.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import org.mo.com.lang.FDictionary;
import org.mo.com.lang.FFatalError;
import org.mo.com.lang.INamePair;
import org.mo.com.lang.RByte;
import org.mo.com.lang.RInteger;

public class FObjectsFile
      extends FByteFile
{
   protected FDictionary<Object> _map = new FDictionary<Object>(Object.class);

   public FObjectsFile(){
   }

   public FObjectsFile(String filename){
      //_filename = filename;
      loadFromFile(filename);
   }

   public FObjectsFile(String filename,
                       boolean load){
      //_filename = filename;
      if(load){
         loadFromFile(filename);
      }
   }

   public void loadFromFile(String fileName){
      _map.clear();
      //super.loadFromFile(fileName);
      try{
         int length;
         String name;
         int position = 0;
         ObjectInputStream objectStream;
         ByteArrayInputStream byteStream;
         while(position < _length){
            // read name
            length = RByte.getInteger(_memory, position);
            position += RInteger.BYTE_LENGTH;
            name = new String(_memory, position, length);
            position += length;
            // read data
            length = RByte.getInteger(_memory, position);
            position += RInteger.BYTE_LENGTH;
            byteStream = new ByteArrayInputStream(_memory, position, length);
            position += length;
            // Add value
            objectStream = new ObjectInputStream(byteStream);
            _map.set(name, objectStream.readObject());
         }
      }catch(Exception e){
         throw new FFatalError(e, "fileName[{0}]", fileName);
      }
   }

   public FDictionary<Object> objects(){
      return _map;
   }

   @Override
   public void saveToFile(String filename){
      try{
         ObjectOutputStream objectStream;
         ByteArrayOutputStream byteStream;
         for(INamePair<Object> pair : _map){
            // To bytes
            byteStream = new ByteArrayOutputStream();
            objectStream = new ObjectOutputStream(byteStream);
            objectStream.writeObject(pair.value());
            objectStream.close();
            byte[] data = byteStream.toByteArray();
            // Save
            byte[] nameBytes = pair.name().getBytes();
            append(RInteger.toBytes(nameBytes.length));
            append(nameBytes);
            append(RInteger.toBytes(data.length));
            append(data);
         }
         super.saveToFile(filename);
      }catch(Exception e){
         throw new FFatalError(e, "filename[{0}]", filename);
      }
   }
}
