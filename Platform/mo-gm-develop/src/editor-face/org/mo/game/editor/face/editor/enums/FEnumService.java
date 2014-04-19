package org.mo.game.editor.face.editor.enums;

import org.mo.com.lang.FFatalError;
import org.mo.com.lang.RBoolean;
import org.mo.com.lang.RString;
import org.mo.com.lang.RStrings;
import org.mo.com.xml.FXmlNode;
import org.mo.com.xml.IXmlObject;
import org.mo.core.aop.face.ALink;
import org.mo.game.editor.core.enums.IEnumConsole;
import org.mo.game.editor.core.enums.common.XConstant;
import org.mo.game.editor.core.enums.common.XEnum;
import org.mo.game.editor.core.enums.common.XEnumGroup;
import org.mo.game.editor.core.enums.common.XEnumItem;
import org.mo.jfa.common.xmlobjects.FAbsXmlObjectService;
import org.mo.web.core.webtree.common.XTreeNode;
import org.mo.web.protocol.context.IWebContext;
import org.mo.web.protocol.context.IWebInput;
import org.mo.web.protocol.context.IWebOutput;

//============================================================
// <T>枚举服务接口。</T>
//============================================================
public class FEnumService
      extends FAbsXmlObjectService<XEnumGroup>
      implements
         IEnumService
{
   // 枚举控制台
   @ALink
   protected IEnumConsole _enumConsole;

   //============================================================
   // <T>格式化集合名称。</T>
   //
   // @param name 名称
   // @return 名称
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
   // <T>建立树目录节点。</T>
   //
   // @param xnode 节点
   // @param xobject 对象
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
   // <T>列出目录内容。</T>
   //
   // @param context 页面环境
   // @param input 输入配置
   // @param output 输出配置
   //============================================================
   @Override
   public void catalog(IWebContext context,
                       IWebInput input,
                       IWebOutput output){
      catalog(_enumConsole, context, input, output);
   }

   //============================================================
   // <T>列出节点内容。</T>
   //
   // @param context 页面环境
   // @param input 输入配置
   // @param output 输出配置
   //============================================================
   @Override
   public void list(IWebContext context,
                    IWebInput input,
                    IWebOutput output){
      list(_enumConsole, context, input, output);
   }

   //============================================================
   // <T>搜索节点内容。</T>
   //
   // @param context 页面环境
   // @param input 输入配置
   // @param output 输出配置
   //============================================================
   @Override
   public void search(IWebContext context,
                      IWebInput input,
                      IWebOutput output){
      // 获得选中的内容
      FXmlNode config = input.config().findNode("Attributes");
      if(null == config){
         throw new FFatalError("Search node is null.");
      }
      String search = RString.toNvlLower(config.get("search"));
      String[] serachs = RString.split(search, ' ');
      serachs = RStrings.filterNotBlank(serachs);
      // 获得容器节点
      for(XEnumGroup xgroup : _enumConsole.list()){
         for(IXmlObject xobject : xgroup.children()){
            if(XEnum.isInstance(xobject)){
               XEnum enums = (XEnum)xobject;
               String name = enums.getName();
               // 过滤节点
               boolean searched = true;
               for(String searchItem : serachs){
                  if(!name.toLowerCase().contains(searchItem)){
                     searched = false;
                     break;
                  }
               }
               if(!searched){
                  continue;
               }
               // 新建树节点
               XTreeNode xnode = new XTreeNode();
               xnode.setType(enums.name());
               xnode.setUuid(enums.objectId());
               xnode.setLabel(name);
               xnode.setNote(enums.innerGet(PTY_LABEL));
               xnode.setChild(enums.hasChild());
               xnode.set("linker_name", xgroup.getName());
               onBuildTreeNode(xnode, enums);
               // 生成树节点
               FXmlNode treeNode = xnode.toSimpleNode();
               treeNode.set("is_valid", enums.innerGet("is_valid"));
               output.config().push(treeNode);
            }
         }
      }
   }

   //============================================================
   // <T>新建节点。</T>
   //
   // @param context 页面环境
   // @param input 输入配置
   // @param output 输出配置
   //============================================================
   @Override
   public void insert(IWebContext context,
                      IWebInput input,
                      IWebOutput output){
      insert(_enumConsole, context, input, output);
   }

   //============================================================
   // <T>更新节点。</T>
   //
   // @param context 页面环境
   // @param input 输入配置
   // @param output 输出配置
   //============================================================
   @Override
   public void update(IWebContext context,
                      IWebInput input,
                      IWebOutput output){
      update(_enumConsole, context, input, output);
   }

   //============================================================
   // <T>删除节点。</T>
   //
   // @param context 页面环境
   // @param input 输入配置
   // @param output 输出配置
   //============================================================
   @Override
   public void delete(IWebContext context,
                      IWebInput input,
                      IWebOutput output){
      delete(_enumConsole, context, input, output);
   }

   //============================================================
   // <T>排序节点。</T>
   //
   // @param context 页面环境
   // @param input 输入配置
   // @param output 输出配置
   //============================================================
   @Override
   public void sort(IWebContext context,
                    IWebInput input,
                    IWebOutput output){
      sort(_enumConsole, context, input, output);
   }
}
