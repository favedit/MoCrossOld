package org.mo.jfa.face.system.environment;

import org.mo.core.aop.face.ALink;
import org.mo.eng.environment.IEnvironmentConsole;
import org.mo.eng.environment.common.XEnvironment;
import org.mo.jfa.common.xmlobjects.FAbsXmlObjectService;
import org.mo.web.protocol.context.IWebContext;
import org.mo.web.protocol.context.IWebInput;
import org.mo.web.protocol.context.IWebOutput;

public class FEnvironmentService
      extends FAbsXmlObjectService<XEnvironment>
      implements
         IEnvironmentService
{

   @ALink
   protected IEnvironmentConsole _envConsole;

   public void catalog(IWebContext context,
                       IWebInput input,
                       IWebOutput output){
      catalog(_envConsole, context, input, output);
   }

   @Override
   public void delete(IWebContext context,
                      IWebInput input,
                      IWebOutput output){
      delete(_envConsole, context, input, output);
   }

   @Override
   public void insert(IWebContext context,
                      IWebInput input,
                      IWebOutput output){
      insert(_envConsole, context, input, output);
   }

   @Override
   public void list(IWebContext context,
                    IWebInput input,
                    IWebOutput output){
      list(_envConsole, context, input, output);
      output.config().sort(PTY_LABEL);
   }

   @Override
   public void sort(IWebContext context,
                    IWebInput input,
                    IWebOutput output){
      sort(_envConsole, context, input, output);
   }

   @Override
   public void update(IWebContext context,
                      IWebInput input,
                      IWebOutput output){
      update(_envConsole, context, input, output);
   }
}
