package org.mo.mime.zip;

import java.io.FileInputStream;
import java.util.zip.ZipInputStream;
import org.mo.com.lang.FFatalError;

public class FZipFileInput
      extends MZipInput
      implements
         IZipInput
{
   protected FZipFileInput(String fileName){
      try{
         FileInputStream stream = new FileInputStream(fileName);
         _input = new ZipInputStream(stream);
      }catch(Exception e){
         throw new FFatalError(e);
      }
   }
}
