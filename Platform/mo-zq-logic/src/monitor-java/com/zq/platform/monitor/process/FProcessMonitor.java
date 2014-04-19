package com.zq.platform.monitor.process;

import org.mo.com.lang.FFatalError;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.com.system.FProcess;
import org.mo.com.system.FProcessGroup;
import org.mo.com.xml.FXmlNode;
import org.mo.core.aop.descriptor.IAopXmlConfig;
import org.mo.core.monitor.common.FAbstractMonitor;

//============================================================
// <T>版本监视器。</T>
//============================================================
public class FProcessMonitor
      extends FAbstractMonitor
      implements
         IProcessMonitor,
         IAopXmlConfig
{
   // 日志输出接口
   private static ILogger _logger = RLogger.find(FProcessMonitor.class);

   // 进程组
   protected FProcessGroup _group = new FProcessGroup();

   // 进程组设置
   protected FXmlNode _processGroupConfig;

   //============================================================
   // <T>构造服务监视。</T>
   //============================================================
   public FProcessMonitor(){
   }

   //============================================================
   // <T>从设置节点中加载设置信息。</T>
   //
   // @param xconfig 设置节点
   //============================================================
   @Override
   public void loadAopConfig(FXmlNode xconfig){
      _processGroupConfig = xconfig.findNode("ProcessGroup");
      for(FXmlNode xprocess : _processGroupConfig.nodes()){
         if(xprocess.isName("Process")){
            // 获得设置
            boolean optionWait = xprocess.getBoolean("wait");
            String command = xprocess.text();
            // 创建进程
            FProcess process = new FProcess();
            process.setCommand(command);
            process.setOptionWait(optionWait);
            _group.processes().push(process);
         }
      }
   }

   //============================================================
   // <T>初始化监视器。</T>
   //============================================================
   @Override
   public void initialize(){
      super.initialize();
   }

   //============================================================
   // <T>执行监视器的逻辑。</T>
   //
   // @return 处理结果
   //============================================================
   @Override
   public boolean onExecute(){
      try{
         for(FProcess process : _group.processes()){
            process.start();
            _logger.info(this, "onExecute", "Start process. " + process);
         }
      }catch(Exception e){
         throw new FFatalError(e);
      }
      return true;
   }
}
