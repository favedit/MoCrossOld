package org.mo.jfa.face.database.codelist;

import org.mo.web.core.container.AContainer;

import org.mo.eng.data.common.ISqlContext;
import org.mo.logic.data.ICmCodeDi;
import org.mo.logic.data.ICmCodeListDi;
import org.mo.web.core.webform.IFormPage;
import org.mo.web.protocol.context.IWebContext;

public interface ICodeListAction
{

   String build(IWebContext context,
                ISqlContext sqlContex,
                @AContainer(name = "page") FCodeListPage page);

   String buildAll(IWebContext context,
                   ISqlContext sqlContex,
                   @AContainer(name = "page") FCodeListPage page);

   String catalog(IWebContext context,
                  @AContainer(name = IFormPage.Page) FCodeListPage page);

   String commit(IWebContext context,
                 ISqlContext sqlContext,
                 ICmCodeListDi cmCodeListDi,
                 ICmCodeDi cmCodeDi,
                 @AContainer(name = IFormPage.Page) FCodeListPage page);

   String commitAll(IWebContext context,
                    ISqlContext sqlContext,
                    ICmCodeListDi cmCodeListDi,
                    ICmCodeDi cmCodeDi,
                    @AContainer(name = IFormPage.Page) FCodeListPage page);

   String delete(IWebContext context,
                 @AContainer(name = IFormPage.Page) FCodeListPage page);

   String execute(IWebContext context,
                  ISqlContext sqlContext,
                  @AContainer(name = "page") FCodeListPage page);

   String insert(IWebContext context,
                 @AContainer(name = IFormPage.Page) FCodeListPage page);

   String list(IWebContext context,
               @AContainer(name = IFormPage.Page) FCodeListPage page);

   String sort(IWebContext context,
               @AContainer(name = IFormPage.Page) FCodeListPage page);

   String update(IWebContext context,
                 @AContainer(name = IFormPage.Page) FCodeListPage page);

}
