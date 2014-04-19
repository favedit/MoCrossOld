package org.mo.core.monitor.common;

import org.mo.com.lang.FError;
import org.mo.core.aop.face.AProperty;

//============================================================
// <T>监视器基类。</T>
// <P>初始化和启动监视器。</P>
//============================================================
public abstract class FAbstractMonitor
      implements
         IMonitor
{
   // 分组名称
   @AProperty
   protected String _groupName = "default.group";

   // 名称
   @AProperty
   protected String _name = "default.monitor";

   // 有效性
   @AProperty
   protected boolean _valid;

   // 触发时间间隔
   @AProperty
   protected long _interval = 1000;

   // 是否进行初始化的标志
   protected boolean _initialize;

   // 当前循环次数
   protected long _current = 0;

   // 监视器的执行结果
   protected boolean _result;

   // 监视器的执行结果对象
   protected Object _resultObject;

   //============================================================
   // <T>构造监视器基类。</T>
   //============================================================
   public FAbstractMonitor(){
   }

   //============================================================
   // <T>获得分组名称。</T>
   //
   // @return 分组名称
   //============================================================
   @Override
   public String groupName(){
      return _groupName;
   }

   //============================================================
   // <T>获得名称。</T>
   //
   // @return 名称
   //============================================================
   @Override
   public String name(){
      return _name;
   }

   //============================================================
   // <T>获得有效性。</T>
   //
   // @return 有效性
   //============================================================
   @Override
   public boolean valid(){
      return _valid;
   }

   //============================================================
   // <T>设置有效性。</T>
   //
   // @param valid 有效性
   //============================================================
   public void setValid(boolean valid){
      _valid = valid;
   }

   //============================================================
   // <T>获得触发时间间隔。</T>
   //
   // @return 触发时间间隔
   //============================================================
   public long interval(){
      return _interval;
   }

   //============================================================
   // <T>设置触发时间间隔。</T>
   //
   // @param interval 触发时间间隔
   //============================================================
   public void setInterval(long interval){
      _interval = interval;
   }

   //============================================================
   // <T>获得执行结果。</T>
   //
   // @return 执行结果
   //============================================================
   public boolean result(){
      return _result;
   }

   //============================================================
   // <T>设置执行结果对象。</T>
   //
   // @param interval 执行结果对象
   //============================================================
   public void setResultObject(Object resultObject){
      _resultObject = resultObject;
   }

   //============================================================
   // <T>获得执行结果对象。</T>
   //
   // @return 执行结果对象
   //============================================================
   public Object resultObject(){
      return _resultObject;
   }

   //============================================================
   // <T>初始化监视器。</T>
   //============================================================
   @Override
   public void initialize(){
      _initialize = true;
      _current = System.currentTimeMillis();
   }

   //============================================================
   // <T>判断是否可以执行逻辑。</T>
   //
   // @return TRUE：是<br>FALSE：否 
   //============================================================
   @Override
   public boolean runable(){
      if(!_initialize){
         initialize();
      }
      if(_initialize){
         return (System.currentTimeMillis() - _current) > _interval;
      }
      return false;
   }

   //============================================================
   // <T>执行监视器的逻辑。</T>
   //
   // @return 处理结果
   //============================================================
   @Override
   public boolean onExecute(){
      return true;
   }

   //============================================================
   // <T>例外处理。</T>
   //
   // @param exception 例外对象
   //============================================================
   @Override
   public void onError(FError exception){
      _current = System.currentTimeMillis();
   }

   //============================================================
   // <T>挂起处理。</T>
   //============================================================
   @Override
   public void onSuspend(){
      _current = System.currentTimeMillis();
   }

   //============================================================
   // <T>执行监视器的逻辑。</T>
   //============================================================
   @Override
   public void execute(){
      onExecute();
   }

   //============================================================
   // <T>响应结束事件。</T>
   //
   // @return 响应结束事件
   //============================================================
   public void onRelease(){
   }
}
