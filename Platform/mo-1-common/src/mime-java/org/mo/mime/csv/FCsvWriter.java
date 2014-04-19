package org.mo.mime.csv;

import org.mo.com.io.FFileLineWriter;
import org.mo.com.lang.FString;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;

public class FCsvWriter
      extends FAbsCsvContainer
{
   @SuppressWarnings("unused")
   private static ILogger _logger = RLogger.find(FCsvWriter.class);

   private String _fileName;

   private int _lineCount = 0;

   private final FFileLineWriter _writer = new FFileLineWriter();

   public FCsvWriter(){
   }

   public FCsvWriter(String fileName){
      openFile(fileName);
   }

   public void Close(){
      _writer.close();
   }

   public void doCommand(ICsvCommand command){
      String commandString = null;
      switch(command.type()){
         case Head:
            commandString = "@head";
            _writer.writeLine(commandString);
            FCsvLine line = new FCsvLine();
            for(String name : _columns.names()){
               line.push(name);
            }
            writeLine(line);
            break;
         case Label:
            commandString = "@label";
            _writer.writeLine(commandString);
            FCsvLine csvline = new FCsvLine();
            for(FCsvColumn column : _columns.values()){
               String label = column.label();
               if(null != label){
                  csvline.push(label);
               }
            }
            if(null != csvline){
               writeLine(csvline);
            }
            break;
         case Data:
            commandString = "@data.start";
            _writer.writeLine(commandString);
            _lineCount = 0;
            break;
         case Comment:
            commandString = "@Comment";
            _writer.writeLine(commandString);
            FCsvCommandComment comment = (FCsvCommandComment)command;
            _writer.writeLine(comment.description());
            break;
         case End:
            commandString = "@data.end";
            _writer.writeLine(commandString);
            break;
         case ValidLines:
            commandString = "@validLines lines " + _lineCount;
            _writer.writeLine(commandString);
            break;
         case Property:
            FCsvCommandProperty ptyCmd = (FCsvCommandProperty)command;
            commandString = "@property " + ptyCmd.name() + "=" + ptyCmd.value();
            _writer.writeLine(commandString);
            break;
         default:
            break;
      }
   }

   public void doCommand(ICsvCommand command,
                         boolean isWrite){
      String commandString = null;
      switch(command.type()){
         case Head:
            if(isWrite){
               commandString = "@head";
               _writer.writeLine(commandString);
            }
            FCsvLine line = new FCsvLine();
            for(String name : _columns.names()){
               line.push(name);
            }
            writeLine(line);
            break;
         case Label:
            if(isWrite){
               commandString = "@label";
               _writer.writeLine(commandString);
            }
            FCsvLine csvline = new FCsvLine();
            for(FCsvColumn column : _columns.values()){
               String label = column.label();
               if(null != label){
                  csvline.push(label);
               }
            }
            if(null != csvline){
               writeLine(csvline);
            }
            break;
         case Data:
            if(isWrite){
               commandString = "@data.start";
               _writer.writeLine(commandString);
            }
            _lineCount = 0;
            break;
         case Comment:
            if(isWrite){
               commandString = "@Comment";
               _writer.writeLine(commandString);
            }
            FCsvCommandComment comment = (FCsvCommandComment)command;
            _writer.writeLine(comment.description());
            break;
         case End:
            if(isWrite){
               commandString = "@data.end";
               _writer.writeLine(commandString);
            }
            break;
         case ValidLines:
            if(isWrite){
               commandString = "@validLines lines " + _lineCount;
               _writer.writeLine(commandString);
            }
            break;
         case Property:
            if(isWrite){
               FCsvCommandProperty ptyCmd = (FCsvCommandProperty)command;
               commandString = "@property " + ptyCmd.name() + "=" + ptyCmd.value();
               _writer.writeLine(commandString);
            }
            break;
         default:
            break;
      }
   }

   public String fileName(){
      return _fileName;
   }

   public void Flush(){
   }

   // 创建写入对象
   public void openFile(String fileName){
      _writer.openFile(fileName);
   }

   // 创建写入对象
   public void openFile(String fileName,
                        String charset){
      _writer.openFile(fileName, charset);
   }

   // 按行写入
   public void writeLine(ICsvLine line){
      FString result = new FString();
      int count = line.count();
      for(int n = 0; n < count; n++){
         if(n > 0){
            result.append(',');
         }
         RCsv.formatField(result, line.value(n));
      }
      _writer.writeLine(result);
      _lineCount++;
   }
}
