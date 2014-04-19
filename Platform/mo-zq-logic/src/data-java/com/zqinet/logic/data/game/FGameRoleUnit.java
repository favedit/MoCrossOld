package com.zqinet.logic.data.game;

import org.mo.com.lang.*;
import org.mo.com.collections.*;
import org.mo.com.lang.type.*;
import org.mo.data.logic.*;

//============================================================
// <T>角色信息逻辑单元。</T>
//============================================================
public class FGameRoleUnit extends FLogicUnit
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

   // 存储字段账户编号的定义。
   private long __accountId;

   // 字段账户编号的定义。
   protected long _accountId;

   // 存储字段状态的定义。
   private int __statusCd;

   // 字段状态的定义。
   protected int _statusCd;

   // 存储字段删除时间的定义。
   private TDateTime __deleteTime = new TDateTime();

   // 字段删除时间的定义。
   protected TDateTime _deleteTime = new TDateTime();

   // 存储字段上线日期时间的定义。
   private TDateTime __onlineDatetime = new TDateTime();

   // 字段上线日期时间的定义。
   protected TDateTime _onlineDatetime = new TDateTime();

   // 存储字段下线日期时间的定义。
   private TDateTime __offlineDatetime = new TDateTime();

   // 字段下线日期时间的定义。
   protected TDateTime _offlineDatetime = new TDateTime();

   // 存储字段标志的定义。
   private int __flags;

   // 字段标志的定义。
   protected int _flags;

   // 存储字段中文名称的定义。
   private String __label;

   // 字段中文名称的定义。
   protected String _label;

   // 存储字段时代模版编号的定义。
   private int __timeTid;

   // 字段时代模版编号的定义。
   protected int _timeTid;

   // 存储字段角色模板编号的定义。
   private int __roleTid;

   // 字段角色模板编号的定义。
   protected int _roleTid;

   // 存储字段种族模板编号的定义。
   private int __raceTid;

   // 字段种族模板编号的定义。
   protected int _raceTid;

   // 存储字段性别模板编号的定义。
   private int __genderTid;

   // 字段性别模板编号的定义。
   protected int _genderTid;

   // 存储字段职业模板编号的定义。
   private int __metierTid;

   // 字段职业模板编号的定义。
   protected int _metierTid;

   // 存储字段阵法模板编号的定义。
   private int __matrixTid;

   // 字段阵法模板编号的定义。
   protected int _matrixTid;

   // 存储字段级别的定义。
   private int __level;

   // 字段级别的定义。
   protected int _level;

   // 存储字段生命值的定义。
   private String __hp;

   // 字段生命值的定义。
   protected String _hp;

   // 存储字段魔法值的定义。
   private String __mp;

   // 字段魔法值的定义。
   protected String _mp;

   // 存储字段活力值的定义。
   private String __lp;

   // 字段活力值的定义。
   protected String _lp;

   // 存储字段精力值的定义。
   private String __ep;

   // 字段精力值的定义。
   protected String _ep;

   // 存储字段怒气值的定义。
   private String __ap;

   // 字段怒气值的定义。
   protected String _ap;

   // 存储字段体质的定义。
   private int __vitality;

   // 字段体质的定义。
   protected int _vitality;

   // 存储字段力量的定义。
   private int __strength;

   // 字段力量的定义。
   protected int _strength;

   // 存储字段耐力的定义。
   private int __stamina;

   // 字段耐力的定义。
   protected int _stamina;

   // 存储字段魔力的定义。
   private int __magic;

   // 字段魔力的定义。
   protected int _magic;

   // 存储字段敏捷的定义。
   private int __agility;

   // 字段敏捷的定义。
   protected int _agility;

   // 存储字段潜力点的定义。
   private int __potencyPoint;

   // 字段潜力点的定义。
   protected int _potencyPoint;

   // 存储字段速度的定义。
   private int __speed;

   // 字段速度的定义。
   protected int _speed;

   // 存储字段行动值的定义。
   private int __mobility;

   // 字段行动值的定义。
   protected int _mobility;

   // 存储字段格挡值的定义。
   private int __block;

   // 字段格挡值的定义。
   protected int _block;

   // 存储字段暴击值的定义。
   private int __cri;

   // 字段暴击值的定义。
   protected int _cri;

   // 存储字段命中值的定义。
   private int __hit;

   // 字段命中值的定义。
   protected int _hit;

   // 存储字段闪避值的定义。
   private int __dodge;

   // 字段闪避值的定义。
   protected int _dodge;

   // 存储字段物理攻击力的定义。
   private int __attackPhysical;

   // 字段物理攻击力的定义。
   protected int _attackPhysical;

   // 存储字段穿刺攻击力的定义。
   private int __attackPuncture;

   // 字段穿刺攻击力的定义。
   protected int _attackPuncture;

   // 存储字段魔法攻击力的定义。
   private int __attackMagic;

   // 字段魔法攻击力的定义。
   protected int _attackMagic;

   // 存储字段物理防御力的定义。
   private int __defencePhysical;

   // 字段物理防御力的定义。
   protected int _defencePhysical;

   // 存储字段穿刺防御力的定义。
   private int __defencePuncture;

   // 字段穿刺防御力的定义。
   protected int _defencePuncture;

   // 存储字段魔法防御力的定义。
   private int __defenceMagic;

   // 字段魔法防御力的定义。
   protected int _defenceMagic;

   // 存储字段经验的定义。
   private int __experience;

   // 字段经验的定义。
   protected int _experience;

   // 存储字段升级所需经验的定义。
   private int __experienceup;

   // 字段升级所需经验的定义。
   protected int _experienceup;

   // 存储字段帮会编号的定义。
   private long __societyId;

   // 字段帮会编号的定义。
   protected long _societyId;

   // 存储字段帮会贡献的定义。
   private String __contribute;

   // 字段帮会贡献的定义。
   protected String _contribute;

   // 存储字段帮会职位的定义。
   private int __societyJob;

   // 字段帮会职位的定义。
   protected int _societyJob;

   // 存储字段帮会的精英成员的定义。
   private int __societyEliteMember;

   // 字段帮会的精英成员的定义。
   protected int _societyEliteMember;

   // 存储字段进帮时间的定义。
   private TDateTime __entranceTime = new TDateTime();

   // 字段进帮时间的定义。
   protected TDateTime _entranceTime = new TDateTime();

   // 存储字段下线场景线模板编号的定义。
   private int __offlineLineTid;

   // 字段下线场景线模板编号的定义。
   protected int _offlineLineTid;

   // 存储字段下线地图模板编号的定义。
   private int __offlineMapTid;

   // 字段下线地图模板编号的定义。
   protected int _offlineMapTid;

   // 存储字段副本中下线的方向的定义。
   private int __offlineMapId;

   // 字段副本中下线的方向的定义。
   protected int _offlineMapId;

   // 存储字段下线坐标的定义。
   private String __offlineLocation;

   // 字段下线坐标的定义。
   protected String _offlineLocation;

   // 存储字段下线方向的定义。
   private int __offlineDirectionCd;

   // 字段下线方向的定义。
   protected int _offlineDirectionCd;

   // 存储字段会员等级的定义。
   private int __vipLevel;

   // 字段会员等级的定义。
   protected int _vipLevel;

   // 存储字段宠物背包的定义。
   private int __bagPet;

   // 字段宠物背包的定义。
   protected int _bagPet;

   // 存储字段宠物仓库的定义。
   private int __depotPet;

   // 字段宠物仓库的定义。
   protected int _depotPet;

   // 存储字段坐骑背包的定义。
   private int __bagRide;

   // 字段坐骑背包的定义。
   protected int _bagRide;

   // 存储字段剩余双倍经验时间的定义。
   private int __surplusDoubleTime;

   // 字段剩余双倍经验时间的定义。
   protected int _surplusDoubleTime;

   // 存储字段可用双倍经验时间的定义。
   private int __availableTime;

   // 字段可用双倍经验时间的定义。
   protected int _availableTime;

   // 存储字段双倍经验开始时间的定义。
   private int __startDoubleTime;

   // 字段双倍经验开始时间的定义。
   protected int _startDoubleTime;

   // 存储字段自动战斗中使用的技能编号的定义。
   private long __skillId;

   // 字段自动战斗中使用的技能编号的定义。
   protected long _skillId;

   // 存储字段挂机标志的定义。
   private int __onhookFlags;

   // 字段挂机标志的定义。
   protected int _onhookFlags;

   // 存储字段角色最小生命比例的定义。
   private int __roleMinhpRate;

   // 字段角色最小生命比例的定义。
   protected int _roleMinhpRate;

   // 存储字段角色最小魔法比例的定义。
   private int __roleMinmpRate;

   // 字段角色最小魔法比例的定义。
   protected int _roleMinmpRate;

   // 存储字段宠物编号的定义。
   private long __petId;

   // 字段宠物编号的定义。
   protected long _petId;

   // 存储字段自动战斗中宠物使用的技能编号的定义。
   private long __petSkillId;

   // 字段自动战斗中宠物使用的技能编号的定义。
   protected long _petSkillId;

   // 存储字段真元的定义。
   private String __crystal;

   // 字段真元的定义。
   protected String _crystal;

   // 存储字段门派贡献的定义。
   private String __metierContribution;

   // 字段门派贡献的定义。
   protected String _metierContribution;

   // 存储字段境界等级的定义。
   private int __realmLevel;

   // 字段境界等级的定义。
   protected int _realmLevel;

   // 存储字段经脉等级的定义。
   private int __channelLevel;

   // 字段经脉等级的定义。
   protected int _channelLevel;

   // 存储字段炼心等级的定义。
   private int __heartLevel;

   // 字段炼心等级的定义。
   protected int _heartLevel;

   // 存储字段融神等级的定义。
   private int __spiritLevel;

   // 字段融神等级的定义。
   protected int _spiritLevel;

   // 存储字段商城物品购买限制的定义。
   private String __shopItemLimit;

   // 字段商城物品购买限制的定义。
   protected String _shopItemLimit;

   // 存储字段累计在线时长的定义。
   private int __totalOnlineTime;

   // 字段累计在线时长的定义。
   protected int _totalOnlineTime;

   // 存储字段押镖刷新次数的定义。
   private int __refreshTaskEscort;

   // 字段押镖刷新次数的定义。
   protected int _refreshTaskEscort;

   // 存储字段押镖任务部分的定义。
   private int __partEscort;

   // 字段押镖任务部分的定义。
   protected int _partEscort;

   // 存储字段登录角色网络地址的定义。
   private String __loginHost;

   // 字段登录角色网络地址的定义。
   protected String _loginHost;

   // 存储字段登录时间的定义。
   private TDateTime __loginDate = new TDateTime();

   // 字段登录时间的定义。
   protected TDateTime _loginDate = new TDateTime();

   // 存储字段pk值的定义。
   private int __pkValue;

   // 字段pk值的定义。
   protected int _pkValue;

   // 存储字段人物战力的定义。
   private int __gradePoint;

   // 字段人物战力的定义。
   protected int _gradePoint;

   // 存储字段引导标志的定义。
   private int __guideFlag;

   // 字段引导标志的定义。
   protected int _guideFlag;

   // 存储字段队伍ID的定义。
   private int __teamId;

   // 字段队伍ID的定义。
   protected int _teamId;

   // 存储字段活跃度点数的定义。
   private String __activePoint;

   // 字段活跃度点数的定义。
   protected String _activePoint;

   // 存储字段竞技场点数的定义。
   private String __tournamentPoint;

   // 字段竞技场点数的定义。
   protected String _tournamentPoint;

   // 存储字段帮会战点数的定义。
   private String __societyWarPoint;

   // 字段帮会战点数的定义。
   protected String _societyWarPoint;

   // 存储字段绑定金的定义。
   private int __goldBind;

   // 字段绑定金的定义。
   protected int _goldBind;

   // 存储字段非绑定金币的定义。
   private int __goldUnbind;

   // 字段非绑定金币的定义。
   protected int _goldUnbind;

   // 存储字段绑定元宝的定义。
   private int __moneyBind;

   // 字段绑定元宝的定义。
   protected int _moneyBind;

   // 存储字段元宝的定义。
   private int __moneyUnbind;

   // 字段元宝的定义。
   protected int _moneyUnbind;

   // 存储字段赠点的定义。
   private int __point;

   // 字段赠点的定义。
   protected int _point;

   // 存储字段仓库绑定金币的定义。
   private int __depotGoldBind;

   // 字段仓库绑定金币的定义。
   protected int _depotGoldBind;

   // 存储字段仓库非绑定金币的定义。
   private int __depotGoldUnbind;

   // 字段仓库非绑定金币的定义。
   protected int _depotGoldUnbind;

   // 存储字段仓库绑定元宝的定义。
   private int __depotMoneyBind;

   // 字段仓库绑定元宝的定义。
   protected int _depotMoneyBind;

   // 存储字段仓库元宝的定义。
   private int __depotMoneyUnbind;

   // 字段仓库元宝的定义。
   protected int _depotMoneyUnbind;

   // 存储字段仓库赠点的定义。
   private int __depotPiont;

   // 字段仓库赠点的定义。
   protected int _depotPiont;

   // 存储字段劫镖次数的定义。
   private int __robberyCount;

   // 字段劫镖次数的定义。
   protected int _robberyCount;

   // 存储字段连续登录天数的定义。
   private int __lastLoginDays;

   // 字段连续登录天数的定义。
   protected int _lastLoginDays;

   // 存储字段交易行出售金钱数量的定义。
   private int __auctionCurrencyCount;

   // 字段交易行出售金钱数量的定义。
   protected int _auctionCurrencyCount;

   // 存储字段离线时间的定义。
   private int __lastOfflineTime;

   // 字段离线时间的定义。
   protected int _lastOfflineTime;

   // 存储字段魅力值的定义。
   private int __charmingValue;

   // 字段魅力值的定义。
   protected int _charmingValue;

   // 存储字段杀人数的定义。
   private int __killCount;

   // 字段杀人数的定义。
   protected int _killCount;

   // 存储字段消耗元宝数量的定义。
   private int __spendMoney;

   // 字段消耗元宝数量的定义。
   protected int _spendMoney;

   // 存储字段会员礼包领取标志的定义。
   private int __vipGiftFlag;

   // 字段会员礼包领取标志的定义。
   protected int _vipGiftFlag;

   // 存储字段会员标志的定义。
   private int __vipFlag;

   // 字段会员标志的定义。
   protected int _vipFlag;

   // 存储字段二级密码问题一的定义。
   private int __questionOne;

   // 字段二级密码问题一的定义。
   protected int _questionOne;

   // 存储字段二级密码回答一的定义。
   private String __answerOne;

   // 字段二级密码回答一的定义。
   protected String _answerOne;

   // 存储字段二级密码问题二的定义。
   private int __questionTwo;

   // 字段二级密码问题二的定义。
   protected int _questionTwo;

   // 存储字段二级密码回答二的定义。
   private String __answerTwo;

   // 字段二级密码回答二的定义。
   protected String _answerTwo;

   // 存储字段二级密码的定义。
   private String __codePassword;

   // 字段二级密码的定义。
   protected String _codePassword;

   // 存储字段上次保存时间的定义。
   private TDateTime __storageLastTime = new TDateTime();

   // 字段上次保存时间的定义。
   protected TDateTime _storageLastTime = new TDateTime();

   // 存储字段禁言开始的定义。
   private TDateTime __talkGanBegin = new TDateTime();

   // 字段禁言开始的定义。
   protected TDateTime _talkGanBegin = new TDateTime();

   // 存储字段禁言结束的定义。
   private TDateTime __talkGanEnd = new TDateTime();

   // 字段禁言结束的定义。
   protected TDateTime _talkGanEnd = new TDateTime();

   // 存储字段角色冻结的定义。
   private int __roleFreeze;

   // 字段角色冻结的定义。
   protected int _roleFreeze;

   // 存储字段时装展示的定义。
   private int __shizhuangShow;

   // 字段时装展示的定义。
   protected int _shizhuangShow;

   // 存储字段神剑开始时间的定义。
   private TDateTime __magicsWordTime = new TDateTime();

   // 字段神剑开始时间的定义。
   protected TDateTime _magicsWordTime = new TDateTime();

   // 存储字段神剑剩余时间的定义。
   private int __surplusTime;

   // 字段神剑剩余时间的定义。
   protected int _surplusTime;

   // 存储字段是否领取过首冲的定义。
   private int __isFirstGet;

   // 字段是否领取过首冲的定义。
   protected int _isFirstGet;

   // 存储字段今日在线时长的定义。
   private int __onlineToday;

   // 字段今日在线时长的定义。
   protected int _onlineToday;

   // 存储字段服务器更新标记的定义。
   private int __serverFlag;

   // 字段服务器更新标记的定义。
   protected int _serverFlag;

   // 存储字段剑魂的定义。
   private int __swordSoul;

   // 字段剑魂的定义。
   protected int _swordSoul;

   // 存储字段师尊挑战等级的定义。
   private int __masterChallengeLevel;

   // 字段师尊挑战等级的定义。
   protected int _masterChallengeLevel;

   // 存储字段师尊挑战最大等级的定义。
   private int __masterChallengeLevelMax;

   // 字段师尊挑战最大等级的定义。
   protected int _masterChallengeLevelMax;

   // 存储字段上次注气时间的定义。
   private TDateTime __lastInjectSoulTime = new TDateTime();

   // 字段上次注气时间的定义。
   protected TDateTime _lastInjectSoulTime = new TDateTime();

   // 存储字段四圣血气的定义。
   private int __fourGodBloodGas;

   // 字段四圣血气的定义。
   protected int _fourGodBloodGas;

   // 存储字段快速师尊挑战时间的定义。
   private TDateTime __quickMasterChallengeTime = new TDateTime();

   // 字段快速师尊挑战时间的定义。
   protected TDateTime _quickMasterChallengeTime = new TDateTime();

   // 存储字段道具增加挂机次数的定义。
   private int __addOnhookCount;

   // 字段道具增加挂机次数的定义。
   protected int _addOnhookCount;

   // 存储字段师门竞技冷却时间的定义。
   private int __metierArenaCdTime;

   // 字段师门竞技冷却时间的定义。
   protected int _metierArenaCdTime;

   // 存储字段师门竞技时间的定义。
   private TDateTime __metierArenaTime = new TDateTime();

   // 字段师门竞技时间的定义。
   protected TDateTime _metierArenaTime = new TDateTime();

   // 存储字段龙珠收集的定义。
   private int __dragonBallGather;

   // 字段龙珠收集的定义。
   protected int _dragonBallGather;

   // 存储字段成就点的定义。
   private int __achievementPoint;

   // 字段成就点的定义。
   protected int _achievementPoint;

   // 存储字段成就等级的定义。
   private int __achievementLevel;

   // 字段成就等级的定义。
   protected int _achievementLevel;

   // 存储字段黄钻奖励的定义。
   private int __yellowReward;

   // 字段黄钻奖励的定义。
   protected int _yellowReward;

   // 存储字段黄钻 冲击奖励信息的定义。
   private int __yellowUpReward;

   // 字段黄钻 冲击奖励信息的定义。
   protected int _yellowUpReward;

   // 存储字段上次领取黄钻每日奖励时间的定义。
   private TDateTime __yellowRewardTime = new TDateTime();

   // 字段上次领取黄钻每日奖励时间的定义。
   protected TDateTime _yellowRewardTime = new TDateTime();

   // 存储字段登录天数的定义。
   private int __loginDay;

   // 字段登录天数的定义。
   protected int _loginDay;

   // 存储字段登录奖励物品的定义。
   private int __loginGetItem;

   // 字段登录奖励物品的定义。
   protected int _loginGetItem;

   // 存储字段累计充值领奖信息的定义。
   private int __allRechargeRewardMail;

   // 字段累计充值领奖信息的定义。
   protected int _allRechargeRewardMail;

   // 存储字段日常数据编号的定义。
   private long __roleDataId;

   // 字段日常数据编号的定义。
   protected long _roleDataId;

   // 存储字段平台邮件编号们的定义。
   private String __pfMailIds;

   // 字段平台邮件编号们的定义。
   protected String _pfMailIds;

   // 存储字段结婚编号的定义。
   private long __marryId;

   // 字段结婚编号的定义。
   protected long _marryId;

   // 存储字段副本快速挑战日期时间的定义。
   private TDateTime __quickCopyTime = new TDateTime();

   // 字段副本快速挑战日期时间的定义。
   protected TDateTime _quickCopyTime = new TDateTime();

   // 存储字段快速挑战副本的CD的定义。
   private int __quickCopyCd;

   // 字段快速挑战副本的CD的定义。
   protected int _quickCopyCd;

   // 存储字段组队竞技积分的定义。
   private int __teamWarMark;

   // 字段组队竞技积分的定义。
   protected int _teamWarMark;

   // 存储字段上次开启杀戮模式时间的定义。
   private TDateTime __lastMurderTime = new TDateTime();

   // 字段上次开启杀戮模式时间的定义。
   protected TDateTime _lastMurderTime = new TDateTime();

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
   // <T>构造角色信息逻辑单元。</T>
   //============================================================
   public FGameRoleUnit(){
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
   // <T>判断账户编号的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isAccountIdChanged(){
      return __accountId != _accountId;
   }

   //============================================================
   // <T>获得账户编号的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public long accountId(){
      return _accountId;
   }

   //============================================================
   // <T>设置账户编号的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setAccountId(long value){
      _accountId = value;
   }

   //============================================================
   // <T>判断状态的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isStatusCdChanged(){
      return __statusCd != _statusCd;
   }

   //============================================================
   // <T>获得状态的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int statusCd(){
      return _statusCd;
   }

   //============================================================
   // <T>设置状态的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setStatusCd(int value){
      _statusCd = value;
   }

   //============================================================
   // <T>判断删除时间的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isDeleteTimeChanged(){
      return !__deleteTime.equals(_deleteTime);
   }

   //============================================================
   // <T>获得删除时间的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public TDateTime deleteTime(){
      return _deleteTime;
   }

   //============================================================
   // <T>设置删除时间的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setDeleteTime(TDateTime value){
      _deleteTime = value;
   }

   //============================================================
   // <T>判断上线日期时间的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isOnlineDatetimeChanged(){
      return !__onlineDatetime.equals(_onlineDatetime);
   }

   //============================================================
   // <T>获得上线日期时间的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public TDateTime onlineDatetime(){
      return _onlineDatetime;
   }

   //============================================================
   // <T>设置上线日期时间的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setOnlineDatetime(TDateTime value){
      _onlineDatetime = value;
   }

   //============================================================
   // <T>判断下线日期时间的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isOfflineDatetimeChanged(){
      return !__offlineDatetime.equals(_offlineDatetime);
   }

   //============================================================
   // <T>获得下线日期时间的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public TDateTime offlineDatetime(){
      return _offlineDatetime;
   }

   //============================================================
   // <T>设置下线日期时间的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setOfflineDatetime(TDateTime value){
      _offlineDatetime = value;
   }

   //============================================================
   // <T>判断标志的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isFlagsChanged(){
      return __flags != _flags;
   }

   //============================================================
   // <T>获得标志的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int flags(){
      return _flags;
   }

   //============================================================
   // <T>设置标志的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setFlags(int value){
      _flags = value;
   }

   //============================================================
   // <T>判断中文名称的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isLabelChanged(){
      return !RString.equals(__label, _label);
   }

   //============================================================
   // <T>获得中文名称的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public String label(){
      return _label;
   }

   //============================================================
   // <T>设置中文名称的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setLabel(String value){
      _label = value;
   }

   //============================================================
   // <T>判断时代模版编号的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isTimeTidChanged(){
      return __timeTid != _timeTid;
   }

   //============================================================
   // <T>获得时代模版编号的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int timeTid(){
      return _timeTid;
   }

   //============================================================
   // <T>设置时代模版编号的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setTimeTid(int value){
      _timeTid = value;
   }

   //============================================================
   // <T>判断角色模板编号的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isRoleTidChanged(){
      return __roleTid != _roleTid;
   }

   //============================================================
   // <T>获得角色模板编号的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int roleTid(){
      return _roleTid;
   }

   //============================================================
   // <T>设置角色模板编号的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setRoleTid(int value){
      _roleTid = value;
   }

   //============================================================
   // <T>判断种族模板编号的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isRaceTidChanged(){
      return __raceTid != _raceTid;
   }

   //============================================================
   // <T>获得种族模板编号的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int raceTid(){
      return _raceTid;
   }

   //============================================================
   // <T>设置种族模板编号的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setRaceTid(int value){
      _raceTid = value;
   }

   //============================================================
   // <T>判断性别模板编号的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isGenderTidChanged(){
      return __genderTid != _genderTid;
   }

   //============================================================
   // <T>获得性别模板编号的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int genderTid(){
      return _genderTid;
   }

   //============================================================
   // <T>设置性别模板编号的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setGenderTid(int value){
      _genderTid = value;
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
   // <T>判断阵法模板编号的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isMatrixTidChanged(){
      return __matrixTid != _matrixTid;
   }

   //============================================================
   // <T>获得阵法模板编号的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int matrixTid(){
      return _matrixTid;
   }

   //============================================================
   // <T>设置阵法模板编号的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setMatrixTid(int value){
      _matrixTid = value;
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
   // <T>判断生命值的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isHpChanged(){
      return !RString.equals(__hp, _hp);
   }

   //============================================================
   // <T>获得生命值的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public String hp(){
      return _hp;
   }

   //============================================================
   // <T>设置生命值的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setHp(String value){
      _hp = value;
   }

   //============================================================
   // <T>判断魔法值的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isMpChanged(){
      return !RString.equals(__mp, _mp);
   }

   //============================================================
   // <T>获得魔法值的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public String mp(){
      return _mp;
   }

   //============================================================
   // <T>设置魔法值的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setMp(String value){
      _mp = value;
   }

   //============================================================
   // <T>判断活力值的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isLpChanged(){
      return !RString.equals(__lp, _lp);
   }

   //============================================================
   // <T>获得活力值的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public String lp(){
      return _lp;
   }

   //============================================================
   // <T>设置活力值的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setLp(String value){
      _lp = value;
   }

   //============================================================
   // <T>判断精力值的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isEpChanged(){
      return !RString.equals(__ep, _ep);
   }

   //============================================================
   // <T>获得精力值的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public String ep(){
      return _ep;
   }

   //============================================================
   // <T>设置精力值的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setEp(String value){
      _ep = value;
   }

   //============================================================
   // <T>判断怒气值的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isApChanged(){
      return !RString.equals(__ap, _ap);
   }

   //============================================================
   // <T>获得怒气值的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public String ap(){
      return _ap;
   }

   //============================================================
   // <T>设置怒气值的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setAp(String value){
      _ap = value;
   }

   //============================================================
   // <T>判断体质的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isVitalityChanged(){
      return __vitality != _vitality;
   }

   //============================================================
   // <T>获得体质的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int vitality(){
      return _vitality;
   }

   //============================================================
   // <T>设置体质的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setVitality(int value){
      _vitality = value;
   }

   //============================================================
   // <T>判断力量的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isStrengthChanged(){
      return __strength != _strength;
   }

   //============================================================
   // <T>获得力量的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int strength(){
      return _strength;
   }

   //============================================================
   // <T>设置力量的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setStrength(int value){
      _strength = value;
   }

   //============================================================
   // <T>判断耐力的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isStaminaChanged(){
      return __stamina != _stamina;
   }

   //============================================================
   // <T>获得耐力的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int stamina(){
      return _stamina;
   }

   //============================================================
   // <T>设置耐力的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setStamina(int value){
      _stamina = value;
   }

   //============================================================
   // <T>判断魔力的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isMagicChanged(){
      return __magic != _magic;
   }

   //============================================================
   // <T>获得魔力的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int magic(){
      return _magic;
   }

   //============================================================
   // <T>设置魔力的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setMagic(int value){
      _magic = value;
   }

   //============================================================
   // <T>判断敏捷的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isAgilityChanged(){
      return __agility != _agility;
   }

   //============================================================
   // <T>获得敏捷的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int agility(){
      return _agility;
   }

   //============================================================
   // <T>设置敏捷的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setAgility(int value){
      _agility = value;
   }

   //============================================================
   // <T>判断潜力点的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isPotencyPointChanged(){
      return __potencyPoint != _potencyPoint;
   }

   //============================================================
   // <T>获得潜力点的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int potencyPoint(){
      return _potencyPoint;
   }

   //============================================================
   // <T>设置潜力点的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setPotencyPoint(int value){
      _potencyPoint = value;
   }

   //============================================================
   // <T>判断速度的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isSpeedChanged(){
      return __speed != _speed;
   }

   //============================================================
   // <T>获得速度的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int speed(){
      return _speed;
   }

   //============================================================
   // <T>设置速度的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setSpeed(int value){
      _speed = value;
   }

   //============================================================
   // <T>判断行动值的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isMobilityChanged(){
      return __mobility != _mobility;
   }

   //============================================================
   // <T>获得行动值的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int mobility(){
      return _mobility;
   }

   //============================================================
   // <T>设置行动值的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setMobility(int value){
      _mobility = value;
   }

   //============================================================
   // <T>判断格挡值的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isBlockChanged(){
      return __block != _block;
   }

   //============================================================
   // <T>获得格挡值的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int block(){
      return _block;
   }

   //============================================================
   // <T>设置格挡值的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setBlock(int value){
      _block = value;
   }

   //============================================================
   // <T>判断暴击值的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isCriChanged(){
      return __cri != _cri;
   }

   //============================================================
   // <T>获得暴击值的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int cri(){
      return _cri;
   }

   //============================================================
   // <T>设置暴击值的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setCri(int value){
      _cri = value;
   }

   //============================================================
   // <T>判断命中值的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isHitChanged(){
      return __hit != _hit;
   }

   //============================================================
   // <T>获得命中值的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int hit(){
      return _hit;
   }

   //============================================================
   // <T>设置命中值的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setHit(int value){
      _hit = value;
   }

   //============================================================
   // <T>判断闪避值的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isDodgeChanged(){
      return __dodge != _dodge;
   }

   //============================================================
   // <T>获得闪避值的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int dodge(){
      return _dodge;
   }

   //============================================================
   // <T>设置闪避值的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setDodge(int value){
      _dodge = value;
   }

   //============================================================
   // <T>判断物理攻击力的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isAttackPhysicalChanged(){
      return __attackPhysical != _attackPhysical;
   }

   //============================================================
   // <T>获得物理攻击力的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int attackPhysical(){
      return _attackPhysical;
   }

   //============================================================
   // <T>设置物理攻击力的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setAttackPhysical(int value){
      _attackPhysical = value;
   }

   //============================================================
   // <T>判断穿刺攻击力的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isAttackPunctureChanged(){
      return __attackPuncture != _attackPuncture;
   }

   //============================================================
   // <T>获得穿刺攻击力的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int attackPuncture(){
      return _attackPuncture;
   }

   //============================================================
   // <T>设置穿刺攻击力的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setAttackPuncture(int value){
      _attackPuncture = value;
   }

   //============================================================
   // <T>判断魔法攻击力的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isAttackMagicChanged(){
      return __attackMagic != _attackMagic;
   }

   //============================================================
   // <T>获得魔法攻击力的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int attackMagic(){
      return _attackMagic;
   }

   //============================================================
   // <T>设置魔法攻击力的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setAttackMagic(int value){
      _attackMagic = value;
   }

   //============================================================
   // <T>判断物理防御力的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isDefencePhysicalChanged(){
      return __defencePhysical != _defencePhysical;
   }

   //============================================================
   // <T>获得物理防御力的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int defencePhysical(){
      return _defencePhysical;
   }

   //============================================================
   // <T>设置物理防御力的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setDefencePhysical(int value){
      _defencePhysical = value;
   }

   //============================================================
   // <T>判断穿刺防御力的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isDefencePunctureChanged(){
      return __defencePuncture != _defencePuncture;
   }

   //============================================================
   // <T>获得穿刺防御力的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int defencePuncture(){
      return _defencePuncture;
   }

   //============================================================
   // <T>设置穿刺防御力的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setDefencePuncture(int value){
      _defencePuncture = value;
   }

   //============================================================
   // <T>判断魔法防御力的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isDefenceMagicChanged(){
      return __defenceMagic != _defenceMagic;
   }

   //============================================================
   // <T>获得魔法防御力的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int defenceMagic(){
      return _defenceMagic;
   }

   //============================================================
   // <T>设置魔法防御力的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setDefenceMagic(int value){
      _defenceMagic = value;
   }

   //============================================================
   // <T>判断经验的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isExperienceChanged(){
      return __experience != _experience;
   }

   //============================================================
   // <T>获得经验的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int experience(){
      return _experience;
   }

   //============================================================
   // <T>设置经验的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setExperience(int value){
      _experience = value;
   }

   //============================================================
   // <T>判断升级所需经验的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isExperienceupChanged(){
      return __experienceup != _experienceup;
   }

   //============================================================
   // <T>获得升级所需经验的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int experienceup(){
      return _experienceup;
   }

   //============================================================
   // <T>设置升级所需经验的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setExperienceup(int value){
      _experienceup = value;
   }

   //============================================================
   // <T>判断帮会编号的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isSocietyIdChanged(){
      return __societyId != _societyId;
   }

   //============================================================
   // <T>获得帮会编号的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public long societyId(){
      return _societyId;
   }

   //============================================================
   // <T>设置帮会编号的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setSocietyId(long value){
      _societyId = value;
   }

   //============================================================
   // <T>判断帮会贡献的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isContributeChanged(){
      return !RString.equals(__contribute, _contribute);
   }

   //============================================================
   // <T>获得帮会贡献的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public String contribute(){
      return _contribute;
   }

   //============================================================
   // <T>设置帮会贡献的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setContribute(String value){
      _contribute = value;
   }

   //============================================================
   // <T>判断帮会职位的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isSocietyJobChanged(){
      return __societyJob != _societyJob;
   }

   //============================================================
   // <T>获得帮会职位的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int societyJob(){
      return _societyJob;
   }

   //============================================================
   // <T>设置帮会职位的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setSocietyJob(int value){
      _societyJob = value;
   }

   //============================================================
   // <T>判断帮会的精英成员的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isSocietyEliteMemberChanged(){
      return __societyEliteMember != _societyEliteMember;
   }

   //============================================================
   // <T>获得帮会的精英成员的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int societyEliteMember(){
      return _societyEliteMember;
   }

   //============================================================
   // <T>设置帮会的精英成员的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setSocietyEliteMember(int value){
      _societyEliteMember = value;
   }

   //============================================================
   // <T>判断进帮时间的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isEntranceTimeChanged(){
      return !__entranceTime.equals(_entranceTime);
   }

   //============================================================
   // <T>获得进帮时间的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public TDateTime entranceTime(){
      return _entranceTime;
   }

   //============================================================
   // <T>设置进帮时间的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setEntranceTime(TDateTime value){
      _entranceTime = value;
   }

   //============================================================
   // <T>判断下线场景线模板编号的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isOfflineLineTidChanged(){
      return __offlineLineTid != _offlineLineTid;
   }

   //============================================================
   // <T>获得下线场景线模板编号的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int offlineLineTid(){
      return _offlineLineTid;
   }

   //============================================================
   // <T>设置下线场景线模板编号的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setOfflineLineTid(int value){
      _offlineLineTid = value;
   }

   //============================================================
   // <T>判断下线地图模板编号的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isOfflineMapTidChanged(){
      return __offlineMapTid != _offlineMapTid;
   }

   //============================================================
   // <T>获得下线地图模板编号的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int offlineMapTid(){
      return _offlineMapTid;
   }

   //============================================================
   // <T>设置下线地图模板编号的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setOfflineMapTid(int value){
      _offlineMapTid = value;
   }

   //============================================================
   // <T>判断副本中下线的方向的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isOfflineMapIdChanged(){
      return __offlineMapId != _offlineMapId;
   }

   //============================================================
   // <T>获得副本中下线的方向的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int offlineMapId(){
      return _offlineMapId;
   }

   //============================================================
   // <T>设置副本中下线的方向的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setOfflineMapId(int value){
      _offlineMapId = value;
   }

   //============================================================
   // <T>判断下线坐标的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isOfflineLocationChanged(){
      return !RString.equals(__offlineLocation, _offlineLocation);
   }

   //============================================================
   // <T>获得下线坐标的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public String offlineLocation(){
      return _offlineLocation;
   }

   //============================================================
   // <T>设置下线坐标的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setOfflineLocation(String value){
      _offlineLocation = value;
   }

   //============================================================
   // <T>判断下线方向的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isOfflineDirectionCdChanged(){
      return __offlineDirectionCd != _offlineDirectionCd;
   }

   //============================================================
   // <T>获得下线方向的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int offlineDirectionCd(){
      return _offlineDirectionCd;
   }

   //============================================================
   // <T>设置下线方向的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setOfflineDirectionCd(int value){
      _offlineDirectionCd = value;
   }

   //============================================================
   // <T>判断会员等级的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isVipLevelChanged(){
      return __vipLevel != _vipLevel;
   }

   //============================================================
   // <T>获得会员等级的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int vipLevel(){
      return _vipLevel;
   }

   //============================================================
   // <T>设置会员等级的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setVipLevel(int value){
      _vipLevel = value;
   }

   //============================================================
   // <T>判断宠物背包的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isBagPetChanged(){
      return __bagPet != _bagPet;
   }

   //============================================================
   // <T>获得宠物背包的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int bagPet(){
      return _bagPet;
   }

   //============================================================
   // <T>设置宠物背包的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setBagPet(int value){
      _bagPet = value;
   }

   //============================================================
   // <T>判断宠物仓库的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isDepotPetChanged(){
      return __depotPet != _depotPet;
   }

   //============================================================
   // <T>获得宠物仓库的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int depotPet(){
      return _depotPet;
   }

   //============================================================
   // <T>设置宠物仓库的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setDepotPet(int value){
      _depotPet = value;
   }

   //============================================================
   // <T>判断坐骑背包的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isBagRideChanged(){
      return __bagRide != _bagRide;
   }

   //============================================================
   // <T>获得坐骑背包的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int bagRide(){
      return _bagRide;
   }

   //============================================================
   // <T>设置坐骑背包的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setBagRide(int value){
      _bagRide = value;
   }

   //============================================================
   // <T>判断剩余双倍经验时间的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isSurplusDoubleTimeChanged(){
      return __surplusDoubleTime != _surplusDoubleTime;
   }

   //============================================================
   // <T>获得剩余双倍经验时间的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int surplusDoubleTime(){
      return _surplusDoubleTime;
   }

   //============================================================
   // <T>设置剩余双倍经验时间的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setSurplusDoubleTime(int value){
      _surplusDoubleTime = value;
   }

   //============================================================
   // <T>判断可用双倍经验时间的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isAvailableTimeChanged(){
      return __availableTime != _availableTime;
   }

   //============================================================
   // <T>获得可用双倍经验时间的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int availableTime(){
      return _availableTime;
   }

   //============================================================
   // <T>设置可用双倍经验时间的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setAvailableTime(int value){
      _availableTime = value;
   }

   //============================================================
   // <T>判断双倍经验开始时间的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isStartDoubleTimeChanged(){
      return __startDoubleTime != _startDoubleTime;
   }

   //============================================================
   // <T>获得双倍经验开始时间的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int startDoubleTime(){
      return _startDoubleTime;
   }

   //============================================================
   // <T>设置双倍经验开始时间的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setStartDoubleTime(int value){
      _startDoubleTime = value;
   }

   //============================================================
   // <T>判断自动战斗中使用的技能编号的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isSkillIdChanged(){
      return __skillId != _skillId;
   }

   //============================================================
   // <T>获得自动战斗中使用的技能编号的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public long skillId(){
      return _skillId;
   }

   //============================================================
   // <T>设置自动战斗中使用的技能编号的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setSkillId(long value){
      _skillId = value;
   }

   //============================================================
   // <T>判断挂机标志的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isOnhookFlagsChanged(){
      return __onhookFlags != _onhookFlags;
   }

   //============================================================
   // <T>获得挂机标志的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int onhookFlags(){
      return _onhookFlags;
   }

   //============================================================
   // <T>设置挂机标志的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setOnhookFlags(int value){
      _onhookFlags = value;
   }

   //============================================================
   // <T>判断角色最小生命比例的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isRoleMinhpRateChanged(){
      return __roleMinhpRate != _roleMinhpRate;
   }

   //============================================================
   // <T>获得角色最小生命比例的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int roleMinhpRate(){
      return _roleMinhpRate;
   }

   //============================================================
   // <T>设置角色最小生命比例的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setRoleMinhpRate(int value){
      _roleMinhpRate = value;
   }

   //============================================================
   // <T>判断角色最小魔法比例的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isRoleMinmpRateChanged(){
      return __roleMinmpRate != _roleMinmpRate;
   }

   //============================================================
   // <T>获得角色最小魔法比例的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int roleMinmpRate(){
      return _roleMinmpRate;
   }

   //============================================================
   // <T>设置角色最小魔法比例的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setRoleMinmpRate(int value){
      _roleMinmpRate = value;
   }

   //============================================================
   // <T>判断宠物编号的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isPetIdChanged(){
      return __petId != _petId;
   }

   //============================================================
   // <T>获得宠物编号的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public long petId(){
      return _petId;
   }

   //============================================================
   // <T>设置宠物编号的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setPetId(long value){
      _petId = value;
   }

   //============================================================
   // <T>判断自动战斗中宠物使用的技能编号的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isPetSkillIdChanged(){
      return __petSkillId != _petSkillId;
   }

   //============================================================
   // <T>获得自动战斗中宠物使用的技能编号的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public long petSkillId(){
      return _petSkillId;
   }

   //============================================================
   // <T>设置自动战斗中宠物使用的技能编号的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setPetSkillId(long value){
      _petSkillId = value;
   }

   //============================================================
   // <T>判断真元的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isCrystalChanged(){
      return !RString.equals(__crystal, _crystal);
   }

   //============================================================
   // <T>获得真元的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public String crystal(){
      return _crystal;
   }

   //============================================================
   // <T>设置真元的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setCrystal(String value){
      _crystal = value;
   }

   //============================================================
   // <T>判断门派贡献的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isMetierContributionChanged(){
      return !RString.equals(__metierContribution, _metierContribution);
   }

   //============================================================
   // <T>获得门派贡献的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public String metierContribution(){
      return _metierContribution;
   }

   //============================================================
   // <T>设置门派贡献的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setMetierContribution(String value){
      _metierContribution = value;
   }

   //============================================================
   // <T>判断境界等级的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isRealmLevelChanged(){
      return __realmLevel != _realmLevel;
   }

   //============================================================
   // <T>获得境界等级的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int realmLevel(){
      return _realmLevel;
   }

   //============================================================
   // <T>设置境界等级的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setRealmLevel(int value){
      _realmLevel = value;
   }

   //============================================================
   // <T>判断经脉等级的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isChannelLevelChanged(){
      return __channelLevel != _channelLevel;
   }

   //============================================================
   // <T>获得经脉等级的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int channelLevel(){
      return _channelLevel;
   }

   //============================================================
   // <T>设置经脉等级的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setChannelLevel(int value){
      _channelLevel = value;
   }

   //============================================================
   // <T>判断炼心等级的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isHeartLevelChanged(){
      return __heartLevel != _heartLevel;
   }

   //============================================================
   // <T>获得炼心等级的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int heartLevel(){
      return _heartLevel;
   }

   //============================================================
   // <T>设置炼心等级的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setHeartLevel(int value){
      _heartLevel = value;
   }

   //============================================================
   // <T>判断融神等级的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isSpiritLevelChanged(){
      return __spiritLevel != _spiritLevel;
   }

   //============================================================
   // <T>获得融神等级的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int spiritLevel(){
      return _spiritLevel;
   }

   //============================================================
   // <T>设置融神等级的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setSpiritLevel(int value){
      _spiritLevel = value;
   }

   //============================================================
   // <T>判断商城物品购买限制的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isShopItemLimitChanged(){
      return !RString.equals(__shopItemLimit, _shopItemLimit);
   }

   //============================================================
   // <T>获得商城物品购买限制的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public String shopItemLimit(){
      return _shopItemLimit;
   }

   //============================================================
   // <T>设置商城物品购买限制的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setShopItemLimit(String value){
      _shopItemLimit = value;
   }

   //============================================================
   // <T>判断累计在线时长的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isTotalOnlineTimeChanged(){
      return __totalOnlineTime != _totalOnlineTime;
   }

   //============================================================
   // <T>获得累计在线时长的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int totalOnlineTime(){
      return _totalOnlineTime;
   }

   //============================================================
   // <T>设置累计在线时长的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setTotalOnlineTime(int value){
      _totalOnlineTime = value;
   }

   //============================================================
   // <T>判断押镖刷新次数的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isRefreshTaskEscortChanged(){
      return __refreshTaskEscort != _refreshTaskEscort;
   }

   //============================================================
   // <T>获得押镖刷新次数的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int refreshTaskEscort(){
      return _refreshTaskEscort;
   }

   //============================================================
   // <T>设置押镖刷新次数的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setRefreshTaskEscort(int value){
      _refreshTaskEscort = value;
   }

   //============================================================
   // <T>判断押镖任务部分的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isPartEscortChanged(){
      return __partEscort != _partEscort;
   }

   //============================================================
   // <T>获得押镖任务部分的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int partEscort(){
      return _partEscort;
   }

   //============================================================
   // <T>设置押镖任务部分的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setPartEscort(int value){
      _partEscort = value;
   }

   //============================================================
   // <T>判断登录角色网络地址的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isLoginHostChanged(){
      return !RString.equals(__loginHost, _loginHost);
   }

   //============================================================
   // <T>获得登录角色网络地址的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public String loginHost(){
      return _loginHost;
   }

   //============================================================
   // <T>设置登录角色网络地址的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setLoginHost(String value){
      _loginHost = value;
   }

   //============================================================
   // <T>判断登录时间的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isLoginDateChanged(){
      return !__loginDate.equals(_loginDate);
   }

   //============================================================
   // <T>获得登录时间的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public TDateTime loginDate(){
      return _loginDate;
   }

   //============================================================
   // <T>设置登录时间的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setLoginDate(TDateTime value){
      _loginDate = value;
   }

   //============================================================
   // <T>判断pk值的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isPkValueChanged(){
      return __pkValue != _pkValue;
   }

   //============================================================
   // <T>获得pk值的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int pkValue(){
      return _pkValue;
   }

   //============================================================
   // <T>设置pk值的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setPkValue(int value){
      _pkValue = value;
   }

   //============================================================
   // <T>判断人物战力的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isGradePointChanged(){
      return __gradePoint != _gradePoint;
   }

   //============================================================
   // <T>获得人物战力的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int gradePoint(){
      return _gradePoint;
   }

   //============================================================
   // <T>设置人物战力的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setGradePoint(int value){
      _gradePoint = value;
   }

   //============================================================
   // <T>判断引导标志的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isGuideFlagChanged(){
      return __guideFlag != _guideFlag;
   }

   //============================================================
   // <T>获得引导标志的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int guideFlag(){
      return _guideFlag;
   }

   //============================================================
   // <T>设置引导标志的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setGuideFlag(int value){
      _guideFlag = value;
   }

   //============================================================
   // <T>判断队伍ID的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isTeamIdChanged(){
      return __teamId != _teamId;
   }

   //============================================================
   // <T>获得队伍ID的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int teamId(){
      return _teamId;
   }

   //============================================================
   // <T>设置队伍ID的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setTeamId(int value){
      _teamId = value;
   }

   //============================================================
   // <T>判断活跃度点数的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isActivePointChanged(){
      return !RString.equals(__activePoint, _activePoint);
   }

   //============================================================
   // <T>获得活跃度点数的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public String activePoint(){
      return _activePoint;
   }

   //============================================================
   // <T>设置活跃度点数的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setActivePoint(String value){
      _activePoint = value;
   }

   //============================================================
   // <T>判断竞技场点数的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isTournamentPointChanged(){
      return !RString.equals(__tournamentPoint, _tournamentPoint);
   }

   //============================================================
   // <T>获得竞技场点数的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public String tournamentPoint(){
      return _tournamentPoint;
   }

   //============================================================
   // <T>设置竞技场点数的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setTournamentPoint(String value){
      _tournamentPoint = value;
   }

   //============================================================
   // <T>判断帮会战点数的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isSocietyWarPointChanged(){
      return !RString.equals(__societyWarPoint, _societyWarPoint);
   }

   //============================================================
   // <T>获得帮会战点数的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public String societyWarPoint(){
      return _societyWarPoint;
   }

   //============================================================
   // <T>设置帮会战点数的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setSocietyWarPoint(String value){
      _societyWarPoint = value;
   }

   //============================================================
   // <T>判断绑定金的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isGoldBindChanged(){
      return __goldBind != _goldBind;
   }

   //============================================================
   // <T>获得绑定金的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int goldBind(){
      return _goldBind;
   }

   //============================================================
   // <T>设置绑定金的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setGoldBind(int value){
      _goldBind = value;
   }

   //============================================================
   // <T>判断非绑定金币的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isGoldUnbindChanged(){
      return __goldUnbind != _goldUnbind;
   }

   //============================================================
   // <T>获得非绑定金币的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int goldUnbind(){
      return _goldUnbind;
   }

   //============================================================
   // <T>设置非绑定金币的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setGoldUnbind(int value){
      _goldUnbind = value;
   }

   //============================================================
   // <T>判断绑定元宝的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isMoneyBindChanged(){
      return __moneyBind != _moneyBind;
   }

   //============================================================
   // <T>获得绑定元宝的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int moneyBind(){
      return _moneyBind;
   }

   //============================================================
   // <T>设置绑定元宝的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setMoneyBind(int value){
      _moneyBind = value;
   }

   //============================================================
   // <T>判断元宝的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isMoneyUnbindChanged(){
      return __moneyUnbind != _moneyUnbind;
   }

   //============================================================
   // <T>获得元宝的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int moneyUnbind(){
      return _moneyUnbind;
   }

   //============================================================
   // <T>设置元宝的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setMoneyUnbind(int value){
      _moneyUnbind = value;
   }

   //============================================================
   // <T>判断赠点的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isPointChanged(){
      return __point != _point;
   }

   //============================================================
   // <T>获得赠点的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int point(){
      return _point;
   }

   //============================================================
   // <T>设置赠点的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setPoint(int value){
      _point = value;
   }

   //============================================================
   // <T>判断仓库绑定金币的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isDepotGoldBindChanged(){
      return __depotGoldBind != _depotGoldBind;
   }

   //============================================================
   // <T>获得仓库绑定金币的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int depotGoldBind(){
      return _depotGoldBind;
   }

   //============================================================
   // <T>设置仓库绑定金币的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setDepotGoldBind(int value){
      _depotGoldBind = value;
   }

   //============================================================
   // <T>判断仓库非绑定金币的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isDepotGoldUnbindChanged(){
      return __depotGoldUnbind != _depotGoldUnbind;
   }

   //============================================================
   // <T>获得仓库非绑定金币的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int depotGoldUnbind(){
      return _depotGoldUnbind;
   }

   //============================================================
   // <T>设置仓库非绑定金币的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setDepotGoldUnbind(int value){
      _depotGoldUnbind = value;
   }

   //============================================================
   // <T>判断仓库绑定元宝的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isDepotMoneyBindChanged(){
      return __depotMoneyBind != _depotMoneyBind;
   }

   //============================================================
   // <T>获得仓库绑定元宝的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int depotMoneyBind(){
      return _depotMoneyBind;
   }

   //============================================================
   // <T>设置仓库绑定元宝的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setDepotMoneyBind(int value){
      _depotMoneyBind = value;
   }

   //============================================================
   // <T>判断仓库元宝的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isDepotMoneyUnbindChanged(){
      return __depotMoneyUnbind != _depotMoneyUnbind;
   }

   //============================================================
   // <T>获得仓库元宝的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int depotMoneyUnbind(){
      return _depotMoneyUnbind;
   }

   //============================================================
   // <T>设置仓库元宝的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setDepotMoneyUnbind(int value){
      _depotMoneyUnbind = value;
   }

   //============================================================
   // <T>判断仓库赠点的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isDepotPiontChanged(){
      return __depotPiont != _depotPiont;
   }

   //============================================================
   // <T>获得仓库赠点的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int depotPiont(){
      return _depotPiont;
   }

   //============================================================
   // <T>设置仓库赠点的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setDepotPiont(int value){
      _depotPiont = value;
   }

   //============================================================
   // <T>判断劫镖次数的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isRobberyCountChanged(){
      return __robberyCount != _robberyCount;
   }

   //============================================================
   // <T>获得劫镖次数的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int robberyCount(){
      return _robberyCount;
   }

   //============================================================
   // <T>设置劫镖次数的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setRobberyCount(int value){
      _robberyCount = value;
   }

   //============================================================
   // <T>判断连续登录天数的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isLastLoginDaysChanged(){
      return __lastLoginDays != _lastLoginDays;
   }

   //============================================================
   // <T>获得连续登录天数的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int lastLoginDays(){
      return _lastLoginDays;
   }

   //============================================================
   // <T>设置连续登录天数的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setLastLoginDays(int value){
      _lastLoginDays = value;
   }

   //============================================================
   // <T>判断交易行出售金钱数量的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isAuctionCurrencyCountChanged(){
      return __auctionCurrencyCount != _auctionCurrencyCount;
   }

   //============================================================
   // <T>获得交易行出售金钱数量的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int auctionCurrencyCount(){
      return _auctionCurrencyCount;
   }

   //============================================================
   // <T>设置交易行出售金钱数量的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setAuctionCurrencyCount(int value){
      _auctionCurrencyCount = value;
   }

   //============================================================
   // <T>判断离线时间的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isLastOfflineTimeChanged(){
      return __lastOfflineTime != _lastOfflineTime;
   }

   //============================================================
   // <T>获得离线时间的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int lastOfflineTime(){
      return _lastOfflineTime;
   }

   //============================================================
   // <T>设置离线时间的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setLastOfflineTime(int value){
      _lastOfflineTime = value;
   }

   //============================================================
   // <T>判断魅力值的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isCharmingValueChanged(){
      return __charmingValue != _charmingValue;
   }

   //============================================================
   // <T>获得魅力值的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int charmingValue(){
      return _charmingValue;
   }

   //============================================================
   // <T>设置魅力值的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setCharmingValue(int value){
      _charmingValue = value;
   }

   //============================================================
   // <T>判断杀人数的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isKillCountChanged(){
      return __killCount != _killCount;
   }

   //============================================================
   // <T>获得杀人数的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int killCount(){
      return _killCount;
   }

   //============================================================
   // <T>设置杀人数的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setKillCount(int value){
      _killCount = value;
   }

   //============================================================
   // <T>判断消耗元宝数量的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isSpendMoneyChanged(){
      return __spendMoney != _spendMoney;
   }

   //============================================================
   // <T>获得消耗元宝数量的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int spendMoney(){
      return _spendMoney;
   }

   //============================================================
   // <T>设置消耗元宝数量的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setSpendMoney(int value){
      _spendMoney = value;
   }

   //============================================================
   // <T>判断会员礼包领取标志的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isVipGiftFlagChanged(){
      return __vipGiftFlag != _vipGiftFlag;
   }

   //============================================================
   // <T>获得会员礼包领取标志的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int vipGiftFlag(){
      return _vipGiftFlag;
   }

   //============================================================
   // <T>设置会员礼包领取标志的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setVipGiftFlag(int value){
      _vipGiftFlag = value;
   }

   //============================================================
   // <T>判断会员标志的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isVipFlagChanged(){
      return __vipFlag != _vipFlag;
   }

   //============================================================
   // <T>获得会员标志的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int vipFlag(){
      return _vipFlag;
   }

   //============================================================
   // <T>设置会员标志的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setVipFlag(int value){
      _vipFlag = value;
   }

   //============================================================
   // <T>判断二级密码问题一的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isQuestionOneChanged(){
      return __questionOne != _questionOne;
   }

   //============================================================
   // <T>获得二级密码问题一的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int questionOne(){
      return _questionOne;
   }

   //============================================================
   // <T>设置二级密码问题一的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setQuestionOne(int value){
      _questionOne = value;
   }

   //============================================================
   // <T>判断二级密码回答一的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isAnswerOneChanged(){
      return !RString.equals(__answerOne, _answerOne);
   }

   //============================================================
   // <T>获得二级密码回答一的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public String answerOne(){
      return _answerOne;
   }

   //============================================================
   // <T>设置二级密码回答一的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setAnswerOne(String value){
      _answerOne = value;
   }

   //============================================================
   // <T>判断二级密码问题二的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isQuestionTwoChanged(){
      return __questionTwo != _questionTwo;
   }

   //============================================================
   // <T>获得二级密码问题二的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int questionTwo(){
      return _questionTwo;
   }

   //============================================================
   // <T>设置二级密码问题二的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setQuestionTwo(int value){
      _questionTwo = value;
   }

   //============================================================
   // <T>判断二级密码回答二的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isAnswerTwoChanged(){
      return !RString.equals(__answerTwo, _answerTwo);
   }

   //============================================================
   // <T>获得二级密码回答二的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public String answerTwo(){
      return _answerTwo;
   }

   //============================================================
   // <T>设置二级密码回答二的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setAnswerTwo(String value){
      _answerTwo = value;
   }

   //============================================================
   // <T>判断二级密码的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isCodePasswordChanged(){
      return !RString.equals(__codePassword, _codePassword);
   }

   //============================================================
   // <T>获得二级密码的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public String codePassword(){
      return _codePassword;
   }

   //============================================================
   // <T>设置二级密码的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setCodePassword(String value){
      _codePassword = value;
   }

   //============================================================
   // <T>判断上次保存时间的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isStorageLastTimeChanged(){
      return !__storageLastTime.equals(_storageLastTime);
   }

   //============================================================
   // <T>获得上次保存时间的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public TDateTime storageLastTime(){
      return _storageLastTime;
   }

   //============================================================
   // <T>设置上次保存时间的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setStorageLastTime(TDateTime value){
      _storageLastTime = value;
   }

   //============================================================
   // <T>判断禁言开始的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isTalkGanBeginChanged(){
      return !__talkGanBegin.equals(_talkGanBegin);
   }

   //============================================================
   // <T>获得禁言开始的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public TDateTime talkGanBegin(){
      return _talkGanBegin;
   }

   //============================================================
   // <T>设置禁言开始的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setTalkGanBegin(TDateTime value){
      _talkGanBegin = value;
   }

   //============================================================
   // <T>判断禁言结束的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isTalkGanEndChanged(){
      return !__talkGanEnd.equals(_talkGanEnd);
   }

   //============================================================
   // <T>获得禁言结束的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public TDateTime talkGanEnd(){
      return _talkGanEnd;
   }

   //============================================================
   // <T>设置禁言结束的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setTalkGanEnd(TDateTime value){
      _talkGanEnd = value;
   }

   //============================================================
   // <T>判断角色冻结的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isRoleFreezeChanged(){
      return __roleFreeze != _roleFreeze;
   }

   //============================================================
   // <T>获得角色冻结的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int roleFreeze(){
      return _roleFreeze;
   }

   //============================================================
   // <T>设置角色冻结的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setRoleFreeze(int value){
      _roleFreeze = value;
   }

   //============================================================
   // <T>判断时装展示的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isShizhuangShowChanged(){
      return __shizhuangShow != _shizhuangShow;
   }

   //============================================================
   // <T>获得时装展示的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int shizhuangShow(){
      return _shizhuangShow;
   }

   //============================================================
   // <T>设置时装展示的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setShizhuangShow(int value){
      _shizhuangShow = value;
   }

   //============================================================
   // <T>判断神剑开始时间的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isMagicsWordTimeChanged(){
      return !__magicsWordTime.equals(_magicsWordTime);
   }

   //============================================================
   // <T>获得神剑开始时间的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public TDateTime magicsWordTime(){
      return _magicsWordTime;
   }

   //============================================================
   // <T>设置神剑开始时间的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setMagicsWordTime(TDateTime value){
      _magicsWordTime = value;
   }

   //============================================================
   // <T>判断神剑剩余时间的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isSurplusTimeChanged(){
      return __surplusTime != _surplusTime;
   }

   //============================================================
   // <T>获得神剑剩余时间的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int surplusTime(){
      return _surplusTime;
   }

   //============================================================
   // <T>设置神剑剩余时间的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setSurplusTime(int value){
      _surplusTime = value;
   }

   //============================================================
   // <T>判断是否领取过首冲的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isIsFirstGetChanged(){
      return __isFirstGet != _isFirstGet;
   }

   //============================================================
   // <T>获得是否领取过首冲的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int isFirstGet(){
      return _isFirstGet;
   }

   //============================================================
   // <T>设置是否领取过首冲的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setIsFirstGet(int value){
      _isFirstGet = value;
   }

   //============================================================
   // <T>判断今日在线时长的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isOnlineTodayChanged(){
      return __onlineToday != _onlineToday;
   }

   //============================================================
   // <T>获得今日在线时长的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int onlineToday(){
      return _onlineToday;
   }

   //============================================================
   // <T>设置今日在线时长的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setOnlineToday(int value){
      _onlineToday = value;
   }

   //============================================================
   // <T>判断服务器更新标记的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isServerFlagChanged(){
      return __serverFlag != _serverFlag;
   }

   //============================================================
   // <T>获得服务器更新标记的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int serverFlag(){
      return _serverFlag;
   }

   //============================================================
   // <T>设置服务器更新标记的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setServerFlag(int value){
      _serverFlag = value;
   }

   //============================================================
   // <T>判断剑魂的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isSwordSoulChanged(){
      return __swordSoul != _swordSoul;
   }

   //============================================================
   // <T>获得剑魂的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int swordSoul(){
      return _swordSoul;
   }

   //============================================================
   // <T>设置剑魂的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setSwordSoul(int value){
      _swordSoul = value;
   }

   //============================================================
   // <T>判断师尊挑战等级的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isMasterChallengeLevelChanged(){
      return __masterChallengeLevel != _masterChallengeLevel;
   }

   //============================================================
   // <T>获得师尊挑战等级的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int masterChallengeLevel(){
      return _masterChallengeLevel;
   }

   //============================================================
   // <T>设置师尊挑战等级的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setMasterChallengeLevel(int value){
      _masterChallengeLevel = value;
   }

   //============================================================
   // <T>判断师尊挑战最大等级的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isMasterChallengeLevelMaxChanged(){
      return __masterChallengeLevelMax != _masterChallengeLevelMax;
   }

   //============================================================
   // <T>获得师尊挑战最大等级的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int masterChallengeLevelMax(){
      return _masterChallengeLevelMax;
   }

   //============================================================
   // <T>设置师尊挑战最大等级的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setMasterChallengeLevelMax(int value){
      _masterChallengeLevelMax = value;
   }

   //============================================================
   // <T>判断上次注气时间的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isLastInjectSoulTimeChanged(){
      return !__lastInjectSoulTime.equals(_lastInjectSoulTime);
   }

   //============================================================
   // <T>获得上次注气时间的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public TDateTime lastInjectSoulTime(){
      return _lastInjectSoulTime;
   }

   //============================================================
   // <T>设置上次注气时间的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setLastInjectSoulTime(TDateTime value){
      _lastInjectSoulTime = value;
   }

   //============================================================
   // <T>判断四圣血气的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isFourGodBloodGasChanged(){
      return __fourGodBloodGas != _fourGodBloodGas;
   }

   //============================================================
   // <T>获得四圣血气的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int fourGodBloodGas(){
      return _fourGodBloodGas;
   }

   //============================================================
   // <T>设置四圣血气的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setFourGodBloodGas(int value){
      _fourGodBloodGas = value;
   }

   //============================================================
   // <T>判断快速师尊挑战时间的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isQuickMasterChallengeTimeChanged(){
      return !__quickMasterChallengeTime.equals(_quickMasterChallengeTime);
   }

   //============================================================
   // <T>获得快速师尊挑战时间的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public TDateTime quickMasterChallengeTime(){
      return _quickMasterChallengeTime;
   }

   //============================================================
   // <T>设置快速师尊挑战时间的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setQuickMasterChallengeTime(TDateTime value){
      _quickMasterChallengeTime = value;
   }

   //============================================================
   // <T>判断道具增加挂机次数的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isAddOnhookCountChanged(){
      return __addOnhookCount != _addOnhookCount;
   }

   //============================================================
   // <T>获得道具增加挂机次数的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int addOnhookCount(){
      return _addOnhookCount;
   }

   //============================================================
   // <T>设置道具增加挂机次数的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setAddOnhookCount(int value){
      _addOnhookCount = value;
   }

   //============================================================
   // <T>判断师门竞技冷却时间的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isMetierArenaCdTimeChanged(){
      return __metierArenaCdTime != _metierArenaCdTime;
   }

   //============================================================
   // <T>获得师门竞技冷却时间的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int metierArenaCdTime(){
      return _metierArenaCdTime;
   }

   //============================================================
   // <T>设置师门竞技冷却时间的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setMetierArenaCdTime(int value){
      _metierArenaCdTime = value;
   }

   //============================================================
   // <T>判断师门竞技时间的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isMetierArenaTimeChanged(){
      return !__metierArenaTime.equals(_metierArenaTime);
   }

   //============================================================
   // <T>获得师门竞技时间的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public TDateTime metierArenaTime(){
      return _metierArenaTime;
   }

   //============================================================
   // <T>设置师门竞技时间的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setMetierArenaTime(TDateTime value){
      _metierArenaTime = value;
   }

   //============================================================
   // <T>判断龙珠收集的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isDragonBallGatherChanged(){
      return __dragonBallGather != _dragonBallGather;
   }

   //============================================================
   // <T>获得龙珠收集的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int dragonBallGather(){
      return _dragonBallGather;
   }

   //============================================================
   // <T>设置龙珠收集的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setDragonBallGather(int value){
      _dragonBallGather = value;
   }

   //============================================================
   // <T>判断成就点的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isAchievementPointChanged(){
      return __achievementPoint != _achievementPoint;
   }

   //============================================================
   // <T>获得成就点的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int achievementPoint(){
      return _achievementPoint;
   }

   //============================================================
   // <T>设置成就点的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setAchievementPoint(int value){
      _achievementPoint = value;
   }

   //============================================================
   // <T>判断成就等级的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isAchievementLevelChanged(){
      return __achievementLevel != _achievementLevel;
   }

   //============================================================
   // <T>获得成就等级的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int achievementLevel(){
      return _achievementLevel;
   }

   //============================================================
   // <T>设置成就等级的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setAchievementLevel(int value){
      _achievementLevel = value;
   }

   //============================================================
   // <T>判断黄钻奖励的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isYellowRewardChanged(){
      return __yellowReward != _yellowReward;
   }

   //============================================================
   // <T>获得黄钻奖励的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int yellowReward(){
      return _yellowReward;
   }

   //============================================================
   // <T>设置黄钻奖励的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setYellowReward(int value){
      _yellowReward = value;
   }

   //============================================================
   // <T>判断黄钻 冲击奖励信息的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isYellowUpRewardChanged(){
      return __yellowUpReward != _yellowUpReward;
   }

   //============================================================
   // <T>获得黄钻 冲击奖励信息的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int yellowUpReward(){
      return _yellowUpReward;
   }

   //============================================================
   // <T>设置黄钻 冲击奖励信息的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setYellowUpReward(int value){
      _yellowUpReward = value;
   }

   //============================================================
   // <T>判断上次领取黄钻每日奖励时间的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isYellowRewardTimeChanged(){
      return !__yellowRewardTime.equals(_yellowRewardTime);
   }

   //============================================================
   // <T>获得上次领取黄钻每日奖励时间的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public TDateTime yellowRewardTime(){
      return _yellowRewardTime;
   }

   //============================================================
   // <T>设置上次领取黄钻每日奖励时间的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setYellowRewardTime(TDateTime value){
      _yellowRewardTime = value;
   }

   //============================================================
   // <T>判断登录天数的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isLoginDayChanged(){
      return __loginDay != _loginDay;
   }

   //============================================================
   // <T>获得登录天数的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int loginDay(){
      return _loginDay;
   }

   //============================================================
   // <T>设置登录天数的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setLoginDay(int value){
      _loginDay = value;
   }

   //============================================================
   // <T>判断登录奖励物品的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isLoginGetItemChanged(){
      return __loginGetItem != _loginGetItem;
   }

   //============================================================
   // <T>获得登录奖励物品的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int loginGetItem(){
      return _loginGetItem;
   }

   //============================================================
   // <T>设置登录奖励物品的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setLoginGetItem(int value){
      _loginGetItem = value;
   }

   //============================================================
   // <T>判断累计充值领奖信息的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isAllRechargeRewardMailChanged(){
      return __allRechargeRewardMail != _allRechargeRewardMail;
   }

   //============================================================
   // <T>获得累计充值领奖信息的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int allRechargeRewardMail(){
      return _allRechargeRewardMail;
   }

   //============================================================
   // <T>设置累计充值领奖信息的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setAllRechargeRewardMail(int value){
      _allRechargeRewardMail = value;
   }

   //============================================================
   // <T>判断日常数据编号的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isRoleDataIdChanged(){
      return __roleDataId != _roleDataId;
   }

   //============================================================
   // <T>获得日常数据编号的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public long roleDataId(){
      return _roleDataId;
   }

   //============================================================
   // <T>设置日常数据编号的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setRoleDataId(long value){
      _roleDataId = value;
   }

   //============================================================
   // <T>判断平台邮件编号们的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isPfMailIdsChanged(){
      return !RString.equals(__pfMailIds, _pfMailIds);
   }

   //============================================================
   // <T>获得平台邮件编号们的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public String pfMailIds(){
      return _pfMailIds;
   }

   //============================================================
   // <T>设置平台邮件编号们的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setPfMailIds(String value){
      _pfMailIds = value;
   }

   //============================================================
   // <T>判断结婚编号的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isMarryIdChanged(){
      return __marryId != _marryId;
   }

   //============================================================
   // <T>获得结婚编号的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public long marryId(){
      return _marryId;
   }

   //============================================================
   // <T>设置结婚编号的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setMarryId(long value){
      _marryId = value;
   }

   //============================================================
   // <T>判断副本快速挑战日期时间的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isQuickCopyTimeChanged(){
      return !__quickCopyTime.equals(_quickCopyTime);
   }

   //============================================================
   // <T>获得副本快速挑战日期时间的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public TDateTime quickCopyTime(){
      return _quickCopyTime;
   }

   //============================================================
   // <T>设置副本快速挑战日期时间的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setQuickCopyTime(TDateTime value){
      _quickCopyTime = value;
   }

   //============================================================
   // <T>判断快速挑战副本的CD的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isQuickCopyCdChanged(){
      return __quickCopyCd != _quickCopyCd;
   }

   //============================================================
   // <T>获得快速挑战副本的CD的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int quickCopyCd(){
      return _quickCopyCd;
   }

   //============================================================
   // <T>设置快速挑战副本的CD的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setQuickCopyCd(int value){
      _quickCopyCd = value;
   }

   //============================================================
   // <T>判断组队竞技积分的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isTeamWarMarkChanged(){
      return __teamWarMark != _teamWarMark;
   }

   //============================================================
   // <T>获得组队竞技积分的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public int teamWarMark(){
      return _teamWarMark;
   }

   //============================================================
   // <T>设置组队竞技积分的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setTeamWarMark(int value){
      _teamWarMark = value;
   }

   //============================================================
   // <T>判断上次开启杀戮模式时间的数据是否改变。</T>
   //
   // @return 数据内容
   //============================================================
   public boolean isLastMurderTimeChanged(){
      return !__lastMurderTime.equals(_lastMurderTime);
   }

   //============================================================
   // <T>获得上次开启杀戮模式时间的数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public TDateTime lastMurderTime(){
      return _lastMurderTime;
   }

   //============================================================
   // <T>设置上次开启杀戮模式时间的数据内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setLastMurderTime(TDateTime value){
      _lastMurderTime = value;
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
      __accountId = RLong.parse(row.get("account_id"));
      _accountId = __accountId;
      __statusCd = row.getInteger("status_cd");
      _statusCd = __statusCd;
      __deleteTime.parse(row.get("delete_time"));
      _deleteTime.assign(__deleteTime);
      __onlineDatetime.parse(row.get("online_datetime"));
      _onlineDatetime.assign(__onlineDatetime);
      __offlineDatetime.parse(row.get("offline_datetime"));
      _offlineDatetime.assign(__offlineDatetime);
      __flags = row.getInteger("flags");
      _flags = __flags;
      __label = row.get("label");
      _label = __label;
      __timeTid = row.getInteger("time_tid");
      _timeTid = __timeTid;
      __roleTid = row.getInteger("role_tid");
      _roleTid = __roleTid;
      __raceTid = row.getInteger("race_tid");
      _raceTid = __raceTid;
      __genderTid = row.getInteger("gender_tid");
      _genderTid = __genderTid;
      __metierTid = row.getInteger("metier_tid");
      _metierTid = __metierTid;
      __matrixTid = row.getInteger("matrix_tid");
      _matrixTid = __matrixTid;
      __level = row.getInteger("level");
      _level = __level;
      __hp = row.get("hp");
      _hp = __hp;
      __mp = row.get("mp");
      _mp = __mp;
      __lp = row.get("lp");
      _lp = __lp;
      __ep = row.get("ep");
      _ep = __ep;
      __ap = row.get("ap");
      _ap = __ap;
      __vitality = row.getInteger("vitality");
      _vitality = __vitality;
      __strength = row.getInteger("strength");
      _strength = __strength;
      __stamina = row.getInteger("stamina");
      _stamina = __stamina;
      __magic = row.getInteger("magic");
      _magic = __magic;
      __agility = row.getInteger("agility");
      _agility = __agility;
      __potencyPoint = row.getInteger("potency_point");
      _potencyPoint = __potencyPoint;
      __speed = row.getInteger("speed");
      _speed = __speed;
      __mobility = row.getInteger("mobility");
      _mobility = __mobility;
      __block = row.getInteger("block");
      _block = __block;
      __cri = row.getInteger("cri");
      _cri = __cri;
      __hit = row.getInteger("hit");
      _hit = __hit;
      __dodge = row.getInteger("dodge");
      _dodge = __dodge;
      __attackPhysical = row.getInteger("attack_physical");
      _attackPhysical = __attackPhysical;
      __attackPuncture = row.getInteger("attack_puncture");
      _attackPuncture = __attackPuncture;
      __attackMagic = row.getInteger("attack_magic");
      _attackMagic = __attackMagic;
      __defencePhysical = row.getInteger("defence_physical");
      _defencePhysical = __defencePhysical;
      __defencePuncture = row.getInteger("defence_puncture");
      _defencePuncture = __defencePuncture;
      __defenceMagic = row.getInteger("defence_magic");
      _defenceMagic = __defenceMagic;
      __experience = row.getInteger("experience");
      _experience = __experience;
      __experienceup = row.getInteger("experienceup");
      _experienceup = __experienceup;
      __societyId = RLong.parse(row.get("society_id"));
      _societyId = __societyId;
      __contribute = row.get("contribute");
      _contribute = __contribute;
      __societyJob = row.getInteger("society_job");
      _societyJob = __societyJob;
      __societyEliteMember = row.getInteger("society_elite_member");
      _societyEliteMember = __societyEliteMember;
      __entranceTime.parse(row.get("entrance_time"));
      _entranceTime.assign(__entranceTime);
      __offlineLineTid = row.getInteger("offline_line_tid");
      _offlineLineTid = __offlineLineTid;
      __offlineMapTid = row.getInteger("offline_map_tid");
      _offlineMapTid = __offlineMapTid;
      __offlineMapId = row.getInteger("offline_map_id");
      _offlineMapId = __offlineMapId;
      __offlineLocation = row.get("offline_location");
      _offlineLocation = __offlineLocation;
      __offlineDirectionCd = row.getInteger("offline_direction_cd");
      _offlineDirectionCd = __offlineDirectionCd;
      __vipLevel = row.getInteger("vip_level");
      _vipLevel = __vipLevel;
      __bagPet = row.getInteger("bag_pet");
      _bagPet = __bagPet;
      __depotPet = row.getInteger("depot_pet");
      _depotPet = __depotPet;
      __bagRide = row.getInteger("bag_ride");
      _bagRide = __bagRide;
      __surplusDoubleTime = row.getInteger("surplus_double_time");
      _surplusDoubleTime = __surplusDoubleTime;
      __availableTime = row.getInteger("available_time");
      _availableTime = __availableTime;
      __startDoubleTime = row.getInteger("start_double_time");
      _startDoubleTime = __startDoubleTime;
      __skillId = RLong.parse(row.get("skill_id"));
      _skillId = __skillId;
      __onhookFlags = row.getInteger("onhook_flags");
      _onhookFlags = __onhookFlags;
      __roleMinhpRate = row.getInteger("role_minhp_rate");
      _roleMinhpRate = __roleMinhpRate;
      __roleMinmpRate = row.getInteger("role_minmp_rate");
      _roleMinmpRate = __roleMinmpRate;
      __petId = RLong.parse(row.get("pet_id"));
      _petId = __petId;
      __petSkillId = RLong.parse(row.get("pet_skill_id"));
      _petSkillId = __petSkillId;
      __crystal = row.get("crystal");
      _crystal = __crystal;
      __metierContribution = row.get("metier_contribution");
      _metierContribution = __metierContribution;
      __realmLevel = row.getInteger("realm_level");
      _realmLevel = __realmLevel;
      __channelLevel = row.getInteger("channel_level");
      _channelLevel = __channelLevel;
      __heartLevel = row.getInteger("heart_level");
      _heartLevel = __heartLevel;
      __spiritLevel = row.getInteger("spirit_level");
      _spiritLevel = __spiritLevel;
      __shopItemLimit = row.get("shop_item_limit");
      _shopItemLimit = __shopItemLimit;
      __totalOnlineTime = row.getInteger("total_online_time");
      _totalOnlineTime = __totalOnlineTime;
      __refreshTaskEscort = row.getInteger("refresh_task_escort");
      _refreshTaskEscort = __refreshTaskEscort;
      __partEscort = row.getInteger("part_escort");
      _partEscort = __partEscort;
      __loginHost = row.get("login_host");
      _loginHost = __loginHost;
      __loginDate.parse(row.get("login_date"));
      _loginDate.assign(__loginDate);
      __pkValue = row.getInteger("pk_value");
      _pkValue = __pkValue;
      __gradePoint = row.getInteger("grade_point");
      _gradePoint = __gradePoint;
      __guideFlag = row.getInteger("guide_flag");
      _guideFlag = __guideFlag;
      __teamId = row.getInteger("team_id");
      _teamId = __teamId;
      __activePoint = row.get("active_point");
      _activePoint = __activePoint;
      __tournamentPoint = row.get("tournament_point");
      _tournamentPoint = __tournamentPoint;
      __societyWarPoint = row.get("society_war_point");
      _societyWarPoint = __societyWarPoint;
      __goldBind = row.getInteger("gold_bind");
      _goldBind = __goldBind;
      __goldUnbind = row.getInteger("gold_unbind");
      _goldUnbind = __goldUnbind;
      __moneyBind = row.getInteger("money_bind");
      _moneyBind = __moneyBind;
      __moneyUnbind = row.getInteger("money_unbind");
      _moneyUnbind = __moneyUnbind;
      __point = row.getInteger("point");
      _point = __point;
      __depotGoldBind = row.getInteger("depot_gold_bind");
      _depotGoldBind = __depotGoldBind;
      __depotGoldUnbind = row.getInteger("depot_gold_unbind");
      _depotGoldUnbind = __depotGoldUnbind;
      __depotMoneyBind = row.getInteger("depot_money_bind");
      _depotMoneyBind = __depotMoneyBind;
      __depotMoneyUnbind = row.getInteger("depot_money_unbind");
      _depotMoneyUnbind = __depotMoneyUnbind;
      __depotPiont = row.getInteger("depot_piont");
      _depotPiont = __depotPiont;
      __robberyCount = row.getInteger("robbery_count");
      _robberyCount = __robberyCount;
      __lastLoginDays = row.getInteger("last_login_days");
      _lastLoginDays = __lastLoginDays;
      __auctionCurrencyCount = row.getInteger("auction_currency_count");
      _auctionCurrencyCount = __auctionCurrencyCount;
      __lastOfflineTime = row.getInteger("last_offline_time");
      _lastOfflineTime = __lastOfflineTime;
      __charmingValue = row.getInteger("charming_value");
      _charmingValue = __charmingValue;
      __killCount = row.getInteger("kill_count");
      _killCount = __killCount;
      __spendMoney = row.getInteger("spend_money");
      _spendMoney = __spendMoney;
      __vipGiftFlag = row.getInteger("vip_gift_flag");
      _vipGiftFlag = __vipGiftFlag;
      __vipFlag = row.getInteger("vip_flag");
      _vipFlag = __vipFlag;
      __questionOne = row.getInteger("question_one");
      _questionOne = __questionOne;
      __answerOne = row.get("answer_one");
      _answerOne = __answerOne;
      __questionTwo = row.getInteger("question_two");
      _questionTwo = __questionTwo;
      __answerTwo = row.get("answer_two");
      _answerTwo = __answerTwo;
      __codePassword = row.get("code_password");
      _codePassword = __codePassword;
      __storageLastTime.parse(row.get("storage_last_time"));
      _storageLastTime.assign(__storageLastTime);
      __talkGanBegin.parse(row.get("talk_gan_begin"));
      _talkGanBegin.assign(__talkGanBegin);
      __talkGanEnd.parse(row.get("talk_gan_end"));
      _talkGanEnd.assign(__talkGanEnd);
      __roleFreeze = row.getInteger("role_freeze");
      _roleFreeze = __roleFreeze;
      __shizhuangShow = row.getInteger("shizhuang_show");
      _shizhuangShow = __shizhuangShow;
      __magicsWordTime.parse(row.get("magics_word_time"));
      _magicsWordTime.assign(__magicsWordTime);
      __surplusTime = row.getInteger("surplus_time");
      _surplusTime = __surplusTime;
      __isFirstGet = row.getInteger("is_first_get");
      _isFirstGet = __isFirstGet;
      __onlineToday = row.getInteger("online_today");
      _onlineToday = __onlineToday;
      __serverFlag = row.getInteger("server_flag");
      _serverFlag = __serverFlag;
      __swordSoul = row.getInteger("sword_soul");
      _swordSoul = __swordSoul;
      __masterChallengeLevel = row.getInteger("master_challenge_level");
      _masterChallengeLevel = __masterChallengeLevel;
      __masterChallengeLevelMax = row.getInteger("master_challenge_level_max");
      _masterChallengeLevelMax = __masterChallengeLevelMax;
      __lastInjectSoulTime.parse(row.get("last_inject_soul_time"));
      _lastInjectSoulTime.assign(__lastInjectSoulTime);
      __fourGodBloodGas = row.getInteger("four_god_blood_gas");
      _fourGodBloodGas = __fourGodBloodGas;
      __quickMasterChallengeTime.parse(row.get("quick_master_challenge_time"));
      _quickMasterChallengeTime.assign(__quickMasterChallengeTime);
      __addOnhookCount = row.getInteger("add_onhook_count");
      _addOnhookCount = __addOnhookCount;
      __metierArenaCdTime = row.getInteger("metier_arena_cd_time");
      _metierArenaCdTime = __metierArenaCdTime;
      __metierArenaTime.parse(row.get("metier_arena_time"));
      _metierArenaTime.assign(__metierArenaTime);
      __dragonBallGather = row.getInteger("dragon_ball_gather");
      _dragonBallGather = __dragonBallGather;
      __achievementPoint = row.getInteger("achievement_point");
      _achievementPoint = __achievementPoint;
      __achievementLevel = row.getInteger("achievement_level");
      _achievementLevel = __achievementLevel;
      __yellowReward = row.getInteger("yellow_reward");
      _yellowReward = __yellowReward;
      __yellowUpReward = row.getInteger("yellow_up_reward");
      _yellowUpReward = __yellowUpReward;
      __yellowRewardTime.parse(row.get("yellow_reward_time"));
      _yellowRewardTime.assign(__yellowRewardTime);
      __loginDay = row.getInteger("login_day");
      _loginDay = __loginDay;
      __loginGetItem = row.getInteger("login_get_item");
      _loginGetItem = __loginGetItem;
      __allRechargeRewardMail = row.getInteger("all_recharge_reward_mail");
      _allRechargeRewardMail = __allRechargeRewardMail;
      __roleDataId = RLong.parse(row.get("role_data_id"));
      _roleDataId = __roleDataId;
      __pfMailIds = row.get("pf_mail_ids");
      _pfMailIds = __pfMailIds;
      __marryId = RLong.parse(row.get("marry_id"));
      _marryId = __marryId;
      __quickCopyTime.parse(row.get("quick_copy_time"));
      _quickCopyTime.assign(__quickCopyTime);
      __quickCopyCd = row.getInteger("quick_copy_cd");
      _quickCopyCd = __quickCopyCd;
      __teamWarMark = row.getInteger("team_war_mark");
      _teamWarMark = __teamWarMark;
      __lastMurderTime.parse(row.get("last_murder_time"));
      _lastMurderTime.assign(__lastMurderTime);
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