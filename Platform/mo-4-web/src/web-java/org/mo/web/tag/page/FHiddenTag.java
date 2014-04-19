/*
 * @(#)FHiddenTag.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.web.tag.page;

/**
 * <T>隐藏标签</T>
 * <P>添加与隐藏标签有关的属性设置</P>
 * 
 * @author MAOCY
 * @version 1.00 - 2008/08/05 CREATE BY MAOCY
 * @version 1.01 - 2009/01/05 UPDATE BY YANGH
 */
public class FHiddenTag
      extends FEditTag
{

   @Override
   public int onStart(){
      /// 设置类型为隐藏
      _type = "hidden";
      /// 调用父类方法
      return super.onStart();
   }

}
