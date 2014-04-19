package org.mo.script.as.parser;

import org.mo.com.lang.RString;
import org.mo.com.lang.RStrings;
import org.mo.com.lang.generic.TDumpInfo;
import org.mo.com.text.parser.FTextComment;
import org.mo.com.text.parser.FTextSource;
import org.mo.com.text.parser.FTextToken;
import org.mo.script.as.converter.FAsConvertContent;

//============================================================
// <T>字段对象。</T>
//============================================================
public class FAsField
      extends FAsObject
{
   // 类对象
   protected FAsClass _class;

   // 注释信息
   protected FTextComment _comment = new FTextComment();

   // 权限类型
   protected String _accessCd;

   // 是否静态
   protected boolean _isStatic;

   // 类型名称
   protected String _typeName;

   // 类型名称
   protected String _typeFullName;

   // 类型名称
   protected String _createTypeName;

   // 类型名称
   protected String _createTypeFullName;

   // 代码
   protected String _source;

   //============================================================
   // <T>构造字段对象。</T>
   //============================================================
   public FAsField(){
   }

   //============================================================
   // <T>获得类对象。</T>
   //
   // @return 类对象
   //============================================================
   public FAsClass clazz(){
      return _class;
   }

   //============================================================
   // <T>设置类对象。</T>
   //
   // @param clazz 类对象
   //============================================================
   public void setClazz(FAsClass clazz){
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
   // <T>获得类型名称。</T>
   //
   // @return 类型名称
   //============================================================
   public String typeName(){
      return _typeName;
   }

   //============================================================
   // <T>获得来源代码。</T>
   //
   // @return 来源代码
   //============================================================
   public String source(){
      return _source;
   }

   //============================================================
   // <T>解析文件。</T>
   //============================================================
   public void parse(FAsParserContent content, FTextToken token, String line){
      // 去掉尾标志
      if(line.endsWith(";")){
         line = line.substring(0, line.length() - 1);
      }
      // 去掉类型空格
      if(RString.contains(line, " :") || RString.contains(line, ": ")){
         line = RString.replace(line, " :", ":");
         line = RString.replace(line, ": ", ":");
      }
      // 解析内容
      String[] items = RStrings.splitTrimNotEmpty(line, ' ');
      int count = items.length;
      for(int n = 0; n < count; n++){
         String item = items[n];
         if(item.equals("private") || item.equals("protected") || item.equals("public")){
            _accessCd = item;
         }else if(item.equals("static")){
            _isStatic = true;
         }else if(item.equals("var")){
            String source = items[++n];
            String[] names = RString.split(source, ':');
            _name = names[0];
            _typeName = names[1];
            _typeFullName = _class.makeTypeName(content, _typeName);
         }else if(item.equals("=")){
            _source = RString.right(line, "=").trim();
            if(RString.startsWith(_source, "new ")){
               _createTypeName = RString.mid(_source, "new ", "(").trim();
               _createTypeFullName = _class.makeTypeName(content, _createTypeName);
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
   }

   //============================================================
   // <T>转换代码内容。</T>
   //
   // @param content 转换环境
   // @param source 代码内容
   //============================================================
   public void convert(FAsConvertContent content, FTextSource source){
      //............................................................
      // 生成注释
      for(String line : _comment.sourceLines()){
         source.appendIndent();
         source.appendLine(line);
      }
      //............................................................
      // 生成代码
      source.appendIndent();
      source.append(_accessCd);
      source.append(' ');
      source.append(content.convertTypeName(_typeFullName));
      source.append(' ');
      source.append(_name);
      if(!RString.isEmpty(_source)){
         source.append(" = ");
         if(RString.isEmpty(_createTypeName)){
            source.append(_source);
         }else{
            source.append("new ");
            source.append(content.convertTypeName(_createTypeFullName));
            source.append("()");
         }
      }
      source.append(';');
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
      info.append("AsField [");
      info.append(_accessCd);
      info.append(' ');
      info.append(_name);
      info.append(':');
      info.append(_typeName);
      if(!RString.isEmpty(_source)){
         info.append(" = ");
         info.append(_source);
      }
      info.append("]");
      return info;
   }
}
