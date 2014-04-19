package org.mo.web.core.action;

import org.mo.web.core.action.common.FWebAction;
import org.mo.web.core.action.common.XAopAction;
import org.mo.web.protocol.context.IWebContext;

//============================================================
//<T>页面命令处理控制台接口。</T>
//============================================================
public interface IActionConsole
{

   //============================================================
   // <T>增加环境设置信息。</T>
   //
   // @param name 名称
   // @param value 内容
   //============================================================
   void addContext(String name,
                   String value);

   //============================================================
   // <T>根据地址查找设置。</T>
   //
   // @param uri 地址
   // @return 设置
   //============================================================
   FWebAction findConfig(String uri);

   //============================================================
   // <T>根据地址查找命令。</T>
   //
   // @param uri 地址
   // @return 命令
   //============================================================
   XAopAction findCommand(String uri);

   //============================================================
   // <T>执行一个地址处理。</T>
   //
   // @param context 页面环境
   // @param uri 地址描述
   // @return 处理地址
   //============================================================
   String execute(IWebContext context,
                  String uri);

   //============================================================
   // <T>执行一个地址处理。</T>
   //
   // @param context 页面环境
   // @param uri 地址描述
   // @param method 函数名称
   // @return 处理地址
   //============================================================
   String execute(IWebContext context,
                  String uri,
                  String method);
}
