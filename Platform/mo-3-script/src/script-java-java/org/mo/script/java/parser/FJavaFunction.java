package org.mo.script.java.parser;

import org.mo.com.lang.RString;
import org.mo.com.lang.generic.TDumpInfo;
import org.mo.com.text.parser.FTextComment;
import org.mo.com.text.parser.FTextSource;
import org.mo.com.text.parser.FTextToken;
import org.mo.script.java.converter.FJavaConvertContent;
import org.mo.script.java.parser.source.FJavaSource;

//============================================================
// <T>函数对象。</T>
//============================================================
public class FJavaFunction
      extends FJavaObject
{
   // 类对象
   protected FJavaClass _class;

   // 注释信息
   protected FTextComment _comment = new FTextComment();

   // 权限类型
   protected String _accessCd;

   // 是否静态
   protected boolean _isStatic;

   // 是否覆盖
   protected boolean _isOverride;

   // 是否获得属性
   protected boolean _isGetter;

   // 是否设置属性
   protected boolean _isSetter;

   // 参数集合
   protected FJavaParameters _parameters = new FJavaParameters();

   // 返回类型名称
   protected String _returnTypeName;

   // 返回类型全称
   protected String _returnTypeFullName;

   // 文本段
   protected FTextToken _token;

   // 参数集合
   protected FJavaSource _source = new FJavaSource();

   //============================================================
   // <T>构造函数对象。</T>
   //============================================================
   public FJavaFunction(){
   }

   //============================================================
   // <T>获得类对象。</T>
   //
   // @return 类对象
   //============================================================
   public FJavaClass clazz(){
      return _class;
   }

   //============================================================
   // <T>设置类对象。</T>
   //
   // @param clazz 类对象
   //============================================================
   public void setClazz(FJavaClass clazz){
      _class = clazz;
   }

   //============================================================
   // <T>获得注释信息。</T>
   //
   // @return 注释信息
   //============================================================
   public FTextComment comment(){
      return _comment;
   }

   //============================================================
   // <T>获得权限类型。</T>
   //
   // @return 权限类型
   //============================================================
   public String accessCd(){
      return _accessCd;
   }

   //============================================================
   // <T>获得是否静态。</T>
   //
   // @return 是否静态
   //============================================================
   public boolean isStatic(){
      return _isStatic;
   }

   //============================================================
   // <T>获得是否覆盖。</T>
   //
   // @return 是否覆盖
   //============================================================
   public boolean isOverride(){
      return _isOverride;
   }

   //============================================================
   // <T>设置是否覆盖。</T>
   //
   // @param value 是否覆盖
   //============================================================
   public void setIsOverride(boolean value){
      _isOverride = value;
   }

   //============================================================
   // <T>获得是否获得属性。</T>
   //
   // @return 是否获得属性
   //============================================================
   public boolean isGetter(){
      return _isGetter;
   }

   //============================================================
   // <T>设置是否获得属性。</T>
   //
   // @param value 是否获得属性
   //============================================================
   public void setIsGetter(boolean value){
      _isGetter = value;
   }

   //============================================================
   // <T>获得是否设置属性。</T>
   //
   // @return 是否设置属性
   //============================================================
   public boolean isSetter(){
      return _isSetter;
   }

   //============================================================
   // <T>设置是否设置属性。</T>
   //
   // @param value 是否设置属性
   //============================================================
   public void setIsSetter(boolean value){
      _isSetter = value;
   }

   //============================================================
   // <T>获得参数集合。</T>
   //
   // @return 参数集合
   //============================================================
   public FJavaParameters parameters(){
      return _parameters;
   }

   //============================================================
   // <T>获得返回类型名称。</T>
   //
   // @return 返回类型名称
   //============================================================
   public String returnTypeName(){
      return _returnTypeName;
   }

   //============================================================
   // <T>获得返回类型全称。</T>
   //
   // @return 返回类型全称
   //============================================================
   public String returnTypeFullName(){
      return _returnTypeFullName;
   }

   //============================================================
   // <T>解析文件。</T>
   //============================================================
   public void parse(FJavaParserContent content, FTextToken token, String line){
      _token = token;
      // 去掉尾部
      if(RString.endsWith(line, ";")){
         line = line.substring(0, line.length() - 1);
      }
      // 分解代码
      String[] items = RString.split(line, ' ');
      int count = items.length;
      for(int n = 0; n < count; n++){
         String item = items[n];
         if(item.equals("private") || item.equals("protected") || item.equals("public")){
            // 解析权限
            _accessCd = item;
            continue;
         }else if(item.equals("static")){
            // 解析静态
            _isStatic = true;
            continue;
         }else if(item.equals("override")){
            // 解析覆盖
            _isOverride = true;
            continue;
         }else if(item.contains("(")){
            // 解析名称
            _name = RString.left(item, "(");
            break;
         }else{
            _returnTypeName = item;
            _returnTypeFullName = _class.makeTypeName(content, item);
         }
      }
      // 分解参数
      String paramLine = RString.mid(line, "(", ")");
      if(!RString.isEmpty(paramLine)){
         String[] paramItems = RString.split(paramLine, ',');
         for(String paramItem : paramItems){
            paramItem = paramItem.trim();
            if(!RString.isEmpty(paramItem)){
               FJavaParameter parameter = new FJavaParameter();
               parameter.setFunction(this);
               parameter.parse(content, paramItem);
               _parameters.push(parameter);
            }
         }
      }
      // 分解代码
      if(token.hasToken()){
         _source.parse(content, token);
      }
   }

   //============================================================
   // <T>解析处理。</T>
   //
   // @param content 处理环境
   //============================================================
   public void process(FJavaProcessContent content){
   }

   //============================================================
   // <T>转换代码内容。</T>
   //
   // @param content 转换环境
   // @param source 代码内容
   //============================================================
   public void convert(FJavaConvertContent content, FTextSource source){
      //............................................................
      // 生成类注释
      for(String line : _comment.sourceLines()){
         source.appendIndent();
         source.appendLine(line);
      }
      //............................................................
      // source.appendIndent();
      // source.appendLine("// " + _token.text());
      //............................................................
      // 生成类代码
      source.appendIndent();
      if(!RString.isEmpty(_accessCd)){
         source.append(_accessCd);
         source.append(' ');
      }
      if(_isStatic){
         source.append("static ");
      }
      if(_isOverride){
         source.append("override ");
      }
      source.append("function ");
      if(_isGetter){
         source.append("get ");
      }
      if(_isSetter){
         source.append("set ");
      }
      source.append(_name);
      source.append("(");
      _parameters.convert(content, source);
      source.append(")");
      if(!RString.isEmpty(_returnTypeName)){
         source.append(':');
         source.append(_returnTypeFullName);
      }
      //............................................................
      // 生成代码
      if(_class.isInterface()){
         source.append(";");
      }else{
         source.append("{");
         if(_source.hasSource()){
            _source.convert(content, source);
            source.appendLine();
         }else{
            source.appendLine();
         }
         //............................................................
         source.appendIndent();
         source.append("}");
      }
   }

   //============================================================
   // <T>生成运行信息。</T>
   //
   // @param info 运行信息
   // @return 运行信息
   //============================================================
   @Override
   public TDumpInfo dump(TDumpInfo info){
      info.appendIndent();
      info.append("AsFunction [");
      if(!RString.isEmpty(_accessCd)){
         info.append(_accessCd);
      }
      if(!RString.isEmpty(_returnTypeName)){
         info.append(' ');
         info.append(_returnTypeName);
      }
      info.append(' ');
      info.append(_name);
      info.append("]");
      return info;
   }
}
