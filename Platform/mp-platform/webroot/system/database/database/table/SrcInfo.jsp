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
   <jc:tbButton caption='trs:this|toolbar.source.build|Build All' pageAction='/system/database/table/SrcBuildAll.wa' icon='sys.cls'/>
   <jc:tbButton caption='|'/>
   <jc:tbButton caption='trs:this|toolbar.package.di|Data Iterface' pageAction='/system/database/table/SrcDataInterface.wa' icon='sys.cls'/>
   <jc:tbButton caption='|'/>
   <jc:tbButton caption='trs:this|toolbar.package.head|Package Head' pageAction='/system/database/table/SrcPkgHead.wa' icon='sys.cls'/>
   <jc:tbButton caption='trs:this|toolbar.package.body|Package Body' pageAction='/system/database/table/SrcPkgBody.wa' icon='sys.cls'/>
   <jc:tbButton caption='trs:this|toolbar.view|View' pageAction='/system/database/table/SrcView.wa' icon='sys.cls'/>
</jc:toolbar>

<!-- Body Initialize -------------------------------------->
<js:body styleClass='frmWork'>
<js:form name='frmConsole' dataForm='top.frmToolbar.frmConsole'>
<TABLE width='100%' cellspacing='8' border='0'><TR><TD>
Source
</TD></TR></TABLE>
</js:form>
</js:body>
</HTML>
<%@ include file="/inc/page/end.inc" %>
