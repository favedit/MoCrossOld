package com.zqinet.logic.data.platform;

import org.mo.com.lang.*;
import org.mo.com.collections.*;
import org.mo.com.lang.type.*;
import org.mo.data.logic.*;

//============================================================
// <T>存储雕像逻辑单元。</T>
//============================================================
public class FPlatformWorldStatusUnit extends FLogicUnit
{
   // 存储字段对象标识的定义。
   private long __ouid;

   // 字段对象标识的定义。
   protected long _ouid;

   // 存储字段有效性的定义。
   private boolean __ovld;

   // 字段有效性的定义。
   protected boolean _ovld;

   // 存储字段唯一标识的定义。
   private int __uniqueId;

   // 字段唯一标识的定义。
   protected int _uniqueId;

   // 存储字段雕像ID的定义。
   private long __statueId;

   // 字段雕像ID的定义。
   protected long _statueId;

   // 存储字段雕像类型的定义。
   private int __statueType;

   // 字段雕像类型的定义。
   protected int _statueType;

   // 存储字段角色ID的定义。
   private long __roleId;

   // 字段角色ID的定义。
   protected long _roleId;

   // 存储字段膜拜次数的定义。
   private int __worshipCount;

   // 字段膜拜次数的定义。
   protected int _worshipCount;

   // 存储字段鄙视次数的定义。
   private int __bsCount;

   // 字段鄙视次数的定义。
   protected int _bsCount;

   // 存储字段名称的定义。
   private String __label;

   // 字段名称的定义。
   protected String _label;

   // 存储字段样式动作集合的定义。
   private String __styleActionSet;

   // 字段样式动作集合的定义。
   protected String _styleActionSet;

   // 存储字段当前样式的定义。
   private String __curStyleactionSet;

   // 字段当前样式的定义。
   protected String _curStyleactionSet;

   // 存储字段当前角色编号的定义。
   private long __curRoleId;

   // 字段当前角色编号的定义。
   protected long _curRoleId;

   // 存储字段当然LABEL的定义。
   private String __curLabel;

   // 字段当然LABEL的定义。
   protected String _curLabel;

   // 存储字段限制值的定义。
   private int __limitValue;

   // 字段限制值的定义。
   protected int _limitValue;

   // 存储字段等级的定义。
   private int __level;

   // 字段等级的定义。
   protected int _level;

   // 存储字段当前等级的定义。
   private int __curLevel;

   // 字段当前等级的定义。
   protected int _curLevel;

   // 存储字段门派模板编号的定义。
   private int __metierTid;

   // 字段门派模板编号的定义。
   protected int _metierTid;

   // 存储字段当前门派编号的定义。
   private int __curMetierTid;

   // 字段当前门派编号的定义。
   protected int _curMetierTid;

   // 存储字段VIP等级的定义。
   private int __vipLevel;

   // 字段VIP等级的定义。
   protected int _vipLevel;

   // 存储字段当前VIP等级的定义。
   private int __curVipLevel;

   // 字段当前VIP等级的定义。
   protected int _curVipLevel;

   // 存储字段额外VIP信息的定义。
   private int __exVipInfo;

   // 字段额外VIP信息的定义。
   protected int _exVipInfo;

   // 存储字段当前额外vip信息的定义。
   private int __curExVipInfo;

   // 字段当前额外vip信息的定义。
   protected int _curExVipInfo;

   // 存储字段额外VIP等级的定义。
   private int __exVipLevel;

   // 字段额外VIP等级的定义。
   protected int _exVipLevel;

   // 存储字段当前额外VIP等级的定义。
   private int __curExVipLevel;

   // 字段当前额外VIP等级的定义。
   protected int _curExVipLevel;

   // 存储字段装备列表的定义。
   private String __equipList;

   // 字段装备列表的定义。
   protected String _equipList;

   // 存储字段当前装备列表的定义。
   private String __curEquipList;

   // 字段当前装备列表的定义。
   protected String _curEquipList;

   // 存储字段创建用户标识的定义。
   private int __createUserId;

   // 字段创建用户标识的定义。
   protected int _createUserId;

   // 存储字段创建日期的定义。
   private TDateTime __createDate = new TDateTime();

   // 字段创建日期的定义。
   protected TDateTime _createDate = new TDateTime();

   // 存储字段更新者标识的定义。
   private int __updateUserId;

   // 字段更新者标识的定义。
   protected int _updateUserId;

   // 存储字段更新时间的定义。
   private TDateTime __updateDate = new TDateTime();

   // 字段更新时间的定义。
   protected TDateTime _updateDate = new TDateTime();

   //============================================================
   // <T>构造存储雕像逻辑单元。</T>
   //============================================================
   public FPlatformWorldStatusUnit(){
   }

   //============================================================
   // <T>判断对象标识的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isOuidChanged(){
      return __ouid != _ouid;
   }

   //============================================================
   // <T>获得对象标识的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public long ouid(){
      return _ouid;
   }

   //============================================================
   // <T>设置对象标识的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setOuid(long value){
      _ouid = value;
   }

   //============================================================
   // <T>判断有效性的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isOvldChanged(){
      return __ovld != _ovld;
   }

   //============================================================
   // <T>获得有效性的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean ovld(){
      return _ovld;
   }

   //============================================================
   // <T>设置有效性的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setOvld(boolean value){
      _ovld = value;
   }

   //============================================================
   // <T>判断唯一标识的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isUniqueIdChanged(){
      return __uniqueId != _uniqueId;
   }

   //============================================================
   // <T>获得唯一标识的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int uniqueId(){
      return _uniqueId;
   }

   //============================================================
   // <T>设置唯一标识的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setUniqueId(int value){
      _uniqueId = value;
   }

   //============================================================
   // <T>判断雕像ID的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isStatueIdChanged(){
      return __statueId != _statueId;
   }

   //============================================================
   // <T>获得雕像ID的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public long statueId(){
      return _statueId;
   }

   //============================================================
   // <T>设置雕像ID的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setStatueId(long value){
      _statueId = value;
   }

   //============================================================
   // <T>判断雕像类型的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isStatueTypeChanged(){
      return __statueType != _statueType;
   }

   //============================================================
   // <T>获得雕像类型的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int statueType(){
      return _statueType;
   }

   //============================================================
   // <T>设置雕像类型的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setStatueType(int value){
      _statueType = value;
   }

   //============================================================
   // <T>判断角色ID的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isRoleIdChanged(){
      return __roleId != _roleId;
   }

   //============================================================
   // <T>获得角色ID的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public long roleId(){
      return _roleId;
   }

   //============================================================
   // <T>设置角色ID的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setRoleId(long value){
      _roleId = value;
   }

   //============================================================
   // <T>判断膜拜次数的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isWorshipCountChanged(){
      return __worshipCount != _worshipCount;
   }

   //============================================================
   // <T>获得膜拜次数的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int worshipCount(){
      return _worshipCount;
   }

   //============================================================
   // <T>设置膜拜次数的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setWorshipCount(int value){
      _worshipCount = value;
   }

   //============================================================
   // <T>判断鄙视次数的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isBsCountChanged(){
      return __bsCount != _bsCount;
   }

   //============================================================
   // <T>获得鄙视次数的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int bsCount(){
      return _bsCount;
   }

   //============================================================
   // <T>设置鄙视次数的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setBsCount(int value){
      _bsCount = value;
   }

   //============================================================
   // <T>判断名称的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isLabelChanged(){
      return !RString.equals(__label, _label);
   }

   //============================================================
   // <T>获得名称的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public String label(){
      return _label;
   }

   //============================================================
   // <T>设置名称的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setLabel(String value){
      _label = value;
   }

   //============================================================
   // <T>判断样式动作集合的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isStyleActionSetChanged(){
      return !RString.equals(__styleActionSet, _styleActionSet);
   }

   //============================================================
   // <T>获得样式动作集合的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public String styleActionSet(){
      return _styleActionSet;
   }

   //============================================================
   // <T>设置样式动作集合的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setStyleActionSet(String value){
      _styleActionSet = value;
   }

   //============================================================
   // <T>判断当前样式的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isCurStyleactionSetChanged(){
      return !RString.equals(__curStyleactionSet, _curStyleactionSet);
   }

   //============================================================
   // <T>获得当前样式的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public String curStyleactionSet(){
      return _curStyleactionSet;
   }

   //============================================================
   // <T>设置当前样式的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setCurStyleactionSet(String value){
      _curStyleactionSet = value;
   }

   //============================================================
   // <T>判断当前角色编号的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isCurRoleIdChanged(){
      return __curRoleId != _curRoleId;
   }

   //============================================================
   // <T>获得当前角色编号的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public long curRoleId(){
      return _curRoleId;
   }

   //============================================================
   // <T>设置当前角色编号的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setCurRoleId(long value){
      _curRoleId = value;
   }

   //============================================================
   // <T>判断当然LABEL的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isCurLabelChanged(){
      return !RString.equals(__curLabel, _curLabel);
   }

   //============================================================
   // <T>获得当然LABEL的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public String curLabel(){
      return _curLabel;
   }

   //============================================================
   // <T>设置当然LABEL的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setCurLabel(String value){
      _curLabel = value;
   }

   //============================================================
   // <T>判断限制值的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isLimitValueChanged(){
      return __limitValue != _limitValue;
   }

   //============================================================
   // <T>获得限制值的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int limitValue(){
      return _limitValue;
   }

   //============================================================
   // <T>设置限制值的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setLimitValue(int value){
      _limitValue = value;
   }

   //============================================================
   // <T>判断等级的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isLevelChanged(){
      return __level != _level;
   }

   //============================================================
   // <T>获得等级的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int level(){
      return _level;
   }

   //============================================================
   // <T>设置等级的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setLevel(int value){
      _level = value;
   }

   //============================================================
   // <T>判断当前等级的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isCurLevelChanged(){
      return __curLevel != _curLevel;
   }

   //============================================================
   // <T>获得当前等级的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int curLevel(){
      return _curLevel;
   }

   //============================================================
   // <T>设置当前等级的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setCurLevel(int value){
      _curLevel = value;
   }

   //============================================================
   // <T>判断门派模板编号的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isMetierTidChanged(){
      return __metierTid != _metierTid;
   }

   //============================================================
   // <T>获得门派模板编号的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int metierTid(){
      return _metierTid;
   }

   //============================================================
   // <T>设置门派模板编号的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setMetierTid(int value){
      _metierTid = value;
   }

   //============================================================
   // <T>判断当前门派编号的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isCurMetierTidChanged(){
      return __curMetierTid != _curMetierTid;
   }

   //============================================================
   // <T>获得当前门派编号的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int curMetierTid(){
      return _curMetierTid;
   }

   //============================================================
   // <T>设置当前门派编号的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setCurMetierTid(int value){
      _curMetierTid = value;
   }

   //============================================================
   // <T>判断VIP等级的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isVipLevelChanged(){
      return __vipLevel != _vipLevel;
   }

   //============================================================
   // <T>获得VIP等级的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int vipLevel(){
      return _vipLevel;
   }

   //============================================================
   // <T>设置VIP等级的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setVipLevel(int value){
      _vipLevel = value;
   }

   //============================================================
   // <T>判断当前VIP等级的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isCurVipLevelChanged(){
      return __curVipLevel != _curVipLevel;
   }

   //============================================================
   // <T>获得当前VIP等级的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int curVipLevel(){
      return _curVipLevel;
   }

   //============================================================
   // <T>设置当前VIP等级的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setCurVipLevel(int value){
      _curVipLevel = value;
   }

   //============================================================
   // <T>判断额外VIP信息的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isExVipInfoChanged(){
      return __exVipInfo != _exVipInfo;
   }

   //============================================================
   // <T>获得额外VIP信息的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int exVipInfo(){
      return _exVipInfo;
   }

   //============================================================
   // <T>设置额外VIP信息的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setExVipInfo(int value){
      _exVipInfo = value;
   }

   //============================================================
   // <T>判断当前额外vip信息的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isCurExVipInfoChanged(){
      return __curExVipInfo != _curExVipInfo;
   }

   //============================================================
   // <T>获得当前额外vip信息的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int curExVipInfo(){
      return _curExVipInfo;
   }

   //============================================================
   // <T>设置当前额外vip信息的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setCurExVipInfo(int value){
      _curExVipInfo = value;
   }

   //============================================================
   // <T>判断额外VIP等级的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isExVipLevelChanged(){
      return __exVipLevel != _exVipLevel;
   }

   //============================================================
   // <T>获得额外VIP等级的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int exVipLevel(){
      return _exVipLevel;
   }

   //============================================================
   // <T>设置额外VIP等级的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setExVipLevel(int value){
      _exVipLevel = value;
   }

   //============================================================
   // <T>判断当前额外VIP等级的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isCurExVipLevelChanged(){
      return __curExVipLevel != _curExVipLevel;
   }

   //============================================================
   // <T>获得当前额外VIP等级的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int curExVipLevel(){
      return _curExVipLevel;
   }

   //============================================================
   // <T>设置当前额外VIP等级的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setCurExVipLevel(int value){
      _curExVipLevel = value;
   }

   //============================================================
   // <T>判断装备列表的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isEquipListChanged(){
      return !RString.equals(__equipList, _equipList);
   }

   //============================================================
   // <T>获得装备列表的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public String equipList(){
      return _equipList;
   }

   //============================================================
   // <T>设置装备列表的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setEquipList(String value){
      _equipList = value;
   }

   //============================================================
   // <T>判断当前装备列表的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isCurEquipListChanged(){
      return !RString.equals(__curEquipList, _curEquipList);
   }

   //============================================================
   // <T>获得当前装备列表的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public String curEquipList(){
      return _curEquipList;
   }

   //============================================================
   // <T>设置当前装备列表的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setCurEquipList(String value){
      _curEquipList = value;
   }

   //============================================================
   // <T>判断创建用户标识的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isCreateUserIdChanged(){
      return __createUserId != _createUserId;
   }

   //============================================================
   // <T>获得创建用户标识的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int createUserId(){
      return _createUserId;
   }

   //============================================================
   // <T>设置创建用户标识的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setCreateUserId(int value){
      _createUserId = value;
   }

   //============================================================
   // <T>判断创建日期的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isCreateDateChanged(){
      return !__createDate.equals(_createDate);
   }

   //============================================================
   // <T>获得创建日期的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public TDateTime createDate(){
      return _createDate;
   }

   //============================================================
   // <T>设置创建日期的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setCreateDate(TDateTime value){
      _createDate = value;
   }

   //============================================================
   // <T>判断更新者标识的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isUpdateUserIdChanged(){
      return __updateUserId != _updateUserId;
   }

   //============================================================
   // <T>获得更新者标识的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int updateUserId(){
      return _updateUserId;
   }

   //============================================================
   // <T>设置更新者标识的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setUpdateUserId(int value){
      _updateUserId = value;
   }

   //============================================================
   // <T>判断更新时间的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isUpdateDateChanged(){
      return !__updateDate.equals(_updateDate);
   }

   //============================================================
   // <T>获得更新时间的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public TDateTime updateDate(){
      return _updateDate;
   }

   //============================================================
   // <T>设置更新时间的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setUpdateDate(TDateTime value){
      _updateDate = value;
   }

   //============================================================
   // <T>加载行记录。</T>
   //
   // @param row 行记录
   //============================================================
   public void load(FRow row){
      __ouid = RLong.parse(row.get("ouid"));
      _ouid = __ouid;
      __uniqueId = row.getInteger("unique_id");
      _uniqueId = __uniqueId;
      __statueId = RLong.parse(row.get("statue_id"));
      _statueId = __statueId;
      __statueType = row.getInteger("statue_type");
      _statueType = __statueType;
      __roleId = RLong.parse(row.get("role_id"));
      _roleId = __roleId;
      __worshipCount = row.getInteger("worship_count");
      _worshipCount = __worshipCount;
      __bsCount = row.getInteger("bs_count");
      _bsCount = __bsCount;
      __label = row.get("label");
      _label = __label;
      __styleActionSet = row.get("style_action_set");
      _styleActionSet = __styleActionSet;
      __curStyleactionSet = row.get("cur_styleaction_set");
      _curStyleactionSet = __curStyleactionSet;
      __curRoleId = RLong.parse(row.get("cur_role_id"));
      _curRoleId = __curRoleId;
      __curLabel = row.get("cur_label");
      _curLabel = __curLabel;
      __limitValue = row.getInteger("limit_value");
      _limitValue = __limitValue;
      __level = row.getInteger("level");
      _level = __level;
      __curLevel = row.getInteger("cur_level");
      _curLevel = __curLevel;
      __metierTid = row.getInteger("metier_tid");
      _metierTid = __metierTid;
      __curMetierTid = row.getInteger("cur_metier_tid");
      _curMetierTid = __curMetierTid;
      __vipLevel = row.getInteger("vip_level");
      _vipLevel = __vipLevel;
      __curVipLevel = row.getInteger("cur_vip_level");
      _curVipLevel = __curVipLevel;
      __exVipInfo = row.getInteger("ex_vip_info");
      _exVipInfo = __exVipInfo;
      __curExVipInfo = row.getInteger("cur_ex_vip_info");
      _curExVipInfo = __curExVipInfo;
      __exVipLevel = row.getInteger("ex_vip_level");
      _exVipLevel = __exVipLevel;
      __curExVipLevel = row.getInteger("cur_ex_vip_level");
      _curExVipLevel = __curExVipLevel;
      __equipList = row.get("equip_list");
      _equipList = __equipList;
      __curEquipList = row.get("cur_equip_list");
      _curEquipList = __curEquipList;
      __createUserId = row.getInteger("create_user_id");
      _createUserId = __createUserId;
      __createDate.parse(row.get("create_date"));
      _createDate.assign(__createDate);
      __updateUserId = row.getInteger("update_user_id");
      _updateUserId = __updateUserId;
      __updateDate.parse(row.get("update_date"));
      _updateDate.assign(__updateDate);
   }
}