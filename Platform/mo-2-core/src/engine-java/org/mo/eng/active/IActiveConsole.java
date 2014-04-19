package org.mo.eng.active;

public interface IActiveConsole
{
   void push(Class<?> clazz,
             IActive active);

   void remove(Class<?> clazz,
               IActive active);
}
