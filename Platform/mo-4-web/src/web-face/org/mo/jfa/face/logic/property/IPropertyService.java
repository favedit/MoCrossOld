/*
 * @(#)IPropertyService.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.jfa.face.logic.property;

import org.mo.web.protocol.context.IWebContext;
import org.mo.web.protocol.context.IWebInput;
import org.mo.web.protocol.context.IWebOutput;

/**
 * <T>属性树目录操作服务接口类。</T>
 * <P>提供对属性进行增删改等各种操作的接口。</P>
 * 
 * @author HYKUN
 * @version 1.00 - 2008/12/06
 */
public interface IPropertyService
{

   /**
    * <T>显示目录操作服务方法。</T>
    * 
    * @param context 页面环境对象。
    * @param input 传入xml节点。
    * @param output 传出的xml节点。
    */
   void catalog(IWebContext context,
                IWebInput input,
                IWebOutput output);

   /**
    * <T>删除节点操作服务方法。</T>
    * 
    * @param context 页面环境对象。
    * @param input 传入xml节点。
    * @param output 传出的xml节点。
    */
   void delete(IWebContext context,
               IWebInput input,
               IWebOutput output);

   /**
    * <T>插入节点操作服务方法。</T>
    * 
    * @param context 页面环境对象。
    * @param input 传入xml节点。
    * @param output 传出的xml节点。
    */
   void insert(IWebContext context,
               IWebInput input,
               IWebOutput output);

   /**
    * <T>展开节点操作服务方法。</T>
    * 
    * @param context 页面环境对象。
    * @param input 传入xml节点。
    * @param output 传出的xml节点。
    */
   void list(IWebContext context,
             IWebInput input,
             IWebOutput output);

   /**
    * <T>节点排序操作服务方法。</T>
    * 
    * @param context 页面环境对象。
    * @param input 传入xml节点。
    * @param output 传出的xml节点。
    */
   void sort(IWebContext context,
             IWebInput input,
             IWebOutput output);

   /**
    * <T>节点更新操作服务方法。</T>
    * 
    * @param context 页面环境对象。
    * @param input 传入xml节点。
    * @param output 传出的xml节点。
    */
   void update(IWebContext context,
               IWebInput input,
               IWebOutput output);
}
