/*
 * @(#)FEventAction.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.jfa.face.system.webtag;

import org.mo.com.io.FStringFile;
import org.mo.com.io.RFile;
import org.mo.com.lang.FFatalError;
import org.mo.com.lang.RString;
import org.mo.com.lang.cultrue.REncoding;
import org.mo.com.xml.EXmlConfig;
import org.mo.com.xml.FXmlNode;
import org.mo.com.xml.FXmlNodes;
import org.mo.com.xml.IXmlObject;
import org.mo.core.aop.face.ALink;
import org.mo.eng.template.FTemplateParser;
import org.mo.jfa.common.xmlobjects.FAbsXmlObjectAction;
import org.mo.jfa.core.webtag.IWebTagConsole;
import org.mo.jfa.face.apl.page.IPublicPage;
import org.mo.web.core.format.ESyntax;
import org.mo.web.core.format.IFormatConsole;
import org.mo.web.core.format.ISyntaxMaker;
import org.mo.web.protocol.context.IWebContext;

/**
 * <T>业务事件树目录各种操作动作接口实体类。</T>
 * <P>提供对业务事件和业务事件用树目录形式进行增删改和同步等各种操作的接口。</P>
 * 
 * @author YJHUA
 * @version 1.00 - 2008/12/08
 */
public class FWebTagAction
      extends FAbsXmlObjectAction<IXmlObject>
      implements
         IWebTagAction
{

   @ALink
   protected IWebTagConsole _webTagConsole;

   public final static String PAGE_CATALOG = "Catalog";

   public final static String PAGE_SOURCE = "Source";

   public final static String JAVA_TYPE = "JAVA";

   public final static String TLD_TYPE = "TLD";

   public final static String PATH = "webtag";

   public final static String FILEPATH = "D:/Workspace/eUIS/webroot/WEB-INF/tag/";

   public final static String JAVAFILEPATH = "D:/Workspace/eUIS/src/web-java";

   @ALink
   protected IFormatConsole _formatConsole;

   @Override
   public String bulid(IWebContext context,
                       FWebTagPage page){
      String tagLibName = page.getSelectCollection();
      IXmlObject xds = _webTagConsole.find(tagLibName);
      if("Y".equals(xds.innerGet("is_valid"))){
         if(null == xds.innerGet("short_name")){
            throw new FFatalError("TagLib short name is null. (name={0},class_name={1})", xds.innerGet("name"), xds.innerGet("short_name"));
         }
         String bulidType = context.parameter("bulidType");
         if(JAVA_TYPE.equals(bulidType)){
            String type = context.parameter(PTY_SEL_TYPE);
            page.setBulidType(JAVA_TYPE);
            if(TYPE_COLLECTION.equals(type)){
               bulidTagLib(xds);
               return IPublicPage.PROCESS_SUCCESS;
            }else{
               String component = context.parameter(PTY_SEL_COMPONENT);
               IXmlObject xcomponent = xds.search(component);
               bulidTag(xds, xcomponent, page);
            }
         }else{
            page.setBulidType(TLD_TYPE);
            FXmlNode nodeTagLib = xds.toNode(EXmlConfig.Simple);
            FXmlNode root = _templateConsole.findTemplate(PATH, "tld");
            FTemplateParser parser = new FTemplateParser(root);
            for(IXmlObject xdsTag : xds.children()){
               if(!("tagFace".equals(xdsTag.name()))){
                  FXmlNode nodeTag = xdsTag.toNode(EXmlConfig.Simple);
                  if(null == nodeTag.get("class_name")){
                     throw new FFatalError("Tag class name is null. (name={0},class_name={1})", nodeTag.get("name"), nodeTag.get("class_name"));
                  }
                  for(IXmlObject xdsAttributes : xdsTag.children()){
                     FXmlNode nodeAttribute = xdsAttributes.toNode(EXmlConfig.Simple);
                     nodeTag.push(nodeAttribute);
                  }
                  nodeTag.pushNodes(getTagFace(xds, nodeTag.get("tag_face")));
                  nodeTagLib.push(nodeTag);
               }
            }
            page.setNode(nodeTagLib);
            // Build
            parser.define("taglib", nodeTagLib);
            String lines = parser.parse().toString();
            String[] line = lines.split("\n");
            line = RString.trimRight(line);
            FStringFile file = new FStringFile();
            file.clear();
            for(int i = 0; i < line.length; ++i){
               file.append(line[i]);
               file.append("\n");
            }
            ISyntaxMaker syntaxMaker = _formatConsole.find(ESyntax.Tag);
            page.setSource(null);
            page.setSource(syntaxMaker.format(file));
         }
      }
      return PAGE_SOURCE;
   }

   protected void bulidTag(IXmlObject xtagLab,
                           IXmlObject xds,
                           FWebTagPage page){
      if("N".equals(xds.innerGet("is_valid"))){
         FStringFile file = new FStringFile();
         file.append("该节点不是有效的！");
         page.setSource(file);
         return;
      }
      if(!("tagFace".equals(xds.name()))){
         FXmlNode root = _templateConsole.findTemplate(PATH, "java");
         FTemplateParser parser = new FTemplateParser(root);
         FXmlNode nodeTag = xds.toNode(EXmlConfig.Simple);
         for(IXmlObject xdsAttributes : xds.children()){
            if("N".equals(xdsAttributes.innerGet("is_valid"))){
               continue;
            }
            FXmlNode nodeAttribute = xdsAttributes.toNode(EXmlConfig.Simple);
            nodeTag.push(nodeAttribute);
         }
         nodeTag.pushNodes(getTagFace(xtagLab, nodeTag.get("tag_face")));
         String classNameAll = RString.replace(nodeTag.get("base_name"), '.', '/');
         String classPath[] = RString.split(classNameAll, "/");
         String className = classPath[classPath.length - 1];
         String pack = RString.left(nodeTag.get("base_name"), className);
         nodeTag.set("class", className);
         nodeTag.set("pack", pack.substring(0, pack.length() - 1));
         page.setNode(nodeTag);
         // Build
         parser.define("tag", nodeTag);
         parser.define("attributes", nodeTag);
         String lines = parser.parse().toString();
         String[] line = lines.split("\n");
         line = RString.trimRight(line);
         FStringFile file = new FStringFile();
         file.clear();
         for(int i = 0; i < line.length; ++i){
            file.append(line[i]);
            file.append("\n");
         }
         ISyntaxMaker syntaxMaker = _formatConsole.find(ESyntax.Java);
         page.setSource(null);
         page.setSource(syntaxMaker.format(file));
      }
   }

   protected void bulidTagLib(IXmlObject xds){
      FXmlNode root = _templateConsole.findTemplate(PATH, "java");
      for(IXmlObject xdsTag : xds.children()){
         if("N".equals(xdsTag.innerGet("is_valid"))){
            continue;
         }
         if(!("tagFace".equals(xdsTag.name()))){
            FTemplateParser parser = new FTemplateParser(root);
            FXmlNode nodeTag = xdsTag.toNode(EXmlConfig.Simple);
            if(null == nodeTag.get("class_name")){
               throw new FFatalError("Tag class name is null. (name={0},class_name={1})", nodeTag.get("name"), nodeTag.get("class_name"));
            }
            for(IXmlObject xdsAttributes : xdsTag.children()){
               if("N".equals(xdsAttributes.innerGet("is_valid"))){
                  continue;
               }
               FXmlNode nodeAttribute = xdsAttributes.toNode(EXmlConfig.Simple);
               nodeTag.push(nodeAttribute);
            }
            nodeTag.pushNodes(getTagFace(xds, nodeTag.get("tag_face")));
            String classNameAll = RString.replace(nodeTag.get("base_name"), '.', '/');
            String classPath[] = RString.split(classNameAll, "/");
            String className = classPath[classPath.length - 1];
            String pack = RString.left(nodeTag.get("base_name"), className);
            nodeTag.set("class", className);
            nodeTag.set("pack", pack.substring(0, pack.length() - 1));
            // Build
            parser.define("tag", nodeTag);
            parser.define("attributes", nodeTag);
            String lines = parser.parse().toString();
            String[] line = lines.split("\n");
            RString.trimRight(line);
            FStringFile file = new FStringFile();
            for(int i = 0; i < line.length; ++i){
               file.append(line[i]);
               file.append("\n");
            }
            RFile.saveToFile(JAVAFILEPATH + "/" + classNameAll + ".java", file, REncoding.UTF8);
            file.clear();
         }
      }
   }

   /* (non-Javadoc)
    * @see org.mo.jfa.face.logic.event.IEventAction#catalog(org.mo.web.protocol.context.IWebContext, org.mo.jfa.face.logic.event.FEventPage)
    */
   @Override
   public String catalog(IWebContext context,
                         FWebTagPage page){
      return catalog(_webTagConsole, context, page, PAGE_CATALOG);
   }

   /* (non-Javadoc)
    * @see org.mo.jfa.face.logic.event.IEventAction#delete(org.mo.web.protocol.context.IWebContext, org.mo.jfa.face.logic.event.FEventPage)
    */
   @Override
   public String delete(IWebContext context,
                        FWebTagPage page){
      return delete(_webTagConsole, context, page, IPublicPage.XOBJECT_FORM);
   }

   public String execute(IWebContext context,
                         FWebTagPage page){
      String bulidType = page.getBulidType();
      FXmlNode node = page.getNode();
      if(JAVA_TYPE.equals(bulidType)){
         FXmlNode root = _templateConsole.findTemplate(PATH, "java");
         FTemplateParser parser = new FTemplateParser(root);
         parser.define("tag", node);
         parser.define("attributes", node);
         String lines = parser.parse().toString();
         String[] line = lines.split("\n");
         line = RString.trimRight(line);
         FStringFile file = new FStringFile();
         file.clear();
         for(int i = 0; i < line.length; ++i){
            file.append(line[i]);
            file.append("\n");
         }
         RFile.saveToFile(JAVAFILEPATH + "/" + RString.replaceChars(node.get("base_name"), ".", "/") + ".java", file, REncoding.UTF8);
      }else{
         FXmlNode root = _templateConsole.findTemplate(PATH, "tld");
         FTemplateParser parser = new FTemplateParser(root);
         // Build
         parser.define("taglib", node);
         String lines = parser.parse().toString();
         String[] line = lines.split("\n");
         line = RString.trimRight(line);
         FStringFile file = new FStringFile();
         file.clear();
         for(int i = 0; i < line.length; ++i){
            file.append(line[i]);
            file.append("\n");
         }

         RFile.saveToFile(FILEPATH + node.get("name") + ".tld", file, REncoding.UTF8);
      }
      return IPublicPage.PROCESS_SUCCESS;
   }

   protected FXmlNodes getTagFace(IXmlObject tagLabNode,
                                  String tagFace){
      FXmlNodes nodes = new FXmlNodes();
      if(null != tagFace){
         for(IXmlObject xds : tagLabNode.children()){
            if(tagFace.equals(xds.innerGet("name"))){
               for(IXmlObject xattribute : xds.children()){
                  nodes.push(xattribute.toNode(EXmlConfig.Simple));
               }
               return nodes;
            }
         }
      }
      return null;
   }

   /* (non-Javadoc)
    * @see org.mo.jfa.face.logic.event.IEventAction#insert(org.mo.web.protocol.context.IWebContext, org.mo.jfa.face.logic.event.FEventPage)
    */
   @Override
   public String insert(IWebContext context,
                        FWebTagPage page){
      return insert(_webTagConsole, context, page, IPublicPage.XOBJECT_FORM);
   }

   /* (non-Javadoc)
    * @see org.mo.jfa.face.logic.event.IEventAction#list(org.mo.web.protocol.context.IWebContext, org.mo.jfa.face.logic.event.FEventPage)
    */
   @Override
   public String list(IWebContext context,
                      FWebTagPage page){
      return list(_webTagConsole, context, page, IPublicPage.XOBJECT_FORM);
   }

   /* (non-Javadoc)
    * @see org.mo.jfa.face.logic.event.IEventAction#sort(org.mo.web.protocol.context.IWebContext, org.mo.jfa.face.logic.event.FEventPage)
    */
   @Override
   public String sort(IWebContext context,
                      FWebTagPage page){
      return sort(_webTagConsole, context, page, IPublicPage.XOBJECT_SORT);
   }

   /* (non-Javadoc)
    * @see org.mo.jfa.face.logic.event.IEventAction#update(org.mo.web.protocol.context.IWebContext, org.mo.jfa.face.logic.event.FEventPage)
    */
   @Override
   public String update(IWebContext context,
                        FWebTagPage page){
      return update(_webTagConsole, context, page, IPublicPage.XOBJECT_FORM);
   }
}
