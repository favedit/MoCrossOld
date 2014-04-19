package org.mo.jfa.face.database.statistics;

import org.mo.core.aop.face.ALink;
import org.mo.data.statistics.IStatisticsConsole;
import org.mo.data.statistics.common.XStatistics;
import org.mo.jfa.common.xmlobjects.FAbsXmlObjectService;
import org.mo.web.protocol.context.IWebContext;
import org.mo.web.protocol.context.IWebInput;
import org.mo.web.protocol.context.IWebOutput;

//============================================================
// <T>配置数据库服务。</T>
//============================================================
public class FStatisticsService
      extends FAbsXmlObjectService<XStatistics>
      implements
         IStatisticsService
{
   // 配置数据库接口
   @ALink
   protected IStatisticsConsole _statisticsConsole;

   //============================================================
   // <T>列出目录处理。</T>
   //
   // @param context 环境信息
   // @param input 网页输入
   // @param output 网页输出
   //============================================================
   @Override
   public void catalog(IWebContext context,
                       IWebInput input,
                       IWebOutput output){
      catalog(_statisticsConsole, context, input, output);
   }

   //============================================================
   // <T>列出节点处理。</T>
   //
   // @param context 环境信息
   // @param input 网页输入
   // @param output 网页输出
   //============================================================
   @Override
   public void list(IWebContext context,
                    IWebInput input,
                    IWebOutput output){
      list(_statisticsConsole, context, input, output);
   }

   //============================================================
   // <T>新建节点处理。</T>
   //
   // @param context 环境信息
   // @param input 网页输入
   // @param output 网页输出
   //============================================================
   @Override
   public void insert(IWebContext context,
                      IWebInput input,
                      IWebOutput output){
      insert(_statisticsConsole, context, input, output);
   }

   //============================================================
   // <T>更新节点处理。</T>
   //
   // @param context 环境信息
   // @param input 网页输入
   // @param output 网页输出
   //============================================================
   @Override
   public void update(IWebContext context,
                      IWebInput input,
                      IWebOutput output){
      update(_statisticsConsole, context, input, output);
   }

   //============================================================
   // <T>删除节点处理。</T>
   //
   // @param context 环境信息
   // @param input 网页输入
   // @param output 网页输出
   //============================================================
   @Override
   public void delete(IWebContext context,
                      IWebInput input,
                      IWebOutput output){
      delete(_statisticsConsole, context, input, output);
   }

   //============================================================
   // <T>节点排序处理。</T>
   //
   // @param context 环境信息
   // @param input 网页输入
   // @param output 网页输出
   //============================================================
   @Override
   public void sort(IWebContext context,
                    IWebInput input,
                    IWebOutput output){
      sort(_statisticsConsole, context, input, output);
   }
}
