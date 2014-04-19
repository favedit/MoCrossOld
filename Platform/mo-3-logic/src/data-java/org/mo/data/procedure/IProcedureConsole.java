package org.mo.data.procedure;

import org.mo.data.procedure.common.FSqlPackage;

public interface IProcedureConsole
{
   FSqlPackage parseBody(String name);

   FSqlPackage parseHead(String name);
}
