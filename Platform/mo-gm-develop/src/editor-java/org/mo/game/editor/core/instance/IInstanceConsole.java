package org.mo.game.editor.core.instance;

import org.mo.eng.store.IXmlConfigConsole;
import org.mo.game.editor.core.instance.common.XInstanceGroup;

//============================================================
// <T>游戏实例控制台接口。</T>
//============================================================
public interface IInstanceConsole
      extends
         IXmlConfigConsole<XInstanceGroup>
{
   //============================================================
   // <T>建立代码。</T>
   //
   // @param typeCd 类型
   //============================================================
   void buildSource(EInstanceSource typeCd);
}
