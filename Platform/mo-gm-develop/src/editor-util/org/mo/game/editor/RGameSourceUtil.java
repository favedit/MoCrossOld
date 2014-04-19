package org.mo.game.editor;

import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.com.system.RApplication;
import org.mo.core.aop.RAop;
import org.mo.game.editor.core.entity.EEntitySource;
import org.mo.game.editor.core.entity.IEntityConsole;
import org.mo.game.editor.core.enums.EEnumSource;
import org.mo.game.editor.core.enums.IEnumConsole;
import org.mo.game.editor.core.message.EMessageSource;
import org.mo.game.editor.core.message.IMessageConsole;
import org.mt.core.aop.RAopTest;

//============================================================
// <T>游戏工具。</T>
//============================================================
public class RGameSourceUtil
{
   // 日志输出接口
   private final static ILogger _logger = RLogger.find(RGameSourceUtil.class);

   //============================================================
   // <T>启动处理。</T>
   //============================================================
   public static void main(String[] args){
      try{
         // 关联参数
         RApplication.linkArgements(args);
         // 获得设置
         String homeOption = RApplication.findArgement("-home", "D:\\ZW-QKB-Work\\Server\\Config\\classes");
         String configOption = RApplication.findArgement("-config", "application-windows") + ".xml";
         String sourceOption = RApplication.findArgement("-source", "all");
         // 加载设置文件
         String configFileName = homeOption + "\\" + configOption;
         RAop.configConsole().loadFile(configFileName);
         // 建立所有枚举
         if("all".equals(sourceOption) || "enum".equals(sourceOption)){
            IEnumConsole console = RAop.find(IEnumConsole.class);
            console.buildAll(EEnumSource.AllCpp);
         }
         // 建立所有实体
         if("all".equals(sourceOption) || "entity".equals(sourceOption)){
            IEntityConsole console = RAop.find(IEntityConsole.class);
            console.buildAll(EEntitySource.AllCpp);
         }
         // 建立所有消息
         if("all".equals(sourceOption) || "message".equals(sourceOption)){
            IMessageConsole console = RAop.find(IMessageConsole.class);
            console.buildAll(EMessageSource.AllCpp);
         }
         // 释放资源
         RAop.release();
      }catch(Exception e){
         _logger.error(RAopTest.class, "main", e);
      }
   }
}
