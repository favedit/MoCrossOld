package org.mo.game.editor.face.editor.storage;

import org.mo.core.aop.face.ALink;
import org.mo.game.editor.core.storage.IStorageConsole;
import org.mo.game.editor.core.storage.common.XGroup;
import org.mo.jfa.common.xmlobjects.FAbsXmlObjectService;
import org.mo.web.protocol.context.IWebContext;
import org.mo.web.protocol.context.IWebInput;
import org.mo.web.protocol.context.IWebOutput;


public class FStorageService
      extends FAbsXmlObjectService<XGroup>
      implements
         IStorageService{

   @ALink
   protected IStorageConsole _storageConsole;

   /* (non-Javadoc)
    * @see org.mo.jfa.face.design.webtools.IWebToolsService#catalog(org.mo.web.protocol.context.IWebContext, org.mo.web.protocol.context.IWebInput, org.mo.web.protocol.context.IWebOutput)
    */
   public void catalog(IWebContext context,
                       IWebInput input,
                       IWebOutput output){
      catalog(_storageConsole, context, input, output);
      // 设置排序
      //orderNode(output);
   }

   @Override
   public void delete(IWebContext context,
                      IWebInput input,
                      IWebOutput output){
      delete(_storageConsole, context, input, output);
   }

   @Override
   public void insert(IWebContext context,
                      IWebInput input,
                      IWebOutput output){
      insert(_storageConsole, context, input, output);
   }

   @Override
   public void list(IWebContext context,
                    IWebInput input,
                    IWebOutput output){
      list(_storageConsole, context, input, output);
   }

   @Override
   public void sort(IWebContext context,
                    IWebInput input,
                    IWebOutput output){
      sort(_storageConsole, context, input, output);
   }

   @Override
   public void update(IWebContext context,
                      IWebInput input,
                      IWebOutput output){
      update(_storageConsole, context, input, output);
   }
}
