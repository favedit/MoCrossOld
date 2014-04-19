/*
 * @(#)IProcedureHelper.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.data.procedure;

import org.mo.eng.help.IBuildHelper;
import org.mo.eng.help.common.XAction;
import org.mo.eng.help.common.XHelp;

/**
 * <T>生成Sql package的帮助文档的接口</T>
 *
 * @author ZENGD
 * @version 1.0.1
 */
public interface IProcedureHelper
      extends
         IBuildHelper
{
   /**
    * <T>建立包页面的方法</T>
    * 
    * @param xhelp 帮助对象的XML节点基类
    * @param xaction 命令对象的XML节点基类
    */
   void buildPackage(XHelp xhelp,
                     XAction xaction);

   /**
    * <T>建立函数页面的方法</T>
    * 
    * @param xhelp 帮助对象的XML节点基类
    * @param xaction 命令对象的XML节点基类
    */
   void buildFunction(XHelp xhelp,
                      XAction xaction);

   /**
    * <T>建立过程页面的方法</T>
    * 
    * @param xhelp 帮助对象的XML节点基类
    * @param xaction 命令对象的XML节点基类
    */
   void buildProcedure(XHelp xhelp,
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
    * <T>建立包列表页面的接口</T>
    * 
    * @param xhelp 帮助对象的XML节点基类
    * @param xaction 命令对象的XML节点基类
    */
   void buildList(XHelp xhelp,
                  XAction xaction);
}
