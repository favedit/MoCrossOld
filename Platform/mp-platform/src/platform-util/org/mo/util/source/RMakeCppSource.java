package org.mo.util.source;

import org.mo.com.collections.FDataset;
import org.mo.com.collections.FRow;
import org.mo.com.io.FStringFile;
import org.mo.com.lang.FString;
import org.mo.com.lang.RString;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.com.system.RApplication;
import org.mo.core.aop.RAop;
import org.mo.eng.template.ITemplateConsole;
import org.mo.eng.template.ITemplateParser;
import org.mt.core.aop.RAopTest;

//============================================================
// <T>AS对象转DotNet类对象代码工具类。</T>
//============================================================
public class RMakeCppSource
{
   // 日志输出接口
   private final static ILogger _logger = RLogger.find(RMakeCppSource.class);

   public static ITemplateConsole _templateConsole;

   public static String _sourcePath = "D:\\ZW-Platform-Work\\mo-1-common\\src\\lang-util\\org\\mu\\com\\lang\\cpp";

   public static String _targetPath = "D:\\ZW-Mobile-Work\\Cross\\Common\\MoCommon";

   //============================================================
   public static String makeGeomHead(String tempalteName,
                                     String typeName,
                                     String value,
                                     String label){
      return makeGeomHead(tempalteName, typeName, value, label, "");
   }

   public static String makeGeomHead(String tempalteName,
                                     String typeName,
                                     String value,
                                     String label,
                                     String value2){

      String source = new FStringFile(_sourcePath + "\\template.geom." + tempalteName + ".h", "utf-8").toString();
      source = RString.replace(source, "{type_name}", typeName.substring(1));
      source = RString.replace(source, "{type}", typeName);
      source = RString.replace(source, "{default}", value);
      source = RString.replace(source, "{default2}", value2);
      source = RString.replace(source, "{label}", label);
      return source;
   }

   //============================================================
   public static String makeGeomTemplateHead(String tempalteName,
                                             String typeName,
                                             String value,
                                             String label){
      ITemplateParser parser = _templateConsole.findParser(tempalteName);
      parser.define("type_name", typeName.substring(1));
      parser.define("type", typeName);
      parser.define("label", label);
      parser.define("default", value);
      FString source = parser.parse();
      return source.toString().trim();
   }

   //============================================================
   public static void makeGeomBody(String tempalteName,
                                   String typeName,
                                   String value,
                                   String label,
                                   String format,
                                   String default2){
      String source = new FStringFile(_sourcePath + "\\template.geom." + tempalteName + ".cpp", "utf-8").toString();
      source = RString.replace(source, "{type_name}", typeName.substring(1));
      source = RString.replace(source, "{type}", typeName);
      source = RString.replace(source, "{default}", value);
      source = RString.replace(source, "{default2}", default2);
      source = RString.replace(source, "{label}", label);
      source = RString.replace(source, "{format}", format);
      // 项目处理
      String outputFileName = _targetPath + "\\S" + typeName.substring(1) + RString.firstUpper(tempalteName) + ".cpp";
      FStringFile soureFile = new FStringFile();
      soureFile.assign(source);
      soureFile.saveFile(outputFileName, "GBK");
      System.out.println(outputFileName);
   }

   //============================================================
   public static void makeGeom(){
      String outputFileName = _targetPath + "\\MoCmGeom.h";
      // 生成代码
      String source = new FStringFile(_sourcePath + "\\template.geom.h", "utf-8").toString();
      // 存储头文件
      source = RString.replace(source, "{range_int}", makeGeomHead("range", "TInt", "0", "整数"));
      source = RString.replace(source, "{range_float}", makeGeomHead("range", "TFloat", "0.0f", "浮点数"));
      source = RString.replace(source, "{range_double}", makeGeomHead("range", "TDouble", "0", "双浮点数"));

      source = RString.replace(source, "{size2_int}", makeGeomTemplateHead("game.cpp.geom-size2", "TInt", "0", "整数"));
      source = RString.replace(source, "{size2_float}", makeGeomTemplateHead("game.cpp.geom-size2", "TFloat", "0.0f", "浮点数"));
      source = RString.replace(source, "{size2_double}", makeGeomTemplateHead("game.cpp.geom-size2", "TDouble", "0", "双浮点数"));

      source = RString.replace(source, "{size3_int}", makeGeomTemplateHead("game.cpp.geom-size3", "TInt", "0", "整数"));
      source = RString.replace(source, "{size3_float}", makeGeomTemplateHead("game.cpp.geom-size3", "TFloat", "0.0f", "浮点数"));
      source = RString.replace(source, "{size3_double}", makeGeomTemplateHead("game.cpp.geom-size3", "TDouble", "0", "双浮点数"));

      source = RString.replace(source, "{point2_int}", makeGeomTemplateHead("game.cpp.geom-point2", "TInt", "0", "整数"));
      source = RString.replace(source, "{point2_float}", makeGeomTemplateHead("game.cpp.geom-point2", "TFloat", "0.0f", "浮点数"));
      source = RString.replace(source, "{point2_double}", makeGeomTemplateHead("game.cpp.geom-point2", "TDouble", "0", "双浮点数"));
      source = RString.replace(source, "{point3_int}", makeGeomTemplateHead("game.cpp.geom-point3", "TInt", "0", "整数"));
      source = RString.replace(source, "{point3_float}", makeGeomTemplateHead("game.cpp.geom-point3", "TFloat", "0.0f", "浮点数"));
      source = RString.replace(source, "{point3_double}", makeGeomTemplateHead("game.cpp.geom-point3", "TDouble", "0", "双浮点数"));
      source = RString.replace(source, "{point4_int}", makeGeomTemplateHead("game.cpp.geom-point4", "TInt", "0", "整数"));
      source = RString.replace(source, "{point4_float}", makeGeomTemplateHead("game.cpp.geom-point4", "TFloat", "0.0f", "浮点数"));
      source = RString.replace(source, "{point4_double}", makeGeomTemplateHead("game.cpp.geom-point4", "TDouble", "0", "双浮点数"));

      source = RString.replace(source, "{vector2_int}", makeGeomTemplateHead("game.cpp.geom-vector2", "TInt", "0", "整数"));
      source = RString.replace(source, "{vector2_float}", makeGeomTemplateHead("game.cpp.geom-vector2", "TFloat", "0.0f", "浮点数"));
      source = RString.replace(source, "{vector2_double}", makeGeomTemplateHead("game.cpp.geom-vector2", "TDouble", "0", "双浮点数"));
      source = RString.replace(source, "{vector3_int}", makeGeomTemplateHead("game.cpp.geom-vector3", "TInt", "0", "整数"));
      source = RString.replace(source, "{vector3_float}", makeGeomTemplateHead("game.cpp.geom-vector3", "TFloat", "0.0f", "浮点数"));
      source = RString.replace(source, "{vector3_double}", makeGeomTemplateHead("game.cpp.geom-vector3", "TDouble", "0", "双浮点数"));
      source = RString.replace(source, "{vector4_int}", makeGeomTemplateHead("game.cpp.geom-vector4", "TInt", "0", "整数"));
      source = RString.replace(source, "{vector4_float}", makeGeomTemplateHead("game.cpp.geom-vector4", "TFloat", "0.0f", "浮点数"));
      source = RString.replace(source, "{vector4_double}", makeGeomTemplateHead("game.cpp.geom-vector4", "TDouble", "0", "双浮点数"));

      source = RString.replace(source, "{color4_float}", makeGeomHead("color4", "TFloat", "0.0f", "浮点数"));

      source = RString.replace(source, "{quaternion_float}", makeGeomHead("quaternion", "TFloat", "0.0f", "浮点数", "1.0f"));
      source = RString.replace(source, "{quaternion_double}", makeGeomHead("quaternion", "TDouble", "0", "双浮点数", "1"));

      source = RString.replace(source, "{rectangle_int}", makeGeomTemplateHead("game.cpp.geom-rectangle", "TInt", "0", "整数"));
      source = RString.replace(source, "{rectangle_float}", makeGeomTemplateHead("game.cpp.geom-rectangle", "TFloat", "0.0f", "浮点数"));
      source = RString.replace(source, "{rectangle_double}", makeGeomTemplateHead("game.cpp.geom-rectangle", "TDouble", "0", "双浮点数"));

      source = RString.replace(source, "{padding_int}", makeGeomTemplateHead("game.cpp.geom-padding", "TInt", "0", "整数"));
      source = RString.replace(source, "{padding_float}", makeGeomTemplateHead("game.cpp.geom-padding", "TFloat", "0.0f", "浮点数"));
      source = RString.replace(source, "{padding_double}", makeGeomTemplateHead("game.cpp.geom-padding", "TDouble", "0", "双浮点数"));

      source = RString.replace(source, "{matrix4x4_float}", makeGeomHead("matrix4x4", "TFloat", "0.0f", "浮点数", "1.0f"));
      source = RString.replace(source, "{matrix4x4_double}", makeGeomHead("matrix4x4", "TDouble", "0", "双浮点数", "1"));

      source = RString.replace(source, "{matrix2d_float}", makeGeomHead("matrix2d", "TFloat", "0.0f", "浮点数", "1.0f"));
      source = RString.replace(source, "{matrix2d_double}", makeGeomHead("matrix2d", "TDouble", "0", "双浮点数", "1"));

      source = RString.replace(source, "{matrix3d_float}", makeGeomHead("matrix3d", "TFloat", "0.0f", "浮点数", "1.0f"));
      source = RString.replace(source, "{matrix3d_double}", makeGeomHead("matrix3d", "TDouble", "0", "双浮点数", "1"));
      // 项目处理
      FStringFile soureFile = new FStringFile();
      soureFile.assign(source);
      soureFile.saveFile(outputFileName, "GBK");
      System.out.println(outputFileName);
      // 项目处理
      makeGeomBody("range", "TInt", "0", "整数", "%d", "1");
      makeGeomBody("range", "TFloat", "0.0f", "浮点数", "%f", "1.0f");
      makeGeomBody("range", "TDouble", "0", "双浮点数", "%llf", "1");

      makeGeomBody("size2", "TInt", "0", "整数", "%d", "1");
      makeGeomBody("size2", "TFloat", "0.0f", "浮点数", "%f", "1.0f");
      makeGeomBody("size2", "TDouble", "0", "双浮点数", "%llf", "1");
      makeGeomBody("size3", "TInt", "0", "整数", "%d", "1");
      makeGeomBody("size3", "TFloat", "0.0f", "浮点数", "%f", "1.0f");
      makeGeomBody("size3", "TDouble", "0", "双浮点数", "%llf", "1");

      makeGeomBody("point2", "TInt", "0", "整数", "%d", "1");
      makeGeomBody("point2", "TFloat", "0.0f", "浮点数", "%f", "1.0f");
      makeGeomBody("point2", "TDouble", "0", "双浮点数", "%llf", "1");
      makeGeomBody("point3", "TInt", "0", "整数", "%d", "1");
      makeGeomBody("point3", "TFloat", "0.0f", "浮点数", "%f", "1.0f");
      makeGeomBody("point3", "TDouble", "0", "双浮点数", "%llf", "1");
      makeGeomBody("point4", "TInt", "0", "整数", "%d", "1");
      makeGeomBody("point4", "TFloat", "0.0f", "浮点数", "%f", "1.0f");
      makeGeomBody("point4", "TDouble", "0", "双浮点数", "%llf", "1");

      makeGeomBody("vector2", "TInt", "0", "整数", "%d", "1");
      makeGeomBody("vector2", "TFloat", "0.0f", "浮点数", "%f", "1.0f");
      makeGeomBody("vector2", "TDouble", "0", "双浮点数", "%llf", "1");
      makeGeomBody("vector3", "TInt", "0", "整数", "%d", "1");
      makeGeomBody("vector3", "TFloat", "0.0f", "浮点数", "%f", "1.0f");
      makeGeomBody("vector3", "TDouble", "0", "双浮点数", "%llf", "1");
      makeGeomBody("vector4", "TInt", "0", "整数", "%d", "1");
      makeGeomBody("vector4", "TFloat", "0.0f", "浮点数", "%f", "1.0f");
      makeGeomBody("vector4", "TDouble", "0", "双浮点数", "%llf", "1");

      makeGeomBody("color4", "TFloat", "0.0f", "浮点数", "%f", "1.0f");

      makeGeomBody("matrix4x4", "TFloat", "0.0f", "浮点数", "%f", "1.0f");
      makeGeomBody("matrix4x4", "TDouble", "0", "双浮点数", "%llf", "1");

      makeGeomBody("matrix3d", "TFloat", "0.0f", "浮点数", "%f", "1.0f");
      makeGeomBody("matrix3d", "TDouble", "0", "双浮点数", "%llf", "1");
   }

   //============================================================
   // <T>启动处理。</T>
   //============================================================
   public static void main(String[] args){
      try{
         FDataset dataset = new FDataset();
         FRow row = new FRow();
         dataset.push(row);

         // 关联参数
         RApplication.linkArgements(args);
         // 获得设置
         String homeOption = RApplication.findArgement("-home", "D:/ZW-Platform.WK/mp-platform/webroot/WEB-INF/classes");
         String configOption = RApplication.findArgement("-config", "application-local") + ".xml";
         // 加载设置文件
         String configFileName = homeOption + "\\" + configOption;
         RAop.configConsole().loadFile(configFileName);
         // 获得模板控制台
         _templateConsole = RAop.find(ITemplateConsole.class);
         //............................................................
         // 生成代码
         makeGeom();
         //............................................................
         // 释放资源
         RAop.release();
      }catch(Exception e){
         _logger.error(RAopTest.class, "main", e);
      }
   }
}
