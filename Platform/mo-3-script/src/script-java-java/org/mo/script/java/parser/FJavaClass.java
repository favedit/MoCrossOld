package org.mo.script.java.parser;

import org.mo.com.lang.RString;
import org.mo.com.lang.RStrings;
import org.mo.com.lang.generic.TDumpInfo;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.com.text.parser.FTextComment;
import org.mo.com.text.parser.FTextSource;
import org.mo.com.text.parser.FTextToken;
import org.mo.script.java.converter.FJavaConvertContent;

//============================================================
// <T>类对象。</T>
//============================================================
public class FJavaClass
      extends FJavaObject
{
   // 引用集合
   private static final ILogger _logger = RLogger.find(FJavaClass.class);

   // 包名称
   protected String _packageName;

   // 所属包
   protected FJavaPackage _package;

   // 引用集合
   protected FJavaImports _imports = new FJavaImports();

   // 注释
   protected FTextComment _comment = new FTextComment();

   // 类全称
   protected String _fullName;

   // 注释标题
   protected String _commentTitle;

   // 父类名称
   protected String _parentClassName;

   // 父类对象
   protected FJavaClass _parentClass;

   // 权限类型
   protected String _accessName;

   // 是否为接口
   protected boolean _isInterface;

   // 是否为最终
   protected boolean _isFinal;

   // 接口集合
   protected FJavaImplements _implemets = new FJavaImplements();

   // 字段集合
   protected FJavaFields _fields = new FJavaFields();

   // 函数集合
   protected FJavaFunctions _functions = new FJavaFunctions();

   // 文件名称
   protected String _fileName;

   //============================================================
   // <T>构造类对象。</T>
   //============================================================
   public FJavaClass(){
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
   // <T>获得父类名称。</T>
   //
   // @return 父类名称
   //============================================================
   protected String parentClassName(){
      return _parentClassName;
   }

   //============================================================
   // <T>获得父类对象。</T>
   //
   // @return 父类对象
   //============================================================
   protected FJavaClass parentClass(){
      return _parentClass;
   }

   //============================================================
   // <T>获得字段集合。</T>
   //
   // @return 字段集合
   //============================================================
   public FJavaFields fields(){
      return _fields;
   }

   //============================================================
   // <T>获得函数集合。</T>
   //
   // @return 函数集合
   //============================================================
   public FJavaFunctions functions(){
      return _functions;
   }

   //============================================================
   // <T>获得文件名称。</T>
   //
   // @return 文件名称
   //============================================================
   public String fileName(){
      return _fileName;
   }

   //============================================================
   // <T>设置文件名称。</T>
   //
   // @param fileName 文件名称
   //============================================================
   public void setFileName(String fileName){
      _fileName = fileName;
   }

   //============================================================
   // <T>根据类名称，获得类对象。</T>
   //
   // @param content 环境
   // @param name 类名称
   // @return 类对象
   //============================================================
   public FJavaClass findClass(FJavaContent content, String className){
      // 获取类名称
      FJavaClass javaClass = null;
      if(!RString.contains(className, ".")){
         // 查找导入类
         String fullName = null;
         FJavaImport javaImport = content.imports().find(className);
         if(null != javaImport){
            fullName = javaImport.name();
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
   // <T>根据类名称，获得类对象。</T>
   //
   // @param content 环境
   // @param name 类名称
   // @return 类对象
   //============================================================
   public boolean testOverride(FJavaContent content, String methodName){
      FJavaClass javaClass = _parentClass;
      while(null != javaClass){
         if(javaClass.functions().contains(methodName)){
            return true;
         }
         javaClass = javaClass.parentClass();
      }
      return false;
   }

   //============================================================
   // <T>获得类型名称。</T>
   //
   // @param source 代码
   //============================================================
   public String makeTypeName(FJavaParserContent content, String name){
      // 获取类名称
      String typeName = RJavaParser.makeTypeName(name);
      if(RString.isEmpty(typeName)){
         FJavaImport asImport = _imports.find(name);
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
         //String subTypeName = RString.mid(typeName, "<", ">");
         //String SubTypeClass = makeTypeName(content, subTypeName);
         return "org.mo.cross.lang.FObjects";

      }
      return typeName;
   }

   //============================================================
   // <T>解析代码。</T>
   //
   // @param source 代码
   //============================================================
   public void parseSource(String source){
      String[] lines = RStrings.splitTrimNotEmpty(source, ' ');
      int count = lines.length;
      for(int n = 0; n < count; n++){
         String line = lines[n];
         // 获得权限
         if(line.equals("private") || line.equals("protected") || line.equals("public")){
            _accessName = line;
            continue;
         }
         // 是否为终止
         if(line.equals("final")){
            _isFinal = true;
            continue;
         }
         // 是否为接口
         if(line.equals("interface")){
            _isInterface = true;
            // 读取接口信息
            _name = lines[n + 1];
            for(n += 2; n < count; n++){
               line = lines[n];
               // 获得接口集合
               if(line.equals("extends")){
                  for(n += 1; n < count; n++){
                     line = lines[n];
                     FJavaImplement javaImplement = new FJavaImplement();
                     javaImplement.setName(line);
                     _implemets.push(javaImplement);
                  }
               }
            }
            continue;
         }
         if(line.equals("class")){
            // 读取类信息
            _name = lines[n + 1];
            for(n += 2; n < count; n++){
               line = lines[n];
               // 获得父类名
               if(line.equals("extends")){
                  _parentClassName = lines[++n];
               }
               // 获得接口集合
               if(line.equals("implements")){
                  for(n += 1; n < count; n++){
                     line = lines[n];
                     FJavaImplement javaImplement = new FJavaImplement();
                     javaImplement.setName(line);
                     _implemets.push(javaImplement);
                  }
               }
            }
         }
      }
   }

   //============================================================
   // <T>解析文件。</T>
   //============================================================
   public void parseChild(FJavaParserContent content, FTextToken token){
      String[] lines = token.lines();
      int lineCount = lines.length;
      for(int lineNumber = 0; lineNumber < lineCount; lineNumber++){
         String line = lines[lineNumber];
         // 解析注释
         FTextComment comment = new FTextComment();
         if(RJavaParser.isComment(line)){
            for(; lineNumber < lineCount; lineNumber++){
               line = lines[lineNumber];
               if(RJavaParser.isComment(line)){
                  comment.push(line);
               }else{
                  break;
               }
            }
         }
         // 解析字段
         if(line.equals("@ACoField")){
            line = lines[++lineNumber];
            // 解析函数
            FJavaField field = new FJavaField();
            field.setClazz(this);
            field.comment().assign(comment);
            field.parse(content, token, line);
            _fields.push(field);
         }
         // 解析函数
         if(line.equals("@ACoGetter") || line.equals("@ACoSetter") || line.equals("@ACoFunction")){
            boolean isOverride = false;
            boolean isGetter = false;
            boolean isSetter = false;
            if(line.equals("@ACoGetter")){
               isGetter = true;
            }
            if(line.equals("@ACoSetter")){
               isSetter = true;
            }
            line = lines[++lineNumber];
            if(line.equals("@Override")){
               isOverride = true;
               line = lines[++lineNumber];
            }
            // 解析函数
            FJavaFunction function = new FJavaFunction();
            function.setClazz(this);
            function.setIsOverride(isOverride);
            function.setIsGetter(isGetter);
            function.setIsSetter(isSetter);
            function.comment().assign(comment);
            function.parse(content, token, line);
            _functions.set(function.name(), function);
         }
      }
   }

   //============================================================
   // <T>解析文件。</T>
   //============================================================
   public void parse(FJavaParserContent content, FTextToken token){
      // 读取包信息
      FTextToken packageToken = token.tokens().first();
      String[] lines = packageToken.lines();
      int lineCount = lines.length;
      for(int lineNumber = 0; lineNumber < lineCount; lineNumber++){
         String line = lines[lineNumber];
         // 解析注释
         if(RJavaParser.isComment(line)){
            for(; lineNumber < lineCount; lineNumber++){
               line = lines[lineNumber];
               if(RJavaParser.isComment(line)){
                  _comment.push(line);
               }else{
                  break;
               }
            }
         }
         // 解析代码
         if(line.startsWith("package ")){
            // 读取包信息
            _packageName = RString.mid(line, "package ", ";").trim();
            _package = content.syncPackage(_packageName);
         }else if(RString.startsWith(line, "import ")){
            // 读取导入库
            FJavaImport javaImport = new FJavaImport();
            javaImport.parse(content, packageToken, line);
            _imports.push(javaImport);

         }else if(line.contains(" class ") || line.contains(" interface ")){
            // 解析类信息
            String source = RString.join(lines, lineNumber, lineCount - lineNumber, ' ');
            parseSource(source);
            _package.push(this);
            // 解析函数
            if(packageToken.hasToken()){
               int count = packageToken.tokens().count();
               for(int n = 0; n < count; n++){
                  FTextToken childToken = packageToken.tokens().get(n);
                  parseChild(content, childToken);
               }
            }
         }
      }
      // 设置全称
      _fullName = _packageName + "." + _name;
   }

   //============================================================
   // <T>解析处理。</T>
   //
   // @param content 处理环境
   //============================================================
   public void process(FJavaProcessContent content){
      // 获得继承类
      if(!RString.isEmpty(_parentClassName)){
         _parentClass = findClass(content, _parentClassName);
      }
      // 获得接口集合
      if(!_implemets.isEmpty()){
         for(FJavaImplement javaImplemet : _implemets){
            String className = javaImplemet.name();
            FJavaClass javaClass = findClass(content, className);
            javaImplemet.setJavaClass(javaClass);
         }
      }
      // 解析所有字段
      for(FJavaField field : _fields){
         field.process(content);
      }
      // 解析所有函数
      for(FJavaFunction function : _functions.toObjects()){
         if(function.isOverride()){
            boolean isOverride = testOverride(content, function.name());
            if(!isOverride){
               function.setIsOverride(isOverride);
            }
         }
         function.process(content);
      }
   }

   //============================================================
   // <T>转换代码内容。</T>
   //
   // @param content 转换环境
   // @param source 代码内容
   //============================================================
   public void convert(FJavaConvertContent content, FTextSource source){
      //............................................................
      // 生成包名
      source.append("package ");
      source.appendLine(_packageName);
      source.appendLine("{");
      source.increase();
      //............................................................
      // 生成引用
      if(!_imports.isEmpty()){
         for(FJavaImport asImport : _imports){
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
         source.append(_parentClassName);
      }
      if(!_implemets.isEmpty()){
         source.append(" implements ");
         int count = _implemets.count();
         for(int n = 0; n < count; n++){
            FJavaImplement javaImplement = _implemets.get(n);
            if(n > 0){
               source.append(',');
            }
            source.append(javaImplement.name());
         }
      }
      source.appendLine();
      source.appendIndent();
      source.append("{");
      source.increase();
      // 生成字段集合
      for(FJavaField field : _fields){
         source.appendLine();
         field.convert(content, source);
         source.appendLine();
      }
      // 生成字段集合
      int functionCount = _functions.count();
      for(int n = 0; n < functionCount; n++){
         FJavaFunction function = _functions.value(n);
         source.appendLine();
         function.convert(content, source);
         source.appendLine();
      }
      source.decrease();
      source.appendIndent();
      source.appendLine("}");
      // 包结束
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
            FJavaImplement javaImplement = _implemets.get(n);
            if(n > 0){
               info.append(',');
            }
            info.append(javaImplement.name());
         }
      }
      info.append("]");
      // 生成字段集合
      for(FJavaField field : _fields){
         info.appendLine();
         info.increaseLevel(field);
         field.dump(info);
         info.decreaseLevel();
      }
      // 生成字段集合
      int functionCount = _functions.count();
      for(int n = 0; n < functionCount; n++){
         FJavaFunction function = _functions.value(n);
         info.appendLine();
         info.increaseLevel(function);
         function.dump(info);
         info.decreaseLevel();
      }
      return info;
   }
}
