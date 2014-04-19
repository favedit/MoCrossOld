package org.mo.jfa.face.database.dataset;

import org.mo.web.core.container.AContainer;

import org.mo.eng.data.common.ISqlContext;
import org.mo.logic.data.ISyRecordTypeDi;
import org.mo.logic.data.ISyTranslateDi;
import org.mo.web.protocol.context.IWebContext;

public interface IDatasetAction
{

   String allBuildSource(IWebContext context,
                         ISqlContext sqlContext,
                         @AContainer(name = "page") FDatasetPage page);

   String allCheck(IWebContext context,
                   ISqlContext sqlContex,
                   @AContainer(name = "page") FDatasetPage page);

   /**
    * <T><T>同步外键所有的数据集和该数据集下的字段</T>
    * 
    * @param context 页面连接
    * @param syTranslateDi 系统翻译模块操作接口
    * @param page 数据容器
    * @return 返回显示页面
    */
   String allSyncFKey(IWebContext context,
                      ISqlContext sqlContext,
                      @AContainer(name = "page") FDatasetPage page);

   /**
    * <T><T>同步翻译所有的数据集和该数据集下的字段</T>
    * 
    * @param context 页面连接
    * @param syTranslateDi 系统翻译模块操作接口
    * @param page 数据容器
    * @return 返回显示页面
    */
   String allSyncTranslate(IWebContext context,
                           ISyTranslateDi syTranslateDi,
                           @AContainer(name = "page") FDatasetPage page);

   String allSyncType(IWebContext context,
                      ISyRecordTypeDi syRecordType,
                      @AContainer(name = "page") FDatasetPage page);

   String build(IWebContext context,
                ISqlContext sqlContex,
                @AContainer(name = "page") FDatasetPage page);

   String buildAll(IWebContext context,
                   ISqlContext sqlContext,
                   @AContainer(name = "page") FDatasetPage page);

   String buildAllProperty(IWebContext context,
                           ISqlContext sqlContext,
                           @AContainer(name = "page") FDatasetPage page);

   String buildSource(IWebContext context,
                      ISqlContext sqlContext,
                      @AContainer(name = "page") FDatasetPage page);

   String buildSystemSource(IWebContext context,
                            ISqlContext sqlContext,
                            @AContainer(name = "page") FDatasetPage page);

   String buildTypeSource(IWebContext context,
                          ISqlContext sqlContext,
                          @AContainer(name = "page") FDatasetPage page);

   /**
    * <P>新建一条viewSql</P>
    * 
    * @param context WEB环境对象
    * @param sqlContext SQL环境对象
    * @param page 数据页面
    * @return 转向地址
    */
   String buildViewSource(IWebContext context,
                          @AContainer(name = "page") FDatasetPage page);

   String catalog(IWebContext context,
                  @AContainer(name = "page") FDatasetPage page);

   String dataDownload(IWebContext context,
                       @AContainer(name = "page") FDatasetPage page);

   String dataRestore(IWebContext context,
                      @AContainer(name = "page") FDatasetPage page);

   String dataRestoreAll(IWebContext context,
                         @AContainer(name = "page") FDatasetPage page);

   String dataStore(IWebContext context,
                    @AContainer(name = "page") FDatasetPage page);

   String dataUpload(IWebContext context,
                     @AContainer(name = "page") FDatasetPage page);

   String delete(IWebContext context,
                 @AContainer(name = "page") FDatasetPage page);

   String execute(IWebContext context,
                  ISqlContext sqlContext,
                  @AContainer(name = "page") FDatasetPage page);

   String help(IWebContext context,
               @AContainer(name = "page") FDatasetPage page);

   String helpBuild(IWebContext context,
                    @AContainer(name = "page") FDatasetPage page);

   String insert(IWebContext context,
                 @AContainer(name = "page") FDatasetPage page);

   String list(IWebContext context,
               @AContainer(name = "page") FDatasetPage page);

   String sort(IWebContext context,
               @AContainer(name = "page") FDatasetPage page);

   String syncModule(IWebContext context,
                     @AContainer(name = "page") FDatasetPage page);

   /**
    * <T>同步翻译当前选中的数据集和该数据集下的字段</T>
    * 
    * @param context 页面连接
    * @param syTranslateDi 系统翻译模块操作接口
    * @param page 数据容器
    * @return 返回显示页面
    */
   String syncTranslate(IWebContext context,
                        ISyTranslateDi syTranslateDi,
                        @AContainer(name = "page") FDatasetPage page);

   String update(IWebContext context,
                 @AContainer(name = "page") FDatasetPage page);

   /**
    * <P>对source进行保存</P>
    * 
    * @param context WEB环境对象
    * @param sqlContext SQL环境对象
    * @param page 数据页面
    * @return 转向地址
    */
   String viewSourceSave(IWebContext context,
                         @AContainer(name = "page") FDatasetPage page);
}
