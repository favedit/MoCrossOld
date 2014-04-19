package org.mo.jfa.face.logic.webform;

import org.mo.com.collections.FStack;
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

   @Override
   public TDumpInfo dump(TDumpInfo info){
      info.append("(index:");
      info.appendInt(_index);
      info.append(")");
      super.dump(info);
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
