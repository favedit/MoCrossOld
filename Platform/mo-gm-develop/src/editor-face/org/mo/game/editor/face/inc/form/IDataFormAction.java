package org.mo.game.editor.face.inc.form;

import org.mo.web.core.container.AContainer;
import org.mo.web.core.container.EContainerScope;

import org.mo.jfa.face.inc.form.FFormPage;
import org.mo.web.protocol.context.IWebContext;

public interface IDataFormAction{

   String back(IWebContext context,
               @AContainer(name = "webform", scope = EContainerScope.Session)
               FFormPage page);

   String delete(IWebContext context,
                 @AContainer(name = "webform", scope = EContainerScope.Session)
                 FFormPage page);

   String deleteSave(IWebContext context,
                     @AContainer(name = "webform", scope = EContainerScope.Session)
                     FFormPage page);

   String insert(IWebContext context,
                 @AContainer(name = "webform", scope = EContainerScope.Session)
                 FFormPage page);

   String insertSave(IWebContext context,
                     @AContainer(name = "webform", scope = EContainerScope.Session)
                     FFormPage page);

   String search(IWebContext context,
                 @AContainer(name = "webform", scope = EContainerScope.Session)
                 FFormPage page);

   String show(IWebContext context,
               @AContainer(name = "webform", scope = EContainerScope.Session)
               FFormPage page);

   String update(IWebContext context,
                 @AContainer(name = "webform", scope = EContainerScope.Session)
                 FFormPage page);

   String updateSave(IWebContext context,
                     @AContainer(name = "webform", scope = EContainerScope.Session)
                     FFormPage page);
}
