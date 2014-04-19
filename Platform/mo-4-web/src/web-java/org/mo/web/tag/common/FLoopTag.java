package org.mo.web.tag.common;

import org.mo.com.lang.FAttributes;
import org.mo.com.lang.IAttributes;
import org.mo.com.lang.RString;

//============================================================
// <T>集合循环标签</T>
// <P>使用指定的集合对象进行循环，每一次循环，将集合内的对象定义为指定的别称，</BR>
// 在标签作用范围内，可以和i使用这些别称，对集合内的对象进行操作。</P>
//
// @author MAOCY
// @version 1.00 - 2008/08/05 CREATE BY MAOCY
//============================================================
public class FLoopTag
      extends FAbstractIterationTag
{
   // 对象的定义
   protected String _source;

   // 别称的定义
   protected String _alias;

   // 过滤器
   protected String _filter;

   // 循环对象别称的定义
   protected String _looper;

   // 集合对象内的循环数目
   private int _loopCount;

   // 循环对象
   private IAttributes _looperObject;

   // 开始位置
   protected String _offset;

   // 循环次数
   protected String _count;

   // 循环对象
   private Object[] _objects;

   // 集合对象内的数据位置
   private int _position;

   //============================================================
   // <T>设置来源。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setSource(String value){
      _source = value;
   }

   //============================================================
   // <T>设置别称。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setAlias(String value){
      _alias = value;
   }

   //============================================================
   // <T>设置循环别称。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setLooper(String value){
      _looper = value;
   }

   //============================================================
   // <T>设置过滤器。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setFilter(String value){
      _filter = value;
   }

   //============================================================
   // <T>设置开始位置。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setOffset(String value){
      _offset = value;
   }

   //============================================================
   // <T>设置循环次数。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setCount(String value){
      _count = value;
   }

   //============================================================
   // <T>标签内容解析开始处理。</T>
   //
   // @return 处理结果
   //============================================================
   @Override
   public int onStart(){
      _objects = _context.parseObjects(_source);
      _loopCount = _objects != null ? _objects.length : 0;
      _position = 0;
      /// 开始循环，记录首位置
      if(_loopCount > 0 && !RString.isEmpty(_alias)){
         if(!RString.isEmpty(_looper)){
            _looperObject = new FAttributes();
            _context.define(_looper, _looperObject);
            _looperObject.set("count", Integer.toString(_loopCount));
            _looperObject.set("position", Integer.toString(_position));
         }
         _context.define(_alias, _objects[_position]);
         return EVAL_BODY_INCLUDE;
      }
      return SKIP_BODY;
   }

   //============================================================
   // <T>标签内容解析后置处理。</T>
   //
   // @return 处理结果
   //============================================================
   @Override
   public int onAfter(){
      if(++_position < _loopCount){
         if(!RString.isEmpty(_looper)){
            _looperObject.set("position", Integer.toString(_position));
         }
         _context.define(_alias, _objects[_position]);
         return EVAL_BODY_AGAIN;
      }
      return EVAL_PAGE;
   }

   //============================================================
   // <T>标签内容解析结束处理。</T>
   //
   // @return 处理结果
   //============================================================
   @Override
   public int onEnd(){
      /// 清除别称
      if(_objects != null){
         _context.undefine(_looper + ".position");
         _context.undefine(_looper + ".count");
         _context.undefine(_alias);
      }
      return EVAL_PAGE;
   }

   //============================================================
   // <T>标签释放处理。</T>
   //============================================================
   @Override
   public void release(){
      _alias = null;
      _source = null;
      _looper = null;
      _position = 0;
      _loopCount = 0;
      _objects = null;
      _filter = null;
      _offset = null;
      _count = null;
      super.release();
   }
}
