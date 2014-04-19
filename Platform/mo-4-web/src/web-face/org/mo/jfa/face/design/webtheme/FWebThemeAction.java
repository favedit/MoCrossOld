package org.mo.jfa.face.design.webtheme;

import org.mo.com.io.FStringFile;
import org.mo.com.io.RFile;
import org.mo.com.lang.FAttributes;
import org.mo.com.lang.FFatalError;
import org.mo.com.lang.IStringPair;
import org.mo.com.lang.RBoolean;
import org.mo.com.lang.RString;
import org.mo.com.lang.cultrue.REncoding;
import org.mo.com.xml.FXmlNode;
import org.mo.com.xml.FXmlNodes;
import org.mo.com.xml.IXmlObject;
import org.mo.com.xml.IXmlObjects;
import org.mo.core.aop.face.ALink;
import org.mo.eng.environment.IEnvironmentConsole;
import org.mo.eng.template.FTemplateParser;
import org.mo.jfa.common.xmlobjects.FAbsXmlObjectAction;
import org.mo.jfa.face.apl.page.IPublicPage;
import org.mo.web.core.format.ESyntax;
import org.mo.web.core.format.IFormatConsole;
import org.mo.web.core.format.ISyntaxMaker;
import org.mo.web.core.webtheme.IWebThemeConsole;
import org.mo.web.core.webtheme.common.XWebTheme;
import org.mo.web.protocol.context.IWebContext;

public class FWebThemeAction
      extends FAbsXmlObjectAction<XWebTheme>
      implements
         IWebThemeAction
{

   public final String PAGE_CATALOG = "Catalog";

   public final String PAGE_LIST = "List";

   public final String PAGE_TOOLBAR = "ToolBar";

   public final static String PAGE_SOURCE = "Source";

   @ALink
   protected IFormatConsole _formatConsole;

   @ALink
   protected IWebThemeConsole _themeConsole;

   @ALink
   protected IEnvironmentConsole _envConsole;

   public void addFilder(IXmlObject partXml,
                         FXmlNode partNode){
      if(partXml.hasChild()){
         String attributes = "";
         for(IXmlObject filterXml : partXml.children()){
            if(!"Y".equals(filterXml.innerGet("is_valid"))){
               continue;
            }
            FXmlNode filterNode = filterXml.toSimpleNode();
            String filterName = filterNode.name();
            filterName = RString.right(filterName, "Filter");
            filterName = "progid:DXImageTransform.Microsoft." + filterName + "(";
            String partAttribute = "";
            // partNode.set("filterName", filterName);
            for(IStringPair pair : filterNode.attributes()){
               String attributeName = pair.name();
               if("name".equals(attributeName) || "label".equals(attributeName) || "is_valid".equals(attributeName)){
                  continue;
               }
               String value = pair.value();
               if(!RString.isBlank(value)){
                  partAttribute = partAttribute + attributeName + "=" + value + ",";
               }
            }
            if(partAttribute.length() > 0){
               partAttribute = partAttribute.substring(0, partAttribute.length() - 1);
            }
            partAttribute = partAttribute + ")";
            attributes = attributes + " " + filterName + partAttribute;
         }
         FXmlNode filterAttribute = new FXmlNode("filter");
         filterAttribute.set("filters", attributes);
         filterAttribute.set("type", "filter");
         partNode.push(filterAttribute);
      }

   }

   /**
    * <p>
    * 编译功能，产生样式表代码
    * </p>
    * 
    * @param context
    *            与页面相连
    * @param page
    *            page
    * @return 页面名称
    */
   public String build(IWebContext context,
                       FWebThemePage page){
      page.setSource(null);
      String collection = context.parameter(PTY_SEL_COLLECTION);
      IXmlObject xcollection = _themeConsole.get(collection);
      if(RBoolean.parse(xcollection.innerGet("is_valid"))){
         if(xcollection.hasChild()){
            FXmlNode webThemeNode = new FXmlNode("WebTheme");
            if(RString.contains(collection, ".")){
               collection = RString.replaceChars(collection, ".", "_");
            }
            String filePath = xcollection.toSimpleNode().get("file_Path");
            filePath = _envConsole.parse(filePath);
            webThemeNode.set("name", collection);
            webThemeNode.set("type", xcollection.name());
            webThemeNode.set("filePath", filePath);
            String type = context.parameter(PTY_SEL_TYPE);
            if(TYPE_COLLECTION.equals(type)){
               IXmlObjects webthemes = xcollection.children();
               for(int i = 0; i < webthemes.count(); ++i){
                  IXmlObject theme = webthemes.get(i);
                  if("Y".equals(theme.innerGet("is_valid"))){
                     webThemeNode.push(makeXmlNode(theme));
                  }
               }
               page.setFXmlNode(webThemeNode);
               execute(context, page);
               return IPublicPage.PROCESS_SUCCESS;
            }else if(TYPE_COMPONENT.equals(type)){
               String component = context.parameter(PTY_SEL_COMPONENT);
               IXmlObject xcomponent = xcollection.search(component);
               if(null == xcomponent){
                  throw new FFatalError("Xml component is not found. (collection={0},component={1})", collection, component);
               }
               webThemeNode.push(makeXmlNode(xcomponent));
            }
            FXmlNode root = _templateConsole.findTemplate("webtheme", "css.style");
            FTemplateParser parser = new FTemplateParser(root);
            FXmlNodes packageNodes = webThemeNode.nodes();
            for(int i = 0; i < packageNodes.count(); ++i){
               FXmlNode packageNode = packageNodes.get(i);
               parser.define("package", packageNode);
            }
            // Build
            String lines = parser.parse().toString();
            String[] line = lines.split("\n");
            line = RString.trimRight(line);
            FStringFile file = new FStringFile();
            for(int i = 0; i < line.length; ++i){
               file.append(line[i]);
               file.append("\n");
            }
            ISyntaxMaker syntaxMaker = _formatConsole.find(ESyntax.WebStyle);
            page.setSource(syntaxMaker.format(file));
            page.setFXmlNode(webThemeNode);
         }

      }

      return PAGE_SOURCE;
   }

   @Override
   public String catalog(IWebContext context,
                         FWebThemePage page){
      return PAGE_CATALOG;
   }

   @Override
   public String copyStyle(IWebContext context,
                           FWebThemePage page){
      String collection = context.parameter(PTY_SEL_COLLECTION);
      IXmlObject xcollection = _themeConsole.get(collection);
      if(XWebTheme.isInstance(xcollection)){
         XWebTheme xtheme = (XWebTheme)xcollection;
         if(xtheme.getIsValid()){
            XWebTheme xparentTheme = _themeConsole.get(xtheme.getParentStyle());
            _themeConsole.copy(xparentTheme, xtheme);
            _themeConsole.store(xtheme);
         }
      }
      return IPublicPage.PROCESS_SUCCESS;
   }

   @Override
   public String delete(IWebContext context,
                        FWebThemePage page){
      return delete(_themeConsole, context, page, IPublicPage.XOBJECT_FORM);
   }

   public String execute(IWebContext context,
                         FWebThemePage page){
      FXmlNode webThemeNode = page.getFXmlNode();
      FXmlNodes packageNodes = webThemeNode.nodes();
      for(int i = 0; i < packageNodes.count(); i++){
         FXmlNode packageNode = packageNodes.get(i);
         FXmlNode root = _templateConsole.findTemplate("webtheme", "css.style");
         FTemplateParser parser = new FTemplateParser(root);
         parser.define("package", packageNode);
         // Build
         String lines = parser.parse().toString();
         String[] line = lines.split("\n");
         line = RString.trimRight(line);
         FStringFile file = new FStringFile();
         for(int j = 0; j < line.length; j++){
            file.append(line[j]);
            file.append("\n");
         }
         page.setSource(file);
         String fileName = packageNode.get("fileName");
         // 判断例外
         if(!RString.contains(fileName, ".css") || fileName == null){
            throw new FFatalError("File Name is error！！！. (collection={0},component={1})", webThemeNode, packageNode);
         }
         String filePath = webThemeNode.get("filePath") + "/";
         if(RString.contains(filePath, ".")){
            filePath = RString.replaceChars(filePath, ".", "/");
         }
         if(RString.contains(filePath, "\\")){
            filePath = RString.replaceChars(filePath, "\\", "/");
         }
         if(filePath == null){
            throw new FFatalError("File Path is error！！！. (collection={0})", webThemeNode);
         }
         filePath = filePath + fileName;
         RFile.saveToFile(filePath, file, REncoding.GBK);
      }

      return IPublicPage.PROCESS_SUCCESS;
   }

   @Override
   public String insert(IWebContext context,
                        FWebThemePage page){
      return insert(_themeConsole, context, page, IPublicPage.XOBJECT_FORM);
   }

   @Override
   public String list(IWebContext context,
                      FWebThemePage page){
      return list(_themeConsole, context, page, IPublicPage.XOBJECT_FORM);
   }

   protected FXmlNode makeXmlNode(IXmlObject xmlPackage){
      FXmlNode packageNode = new FXmlNode("package");
      String packageName = xmlPackage.toSimpleNode().get("name");
      if(RString.contains(packageName, ".")){
         packageName = RString.replaceChars(packageName, ".", "_");
      }
      packageNode.set("name", packageName);
      if(xmlPackage.toSimpleNode().get("file_name") != null){
         String fileName = xmlPackage.toSimpleNode().get("file_name");
         packageNode.set("fileName", fileName);
      }

      packageNode.set("type", xmlPackage.name());
      if(xmlPackage.hasChild()){
         IXmlObjects controls = xmlPackage.children();
         for(int i = 0; i < controls.count(); ++i){
            IXmlObject child = controls.get(i);
            if(!"Y".equals(child.innerGet("is_valid"))){
               continue;
            }
            FXmlNode controlNode = new FXmlNode("control");
            controlNode.set("name", child.toSimpleNode().get("name"));
            controlNode.set("type", child.name());
            IXmlObjects attributes = child.children();
            for(int j = 0; j < attributes.count(); ++j){
               String[] paddingName = new String[5];
               paddingName[0] = "padding";
               String[] paddingValue = new String[5];
               boolean isPadding = false;
               int paddingCount = 0;
               String[] marginName = new String[5];
               marginName[0] = "margin";
               String[] marginValue = new String[5];
               boolean isMargin = false;
               int marginCount = 0;
               IXmlObject part = attributes.get(j);
               if(!"Y".equals(part.innerGet("is_valid"))){
                  continue;
               }
               FXmlNode partNode = new FXmlNode("part");
               FXmlNode xmlPart = part.toSimpleNode();
               partNode.set("name", xmlPart.get("name"));
               partNode.set("type", xmlPart.name());
               FAttributes xmlAttributes = xmlPart.attributes();
               for(int n = 0; n < xmlAttributes.count(); ++n){
                  String name = xmlAttributes.name(n);
                  if(RString.contains(name, "_")){
                     name = RString.replaceChars(name, "_", "-");
                  }
                  String value = xmlAttributes.value(n);
                  if(!RString.isBlank(value)){
                     if("border-width".equals(name) || "border-style".equals(name) || "border-color".equals(name) || "border-top-width".equals(name) || "border-top-style".equals(name) || "border-top-color".equals(name) || "border-left-width".equals(name)
                           || "border-left-style".equals(name) || "border-left-color".equals(name) || "border-right-width".equals(name) || "border-right-style".equals(name) || "border-right-color".equals(name) || "border-bottom-width".equals(name)
                           || "border-bottom-style".equals(name) || "border-bottom-color".equals(name) || "name".equals(name) || "label".equals(name) || "is-valid".equals(name) || "note".equals(name)){
                        continue;
                     }
                     if(RString.startsWith(name, "padding-")){
                        paddingCount++;
                        paddingName[paddingCount] = name;
                        paddingValue[paddingCount] = value;
                        isPadding = true;
                        if(n + 1 < xmlAttributes.count()){
                           continue;
                        }
                     }
                     System.out.println("padding_count======" + controlNode.get("name") + "   " + paddingCount + isPadding);
                     if(isPadding){
                        System.out.println("padding_count======" + paddingCount);
                        if(paddingCount >= 4){
                           if(paddingValue[1].equals(paddingValue[2]) && paddingValue[2].equals(paddingValue[3]) && paddingValue[3].equals(paddingValue[4])){
                              FXmlNode attributeNode = new FXmlNode("attribute");
                              attributeNode.set("type", "attribute");
                              attributeNode.set("name", paddingName[0]);
                              attributeNode.set("value", paddingValue[1]);
                              partNode.push(attributeNode);
                           }else{
                              for(int m = 1; m <= paddingCount; m++){
                                 FXmlNode attributeNode = new FXmlNode("attribute");
                                 attributeNode.set("type", "attribute");
                                 attributeNode.set("name", paddingName[m]);
                                 attributeNode.set("value", paddingValue[m]);
                                 partNode.push(attributeNode);
                              }
                           }
                        }else{
                           for(int m = 1; m <= paddingCount; m++){
                              FXmlNode attributeNode = new FXmlNode("attribute");
                              attributeNode.set("type", "attribute");
                              attributeNode.set("name", paddingName[m]);
                              attributeNode.set("value", paddingValue[m]);
                              partNode.push(attributeNode);
                           }

                        }
                        paddingCount = 0;
                        isPadding = false;
                     }

                     if(RString.startsWith(name, "margin-")){
                        marginCount++;
                        marginName[marginCount] = name;
                        marginValue[marginCount] = value;
                        isMargin = true;
                        if(n + 1 < xmlAttributes.count()){
                           continue;
                        }
                     }
                     if(isMargin){
                        if(marginCount >= 4){
                           if(marginValue[1].equals(marginValue[2]) && marginValue[2].equals(marginValue[3]) && marginValue[3].equals(marginValue[4])){
                              FXmlNode attributeNode = new FXmlNode("attribute");
                              attributeNode.set("type", "attribute");
                              attributeNode.set("name", marginName[0]);
                              attributeNode.set("value", marginValue[1]);
                              partNode.push(attributeNode);
                           }else{
                              for(int m = 1; m <= marginCount; m++){
                                 FXmlNode attributeNode = new FXmlNode("attribute");
                                 attributeNode.set("type", "attribute");
                                 attributeNode.set("name", marginName[m]);
                                 attributeNode.set("value", marginValue[m]);
                                 partNode.push(attributeNode);
                              }
                           }
                        }else{
                           for(int m = 1; m <= marginCount; m++){
                              FXmlNode attributeNode = new FXmlNode("attribute");
                              attributeNode.set("type", "attribute");
                              attributeNode.set("name", marginName[m]);
                              attributeNode.set("value", marginValue[m]);
                              partNode.push(attributeNode);
                           }

                        }
                        marginCount = 0;
                        isMargin = false;
                     }
                     if((!RString.startsWith(name, "padding-")) && (!RString.startsWith(name, "margin-"))){
                        FXmlNode attributeNode = new FXmlNode("attribute");
                        attributeNode.set("type", "attribute");
                        attributeNode.set("name", name);
                        attributeNode.set("value", value);
                        partNode.push(attributeNode);

                     }

                  }
               }
               addFilder(part, partNode);
               controlNode.push(partNode);
            }
            packageNode.push(controlNode);
         }
      }
      System.out.println(packageNode.xml());
      return packageNode;
   }

   @Override
   public String sort(IWebContext context,
                      FWebThemePage page){
      return sort(_themeConsole, context, page, IPublicPage.XOBJECT_SORT);
   }

   @Override
   public String update(IWebContext context,
                        FWebThemePage page){
      return update(_themeConsole, context, page, IPublicPage.XOBJECT_FORM);
   }
}
