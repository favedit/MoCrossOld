package org.mo.com.system;

import java.io.File;
import org.mo.com.lang.FObject;
import org.mo.com.lang.FStrings;
import org.mo.com.lang.RString;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;

//============================================================
// <T>进程。</T>
//============================================================
public class FProcess
      extends FObject
{
   // 日志输出接口
   private final static ILogger _logger = RLogger.find(FProcess.class);

   // 进程建立器
   protected ProcessBuilder _builder;

   // 处理进程
   protected Process _process;

   // 命令字符串
   protected String _command;

   // 路径
   protected String _path;

   // 参数集合
   protected FStrings _parameters = new FStrings();

   // 字符编码
   protected String _charset = "utf-8";

   // 是否后台
   protected boolean _optionBackground;

   // 是否等候
   protected boolean _optionWait;

   // 输入线程
   protected FProcessInputThread _inputThread = new FProcessInputThread();

   // 输出线程
   protected FProcessOutputThread _outputThread = new FProcessOutputThread();

   // 释放监听器
   protected FListeners _destroyListeners = new FListeners();

   //============================================================
   // <T>构造进程。</T>
   //============================================================
   public FProcess(){
   }

   //============================================================
   // <T>构造进程。</T>
   //
   // @param command 命令字符串
   //============================================================
   public FProcess(String command){
      _command = command;
   }

   //============================================================
   // <T>构造进程。</T>
   //
   // @param command 命令字符串
   // @param optionWait 等待配置
   //============================================================
   public FProcess(String command,
                   boolean optionWait){
      _command = command;
      _optionWait = optionWait;
   }

   //============================================================
   // <T>获得本地对象。</T>
   //
   // @return 本地对象
   //============================================================
   public Process nativeObject(){
      return _process;
   }

   //============================================================
   // <T>获得命令字符串。</T>
   //
   // @return 命令字符串
   //============================================================
   public String command(){
      return _command;
   }

   //============================================================
   // <T>设置命令字符串。</T>
   //
   // @param command 命令字符串
   //============================================================
   public void setCommand(String command){
      _command = command;
   }

   //============================================================
   // <T>获得路径。</T>
   //
   // @return 路径
   //============================================================
   public String path(){
      return _path;
   }

   //============================================================
   // <T>设置路径。</T>
   //
   // @param path 路径
   //============================================================
   public void setPath(String path){
      _path = path;
   }

   //============================================================
   // <T>获得参数集合。</T>
   //
   // @return 参数集合
   //============================================================
   public FStrings parameters(){
      return _parameters;
   }

   //============================================================
   // <T>增加参数。</T>
   //
   // @param parameter 参数
   //============================================================
   public void addParameter(String parameter){
      _parameters.push(parameter);
   }

   //============================================================
   // <T>获得字符编码。</T>
   //
   // @return 字符编码
   //============================================================
   public String charset(){
      return _charset;
   }

   //============================================================
   // <T>设置字符编码。</T>
   //
   // @param charset 字符编码
   //============================================================
   public void setCharset(String charset){
      _charset = charset;
   }

   //============================================================
   // <T>获得后台设置。</T>
   //
   // @return 后台设置
   //============================================================
   public boolean optionBackground(){
      return _optionBackground;
   }

   //============================================================
   // <T>设置后台设置。</T>
   //
   // @param optionBackground 后台设置
   //============================================================
   public void setOptionBackground(boolean optionBackground){
      _optionBackground = optionBackground;
   }

   //============================================================
   // <T>获得等候设置。</T>
   //
   // @return 等候设置
   //============================================================
   public boolean optionWait(){
      return _optionWait;
   }

   //============================================================
   // <T>设置等候设置。</T>
   //
   // @param optionWait 等候设置
   //============================================================
   public void setOptionWait(boolean optionWait){
      _optionWait = optionWait;
   }

   //============================================================
   // <T>获得输入线程。</T>
   //
   // @return 输入线程
   //============================================================
   public FProcessInputThread inputThread(){
      return _inputThread;
   }

   //============================================================
   // <T>增加销毁监听。</T>
   //
   // @param listener 监听器
   //============================================================
   public void addDestroyListener(IListener listener){
      _destroyListeners.push(listener);
   }

   //============================================================
   // <T>获取结果内容。</T>
   //
   // @return 内容
   //============================================================
   public String fetchResult(){
      return _inputThread.info().toString();
   }

   //============================================================
   // <T>追加一行文本信息。</T>
   //
   // @param value 内容
   //============================================================
   public void appendLine(String value){
      _outputThread.info().appendLine(value);
   }

   //============================================================
   // <T>获取文本内容。</T>
   //
   // @return 内容
   //============================================================
   public String fetchText(){
      return _outputThread.info().toString();
   }

   //============================================================
   // <T>启动处理。</T>
   //
   // @return 处理结果
   //============================================================
   public boolean start(){
      if(null != _process){
         _logger.info(this, "start", "Process has start. (command={1}, option_wait={2})", _command, _optionWait);
         return false;
      }
      _inputThread.setProcess(this);
      _outputThread.setProcess(this);
      boolean outputEmpty = _outputThread.info().isEmpty();
      //............................................................
      // 生成进程构建器
      if(null == _builder){
         _builder = new ProcessBuilder(_command);
         // 设置路径
         if(!RString.isEmpty(_path)){
            _builder.directory(new File(_path));
         }
         // 设置参数
         for(String parameter : _parameters){
            _builder.command().add(parameter);
         }
         // 重新定向
         _builder.redirectErrorStream();
      }
      //............................................................
      // 执行进程
      try{
         String command = _command;
         if(!_parameters.isEmpty()){
            command += " " + _parameters.join(' ');
         }
         _logger.info(this, "start", "Process start. (command={1}, option_wait={2})", command, _optionWait);
         // 执行处理
         _process = _builder.start();
         // 启动线程
         _inputThread.start();
         if(!outputEmpty){
            _outputThread.start();
         }
         // 是否等候
         if(_optionWait){
            // 等待输出线程结束
            if(!outputEmpty){
               _outputThread.join();
            }
            // 等待进程结束
            _process.waitFor();
            // 等待输入线程结束
            _inputThread.join();
         }
      }catch(Exception e){
         _logger.error(this, "start", e);
      }
      return true;
   }

   //============================================================
   // <T>释放处理。</T>
   //============================================================
   public void onRelease(){
   }

   //============================================================
   // <T>释放处理。</T>
   //
   // @param mode 模式
   //============================================================
   public void release(){
      _process.destroy();
      try{
         onRelease();
      }catch(Exception e){
         _logger.error(this, "release", e);
      }
      _logger.debug(this, "release", "Destroy process. (process={1})", _process);
   }
}
