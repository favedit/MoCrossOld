package org.mo.core.persistent.io;

import org.mo.com.lang.FDictionary;

public class FNamingObjidMap
      extends FDictionary<Object>
{
   protected int[] _dataIndexs;

   protected int[] _keys;

   protected int[] _nameIndexs;

   public FNamingObjidMap(){
      super(Object.class);
   }

   public void add(int nameIndex,
                   int dataIndex,
                   int key,
                   String name,
                   Object value){
      set(name, value);
      int find = indexOf(name);
      _nameIndexs[find] = nameIndex;
      _dataIndexs[find] = dataIndex;
      _keys[find] = key;
   }

   public int dataIndex(int index){
      return (index >= 0 && index < _count) ? _dataIndexs[index] : -1;
   }

   @Override
   public void ensureSize(int count){
      super.ensureSize(count);
      int total = _names.length;
      int[] nameIndexs = new int[total];
      int[] dataIndexs = new int[total];
      int[] keys = new int[total];
      if(_count > 0){
         System.arraycopy(_nameIndexs, 0, nameIndexs, 0, _count);
         System.arraycopy(_dataIndexs, 0, dataIndexs, 0, _count);
         System.arraycopy(_keys, 0, keys, 0, _count);
      }
      _nameIndexs = nameIndexs;
      _dataIndexs = dataIndexs;
      _keys = keys;
   }

   public int indexOfDataIndex(int index){
      int n = -1;
      while(++n < _count){
         if(_dataIndexs[n] == index){
            return n;
         }
      }
      return -1;
   }

   public int indexOfKey(int key){
      int n = -1;
      while(++n < _count){
         if(_keys[n] == key){
            return n;
         }
      }
      return -1;
   }

   public int indexOfNameIndex(int index){
      int n = -1;
      while(++n < _count){
         if(_nameIndexs[n] == index){
            return n;
         }
      }
      return -1;
   }

   public int key(int index){
      return (index >= 0 && index < _count) ? _keys[index] : -1;
   }

   public int nameIndex(int index){
      return (index >= 0 && index < _count) ? _nameIndexs[index] : -1;
   }

   public Object remove(int position){
      if(position >= 0 && position < _count){
         int move = _count - position - 1;
         if(move > 0){
            System.arraycopy(_nameIndexs, position + 1, _nameIndexs, position, move);
            System.arraycopy(_dataIndexs, position + 1, _dataIndexs, position, move);
            System.arraycopy(_keys, position + 1, _keys, position, move);
         }
         _nameIndexs[_count - 1] = 0;
         _dataIndexs[_count - 1] = 0;
         _keys[_count - 1] = 0;
         return super.erase(position);
      }
      return null;
   }
}
