<%@ page contentType='text/html;charset=utf-8' %>
<%@ include file='/apl/public.inc' %>

<HTML>
<HEAD>
<TITLE>Welcome</TITLE>
<js:css/>
<js:js type='runtime.client'/>
</HEAD>
<!-- Script -------------------------------------------------->
<SCRIPT language='javascript'>
function onNodeClick(tree, node){
   if(node.type.typeName == 'object'){
      var name = node.get('name');
      var face = node.get('face');
      if(face){
         fmMain.action = '?do=showObject&type=face&name=' + face;
      }else if(name){
         fmMain.action = '?do=showObject&type=name&name=' + name;
      }else{
         fmMain.action = '?do=showObject&type=name&name=' + node.caption;
      }
      fmMain.target = 'frmMain';
      fmMain.submit();
   }
}
function _onloadAll(){
	MoJS.connect();
   // TreeView
   tree = RControl.fromXml(xTree, _id_tree);
   tree.lsnsClick.push(new TListener(tree, onNodeClick));
// Global
	RGlobal.set('catalog.tree', tree);
}
//----------------------------------------------------------
function _onload(){
   RWindow.connect(window);
   RLoader.loadJs('mobj');
   RLoader.waitJs(new TInvoke(null, _onloadAll), 'mobj');
}
</SCRIPT>
<!------------------------------------------------------------>
<XML ID="xTree">
<TreeView>
   <Type name='floder' type_name='floder' icon='sys-floder'/>
   <Type name='object' type_name='object' icon='#monitor.objects.instance'/>
   
   <Node type='object' label='Core Config' attribute='name:core.config'/>
   <Node type='object' label='Core Component' attribute='name:core.component'/>
   <Node type='object' label='Engine Database' attribute='face:org.mobj.eng.data.IDatabaseConsole'/>
   <Node type='object' label='Engine Template' attribute='face:org.mobj.eng.template.ITemplateConsole'/>
   <Node type='object' label='Web Action' attribute='face:org.mobj.web.core.action.IActionConsole'/>
   <Node type='object' label='Web Service' attribute='face:org.mobj.web.core.service.IServiceConsole'/>
   <Node type='object' label='Web Form' attribute='face:org.mobj.web.core.form.IFormConsole'/>
   <Node type='object' label='Web Session' attribute='face:org.mobj.web.core.session.IWebSessionConsole'/>
</TreeView>
</XML>
<!-- Body begin ---------------------------------------------->
<jh:body style='bodyCatalog' onload='_onload()' scroll='no'>
<jh:form name='fmMain' method='post'>
<!-- Hidden -------------------------------------------------->
<jh:hidden name='form_name'/>
<jh:hidden name='sel_type'/>
<jh:hidden name='sel_collection'/>
<jh:hidden name='sel_component'/>
<!-- Catalog ------------------------------------------------->
<TABLE width='100%' height='100%' border='0' cellpadding='0' cellspacing='0'>
<TR><TD>
<!-- Tree begin ---------------------------------------------->
<DIV id='_id_tree' style='width:100%; height:100%; overflow:auto'><DIV>
<!-- Tree end ------------------------------------------------>
</TD></TR>
</TABLE>
</jh:form>
</jh:body>
<!-- Body end ------------------------------------------------>
</HTML>
