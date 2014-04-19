package org.mo.com.io;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import org.mo.com.lang.FFatalError;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;

public class FFileWriter
{
   private static ILogger _logger = RLogger.find(FFileWriter.class);

   protected BufferedWriter _writer;

   public FFileWriter(){
   }

   public FFileWriter(String fileName){
      openFile(fileName);
   }

   public FFileWriter(String fileName,
                      String charset){
      openFile(fileName, charset);
   }

   public void close(){
      if(null != _writer){
         try{
            _writer.close();
         }catch(Exception e){
            throw new FFatalError(e, "Open", "Close file failure.");
         }
      }
   }

   public void openFile(String fileName){
      _logger.debug(this, "openFile", "Open writer. (file_name={0})", fileName);
      openFile(fileName, "UTF-8");
   }

   public void openFile(String fileName,
                        String charset){
      FileOutputStream output = null;
      OutputStreamWriter writer = null;
      try{
         output = new FileOutputStream(fileName);
         writer = new OutputStreamWriter(output, charset);
         _writer = new BufferedWriter(writer);
      }catch(Exception exception){
         if(null != _writer){
            try{
               _writer.close();
            }catch(Exception e){
               throw new FFatalError(e, "Open", "Close file writer failure. (fileName={0}, charset={1})", fileName, charset);
            }
         }else if(null != writer){
            try{
               writer.close();
            }catch(Exception e){
               throw new FFatalError(e, "Open", "Close file stream writer failure. (fileName={0}, charset={1})", fileName, charset);
            }
         }else if(null != output){
            try{
               output.close();
            }catch(Exception e){
               throw new FFatalError(e, "Open", "Close file output failure. (fileName={0}, charset={1})", fileName, charset);
            }
         }
         throw new FFatalError(exception, "Open", "Open file failure. (fileName={0}, charset={1})", fileName, charset);
      }
   }

   public void write(int value){
      try{
         _writer.write(value);
      }catch(IOException e){
         throw new FFatalError(e, "write", "Write value failure. (value={0})", value);
      }
   }
}
