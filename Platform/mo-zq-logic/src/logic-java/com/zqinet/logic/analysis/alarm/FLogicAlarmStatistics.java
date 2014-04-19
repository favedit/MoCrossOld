package com.zqinet.logic.analysis.alarm;

import com.zqinet.logic.analysis.common.FLogicAnalysisStatistics;
import com.zqinet.logic.data.analysis.FAnalysisServiceAlarmmLogic;
import com.zqinet.logic.data.analysis.FAnalysisServiceAlarmmUnit;
import com.zqinet.logic.data.logger.FLoggerRoleDetailLogic;
import com.zqinet.logic.data.logger.FLoggerRoleDetailUnit;

import org.mo.com.collections.FRow;
import org.mo.com.data.ESqlDataDirection;
import org.mo.com.data.ESqlDataType;
import org.mo.com.data.FSqlProcedure;
import org.mo.com.lang.type.TDateTime;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.data.statistics.EStatisticsResult;
import org.mo.data.synchronizer.FSynchronizerUnit;

public class FLogicAlarmStatistics
      extends FLogicAnalysisStatistics
      implements
         ILogicAlarmStatistics
{
   // 日志输出接口
   private static ILogger _logger = RLogger.find(FSynchronizerUnit.class);

   // 每次计算量
   protected int _calculateLimit = 1000;

   // 金钱变化分段时间隔(10分钟)
   protected int _moneytruncateSpan = 1000 * 60 * 10;

   // 计算离现在的时间段（最新数据间隔）
   protected int _calculageSpan = 60 * 10;

   //
   protected FAnalysisServiceAlarmmLogic _alarmmLogic;

   // 日志角色详细记录
   protected FLoggerRoleDetailLogic _roleDetailLogic;

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

   public boolean calculateOld(){
      boolean result = false;
      // 获取链接
      _roleDetailLogic = new FLoggerRoleDetailLogic(_dataConnection);
      _alarmmLogic = new FAnalysisServiceAlarmmLogic(_dataConnection);

      // 获取最大时间
      FRow detailRow = _dataConnection.find("SELECT MAX(CREATE_DATE) CREATE_MAX_DATE FROM LOG_ROLE_DETAIL");
      TDateTime createMaxDate = new TDateTime(detailRow.get("create_max_date"));
      String createMaxDateValue = null;
      if(createMaxDate.isEmpty()){
         createMaxDateValue = "NOW()-" + _calculageSpan;
      }else{
         createMaxDate.add(-(_calculageSpan * 1000));
         createMaxDateValue = "STR_TO_DATE('" + createMaxDate.format() + "','%Y%m%d%H%i%s')";
      }

      long maxOuid = 0;
      int validCount = 0;
      //LOG_ROLE_DETAIL
      FLoggerRoleDetailUnit[] roleDetailUnits = _roleDetailLogic.fetch("OVLD=0 AND CREATE_DATE<" + createMaxDateValue, null, _calculateLimit);
      if(0 < roleDetailUnits.length){
         for(FLoggerRoleDetailUnit roleDetail : roleDetailUnits){
            // 获取记录信息
            long ouid = roleDetail.ouid();
            long roleId = roleDetail.roleId();
            int valueType = roleDetail.valueCd();
            int obtainType = roleDetail.obtainType();
            int value = roleDetail.value();
            TDateTime opTime = roleDetail.createDate();
            TDateTime operationDate = new TDateTime(opTime);
            operationDate.truncate(_moneytruncateSpan);//截取10分钟
            if(ouid > maxOuid){
               maxOuid = ouid;
            }
            // 如果不是获得类型，修改标识 继续寻找
            if(1 != obtainType){
               roleDetail.setOvld(true);
               _roleDetailLogic.doUpdate(roleDetail, roleDetail.ouid());
               continue;
            }
            //AS_SVC_ALARMM
            FAnalysisServiceAlarmmUnit alarmUnit = syncAlarmmUnit(roleId, operationDate);

            // 门贡3
            if(3 == valueType){
               int oldValue = alarmUnit.singleMaxCountMetier();
               if(value > oldValue){
                  alarmUnit.setSingleMaxCountMetier(value);
               }
               alarmUnit.setSumCountMetier(alarmUnit.sumCountMetier() + 1);
               //
            }
            // 帮贡
            if(4 == valueType){
               int oldValue = alarmUnit.singleMaxCountGang();
               if(value > oldValue){
                  alarmUnit.setSingleMaxCountGang(value);
               }
               alarmUnit.setSumCountGang(alarmUnit.sumCountGang() + 1);
               //
            }
            // 斗法
            if(5 == valueType){
               int oldValue = alarmUnit.singleMaxCountTournament();
               if(value > oldValue){
                  alarmUnit.setSingleMaxCountTournament(value);
               }
               alarmUnit.setSumCountTournament(alarmUnit.sumCountTournament() + 1);
            }
            // 逐鹿
            if(6 == valueType){
               int oldValue = alarmUnit.singleMaxCountSociety();
               if(value > oldValue){
                  alarmUnit.setSingleMaxCountSociety(value);
               }
               alarmUnit.setSumCountSociety(alarmUnit.sumCountSociety() + 1);
               //
            }
            // 经验
            if(12 == valueType){
               int oldValue = alarmUnit.singleMaxCountExp();
               if(value > oldValue){
                  alarmUnit.setSingleMaxCountExp(value);
               }
               alarmUnit.setSumCountExp(alarmUnit.sumCountExp() + 1);
               //
            }
            _alarmmLogic.doUpdate(alarmUnit, alarmUnit.ouid());
            result = true;
            // 更新已处理数据
            roleDetail.setOvld(true);
            _roleDetailLogic.doUpdate(roleDetail, roleDetail.ouid());
            validCount++;
         }
      }

      _logger.info(this, "calculate", "Calculate service alarmp. (count={1}, valid={2}, max_ouid={3},game_id={4})", roleDetailUnits.length, validCount, maxOuid, _gameId);
      return result;
   }

   //============================================================
   // <T>计算处理。</T>
   //
   // @return 统计结果
   //============================================================
   @Override
   public EStatisticsResult calculate(){
	   /*FSqlProcedure procedure = new FSqlProcedure("Logic_Alarm_Statistics");
	   procedure.createParameter("game_id_", _gameId,ESqlDataType.Integer,ESqlDataDirection.In);
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
