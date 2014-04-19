package org.mo.game.editor.web.apl.form;

import org.mo.com.collections.FStack;
import org.mo.com.lang.RDump;
import org.mo.com.lang.RString;
import org.mo.com.lang.generic.TDumpInfo;

public class FWebFormInfos
      extends FStack<FWebFormInfo>
{

   private int _index = 0;

   public FWebFormInfos(){
      super(FWebFormInfo.class);
   }

   public FWebFormInfo back(){
      _index = Math.max(0, _index - 1);
      return get(Math.max(0, _index - 1));
   }

   public void checkLast(){
      if(_index > 1){
         FWebFormInfo last = _items[_index - 1];
         FWebFormInfo prior = _items[_index - 2];
         if(null != last && null != prior && prior.equals(last)){
            _index--;
         }
      }
   }

   @Override
   public TDumpInfo dump(TDumpInfo info){
      RDump.dump(info, this);
      info.append("(index:");
      info.appendInt(_index);
      info.append(")");
      info.append("(count:");
      info.appendInt(_count);
      info.append(")");
      info.append("\n");
      int cl = Integer.toString(_count).length();
      for(int n = 0; n < _index; n++){
         String pad = RString.leftPad(Integer.toString(n), cl);
         RDump.dump(info, pad, _items[n]);
      }
      return info;
   }

   public FWebFormInfo findNext(){
      FWebFormInfo info = get(_index);
      if(null == info){
         info = new FWebFormInfo();
         if(_index < _count){
            set(_index, info);
         }else{
            push(info);
         }
      }
      _index++;
      return info;
   }

   public FWebFormInfo getBack(){
      return get(_index - 2);
   }

   public FWebFormInfo getPrior(){
      return get(_index - 1);
   }

   public FWebFormInfo prior(){
      return get(--_index);
   }

   public void reset(){
      _index = 0;
   }
}
