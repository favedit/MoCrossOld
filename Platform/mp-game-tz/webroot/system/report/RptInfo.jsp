<%@ include file="/inc/page/begin.inc" %>
<jh:define form='sys.ctl.uml' alias='uml_form'/>
<jh:define item='&uml_form.class_list' alias='class_list'/>
<jh:define item='&uml_form.type_list' alias='type_list'/>
<jh:define item='&uml_form.association_list' alias='association_list'/>

<HTML>

<!-- Html Header Initialize ------------------------------->
<HEAD>
<js:meta/>
<js:css/>
<js:js/>
<jh:title caption='FavInfo'/>
</HEAD>

<!-- Toolbar Initialize ----------------------------------->
<jc:toolbar name='top.frames.frmToolbar.oToolbar' inFrame='frmWork' inForm='frmConsole' target='frmWork' action='refresh'/>

<!-- Body Initialize -------------------------------------->
<js:body styleClass='frmWork'>
<js:form name='frmConsole'>
<TABLE width='100%' cellspacing='8' border='0'><TR><TD>

<jh:hasChild item='&class_list'>
<B>CLASS</B><BR>
<TABLE width='100%' border='0' cellspacing='1' cellpadding='2' class='table'>
<TR class='tableHead'>
   <TD nowrap width='200px'><jh:text value='trs:this|class.label|Label'/></TD>
   <TD nowrap><jh:text value='trs:this|class.path|Path'/></TD>
</TR>
<jh:loop item='&class_list' alias='node'>
<TR class='tableLine'>
   <TD nowrap><jh:item item='&node.label'/></TD>
   <TD nowrap><jh:item item='&node.path'/></TD>
</TR>
</jh:loop>
</TABLE>
</jh:hasChild><BR>

<jh:hasChild item='&type_list'>
<B>TYPE</B><BR>
<TABLE width='100%' border='0' cellspacing='1' cellpadding='2' class='table'>
<TR class='tableHead'>
   <TD nowrap width='200px'><jh:text value='trs:this|class.label|Label'/></TD>
   <TD nowrap><jh:text value='trs:this|class.path|Path'/></TD>
</TR>
<jh:loop item='&type_list' alias='node'>
<TR class='tableLine'>
   <TD nowrap><jh:item item='&node.label'/></TD>
   <TD nowrap><jh:item item='&node.path'/></TD>
</TR>
</jh:loop>
</TABLE>
</jh:hasChild><BR>

<jh:hasChild item='&association_list'>
<B>ASSOCIATION</B><BR>
<TABLE width='100%' border='0' cellspacing='1' cellpadding='2' class='table'>
<TR class='tableHead'>
   <TD nowrap width='200px'><jh:text value='trs:this|class.name|Name'/></TD>
   <TD nowrap><jh:text value='trs:this|class.path|Path'/></TD>
</TR>
<jh:loop item='&Association_list' alias='node'>
<TR class='tableLine'>
   <TD nowrap><jh:item item='&node.name'/></TD>
   <TD nowrap><jh:item item='&node.path'/></TD>
</TR>
</jh:loop>
</TABLE>
</jh:hasChild>

</TD></TR></TABLE>
</js:form>
</js:body>
</HTML>
<%@ include file="/inc/page/end.inc" %>
