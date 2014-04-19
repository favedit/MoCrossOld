package org.mo.script.as.parser.source;

import org.mo.com.lang.FObject;
import org.mo.com.lang.RString;
import org.mo.com.lang.RStrings;
import org.mo.com.lang.generic.TDumpInfo;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.com.text.parser.FTextSource;
import org.mo.com.text.parser.FTextToken;
import org.mo.com.text.parser.FTextTokens;
import org.mo.script.as.converter.FAsConvertContent;
import org.mo.script.as.parser.FAsClass;
import org.mo.script.as.parser.FAsFunction;
import org.mo.script.as.parser.FAsParserContent;
import org.mo.script.as.parser.RAsParser;

//============================================================
// <T>代码。</T>
//============================================================
public class FAsSource
      extends FObject
{
   // 日志输出接口
   @SuppressWarnings("unused")
   private static final ILogger _logger = RLogger.find(FAsSource.class);

   // 父对象
   protected FAsSource _parent;

   // 拥有类
   protected FAsClass _ownerClass;

   // 拥有函数
   protected FAsFunction _ownerFunction;

   // 前置行
   protected boolean _hasLineBegin = true;

   // 前置行
   protected boolean _hasLineEnd = false;

   // 代码集合
   protected FAsSources _sources;

   //============================================================
   // <T>构造代码。</T>
   //============================================================
   public FAsSource(){
   }

   //============================================================
   // <T>获得代码。</T>
   //
   // @return 代码
   //============================================================
   public FAsSource parent(){
      return _parent;
   }

   //============================================================
   // <T>设置代码。</T>
   //
   // @param parent 代码
   //============================================================
   public void setParent(FAsSource parent){
      _parent = parent;
      _ownerClass = parent.ownerClass();
      _ownerFunction = parent.ownerFunction();
   }

   //============================================================
   // <T>获得拥有类对象。</T>
   //
   // @return 拥有类对象
   //============================================================
   public FAsClass ownerClass(){
      return _ownerClass;
   }

   //============================================================
   // <T>设置拥有类对象。</T>
   //
   // @param clazz 拥有类对象
   //============================================================
   public void setOwnerClass(FAsClass clazz){
      _ownerClass = clazz;
   }

   //============================================================
   // <T>获得拥有函数对象。</T>
   //
   // @return 拥有函数对象
   //============================================================
   public FAsFunction ownerFunction(){
      return _ownerFunction;
   }

   //============================================================
   // <T>设置类对象。</T>
   //
   // @param function 拥有函数对象
   //============================================================
   public void setOwnerFunction(FAsFunction function){
      _ownerFunction = function;
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
   public FAsSources sources(){
      if(null == _sources){
         _sources = new FAsSources();
      }
      return _sources;
   }

   //============================================================
   // <T>推入代码集合。</T>
   //
   // @param source 代码段
   //============================================================
   public void push(FAsSource source){
      source.setOwnerClass(_ownerClass);
      sources().push(source);
   }

   //============================================================
   // <T>解析代码内容。</T>
   //
   // @param content 解析环境
   // @param token 文本块
   //============================================================
   public void parseSource(FAsParserContent content, FTextToken token, String line){
      String[] items = RString.split(line, ';');
      RString.trim(items);
      items = RStrings.filterNotBlank(items);
      for(String item : items){
         if(RString.startsWith(item, "new ")){
            FAsNewSource newSource = new FAsNewSource();
            newSource.setParent(this);
            newSource.parseKeyword(content, token, item);
            push(newSource);
         }else if(RAsParser.isOperator2(item)){
            FAsOperator2Source operator2Source = new FAsOperator2Source();
            operator2Source.setParent(this);
            operator2Source.parseKeyword(content, token, item);
            push(operator2Source);
         }else if(RString.contains(item, " ? ")){
            FAsOperator3Source operator3Source = new FAsOperator3Source();
            operator3Source.setParent(this);
            operator3Source.parseKeyword(content, token, item);
            push(operator3Source);
         }else{
            FAsTextSource textSource = new FAsTextSource();
            textSource.setParent(this);
            textSource.parseKeyword(content, token, item);
            push(textSource);
            //_logger.error(this, "parseSource", "Unknown parse source. (source={1})", item);
         }
      }
   }

   //============================================================
   // <T>解析代码内容。</T>
   //
   // @param content 解析环境
   // @param token 文本块
   //============================================================
   public void parse(FAsParserContent content, FTextToken token){
      FTextTokens tokens = token.tokens();
      for(FTextToken subToken : tokens){
         String source = subToken.source();
         String[] items = RString.split(source, '\n');
         RString.trim(items);
         items = RStrings.filterNotBlank(items);
         for(String item : items){
            if(RString.startsWith(item, "//")){
               // 解析注释
               FAsCommentSource commentSource = new FAsCommentSource();
               commentSource.setParent(this);
               commentSource.parseKeyword(content, subToken, item);
               push(commentSource);
            }else if(RString.startsWith(item, "var ")){
               // 解析变量声明
               FAsDeclareSource declareSource = new FAsDeclareSource();
               declareSource.setParent(this);
               declareSource.parseKeyword(content, subToken, item);
               push(declareSource);
            }else if(RString.startsWith(item, "if(")){
               // 解析If判断
               FAsIfSource ifSource = new FAsIfSource();
               ifSource.setParent(this);
               ifSource.parseKeyword(content, subToken, item);
               push(ifSource);
            }else if(RString.startsWith(item, "else")){
               // 解析Else判断
               FAsElseSource elseSource = new FAsElseSource();
               elseSource.setParent(this);
               elseSource.parseKeyword(content, subToken, item);
               push(elseSource);
            }else if(RString.startsWith(item, "for(")){
               // 解析For循环
               FAsForSource forSource = new FAsForSource();
               forSource.setParent(this);
               forSource.parseKeyword(content, subToken, item);
               push(forSource);
            }else if(RString.startsWith(item, "while(")){
               // 解析While循环
               FAsWhileSource whileSource = new FAsWhileSource();
               whileSource.setParent(this);
               whileSource.parseKeyword(content, subToken, item);
               push(whileSource);
            }else if(RString.startsWith(item, "switch(")){
               // 解析Switch选择
               FAsSwitchSource switchSource = new FAsSwitchSource();
               switchSource.setParent(this);
               switchSource.parseKeyword(content, subToken, item);
               push(switchSource);
            }else if(RString.startsWith(item, "try")){
               // 解析Try
               FAsTrySource trySource = new FAsTrySource();
               trySource.setParent(this);
               trySource.parseKeyword(content, subToken, item);
               push(trySource);
            }else if(RString.startsWith(item, "catch(")){
               // 解析Catch
               FAsCatchSource catchSource = new FAsCatchSource();
               catchSource.setParent(this);
               catchSource.parseKeyword(content, subToken, item);
               push(catchSource);
            }else if(RString.startsWith(item, "return ")){
               // 解析返回
               FAsReturnSource returnSource = new FAsReturnSource();
               returnSource.setParent(this);
               returnSource.parseKeyword(content, subToken, item);
               push(returnSource);
            }else{
               String code = RAsParser.skipStrings(item);
               if(RString.contains(code, " = ")){
                  // 解析赋值
                  FAsAssignSource assignSource = new FAsAssignSource();
                  assignSource.setParent(this);
                  assignSource.parseKeyword(content, subToken, item);
                  push(assignSource);
               }else if(RAsParser.isOperator2(code)){
                  // 解析2元操作数
                  parseSource(content, token, item);
               }else if(RString.contains(code, ".")){
                  // 解析调用
                  FAsCallSource callSource = new FAsCallSource();
                  callSource.setParent(this);
                  callSource.parseKeyword(content, subToken, item);
                  push(callSource);
               }else{
                  // 解析文本
                  FAsTextSource textSource = new FAsTextSource();
                  textSource.setParent(this);
                  textSource.parseKeyword(content, subToken, item);
                  push(textSource);
                  //_logger.error(this, "parse", "Parse unknown source. (item={1})", item);
               }
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
   public void convertSourceBegin(FAsConvertContent content, FTextSource source){
   }

   //============================================================
   // <T>转换代码内容。</T>
   //
   // @param content 转换环境
   // @param source 代码内容
   //============================================================
   public void convertSourceEnd(FAsConvertContent content, FTextSource source){
   }

   //============================================================
   // <T>转换代码内容。</T>
   //
   // @param content 转换环境
   // @param source 代码内容
   //============================================================
   public void convert(FAsConvertContent content, FTextSource source){
      // 转换前代码
      convertSourceBegin(content, source);
      //............................................................
      // 转换子代码
      if(null != _sources){
         for(FAsSource asSource : _sources){
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
