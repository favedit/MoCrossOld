package org.mo.com.xml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.List;
import javax.swing.text.AbstractDocument.Content;
import org.jdom.Attribute;
import org.jdom.CDATA;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.Text;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.mo.com.io.RFile;
import org.mo.com.lang.FAttributes;
import org.mo.com.lang.FFatalError;
import org.mo.com.lang.FObject;
import org.mo.com.lang.IDump;
import org.mo.com.lang.IInitialize;
import org.mo.com.lang.RString;
import org.mo.com.lang.generic.TDumpInfo;
import org.mo.com.lang.reflect.FClass;
import org.mo.com.lang.reflect.RClass;

//============================================================
// <T>XML文档。</T>
// <p>借助第三方组件，解析和存储Xml文件。<br>
// 在内存中构建以FXmlNode/FXmlNodes为主的Xml数据树。</p>
//
// @history 051014 MAOCY 创建
//============================================================
public class FXmlDocument
      extends FObject
      implements
         IDump
{
   // 文件编码
   protected String _encoding = RXml.DEFAULT_ENCODING;

   // 前置空白(TAB=" ")
   protected String _indent = RXml.DEFAULT_INDENT;

   // 配置是否建立新行
   protected boolean _optionNewLine = true;

   // 配置属性区分大小写
   protected boolean _optionAttributeCareCase = true;

   // 是否建立文本节点
   protected boolean _textFlag = false;

   // 节点内容两边去空格标志
   protected boolean _textTrimFlag = true;

   // 节点类对象
   protected FClass<?> _nodeClass;

   // Xml根节点
   protected FXmlNode _rootNode;

   // 文件名称
   protected String _fileName;

   //============================================================
   // <T>构造XML文档。</T>
   //============================================================
   public FXmlDocument(){
   }

   //============================================================
   // <T>构造XML文档。</T>
   //
   // @param root 根节点
   //============================================================
   public FXmlDocument(FXmlNode root){
      _rootNode = root;
      if(RString.isEmpty(_rootNode.name())){
         _rootNode.setName(RXml.DEFAULT_NODE_NAME);
      }
   }

   //============================================================
   // <T>构造XML文档。</T>
   //
   // @param inputStream 输入流
   //============================================================
   public FXmlDocument(InputStream inputStream){
      loadStream(inputStream);
   }

   //============================================================
   // <T>构造XML文档。</T>
   //
   // @param fileName 文件名称
   //============================================================
   public FXmlDocument(String fileName){
      loadFile(fileName);
   }

   //============================================================
   // <T>获得内容编码。</T>
   //
   // @return 内容编码
   //============================================================
   public String encoding(){
      return _encoding;
   }

   //============================================================
   // <T>设置内容编码。</T>
   //
   // @param encoding 内容编码
   //============================================================
   public void setEncoding(String encoding){
      _encoding = encoding;
   }

   //============================================================
   // <T>获得前缀空白内容。</T>
   //
   // @return 前缀空白内容
   //============================================================
   public String indent(){
      return _indent;
   }

   //============================================================
   // <T>设置前缀空白内容。</T>
   //
   // @param indent 前缀空白内容
   //============================================================
   public void setIndent(String indent){
      _indent = indent;
   }

   //============================================================
   // <T>获得配置是否建立新行。</T>
   //
   // @return 配置是否建立新行
   //============================================================
   public boolean optionNewLine(){
      return _optionNewLine;
   }

   //============================================================
   // <T>设置配置是否建立新行。</T>
   //
   // @param optionNewLine 配置是否建立新行
   //============================================================
   public void setOptionNewLine(boolean optionNewLine){
      _optionNewLine = optionNewLine;
   }

   //============================================================
   // <T>获得配置属性区分大小写。</T>
   //
   // @return 配置属性区分大小写
   //============================================================
   public boolean optionAttributeCareCase(){
      return _optionAttributeCareCase;
   }

   //============================================================
   // <T>设置配置属性区分大小写。</T>
   //
   // @param optionAttributeCareCase 配置属性区分大小写
   //============================================================
   public void setOptionAttributeCareCase(boolean optionAttributeCareCase){
      _optionAttributeCareCase = optionAttributeCareCase;
   }

   //============================================================
   // <T>获得是否建立文本节点标志。</T>
   //
   // @return 节点标志
   //============================================================
   public boolean textFlag(){
      return _textFlag;
   }

   //============================================================
   // <T>设置是否建立文本节点标志。</T>
   //
   // @param textFlag 节点标志
   //============================================================
   public void setTextFlag(boolean textFlag){
      _textFlag = textFlag;
   }

   //============================================================
   // <T>获得节点内容两边去空格标志。</T>
   //
   // @return 节点内容两边去空格标志
   //============================================================
   public boolean textTrimFlag(){
      return _textTrimFlag;
   }

   //============================================================
   // <T>设置节点内容两边去空格标志。</T>
   //
   // @param textTrimFlag 标志
   //============================================================
   public void setTextTrimFlag(boolean textTrimFlag){
      _textTrimFlag = textTrimFlag;
   }

   //============================================================
   // <T>获得节点类对象。</T>
   //
   // @return 节点类对象
   //============================================================
   public FClass<?> nodeClass(){
      return _nodeClass;
   }

   //============================================================
   // <T>设置节点类对象。</T>
   //
   // @param clazz 节点类对象
   //============================================================
   public void setNodeClass(FClass<?> clazz){
      _nodeClass = clazz;
   }

   //============================================================
   // <T>获得XML根节点。</T>
   //
   // @return 根节点
   //============================================================
   public FXmlNode root(){
      if(null == _rootNode){
         _rootNode = createNode(RXml.DEFAULT_ROOT_NAME);
      }
      return _rootNode;
   }

   //============================================================
   // <T>获得文件名称。</T>
   //
   // @return 节点内容两边去空格标志
   //============================================================
   public String fileName(){
      return _fileName;
   }

   //============================================================
   // <T>设置文件名称。</T>
   //
   // @param fileName 文件名称
   //============================================================
   public void setFileName(String fileName){
      _fileName = RFile.formatFileName(fileName);
   }

   //============================================================
   // <T>创建一个新的节点。</T>
   //
   // @return 节点
   //============================================================
   public FXmlNode createNode(){
      return createNode(RXml.DEFAULT_NODE_NAME);
   }

   //============================================================
   // <T>创建一个新的节点。</T>
   //
   // @param name 节点名称
   // @return 节点
   //============================================================
   public FXmlNode createNode(String name){
      FXmlNode node = null;
      if(null != _nodeClass){
         node = (FXmlNode)_nodeClass.newInstance();
         node.setName(name);
      }else{
         node = new FXmlNode(name);
      }
      node._document = this;
      return node;
   }

   //============================================================
   // <T>创建一个新的文本节点。</T>
   //
   // @param name 节点名称
   // @return 节点
   //============================================================
   public FXmlText createTextNode(){
      FXmlText text = new FXmlText();
      text._document = this;
      return text;
   }

   //============================================================
   // <T>创建一个新的数据节点。</T>
   //
   // @param name 节点名称
   // @return 节点
   //============================================================
   public FXmlData createDataNode(String name){
      FXmlData data = new FXmlData(name);
      data._document = this;
      return data;
   }

   //============================================================
   // <T>同步Element数据到FXmlNode节点对象中。</T>
   //
   // @param fileName 文件名称
   //============================================================
   protected FXmlNode makeNodeFromElement(Element element){
      FXmlNode node = null;
      // 检查参数合法性
      if(null == element){
         throw new FFatalError("Make node from element error. (element={1})", element);
      }
      List<?> contents = element.getContent();
      int contentCount = contents.size();
      boolean isData = false;
      // 处理普通节点和CDATA节点
      if(1 == contentCount){
         Object item = contents.get(0);
         if(item instanceof CDATA){
            isData = true;
            node = createDataNode(element.getName());
            node.setText(((CDATA)item).getText());
         }
      }
      if(null == node){
         node = createNode(element.getName());
      }
      // 设置节点的内容
      if(!isData){
         if(_textTrimFlag){
            node.setText(element.getTextTrim());
         }else{
            node.setText(element.getText());
         }
      }
      // 设置节点的属性内容
      List<?> elementsAttrs = element.getAttributes();
      if(!elementsAttrs.isEmpty()){
         int count = elementsAttrs.size();
         FAttributes nodeAttrs = node.attributes();
         for(int n = 0; n < count; n++){
            Attribute elementAttr = (Attribute)elementsAttrs.get(n);
            String attrName = elementAttr.getName();
            String attrValue = elementAttr.getValue();
            if(-1 != attrValue.indexOf("\\n")){
               attrValue = attrValue.replaceAll("\\\\n", "\n");
            }
            if(!_optionAttributeCareCase){
               attrName = attrName.toLowerCase();
            }
            nodeAttrs.set(attrName, attrValue);
         }
      }
      // 设置节点的子节点
      if(!isData && contentCount > 0){
         FXmlNodes nodes = node.nodes();
         for(int n = 0; n < contentCount; n++){
            Object content = contents.get(n);
            if(_textFlag && content instanceof Text){
               // 处理文本节点
               FXmlText child = createTextNode();
               child.setText(((Text)content).getText());
               nodes.push(child);
            }else if(content instanceof Element){
               // 处理元素节点
               nodes.push(makeNodeFromElement((Element)content));
            }
         }
      }
      // 初始化节点信息
      if(node instanceof IInitialize){
         ((IInitialize)node).initialize();
      }
      return node;
   }

   //============================================================
   // <T>同步Element数据到FXmlNode节点对象中。</T>
   //
   // @param fileName 文件名称
   //============================================================
   protected void syncNodeFromElement(FXmlNode node,
                                      Element element){
      // 检查参数合法性
      if(null == node || null == element){
         throw new FFatalError("Sync node from element error. (node={1}, element={2})", node, element);
      }
      List<?> elmAttrs = element.getAttributes();
      List<?> contents = element.getContent();
      boolean hasAttr = !elmAttrs.isEmpty();
      boolean hasContent = !contents.isEmpty();
      // 设置节点的名称和内容
      node.setName(element.getName());
      if(!hasContent){
         if(_textTrimFlag){
            node.setText(element.getTextTrim());
         }else{
            node.setText(element.getText());
         }
      }
      // 设置节点的属性内容
      if(hasAttr){
         int count = elmAttrs.size();
         FAttributes nodeAttrs = node.attributes();
         for(int n = 0; n < count; n++){
            Attribute elmAttr = (Attribute)elmAttrs.get(n);
            String attrValue = elmAttr.getValue();
            if(attrValue.indexOf("\\n") != -1){
               attrValue = attrValue.replaceAll("\\\\n", "\n");
            }
            nodeAttrs.set(elmAttr.getName(), attrValue);
         }
      }
      // 设置节点的子节点
      if(hasContent){
         int count = contents.size();
         FXmlNodes nodes = node.nodes();
         for(int n = 0; n < count; n++){
            Content content = (Content)contents.get(n);
            if(_textFlag && content instanceof Text){
               // 处理文本节点
               FXmlNode child = createTextNode();
               child.setText(((Text)content).getText());
               nodes.push(child);
            }else if(content instanceof Element){
               // 处理元素节点
               FXmlNode child = createNode(((Element)content).getName());
               nodes.push(child);
               syncNodeFromElement(child, (Element)content);
            }else if(content instanceof CDATA){
               // 处理数据节点
               if(_textTrimFlag){
                  node.setText(element.getTextTrim());
               }else{
                  node.setText(element.getText());
               }
            }
         }
      }
      // 初始化节点信息
      if(node instanceof IInitialize){
         ((IInitialize)node).initialize();
      }
   }

   //============================================================
   // <T>同步FXmlNode数据到Element节点对象中。</T>
   //
   // @param node 节点
   // @return 元素
   //============================================================
   protected Element makeElementFromNode(FXmlNode node){
      // 检查参数合法性
      if(null == node){
         throw new FFatalError("Make element from node error. (node={1})", node);
      }
      // 设置元素（Element）的名称和内容
      Element element = new Element(node.name());
      if(node instanceof FXmlData){
         element.addContent(new CDATA(node.text()));
      }else if(node instanceof FXmlText){
         element.addContent(new Text(node.text()));
      }else{
         element.setText(RString.nvl(node.text()));
      }
      // 设置元素（Element）的属性内容
      if(node.hasAttribute()){
         List<?> elmAttrs = element.getAttributes();
         if(null != elmAttrs){
            FAttributes nodeAttrs = node.attributes();
            int count = nodeAttrs.count();
            for(int n = 0; n < count; n++){
               String attrName = nodeAttrs.name(n);
               String attrValue = nodeAttrs.value(n);
               // 修正属性内容
               if(null != attrValue){
                  if(attrValue.indexOf("\n") != -1){
                     attrValue = attrValue.replaceAll("\r", "");
                     attrValue = attrValue.replaceAll("\n", "\\\\n");
                  }
                  element.setAttribute(attrName, attrValue);
               }
            }
         }
      }
      // 设置元素（Element）的子节点
      if(node.hasNode()){
         FXmlNodes nodes = node.nodes();
         int count = nodes.count();
         for(int n = 0; n < count; n++){
            element.addContent(makeElementFromNode(nodes.get(n)));
         }
      }
      return element;
   }

   //============================================================
   // <T>从字符串信息中读取XML文档。</T>
   //
   // @param xml Xml字符串
   //============================================================
   public void loadString(String xml){
      root().clear();
      try{
         SAXBuilder builder = new SAXBuilder();
         Document document = builder.build(new StringReader(xml));
         _rootNode = makeNodeFromElement(document.getRootElement());
      }catch(Exception e){
         throw new FFatalError(e, "Load from xml. (xml={1})", xml);
      }
   }

   //============================================================
   // <T>从流信息中读取XML文档。</T>
   //
   // @param inputStream 输入流
   //============================================================
   public void loadStream(InputStream inputStream){
      root().clear();
      try{
         SAXBuilder builder = new SAXBuilder();
         Document document = builder.build(inputStream);
         _rootNode = makeNodeFromElement(document.getRootElement());
      }catch(Exception e){
         throw new FFatalError(e, "Load stream failure. (input_stream={1}])", inputStream);
      }
   }

   //============================================================
   // <T>从指定文件中读取XML文档。</T>
   //
   // @param fileName 文件名称
   //============================================================
   public void loadFile(String fileName){
      _fileName = fileName;
      // 读取文件
      try(FileInputStream input = new FileInputStream(_fileName)){
         loadStream(input);
      }catch(Exception e){
         throw new FFatalError(e, "Load file failure. (file_name={1})", _fileName);
      }
   }

   //============================================================
   // <T>重新读取Xml文档。</T>
   //============================================================
   public void reload(){
      loadFile(_fileName);
   }

   //============================================================
   // <T>同步FXmlNode数据到Element节点对象中。</T>
   //
   // @param element 元素
   // @param node 节点
   //============================================================
   @SuppressWarnings("unchecked")
   protected void syncElementFromNode(Element element,
                                      FXmlNode node){
      if(node == null || element == null){
         return;
      }
      // 设置元素（Element）的名称和内容
      element.setName(node.name());
      element.setText(RString.nvl(node.text()));
      // 设置元素（Element）的属性内容
      if(node.hasAttribute()){
         List<Attribute> elmAttrs = element.getAttributes();
         if(elmAttrs != null){
            String attrName = null;
            String attrValue = null;
            FAttributes nodeAttrs = node.attributes();
            int count = nodeAttrs.count();
            for(int n = 0; n < count; n++){
               attrName = nodeAttrs.name(n);
               attrValue = nodeAttrs.value(n);
               // 修正属性内容
               if(attrValue != null){
                  if(attrValue.indexOf("\n") != -1){
                     attrValue = attrValue.replaceAll("\r", "");
                     attrValue = attrValue.replaceAll("\n", "\\\\n");
                  }
                  element.setAttribute(attrName, attrValue);
               }
            }
         }
      }
      // 设置元素（Element）的子节点
      if(node.hasNode()){
         Element child = null;
         FXmlNodes nodes = node.nodes();
         int count = nodes.count();
         for(int n = 0; n < count; n++){
            child = new Element(node.name());
            element.addContent(child);
            syncElementFromNode(child, nodes.get(n));
         }
      }
   }

   //============================================================
   // <T>保存Xml文档到输出流中。</T>
   //
   // @param stream 输出流
   //============================================================
   public void saveStream(OutputStream stream){
      try{
         // 复制信息到节点内
         Element root = new Element(RXml.DEFAULT_NODE_NAME);
         syncElementFromNode(root, root());
         // 生成内存节点
         Document document = new Document();
         document.setRootElement(makeElementFromNode(root()));
         // 设置存储格式，保存文件
         XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
         Format format = outputter.getFormat();
         format.setIndent(_indent);
         format.setLineSeparator("\n");
         format.setEncoding(_encoding);
         outputter.output(document, stream);
      }catch(Exception e){
         throw new FFatalError(e, "Save output stream failure. (stream={1})", stream);
      }
   }

   //============================================================
   // <T>自动创建目录，保存XML文档。</T>
   //
   // @param createDir 自动创建目录
   //============================================================
   public void saveFile(boolean createDir){
      try{
         // 检查文件存储路径是否存在
         if(createDir){
            int index = _fileName.lastIndexOf(File.separator);
            if(index > 0){
               String path = _fileName.substring(0, index);
               File dir = new File(path);
               if(!dir.exists()){
                  dir.mkdirs();
               }
            }
         }
         // 设置存储格式，保存Xml文件
         FileOutputStream os = new FileOutputStream(_fileName);
         saveStream(os);
         os.close();
      }catch(Exception e){
         throw new FFatalError(e, "Save file failure. (dir={1})", createDir);
      }
   }

   //============================================================
   // <T>保存XML文档。</T>
   //
   // @param fileName 文件名称
   //============================================================
   public void saveFile(String fileName){
      setFileName(fileName);
      saveFile(true);
   }

   //============================================================
   // <T>自动创建目录，保存XML文档。</T>
   //
   // @param fileName 文件名称
   // @param createDir 自动创建目录
   //============================================================
   public void saveFile(String fileName,
                        boolean createDir){
      setFileName(fileName);
      saveFile(createDir);
   }

   //============================================================
   // <T>存储XML文档。</T>
   //============================================================
   public void store(){
      saveFile(true);
   }

   //============================================================
   // <T>获得XML字符串。</T>
   //
   // @return 字符串
   //============================================================
   public String xml(){
      try{
         // 复制信息到Xml节点内
         Element root = new Element(RXml.DEFAULT_NODE_NAME);
         syncElementFromNode(root, root());
         // 设置存储格式，保存Xml文件
         StringWriter writer = new StringWriter();
         Document document = new Document();
         document.setRootElement(root);
         XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
         Format format = outputter.getFormat();
         format.setIndent(_indent);
         format.setLineSeparator("\n");
         format.setEncoding(_encoding);
         outputter.output(document, writer);
         return writer.toString();
      }catch(Exception e){
         throw new FFatalError(e);
      }
   }

   //============================================================
   // <T>生成运行信息。</T>
   //
   // @param info 运行信息
   // @return 运行信息
   //============================================================
   @Override
   public TDumpInfo dump(TDumpInfo info){
      RClass.dump(info, this);
      info.append("\n");
      info.append(xml());
      return info;
   }
}
