package org.mo.jfa.face.design.webform;

import org.mo.web.core.container.AContainer;

import org.mo.logic.data.ISyModuleDi;
import org.mo.logic.data.ISyTranslateDi;
import org.mo.web.protocol.context.IWebContext;

//============================================================
// <T>表单命令接口。</T>
//============================================================
public interface IWebFormAction
{
   //============================================================
   // <T>生成目录处理。</T>
   //
   // @param context 页面环境
   // @param page 数据容器
   //============================================================
   String catalog(IWebContext context,
                  @AContainer(name = "page") FWebFormPage page);

   //============================================================
   // <T>列表处理。</T>
   //
   // @param context 页面环境
   // @param page 数据容器
   //============================================================
   String list(IWebContext context,
               @AContainer(name = "page") FWebFormPage page);

   //============================================================
   // <T>新建处理。</T>
   //
   // @param context 页面环境
   // @param page 数据容器
   //============================================================
   String insert(IWebContext context,
                 @AContainer(name = "page") FWebFormPage page);

   //============================================================
   // <T>更新处理。</T>
   //
   // @param context 页面环境
   // @param page 数据容器
   //============================================================
   String update(IWebContext context,
                 @AContainer(name = "page") FWebFormPage page);

   //============================================================
   // <T>删除处理。</T>
   //
   // @param context 页面环境
   // @param page 数据容器
   //============================================================
   String delete(IWebContext context,
                 @AContainer(name = "page") FWebFormPage page);

   //============================================================
   // <T>排序处理。</T>
   //
   // @param context 页面环境
   // @param page 数据容器
   //============================================================
   String sort(IWebContext context,
               @AContainer(name = "page") FWebFormPage page);

   //============================================================
   // <T>设计处理。</T>
   //
   // @param context 页面环境
   // @param page 数据容器
   //============================================================
   String design(IWebContext context,
                 @AContainer(name = "page") FWebFormPage page);

   /**
    * <P>对source进行保存</P>
    * 
    * @param context WEB环境对象
    * @param sqlContext SQL环境对象
    * @param page 数据页面
    * @return 转向地址
    */
   String sqlSourceSave(IWebContext context,
                        @AContainer(name = "page") FWebFormPage page);

   /** 
    * <T>把当前的表单或表格生成模板并存储到模板数据库中</T>
    * 
    * @param context 页面连接
    * @param syModuleDi 模块操作接口
    * @param page 数据容器
    */
   String syncModule(IWebContext context,
                     ISyModuleDi syModuleDi,
                     @AContainer(name = "page") FWebFormPage page);

   String allCheck(IWebContext context,
                   @AContainer(name = "page") FWebFormPage page);

   /** 
    * <T>把所以的表单或表格生成模板并存储到模板数据库中</T>
    * 
    * @param context 页面连接
    * @param syModuleDi 模块操作接口
    * @param page 数据容器
    */
   String allSyncModule(IWebContext context,
                        ISyModuleDi syModuleDi,
                        @AContainer(name = "page") FWebFormPage page);

   /** 
    * <T>把所以的表单或表格生成模板并存储到模板数据库中</T>
    * 
    * @param context 页面连接
    * @param syModuleDi 模块操作接口
    * @param page 数据容器
    */
   String allSyncTranslate(IWebContext context,
                           ISyTranslateDi syTranslateDi,
                           @AContainer(name = "page") FWebFormPage page);

   /**
    * <P>新建一条sql</P>
    * 
    * @param context WEB环境对象
    * @param sqlContext SQL环境对象
    * @param page 数据页面
    * @return 转向地址
    */
   String buildSqlSource(IWebContext context,
                         @AContainer(name = "page") FWebFormPage page);
}
