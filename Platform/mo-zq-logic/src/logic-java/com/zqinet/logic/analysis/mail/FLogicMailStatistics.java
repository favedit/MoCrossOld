package com.zqinet.logic.analysis.mail;

import com.zqinet.logic.data.analysis.FAnalysisServiceAlarmmLogic;
import com.zqinet.logic.data.analysis.FAnalysisServiceAlarmmUnit;
import com.zqinet.logic.data.analysis.FAnalysisServiceAlarmpLogic;
import com.zqinet.logic.data.analysis.FAnalysisServiceAlarmpUnit;
import com.zqinet.logic.data.game.FGameTemplateAlarmItemLogic;
import com.zqinet.logic.data.game.FGameTemplateAlarmItemUnit;

import org.mo.com.collections.FRow;
import org.mo.com.lang.type.TDateTime;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.com.net.mail.FMail;
import org.mo.com.net.mail.FMailSession;
import org.mo.com.net.mail.RMail;
import org.mo.com.net.mail.TMailProperties;
import org.mo.data.statistics.EStatisticsResult;
import org.mo.data.statistics.FStatisticsLogic;
import org.mo.data.synchronizer.FSynchronizerUnit;

public class FLogicMailStatistics
      extends FStatisticsLogic
      implements
         ILogicMailStatistics
{
   // 日志输出接口
   private static ILogger _logger = RLogger.find(FSynchronizerUnit.class);

   // 每次计算量
   protected int _calculateLimit = 1000;

   // 金钱变化分段时间隔(10分钟)
   protected int _proptruncateSpan = 1000 * 60 * 10;

   // 计算里现在的时间段（最新数据间隔）
   protected int _calculageSpan = 60 * 10;

   // 道具
   protected FAnalysisServiceAlarmpLogic _alarmpLogic;

   // 金钱
   protected FAnalysisServiceAlarmmLogic _alarmmLogic;

   //protected FGameTemplateAlarmItemLogic _tplAlarmItemLogic;

   // 元宝定义
   protected static final int MONEY_VALUE = 10000;

   // 绑定元宝定义
   protected static final int BIND_MONEY_VALUE = 15400;

   // 银两定义
   protected static final int GOLD_VALUE = 50000000;

   // 绑定银两定义
   protected static final int BIND_GOLD_VALUE = 50000000;

   // 斗法定义
   protected static final int TOURNAMENT_VALUE = 5000;

   // 帮贡定义
   protected static final int GANG_VALUE = 50000;

   // 门贡定义
   protected static final int METIER_VALUE = 5000;

   // 逐鹿定义
   protected static final int SOCIETY_VALUE = 5000;

   // 经验定义
   protected static final int EXP_VALUE = 99999999;

   public void SendMail(String subject,
                        String value,
                        String to){
      TMailProperties properties = new TMailProperties();
      // 服务器地址
      properties.setHost("smtp.ym.163.com");
      // 发送端口
      properties.setPort(25);
      // 是否需要身份认证
      properties.setAuthentic(true);
      // 用户名
      properties.setLoginPassport("yypt@zqinet.com");
      // 密码
      properties.setLoginPassword("yunyingpingtai");
      FMail fm = new FMail();
      // 邮件接收者的地址
      fm.addCc(to);
      // 内容
      fm.setBody(value);
      // 标题
      fm.setSubject(subject);
      fm.setFrom("yypt@zqinet.com");
      FMailSession ms = RMail.findSession(properties);
      ms.send(fm);
   }

   public boolean calculateOld(){
      boolean result = false;
//            _tplAlarmItemLogic = new FGameTemplateAlarmItemLogic(_sourceConnection);
//            _alarmpLogic = new FAnalysisServiceAlarmpLogic(_targetConnection);
//            _alarmmLogic = new FAnalysisServiceAlarmmLogic(_targetConnection);
//      
//            // 获取最大时间
//            FRow pRow = _targetConnection.find("SELECT MAX(CREATE_DATE) CREATE_MAX_DATE FROM AS_SVC_ALARMP");
//            FRow mRow = _targetConnection.find("SELECT MAX(CREATE_DATE) CREATE_MAX_DATE FROM AS_SVC_ALARMM");
//      
//            TDateTime createMaxDate = new TDateTime(pRow.get("create_max_date"));
//      
//            TDateTime mcreateMaxDate = new TDateTime(mRow.get("create_max_date"));
//      
//            if(mcreateMaxDate.isBefore(createMaxDate)){
//               createMaxDate = mcreateMaxDate;
//            }
//      
//            String createMaxDateValue = null;
//            if(createMaxDate.isEmpty()){
//               createMaxDateValue = "NOW()-" + _calculageSpan;
//            }else{
//               createMaxDate.add(-(_calculageSpan * 1000));
//               createMaxDateValue = "STR_TO_DATE('" + createMaxDate.format() + "','%Y%m%d%H%i%s')";
//            }
//      
//            // 处理到最大id
//            long itemMaxOuid = 0;
//            long basicMaxOuid = 0;
//            // 处理次数
//            int validCount = 0;
//      
//            // 金钱数据处理
//            FAnalysisServiceAlarmmUnit[] alarmmUnits = _alarmmLogic.fetch("OVLD=0 AND CREATE_DATE<" + createMaxDateValue, null, _calculateLimit);
//      
//            if(0 != alarmmUnits.length){
//      
//               for(FAnalysisServiceAlarmmUnit alarmmUnit : alarmmUnits){
//      
//                  // 获取标识
//                  long ouid = alarmmUnit.ouid();
//                  // 获取用户编号
//                  long roleId = alarmmUnit.roleId();
//                  // 获取服务器编号
//                  long gameId = alarmmUnit.gameId();
//                  // 元宝
//                  int money = alarmmUnit.singleMaxCountMoney();
//                  // 绑定元宝
//                  int bindMoney = alarmmUnit.singleMaxCountBindMoney();
//                  // 银两
//                  int gold = alarmmUnit.singleMaxCountGold();
//                  // 绑定银两
//                  int bindGold = alarmmUnit.singleMaxCountBindGold();
//                  // 斗法
//                  int tournament = alarmmUnit.singleMaxCountTournament();
//                  // 逐鹿
//                  int society = alarmmUnit.singleMaxCountSociety();
//                  // 门贡
//                  int metier = alarmmUnit.singleMaxCountMetier();
//                  // 帮贡
//                  int gang = alarmmUnit.singleMaxCountGang();
//                  // 帮贡
//                  int exp = alarmmUnit.singleMaxCountExp();
//      
//                  TDateTime recordDate = alarmmUnit.recordDate();
//      
//                  String beginRecordDateStr = recordDate.format("YYYY/MM/DD HH24:MI:SS");
//      
//                  long mis = 1000 * 10 * 60;
//      
//                  long record = recordDate.get() - mis;
//      
//                  TDateTime endRecord = new TDateTime(record);
//      
//                  String endRecordDateStr = endRecord.format("YYYY/MM/DD HH24:MI:SS");
//      
//                  if(ouid > basicMaxOuid){
//                     basicMaxOuid = ouid;
//                  }
//      
//                  // 元宝
//                  if(MONEY_VALUE < money){
//      
//                     String message = gameId + "服务器，玩家ID：" + roleId + " 在" + endRecordDateStr + "至" + beginRecordDateStr + "获得元宝：" + money + ", 超过阈值,请查看该玩家数据是否正常.";
//                     // 发送邮件
//                     SendMail("元宝预警", message, "991401619@qq.com");
//                     SendMail("元宝预警", message, "2239886020@qq.com");
//                     SendMail("元宝预警", message, "1106716919@qq.com");
//                  }
//      
//                  // 绑定元宝
//                  if(BIND_MONEY_VALUE < bindMoney){
//                     String message = gameId + "服务器，玩家ID：" + roleId + " 在" + endRecordDateStr + "至" + beginRecordDateStr + " 获得绑定元包:" + bindMoney + ", 超过阈值,请查看该玩家数据是否正常.";
//                     // 发送邮件
//                     SendMail("绑定元宝预警", message, "991401619@qq.com");
//                     SendMail("绑定元宝预警", message, "2239886020@qq.com");
//                     SendMail("绑定元宝预警", message, "1106716919@qq.com");
//                  }
//      
//                  // 银两
//                  if(GOLD_VALUE < gold){
//      
//                     String message = gameId + "服务器，玩家ID：" + roleId + " 在" + endRecordDateStr + "至" + beginRecordDateStr + " 获得银两量：" + gold + ",超过阈值,请查看该玩家数据是否正常";
//                     // 发送邮件
//                     SendMail("银两预警", message, "991401619@qq.com");
//                     SendMail("银两预警", message, "2239886020@qq.com");
//                     SendMail("银两预警", message, "1106716919@qq.com");
//                  }
//      
//                  // 绑定银两
//                  if(BIND_GOLD_VALUE < bindGold){
//                     String message = gameId + "服务器，玩家ID：" + roleId + " 在" + endRecordDateStr + "至" + beginRecordDateStr + " 获得绑定银两：" + bindGold + ",超过阈值,请查看该玩家数据是否正常";
//                     // 发送邮件
//                     SendMail("绑定银两预警", message, "991401619@qq.com");
//                     SendMail("绑定银两预警", message, "2239886020@qq.com");
//                     SendMail("绑定银两预警", message, "1106716919@qq.com");
//                  }
//      
//                  // 斗法
//                  if(TOURNAMENT_VALUE < tournament){
//      
//                     String message = gameId + "服务器，玩家ID：" + roleId + " 在" + endRecordDateStr + "至" + beginRecordDateStr + " 获得斗法：" + tournament + ",超过阈值,请查看该玩家数据是否正常";
//                     // 发送邮件
//                     SendMail("斗法预警", message, "991401619@qq.com");
//                     SendMail("斗法预警", message, "2239886020@qq.com");
//                     SendMail("斗法预警", message, "1106716919@qq.com");
//                  }
//      
//                  // 帮贡
//                  if(GANG_VALUE < gang){
//                     String message = gameId + "服务器，玩家ID：" + roleId + " 在" + endRecordDateStr + "至" + beginRecordDateStr + " 获得帮贡量：" + gang + ",超过阈值,请查看该玩家数据是否正常";
//                     // 发送邮件
//                     SendMail("帮贡预警", message, "991401619@qq.com");
//                     SendMail("帮贡预警", message, "2239886020@qq.com");
//                     SendMail("帮贡-预警", message, "1106716919@qq.com");
//                  }
//      
//                  // 门贡
//                  if(METIER_VALUE < metier){
//                     String message = gameId + "服务器，玩家ID：" + roleId + " 在" + endRecordDateStr + "至" + beginRecordDateStr + " 获得门贡量：" + metier + ",超过阈值,请查看该玩家数据是否正常";
//                     // 发送邮件
//                     SendMail("门贡预警", message, "991401619@qq.com");
//                     SendMail("门贡预警", message, "2239886020@qq.com");
//                     SendMail("门贡-预警", message, "1106716919@qq.com");
//                  }
//      
//                  // 逐鹿
//                  if(SOCIETY_VALUE < society){
//                     String message = gameId + "服务器，玩家ID：" + roleId + " 在" + endRecordDateStr + "至" + beginRecordDateStr + " 获得逐鹿量：" + society + ",超过阈值,请查看该玩家数据是否正常";
//                     // 发送邮件
//                     SendMail("逐鹿预警", message, "991401619@qq.com");
//                     SendMail("逐鹿预警", message, "2239886020@qq.com");
//                     SendMail("逐鹿预警", message, "1106716919@qq.com");
//                  }
//      
//                  // 经验
//                  if(EXP_VALUE < exp){
//      
//                     String message = gameId + "服务器，玩家ID：" + roleId + " 在" + endRecordDateStr + "至" + beginRecordDateStr + " 获得经验量：" + exp + ",超过阈值,请查看该玩家数据是否正常";
//                     // 发送邮件
//                     SendMail("经验预警", message, "991401619@qq.com");
//                     SendMail("经验预警", message, "2239886020@qq.com");
//                     SendMail("经验预警", message, "1106716919@qq.com");
//                  }
//                  result = true;
//                  alarmmUnit.setOvld(true);
//                  _alarmmLogic.doUpdate(alarmmUnit, alarmmUnit.ouid());
//                  validCount++;
//               }
//            }
//            // 道具金钱处理
//            FAnalysisServiceAlarmpUnit[] alarmpUnits = _alarmpLogic.fetch("OVLD=0 AND CREATE_DATE<" + createMaxDateValue, null, _calculateLimit);
//            if(0 < alarmpUnits.length){
//               for(FAnalysisServiceAlarmpUnit alarmpUnit : alarmpUnits){
//                  long ouid = alarmpUnit.ouid();
//                  // 游戏编号 
//                  long gameId = alarmpUnit.gameId();
//                  // 角色编号
//                  long roleId = alarmpUnit.roleId();
//                  // 物品编号
//                  int itemTid = alarmpUnit.itemTid();
//                  // 物品类型
//                  int itemType = alarmpUnit.itemType();
//                  // 物品类型名称
//                  String itemTypeLabel = getType(itemType);
//                  // 单次获得最大量
//                  int singleMaxCount = alarmpUnit.singleMaxCount();
//                  // 获得时间
//                  TDateTime recordDate = alarmpUnit.recordDate();
//      
//                  String beginRecordDateStr = recordDate.format("YYYY/MM/DD HH24:MI:SS");
//                  long mis = 1000 * 10 * 60;
//      
//                  long record = recordDate.get() - mis;
//      
//                  TDateTime endRecord = new TDateTime(record);
//      
//                  String endRecordDateStr = endRecord.format("YYYY/MM/DD HH24:MI:SS");
//      
//                  if(ouid > itemMaxOuid){
//                     itemMaxOuid = ouid;
//                  }
//      
//                  FGameTemplateAlarmItemUnit itemUnit = _tplAlarmItemLogic.serach(" OUID=" + itemTid);
//                  if(null == itemUnit){
//                     alarmpUnit.setOvld(true);
//                     _alarmpLogic.doUpdate(alarmpUnit, alarmpUnit.ouid());
//                     continue;
//                  }
//                  int maxCount = itemUnit.singleMaxCount();
//                  if(singleMaxCount > maxCount){
//                     String message = gameId + "服务器，玩家ID：" + roleId + " 在" + endRecordDateStr + "至" + beginRecordDateStr + " 获得" + itemTypeLabel + "量：" + singleMaxCount + ",超过阈值,请查看该玩家数据是否正常";
//                     // 发送邮件
//                     SendMail(itemTypeLabel + "预警", message, "991401619@qq.com");
//                     SendMail(itemTypeLabel + "预警", message, "2239886020@qq.com");
//                     SendMail(itemTypeLabel + "预警", message, "1106716919@qq.com");
//                  }
//                  result = true;
//                  alarmpUnit.setOvld(true);
//                  _alarmpLogic.doUpdate(alarmpUnit, alarmpUnit.ouid());
//                  validCount++;
//               }
//            }
//            _logger.info(this, "calculate", "Calculate alarm item. (count={1}, valid={2}, basic_max_ouid={3}, item_max_ouid={4})", alarmmUnits.length + alarmpUnits.length, validCount, basicMaxOuid, itemMaxOuid);
      return result;
   }

   public String getType(int itemType){
      switch(itemType){
         case 2:
            return "道具";
         case 3:
            return "装备";
         default:
            return "未知";
      }
   }

   //============================================================
   // <T>计算处理。</T>
   //
   // @return 统计结果
   //============================================================
   @Override
   public EStatisticsResult calculate(){
	   //calculateOld();
      return EStatisticsResult.Success;
   }
}
