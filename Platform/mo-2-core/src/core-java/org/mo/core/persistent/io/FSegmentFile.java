package org.mo.core.persistent.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import org.mo.com.io.FRandomFile;
import org.mo.com.io.RFile;
import org.mo.com.lang.FAttributes;
import org.mo.com.lang.FFatalError;
import org.mo.com.lang.FObject;
import org.mo.com.lang.RByte;
import org.mo.com.lang.RDump;
import org.mo.com.lang.RInteger;
import org.mo.com.lang.generic.TDumpInfo;
import org.mo.com.lang.reflect.RClass;
import org.mo.core.persistent.common.IPersistentContext;

public class FSegmentFile
      extends FObject
{
   public final static String FILE_TYPE = ".pst";

   public final static String HEAD_CHARSET = "UTF-8";

   protected int _allocKey = 0;

   protected Class<?> _class = null;

   protected IPersistentContext _context;

   protected FDataBlockFile _dataFile;

   protected FRandomFile _file = null;

   protected FAttributes _heads = new FAttributes();

   protected FNamingObjidMap _map = new FNamingObjidMap();

   protected FNameBlockFile _nameFile;

   public FSegmentFile(IPersistentContext context,
                       Class<?> clazz){
      _context = context;
      _class = clazz;
      String path = RClass.packageName(clazz).toLowerCase();
      String name = RClass.shortName(clazz).toLowerCase() + FILE_TYPE;
      openFile(RFile.makeFilename(context.path(), path, name));
   }

   private synchronized int allocKey(){
      return ++_allocKey;
   }

   public void close(){
      _file.close();
   }

   public int count(){
      return _map.count();
   }

   protected void createFile(){
      _nameFile.setStart(_context.nameStart());
      _nameFile.setBlock(_context.nameBlock());
      _dataFile.setStart(_context.dataStart());
      _dataFile.setBlock(_context.dataBlock());
      // Build Head
      _heads.clear();
      _heads.set("name.start", _context.nameStart());
      _heads.set("name.block", _context.nameBlock());
      _heads.set("data.start", _context.dataStart());
      _heads.set("data.block", _context.dataBlock());
      writeHead();
      // Build Names
      byte[] names = new byte[_context.dataStart() - _context.nameStart()];
      _file.write(_context.nameStart(), names);
   }

   @Override
   public TDumpInfo dump(TDumpInfo info){
      RDump.dump(info, this);
      info.append("\n[ ");
      info.append(_heads.toString("=", ", "));
      info.append(" ]\n");
      int n = -1;
      int count = _map.count();
      info.append(" count:");
      info.appendInt(count);
      while(++n < count){
         info.append("\n");
         info.append(_map.name(n));
         info.append(" - ");
         Object item = this.findByPosition(n);
         if(item != null){
            RDump.dump(info, item);
         }else{
            info.append("[null]");
         }
      }
      return info;
   }

   public boolean existsById(int oid){
      return _map.indexOfKey(oid) != -1;
   }

   public boolean existsByName(String name){
      return _map.indexOf(name) != -1;
   }

   public Object findById(int oid){
      return findByPosition(_map.indexOfKey(oid));
   }

   private Object findByIndex(int index){
      Object item = null;
      // Read
      byte[] data = _dataFile.read(index);
      if(data.length == 0){
         throw new FFatalError("finded invalid object.[index:{0}]", index);
      }
      ByteArrayInputStream is = new ByteArrayInputStream(data);
      try{
         ObjectInputStream iis = new ObjectInputStream(is);
         item = iis.readObject();
         iis.close();
      }catch(Exception e){
         throw new FFatalError(e);
      }
      return item;
   }

   public Object findByName(String name){
      return findByPosition(_map.indexOf(name));
   }

   public Object findByPosition(int position){
      if(position != -1){
         Object item = _map.value(position);
         if(item == null){
            item = findByIndex(_map.dataIndex(position));
            _map.setValue(position, item);
         }
         return item;
      }
      return null;
   }

   public void modifyById(int oid,
                          Object value){
      modifyByPosition(_map.indexOfKey(oid), value);
   }

   private void modifyByIndex(int index,
                              Object value){
      // Get bytes
      byte[] data = null;
      if(value instanceof byte[]){
         data = (byte[])value;
      }else{
         ByteArrayOutputStream os = new ByteArrayOutputStream();
         try{
            ObjectOutputStream oos = new ObjectOutputStream(os);
            oos.writeObject(value);
            oos.close();
            data = os.toByteArray();
         }catch(Exception e){
            throw new FFatalError(e);
         }
      }
      // Write 
      _dataFile.modify(index, data, 0, data.length);
   }

   public void modifyByName(String name,
                            Object value){
      modifyByPosition(_map.indexOf(name), value);
   }

   public void modifyByPosition(int position,
                                Object value){
      int index = _map.dataIndex(position);
      modifyByIndex(index, value);
      _map.setValue(position, value);
   }

   public void openFile(String filename){
      RFile.makeFileDirectory(filename);
      // Open file
      _file = new FRandomFile(filename);
      _nameFile = new FNameBlockFile(_file);
      _dataFile = new FDataBlockFile(_file);
      // Create file
      if(_file.length() == 0){
         createFile();
      }
      // Read
      readHead();
      readNames();
   }

   public void persist(String name,
                       Object value){
      // Find index
      if(_map.contains(name)){
         throw new FFatalError("Has name object[{0}]", name);
      }
      // write
      byte[] data = null;
      if(value instanceof byte[]){
         data = (byte[])value;
      }else{
         ByteArrayOutputStream os = new ByteArrayOutputStream();
         try{
            ObjectOutputStream oos = new ObjectOutputStream(os);
            oos.writeObject(value);
            oos.close();
            data = os.toByteArray();
         }catch(Exception e){
            throw new FFatalError(e);
         }
      }
      // Alloc
      int key = allocKey();
      int dataIndex = _dataFile.append(data, 0, data.length);
      int nameIndex = _nameFile.append(dataIndex, key, name);
      if(nameIndex == -1){
         resizeNames();
         nameIndex = _nameFile.append(dataIndex, key, name);
      }
      _map.add(nameIndex, dataIndex, key, name, value);
   }

   protected void readHead(){
      //      int length = _file.readInteger(0);
      //      FBaseByteStream stream = new FBaseByteStream(_file.readBytes(4, length));
      //      _heads.unpack(new FString(stream.toArray(), HEAD_CHARSET));
      //      _nameFile.setStart(_heads.integerValue("name.start"));
      //      _nameFile.setBlock(_heads.integerValue("name.block"));
      //      _dataFile.setStart(_heads.integerValue("data.start"));
      //      _dataFile.setBlock(_heads.integerValue("data.block"));
      //      _nameFile.setLength(_dataFile.start() - _nameFile.start());
   }

   private void readName(int nameIndex,
                         byte[] data,
                         int offset){
      int length = (data[offset] & 0xFF);
      if(length != 0){
         int dataIndex = RByte.getInteger(data, offset + 1);
         int key = RByte.getInteger(data, offset + 5);
         String name = new String(data, offset + 9, length);
         _map.add(nameIndex, dataIndex, key, name, null);
         _allocKey = Math.max(_allocKey, key);
      }
   }

   private void readNames(){
      int nameStart = _nameFile.start();
      int nameBlock = _nameFile.blockLength();
      int length = _dataFile.start() - nameStart;
      byte[] bytes = _file.readBytes(nameStart, length);
      for(int i = 0, p = 0; p < bytes.length; i++, p += nameBlock){
         readName(i, bytes, p);
      }
   }

   public void removeById(int oid){
      int position = _map.indexOfKey(oid);
      if(position != -1){
         _nameFile.erasure(_map.nameIndex(position), false);
         _dataFile.erasure(_map.dataIndex(position), false);
         _map.remove(position);
      }
   }

   public void removeByName(String name){
      int position = _map.indexOf(name);
      if(position != -1){
         _nameFile.erasure(_map.nameIndex(position), false);
         _dataFile.erasure(_map.dataIndex(position), false);
         _map.remove(position);
      }
   }

   private void resizeNames(){
      int alloc = _context.nameAlloc();
      int move = RInteger.SIZE_64K;
      byte[] buffer = new byte[move];
      int length = _file.length();
      int dataStart = _dataFile.start();
      int remain = length - dataStart;
      int readPos = length - move;
      int writePos = length + alloc - move;
      while(remain > 0){
         _file.read(readPos, buffer, 0, Math.min(remain, move));
         _file.write(writePos, buffer, 0, Math.min(remain, move));
         remain -= move;
         readPos -= move;
         writePos -= move;
      }
      _file.erasure(dataStart, alloc);
      _dataFile.setStart(dataStart + alloc);
      _nameFile.setLength(dataStart + alloc - _nameFile.start());
      _heads.set("data.start", _dataFile.start());
      writeHead();
   }

   protected void writeHead(){
      //      byte[] data = _heads.pack().toBytes(HEAD_CHARSET);
      //      FBytes bytes = new FBytes();
      //      bytes.appendInteger(data.length);
      //      bytes.append(data);
      //      if(bytes.length() > _nameFile.start()){
      //         throw new FFatalError("Head length is too small.[require:{1} size:{2}]", bytes.length(), _nameFile.start());
      //      }
      //      _file.write(0, bytes.memory(), 0, bytes.length());
   }
}
