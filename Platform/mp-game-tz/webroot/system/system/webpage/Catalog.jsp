<%@ include file='/apl/public.inc' %>
<jh:define item='&page.metas' alias='metas'/>
<jh:define item='&page.extendName' alias='formName'/>
<jh:define item='&page.extendFields' alias='fields'/>

<HTML>
<HEAD>
<TITLE>Welcome</TITLE>
<js:css/>
<js:js type='runtime.client'/>
</HEAD>
<!------------------------------------------------------------>
<STYLE>
</STYLE>
<SCRIPT language='javascript'>
function selForm(name){
}
function onNodeClick(tree, node){
   var type = node.type.name;
   if(type == 'form'){
      var form = node.caption;
      fmMain.action = '?do=showForm&name=' + form;
      fmMain.target = 'frmBody';
      fmMain.submit();
   }else if(type == 'field'){
      var field = node.caption;
      var form = node.parent.caption;
      fmMain.action = '<jh:context/>/inc/form/Form.wa?do=update&fid=system.form.frmField&selform=' + form + "&selfield=" + field;
      fmMain.target = 'frmBody';
      fmMain.submit();
   }
}
function _onloadAll(){
	MoJS.connect();
   var tree = RClass.create(FTreeView);
   tree.linkPanel(_id_tree);
   tree.construct();
	tree.hTable.width = '100%';
   tree.loadDocument(RXml.build(xmlTree.XMLDocument));
   tree.lsnsClick.push(new TListener(null, onNodeClick));
   tree.connect('system.design.form', 'listForms');
}
//----------------------------------------------------------
function _onload(){
   RWindow.connect(window);
   RLoader.loadJs('mobj');
   RLoader.waitJs(new TInvoke(null, _onloadAll), 'mobj');
}
</SCRIPT>
<!------------------------------------------------------------>
<XML ID="xmlTree">
   <TreeView>
   	<Type name='form' icon='form.form' service='system.design.form' action='listFields'/>
   	<Type name='table' icon='form.table' service='system.design.form' action='listFields'/>
   	<Type name='dialog' icon='form.dialog' service='system.design.form' action='listFields'/>
   	<Type name='field' icon='form.field'/>
   </TreeView>
</XML>
<!------------------------------------------------------------>
<BODY style='margin:0; padding-left:6; padding-bottom:6' scroll='no' onload='_onload'>
<FORM name='fmMain' method='post'>

<TABLE border='0' cellpadding='0' cellspacing='0' style='width:100%; height:100%;'><TR><TD>
<DIV id='_id_tree' style='width:100%; height:100%; border:1 solid #999999; padding:3px; overflow:auto'>

<DIV>
</TD></TR></TABLE>

</FORM>
</BODY>

</HTML>

