/*
 * @(#)FsqlParser.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.data.procedure.common;

import org.mo.com.lang.FString;
import org.mo.com.lang.FStrings;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.com.text.RStringTokenizer;

/**
 * <T>分解包并格式化包头和包体的信息</T>
 *
 * @author ZENGD
 * @version 1.0.1
 */
public class FSqlParser
{
   private static ILogger _logger = RLogger.find(FSqlParser.class);

   protected FSqlParserContext _context = new FSqlParserContext();

   public FSqlPackage parse(FString packages,
                            FString packageBody,
                            String packageName,
                            String encoding){
      _logger.debug(this, "parse", "Parse file (file={0},encoding={1})", packageName, encoding);
      // 建立文件和分解内容
      FStrings lines = RStringTokenizer.split(packages, "\r\n");
      for(int n = 0; n < lines.count(); n++){
         lines.set(n, lines.get(n).trim());
      }
      FStrings linesBody = RStringTokenizer.split(packageBody, "\n");
      // 分解当前类
      FSqlPackage sqlPackage = new FSqlPackage(_context);
      sqlPackage.parse(packageName, linesBody, lines, 0, lines.count());
      return sqlPackage;
   }
}
