package org.mo.eng.data.common;

public enum ESqlSearchType{
   /**
    * <T>字符串方式比较，开始部分相同。<T>
    */
   Begin,
   /**
    * <T>字符串方式比较，日期时间按照格式比较。<T>
    */
   Date,
   /**
    * <T>字符串方式比较，结尾部分相同。<T>
    */
   End,
   /**
    * <T>字符串方式比较，全部相同。<T>
    */
   Equals,
   /**
    * <T>字符串方式比较，数据在几种指定情况中。<T>
    */
   In,
   /**
    * <T>字符串方式比较，内容包含指定部分。<T>
    */
   Like,
   /**
    * <T>未知比较方式。<T>
    */
   None,
   /**
    * <T>SQL文。<T>
    */
   Source
}
