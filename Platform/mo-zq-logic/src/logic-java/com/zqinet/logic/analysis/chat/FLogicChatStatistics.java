package com.zqinet.logic.analysis.chat;

import com.zqinet.logic.analysis.common.FLogicAnalysisStatistics;
import com.zqinet.logic.data.analysis.FAnalysisServiceChatLogic;
import com.zqinet.logic.data.analysis.FAnalysisServiceChatUnit;
import com.zqinet.logic.data.platform.FPlatformChatRecordLogic;
import com.zqinet.logic.data.platform.FPlatformChatRecordUnit;

import org.mo.com.collections.FRow;
import org.mo.com.data.ESqlDataDirection;
import org.mo.com.data.ESqlDataType;
import org.mo.com.data.FSqlProcedure;
import org.mo.com.lang.type.TDateTime;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.data.statistics.EStatisticsResult;
import org.mo.data.synchronizer.FSynchronizerUnit;

public class FLogicChatStatistics
      extends FLogicAnalysisStatistics
      implements
         ILogicChatStatistics
{

   // 日志输出接口
   private static ILogger _logger = RLogger.find(FSynchronizerUnit.class);

   // 每次计算量
   protected int _calculateLimit = 1000;

   // 分段时间段（1分钟）
   protected int _truncateSpan = 1000 * 60;

   // 计算离现在的时间段（最新数据间隔）
   protected int _calculageSpan = 60 * 10;

   // 来源表
   protected FPlatformChatRecordLogic _chatRecordLogic;

   // 目标表
   protected FAnalysisServiceChatLogic _chatLogic;

   // ============================================================
   // <T>聊天信息同步。</T>
   // ============================================================
   public FAnalysisServiceChatUnit syncChatUnit(TDateTime date,
                                                long roleId,
                                                String accountLabel,
                                                String roleLabel,
                                                String value){
      TDateTime recordDate = new TDateTime(date);
      recordDate.truncate(_truncateSpan);

      /*if(",.l;'[];;{神马}" == value){
      	value = value.replace("'", "\\\\'");
      }*/

      /*if (-1 != value.indexOf("'")) {
      	value = value.replace("'", "\\\\'");
      }*/
      
            //value = _targetConnection.formatValue(value);
      
            FAnalysisServiceChatUnit chatUnit = _chatLogic.serach("ROLE_ID=" + roleId + " AND CHAT_MESSAGE='" + value + "' AND GAME_ID=" + _gameId);
            if(null == chatUnit){
               // 创建记录
               chatUnit = new FAnalysisServiceChatUnit();
               chatUnit.recordDate().assign(recordDate);
               chatUnit.recordHour().assign(recordDate);
               chatUnit.recordHour().truncateHour();
               chatUnit.recordDay().assign(recordDate);
               chatUnit.recordDay().truncateDay();
               chatUnit.recordWeek().assign(recordDate);
               chatUnit.recordWeek().truncateWeek();
               chatUnit.recordMonth().assign(recordDate);
               chatUnit.recordMonth().truncateMonth();
               chatUnit.setRecordInterval(_truncateSpan);
               // 设置游戏编号
               chatUnit.setGameId(_gameId);
               // 设置角色编号
               chatUnit.setRoleId(roleId);
               /*
                *  获得玩家id FRow accountRow = _sourceConnection
                * .find("SELECT PASSPORT FROM GM_ACCOUNT WHERE OUID=" + accountId);
                */
               // 设置玩家id
               chatUnit.setUserId(accountLabel);
               // 设置玩家名称
               chatUnit.setRoleLabel(roleLabel);
               // 设置内容
               chatUnit.setChatMessage(value);
               _chatLogic.doInsert(chatUnit);
            }

      return chatUnit;
      //return null;
   }

   // ============================================================
   // <T>逻辑计算处理。</T>
   // ============================================================
   public boolean calculateOld(){
      boolean result = false;
      // 获取链接
      _chatRecordLogic = new FPlatformChatRecordLogic(_dataConnection);
      _chatLogic = new FAnalysisServiceChatLogic(_dataConnection);

      // 获取最大时间
      FRow chatRecordRow = _dataConnection.find("SELECT MAX(CREATE_DATE) CREATE_MAX_DATE FROM PF_CHAT_RECORD");
      TDateTime createMaxDate = new TDateTime(chatRecordRow.get("create_max_date"));

      String createMaxDateValue = null;
      if(createMaxDate.isEmpty()){
         createMaxDateValue = "NOW()-" + _calculageSpan;
      }else{
         createMaxDate.add(-(_calculageSpan * 1000));//减掉10分钟
         createMaxDateValue = "STR_TO_DATE('" + createMaxDate.format() + "','%Y%m%d%H%i%s')";
      }
      long maxOuid = 0;
      int validCount = 0;
      // 获取聊天未吃力记录PF_CHAT_RECORD
      FPlatformChatRecordUnit[] chatRecordUnits = _chatRecordLogic.fetch("OVLD=0 AND CREATE_DATE<" + createMaxDateValue, null, _calculateLimit);
      if(0 < chatRecordUnits.length){
         for(FPlatformChatRecordUnit chatRecordUnit : chatRecordUnits){
            // 获取记录信息
            long ouid = chatRecordUnit.ouid();
            long roleId = chatRecordUnit.roleId();
            String roleLabel = chatRecordUnit.roleLabel();
            int talkType = chatRecordUnit.talkType();
            String accountLabel = chatRecordUnit.passport();
            String content = chatRecordUnit.content();
            TDateTime talkTime = chatRecordUnit.talkTime();
            TDateTime operationDate = new TDateTime(talkTime);
            operationDate.truncate(_truncateSpan);
            if(ouid > maxOuid){
               maxOuid = ouid;
            }
            // 数据同步
            FAnalysisServiceChatUnit chatUnit = syncChatUnit(operationDate, roleId, accountLabel, roleLabel, content);
            // 设置说话类型
            chatUnit.setChatType(talkType);
            // 设置说话次数
            chatUnit.setChatRepeat(chatUnit.chatRepeat() + 1);
            // 设置发送时间
            chatUnit.setSendTime(talkTime);
            //
            _chatLogic.doUpdate(chatUnit, chatUnit.ouid());
            result = true;
            // 更新已处理记录
            chatRecordUnit.setOvld(true);
            _chatRecordLogic.doUpdate(chatRecordUnit, chatRecordUnit.ouid());
            validCount++;
         }
      }
      _logger.info(this, "calculate", "Calculate service chat. (count={1}, valid={2}, max_ouid={3},game_id={4})", chatRecordUnits.length, validCount, maxOuid, _gameId);
      return result;
   }

   //============================================================
   // <T>计算处理。</T>
   //
   // @return 统计结果
   //============================================================
   @Override
   public EStatisticsResult calculate(){
	   calculateOld();
	   //System.out.println(_gameId);
	   /*FSqlProcedure procedure = new FSqlProcedure("Logic_Chat_Statistics");
	   procedure.createParameter("game_id_", _gameId,ESqlDataType.Integer,ESqlDataDirection.In);
	   procedure.createParameter("count_", 0, ESqlDataType.Integer, ESqlDataDirection.Out);
	   _dataConnection.execute(procedure);
	   // 获得内容
	   int count = procedure.parameter("count_").asInt();
	   if(count > 0){
	      return EStatisticsResult.Continue;
	   }*/
	  // _logger.info(this, "calculate", "Calculate service logic. (game_id={1})", _gameId);
	      
      return EStatisticsResult.Success;
   }
}
