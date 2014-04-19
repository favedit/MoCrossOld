package org.mo.game.editor.face.editor.logic;

import org.mo.com.lang.RBoolean;
import org.mo.com.lang.RString;
import org.mo.com.xml.IXmlObject;
import org.mo.core.aop.face.ALink;
import org.mo.game.editor.core.enums.common.XConstant;
import org.mo.game.editor.core.enums.common.XEnum;
import org.mo.game.editor.core.enums.common.XEnumGroup;
import org.mo.game.editor.core.enums.common.XEnumItem;
import org.mo.game.editor.core.logic.ILogicConsole;
import org.mo.game.editor.core.logic.common.XLogic;
import org.mo.jfa.common.xmlobjects.FAbsXmlObjectService;
import org.mo.web.core.webtree.common.XTreeNode;
import org.mo.web.protocol.context.IWebContext;
import org.mo.web.protocol.context.IWebInput;
import org.mo.web.protocol.context.IWebOutput;

//============================================================
public class FLogicService
      extends FAbsXmlObjectService<XLogic>
      implements
         ILogicService
{
   @ALink
   protected ILogicConsole _logicConsole;

   //============================================================
   @Override
   protected String makeCollectionName(String name){
      int find = name.indexOf(" - ");
      if(-1 != find){
         name = name.substring(find + 3);
      }
      return name;
   }

   //============================================================
   @Override
   protected void onBuildTreeNode(XTreeNode xnode,
                                  IXmlObject xobject){
      String objectName = xobject.name();
      if(XEnumGroup.isName(objectName)){
         String name = xobject.innerGet("name");
         String displayIndex = xobject.innerGet("display_index");
         if(!RString.isEmpty(displayIndex)){
            displayIndex = "<FONT color='darkred'>" + displayIndex + "</FONT> - ";
         }else{
            displayIndex = "";
         }
         xnode.setLabel(displayIndex + name);
      }else if(XConstant.isName(objectName)){
         String name = xobject.innerGet("name");
         String value = xobject.innerGet("value");
         xnode.setLabel(name + " = <FONT color='gray'>" + value + "</FONT>");
      }else if(XEnum.isName(objectName)){
         String name = xobject.innerGet("name");
         String value = Integer.toString(xobject.children().count());
         if(RString.isEmpty(value)){
            value = name;
         }else{
            value = name + " : " + value;
         }
         // 设置标志
         String flag = "";
         flag += RBoolean.parse(xobject.innerGet(XEnum.PTY_IS_SET)) ? "S" : "_";
         flag += RBoolean.parse(xobject.innerGet(XEnum.PTY_IS_COUNT)) ? "C" : "_";
         flag += ":";
         flag += RBoolean.parse(xobject.innerGet(XEnum.PTY_IS_SERVER_CPP)) ? "C" : "_";
         flag += RBoolean.parse(xobject.innerGet(XEnum.PTY_IS_SERVER_JAVA)) ? "J" : "_";
         flag += ":";
         flag += RBoolean.parse(xobject.innerGet(XEnum.PTY_IS_CLIENT_CPP)) ? "C" : "_";
         flag += RBoolean.parse(xobject.innerGet(XEnum.PTY_IS_CLIENT_JAVA)) ? "J" : "_";
         flag += RBoolean.parse(xobject.innerGet(XEnum.PTY_IS_CLIENT_AS)) ? "A" : "_";
         flag += RBoolean.parse(xobject.innerGet(XEnum.PTY_IS_CLIENT_CS)) ? "S" : "_";
         // 设置显示内容
         String display = "<FONT face='NSimSun' color='gray'>[" + flag + "]</FONT> " + value;
         xnode.setLabel(display);
      }else if(XEnumItem.isName(objectName)){
         String name = xobject.innerGet("name");
         String value = xobject.innerGet("value");
         String flag = xobject.innerGet("flag");
         String code = xobject.innerGet("code");
         String label = "";
         if(!RString.isEmpty(code)){
            code = "<FONT face='NSimSun' color='gray'>[" + code + "]</FONT> ";
         }else{
            code = "";
         }
         if(RString.isEmpty(value)){
            label = name;
         }else{
            label = name + " = " + value;
         }
         if(!RString.isEmpty(flag)){
            label = "(" + flag + ") " + label;
         }
         xnode.setLabel(code + label);
      }
   }

   //============================================================
   @Override
   public void catalog(IWebContext context,
                       IWebInput input,
                       IWebOutput output){
      catalog(_logicConsole, context, input, output);
   }

   //============================================================
   @Override
   public void list(IWebContext context,
                    IWebInput input,
                    IWebOutput output){
      list(_logicConsole, context, input, output);
   }

   //============================================================
   @Override
   public void insert(IWebContext context,
                      IWebInput input,
                      IWebOutput output){
      insert(_logicConsole, context, input, output);
   }

   //============================================================
   @Override
   public void update(IWebContext context,
                      IWebInput input,
                      IWebOutput output){
      update(_logicConsole, context, input, output);
   }

   //============================================================
   @Override
   public void delete(IWebContext context,
                      IWebInput input,
                      IWebOutput output){
      delete(_logicConsole, context, input, output);
   }

   //============================================================
   @Override
   public void sort(IWebContext context,
                    IWebInput input,
                    IWebOutput output){
      sort(_logicConsole, context, input, output);
   }
}
