package org.mo.com.io;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import org.mo.com.lang.FFatalError;

public class FFileReader
{
   protected String _fileName;

   protected BufferedReader _reader;

   public FFileReader(){
   }

   public FFileReader(String fileName){
      openFile(fileName);
   }

   public FFileReader(String fileName,
                      String charset){
      openFile(fileName, charset);
   }

   public void close(){
      if(null != _reader){
         try{
            _reader.close();
         }catch(Exception e){
            throw new FFatalError(e, "Open", "Close file failure.");
         }
      }
   }

   public String fileName(){
      return _fileName;
   }

   public void openFile(String fileName){
      openFile(fileName, "UTF-8");
   }

   public void openFile(String fileName,
                        String charset){
      FileInputStream input = null;
      InputStreamReader reader = null;
      try{
         _fileName = fileName;
         input = new FileInputStream(fileName);
         reader = new InputStreamReader(input, charset);
         _reader = new BufferedReader(reader);
      }catch(Exception exception){
         if(null != _reader){
            try{
               _reader.close();
            }catch(Exception e){
               throw new FFatalError(e, "openFile", "Close file reader failure. (fileName={0}, charset={1})", fileName, charset);
            }
         }else if(null != reader){
            try{
               reader.close();
            }catch(Exception e){
               throw new FFatalError(e, "openFile", "Close file stream reader failure. (fileName={0}, charset={1})", fileName, charset);
            }
         }else if(null != input){
            try{
               input.close();
            }catch(Exception e){
               throw new FFatalError(e, "openFile", "Close file input failure. (fileName={0}, charset={1})", fileName, charset);
            }
         }
         throw new FFatalError(exception, "openFile", "Open file failure. (fileName={0}, charset={1})", fileName, charset);
      }
   }

   public void openStream(InputStreamReader inputStream,
                          String charset){
      try{
         /// reader = new InputStreamReader(inputStream, charset);
         _reader = new BufferedReader(inputStream);
      }catch(Exception exception){
         throw new FFatalError(exception, "openFile", "Open file failure. (inputStream={0}, charset={1})", inputStream, charset);
      }
   }

   public int read(){
      try{
         return _reader.read();
      }catch(IOException e){
         throw new FFatalError(e, "readLine", "Read file line failure.");
      }
   }

   public String readLine(){
      try{
         return _reader.readLine();
      }catch(IOException e){
         throw new FFatalError(e, "readLine", "Read file line failure.");
      }
   }
}
