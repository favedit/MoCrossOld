package org.mo.jfa.core.property;

import org.mo.eng.store.FXmlConfigMeta;

public interface IWebFormConsole
{

   FWebForm find(String name);

   FXmlConfigMeta[] listMetas();

   void persist(FWebForm form);

   void remove(FWebForm form);

   void store(FWebForm form);

   FWebForm sync(String name);

}
