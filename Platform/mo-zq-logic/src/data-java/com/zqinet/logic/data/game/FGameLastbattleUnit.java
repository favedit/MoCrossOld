package com.zqinet.logic.data.game;

import org.mo.com.lang.*;
import org.mo.com.collections.*;
import org.mo.com.lang.type.*;
import org.mo.data.logic.*;

//============================================================
// <T>记录上场战斗的详细信息逻辑单元。</T>
//============================================================
public class FGameLastbattleUnit extends FLogicUnit
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

   // 存储字段战场id的定义。
   private int __battleId;

   // 字段战场id的定义。
   protected int _battleId;

   // 存储字段角色ID的定义。
   private long __roleId;

   // 字段角色ID的定义。
   protected long _roleId;

   // 存储字段用户名的定义。
   private String __label;

   // 字段用户名的定义。
   protected String _label;

   // 存储字段等级的定义。
   private int __level;

   // 字段等级的定义。
   protected int _level;

   // 存储字段最大连杀数的定义。
   private int __maxCombokill;

   // 字段最大连杀数的定义。
   protected int _maxCombokill;

   // 存储字段阵营的定义。
   private int __side;

   // 字段阵营的定义。
   protected int _side;

   // 存储字段累计分数的定义。
   private int __totalPoint;

   // 字段累计分数的定义。
   protected int _totalPoint;

   // 存储字段获得的竞技场点数的定义。
   private int __point;

   // 字段获得的竞技场点数的定义。
   protected int _point;

   // 存储字段总共击杀的个数的定义。
   private int __totalKill;

   // 字段总共击杀的个数的定义。
   protected int _totalKill;

   // 存储字段战场结果的定义。
   private int __battleResult;

   // 字段战场结果的定义。
   protected int _battleResult;

   // 存储字段是否是帮会记录的定义。
   private int __isSociety;

   // 字段是否是帮会记录的定义。
   protected int _isSociety;

   // 存储字段帮会ID的定义。
   private long __societyId;

   // 字段帮会ID的定义。
   protected long _societyId;

   // 存储字段所在战区的定义。
   private int __battleSection;

   // 字段所在战区的定义。
   protected int _battleSection;

   // 存储字段所在等级组的定义。
   private int __levelgroup;

   // 字段所在等级组的定义。
   protected int _levelgroup;

   // 存储字段个人帮贡的定义。
   private int __banggong;

   // 字段个人帮贡的定义。
   protected int _banggong;

   // 存储字段帮会资金 的定义。
   private int __money;

   // 字段帮会资金 的定义。
   protected int _money;

   // 存储字段繁荣度的定义。
   private int __fanrong;

   // 字段繁荣度的定义。
   protected int _fanrong;

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
   // <T>构造记录上场战斗的详细信息逻辑单元。</T>
   //============================================================
   public FGameLastbattleUnit(){
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
   // <T>判断战场id的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isBattleIdChanged(){
      return __battleId != _battleId;
   }

   //============================================================
   // <T>获得战场id的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int battleId(){
      return _battleId;
   }

   //============================================================
   // <T>设置战场id的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setBattleId(int value){
      _battleId = value;
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
   // <T>判断用户名的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isLabelChanged(){
      return !RString.equals(__label, _label);
   }

   //============================================================
   // <T>获得用户名的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public String label(){
      return _label;
   }

   //============================================================
   // <T>设置用户名的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setLabel(String value){
      _label = value;
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
   // <T>判断最大连杀数的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isMaxCombokillChanged(){
      return __maxCombokill != _maxCombokill;
   }

   //============================================================
   // <T>获得最大连杀数的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int maxCombokill(){
      return _maxCombokill;
   }

   //============================================================
   // <T>设置最大连杀数的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setMaxCombokill(int value){
      _maxCombokill = value;
   }

   //============================================================
   // <T>判断阵营的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isSideChanged(){
      return __side != _side;
   }

   //============================================================
   // <T>获得阵营的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int side(){
      return _side;
   }

   //============================================================
   // <T>设置阵营的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setSide(int value){
      _side = value;
   }

   //============================================================
   // <T>判断累计分数的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isTotalPointChanged(){
      return __totalPoint != _totalPoint;
   }

   //============================================================
   // <T>获得累计分数的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int totalPoint(){
      return _totalPoint;
   }

   //============================================================
   // <T>设置累计分数的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setTotalPoint(int value){
      _totalPoint = value;
   }

   //============================================================
   // <T>判断获得的竞技场点数的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isPointChanged(){
      return __point != _point;
   }

   //============================================================
   // <T>获得获得的竞技场点数的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int point(){
      return _point;
   }

   //============================================================
   // <T>设置获得的竞技场点数的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setPoint(int value){
      _point = value;
   }

   //============================================================
   // <T>判断总共击杀的个数的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isTotalKillChanged(){
      return __totalKill != _totalKill;
   }

   //============================================================
   // <T>获得总共击杀的个数的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int totalKill(){
      return _totalKill;
   }

   //============================================================
   // <T>设置总共击杀的个数的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setTotalKill(int value){
      _totalKill = value;
   }

   //============================================================
   // <T>判断战场结果的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isBattleResultChanged(){
      return __battleResult != _battleResult;
   }

   //============================================================
   // <T>获得战场结果的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int battleResult(){
      return _battleResult;
   }

   //============================================================
   // <T>设置战场结果的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setBattleResult(int value){
      _battleResult = value;
   }

   //============================================================
   // <T>判断是否是帮会记录的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isIsSocietyChanged(){
      return __isSociety != _isSociety;
   }

   //============================================================
   // <T>获得是否是帮会记录的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int isSociety(){
      return _isSociety;
   }

   //============================================================
   // <T>设置是否是帮会记录的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setIsSociety(int value){
      _isSociety = value;
   }

   //============================================================
   // <T>判断帮会ID的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isSocietyIdChanged(){
      return __societyId != _societyId;
   }

   //============================================================
   // <T>获得帮会ID的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public long societyId(){
      return _societyId;
   }

   //============================================================
   // <T>设置帮会ID的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setSocietyId(long value){
      _societyId = value;
   }

   //============================================================
   // <T>判断所在战区的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isBattleSectionChanged(){
      return __battleSection != _battleSection;
   }

   //============================================================
   // <T>获得所在战区的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int battleSection(){
      return _battleSection;
   }

   //============================================================
   // <T>设置所在战区的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setBattleSection(int value){
      _battleSection = value;
   }

   //============================================================
   // <T>判断所在等级组的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isLevelgroupChanged(){
      return __levelgroup != _levelgroup;
   }

   //============================================================
   // <T>获得所在等级组的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int levelgroup(){
      return _levelgroup;
   }

   //============================================================
   // <T>设置所在等级组的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setLevelgroup(int value){
      _levelgroup = value;
   }

   //============================================================
   // <T>判断个人帮贡的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isBanggongChanged(){
      return __banggong != _banggong;
   }

   //============================================================
   // <T>获得个人帮贡的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int banggong(){
      return _banggong;
   }

   //============================================================
   // <T>设置个人帮贡的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setBanggong(int value){
      _banggong = value;
   }

   //============================================================
   // <T>判断帮会资金 的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isMoneyChanged(){
      return __money != _money;
   }

   //============================================================
   // <T>获得帮会资金 的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int money(){
      return _money;
   }

   //============================================================
   // <T>设置帮会资金 的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setMoney(int value){
      _money = value;
   }

   //============================================================
   // <T>判断繁荣度的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isFanrongChanged(){
      return __fanrong != _fanrong;
   }

   //============================================================
   // <T>获得繁荣度的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int fanrong(){
      return _fanrong;
   }

   //============================================================
   // <T>设置繁荣度的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setFanrong(int value){
      _fanrong = value;
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
      __battleId = row.getInteger("battle_id");
      _battleId = __battleId;
      __roleId = RLong.parse(row.get("role_id"));
      _roleId = __roleId;
      __label = row.get("label");
      _label = __label;
      __level = row.getInteger("level");
      _level = __level;
      __maxCombokill = row.getInteger("max_combokill");
      _maxCombokill = __maxCombokill;
      __side = row.getInteger("side");
      _side = __side;
      __totalPoint = row.getInteger("total_point");
      _totalPoint = __totalPoint;
      __point = row.getInteger("point");
      _point = __point;
      __totalKill = row.getInteger("total_kill");
      _totalKill = __totalKill;
      __battleResult = row.getInteger("battle_result");
      _battleResult = __battleResult;
      __isSociety = row.getInteger("is_society");
      _isSociety = __isSociety;
      __societyId = RLong.parse(row.get("society_id"));
      _societyId = __societyId;
      __battleSection = row.getInteger("battle_section");
      _battleSection = __battleSection;
      __levelgroup = row.getInteger("levelgroup");
      _levelgroup = __levelgroup;
      __banggong = row.getInteger("banggong");
      _banggong = __banggong;
      __money = row.getInteger("money");
      _money = __money;
      __fanrong = row.getInteger("fanrong");
      _fanrong = __fanrong;
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