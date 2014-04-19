package org.mo.jfa.face.monitor.thread;

import org.mo.web.protocol.context.IWebContext;
import org.mo.web.protocol.context.IWebInput;
import org.mo.web.protocol.context.IWebOutput;

public interface IThreadService
{

   void catalog(IWebContext context,
                IWebInput input,
                IWebOutput output);

   void insertComponent(IWebContext context,
                        IWebInput input,
                        IWebOutput output);

   void insertToolBar(IWebContext context,
                      IWebInput input,
                      IWebOutput output);

   void listComponent(IWebContext context,
                      IWebInput input,
                      IWebOutput output);

   void saveComponent(IWebContext context,
                      IWebInput input,
                      IWebOutput output);

   void saveControlOrder(IWebContext context,
                         IWebInput input,
                         IWebOutput output);

   void sort(IWebContext context,
             IWebInput input,
             IWebOutput output);

   void saveToolBar(IWebContext context,
                    IWebInput input,
                    IWebOutput output);
}
