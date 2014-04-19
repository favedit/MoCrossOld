package org.mo.eng.entity.info;

import org.mo.eng.entity.face.EFieldType;

public interface ISqlFieldInfo
{
   boolean auto();

   String format(String value);

   String name();

   @Override
   String toString();

   EFieldType type();
}
