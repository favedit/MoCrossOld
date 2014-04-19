package org.mo.com.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Iterator;
import org.mo.com.collections.FRow;
import org.mo.com.lang.FFatalError;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;

//============================================================
// <T>数据集合阅读器。</T>
//============================================================
public class FSqlDatasetReader
      implements
         ISqlDatasetReader
{
   // 日志输出接口
   private static ILogger _logger = RLogger.find(FSqlDatasetReader.class);

   // 数据库链接
   protected MSqlConnection _connection;

   // 描述信息
   protected FSqlDatasetMeta _meta;

   // 打开
   protected boolean _opened;

   // 状态对象
   protected Statement _statement;

   // 结果集对象
   protected ResultSet _result;

   // 是否有下一个
   protected boolean _hasNext;

   // 列总数
   protected int _columnCount;

   // 名称集合
   protected String[] _names;

   // 类型集合
   protected int[] _typeCds;

   // 当前行
   protected FRow _current = new FRow();

   // 当前行索引
   protected int _currentIndex;

   //============================================================
   // <T>构造数据集合阅读器。</T>
   //
   // @param connection 数据链接
   //============================================================
   public FSqlDatasetReader(MSqlConnection connection){
      _connection = connection;
   }

   //============================================================
   // <T>获得数据描述信息。</T>
   //
   // @return 数据描述信息
   //============================================================
   @Override
   public FSqlDatasetMeta meta(){
      if(null == _meta){
         _meta = new FSqlDatasetMeta();
         try{
            _connection.fillDatabaseMeta(_meta, _result.getMetaData());
         }catch(Exception e){
            throw new FFatalError(e);
         }
      }
      return _meta;
   }

   //============================================================
   // <T>打开阅读器。</T>
   //
   // @param connection 链接
   // @param sql 链接
   //============================================================
   public void open(Connection connection,
                    String sql){
      // 检查标志
      if(_opened){
         return;
      }
      _opened = true;
      // 打开处理
      Exception exception = null;
      try{
         if(_logger.debugAble()){
            _logger.debug(this, "Open", "Open dataset reader. (sql={1})", sql);
         }
         _currentIndex = 0;
         _statement = connection.createStatement();
         _result = _statement.executeQuery(sql);
         if(null != _result){
            // 获得数据集描述 
            ResultSetMetaData meta = _result.getMetaData();
            // 获得数据列信息
            _columnCount = meta.getColumnCount();
            _names = new String[_columnCount];
            _typeCds = new int[_columnCount];
            for(int n = 0; n < _columnCount; n++){
               _names[n] = meta.getColumnName(n + 1).toLowerCase();
               _typeCds[n] = meta.getColumnType(n + 1);
            }
            _hasNext = _result.next();
         }
      }catch(Exception e){
         exception = e;
         // 关闭结果集
         if(null != _result){
            try{
               _result.close();
            }catch(Exception ex){
               exception = ex;
            }
         }
         // 关闭查询声明
         if(null != _statement){
            try{
               _statement.close();
            }catch(Exception ex){
               exception = ex;
            }
         }
         // 如果产生错误，则抛出
         if(null != exception){
            throw new FFatalError(exception, "Open dataset reader failure. (sql={1})", sql);
         }
      }
   }

   //============================================================
   // <T>获得迭代器。</T>
   //
   // @return 迭代器
   //============================================================
   @Override
   public Iterator<FRow> iterator(){
      return this;
   }

   //============================================================
   // <T>判断是否存在下一行。</T>
   //
   // @return 是否存在下一行
   //============================================================
   @Override
   public boolean hasNext(){
      return _hasNext;
   }

   //============================================================
   // <T>获得当前索引。</T>
   //
   // @return 索引
   //============================================================
   @Override
   public int currentIndex(){
      return _currentIndex;
   }

   //============================================================
   // <T>获得当前行数据。</T>
   //
   // @return 行数据
   //============================================================
   @Override
   public FRow currentRow(){
      return _current;
   }

   //============================================================
   // <T>读取下一行数据。</T>
   //
   // @return 行数据
   //============================================================
   @Override
   public FRow next(){
      if(_hasNext){
         try{
            _connection.fillUnit(_current, _columnCount, _names, _typeCds, _result);
            _hasNext = _result.next();
            _currentIndex++;
         }catch(Exception e){
            throw new FFatalError(e);
         }
      }
      return _current;
   }

   //============================================================
   // <T>移除数据处理。</T>
   //============================================================
   @Override
   public void remove(){
      throw new NoSuchMethodError();
   }

   //============================================================
   // <T>关闭阅读器。</T>
   //============================================================
   @Override
   public void close(){
      // 检查标志
      if(!_opened){
         return;
      }
      _opened = false;
      // 关闭结果集
      Exception exception = null;
      if(null != _result){
         try{
            _result.close();
            _result = null;
         }catch(Exception e){
            exception = e;
         }
      }
      // 关闭查询声明
      if(null != _statement){
         try{
            _statement.close();
            _statement = null;
         }catch(Exception e){
            exception = e;
         }
      }
      // 如果产生错误，则抛出
      if(null != exception){
         throw new FFatalError(exception);
      }
      _logger.debug(this, "Close", "Close dataset reader. (read_count={1})", _currentIndex);
   }
}
