<%@ page contentType='text/html;charset=utf-8' %>
<!-- Script ----------------------------------------------->
<SCRIPT>
var _start = 0;
var _pageStart = new Date().getTime();
//----------------------------------------------------------
function _onload(){
   _start = new Date().getTime();
   RWindow.connect(window);
   RLoader.loadJs('mobj', 'workspace');
   RLoader.waitJs(new TInvoke(null, _onloadAll), 'mobj', 'workspace');
}
</SCRIPT>
<!-- Form ------------------------------------------------->
<XML ID="xEnvironment"><jh:write source='&page.environment' format='text'/></XML>
<XML ID="xForm"><jh:write source='&page.formConfig' format='text'/></XML>
<XML ID="xEvent"><jh:write source='&page.formEvent' format='text'/></XML>
<XML ID="xSearch"><jh:write source='&page.formSearch' format='text'/></XML>
<XML ID="xParent"><jh:write source='&page.formParent' format='text'/></XML>
<XML ID="xOrder"><jh:write source='&page.formOrder' format='text'/></XML>
<XML ID="xValue"><jh:write source='&page.formValue' format='text'/></XML>
<!-- ToolBar ---------------------------------------------->
<jc:toolbar name='xToolBar' source='logic.common'/>
<!-- Body begin ------------------------------------------->
<jh:body onload='_onload()' scroll='no' style='Frame_Workspace'>
<jh:form>
<!-- Hidden begin ----------------------------------------->
<jh:hidden name='page_action' source='&page.action'/>
<jh:hidden name='form_name' source='&page.formName'/>
<jh:hidden name='form_action' source='&page.formAction'/>
<jh:hidden name='form_parent' source='&page.formParent'/>
<jh:hidden name='form_search' source='&page.formSearch'/>
<jh:hidden name='form_order' source='&page.formOrder'/>
<jh:hidden name='form_value' source='&page.formValue'/>
<jh:hidden name='form_pack' source='&page.formPack'/>
<!-- Hidden end ------------------------------------------->
<TABLE border='0' cellpadding='0' cellspacing='0' width='100%' height='100%'>
<!-- Title ------------------------------------------------>
<TR><TD height='1' style='overflow:hidden'>
   <DIV id='id_title' class='Workspace_TitleBar'></DIV>
</TD></TR>
<!-- HistoryBar ------------------------------------------->
<TR><TD height='1'>
   <TABLE border='0' cellpadding='0' cellspacing='0' width='100%' style='table-layout:fixed'>
   <TR><TD id='id_historybar' class='Workspace_HistoryBar'></TD></TR>
   </TABLE>
</TD></TR>
<!-- ToolBar ---------------------------------------------->
<TR><TD height='1'>
   <TABLE border='0' cellpadding='0' cellspacing='0' width='100%' style='table-layout:fixed'>
   <TR><TD id='id_toolbar' class='Workspace_ToolBar'></TD></TR>
   </TABLE>
</TD></TR>
<!-- Form ------------------------------------------------->
<TR><TD valign='top'>
   <DIV id='id_form' style='width:100%;height:100%;overflow:auto;'></DIV>
</TD></TR>
</TABLE>
</jh:form>
</jh:body>
<!-- Body end --------------------------------------------->
</HTML>
