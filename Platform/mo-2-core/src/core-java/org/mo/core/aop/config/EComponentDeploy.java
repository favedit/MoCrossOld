package org.mo.core.aop.config;

import org.mo.com.lang.REnum;

//============================================================
// <T>组件部署模式。</T>
//============================================================
public enum EComponentDeploy{
   // 当被引用时自动部署
   Auto,
   // 系统启动时部署
   Start;
   //============================================================
   // <T>解析字符串到枚举内容。</T>
   //
   // @param value 字符串
   // @return 枚举内容
   //============================================================
   public static EComponentDeploy parse(String value){
      if(null != value){
         return REnum.parse(EComponentDeploy.class, value);
      }
      return Auto;
   };
}
