package org.mo.eng.help;

import org.mo.com.io.RFile;
import org.mo.com.lang.FFatalError;
import org.mo.com.lang.FObjects;
import org.mo.com.lang.RObject;
import org.mo.com.lang.RString;
import org.mo.com.lang.reflect.FMethodDescriptor;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.com.system.FProcess;
import org.mo.com.xml.IXmlObject;
import org.mo.com.xml.RXmlObject;
import org.mo.core.aop.RAop;
import org.mo.core.aop.RAopDescriptor;
import org.mo.core.aop.face.ALink;
import org.mo.eng.data.IDatabaseConsole;
import org.mo.eng.data.common.FSqlContext;
import org.mo.eng.data.common.ISqlContext;
import org.mo.eng.environment.IEnvironmentConsole;
import org.mo.eng.help.common.XAction;
import org.mo.eng.help.common.XHelp;
import org.mo.eng.store.FXmlConfigConsole;

public class FHelpConsole
      extends FXmlConfigConsole<XHelp>
      implements
         IHelpConsole
{
   private static ILogger _logger = RLogger.find(FHelpConsole.class);

   @ALink
   protected IDatabaseConsole _databaseConsole;

   @ALink
   protected IEnvironmentConsole _environmentConsole;

   @Override
   public void build(FHelpBuilderArgs args){
      IXmlObject xinstance = args.getInstance();
      if(XHelp.isInstance(xinstance)){
         // 执行帮助对象内的所有编译事件
         XHelp xhelp = (XHelp)RXmlObject.copyXmlObject(xinstance);
         String outputRoot = xhelp.getOutputRoot();
         xhelp.setOutputRoot(_environmentConsole.parse(outputRoot));
         String outputPath = xhelp.getOutputPath();
         xhelp.setOutputPath(_environmentConsole.parse(outputPath));
         // 处理所以子对象
         int count = xinstance.children().count();
         for(int n = 0; n < count; n++){
            IXmlObject xchildren = xinstance.children().get(n);
            if(XAction.isInstance(xchildren)){
               // 获得一个事件，进行编译部分
               XAction xaction = (XAction)RXmlObject.copyXmlObject(xchildren);
               String outputFile = xaction.getOutputFile();
               xaction.setOutputFile(_environmentConsole.parse(outputFile));
               outputPath = xaction.getOutputPath();
               xaction.setOutputPath(_environmentConsole.parse(outputPath));
               // 对事件进行调用
               String processInvoke = xaction.getProcessInvoke();
               if(!RString.isEmpty(processInvoke)){
                  invoke(xhelp, xaction, processInvoke);
               }
            }
         }
         // 获得帮助对象
         IBuildHelper helper = RAop.find(xhelp.getProcessFace());
         // 编译帮助目录
         helper.buildCatalog(xhelp);
         // 编译帮助索引
         helper.buildIndex(xhelp);
         // 编译帮助项目
         helper.buildProject(xhelp);
         // 生成最终帮助文件
         compile(xhelp);
      }
   }

   protected void compile(XHelp xhelp){
      String name = xhelp.getName();
      String path = RFile.makeFilename(xhelp.getOutputRoot(), name + ".hhp");
      String compiler = _environmentConsole.parse("${system.help|compiler}");
      String command = compiler + " " + path;
      FProcess process = new FProcess(command);
      process.start();
   }

   @Override
   protected FObjects<XHelp> createCollection(){
      return new FObjects<XHelp>(XHelp.class);
   }

   protected void invoke(XHelp xhelp,
                         XAction xaction,
                         String methodName){
      String face = xhelp.getProcessFace();
      _logger.debug(this, "invoke", "Begin invoke method (face={0}, method={0})", face, methodName);
      Object instance = RAop.find(face);
      FMethodDescriptor method = RAopDescriptor.findMethod(face, methodName);
      // 构造参数
      Exception exception = null;
      Object[] params = method.createParameters();
      try{
         int count = method.parameterCount();
         for(int n = 0; n < count; n++){
            Class<?> type = method.parameterType(n);
            Object value = null;
            if(type == ISqlContext.class){
               value = new FSqlContext(_databaseConsole);
            }else if(type.isInterface()){
               value = RAop.find(type);
            }else if(type == XHelp.class){
               value = xhelp;
            }else if(type == XAction.class){
               value = xaction;
            }else{
               throw new FFatalError("Build param error. {0}", type);
            }
            params[n] = value;
         }
         // 调用函数
         method.invoke(instance, params);
      }catch(Exception e){
         exception = e;
      }finally{
         // 释放参数
         Exception releaseException = RObject.tryRelease(params);
         if(null != releaseException){
            exception = releaseException;
         }
         // 如果产生错误则产生例外
         if(null != exception){
            throw new FFatalError(exception);
         }
      }
   }
}
