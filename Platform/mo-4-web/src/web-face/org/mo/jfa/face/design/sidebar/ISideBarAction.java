package org.mo.jfa.face.design.sidebar;

import org.mo.web.core.container.AContainer;

import org.mo.jfa.face.inc.form.IFormAble;
import org.mo.web.protocol.context.IWebContext;

public interface ISideBarAction
      extends
         IFormAble
{

   String catalog(IWebContext context,
                  @AContainer(name = "page") FSideBarPage page);

   String delete(IWebContext context,
                 @AContainer(name = "page") FSideBarPage page);

   String insert(IWebContext context,
                 @AContainer(name = "page") FSideBarPage page);

   String list(IWebContext context,
               @AContainer(name = "page") FSideBarPage page);

   String sort(IWebContext context,
               @AContainer(name = "page") FSideBarPage page);

   String update(IWebContext context,
                 @AContainer(name = "page") FSideBarPage page);

}
