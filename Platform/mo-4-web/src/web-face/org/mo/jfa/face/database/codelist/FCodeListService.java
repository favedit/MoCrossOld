package org.mo.jfa.face.database.codelist;

import org.mo.com.lang.RString;
import org.mo.com.lang.RStrings;
import org.mo.com.xml.FXmlNode;
import org.mo.com.xml.IXmlObject;
import org.mo.core.aop.face.ALink;
import org.mo.data.codelist.ICodeListConsole;
import org.mo.jfa.common.xmlobjects.FAbsXmlObjectService;
import org.mo.web.core.webtree.common.XTreeNode;
import org.mo.web.protocol.context.IWebContext;
import org.mo.web.protocol.context.IWebInput;
import org.mo.web.protocol.context.IWebOutput;

public class FCodeListService
      extends FAbsXmlObjectService<IXmlObject>
      implements
         ICodeListService
{

   @ALink
   protected ICodeListConsole _codelistConsole;

   @Override
   public void catalog(IWebContext context,
                       IWebInput input,
                       IWebOutput output){
      catalog(_codelistConsole, context, input, output);
   }

   @Override
   public void delete(IWebContext context,
                      IWebInput input,
                      IWebOutput output){
      delete(_codelistConsole, context, input, output);
   }

   @Override
   public void insert(IWebContext context,
                      IWebInput input,
                      IWebOutput output){
      insert(_codelistConsole, context, input, output);
   }

   @Override
   public void list(IWebContext context,
                    IWebInput input,
                    IWebOutput output){
      list(_codelistConsole, context, input, output);
   }

   @Override
   public void search(IWebContext context,
                      IWebInput input,
                      IWebOutput output){
      // 获得选中的内容
      FXmlNode config = input.config().findNode("Attributes");
      String search = config.get("search").toLowerCase();
      String[] serachs = RString.split(search, ' ');
      serachs = RStrings.filterNotBlank(serachs);
      // 获得容器节点
      for(IXmlObject xcollection : _codelistConsole.list()){
         String name = xcollection.innerGet(PTY_NAME);
         String type = xcollection.name();
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
         xnode.setType(type);
         xnode.setUuid(xcollection.objectId());
         xnode.setLabel(name);
         xnode.setNote(xcollection.innerGet(PTY_LABEL));
         xnode.setChild(xcollection.hasChild());
         xnode.set("dataset_name", name);
         // 生成树节点
         FXmlNode treeNode = xnode.toSimpleNode();
         treeNode.set("is_valid", xcollection.innerGet("is_valid"));
         output.config().push(treeNode);
      }
   }

   @Override
   public void sort(IWebContext context,
                    IWebInput input,
                    IWebOutput output){
      sort(_codelistConsole, context, input, output);
   }

   @Override
   public void update(IWebContext context,
                      IWebInput input,
                      IWebOutput output){
      update(_codelistConsole, context, input, output);
   }
}
