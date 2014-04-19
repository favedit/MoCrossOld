package com.zqinet.logic.analysis.consume;

import com.zqinet.logic.data.analysis.FAnalysisLoggerMoneyLogic;
import com.zqinet.logic.data.analysis.FAnalysisLoggerMoneyUnit;
import com.zqinet.logic.data.analysis.FAnalysisLoggerMoneyhisLogic;
import com.zqinet.logic.data.analysis.FAnalysisLoggerMoneyhisUnit;
import com.zqinet.logic.data.analysis.FAnalysisServiceAlarmmLogic;
import com.zqinet.logic.data.analysis.FAnalysisServiceAlarmmUnit;
import com.zqinet.logic.data.analysis.FAnalysisServiceConsumeLogic;
import com.zqinet.logic.data.analysis.FAnalysisServiceConsumeUnit;
import com.zqinet.logic.data.analysis.FAnalysisServiceMoneyLogic;
import com.zqinet.logic.data.analysis.FAnalysisServiceMoneyUnit;
import com.zqinet.logic.data.analysis.FAnalysisServiceMoneyhisLogic;
import com.zqinet.logic.data.analysis.FAnalysisServiceMoneyhisUnit;
import com.zqinet.logic.data.analysis.FAnalysisServiceStatisticsLogic;
import com.zqinet.logic.data.analysis.FAnalysisServiceStatisticsUnit;
import com.zqinet.logic.data.logger.FLoggerItemsellLogic;
import com.zqinet.logic.data.logger.FLoggerItemsellUnit;
import com.zqinet.logic.data.logger.FLoggerMoneyLogic;
import com.zqinet.logic.data.logger.FLoggerMoneyUnbindLogic;
import com.zqinet.logic.data.logger.FLoggerMoneyUnbindUnit;
import com.zqinet.logic.data.logger.FLoggerMoneyUnit;

import org.mo.com.collections.FRow;
import org.mo.com.data.ESqlDataDirection;
import org.mo.com.data.ESqlDataType;
import org.mo.com.data.FSqlProcedure;
import org.mo.com.lang.RInteger;
import org.mo.com.lang.type.TDateTime;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.data.statistics.EStatisticsResult;
import org.mo.data.statistics.FStatisticsLogic;
import org.mo.data.synchronizer.FSynchronizerUnit;

public class FLogicConsumeStatistics
      extends FStatisticsLogic
      implements
         ILogicConsumeStatistics
{

   // 日志输出接口
   private static ILogger _logger = RLogger.find(FSynchronizerUnit.class);

   public static final int[] Operation_Valids = new int[]{42, 45, 46, 47, 49, 51, 50, 59, 62, 40, 57, 60, 61, 53, 22, 72, 73, 74, 75, 76, 79, 84, 87, 89, 91, 93};

   // 获得元宝枚举
   public static final int[] Operation_Add_Valids = new int[]{1, 3, 5, 6, 8, 24, 25, 27, 29, 31, 38, 41, 52, 54, 55, 56, 58, 63, 64, 65, 67, 70, 71};

   // 消耗元宝枚举
   public static final int[] Operation_reduce_Valids = new int[]{2, 4, 7, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 26, 28, 30, 32, 33, 34, 35, 36, 37, 39, 40, 42, 43, 44, 45, 46, 47, 49, 50, 51, 53, 57, 59, 60, 61, 62, 66, 68, 69, 72, 73};

   // 游戏编号
   protected long _gameId;

   // 每次计算量
   protected int _calculateLimit = 1000;

   // 消费分段时间段（2小时）
   protected int _consumtruncateSpan = 1000 * 60 * 60 * 2;

   // 金钱变化分段时间隔(10分钟)
   protected int _moneytruncateSpan = 1000 * 60 * 10;

   // 金钱历史分段时间隔（1天）
   protected int _moneyhistruncateSpan = 1000 * 60 * 60 * 24;

   // 计算离现在的时间段（最新数据间隔）
   protected int _calculageSpan = 60 * 10;

   // 目标表
   protected FAnalysisServiceConsumeLogic _consumeLogic;

   // 功能
   protected FLoggerMoneyLogic _moneyLogic;

   // 商城
   protected FLoggerItemsellLogic _itemSellLogic;

   // 登录
   protected FAnalysisServiceStatisticsLogic _statisticsLogic;

   // 金钱日志
   protected FAnalysisLoggerMoneyLogic _moneyLoggerLogic;

   // 金钱变化
   protected FAnalysisServiceMoneyLogic _anmoneyLogic;

   protected FAnalysisServiceMoneyhisLogic _moneyHisLogic;

   protected FAnalysisLoggerMoneyhisLogic _moneyHisLoggerLogic;

   protected FAnalysisServiceAlarmmLogic _alarmmLogic;

   protected FLoggerMoneyUnbindLogic _moneyUnbindLogic;

   //============================================================
   // <T>启动处理。</T>
   //============================================================
   @Override
   public void startup(){
      super.startup();
      _gameId = _statistics.parameters().getLong("game_id");
   }

   // ============================================================
   // <T>同步预警日志。</T>
   // ============================================================
   public FAnalysisServiceAlarmmUnit syncAlarmmUnit(long roleId,
                                                    TDateTime date){
            TDateTime recordDate = new TDateTime(date);
            recordDate.truncate(_moneytruncateSpan);
            //AS_SVC_ALARMM
            FAnalysisServiceAlarmmUnit alarmmUnit = _alarmmLogic.serach("ROLE_ID=" + roleId + " AND RECORD_DATE=STR_TO_DATE('" + recordDate.format() + "','%Y%m%d%H%i%s') AND GAME_ID=" + _gameId);
            if(null == alarmmUnit){
               alarmmUnit = new FAnalysisServiceAlarmmUnit();
               alarmmUnit.recordDate().assign(recordDate);
               
               alarmmUnit.recordHour().assign(recordDate);
               alarmmUnit.recordHour().truncateHour();
               
               alarmmUnit.recordDay().assign(recordDate);
               alarmmUnit.recordDay().truncateDay();
               
               alarmmUnit.recordWeek().assign(recordDate);
               alarmmUnit.recordWeek().truncateWeek();
               
               alarmmUnit.recordMonth().assign(recordDate);
               alarmmUnit.recordMonth().truncateMonth();
               
               alarmmUnit.setRecordInterval(_moneytruncateSpan);
               // 设置游戏编号
               alarmmUnit.setGameId(_gameId);
               // 设置角色id
               alarmmUnit.setRoleId(roleId);
               _alarmmLogic.doInsert(alarmmUnit);
            }
            return alarmmUnit;
      //return null;
   }

   // ============================================================
   // <T>同步消费日志。</T>
   // ============================================================
   public FAnalysisServiceConsumeUnit sysnConsumeUint(TDateTime date,
                                                      long roleId,
                                                      int itemTid,
                                                      int discountPrice,
                                                      int consumeCd,
                                                      int itemType){
            TDateTime recordDate = new TDateTime(date);
            recordDate.truncate(_consumtruncateSpan);
            //AS_SVC_CONSUME
            FAnalysisServiceConsumeUnit consumeUnit = _consumeLogic.serach("ROLE_ID=" + roleId + " AND ITEM_TID=" + itemTid + " AND CONSUME_CD=" + consumeCd + " AND RECORD_DATE=STR_TO_DATE('" + recordDate.format() + "','%Y%m%d%H%i%s') AND GAME_ID="
                  + _gameId);
            if(null == consumeUnit){
               // 创建记录
               consumeUnit = new FAnalysisServiceConsumeUnit();
               consumeUnit.recordDate().assign(recordDate);
               consumeUnit.recordHour().assign(recordDate);
               consumeUnit.recordHour().truncateHour();
               consumeUnit.recordDay().assign(recordDate);
               consumeUnit.recordDay().truncateDay();
               consumeUnit.recordWeek().assign(recordDate);
               consumeUnit.recordWeek().truncateWeek();
               consumeUnit.recordMonth().assign(recordDate);
               consumeUnit.recordMonth().truncateMonth();
               consumeUnit.setRecordInterval(_consumtruncateSpan);
               // 设置游戏编号
               consumeUnit.setGameId(_gameId);
               // 设置角色ID
               consumeUnit.setRoleId(roleId);
               // 设置物品类型
               consumeUnit.setItemType(itemType);
               // 设置物品TID
               consumeUnit.setItemTid(itemTid);
               // 设置物品单价
               consumeUnit.setDiscountPrice(discountPrice);
               // 设置交易类型
               consumeUnit.setConsumeCd(consumeCd);
               // 新建记录
               _consumeLogic.doInsert(consumeUnit);
            }
            return consumeUnit;
      //return null;
   }

   // 金钱变更同步
   public FAnalysisServiceMoneyUnit syncMoney(TDateTime date,
                                              long roleId){
            TDateTime recordDate = new TDateTime(date);
            recordDate.truncate(_moneytruncateSpan);// 10分钟
            //AS_SVC_MONEY
            FAnalysisServiceMoneyUnit moneyUnit = _anmoneyLogic.serach("ROLE_ID=" + roleId + " AND GAME_ID=" + _gameId);
            if(null == moneyUnit){
               moneyUnit = new FAnalysisServiceMoneyUnit();
               moneyUnit.recordDate().assign(recordDate);
               moneyUnit.recordHour().assign(recordDate);
               moneyUnit.recordHour().truncateHour();
               moneyUnit.recordDay().assign(recordDate);
               moneyUnit.recordDay().truncateDay();
               moneyUnit.recordWeek().assign(recordDate);
               moneyUnit.recordWeek().truncateWeek();
               moneyUnit.recordMonth().assign(recordDate);
               moneyUnit.recordMonth().truncateMonth();
               moneyUnit.setRecordInterval(_moneytruncateSpan);
               // 设置游戏编号
               moneyUnit.setGameId(_gameId);
               // 设置玩家编号
               moneyUnit.setRoleId(roleId);
               // 获得玩家名称
               //         FRow roleRow = _sourceConnection.find("SELECT LABEL FROM GM_ROLE WHERE OUID=" + roleId);
               //         // 设置玩家名称
               //         if(null != roleRow){
               //            moneyUnit.setRoleLabel(roleRow.get("label"));
               //         }else{
               //            moneyUnit.setRoleLabel("");
               //         }
      
               _anmoneyLogic.doInsert(moneyUnit);
            }
      
            return moneyUnit;
      //return null;
   }

   // ============================================================
   // <T>元宝消耗记录。</T>
   // ============================================================
   public FAnalysisLoggerMoneyUnit syncMoneyUnit(long roleId){
            // 检查有效性
            if(0 == roleId){
               return null;
            }
            // ............................................................AS_LOG_MONEY
            FAnalysisLoggerMoneyUnit moneyUnit = _moneyLoggerLogic.serach("ROLE_ID=" + roleId + " AND GAME_ID=" + _gameId);
            if(null == moneyUnit){
               moneyUnit = new FAnalysisLoggerMoneyUnit();
               moneyUnit.setRoleId(roleId);
               moneyUnit.setGameId(_gameId);
               _moneyLoggerLogic.doInsert(moneyUnit);
            }
            return moneyUnit;
      //return null;
   }

   // ============================================================
   // <T>历史元宝消费记录。</T>
   // ============================================================
   public FAnalysisServiceMoneyhisUnit syncMoneyHisUnit(TDateTime date){
            // 时间
            TDateTime recordDate = new TDateTime(date);
            recordDate.truncateDay();
            //AS_SVC_MONEYHIS
            FAnalysisServiceMoneyhisUnit moneyUnit = _moneyHisLogic.serach("RECORD_DATE=STR_TO_DATE('" + recordDate.format() + "','%Y%m%d%H%i%s') AND GAME_ID=" + _gameId);
            if(null == moneyUnit){
               // 时间设置
               moneyUnit = new FAnalysisServiceMoneyhisUnit();
               moneyUnit.recordDate().assign(recordDate);
               moneyUnit.recordHour().assign(recordDate);
               moneyUnit.recordHour().truncateHour();
               moneyUnit.recordDay().assign(recordDate);
               moneyUnit.recordDay().truncateDay();
               moneyUnit.recordWeek().assign(recordDate);
               moneyUnit.recordWeek().truncateWeek();
               moneyUnit.recordMonth().assign(recordDate);
               moneyUnit.recordMonth().truncateMonth();
               moneyUnit.setRecordInterval(_moneyhistruncateSpan);
               // 设置游戏编号
               moneyUnit.setGameId(_gameId);
               // 新建记录
               _moneyHisLogic.doInsert(moneyUnit);
            }
            return moneyUnit;
      //return null;
   }

   // ============================================================
   // <T>历史元宝消费记录。</T>
   // ============================================================
   public FAnalysisLoggerMoneyhisUnit syncLoggerMoneyhisUnit(long roleId,
                                                             TDateTime date){
            // 时间
            TDateTime recordDate = new TDateTime(date);
            recordDate.truncateDay();
            //AS_LOG_MONEYHIS
            FAnalysisLoggerMoneyhisUnit hisMoneyLoggicUnit = _moneyHisLoggerLogic.serach("RECORD_DATE=STR_TO_DATE('" + recordDate.format() + "','%Y%m%d%H%i%s') AND ROLE_ID=" + roleId + " AND GAME_ID=" + _gameId);
            if(null == hisMoneyLoggicUnit){
               hisMoneyLoggicUnit = new FAnalysisLoggerMoneyhisUnit();
               hisMoneyLoggicUnit.recordDate().assign(recordDate);
               // 设置游戏编号
               hisMoneyLoggicUnit.setGameId(_gameId);
               // 设置角色编号
               hisMoneyLoggicUnit.setRoleId(roleId);
               // 设置有效性
               hisMoneyLoggicUnit.setIsValid(0);
               // 更新
               _moneyHisLoggerLogic.doInsert(hisMoneyLoggicUnit);
            }
            return hisMoneyLoggicUnit;
      //return null;
   }

   // ============================================================
   // <T>逻辑计算处理。</T>
   // ============================================================
   public boolean calculateOld(){
      boolean result = false;
      // 获取链接
      _moneyLogic = new FLoggerMoneyLogic(_dataConnection);
      _itemSellLogic = new FLoggerItemsellLogic(_dataConnection);
      _moneyUnbindLogic = new FLoggerMoneyUnbindLogic(_dataConnection);
      _moneyHisLoggerLogic = new FAnalysisLoggerMoneyhisLogic(_dataConnection);
      _consumeLogic = new FAnalysisServiceConsumeLogic(_dataConnection);
      _statisticsLogic = new FAnalysisServiceStatisticsLogic(_dataConnection);
      _moneyLoggerLogic = new FAnalysisLoggerMoneyLogic(_dataConnection);
      _anmoneyLogic = new FAnalysisServiceMoneyLogic(_dataConnection);
      _moneyHisLogic = new FAnalysisServiceMoneyhisLogic(_dataConnection);
      _alarmmLogic = new FAnalysisServiceAlarmmLogic(_dataConnection);
      // 获取最大时间
      FRow itemsellRow = _dataConnection.find("SELECT MAX(CREATE_DATE) CREATE_MAX_DATE FROM LOG_ROLE_ITEMSELL");
      TDateTime createMaxDate = new TDateTime(itemsellRow.get("create_max_date"));
      FRow meoneyRow = _dataConnection.find("SELECT MAX(CREATE_DATE) CREATE_MAX_DATE FROM LOG_ROLE_MONEY");
      TDateTime mcreateMaxDate = new TDateTime(meoneyRow.get("create_max_date"));
      //<
      if(mcreateMaxDate.isBefore(createMaxDate)){
         createMaxDate = mcreateMaxDate;
      }
      String createMaxDateValue = null;
      if(createMaxDate.isEmpty()){
         createMaxDateValue = "NOW()-" + _calculageSpan;
      }else{
         createMaxDate.add(-(_calculageSpan * 1000));//-10分钟
         createMaxDateValue = "STR_TO_DATE('" + createMaxDate.format() + "','%Y%m%d%H%i%s')";
      }
      long sellMaxOuid = 0;
      long moneyMaxOuid = 0;
      long UnbindmoneyMaxOuid = 0;
      int validCount = 0;
      TDateTime time = null;

      // 获取未处理的商城记录LOG_ROLE_ITEMSELL
      FLoggerItemsellUnit[] itemsellUnits = _itemSellLogic.fetch("OVLD=0 AND CREATE_DATE<" + createMaxDateValue, null, _calculateLimit);
      if(0 < itemsellUnits.length){
    	  
         for(FLoggerItemsellUnit itemsell : itemsellUnits){
            // 获取记录信息
            long ouid = itemsell.ouid();
            long roleId = itemsell.roleId();
            int itemTid = itemsell.itemTid();
            int itemNum = itemsell.itemNum();
            int itemType = itemsell.itemType();
            int discountPrice = itemsell.discountPrice();
            int consumeValue = itemsell.totalPrice();
            int balance = itemsell.balance();
            int moneyType = itemsell.moneyType();
            TDateTime opTime = itemsell.opTime();
            TDateTime operationDate = new TDateTime(opTime);
            operationDate.truncate(_consumtruncateSpan);//两小时
            if(ouid > sellMaxOuid){
               sellMaxOuid = ouid;
            }
            // 检查货币类型有效性
            if(4 != moneyType){
               itemsell.setOvld(true);
               _itemSellLogic.doUpdate(itemsell, itemsell.ouid());
               continue;
            }
            // 同步商城消费AS_SVC_CONSUME
            FAnalysisServiceConsumeUnit consumeUnit = sysnConsumeUint(operationDate, roleId, itemTid, discountPrice, 8000, itemType);
            // 同步历史消费AS_SVC_MONEYHIS
            FAnalysisServiceMoneyhisUnit moneyhisUnit = syncMoneyHisUnit(operationDate);

            // 同步日志历史消费AS_LOG_MONEYHIS
            FAnalysisLoggerMoneyhisUnit moneyhisLoggerUnit = syncLoggerMoneyhisUnit(roleId, operationDate);
            // 判断玩家是否记录过
            if(0 == moneyhisLoggerUnit.isValid()){
               moneyhisUnit.setRoleCount(moneyhisUnit.roleCount() + 1);
            }
            moneyhisUnit.setConsumeCount(moneyhisUnit.consumeCount() + 1);
            moneyhisUnit.setConsumeValue(moneyhisUnit.consumeValue() + consumeValue);
            _moneyHisLogic.doUpdate(moneyhisUnit, moneyhisUnit.ouid());
            // 更新历史消费日志状态
            moneyhisLoggerUnit.setIsValid(1);
            _moneyHisLoggerLogic.doUpdate(moneyhisLoggerUnit, moneyhisLoggerUnit.ouid());

            // 更新玩家消费金额
            consumeUnit.setConsumeValue(consumeUnit.consumeValue() + consumeValue);
            // 更新玩家剩余金额
            consumeUnit.setBalanceValue(balance);
            // 更新道具数量
            consumeUnit.setItemNum(consumeUnit.itemNum() + itemNum);
            //
            _consumeLogic.doUpdate(consumeUnit, consumeUnit.ouid());
            result = true;
            // 更新商城记录已处理
            itemsell.setOvld(true);
            _itemSellLogic.doUpdate(itemsell, itemsell.ouid());
            validCount++;
         }
      }
      // 获取为处理的元宝记录LOG_ROLE_MONEY_UNBIND
      FLoggerMoneyUnbindUnit[] moneyUnbindUnits = _moneyUnbindLogic.fetch("OVLD=0 AND CREATE_DATE<" + createMaxDateValue, null, _calculateLimit);
      if(0 < moneyUnbindUnits.length){
         for(FLoggerMoneyUnbindUnit moneyUnbindUnit : moneyUnbindUnits){
            // 获取记录信息
            long ouid = moneyUnbindUnit.ouid();
            long roleId = moneyUnbindUnit.roleId();
            int consumeCd = moneyUnbindUnit.opType();
            int consumeValue = moneyUnbindUnit.deltaMoney();
            int balance = moneyUnbindUnit.curMoney();
            // 变更后金钱
            int deltaMoney = moneyUnbindUnit.deltaMoney();
            // 变更类型
            int opType = moneyUnbindUnit.opType();

            TDateTime opTime = moneyUnbindUnit.opTime();
            TDateTime operationDate = new TDateTime(opTime);
            if(null == time){
               time = new TDateTime(operationDate);
            }
            operationDate.truncate(_consumtruncateSpan);//截掉2小时
            if(ouid > UnbindmoneyMaxOuid){
               UnbindmoneyMaxOuid = ouid;
            }
            // 货币时间
            TDateTime operationMoneyDate = new TDateTime(opTime);
            operationMoneyDate.truncate(_moneytruncateSpan);//截掉10分钟
            
            // 同步日志金钱变化统计表 （包括所有货币类型）AS_SVC_MONEY
            FAnalysisServiceMoneyUnit anMoneyUnits = syncMoney(operationMoneyDate, roleId);
            
            //AS_SVC_ALARMM获取符合条件的日志分析金钱预警
            FAnalysisServiceAlarmmUnit alarmmUnit = syncAlarmmUnit(roleId, operationMoneyDate);
            
            // 判断操作类型，如果是获得元宝类型
            if(-1 != RInteger.indexOf(Operation_Add_Valids, opType)){
            	
               anMoneyUnits.setAddMoneyUnbind(anMoneyUnits.addMoneyUnbind() + deltaMoney);
               // 预警更新
               int oldValue = alarmmUnit.singleMaxCountMoney();
               if(deltaMoney > oldValue){
                  alarmmUnit.setSingleMaxCountMoney(deltaMoney);
               }
               alarmmUnit.setSumCountMoney(alarmmUnit.sumCountMoney() + 1);
               alarmmUnit.setSingleBalanceMoney(balance);
               _alarmmLogic.doUpdate(alarmmUnit, alarmmUnit.ouid());
               result = true;
            }
            //如果是 消耗元宝 类型 
            if(-1 != RInteger.indexOf(Operation_reduce_Valids, opType)){
               anMoneyUnits.setReduceMoneyUnbind(anMoneyUnits.reduceMoneyUnbind() + deltaMoney);
            }
            // 更新同步记录
            _anmoneyLogic.doUpdate(anMoneyUnits, anMoneyUnits.ouid());
            
            // 玩家日志分析金钱更新AS_LOG_MONEY
            FAnalysisLoggerMoneyUnit anmoneyUnit = syncMoneyUnit(roleId);
            
            // 玩家剩余元宝
            anmoneyUnit.setCurrentMoney(balance);
            
            // 判断操作类型
            if(-1 != RInteger.indexOf(Operation_Add_Valids, opType)){
               anmoneyUnit.setAddMoney(anmoneyUnit.addMoney() + deltaMoney);
            }
            
            if(-1 != RInteger.indexOf(Operation_reduce_Valids, opType)){
               anmoneyUnit.setReduceMoney(anmoneyUnit.reduceMoney() + deltaMoney);
            }
            
            _moneyLoggerLogic.doUpdate(anmoneyUnit, anmoneyUnit.ouid());
            
            // 检查花费类型有效性
            if(-1 == RInteger.indexOf(Operation_Valids, consumeCd)){
               moneyUnbindUnit.setOvld(true);
               _moneyUnbindLogic.doUpdate(moneyUnbindUnit, moneyUnbindUnit.ouid());
               continue;
            }

            // 同步历史消费AS_SVC_MONEYHIS
            FAnalysisServiceMoneyhisUnit moneyhisUnit = syncMoneyHisUnit(operationDate);
            
            // 同步日志历史消费AS_LOG_MONEYHIS
            FAnalysisLoggerMoneyhisUnit moneyhisLoggerUnit = syncLoggerMoneyhisUnit(roleId, operationDate);
            // 判断玩家是否记录过
            if(0 == moneyhisLoggerUnit.isValid()){
               moneyhisUnit.setRoleCount(moneyhisUnit.roleCount() + 1);
            }
            moneyhisUnit.setConsumeCount(moneyhisUnit.consumeCount() + 1);
            moneyhisUnit.setConsumeValue(moneyhisUnit.consumeValue() + consumeValue);
            _moneyHisLogic.doUpdate(moneyhisUnit, moneyhisUnit.ouid());
            // 更新历史消费日志状态
            moneyhisLoggerUnit.setIsValid(1);
            _moneyHisLoggerLogic.doUpdate(moneyhisLoggerUnit, moneyhisLoggerUnit.ouid());

            // 玩家消费AS_SVC_CONSUME
            FAnalysisServiceConsumeUnit consumeUnit = sysnConsumeUint(operationDate, roleId, 0, 0, consumeCd, 0);

            // 更新玩家消费金额
            consumeUnit.setConsumeValue(consumeUnit.consumeValue() + consumeValue);
            // 更新玩家剩余金额
            consumeUnit.setBalanceValue(balance);
            //
            _consumeLogic.doUpdate(consumeUnit, consumeUnit.ouid());
            result = true;
            // 更新一处理
            moneyUnbindUnit.setOvld(true);
            _moneyUnbindLogic.doUpdate(moneyUnbindUnit, moneyUnbindUnit.ouid());
            validCount++;
         }
      }

      // 获取未处理角色金币记录LOG_ROLE_MONEY
      FLoggerMoneyUnit[] moneyUnits = _moneyLogic.fetch("OVLD=0 AND CREATE_DATE<" + createMaxDateValue, null, _calculateLimit);
      if(0 < moneyUnits.length){
         for(FLoggerMoneyUnit moneyUnit : moneyUnits){
            // 获取记录信息
            long ouid = moneyUnit.ouid();
            long roleId = moneyUnit.roleId();
            int balance = moneyUnit.curMoney();
            int moneyType = moneyUnit.moneyType();
            // 变更后金钱
            int deltaMoney = moneyUnit.deltaMoney();
            // 变更类型
            int opType = moneyUnit.opType();

            TDateTime opTime = moneyUnit.opTime();
            TDateTime operationDate = new TDateTime(opTime);
            if(null == time){
               time = new TDateTime(operationDate);
            }
            operationDate.truncate(_consumtruncateSpan);//截取2小时
            if(ouid > moneyMaxOuid){
               moneyMaxOuid = ouid;
            }
            // 货币时间
            TDateTime operationMoneyDate = new TDateTime(opTime);
            operationMoneyDate.truncate(_moneytruncateSpan);//10分
            // 同步日志金钱变化统计表 （包括所有货币类型）AS_SVC_MONEY
            FAnalysisServiceMoneyUnit anMoneyUnits = syncMoney(operationMoneyDate, roleId);
            //AS_SVC_ALARMM
            FAnalysisServiceAlarmmUnit alarmmUnit = syncAlarmmUnit(roleId, operationMoneyDate);

            // 判断货币类型 1 绑定金 2非绑定金 3绑定元宝 4非绑定元宝
            if(1 == moneyType){
               // 判断操作类型
               if(-1 != RInteger.indexOf(Operation_Add_Valids, opType)){
                  anMoneyUnits.setAddGoldBind(anMoneyUnits.addGoldBind() + deltaMoney);
                  // 更新预警
                  int oldValue = alarmmUnit.singleMaxCountBindGold();
                  if(deltaMoney > oldValue){
                     alarmmUnit.setSingleMaxCountBindGold(deltaMoney);
                  }
                  alarmmUnit.setSumCountBindGold(alarmmUnit.sumCountBindGold() + 1);
                  alarmmUnit.setSingleBalanceBindGold(balance);
                  _alarmmLogic.doUpdate(alarmmUnit, alarmmUnit.ouid());
                  result = true;
               }
               if(-1 != RInteger.indexOf(Operation_reduce_Valids, opType)){
                  anMoneyUnits.setReduceGoldBind(anMoneyUnits.reduceGoldBind() + deltaMoney);
               }

            }else if(2 == moneyType){
               // 判断操作类型
               if(-1 != RInteger.indexOf(Operation_Add_Valids, opType)){
                  anMoneyUnits.setAddGoldUnbind(anMoneyUnits.addGoldUnbind() + deltaMoney);
                  // 更新预警
                  int oldValue = alarmmUnit.singleMaxCountGold();
                  if(deltaMoney > oldValue){
                     alarmmUnit.setSingleMaxCountGold(deltaMoney);
                  }
                  alarmmUnit.setSumCountGold(alarmmUnit.sumCountGold() + 1);
                  alarmmUnit.setSingleBalanceGold(balance);
                  _alarmmLogic.doUpdate(alarmmUnit, alarmmUnit.ouid());
                  result = true;
               }
               if(-1 != RInteger.indexOf(Operation_reduce_Valids, opType)){
                  anMoneyUnits.setReduceGoldUnbind(anMoneyUnits.reduceGoldUnbind() + deltaMoney);
               }

            }else if(3 == moneyType){
               // 判断操作类型
               if(-1 != RInteger.indexOf(Operation_Add_Valids, opType)){
                  anMoneyUnits.setAddMoneyBind(anMoneyUnits.addMoneyBind() + deltaMoney);
                  // 预警更新
                  int oldValue = alarmmUnit.singleMaxCountBindMoney();
                  if(deltaMoney > oldValue){
                     alarmmUnit.setSingleMaxCountBindMoney(deltaMoney);
                  }
                  alarmmUnit.setSumCountBindMoney(alarmmUnit.sumCountBindMoney() + 1);
                  alarmmUnit.setSingleBalanceBindMoney(balance);
                  _alarmmLogic.doUpdate(alarmmUnit, alarmmUnit.ouid());
                  result = true;
               }
               if(-1 != RInteger.indexOf(Operation_reduce_Valids, opType)){
                  anMoneyUnits.setReduceMoneyBind(anMoneyUnits.reduceMoneyBind() + deltaMoney);
               }

            }
            // 更新同步记录
            _anmoneyLogic.doUpdate(anMoneyUnits, anMoneyUnits.ouid());

            // 检查货币有效性
            moneyUnit.setOvld(true);
            _moneyLogic.doUpdate(moneyUnit, moneyUnit.ouid());
            continue;
         }
      }
      // 更新玩家
      FRow currenMoneyCount = _dataConnection.find("SELECT SUM(CURRENT_MONEY) FROM AS_LOG_MONEY WHERE GAME_ID=" + _gameId);
      FRow reduceMoneyCount = _dataConnection.find("SELECT SUM(REDUCE_MONEY) FROM AS_LOG_MONEY WHERE GAME_ID=" + _gameId);
      // 查找记录更新
      if(null != time){
         time.truncate(_calculageSpan * 1000);
         //AS_SVC_STATISTICS
         FAnalysisServiceStatisticsUnit statisticsUnit = _statisticsLogic.serach("RECORD_DATE=STR_TO_DATE('" + time.format() + "','%Y%m%d%H%i%s') AND GAME_ID=" + _gameId);
         if(null != statisticsUnit){
            statisticsUnit.setRemainMoney(currenMoneyCount.getInt("sum(current_money)"));
            statisticsUnit.setConsumeMoney(reduceMoneyCount.getInt("sum(reduce_money)"));
            _statisticsLogic.doUpdate(statisticsUnit, statisticsUnit.ouid());
         }
      }
      _logger.info(this, "calculate", "Calculate service cousume. (count={1}, valid={2}, role_sell_max_ouid={3}, role_money_max_ouid={4},game_id={5})", itemsellUnits.length + moneyUnits.length, validCount, sellMaxOuid, moneyMaxOuid, _gameId);
      return result;
   }

   //============================================================
   // <T>计算处理。</T>
   //
   // @return 统计结果
   //============================================================
   @Override
   public EStatisticsResult calculate(){
	   //calculateOld();
	   /*FSqlProcedure procedure = new FSqlProcedure("Logic_Consume_Statistics");
	   procedure.createParameter("game_id_",  _gameId, ESqlDataType.Integer, ESqlDataDirection.In);
	   procedure.createParameter("count_", 0, ESqlDataType.Integer, ESqlDataDirection.Out);
	   _dataConnection.execute(procedure);
	   // 获得内容
	   int count = procedure.parameter("count_").asInt();
	   if(count > 0){
	      return EStatisticsResult.Continue;
	   }*/
      return EStatisticsResult.Success;
   }
}
