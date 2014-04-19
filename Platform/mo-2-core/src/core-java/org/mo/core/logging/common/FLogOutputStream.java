package org.mo.core.logging.common;

import java.io.OutputStream;

public class FLogOutputStream
{
   private int _linkCount = 0;

   private OutputStream _outputStream = null;

   public FLogOutputStream(){
   }

   public FLogOutputStream(OutputStream outputStream){
      _outputStream = outputStream;
   }

   public boolean isLink(){
      return (_linkCount > 0);
   }

   public void link(){
      _linkCount++;
   }

   public OutputStream outputStream(){
      return _outputStream;
   }

   public void unlink(){
      _linkCount--;
   }
}
