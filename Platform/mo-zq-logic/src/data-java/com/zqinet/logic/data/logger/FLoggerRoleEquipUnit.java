package com.zqinet.logic.data.logger;

import org.mo.com.lang.*;
import org.mo.com.collections.*;
import org.mo.com.lang.type.*;
import org.mo.data.logic.*;

//============================================================
// <T>角色装备数据变化日志逻辑单元。</T>
//============================================================
public class FLoggerRoleEquipUnit extends FLogicUnit
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

   // 存储字段装备编号的定义。
   private int __equipTid;

   // 字段装备编号的定义。
   protected int _equipTid;

   // 存储字段装备级别的定义。
   private int __equipLevel;

   // 字段装备级别的定义。
   protected int _equipLevel;

   // 存储字段装备品质的定义。
   private int __equipQuality;

   // 字段装备品质的定义。
   protected int _equipQuality;

   // 存储字段装备星级的定义。
   private int __equipStarLevel;

   // 字段装备星级的定义。
   protected int _equipStarLevel;

   // 存储字段操作类型的定义。
   private int __operationType;

   // 字段操作类型的定义。
   protected int _operationType;

   // 存储字段参数1的定义。
   private int __param1;

   // 字段参数1的定义。
   protected int _param1;

   // 存储字段参数2的定义。
   private int __param2;

   // 字段参数2的定义。
   protected int _param2;

   // 存储字段参数3的定义。
   private int __param3;

   // 字段参数3的定义。
   protected int _param3;

   // 存储字段参数4的定义。
   private int __param4;

   // 字段参数4的定义。
   protected int _param4;

   // 存储字段参数5的定义。
   private int __param5;

   // 字段参数5的定义。
   protected int _param5;

   // 存储字段参数6的定义。
   private int __param6;

   // 字段参数6的定义。
   protected int _param6;

   // 存储字段参数7的定义。
   private int __param7;

   // 字段参数7的定义。
   protected int _param7;

   // 存储字段参数8的定义。
   private int __param8;

   // 字段参数8的定义。
   protected int _param8;

   // 存储字段参数9的定义。
   private int __param9;

   // 字段参数9的定义。
   protected int _param9;

   // 存储字段参数10的定义。
   private int __param10;

   // 字段参数10的定义。
   protected int _param10;

   // 存储字段参数11的定义。
   private int __param11;

   // 字段参数11的定义。
   protected int _param11;

   // 存储字段参数12的定义。
   private int __param12;

   // 字段参数12的定义。
   protected int _param12;

   // 存储字段参数13的定义。
   private int __param13;

   // 字段参数13的定义。
   protected int _param13;

   // 存储字段参数14的定义。
   private int __param14;

   // 字段参数14的定义。
   protected int _param14;

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
   // <T>构造角色装备数据变化日志逻辑单元。</T>
   //============================================================
   public FLoggerRoleEquipUnit(){
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
   // <T>判断装备编号的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isEquipTidChanged(){
      return __equipTid != _equipTid;
   }

   //============================================================
   // <T>获得装备编号的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int equipTid(){
      return _equipTid;
   }

   //============================================================
   // <T>设置装备编号的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setEquipTid(int value){
      _equipTid = value;
   }

   //============================================================
   // <T>判断装备级别的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isEquipLevelChanged(){
      return __equipLevel != _equipLevel;
   }

   //============================================================
   // <T>获得装备级别的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int equipLevel(){
      return _equipLevel;
   }

   //============================================================
   // <T>设置装备级别的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setEquipLevel(int value){
      _equipLevel = value;
   }

   //============================================================
   // <T>判断装备品质的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isEquipQualityChanged(){
      return __equipQuality != _equipQuality;
   }

   //============================================================
   // <T>获得装备品质的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int equipQuality(){
      return _equipQuality;
   }

   //============================================================
   // <T>设置装备品质的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setEquipQuality(int value){
      _equipQuality = value;
   }

   //============================================================
   // <T>判断装备星级的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isEquipStarLevelChanged(){
      return __equipStarLevel != _equipStarLevel;
   }

   //============================================================
   // <T>获得装备星级的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int equipStarLevel(){
      return _equipStarLevel;
   }

   //============================================================
   // <T>设置装备星级的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setEquipStarLevel(int value){
      _equipStarLevel = value;
   }

   //============================================================
   // <T>判断操作类型的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isOperationTypeChanged(){
      return __operationType != _operationType;
   }

   //============================================================
   // <T>获得操作类型的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int operationType(){
      return _operationType;
   }

   //============================================================
   // <T>设置操作类型的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setOperationType(int value){
      _operationType = value;
   }

   //============================================================
   // <T>判断参数1的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isParam1Changed(){
      return __param1 != _param1;
   }

   //============================================================
   // <T>获得参数1的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int param1(){
      return _param1;
   }

   //============================================================
   // <T>设置参数1的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setParam1(int value){
      _param1 = value;
   }

   //============================================================
   // <T>判断参数2的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isParam2Changed(){
      return __param2 != _param2;
   }

   //============================================================
   // <T>获得参数2的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int param2(){
      return _param2;
   }

   //============================================================
   // <T>设置参数2的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setParam2(int value){
      _param2 = value;
   }

   //============================================================
   // <T>判断参数3的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isParam3Changed(){
      return __param3 != _param3;
   }

   //============================================================
   // <T>获得参数3的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int param3(){
      return _param3;
   }

   //============================================================
   // <T>设置参数3的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setParam3(int value){
      _param3 = value;
   }

   //============================================================
   // <T>判断参数4的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isParam4Changed(){
      return __param4 != _param4;
   }

   //============================================================
   // <T>获得参数4的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int param4(){
      return _param4;
   }

   //============================================================
   // <T>设置参数4的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setParam4(int value){
      _param4 = value;
   }

   //============================================================
   // <T>判断参数5的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isParam5Changed(){
      return __param5 != _param5;
   }

   //============================================================
   // <T>获得参数5的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int param5(){
      return _param5;
   }

   //============================================================
   // <T>设置参数5的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setParam5(int value){
      _param5 = value;
   }

   //============================================================
   // <T>判断参数6的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isParam6Changed(){
      return __param6 != _param6;
   }

   //============================================================
   // <T>获得参数6的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int param6(){
      return _param6;
   }

   //============================================================
   // <T>设置参数6的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setParam6(int value){
      _param6 = value;
   }

   //============================================================
   // <T>判断参数7的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isParam7Changed(){
      return __param7 != _param7;
   }

   //============================================================
   // <T>获得参数7的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int param7(){
      return _param7;
   }

   //============================================================
   // <T>设置参数7的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setParam7(int value){
      _param7 = value;
   }

   //============================================================
   // <T>判断参数8的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isParam8Changed(){
      return __param8 != _param8;
   }

   //============================================================
   // <T>获得参数8的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int param8(){
      return _param8;
   }

   //============================================================
   // <T>设置参数8的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setParam8(int value){
      _param8 = value;
   }

   //============================================================
   // <T>判断参数9的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isParam9Changed(){
      return __param9 != _param9;
   }

   //============================================================
   // <T>获得参数9的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int param9(){
      return _param9;
   }

   //============================================================
   // <T>设置参数9的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setParam9(int value){
      _param9 = value;
   }

   //============================================================
   // <T>判断参数10的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isParam10Changed(){
      return __param10 != _param10;
   }

   //============================================================
   // <T>获得参数10的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int param10(){
      return _param10;
   }

   //============================================================
   // <T>设置参数10的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setParam10(int value){
      _param10 = value;
   }

   //============================================================
   // <T>判断参数11的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isParam11Changed(){
      return __param11 != _param11;
   }

   //============================================================
   // <T>获得参数11的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int param11(){
      return _param11;
   }

   //============================================================
   // <T>设置参数11的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setParam11(int value){
      _param11 = value;
   }

   //============================================================
   // <T>判断参数12的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isParam12Changed(){
      return __param12 != _param12;
   }

   //============================================================
   // <T>获得参数12的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int param12(){
      return _param12;
   }

   //============================================================
   // <T>设置参数12的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setParam12(int value){
      _param12 = value;
   }

   //============================================================
   // <T>判断参数13的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isParam13Changed(){
      return __param13 != _param13;
   }

   //============================================================
   // <T>获得参数13的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int param13(){
      return _param13;
   }

   //============================================================
   // <T>设置参数13的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setParam13(int value){
      _param13 = value;
   }

   //============================================================
   // <T>判断参数14的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isParam14Changed(){
      return __param14 != _param14;
   }

   //============================================================
   // <T>获得参数14的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int param14(){
      return _param14;
   }

   //============================================================
   // <T>设置参数14的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setParam14(int value){
      _param14 = value;
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
      __equipTid = row.getInteger("equip_tid");
      _equipTid = __equipTid;
      __equipLevel = row.getInteger("equip_level");
      _equipLevel = __equipLevel;
      __equipQuality = row.getInteger("equip_quality");
      _equipQuality = __equipQuality;
      __equipStarLevel = row.getInteger("equip_star_level");
      _equipStarLevel = __equipStarLevel;
      __operationType = row.getInteger("operation_type");
      _operationType = __operationType;
      __param1 = row.getInteger("param1");
      _param1 = __param1;
      __param2 = row.getInteger("param2");
      _param2 = __param2;
      __param3 = row.getInteger("param3");
      _param3 = __param3;
      __param4 = row.getInteger("param4");
      _param4 = __param4;
      __param5 = row.getInteger("param5");
      _param5 = __param5;
      __param6 = row.getInteger("param6");
      _param6 = __param6;
      __param7 = row.getInteger("param7");
      _param7 = __param7;
      __param8 = row.getInteger("param8");
      _param8 = __param8;
      __param9 = row.getInteger("param9");
      _param9 = __param9;
      __param10 = row.getInteger("param10");
      _param10 = __param10;
      __param11 = row.getInteger("param11");
      _param11 = __param11;
      __param12 = row.getInteger("param12");
      _param12 = __param12;
      __param13 = row.getInteger("param13");
      _param13 = __param13;
      __param14 = row.getInteger("param14");
      _param14 = __param14;
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