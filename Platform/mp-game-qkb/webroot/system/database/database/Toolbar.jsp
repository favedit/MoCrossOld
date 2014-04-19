<%@ include file="/inc/page_begin.inc" %>

<jh:define item='[Sys.ToolbarForm]' alias='toolbar_form'/>

<HTML>
<!-- Html Header Initialize ------------------------------->
<HEAD>
<js:meta/>
<js:css/>
<js:js/>
<jh:js src='[Js.LH.Com]'/>
<jh:title caption='FavInfo'/>
</HEAD>

<!-- Body Initialize -------------------------------------->
<js:body styleClass='frmToolbar'>
<jh:form name='frmConsole'>

<!-- Page Control Initialize ------------------------------>
<TABLE width='100%' border='0' cellspacing='0' cellpadding='0' class='comPageCtlTab'>
<TR><TD height='2'></TD></TR>
<TR background='/res/img/mark/h1.gif'><TD>
<jc:pageControl name='oPageCtl' target='[html.frame.body]' action='init' pageIndexForm='[html.form.catalog.inner]' pageIndex='&toolbar_form.page_index'>

   <jh:empty item='&toolbar_form.tv_node_type'>
      <jc:tabSheet caption='txt|sys|PageControl.Information' action='/sys/Info.wa' icon='sys.info'/>
   </jh:empty>

   <jh:equals item='&toolbar_form.tv_node_type' value='NODE_CLASS_ROOT|NODE_CLASS_PACKAGE@[Sys.TreeViewConstants]'>
      <jc:tabSheet name='Information' caption='txt|sys|PageControl.Information' action='/sys/cls/List.wa' icon='sys.form.form'/>
   </jh:equals>

   <jh:equals item='&toolbar_form.tv_node_type' value='NODE_CLASS@[Sys.TreeViewConstants]'>
      <jc:tabSheet name='Information' caption='txt|sys|PageControl.Information' action='/sys/cls/Info.wa' icon='sys.form.form'/>
      <jc:tabSheet name='Source' caption='txt|sys|PageControl.Source' action='/sys/cls/Source.wa' icon='sys.form.form'/>
      <jc:tabSheet name='Help' caption='txt|sys|PageControl.Help' action='/sys/cls/Help.wa' icon='sys.form.form'/>
   </jh:equals>

   <jh:equals item='&toolbar_form.tv_node_type' value='NODE_REPORT@[Sys.TreeViewConstants]'>
      <jc:tabSheet caption='txt|sys|PageControl.Information' action='/sys/Info.wa' icon='sys.info'/>
   </jh:equals>

   <jh:equals item='&toolbar_form.tv_node_type' value='NODE_REPORT_MEMORY@[Sys.TreeViewConstants]'>
      <jc:tabSheet name='Information' caption='txt|sys|PageControl.Information' action='/sys/rpt/Memory.wa' icon='sys.edit'/>
   </jh:equals>

   <jh:equals item='&toolbar_form.tv_node_type' value='NODE_REPORT_DATABASE@[Sys.TreeViewConstants]'>
      <jc:tabSheet name='Information' caption='txt|sys|PageControl.Information' action='/sys/rpt/Database.wa' icon='sys.edit'/>
   </jh:equals>

   <jh:equals item='&toolbar_form.tv_node_type' value='NODE_REPORT_PAGE@[Sys.TreeViewConstants]'>
      <jc:tabSheet name='Information' caption='txt|sys|PageControl.Information' action='/sys/rpt/Page.wa' icon='sys.edit'/>
   </jh:equals>

   <jh:equals item='&toolbar_form.tv_node_type' value='NODE_REPORT_NAMING@[Sys.TreeViewConstants]'>
      <jc:tabSheet name='Information' caption='txt|sys|PageControl.Information' action='/sys/rpt/Naming.wa' icon='sys.edit'/>
   </jh:equals>

</jc:pageControl>
</TD></TR>
</TABLE>

<!-- Toolbar Initialize ----------------------------------->
<TABLE width='100%' bgcolor='#1E56C0' border='0' cellspacing='0' cellpadding='0' height='1'><TR><TD></TD></TR></TABLE>
<TABLE border='0' height='22px' width='100%' cellpadding='0' cellspacing='0' bgcolor='#EFF3FF'>
   <TR><TD><jc:toolbar name='oToolBar' action='init'/></TD></TR>
</TABLE>
<TABLE width='100%' bgcolor='#1E56C0' border='0' cellspacing='0' cellpadding='0' height='1'><TR><TD></TD></TR></TABLE>
</jh:form>
</js:body>
</HTML>
<%@ include file="/inc/page_end.inc" %>
