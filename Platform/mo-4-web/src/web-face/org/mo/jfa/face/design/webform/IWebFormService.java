package org.mo.jfa.face.design.webform;

import org.mo.eng.data.common.ISqlContext;
import org.mo.web.protocol.context.IWebContext;
import org.mo.web.protocol.context.IWebInput;
import org.mo.web.protocol.context.IWebOutput;

public interface IWebFormService
{

   void assign(IWebContext context,
               IWebInput input,
               IWebOutput output);

   void catalog(IWebContext context,
                IWebInput input,
                IWebOutput output);

   void delete(IWebContext context,
               IWebInput input,
               IWebOutput output);

   void design(IWebContext context,
               IWebInput input,
               IWebOutput output);

   void fetchLov(IWebContext context,
                 ISqlContext sqlContext,
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
