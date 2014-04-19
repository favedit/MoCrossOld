<%@ include file='/apl/public.inc' %>
<jh:define item='&webform.action' alias='action'/>
<jh:define item='&webform.form' alias='form'/>
<jh:define item='&webform.fields' alias='fields'/>
<jh:define item='&webform.item' alias='item'/>
<jh:define item='&webform.list' alias='list'/>

<HTML>
<HEAD>
<TITLE>Welcome</TITLE>
<jh:css />
<jh:js src='/js/mobj.js'/>
</HEAD>
<!------------------------------------------------------------>
<STYLE>
.tbarBtnNor {color:#666666; background-color:#DDDDDD;
      padding-top:0; padding-left:16; padding-bottom:0; padding-right:16; }
.tbarBtnHover {color:#666666; background-color:#E7A476; cursor:hand;
      padding-top:0; padding-left:16; padding-bottom:0; padding-right:16; }
.tbarBtnSel {color:#666666; background-color:#DD7C3B;
      padding-top:0; padding-left:16; padding-bottom:0; padding-right:16; }
</STYLE>
<SCRIPT language='javascript'>
function tbn_onmenter(o){
   if(o.className == 'tbarBtnNor'){
      o.className = 'tbarBtnHover';
   }
}
function btn_onmleave(o){
   if(o.className == 'tbarBtnHover'){
      o.className = 'tbarBtnNor';
   }
}
function btn_onmdownk(o,method){
   fmMain.action = '?do=' + method;
   fmMain.target = '';
   fmMain.submit();
}
// ------------------------------------------------------------
function selConsole(name){
}
</SCRIPT>
<!------------------------------------------------------------>
<BODY style='margin:0; padding-right:6; padding-bottom:6' scroll='no'>
<FORM name='fmMain' method='post'>

<TABLE border='0' cellpadding='0' cellspacing='0' width='100%' height='100%'>
   <TR>
      <TD height='20' style='border:1 solid #999999'>

<!------- Toolbar Start -------------------------------------->
<TABLE border='0' cellpadding="0" cellspacing='1' height='20'>
<TR id='_id_tools' style='display:block'>
   <!------- Show Action ---------------------------------------->
   <jh:equals item='&action' value='show'>
      <TD class='tbarBtnNor' onmouseenter='tbn_onmenter(this)' onmouseleave='btn_onmleave(this)' onmousedown='btn_onmdownk(this,"back")'>Back</TD>
      <TD>&nbsp;</TD>
      <jh:isTrue item='&form.editSearch'>
         <TD class='tbarBtnNor' onmouseenter='tbn_onmenter(this)' onmouseleave='btn_onmleave(this)' onmousedown='btn_onmdownk(this,"search")'>Search</TD>
         <TD>&nbsp;</TD>
      </jh:isTrue>
      <jh:isTrue item='&form.editInsert'>
         <TD class='tbarBtnNor' onmouseenter='tbn_onmenter(this)' onmouseleave='btn_onmleave(this)' onmousedown='btn_onmdownk(this,"insert")'>Insert</TD>
      </jh:isTrue>
      <jh:isTrue item='&form.editUpdate'>
         <TD class='tbarBtnNor' onmouseenter='tbn_onmenter(this)' onmouseleave='btn_onmleave(this)' onmousedown='btn_onmdownk(this,"update")'>Update</TD>
      </jh:isTrue>
      <jh:isTrue item='&form.editDelete'>
         <TD class='tbarBtnNor' onmouseenter='tbn_onmenter(this)' onmouseleave='btn_onmleave(this)' onmousedown='btn_onmdownk(this,"delete")'>Delete</TD>
      </jh:isTrue>
   </jh:equals>
   <!------- Update Action -------------------------------------->
   <jh:equals item='&action' value='update'>
      <TD class='tbarBtnNor' onmouseenter='tbn_onmenter(this)' onmouseleave='btn_onmleave(this)' onmousedown='btn_onmdownk(this,"updateSave")'>Save</TD>
   </jh:equals>
</TR>
</TABLE>
<!------- Toolbar End ---------------------------------------->

      </TD>
   </TR>
   <TR><TD height='4'></TD></TR>
   <TR>
      <TD style='border:1 solid #999999' valign='top'>

<!------- Form Start ----------------------------------------->
<TABLE border='0' cellpadding='4' cellspacing='0'><TR>
<jh:loop item='&fields' alias='field'>

<jh:equals item='&field.editType' value='line'>
</TR></TABLE>
<TABLE border='0' cellpadding='0' cellspacing='0' width='100%'>
   <TR>
      <TD width='10%'><HR></TD>
      <TD style='padding-left:20;padding-right:20;'><jh:write item='&field.label'/></TD>
      <TD width='90%'><HR></TD>
   </TR>
</TABLE>
<TABLE border='0' cellpadding='4' cellspacing='0'><TR>
</jh:equals>

<jh:notEquals item='&field.editType' value='line'>
   <TD width='<jh:write item='&field.labelWidth'/>'><jh:write item='&field.label'/></TD>
   <TD>
      <jh:equals item='&field.editType' value='edit'>
         <jh:edit item='&item.{&field.dataName}'/>
      </jh:equals>
      <jh:equals item='&field.editType' value='check'>
         <jh:check item='&item.{&field.dataName}'/>
      </jh:equals>
      <jh:equals item='&field.editType' value='combo'>
         <jh:combo item='&item.{&field.dataName}' enum='&field.editRefer'/>
      </jh:equals>
   </TD>
</jh:notEquals>

<jh:equals item='&field.dispWrap' value='Y'>
</TR></TABLE>
<TABLE border='0' cellpadding='4' cellspacing='0'><TR>
</jh:equals>

</jh:loop>
</TR></TABLE>
<!------- Form End ------------------------------------------->

      </TD>
   </TR>
</TABLE>

</FORM>
</BODY>

</HTML>

