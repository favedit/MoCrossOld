package org.mo.com.data;

import org.mo.com.lang.FAttributes;
import org.mo.com.lang.FString;
import org.mo.com.lang.RString;
import org.mo.com.lang.RThrowable;
import org.mo.com.lang.generic.MString;
import org.mo.com.message.FErrorMessage;
import org.mo.com.message.FFatalMessage;
import org.mo.com.message.FMessages;
import org.mo.com.resource.IResource;
import org.mo.com.resource.RResource;

//============================================================
// <T>SQL数据工具类。</T>
//============================================================
public class RSql
{
   // 资源接口
   private static IResource _resource = RResource.find(RSql.class);

   // 最大获取限制
   public final static int FETCH_MAX_COUNT = 100000;

   // 默认页最大记录数
   public final static int PAGE_MAX_SIZE = 10000;

   // 默认页最小记录数
   public final static int PAGE_MIN_SIZE = 1;

   // 默认页位置
   public final static int PAGE_POSITION = 0;

   // 默认页记录数
   public final static int PAGE_SIZE = 20;

   // 最大重试次数
   public final static int RETRY_MAX_COUNT = 3;

   // 空数据
   public final static String NULL = "NULL";

   //============================================================
   // <T>格式化字符串为命令内容。</T>
   //
   // @param value 内容
   // @return 命令内容
   //============================================================
   public final static String formatValue(String value){
      if(!RString.isEmpty(value)){
         StringBuffer sql = new StringBuffer();
         for(char ch : value.toCharArray()){
            if('\'' == ch){
               sql.append("''");
            }else{
               sql.append(ch);
            }
         }
         return sql.toString();
      }
      return RString.EMPTY;
   }

   //============================================================
   // <T>格式化命令字符串。</T>
   //
   // @param sql 命令字符串
   // @return 命令字符串
   //============================================================
   public final static String formatSql(String sql){
      FString command = new FString();
      formatSql(command, sql);
      return command.toString();
   }

   //============================================================
   // <T>格式化命令字符串。</T>
   //
   // @param sql 命令字符串
   // @return 命令字符串
   //============================================================
   public final static String formatSql(FString sql){
      FString command = new FString();
      formatSql(command, sql.toString());
      return command.toString();
   }

   //============================================================
   // <T>格式化命令字符串。</T>
   //
   // @param command 命令字符串
   // @param sql 命令字符串
   //============================================================
   public final static void formatSql(MString command,
                                      String sql){
      // 清空缓冲
      command.clear();
      // 检查参数
      if(sql == null){
         return;
      }
      if(sql.isEmpty()){
         return;
      }
      // 格式化处理
      String[] items = RString.split(sql, '\n');
      if(null != items){
         boolean first = true;
         for(String item : items){
            item = item.trim();
            if(item.length() > 0){
               if(first){
                  first = false;
               }else{
                  command.append(' ');
               }
               command.append(item);
            }
         }
      }
   }

   //============================================================
   // <T>解析例外处理信息。</T>
   //
   // @param messages 消息集合
   // @param throwable 抛出例外
   //============================================================
   public static void parseSqlException(FMessages messages,
                                        Throwable throwable){
      String sqlMessage = throwable.getMessage();
      if(sqlMessage.contains("ORA-20001")){
         // 系统例外消息
         String message = RString.mid(sqlMessage, "MsgBegin[", "]MsgEnd");
         if(!RString.isEmpty(message)){
            FAttributes pack = new FAttributes();
            pack.unpack(message);
            // 加入一个错误消息
            String msg = _resource.findDisplay(pack.get("C"));
            if(RString.isEmpty(msg)){
               msg = pack.get("M");
            }
            msg = RString.format(msg, pack.get("1"), pack.get("2"), pack.get("3"), pack.get("4"), pack.get("5"), pack.get("6"));
            FErrorMessage error = new FErrorMessage();
            error.setCode(pack.get("C"));
            error.setMessage(msg);
            //error.setDescription(sqlMessage);
            error.setDescription(RThrowable.buildDescription(throwable).toString());
            messages.push(error);
         }
      }else if(sqlMessage.contains("ORA-20002")){
         // 业务例外消息
         String message = RString.mid(sqlMessage, "MsgBegin[", "]MsgEnd");
         if(!RString.isEmpty(message)){
            FAttributes pack = new FAttributes();
            pack.unpack(message);
            // 加入一个错误消息
            String msg = RString.format(pack.get("M"), pack.get("1"), pack.get("2"), pack.get("3"), pack.get("4"), pack.get("5"), pack.get("6"));
            FErrorMessage error = new FErrorMessage();
            error.setCode(pack.get("C"));
            error.setMessage(msg);
            //error.setDescription(sqlMessage);
            error.setDescription(RThrowable.buildDescription(throwable).toString());
            messages.push(error);
         }
      }else{
         // 其他例外消息
         messages.push(new FFatalMessage(throwable));
      }
   }

   //============================================================
   // <T>跟踪数字信息。</T>
   //
   // @param value 数据内容
   // @return 显示内容
   //============================================================
   public final static String dumpNumber(String value){
      if(!RString.isEmpty(value)){
         return value;
      }
      return "NULL";
   }

   //============================================================
   // <T>跟踪时间信息。</T>
   //
   // @param value 数据内容
   // @return 显示内容
   //============================================================
   public final static String dumpDate(String value){
      if(!RString.isEmpty(value)){
         int dateLength = value.length();
         if(dateLength == 14){
            return "TO_DATE('" + value + "', 'YYYYMMDDHH24MISS')";
         }else if(dateLength == 8){
            return "TO_DATE('" + value + "', 'YYYYMMDD')";
         }else if(dateLength == 6){
            return "TO_DATE('" + value + "', 'YYYYMM')";
         }else if(dateLength == 4){
            return "TO_DATE('" + value + "', 'YYYY')";
         }
      }
      return "NULL";
   }

   //============================================================
   // <T>跟踪字符串信息。</T>
   //
   // @param value 数据内容
   // @return 显示内容
   //============================================================
   public final static String dumpString(String value){
      if(!RString.isEmpty(value)){
         return "'" + value + "'";
      }
      return "NULL";
   }

   //============================================================
   // <T>跟踪指定类型的信息。</T>
   //
   // @param typeCd 数据类型
   // @param value 数据内容
   // @return 显示内容
   //============================================================
   public final static String dumpValue(ESqlDataType typeCd,
                                        String value){
      if(ESqlDataType.DateTime == typeCd){
         return dumpDate(value);
      }else if((ESqlDataType.Integer == typeCd) || (ESqlDataType.Float == typeCd)){
         return dumpNumber(value);
      }else if(ESqlDataType.String == typeCd){
         return dumpString(value);
      }
      return "NULL";
   }
}
