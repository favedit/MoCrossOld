package org.mo.com.io;

import java.io.IOException;
import java.io.InputStreamReader;
import org.mo.com.lang.FFatalError;

public class FFileLineReader
      extends FFileReader
{
   protected String _line;

   public FFileLineReader(){
   }

   public FFileLineReader(String fileName){
      super(fileName);
   }

   public FFileLineReader(String fileName,
                          String charset){
      super(fileName, charset);
   }

   public boolean hasNext(){
      return (null != _line);
   }

   @Override
   public void openFile(String fileName,
                        String charset){
      super.openFile(fileName, charset);
      try{
         _line = _reader.readLine();
      }catch(Exception e){
         throw new FFatalError(e, "openFile", "Read file first line failure.");
      }
   }

   @Override
   public void openStream(InputStreamReader inputStream,
                          String charset){
      super.openStream(inputStream, charset);
      try{
         _line = _reader.readLine();
      }catch(Exception e){
         throw new FFatalError(e, "openStream", "Read file first line failure.");
      }
   }

   @Override
   public String readLine(){
      try{
         String line = _line;
         _line = _reader.readLine();
         return line;
      }catch(IOException e){
         throw new FFatalError(e, "readLine", "Read file line failure.");
      }
   }
}
