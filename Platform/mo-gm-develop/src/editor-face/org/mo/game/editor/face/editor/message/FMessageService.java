package org.mo.game.editor.face.editor.message;

import org.mo.com.lang.FFatalError;
import org.mo.com.lang.RBoolean;
import org.mo.com.lang.RHex;
import org.mo.com.lang.RString;
import org.mo.com.lang.RStrings;
import org.mo.com.xml.FXmlNode;
import org.mo.com.xml.FXmlNodes;
import org.mo.com.xml.IXmlObject;
import org.mo.core.aop.face.ALink;
import org.mo.game.editor.core.enums.IEnumConsole;
import org.mo.game.editor.core.message.IMessageConsole;
import org.mo.game.editor.core.message.common.XMessage;
import org.mo.game.editor.core.message.common.XMessageGroup;
import org.mo.jfa.common.page.RXmlObjects;
import org.mo.jfa.common.service.RServiceResult;
import org.mo.jfa.common.xmlobjects.FAbsXmlObjectService;
import org.mo.jfa.face.apl.page.IPublicPage;
import org.mo.web.core.webtree.common.XTreeNode;
import org.mo.web.protocol.context.IWebContext;
import org.mo.web.protocol.context.IWebInput;
import org.mo.web.protocol.context.IWebOutput;

//============================================================
// <T>消息服务。</T>
//============================================================
public class FMessageService
      extends FAbsXmlObjectService<XMessageGroup>
      implements
         IMessageService
{
   // 枚举控制台
   @ALink
   protected IEnumConsole _enumConsole;

   // 消息控制台
   @ALink
   protected IMessageConsole _messageConsole;

   //============================================================
   // <T>格式化终端名称。</T>
   //
   // @param type 类型
   // @return 终端名称
   //============================================================
   protected String formatTerminal(String type){
      if(!RString.isEmpty(type)){
         return type.substring(0, 2);
      }
      return "";
   }

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
      if("Group".equals(xobject.name())){
         String name = xobject.innerGet("name");
         String displayIndex = xobject.innerGet("display_index");
         if(!RString.isEmpty(displayIndex)){
            displayIndex = "<FONT color='darkred'>" + displayIndex + "</FONT> - ";
         }else{
            displayIndex = "";
         }
         xnode.setLabel(displayIndex + name);
      }else if("Message".equalsIgnoreCase(xobject.name())){
         String name = xobject.innerGet("name");
         String codeValue = xobject.innerGet("code_value");
         if(null != codeValue){
            if(codeValue.startsWith("0x")){
               codeValue = codeValue.substring(2);
            }
         }else{
            codeValue = "";
         }
         String sourceType = formatTerminal(xobject.innerGet("source_type"));
         String targetType = formatTerminal(xobject.innerGet("target_type"));
         String transfer = sourceType + "-" + targetType;
         // 设置标志
         String flag = "";
         flag += RBoolean.parse(xobject.innerGet(XMessage.PTY_IS_SERVER_CPP)) ? "C" : "_";
         flag += RBoolean.parse(xobject.innerGet(XMessage.PTY_IS_SERVER_JAVA)) ? "J" : "_";
         flag += ":";
         flag += RBoolean.parse(xobject.innerGet(XMessage.PTY_IS_CLIENT_CPP)) ? "C" : "_";
         flag += RBoolean.parse(xobject.innerGet(XMessage.PTY_IS_CLIENT_JAVA)) ? "J" : "_";
         flag += RBoolean.parse(xobject.innerGet(XMessage.PTY_IS_CLIENT_AS)) ? "A" : "_";
         flag += RBoolean.parse(xobject.innerGet(XMessage.PTY_IS_CLIENT_CS)) ? "S" : "_";
         flag += ":";
         flag += RBoolean.parse(xobject.innerGet(XMessage.PTY_CODE_FIX)) ? "F" : "_";
         flag += RBoolean.parse(xobject.innerGet(XMessage.PTY_IS_ASYNCHRONOUS)) ? "A" : "_";
         flag += RBoolean.parse(xobject.innerGet(XMessage.PTY_IS_SESSION)) ? "S" : "_";
         flag += RBoolean.parse(xobject.innerGet(XMessage.PTY_IS_MANAGE)) ? "M" : "_";
         // 设置显示内容
         String display = "<FONT face='NSimSun' color='gray'>[";
         display += "<FONT face='NSimSun' color='blue'>" + codeValue + "</FONT> ";
         display += "<FONT face='NSimSun' color='darkred'>" + transfer + "</FONT> ";
         display += flag + "]</FONT> ";
         display += name;
         xnode.setLabel(display);
      }else if("Item".equals(xobject.name())){
         String value = xobject.innerGet("name");
         String typeName = xobject.innerGet("type_name");
         if(RString.isBlank(typeName)){
            typeName = xobject.innerGet("type");
         }
         String datasize = xobject.innerGet("data_size");
         if(!RString.isEmpty(datasize)){
            String ds = _enumConsole.searchConstantValue(datasize);
            if(datasize.equals(ds)){
               datasize = "(<FONT color='gray'>" + datasize + "</FONT>)";
            }else{
               datasize = "(<FONT color='red'>" + ds + "</FONT>)";
            }
         }else{
            datasize = "";
         }
         String display = value;
         String isCollection = xobject.innerGet("is_collection");
         if(RBoolean.parse(isCollection)){
            display += " <FONT face='NSimSun' color='darkred'>[ " + typeName + datasize + " ]</FONT>";
         }else{
            display += " <FONT face='NSimSun' color='gray'>[ " + typeName + datasize + " ]</FONT>";
         }
         xnode.setLabel(display);
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
      catalog(_messageConsole, context, input, output);
      // 设置排序
      output.config().nodes().sortByAttribute("name");
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
      list(_messageConsole, context, input, output);
      FXmlNode selectNode = getSelectNode(input);
      String nodeType = selectNode.get("type");
      if("group".equalsIgnoreCase(nodeType)){
         // 重新排序
         FXmlNodes typeNodes = new FXmlNodes();
         FXmlNodes enumNodes = new FXmlNodes();
         FXmlNodes structNodes = new FXmlNodes();
         FXmlNodes messageNodes = new FXmlNodes();
         FXmlNodes outputNodes = output.config().nodes();
         outputNodes.sortByAttribute(true, "label");
         for(FXmlNode xnode : outputNodes){
            String type = xnode.get("type");
            if("Type".equals(type)){
               typeNodes.push(xnode);
            }else if("Struct".equals(type)){
               structNodes.push(xnode);
            }else if("Enum".equals(type)){
               enumNodes.push(xnode);
            }else if("Message".equals(type)){
               messageNodes.push(xnode);
            }
         }
         outputNodes.clear();
         outputNodes.append(typeNodes);
         outputNodes.append(enumNodes);
         outputNodes.append(structNodes);
         outputNodes.append(messageNodes);
      }
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
      for(XMessageGroup xgroup : _messageConsole.list()){
         for(IXmlObject xobject : xgroup.children()){
            if(XMessage.isInstance(xobject)){
               XMessage message = (XMessage)xobject;
               String name = message.getName();
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
               xnode.setType(message.name());
               xnode.setUuid(message.objectId());
               xnode.setLabel(name);
               xnode.setNote(message.innerGet(PTY_LABEL));
               xnode.setChild(message.hasChild());
               xnode.set("linker_name", xgroup.getName());
               onBuildTreeNode(xnode, message);
               // 生成树节点
               FXmlNode treeNode = xnode.toSimpleNode();
               treeNode.set("is_valid", message.innerGet("is_valid"));
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
      insert(_messageConsole, context, input, output);
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
      update(_messageConsole, context, input, output);
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
      delete(_messageConsole, context, input, output);
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
      // 获得环境对象
      FXmlNode envNode = input.config().findNode("Environment");
      if(null == envNode){
         throw new FFatalError("Environment config is null.");
      }
      // 查找XML集合对象
      String collection = envNode.get(PTY_SEL_COLLECTION);
      XMessageGroup xcollection = _messageConsole.get(collection);
      FXmlNode sortForm = input.config().findNode("FWebForm");
      FXmlNode sortNode = sortForm.findNode("FListBox", "name", NAME_SORT);
      if(null == sortNode){
         throw new FFatalError("Sort config is null.");
      }
      // 根据类型来选择操作
      String type = envNode.get(PTY_SEL_TYPE);
      IXmlObject xsort = null;
      if(TYPE_COLLECTION.equals(type)){
         xsort = xcollection;
      }else if(TYPE_COMPONENT.equals(type)){
         String component = envNode.get(PTY_SEL_COMPONENT);
         IXmlObject xcomponent = xcollection.children().search(component);
         if(null == xcomponent){
            throw new FFatalError("Component is null. (collection={0}, component={1})", collection, component);
         }
         xsort = xcomponent;
      }else{
         throw new FFatalError("Unknown select type. (type={0})", type);
      }
      // 对集合中对象进行重新排序
      if(RXmlObjects.sortUuidByNode(xsort, sortNode, false)){
         int index = 1;
         for(IXmlObject xobject : xsort.children()){
            System.out.println(xobject.toSimpleNode().xml());
            if(XMessage.isInstance(xobject)){
               XMessage xmessage = (XMessage)xobject;
               String codeFix = xmessage.getCodeFix();
               if(RBoolean.parse(codeFix)){
                  String codeValue = xmessage.getCodeValue();
                  if(!RString.isEmpty(codeValue)){
                     if(codeValue.startsWith("0x")){
                        codeValue = codeValue.substring(2);
                     }
                     index = Integer.parseInt(codeValue, 16);
                  }
               }
               String hex = "0x" + RHex.toString(index, 4);
               xmessage.setCodeValue(hex);
               index++;
            }
         }
         _messageConsole.store(xcollection);
      }
      // 刷新树目录节点
      RServiceResult.setTreeRefresh(output);
      RServiceResult.setPageRedirect(output, IPublicPage.PROCESS_SUCCESS);
   }
}
