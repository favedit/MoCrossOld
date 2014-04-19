package org.mo.game.editor.core.entity;

import org.mo.eng.store.IXmlConfigConsole;
import org.mo.game.editor.core.entity.common.XEntityGroup;

public interface IEntityConsole
      extends
         IXmlConfigConsole<XEntityGroup>{

   void buildAll(EEntitySource type);

   String buildPath(String type,
                    String fileName);
}
