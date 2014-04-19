package org.mo.web.core.process;

public interface IWebProcessConsole
{

   public Object execute(String action,
                         FWebProcessArgs args);

   public Object execute(String faceName,
                         String methodName,
                         FWebProcessArgs args);

   public Object executeFace(Object face,
                             String methodName,
                             FWebProcessArgs args);

}
