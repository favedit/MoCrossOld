package org.mo.com.lang;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.UndeclaredThrowableException;

//============================================================
// <T>错误。</T>
//============================================================
public abstract class FError
      extends RuntimeException
{
   // 序列化标识
   private final static long serialVersionUID = 1L;

   // 消息
   protected String _message;

   // 参数
   protected Object[] _params;

   // 例外
   protected Throwable _throwable;

   //============================================================
   // <T>构造错误。</T>
   //============================================================
   public FError(){
   }

   //============================================================
   // <T>构造错误。</T>
   //
   // @param message 消息
   //============================================================
   public FError(String message){
      _message = message;
   }

   //============================================================
   // <T>构造错误。</T>
   //
   // @param message 消息
   // @param params 参数
   //============================================================
   public FError(String message,
                 Object... params){
      _message = message;
      _params = params;
   }

   //============================================================
   // <T>构造错误。</T>
   //
   // @param throwable 例外
   //============================================================
   public FError(Throwable throwable){
      _throwable = throwable;
   }

   //============================================================
   // <T>构造错误。</T>
   //
   // @param throwable 例外
   // @param message 消息
   //============================================================
   public FError(Throwable throwable,
                 String message){
      _throwable = throwable;
      _message = message;
   }

   //============================================================
   // <T>构造错误。</T>
   //
   // @param throwable 例外
   // @param message 消息
   // @param params 参数
   //============================================================
   public FError(Throwable throwable,
                 String message,
                 Object... params){
      _throwable = throwable;
      _message = message;
      _params = params;
   }

   public String description(){
      return RString.format(_message, _params);
   }

   //============================================================
   // <T>获得消息字符串。</T>
   //
   // @return 消息字符串
   //============================================================
   public String message(){
      return _message;
   }

   //============================================================
   // <T>获得消息参数。</T>
   //
   // @return 消息参数
   //============================================================
   public Object[] params(){
      return _params;
   }

   //============================================================
   // <T>获得例外。</T>
   //
   // @return 例外
   //============================================================
   public Throwable throwable(){
      if(null != _throwable){
         return _throwable;
      }
      return this;
   }

   //============================================================
   // <T>获得根例外。</T>
   //
   // @return 例外
   //============================================================
   public Throwable rootThrowable(){
      Throwable throwable = _throwable;
      while(null != throwable){
         if(throwable instanceof InvocationTargetException){
            throwable = ((InvocationTargetException)throwable).getTargetException();
         }else if(throwable instanceof UndeclaredThrowableException){
            throwable = ((UndeclaredThrowableException)throwable).getUndeclaredThrowable();
         }else if(throwable instanceof FError){
            throwable = ((FError)throwable)._throwable;
         }else{
            break;
         }
      }
      return throwable;
   }

   //============================================================
   // <T>获得例外栈内容。</T>
   //
   // @return 栈内容
   //============================================================
   public String rootCauseStack(){
      try{
         Throwable throwable = rootThrowable();
         ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
         throwable.printStackTrace(new PrintStream(outputStream));
         String stack = outputStream.toString();
         outputStream.close();
         return stack;
      }catch(Exception e){
         throw new FFatalError(e);
      }
   }

   //============================================================
   // <T>获得内容字符串。</T>
   //
   // @return 字符串
   //============================================================
   @Override
   public String toString(){
      return RString.format(_message, _params);
   }
}
