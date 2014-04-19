
<HTML>

<!-- Html Header Initialize ------------------------------->
<HEAD>
<META http-equiv='Content-Type' content='text/html; charset=UTF-8'>
<jh:css css="/css/ml.css"/>
<jh:js src="/js/lang.js"/>
<jh:js src="/js/public.js"/>
<jh:js src="/js/system.js"/>
<jh:js src="/js/lh_tree.js"/>
<TITLE>FavInfo</TITLE>
</HEAD>

<!-- Action Initialize ----------------------------------->
<SCRIPT language='javascript'>
function goActionBack(){
    frmConsole.wa = '<jh:context/>/db/table/info/Display.wa';
    frmConsole.submit();
}
</SCRIPT>

<!-- Toolbar Initialize ----------------------------------->
<jc:toolbar name='app_toolbar' action='refresh'>
    <jc:tbButton caption='Back' action='goActionBack()' icon='/res/img/sys/back.gif'/>
</jc:toolbar>

<!-- Body Initialize -------------------------------------->
<BODY bgcolor='#FFFFFF' text='#000000' leftmargin='0' topmargin='0' marginwidth='0' marginheight='0'>
<jh:form name='frmConsole' link='jfa.app.sys.web.root.db.table.TableForm'>

<TABLE width='100%' cellspacing='8' border='0'><TR><TD>
Save Ok
</TD></TR></TABLE>

</jh:form>
</BODY>
</HTML>
