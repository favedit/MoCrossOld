package org.mo.mime.csv;

import java.io.InputStreamReader;
import org.mo.com.io.FFileLineReader;
import org.mo.com.lang.RString;
import org.mo.com.xml.FXmlNode;

public class FCsvReader
      extends FAbsCsvContainer
{
   private String _fileName;

   private ICsvFormat _format = RCsv.DEFAULT_FORMAT;

   private FCsvCommandProperties _properties;

   private String _nextLine;

   private final FFileLineReader _reader = new FFileLineReader();

   public FCsvReader(){
   }

   /**
    * <T>按指定文件名创建一个reader对象。</T>
    * 
    * @param filename 指定文件名
    */
   public FCsvReader(String filename){
      openFile(filename);
   }

   /**
    * <T>按指定文件名创建一个reader对象。</T>
    * 
    * @param filename 指定文件名
    */
   public FCsvReader(String filename,
                     String charset){
      openFile(filename, charset);
   }

   /**
    * <T>关闭reader对象。</T>
    */
   public void close(){
      _reader.close();
   }

   public void closeSegment(){
   }

   /**
    * <T>获得文件名称。</T>
    * 
    * @return 文件名称
    */
   public String fileName(){
      return _fileName;
   }

   /**
    * <T>返回格式化字符。</T>
    * 
    * @return 格式化字符
    */
   public ICsvFormat format(){
      return _format;
   }

   /**
    * <T>当前文件是否还有下一行。</T>
    * 
    * @return 有或无
    *         <UL>
    *         <L value='true'>文件还有下文</L>
    *         <L value='false'>文件已经到最后一行</L>
    *         </UL>
    */
   public boolean hasNext(){
      if(_reader.hasNext()){
         _nextLine = _reader.readLine();
         return true;
      }
      return false;
   }

   /**
    * <T>当前文件是否还有下一行。</T>
    * 
    * @return 有或无
    *         <UL>
    *         <L value='true'>文件还有下文</L>
    *         <L value='false'>文件已经到最后一行</L>
    *         </UL>
    */
   public boolean hasNextData(){
      _nextLine = _reader.readLine();
      String line = _nextLine;
      if(null != line){
         if(line.startsWith("@data.end")){
            return false;
         }
         return true;
      }
      return false;
   }

   /**
    * <T>加载配置。</T>
    * 
    * @param config 配置xml节点
    */
   public void loadConfig(FXmlNode config){
   }

   /**
    * <T>打开指定文件。</T>
    * 
    * @param fileName
    */
   public void openFile(String fileName){
      _reader.openFile(fileName, "UTF-8");
   }

   /**
    * <T>打开指定文件。</T>
    * 
    * @param fileName
    */
   public void openFile(String fileName,
                        String charset){
      _reader.openFile(fileName, charset);
   }

   /**
    * <T>读取读取默认的段头信息。</T>
    * 
    */
   public void openSegment(){
      openSegment(null);
   }

   /**
    * <T>读取读取指定段名的段头信息。</T>
    * 
    * @param name 段名称
    */
   public void openSegment(String name){
      boolean isRead = true;
      while(isRead){
         String tempLine = _reader.readLine();
         FCsvLine line = new FCsvLine();
         if(tempLine.startsWith("@head")){
            tempLine = _reader.readLine();
            line = RCsv.parseLine(tempLine);
            int count = line.count();
            for(int i = 0; i < count; i++){
               FCsvColumn column = new FCsvColumn();
               String columnName = line.value(i);
               column.setName(columnName);
               _columns.set(columnName, column);
            }
         }else if(tempLine.startsWith("@label")){
            tempLine = _reader.readLine();
            line = RCsv.parseLine(tempLine);
            int count = line.count();
            for(int i = 0; i < count; i++){
               String columnlabel = line.value(i);
               _columns.value(i).setLabel(columnlabel);
            }
         }else if(tempLine.startsWith("@property")){
            String[] content = RString.splitTwo(tempLine, "@property", true);
            String[] pty;
            if(content[1].endsWith(",")){
               String[] ptyString = RString.splitTwo(content[1], ',', false);
               pty = RString.splitTwo(ptyString[0], '=', false);
            }else{
               pty = RString.splitTwo(content[1], '=', false);
            }
            FCsvCommandProperty property = new FCsvCommandProperty();
            property.setName(pty[0]);
            property.setValue(pty[1]);
            properties().set(property.name(), property);
         }else if(tempLine.startsWith("@data.start")){
            isRead = false;
         }
      }
   }

   /**
    * <T>打开流文件。</T>
    * 
    * @param fileName
    */
   public void openStream(InputStreamReader inputStream,
                          String charset){
      _reader.openStream(inputStream, charset);
   }

   public FCsvCommandProperties properties(){
      if(null == _properties){
         _properties = new FCsvCommandProperties();
      }
      return _properties;
   }

   public void readHead(){
      _reader.readLine();
   }

   /**
    * <T>读取指定文件中的一行数据，并转换为FCsvLine对象返回。</T>
    * 
    * @return 一行数据的FCsvLine对象
    */
   public FCsvLine readLine(){
      FCsvLine line = null;
      String tempLine = _nextLine;
      if(RCsv.isEmptyLine(tempLine)){
         return null;
      }
      if(tempLine.startsWith("@comment")){
         return null;
      }else{
         line = RCsv.parseLine(tempLine);
         line._container = this;
      }
      return line;
   }

   public FCsvLine returnHead(){
      return readLine();
   }

   /**
    * <T>设置格式化字符。</T>
    * 
    * @param format
    */
   public void setFormat(ICsvFormat format){
      _format = format;
   }
}
