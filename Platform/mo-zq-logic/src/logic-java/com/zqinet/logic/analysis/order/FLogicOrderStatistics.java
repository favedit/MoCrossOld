package com.zqinet.logic.analysis.order;

import com.zqinet.logic.analysis.common.FLogicAnalysisStatistics;
import com.zqinet.logic.data.analysis.FAnalysisLoggerOrderLogic;
import com.zqinet.logic.data.analysis.FAnalysisLoggerOrderUnit;
import com.zqinet.logic.data.analysis.FAnalysisServiceAlarmpLogic;
import com.zqinet.logic.data.analysis.FAnalysisServiceAlarmpUnit;
import com.zqinet.logic.data.analysis.FAnalysisServiceOrderLogic;
import com.zqinet.logic.data.analysis.FAnalysisServiceOrderUnit;
import com.zqinet.logic.data.logger.FLoggerItemLogic;
import com.zqinet.logic.data.logger.FLoggerItemUnit;
import com.zqinet.logic.data.platform.FPlatformChargeOrderLogic;
import com.zqinet.logic.data.platform.FPlatformChargeOrderUnit;

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

public class FLogicOrderStatistics
      extends FLogicAnalysisStatistics
      implements
         ILogicOrderStatistics
{

   // 日志输出接口
   private static ILogger _logger = RLogger.find(FSynchronizerUnit.class);

   // 每次计算量
   protected int _calculateLimit = 1000;

   // 金钱变化分段时间隔(10分钟)
   protected int _proptruncateSpan = 1000 * 60 * 10;

   // 分割时间段(1天)
   protected int _truncateSpan = 1000 * 60 * 60 * 24;

   // 计算里现在的时间段（最新数据间隔）
   protected int _calculageSpan = 60 * 10;

   // 道具TID集合
   public static final int[] Operation_Valids = new int[]{1362, 1369, 54, 56, 1061, 445, 444, 443, 181};

   // 预警道具有效性集合
   public static final int[] Prop_Valids = new int[]{21, 22, 23, 24, 25, 26, 27, 28, 30, 32, 33, 35, 36, 37, 43, 45, 46, 47, 48, 49, 50, 52, 54, 55, 56, 57, 59, 67, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94,
         95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 137, 138, 139, 140, 141, 142, 143, 144, 145,
         146, 147, 148, 149, 150, 151, 152, 154, 155, 156, 163, 164, 165, 166, 167, 168, 169, 170, 173, 174, 175, 176, 178, 179, 180, 181, 183, 184, 185, 186, 187, 188, 189, 190, 191, 192, 193, 194, 195, 196, 197, 198, 199, 200, 201, 202, 203, 204, 205,
         206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223, 224, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 235, 236, 237, 238, 239, 240, 241, 242, 243, 244, 245, 246, 247, 248, 249, 250, 251, 252, 253, 254,
         255, 256, 257, 258, 259, 260, 261, 262, 263, 264, 265, 266, 267, 268, 269, 270, 271, 272, 285, 286, 289, 291, 293, 294, 295, 296, 297, 298, 299, 300, 301, 303, 304, 308, 309, 310, 311, 312, 341, 381, 382, 383, 402, 403, 405, 406, 407, 432, 433,
         434, 442, 443, 444, 445, 446, 447, 448, 449, 450, 451, 452, 453, 454, 455, 456, 457, 458, 459, 460, 461, 462, 463, 464, 465, 466, 467, 468, 469, 470, 471, 472, 473, 474, 475, 476, 477, 478, 479, 480, 481, 482, 483, 484, 485, 486, 487, 488, 489,
         502, 521, 541, 561, 562, 564, 567, 568, 569, 570, 571, 572, 573, 581, 582, 583, 584, 585, 586, 587, 588, 589, 590, 591, 613, 614, 615, 616, 617, 618, 619, 620, 621, 622, 623, 624, 625, 626, 627, 628, 629, 630, 631, 632, 633, 634, 635, 641, 642,
         643, 644, 646, 647, 648, 649, 650, 652, 654, 657, 658, 659, 661, 662, 670, 673, 674, 675, 676, 678, 679, 680, 681, 682, 684, 701, 721, 741, 742, 761, 781, 782, 783, 784, 786, 787, 788, 806, 807, 808, 809, 810, 811, 812, 813, 814, 815, 816, 817,
         818, 819, 820, 821, 822, 841, 941, 942, 943, 944, 945, 946, 947, 948, 949, 950, 951, 952, 953, 954, 955, 956, 957, 958, 959, 960, 961, 962, 963, 964, 965, 966, 967, 968, 969, 970, 971, 972, 973, 974, 975, 976, 977, 978, 979, 980, 981, 982, 983,
         984, 985, 986, 987, 988, 989, 990, 991, 992, 993, 994, 995, 996, 997, 998, 999, 1000, 1001, 1002, 1003, 1010, 1011, 1012, 1021, 1022, 1041, 1042, 1043, 1044, 1045, 1046, 1047, 1061, 1062, 1063, 1064, 1065, 1066, 1067, 1068, 1069, 1070, 1074,
         1075, 1076, 1077, 1078, 1079, 1081, 1082, 1101, 1102, 1103, 1104, 1105, 1106, 1107, 1108, 1109, 1123, 1124, 1125, 1126, 1127, 1128, 1129, 1130, 1131, 1132, 1133, 1134, 1141, 1142, 1143, 1144, 1145, 1146, 1147, 1149, 1150, 1151, 1152, 1153, 1154,
         1155, 1156, 1157, 1158, 1159, 1160, 1161, 1181, 1182, 1183, 1184, 1185, 1186, 1187, 1201, 1221, 1241, 1242, 1243, 1244, 1245, 1261, 1263, 1264, 1265, 1266, 1267, 1268, 1269, 1270, 1271, 1272, 1273, 1274, 1275, 1276, 1277, 1278, 1279, 1280, 1281,
         1282, 1283, 1284, 1301, 1302, 1321, 1341, 1342, 1361, 1362, 1363, 1364, 1365, 1366, 1367, 1368, 1369, 1373, 1374, 1375, 1376, 1377, 1381, 1382, 1383, 1384, 1385, 1386, 1387, 1388, 1389, 1390, 1391, 1392, 1393, 1394, 1395, 1396, 1397, 1398, 1399,
         1400, 1401, 1402, 1421, 1422, 1423, 1424, 1425, 1426, 1451, 1452, 1453, 1454, 1455, 1456, 1457, 1458, 1459, 1460, 1461, 1463, 1465, 1466, 1467, 1468, 1469};

   protected FAnalysisServiceOrderLogic _orderLogic;

   protected FPlatformChargeOrderLogic _chargeOrderLogic;

   protected FLoggerItemLogic _loggerItemLogic;

   protected FAnalysisLoggerOrderLogic _loggerOrderLogic;

   //
   protected FAnalysisServiceAlarmpLogic _alarmpLogc;

   public FAnalysisServiceAlarmpUnit syncAlarmunit(TDateTime date,
                                                   long roleId,
                                                   int propTid,
                                                   int itemType){
            TDateTime recordDate = new TDateTime(date);
            recordDate.truncate(_proptruncateSpan);
            //AS_SVC_ALARMP
            FAnalysisServiceAlarmpUnit alarmpUnit = _alarmpLogc.serach("ROLE_ID=" + roleId + " AND RECORD_DATE=STR_TO_DATE('" + recordDate.format() + "','%Y%m%d%H%i%s') AND ITEM_TID=" + propTid + " AND ITEM_TYPE=" + itemType + " AND GAME_ID=" + _gameId);
            if(null == alarmpUnit){
               alarmpUnit = new FAnalysisServiceAlarmpUnit();
               alarmpUnit.recordDate().assign(recordDate);
               alarmpUnit.recordHour().assign(recordDate);
               alarmpUnit.recordHour().truncateHour();
               alarmpUnit.recordDay().assign(recordDate);
               alarmpUnit.recordDay().truncateDay();
               alarmpUnit.recordWeek().assign(recordDate);
               alarmpUnit.recordWeek().truncateWeek();
               alarmpUnit.recordMonth().assign(recordDate);
               alarmpUnit.recordMonth().truncateMonth();
               alarmpUnit.setRecordInterval(_proptruncateSpan);
               // 设置游戏编号
               alarmpUnit.setGameId(_gameId);
               // 设置角色id
               alarmpUnit.setRoleId(roleId);
               // 设置物品tid
               alarmpUnit.setItemTid(propTid);
               // 设置物品类型
               alarmpUnit.setItemType(itemType);
               // 执行插入操作
               _alarmpLogc.doInsert(alarmpUnit);
            }
            return alarmpUnit;
      //return null;
   }

   public FAnalysisServiceOrderUnit syncOrderLogic(TDateTime date){
            // 获得时间
            TDateTime recordDate = new TDateTime(date);
            recordDate.truncateDay();
            // ............................................................AS_SVC_ORDER
            FAnalysisServiceOrderUnit orderUnit = _orderLogic.serach("RECORD_DATE=STR_TO_DATE('" + recordDate.format() + "','%Y%m%d%H%i%s') AND GAME_ID=" + _gameId);
            if(null == orderUnit){
               // 创建记录
               orderUnit = new FAnalysisServiceOrderUnit();
               orderUnit.recordDate().assign(recordDate);
               orderUnit.recordHour().assign(recordDate);
               orderUnit.recordHour().truncateHour();
               orderUnit.recordDay().assign(recordDate);
               orderUnit.recordDay().truncateDay();
               orderUnit.recordWeek().assign(recordDate);
               orderUnit.recordWeek().truncateWeek();
               orderUnit.recordMonth().assign(recordDate);
               orderUnit.recordMonth().truncateMonth();
               orderUnit.setRecordInterval(_truncateSpan);
               // 设置游戏编号
               orderUnit.setGameId(_gameId);
               _orderLogic.doInsert(orderUnit);
            }
      
            return orderUnit;
     //return null;
   }

   public FAnalysisLoggerOrderUnit syncLoggerOrder(String userId,
                                                   TDateTime date){
            TDateTime recordDate = new TDateTime(date);
            recordDate.truncateDay();
            //AS_LOG_ORDER
            FAnalysisLoggerOrderUnit orderUnit = _loggerOrderLogic.serach("ACCOUNT_PASSPORT='" + userId + "'AND RECORD_DATE=STR_TO_DATE('" + recordDate.format() + "','%Y%m%d%H%i%s') AND GAME_ID=" + _gameId);
            if(null == orderUnit){
               orderUnit = new FAnalysisLoggerOrderUnit();
               orderUnit.setGameId(_gameId);
               orderUnit.setAccountPassport(userId);
               orderUnit.recordDate().assign(recordDate);
               orderUnit.setIsValid(0);
               _loggerOrderLogic.doInsert(orderUnit);
            }
      
            return orderUnit;
      //return null;
   }

   public boolean calculateOld(){
      boolean result = false;
      // 获取链接
      _chargeOrderLogic = new FPlatformChargeOrderLogic(_dataConnection);
      _loggerItemLogic = new FLoggerItemLogic(_dataConnection);
      _orderLogic = new FAnalysisServiceOrderLogic(_dataConnection);
      _loggerOrderLogic = new FAnalysisLoggerOrderLogic(_dataConnection);
      _alarmpLogc = new FAnalysisServiceAlarmpLogic(_dataConnection);
      // 获取最大时间
      FRow chargeOrderRow = _dataConnection.find("SELECT MAX(CREATE_DATE) CREATE_MAX_DATE FROM PF_CHARGE_ORDER");
      TDateTime createMaxDate = new TDateTime(chargeOrderRow.get("create_max_date"));
      FRow loggerItemRow = _dataConnection.find("SELECT MAX(CREATE_DATE) CREATE_MAX_DATE FROM LOG_ROLE_ITEM");
      TDateTime lcreateMaxDate = new TDateTime(loggerItemRow.get("create_max_date"));

      if(lcreateMaxDate.isBefore(createMaxDate)){
         createMaxDate = lcreateMaxDate;
      }

      String createMaxDateValue = null;
      if(createMaxDate.isEmpty()){
         createMaxDateValue = "NOW()-" + _calculageSpan;
      }else{
         createMaxDate.add(-(_calculageSpan * 1000));
         createMaxDateValue = "STR_TO_DATE('" + createMaxDate.format() + "','%Y%m%d%H%i%s')";
      }
      // 日志订单表最大ouid
      long chargeOrderMaxOuid = 0;
      // 日志物品表最大ouid
      long loggerItemMaxOuid = 0;
      // 处理次数
      int validCount = 0;
      
      // 获取未处理订单记录PF_CHARGE_ORDER
      FPlatformChargeOrderUnit[] chargeOrderUnits = _chargeOrderLogic.fetch("OVLD=0 AND CREATE_DATE<" + createMaxDateValue, null, _calculateLimit);
      
      if(0 < chargeOrderUnits.length){

         for(FPlatformChargeOrderUnit chargeOrderUnit : chargeOrderUnits){
            // 获取信息
            long ouid = chargeOrderUnit.ouid();
            String userId = chargeOrderUnit.userId();
            String orderId = chargeOrderUnit.orderId();
            int balanace = chargeOrderUnit.balance();
            TDateTime opTime = chargeOrderUnit.createDate();
            TDateTime operationDate = new TDateTime(opTime);
            operationDate.truncateDay();
            if(ouid > chargeOrderMaxOuid){
               chargeOrderMaxOuid = ouid;
            }
            // 检查订单编号有效性
            if(null == orderId || "0".equals(orderId)){
               chargeOrderUnit.setOvld(true);
               _chargeOrderLogic.doUpdate(chargeOrderUnit, chargeOrderUnit.ouid());
               continue;
            }
            // 检查余额有效性
            if(0 == balanace){
               chargeOrderUnit.setOvld(true);
               _chargeOrderLogic.doUpdate(chargeOrderUnit, chargeOrderUnit.ouid());
               continue;
            }
            // 同步一条记录AS_SVC_ORDER
            FAnalysisServiceOrderUnit orderUnit = syncOrderLogic(operationDate);

            // 查询获取该玩家是否已经被记录
            FRow roleCountRow = _dataConnection.find("SELECT COUNT(*) ROLE_COUNT FROM AS_LOG_ORDER WHERE ACCOUNT_PASSPORT='" + userId + "'");
            // 更新新增充值人数
            if(roleCountRow.getInt("role_count") < 1){
               orderUnit.setNewRoleCount(orderUnit.newRoleCount() + 1);
            }
            
            // 更新充值人次
            orderUnit.setConsumeCount(orderUnit.consumeCount() + 1);
            // 更新充值人数AS_LOG_ORDER
            FAnalysisLoggerOrderUnit loggerOrderUnit = syncLoggerOrder(userId, operationDate);
            int isValid = loggerOrderUnit.isValid();
            if(0 == isValid){
               orderUnit.setRoleCount(orderUnit.roleCount() + 1);
               loggerOrderUnit.setIsValid(1);
               _loggerOrderLogic.doUpdate(loggerOrderUnit, loggerOrderUnit.ouid());
            }
            _orderLogic.doUpdate(orderUnit, orderUnit.ouid());
            result = true;
            // 更新记录为已处理
            chargeOrderUnit.setOvld(true);
            _chargeOrderLogic.doUpdate(chargeOrderUnit, chargeOrderUnit.ouid());
            validCount++;
         }
      }
      // 检查物品日志未处理记录LOG_ROLE_ITEM
      FLoggerItemUnit[] itemUnits = _loggerItemLogic.fetch("OVLD=0 AND CREATE_DATE<" + createMaxDateValue, null, _calculateLimit);
      if(0 < itemUnits.length){
         for(FLoggerItemUnit itemUnit : itemUnits){
            // 获取记录
            long ouid = itemUnit.ouid();
            long roleId = itemUnit.roleId();
            int itemTid = itemUnit.itemTid();
            int itemType = itemUnit.itemType();
            int itemNum = itemUnit.itemNum();
            int opType = itemUnit.opType();
            TDateTime opTime = itemUnit.opTime();
            TDateTime operationDate = new TDateTime(opTime);
            operationDate.truncateDay();
            if(ouid > loggerItemMaxOuid){
               loggerItemMaxOuid = ouid;
            }
            // 检查是否是装备或者道具
            if(3 == itemType || 2 == itemType){
            	//如果是预警道具
               if(-1 != RInteger.indexOf(Prop_Valids, itemTid)){
                  // 更新预警
                  TDateTime propDate = new TDateTime(opTime);
                  propDate.truncate(_proptruncateSpan);//截取10分钟
                  //AS_SVC_ALARMP
                  FAnalysisServiceAlarmpUnit alarmUnit = syncAlarmunit(propDate, roleId, itemTid, itemType);

                  int oldValue = alarmUnit.singleMaxCount();
                  if(itemNum > oldValue){
                     alarmUnit.setSingleMaxCount(itemNum);
                  }
                  alarmUnit.setSumCount(alarmUnit.sumCount() + itemNum);
                  alarmUnit.setSumNumber(alarmUnit.sumNumber() + 1);
                  // 更新
                  _alarmpLogc.doUpdate(alarmUnit, alarmUnit.ouid());
               }
            }

            // 检查物品类型有效性
            if(2 != itemType){
               itemUnit.setOvld(true);
               _loggerItemLogic.doUpdate(itemUnit, itemUnit.ouid());
               continue;
            }
            // 检查是否是首冲奖励
            if(1362 != itemTid){
               itemUnit.setOvld(true);
               _loggerItemLogic.doUpdate(itemUnit, itemUnit.ouid());
               continue;
            }

            // 检查是否是获得礼包
            if(46 != opType){
               itemUnit.setOvld(true);
               _loggerItemLogic.doUpdate(itemUnit, itemUnit.ouid());
               continue;
            }

            // System.out.println(operationDate);
            // 同步一条记录AS_SVC_ORDER
            FAnalysisServiceOrderUnit orderUnit = syncOrderLogic(operationDate);
            // 更新领取礼包个数
            orderUnit.setReceiveGiftNum(orderUnit.receiveGiftNum() + 1);
            _orderLogic.doUpdate(orderUnit, orderUnit.ouid());
            result = true;
            // 更新记录为已处理
            itemUnit.setOvld(true);
            _loggerItemLogic.doUpdate(itemUnit, itemUnit.ouid());
            validCount++;
         }
      }
      _logger.info(this, "calculate", "Calculate service order. (count={1}, valid={2}, charge_order_max_ouid={3}, role_item_max_ouid={4},game_id={5})", chargeOrderUnits.length + itemUnits.length, validCount, chargeOrderMaxOuid, loggerItemMaxOuid, _gameId);
      return result;
   }

   //============================================================
   // <T>计算处理。</T>
   //
   // @return 统计结果
   //============================================================
   @Override
   public EStatisticsResult calculate(){
	   /*FSqlProcedure procedure = new FSqlProcedure("Logic_Order_Statistics");
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
