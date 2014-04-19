package org.mo.com.collections;

import org.mo.com.lang.FFatalError;
import org.mo.com.lang.FString;
import org.mo.com.lang.IAttributes;
import org.mo.com.lang.RDump;
import org.mo.com.lang.RInteger;
import org.mo.com.lang.RString;
import org.mo.com.lang.generic.MObjects;
import org.mo.com.lang.generic.TDumpInfo;
import org.mo.com.xml.FXmlNode;

//============================================================
// <T>数据集合对象。</T>
//============================================================
public class FDataset
      extends MObjects<FRow>
{
   // 名称定义
   public final static String NAME = "Dataset";

   // 属性编号定义
   public final static String PTY_ID = "_id";

   // 属性名称定义
   public final static String PTY_NAME = "name";

   // 属性页号定义
   public final static String PTY_PAGE = "page";

   // 属性页数定义
   public final static String PTY_PAGE_COUNT = "page_count";

   // 属性页大小定义
   public final static String PTY_PAGE_SIZE = "page_size";

   // 属性总数定义
   public final static String PTY_TOTAL = "total";

   // 代码
   private String _code;

   // 总记录数
   protected int _total;

   // 分页记录数
   protected int _pageSize = RInteger.SIZE_4K;

   // 总页数
   protected int _pageCount;

   // 页号
   protected int _page;

   //============================================================
   // <T>构造数据集合对象。</T>
   //============================================================
   public FDataset(){
      super(FRow.class);
   }

   //============================================================
   // <T>获得代码。</T>
   //
   // @return 代码
   //============================================================
   public String code(){
      return _code;
   }

   //============================================================
   // <T>设置代码。</T>
   //
   // @param code 代码
   //============================================================
   public void setCode(String code){
      _code = code;
   }

   //============================================================
   // <T>获得总记录数。</T>
   //
   // @return 总记录数
   //============================================================
   public int total(){
      return _total;
   }

   //============================================================
   // <T>设置总记录数。</T>
   //
   // @param total 总记录数
   //============================================================
   public void setTotal(int total){
      _total = total;
   }

   //============================================================
   // <T>获得分页记录数。</T>
   //
   // @return 分页记录数
   //============================================================
   public int pageSize(){
      return _pageSize;
   }

   //============================================================
   // <T>设置分页记录数。</T>
   //
   // @param pageSize 分页记录数
   //============================================================
   public void setPageSize(int pageSize){
      _pageSize = pageSize;
   }

   //============================================================
   // <T>获得总页数。</T>
   //
   // @return 总页数
   //============================================================
   public int pageCount(){
      return _pageCount;
   }

   //============================================================
   // <T>设置总页数。</T>
   //
   // @param pageCount 总页数
   //============================================================
   public void setPageCount(int pageCount){
      _pageCount = pageCount;
   }

   //============================================================
   // <T>获得页号。</T>
   //
   // @return 页号
   //============================================================
   public int page(){
      return _page;
   }

   //============================================================
   // <T>获得首页号。</T>
   //
   // @return 页号
   //============================================================
   public int firstPage(){
      return 1;
   }

   //============================================================
   // <T>获得前一页号。</T>
   //
   // @return 页号
   //============================================================
   public int priorPage(){
      if(_page > 1){
         return _page - 1;
      }
      return _page;
   }

   //============================================================
   // <T>获得前一页号。</T>
   //
   // @return 页号
   //============================================================
   public int nextPage(){
      if(_page < _pageCount - 1){
         return _page + 1;
      }
      if(_pageCount > 1){
         return _pageCount - 1;
      }
      return 1;
   }

   //============================================================
   // <T>获得前一页号。</T>
   //
   // @return 页号
   //============================================================
   public int lastPage(){
      if(_pageCount > 1){
         return _pageCount - 1;
      }
      return 1;
   }

   //============================================================
   // <T>设置页号。</T>
   //
   // @param page 页号
   //============================================================
   public void setPage(int page){
      _page = page;
   }

   //============================================================
   // <T>创建一行数据。</T>
   //
   // @return 行数据
   //============================================================
   public FRow createRow(){
      FRow row = new FRow();
      push(row);
      return row;
   }

   //============================================================
   // <T>查找执行名称和内容的数据单元。</T>
   //
   // @param name 指定名称
   // @param value 指定内容
   // @return 数据单元
   //============================================================
   public FRow find(String name,
                    String value){
      for(FRow row : this){
         if(RString.equalsIgnoreCase(row.get(name), value)){
            return row;
         }
      }
      return null;
   }

   //============================================================
   // <T>按照节点指定属性进行排序。</T>
   //
   // @param name 属性名称
   //============================================================
   public void sortAsInteger(String name){
      sortAsInteger(name, true);
   }

   //============================================================
   // <T>按照节点指定属性进行排序。</T>
   //
   // @param name 属性名称
   // @param type 是否按照升序排列
   //============================================================
   public void sortAsInteger(String name,
                             boolean type){
      boolean compare = false;
      for(int j = 0; j < _count; j++){
         for(int i = 0; i < _count - 1 - j; i++){
            int value1 = RInteger.parse(_items[i].get(name));
            int value2 = RInteger.parse(_items[i + 1].get(name));
            compare = type ? value1 > value2 : value1 < value2;
            if(compare){
               swap(i, i + 1);
            }
         }
      }
   }

   //============================================================
   // <T>将当前列表打包为字符串内容。</T>
   //
   // @return 打包字符串
   //============================================================
   public String pack(){
      StringBuilder pack = new StringBuilder();
      for(FRow row : this){
         String rowPack = row.pack();
         pack.append(Integer.toString(rowPack.length()));
         pack.append("_");
         pack.append(rowPack);
      }
      return pack.toString();
   }

   //============================================================
   // <T>将打包字符串内容,反解到当前列表中。</T>
   //
   // @param pack 打包字符串
   //============================================================
   public void unpack(String pack){
      unpack(pack, false);
   }

   //============================================================
   // <T>将打包字符串内容,反解到当前列表中。</T>
   //
   // @param pack 打包字符串
   // @param merge 合并标志
   //============================================================
   public void unpack(String pack,
                      boolean merge){
      // 是否合并
      if(!merge){
         clear();
      }
      // 放入内容
      if(!RString.isEmpty(pack)){
         // 初始化变量
         int position = 0;
         int find = -1;
         int itemLength = 0;
         String itemPack = "";
         // 反解打包字符串
         try{
            while(true){
               find = pack.indexOf("_", position);
               if(find >= 0){
                  itemLength = Integer.parseInt(pack.substring(position, find));
                  itemPack = pack.substring(find + 1, find + 1 + itemLength);
               }else{
                  break;
               }
               position = find + 1 + itemLength;
               FRow row = new FRow();
               row.unpack(itemPack);
               push(row);
            }
         }catch(Exception e){
            throw new FFatalError(e);
         }
      }
   }

   //============================================================
   // <T>变换为一个XML节点。</T>
   //
   // @return 节点
   //============================================================
   public FXmlNode toXmlNode(){
      FXmlNode outDsNode = new FXmlNode(FDataset.NAME);
      outDsNode.set(PTY_NAME, _code);
      outDsNode.set(PTY_PAGE, _page);
      outDsNode.set(PTY_PAGE_SIZE, _pageSize);
      outDsNode.set(PTY_PAGE_COUNT, _pageCount);
      outDsNode.set(PTY_TOTAL, _total);
      for(FRow row : this){
         IAttributes attributes = outDsNode.createNode(FRow.NAME).attributes();
         attributes.set(PTY_ID, row.get("id_rownum"));
         int count = row.count();
         for(int n = 0; n < count; n++){
            String name = row.name(n);
            if(!"id_rownum".equals(name)){
               String value = row.value(n);
               if(!RString.isEmpty(value)){
                  attributes.set(name, value);
               }
            }

         }
      }
      return outDsNode;
   }

   //============================================================
   // <T>生成运行信息。</T>
   //
   // @param info 运行信息
   // @return 运行信息
   //============================================================
   @Override
   public TDumpInfo dump(TDumpInfo info){
      RDump.dump(info, this);
      info.append(" Size:" + _count + "/" + pageSize() + "/" + total() + " Page:" + page() + "/" + pageCount());
      if(!isEmpty()){
         FString sLeftSpace = new FString(RString.repeat(RDump.LEVEL_SPACE, info.level() + 1));
         info.append(" {\n");
         for(FRow oValue : this){
            info.append(sLeftSpace);
            info.append(oValue.dump());
            info.append("\n");
         }
         info.append(RString.repeat("   ", info.level()));
         info.append("}");
      }
      return info;
   }
}
