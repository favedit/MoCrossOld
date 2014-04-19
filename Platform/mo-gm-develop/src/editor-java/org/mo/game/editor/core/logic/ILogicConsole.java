package org.mo.game.editor.core.logic;

import org.mo.com.xml.EXmlConfig;
import org.mo.com.xml.FXmlNode;
import org.mo.com.xml.IXmlObject;
import org.mo.eng.store.IXmlConfigConsole;
import org.mo.game.editor.core.enums.common.XEnum;
import org.mo.game.editor.core.logic.common.XLogic;

public interface ILogicConsole
      extends
         IXmlConfigConsole<XLogic>
{

   void buildAll(ELogicSource type);

   FXmlNode buildConfig(IXmlObject xenumeGroup,
                        EXmlConfig type);

   FXmlNode buildConfig(String name);

   String searchConstantCode(String name);

   String searchConstantValue(String name);

   String searchConstantValue(String name,
                              String value);

   FXmlNode searchEnum(String name);

   XEnum searchEnumObject(String name);

   String sourceClientPath();

   String sourceServerPath();

   String sourceToolPath();
}
