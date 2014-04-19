package com.zqinet.logic.data.game;

import org.mo.com.lang.*;
import org.mo.com.collections.*;
import org.mo.com.data.*;
import org.mo.data.logic.*;

//============================================================
// <T>角色信息逻辑。</T>
//============================================================
public class FGameRoleLogic extends FLogicDataset
{
   // 的定义。
   public final static FLogicTable Table = new FLogicTable("GM_ROLE");

   // 字段对象标识的定义。
   public final static FLogicField FieldOuid = new FLogicField("OUID");

   // 字段有效性的定义。
   public final static FLogicField FieldOvld = new FLogicField("OVLD");

   // 字段唯一标识的定义。
   public final static FLogicField FieldUniqueId = new FLogicField("UNIQUE_ID");

   // 字段账户编号的定义。
   public final static FLogicField FieldAccountId = new FLogicField("ACCOUNT_ID");

   // 字段状态的定义。
   public final static FLogicField FieldStatusCd = new FLogicField("STATUS_CD");

   // 字段删除时间的定义。
   public final static FLogicField FieldDeleteTime = new FLogicField("DELETE_TIME");

   // 字段上线日期时间的定义。
   public final static FLogicField FieldOnlineDatetime = new FLogicField("ONLINE_DATETIME");

   // 字段下线日期时间的定义。
   public final static FLogicField FieldOfflineDatetime = new FLogicField("OFFLINE_DATETIME");

   // 字段标志的定义。
   public final static FLogicField FieldFlags = new FLogicField("FLAGS");

   // 字段中文名称的定义。
   public final static FLogicField FieldLabel = new FLogicField("LABEL");

   // 字段时代模版编号的定义。
   public final static FLogicField FieldTimeTid = new FLogicField("TIME_TID");

   // 字段角色模板编号的定义。
   public final static FLogicField FieldRoleTid = new FLogicField("ROLE_TID");

   // 字段种族模板编号的定义。
   public final static FLogicField FieldRaceTid = new FLogicField("RACE_TID");

   // 字段性别模板编号的定义。
   public final static FLogicField FieldGenderTid = new FLogicField("GENDER_TID");

   // 字段职业模板编号的定义。
   public final static FLogicField FieldMetierTid = new FLogicField("METIER_TID");

   // 字段阵法模板编号的定义。
   public final static FLogicField FieldMatrixTid = new FLogicField("MATRIX_TID");

   // 字段级别的定义。
   public final static FLogicField FieldLevel = new FLogicField("LEVEL");

   // 字段生命值的定义。
   public final static FLogicField FieldHp = new FLogicField("HP");

   // 字段魔法值的定义。
   public final static FLogicField FieldMp = new FLogicField("MP");

   // 字段活力值的定义。
   public final static FLogicField FieldLp = new FLogicField("LP");

   // 字段精力值的定义。
   public final static FLogicField FieldEp = new FLogicField("EP");

   // 字段怒气值的定义。
   public final static FLogicField FieldAp = new FLogicField("AP");

   // 字段体质的定义。
   public final static FLogicField FieldVitality = new FLogicField("VITALITY");

   // 字段力量的定义。
   public final static FLogicField FieldStrength = new FLogicField("STRENGTH");

   // 字段耐力的定义。
   public final static FLogicField FieldStamina = new FLogicField("STAMINA");

   // 字段魔力的定义。
   public final static FLogicField FieldMagic = new FLogicField("MAGIC");

   // 字段敏捷的定义。
   public final static FLogicField FieldAgility = new FLogicField("AGILITY");

   // 字段潜力点的定义。
   public final static FLogicField FieldPotencyPoint = new FLogicField("POTENCY_POINT");

   // 字段速度的定义。
   public final static FLogicField FieldSpeed = new FLogicField("SPEED");

   // 字段行动值的定义。
   public final static FLogicField FieldMobility = new FLogicField("MOBILITY");

   // 字段格挡值的定义。
   public final static FLogicField FieldBlock = new FLogicField("BLOCK");

   // 字段暴击值的定义。
   public final static FLogicField FieldCri = new FLogicField("CRI");

   // 字段命中值的定义。
   public final static FLogicField FieldHit = new FLogicField("HIT");

   // 字段闪避值的定义。
   public final static FLogicField FieldDodge = new FLogicField("DODGE");

   // 字段物理攻击力的定义。
   public final static FLogicField FieldAttackPhysical = new FLogicField("ATTACK_PHYSICAL");

   // 字段穿刺攻击力的定义。
   public final static FLogicField FieldAttackPuncture = new FLogicField("ATTACK_PUNCTURE");

   // 字段魔法攻击力的定义。
   public final static FLogicField FieldAttackMagic = new FLogicField("ATTACK_MAGIC");

   // 字段物理防御力的定义。
   public final static FLogicField FieldDefencePhysical = new FLogicField("DEFENCE_PHYSICAL");

   // 字段穿刺防御力的定义。
   public final static FLogicField FieldDefencePuncture = new FLogicField("DEFENCE_PUNCTURE");

   // 字段魔法防御力的定义。
   public final static FLogicField FieldDefenceMagic = new FLogicField("DEFENCE_MAGIC");

   // 字段经验的定义。
   public final static FLogicField FieldExperience = new FLogicField("EXPERIENCE");

   // 字段升级所需经验的定义。
   public final static FLogicField FieldExperienceup = new FLogicField("EXPERIENCEUP");

   // 字段帮会编号的定义。
   public final static FLogicField FieldSocietyId = new FLogicField("SOCIETY_ID");

   // 字段帮会贡献的定义。
   public final static FLogicField FieldContribute = new FLogicField("CONTRIBUTE");

   // 字段帮会职位的定义。
   public final static FLogicField FieldSocietyJob = new FLogicField("SOCIETY_JOB");

   // 字段帮会的精英成员的定义。
   public final static FLogicField FieldSocietyEliteMember = new FLogicField("SOCIETY_ELITE_MEMBER");

   // 字段进帮时间的定义。
   public final static FLogicField FieldEntranceTime = new FLogicField("ENTRANCE_TIME");

   // 字段下线场景线模板编号的定义。
   public final static FLogicField FieldOfflineLineTid = new FLogicField("OFFLINE_LINE_TID");

   // 字段下线地图模板编号的定义。
   public final static FLogicField FieldOfflineMapTid = new FLogicField("OFFLINE_MAP_TID");

   // 字段副本中下线的方向的定义。
   public final static FLogicField FieldOfflineMapId = new FLogicField("OFFLINE_MAP_ID");

   // 字段下线坐标的定义。
   public final static FLogicField FieldOfflineLocation = new FLogicField("OFFLINE_LOCATION");

   // 字段下线方向的定义。
   public final static FLogicField FieldOfflineDirectionCd = new FLogicField("OFFLINE_DIRECTION_CD");

   // 字段会员等级的定义。
   public final static FLogicField FieldVipLevel = new FLogicField("VIP_LEVEL");

   // 字段宠物背包的定义。
   public final static FLogicField FieldBagPet = new FLogicField("BAG_PET");

   // 字段宠物仓库的定义。
   public final static FLogicField FieldDepotPet = new FLogicField("DEPOT_PET");

   // 字段坐骑背包的定义。
   public final static FLogicField FieldBagRide = new FLogicField("BAG_RIDE");

   // 字段剩余双倍经验时间的定义。
   public final static FLogicField FieldSurplusDoubleTime = new FLogicField("SURPLUS_DOUBLE_TIME");

   // 字段可用双倍经验时间的定义。
   public final static FLogicField FieldAvailableTime = new FLogicField("AVAILABLE_TIME");

   // 字段双倍经验开始时间的定义。
   public final static FLogicField FieldStartDoubleTime = new FLogicField("START_DOUBLE_TIME");

   // 字段自动战斗中使用的技能编号的定义。
   public final static FLogicField FieldSkillId = new FLogicField("SKILL_ID");

   // 字段挂机标志的定义。
   public final static FLogicField FieldOnhookFlags = new FLogicField("ONHOOK_FLAGS");

   // 字段角色最小生命比例的定义。
   public final static FLogicField FieldRoleMinhpRate = new FLogicField("ROLE_MINHP_RATE");

   // 字段角色最小魔法比例的定义。
   public final static FLogicField FieldRoleMinmpRate = new FLogicField("ROLE_MINMP_RATE");

   // 字段宠物编号的定义。
   public final static FLogicField FieldPetId = new FLogicField("PET_ID");

   // 字段自动战斗中宠物使用的技能编号的定义。
   public final static FLogicField FieldPetSkillId = new FLogicField("PET_SKILL_ID");

   // 字段真元的定义。
   public final static FLogicField FieldCrystal = new FLogicField("CRYSTAL");

   // 字段门派贡献的定义。
   public final static FLogicField FieldMetierContribution = new FLogicField("METIER_CONTRIBUTION");

   // 字段境界等级的定义。
   public final static FLogicField FieldRealmLevel = new FLogicField("REALM_LEVEL");

   // 字段经脉等级的定义。
   public final static FLogicField FieldChannelLevel = new FLogicField("CHANNEL_LEVEL");

   // 字段炼心等级的定义。
   public final static FLogicField FieldHeartLevel = new FLogicField("HEART_LEVEL");

   // 字段融神等级的定义。
   public final static FLogicField FieldSpiritLevel = new FLogicField("SPIRIT_LEVEL");

   // 字段商城物品购买限制的定义。
   public final static FLogicField FieldShopItemLimit = new FLogicField("SHOP_ITEM_LIMIT");

   // 字段累计在线时长的定义。
   public final static FLogicField FieldTotalOnlineTime = new FLogicField("TOTAL_ONLINE_TIME");

   // 字段押镖刷新次数的定义。
   public final static FLogicField FieldRefreshTaskEscort = new FLogicField("REFRESH_TASK_ESCORT");

   // 字段押镖任务部分的定义。
   public final static FLogicField FieldPartEscort = new FLogicField("PART_ESCORT");

   // 字段登录角色网络地址的定义。
   public final static FLogicField FieldLoginHost = new FLogicField("LOGIN_HOST");

   // 字段登录时间的定义。
   public final static FLogicField FieldLoginDate = new FLogicField("LOGIN_DATE");

   // 字段pk值的定义。
   public final static FLogicField FieldPkValue = new FLogicField("PK_VALUE");

   // 字段人物战力的定义。
   public final static FLogicField FieldGradePoint = new FLogicField("GRADE_POINT");

   // 字段引导标志的定义。
   public final static FLogicField FieldGuideFlag = new FLogicField("GUIDE_FLAG");

   // 字段队伍ID的定义。
   public final static FLogicField FieldTeamId = new FLogicField("TEAM_ID");

   // 字段活跃度点数的定义。
   public final static FLogicField FieldActivePoint = new FLogicField("ACTIVE_POINT");

   // 字段竞技场点数的定义。
   public final static FLogicField FieldTournamentPoint = new FLogicField("TOURNAMENT_POINT");

   // 字段帮会战点数的定义。
   public final static FLogicField FieldSocietyWarPoint = new FLogicField("SOCIETY_WAR_POINT");

   // 字段绑定金的定义。
   public final static FLogicField FieldGoldBind = new FLogicField("GOLD_BIND");

   // 字段非绑定金币的定义。
   public final static FLogicField FieldGoldUnbind = new FLogicField("GOLD_UNBIND");

   // 字段绑定元宝的定义。
   public final static FLogicField FieldMoneyBind = new FLogicField("MONEY_BIND");

   // 字段元宝的定义。
   public final static FLogicField FieldMoneyUnbind = new FLogicField("MONEY_UNBIND");

   // 字段赠点的定义。
   public final static FLogicField FieldPoint = new FLogicField("POINT");

   // 字段仓库绑定金币的定义。
   public final static FLogicField FieldDepotGoldBind = new FLogicField("DEPOT_GOLD_BIND");

   // 字段仓库非绑定金币的定义。
   public final static FLogicField FieldDepotGoldUnbind = new FLogicField("DEPOT_GOLD_UNBIND");

   // 字段仓库绑定元宝的定义。
   public final static FLogicField FieldDepotMoneyBind = new FLogicField("DEPOT_MONEY_BIND");

   // 字段仓库元宝的定义。
   public final static FLogicField FieldDepotMoneyUnbind = new FLogicField("DEPOT_MONEY_UNBIND");

   // 字段仓库赠点的定义。
   public final static FLogicField FieldDepotPiont = new FLogicField("DEPOT_PIONT");

   // 字段劫镖次数的定义。
   public final static FLogicField FieldRobberyCount = new FLogicField("ROBBERY_COUNT");

   // 字段连续登录天数的定义。
   public final static FLogicField FieldLastLoginDays = new FLogicField("LAST_LOGIN_DAYS");

   // 字段交易行出售金钱数量的定义。
   public final static FLogicField FieldAuctionCurrencyCount = new FLogicField("AUCTION_CURRENCY_COUNT");

   // 字段离线时间的定义。
   public final static FLogicField FieldLastOfflineTime = new FLogicField("LAST_OFFLINE_TIME");

   // 字段魅力值的定义。
   public final static FLogicField FieldCharmingValue = new FLogicField("CHARMING_VALUE");

   // 字段杀人数的定义。
   public final static FLogicField FieldKillCount = new FLogicField("KILL_COUNT");

   // 字段消耗元宝数量的定义。
   public final static FLogicField FieldSpendMoney = new FLogicField("SPEND_MONEY");

   // 字段会员礼包领取标志的定义。
   public final static FLogicField FieldVipGiftFlag = new FLogicField("VIP_GIFT_FLAG");

   // 字段会员标志的定义。
   public final static FLogicField FieldVipFlag = new FLogicField("VIP_FLAG");

   // 字段二级密码问题一的定义。
   public final static FLogicField FieldQuestionOne = new FLogicField("QUESTION_ONE");

   // 字段二级密码回答一的定义。
   public final static FLogicField FieldAnswerOne = new FLogicField("ANSWER_ONE");

   // 字段二级密码问题二的定义。
   public final static FLogicField FieldQuestionTwo = new FLogicField("QUESTION_TWO");

   // 字段二级密码回答二的定义。
   public final static FLogicField FieldAnswerTwo = new FLogicField("ANSWER_TWO");

   // 字段二级密码的定义。
   public final static FLogicField FieldCodePassword = new FLogicField("CODE_PASSWORD");

   // 字段上次保存时间的定义。
   public final static FLogicField FieldStorageLastTime = new FLogicField("STORAGE_LAST_TIME");

   // 字段禁言开始的定义。
   public final static FLogicField FieldTalkGanBegin = new FLogicField("TALK_GAN_BEGIN");

   // 字段禁言结束的定义。
   public final static FLogicField FieldTalkGanEnd = new FLogicField("TALK_GAN_END");

   // 字段角色冻结的定义。
   public final static FLogicField FieldRoleFreeze = new FLogicField("ROLE_FREEZE");

   // 字段时装展示的定义。
   public final static FLogicField FieldShizhuangShow = new FLogicField("SHIZHUANG_SHOW");

   // 字段神剑开始时间的定义。
   public final static FLogicField FieldMagicsWordTime = new FLogicField("MAGICS_WORD_TIME");

   // 字段神剑剩余时间的定义。
   public final static FLogicField FieldSurplusTime = new FLogicField("SURPLUS_TIME");

   // 字段是否领取过首冲的定义。
   public final static FLogicField FieldIsFirstGet = new FLogicField("IS_FIRST_GET");

   // 字段今日在线时长的定义。
   public final static FLogicField FieldOnlineToday = new FLogicField("ONLINE_TODAY");

   // 字段服务器更新标记的定义。
   public final static FLogicField FieldServerFlag = new FLogicField("SERVER_FLAG");

   // 字段剑魂的定义。
   public final static FLogicField FieldSwordSoul = new FLogicField("SWORD_SOUL");

   // 字段师尊挑战等级的定义。
   public final static FLogicField FieldMasterChallengeLevel = new FLogicField("MASTER_CHALLENGE_LEVEL");

   // 字段师尊挑战最大等级的定义。
   public final static FLogicField FieldMasterChallengeLevelMax = new FLogicField("MASTER_CHALLENGE_LEVEL_MAX");

   // 字段上次注气时间的定义。
   public final static FLogicField FieldLastInjectSoulTime = new FLogicField("LAST_INJECT_SOUL_TIME");

   // 字段四圣血气的定义。
   public final static FLogicField FieldFourGodBloodGas = new FLogicField("FOUR_GOD_BLOOD_GAS");

   // 字段快速师尊挑战时间的定义。
   public final static FLogicField FieldQuickMasterChallengeTime = new FLogicField("QUICK_MASTER_CHALLENGE_TIME");

   // 字段道具增加挂机次数的定义。
   public final static FLogicField FieldAddOnhookCount = new FLogicField("ADD_ONHOOK_COUNT");

   // 字段师门竞技冷却时间的定义。
   public final static FLogicField FieldMetierArenaCdTime = new FLogicField("METIER_ARENA_CD_TIME");

   // 字段师门竞技时间的定义。
   public final static FLogicField FieldMetierArenaTime = new FLogicField("METIER_ARENA_TIME");

   // 字段龙珠收集的定义。
   public final static FLogicField FieldDragonBallGather = new FLogicField("DRAGON_BALL_GATHER");

   // 字段成就点的定义。
   public final static FLogicField FieldAchievementPoint = new FLogicField("ACHIEVEMENT_POINT");

   // 字段成就等级的定义。
   public final static FLogicField FieldAchievementLevel = new FLogicField("ACHIEVEMENT_LEVEL");

   // 字段黄钻奖励的定义。
   public final static FLogicField FieldYellowReward = new FLogicField("YELLOW_REWARD");

   // 字段黄钻 冲击奖励信息的定义。
   public final static FLogicField FieldYellowUpReward = new FLogicField("YELLOW_UP_REWARD");

   // 字段上次领取黄钻每日奖励时间的定义。
   public final static FLogicField FieldYellowRewardTime = new FLogicField("YELLOW_REWARD_TIME");

   // 字段登录天数的定义。
   public final static FLogicField FieldLoginDay = new FLogicField("LOGIN_DAY");

   // 字段登录奖励物品的定义。
   public final static FLogicField FieldLoginGetItem = new FLogicField("LOGIN_GET_ITEM");

   // 字段累计充值领奖信息的定义。
   public final static FLogicField FieldAllRechargeRewardMail = new FLogicField("ALL_RECHARGE_REWARD_MAIL");

   // 字段日常数据编号的定义。
   public final static FLogicField FieldRoleDataId = new FLogicField("ROLE_DATA_ID");

   // 字段平台邮件编号们的定义。
   public final static FLogicField FieldPfMailIds = new FLogicField("PF_MAIL_IDS");

   // 字段结婚编号的定义。
   public final static FLogicField FieldMarryId = new FLogicField("MARRY_ID");

   // 字段副本快速挑战日期时间的定义。
   public final static FLogicField FieldQuickCopyTime = new FLogicField("QUICK_COPY_TIME");

   // 字段快速挑战副本的CD的定义。
   public final static FLogicField FieldQuickCopyCd = new FLogicField("QUICK_COPY_CD");

   // 字段组队竞技积分的定义。
   public final static FLogicField FieldTeamWarMark = new FLogicField("TEAM_WAR_MARK");

   // 字段上次开启杀戮模式时间的定义。
   public final static FLogicField FieldLastMurderTime = new FLogicField("LAST_MURDER_TIME");

   // 字段创建用户标识的定义。
   public final static FLogicField FieldCreateUserId = new FLogicField("CREATE_USER_ID");

   // 字段创建日期的定义。
   public final static FLogicField FieldCreateDate = new FLogicField("CREATE_DATE");

   // 字段更新者标识的定义。
   public final static FLogicField FieldUpdateUserId = new FLogicField("UPDATE_USER_ID");

   // 字段更新时间的定义。
   public final static FLogicField FieldUpdateDate = new FLogicField("UPDATE_DATE");

   //============================================================
   // <T>构造角色信息逻辑单元。</T>
   //============================================================
   public FGameRoleLogic(){
   }

   //============================================================
   // <T>构造角色信息逻辑单元。</T>
   //
   // @param connection 链接
   //============================================================
   public FGameRoleLogic(ISqlConnection connection){
      _connection = connection;
   }

   //============================================================
   // <T>根据编号获得一个数据单元。</T>
   //
   // @param recordId 记录编号
   // @return 数据单元
   //============================================================
   public FGameRoleUnit find(long recordId){
      // 检查记录编号
      if(0 == recordId){
         return null;
      }
      // 生成命令
      FSql sql = new FSql("SELECT * FROM ");
      sql.append(Table.name());
      sql.append(" WHERE OUID=");
      sql.append(recordId);
      // 执行命令
      FRow row = _connection.find(sql.toString());
      if(null == row){
         return null;
      }
      // 获得数据
      FGameRoleUnit unit = new FGameRoleUnit();
      unit.load(row);
      return unit;
   }

   //============================================================
   // <T>根据条件获得一个数据单元。</T>
   //
   // @param whereSql 条件
   // @return 数据单元
   //============================================================
   public FGameRoleUnit serach(String whereSql){
      // 生成命令
      FSql sql = new FSql("SELECT * FROM ");
      sql.append(Table.name());
      sql.append(" WHERE ");
      sql.append(whereSql);
      // 执行命令
      FRow row = _connection.find(sql.toString());
      // 获得数据
      if(null == row){
         return null;
      }
      FGameRoleUnit unit = new FGameRoleUnit();
      unit.load(row);
      return unit;
   }

   //============================================================
   // <T>根据条件获得数据单元集合。</T>
   //
   // @param whereSql 条件命令
   // @return 数据单元集合
   //============================================================
   public FGameRoleUnit[] fetch(String whereSql){
      return fetch(whereSql, null, 0);
   }

   //============================================================
   // <T>根据条件获得数据单元集合。</T>
   //
   // @param whereSql 条件命令
   // @param orderSql 排序命令
   // @return 数据单元集合
   //============================================================
   public FGameRoleUnit[] fetch(String whereSql, String orderSql){
      return fetch(whereSql, orderSql, 0);
   }

   //============================================================
   // <T>根据条件获得数据单元集合。</T>
   //
   // @param whereSql 条件命令
   // @param orderSql 排序命令
   // @param limitCount 限制条数
   // @return 数据单元集合
   //============================================================
   public FGameRoleUnit[] fetch(String whereSql, String orderSql, int limitCount){
      // 生成命令
      FSql sql = new FSql("SELECT * FROM ");
      sql.append(Table.name());
      if(!RString.isEmpty(whereSql)){
         sql.append(" WHERE ");
         sql.append(whereSql);
      }
      if(!RString.isEmpty(orderSql)){
         sql.append(" ORDER BY ");
         sql.append(orderSql);
      }
      if(limitCount > 0){
         sql.append(" LIMIT ");
         sql.append(limitCount);
      }
      // 执行命令
      FDataset dataset = _connection.fetchDataset(sql.toString());
      int count = dataset.count();
      // 获得数据
      FGameRoleUnit[] units = new FGameRoleUnit[count];
      for(int n = 0; n < count; n++){
         FGameRoleUnit unit = new FGameRoleUnit();
         unit.load(dataset.get(n));
         units[n] = unit;
      }
      return units;
   }

   //============================================================
   // <T>根据条件获得全部数据单元集合。</T>
   //
   // @return 数据单元集合
   //============================================================
   public FGameRoleUnit[] fetchAll(){
      return fetch(null);
   }

   //============================================================
   // <T>插入一个数据单元。</T>
   //
   // @param unit 数据单元
   // @return 处理结果
   //============================================================
   public boolean doInsert(FGameRoleUnit unit){
      // 生成命令
      FSql sql = new FSql("INSERT INTO ");
      sql.append(Table.name());
      sql.append("(");
      sql.append("`OVLD`");
      sql.append(",`UNIQUE_ID`");
      sql.append(",`ACCOUNT_ID`");
      sql.append(",`STATUS_CD`");
      sql.append(",`DELETE_TIME`");
      sql.append(",`ONLINE_DATETIME`");
      sql.append(",`OFFLINE_DATETIME`");
      sql.append(",`FLAGS`");
      sql.append(",`LABEL`");
      sql.append(",`TIME_TID`");
      sql.append(",`ROLE_TID`");
      sql.append(",`RACE_TID`");
      sql.append(",`GENDER_TID`");
      sql.append(",`METIER_TID`");
      sql.append(",`MATRIX_TID`");
      sql.append(",`LEVEL`");
      sql.append(",`HP`");
      sql.append(",`MP`");
      sql.append(",`LP`");
      sql.append(",`EP`");
      sql.append(",`AP`");
      sql.append(",`VITALITY`");
      sql.append(",`STRENGTH`");
      sql.append(",`STAMINA`");
      sql.append(",`MAGIC`");
      sql.append(",`AGILITY`");
      sql.append(",`POTENCY_POINT`");
      sql.append(",`SPEED`");
      sql.append(",`MOBILITY`");
      sql.append(",`BLOCK`");
      sql.append(",`CRI`");
      sql.append(",`HIT`");
      sql.append(",`DODGE`");
      sql.append(",`ATTACK_PHYSICAL`");
      sql.append(",`ATTACK_PUNCTURE`");
      sql.append(",`ATTACK_MAGIC`");
      sql.append(",`DEFENCE_PHYSICAL`");
      sql.append(",`DEFENCE_PUNCTURE`");
      sql.append(",`DEFENCE_MAGIC`");
      sql.append(",`EXPERIENCE`");
      sql.append(",`EXPERIENCEUP`");
      sql.append(",`SOCIETY_ID`");
      sql.append(",`CONTRIBUTE`");
      sql.append(",`SOCIETY_JOB`");
      sql.append(",`SOCIETY_ELITE_MEMBER`");
      sql.append(",`ENTRANCE_TIME`");
      sql.append(",`OFFLINE_LINE_TID`");
      sql.append(",`OFFLINE_MAP_TID`");
      sql.append(",`OFFLINE_MAP_ID`");
      sql.append(",`OFFLINE_LOCATION`");
      sql.append(",`OFFLINE_DIRECTION_CD`");
      sql.append(",`VIP_LEVEL`");
      sql.append(",`BAG_PET`");
      sql.append(",`DEPOT_PET`");
      sql.append(",`BAG_RIDE`");
      sql.append(",`SURPLUS_DOUBLE_TIME`");
      sql.append(",`AVAILABLE_TIME`");
      sql.append(",`START_DOUBLE_TIME`");
      sql.append(",`SKILL_ID`");
      sql.append(",`ONHOOK_FLAGS`");
      sql.append(",`ROLE_MINHP_RATE`");
      sql.append(",`ROLE_MINMP_RATE`");
      sql.append(",`PET_ID`");
      sql.append(",`PET_SKILL_ID`");
      sql.append(",`CRYSTAL`");
      sql.append(",`METIER_CONTRIBUTION`");
      sql.append(",`REALM_LEVEL`");
      sql.append(",`CHANNEL_LEVEL`");
      sql.append(",`HEART_LEVEL`");
      sql.append(",`SPIRIT_LEVEL`");
      sql.append(",`SHOP_ITEM_LIMIT`");
      sql.append(",`TOTAL_ONLINE_TIME`");
      sql.append(",`REFRESH_TASK_ESCORT`");
      sql.append(",`PART_ESCORT`");
      sql.append(",`LOGIN_HOST`");
      sql.append(",`LOGIN_DATE`");
      sql.append(",`PK_VALUE`");
      sql.append(",`GRADE_POINT`");
      sql.append(",`GUIDE_FLAG`");
      sql.append(",`TEAM_ID`");
      sql.append(",`ACTIVE_POINT`");
      sql.append(",`TOURNAMENT_POINT`");
      sql.append(",`SOCIETY_WAR_POINT`");
      sql.append(",`GOLD_BIND`");
      sql.append(",`GOLD_UNBIND`");
      sql.append(",`MONEY_BIND`");
      sql.append(",`MONEY_UNBIND`");
      sql.append(",`POINT`");
      sql.append(",`DEPOT_GOLD_BIND`");
      sql.append(",`DEPOT_GOLD_UNBIND`");
      sql.append(",`DEPOT_MONEY_BIND`");
      sql.append(",`DEPOT_MONEY_UNBIND`");
      sql.append(",`DEPOT_PIONT`");
      sql.append(",`ROBBERY_COUNT`");
      sql.append(",`LAST_LOGIN_DAYS`");
      sql.append(",`AUCTION_CURRENCY_COUNT`");
      sql.append(",`LAST_OFFLINE_TIME`");
      sql.append(",`CHARMING_VALUE`");
      sql.append(",`KILL_COUNT`");
      sql.append(",`SPEND_MONEY`");
      sql.append(",`VIP_GIFT_FLAG`");
      sql.append(",`VIP_FLAG`");
      sql.append(",`QUESTION_ONE`");
      sql.append(",`ANSWER_ONE`");
      sql.append(",`QUESTION_TWO`");
      sql.append(",`ANSWER_TWO`");
      sql.append(",`CODE_PASSWORD`");
      sql.append(",`STORAGE_LAST_TIME`");
      sql.append(",`TALK_GAN_BEGIN`");
      sql.append(",`TALK_GAN_END`");
      sql.append(",`ROLE_FREEZE`");
      sql.append(",`SHIZHUANG_SHOW`");
      sql.append(",`MAGICS_WORD_TIME`");
      sql.append(",`SURPLUS_TIME`");
      sql.append(",`IS_FIRST_GET`");
      sql.append(",`ONLINE_TODAY`");
      sql.append(",`SERVER_FLAG`");
      sql.append(",`SWORD_SOUL`");
      sql.append(",`MASTER_CHALLENGE_LEVEL`");
      sql.append(",`MASTER_CHALLENGE_LEVEL_MAX`");
      sql.append(",`LAST_INJECT_SOUL_TIME`");
      sql.append(",`FOUR_GOD_BLOOD_GAS`");
      sql.append(",`QUICK_MASTER_CHALLENGE_TIME`");
      sql.append(",`ADD_ONHOOK_COUNT`");
      sql.append(",`METIER_ARENA_CD_TIME`");
      sql.append(",`METIER_ARENA_TIME`");
      sql.append(",`DRAGON_BALL_GATHER`");
      sql.append(",`ACHIEVEMENT_POINT`");
      sql.append(",`ACHIEVEMENT_LEVEL`");
      sql.append(",`YELLOW_REWARD`");
      sql.append(",`YELLOW_UP_REWARD`");
      sql.append(",`YELLOW_REWARD_TIME`");
      sql.append(",`LOGIN_DAY`");
      sql.append(",`LOGIN_GET_ITEM`");
      sql.append(",`ALL_RECHARGE_REWARD_MAIL`");
      sql.append(",`ROLE_DATA_ID`");
      sql.append(",`PF_MAIL_IDS`");
      sql.append(",`MARRY_ID`");
      sql.append(",`QUICK_COPY_TIME`");
      sql.append(",`QUICK_COPY_CD`");
      sql.append(",`TEAM_WAR_MARK`");
      sql.append(",`LAST_MURDER_TIME`");
      sql.append(",`CREATE_USER_ID`");
      sql.append(",`CREATE_DATE`");
      sql.append(",`UPDATE_USER_ID`");
      sql.append(",`UPDATE_DATE`");
      sql.append(") VALUES(");
      sql.append(unit.ovld());
      sql.append(',');
      sql.append(unit.uniqueId());
      sql.append(',');
      sql.append(unit.accountId());
      sql.append(',');
      sql.append(unit.statusCd());
      sql.append(',');
      if(unit.deleteTime().isEmpty()){
         sql.append("NULL");
      }else{
         sql.append("STR_TO_DATE('");
         sql.append(unit.deleteTime().format());
         sql.append("','%Y%m%d%H%i%s')");
      }
      sql.append(',');
      if(unit.onlineDatetime().isEmpty()){
         sql.append("NULL");
      }else{
         sql.append("STR_TO_DATE('");
         sql.append(unit.onlineDatetime().format());
         sql.append("','%Y%m%d%H%i%s')");
      }
      sql.append(',');
      if(unit.offlineDatetime().isEmpty()){
         sql.append("NULL");
      }else{
         sql.append("STR_TO_DATE('");
         sql.append(unit.offlineDatetime().format());
         sql.append("','%Y%m%d%H%i%s')");
      }
      sql.append(',');
      sql.append(unit.flags());
      sql.append(',');
      if(RString.isEmpty(unit.label())){
         sql.append("NULL");
      }else{
         sql.append('\'');
         sql.append(_connection.formatValue(unit.label()));
         sql.append('\'');
      }
      sql.append(',');
      sql.append(unit.timeTid());
      sql.append(',');
      sql.append(unit.roleTid());
      sql.append(',');
      sql.append(unit.raceTid());
      sql.append(',');
      sql.append(unit.genderTid());
      sql.append(',');
      sql.append(unit.metierTid());
      sql.append(',');
      sql.append(unit.matrixTid());
      sql.append(',');
      sql.append(unit.level());
      sql.append(',');
      if(RString.isEmpty(unit.hp())){
         sql.append("NULL");
      }else{
         sql.append('\'');
         sql.append(_connection.formatValue(unit.hp()));
         sql.append('\'');
      }
      sql.append(',');
      if(RString.isEmpty(unit.mp())){
         sql.append("NULL");
      }else{
         sql.append('\'');
         sql.append(_connection.formatValue(unit.mp()));
         sql.append('\'');
      }
      sql.append(',');
      if(RString.isEmpty(unit.lp())){
         sql.append("NULL");
      }else{
         sql.append('\'');
         sql.append(_connection.formatValue(unit.lp()));
         sql.append('\'');
      }
      sql.append(',');
      if(RString.isEmpty(unit.ep())){
         sql.append("NULL");
      }else{
         sql.append('\'');
         sql.append(_connection.formatValue(unit.ep()));
         sql.append('\'');
      }
      sql.append(',');
      if(RString.isEmpty(unit.ap())){
         sql.append("NULL");
      }else{
         sql.append('\'');
         sql.append(_connection.formatValue(unit.ap()));
         sql.append('\'');
      }
      sql.append(',');
      sql.append(unit.vitality());
      sql.append(',');
      sql.append(unit.strength());
      sql.append(',');
      sql.append(unit.stamina());
      sql.append(',');
      sql.append(unit.magic());
      sql.append(',');
      sql.append(unit.agility());
      sql.append(',');
      sql.append(unit.potencyPoint());
      sql.append(',');
      sql.append(unit.speed());
      sql.append(',');
      sql.append(unit.mobility());
      sql.append(',');
      sql.append(unit.block());
      sql.append(',');
      sql.append(unit.cri());
      sql.append(',');
      sql.append(unit.hit());
      sql.append(',');
      sql.append(unit.dodge());
      sql.append(',');
      sql.append(unit.attackPhysical());
      sql.append(',');
      sql.append(unit.attackPuncture());
      sql.append(',');
      sql.append(unit.attackMagic());
      sql.append(',');
      sql.append(unit.defencePhysical());
      sql.append(',');
      sql.append(unit.defencePuncture());
      sql.append(',');
      sql.append(unit.defenceMagic());
      sql.append(',');
      sql.append(unit.experience());
      sql.append(',');
      sql.append(unit.experienceup());
      sql.append(',');
      sql.append(unit.societyId());
      sql.append(',');
      if(RString.isEmpty(unit.contribute())){
         sql.append("NULL");
      }else{
         sql.append('\'');
         sql.append(_connection.formatValue(unit.contribute()));
         sql.append('\'');
      }
      sql.append(',');
      sql.append(unit.societyJob());
      sql.append(',');
      sql.append(unit.societyEliteMember());
      sql.append(',');
      if(unit.entranceTime().isEmpty()){
         sql.append("NULL");
      }else{
         sql.append("STR_TO_DATE('");
         sql.append(unit.entranceTime().format());
         sql.append("','%Y%m%d%H%i%s')");
      }
      sql.append(',');
      sql.append(unit.offlineLineTid());
      sql.append(',');
      sql.append(unit.offlineMapTid());
      sql.append(',');
      sql.append(unit.offlineMapId());
      sql.append(',');
      if(RString.isEmpty(unit.offlineLocation())){
         sql.append("NULL");
      }else{
         sql.append('\'');
         sql.append(_connection.formatValue(unit.offlineLocation()));
         sql.append('\'');
      }
      sql.append(',');
      sql.append(unit.offlineDirectionCd());
      sql.append(',');
      sql.append(unit.vipLevel());
      sql.append(',');
      sql.append(unit.bagPet());
      sql.append(',');
      sql.append(unit.depotPet());
      sql.append(',');
      sql.append(unit.bagRide());
      sql.append(',');
      sql.append(unit.surplusDoubleTime());
      sql.append(',');
      sql.append(unit.availableTime());
      sql.append(',');
      sql.append(unit.startDoubleTime());
      sql.append(',');
      sql.append(unit.skillId());
      sql.append(',');
      sql.append(unit.onhookFlags());
      sql.append(',');
      sql.append(unit.roleMinhpRate());
      sql.append(',');
      sql.append(unit.roleMinmpRate());
      sql.append(',');
      sql.append(unit.petId());
      sql.append(',');
      sql.append(unit.petSkillId());
      sql.append(',');
      if(RString.isEmpty(unit.crystal())){
         sql.append("NULL");
      }else{
         sql.append('\'');
         sql.append(_connection.formatValue(unit.crystal()));
         sql.append('\'');
      }
      sql.append(',');
      if(RString.isEmpty(unit.metierContribution())){
         sql.append("NULL");
      }else{
         sql.append('\'');
         sql.append(_connection.formatValue(unit.metierContribution()));
         sql.append('\'');
      }
      sql.append(',');
      sql.append(unit.realmLevel());
      sql.append(',');
      sql.append(unit.channelLevel());
      sql.append(',');
      sql.append(unit.heartLevel());
      sql.append(',');
      sql.append(unit.spiritLevel());
      sql.append(',');
      if(RString.isEmpty(unit.shopItemLimit())){
         sql.append("NULL");
      }else{
         sql.append('\'');
         sql.append(_connection.formatValue(unit.shopItemLimit()));
         sql.append('\'');
      }
      sql.append(',');
      sql.append(unit.totalOnlineTime());
      sql.append(',');
      sql.append(unit.refreshTaskEscort());
      sql.append(',');
      sql.append(unit.partEscort());
      sql.append(',');
      if(RString.isEmpty(unit.loginHost())){
         sql.append("NULL");
      }else{
         sql.append('\'');
         sql.append(_connection.formatValue(unit.loginHost()));
         sql.append('\'');
      }
      sql.append(',');
      if(unit.loginDate().isEmpty()){
         sql.append("NULL");
      }else{
         sql.append("STR_TO_DATE('");
         sql.append(unit.loginDate().format());
         sql.append("','%Y%m%d%H%i%s')");
      }
      sql.append(',');
      sql.append(unit.pkValue());
      sql.append(',');
      sql.append(unit.gradePoint());
      sql.append(',');
      sql.append(unit.guideFlag());
      sql.append(',');
      sql.append(unit.teamId());
      sql.append(',');
      if(RString.isEmpty(unit.activePoint())){
         sql.append("NULL");
      }else{
         sql.append('\'');
         sql.append(_connection.formatValue(unit.activePoint()));
         sql.append('\'');
      }
      sql.append(',');
      if(RString.isEmpty(unit.tournamentPoint())){
         sql.append("NULL");
      }else{
         sql.append('\'');
         sql.append(_connection.formatValue(unit.tournamentPoint()));
         sql.append('\'');
      }
      sql.append(',');
      if(RString.isEmpty(unit.societyWarPoint())){
         sql.append("NULL");
      }else{
         sql.append('\'');
         sql.append(_connection.formatValue(unit.societyWarPoint()));
         sql.append('\'');
      }
      sql.append(',');
      sql.append(unit.goldBind());
      sql.append(',');
      sql.append(unit.goldUnbind());
      sql.append(',');
      sql.append(unit.moneyBind());
      sql.append(',');
      sql.append(unit.moneyUnbind());
      sql.append(',');
      sql.append(unit.point());
      sql.append(',');
      sql.append(unit.depotGoldBind());
      sql.append(',');
      sql.append(unit.depotGoldUnbind());
      sql.append(',');
      sql.append(unit.depotMoneyBind());
      sql.append(',');
      sql.append(unit.depotMoneyUnbind());
      sql.append(',');
      sql.append(unit.depotPiont());
      sql.append(',');
      sql.append(unit.robberyCount());
      sql.append(',');
      sql.append(unit.lastLoginDays());
      sql.append(',');
      sql.append(unit.auctionCurrencyCount());
      sql.append(',');
      sql.append(unit.lastOfflineTime());
      sql.append(',');
      sql.append(unit.charmingValue());
      sql.append(',');
      sql.append(unit.killCount());
      sql.append(',');
      sql.append(unit.spendMoney());
      sql.append(',');
      sql.append(unit.vipGiftFlag());
      sql.append(',');
      sql.append(unit.vipFlag());
      sql.append(',');
      sql.append(unit.questionOne());
      sql.append(',');
      if(RString.isEmpty(unit.answerOne())){
         sql.append("NULL");
      }else{
         sql.append('\'');
         sql.append(_connection.formatValue(unit.answerOne()));
         sql.append('\'');
      }
      sql.append(',');
      sql.append(unit.questionTwo());
      sql.append(',');
      if(RString.isEmpty(unit.answerTwo())){
         sql.append("NULL");
      }else{
         sql.append('\'');
         sql.append(_connection.formatValue(unit.answerTwo()));
         sql.append('\'');
      }
      sql.append(',');
      if(RString.isEmpty(unit.codePassword())){
         sql.append("NULL");
      }else{
         sql.append('\'');
         sql.append(_connection.formatValue(unit.codePassword()));
         sql.append('\'');
      }
      sql.append(',');
      if(unit.storageLastTime().isEmpty()){
         sql.append("NULL");
      }else{
         sql.append("STR_TO_DATE('");
         sql.append(unit.storageLastTime().format());
         sql.append("','%Y%m%d%H%i%s')");
      }
      sql.append(',');
      if(unit.talkGanBegin().isEmpty()){
         sql.append("NULL");
      }else{
         sql.append("STR_TO_DATE('");
         sql.append(unit.talkGanBegin().format());
         sql.append("','%Y%m%d%H%i%s')");
      }
      sql.append(',');
      if(unit.talkGanEnd().isEmpty()){
         sql.append("NULL");
      }else{
         sql.append("STR_TO_DATE('");
         sql.append(unit.talkGanEnd().format());
         sql.append("','%Y%m%d%H%i%s')");
      }
      sql.append(',');
      sql.append(unit.roleFreeze());
      sql.append(',');
      sql.append(unit.shizhuangShow());
      sql.append(',');
      if(unit.magicsWordTime().isEmpty()){
         sql.append("NULL");
      }else{
         sql.append("STR_TO_DATE('");
         sql.append(unit.magicsWordTime().format());
         sql.append("','%Y%m%d%H%i%s')");
      }
      sql.append(',');
      sql.append(unit.surplusTime());
      sql.append(',');
      sql.append(unit.isFirstGet());
      sql.append(',');
      sql.append(unit.onlineToday());
      sql.append(',');
      sql.append(unit.serverFlag());
      sql.append(',');
      sql.append(unit.swordSoul());
      sql.append(',');
      sql.append(unit.masterChallengeLevel());
      sql.append(',');
      sql.append(unit.masterChallengeLevelMax());
      sql.append(',');
      if(unit.lastInjectSoulTime().isEmpty()){
         sql.append("NULL");
      }else{
         sql.append("STR_TO_DATE('");
         sql.append(unit.lastInjectSoulTime().format());
         sql.append("','%Y%m%d%H%i%s')");
      }
      sql.append(',');
      sql.append(unit.fourGodBloodGas());
      sql.append(',');
      if(unit.quickMasterChallengeTime().isEmpty()){
         sql.append("NULL");
      }else{
         sql.append("STR_TO_DATE('");
         sql.append(unit.quickMasterChallengeTime().format());
         sql.append("','%Y%m%d%H%i%s')");
      }
      sql.append(',');
      sql.append(unit.addOnhookCount());
      sql.append(',');
      sql.append(unit.metierArenaCdTime());
      sql.append(',');
      if(unit.metierArenaTime().isEmpty()){
         sql.append("NULL");
      }else{
         sql.append("STR_TO_DATE('");
         sql.append(unit.metierArenaTime().format());
         sql.append("','%Y%m%d%H%i%s')");
      }
      sql.append(',');
      sql.append(unit.dragonBallGather());
      sql.append(',');
      sql.append(unit.achievementPoint());
      sql.append(',');
      sql.append(unit.achievementLevel());
      sql.append(',');
      sql.append(unit.yellowReward());
      sql.append(',');
      sql.append(unit.yellowUpReward());
      sql.append(',');
      if(unit.yellowRewardTime().isEmpty()){
         sql.append("NULL");
      }else{
         sql.append("STR_TO_DATE('");
         sql.append(unit.yellowRewardTime().format());
         sql.append("','%Y%m%d%H%i%s')");
      }
      sql.append(',');
      sql.append(unit.loginDay());
      sql.append(',');
      sql.append(unit.loginGetItem());
      sql.append(',');
      sql.append(unit.allRechargeRewardMail());
      sql.append(',');
      sql.append(unit.roleDataId());
      sql.append(',');
      if(RString.isEmpty(unit.pfMailIds())){
         sql.append("NULL");
      }else{
         sql.append('\'');
         sql.append(_connection.formatValue(unit.pfMailIds()));
         sql.append('\'');
      }
      sql.append(',');
      sql.append(unit.marryId());
      sql.append(',');
      if(unit.quickCopyTime().isEmpty()){
         sql.append("NULL");
      }else{
         sql.append("STR_TO_DATE('");
         sql.append(unit.quickCopyTime().format());
         sql.append("','%Y%m%d%H%i%s')");
      }
      sql.append(',');
      sql.append(unit.quickCopyCd());
      sql.append(',');
      sql.append(unit.teamWarMark());
      sql.append(',');
      if(unit.lastMurderTime().isEmpty()){
         sql.append("NULL");
      }else{
         sql.append("STR_TO_DATE('");
         sql.append(unit.lastMurderTime().format());
         sql.append("','%Y%m%d%H%i%s')");
      }
      sql.append(",0,NOW(),0,NOW())");
      // 执行命令
      long recordId = _connection.executeInsertSql(sql.toString());
      unit.setOuid(recordId);
      return true;
   }

   //============================================================
   // <T>更新一个数据单元。</T>
   //
   // @param unit 数据单元
   // @param recordId 记录编号
   // @return 处理结果
   //============================================================
   public boolean doUpdate(FGameRoleUnit unit, long recordId){
      // 检查记录编号
      if(0 == recordId){
         throw new FFatalError("Record id is empty. (record_id={1})", recordId);
      }
      // 生成命令
      FSql sql = new FSql("UPDATE ");
      sql.append(Table.name());
      sql.append(" SET OVLD=");
      sql.append(unit.ovld());
      if(unit.isUniqueIdChanged()){
         sql.append(",`UNIQUE_ID`=");
         sql.append(unit.uniqueId());
      }
      if(unit.isAccountIdChanged()){
         sql.append(",`ACCOUNT_ID`=");
         sql.append(unit.accountId());
      }
      if(unit.isStatusCdChanged()){
         sql.append(",`STATUS_CD`=");
         sql.append(unit.statusCd());
      }
      if(unit.isDeleteTimeChanged()){
         sql.append(",`DELETE_TIME`=");
         if(unit.deleteTime().isEmpty()){
            sql.append("NULL");
         }else{
            sql.append("STR_TO_DATE('");
            sql.append(unit.deleteTime().format());
            sql.append("','%Y%m%d%H%i%s')");
         }
      }
      if(unit.isOnlineDatetimeChanged()){
         sql.append(",`ONLINE_DATETIME`=");
         if(unit.onlineDatetime().isEmpty()){
            sql.append("NULL");
         }else{
            sql.append("STR_TO_DATE('");
            sql.append(unit.onlineDatetime().format());
            sql.append("','%Y%m%d%H%i%s')");
         }
      }
      if(unit.isOfflineDatetimeChanged()){
         sql.append(",`OFFLINE_DATETIME`=");
         if(unit.offlineDatetime().isEmpty()){
            sql.append("NULL");
         }else{
            sql.append("STR_TO_DATE('");
            sql.append(unit.offlineDatetime().format());
            sql.append("','%Y%m%d%H%i%s')");
         }
      }
      if(unit.isFlagsChanged()){
         sql.append(",`FLAGS`=");
         sql.append(unit.flags());
      }
      if(unit.isLabelChanged()){
         sql.append(",`LABEL`=");
         if(RString.isEmpty(unit.label())){
            sql.append("NULL");
         }else{
            sql.append("'");
            sql.append(_connection.formatValue(unit.label()));
            sql.append("'");
         }
      }
      if(unit.isTimeTidChanged()){
         sql.append(",`TIME_TID`=");
         sql.append(unit.timeTid());
      }
      if(unit.isRoleTidChanged()){
         sql.append(",`ROLE_TID`=");
         sql.append(unit.roleTid());
      }
      if(unit.isRaceTidChanged()){
         sql.append(",`RACE_TID`=");
         sql.append(unit.raceTid());
      }
      if(unit.isGenderTidChanged()){
         sql.append(",`GENDER_TID`=");
         sql.append(unit.genderTid());
      }
      if(unit.isMetierTidChanged()){
         sql.append(",`METIER_TID`=");
         sql.append(unit.metierTid());
      }
      if(unit.isMatrixTidChanged()){
         sql.append(",`MATRIX_TID`=");
         sql.append(unit.matrixTid());
      }
      if(unit.isLevelChanged()){
         sql.append(",`LEVEL`=");
         sql.append(unit.level());
      }
      if(unit.isHpChanged()){
         sql.append(",`HP`=");
         if(RString.isEmpty(unit.hp())){
            sql.append("NULL");
         }else{
            sql.append("'");
            sql.append(_connection.formatValue(unit.hp()));
            sql.append("'");
         }
      }
      if(unit.isMpChanged()){
         sql.append(",`MP`=");
         if(RString.isEmpty(unit.mp())){
            sql.append("NULL");
         }else{
            sql.append("'");
            sql.append(_connection.formatValue(unit.mp()));
            sql.append("'");
         }
      }
      if(unit.isLpChanged()){
         sql.append(",`LP`=");
         if(RString.isEmpty(unit.lp())){
            sql.append("NULL");
         }else{
            sql.append("'");
            sql.append(_connection.formatValue(unit.lp()));
            sql.append("'");
         }
      }
      if(unit.isEpChanged()){
         sql.append(",`EP`=");
         if(RString.isEmpty(unit.ep())){
            sql.append("NULL");
         }else{
            sql.append("'");
            sql.append(_connection.formatValue(unit.ep()));
            sql.append("'");
         }
      }
      if(unit.isApChanged()){
         sql.append(",`AP`=");
         if(RString.isEmpty(unit.ap())){
            sql.append("NULL");
         }else{
            sql.append("'");
            sql.append(_connection.formatValue(unit.ap()));
            sql.append("'");
         }
      }
      if(unit.isVitalityChanged()){
         sql.append(",`VITALITY`=");
         sql.append(unit.vitality());
      }
      if(unit.isStrengthChanged()){
         sql.append(",`STRENGTH`=");
         sql.append(unit.strength());
      }
      if(unit.isStaminaChanged()){
         sql.append(",`STAMINA`=");
         sql.append(unit.stamina());
      }
      if(unit.isMagicChanged()){
         sql.append(",`MAGIC`=");
         sql.append(unit.magic());
      }
      if(unit.isAgilityChanged()){
         sql.append(",`AGILITY`=");
         sql.append(unit.agility());
      }
      if(unit.isPotencyPointChanged()){
         sql.append(",`POTENCY_POINT`=");
         sql.append(unit.potencyPoint());
      }
      if(unit.isSpeedChanged()){
         sql.append(",`SPEED`=");
         sql.append(unit.speed());
      }
      if(unit.isMobilityChanged()){
         sql.append(",`MOBILITY`=");
         sql.append(unit.mobility());
      }
      if(unit.isBlockChanged()){
         sql.append(",`BLOCK`=");
         sql.append(unit.block());
      }
      if(unit.isCriChanged()){
         sql.append(",`CRI`=");
         sql.append(unit.cri());
      }
      if(unit.isHitChanged()){
         sql.append(",`HIT`=");
         sql.append(unit.hit());
      }
      if(unit.isDodgeChanged()){
         sql.append(",`DODGE`=");
         sql.append(unit.dodge());
      }
      if(unit.isAttackPhysicalChanged()){
         sql.append(",`ATTACK_PHYSICAL`=");
         sql.append(unit.attackPhysical());
      }
      if(unit.isAttackPunctureChanged()){
         sql.append(",`ATTACK_PUNCTURE`=");
         sql.append(unit.attackPuncture());
      }
      if(unit.isAttackMagicChanged()){
         sql.append(",`ATTACK_MAGIC`=");
         sql.append(unit.attackMagic());
      }
      if(unit.isDefencePhysicalChanged()){
         sql.append(",`DEFENCE_PHYSICAL`=");
         sql.append(unit.defencePhysical());
      }
      if(unit.isDefencePunctureChanged()){
         sql.append(",`DEFENCE_PUNCTURE`=");
         sql.append(unit.defencePuncture());
      }
      if(unit.isDefenceMagicChanged()){
         sql.append(",`DEFENCE_MAGIC`=");
         sql.append(unit.defenceMagic());
      }
      if(unit.isExperienceChanged()){
         sql.append(",`EXPERIENCE`=");
         sql.append(unit.experience());
      }
      if(unit.isExperienceupChanged()){
         sql.append(",`EXPERIENCEUP`=");
         sql.append(unit.experienceup());
      }
      if(unit.isSocietyIdChanged()){
         sql.append(",`SOCIETY_ID`=");
         sql.append(unit.societyId());
      }
      if(unit.isContributeChanged()){
         sql.append(",`CONTRIBUTE`=");
         if(RString.isEmpty(unit.contribute())){
            sql.append("NULL");
         }else{
            sql.append("'");
            sql.append(_connection.formatValue(unit.contribute()));
            sql.append("'");
         }
      }
      if(unit.isSocietyJobChanged()){
         sql.append(",`SOCIETY_JOB`=");
         sql.append(unit.societyJob());
      }
      if(unit.isSocietyEliteMemberChanged()){
         sql.append(",`SOCIETY_ELITE_MEMBER`=");
         sql.append(unit.societyEliteMember());
      }
      if(unit.isEntranceTimeChanged()){
         sql.append(",`ENTRANCE_TIME`=");
         if(unit.entranceTime().isEmpty()){
            sql.append("NULL");
         }else{
            sql.append("STR_TO_DATE('");
            sql.append(unit.entranceTime().format());
            sql.append("','%Y%m%d%H%i%s')");
         }
      }
      if(unit.isOfflineLineTidChanged()){
         sql.append(",`OFFLINE_LINE_TID`=");
         sql.append(unit.offlineLineTid());
      }
      if(unit.isOfflineMapTidChanged()){
         sql.append(",`OFFLINE_MAP_TID`=");
         sql.append(unit.offlineMapTid());
      }
      if(unit.isOfflineMapIdChanged()){
         sql.append(",`OFFLINE_MAP_ID`=");
         sql.append(unit.offlineMapId());
      }
      if(unit.isOfflineLocationChanged()){
         sql.append(",`OFFLINE_LOCATION`=");
         if(RString.isEmpty(unit.offlineLocation())){
            sql.append("NULL");
         }else{
            sql.append("'");
            sql.append(_connection.formatValue(unit.offlineLocation()));
            sql.append("'");
         }
      }
      if(unit.isOfflineDirectionCdChanged()){
         sql.append(",`OFFLINE_DIRECTION_CD`=");
         sql.append(unit.offlineDirectionCd());
      }
      if(unit.isVipLevelChanged()){
         sql.append(",`VIP_LEVEL`=");
         sql.append(unit.vipLevel());
      }
      if(unit.isBagPetChanged()){
         sql.append(",`BAG_PET`=");
         sql.append(unit.bagPet());
      }
      if(unit.isDepotPetChanged()){
         sql.append(",`DEPOT_PET`=");
         sql.append(unit.depotPet());
      }
      if(unit.isBagRideChanged()){
         sql.append(",`BAG_RIDE`=");
         sql.append(unit.bagRide());
      }
      if(unit.isSurplusDoubleTimeChanged()){
         sql.append(",`SURPLUS_DOUBLE_TIME`=");
         sql.append(unit.surplusDoubleTime());
      }
      if(unit.isAvailableTimeChanged()){
         sql.append(",`AVAILABLE_TIME`=");
         sql.append(unit.availableTime());
      }
      if(unit.isStartDoubleTimeChanged()){
         sql.append(",`START_DOUBLE_TIME`=");
         sql.append(unit.startDoubleTime());
      }
      if(unit.isSkillIdChanged()){
         sql.append(",`SKILL_ID`=");
         sql.append(unit.skillId());
      }
      if(unit.isOnhookFlagsChanged()){
         sql.append(",`ONHOOK_FLAGS`=");
         sql.append(unit.onhookFlags());
      }
      if(unit.isRoleMinhpRateChanged()){
         sql.append(",`ROLE_MINHP_RATE`=");
         sql.append(unit.roleMinhpRate());
      }
      if(unit.isRoleMinmpRateChanged()){
         sql.append(",`ROLE_MINMP_RATE`=");
         sql.append(unit.roleMinmpRate());
      }
      if(unit.isPetIdChanged()){
         sql.append(",`PET_ID`=");
         sql.append(unit.petId());
      }
      if(unit.isPetSkillIdChanged()){
         sql.append(",`PET_SKILL_ID`=");
         sql.append(unit.petSkillId());
      }
      if(unit.isCrystalChanged()){
         sql.append(",`CRYSTAL`=");
         if(RString.isEmpty(unit.crystal())){
            sql.append("NULL");
         }else{
            sql.append("'");
            sql.append(_connection.formatValue(unit.crystal()));
            sql.append("'");
         }
      }
      if(unit.isMetierContributionChanged()){
         sql.append(",`METIER_CONTRIBUTION`=");
         if(RString.isEmpty(unit.metierContribution())){
            sql.append("NULL");
         }else{
            sql.append("'");
            sql.append(_connection.formatValue(unit.metierContribution()));
            sql.append("'");
         }
      }
      if(unit.isRealmLevelChanged()){
         sql.append(",`REALM_LEVEL`=");
         sql.append(unit.realmLevel());
      }
      if(unit.isChannelLevelChanged()){
         sql.append(",`CHANNEL_LEVEL`=");
         sql.append(unit.channelLevel());
      }
      if(unit.isHeartLevelChanged()){
         sql.append(",`HEART_LEVEL`=");
         sql.append(unit.heartLevel());
      }
      if(unit.isSpiritLevelChanged()){
         sql.append(",`SPIRIT_LEVEL`=");
         sql.append(unit.spiritLevel());
      }
      if(unit.isShopItemLimitChanged()){
         sql.append(",`SHOP_ITEM_LIMIT`=");
         if(RString.isEmpty(unit.shopItemLimit())){
            sql.append("NULL");
         }else{
            sql.append("'");
            sql.append(_connection.formatValue(unit.shopItemLimit()));
            sql.append("'");
         }
      }
      if(unit.isTotalOnlineTimeChanged()){
         sql.append(",`TOTAL_ONLINE_TIME`=");
         sql.append(unit.totalOnlineTime());
      }
      if(unit.isRefreshTaskEscortChanged()){
         sql.append(",`REFRESH_TASK_ESCORT`=");
         sql.append(unit.refreshTaskEscort());
      }
      if(unit.isPartEscortChanged()){
         sql.append(",`PART_ESCORT`=");
         sql.append(unit.partEscort());
      }
      if(unit.isLoginHostChanged()){
         sql.append(",`LOGIN_HOST`=");
         if(RString.isEmpty(unit.loginHost())){
            sql.append("NULL");
         }else{
            sql.append("'");
            sql.append(_connection.formatValue(unit.loginHost()));
            sql.append("'");
         }
      }
      if(unit.isLoginDateChanged()){
         sql.append(",`LOGIN_DATE`=");
         if(unit.loginDate().isEmpty()){
            sql.append("NULL");
         }else{
            sql.append("STR_TO_DATE('");
            sql.append(unit.loginDate().format());
            sql.append("','%Y%m%d%H%i%s')");
         }
      }
      if(unit.isPkValueChanged()){
         sql.append(",`PK_VALUE`=");
         sql.append(unit.pkValue());
      }
      if(unit.isGradePointChanged()){
         sql.append(",`GRADE_POINT`=");
         sql.append(unit.gradePoint());
      }
      if(unit.isGuideFlagChanged()){
         sql.append(",`GUIDE_FLAG`=");
         sql.append(unit.guideFlag());
      }
      if(unit.isTeamIdChanged()){
         sql.append(",`TEAM_ID`=");
         sql.append(unit.teamId());
      }
      if(unit.isActivePointChanged()){
         sql.append(",`ACTIVE_POINT`=");
         if(RString.isEmpty(unit.activePoint())){
            sql.append("NULL");
         }else{
            sql.append("'");
            sql.append(_connection.formatValue(unit.activePoint()));
            sql.append("'");
         }
      }
      if(unit.isTournamentPointChanged()){
         sql.append(",`TOURNAMENT_POINT`=");
         if(RString.isEmpty(unit.tournamentPoint())){
            sql.append("NULL");
         }else{
            sql.append("'");
            sql.append(_connection.formatValue(unit.tournamentPoint()));
            sql.append("'");
         }
      }
      if(unit.isSocietyWarPointChanged()){
         sql.append(",`SOCIETY_WAR_POINT`=");
         if(RString.isEmpty(unit.societyWarPoint())){
            sql.append("NULL");
         }else{
            sql.append("'");
            sql.append(_connection.formatValue(unit.societyWarPoint()));
            sql.append("'");
         }
      }
      if(unit.isGoldBindChanged()){
         sql.append(",`GOLD_BIND`=");
         sql.append(unit.goldBind());
      }
      if(unit.isGoldUnbindChanged()){
         sql.append(",`GOLD_UNBIND`=");
         sql.append(unit.goldUnbind());
      }
      if(unit.isMoneyBindChanged()){
         sql.append(",`MONEY_BIND`=");
         sql.append(unit.moneyBind());
      }
      if(unit.isMoneyUnbindChanged()){
         sql.append(",`MONEY_UNBIND`=");
         sql.append(unit.moneyUnbind());
      }
      if(unit.isPointChanged()){
         sql.append(",`POINT`=");
         sql.append(unit.point());
      }
      if(unit.isDepotGoldBindChanged()){
         sql.append(",`DEPOT_GOLD_BIND`=");
         sql.append(unit.depotGoldBind());
      }
      if(unit.isDepotGoldUnbindChanged()){
         sql.append(",`DEPOT_GOLD_UNBIND`=");
         sql.append(unit.depotGoldUnbind());
      }
      if(unit.isDepotMoneyBindChanged()){
         sql.append(",`DEPOT_MONEY_BIND`=");
         sql.append(unit.depotMoneyBind());
      }
      if(unit.isDepotMoneyUnbindChanged()){
         sql.append(",`DEPOT_MONEY_UNBIND`=");
         sql.append(unit.depotMoneyUnbind());
      }
      if(unit.isDepotPiontChanged()){
         sql.append(",`DEPOT_PIONT`=");
         sql.append(unit.depotPiont());
      }
      if(unit.isRobberyCountChanged()){
         sql.append(",`ROBBERY_COUNT`=");
         sql.append(unit.robberyCount());
      }
      if(unit.isLastLoginDaysChanged()){
         sql.append(",`LAST_LOGIN_DAYS`=");
         sql.append(unit.lastLoginDays());
      }
      if(unit.isAuctionCurrencyCountChanged()){
         sql.append(",`AUCTION_CURRENCY_COUNT`=");
         sql.append(unit.auctionCurrencyCount());
      }
      if(unit.isLastOfflineTimeChanged()){
         sql.append(",`LAST_OFFLINE_TIME`=");
         sql.append(unit.lastOfflineTime());
      }
      if(unit.isCharmingValueChanged()){
         sql.append(",`CHARMING_VALUE`=");
         sql.append(unit.charmingValue());
      }
      if(unit.isKillCountChanged()){
         sql.append(",`KILL_COUNT`=");
         sql.append(unit.killCount());
      }
      if(unit.isSpendMoneyChanged()){
         sql.append(",`SPEND_MONEY`=");
         sql.append(unit.spendMoney());
      }
      if(unit.isVipGiftFlagChanged()){
         sql.append(",`VIP_GIFT_FLAG`=");
         sql.append(unit.vipGiftFlag());
      }
      if(unit.isVipFlagChanged()){
         sql.append(",`VIP_FLAG`=");
         sql.append(unit.vipFlag());
      }
      if(unit.isQuestionOneChanged()){
         sql.append(",`QUESTION_ONE`=");
         sql.append(unit.questionOne());
      }
      if(unit.isAnswerOneChanged()){
         sql.append(",`ANSWER_ONE`=");
         if(RString.isEmpty(unit.answerOne())){
            sql.append("NULL");
         }else{
            sql.append("'");
            sql.append(_connection.formatValue(unit.answerOne()));
            sql.append("'");
         }
      }
      if(unit.isQuestionTwoChanged()){
         sql.append(",`QUESTION_TWO`=");
         sql.append(unit.questionTwo());
      }
      if(unit.isAnswerTwoChanged()){
         sql.append(",`ANSWER_TWO`=");
         if(RString.isEmpty(unit.answerTwo())){
            sql.append("NULL");
         }else{
            sql.append("'");
            sql.append(_connection.formatValue(unit.answerTwo()));
            sql.append("'");
         }
      }
      if(unit.isCodePasswordChanged()){
         sql.append(",`CODE_PASSWORD`=");
         if(RString.isEmpty(unit.codePassword())){
            sql.append("NULL");
         }else{
            sql.append("'");
            sql.append(_connection.formatValue(unit.codePassword()));
            sql.append("'");
         }
      }
      if(unit.isStorageLastTimeChanged()){
         sql.append(",`STORAGE_LAST_TIME`=");
         if(unit.storageLastTime().isEmpty()){
            sql.append("NULL");
         }else{
            sql.append("STR_TO_DATE('");
            sql.append(unit.storageLastTime().format());
            sql.append("','%Y%m%d%H%i%s')");
         }
      }
      if(unit.isTalkGanBeginChanged()){
         sql.append(",`TALK_GAN_BEGIN`=");
         if(unit.talkGanBegin().isEmpty()){
            sql.append("NULL");
         }else{
            sql.append("STR_TO_DATE('");
            sql.append(unit.talkGanBegin().format());
            sql.append("','%Y%m%d%H%i%s')");
         }
      }
      if(unit.isTalkGanEndChanged()){
         sql.append(",`TALK_GAN_END`=");
         if(unit.talkGanEnd().isEmpty()){
            sql.append("NULL");
         }else{
            sql.append("STR_TO_DATE('");
            sql.append(unit.talkGanEnd().format());
            sql.append("','%Y%m%d%H%i%s')");
         }
      }
      if(unit.isRoleFreezeChanged()){
         sql.append(",`ROLE_FREEZE`=");
         sql.append(unit.roleFreeze());
      }
      if(unit.isShizhuangShowChanged()){
         sql.append(",`SHIZHUANG_SHOW`=");
         sql.append(unit.shizhuangShow());
      }
      if(unit.isMagicsWordTimeChanged()){
         sql.append(",`MAGICS_WORD_TIME`=");
         if(unit.magicsWordTime().isEmpty()){
            sql.append("NULL");
         }else{
            sql.append("STR_TO_DATE('");
            sql.append(unit.magicsWordTime().format());
            sql.append("','%Y%m%d%H%i%s')");
         }
      }
      if(unit.isSurplusTimeChanged()){
         sql.append(",`SURPLUS_TIME`=");
         sql.append(unit.surplusTime());
      }
      if(unit.isIsFirstGetChanged()){
         sql.append(",`IS_FIRST_GET`=");
         sql.append(unit.isFirstGet());
      }
      if(unit.isOnlineTodayChanged()){
         sql.append(",`ONLINE_TODAY`=");
         sql.append(unit.onlineToday());
      }
      if(unit.isServerFlagChanged()){
         sql.append(",`SERVER_FLAG`=");
         sql.append(unit.serverFlag());
      }
      if(unit.isSwordSoulChanged()){
         sql.append(",`SWORD_SOUL`=");
         sql.append(unit.swordSoul());
      }
      if(unit.isMasterChallengeLevelChanged()){
         sql.append(",`MASTER_CHALLENGE_LEVEL`=");
         sql.append(unit.masterChallengeLevel());
      }
      if(unit.isMasterChallengeLevelMaxChanged()){
         sql.append(",`MASTER_CHALLENGE_LEVEL_MAX`=");
         sql.append(unit.masterChallengeLevelMax());
      }
      if(unit.isLastInjectSoulTimeChanged()){
         sql.append(",`LAST_INJECT_SOUL_TIME`=");
         if(unit.lastInjectSoulTime().isEmpty()){
            sql.append("NULL");
         }else{
            sql.append("STR_TO_DATE('");
            sql.append(unit.lastInjectSoulTime().format());
            sql.append("','%Y%m%d%H%i%s')");
         }
      }
      if(unit.isFourGodBloodGasChanged()){
         sql.append(",`FOUR_GOD_BLOOD_GAS`=");
         sql.append(unit.fourGodBloodGas());
      }
      if(unit.isQuickMasterChallengeTimeChanged()){
         sql.append(",`QUICK_MASTER_CHALLENGE_TIME`=");
         if(unit.quickMasterChallengeTime().isEmpty()){
            sql.append("NULL");
         }else{
            sql.append("STR_TO_DATE('");
            sql.append(unit.quickMasterChallengeTime().format());
            sql.append("','%Y%m%d%H%i%s')");
         }
      }
      if(unit.isAddOnhookCountChanged()){
         sql.append(",`ADD_ONHOOK_COUNT`=");
         sql.append(unit.addOnhookCount());
      }
      if(unit.isMetierArenaCdTimeChanged()){
         sql.append(",`METIER_ARENA_CD_TIME`=");
         sql.append(unit.metierArenaCdTime());
      }
      if(unit.isMetierArenaTimeChanged()){
         sql.append(",`METIER_ARENA_TIME`=");
         if(unit.metierArenaTime().isEmpty()){
            sql.append("NULL");
         }else{
            sql.append("STR_TO_DATE('");
            sql.append(unit.metierArenaTime().format());
            sql.append("','%Y%m%d%H%i%s')");
         }
      }
      if(unit.isDragonBallGatherChanged()){
         sql.append(",`DRAGON_BALL_GATHER`=");
         sql.append(unit.dragonBallGather());
      }
      if(unit.isAchievementPointChanged()){
         sql.append(",`ACHIEVEMENT_POINT`=");
         sql.append(unit.achievementPoint());
      }
      if(unit.isAchievementLevelChanged()){
         sql.append(",`ACHIEVEMENT_LEVEL`=");
         sql.append(unit.achievementLevel());
      }
      if(unit.isYellowRewardChanged()){
         sql.append(",`YELLOW_REWARD`=");
         sql.append(unit.yellowReward());
      }
      if(unit.isYellowUpRewardChanged()){
         sql.append(",`YELLOW_UP_REWARD`=");
         sql.append(unit.yellowUpReward());
      }
      if(unit.isYellowRewardTimeChanged()){
         sql.append(",`YELLOW_REWARD_TIME`=");
         if(unit.yellowRewardTime().isEmpty()){
            sql.append("NULL");
         }else{
            sql.append("STR_TO_DATE('");
            sql.append(unit.yellowRewardTime().format());
            sql.append("','%Y%m%d%H%i%s')");
         }
      }
      if(unit.isLoginDayChanged()){
         sql.append(",`LOGIN_DAY`=");
         sql.append(unit.loginDay());
      }
      if(unit.isLoginGetItemChanged()){
         sql.append(",`LOGIN_GET_ITEM`=");
         sql.append(unit.loginGetItem());
      }
      if(unit.isAllRechargeRewardMailChanged()){
         sql.append(",`ALL_RECHARGE_REWARD_MAIL`=");
         sql.append(unit.allRechargeRewardMail());
      }
      if(unit.isRoleDataIdChanged()){
         sql.append(",`ROLE_DATA_ID`=");
         sql.append(unit.roleDataId());
      }
      if(unit.isPfMailIdsChanged()){
         sql.append(",`PF_MAIL_IDS`=");
         if(RString.isEmpty(unit.pfMailIds())){
            sql.append("NULL");
         }else{
            sql.append("'");
            sql.append(_connection.formatValue(unit.pfMailIds()));
            sql.append("'");
         }
      }
      if(unit.isMarryIdChanged()){
         sql.append(",`MARRY_ID`=");
         sql.append(unit.marryId());
      }
      if(unit.isQuickCopyTimeChanged()){
         sql.append(",`QUICK_COPY_TIME`=");
         if(unit.quickCopyTime().isEmpty()){
            sql.append("NULL");
         }else{
            sql.append("STR_TO_DATE('");
            sql.append(unit.quickCopyTime().format());
            sql.append("','%Y%m%d%H%i%s')");
         }
      }
      if(unit.isQuickCopyCdChanged()){
         sql.append(",`QUICK_COPY_CD`=");
         sql.append(unit.quickCopyCd());
      }
      if(unit.isTeamWarMarkChanged()){
         sql.append(",`TEAM_WAR_MARK`=");
         sql.append(unit.teamWarMark());
      }
      if(unit.isLastMurderTimeChanged()){
         sql.append(",`LAST_MURDER_TIME`=");
         if(unit.lastMurderTime().isEmpty()){
            sql.append("NULL");
         }else{
            sql.append("STR_TO_DATE('");
            sql.append(unit.lastMurderTime().format());
            sql.append("','%Y%m%d%H%i%s')");
         }
      }
      if(unit.isCreateUserIdChanged()){
         sql.append(",`CREATE_USER_ID`=");
         sql.append(unit.createUserId());
      }
      sql.append(",UPDATE_USER_ID=0,UPDATE_DATE=NOW()");
      sql.append(" WHERE OUID=");
      sql.append(recordId);
      // 执行命令
      return _connection.executeSql(sql.toString());
   }

   //============================================================
   // <T>删除一个数据单元。</T>
   //
   // @param recordId 记录编号
   // @return 处理结果
   //============================================================
   public boolean doDelete(long recordId){
      // 生成命令
      FSql sql = new FSql("DELETE FROM ");
      sql.append(Table.name());
      sql.append("WHERE OUID=");
      sql.append(recordId);
      // 执行命令
      return _connection.executeSql(sql.toString());
   }
}