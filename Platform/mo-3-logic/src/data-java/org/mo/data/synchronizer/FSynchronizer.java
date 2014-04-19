package org.mo.data.synchronizer;

import org.mo.com.data.ISqlConnection;
import org.mo.com.lang.FObject;
import org.mo.com.lang.FObjects;
import org.mo.data.connector.IConnectorConsole;

//============================================================
// <T>数据同步器线程来源。</T>
//============================================================
public class FSynchronizer
      extends FObject
{
   // 名称
   protected String _name;

   // 处理间隔
   protected int _interval;

   // 主域链接名称
   protected String _domainConnectionName;

   // 来源链接名称
   protected String _sourceConnectionName;

   // 目标链接名称
   protected String _targetConnectionName;

   // 链接控制台
   protected IConnectorConsole _connectorConsole;

   // 同步器单元集合
   protected FObjects<FSynchronizerUnit> _units = new FObjects<FSynchronizerUnit>(FSynchronizerUnit.class);

   // 处理线程
   protected FSynchronizerThread _thread = new FSynchronizerThread();

   //============================================================
   // <T>构造数据同步器线程来源。</T>
   //============================================================
   public FSynchronizer(){
      _thread.setSource(this);
   }

   //============================================================
   // <T>获得名称。</T>
   //
   // @return 名称
   //============================================================
   public String name(){
      return _name;
   }

   //============================================================
   // <T>设置名称。</T>
   //
   // @param name 名称
   //============================================================
   public void setName(String name){
      _name = name;
   }

   //============================================================
   // <T>获得间隔。</T>
   //
   // @return 间隔
   //============================================================
   public int interval(){
      return _interval;
   }

   //============================================================
   // <T>设置间隔。</T>
   //
   // @param interval 间隔
   //============================================================
   public void setInterval(int interval){
      _interval = interval;
   }

   //============================================================
   // <T>获得主域链接名称。</T>
   //
   // @return 主域链接名称
   //============================================================
   public String domainConnectionName(){
      return _domainConnectionName;
   }

   //============================================================
   // <T>设置主域链接名称。</T>
   //
   // @param value 主域链接名称
   //============================================================
   public void setDomainConnectionName(String value){
      _domainConnectionName = value;
   }

   //============================================================
   // <T>获得来源链接名称。</T>
   //
   // @return 来源链接名称
   //============================================================
   public String sourceConnectionName(){
      return _sourceConnectionName;
   }

   //============================================================
   // <T>设置来源链接名称。</T>
   //
   // @param value 来源链接名称
   //============================================================
   public void setSourceConnectionName(String value){
      _sourceConnectionName = value;
   }

   //============================================================
   // <T>获得目标链接名称。</T>
   //
   // @return 目标链接名称
   //============================================================
   public String targetConnectionName(){
      return _targetConnectionName;
   }

   //============================================================
   // <T>设置目标链接名称。</T>
   //
   // @param value 目标链接名称
   //============================================================
   public void setTargetConnectionName(String value){
      _targetConnectionName = value;
   }

   //============================================================
   // <T>获得链接控制台。</T>
   //
   // @return 链接控制台
   //============================================================
   public IConnectorConsole connectorConsole(){
      return _connectorConsole;
   }

   //============================================================
   // <T>设置链接控制台。</T>
   //
   // @param connectorConsole 链接控制台
   //============================================================
   public void setConnectorConsole(IConnectorConsole connectorConsole){
      _connectorConsole = connectorConsole;
   }

   //============================================================
   // <T>获得同步器单元集合。</T>
   //
   // @return 同步器单元集合
   //============================================================
   public FObjects<FSynchronizerUnit> units(){
      return _units;
   }

   //============================================================
   // <T>增加一个同步器单元。</T>
   //
   // @param unit 同步器单元
   //============================================================
   public void push(FSynchronizerUnit unit){
      _units.push(unit);
   }

   //============================================================
   // <T>获得线程。</T>
   //
   // @return 线程
   //============================================================
   public FSynchronizerThread thread(){
      return _thread;
   }

   //============================================================
   // <T>收集一个主域链接。</T>
   //
   // @return 链接
   //============================================================
   public ISqlConnection allocDomainConnection(){
      return _connectorConsole.alloc(_domainConnectionName);
   }

   //============================================================
   // <T>收集一个来源链接。</T>
   //
   // @return 链接
   //============================================================
   public ISqlConnection allocSourceConnection(){
      return _connectorConsole.alloc(_sourceConnectionName);
   }

   //============================================================
   // <T>收集一个目标链接。</T>
   //
   // @return 链接
   //============================================================
   public ISqlConnection allocTargetConnection(){
      return _connectorConsole.alloc(_targetConnectionName);
   }

   //============================================================
   // <T>收集一个数据链接。</T>
   //
   // @param connection 链接
   //============================================================
   public void free(ISqlConnection connection){
      _connectorConsole.free(connection);
   }

   //============================================================
   // <T>启动来源。</T>
   //============================================================
   public void startup(){
      _thread.start();
   }

   //============================================================
   // <T>同步处理。</T>
   //
   // @param synchronizer 同步器
   //============================================================
   public boolean process(){
      boolean result = false;
      for(FSynchronizerUnit synchronizer : _units){
         boolean processResult = synchronizer.process();
         if(processResult){
            result = true;
         }
      }
      return result;
   }
}
