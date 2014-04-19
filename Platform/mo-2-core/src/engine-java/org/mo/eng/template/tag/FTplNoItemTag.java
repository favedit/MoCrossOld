package org.mo.eng.template.tag;

import org.mo.com.lang.FFatalError;
import org.mo.com.lang.FObjects;
import org.mo.com.lang.IObjects;
import org.mo.com.lang.RString;
import org.mo.com.lang.generic.MString;
import org.mo.eng.template.FTemplateContext;

public class FTplNoItemTag
      extends FAbstractTplTag
{
   public final static String NAME = "HasItem";

   private String _value;

   private String _notValue;

   private String _filter;

   private String _source;

   protected String[] _filterItems;

   @Override
   public void construct(FTemplateContext context){
      _nowrap = "r";
      super.construct(context);
   }

   protected Object[] filterObjects(Object[] objects){
      FObjects<Object> items = new FObjects<Object>(Object.class);
      for(Object item : objects){
         _context.define("item", item);
         String value = _context.parseString(_filterItems[0]);
         if(_filterItems[1].equals(value)){
            items.push(item);
         }
      }
      return items.toObjects();
   }

   @Override
   public void onDump(MString dump){
      dump.append("Loop ");
      dump.append("[ source=", _source);
      dump.append(", value=", _value);
      dump.append(" ]");
   }

   @Override
   public int onStart(){
      Object source = _context.parse(_source);
      if(null == source){
         return CONTINUE;
      }
      if(source instanceof IObjects<?>){
         Object[] items = ((IObjects<?>)source).toObjects();
         // 过滤数据对象
         if(!RString.isEmpty(_filter)){
            _filterItems = RString.splitTwo(_filter, '=', true);
            if(_filterItems.length != 2){
               throw new FFatalError("Invalid filter format (filter={0})", _filter);
            }
            items = filterObjects(items);
         }
         // 定义输出数据
         if(!RString.isEmpty(_value) && items.length > 0){
            _context.append(_value);
         }
         if(!RString.isEmpty(_notValue) && items.length == 0){
            _context.append(_notValue);
         }
         if(items.length > 0){
            return STOP;
         }
      }else{
         throw new FFatalError("Source object is not collection (source={0})", source);
      }
      return CONTINUE;
   }

   public void setFilter(String filter){
      _filter = filter;
   }

   public void setNotValue(String notValue){
      _notValue = notValue;
   }

   public void setSource(String source){
      _source = source;
   }

   public void setValue(String value){
      _value = value;
   }
}
