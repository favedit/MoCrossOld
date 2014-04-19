package org.mo.script.as.parser;

import org.mo.com.lang.RString;
import org.mo.com.lang.generic.TDumpInfo;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.com.text.parser.FTextComment;
import org.mo.com.text.parser.FTextSource;
import org.mo.com.text.parser.FTextToken;
import org.mo.script.as.common.FAsSourceDirectory;
import org.mo.script.as.converter.FAsConvertContent;

//============================================================
// <T>类对象。</T>
//============================================================
public class FAsClass
      extends FAsObject
{
   // 日志输出接口
   private static final ILogger _logger = RLogger.find(FAsClass.class);

   // 引用集合
   protected FAsImports _imports = new FAsImports();

   // 注释
   protected FTextComment _comment = new FTextComment();

   // 类全称
   protected String _fullName;

   // 包名称
   protected String _packageName;

   // 所属包
   protected FAsPackage _package;

   // 父函数名称
   protected String _parentClassName;

   // 父函数名称
   protected String _parentClassFullName;

   // 父类对象
   protected FAsClass _parentClass;

   // 是否为接口
   protected boolean _isInterface;

   // 是否为最终
   protected boolean _isFinal;

   // 接口集合
   protected FAsImplements _implemets = new FAsImplements();

   // 字段集合
   protected FAsFields _fields = new FAsFields();

   // 函数集合
   protected FAsFunctions _functions = new FAsFunctions();

   // 代码目录
   protected FAsSourceDirectory _sourceDirectory;

   // 来源文件名称
   protected String _sourceFileName;

   // 目标文件名称
   protected String _targetFileName;

   //============================================================
   // <T>构造类对象。</T>
   //============================================================
   public FAsClass(){
   }

   //============================================================
   // <T>获得全称。</T>
   //
   // @return 全称
   //============================================================
   public String fullName(){
      return _fullName;
   }

   //============================================================
   // <T>判断是否为接口。</T>
   //
   // @return 是否为接口
   //============================================================
   public boolean isInterface(){
      return _isInterface;
   }

   //============================================================
   // <T>获得字段集合。</T>
   //
   // @return 字段集合
   //============================================================
   public FAsFields fields(){
      return _fields;
   }

   //============================================================
   // <T>获得函数集合。</T>
   //
   // @return 函数集合
   //============================================================
   public FAsFunctions functions(){
      return _functions;
   }

   //============================================================
   // <T>获得代码目录。</T>
   //
   // @return 代码目录
   //============================================================
   public FAsSourceDirectory sourceDirectory(){
      return _sourceDirectory;
   }

   //============================================================
   // <T>设置代码目录。</T>
   //
   // @param directory 代码目录
   //============================================================
   public void setSourceDirectory(FAsSourceDirectory directory){
      _sourceDirectory = directory;
   }

   //============================================================
   // <T>获得来源文件名称。</T>
   //
   // @return 文件名称
   //============================================================
   public String sourceFileName(){
      return _sourceFileName;
   }

   //============================================================
   // <T>设置来源文件名称。</T>
   //
   // @param fileName 文件名称
   //============================================================
   public void setSourceFileName(String fileName){
      _sourceFileName = fileName;
   }

   //============================================================
   // <T>获得目标文件名称。</T>
   //
   // @return 文件名称
   //============================================================
   public String targetFileName(){
      return _targetFileName;
   }

   //============================================================
   // <T>设置目标文件名称。</T>
   //
   // @param fileName 目标文件名称
   //============================================================
   public void setTargetFileName(String fileName){
      _targetFileName = fileName;
   }

   //============================================================
   // <T>获得类型名称。</T>
   //
   // @param source 代码
   //============================================================
   public String makeTypeName(FAsParserContent content, String name){
      // 获取类名称
      String typeName = RAsParser.makeTypeName(name);
      if(RString.isEmpty(typeName)){
         FAsImport asImport = _imports.find(name);
         if(null != asImport){
            typeName = asImport.name();
         }else{
            asImport = content.imports().find(name);
            if(null != asImport){
               typeName = asImport.name();
            }
         }
      }
      // 获取错误处理
      if(RString.isEmpty(typeName)){
         _logger.error(this, "makeTypeName", "Unknown type name. (type_name={1})", name);
         return name;
      }
      // 处理集合对象
      if(typeName.startsWith("Vector.<")){
         if(typeName.equals("Vector.<int>")){
            return "org.mc.common.lang.FInts";
         }else if(typeName.equals("Vector.<uint>")){
            return "org.mc.common.lang.FUints";
         }else if(typeName.equals("Vector.<double>")){
            return "org.mc.common.lang.FDoubles";
         }else{
            return "org.mc.common.lang.FObjects";
         }
      }
      return typeName;
   }

   //============================================================
   // <T>根据类名称，获得类对象。</T>
   //
   // @param content 环境
   // @param name 类名称
   // @return 类对象
   //============================================================
   public FAsClass findClass(FAsContent content, String className){
      // 获取类名称
      FAsClass javaClass = null;
      if(!RString.contains(className, ".")){
         // 查找导入类
         String fullName = null;
         FAsImport asImport = content.imports().find(className);
         if(null != asImport){
            fullName = asImport.name();
         }else{
            fullName = _packageName + "." + className;
         }
         javaClass = content.classes().find(fullName);
      }else{
         javaClass = content.classes().find(className);
      }
      if(null == javaClass){
         _logger.error(this, "process", "Can't find implement class. (class_name={1})", className);
      }
      return javaClass;
   }

   //============================================================
   // <T>解析代码。</T>
   //
   // @param source 代码
   //============================================================
   public void parseSource(String source){
      String[] lines = RString.split(source, ';');
      for(String line : lines){
         // 是否为终止
         if(RString.contains(line, " final ")){
            _isFinal = true;
            line = RString.replace(line, " final ", " ");
         }
         // 分解行
         if(RString.startsWith(line, "import ")){
            // 读取导入库
            FAsImport asImport = new FAsImport();
            asImport.setName(RString.right(line, " "));
            _imports.push(asImport);
         }else if(RString.startsWith(line, "public interface ")){
            _isInterface = true;
            // 读取接口信息
            String name = line.substring("public interface ".length());
            String[] items = RString.split(name, " ");
            int itemCount = items.length;
            for(int n = 0; n < itemCount; n++){
               String item = items[n];
               if(0 == n){
                  // 获得类名
                  _name = item;
               }else if(item.endsWith("extends")){
                  // 获得接口集合
                  String implementValue = items[n + 1];
                  String[] implementValues = implementValue.split(",");
                  for(String value : implementValues){
                     FAsImplement asImplement = new FAsImplement();
                     asImplement.setName(value);
                     _implemets.push(asImplement);
                  }
               }
            }
         }else if(RString.startsWith(line, "public class ")){
            // 读取类信息
            String name = line.substring("public class ".length());
            String[] items = RString.split(name, " ");
            int itemCount = items.length;
            for(int n = 0; n < itemCount; n++){
               String item = items[n];
               if(0 == n){
                  // 获得类名
                  _name = item;
               }else if(item.endsWith("extends")){
                  // 获得父类名
                  _parentClassName = items[n + 1];
               }else if(item.endsWith("implements")){
                  // 获得接口集合
                  String implementValue = items[n + 1];
                  String[] implementValues = implementValue.split(",");
                  for(String value : implementValues){
                     FAsImplement asImplement = new FAsImplement();
                     asImplement.setName(value);
                     _implemets.push(asImplement);
                  }
               }
            }
         }
      }
      // 设置全称
      if(RString.isEmpty(_packageName)){
         _fullName = _name;
      }else{
         _fullName = _packageName + "." + _name;
      }
   }

   //============================================================
   // <T>解析文件。</T>
   //============================================================
   public void parse(FAsParserContent content, FTextToken token){
      // 读取包信息
      FTextToken packageToken = token.tokens().first();
      String packageText = packageToken.text();
      if(packageText.startsWith("package ")){
         _packageName = packageText.substring("package ".length());
         _package = content.syncPackage(_packageName);
         // 解析类信息
         FTextToken classToken = packageToken.tokens().first();
         for(String line : classToken.lines()){
            if(RAsParser.isComment(line)){
               _comment.push(line);
            }
         }
         parseSource(classToken.text());
         _package.push(this);
         // 解析函数
         if(token.hasToken()){
            int count = classToken.tokens().count();
            for(int n = 0; n < count; n++){
               FTextToken childToken = classToken.tokens().get(n);
               String[] lines = childToken.lines();
               int lineCount = lines.length;
               for(int lineNumber = 0; lineNumber < lineCount; lineNumber++){
                  String line = lines[lineNumber];
                  // 解析注释
                  FTextComment comment = new FTextComment();
                  if(RAsParser.isComment(line)){
                     for(; lineNumber < lineCount; lineNumber++){
                        line = lines[lineNumber];
                        if(RAsParser.isComment(line)){
                           comment.push(line);
                        }else{
                           break;
                        }
                     }
                  }
                  // 解析代码
                  if(RString.contains(line, "function ")){
                     // 解析函数
                     FAsFunction function = new FAsFunction();
                     function.setOwnerClass(this);
                     function.comment().assign(comment);
                     if(!RString.contains(line, ")")){
                        for(; lineNumber < lineCount; lineNumber++){
                           String nextLine = lines[lineNumber];
                           if(RString.contains(nextLine, ")")){
                              line += nextLine;
                              break;
                           }
                        }
                     }
                     function.parse(content, childToken, line);
                     _functions.set(function.name(), function);
                  }else if(RString.contains(line, "var ")){
                     // 解析字段
                     FAsField field = new FAsField();
                     field.setClazz(this);
                     field.comment().assign(comment);
                     field.parse(content, childToken, line);
                     _fields.push(field);
                  }else{
                     _logger.error(this, "parse", "Parse unknown line. (line={1})", line);
                  }
               }
            }
         }
      }
   }

   //============================================================
   // <T>解析处理。</T>
   //
   // @param content 处理环境
   //============================================================
   public void process(FAsProcessContent content){
      // 获得继承类
      if(!RString.isEmpty(_parentClassName)){
         _parentClass = findClass(content, _parentClassName);
      }
      // 获得接口集合
      if(!_implemets.isEmpty()){
         for(FAsImplement asImplemet : _implemets){
            String className = asImplemet.name();
            FAsClass asClass = findClass(content, className);
            asImplemet.setAsClass(asClass);
         }
      }
      // 解析所有字段
      for(FAsField field : _fields){
         field.process(content);
      }
      // 解析所有函数
      for(FAsFunction function : _functions.toObjects()){
         //         if(function.isOverride()){
         //            boolean isOverride = testOverride(content, function.name());
         //            if(!isOverride){
         //               function.setIsOverride(isOverride);
         //            }
         //         }
         function.process(content);
      }
   }

   //============================================================
   // <T>转换代码内容。</T>
   //
   // @param content 转换环境
   // @param source 代码内容
   //============================================================
   public void convert(FAsConvertContent content, FTextSource source){
      //............................................................
      // 生成包名
      source.appendLine("package " + _packageName + ";\n");
      //............................................................
      // 生成引用
      if(!_imports.isEmpty()){
         for(FAsImport asImport : _imports){
            String importName = asImport.name();
            importName = content.convertTypeName(importName);
            source.appendIndent();
            source.appendLine("import " + importName + ";");
         }
         source.appendLine();
      }
      //............................................................
      // 生成注释
      for(String line : _comment.sourceLines()){
         source.appendIndent();
         source.appendLine(line);
      }
      //............................................................
      // 生成类名
      source.appendIndent();
      source.append("public");
      if(_isInterface){
         source.append(" interface");
      }else{
         source.append(" class");
      }
      source.append(" " + _name);
      if(!RString.isEmpty(_parentClassName)){
         source.append(" extends ");
         String className = _parentClassName;
         if(null != _parentClass){
            className = _parentClass.fullName();
         }
         String convertClassName = content.convertTypeName(className);
         source.append(convertClassName);
      }
      if(!_implemets.isEmpty()){
         source.append(" implements ");
         int count = _implemets.count();
         for(int n = 0; n < count; n++){
            FAsImplement asImplement = _implemets.get(n);
            if(n > 0){
               source.append(',');
            }
            source.append(asImplement.name());
         }
      }
      source.appendLine();
      source.append("{");
      source.increase();
      // 生成字段集合
      for(FAsField field : _fields){
         source.appendLine();
         field.convert(content, source);
         source.appendLine();
      }
      // 生成字段集合
      int functionCount = _functions.count();
      for(int n = 0; n < functionCount; n++){
         FAsFunction function = _functions.value(n);
         source.appendLine();
         function.convert(content, source);
         source.appendLine();
      }
      source.decrease();
      source.appendLine("}");
   }

   //============================================================
   // <T>生成运行信息。</T>
   //
   // @param info 运行信息
   // @return 运行信息
   //============================================================
   @Override
   public TDumpInfo dump(TDumpInfo info){
      // 生成类信息
      info.append("AsClass [");
      info.append(_name);
      if(!RString.isEmpty(_parentClassName)){
         info.append(" extends ");
         info.append(_parentClassName);
      }
      if(!_implemets.isEmpty()){
         info.append(" implements ");
         int count = _implemets.count();
         for(int n = 0; n < count; n++){
            FAsImplement asImplement = _implemets.get(n);
            if(n > 0){
               info.append(',');
            }
            info.append(asImplement.name());
         }
      }
      info.append("]");
      // 生成字段集合
      for(FAsField field : _fields){
         info.appendLine();
         info.increaseLevel(field);
         field.dump(info);
         info.decreaseLevel();
      }
      // 生成字段集合
      int functionCount = _functions.count();
      for(int n = 0; n < functionCount; n++){
         FAsFunction function = _functions.value(n);
         info.appendLine();
         info.increaseLevel(function);
         function.dump(info);
         info.decreaseLevel();
      }
      return info;
   }
}
