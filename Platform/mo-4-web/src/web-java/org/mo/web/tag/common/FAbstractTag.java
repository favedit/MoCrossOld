/*
 * @(#)FAbstractTag.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.web.tag.common;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;
import org.mo.com.lang.RString;
import org.mo.com.lang.reflect.RClass;
import org.mo.web.protocol.context.FControlConfig;
import org.mo.web.protocol.context.FStringWriter;
import org.mo.web.protocol.context.FWebContext;
import org.mo.web.protocol.context.IWebGlobals;

/**
 * <T>标签基类</T>
 * <P>统一JSP和WAP使用的标签 </BR>
 * 1. 定义标签开始和结束的逻辑操作 </BR>
 * 2. 一些环境变量的存储</p>
 *
 * @author MAOCY
 * @version 1.00 - 2008/08/05 CREATE BY MAOCY
 * @version 1.01 - 2009/01/05 UPDATE BY YANGH
 */
public abstract class FAbstractTag
      implements
         Tag
{

   /// WEB应用程序的环境对象
   protected FWebContext _context;

   /// 定义标题对象
   protected String _config;

   /// 页面的环境对象
   protected PageContext _pageContext;

   /// 父节点对象
   protected FAbstractTag _parent;

   /// 定义输出流
   protected FStringWriter _writer = new FStringWriter();

   /**
    * <P>创建标签对象的实例</P>
    */
   public FAbstractTag(){
   }

   public void construct(FControlConfig config){
   }

   public FWebContext context(){
      return _context;
   }

   /**
    * <P>结束标签的逻辑</P>
    *
    * @return 逻辑执行后的状态
    * @exception JspException 逻辑例外
    */
   public int doEndTag() throws JspException{
      try{
         return onEnd();
      }catch(Exception e){
         throw new JspException(e);
      }finally{
         release();
      }
   }

   /**
    * <P>开始标签的逻辑</P>
    *
    * @return 逻辑执行后的状态
    * @exception JspException JSP例外
    */
   public int doStartTag() throws JspException{
      try{
         if(RString.isNotBlank(_config)){
            FControlConfig config = _context.pageConfig().control(_config);
            if(config != null){
               construct(config);
            }
         }
         return onStart();
      }catch(Exception e){
         throw new JspException(e);
      }
   }

   /**
    * <P>获得类型为指定类的父标签对象</P>
    *
    * @param clazz 类对象
    * @retrun 标签对象
    */
   public FAbstractTag findParent(Class<?> clazz){
      if(clazz != null){
         FAbstractTag tag = (FAbstractTag)getParent();
         while(tag != null){
            if(RClass.isInherit(tag, clazz)){
               return tag;
            }
            tag = (FAbstractTag)tag.getParent();
         }
      }
      return null;
   }

   /**
    * <P>获得父标签对象(接口实现用)</P>
    *
    * @retrun 父标签对象
    */
   public Tag getParent(){
      return _parent;
   }

   /**
    * <P>结束标签的逻辑</P>
    *
    * @return 逻辑执行后的状态
    */
   public abstract int onEnd();

   /**
    * <P>开始标签的逻辑</P>
    *
    * @return 逻辑执行后的状态
    */
   public abstract int onStart();

   /**
    * <P>获得页面环境对象</P>
    *
    * @retrun 页面环境对象
    */
   public PageContext pageContext(){
      return _pageContext;
   }

   /**
    * <P>获得页面标识</P>
    *
    * @retrun 页面标识
    */
   public String pageId(){
      String page = _pageContext.getPage().toString().toLowerCase();
      page = page.substring(page.indexOf(".jsp.") + 5, page.indexOf("@"));
      if(page.endsWith("_jsp")){
         page = page.substring(0, page.length() - 4);
      }
      return page;
   }

   /**
    * <P>获得父标签对象</P>
    *
    * @retrun 父标签对象
    */
   public FAbstractTag parent(){
      return _parent;
   }

   /**
    * <P>释放标签所使用的资源</P>
    */
   public void release(){
      _writer.unlink();
      _pageContext = null;
      _parent = null;
      _context = null;
   }

   public void setConfig(String config){
      _config = config;
   }

   /**
    * <P>设置页面环境对象</P>
    *
    * @param pageContext 页面环境对象
    */
   public void setPageContext(PageContext pageContext){
      _pageContext = pageContext;
      _writer.link(pageContext.getOut());
      /// 获得和设置WEB应用程序的环境对象
      _context = (FWebContext)pageContext.getRequest().getAttribute(IWebGlobals.INSTANCE_WEB_CONTEXT);
   }

   /**
    * <P>设置父标签对象</P>
    *
    * @param parent 父标签对象
    */
   public void setParent(Tag parent){
      _parent = (FAbstractTag)parent;
   }

}
