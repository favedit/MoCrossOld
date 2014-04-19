package org.mo.core.server;

public interface IServer
{
   IClientSession CreateSession();

   void process(int command,
                Object... params);
}
