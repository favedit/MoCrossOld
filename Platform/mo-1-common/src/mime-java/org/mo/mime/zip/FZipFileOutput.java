package org.mo.mime.zip;

import java.io.FileOutputStream;
import java.util.zip.ZipOutputStream;
import org.mo.com.lang.FFatalError;

public class FZipFileOutput
      extends MZipOutput
      implements
         IZipOutput
{
   protected FZipFileOutput(String fileName){
      try{
         FileOutputStream stream = new FileOutputStream(fileName);
         _output = new ZipOutputStream(stream);
      }catch(Exception e){
         throw new FFatalError(e);
      }
   }
}
