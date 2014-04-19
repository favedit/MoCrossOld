<%@ include file="/inc/page/begin.inc" %>
<jh:define form='reference' alias='reference_form'/>
<jh:define item='&reference_form.define' alias='define'/>
<jh:define item='&reference_form.dataset' alias='dataset'/>

<HTML>

<!-- Html Header Initialize ------------------------------->
<HEAD>
<js:meta/>
<js:css/>
<js:js top='Y'/>
<jh:title caption='Table'/>
</HEAD>

<SCRIPT language='javascript'>
function FDataRefManager(){
   this.className = 'FDataRefManager';
   this.classDataRefManager = this;
   // Attribute
   this.position = 0;
   this.count = 0;
   this.selectRow = null;
   this.isFocus = false;
   // Event
   this.onRowOver = mgr_ref_onRowOver;
   this.onRowOut = mgr_ref_onRowOut;
   this.onTableFocus = mgr_ref_onTableFocus;
   this.onTableBlur = mgr_ref_onTableBlur;
   this.onEventInitialize = mgr_ref_onEventInitialize;
   this.onEventKeydown = mgr_ref_onEventKeydown;
   this.onEventMousedown = mgr_ref_onEventMousedown;
   // Method
   this.pack = mgr_ref_pack;
   this.close = mgr_ref_close;
   this.move = mgr_ref_move;
   this.movePrior = mgr_ref_movePrior;
   this.moveNext = mgr_ref_moveNext;
   return this;
}
IEngine.register('DataRefManager', 'FDataRefManager');
// ------------------------------------------------------------
function mgr_ref_pack(oRow){
   if(oRow){
      var oParams = new FList();
      var sName = null;
      var sValue = null;
      var arFields = id_fields;
      for(var n=0; n<arFields.cells.length; n++){
         oParams.add(arFields.cells[n].innerText, oRow.cells[n].innerText);
      }
      return oParams.pack();
   }
   return null;
}
// ------------------------------------------------------------
function mgr_ref_close(oRow){
   dialogArguments['select_row'] = this.pack(oRow);
   window.close();
}
// ------------------------------------------------------------
function mgr_ref_move(nIndex){
   if(this.selectRow){
      this.selectRow.bgColor = '#FFFFFF';
   }
   var oRow = null;
   var oRows = id_rows;
   nIndex = parseInt(IString.nvl(nIndex,0));
   if(oRows.length){
      if(nIndex >= 0 && nIndex<this.count){
         oRow = oRows[nIndex];
      }
   }else{
      oRow = oRows;
   }
   if(oRow){
      this.selectRow = oRow;
      oRow.bgColor = '#D8E8FF';
   }
}
// ------------------------------------------------------------
function mgr_ref_movePrior(){
   if(this.position > 0){
      this.position--;
      this.move(this.position);
   }
}
// ------------------------------------------------------------
function mgr_ref_moveNext(){
   if(this.position < this.count-1){
      this.position++;
      this.move(this.position);
   }
}
// ------------------------------------------------------------
function mgr_ref_onEventInitialize(){
   this.count = (typeof(id_rows.length) == 'number') ? id_rows.length : 0;
   this.move(this.position);
   id_table.focus();
}
// ------------------------------------------------------------
function mgr_ref_onTableFocus(){
   id_table.style.border = '1px solid #999999';
   this.isFocus = true;
}
// ------------------------------------------------------------
function mgr_ref_onTableBlur(){
   id_table.style.border = '1px solid #FFFFFF';
   this.isFocus = false;
}
// ------------------------------------------------------------
function mgr_ref_onEventKeydown(oCWin){
   var nKeyCode = oCWin.event.keyCode;
   if(nKeyCode == IKeyCode.ESC){
      this.close();
   }else if(nKeyCode == IKeyCode.UP){
      this.movePrior();
   }else if(nKeyCode == IKeyCode.DOWN){
      this.moveNext();
   }else if(nKeyCode == IKeyCode.ENTER){
      if(this.isFocus){
         this.close(this.selectRow);
      }
   }
}
// ------------------------------------------------------------
function mgr_ref_onEventMousedown(oCWin){
}
// ------------------------------------------------------------
function mgr_ref_onRowOver(oRow){
   if(this.selectRow){
      this.selectRow.bgColor = '#FFFFFF';
   }
   oRow.bgColor = '#D8E8FF';
   this.selectRow = oRow;
}
// ------------------------------------------------------------
function mgr_ref_onRowOut(oRow){
   oRow.bgColor = '#FFFFFF';
}
</SCRIPT>

<js:body styleClass='frmWork' jstop='Y' scroll='no'>
<js:form name='frmConsole'>

<TABLE width='100%' height='100%' border='0' cellspacing='0' cellpadding='4'>
<TR><TD colspan='2' height='20' style="filter:progid:DXImageTransform.Microsoft.Gradient(gradienttype=1, startcolorstr=green, endcolorstr=#FFFFFF);">
   <FONT color='#FFFFFF'><B>Data Reference</B></FONT>
</TD></TR>
<TR valign='top'>
   <TD width='100%'>
      <TABLE width='100%' height='100%' border='0' cellspacing='0' cellpadding='4'>
         <TR><TD height='40px'>Select Queries<SELECT tabindex='1' style='width:100%'></SELECT></TD></TR>
         <TR height='20px'><TD valign='top'>
            <TABLE width='100%' border='0' cellspacing='0' cellpadding='0'><TR>
               <TD id='id_query'>Data List View</TD>
            </TR></TABLE>
         </TD></TR>
         <TR><TD valign='top'>
            <DIV style='height:200px;overflow:auto;'>

<jh:hasChild item='&define'>
<TABLE id='id_table' bgcolor='#999999' width='100%' cellspacing='1' cellpadding='2' tabindex='2' onfocus='DataRefManager.onTableFocus()' onblur='DataRefManager.onTableBlur()'>
   <TR bgcolor='#CCCCCC'>
   <jh:loop item='&define' alias='field'>
      <TD><jh:item item='&field.label'/></TD>
   </jh:loop>
   </TR>
   <TR id='id_fields' style='display:none'>
   <jh:loop item='&define' alias='field'>
      <TD><jh:item item='&field.data_alias'/></TD>
   </jh:loop>
   </TR>
   <jh:hasChild item='&dataset'>
      <jh:loop item='&dataset' alias='unit'>
      <TR id='id_rows' bgcolor='#FFFFFF' onmouseover='DataRefManager.onRowOver(this)' onmouseout='DataRefManager.onRowOut(this)' onclick='DataRefManager.close(this)' style='cursor:hand'>
         <jh:loop item='&define' alias='field'>
            <TD><jh:item item='&unit.{&field.data_name}'/></TD>
         </jh:loop>
      </TR>
      </jh:loop>
   </jh:hasChild>
</TABLE>
</jh:hasChild>

            </DIV>
         </TD></TR>
      </TABLE>
   </TD>
   <TD width='200px'>
      <TABLE width='100%' border='0' cellspacing='0' cellpadding='4'>
         <TR><TD><jh:button tabindex='3' caption='trs:button.cancel|Cancel' width='100px' action='DataRefManager.close()'/></TD></TR>
         <TR><TD><jh:button tabindex='4' caption='trs:button.clear|Clear' width='100px' action='DataRefManager.close()'/></TD></TR>
         <TR><TD><jh:img src='/res/img/n.gif'/></TD></TR>
         <TR><TD><jh:button tabindex='5' caption='trs:button.find|Find' width='100px'/></TD></TR>
         <TR><TD><jh:button tabindex='6' caption='trs:button.save|Save' width='100px'/></TD></TR>
         <TR><TD><jh:button tabindex='7' caption='trs:button.remove|Remove' width='100px'/></TD></TR>
      </TABLE>
   </TD>
<TR>
</TABLE>

</js:form>
</js:body>
</HTML>
<%@ include file="/inc/page/end.inc" %>
