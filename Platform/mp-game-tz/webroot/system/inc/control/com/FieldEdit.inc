<jh:equals item='&edit_node.type' value='CHECKBOX'>
	<jh:check item='&unit.{&data_node.data_name}' trueValue='&edit_node.true' falseValue='&edit_node.false' default='&data_node.data_default'/>
</jh:equals>
<jh:equals item='&edit_node.type' value='NUMBER'>
	<jh:number item='&unit.{&data_node.data_name}' size='&edit_node.size' maxlength='&data_node.data_size' readonly='&edit_node.readonly'/>
</jh:equals>
<jh:equals item='&edit_node.type' value='EDIT'>
   <jh:isTrue item='&edit_node.password'>
      <jh:password item='&unit.{&data_node.data_name}' size='&edit_node.size' maxlength='&data_node.data_size' readonly='&edit_node.readonly'/>
   </jh:isTrue>
   <jh:isFalse item='&edit_node.password'>
      <jh:edit item='&unit.{&data_node.data_name}' size='&edit_node.size' maxlength='&data_node.data_size' readonly='&edit_node.readonly'/>
   </jh:isFalse>
</jh:equals>
<jh:equals item='&edit_node.type' value='MEMO'>
	<jh:memo item='&unit.{&data_node.data_name}' rows='&edit_node.rows' cols='&edit_node.cols'/>
</jh:equals>
<jh:equals item='&edit_node.type' value='DATE'>
	<jh:datetime item='&unit.{&data_node.data_name}' format='&edit_node.input_format' default='&data_node.data_default'/>
</jh:equals>
<jh:equals item='&edit_node.type' value='COMBOBOX'>
	<jh:isTrue item='&edit_node.event'>
		<jh:combo item='&unit.{&data_node.data_name}' source='&edit_node.source' default='&data_node.data_default' parameter='&link_unit' onchange='_onRefresh()'/>
	</jh:isTrue>
	<jh:isFalse item='&edit_node.event'>
		<jh:combo item='&unit.{&data_node.data_name}' source='&edit_node.source' default='&data_node.data_default' parameter='&link_unit' />
	</jh:isFalse>
</jh:equals>
<jh:equals item='&edit_node.type' value='IP'>
	<jh:ipaddress item='&unit.{&data_node.data_name}'/>
</jh:equals>
<jh:equals item='&edit_node.type' value='PREVIEW'>
	<js:preview type='&control_node.disp_preview_type' source='&unit.{&data_node.data_name}' property='&edit_node.object_property'/>
</jh:equals>
<jh:combo displayOnly='Y' item='&control_node.linear_module' source='code|sys|linear.module'/>
<jh:isTrue item='&edit_node.lov_option'>
   <jc:lov item='&unit' source='&edit_node.link_window' lovWidth='&edit_node.link_window_width' lovHeight='&edit_node.link_window_height'/>
</jh:isTrue>
