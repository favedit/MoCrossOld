<%@ page contentType='text/html;charset=utf-8' %>
<%@ include file='/apl/public.inc' %>
<HTML>
<HEAD>
<TITLE>eUIS</TITLE>
<SCRIPT>var pageStart = new Date().getTime();</SCRIPT>
<je:css/>
<je:js/>
<jh:js src='/ajs/apl/FormAction.js'/>
</HEAD>
<!-- Script -------------------------------------------------->
<SCRIPT>
</SCRIPT>
<!-- Environment --------------------------------------------->
<XML ID="xEnvironment">
<jh:write source='&environment' format='text'/>
</XML>
<!-- ToolBar ------------------------------------------------->
<XML ID="xToolBar">
<DsToolBar width='100%'>
<jh:notEquals source='&page.history' value='top'>
   <ToolButton name='btnBack' type='back' label='返回' icon='#tbr.back' action='doBack()'/>
   <ToolButtonSplit/>
</jh:notEquals>
   <ToolButton name='btnFetch' type='fetch' label='查询' icon='#tbr.fetch' hotkey='F2' action='doFetch()'/>
   <ToolButton name='btnSearch' type='search' label='搜索' icon='#tbr.search' hotkey='F3' action='doSearch()'/>
   <ToolButton name='btnLov' type='lov' label='选取'  icon='#tbr.picker' hotkey='F4' action='doLov()'/>
   <ToolButton name='btnZoom' type='zoom' label='放大'  icon='#tbr.zoom' hotkey='F9' action='doZoom()'/>
   <ToolButtonSplit/>
   <ToolButton name='btnInsert' type='insert' label='新建' icon='#tbr.insert' hotkey='F5' action='doInsert()'/>
   <ToolButton name='btnUpdate' type='update' label='更新' icon='#tbr.update' hotkey='F8' action='doUpdate()'/>
   <ToolButton name='btnDelete' type='delete' label='删除' icon='#tbr.delete' hotkey='F6' action='doDelete()'/>
   <ToolButtonSplit/>
   <ToolButton name='btnFirst' type='first' icon='#tbr.ds-first' action='doMovePage(EDataAction.First)'/>
   <ToolButton name='btnPrior' type='prior' icon='#tbr.ds-prior' action='doMovePage(EDataAction.Prior)'/>
   <ToolButton name='btnNext' type='next' icon='#tbr.ds-next' action='doMovePage(EDataAction.Next)'/>
   <ToolButton name='btnLast' type='last' icon='#tbr.ds-last' action='doMovePage(EDataAction.Last)'/>
   <ToolButtonSplit/>
   <ToolButtonMenu name='btnAction' type='action' label='操作'  icon='#tbr.action' icon_disable='tool.actiond' hotkey='F12'>
      <MenuButton name='miDetail' type='first' label='详细信息' icon='#tbr.file-pdf' action='doDetail()'/>
      <MenuButton name='miPrintPdf' type='first' label='打印-PDF' icon='#tbr.file-pdf' action='doPrintPdf()'/>
   </ToolButtonMenu>
</DsToolBar>
</XML>
<!-- Form ---------------------------------------------------->
<XML ID="xForm"><jh:write source='&page.formConfig' format='text'/></XML>
<XML ID="xEvent"><jh:write source='&page.formEvent' format='text'/></XML>
<XML ID="xSearch"><jh:write source='&page.formSearch' format='text'/></XML>
<XML ID="xParent"><jh:write source='&page.formParent' format='text'/></XML>
<XML ID="xOrder"><jh:write source='&page.formOrder' format='text'/></XML>
<XML ID="xValue"><jh:write source='&page.formValue' format='text'/></XML>
<!-- Body begin ---------------------------------------------->
<jh:body style='bodyMain' scroll='no' onload='onFormLoad()'>
<jh:form>
<!-- Hidden begin -------------------------------------------->
<jh:hidden name='page_action' source='&page.action'/>
<jh:hidden name='form_name' source='&page.formName'/>
<jh:hidden name='form_parameters' source='&page.formParameters'/>
<jh:hidden name='form_action' source='&page.formAction'/>
<jh:hidden name='form_parent' source='&page.formParent'/>
<jh:hidden name='form_search' source='&page.formSearch'/>
<jh:hidden name='form_order' source='&page.formOrder'/>
<jh:hidden name='form_pack' source='&page.formPack'/>
<jh:hidden name='ouid'/>
<jh:hidden name='over'/>
<!-- Hidden end ---------------------------------------------->
<TABLE border='0' cellpadding='0' cellspacing='0' width='100%' height='100%'>
<TR><TD height='1'>
<!-- Title <S> -------------------------------------------->
<TABLE height='20'border='0' cellpadding='0' cellspacing='0' width='100%'>
<TR>
   <TD style='padding-left:16; background-color:#DDDDDD;height:100%; filter: progid:DXImageTransform.Microsoft.Gradient(gradienttype=0,startcolorstr=#CCCCFF,endcolorstr=#DDDDDD);'>
         <SPAN id='_id_title'></SPAN>
   </TD>
</TR>
</TABLE>
<!-- Title <E> -------------------------------------------->
</TD></TR>
<TR>
   <TD height='3'><jh:img icon='n'/></TD>
</TR>
<TR><TD height='24'>
<!-- Toolbar begin ------------------------------------------->
<DIV id='_id_toolbar'></DIV>
<!-- Toolbar end --------------------------------------------->
</TD></TR>
<!-- Form begin ---------------------------------------------->
<TR><TD id='_id_form' valign='top' style='padding:8'></TD></TR>
<!-- Form end ------------------------------------------------>
</TABLE>
</jh:form>
</jh:body>
<!-- Body end ------------------------------------------------>
</HTML>
