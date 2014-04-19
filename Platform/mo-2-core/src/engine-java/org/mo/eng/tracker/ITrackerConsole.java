package org.mo.eng.tracker;

public interface ITrackerConsole
{
   void bind(String id,
             String name,
             Object value);

   Object find(String id,
               String name);
}
