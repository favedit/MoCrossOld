package org.mo.core.aop.config;

import org.mo.com.lang.REnum;

//============================================================
// <T>关系类型。</T>
//============================================================
public enum ERelationType{
   // 空
   None,
   // 类路径
   ClassPath;
   //============================================================
   // <T>解析字符串到枚举内容。</T>
   //
   // @param value 字符串
   // @return 枚举内容
   //============================================================
   public static ERelationType parse(String value){
      if(null != value){
         return REnum.parse(ERelationType.class, value);
      }
      return None;
   };
}
