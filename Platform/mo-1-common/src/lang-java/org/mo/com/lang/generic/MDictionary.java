package org.mo.com.lang.generic;

import java.util.Iterator;
import org.mo.com.lang.EStringCase;
import org.mo.com.lang.FStrings;
import org.mo.com.lang.IDictionary;
import org.mo.com.lang.INamePair;
import org.mo.com.lang.RStrings;

//============================================================
//<T>哈希字典。</T>
//============================================================
public abstract class MDictionary<P extends INamePair<V>, V>
      extends MMap<P, String, V>
      implements
         IDictionary<V>
{
   // 名称处理
   protected EStringCase _nameCase = EStringCase.Unchange;

   // 是否关心字符大小写
   protected boolean _careCase = true;

   //============================================================
   // <T>构造哈希字典。</T>
   //
   // @param clazz 内容类对象
   //============================================================
   public MDictionary(){
   }

   //============================================================
   // <T>构造哈希字典。</T>
   //
   // @param clazz 内容类对象
   //============================================================
   public MDictionary(Class<V> clazz){
      super(String.class, clazz);
   }

   //============================================================
   // <T>构造哈希字典。</T>
   //
   // @param clazz 内容类对象
   // @param capacity 容量
   //============================================================
   public MDictionary(Class<V> clazz,
                      int capacity){
      super(String.class, clazz, capacity);
   }

   //============================================================
   // <T>根据名称获得变换后名称。</T>
   //
   // @param name 名称
   // @return 名称
   //============================================================
   @Override
   protected String innerMakeName(String name){
      if(name != null){
         return _careCase ? name : name.toLowerCase();
      }
      return name;
   }

   //============================================================
   // <T>获得名称大小写。</T>
   //
   // @return 名称大小写
   //============================================================
   public EStringCase nameCase(){
      return _nameCase;
   }

   //============================================================
   // <T>设置名称大小写。</T>
   //
   // @param nameCase 名称大小写
   //============================================================
   public void setNameCase(EStringCase nameCase){
      _nameCase = nameCase;
   }

   //============================================================
   // <T>获得是否关心字符大小写。</T>
   //
   // @return 是否关心字符大小写
   //============================================================
   public boolean careCase(){
      return _careCase;
   }

   //============================================================
   // <T>设置是否关心字符大小写。</T>
   //
   // @param careCase 是否关心字符大小写
   //============================================================
   public void setCareCase(boolean careCase){
      _careCase = careCase;
   }

   //============================================================
   // <T>获得所有名称中最短的长度。</T>
   //
   // @return 长度
   //============================================================
   public int nameLengthMin(){
      return RStrings.lengthMin(_names, 0, _count);
   }

   //============================================================
   // <T>获得所有名称中最长的长度。</T>
   //
   // @return 长度
   //============================================================
   public int nameLengthMax(){
      return RStrings.lengthMax(_names, 0, _count);
   }

   //============================================================
   // <T>获得迭代器。</T>
   //
   // @return 迭代器
   //============================================================
   @Override
   public Iterator<P> iterator(){
      return new FPairIterator<P, String, V>(new FNamePair<V>(), _names, _values, 0, _count);
   }

   //============================================================
   // <T>获得名称集合。</T>
   //
   // @return 名称集合
   //============================================================
   public FStrings nameStrings(){
      return new FStrings(_names, 0, _count);
   }
}
