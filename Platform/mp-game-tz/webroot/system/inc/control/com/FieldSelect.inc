<jh:equals item='&edit_node.type' value='CHECKBOX'>
	<jh:check displayOnly='Y' item='&unit.{&data_node.data_name}' trueValue='&control_node.true_value' falseValue='&control_node.false_value'/>
</jh:equals>
<jh:equals item='&edit_node.type' value='NUMBER'>
	<jh:item item='&unit.{&data_node.data_name}'/>
</jh:equals>
<jh:equals item='&edit_node.type' value='EDIT'>
   <jh:isTrue item='&edit_node.password'>
      <jh:password displayOnly='Y' item='&unit.{&data_node.data_name}' size='&edit_node.size' maxlength='&data_node.data_size'/>
   </jh:isTrue>
   <jh:isFalse item='&edit_node.password'>
      <jh:edit displayOnly='Y' item='&unit.{&data_node.data_name}' size='&edit_node.size' maxlength='&data_node.data_size'/>
   </jh:isFalse>
</jh:equals>
<jh:equals item='&edit_node.type' value='MEMO'>
	<jh:item item='&unit.{&data_node.data_name}'/>
</jh:equals>
<jh:equals item='&edit_node.type' value='DATE'>
	<jh:datetime displayOnly='Y' item='&unit.{&data_node.data_name}' format='&edit_node.format' />
</jh:equals>
<jh:equals item='&edit_node.type' value='COMBOBOX'>
	<jh:combo displayOnly='Y' item='&unit.{&data_node.data_name}' source='&edit_node.source'/>
</jh:equals>
<jh:equals item='&edit_node.type' value='IP'>
	<jh:ipaddress displayOnly='Y' item='&unit.{&data_node.data_name}'/>
</jh:equals>
<jh:equals item='&edit_node.type' value='OBJECT'>
	<js:preview type='&control_node.disp_preview_type' source='&unit.{&data_node.data_name}' property='&edit_node.object_property'/>
</jh:equals>
<jh:combo displayOnly='Y' item='&control_node.linear_module' source='code|sys|linear.module'/>