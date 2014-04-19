package org.mo.script.java.parser.source;

import org.mo.com.lang.FObject;
import org.mo.com.lang.RString;
import org.mo.com.lang.RStrings;
import org.mo.com.lang.generic.TDumpInfo;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.com.text.parser.FTextSource;
import org.mo.com.text.parser.FTextToken;
import org.mo.com.text.parser.FTextTokens;
import org.mo.script.java.converter.FJavaConvertContent;
import org.mo.script.java.parser.FJavaParserContent;

//============================================================
// <T>代码。</T>
//============================================================
public class FJavaSource
      extends FObject
{
   // 日志输出接口
   private static ILogger _logger = RLogger.find(FJavaSource.class);

   // 代码
   protected FJavaSource _parent;

   // 前置行
   protected boolean _hasLineBegin = true;

   // 前置行
   protected boolean _hasLineEnd = false;

   // 代码集合
   protected FJavaSources _sources;

   //============================================================
   // <T>构造代码。</T>
   //============================================================
   public FJavaSource(){
   }

   //============================================================
   // <T>获得代码。</T>
   //
   // @return 代码
   //============================================================
   public FJavaSource parent(){
      return _parent;
   }

   //============================================================
   // <T>设置代码。</T>
   //
   // @param parent 代码
   //============================================================
   public void setParent(FJavaSource parent){
      _parent = parent;
   }

   //============================================================
   // <T>判断是否含有前置行。</T>
   //
   // @return 是否含有
   //============================================================
   public boolean hasLineBegin(){
      return _hasLineBegin;
   }

   //============================================================
   // <T>判断是否含有后置行。</T>
   //
   // @return 是否含有
   //============================================================
   public boolean hasLineEnd(){
      return _hasLineEnd;
   }

   //============================================================
   // <T>判断是否含有代码。</T>
   //
   // @return 是否含有
   //============================================================
   public boolean hasSource(){
      return (null != _sources) ? !_sources.isEmpty() : false;
   }

   //============================================================
   // <T>获得代码集合。</T>
   //
   // @return 代码集合
   //============================================================
   public FJavaSources sources(){
      if(null == _sources){
         _sources = new FJavaSources();
      }
      return _sources;
   }

   //============================================================
   // <T>推入代码集合。</T>
   //
   // @param source 代码段
   //============================================================
   public void push(FJavaSource source){
      sources().push(source);
   }

   //============================================================
   // <T>解析代码内容。</T>
   //
   // @param content 解析环境
   // @param token 文本块
   //============================================================
   public void parseLine(FJavaParserContent content, FTextToken token, String line){
      String[] items = RString.split(line, ';');
      RString.trim(items);
      items = RStrings.filterNotBlank(items);
      for(String item : items){
         if(RString.contains(item, " == ") || RString.contains(item, " != ")){
            FJavaOperator2Source operator2Source = new FJavaOperator2Source();
            operator2Source.parseKeyword(content, token, item);
            push(operator2Source);
         }else if(RString.contains(item, " ? ")){
            FJavaOperator3Source operator3Source = new FJavaOperator3Source();
            operator3Source.parseKeyword(content, token, item);
            push(operator3Source);
         }else{
            _logger.debug(this, "parseLine", "Can't parse line. (item={1})", item);
         }
      }
   }

   //============================================================
   // <T>解析代码内容。</T>
   //
   // @param content 解析环境
   // @param token 文本块
   //============================================================
   public void parse(FJavaParserContent content, FTextToken token){
      FTextTokens tokens = token.tokens();
      for(FTextToken subToken : tokens){
         String source = subToken.source();
         String[] items = RString.split(source, '\n');
         RString.trim(items);
         items = RStrings.filterNotBlank(items);
         for(String item : items){
            if(RString.startsWith(item, "var ")){
               FJavaDeclareSource declareSource = new FJavaDeclareSource();
               declareSource.parseKeyword(content, subToken, item);
               push(declareSource);
            }else if(RString.startsWith(item, "if(")){
               FJavaIfSource ifSource = new FJavaIfSource();
               ifSource.parseKeyword(content, subToken, item);
               push(ifSource);
            }else if(RString.startsWith(item, "for(")){
               FJavaForSource forSource = new FJavaForSource();
               forSource.parseKeyword(content, subToken, item);
               push(forSource);
            }else if(RString.startsWith(item, "while(")){
               FJavaWhileSource whileSource = new FJavaWhileSource();
               whileSource.parseKeyword(content, subToken, item);
               push(whileSource);
            }else if(RString.startsWith(item, "switch(")){
               FJavaSwitchSource switchSource = new FJavaSwitchSource();
               switchSource.parseKeyword(content, subToken, item);
               push(switchSource);
            }else if(RString.startsWith(item, "return ")){
               FJavaReturnSource returnSource = new FJavaReturnSource();
               returnSource.parseKeyword(content, subToken, item);
               push(returnSource);
            }else if(RString.contains(item, " = ")){
               FJavaAssignSource assignSource = new FJavaAssignSource();
               assignSource.parseKeyword(content, subToken, item);
               push(assignSource);
            }else{
               FJavaCallSource callSource = new FJavaCallSource();
               callSource.parseKeyword(content, subToken, item);
               push(callSource);
            }
         }
      }
   }

   //============================================================
   // <T>转换代码内容。</T>
   //
   // @param content 转换环境
   // @param source 代码内容
   //============================================================
   public void convertSourceBegin(FJavaConvertContent content, FTextSource source){
   }

   //============================================================
   // <T>转换代码内容。</T>
   //
   // @param content 转换环境
   // @param source 代码内容
   //============================================================
   public void convertSourceEnd(FJavaConvertContent content, FTextSource source){
   }

   //============================================================
   // <T>转换代码内容。</T>
   //
   // @param content 转换环境
   // @param source 代码内容
   //============================================================
   public void convert(FJavaConvertContent content, FTextSource source){
      // 转换前代码
      convertSourceBegin(content, source);
      //............................................................
      // 转换子代码
      if(null != _sources){
         for(FJavaSource asSource : _sources){
            if(asSource.hasLineBegin()){
               source.appendLine();
            }
            source.increase();
            asSource.convert(content, source);
            source.decrease();
            if(asSource.hasLineEnd()){
               source.appendLine();
            }
         }
      }
      //............................................................
      // 转换后代码
      convertSourceEnd(content, source);
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
      info.append("AsSource [");
      info.append("]");
      return info;
   }
}
