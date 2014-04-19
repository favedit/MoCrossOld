/*
 * @(#)FSqlSessionContext.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.logic.session;

import org.mo.com.data.FSqlProcedure;
import org.mo.com.data.ISqlConnection;
import org.mo.com.lang.FAttributes;
import org.mo.com.lang.FStrings;
import org.mo.com.lang.IAttributes;
import org.mo.com.lang.RString;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.com.message.FInfoMessage;
import org.mo.com.message.FMessages;
import org.mo.com.message.FWarnMessage;
import org.mo.eng.data.IDatabaseConsole;
import org.mo.eng.data.common.FSqlContext;
import org.mo.logic.data.ICpSession;
import org.mo.logic.data.impl.FCpSessionImpl;

//============================================================
// <T>SQL会话环境。</T>
//============================================================
public class FSqlSessionContext
      extends FSqlContext
      implements
         ISqlSessionContext
{
   // 日志输出接口
   private static ILogger _logger = RLogger.find(FSqlSessionContext.class);

   public static String PTY_LOGIC = "logic_";

   public static String PTY_LOGGER = "logger_";

   public static String PTY_SESSION_ID = "session_id";

   public static String PTY_TYPE = "T";

   public static String PTY_CODE = "C";

   public static String PTY_CONTENT = "M";

   public static String PTY_DESCRIPTION = "M";

   public static String PTY_LOGIC_NAME = "L";

   public static String PTY_METHOD_NAME = "M";

   public static String PTY_MESSAGE = "S";

   public static String PTY_FIRST = "1";

   public static String PTY_SECOND = "2";

   public static String PTY_THIRD = "3";

   public static String PTY_FOURTH = "4";

   public static String PTY_FIFTH = "5";

   // 是否已经关联
   protected boolean _linked;

   protected String _sessionId;

   protected FMessages _messages;

   //============================================================
   // <T>构造SQL会话环境。</T>
   //============================================================
   public FSqlSessionContext(IDatabaseConsole databaseConsole){
      super(databaseConsole);
   }

   /* (non-Javadoc)
    * @see org.mo.logic.session.ISqlSessionContext#link(java.lang.String)
    */
   //============================================================
   // <T>关联会话处理。</T>
   //
   // @param sessionId 会话编号
   //============================================================
   @Override
   public void link(String sessionId){
      _linked = true;
      _sessionId = sessionId;
      // 构建登录参数
      IAttributes parameters = new FAttributes();
      parameters.set(PTY_SESSION_ID, sessionId);
      // 用户登录系统
      ICpSession cpSession = new FCpSessionImpl(activeConnection());
      cpSession.link(null, parameters);
   }

   /* (non-Javadoc)
    * @see org.mo.logic.session.ISqlSessionContext#message()
    */
   @Override
   public FMessages messages(){
      if(null == _messages){
         _messages = new FMessages();
      }
      return _messages;
   }

   /* (non-Javadoc)
    * @see org.mo.eng.data.common.FSqlContext#rollback(org.mo.com.data.ISqlConnection)
    */
   @Override
   public void rollback(ISqlConnection connection){
      if(null != connection){
         //unlink(connection);
         connection.rollback();
         _databaseConsole.free(connection);
      }
   }

   /* (non-Javadoc)
    * @see org.mo.eng.data.common.FSqlContext#release(org.mo.com.data.ISqlConnection)
    */
   @Override
   public void release(ISqlConnection connection){
      if(null != connection){
         //unlink(connection);
         connection.commit();
         _databaseConsole.free(connection);
      }
   }

   /* (non-Javadoc)
   * @see org.mo.logic.session.ISqlSessionContext#unlink()
   */
   @Override
   public void unlink(){
      if(_linked){
         unlink(activeConnection());
      }
   }

   protected void unlink(ISqlConnection connection){
      // 构建登录参数
      IAttributes parameters = new FAttributes();
      parameters.set(PTY_SESSION_ID, _sessionId);
      // 用户退出系统
      ICpSession cpSession = new FCpSessionImpl(connection);
      FSqlProcedure procedure = cpSession.unlink(null, parameters, null);
      // 获得消息列表
      FStrings messageList = procedure.parameter(PTY_LOGIC).asStrings();
      // 循环取出消息和警告，存入消息列表。
      for(String messagePack : messageList){
         FAttributes message = new FAttributes();
         message.unpack(messagePack);
         String type = message.get(PTY_TYPE);
         if("I".equals(type)){
            FInfoMessage info = new FInfoMessage();
            info.setCode(message.get(PTY_CODE));
            info.setMessage(message.get(PTY_CONTENT));
            messages().push(info);
         }else if("W".equals(type)){
            FWarnMessage warn = new FWarnMessage();
            warn.setCode(message.get(PTY_CODE));
            warn.setMessage(message.get(PTY_CONTENT));
            messages().push(warn);
         }
      }
      // 输出所有日志
      if(_logger.debugAble()){
         FStrings lines = new FStrings();
         FAttributes line = new FAttributes();
         lines.unpack(procedure.parameter(PTY_LOGGER).asString());
         for(String lineString : lines){
            line.unpack(lineString);
            // 构建消息内容
            String logicName = line.get(PTY_LOGIC_NAME);
            String methodName = line.get(PTY_METHOD_NAME);
            String message = line.get(PTY_MESSAGE);
            message = RString.format(message, line.get(PTY_FIRST), line.get(PTY_SECOND), line.get(PTY_THIRD), line.get(PTY_FOURTH), line.get(PTY_FIFTH));
            _logger.debug(logicName, methodName, message);
         }
      }
   }
}
