package org.mo.core.aop.config;

import org.mo.com.lang.REnum;

//============================================================
// <T>组件有效范围。</T>
//============================================================
public enum EComponentScope
{
   // 全局有效
   Global,

   // 单个有效
   Single,

   // 线程有效
   Session;

   //============================================================
   // <T>解析字符串到枚举内容。</T>
   //
   // @param value 字符串
   // @return 枚举内容
   //============================================================
   public static EComponentScope parse(String value){
      if(null != value){
         return REnum.parse(EComponentScope.class, value);
      }
      return Single;
   };
}
