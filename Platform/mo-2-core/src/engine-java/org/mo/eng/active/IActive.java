package org.mo.eng.active;

public interface IActive
{
   boolean isActive();

   void resume();

   void stop();

   void run();

   void release();
}
