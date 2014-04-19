package org.mo.eng.culture;

import org.mo.com.lang.cultrue.FCulture;

public interface ICultureConsole
{
   FCulture culture();

   FCulture find();

   void release(FCulture culture);
}
