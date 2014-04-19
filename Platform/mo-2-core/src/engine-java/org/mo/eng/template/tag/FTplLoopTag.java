package org.mo.eng.template.tag;

import org.mo.com.lang.FFatalError;
import org.mo.com.lang.FObjects;
import org.mo.com.lang.IObjects;
import org.mo.com.lang.RString;
import org.mo.com.lang.generic.MString;
import org.mo.eng.template.FTemplateContext;

public class FTplLoopTag
      extends FAbstractTplTag
{
   public final static String NAME = "Loop";

   public final static String REF_COUNT = "-count";

   public final static String REF_POSITION = "-position";

   public final static String REF_INDEX = "-index";

   protected String _alias;

   protected int _count;

   protected String _filter;

   protected Object[] _items;

   protected String _order;

   protected int _position;

   protected String _source;

   public void setAlias(String alias){
      _alias = alias;
   }

   public void setFilter(String filter){
      _filter = filter;
   }

   public void setOrder(String order){
      _order = order;
   }

   public void setSource(String source){
      _source = source;
   }

   @Override
   public void construct(FTemplateContext context){
      _nowrap = "r";
      super.construct(context);
   }

   protected Object[] filterObjects(Object[] objects){
      ETplFilterType type = ETplFilterType.None;
      String[] splits = RString.splitTwo(_filter, "!=", true);
      if(null != splits){
         type = ETplFilterType.NotEquals;
      }else{
         splits = RString.splitTwo(_filter, '=', true);
         if(null == splits){
            throw new FFatalError("Invalid filter format (filter={0})", _filter);
         }
         type = ETplFilterType.Equals;
      }
      // Filter
      FObjects<Object> items = new FObjects<Object>(Object.class);
      for(Object item : objects){
         _context.define(_alias, item);
         String value = _context.parseString(splits[0]);
         if(ETplFilterType.Equals == type && splits[1].equals(value)){
            items.push(item);
         }else if(ETplFilterType.NotEquals == type && !splits[1].equals(value)){
            items.push(item);
         }
      }
      return items.toObjects();
   }

   @Override
   public int onStart(){
      Object source = _context.parse(_source);
      if(null != source){
         if(source instanceof IObjects<?>){
            _items = ((IObjects<?>)source).toObjects();
            // 过滤数据对象
            if(!RString.isEmpty(_filter)){
               _items = filterObjects(_items);
            }
            // 定义循环数据
            _position = 0;
            _count = _items.length;
            if(_count > 0){
               _context.define(_alias, _items[_position]);
               _context.define(_alias + REF_COUNT, _count);
               _context.define(_alias + REF_POSITION, _position);
               _context.define(_alias + REF_INDEX, _position + 1);
               return INCLUDE;
            }
         }else{
            throw new FFatalError("Source object is not collection (source={0}, instance={1})", _source, source);
         }
      }
      return STOP;
   }

   @Override
   public int onLoop(){
      if(++_position < _items.length){
         _context.define(_alias, _items[_position]);
         _context.define(_alias + REF_POSITION, _position);
         _context.define(_alias + REF_INDEX, _position + 1);
         return CONTINUE;
      }
      return STOP;
   }

   @Override
   public void onDump(MString dump){
      dump.append("Loop ");
      dump.append("[ source=", _source);
      dump.append(", alias=", _alias);
      dump.append(" ]");
   }
}
