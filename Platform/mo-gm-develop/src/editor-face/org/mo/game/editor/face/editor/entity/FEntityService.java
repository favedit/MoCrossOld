package org.mo.game.editor.face.editor.entity;

import org.mo.com.lang.FFatalError;
import org.mo.com.lang.RBoolean;
import org.mo.com.lang.RString;
import org.mo.com.lang.RStrings;
import org.mo.com.xml.FXmlNode;
import org.mo.com.xml.IXmlObject;
import org.mo.core.aop.face.ALink;
import org.mo.game.editor.core.entity.IEntityConsole;
import org.mo.game.editor.core.entity.common.XEntity;
import org.mo.game.editor.core.entity.common.XEntityGroup;
import org.mo.game.editor.core.enums.IEnumConsole;
import org.mo.jfa.common.xmlobjects.FAbsXmlObjectService;
import org.mo.web.core.webtree.common.XTreeNode;
import org.mo.web.protocol.context.IWebContext;
import org.mo.web.protocol.context.IWebInput;
import org.mo.web.protocol.context.IWebOutput;

//============================================================
// <T>实体服务。</T>
//============================================================
public class FEntityService
      extends FAbsXmlObjectService<XEntityGroup>
      implements
         IEntityService
{
   // 枚举控制台
   @ALink
   protected IEnumConsole _enumConsole;

   // 实体控制台
   @ALink
   protected IEntityConsole _entityConsole;

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
      }else if("Entity".equals(xobject.name())){
         // 获得类型
         String value = xobject.innerGet("name");
         // 设置标志
         String flag = "";
         flag += RBoolean.parse(xobject.innerGet(XEntity.PTY_IS_CLASS)) ? "H" : "_";
         flag += ":";
         flag += RBoolean.parse(xobject.innerGet(XEntity.PTY_IS_SERVER_CPP)) ? "C" : "_";
         flag += RBoolean.parse(xobject.innerGet(XEntity.PTY_IS_SERVER_JAVA)) ? "J" : "_";
         flag += ":";
         flag += RBoolean.parse(xobject.innerGet(XEntity.PTY_IS_CLIENT_CPP)) ? "C" : "_";
         flag += RBoolean.parse(xobject.innerGet(XEntity.PTY_IS_CLIENT_JAVA)) ? "J" : "_";
         flag += RBoolean.parse(xobject.innerGet(XEntity.PTY_IS_CLIENT_AS)) ? "A" : "_";
         flag += RBoolean.parse(xobject.innerGet(XEntity.PTY_IS_CLIENT_CS)) ? "S" : "_";
         // 设置显示内容
         String display = "<FONT face='NSimSun' color='gray'>[" + flag + "]</FONT> " + value;
         xnode.setLabel(display);
      }else if("Item".equals(xobject.name())){
         // 获得类型
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
         // 设置标志
         String flag = "";
         flag += RBoolean.parse(xobject.innerGet("is_persistence")) ? "P" : "_";
         flag += RBoolean.parse(xobject.innerGet("is_system")) ? "T" : "_";
         flag += RBoolean.parse(xobject.innerGet("is_lock")) ? "L" : "_";
         flag += RBoolean.parse(xobject.innerGet("is_get")) ? "G" : "_";
         flag += RBoolean.parse(xobject.innerGet("is_set")) ? "S" : "_";
         // 设置编号
         String propertyId = RString.leftPad(xobject.innerGet("property_id"), 3, '0');
         // 设置显示内容
         String display = "<FONT face='NSimSun' color='#000000'>" + propertyId + "</FONT> <FONT face='NSimSun' color='gray'>[" + flag + "]</FONT> " + value;
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
      // 获得列表
      catalog(_entityConsole, context, input, output);
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
      list(_entityConsole, context, input, output);
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
      for(XEntityGroup xgroup : _entityConsole.list()){
         for(IXmlObject xobject : xgroup.children()){
            if(XEntity.isInstance(xobject)){
               XEntity entity = (XEntity)xobject;
               String name = entity.getName();
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
               xnode.setType(entity.name());
               xnode.setUuid(entity.objectId());
               xnode.setLabel(name);
               xnode.setNote(entity.innerGet(PTY_LABEL));
               xnode.setChild(entity.hasChild());
               xnode.set("linker_name", xgroup.getName());
               onBuildTreeNode(xnode, entity);
               // 生成树节点
               FXmlNode treeNode = xnode.toSimpleNode();
               treeNode.set("is_valid", entity.innerGet("is_valid"));
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
      insert(_entityConsole, context, input, output);
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
      update(_entityConsole, context, input, output);
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
      delete(_entityConsole, context, input, output);
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
      sort(_entityConsole, context, input, output);
   }
}
