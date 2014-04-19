package org.mo.com.net.http;

import org.mo.com.lang.FAttributes;
import org.mo.com.lang.FFatalError;
import org.mo.com.lang.FString;
import org.mo.com.lang.RDump;
import org.mo.com.lang.RString;
import org.mo.com.lang.generic.TDumpInfo;
import org.mo.com.net.FSocketOutput;

//============================================================
// <T>网络请求。</T>
//============================================================
public class FHttpRequest
{
   // 代理信息
   public static String DEFAULT_AGENT = "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; .NET CLR 1.1.4322)";

   // 网络链接
   protected FHttpConnection _connection;

   // 提交方法
   protected EHttpRequestMethod _methodCd = EHttpRequestMethod.Get;

   // 头信息集合
   protected FAttributes _heads = new FAttributes();

   // 内容集合
   protected FAttributes _values = new FAttributes();

   // 文本内容
   protected String _text;

   // 网路输出
   protected FSocketOutput _output;

   //============================================================
   // <T>构造网络请求。</T>
   //============================================================
   public FHttpRequest(FHttpConnection connection){
      _connection = connection;
      _output = _connection.socket().output();
      setDefaultHead();
   }

   //============================================================
   // <T>获得提交方法。</T>
   //
   // @return 提交方法
   //============================================================
   public EHttpRequestMethod methodCd(){
      return _methodCd;
   }

   //============================================================
   // <T>设置提交方法。</T>
   //
   // @param methodCd 提交方法
   //============================================================
   public void setMethod(EHttpRequestMethod methodCd){
      _methodCd = methodCd;
   }

   //============================================================
   // <T>获得头信息集合。</T>
   //
   // @return 头信息集合
   //============================================================
   public FAttributes heads(){
      return _heads;
   }

   //============================================================
   // <T>设置头信息。</T>
   //
   // @param name 名称
   // @param value 内容
   //============================================================
   public void setHead(String name,
                       String value){
      _heads.set(name, value);
   }

   //============================================================
   // <T>获得内容集合。</T>
   //
   // @return 内容集合
   //============================================================
   public FAttributes values(){
      return _values;
   }

   //============================================================
   // <T>设置内容。</T>
   //
   // @param name 名称
   // @param value 内容
   //============================================================
   public void setValue(String name,
                        String value){
      _values.set(name, value);
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
   // <T>设置默认头信息集合。</T>
   //============================================================
   protected void setDefaultHead(){
      _heads.set("Accept", "*/*");
      _heads.set("Accept-Language", "zh-cn");
      _heads.set("User-Agent", DEFAULT_AGENT);
      _heads.set("Connection", "Keep-Alive");
   }

   //============================================================
   // <T>写入字符串。</T>
   //
   // @param value 字符串
   //============================================================
   public void write(String value){
      _output.write(value.getBytes());
   }

   //============================================================
   // <T>写入一行分隔。</T>
   //============================================================
   public void writeLine(){
      _output.write("\r\n".getBytes());
   }

   //============================================================
   // <T>建立数据流。</T>
   //
   // @return 数据流
   //============================================================
   protected FHttpStream build(){
      FHttpStream bytes = new FHttpStream();
      FString line = new FString();
      // Protocol
      if(_methodCd == EHttpRequestMethod.Get){
         line.append("GET ");
      }else if(_methodCd == EHttpRequestMethod.Post){
         line.append("POST ");
      }else{
         throw new FFatalError("Unknown method.");
      }
      if(_connection.useProxy()){
         line.append(_connection.url().toString());
      }else{
         if(RString.isEmpty(_connection.url().path())){
            line.append("/");
         }else{
            line.append(_connection.url().path());
         }
      }
      line.append(" HTTP/1.1\r\n");
      // Values
      FString value = new FString();
      if(!_values.isEmpty()){
         int count = _values.count();
         for(int n = 0; n < count; n++){
            if(n != 0){
               value.append('&');
            }
            value.append(_values.name(n));
            value.append('=');
            value.append(_values.value(n));
         }
      }
      if(!RString.isEmpty(_text)){
         value.append(_text);
      }
      byte[] valueData = value.toBytes();
      int valueLength = valueData.length;
      if(valueLength > 0){
         _heads.set("Content-Length", Integer.toString(valueLength));
      }
      bytes.append(line.toBytes());
      // Heads
      if(!_heads.isEmpty()){
         line.clear();
         int count = _heads.count();
         for(int n = 0; n < count; n++){
            line.append(_heads.name(n));
            line.append(": ");
            line.append(_heads.value(n));
            line.append("\r\n");
         }
         bytes.append(line.toBytes());
      }
      // Host Fix
      if(!_heads.contains("Host")){
         line.clear();
         line.append("Host: ");
         line.append(_connection.url().host());
         line.append("\r\n");
         bytes.append(line.toBytes());
      }
      // Make
      line.clear();
      line.append("\r\n");
      bytes.append(line.toBytes());
      if(valueLength > 0){
         bytes.append(valueData);
      }
      return bytes;
   }

   //============================================================
   // <T>发送数据流。</T>
   //============================================================
   public void send(){
      FHttpStream bytes = build();
      _output.write(bytes.memory(), 0, bytes.length());
   }

   //============================================================
   // <T>重置数据。</T>
   //============================================================
   public void reset(){
      _heads.clear();
      _values.clear();
      setDefaultHead();
   }

   //============================================================
   // <T>获得运行信息。</T>
   //
   // @return 运行信息
   //============================================================
   public TDumpInfo dump(){
      return dump(new TDumpInfo(this));
   }

   //============================================================
   // <T>获得运行信息。</T>
   //
   // @param info 信息
   // @return 运行信息
   //============================================================
   public TDumpInfo dump(TDumpInfo info){
      FHttpStream bytes = build();
      RDump.dump(info, this);
      info.append(" Length:");
      info.appendLine(bytes.length());
      info.appendLine(RDump.LINE_L2);
      info.appendLine(new String(bytes.memory(), 0, bytes.length()));
      info.appendLine(RDump.LINE_L2);
      return info;
   }
}
