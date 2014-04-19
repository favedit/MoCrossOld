package org.mo.eng.translate.base;

import org.mo.com.lang.RBoolean;
import org.mo.com.lang.RString;
import org.mo.com.lang.face.AName;
import org.mo.com.xml.EXmlConfig;
import org.mo.com.xml.FXmlNode;
import org.mo.com.xml.FXmlObject;
import org.mo.com.xml.IXmlObject;
import org.mo.eng.translate.common.XObjectFace;

//============================================================
// <T>翻译对象对象的XML节点基类。</T>
//============================================================
public abstract class XBaseTranslate
      extends FXmlObject
      implements
         XObjectFace
{
   // 名称定义
   public static final String NAME = "Translate";

   //============================================================
   // <T>获得名称定义。</T>
   //
   // @return 名称定义
   //============================================================
   public String name(){
      return NAME;
   }

   //============================================================
   // <T>判断是否指定名称。</T>
   //
   // @param name 名称
   // @return 是否指定
   //============================================================
   public static boolean isName(String name){
      return NAME.equals(name);
   }

   //============================================================
   // <T>判断是否指定实例。</T>
   //
   // @param xobject 对象
   // @return 是否指定
   //============================================================
   public static boolean isInstance(IXmlObject xobject){
      return NAME.equals(xobject.name());
   }

   // 名称的名称定义
   public static final String PTY_NAME = "name";

   // 标签的名称定义
   public static final String PTY_LABEL = "label";

   // 有效性的名称定义
   public static final String PTY_IS_VALID = "is_valid";

   // 描述信息的名称定义
   public static final String PTY_NOTE = "note";

   // 代码编号的名称定义
   public static final String PTY_CODE = "code";

   // 代码类型的名称定义
   public static final String PTY_TYPE_CD = "type_cd";

   // 翻译状态的名称定义
   public static final String PTY_STATUS_CD = "status_cd";

   // 内容的名称定义
   public static final String PTY_CONTENT = "content";

   // 创建者工号的名称定义
   public static final String PTY_CREATE_USER_CODE = "create_user_code";

   // 更新人工号的名称定义
   public static final String PTY_UPDATE_USER_CODE = "update_user_code";

   // 更新时间的名称定义
   public static final String PTY_UPDATE_DATE = "update_date";

   // 中文内容的名称定义
   public static final String PTY_ZH_CONTENT = "zh_content";

   // 日文内容的名称定义
   public static final String PTY_JA_CONTENT = "ja_content";

   // 名称的定义
   @AName("name")
   protected String _name;

   // 标签的定义
   @AName("label")
   protected String _label;

   // 有效性的定义
   @AName("is_valid")
   protected Boolean _isValid = Boolean.FALSE;

   // 描述信息的定义
   @AName("note")
   protected String _note;

   // 代码编号的定义
   @AName("code")
   protected String _code;

   // 代码类型的定义
   @AName("type_cd")
   protected String _typeCd;

   // 翻译状态的定义
   @AName("status_cd")
   protected String _statusCd;

   // 内容的定义
   @AName("content")
   protected String _content;

   // 创建者工号的定义
   @AName("create_user_code")
   protected String _createUserCode;

   // 更新人工号的定义
   @AName("update_user_code")
   protected String _updateUserCode;

   // 更新时间的定义
   @AName("update_date")
   protected String _updateDate;

   // 中文内容的定义
   @AName("zh_content")
   protected String _zhContent;

   // 日文内容的定义
   @AName("ja_content")
   protected String _jaContent;

   //============================================================
   // <T>获得名称的内容。</T>
   //
   // @return 名称
   //============================================================
   public String getName(){
      return _name;
   }

   //============================================================
   // <T>设置名称的内容。</T>
   //
   // @param value 名称
   //============================================================
   public void setName(String value){
      _name = value;
   }

   //============================================================
   // <T>获得标签的内容。</T>
   //
   // @return 标签
   //============================================================
   public String getLabel(){
      return _label;
   }

   //============================================================
   // <T>设置标签的内容。</T>
   //
   // @param value 标签
   //============================================================
   public void setLabel(String value){
      _label = value;
   }

   //============================================================
   // <T>获得有效性的内容。</T>
   //
   // @return 有效性
   //============================================================
   public Boolean getIsValid(){
      return _isValid;
   }

   //============================================================
   // <T>设置有效性的内容。</T>
   //
   // @param value 有效性
   //============================================================
   public void setIsValid(Boolean value){
      _isValid = value;
   }

   //============================================================
   // <T>获得描述信息的内容。</T>
   //
   // @return 描述信息
   //============================================================
   public String getNote(){
      return _note;
   }

   //============================================================
   // <T>设置描述信息的内容。</T>
   //
   // @param value 描述信息
   //============================================================
   public void setNote(String value){
      _note = value;
   }

   //============================================================
   // <T>获得代码编号的内容。</T>
   //
   // @return 代码编号
   //============================================================
   public String getCode(){
      return _code;
   }

   //============================================================
   // <T>设置代码编号的内容。</T>
   //
   // @param value 代码编号
   //============================================================
   public void setCode(String value){
      _code = value;
   }

   //============================================================
   // <T>获得代码类型的内容。</T>
   //
   // @return 代码类型
   //============================================================
   public String getTypeCd(){
      return _typeCd;
   }

   //============================================================
   // <T>设置代码类型的内容。</T>
   //
   // @param value 代码类型
   //============================================================
   public void setTypeCd(String value){
      _typeCd = value;
   }

   //============================================================
   // <T>获得翻译状态的内容。</T>
   //
   // @return 翻译状态
   //============================================================
   public String getStatusCd(){
      return _statusCd;
   }

   //============================================================
   // <T>设置翻译状态的内容。</T>
   //
   // @param value 翻译状态
   //============================================================
   public void setStatusCd(String value){
      _statusCd = value;
   }

   //============================================================
   // <T>获得内容的内容。</T>
   //
   // @return 内容
   //============================================================
   public String getContent(){
      return _content;
   }

   //============================================================
   // <T>设置内容的内容。</T>
   //
   // @param value 内容
   //============================================================
   public void setContent(String value){
      _content = value;
   }

   //============================================================
   // <T>获得创建者工号的内容。</T>
   //
   // @return 创建者工号
   //============================================================
   public String getCreateUserCode(){
      return _createUserCode;
   }

   //============================================================
   // <T>设置创建者工号的内容。</T>
   //
   // @param value 创建者工号
   //============================================================
   public void setCreateUserCode(String value){
      _createUserCode = value;
   }

   //============================================================
   // <T>获得更新人工号的内容。</T>
   //
   // @return 更新人工号
   //============================================================
   public String getUpdateUserCode(){
      return _updateUserCode;
   }

   //============================================================
   // <T>设置更新人工号的内容。</T>
   //
   // @param value 更新人工号
   //============================================================
   public void setUpdateUserCode(String value){
      _updateUserCode = value;
   }

   //============================================================
   // <T>获得更新时间的内容。</T>
   //
   // @return 更新时间
   //============================================================
   public String getUpdateDate(){
      return _updateDate;
   }

   //============================================================
   // <T>设置更新时间的内容。</T>
   //
   // @param value 更新时间
   //============================================================
   public void setUpdateDate(String value){
      _updateDate = value;
   }

   //============================================================
   // <T>获得中文内容的内容。</T>
   //
   // @return 中文内容
   //============================================================
   public String getZhContent(){
      return _zhContent;
   }

   //============================================================
   // <T>设置中文内容的内容。</T>
   //
   // @param value 中文内容
   //============================================================
   public void setZhContent(String value){
      _zhContent = value;
   }

   //============================================================
   // <T>获得日文内容的内容。</T>
   //
   // @return 日文内容
   //============================================================
   public String getJaContent(){
      return _jaContent;
   }

   //============================================================
   // <T>设置日文内容的内容。</T>
   //
   // @param value 日文内容
   //============================================================
   public void setJaContent(String value){
      _jaContent = value;
   }

   //============================================================
   // <T>内部获得内容置信息。</T>
   //
   // @param name 名称
   // @return 内容
   //============================================================
   public String innerGet(String name){
      if(RString.isEmpty(name)){
         return null;
      }else if(PTY_NAME.equalsIgnoreCase(name)){
         return getName();
      }else if(PTY_LABEL.equalsIgnoreCase(name)){
         return getLabel();
      }else if(PTY_IS_VALID.equalsIgnoreCase(name)){
         return RBoolean.toString(getIsValid());
      }else if(PTY_NOTE.equalsIgnoreCase(name)){
         return getNote();
      }else if(PTY_CODE.equalsIgnoreCase(name)){
         return getCode();
      }else if(PTY_TYPE_CD.equalsIgnoreCase(name)){
         return getTypeCd();
      }else if(PTY_STATUS_CD.equalsIgnoreCase(name)){
         return getStatusCd();
      }else if(PTY_CONTENT.equalsIgnoreCase(name)){
         return getContent();
      }else if(PTY_CREATE_USER_CODE.equalsIgnoreCase(name)){
         return getCreateUserCode();
      }else if(PTY_UPDATE_USER_CODE.equalsIgnoreCase(name)){
         return getUpdateUserCode();
      }else if(PTY_UPDATE_DATE.equalsIgnoreCase(name)){
         return getUpdateDate();
      }else if(PTY_ZH_CONTENT.equalsIgnoreCase(name)){
         return getZhContent();
      }else if(PTY_JA_CONTENT.equalsIgnoreCase(name)){
         return getJaContent();
      }
      return null;
   }

   //============================================================
   // <T>内部设置内容置信息。</T>
   //
   // @param name 名称
   // @param value 内容
   //============================================================
   public void innerSet(String name, String value){
      if(RString.isEmpty(name)){
         return;
      }else if(PTY_NAME.equalsIgnoreCase(name)){
         setName(value);
      }else if(PTY_LABEL.equalsIgnoreCase(name)){
         setLabel(value);
      }else if(PTY_IS_VALID.equalsIgnoreCase(name)){
         setIsValid(RBoolean.parse(value));
      }else if(PTY_NOTE.equalsIgnoreCase(name)){
         setNote(value);
      }else if(PTY_CODE.equalsIgnoreCase(name)){
         setCode(value);
      }else if(PTY_TYPE_CD.equalsIgnoreCase(name)){
         setTypeCd(value);
      }else if(PTY_STATUS_CD.equalsIgnoreCase(name)){
         setStatusCd(value);
      }else if(PTY_CONTENT.equalsIgnoreCase(name)){
         setContent(value);
      }else if(PTY_CREATE_USER_CODE.equalsIgnoreCase(name)){
         setCreateUserCode(value);
      }else if(PTY_UPDATE_USER_CODE.equalsIgnoreCase(name)){
         setUpdateUserCode(value);
      }else if(PTY_UPDATE_DATE.equalsIgnoreCase(name)){
         setUpdateDate(value);
      }else if(PTY_ZH_CONTENT.equalsIgnoreCase(name)){
         setZhContent(value);
      }else if(PTY_JA_CONTENT.equalsIgnoreCase(name)){
         setJaContent(value);
      }
   }

   //============================================================
   // <T>加载设置信息。</T>
   //
   // @param config 设置信息
   // @param type 类型
   //============================================================
   public void loadConfig(FXmlNode config, EXmlConfig type){
      super.loadConfig(config, type);
      if(EXmlConfig.Full == type){
         if(config.contains("name")){
            setName(config.get(PTY_NAME));
         }
         if(config.contains("label")){
            setLabel(config.get(PTY_LABEL));
         }
         if(config.contains("is_valid")){
            setIsValid(RBoolean.parse(config.get(PTY_IS_VALID)));
         }
         if(config.contains("note")){
            setNote(config.get(PTY_NOTE));
         }
         if(config.contains("code")){
            setCode(config.get(PTY_CODE));
         }
         if(config.contains("type_cd")){
            setTypeCd(config.get(PTY_TYPE_CD));
         }
         if(config.contains("status_cd")){
            setStatusCd(config.get(PTY_STATUS_CD));
         }
         if(config.contains("content")){
            setContent(config.get(PTY_CONTENT));
         }
         if(config.contains("create_user_code")){
            setCreateUserCode(config.get(PTY_CREATE_USER_CODE));
         }
         if(config.contains("update_user_code")){
            setUpdateUserCode(config.get(PTY_UPDATE_USER_CODE));
         }
         if(config.contains("update_date")){
            setUpdateDate(config.get(PTY_UPDATE_DATE));
         }
         if(config.contains("zh_content")){
            setZhContent(config.get(PTY_ZH_CONTENT));
         }
         if(config.contains("ja_content")){
            setJaContent(config.get(PTY_JA_CONTENT));
         }
      }else if(EXmlConfig.Simple == type){
         if(config.contains("name")){
            setName(config.get(PTY_NAME));
         }
         if(config.contains("label")){
            setLabel(config.get(PTY_LABEL));
         }
         if(config.contains("is_valid")){
            setIsValid(RBoolean.parse(config.get(PTY_IS_VALID)));
         }
         if(config.contains("note")){
            setNote(config.get(PTY_NOTE));
         }
         if(config.contains("code")){
            setCode(config.get(PTY_CODE));
         }
         if(config.contains("type_cd")){
            setTypeCd(config.get(PTY_TYPE_CD));
         }
         if(config.contains("status_cd")){
            setStatusCd(config.get(PTY_STATUS_CD));
         }
         if(config.contains("content")){
            setContent(config.get(PTY_CONTENT));
         }
         if(config.contains("create_user_code")){
            setCreateUserCode(config.get(PTY_CREATE_USER_CODE));
         }
         if(config.contains("update_user_code")){
            setUpdateUserCode(config.get(PTY_UPDATE_USER_CODE));
         }
         if(config.contains("update_date")){
            setUpdateDate(config.get(PTY_UPDATE_DATE));
         }
         if(config.contains("zh_content")){
            setZhContent(config.get(PTY_ZH_CONTENT));
         }
         if(config.contains("ja_content")){
            setJaContent(config.get(PTY_JA_CONTENT));
         }
      }else if(EXmlConfig.Value == type){
         if(config.contains("name")){
            setName(config.get(PTY_NAME));
         }
         if(config.contains("label")){
            setLabel(config.get(PTY_LABEL));
         }
         if(config.contains("is_valid")){
            setIsValid(RBoolean.parse(config.get(PTY_IS_VALID)));
         }
         if(config.contains("note")){
            setNote(config.get(PTY_NOTE));
         }
         if(config.contains("code")){
            setCode(config.get(PTY_CODE));
         }
         if(config.contains("type_cd")){
            setTypeCd(config.get(PTY_TYPE_CD));
         }
         if(config.contains("status_cd")){
            setStatusCd(config.get(PTY_STATUS_CD));
         }
         if(config.contains("content")){
            setContent(config.get(PTY_CONTENT));
         }
         if(config.contains("create_user_code")){
            setCreateUserCode(config.get(PTY_CREATE_USER_CODE));
         }
         if(config.contains("update_user_code")){
            setUpdateUserCode(config.get(PTY_UPDATE_USER_CODE));
         }
         if(config.contains("update_date")){
            setUpdateDate(config.get(PTY_UPDATE_DATE));
         }
         if(config.contains("zh_content")){
            setZhContent(config.get(PTY_ZH_CONTENT));
         }
         if(config.contains("ja_content")){
            setJaContent(config.get(PTY_JA_CONTENT));
         }
      }else if(EXmlConfig.Default == type){
         if(RString.isEmpty(getCode())){
            if(config.contains("code")){
               setCode(config.get(PTY_CODE));
            }
         }
         if(RString.isEmpty(getTypeCd())){
            if(config.contains("type_cd")){
               setTypeCd(config.get(PTY_TYPE_CD));
            }
         }
         if(RString.isEmpty(getStatusCd())){
            if(config.contains("status_cd")){
               setStatusCd(config.get(PTY_STATUS_CD));
            }
         }
         if(RString.isEmpty(getContent())){
            if(config.contains("content")){
               setContent(config.get(PTY_CONTENT));
            }
         }
         if(RString.isEmpty(getCreateUserCode())){
            if(config.contains("create_user_code")){
               setCreateUserCode(config.get(PTY_CREATE_USER_CODE));
            }
         }
         if(RString.isEmpty(getUpdateUserCode())){
            if(config.contains("update_user_code")){
               setUpdateUserCode(config.get(PTY_UPDATE_USER_CODE));
            }
         }
         if(RString.isEmpty(getUpdateDate())){
            if(config.contains("update_date")){
               setUpdateDate(config.get(PTY_UPDATE_DATE));
            }
         }
         if(RString.isEmpty(getZhContent())){
            if(config.contains("zh_content")){
               setZhContent(config.get(PTY_ZH_CONTENT));
            }
         }
         if(RString.isEmpty(getJaContent())){
            if(config.contains("ja_content")){
               setJaContent(config.get(PTY_JA_CONTENT));
            }
         }
      }
   }

   //============================================================
   // <T>保存设置信息。</T>
   //
   // @param config 设置信息
   // @param type 类型
   //============================================================
   public void saveConfig(FXmlNode config, EXmlConfig type){
      config.setName(NAME);
      super.saveConfig(config, type);
      if(EXmlConfig.Full == type){
         if(RString.isNotEmpty(getName())){
            config.set(PTY_NAME, getName());
         }
         if(RString.isNotEmpty(getLabel())){
            config.set(PTY_LABEL, getLabel());
         }
         if(RBoolean.parse(getIsValid())){
            config.set(PTY_IS_VALID, RBoolean.toString(getIsValid()));
         }
         if(RString.isNotEmpty(getNote())){
            config.set(PTY_NOTE, getNote());
         }
         if(RString.isNotEmpty(getCode())){
            config.set(PTY_CODE, getCode());
         }
         if(RString.isNotEmpty(getTypeCd())){
            config.set(PTY_TYPE_CD, getTypeCd());
         }
         if(RString.isNotEmpty(getStatusCd())){
            config.set(PTY_STATUS_CD, getStatusCd());
         }
         if(RString.isNotEmpty(getContent())){
            config.set(PTY_CONTENT, getContent());
         }
         if(RString.isNotEmpty(getCreateUserCode())){
            config.set(PTY_CREATE_USER_CODE, getCreateUserCode());
         }
         if(RString.isNotEmpty(getUpdateUserCode())){
            config.set(PTY_UPDATE_USER_CODE, getUpdateUserCode());
         }
         if(RString.isNotEmpty(getUpdateDate())){
            config.set(PTY_UPDATE_DATE, getUpdateDate());
         }
         if(RString.isNotEmpty(getZhContent())){
            config.set(PTY_ZH_CONTENT, getZhContent());
         }
         if(RString.isNotEmpty(getJaContent())){
            config.set(PTY_JA_CONTENT, getJaContent());
         }
      }else if(EXmlConfig.Simple == type){
         if(RString.isNotEmpty(getName())){
            config.set(PTY_NAME, getName());
         }
         if(RString.isNotEmpty(getLabel())){
            config.set(PTY_LABEL, getLabel());
         }
         if(RBoolean.parse(getIsValid())){
            config.set(PTY_IS_VALID, RBoolean.toString(getIsValid()));
         }
         if(RString.isNotEmpty(getNote())){
            config.set(PTY_NOTE, getNote());
         }
         if(RString.isNotEmpty(getCode())){
            config.set(PTY_CODE, getCode());
         }
         if(RString.isNotEmpty(getTypeCd())){
            config.set(PTY_TYPE_CD, getTypeCd());
         }
         if(RString.isNotEmpty(getStatusCd())){
            config.set(PTY_STATUS_CD, getStatusCd());
         }
         if(RString.isNotEmpty(getContent())){
            config.set(PTY_CONTENT, getContent());
         }
         if(RString.isNotEmpty(getCreateUserCode())){
            config.set(PTY_CREATE_USER_CODE, getCreateUserCode());
         }
         if(RString.isNotEmpty(getUpdateUserCode())){
            config.set(PTY_UPDATE_USER_CODE, getUpdateUserCode());
         }
         if(RString.isNotEmpty(getUpdateDate())){
            config.set(PTY_UPDATE_DATE, getUpdateDate());
         }
         if(RString.isNotEmpty(getZhContent())){
            config.set(PTY_ZH_CONTENT, getZhContent());
         }
         if(RString.isNotEmpty(getJaContent())){
            config.set(PTY_JA_CONTENT, getJaContent());
         }
      }else if(EXmlConfig.Value == type){
         String sName = getName();
         if(RString.isNotEmpty(sName)){
            config.set(PTY_NAME, sName);
         }
         String sLabel = getLabel();
         if(RString.isNotEmpty(sLabel)){
            config.set(PTY_LABEL, sLabel);
         }
         Boolean bIsValid = getIsValid();
         if(RBoolean.parse(bIsValid)){
            config.set(PTY_IS_VALID, RBoolean.toString(bIsValid));
         }
         String sNote = getNote();
         if(RString.isNotEmpty(sNote)){
            config.set(PTY_NOTE, sNote);
         }
         String sCode = getCode();
         if(RString.isNotEmpty(sCode)){
            config.set(PTY_CODE, sCode);
         }
         String sTypeCd = getTypeCd();
         if(RString.isNotEmpty(sTypeCd)){
            config.set(PTY_TYPE_CD, sTypeCd);
         }
         String sStatusCd = getStatusCd();
         if(RString.isNotEmpty(sStatusCd)){
            config.set(PTY_STATUS_CD, sStatusCd);
         }
         String sContent = getContent();
         if(RString.isNotEmpty(sContent)){
            config.set(PTY_CONTENT, sContent);
         }
         String sCreateUserCode = getCreateUserCode();
         if(RString.isNotEmpty(sCreateUserCode)){
            config.set(PTY_CREATE_USER_CODE, sCreateUserCode);
         }
         String sUpdateUserCode = getUpdateUserCode();
         if(RString.isNotEmpty(sUpdateUserCode)){
            config.set(PTY_UPDATE_USER_CODE, sUpdateUserCode);
         }
         String sUpdateDate = getUpdateDate();
         if(RString.isNotEmpty(sUpdateDate)){
            config.set(PTY_UPDATE_DATE, sUpdateDate);
         }
         String sZhContent = getZhContent();
         if(RString.isNotEmpty(sZhContent)){
            config.set(PTY_ZH_CONTENT, sZhContent);
         }
         String sJaContent = getJaContent();
         if(RString.isNotEmpty(sJaContent)){
            config.set(PTY_JA_CONTENT, sJaContent);
         }
      }else if(EXmlConfig.Default == type){
         String sCode = getCode();
         if(RString.isNotEmpty(sCode)){
            config.set(PTY_CODE, sCode);
         }
         String sTypeCd = getTypeCd();
         if(RString.isNotEmpty(sTypeCd)){
            config.set(PTY_TYPE_CD, sTypeCd);
         }
         String sStatusCd = getStatusCd();
         if(RString.isNotEmpty(sStatusCd)){
            config.set(PTY_STATUS_CD, sStatusCd);
         }
         String sContent = getContent();
         if(RString.isNotEmpty(sContent)){
            config.set(PTY_CONTENT, sContent);
         }
         String sCreateUserCode = getCreateUserCode();
         if(RString.isNotEmpty(sCreateUserCode)){
            config.set(PTY_CREATE_USER_CODE, sCreateUserCode);
         }
         String sUpdateUserCode = getUpdateUserCode();
         if(RString.isNotEmpty(sUpdateUserCode)){
            config.set(PTY_UPDATE_USER_CODE, sUpdateUserCode);
         }
         String sUpdateDate = getUpdateDate();
         if(RString.isNotEmpty(sUpdateDate)){
            config.set(PTY_UPDATE_DATE, sUpdateDate);
         }
         String sZhContent = getZhContent();
         if(RString.isNotEmpty(sZhContent)){
            config.set(PTY_ZH_CONTENT, sZhContent);
         }
         String sJaContent = getJaContent();
         if(RString.isNotEmpty(sJaContent)){
            config.set(PTY_JA_CONTENT, sJaContent);
         }
      }
   }
}