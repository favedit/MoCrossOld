package org.mo.com.lang;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.UndeclaredThrowableException;
import java.rmi.RemoteException;
import org.mo.com.lang.reflect.RClass;

//============================================================
// <T>例外管理器。</T>
//============================================================
public class RThrowable
{
   // 分割线
   public static String DETAIL_LINE = "\n-- Detail --------------------------------------------------\n";

   //============================================================
   // <T>建立例外堆栈集合。</T>
   //
   // @param throwable 例外对象
   // @return 堆栈集合
   //============================================================
   public static FThrowables buildStack(Throwable throwable){
      FThrowables stack = new FThrowables();
      while(throwable != null && !stack.contains(throwable)){
         stack.push(throwable);
         if(throwable instanceof FError){
            throwable = ((FError)throwable).throwable();
         }else if(throwable instanceof RemoteException){
            throwable = ((RemoteException)throwable).detail;
         }else if(throwable instanceof InvocationTargetException){
            throwable = ((InvocationTargetException)throwable).getTargetException();
         }else if(throwable instanceof UndeclaredThrowableException){
            throwable = ((UndeclaredThrowableException)throwable).getUndeclaredThrowable();
         }else{
            throwable = throwable.getCause();
         }
      }
      return stack;
   }

   //============================================================
   // <T>建立例外信息。</T>
   //
   // @param throwable 例外
   // @return 例外信息
   //============================================================
   public static String buildMessage(Throwable throwable){
      FString result = new FString();
      buildMessage(result, throwable);
      return result.toString();
   }

   //============================================================
   // <T>获得抛出例外的根错误。</T>
   //
   // @param throwable 例外
   // @return 根错误
   //============================================================
   public static void buildMessage(FString message,
                                   Throwable throwable){
      message.assign(buildStack(throwable).last().getMessage());
   }

   //============================================================
   // <T>建立例外堆栈集合。</T>
   //
   // @param message 消息信息
   // @param stack 堆栈集合
   // @return 堆栈集合
   //============================================================
   public static void buildMessage(FString message,
                                   FThrowables stack){
      Throwable throwable = null;
      int count = stack.count();
      Throwable rootThrowable = stack.get(count - 1);
      // 获得类名称和函数名的最大长度
      int maxLength = 0;
      for(int n = count - 1; n >= 0; n--){
         throwable = stack.get(n);
         StackTraceElement[] traces = throwable.getStackTrace();
         if(null != traces && traces.length > 0){
            StackTraceElement trace = traces[0];
            int length = RClass.shortName(trace.getClassName()).length();
            length += trace.getMethodName().length() + 1;
            maxLength = Math.max(maxLength, length);
         }
      }
      // 获得错误堆栈的信息列表
      for(int n = count - 1; n >= 0; n--){
         throwable = stack.get(n);
         StackTraceElement[] traces = throwable.getStackTrace();
         if(null != traces && traces.length > 0){
            message.append('\n');
            message.appendInt(count - n);
            message.append('.');
            if(count > 9 && count - n < 10){
               message.append(' ');
            }
            // 获得错误类和函数的名称
            StackTraceElement trace = traces[0];
            message.append(" [ ");
            String classMethod = RClass.shortName(trace.getClassName());
            classMethod += "." + trace.getMethodName();
            message.append(classMethod);
            message.append(RString.repeat(" ", maxLength - classMethod.length()));
            message.append(" ] ");
            // 获得错误内容
            if(throwable instanceof FError){
               FError exception = (FError)throwable;
               String info = exception.message();
               Object[] params = exception.params();
               if(params != null){
                  info = RString.format(info, params);
               }
               message.append(info);
            }else{
               message.append(throwable.getMessage());
            }
         }
      }
      if(rootThrowable != null){
         ByteArrayOutputStream oOutputStream = new ByteArrayOutputStream();
         rootThrowable.printStackTrace(new PrintStream(oOutputStream));
         String info = oOutputStream.toString();
         info = info.replaceAll("\r", "");
         info = info.replaceAll("\n\n", "\n");
         message.append(DETAIL_LINE);
         message.append(info);
      }
   }

   //============================================================
   // <T>建立描述信息。</T>
   //
   // @param throwable 例外
   // @return 描述信息
   //============================================================
   public static String buildDescription(Throwable throwable){
      FString result = new FString();
      buildMessage(result, buildStack(throwable));
      return result.toString();
   }

   //============================================================
   // <T>获得抛出例外的根错误。</T>
   //
   // @param throwable 例外
   // @return 根错误
   //============================================================
   public static Throwable rootThrowable(Throwable throwable){
      return buildStack(throwable).last();
   }
}
