<%@ include file="/inc/page_begin.inc" %>

<jh:define form='db.package' alias='package_form'/>

<HTML>
<HEAD>
<js:meta/>
<js:css/>
<js:js/>
<jh:title caption='FavInfo'/>
</HEAD>

<!-- Toolbar Initialize ----------------------------------->
<jc:toolbar inFrame='frmBody' inForm='frmConsole' target='frmBody' name='top.frames.frmToolBar.oToolBar' action='refresh'>
   <jc:tbButton caption='txt|db|toolbar.source.package.head' pageAction='/db/oracle/pkg/PackageHeader.wa' icon='sys.edit'/>
   <jc:tbButton caption='txt|db|toolbar.source.package.body' pageAction='/db/oracle/pkg/PackageBody.wa' icon='sys.edit'/>
   <jc:tbButton caption='txt|db|toolbar.source.package' pageAction='/db/oracle/pkg/Package.wa' icon='sys.edit'/>
</jc:toolbar>

<!-- Body Initialize -------------------------------------->
<js:body styleClass='frmBody'>
<jh:form name='frmConsole'>
<TABLE border='0'>
<TR>
<TD>
<jh:item item='&package_form.source_html' format='off'/>
</TD>
</TR>
</TABLE>
</jh:form>
</js:body>
</HTML>

<%@ include file="/inc/page_end.inc" %>
