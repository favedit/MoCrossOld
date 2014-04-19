package org.mo.game.editor.face.apl.logic.form;

import org.mo.web.core.container.AContainer;

import org.mo.game.editor.face.inc.form.IFormAble;
import org.mo.logic.session.ISqlSessionContext;
import org.mo.web.core.action.AWebLogin;
import org.mo.web.protocol.context.IWebContext;

/**
 * 显示数据操作用的表单
 * 
 * @author maocy
 */
@AWebLogin
public interface IWebFormAction
      extends
         IFormAble{

   String back(IWebContext context,
               ISqlSessionContext sqlContext,
               @AContainer(name = "page") FWebFormPage page);

   /**
    * <T>在数据库内删除一条记录的表单显示操作。</T>
    * <P>form_search(pack格式)：数据库内要删除的记录。</P>
    * 
    * @param context WEB环境对象
    * @param sqlContext SQL环境对象
    * @param page 数据页面
    * @return 转向地址
    */
   String delete(IWebContext context,
                 ISqlSessionContext sqlContext,
                 @AContainer(name = "page") FWebFormPage page);

   String display(IWebContext context,
                  ISqlSessionContext sqlContext,
                  @AContainer(name = "page") FWebFormPage page);

   /**
    * <T>在数据库内插入一条记录的表单显示操作。</T>
    * 
    * @param context WEB环境对象
    * @param sqlContext SQL环境对象
    * @param page 数据页面
    * @return 转向地址
    */
   String insert(IWebContext context,
                 ISqlSessionContext sqlContext,
                 @AContainer(name = "page") FWebFormPage page);

   /**
    * <T>多条记录的表单显示操作。</T>
    * 
    * @param context WEB环境对象
    * @param sqlContext SQL环境对象
    * @param page 数据页面
    * @return 转向地址
    */
   String list(IWebContext context,
               ISqlSessionContext sqlContext,
               @AContainer(name = "page") FWebFormPage page);

   String onUpdate();

   /**
    * <T>一条记录的表单显示操作。</T>
    * 
    * @param context WEB环境对象
    * @param sqlContext SQL环境对象
    * @param page 数据页面
    * @return 转向地址
    */
   String show(IWebContext context,
               ISqlSessionContext sqlContext,
               @AContainer(name = "page") FWebFormPage page);

   /**
    * <T>在数据库内修改一条记录的表单显示操作。</T>
    * <P>form_search(pack格式)：数据库内要修改的记录。</P>
    * 
    * @param context WEB环境对象
    * @param sqlContext SQL环境对象
    * @param page 数据页面
    * @return 转向地址
    */
   String update(IWebContext context,
                 ISqlSessionContext sqlContext,
                 @AContainer(name = "page") FWebFormPage page);

   /**
    * <T>弹出画面查看一条记录的详细信息。</T>
    * 
    * @param context WEB环境对象
    * @param sqlContext SQL环境对象
    * @param page 数据页面
    * @return 转向地址
    */
   String zoom(IWebContext context,
               ISqlSessionContext sqlContext,
               @AContainer(name = "page") FWebFormPage page);

}
