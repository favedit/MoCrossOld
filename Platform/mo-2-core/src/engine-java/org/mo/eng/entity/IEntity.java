package org.mo.eng.entity;

import org.mo.com.collections.FObjectDictionary;
import org.mo.com.lang.FAttributes;

public interface IEntity
{
   FAttributes attributes();

   int ouid();

   String over();

   void setOuid(int ouid);

   FObjectDictionary values();
   /*String innerGet(String name);
   void innerLoadRow(IStringMap row);
   void innerLoadValue(IStringMap values);
   void innerSaveRow(IStringMap row);
   void innerSaveValue(IStringMap values);
   void innerSet(String name,
                 String value);*/
}
