package org.mo.jfa.face.design.webtheme;

import org.mo.web.core.container.AContainer;

import org.mo.jfa.face.inc.form.IFormAble;
import org.mo.web.protocol.context.IWebContext;

public interface IWebThemeAction
      extends
         IFormAble
{

   String catalog(IWebContext context,
                  @AContainer(name = "page") FWebThemePage page);

   String delete(IWebContext context,
                 @AContainer(name = "page") FWebThemePage page);

   String insert(IWebContext context,
                 @AContainer(name = "page") FWebThemePage page);

   String list(IWebContext context,
               @AContainer(name = "page") FWebThemePage page);

   String sort(IWebContext context,
               @AContainer(name = "page") FWebThemePage page);

   String update(IWebContext context,
                 @AContainer(name = "page") FWebThemePage page);

   String build(IWebContext context,
                @AContainer(name = "page") FWebThemePage page);

   String execute(IWebContext context,
                  @AContainer(name = "page") FWebThemePage page);

   String copyStyle(IWebContext context,
                    @AContainer(name = "page") FWebThemePage page);
}
