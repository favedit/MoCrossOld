<%@ page contentType='text/html;charset=utf-8' %>
<%@ include file='/apl/public.inc' %>

<HTML>
<HEAD>
<TITLE>eUIS - CSV数据文件上传</TITLE>
<je:css/>
<je:js type='runtime'/>
<!--------------------------------------------------------->
<SCRIPT language='javascript'>
function onSave(filePath) {
	// 页面跳转
   if('' == filePath){
      alert("请选择合法文件路径！");
      fmMain.filePath.focus();
   }	
   else{
	   fmMain.target = 'frmMain';
	   fmMain.file_path_name.value = 'logic.person.user.detail';
	   fmMain.action = "<jh:context path='/apl/logic/transfer/CsvImport.wa?do=uploadSave'/>";
	   fmMain.submit();
   }
}

//----------------------------------------------------------
function onKeyDown(){
   if (event.keyCode==13){
      alert("请选择合法文件！"); 
   }
}

function _onloadAll(){}
//----------------------------------------------------------
function _onload(){
   RWindow.connect(window);
   RLoader.loadJs('mobj');
}
</SCRIPT>
<!--------------------------------------------------------->
<BODY style='margin:0; padding:0' onload='_onload()' >
<jh:form name='fmMain' method='post' multipart='Y'>
<jh:hidden name='file_path_name'/>
<!-- Detail <S> ------------------------------------------->
<TABLE width="100%" height='100%' border="0" cellpadding="0" cellspacing="8" align=center>
<TR valign="top" align=center>
	<!--------------------------------------------------------->
	<TD width="30%" align=center> 
		<TABLE width="100%" height='100%' border="0" cellpadding="0" cellspacing="0" align=center>
		<TR valign='top'>
            <TD class='NavMenu_Caption'>
              小贴士：<BR><BR>
               <TABLE width='100%' border='0' cellspacing='0' cellpadding='0' class='NavMenu_SubText'>
               <TR valign='top'>
                  <TD class='NavMenu_SubText'><LI>单次只允许导入一个文件，如果需要导入多个CSV文件，请一个一个的按照顺序导入。</LI></TD>
				</TR>
               <TR valign='top'>
				  <TD class='NavMenu_SubText'><LI>目前上传支持的文件大小有限，请尽量选择小于10M的文件上传。</LI></TD> 
				</TR>
               <TR valign='top'>
				  <TD class='NavMenu_SubText'><LI>原导入文件由表头，设置项和数据内容3部分组成，其中第2行有“Y”和”N“两种情况，“Y”表示
                      需要导入的列，“N”表示不需要导入的列，以下为详细样例。</LI></TD> 
				</TR>
               </TABLE>
               <TABLE width='100%' border='0' cellspacing='0' cellpadding='0' class='NavMenu_SubText'>
               <TR valign='top'>
                  <TD class='NavMenu_SubTitle'><LI>导入样例：</LI></TD>
				</TR>
               <TR valign='top'>
				  <TD>
                     <TABLE width='100%' border='0' cellspacing='0' cellpadding='0'>
                      <TR height =20  bgcolor='#CCCCCC'><TD>工号</TD><TD>姓名</TD><TD>民族</TD><TD>婚否</TD><TD>性别</TD></TR>
                       <TR height =20 ><TD>Y</TD><TD>Y</TD><TD>Y</TD><TD>Y</TD><TD>Y</TD></TR>
                      <TR height =20 ><TD>360</TD><TD>王XX</TD><TD>汉族</TD><TD>已婚</TD><TD>男</TD></TR>
                    </TABLE>
                  </TD> 
			  </TR>
               </TABLE>
            </TD>
		</TR>
		</TABLE>
 </TD>
	<TD width="50%">
		<TABLE width="100%" height='100%' border="0" cellpadding="0" cellspacing="0">
        <TR valign='top'>
           <TD class='NavMenu_SubTitle' height=50>请选择上传文件的地址：</TD>
        </TR>
		<TR>
			<TD valign='top' height=50>
				<B>文件地址：</B>&nbsp;&nbsp;
                <jh:file id= 'filePath' source='&page.file' size='30'/>
            </TD>
		</TR>
		<TR>
			<TD valign='top'>
				<B>编码方式：</B>&nbsp;&nbsp;
			   <jh:select id="charset" source="&page.charset" enum='common.charset' onchange="" default="GB18030">
			   </jh:select>
                <BR><BR><BR>
               <IMG src='<jh:img type='path' src='/login/submit.gif'/>' style='cursor:hand' onclick='onSave(filePath.value)'>
            </TD>
		</TR>
		</TABLE>
	</TD>
</TR>
</TABLE>
</jh:form>
</BODY>
</HTML>
