/*
 * @(#)IJavascriptHelper.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.web.core.parser.js;

import org.mo.eng.help.IBuildHelper;
import org.mo.eng.help.common.XAction;
import org.mo.eng.help.common.XHelp;

/**
 * <T>生成JavaScript的帮助文档的接口</T>
 *
 * @author ZENGD
 * @version 1.0.1
 */
public interface IJavascriptHelper
      extends
         IBuildHelper
{

   /**
    * <T>建立变量页面的方法</T>
    * 
    * @param xhelp 帮助对象的XML节点基类
    * @param xaction 命令对象的XML节点基类
    */
   void buildAttribute(XHelp xhelp,
                       XAction xaction);

   /**
    * <T>建立类页面的方法</T>
    * 
    * @param xhelp 帮助对象的XML节点基类
    * @param xaction 命令对象的XML节点基类
    */
   void buildClass(XHelp xhelp,
                   XAction xaction);

   /**
    * <T>建立方法页面的方法</T>
    * 
    * @param xhelp 帮助对象的XML节点基类
    * @param xaction 命令对象的XML节点基类
    */
   void buildMethod(XHelp xhelp,
                    XAction xaction);

   /**
    * <T>建立属性页面的方法</T>
    * 
    * @param xhelp 帮助对象的XML节点基类
    * @param xaction 命令对象的XML节点基类
    */
   void buildProperty(XHelp xhelp,
                      XAction xaction);

   /**
    * <T>建立空间页面的方法</T>
    * 
    * @param xhelp 帮助对象的XML节点基类
    * @param xaction 命令对象的XML节点基类
    */
   void buildSpace(XHelp xhelp,
                   XAction xaction);
}
