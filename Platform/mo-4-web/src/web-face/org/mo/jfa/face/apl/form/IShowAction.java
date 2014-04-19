package org.mo.jfa.face.apl.form;

import org.mo.web.core.container.AContainer;

import org.mo.web.protocol.context.IWebContext;

//============================================================
// <T>显示接口。</T>
//============================================================
public interface IShowAction
{
   //============================================================
   // <T>构建处理。</T>
   //
   // @param context 网页环境
   //============================================================
   String construct(IWebContext context,
                    @AContainer(name = "page") FFormContainer page);

   String delete(IWebContext context);

   String insert(IWebContext context);

   String update(IWebContext context);
}
