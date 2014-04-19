package org.mo.web.core.service;

import org.mo.web.protocol.context.IWebContext;
import org.mo.web.protocol.context.IWebInput;
import org.mo.web.protocol.context.IWebOutput;

//============================================================
// <T>服务命令处理控制台。</T>
// <P>根据访问的地址，对页面服务执行分发处理。</P>
//============================================================
public interface IServiceConsole
{
   //============================================================
   // <T>获得字符编码的类型。</T>
   //
   // @return 字符编码
   //============================================================
   String encoding();

   //============================================================
   // <T>根据名称查找实例。</T>
   //
   // @param name 名称
   // @return 实例
   //============================================================
   Object findInstance(String name);

   //============================================================
   // <T>根据服务名称执行一个服务处理过程。</T>
   //
   // @param name 服务名称
   // @param context 页面环境
   // @param input 输入信息
   // @param output 输出信息
   //============================================================
   void execute(String name,
                IWebContext context,
                IWebInput input,
                IWebOutput output);
}
