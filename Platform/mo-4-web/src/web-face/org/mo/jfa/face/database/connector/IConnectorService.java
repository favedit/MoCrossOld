package org.mo.jfa.face.database.connector;

import org.mo.web.protocol.context.IWebContext;
import org.mo.web.protocol.context.IWebInput;
import org.mo.web.protocol.context.IWebOutput;

//============================================================
// <T>配置数据库服务。</T>
//============================================================
public interface IConnectorService
{
   //============================================================
   // <T>列出目录处理。</T>
   //
   // @param context 环境信息
   // @param input 网页输入
   // @param output 网页输出
   //============================================================
   void catalog(IWebContext context,
                IWebInput input,
                IWebOutput output);

   //============================================================
   // <T>列出节点处理。</T>
   //
   // @param context 环境信息
   // @param input 网页输入
   // @param output 网页输出
   //============================================================
   void list(IWebContext context,
             IWebInput input,
             IWebOutput output);

   //============================================================
   // <T>新建节点处理。</T>
   //
   // @param context 环境信息
   // @param input 网页输入
   // @param output 网页输出
   //============================================================
   void insert(IWebContext context,
               IWebInput input,
               IWebOutput output);

   //============================================================
   // <T>更新节点处理。</T>
   //
   // @param context 环境信息
   // @param input 网页输入
   // @param output 网页输出
   //============================================================
   void update(IWebContext context,
               IWebInput input,
               IWebOutput output);

   //============================================================
   // <T>删除节点处理。</T>
   //
   // @param context 环境信息
   // @param input 网页输入
   // @param output 网页输出
   //============================================================
   void delete(IWebContext context,
               IWebInput input,
               IWebOutput output);

   //============================================================
   // <T>节点排序处理。</T>
   //
   // @param context 环境信息
   // @param input 网页输入
   // @param output 网页输出
   //============================================================
   void sort(IWebContext context,
             IWebInput input,
             IWebOutput output);
}
