package com.zq.platform.builder;

import org.mo.com.lang.FFatalError;
import org.mo.core.monitor.common.FAbstractMonitor;

//============================================================
// <T>开发编译监视器。</T>
//============================================================
public class FDevelopBuilderMonitor
      extends FAbstractMonitor
{
   // 构建器接口
   protected IDevelopBuilder _builder;

   //============================================================
   // <T>构造服务监视。</T>
   //============================================================
   public FDevelopBuilderMonitor(){
   }

   //============================================================
   // <T>获得构建器接口。</T>
   //
   // @return 构建器接口
   //============================================================
   public IDevelopBuilder builder(){
      return _builder;
   }

   //============================================================
   // <T>设置构建器接口。</T>
   //
   // @param builder 构建器接口
   //============================================================
   public void setBuilder(IDevelopBuilder builder){
      _builder = builder;
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
         return _builder.process();
      }catch(Exception e){
         throw new FFatalError(e);
      }
   }
}
