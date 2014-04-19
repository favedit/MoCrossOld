package org.mo.com.io;

import java.io.IOException;
import org.mo.com.lang.FFatalError;
import org.mo.com.lang.FString;

public class FFileLineWriter
      extends FFileWriter
{
   protected String _fileName;

   public FFileLineWriter(){
   }

   public FFileLineWriter(String fileName){
      super(fileName);
   }

   public FFileLineWriter(String fileName,
                          String charset){
      super(fileName, charset);
   }

   public String fileName(){
      return _fileName;
   }

   @Override
   public void openFile(String fileName,
                        String charset){
      super.openFile(fileName, charset);
      _fileName = fileName;
   }

   public void writeLine(FString line){
      try{
         line.append('\n');
         _writer.write(line.toString());
      }catch(IOException e){
         throw new FFatalError(e, "writeLine", "Write file line failure. (line={0})", line);
      }
   }

   public void writeLine(String line){
      try{
         _writer.write(line + '\n');
      }catch(IOException e){
         throw new FFatalError(e, "writeLine", "Write file line failure. (line={0})", line);
      }
   }
}
