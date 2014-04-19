package org.mo.com.system;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import org.mo.com.lang.FFatalError;
import org.mo.com.lang.FString;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;

//============================================================
// <T>进程输出线程。</T>
//============================================================
public class FProcessOutputThread
      extends Thread
{
   // 日志输出接口
   private final static ILogger _logger = RLogger.find(FProcessOutputThread.class);

   // 进程
   protected FProcess _process;

   // 输出内容
   protected FString _info = new FString();

   //============================================================
   // <T>构造进程输入线程。</T>
   //
   // @param process 进程
   //============================================================
   public FProcessOutputThread(){
   }

   //============================================================
   // <T>获得进程。</T>
   //
   // @return 进程
   //============================================================
   public FProcess process(){
      return _process;
   }

   //============================================================
   // <T>设置进程。</T>
   //
   // @param process 进程
   //============================================================
   public void setProcess(FProcess process){
      _process = process;
   }

   //============================================================
   // <T>获得内容。</T>
   //
   // @return 内容
   //============================================================
   public FString info(){
      return _info;
   }

   //============================================================
   // <T>逻辑处理。</T>
   //============================================================
   @Override
   public void run(){
      OutputStreamWriter outputWriter = null;
      BufferedWriter writer = null;
      try{
         Process process = _process.nativeObject();
         OutputStream output = process.getOutputStream();
         outputWriter = new OutputStreamWriter(output);
         writer = new BufferedWriter(outputWriter);
         writer.write(_info.toString());
         writer.flush();
         // 输出结束日志
         if(!_info.isEmpty()){
            _logger.debug(this, "run", "Process output finish.\n{1}", _info);
         }else{
            _logger.debug(this, "run", "Process output finish.");
         }
      }catch(Exception e){
         _logger.error(this, "run", e);
      }finally{
         // 关闭读取器
         try{
            if(writer != null){
               writer.close();
            }
            if(outputWriter != null){
               outputWriter.close();
            }
         }catch(IOException e){
            throw new FFatalError(e);
         }
      }
   }
}
