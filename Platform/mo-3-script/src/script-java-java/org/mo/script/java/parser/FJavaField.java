package org.mo.script.java.parser;

import org.mo.com.lang.RString;
import org.mo.com.lang.RStrings;
import org.mo.com.lang.generic.TDumpInfo;
import org.mo.com.text.parser.FTextComment;
import org.mo.com.text.parser.FTextSource;
import org.mo.com.text.parser.FTextToken;
import org.mo.script.java.converter.FJavaConvertContent;

//============================================================
// <T>字段对象。</T>
//============================================================
public class FJavaField
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
   public FJavaField(){
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
   public void parse(FJavaParserContent content, FTextToken token, String line){
      // 去掉尾分号
      if(line.endsWith(";")){
         line = line.substring(0, line.length() - 1);
      }
      // 判断是否有初始化赋值
      String source = line;
      if(line.contains("=")){
         source = RString.left(line, "=");
      }
      // 分割字符
      String[] items = RStrings.splitTrimNotEmpty(source, ' ');
      int count = items.length;
      for(int n = 0; n < count; n++){
         String item = items[n];
         if(item.equals("private") || item.equals("protected") || item.equals("public")){
            _accessCd = item;
            continue;
         }else if(item.equals("static")){
            _isStatic = true;
            continue;
         }else{
            // 获得类型
            _typeName = item;
            _typeFullName = _class.makeTypeName(content, item);
            // 获得名称
            item = items[++n];
            _name = item;
         }
      }
      if(line.contains("=")){
         _source = RString.right(line, "=");
      }
      //      if(items.length > 3){
      //         if(items[3].equals("=")){
      //            _source = RString.right(line, "=").trim();
      //            if(RString.startsWith(_source, "new ")){
      //               _createTypeName = RString.mid(_source, "new ", "(").trim();
      //               _createTypeFullName = _class.makeTypeName(content, _createTypeName);
      //            }
      //         }
      //      }
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
      // 生成注释
      for(String line : _comment.sourceLines()){
         source.appendIndent();
         source.appendLine(line);
      }
      //............................................................
      source.appendIndent();
      source.append(_accessCd);
      source.append(" var ");
      source.append(_name);
      source.append(':');
      source.append(content.convertTypeName(_typeFullName));
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
      info.append("JavaField [");
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
