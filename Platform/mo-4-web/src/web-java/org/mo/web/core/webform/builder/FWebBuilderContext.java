package org.mo.web.core.webform.builder;

import org.mo.com.collections.FDataset;
import org.mo.com.collections.FRow;
import org.mo.com.lang.FAttributes;
import org.mo.com.lang.FString;
import org.mo.eng.data.IConnectionConsole;

//============================================================
// <T>表单建立环境。</T>
//============================================================
public class FWebBuilderContext
{
   // 数据库链接接口
   protected IConnectionConsole _connectionConsole;

   // 分页大小
   private int _pageSize = 20;

   // 分页索引
   private int _pageIndex = 1;

   // 参数集合
   protected FAttributes _parameters = new FAttributes();

   // 焦点数据集合
   protected FDataset _focusDataset;

   // 焦点数据行
   protected FRow _focusRow;

   // 网页字符串
   protected FString _html = new FString();

   //============================================================
   // <T>构造表单建立环境。</T>
   //============================================================
   public FWebBuilderContext(){
   }

   //============================================================
   // <T>获得数据库链接接口。</T>
   //
   // @return 数据库链接接口
   //============================================================
   public IConnectionConsole connectionConsole(){
      return _connectionConsole;
   }

   //============================================================
   // <T>设置数据库链接接口。</T>
   //
   // @param connectionConsole 数据库链接接口
   //============================================================
   public void setConnectionConsole(IConnectionConsole connectionConsole){
      _connectionConsole = connectionConsole;
   }

   //============================================================
   // <T>获得分页大小。</T>
   //
   // @return 分页大小
   //============================================================
   public int pageSize(){
      return _pageSize;
   }

   //============================================================
   // <T>设置分页大小。</T>
   //
   // @param pageSize 分页大小
   //============================================================
   public void setPageSize(int pageSize){
      _pageSize = pageSize;
   }

   //============================================================
   // <T>获得分页索引。</T>
   //
   // @return 分页索引
   //============================================================
   public int pageIndex(){
      return _pageIndex;
   }

   //============================================================
   // <T>设置分页索引。</T>
   //
   // @param pageIndex 分页索引
   //============================================================
   public void setPageIndex(int pageIndex){
      _pageIndex = pageIndex;
   }

   //============================================================
   // <T>获得参数集合。</T>
   //
   // @return 参数集合
   //============================================================
   public FAttributes parameters(){
      return _parameters;
   }

   //============================================================
   // <T>获得焦点数据集合。</T>
   //
   // @return 焦点数据集合
   //============================================================
   public FDataset focusDataset(){
      return _focusDataset;
   }

   //============================================================
   // <T>设置焦点数据集合。</T>
   //
   // @param focusDataset 焦点数据集合
   //============================================================
   public void setFocusDataset(FDataset focusDataset){
      _focusDataset = focusDataset;
   }

   //============================================================
   // <T>获得焦点数据行。</T>
   //
   // @return 焦点数据行
   //============================================================
   public FRow focusRow(){
      return _focusRow;
   }

   //============================================================
   // <T>设置焦点数据行。</T>
   //
   // @param focusRow 焦点数据行
   //============================================================
   public void setFocusRow(FRow focusRow){
      _focusRow = focusRow;
   }

   //============================================================
   // <T>获得网页字符串。</T>
   //
   // @return 网页字符串
   //============================================================
   public FString html(){
      return _html;
   }

   //============================================================
   // <T>追加网页字符串。</T>
   //
   // @param value 网页字符串
   //============================================================
   public void appendHtml(String value){
      _html.append(value);
   }
}
