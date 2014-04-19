package com.zqinet.logic.analysis.common;

import org.mo.data.statistics.FStatisticsLogic;

//============================================================
// <T>逻辑分析统计基类。</T>
//============================================================
public abstract class FLogicAnalysisStatistics
      extends FStatisticsLogic
{
   // 游戏编号
   protected long _gameId;

   //============================================================
   // <T>启动处理。</T>
   //============================================================
   @Override
   public void startup(){
      super.startup();
      _gameId = _statistics.parameters().getLong("game_id");
   }
}
