package org.mo.web.tag.control;

import org.mo.com.lang.FFatalError;
import org.mo.com.lang.RBoolean;
import org.mo.com.xml.FXmlNode;
import org.mo.core.aop.RAop;
import org.mo.web.core.webtree.IWebTreeConsole;

//============================================================
// <T>树目录标签。</T>
//============================================================
public class FTreeTag
      extends FBaseTreeTag
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
      String treeName = _context.parseString(_source);
      // 获得定义
      IWebTreeConsole console = RAop.find(IWebTreeConsole.class);
      FXmlNode config = null;
      if(RBoolean.parse(_permission)){
         String userId = _context.session().user().userId();
         config = console.buildSimpleWithPermission(treeName, userId);
      }else{
         config = console.buildSimple(treeName);
      }
      if(config == null){
         throw new FFatalError("Tree is not exists. (source={1}, tree_name={2})", _source, treeName);
      }
      // 输出内容
      _writer.append("<SCRIPT id='", name, "' type='application/xml'>\n");
      if(null != config){
         _writer.append(config.xml());
      }
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
