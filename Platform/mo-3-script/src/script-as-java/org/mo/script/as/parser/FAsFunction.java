package org.mo.script.as.parser;

import org.mo.com.lang.RString;
import org.mo.com.lang.RStrings;
import org.mo.com.lang.generic.TDumpInfo;
import org.mo.com.text.parser.FTextComment;
import org.mo.com.text.parser.FTextSource;
import org.mo.com.text.parser.FTextToken;
import org.mo.script.as.converter.FAsConvertContent;
import org.mo.script.as.parser.source.FAsSource;

//============================================================
// <T>函数对象。</T>
//============================================================
public class FAsFunction
      extends FAsObject
{
   // 类对象
   protected FAsClass _ownerClass;

   // 注释信息
   protected FTextComment _comment = new FTextComment();

   // 权限类型
   protected String _accessCd;

   // 类型名称
   protected String _typeName;

   // 类型全称
   protected String _typeFullName;

   // 是否静态
   protected boolean _isStatic;

   // 是否付给
   protected boolean _isOverride;

   // 是否获得属性
   protected boolean _isGetter;

   // 是否设置属性
   protected boolean _isSetter;

   // 文本段
   protected FTextToken _token;

   // 参数集合
   protected FAsParameters _parameters = new FAsParameters();

   // 变量集合
   protected FAsVariables _variables = new FAsVariables();

   // 参数集合
   protected FAsSource _source = new FAsSource();

   //============================================================
   // <T>构造函数对象。</T>
   //============================================================
   public FAsFunction(){
   }

   //============================================================
   // <T>获得类对象。</T>
   //
   // @return 类对象
   //============================================================
   public FAsClass ownerClass(){
      return _ownerClass;
   }

   //============================================================
   // <T>设置类对象。</T>
   //
   // @param clazz 类对象
   //============================================================
   public void setOwnerClass(FAsClass clazz){
      _ownerClass = clazz;
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
   // <T>获得参数集合。</T>
   //
   // @return 参数集合
   //============================================================
   public FAsParameters parameters(){
      return _parameters;
   }

   //============================================================
   // <T>获得变量集合。</T>
   //
   // @return 变量集合
   //============================================================
   public FAsVariables variables(){
      return _variables;
   }

   //============================================================
   // <T>根据变量名称查找变量类型名称。</T>
   //
   // @param name 变量名称
   // @return 类型名称
   //============================================================
   public String findVariableClassName(String name){
      FAsParameter parameter = _parameters.find(name);
      if(null != parameter){
         return parameter.typeName();
      }
      FAsVariable variable = _variables.find(name);
      if(null != variable){
         return variable.typeName();
      }
      FAsField field = _ownerClass.fields().find(name);
      if(null != field){
         return field.typeName();
      }
      return null;
      //throw new FFatalError("Unknown variable name");
   }

   //============================================================
   // <T>解析文件。</T>
   //============================================================
   public void parse(FAsParserContent content, FTextToken token, String line){
      _token = token;
      // 去掉结尾
      if(RString.endsWith(line, ";")){
         line = line.substring(0, line.length() - 1);
      }
      // 获得返回类型
      if(RString.contains(line, ')')){
         String typeText = RString.right(line, ")");
         if(RString.contains(typeText, ':')){
            _typeName = RString.right(typeText, ":").trim();
            _typeFullName = _ownerClass.makeTypeName(content, _typeName);
         }
      }
      // 分解代码
      String[] items = RStrings.splitTrimNotEmpty(line, ' ');
      int count = items.length;
      for(int n = 0; n < count; n++){
         String item = items[n];
         if(item.equals("private") || item.equals("protected") || item.equals("public")){
            _accessCd = item;
            continue;
         }
         if(item.equals("static")){
            _isStatic = true;
            continue;
         }
         if(item.equals("override")){
            _isOverride = true;
            continue;
         }
         if(item.equals("function")){
            String functionName = items[n + 1];
            // 判断是否属性
            if(functionName.equals("get")){
               _isGetter = true;
               functionName = items[n + 2];
            }else if(functionName.equals("set")){
               _isSetter = true;
               functionName = items[n + 2];
            }
            // 获得函数名称
            if(RString.contains(functionName, '(')){
               _name = RString.left(functionName, "(");
            }else{
               _name = functionName;
            }
            continue;
         }
      }
      // 分解参数
      String paramLine = RString.mid(line, "(", ")");
      if(!RString.isEmpty(paramLine)){
         String[] paramItems = RString.split(paramLine, ',');
         for(String paramItem : paramItems){
            paramItem = paramItem.trim();
            if(!RString.isEmpty(paramItem)){
               FAsParameter parameter = new FAsParameter();
               parameter.setFunction(this);
               parameter.parse(content, paramItem);
               _parameters.push(parameter);
            }
         }
      }
      // 分解代码
      if(token.hasToken()){
         _source.setOwnerClass(_ownerClass);
         _source.setOwnerFunction(this);
         _source.parse(content, token);
      }
   }

   //============================================================
   // <T>解析处理。</T>
   //
   // @param content 处理环境
   //============================================================
   public void process(FAsProcessContent content){
   }

   //============================================================
   // <T>转换代码内容。</T>
   //
   // @param content 转换环境
   // @param source 代码内容
   //============================================================
   public void convert(FAsConvertContent content, FTextSource source){
      //............................................................
      // 生成类注释
      for(String line : _comment.sourceLines()){
         source.appendIndent();
         source.appendLine(line);
      }
      //............................................................
      source.appendIndent();
      if(!_ownerClass.isInterface()){
         if(!RString.isEmpty(_accessCd)){
            source.append(_accessCd);
            source.append(' ');
         }
         if(_isStatic){
            source.append("static ");
         }
      }
      if(!RString.isEmpty(_typeName)){
         source.append(_typeFullName);
         source.append(' ');
      }
      source.append(_name);
      source.append("(");
      _parameters.convert(content, source);
      source.append(")");
      if(_ownerClass.isInterface()){
         source.append(";");
      }else{
         source.append("{");
         //............................................................
         // 生成代码
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
      if(!RString.isEmpty(_typeName)){
         info.append(' ');
         info.append(_typeName);
      }
      info.append(' ');
      info.append(_name);
      info.append("]");
      return info;
   }
}
