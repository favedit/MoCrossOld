<%@ include file="/inc/page_begin.inc" %>
<HTML>

<!-- Html Header Initialize ------------------------------->
<HEAD>
<js:meta/>
<js:css/>
<js:js/>
<jh:title caption='FavInfo'/>
</HEAD>

<jh:define item='[Sys.ClassForm]' alias='class_form'/>

<!-- Toolbar Initialize ----------------------------------->
<jc:toolbar inFrame='frmBody' inForm='frmConsole' target='frmBody' name='top.frames.frmToolBar.oToolBar' action='refresh'>
</jc:toolbar>

<SCRIPT language='javascript'>
function nodeClick(sAttr){
   var oTreeView = top.frmCatalog.frmCatalogInner.oTreeView;
   var oNode = oTreeView.findNodeByAttr(sAttr);
   if(oNode){
      oTreeView.clickNode(oNode);
   }
}
</SCRIPT>

<!-- Body Initialize -------------------------------------->
<js:body styleClass='frmBody'>
<jh:form name='frmConsole'>
<TABLE width='100%' cellspacing='8' border='0'><TR><TD>

<jh:notEmpty item='&class_form.dataset'>
<TABLE width='100%' border='0' cellspacing='0' cellpadding='4' class='table'>
<TR bgcolor='#999999'><TD></TD></TR>
<TR class='tableHead'>
   <TD nowrap><jh:write item='txt|sys.forms|Form.Field.Name'/></TD>
</TR>
<TR bgcolor='#999999'><TD></TD></TR>
<jh:loop item='&class_form.dataset' alias='unit'>
<TR class='tableLine'>
   <TD nowrap>
      <jh:equals item='&unit.icon' value='cls.pkg'><jh:img src='/res/img/cls/pkg.gif' align='absmiddle'/></jh:equals>
      <jh:equals item='&unit.icon' value='cls.cls'><jh:img src='/res/img/cls/cls.gif' align='absmiddle'/></jh:equals>
      <a href='javascript:nodeClick("<jh:item item='&unit.attr'/>")'><jh:item item='&unit.name'/></a>
   </TD>
</TR>
<TR bgcolor='#999999'><TD></TD></TR>
</jh:loop>
</TABLE>
</jh:notEmpty>

</TD></TR></TABLE>
</jh:form>
</js:body>
</HTML>
<%@ include file="/inc/page_end.inc" %>
