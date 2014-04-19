package com.zqinet.logic.data.game;

import org.mo.com.lang.*;
import org.mo.com.collections.*;
import org.mo.com.lang.type.*;
import org.mo.data.logic.*;

//============================================================
// <T>社会系统逻辑单元。</T>
//============================================================
public class FGameSocietyUnit extends FLogicUnit
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

   // 存储字段标签的定义。
   private String __label;

   // 字段标签的定义。
   protected String _label;

   // 存储字段级别的定义。
   private int __level;

   // 字段级别的定义。
   protected int _level;

   // 存储字段帮会公告的定义。
   private String __placard;

   // 字段帮会公告的定义。
   protected String _placard;

   // 存储字段公会宣言的定义。
   private String __manifesto;

   // 字段公会宣言的定义。
   protected String _manifesto;

   // 存储字段帮会人数的定义。
   private String __memberCount;

   // 字段帮会人数的定义。
   protected String _memberCount;

   // 存储字段帮会申请人数的定义。
   private String __applyMemberCount;

   // 字段帮会申请人数的定义。
   protected String _applyMemberCount;

   // 存储字段帮主的定义。
   private long __firstLeader;

   // 字段帮主的定义。
   protected long _firstLeader;

   // 存储字段创建人的编号的定义。
   private long __createRoleId;

   // 字段创建人的编号的定义。
   protected long _createRoleId;

   // 存储字段帮会繁荣度的定义。
   private int __prosperityDegree;

   // 字段帮会繁荣度的定义。
   protected int _prosperityDegree;

   // 存储字段帮会资金的定义。
   private int __capital;

   // 字段帮会资金的定义。
   protected int _capital;

   // 存储字段帮徽等级的定义。
   private int __badgeLevel;

   // 字段帮徽等级的定义。
   protected int _badgeLevel;

   // 存储字段创建世间的定义。
   private TDateTime __createTime = new TDateTime();

   // 字段创建世间的定义。
   protected TDateTime _createTime = new TDateTime();

   // 存储字段帮会申请列表的定义。
   private String __applyMember;

   // 字段帮会申请列表的定义。
   protected String _applyMember;

   // 存储字段帮战总积分的定义。
   private int __totalSocietywarPoint;

   // 字段帮战总积分的定义。
   protected int _totalSocietywarPoint;

   // 存储字段日常数据的定义。
   private String __dailyData;

   // 字段日常数据的定义。
   protected String _dailyData;

   // 存储字段周常数据的定义。
   private String __weeklyData;

   // 字段周常数据的定义。
   protected String _weeklyData;

   // 存储字段帮会财主活动记录的定义。
   private String __richmanRecord;

   // 字段帮会财主活动记录的定义。
   protected String _richmanRecord;

   // 存储字段帮会技能列表的定义。
   private String __societySkillList;

   // 字段帮会技能列表的定义。
   protected String _societySkillList;

   // 存储字段新手帮会标志的定义。
   private int __isNewerSociety;

   // 字段新手帮会标志的定义。
   protected int _isNewerSociety;

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
   // <T>构造社会系统逻辑单元。</T>
   //============================================================
   public FGameSocietyUnit(){
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
   // <T>判断标签的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isLabelChanged(){
      return !RString.equals(__label, _label);
   }

   //============================================================
   // <T>获得标签的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public String label(){
      return _label;
   }

   //============================================================
   // <T>设置标签的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setLabel(String value){
      _label = value;
   }

   //============================================================
   // <T>判断级别的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isLevelChanged(){
      return __level != _level;
   }

   //============================================================
   // <T>获得级别的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int level(){
      return _level;
   }

   //============================================================
   // <T>设置级别的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setLevel(int value){
      _level = value;
   }

   //============================================================
   // <T>判断帮会公告的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isPlacardChanged(){
      return !RString.equals(__placard, _placard);
   }

   //============================================================
   // <T>获得帮会公告的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public String placard(){
      return _placard;
   }

   //============================================================
   // <T>设置帮会公告的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setPlacard(String value){
      _placard = value;
   }

   //============================================================
   // <T>判断公会宣言的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isManifestoChanged(){
      return !RString.equals(__manifesto, _manifesto);
   }

   //============================================================
   // <T>获得公会宣言的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public String manifesto(){
      return _manifesto;
   }

   //============================================================
   // <T>设置公会宣言的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setManifesto(String value){
      _manifesto = value;
   }

   //============================================================
   // <T>判断帮会人数的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isMemberCountChanged(){
      return !RString.equals(__memberCount, _memberCount);
   }

   //============================================================
   // <T>获得帮会人数的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public String memberCount(){
      return _memberCount;
   }

   //============================================================
   // <T>设置帮会人数的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setMemberCount(String value){
      _memberCount = value;
   }

   //============================================================
   // <T>判断帮会申请人数的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isApplyMemberCountChanged(){
      return !RString.equals(__applyMemberCount, _applyMemberCount);
   }

   //============================================================
   // <T>获得帮会申请人数的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public String applyMemberCount(){
      return _applyMemberCount;
   }

   //============================================================
   // <T>设置帮会申请人数的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setApplyMemberCount(String value){
      _applyMemberCount = value;
   }

   //============================================================
   // <T>判断帮主的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isFirstLeaderChanged(){
      return __firstLeader != _firstLeader;
   }

   //============================================================
   // <T>获得帮主的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public long firstLeader(){
      return _firstLeader;
   }

   //============================================================
   // <T>设置帮主的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setFirstLeader(long value){
      _firstLeader = value;
   }

   //============================================================
   // <T>判断创建人的编号的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isCreateRoleIdChanged(){
      return __createRoleId != _createRoleId;
   }

   //============================================================
   // <T>获得创建人的编号的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public long createRoleId(){
      return _createRoleId;
   }

   //============================================================
   // <T>设置创建人的编号的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setCreateRoleId(long value){
      _createRoleId = value;
   }

   //============================================================
   // <T>判断帮会繁荣度的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isProsperityDegreeChanged(){
      return __prosperityDegree != _prosperityDegree;
   }

   //============================================================
   // <T>获得帮会繁荣度的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int prosperityDegree(){
      return _prosperityDegree;
   }

   //============================================================
   // <T>设置帮会繁荣度的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setProsperityDegree(int value){
      _prosperityDegree = value;
   }

   //============================================================
   // <T>判断帮会资金的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isCapitalChanged(){
      return __capital != _capital;
   }

   //============================================================
   // <T>获得帮会资金的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int capital(){
      return _capital;
   }

   //============================================================
   // <T>设置帮会资金的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setCapital(int value){
      _capital = value;
   }

   //============================================================
   // <T>判断帮徽等级的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isBadgeLevelChanged(){
      return __badgeLevel != _badgeLevel;
   }

   //============================================================
   // <T>获得帮徽等级的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int badgeLevel(){
      return _badgeLevel;
   }

   //============================================================
   // <T>设置帮徽等级的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setBadgeLevel(int value){
      _badgeLevel = value;
   }

   //============================================================
   // <T>判断创建世间的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isCreateTimeChanged(){
      return !__createTime.equals(_createTime);
   }

   //============================================================
   // <T>获得创建世间的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public TDateTime createTime(){
      return _createTime;
   }

   //============================================================
   // <T>设置创建世间的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setCreateTime(TDateTime value){
      _createTime = value;
   }

   //============================================================
   // <T>判断帮会申请列表的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isApplyMemberChanged(){
      return !RString.equals(__applyMember, _applyMember);
   }

   //============================================================
   // <T>获得帮会申请列表的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public String applyMember(){
      return _applyMember;
   }

   //============================================================
   // <T>设置帮会申请列表的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setApplyMember(String value){
      _applyMember = value;
   }

   //============================================================
   // <T>判断帮战总积分的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isTotalSocietywarPointChanged(){
      return __totalSocietywarPoint != _totalSocietywarPoint;
   }

   //============================================================
   // <T>获得帮战总积分的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int totalSocietywarPoint(){
      return _totalSocietywarPoint;
   }

   //============================================================
   // <T>设置帮战总积分的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setTotalSocietywarPoint(int value){
      _totalSocietywarPoint = value;
   }

   //============================================================
   // <T>判断日常数据的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isDailyDataChanged(){
      return !RString.equals(__dailyData, _dailyData);
   }

   //============================================================
   // <T>获得日常数据的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public String dailyData(){
      return _dailyData;
   }

   //============================================================
   // <T>设置日常数据的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setDailyData(String value){
      _dailyData = value;
   }

   //============================================================
   // <T>判断周常数据的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isWeeklyDataChanged(){
      return !RString.equals(__weeklyData, _weeklyData);
   }

   //============================================================
   // <T>获得周常数据的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public String weeklyData(){
      return _weeklyData;
   }

   //============================================================
   // <T>设置周常数据的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setWeeklyData(String value){
      _weeklyData = value;
   }

   //============================================================
   // <T>判断帮会财主活动记录的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isRichmanRecordChanged(){
      return !RString.equals(__richmanRecord, _richmanRecord);
   }

   //============================================================
   // <T>获得帮会财主活动记录的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public String richmanRecord(){
      return _richmanRecord;
   }

   //============================================================
   // <T>设置帮会财主活动记录的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setRichmanRecord(String value){
      _richmanRecord = value;
   }

   //============================================================
   // <T>判断帮会技能列表的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isSocietySkillListChanged(){
      return !RString.equals(__societySkillList, _societySkillList);
   }

   //============================================================
   // <T>获得帮会技能列表的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public String societySkillList(){
      return _societySkillList;
   }

   //============================================================
   // <T>设置帮会技能列表的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setSocietySkillList(String value){
      _societySkillList = value;
   }

   //============================================================
   // <T>判断新手帮会标志的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isIsNewerSocietyChanged(){
      return __isNewerSociety != _isNewerSociety;
   }

   //============================================================
   // <T>获得新手帮会标志的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int isNewerSociety(){
      return _isNewerSociety;
   }

   //============================================================
   // <T>设置新手帮会标志的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setIsNewerSociety(int value){
      _isNewerSociety = value;
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
      __label = row.get("label");
      _label = __label;
      __level = row.getInteger("level");
      _level = __level;
      __placard = row.get("placard");
      _placard = __placard;
      __manifesto = row.get("manifesto");
      _manifesto = __manifesto;
      __memberCount = row.get("member_count");
      _memberCount = __memberCount;
      __applyMemberCount = row.get("apply_member_count");
      _applyMemberCount = __applyMemberCount;
      __firstLeader = RLong.parse(row.get("first_leader"));
      _firstLeader = __firstLeader;
      __createRoleId = RLong.parse(row.get("create_role_id"));
      _createRoleId = __createRoleId;
      __prosperityDegree = row.getInteger("prosperity_degree");
      _prosperityDegree = __prosperityDegree;
      __capital = row.getInteger("capital");
      _capital = __capital;
      __badgeLevel = row.getInteger("badge_level");
      _badgeLevel = __badgeLevel;
      __createTime.parse(row.get("create_time"));
      _createTime.assign(__createTime);
      __applyMember = row.get("apply_member");
      _applyMember = __applyMember;
      __totalSocietywarPoint = row.getInteger("total_societywar_point");
      _totalSocietywarPoint = __totalSocietywarPoint;
      __dailyData = row.get("daily_data");
      _dailyData = __dailyData;
      __weeklyData = row.get("weekly_data");
      _weeklyData = __weeklyData;
      __richmanRecord = row.get("richman_record");
      _richmanRecord = __richmanRecord;
      __societySkillList = row.get("society_skill_list");
      _societySkillList = __societySkillList;
      __isNewerSociety = row.getInteger("is_newer_society");
      _isNewerSociety = __isNewerSociety;
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