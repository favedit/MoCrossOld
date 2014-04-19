
<HTML>

<!-- Html Header Initialize ------------------------------->
<HEAD>
<js:meta/>
<js:css/>
<js:js/>
<jh:title caption='FavInfo'/>
</HEAD>

<!-- Body Initialize -------------------------------------->
<BODY bgcolor='#FFFFFF' text='#000000' leftmargin='0' topmargin='0' marginwidth='0' marginheight='0' onclick='oTable.clearFoucs();' onload="loadDataStatus()">
<jh:form name='frmConsole' link='jfa.app.sys.web.root.db.com.form.TableForm'>
<IFRAME width='100%' height='80px' name='frm_frmDataEdit' style='display:none;' src='#'></IFRAME>

<TABLE width='100%' border='0' cellspacing='0' cellpadding='0'>
<TR><TD>

<jh:edit type='hidden' item='table_pack'/>
<jh:container item='data_set'>
   <jh:edit type='hidden' property="totalCount"/>
   <jh:edit type='hidden' property="pageCount"/>
   <jh:edit type='hidden' property="pageSize"/>
   <jh:edit type='hidden' property="pagePosition"/>
<SCRIPT language='javascript'>
function loadDataStatus(){
   var nTotalCount = frmConsole.<jh:name property="totalCount"/>.value;
   var nPageCount = frmConsole.<jh:name property="pageCount"/>.value;
   var nPageSize = frmConsole.<jh:name property="pageSize"/>.value;
   var nPagePosition = frmConsole.<jh:name property="pagePosition"/>.value;
   changeStatus(nTotalCount, nPageCount, nPageSize, nPagePosition);
}
</SCRIPT>
</jh:container>
<jh:container item='data_sql'>
<SCRIPT language='javascript'>
function changeStatus(nTotalCount, nPageCount, nPageSize, nPagePosition){
   var oFrame = top.frmBody.frames.frmDataColumns;
   if(oFrame.frmConsole){
      var oForm = oFrame.frmConsole;
      if(oForm){
         top.frmBody.frames.frmDataColumns.id_total_count.innerText = nTotalCount;
         top.frmBody.frames.frmDataColumns.id_page_count.innerText = nPageCount;
         oForm.<jh:name item="total_count"/>.value = nTotalCount;
         oForm.<jh:name item="page_count"/>.value = nPageCount;
         oForm.<jh:name item="page_size"/>.value = nPageSize;
         oForm.<jh:name item="page_position"/>.value = nPagePosition;
      }
   }
}
</SCRIPT>
</jh:container>

<jc:dbTable name='oTable' item='data_set'/>

</TD></TR>
</TABLE>

</jh:form>
</BODY>
</HTML>
