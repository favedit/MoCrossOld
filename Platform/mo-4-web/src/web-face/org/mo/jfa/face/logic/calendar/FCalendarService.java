package org.mo.jfa.face.logic.calendar;

import org.mo.logic.session.ISqlSessionContext;
import org.mo.web.protocol.context.IWebContext;
import org.mo.web.protocol.context.IWebInput;
import org.mo.web.protocol.context.IWebOutput;

public class FCalendarService
      implements
         ICalendarService
{
   @SuppressWarnings("unused")
   private final static String PTY_CALENDAR_ID = "calendar_id";

   @SuppressWarnings("unused")
   private final static String PTY_MONTH = "month";

   @SuppressWarnings("unused")
   private final static String PTY_USER_ID = "user_id";

   @SuppressWarnings("unused")
   private final static String PTY_YEAR = "year";

   /* (non-Javadoc)
    * @see org.mo.jfa.face.logic.webform.IWebDatasetService#fetch(org.mo.web.protocol.context.IWebContext, org.mo.eng.data.common.ISqlContext, org.mo.web.protocol.context.IWebInput, org.mo.web.protocol.context.IWebOutput)
    */
   @Override
   public void fetch(IWebContext context,
                     ISqlSessionContext sqlContext,
                     IWebInput input,
                     IWebOutput output){
      //      // 获取输入参数
      //      FXmlNode calendarNode = input.config().findNode("Calendar");
      //      IAttributes params = new FAttributes();
      //      params.set(PTY_USER_ID, context.session().user().userId());
      //      params.set(PTY_CALENDAR_ID, calendarNode.get(PTY_CALENDAR_ID));
      //      params.set(PTY_YEAR, calendarNode.get(PTY_YEAR));
      //      params.set(PTY_MONTH, calendarNode.get(PTY_MONTH));
      //      // 查询数据库
      //      FSqlProcedure procedure = pmCalendarDi.fetchUserCalendar(null, params, null, null, null);
      //      IAttributes calendar = procedure.parameter("calendar_pack_").asAttributes();
      //      FStrings eventTypes = procedure.parameter("event_type_pack_").asStrings();
      //      FStrings events = procedure.parameter("events_pack_").asStrings();
      //      // 建立输出
      //      FXmlNode xCalendar = output.config().createNode("Calendar");
      //      // 建立日历信息
      //      xCalendar.attributes().append(calendar);
      //      // 建立事件类型信息
      //      if(!eventTypes.isEmpty()){
      //         FXmlNode xTypes = xCalendar.createNode("Type");
      //         for(String eventTypeValue : eventTypes){
      //            xTypes.createNode("Type").attributes().unpack(eventTypeValue);
      //         }
      //      }
      //      // 建立事件信息
      //      if(!events.isEmpty()){
      //         FXmlNode xEvents = xCalendar.createNode("Events");
      //         for(String eventValue : events){
      //            xEvents.createNode("Event").attributes().unpack(eventValue);
      //         }
      //      }
   }
}
