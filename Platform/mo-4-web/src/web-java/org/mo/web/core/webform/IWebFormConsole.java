package org.mo.web.core.webform;

import org.mo.com.xml.EXmlConfig;
import org.mo.com.xml.FXmlNode;
import org.mo.com.xml.FXmlNodeMap;
import org.mo.com.xml.IXmlObject;
import org.mo.eng.store.IXmlConfigConsole;
import org.mo.eng.store.IXmlConfigConvert;
import org.mo.web.core.webform.control.XWebTemplate;

//============================================================
// <T>网页表单控制台接口。</T>
//============================================================
public interface IWebFormConsole
      extends
         IXmlConfigConsole<IXmlObject>,
         IXmlConfigConvert
{
   /**
    * <T>建立指定名称的表单节点结构。</T>
    * <P><UL>
    *    <L>处理父表单继承关系。</L>
    *    <L>处理表单内模板的嵌套引用。</L>
    *    <L>处理表单内所有控件的样式引用。</L>
    *    <L>根据设置类型复制节点的属性内容</L>
    * </UL></P>
    * 
    * @param name 表单名称
    * @param type 设置类型
    * @return 节点设置
    * @history 090802 MAOCY 使用缓冲对象
    */
   FXmlNode findConfig(TWebFormArgs args);

   //============================================================
   // <T>建立指定名称的表单节点结构。</T>
   // <P><UL>
   //    <L>处理父表单继承关系。</L>
   //    <L>处理表单内模板的嵌套引用。</L>
   //    <L>处理表单内所有控件的样式引用。</L>
   //    <L>根据设置类型复制节点的属性内容</L>
   // </UL></P>
   //
   // @param name 表单名称
   // @param type 设置类型
   // @return 节点设置
   //============================================================
   FXmlNode build(String name,
                  EXmlConfig type);

   /**
    * <T>建立指定名称的表单节点结构。</T>
    * <P><UL>
    *    <L>处理父表单继承关系。</L>
    *    <L>处理表单内模板的嵌套引用。</L>
    *    <L>处理表单内所有控件的样式引用。</L>
    *    <L>根据设置类型复制节点的属性内容</L>
    * </UL></P>
    * 
    * @param name 表单名称
    * @param type 设置类型
    * @return 节点设置
    * @history 090614 MAOCY 使用指定的数据库链接创建表单结构
    */
   FXmlNode build(TWebFormArgs args);

   /**
    * <T>建立指定名称的表单节点结构。</T>
    * <P><UL>
    *    <L>处理父表单继承关系。</L>
    *    <L>处理表单内模板的嵌套引用。</L>
    *    <L>处理表单内所有控件的样式引用。</L>
    *    <L>根据设置类型复制节点的属性内容</L>
    * </UL></P>
    * 
    * @param name 表单名称
    * @param type 设置类型
    * @return 节点设置
    * @history 090802 MAOCY 使用缓冲对象
    */
   FXmlNode buildConfig(TWebFormArgs args);

   /**
    * <T>为设计模式建立指定名称的表单节点结构。</T>
    * <P><UL>
    *    <L>处理父表单继承关系。</L>
    *    <L>不展开表单内模板引用。</L>
    *    <L>处理表单内所有控件的样式引用。</L>
    *    <L>根据设置类型复制节点的属性内容</L>
    * </UL></P>
    *
    * @param name 表单名称
    * @param type 设置类型
    * @return 节点设置
    */
   FXmlNode buildDesign(String name,
                        EXmlConfig type);

   /**
    * <T>为事件模式建立指定名称的表单节点结构。</T>
    * <P><UL>
    *    <L>处理父表单继承关系。</L>
    *    <L>处理表单内模板的嵌套引用。</L>
    *    <L>只建立类型为事件的节点对象，没有递归结构。</L>
    * </UL></P>
    *
    * @param name 表单名称
    * @return 节点设置
    */
   FXmlNode buildEvent(String name);

   /**
    * <T>为事件模式建立指定名称的表单节点结构。</T>
    * <P><UL>
    *    <L>处理父表单继承关系。</L>
    *    <L>处理表单内模板的嵌套引用。</L>
    *    <L>只建立类型为事件的节点对象，没有递归结构。</L>
    * </UL></P>
    *
    * @param args 表单参数
    * @return 节点设置
    */
   FXmlNode buildEvent(TWebFormArgs args);

   /**
    * <T>建立选取用的数据表单结构。</T>
    * 
    * @param name 表单名称
    * @return 节点设置
    */
   FXmlNode buildLov(String name);

   /**
    * <T>建立指定名称的样式节点结构。</T>
    * <P><UL>
    *    <L>处理父样式表单继承关系。</L>
    *    <L>处理样式表单内模板的嵌套引用。</L>
    *    <L>只建立类型为样式的节点对象，没有递归结构。</L>
    * </UL></P>
    *
    * @param name 样式表单名称
    * @param type 设置类型
    * @return 节点设置
    */
   FXmlNodeMap buildStyleNode(String name,
                              EXmlConfig type);

   /**
    * <T>建立指定名称的模板节点结构。</T>
    * <P><UL>
    *    <L>处理父表单继承关系。</L>
    *    <L>展开模板内模板引用。</L>
    *    <L>处理表单内所有控件的样式引用。</L>
    *    <L>根据设置类型复制节点的属性内容</L>
    * </UL></P>
    *
    * @param name 表单名称
    * @param type 设置类型
    * @return 节点设置
    */
   FXmlNode buildTemplate(String name,
                          EXmlConfig type);

   /**
    * <T>根据指定名称获得模板对象。</T>
    *
    * @param name 模板名称
    * @return 模板对象
    */
   XWebTemplate getTemplate(String name);
}
