package org.mo.util.plsql;

import org.mo.com.io.FStringFile;
import org.mo.com.io.RFile;

public class RPlSqlEdit
{

   public static void changeFile(String filename){
      System.out.println(filename);
      FStringFile file = new FStringFile(filename);
      String body = file.toString();
      // Before_Insert__
      String find = "PROCEDURE Prepare__(";
      int start = body.indexOf(find);
      start = body.indexOf("\n", start + find.length() + 1);
      start = body.indexOf("\n", start + 1);
      body = body.substring(0, start - 1) + ",\n      params_              IN OUT VARCHAR2)" + body.substring(start);
      // Before_Insert__
      find = "PROCEDURE Before_Insert__(";
      start = body.indexOf(find);
      start = body.indexOf("\n", start + find.length() + 1);
      start = body.indexOf("\n", start + 1);
      body = body.substring(0, start) + "\n      params_              IN OUT VARCHAR2," + body.substring(start);
      // Before_Insert__
      find = "PROCEDURE After_Insert__(";
      start = body.indexOf(find);
      start = body.indexOf("\n", start + find.length() + 1);
      start = body.indexOf("\n", start + 1);
      body = body.substring(0, start - 1) + ",\n      params_              IN OUT VARCHAR2)" + body.substring(start);
      // Before_Update__
      find = "PROCEDURE Before_Update__(";
      start = body.indexOf(find);
      start = body.indexOf("\n", start + find.length() + 1);
      start = body.indexOf("\n", start + 1);
      start = body.indexOf("\n", start + 1);
      body = body.substring(0, start) + "\n      params_              IN OUT VARCHAR2," + body.substring(start);
      // After_Update__
      find = "PROCEDURE After_Update__(";
      start = body.indexOf(find);
      start = body.indexOf("\n", start + find.length() + 1);
      start = body.indexOf("\n", start + 1);
      start = body.indexOf("\n", start + 1);
      body = body.substring(0, start - 1) + ",\n      params_              IN OUT VARCHAR2)" + body.substring(start);
      // Before_Change__
      find = "PROCEDURE Before_Change__(";
      start = body.indexOf(find);
      start = body.indexOf("\n", start + find.length() + 1);
      start = body.indexOf("\n", start + 1);
      start = body.indexOf("\n", start + 1);
      body = body.substring(0, start) + "\n      params_              IN OUT VARCHAR2," + body.substring(start);
      // After_Change__
      find = "PROCEDURE After_Change__(";
      start = body.indexOf(find);
      start = body.indexOf("\n", start + find.length() + 1);
      start = body.indexOf("\n", start + 1);
      start = body.indexOf("\n", start + 1);
      body = body.substring(0, start - 1) + ",\n      params_              IN OUT VARCHAR2)" + body.substring(start);
      // Before_Delete__
      find = "PROCEDURE Before_Delete__(";
      start = body.indexOf(find);
      start = body.indexOf("\n", start + find.length() + 1);
      start = body.indexOf("\n", start + 1);
      body = body.substring(0, start) + "\n      params_              IN OUT VARCHAR2," + body.substring(start);
      // After_Delete__
      find = "PROCEDURE After_Delete__(";
      start = body.indexOf(find);
      start = body.indexOf("\n", start + find.length() + 1);
      start = body.indexOf("\n", start + 1);
      body = body.substring(0, start - 1) + ",\n      params_              IN OUT VARCHAR2)" + body.substring(start);
      //System.out.println(body);
      file.clear();
      file.append(body);
      file.saveFile(filename);
   }

   public static void main(String[] args){
      String path = "D:/Workspace/eUIS/webroot/WEB-INF/config/system.deploy.store/current/install/sql_package_body";
      for(String fileName : RFile.listAllFile(path)){
         String name = fileName.toLowerCase();
         if(name.endsWith(".pky")){
            changeFile(fileName);
         }
      }
   }
}
