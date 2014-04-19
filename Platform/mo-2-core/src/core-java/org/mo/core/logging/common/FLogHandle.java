package org.mo.core.logging.common;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import org.mo.com.io.RFile;
import org.mo.com.lang.FDictionary;
import org.mo.com.lang.FFatalError;
import org.mo.com.lang.FString;
import org.mo.com.lang.RDateTime;
import org.mo.com.lang.RInteger;
import org.mo.com.lang.RString;
import org.mo.com.xml.FXmlNode;
import org.mo.core.aop.face.AProperty;

public class FLogHandle
      implements
         ILogHandle
{
   private static FDictionary<FLogOutputStream> _outputStreamMap = new FDictionary<FLogOutputStream>(FLogOutputStream.class);

   public static OutputStream allocateOutputStream(String name){
      try{
         FLogOutputStream outputStream = null;
         synchronized(_outputStreamMap){
            if(_outputStreamMap.contains(name)){
               outputStream = _outputStreamMap.get(name);
            }else{
               if(!RFile.isFile(name)){
                  RFile.saveToFile(name, RString.EMPTY);
               }
               outputStream = new FLogOutputStream(new BufferedOutputStream(new FileOutputStream(name, true), 8192));
               _outputStreamMap.set(name, outputStream);
            }
            outputStream.link();
         }
         return outputStream.outputStream();
      }catch(Exception e){
         throw new FFatalError(e);
      }
   }

   public static boolean closeOutputStream(String name){
      FLogOutputStream outputStream = null;
      synchronized(_outputStreamMap){
         if(_outputStreamMap.contains(name)){
            outputStream = _outputStreamMap.get(name);
            outputStream.unlink();
            if(!outputStream.isLink()){
               try{
                  outputStream.outputStream().close();
               }catch(Exception e){
                  throw new FFatalError(e);
               }
               _outputStreamMap.remove(name);
            }
         }
      }
      return true;
   }

   private final FString _buffer = new FString();

   @AProperty
   private int _bufferCount = 100;

   @AProperty
   private int _bufferSize = RInteger.SIZE_32K;

   @AProperty
   private String _dateFormat;

   private String _fileName;

   @AProperty
   private String _lineSplitter = "\n";

   private OutputStream _outputStream;

   @AProperty
   private String _partten;

   @AProperty
   private String _storePath;

   public FLogHandle(){
   }

   public FLogHandle(FXmlNode config){
      _storePath = config.nodeText("Path");
      File path = new File(_storePath);
      if(!path.isDirectory()){
         path.mkdirs();
      }
      String lineSplitter = config.nodeText("LineSplitter");
      if(!RString.isEmpty(lineSplitter)){
         lineSplitter = RString.replace(lineSplitter, "\\r", "\r");
         lineSplitter = RString.replace(lineSplitter, "\\n", "\n");
         _lineSplitter = lineSplitter;
      }
      _partten = config.nodeText("Partten");
      _bufferCount = RInteger.parse(config.nodeText("BufferCount"), _bufferCount);
      _bufferSize = RInteger.parse(config.nodeText("BufferSize"), _bufferSize);
   }

   public int bufferCount(){
      return _bufferCount;
   }

   public void checkOutputStream(){
      String fileName = RDateTime.format(System.currentTimeMillis(), _partten);
      if(null != _fileName && _fileName.equalsIgnoreCase(fileName)){
         return;
      }
      try{
         if(null != _outputStream){
            flush(true);
            _outputStream.close();
         }
         _outputStream = new FileOutputStream(_storePath + fileName, true);
         _fileName = fileName;
      }catch(Exception e){
         throw new FFatalError(e);
      }
   }

   @Override
   public void close(){
      try{
         flush(true);
         if(null != _outputStream){
            closeOutputStream(_storePath + _fileName);
         }
         _outputStream = null;
      }catch(Exception e){
         throw new FFatalError(e);
      }
   }

   @Override
   public String dateFormat(){
      return _dateFormat;
   }

   @Override
   public void flush(boolean force){
      synchronized(_buffer){
         // 一次未输出过时，输出流为空
         if(null != _outputStream){
            try{
               _outputStream.write(_buffer.toBytes());
            }catch(Exception e){
               throw new FFatalError(e);
            }
            _buffer.clear();
         }
      }
   }

   @Override
   public void setDateFormat(String dateFormat){
      _dateFormat = dateFormat;
   }

   @Override
   public void setStorePath(String storePath){
      _storePath = storePath;
   }

   @Override
   public String storePath(){
      return _storePath;
   }

   public boolean writeLine(long time,
                            FString message){
      synchronized(_buffer){
         // 获得输出文件流
         String fileName = RDateTime.format(time, _partten);
         if(null != _fileName){
            if(!_fileName.equals(fileName)){
               if(null != _outputStream){
                  flush(true);
                  closeOutputStream(_storePath + _fileName);
                  _outputStream = allocateOutputStream(_storePath + fileName);
                  _fileName = fileName;
               }
            }
         }else{
            _outputStream = allocateOutputStream(_storePath + fileName);
            _fileName = fileName;
         }
         // 设置数据内容
         _buffer.append(RDateTime.format(time, _dateFormat));
         _buffer.append(message);
         _buffer.append(_lineSplitter);
      }
      flush(false);
      return true;
   }
}
