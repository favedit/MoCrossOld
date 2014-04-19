package org.mo.game.editor.face.editor.dataset;

import org.mo.web.protocol.context.IWebContext;
import org.mo.web.protocol.context.IWebInput;
import org.mo.web.protocol.context.IWebOutput;

public interface IDatasetService{

   void catalog(IWebContext context,
                IWebInput input,
                IWebOutput output);

   void delete(IWebContext context,
               IWebInput input,
               IWebOutput output);

   void insert(IWebContext context,
               IWebInput input,
               IWebOutput output);

   void list(IWebContext context,
             IWebInput input,
             IWebOutput output);

   void search(IWebContext context,
               IWebInput input,
               IWebOutput output);

   void sort(IWebContext context,
             IWebInput input,
             IWebOutput output);

   void update(IWebContext context,
               IWebInput input,
               IWebOutput output);

}
