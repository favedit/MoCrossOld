<%@ include file='/apl/public.inc' %>
<jh:define form='sys.control' alias='control_form'/>
<jh:define item='&control_form.window_list' alias='window_list'/>

<HTML>
<!-- Html Header Initialize ------------------------------->
<HEAD>
<js:meta/>
<js:css/>
<js:js/>
<jh:title caption='FavInfo'/>
</HEAD>

<jc:toolbar name='top.frames.frmToolbar.oToolbar' inFrame='frmWork' inForm='frmConsole' target='frmWork' action='refresh'>
   <jc:tbButton caption='trs:sys|button.node.insert|Node Insert' pageAction='/system/window/WinInsert.wa' icon='sys.tvni'/>
   <jc:tbButton caption='trs:sys|button.node.delete|Node Delete' pageAction='/system/window/WinDelete.wa' icon='sys.tvnd'/>
</jc:toolbar>

<!-- Body Initialize -------------------------------------->
<js:body styleClass='frmWork' scroll='auto'>
<js:form name='frmConsole' dataForm='top.frmToolbar.frmConsole'>

<!-- Page Control Initialize ------------------------------>
<TABLE width='100%' border='0' cellspacing='0' cellpadding='8'>
<TR><TD>

<jh:hasChild item='&window_list'>
<TABLE width='100%' border='0' cellspacing='1' cellpadding='2' class='table'>
<TR class='tableHead'>
   <TD nowrap width='160px'><jh:text value='trs:this|window.name|Name'/></TD>
   <TD nowrap><jh:text value='trs:this|window.name|Type'/></TD>
</TR>
<jh:loop item='&window_list' alias='window'>
<TR class='tableLine'>
   <TD nowrap>
     <jh:img src='/res/img/sys/ctl/ctl.gif' align='absmiddle'/> <jh:item item='&window.name'/>
   </TD>
   <TD nowrap><jh:item item='&window.label'/></TD>
</TR>
</jh:loop>
</TABLE>
</jh:hasChild>

</TD></TR>
</TABLE>

</js:form>
</js:body>
</HTML>

