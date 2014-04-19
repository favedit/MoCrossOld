package org.mo.eng.session.common;

import org.mo.com.collections.FObjectDictionary;
import org.mo.com.lang.FAttributes;
import org.mo.com.lang.FObject;
import org.mo.com.lang.RDump;
import org.mo.com.lang.cultrue.FCulture;
import org.mo.com.lang.generic.TDumpInfo;
import org.mo.eng.security.IPermission;

//============================================================
// <T>会话。</T>
//============================================================
public class FSession
      extends FObject
      implements
         ISession
{
   // 序列化标志
   private static final long serialVersionUID = 1L;

   // 线程标识
   protected String _id;

   //  存储标识
   protected String _storeId;

   // 参数列表
   protected FAttributes _parameters;

   // 属性列表
   protected FObjectDictionary _attributes;

   // 关联对象列表
   protected FObjectDictionary _componments;

   // 环境信息
   protected FCulture _culture;

   // 权限对象
   protected IPermission _permission;

   // 引用次数
   protected int _referCount = 0;

   //============================================================
   // <T>构造会话。</T>
   //============================================================
   public FSession(){
   }

   //============================================================
   // <T>获得编号。</T>
   //
   // @return 编号
   //============================================================
   @Override
   public String id(){
      return _id;
   }

   //============================================================
   // <T>设置编号。</T>
   //
   // @param id 编号
   //============================================================
   public void setId(String id){
      _id = id;
   }

   //============================================================
   // <T>获得存储编号。</T>
   //
   // @return 存储编号
   //============================================================
   @Override
   public String storeId(){
      return _storeId;
   }

   //============================================================
   // <T>设置存储编号。</T>
   //
   // @param storeId 存储编号
   //============================================================
   public void setStoreId(String storeId){
      _storeId = storeId;
   }

   //============================================================
   // <T>获得语言编号。</T>
   //
   // @return 语言编号
   //============================================================
   @Override
   public String languageId(){
      return culture().countryLanguage();
   }

   //============================================================
   // <T>设置语言编号。</T>
   //
   // @param languageId 语言编号
   //============================================================
   @Override
   public void setLanguageId(String languageId){
      culture().country().setLanguage(languageId);
   }

   //============================================================
   // <T>获得属性字典。</T>
   //
   // @return 属性字典
   //============================================================
   @Override
   public FObjectDictionary attributes(){
      if(_attributes == null){
         _attributes = new FObjectDictionary();
      }
      return _attributes;
   }

   //============================================================
   // <T>获得参数字典。</T>
   //
   // @return 参数字典
   //============================================================
   @Override
   public FAttributes parameters(){
      if(_parameters == null){
         _parameters = new FAttributes();
      }
      return _parameters;
   }

   //============================================================
   // <T>获得权限对象。</T>
   //
   // @return 权限对象
   //============================================================
   @Override
   public IPermission permission(){
      return _permission;
   }

   //============================================================
   // <T>设置权限对象。</T>
   //
   // @param permission 权限对象
   //============================================================
   public void setPermission(IPermission permission){
      _permission = permission;
   }

   //============================================================
   // <T>获得组件字典。</T>
   //
   // @return 组件字典
   //============================================================
   @Override
   public FObjectDictionary componments(){
      if(null == _componments){
         _componments = new FObjectDictionary();
      }
      return _componments;
   }

   //============================================================
   // <T>获得文化设置。</T>
   //
   // @return 文化设置
   //============================================================
   @Override
   public FCulture culture(){
      if(null == _culture){
         _culture = new FCulture();
      }
      return _culture;
   }

   //============================================================
   // <T>测试是否被引用。</T>
   //
   // @return 是否被引用
   //============================================================
   @Override
   public boolean referTest(){
      return (_referCount > 0);
   }

   //============================================================
   // <T>测试引用计数加一。</T>
   //============================================================
   @Override
   public void referIncrease(){
      _referCount++;
   }

   //============================================================
   // <T>测试引用计数减一。</T>
   //============================================================
   @Override
   public void referDecrease(){
      if(_referCount > 0){
         _referCount--;
      }
   }

   //============================================================
   // <T>生成运行信息。</T>
   //
   // @param info 运行信息
   // @return 运行信息
   //============================================================
   @Override
   public TDumpInfo dump(TDumpInfo info){
      RDump.dump(info);
      info.append("\n   Attributes  : ");
      info.append(attributes().dump());
      info.append("\n   Parameters  : ");
      info.append(parameters().dump());
      return info;
   }
}
