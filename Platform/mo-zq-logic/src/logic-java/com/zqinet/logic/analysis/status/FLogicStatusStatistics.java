package com.zqinet.logic.analysis.status;

import com.zqinet.logic.analysis.common.FLogicAnalysisStatistics;
import com.zqinet.logic.data.analysis.FAnalysisLoggerLoginLogic;
import com.zqinet.logic.data.analysis.FAnalysisLoggerLoginUnit;
import com.zqinet.logic.data.analysis.FAnalysisServiceStatisticsLogic;
import com.zqinet.logic.data.analysis.FAnalysisServiceStatisticsUnit;
import com.zqinet.logic.data.game.FGameAccountLogic;
import com.zqinet.logic.data.game.FGameAccountUnit;
import com.zqinet.logic.data.game.FGameRoleLogic;
import com.zqinet.logic.data.game.FGameRoleUnit;
import com.zqinet.logic.data.logger.FLoggerLoginLogic;
import com.zqinet.logic.data.logger.FLoggerLoginUnit;
import org.mo.com.collections.FRow;
import org.mo.com.data.ESqlDataDirection;
import org.mo.com.data.ESqlDataType;
import org.mo.com.data.FSql;
import org.mo.com.data.FSqlProcedure;
import org.mo.com.lang.RInteger;
import org.mo.com.lang.type.TDateTime;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.core.aop.face.AProperty;
import org.mo.data.statistics.EStatisticsResult;
import org.mo.data.synchronizer.FSynchronizerUnit;

//============================================================
// <T>服务信息间隔统计。</T>
//============================================================
public class FLogicStatusStatistics
      extends FLogicAnalysisStatistics
      implements
         ILogicStatusStatistics
{
   // 日志输出接口
   private static ILogger _logger = RLogger.find(FSynchronizerUnit.class);

   public static final int Operation_AccountCreate = 9;

   public static final int Operation_RoleCreating = 7;

   public static final int Operation_RoleCreate = 2;

   public static final int Operation_RoleLoad = 6;

   public static final int Operation_RoleLogin = 8;

   public static final int[] Operation_Valids = new int[]{Operation_AccountCreate, Operation_RoleCreating, Operation_RoleCreate, Operation_RoleLoad, Operation_RoleLogin};

   // 每次计算间隔
   @AProperty
   protected int _calculateInterval;

   // 每次计算量
   protected int _calculateLimit = 1000;

   // 分割时间段（10分钟）
   protected int _truncateSpan = 1000 * 60 * 10;

   // 计算离现在的时间段（最新数据间隔）
   protected int _calculageSpan = 60 * 5;

   protected FAnalysisServiceStatisticsLogic _statisticsLogic;

   protected FAnalysisLoggerLoginLogic _loginLogic;

   protected FGameAccountLogic _accountLogic;

   protected FGameRoleLogic _roleLogic;

   //============================================================
   // <T>获得当前时间的服务器变量内容。</T>
   //
   // @param date 日期
   // @param key 名称
   // @return 变量内容
   //============================================================
   public int findServerStatus(TDateTime date,
                               String key){
      TDateTime beginDate = new TDateTime(date);
      beginDate.truncate(_truncateSpan);
      TDateTime endDate = new TDateTime(date);
      endDate.add(_truncateSpan);
      // 生成命令
      FSql sql = new FSql("SELECT MAX(`VALUE`) ACCOUNT_CREATE_COUNT FROM LOG_SERVER_STATUS WHERE `KEY`='" + key + "'");
      sql.append(" AND CREATE_DATE >= STR_TO_DATE('" + beginDate.format() + "','%Y%m%d%H%i%s')");
      sql.append(" AND CREATE_DATE < STR_TO_DATE('" + endDate.format() + "','%Y%m%d%H%i%s')");
      // 获得结果
      return _dataConnection.executeInteger(sql.toString());
   }

   //============================================================
   // <T>同步统计记录。</T>
   //
   // @param 日期
   //============================================================
   public FAnalysisServiceStatisticsUnit syncStatisticsUnit(TDateTime date){
      // 获得时间
      TDateTime recordDate = new TDateTime(date);
      recordDate.truncate(_truncateSpan);
      //............................................................AS_SVC_STATISTICS
      FAnalysisServiceStatisticsUnit statisticsUnit = _statisticsLogic.serach("RECORD_DATE=STR_TO_DATE('" + recordDate.format() + "','%Y%m%d%H%i%s') AND GAME_ID=" + _gameId);
      if(null == statisticsUnit){
         // 创建记录
         statisticsUnit = new FAnalysisServiceStatisticsUnit();
         statisticsUnit.recordDate().assign(recordDate);
         statisticsUnit.recordHour().assign(recordDate);
         statisticsUnit.recordHour().truncateHour();
         statisticsUnit.recordDay().assign(recordDate);
         statisticsUnit.recordDay().truncateDay();
         statisticsUnit.recordWeek().assign(recordDate);
         statisticsUnit.recordWeek().truncateWeek();
         statisticsUnit.recordMonth().assign(recordDate);
         statisticsUnit.recordMonth().truncateMonth();
         statisticsUnit.setRecordInterval(_truncateSpan);
         // 设置游戏编号
         statisticsUnit.setGameId(_gameId);
         // 获得用户创建时间
         int onlineCount = findServerStatus(recordDate, "OnlineNum");
         statisticsUnit.setRoleOnlineCount(onlineCount);
         int hostCount = findServerStatus(recordDate, "HostNum");
         statisticsUnit.setRoleOnlineHostCount(hostCount);
         // 新建记录
         _statisticsLogic.doInsert(statisticsUnit);
      }
      return statisticsUnit;
   }

   //============================================================
   // <T>同步登录日志。</T>
   //============================================================
   public FAnalysisLoggerLoginUnit syncLoginUnit(long accountId,
                                                 FGameAccountUnit accountUnit,
                                                 FGameRoleUnit roleUnit,
                                                 TDateTime recordDate){
      // 检查参数
      if(0 == accountId){
         return null;
      }
      //............................................................
      TDateTime currentDay = new TDateTime(recordDate);
      currentDay.truncateDay();
      //............................................................AS_LOG_LOGIN
      String whereSql = "ACCOUNT_ID=" + accountId + " AND DATE_FORMAT(RECORD_DAY, '%Y%m%d')='" + currentDay.format("YYYYMMDD") + "' AND GAME_ID=" + _gameId;
      FAnalysisLoggerLoginUnit loginUnit = _loginLogic.serach(whereSql);
      if(null == loginUnit){
         loginUnit = new FAnalysisLoggerLoginUnit();
         loginUnit.recordDate().assign(recordDate);
         loginUnit.recordDay().assign(currentDay);
         loginUnit.setGameId(_gameId);
         loginUnit.setAccountId(accountId);
         // 新建记录
         _loginLogic.doInsert(loginUnit);
      }
      //............................................................
      boolean changed = false;
      // 查询账号创建时间
      if(loginUnit.accountCreateDate().isEmpty()){
         FSql sql = new FSql("SELECT MIN(OP_TIME) OP_MIN_TIME FROM LOG_ROLE_LOGIN");
         sql.append(" WHERE ACCOUNT_ID=" + accountId + " AND OP_TYPE=" + Operation_AccountCreate);
         String datetimeValue = _dataConnection.executeScalar(sql.toString());
         if(null != datetimeValue){
            loginUnit.accountCreateDate().parse(datetimeValue);
            changed = true;
         }
      }
      // 查询角色创建中时间
      if(loginUnit.roleCreatingDate().isEmpty()){
         FSql sql = new FSql("SELECT MIN(OP_TIME) OP_MIN_TIME FROM LOG_ROLE_LOGIN");
         sql.append(" WHERE ACCOUNT_ID=" + accountId + " AND OP_TYPE=" + Operation_RoleCreating);
         String datetimeValue = _dataConnection.executeScalar(sql.toString());
         if(null != datetimeValue){
            loginUnit.roleCreatingDate().parse(datetimeValue);
            changed = true;
         }
      }
      // 查询角色创建时间
      if(loginUnit.roleCreateDate().isEmpty()){
         FSql sql = new FSql("SELECT MIN(OP_TIME) OP_MIN_TIME FROM LOG_ROLE_LOGIN");
         sql.append(" WHERE ACCOUNT_ID=" + accountId + " AND OP_TYPE=" + Operation_RoleCreate);
         String datetimeValue = _dataConnection.executeScalar(sql.toString());
         if(null != datetimeValue){
            loginUnit.roleCreateDate().parse(datetimeValue);
            changed = true;
         }
      }
      // 查询角色当天加载时间
      if(loginUnit.roleLoadDate().isEmpty()){
         FSql sql = new FSql("SELECT MIN(OP_TIME) OP_MIN_TIME FROM LOG_ROLE_LOGIN");
         sql.append(" WHERE ACCOUNT_ID=" + accountId + " AND OP_TYPE=" + Operation_RoleLoad);
         sql.append(" AND DATE_FORMAT(OP_TIME,'%Y%m%d') = '" + currentDay.format("YYYYMMDD") + "'");
         String datetimeValue = _dataConnection.executeScalar(sql.toString());
         if(null != datetimeValue){
            loginUnit.roleLoadDate().parse(datetimeValue);
            changed = true;
         }
      }
      // 查询角色当天登录时间
      if(loginUnit.roleLoginDate().isEmpty()){
         FSql sql = new FSql("SELECT MIN(OP_TIME) OP_MIN_TIME FROM LOG_ROLE_LOGIN");
         sql.append(" WHERE ACCOUNT_ID=" + accountId + " AND OP_TYPE=" + Operation_RoleLogin);
         sql.append(" AND DATE_FORMAT(OP_TIME,'%Y%m%d') = '" + currentDay.format("YYYYMMDD") + "'");
         String datetimeValue = _dataConnection.executeScalar(sql.toString());
         if(null != datetimeValue){
            loginUnit.roleLoginDate().parse(datetimeValue);
            changed = true;
         }
      }
      //............................................................
      // 更新处理
      if(changed){
         _loginLogic.doUpdate(loginUnit, loginUnit.ouid());
      }
      return loginUnit;
   }

   //============================================================
   // <T>同步登录日志。</T>
   //============================================================
   public boolean calculateRoleLoginCount(TDateTime recordDate,
                                          FGameRoleUnit roleUnit,
                                          int day){
      TDateTime roleCreateDate = new TDateTime(roleUnit.createDate());
      roleCreateDate.addDay(day);
      return roleCreateDate.equalsDate(recordDate);
   }

   //============================================================
   // <T>逻辑计算处理。</T>
   //
   // @return 处理结果
   //============================================================
   public boolean calculateOld(){
      boolean result = true;
      //FSqlProcedure procedure = new FSqlProcedure("Analysis_ServerStatus");
      //_dataConnection.execute(procedure);
      //_logger.info(this, "calculate", "Calculate service logic. (game_id={1})", _gameId);
      // 获取目标连接
      _accountLogic = new FGameAccountLogic(_dataConnection);
      _roleLogic = new FGameRoleLogic(_dataConnection);
      _statisticsLogic = new FAnalysisServiceStatisticsLogic(_dataConnection);
      _loginLogic = new FAnalysisLoggerLoginLogic(_dataConnection);
      // 获得最大时间
      FRow loginRow = _dataConnection.find("SELECT MAX(CREATE_DATE) CREATE_MAX_DATE FROM LOG_ROLE_LOGIN");
      TDateTime createMaxDate = new TDateTime(loginRow.get("create_max_date"));
      String createMaxDateValue = null;
      if(createMaxDate.isEmpty()){
         createMaxDateValue = "NOW()-" + _calculageSpan;
      }else{
         createMaxDate.add(-(_calculageSpan * 1000));
         createMaxDateValue = "STR_TO_DATE('" + createMaxDate.format() + "','%Y%m%d%H%i%s')";
      }
      // 获取未处理的登录记录LOG_ROLE_LOGIN
      FLoggerLoginLogic loggerLogic = new FLoggerLoginLogic(_dataConnection);
      FLoggerLoginUnit[] loggerUnits = loggerLogic.fetch("OVLD= 0 AND CREATE_DATE<" + createMaxDateValue, null, _calculateLimit);
      if(0 == loggerUnits.length){
         return false;
      }
      long maxOuid = 0;
      int validCount = 0;
      for(FLoggerLoginUnit loggerUnit : loggerUnits){
         // 获取记录信息
         long ouid = loggerUnit.ouid();
         long accountId = loggerUnit.accountId();
         long roleId = loggerUnit.roleId();
         int operationCd = loggerUnit.opType();
         TDateTime opTime = loggerUnit.opTime();
         TDateTime operationDate = new TDateTime(opTime);
         operationDate.truncate(_truncateSpan);
         if(ouid > maxOuid){
            maxOuid = ouid;
         }
         //............................................................
         // 检查账号编号有效性
         if(0 == accountId){
            loggerUnit.setOvld(true);
            loggerLogic.doUpdate(loggerUnit, loggerUnit.ouid());
            continue;
         }
         // 检查日志的操作类型
         if(-1 == RInteger.indexOf(Operation_Valids, operationCd)){
            loggerUnit.setOvld(true);
            loggerLogic.doUpdate(loggerUnit, loggerUnit.ouid());
            continue;
         }
         // 查找角色信息GM_ACCOUNT
         FGameAccountUnit accountUnit = _accountLogic.find(accountId);
         //GM_ROLE
         FGameRoleUnit roleUnit = _roleLogic.find(roleId);
         if(null == roleUnit){
            roleUnit = _roleLogic.serach("ACCOUNT_ID=" + accountId);
         }
         //............................................................
         // 同步一条统计记录AS_SVC_STATISTICS
         FAnalysisServiceStatisticsUnit statisticsUnit = syncStatisticsUnit(operationDate);
         // 同步一条登录记录AS_LOG_LOGIN
         FAnalysisLoggerLoginUnit loginUnit = syncLoginUnit(accountId, accountUnit, roleUnit, operationDate);
         //         public static final int Operation_AccountCreate = 9;
         //         public static final int Operation_RoleCreating = 7;
         //         public static final int Operation_RoleCreate = 2;
         //         public static final int Operation_RoleLoad = 6;
         //         public static final int Operation_RoleLogin = 8;
         //............................................................
         // 账号创建统计（一个玩家一次）
         if(Operation_AccountCreate == operationCd){
            if(opTime.equals(loginUnit.accountCreateDate())){
               statisticsUnit.setAccountCreateCount(statisticsUnit.accountCreateCount() + 1);
            }
         }
         // 角色中创建中统计（一个玩家一次）
         if(Operation_RoleCreating == operationCd){
            if(opTime.equals(loginUnit.roleCreatingDate())){
               statisticsUnit.setRoleCreatingCount(statisticsUnit.roleCreatingCount() + 1);
            }
         }
         // 角色创建统计（一个玩家一次）
         if(Operation_RoleCreate == operationCd){
            if(opTime.equals(loginUnit.roleCreateDate())){
               statisticsUnit.setRoleCreateCount(statisticsUnit.roleCreateCount() + 1);
            }
         }
         // 角色加载统计（一个玩家一天一次）
         if(Operation_RoleLoad == operationCd){
            if(opTime.equalsDate(loginUnit.roleCreateDate())){
               if(opTime.equals(loginUnit.roleLoadDate())){
                  statisticsUnit.setRoleLoadCount(statisticsUnit.roleLoadCount() + 1);
               }
            }
            // 角色总加载次数（包含重复）
            statisticsUnit.setRoleLoadTotal(statisticsUnit.roleLoadTotal() + 1);
         }
         // 角色登录统计（一个玩家一天一次）
         if(Operation_RoleLogin == operationCd){
            // 角色创建日期有，登录日期为第一次，则是刚创建角色，并且登录成功
            if(opTime.equalsDate(loginUnit.roleCreateDate())){
               if(opTime.equals(loginUnit.roleLoginDate())){
                  statisticsUnit.setRoleCreateSuccessCount(statisticsUnit.roleCreateSuccessCount() + 1);
               }
            }
            // 角色登录日期为一天第一次
            if(opTime.equals(loginUnit.roleLoginDate())){
               statisticsUnit.setRoleLoginCount(statisticsUnit.roleLoginCount() + 1);
               // 计算注册留存率
               if(null != roleUnit){
                  TDateTime roleCreateDate = roleUnit.createDate();
                  // 1天注册留存
                  if(calculateRoleLoginCount(operationDate, roleUnit, 1)){
                     FAnalysisServiceStatisticsUnit roleCreateUint = syncStatisticsUnit(roleCreateDate);
                     roleCreateUint.setRoleHistoryLogin1Count(roleCreateUint.roleHistoryLogin1Count() + 1);
                     _statisticsLogic.doUpdate(roleCreateUint, roleCreateUint.ouid());
                  }
                  // 2天注册留存
                  if(calculateRoleLoginCount(operationDate, roleUnit, 2)){
                     FAnalysisServiceStatisticsUnit roleCreateUint = syncStatisticsUnit(roleCreateDate);
                     roleCreateUint.setRoleHistoryLogin2Count(roleCreateUint.roleHistoryLogin2Count() + 1);
                     _statisticsLogic.doUpdate(roleCreateUint, roleCreateUint.ouid());
                  }
                  // 3天注册留存
                  if(calculateRoleLoginCount(operationDate, roleUnit, 3)){
                     FAnalysisServiceStatisticsUnit roleCreateUint = syncStatisticsUnit(roleCreateDate);
                     roleCreateUint.setRoleHistoryLogin3Count(roleCreateUint.roleHistoryLogin3Count() + 1);
                     _statisticsLogic.doUpdate(roleCreateUint, roleCreateUint.ouid());
                  }
                  // 4天注册留存
                  if(calculateRoleLoginCount(operationDate, roleUnit, 4)){
                     FAnalysisServiceStatisticsUnit roleCreateUint = syncStatisticsUnit(roleCreateDate);
                     roleCreateUint.setRoleHistoryLogin4Count(roleCreateUint.roleHistoryLogin4Count() + 1);
                     _statisticsLogic.doUpdate(roleCreateUint, roleCreateUint.ouid());
                  }
                  // 5天注册留存
                  if(calculateRoleLoginCount(operationDate, roleUnit, 5)){
                     FAnalysisServiceStatisticsUnit roleCreateUint = syncStatisticsUnit(roleCreateDate);
                     roleCreateUint.setRoleHistoryLogin5Count(roleCreateUint.roleHistoryLogin5Count() + 1);
                     _statisticsLogic.doUpdate(roleCreateUint, roleCreateUint.ouid());
                  }
                  // 6天注册留存
                  if(calculateRoleLoginCount(operationDate, roleUnit, 6)){
                     FAnalysisServiceStatisticsUnit roleCreateUint = syncStatisticsUnit(roleCreateDate);
                     roleCreateUint.setRoleHistoryLogin6Count(roleCreateUint.roleHistoryLogin6Count() + 1);
                     _statisticsLogic.doUpdate(roleCreateUint, roleCreateUint.ouid());
                  }
                  // 7天注册留存
                  if(calculateRoleLoginCount(operationDate, roleUnit, 7)){
                     FAnalysisServiceStatisticsUnit roleCreateUint = syncStatisticsUnit(roleCreateDate);
                     roleCreateUint.setRoleHistoryLogin7Count(roleCreateUint.roleHistoryLogin7Count() + 1);
                     _statisticsLogic.doUpdate(roleCreateUint, roleCreateUint.ouid());
                  }
                  // 14天注册留存
                  if(calculateRoleLoginCount(operationDate, roleUnit, 14)){
                     FAnalysisServiceStatisticsUnit roleCreateUint = syncStatisticsUnit(roleCreateDate);
                     roleCreateUint.setRoleHistoryLogin14Count(roleCreateUint.roleHistoryLogin14Count() + 1);
                     _statisticsLogic.doUpdate(roleCreateUint, roleCreateUint.ouid());
                  }
                  // 30天注册留存
                  if(calculateRoleLoginCount(operationDate, roleUnit, 30)){
                     FAnalysisServiceStatisticsUnit roleCreateUint = syncStatisticsUnit(roleCreateDate);
                     roleCreateUint.setRoleHistoryLogin30Count(roleCreateUint.roleHistoryLogin30Count() + 1);
                     _statisticsLogic.doUpdate(roleCreateUint, roleCreateUint.ouid());
                  }
                  // 60天注册留存
                  if(calculateRoleLoginCount(operationDate, roleUnit, 60)){
                     FAnalysisServiceStatisticsUnit roleCreateUint = syncStatisticsUnit(roleCreateDate);
                     roleCreateUint.setRoleHistoryLogin60Count(roleCreateUint.roleHistoryLogin60Count() + 1);
                     _statisticsLogic.doUpdate(roleCreateUint, roleCreateUint.ouid());
                  }
               }
            }
            // 角色总登录次数（包含重复）
            statisticsUnit.setRoleLoginTotal(statisticsUnit.roleLoginTotal() + 1);
         }
         _statisticsLogic.doUpdate(statisticsUnit, statisticsUnit.ouid());
         result = true;
         // 更新登录记录为已处理
         loggerUnit.setOvld(true);
         loggerLogic.doUpdate(loggerUnit, loggerUnit.ouid());
         validCount++;
      }
      _logger.info(this, "calculate", "Calculate service logic. (count={1}, valid={2}, max_ouid={3},game_id={4})", loggerUnits.length, validCount, maxOuid, _gameId);
      return result;
   }

   //============================================================
   // <T>计算处理。</T>
   //
   // @return 统计结果
   //============================================================
   @Override
   public EStatisticsResult calculate(){
      // 创建存储过程
      /*FSqlProcedure procedure = new FSqlProcedure("Analysis_ServerStatus");
      procedure.createParameter("game_id_", _gameId, ESqlDataType.Integer, ESqlDataDirection.In);
      procedure.createParameter("interval_", _calculateInterval, ESqlDataType.Integer, ESqlDataDirection.In);
      procedure.createParameter("count_", 0, ESqlDataType.Integer, ESqlDataDirection.Out);
      // 执行处理
      _dataConnection.execute(procedure);
      // 获得内容
      int count = procedure.parameter("count_").asInt();
      // 返回结果
      if(count > 0){
         return EStatisticsResult.Continue;
      }*/
      return EStatisticsResult.Success;
   }
}
