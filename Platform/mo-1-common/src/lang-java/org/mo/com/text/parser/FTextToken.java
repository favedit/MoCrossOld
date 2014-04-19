package org.mo.com.text.parser;

import org.mo.com.lang.FObject;
import org.mo.com.lang.FString;
import org.mo.com.lang.RInteger;
import org.mo.com.lang.RString;
import org.mo.com.lang.RStrings;
import org.mo.com.lang.generic.TDumpInfo;

//============================================================
// <T>文本段。</T>
//============================================================
public class FTextToken
      extends FObject
{
   // 文本环境
   protected FTextContext _context;

   // 文本来源
   protected String _source;

   // 分行集合
   protected String[] _lines;

   // 文本内容
   protected String _text;

   // 文本段集合
   protected FTextTokens _tokens;

   //============================================================
   // <T>构造文本段。</T>
   //============================================================
   public FTextToken(){
   }

   //============================================================
   // <T>获得文本环境。</T>
   //
   // @return 文本环境
   //============================================================
   public FTextContext context(){
      return _context;
   }

   //============================================================
   // <T>设置文本环境。</T>
   //
   // @param context 文本环境
   //============================================================
   public void setContext(FTextContext context){
      _context = context;
   }

   //============================================================
   // <T>获得文本来源。</T>
   //
   // @return 文本来源
   //============================================================
   public String source(){
      return _source;
   }

   //============================================================
   // <T>设置文本来源。</T>
   //
   // @param text 文本来源
   //============================================================
   public void setSource(String source){
      _source = source;
   }

   //============================================================
   // <T>获得分行集合。</T>
   //
   // @return 分行集合
   //============================================================
   public String[] lines(){
      return _lines;
   }

   //============================================================
   // <T>获得文本内容。</T>
   //
   // @return 文本内容
   //============================================================
   public String text(){
      return _text;
   }

   //============================================================
   // <T>设置文本内容。</T>
   //
   // @param text 文本内容
   //============================================================
   public void setText(String text){
      _text = text;
   }

   //============================================================
   // <T>获得是否含有文本段。</T>
   //
   // @return 是否含有
   //============================================================
   public boolean hasToken(){
      return (null != _tokens) ? !_tokens.isEmpty() : false;
   }

   //============================================================
   // <T>获得文本段集合。</T>
   //
   // @return 文本段集合
   //============================================================
   public FTextTokens tokens(){
      if(null == _tokens){
         _tokens = new FTextTokens();
      }
      return _tokens;
   }

   //============================================================
   // <T>获得文本段集合。</T>
   //
   // @return 文本段集合
   //============================================================
   public void push(FTextToken token){
      tokens().push(token);
   }

   //============================================================
   // <T>加载来源内容。</T>
   //
   // @param source 来源内容
   //============================================================
   public void load(String source){
      // 设置代码
      _source = source;
      // 设置分行
      String[] lines = RString.split(source, "\n");
      RString.trim(lines);
      _lines = RStrings.filterNotEmpty(lines);
      // 建立文本字符串
      FString text = new FString();
      for(String line : _lines){
         if(!_context.isLineComment(line)){
            text.append(line);
         }
      }
      _text = text.toString();
   }

   //============================================================
   // <T>解析文本内容。</T>
   //============================================================
   public void parse(String source){
      // 格式化代码，分隔成行
      //boolean inComment = false;
      boolean inString = false;
      char[] chars = source.toCharArray();
      int n = -1;
      int end = chars.length;
      FString code = new FString();
      int deepCount = 0;
      FTextToken tokenOpen = null;
      while(++n < end){
         char value = chars[n];
         // 处理字符串中内容
         if(!inString && (value == '"')){
            inString = true;
            code.append(value);
            continue;
         }
         if(inString && (value == '"')){
            inString = false;
            code.append(value);
            continue;
         }
         if(inString){
            code.append(value);
            continue;
         }
         // 处理分段
         if(_context.isTokenBegin(value)){
            // 开启块操作
            if(0 == deepCount){
               if(!code.isBlank()){
                  tokenOpen = new FTextToken();
                  tokenOpen.setContext(_context);
                  tokenOpen.load(code.flushString());
                  push(tokenOpen);
               }
            }else{
               code.append(value);
            }
            deepCount++;
         }else if(_context.isTokenEnd(value)){
            // 关闭块操作
            deepCount--;
            if(0 == deepCount){
               if(!code.isBlank()){
                  tokenOpen.parse(code.flushString());
               }
               tokenOpen = null;
            }else{
               code.append(value);
            }
         }else{
            code.append(value);
         }
      }
      // 处理尾节点
      if(!code.isBlank()){
         FTextToken token = new FTextToken();
         token.setContext(_context);
         token.load(code.flushString());
         push(token);
      }
   }

   //   //============================================================
   //   // <T>解析文本内容。</T>
   //   //============================================================
   //   public void parseSelf(){
   //      if(null != _tokens){
   //         for(FTextToken token : _tokens){
   //            token.setContext(_context);
   //            token.parseSelf();
   //         }
   //      }
   //      if(!RString.isEmpty(_text)){
   //         // 格式化代码，分隔成行
   //         char[] chars = _text.toCharArray();
   //         int n = -1;
   //         int end = chars.length;
   //         FString code = new FString();
   //         int deepCount = 0;
   //         FTextToken tokenOpen = null;
   //         while(++n < end){
   //            char value = chars[n];
   //            if(_context.isTokenBegin(value)){
   //               // 开启块操作
   //               if(0 == deepCount){
   //                  if(!code.isBlank()){
   //                     tokenOpen = new FTextToken();
   //                     tokenOpen.setContext(_context);
   //                     tokenOpen.load(code.flushString());
   //                     push(tokenOpen);
   //                  }
   //               }else{
   //                  code.append(value);
   //               }
   //               deepCount++;
   //            }else if(_context.isTokenEnd(value)){
   //               // 关闭块操作
   //               deepCount--;
   //               if(0 == deepCount){
   //                  if(!code.isBlank()){
   //                     tokenOpen.parse(code.flushString());
   //                  }
   //                  tokenOpen = null;
   //               }else{
   //                  code.append(value);
   //               }
   //            }else{
   //               code.append(value);
   //            }
   //         }
   //         // 处理尾节点
   //         if(!code.isBlank()){
   //            FTextToken token = new FTextToken();
   //            token.setContext(_context);
   //            token.load(code.flushString());
   //            push(token);
   //         }
   //      }
   //   }
   //============================================================
   // <T>生成运行信息。</T>
   //
   // @param info 运行信息
   // @return 运行信息
   //============================================================
   @Override
   public TDumpInfo dump(TDumpInfo info){
      info.append(RInteger.format(info.level(), 2));
      info.appendIndent();
      if(null != _tokens){
         info.append(" + ");
      }else{
         info.append(" - ");
      }
      if(RString.isEmpty(_text)){
         info.appendLine("[Node]");
      }else{
         info.appendLine("{" + _text + "}");
      }
      if(null != _tokens){
         for(FTextToken token : _tokens){
            info.increaseLevel(token);
            token.dump(info);
            info.decreaseLevel();
         }
      }
      return info;
   }
}
