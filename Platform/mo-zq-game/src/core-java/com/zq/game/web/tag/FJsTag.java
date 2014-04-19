package com.zq.game.web.tag;

import org.mo.com.lang.RString;
import org.mo.web.tag.base.FBaseJsTag;

public class FJsTag
      extends FBaseJsTag
{

   @Override
   public int onEnd(){
      return EVAL_PAGE;
   }

   /**
    * <p>开始标签的逻辑</p>
    *
    * @return 逻辑执行后的状态
    */
   @Override
   public int onStart(){
      String type = RString.nvl(_type, "client");
      if("global".equalsIgnoreCase(type)){
         _writer.append("<SCRIPT src=\"");
         if("server".equalsIgnoreCase(type)){
            _writer.append(_context.contextPath(), "/ajs/mobj.js");
         }else{
            _writer.append(_context.contextPath(), "/ajs/client.js");
         }
         // 获得语言标识
         String languageId = _context.session().languageId();
         // 获得主题标识
         String themeId = _context.session().themeId();
         // 设置JS工作环境
         _writer.append("\"></SCRIPT>\n");
         _writer.append("<SCRIPT>RGlobal.initialize(");
         _writer.append("'", _context.contextPath(), "',");
         _writer.append("'", _context.contextTag(), "',");
         _writer.append("'", languageId, "',");
         _writer.append("'", themeId, "',");
         _writer.append("'/game/ars/icon',");
         _writer.append("'/game/ars/pic');");
         _writer.append("</SCRIPT>");
         _writer.flush();
      }else if("runtime".equalsIgnoreCase(type)){
         // 获得语言标识
         String languageId = _context.session().languageId();
         String themeId = _context.session().themeId();
         // 获得主题标识
         // 设置JS工作环境
         _writer.append("<SCRIPT src=\"");
         _writer.append(_context.contextPath(), "/ajs/runtime.js");
         _writer.appendLine("\"></SCRIPT>");
         // 设置JS工作环境
         _writer.appendLine("<SCRIPT>");
         _writer.appendLine("RContext.contextPath = '", _context.contextPath(), "';");
         _writer.appendLine("RContext.contextTag = '", _context.contextTag(), "';");
         _writer.appendLine("RContext.themeId = '", themeId, "';");
         _writer.appendLine("RContext.languageId = '", languageId, "';");
         _writer.appendLine("RContext.uriIcon = '/game/ars/icon';");
         _writer.appendLine("RContext.uriPicture = '/game/ars/pic';");
         _writer.append("</SCRIPT>");
         _writer.flush();
      }else if("runtime.client".equalsIgnoreCase(type)){
         // 设置JS工作环境
         _writer.append("<SCRIPT src=\"");
         _writer.append(_context.contextPath(), "/ajs/runtime.js");
         _writer.append("\"></SCRIPT>");
         _writer.flush();
      }
      return EVAL_BODY_INCLUDE;
   }

}
