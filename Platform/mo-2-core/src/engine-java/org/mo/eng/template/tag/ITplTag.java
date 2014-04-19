package org.mo.eng.template.tag;

import org.mo.com.lang.IDump;
import org.mo.eng.template.FTemplateContext;

public interface ITplTag
      extends
         IDump
{
   int CONTINUE = 0;

   int INCLUDE = 0;

   int STOP = 1;

   ITplTag child(int index);

   void construct(FTemplateContext context);

   int count();

   boolean nowrapLeft();

   boolean nowrapRight();

   int onEnd();

   int onLoop();

   /**
    * 进入标签时要执行的逻辑
    * 
    * @return 执行结果
    */
   int onStart();

   void push(ITplTag tag);
}
