<%@ include file="/inc/page/begin.inc" %>
<jh:define form='db.source' alias='source_form'/>

<HTML>

<!-- Html Header Initialize ------------------------------->
<HEAD>
<js:meta/>
<js:css/>
<js:js/>
<jh:title caption='Table'/>
</HEAD>

<!-- Toolbar Initialize ----------------------------------->
<jc:toolbar name='top.frames.frmToolbar.oToolbar' inFrame='frmWork' inForm='frmConsole' target='frmWork' action='refresh'>
   <jc:tbButton caption='trs:sys|button.execute|Execute' pageAction='/sys/db/oracle/db/src/Execute.wa' icon='sys.next'/>
</jc:toolbar>

<!-- Body Initialize -------------------------------------->
<js:body styleClass='frmWork'>
<jh:form name='frmConsole'>

<TABLE width='100%' cellspacing='8' border='0'><TR><TD>

<TABLE width='100%' border='0' cellspacing='1' cellpadding='2' bgcolor='#CCCCCC' class='table'>
<TR class='tableLine'>
    <TD nowrap width='100px'><jh:text value='trs:this|source.java|Java Source'/></TD>
    <TD nowrap>LU<jh:check source='&source_form.select_lu'/>
   </TD>
</TR>
<TR class='tableLine'>
    <TD nowrap width='100px'><jh:text value='trs:this|source.package|Package'/></TD>
    <TD nowrap>
      VIEW<jh:check source='&source_form.select_view'/>
      PKH<jh:check source='&source_form.select_pkh'/>
      PKY<jh:check source='&source_form.select_pky'/>
   </TD>
</TR>
<TR class='tableLine'>
    <TD nowrap width='100px'><jh:text value='trs:this|source.sequence|Sequence'/></TD>
    <TD nowrap>
      PKH<jh:check source='&source_form.execute_seq_pkh'/>
      PKY<jh:check source='&source_form.execute_seq_pky'/>
   </TD>
</TR>
<TR class='tableLine'>
    <TD nowrap width='100px'><jh:text value='trs:this|source.dataset|Dataset'/></TD>
    <TD nowrap>
      Dataset<jh:check source='&source_form.execute_dataset'/>
   </TD>
</TR>
</TABLE><BR>

</jh:form>
</js:body>
</HTML>
<%@ include file="/inc/page/end.inc" %>
