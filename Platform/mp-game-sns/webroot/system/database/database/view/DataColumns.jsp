
<HTML>

<!-- Html Header Initialize ------------------------------->
<HEAD>
<js:meta/>
<js:css/>
<js:js/>
<jh:title caption='FavInfo'/>
</HEAD>

<!-- Javascript Initialize ----------------------------------->
<SCRIPT language='javascript'>
function goActionExecute(){
   frmConsole.wa = "<jh:context/>/db/oracle/tab/DataEdit.wa";
   frmConsole.target = "frmDataEdit";
   frmConsole.submit();
}
function goActionInsert(){
   var oTable = top.frmBody.frames.frmDataEdit.oTable;
   oTable.insertUnit();
}
function goActionCopy(){
   var oTable = top.frmBody.frames.frmDataEdit.oTable;
   oTable.copyUnit();
}
function goActionDelete(){
   var oTable = top.frmBody.frames.frmDataEdit.oTable;
   oTable.deleteUnit();
}
function goActionUpdate(){
   var oForm = top.frmBody.frames.frmDataEdit.frmConsole;
   var oTable = top.frmBody.frames.frmDataEdit.oTable;
   if(oTable && oForm){
      oTable.clearFoucs(true);
      oForm.wa = "<jh:context/>/db/oracle/tab/DataEditExecute.wa";
      oForm.target = "frm_frmDataEdit";
      oForm.<jh:name link='jfa.app.sys.web.root.db.com.form.TableForm' source='table_pack'/>.value = oTable.pack();
      oForm.submit();
   }
}
function showColumns(oSelf){
   var oTD = oSelf.parentElement;
   var oTR = oTD.parentElement;
   var oTable = oTR.parentElement;
   var oUnitInex = oTR.rowIndex;
   var oCellIndex = oTD.cellIndex;
   var nUnitCount = oTable.rows.length;
   var oCheckBox = null;
   for(var n=oUnitInex+1; n<nUnitCount; n++){
      oCheckBox = oTable.rows[n].cells[0].children[1];
      oCheckBox.checked = oSelf.checked;
      showColumn(oCheckBox);
   }
}
function showColumn(oSelf){
   var oTD = oSelf.parentElement;
   var sColumnName = FString.trim(oTD.nextSibling.innerText);
   var oTable = top.frmBody.frames.frmDataEdit.oTable;
   if(oTable){
      oTable.showColumn(sColumnName, oSelf.checked);
   }
}
</SCRIPT>

<!-- Toolbar Initialize ----------------------------------->
<jc:toolbar inFrame='frmBody' inForm='frmConsole' target='frmBody' name='top.frames.frmToolBar.oToolBar' action='refresh'>
    <jc:tbButton caption='db.res|Oracle.Toolbar.Table.Execute|Execute' action='frmBody.frames.frmDataColumns.goActionExecute()' icon='/res/img/db/query.gif'/>
    <jc:tbButton caption='|'/>
    <jc:tbButton action='frmBody.frames.frmDataColumns.goActionInsert()' icon='/res/img/db/add.gif'/>
    <jc:tbButton action='frmBody.frames.frmDataColumns.goActionCopy()' icon='/res/img/db/add2.gif'/>
    <jc:tbButton action='frmBody.frames.frmDataColumns.goActionDelete()' icon='/res/img/db/del.gif'/>
    <jc:tbButton action='frmBody.frames.frmDataColumns.goActionUpdate()' icon='/res/img/db/post.gif'/>
    <jc:tbButton caption='|'/>
    <jc:tbButton action='frmBody.frames.frmDataColumns.goActionFirst()' icon='/res/img/db/first.gif'/>
    <jc:tbButton action='frmBody.frames.frmDataColumns.goActionPrev()' icon='/res/img/db/prev.gif'/>
    <jc:tbButton action='frmBody.frames.frmDataColumns.goActionNext()' icon='/res/img/db/next.gif'/>
    <jc:tbButton action='frmBody.frames.frmDataColumns.goActionLast()' icon='/res/img/db/last.gif'/>
</jc:toolbar>

<!-- Body Initialize -------------------------------------->
<BODY bgcolor='#FFFFFF' text='#000000' leftmargin='0' topmargin='0' marginwidth='0' marginheight='0' onload='loadDataStatus()'>
<jh:form name='frmConsole' link='jfa.app.sys.web.root.db.com.form.TableForm'>

<TABLE width='100%' border='0' cellspacing='1' cellpadding='2'><TR><TD>

<jh:container source='data_sql'>
Page Position: <jh:edit source='page_position' size='4' value='1'/>
Page Size: <jh:edit source='page_size' size='4' value='20'/>
Total Count: <SPAN id='id_total_count'></SPAN><jh:edit type='hidden' source='total_count' size='4' value='0'/>
Page Count: <SPAN id='id_page_count'></SPAN><jh:edit type='hidden' source='page_count' size='4' value='0'/>
<TABLE width='100%' border='0' cellspacing='0' cellpadding='0' frame='border'>
<TR bgcolor='#FFFFFF' height='22px'>
   <TD nowrap width='60px'>WHERE: </TD><TD><jh:edit source='sql_where' size='80'/></TD>
</TR>
<TR bgcolor='#FFFFFF' height='22px'>
   <TD nowrap width='60px'>ORDER BY:</TD><TD><jh:edit source='sql_order_by' size='80'/></TD>
</TR>
</TABLE>
<SCRIPT language='javascript'>
function loadDataStatus(){
   var oFrame = top.frmBody.frames.frmDataEdit;
   if(oFrame){
      if(oFrame.frmConsole && oFrame.loadDataStatus){
         oFrame.loadDataStatus();
      }
   }
}
function goActionFirst(){
   var oPagePosition = frmConsole.<jh:name source="page_position"/>;
   if(oPagePosition.value != 1){
      oPagePosition.value = 1;
      frmConsole.wa = "<jh:context/>/db/oracle/tab/DataEdit.wa";
      frmConsole.target = "frmDataEdit";
      frmConsole.submit();
   }
}
function goActionPrev(){
   var oPagePosition = frmConsole.<jh:name source="page_position"/>;
   if(oPagePosition.value > 1){
      oPagePosition.value = FString.toInteger(oPagePosition.value)-1;
      frmConsole.wa = "<jh:context/>/db/oracle/tab/DataEdit.wa";
      frmConsole.target = "frmDataEdit";
      frmConsole.submit();
   }
}
function goActionNext(){
   var oPagePosition = frmConsole.<jh:name source="page_position"/>;
   var nPageCount = FString.toInteger(frmConsole.<jh:name source="page_count"/>.value);
   if(oPagePosition.value < nPageCount){
      oPagePosition.value = FString.toInteger(oPagePosition.value)+1;
      frmConsole.wa = "<jh:context/>/db/oracle/tab/DataEdit.wa";
      frmConsole.target = "frmDataEdit";
      frmConsole.submit();
   }
}
function goActionLast(){
   var oPagePosition = frmConsole.<jh:name source="page_position"/>;
   var nPageCount = FString.toInteger(frmConsole.<jh:name source="page_count"/>.value);
   if(oPagePosition.value != nPageCount){
      oPagePosition.value = nPageCount;
      frmConsole.wa = "<jh:context/>/db/oracle/tab/DataEdit.wa";
      frmConsole.target = "frmDataEdit";
      frmConsole.submit();
   }
}
</SCRIPT>
</jh:container>

<jh:container source='column_dataset' position='first'>
<TABLE border='0' cellspacing='1' cellpadding='1' bgcolor='#CCCCCC'>
<TR bgcolor='#DDDDDD'>
    <TD nowrap width='20px' align='center'><INPUT type='checkbox' class='comCheckBox' onclick='showColumns(this)' checked></TD>
    <TD nowrap width='80px'><jh:write source='txt|db' source='Oracle.Table.Column.Name' value='Column Name'/></TD>
    <TD nowrap width='30px' align='center'><jh:write source='txt|db' source='Oracle.Table.Column.IsKey' value='K'/></TD>
    <TD nowrap width='30px' align='center'><jh:write source='txt|db' source='Oracle.Table.Column.IsNull' value='N'/></TD>
    <TD nowrap width='80px'><jh:write source='txt|db' source='Oracle.Table.Column.DisplayLabel' value='Display Label'/></TD>
    <TD nowrap width='60px' align='right'><jh:write source='txt|db' source='Oracle.Table.Column.MinValue' value='Min Value'/></TD>
    <TD nowrap width='60px' align='right'><jh:write source='txt|db' source='Oracle.Table.Column.MaxValue' value='Max Value'/></TD>
</TR>
<jh:container link='#source' position='next'>
<TR bgcolor='#FFFFFF'>
   <TD align='center'><jh:check source='visible' trueValue='Y' falseValue='N' onclick='showColumn(this)'/></TD>
   <TD nowrap><jh:img src='/res/img/db/tab.gif' align='absmiddle'/> <jh:source source='column_name'/></TD>
   <TD align='center'><jh:check displayOnly='Y' trueValue='Y' falseValue='N' source='is_key'/></TD>
   <TD align='center'><jh:check displayOnly='Y' trueValue='Y' falseValue='N' source='is_null'/></TD>
   <TD nowrap><jh:source source='column_name'/></TD>
   <TD nowrap><jh:edit source='min_value' size='20'/></TD>
   <TD nowrap><jh:edit source='max_value' size='20'/></TD>
</TR>
</jh:container>
</TABLE>
</jh:container>

</TD></TR></TABLE>

</jh:form>
</BODY>
</HTML>
