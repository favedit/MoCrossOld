package org.mo.com.net.http;

import org.mo.com.lang.FAttributes;
import org.mo.com.lang.FFatalError;
import org.mo.com.lang.RDump;
import org.mo.com.lang.RString;
import org.mo.com.lang.generic.TDumpInfo;
import org.mo.com.net.FSocketInput;

//============================================================
// <T>网络应答。</T>
//============================================================
public class FHttpResponse
{
   // 网络链接
   protected FHttpConnection _connection;

   // 协议
   protected String _protocol;

   // 头信息集合
   protected FAttributes _heads = new FAttributes();

   // 状态
   protected int _status = 0;

   // 状态内容
   protected String _statusNote;

   // 数据内容
   protected byte[] _data;

   // 网络输入
   protected FSocketInput _input;

   //============================================================
   // <T>构造网络应答。</T>
   //
   // @param connection 网络链接
   //============================================================
   public FHttpResponse(FHttpConnection connection){
      _connection = connection;
      _input = _connection.socket().input();
   }

   //============================================================
   // <T>获得协议。</T>
   //
   // @return 协议
   //============================================================
   public String protocol(){
      return _protocol;
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
   // <T>根据头名称获得头内容。</T>
   //
   // @param head 头名称
   // @return 头内容
   //============================================================
   public String head(String head){
      return _heads.get(head);
   }

   //============================================================
   // <T>判断是否指定的状态。</T>
   //
   // @param status 状态
   // @return 是否指定的状态
   //============================================================
   public boolean isStatus(int status){
      return (_status == status);
   }

   //============================================================
   // <T>获得状态。</T>
   //
   // @return 状态
   //============================================================
   public int status(){
      return _status;
   }

   //============================================================
   // <T>获得状态内容。</T>
   //
   // @return 状态内容
   //============================================================
   public String statusNote(){
      return _statusNote;
   }

   //============================================================
   // <T>判断是否有数据。</T>
   //
   // @return 是否有数据
   //============================================================
   public boolean hasData(){
      return (null != _data);
   }

   //============================================================
   // <T>获得数据。</T>
   //
   // @return 数据
   //============================================================
   public byte[] data(){
      return _data;
   }

   //============================================================
   // <T>获得内容。</T>
   //
   // @return 内容
   //============================================================
   public String content(){
      if(_data != null){
         return new String(_data);
      }
      return null;
   }

   //============================================================
   // <T>获得指定编码的内容。</T>
   //
   // @param charset 编码
   // @return 内容
   //============================================================
   public String content(String charset){
      if(_data != null){
         try{
            return new String(_data, charset);
         }catch(Exception e){
            throw new FFatalError(e);
         }
      }
      return null;
   }

   //============================================================
   // <T>读取一行数据。</T>
   //
   // @return 行内容
   //============================================================
   private String readLine(){
      int value = 0;
      FHttpStream bytes = new FHttpStream();
      while(true){
         value = _input.readByte();
         if(value == '\r'){
            continue;
         }else if(value == '\n'){
            break;
         }
         bytes.append((byte)value);
      }
      if(bytes.isEmpty()){
         return null;
      }
      return new String(bytes.memory(), 0, bytes.length());
   }

   //============================================================
   // <T>读取数据块。</T>
   //============================================================
   private void readChunked(){
      int length = 0;
      String line = null;
      FHttpStream bytes = new FHttpStream();
      while(true){
         line = readLine();
         if(RString.isEmpty(line)){
            break;
         }
         line = line.trim();
         length = Integer.parseInt(line, 16);
         if(length == 0){
            break;
         }
         _input.read(bytes, length);
      }
      _data = bytes.toArray();
   }

   //============================================================
   // <T>接收数据。</T>
   //============================================================
   public void receive(){
      int index = 0;
      String line = readLine();
      String[] lines = RString.split(line, ' ', 3);
      if(lines.length != 3){
         throw new FFatalError("receive: {0}", line);
      }
      _protocol = lines[0];
      _status = Integer.parseInt(lines[1]);
      _statusNote = lines[2];
      _heads.clear();
      while(true){
         line = readLine();
         if(line == null){
            break;
         }
         if(line.isEmpty()){
            break;
         }
         index = line.indexOf(": ");
         if(index == -1){
            throw new FFatalError("receive: {0}", line);
         }
         _heads.set(line.substring(0, index), line.substring(index + 2));
      }
      _data = null;
      String encoding = _heads.find("Transfer-Encoding");
      if("chunked".equalsIgnoreCase(encoding)){
         readChunked();
      }else{
         int length = Integer.parseInt(_heads.get("Content-Length"));
         if(length > 0){
            _data = _input.read(length);
         }
      }
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
      RDump.dump(info, this);
      info.append(" Protocol:");
      info.append(_protocol);
      info.append(" Status:");
      info.appendInt(_status);
      info.append(' ');
      info.appendLine(_statusNote);
      info.appendLine(RDump.LINE_L2);
      int nCount = _heads.count();
      for(int n = 0; n < nCount; n++){
         info.append(_heads.name(n));
         info.append(": ");
         info.appendLine(_heads.value(n));
      }
      info.appendLine(RDump.LINE_L2);
      if(_data == null){
         info.appendLine("[null]");
      }else{
         info.appendLine(new String(_data));
      }
      info.appendLine(RDump.LINE_L2);
      return info;
   }
}
