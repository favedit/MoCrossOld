﻿using System;

namespace MS.Common.System
{
   //============================================================
   // <T>监听器接口。</T>
   //
   // @history MAOCY 140414
   //============================================================
   public interface IListener : IDisposable
   {
      //============================================================
      // <T>获得或设置拥有对象。</T>
      //
      // @param value 拥有对象
      // @return 拥有对象
      //============================================================
      object Owner { get; }

      //============================================================
      // <T>执行处理。</T>
      //
      // @param sender 发出者
      // @param args 参数列表
      //============================================================
      void Process(object sender, params object[] args);
   }
}
