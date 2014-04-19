package org.mo.logic.event.handler;

import org.mo.eng.template.ITemplateParser;
import org.mo.logic.event.ILogicEvent;

public interface IMailEventBuilder
{
   void buildBody(ILogicEvent event,
                  ITemplateParser parser);

   void buildHead(ILogicEvent event,
                  ITemplateParser parser);
}
