<%@ page contentType='text/html;charset=utf-8'%>
<%@ include file='/apl/public.inc'%>

<HTML>
<HEAD>
<TITLE>Class Example</TITLE>
<js:css />
<js:js type='runtime.client'/>
</HEAD>
<!-- Script -------------------------------------------------->
<SCRIPT>
var form = null;
var toolbar = null;
function doRefresh() {
   fmMain.action = '';
   fmMain.submit();
}
function doSelectAll(){
   fmMain.<jh:name source='&page.buildTable'/>.value = 'Y';
   fmMain.<jh:name source='&page.buildTableDel'/>.value = 'Y';
   fmMain.<jh:name source='&page.buildView'/>.value = 'Y';
   fmMain.<jh:name source='&page.buildSequence'/>.value = 'Y';
   fmMain.<jh:name source='&page.buildSequenceDel'/>.value = 'Y';
   fmMain.<jh:name source='&page.buildPackageHead'/>.value = 'Y';
   fmMain.<jh:name source='&page.buildPackageBody'/>.value = 'Y';
   fmMain.<jh:name source='&page.buildStore'/>.value = 'Y';
   fmMain.<jh:name source='&page.buildRestore'/>.value = 'Y';
   fmMain.<jh:name source='&page.buildHsTable'/>.value = 'Y';
   fmMain.<jh:name source='&page.buildHsTableDel'/>.value = 'Y';
   fmMain.<jh:name source='&page.buildHsView'/>.value = 'Y';
   fmMain.<jh:name source='&page.buildHsSequence'/>.value = 'Y';
   fmMain.<jh:name source='&page.buildHsSequenceDel'/>.value = 'Y';
   fmMain.<jh:name source='&page.buildHsPackageHead'/>.value = 'Y';
   fmMain.<jh:name source='&page.buildHsPackageBody'/>.value = 'Y';
   fmMain.<jh:name source='&page.buildHsStore'/>.value = 'Y';
   fmMain.<jh:name source='&page.buildHsRestore'/>.value = 'Y';

   fmMain.<jh:name source='&page.buildTable'/>_chk.checked = true;
   fmMain.<jh:name source='&page.buildTableDel'/>_chk.checked = true;
   fmMain.<jh:name source='&page.buildView'/>_chk.checked = true;
   fmMain.<jh:name source='&page.buildSequence'/>_chk.checked = true;
   fmMain.<jh:name source='&page.buildSequenceDel'/>_chk.checked = true;
   fmMain.<jh:name source='&page.buildPackageHead'/>_chk.checked = true;
   fmMain.<jh:name source='&page.buildPackageBody'/>_chk.checked = true;
   fmMain.<jh:name source='&page.buildStore'/>_chk.checked = true;
   fmMain.<jh:name source='&page.buildRestore'/>_chk.checked = true;
   fmMain.<jh:name source='&page.buildHsTable'/>_chk.checked = true;
   fmMain.<jh:name source='&page.buildHsTableDel'/>_chk.checked = true;
   fmMain.<jh:name source='&page.buildHsView'/>_chk.checked = true;
   fmMain.<jh:name source='&page.buildHsSequence'/>_chk.checked = true;
   fmMain.<jh:name source='&page.buildHsSequenceDel'/>_chk.checked = true;
   fmMain.<jh:name source='&page.buildHsPackageHead'/>_chk.checked = true;
   fmMain.<jh:name source='&page.buildHsPackageBody'/>_chk.checked = true;
   fmMain.<jh:name source='&page.buildHsStore'/>_chk.checked = true;
   fmMain.<jh:name source='&page.buildHsRestore'/>_chk.checked = true;
}
function doCancelAll(){
   fmMain.<jh:name source='&page.buildTable'/>.value = 'N';
   fmMain.<jh:name source='&page.buildTableDel'/>.value = 'N';
   fmMain.<jh:name source='&page.buildView'/>.value = 'N';
   fmMain.<jh:name source='&page.buildSequence'/>.value = 'N';
   fmMain.<jh:name source='&page.buildSequenceDel'/>.value = 'N';
   fmMain.<jh:name source='&page.buildPackageHead'/>.value = 'N';
   fmMain.<jh:name source='&page.buildPackageBody'/>.value = 'N';
   fmMain.<jh:name source='&page.buildStore'/>.value = 'N';
   fmMain.<jh:name source='&page.buildRestore'/>.value = 'N';
   fmMain.<jh:name source='&page.buildHsTable'/>.value = 'N';
   fmMain.<jh:name source='&page.buildHsTableDel'/>.value = 'N';
   fmMain.<jh:name source='&page.buildHsView'/>.value = 'N';
   fmMain.<jh:name source='&page.buildHsSequence'/>.value = 'N';
   fmMain.<jh:name source='&page.buildHsSequenceDel'/>.value = 'N';
   fmMain.<jh:name source='&page.buildHsPackageHead'/>.value = 'N';
   fmMain.<jh:name source='&page.buildHsPackageBody'/>.value = 'N';
   fmMain.<jh:name source='&page.buildHsStore'/>.value = 'N';
   fmMain.<jh:name source='&page.buildHsRestore'/>.value = 'N';
   
   fmMain.<jh:name source='&page.buildTable'/>_chk.checked = false;
   fmMain.<jh:name source='&page.buildTableDel'/>_chk.checked = false;
   fmMain.<jh:name source='&page.buildView'/>_chk.checked = false;
   fmMain.<jh:name source='&page.buildSequence'/>_chk.checked = false;
   fmMain.<jh:name source='&page.buildSequenceDel'/>_chk.checked = false;
   fmMain.<jh:name source='&page.buildPackageHead'/>_chk.checked = false;
   fmMain.<jh:name source='&page.buildPackageBody'/>_chk.checked = false;
   fmMain.<jh:name source='&page.buildStore'/>_chk.checked = false;
   fmMain.<jh:name source='&page.buildRestore'/>_chk.checked = false;
   fmMain.<jh:name source='&page.buildHsTable'/>_chk.checked = false;
   fmMain.<jh:name source='&page.buildHsTableDel'/>_chk.checked = false;
   fmMain.<jh:name source='&page.buildHsView'/>_chk.checked = false;
   fmMain.<jh:name source='&page.buildHsSequence'/>_chk.checked = false;
   fmMain.<jh:name source='&page.buildHsSequenceDel'/>_chk.checked = false;
   fmMain.<jh:name source='&page.buildHsPackageHead'/>_chk.checked = false;
   fmMain.<jh:name source='&page.buildHsPackageBody'/>_chk.checked = false;
   fmMain.<jh:name source='&page.buildHsStore'/>_chk.checked = false;
   fmMain.<jh:name source='&page.buildHsRestore'/>_chk.checked = false;
}
function doExecute() {
   fmMain.action = '<jh:context/>/database/dataset/Dataset.wa?do=allBuildSource&type=execute';
   fmMain.submit();
}
function _onloadAll() {
	MoJS.connect();
   toolbar = RControl.fromXml(xToolBar, _id_toolbar);
}
//----------------------------------------------------------
function _onload(){
   RWindow.connect(window);
   RLoader.loadJs('mobj');
   RLoader.waitJs(new TInvoke(null, _onloadAll), 'mobj');
}
</SCRIPT>
<!-- Environment --------------------------------------------->
<XML ID="xEnvironment">
<jh:write source='&environment' format='text' />
</XML>
<!-- ToolBar ------------------------------------------------->
<XML ID="xToolBar">
<ToolBar width='100%' height='100%'>
<ToolButton name='btnRefresh' label='刷新' icon='tool.refresh' icon_disable='tool.refreshd' action='doRefresh()' />
<ToolButtonSplit />
<ToolButton name='btnSelectNone' label='全部选中' icon='tool.refresh' icon_disable='tool.executed' action='doSelectAll()' />
<ToolButton name='btnSelectAll' label='全部取消' icon='tool.refresh' icon_disable='tool.executed' action='doCancelAll()' />
<ToolButtonSplit />
<ToolButton name='btnExecute' label='执行' icon='tool.execute' icon_disable='tool.executed' action='doExecute()' />
</ToolBar>
</XML>
<!-- Body begin ---------------------------------------------->
<jh:body style='bodyMain' scroll='no' onload='_onload()'>
<jh:form>
<jh:hidden name='form_name' source='&page.formName'/>
<jh:hidden name='form_service' source='&page.formService'/>
<jh:hidden name='source_type' source='&#parameter.type'/>
<jh:hidden name='sel_type' source='&page.selectType'/>
<jh:hidden name='sel_collection' source='&page.selectCollection'/>
<jh:hidden name='sel_component' source='&page.selectComponent'/>
<TABLE border='0' cellpadding='0' cellspacing='0' width='100%' height='100%'>
   <TR>
      <TD height='24'><!-- Toolbar begin ------------------------------------------->
      <DIV id='_id_toolbar'></DIV>
      <!-- Toolbar end ---------------------------------------------></TD>
   </TR>
   <TR>
      <TD height='2'></TD>
   </TR>
   <TR>
      <TD style='padding:16' valign='top'>
<B>实体表</B><BR><BR>
<table width="100%" border="0" cellpadding="2" cellspacing="0">
  <tr>
    <td width="140"><jh:check source="&page.buildTable"/> 数据表</td>
    <td><jh:check source="&page.buildTableDel"/> 反映前删除数据的数据表</td>
    </tr>
  <tr>
     <td><jh:check source="&page.buildView"/> 数据视图</td>
     <td>&nbsp;</td>
     </tr>
  <tr>
     <td><jh:check source="&page.buildSequence"/> 序列</td>
     <td><jh:check source="&page.buildSequenceDel"/> 反映前删除数据库的序列</td>
     </tr>
  <tr>
    <td><jh:check source="&page.buildPackageHead"/> Package包头</td>
    <td><jh:check source="&page.buildPackageBody"/> Package包体</td>
  	</tr>
  <tr>
    <td><jh:check source="&page.buildStore"/> 数据存储</td>
    <td><jh:check source="&page.buildRestore"/> 数据恢复</td>
    </tr>
</table>
<BR><BR>
<B>历史表</B><BR><BR>
<table width="100%" border="0" cellpadding="2" cellspacing="0">
  <tr>
    <td width="140"><jh:check source="&page.buildHsTable"/> 数据表</td>
    <td><jh:check source="&page.buildHsTableDel"/> 反映前删除数据的数据表</td>
    </tr>
  <tr>
     <td><jh:check source="&page.buildHsView"/> 数据视图</td>
     <td>&nbsp;</td>
     </tr>
  <tr>
     <td><jh:check source="&page.buildHsSequence"/> 序列</td>
     <td><jh:check source="&page.buildHsSequenceDel"/> 反映前删除数据库的序列</td>
     </tr>
  <tr>
    <td><jh:check source="&page.buildHsPackageHead"/> Package包头</td>
    <td><jh:check source="&page.buildHsPackageBody"/> Package包体</td>
    </tr>
  <tr>
    <td><jh:check source="&page.buildHsStore"/> 数据存储</td>
    <td><jh:check source="&page.buildHsRestore"/> 数据恢复</td>
    </tr>
</table>
</TD>
        </TR>
      </TABLE>
   </jh:form>
</jh:body>
<!-- Body end ------------------------------------------------>
</HTML>
