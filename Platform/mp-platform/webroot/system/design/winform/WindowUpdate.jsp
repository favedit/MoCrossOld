<%@ include file='/apl/public.inc' %>
<jh:define form='wfc.window' alias='window_form'/>
<jh:define item='&window_form.window' alias='window'/>
<jh:define item='&window_form.controls' alias='controls'/>

<HTML>
<!-- Html Header Initialize ------------------------------->
<HEAD>
<js:meta/>
<js:css/>
<js:js/>
<jh:js src='/js/mo_ext.js'/>
<jh:title caption='FavInfo'/>
</HEAD>

<jc:toolbar name='top.frames.frmToolbar.oToolbar' inFrame='frmWork' inForm='frmConsole' target='frmWork' action='refresh'>
   <jc:tbButton caption='trs:this|button.save|Save' pageAction='/system/window/WindowUpdateSave.wa' icon='sys.save'/>
   <jc:tbButton caption='trs:this|button.save.order|Order' pageAction='/system/window/WindowUpdateOrder.wa' icon='sys.save'/>
   <jc:tbButton caption='trs:sys|button.delete|Delete' pageAction='/system/window/WindowDelete.wa' icon='sys.delete'/>
   <jc:tbButton caption='|'/>
   <jc:tbButton caption='trs:sys|button.build.source|Source' pageAction='/system/window/WindowBuildSource.wa' icon='sys.cfg'/>
   <jc:tbButton caption='|'/>
   <jc:tbButton caption='trs:sys|button.insert.control|Insert Control' pageAction='/system/window/ControlInsert.wa' icon='sys.insert'/>
</jc:toolbar>

<!-- Body Initialize -------------------------------------->
<js:body styleClass='frmWork' scroll='auto'>
<js:form name='frmConsole' dataForm='top.frmToolbar.frmConsole'>

<!-- Page Control Initialize ------------------------------>
<TABLE width='100%' border='0' cellspacing='0' cellpadding='8'>
<TR><TD>
   Property:<BR>
   <TABLE width='100%' border='0' cellspacing='0' cellpadding='2'>
   <TR>
      <TD width='80'>Name:</TD>
      <TD><jh:edit item='&window.name' size='20'/></TD>
   </TR>
   <TR>
      <TD>Label:</TD>
      <TD colspan='3'><jh:edit item='&window.label' size='40'/></TD>
   </TR>
   <TR>
      <TD>Note:</TD>
      <TD colspan='3'><jh:memo item='&window.note' cols='40' rows='2'/></TD>
   </TR>
   </TABLE>

   <jh:hasChild item='&controls'>
   <BR>
   <TABLE width='100%' border='0' cellspacing='1' cellpadding='2' class='table'>
      <TR class='tableHead'>
         <TD width='100'><jh:text value='trs:this|field.name|Name'/></TD>
         <TD width='100'><jh:text value='trs:this|field.label|Label'/></TD>
         <TD><jh:text value='trs:this|field.disporder|Display Order'/></TD>
      </TR>
      <jh:loop item='&controls' alias='control'>
      <TR class='tableLine'>
         <TD width='100'><jh:item item='&control.name'/></TD>
         <TD width='100'><jh:item item='&control.label'/></TD>
         <TD><jh:number item='&control.disp_order' size='2' maxlength='4'/></TD>
      </TR>
      </jh:loop>
   </TABLE>
   </jh:hasChild>

</TD></TR>
</TABLE>


</js:form>
</js:body>
</HTML>

