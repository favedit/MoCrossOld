<jh:notEmpty item='&toolbar_button_list'>
	<jh:isTrue item='&table_node.can_search'><jc:tbButton caption='|'/></jh:isTrue>
	<jh:loop item='&toolbar_button_list' alias='toolbar_button'>
      <jh:notEmpty item='&toolbar_button.javascript'>
         <SCRIPT><jh:item item='&toolbar_button.javascript' format='false'/></SCRIPT>
      </jh:notEmpty>
		<jc:tbButton inFrame='&toolbar_button.in_frame' inForm='&toolbar_button.in_form' target='&toolbar_button.target' caption='&toolbar_button.caption' action='&toolbar_button.action' pageAction='&toolbar_button.page_action' icon='&toolbar_button.icon'/>
	</jh:loop>
</jh:notEmpty>