package org.mo.web.tag.page;

import org.mo.com.logging.RLogger;
import org.mo.core.aop.RAop;
import org.mo.web.tag.util.FTagBuilder;

public class FTagBuilderJava
{

   public static void main(String[] args){
      String path = "D:/Workspace/mylife/webroot/WEB-INF";
      try{
         RAop.configConsole().loadFile("D:/Workspace/mylife/src/home/application.xml");
         FTagBuilder builder = new FTagBuilder();

         builder.buildJavaSource(path + "/config/web.tag/html.xml");

         builder.buildJavaSource(path + "/config/web.tag/control.xml");

         RAop.release();
      }catch(Exception e){
         RLogger.find(FTagBuilderJava.class).error(null, "main", e);
      }
   }

}
