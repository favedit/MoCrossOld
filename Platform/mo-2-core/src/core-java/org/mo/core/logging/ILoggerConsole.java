package org.mo.core.logging;

import org.mo.com.logging.ILoggerListener;

//============================================================
// <T>日志控制台接口。</T>
//============================================================
public interface ILoggerConsole
{
   int DEBUG_LEVEL = 1;

   int ERROR_LEVEL = 4;

   int FATAL_LEVEL = 5;

   int INFORMATION_LEVEL = 2;

   int MODE_LOG = 2;

   int MODE_NONE = 0;

   int MODE_STD = 1;

   int PRINT_LEVEL = 0;

   int WARNING_LEVEL = 3;

   void addFilter(String className);

   void addListener(ILoggerListener listener);

   String dateFormat();

   String storePath();
}
