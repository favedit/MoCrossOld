<%@ include file='/apl/public.inc' %>
<jh:define source='&tag.config' alias='config'/>
<table border="1">
   <tr>
      <td>测试</td>
   </tr>
   <tr> 
      <td cols="3">*[<jh:write source='&tag.hello'/>]*</td>
   </tr>
   <jh:loop source='&tag.config' alias='con'>
      <tr  cols="3">
         <td><jh:write source='&con.name' /></td>
      </tr>
<tr>
      <jh:loop source='&con' alias='chi'>
            <td>--<jh:write source='&chi.name' /></td>
       
      </jh:loop>
  </tr>
   </jh:loop>
   
</table>


