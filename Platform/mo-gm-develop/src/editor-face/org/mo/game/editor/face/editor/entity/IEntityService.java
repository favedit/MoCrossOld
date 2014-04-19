package org.mo.game.editor.face.editor.entity;

import org.mo.web.protocol.context.IWebContext;
import org.mo.web.protocol.context.IWebInput;
import org.mo.web.protocol.context.IWebOutput;

//============================================================
// <T>实体服务接口。</T>
//============================================================
public interface IEntityService
{
   //============================================================
   // <T>列出目录内容。</T>
   //
   // @param context 页面环境
   // @param input 输入配置
   // @param output 输出配置
   //============================================================
   void catalog(IWebContext context,
                IWebInput input,
                IWebOutput output);

   //============================================================
   // <T>列出节点内容。</T>
   //
   // @param context 页面环境
   // @param input 输入配置
   // @param output 输出配置
   //============================================================
   void list(IWebContext context,
             IWebInput input,
             IWebOutput output);

   //============================================================
   // <T>搜索节点内容。</T>
   //
   // @param context 页面环境
   // @param input 输入配置
   // @param output 输出配置
   //============================================================
   void search(IWebContext context,
               IWebInput input,
               IWebOutput output);

   //============================================================
   // <T>新建节点。</T>
   //
   // @param context 页面环境
   // @param input 输入配置
   // @param output 输出配置
   //============================================================
   void insert(IWebContext context,
               IWebInput input,
               IWebOutput output);

   //============================================================
   // <T>更新节点。</T>
   //
   // @param context 页面环境
   // @param input 输入配置
   // @param output 输出配置
   //============================================================
   void update(IWebContext context,
               IWebInput input,
               IWebOutput output);

   //============================================================
   // <T>删除节点。</T>
   //
   // @param context 页面环境
   // @param input 输入配置
   // @param output 输出配置
   //============================================================
   void delete(IWebContext context,
               IWebInput input,
               IWebOutput output);

   //============================================================
   // <T>排序节点。</T>
   //
   // @param context 页面环境
   // @param input 输入配置
   // @param output 输出配置
   //============================================================
   void sort(IWebContext context,
             IWebInput input,
             IWebOutput output);
}
