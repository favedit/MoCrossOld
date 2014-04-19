package org.mo.game.editor.face.apl.logic.resource;

import org.mo.eng.data.common.ISqlContext;
import org.mo.game.editor.face.inc.form.IFormAble;
import org.mo.web.core.container.AContainer;
import org.mo.web.protocol.context.IWebContext;

/**
 * 显示数据操作用的表单
 * 
 * @author maocy
 */
public interface IWebNoteAction
      extends
         IFormAble
{

   /**
    * <T>一条记录的表单显示操作。</T>
    * 
    * @param context WEB环境对象
    * @param sqlContext SQL环境对象
    * @param page 数据页面
    * @return 转向地址
    */
   String construct(IWebContext context,
                    ISqlContext sqlContext,
                    @AContainer(name = "page") FWebResourcePage page);

   String upload(IWebContext context,
                 ISqlContext sqlContext,
                 @AContainer(name = "page") FWebResourcePage page);

   String uploadSave(IWebContext context,
                     ISqlContext sqlContext,
                     @AContainer(name = "page") FWebResourcePage page);

}
