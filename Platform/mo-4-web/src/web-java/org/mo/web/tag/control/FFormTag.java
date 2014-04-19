package org.mo.web.tag.control;

import org.mo.com.lang.FFatalError;
import org.mo.com.lang.RString;
import org.mo.com.xml.EXmlConfig;
import org.mo.com.xml.FXmlNode;
import org.mo.core.aop.RAop;
import org.mo.web.core.webform.IWebFormConsole;

//============================================================
// <T>表单标签。</T>
//============================================================
public class FFormTag
      extends FBaseFormTag
{
   //============================================================
   // <T>开始处理。</T>
   //
   // @return 处理结果
   //============================================================
   @Override
   public int onStart(){
      // 获得来源
      String name = _context.parseString(_name);
      String formName = _context.parseString(_source);
      if(RString.isEmpty(formName)){
         throw new FFatalError("Parse source empty. (source={1})", _source);
      }
      // 获得定义
      IWebFormConsole console = RAop.find(IWebFormConsole.class);
      FXmlNode config = console.build(formName, EXmlConfig.Simple);
      if(config == null){
         throw new FFatalError("Form is not exists. (source={1}, form_name={2})", _source, formName);
      }
      // 输出内容
      _writer.append("<SCRIPT id='", name, "' type='application/xml'>\n");
      _writer.append(config.xml());
      _writer.append("\n</SCRIPT>");
      _writer.flush();
      return SKIP_BODY;
   }

   //============================================================
   // <T>结束处理。</T>
   //
   // @return 处理结果
   //============================================================
   @Override
   public int onEnd(){
      return EVAL_PAGE;
   }
}
