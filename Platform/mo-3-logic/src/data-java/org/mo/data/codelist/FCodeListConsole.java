package org.mo.data.codelist;

import org.mo.com.data.FSql;
import org.mo.com.io.FStringFile;
import org.mo.com.io.RFile;
import org.mo.com.lang.FObjects;
import org.mo.com.lang.FString;
import org.mo.com.lang.cultrue.REncoding;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.com.xml.EXmlConfig;
import org.mo.com.xml.FXmlNode;
import org.mo.com.xml.IXmlObject;
import org.mo.core.aop.face.ALink;
import org.mo.core.aop.face.AProperty;
import org.mo.data.codelist.common.FCodeListSourceBuilder;
import org.mo.data.codelist.common.XCodeList;
import org.mo.eng.store.FXmlConfigConsole;
import org.mo.eng.template.ITemplateConsole;

public class FCodeListConsole
      extends FXmlConfigConsole<IXmlObject>
      implements
         ICodeListConsole
{
   private static ILogger _logger = RLogger.find(FCodeListConsole.class);

   @ALink
   private ITemplateConsole _templateConsole;

   @AProperty
   protected String _sourcepath;

   @AProperty
   protected String _sourceSqlLogic;

   @AProperty
   protected String _enum;

   @Override
   public void build(FCodeListBuilderArgs args){
      // 获取传过来的类型（FXmlNode,XDataset类型）
      ECodeListSource esource = args.type();
      ECodeListBuildAction eaction = args.action();
      // 获得设置信息
      IXmlObject xcodelist = args.instance();
      String codelistName = xcodelist.innerGet(XCodeList.PTY_NAME);
      // 建立XML节点信息
      FXmlNode codelist = convertInstanceToNode(xcodelist, EXmlConfig.Simple);
      // 建立模板控制台
      FCodeListSourceBuilder sourceBuilder = new FCodeListSourceBuilder(_templateConsole);
      // 建立数据集对应的包头的操作
      if(ECodeListSource.AllPackageHead == esource || ECodeListSource.PackageHead == esource){
         if(ECodeListBuildAction.Query == eaction){
            args.setSource(sourceBuilder.makePackageHead(codelist));
         }else if(ECodeListBuildAction.Execute == eaction){
            args.setSource(sourceBuilder.makePackageHeadSql(codelist));
         }else if(ECodeListBuildAction.Store == eaction){
            FString argsSource = args.source();
            args.setSource(sourceBuilder.makePackageHead(codelist));
            //String packageName = RString.replace(codelistName, '.', '_');
            //String filename = RFile.format(_sourceSqlLogic + "/" + packageName + ".pkh");
            //RFile.saveToFile(filename, args.source(), REncoding.UTF8);
            //_logger.info(this, "build", "Build plsql package head (file={0})", filename);
            // 生成枚举包头文件
            String installFileName = RFile.format(_enum + codelistName + "/install_package_head.pkh");
            RFile.saveToFile(installFileName, argsSource, REncoding.UTF8);
            _logger.info(this, "build", "Build plsql package head (file={0})", installFileName);
            // 卸载枚举包头
            String uninstallSqlFileName = RFile.format(_enum + codelistName + "/uninstall_package_head.sql");
            FSql sql = new FSql();
            sql.append("DROP PACKAGE " + codelist.get("data_name") + "_EI");
            FStringFile file = new FStringFile();
            file.append(sql);
            RFile.saveToFile(uninstallSqlFileName, file, REncoding.UTF8);
            _logger.info(this, "build", "Build delete codelist package head sql (file={0})", installFileName);
         }
      }
      // 建立数据集对应的包体的操作
      if(ECodeListSource.AllPackageBody == esource || ECodeListSource.PackageBody == esource){
         if(ECodeListBuildAction.Query == eaction){
            args.setSource(sourceBuilder.makePackageBody(codelist));
         }else if(ECodeListBuildAction.Execute == eaction){
            args.setSource(sourceBuilder.makePackageBodySql(codelist));
         }else if(ECodeListBuildAction.Store == eaction){
            FString argsSource = args.source();
            args.setSource(sourceBuilder.makePackageBody(codelist));
            //String packageName = RString.replace(codelistName, '.', '_');
            //String filename = RFile.format(_sourceSqlLogic + "/" + packageName + ".pky");
            //RFile.saveToFile(filename, args.source(), REncoding.UTF8);
            //_logger.info(this, "build", "Build plsql package body (file={0})", filename);
            // 生成枚举包体文件
            String installFileName = RFile.format(_enum + codelistName + "/install_package_body.pky");
            RFile.saveToFile(installFileName, argsSource, REncoding.UTF8);
            _logger.info(this, "build", "Build plsql package head (file={0})", installFileName);
         }
      }
      //      // 建立数据集对应的所有包头的操作
      //      if(ECodeListSource.All == esource || ECodeListSource.AllPackageHead == esource){
      //         args.setSource(sourceBuilder.makePackageHeadSql(codelist));
      //         FString argsSource = args.source();
      //         args.setSource(sourceBuilder.makePackageHead(codelist));
      //         //String packageName = RString.replace(codelistName, '.', '_');
      //         //String filename = RFile.format(_sourceSqlLogic + "/" + packageName + ".pkh");
      //         //RFile.saveToFile(filename, args.source(), REncoding.UTF8);
      //         //_logger.info(this, "build", "Build plsql package head (file={0})", filename);
      //         // 生成枚举包头文件
      //         String installFileName = RFile.format(_enum + codelistName + "/install_package_head.pkh");
      //         RFile.saveToFile(installFileName, argsSource, REncoding.UTF8);
      //         _logger.info(this, "build", "Build plsql package head (file={0})", installFileName);
      //         // 卸载枚举包头
      //         String uninstallSqlFileName = RFile.format(_enum + codelistName + "/uninstall_package_head.sql");
      //         FSql sql = new FSql();
      //         sql.append("DROP PACKAGE " + codelist.get("data_name") + "_EI");
      //         FStringFile file = new FStringFile();
      //         file.append(sql);
      //         RFile.saveToFile(uninstallSqlFileName, file, REncoding.UTF8);
      //         _logger.info(this, "build", "Build delete codelist package head sql (file={0})", uninstallSqlFileName);
      //      }
      //      // 建立数据集对应的所有包体的操作
      //      if(ECodeListSource.All == esource || ECodeListSource.AllPackageBody == esource){
      //         args.setSource(sourceBuilder.makePackageBodySql(codelist));
      //         FString argsSource = args.source();
      //         args.setSource(sourceBuilder.makePackageBody(codelist));
      //         //String packageName = RString.replace(codelistName, '.', '_');
      //         //String filename = RFile.format(_sourceSqlLogic + "/" + packageName + ".pky");
      //         //RFile.saveToFile(filename, args.source(), REncoding.UTF8);
      //         //_logger.info(this, "build", "Build plsql package body (file={0})", filename);
      //         // 生成枚举包体文件
      //         String installFileName = RFile.format(_enum + codelistName + "/install_package_body.pky");
      //         RFile.saveToFile(installFileName, argsSource, REncoding.UTF8);
      //         _logger.info(this, "build", "Build plsql package head (file={0})", installFileName);
      //      }
   }

   @Override
   protected FObjects<IXmlObject> createCollection(){
      return new FObjects<IXmlObject>(IXmlObject.class);
   }
}
