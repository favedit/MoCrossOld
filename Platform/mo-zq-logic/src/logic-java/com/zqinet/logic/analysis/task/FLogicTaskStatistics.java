package com.zqinet.logic.analysis.task;

import com.zqinet.logic.analysis.common.FLogicAnalysisStatistics;
import com.zqinet.logic.data.analysis.FAnalysisLoggerTaskLogic;
import com.zqinet.logic.data.analysis.FAnalysisLoggerTaskUnit;
import com.zqinet.logic.data.analysis.FAnalysisServiceTaskLogic;
import com.zqinet.logic.data.analysis.FAnalysisServiceTaskUnit;
import com.zqinet.logic.data.logger.FLoggerTaskLogic;
import com.zqinet.logic.data.logger.FLoggerTaskUnit;

import org.mo.com.collections.FRow;
import org.mo.com.data.ESqlDataDirection;
import org.mo.com.data.ESqlDataType;
import org.mo.com.data.FSqlProcedure;
import org.mo.com.lang.RInteger;
import org.mo.com.lang.type.TDateTime;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.data.statistics.EStatisticsResult;
import org.mo.data.synchronizer.FSynchronizerUnit;

//============================================================
// <T>日志流失信息间隔统计。</T>
//============================================================
public class FLogicTaskStatistics
      extends FLogicAnalysisStatistics
      implements
         ILogicTaskStatistics
{

   // 日志输出接口
   private static ILogger _logger = RLogger.find(FSynchronizerUnit.class);

   public static final int Task_Accept = 1;

   public static final int Task_Deliver = 2;

   public static final int Task_Complete = 3;

   public static final int Task_Track = 4;

   public static final int Task_Abandon = 5;

   // 游戏编号
   public long _gameId;

   // 每次计算量
   protected int _calculateLimit = 1000;

   // 分割时间段(1小时)
   protected int _truncateSpan = 1000 * 60 * 60;

   // 计算里现在的时间段（最新数据间隔）
   protected int _calculageSpan = 60 * 5;

   // 主线任务TID集合
   public static final int[] Operation_Valids = new int[]{1, 2, 3, 181, 182, 183, 184, 185, 196, 221, 186, 187, 188, 191, 192, 194, 195, 401, 402, 198, 510, 511, 199, 200, 512, 201, 203, 206, 207, 208, 209, 210, 211, 212, 213, 214, 222, 215, 217, 218,
         219, 868, 869, 870, 362, 871, 872, 873, 874, 875, 876, 877, 878, 879, 880, 881, 882, 361, 883, 884, 885, 886, 887, 888, 889, 237, 223, 220, 238, 239, 226, 240, 383, 241, 242, 243, 244, 245, 246, 247, 248, 249, 250, 251, 252, 259, 260, 261, 262,
         263, 264, 265, 266, 267, 268, 269, 270, 271, 272, 273, 274, 275, 276, 277, 302, 303, 304, 305, 306, 307, 308, 309, 310, 311, 312, 313, 314, 315, 316, 317, 318, 319, 320, 321, 618, 619, 620, 621, 622, 623, 624, 625, 626, 627, 628, 629, 225};

   protected FLoggerTaskLogic _loggerLogic;

   protected FAnalysisLoggerTaskLogic _loggerTaskLogic;

   protected FAnalysisServiceTaskLogic _taskLogic;

   protected static String _hour = null;

   // ============================================================
   // <T>同步任务统计记录。</T>
   //
   // @return 记录
   // ============================================================
   public FAnalysisServiceTaskUnit syncTaskUnit(TDateTime date,
                                                int taskTid){
      // 获得时间
      TDateTime recordDate = new TDateTime(date);
      recordDate.truncateHour();
      // ............................................................AS_SVC_TASK
      FAnalysisServiceTaskUnit taskUnit = _taskLogic.serach("TASK_TID=" + taskTid + " AND GAME_ID=" + _gameId + " AND RECORD_DATE=STR_TO_DATE('" + recordDate.format() + "','%Y%m%d%H%i%s')");
      if(null == taskUnit){
         // 创建记录
         taskUnit = new FAnalysisServiceTaskUnit();
         taskUnit.recordDate().assign(recordDate);
         taskUnit.recordHour().assign(recordDate);
         taskUnit.recordHour().truncateHour();
         taskUnit.recordDay().assign(recordDate);
         taskUnit.recordDay().truncateDay();
         taskUnit.recordWeek().assign(recordDate);
         taskUnit.recordWeek().truncateWeek();
         taskUnit.recordMonth().assign(recordDate);
         taskUnit.recordMonth().truncateMonth();
         taskUnit.setRecordInterval(_truncateSpan);
         // 设置游戏编号
         taskUnit.setGameId(_gameId);
         // 设置任务编号
         taskUnit.setTaskTid(taskTid);
         // 新建记录
         _taskLogic.doInsert(taskUnit);
      }
      return taskUnit;
   }

   // ============================================================
   // <T>同步日志任务统计记录。</T>
   //
   // @return 记录
   // ============================================================
   public FAnalysisLoggerTaskUnit syncLoggerTaskUnit(int taskTid,
                                                     int opType,
                                                     TDateTime time){
      // 查找记录 AS_LOG_TASK 当前game_id 和 任务模板编 号
      FAnalysisLoggerTaskUnit unit = _loggerTaskLogic.serach("GAME_ID=" + _gameId + " AND TASK_TID=" + taskTid);
      if(null == unit){
         // 创建记录
         unit = new FAnalysisLoggerTaskUnit();
         unit.setGameId(_gameId);
         // unit.setRoleId(roleId);
         unit.setTaskTid(taskTid);
         _loggerTaskLogic.doInsert(unit);
      }
      // ............................................................
      // 改变操作类型
      boolean changed = false;
      if(opType == Task_Accept){
         unit.setOperateAccept(unit.operateAccept() + 1);
         changed = true;
      }else if(opType == Task_Deliver){
         unit.setOperateDeliver(unit.operateDeliver() + 1);
         changed = true;
      }else if(opType == Task_Complete){
         unit.setOperateComplete(unit.operateComplete() + 1);
         changed = true;
      }else if(opType == Task_Track){
         unit.setOperateTrack(unit.operateTrack() + 1);
         changed = true;
      }else if(opType == Task_Abandon){
         unit.setOperateAbandon(unit.operateAbandon() + 1);
         changed = true;
      }
      unit.setRecordDate(time);
      // 更新记录
      if(changed){
         _loggerTaskLogic.doUpdate(unit, unit.ouid());
      }
      return unit;
   }

   // ============================================================
   // <T>逻辑计算处理。</T>
   //
   // @return 处理结果
   // ============================================================
   public void doInsert(TDateTime time){
      TDateTime recordDate = new TDateTime(time);
      recordDate.truncateHour();
      //AS_SVC_TASK
      FAnalysisServiceTaskUnit[] units = _taskLogic.fetch("RECORD_DATE=STR_TO_DATE('" + recordDate.format() + "','%Y%m%d%H%i%s')");
      //如果，没有相同操 作 时间的 结束;
      if(0 != units.length){
         return;
      }
      //AS_LOG_TASK 获取所有游戏ID为当前gameid的日志分析登录。
      FAnalysisLoggerTaskUnit[] taskUnits = _loggerTaskLogic.fetch("GAME_ID=" + _gameId);
      for(FAnalysisLoggerTaskUnit taskUnit : taskUnits){
         FAnalysisServiceTaskUnit task = new FAnalysisServiceTaskUnit();
         // 设置时间
         task.recordDate().assign(recordDate);
         task.recordHour().assign(recordDate);
         task.recordHour().truncateHour();
         task.recordDay().assign(recordDate);
         task.recordDay().truncateDay();
         task.recordWeek().assign(recordDate);
         task.recordWeek().truncateWeek();
         task.recordMonth().assign(recordDate);
         task.recordMonth().truncateMonth();
         task.setRecordInterval(_truncateSpan);
         // 设置游戏编号
         task.setGameId(_gameId);
         // 设置任务编号
         task.setTaskTid(taskUnit.taskTid());
         // 设置任务
         task.setAccept(taskUnit.operateAccept());
         task.setFinish(taskUnit.operateComplete());
         task.setDeliver(taskUnit.operateDeliver());
         // 
         int nowTaskTidIndex = RInteger.indexOf(Operation_Valids, taskUnit.taskTid());
         if(nowTaskTidIndex > 0){
        	 //得到前一个任务编 号
            int beforeTaskTid = Operation_Valids[nowTaskTidIndex - 1];
            //AS_LOG_TASK 查找当前游戏编号和上一个任务编号数据。
            FAnalysisLoggerTaskUnit loggerTask = _loggerTaskLogic.serach("TASK_TID=" + beforeTaskTid + " AND GAME_ID=" + _gameId);
            // 是否找到前一个任务数据
            if(null != loggerTask){
            	//把 操作交付给可接取
               task.setCanhave(taskUnit.operateDeliver());
            }
         }else{
        	 //把操作接受给可接取
            task.setCanhave(taskUnit.operateAccept());
         }
         //AS_SVC_TASK
         _taskLogic.doInsert(task);
      }
   }

   // ============================================================
   // <T>逻辑计算处理。</T>
   //
   // @return 处理结果
   // ============================================================
   public boolean calculateOld(){
      boolean result = false;
      // 获取链接 
      _loggerLogic = new FLoggerTaskLogic(_dataConnection);
      _loggerTaskLogic = new FAnalysisLoggerTaskLogic(_dataConnection);
      _taskLogic = new FAnalysisServiceTaskLogic(_dataConnection);
      // ............................................................
      // 获取最大时间
      FRow logTaskRow = _dataConnection.find("SELECT MAX(CREATE_DATE) CREATE_MAX_DATE FROM LOG_ROLE_TASK");
      TDateTime createMaxDate = new TDateTime(logTaskRow.get("create_max_date"));
      String createMaxDateValue = null;
      if(createMaxDate.isEmpty()){
         createMaxDateValue = "NOW()-" + _calculageSpan;
      }else{
         createMaxDate.add(-(_calculageSpan * 1000));
         createMaxDateValue = "STR_TO_DATE('" + createMaxDate.format() + "','%Y%m%d%H%i%s')";
      }
      // 获取未处理的任务记录 LOG_ROLE_TASK
      FLoggerTaskLogic logTaskLogic = new FLoggerTaskLogic(_dataConnection);
      FLoggerTaskUnit[] loggerTaskUnits = logTaskLogic.fetch("OVLD=0 AND CREATE_DATE<" + createMaxDateValue, "CREATE_DATE", _calculateLimit);
      // ............................................................
      // 计算任务统计数据
      long maxOuid = 0;
      int validCount = 0;
      boolean isInsert = false;
      //String hour = null;
      if(0 < loggerTaskUnits.length){
         for(FLoggerTaskUnit loggerUnit : loggerTaskUnits){
            // 获取记录信息
            long ouid = loggerUnit.ouid();
            int taskTid = loggerUnit.taskTid();
            int opType = loggerUnit.opType();
            TDateTime opTime = loggerUnit.opTime();
            TDateTime operationDate = new TDateTime(opTime);
            operationDate.truncate(_truncateSpan);//间隔一小时（截掉一小时）
            String time = operationDate.toString();
            // 获取最大记录时间AS_LOG_TASK
            FRow row = _dataConnection.find("SELECT MAX(RECORD_DATE) RECORD_DATE FROM AS_LOG_TASK WHERE GAME_ID=" + _gameId);
            String maxDatetime = row.get("record_date");
            // 判断时间点是否到下一个时间20130508170000 20130508170000
            //如果 任务日志的操作时间不等于日志分析登录的最大记录时间
            if(!maxDatetime.equals(time)){
               isInsert = true;
            }
            // 判断是否加入任务
            if(isInsert){
            	//AS_SVC_TASK如果没有将插入一条数据
               doInsert(opTime);//传入 操作时间
               isInsert = false;
            }
            if(ouid > maxOuid){
               maxOuid = ouid;
            }
            // ............................................................
            // 判断主线任务类型  LOG_ROLE_TASK
            if(-1 == RInteger.indexOf(Operation_Valids, taskTid)){
            	//如果不是，将把任务日志改为已处理，然后进入下一次循环
               loggerUnit.setOvld(true);
               logTaskLogic.doUpdate(loggerUnit, loggerUnit.ouid());
               continue;
            }
            // ............................................................
            // 统计所有任务情况 AS_LOG_TASK 更改操作类型
            FAnalysisLoggerTaskUnit loggerTaskUnit = syncLoggerTaskUnit(taskTid, opType, operationDate);
            // ............................................................
            // 同步一条统计记录AS_SVC_TASK
            boolean changed = false;
            FAnalysisServiceTaskUnit taskUnit = syncTaskUnit(operationDate, taskTid);
            // 判断任务改变
            if(Task_Accept == opType || Task_Deliver == opType || Task_Complete == opType){
               // 更新可接受
               taskUnit.setAccept(loggerTaskUnit.operateAccept());
               // 更新已接受
               taskUnit.setDeliver(loggerTaskUnit.operateDeliver());
               // 更新已完成
               taskUnit.setFinish(loggerTaskUnit.operateComplete());
               changed = true;
            }
            // ............................................................
            // 获取前一任务的任务TID
            if(changed){
               int beforeTaskTid = 0;
               int nowTaskTidIndex = RInteger.indexOf(Operation_Valids, taskTid);
               // 如果不是第一个任务，找到前一个任务的tid，获取交付任务数量即当前任务可接取 如果是第一个任务
               // 可接取任务数量等于gm_role的大小
               if(nowTaskTidIndex > 0){
                  beforeTaskTid = Operation_Valids[nowTaskTidIndex - 1];
                  // AS_LOG_TASK
                  FAnalysisLoggerTaskUnit loggerTask = _loggerTaskLogic.serach("TASK_TID=" + beforeTaskTid + " AND GAME_ID=" + _gameId);
                  // 是否找到前一个任务数据
                  if(null != loggerTask){
                     taskUnit.setCanhave(loggerTask.operateDeliver());
                  }
               }else{
                  taskUnit.setCanhave(taskUnit.accept());
               }
            }
            // ............................................................
            _taskLogic.doUpdate(taskUnit, taskUnit.ouid());
            result = true;
            // ............................................................
            // 更新已处理数据
            loggerUnit.setOvld(true);
            _loggerLogic.doUpdate(loggerUnit, loggerUnit.ouid());
            // 记录执行次数
            validCount++;
         }
      }
      // 输出日住
      _logger.info(this, "calculate", "Calculate task rate. (count={1}, valid={2}, max_ouid={3},game_id={4})", loggerTaskUnits.length, validCount, maxOuid, _gameId);
      // 返回结果
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
	   FSqlProcedure procedure = new FSqlProcedure("Logic_Task_Statistics");
	   procedure.createParameter("game_id_",  _gameId, ESqlDataType.Integer, ESqlDataDirection.In);
	   procedure.createParameter("count_", 0, ESqlDataType.Integer, ESqlDataDirection.Out);
	   _dataConnection.execute(procedure);
	   // 获得内容
	   int count = procedure.parameter("count_").asInt();
	   if(count > 0){
	      return EStatisticsResult.Continue;
	   }
      return EStatisticsResult.Success;
   }
}
