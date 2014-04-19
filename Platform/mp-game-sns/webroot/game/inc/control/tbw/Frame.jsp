<%@ include file="/inc/page_begin.inc" %>

<jh:define item='[Control.TableWindowForm]|{&request.source}' alias='table_window_form'/>
<jh:define item='&table_window_form.window_node' alias='window_node'/>

<HTML>
<HEAD>
<js:meta/>
<js:css/>
<js:js/>
<jh:title caption='FavInfo'/>
</HEAD>
<jh:isTrue item='&window_node.can_search'>
<FRAMESET id='fstSearch' rows='60px,*' frameborder='yes' border='0' framespacing='1' bordercolor='#1E56C0'>
</jh:isTrue>
<jh:isFalse item='&window_node.can_search'>
<FRAMESET id='fstSearch' rows='0px,*' frameborder='yes' border='0' framespacing='1' bordercolor='#1E56C0'>
</jh:isFalse>
   <jh:frame name="frmSearch" scrolling="no" frameborder="no" noresize="yes" src="/inc/ctl/tbw/Search.wa?source={&request.source}"/>
   <jh:frame name="frmList" scrolling="auto" frameborder="no" src="/inc/ctl/tbw/List.wa?source={&request.source}"/>
</FRAMESET>
<NOFRAMES>
<js:body/>
</NOFRAMES>
</HTML>
<%@ include file="/inc/page_end.inc" %>
