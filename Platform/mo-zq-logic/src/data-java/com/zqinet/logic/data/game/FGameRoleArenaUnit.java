package com.zqinet.logic.data.game;

import org.mo.com.lang.*;
import org.mo.com.collections.*;
import org.mo.com.lang.type.*;
import org.mo.data.logic.*;

//============================================================
// <T>擂台存储表逻辑单元。</T>
//============================================================
public class FGameRoleArenaUnit extends FLogicUnit
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

   // 存储字段角色编号的定义。
   private long __roleId;

   // 字段角色编号的定义。
   protected long _roleId;

   // 存储字段胜利场数的定义。
   private int __win;

   // 字段胜利场数的定义。
   protected int _win;

   // 存储字段连杀的定义。
   private int __comboKill;

   // 字段连杀的定义。
   protected int _comboKill;

   // 存储字段积分的定义。
   private int __point;

   // 字段积分的定义。
   protected int _point;

   // 存储字段师门竞技排名的定义。
   private int __metierArenaRank;

   // 字段师门竞技排名的定义。
   protected int _metierArenaRank;

   // 存储字段战力的定义。
   private int __gradePoint;

   // 字段战力的定义。
   protected int _gradePoint;

   // 存储字段等级的定义。
   private int __level;

   // 字段等级的定义。
   protected int _level;

   // 存储字段名称的定义。
   private String __label;

   // 字段名称的定义。
   protected String _label;

   // 存储字段师门竞技战况的定义。
   private String __metierArenaResult;

   // 字段师门竞技战况的定义。
   protected String _metierArenaResult;

   // 存储字段头像编号的定义。
   private int __styleIconTid;

   // 字段头像编号的定义。
   protected int _styleIconTid;

   // 存储字段职业模板编号的定义。
   private int __metierTid;

   // 字段职业模板编号的定义。
   protected int _metierTid;

   // 存储字段师门竞技积分的定义。
   private int __metierArenaPoint;

   // 字段师门竞技积分的定义。
   protected int _metierArenaPoint;

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
   // <T>构造擂台存储表逻辑单元。</T>
   //============================================================
   public FGameRoleArenaUnit(){
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
   // <T>判断角色编号的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isRoleIdChanged(){
      return __roleId != _roleId;
   }

   //============================================================
   // <T>获得角色编号的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public long roleId(){
      return _roleId;
   }

   //============================================================
   // <T>设置角色编号的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setRoleId(long value){
      _roleId = value;
   }

   //============================================================
   // <T>判断胜利场数的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isWinChanged(){
      return __win != _win;
   }

   //============================================================
   // <T>获得胜利场数的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int win(){
      return _win;
   }

   //============================================================
   // <T>设置胜利场数的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setWin(int value){
      _win = value;
   }

   //============================================================
   // <T>判断连杀的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isComboKillChanged(){
      return __comboKill != _comboKill;
   }

   //============================================================
   // <T>获得连杀的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int comboKill(){
      return _comboKill;
   }

   //============================================================
   // <T>设置连杀的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setComboKill(int value){
      _comboKill = value;
   }

   //============================================================
   // <T>判断积分的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isPointChanged(){
      return __point != _point;
   }

   //============================================================
   // <T>获得积分的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int point(){
      return _point;
   }

   //============================================================
   // <T>设置积分的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setPoint(int value){
      _point = value;
   }

   //============================================================
   // <T>判断师门竞技排名的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isMetierArenaRankChanged(){
      return __metierArenaRank != _metierArenaRank;
   }

   //============================================================
   // <T>获得师门竞技排名的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int metierArenaRank(){
      return _metierArenaRank;
   }

   //============================================================
   // <T>设置师门竞技排名的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setMetierArenaRank(int value){
      _metierArenaRank = value;
   }

   //============================================================
   // <T>判断战力的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isGradePointChanged(){
      return __gradePoint != _gradePoint;
   }

   //============================================================
   // <T>获得战力的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int gradePoint(){
      return _gradePoint;
   }

   //============================================================
   // <T>设置战力的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setGradePoint(int value){
      _gradePoint = value;
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
   // <T>判断师门竞技战况的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isMetierArenaResultChanged(){
      return !RString.equals(__metierArenaResult, _metierArenaResult);
   }

   //============================================================
   // <T>获得师门竞技战况的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public String metierArenaResult(){
      return _metierArenaResult;
   }

   //============================================================
   // <T>设置师门竞技战况的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setMetierArenaResult(String value){
      _metierArenaResult = value;
   }

   //============================================================
   // <T>判断头像编号的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isStyleIconTidChanged(){
      return __styleIconTid != _styleIconTid;
   }

   //============================================================
   // <T>获得头像编号的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int styleIconTid(){
      return _styleIconTid;
   }

   //============================================================
   // <T>设置头像编号的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setStyleIconTid(int value){
      _styleIconTid = value;
   }

   //============================================================
   // <T>判断职业模板编号的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isMetierTidChanged(){
      return __metierTid != _metierTid;
   }

   //============================================================
   // <T>获得职业模板编号的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int metierTid(){
      return _metierTid;
   }

   //============================================================
   // <T>设置职业模板编号的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setMetierTid(int value){
      _metierTid = value;
   }

   //============================================================
   // <T>判断师门竞技积分的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isMetierArenaPointChanged(){
      return __metierArenaPoint != _metierArenaPoint;
   }

   //============================================================
   // <T>获得师门竞技积分的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int metierArenaPoint(){
      return _metierArenaPoint;
   }

   //============================================================
   // <T>设置师门竞技积分的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setMetierArenaPoint(int value){
      _metierArenaPoint = value;
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
      __roleId = RLong.parse(row.get("role_id"));
      _roleId = __roleId;
      __win = row.getInteger("win");
      _win = __win;
      __comboKill = row.getInteger("combo_kill");
      _comboKill = __comboKill;
      __point = row.getInteger("point");
      _point = __point;
      __metierArenaRank = row.getInteger("metier_arena_rank");
      _metierArenaRank = __metierArenaRank;
      __gradePoint = row.getInteger("grade_point");
      _gradePoint = __gradePoint;
      __level = row.getInteger("level");
      _level = __level;
      __label = row.get("label");
      _label = __label;
      __metierArenaResult = row.get("metier_arena_result");
      _metierArenaResult = __metierArenaResult;
      __styleIconTid = row.getInteger("style_icon_tid");
      _styleIconTid = __styleIconTid;
      __metierTid = row.getInteger("metier_tid");
      _metierTid = __metierTid;
      __metierArenaPoint = row.getInteger("metier_arena_point");
      _metierArenaPoint = __metierArenaPoint;
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