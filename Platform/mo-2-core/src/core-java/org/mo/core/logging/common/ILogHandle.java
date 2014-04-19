package org.mo.core.logging.common;

import org.mo.com.lang.FString;

public interface ILogHandle
{
   void close();

   String dateFormat();

   void flush(boolean force);

   void setDateFormat(String dateFormat);

   void setStorePath(String storePath);

   String storePath();

   boolean writeLine(long time,
                     FString message);
}
