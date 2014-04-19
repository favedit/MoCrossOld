package org.mo.com.system;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.mo.com.lang.FFatalError;
import org.mo.com.lang.FString;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;

//============================================================
// <T>进程输入线程。</T>
//============================================================
public class FProcessInputThread
      extends Thread
{
   // 日志输出接口
   private final static ILogger _logger = RLogger.find(FProcessInputThread.class);

   // 进程
   protected FProcess _process;

   // 进程内容
   protected FString _info = new FString();

   //============================================================
   // <T>构造进程输入线程。</T>
   //
   // @param process 进程
   //============================================================
   public FProcessInputThread(){
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
      InputStreamReader inputReader = null;
      BufferedReader reader = null;
      try{
         boolean background = _process.optionBackground();
         Process process = _process.nativeObject();
         InputStream input = process.getInputStream();
         inputReader = new InputStreamReader(input);
         reader = new BufferedReader(inputReader);
         while(true){
            // 读取数据
            String line = reader.readLine();
            if(line == null){
               break;
            }
            // 追加字符串
            if(background){
               System.out.println(line);
            }else{
               _info.appendLine(line);
            }
         }
         // 输出结束日志
         if(!_info.isEmpty()){
            _logger.debug(this, "run", "Process input finish.\n{1}", _info);
         }else{
            _logger.debug(this, "run", "Process input finish.");
         }
      }catch(Exception e){
         _logger.error(this, "run", e);
      }finally{
         // 关闭读取器
         try{
            if(reader != null){
               reader.close();
            }
            if(inputReader != null){
               inputReader.close();
            }
         }catch(IOException e){
            throw new FFatalError(e);
         }finally{
            // 关闭进程
            _process.release();
         }
      }
   }
}
