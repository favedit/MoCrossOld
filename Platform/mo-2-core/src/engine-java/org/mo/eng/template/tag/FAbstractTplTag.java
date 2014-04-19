package org.mo.eng.template.tag;

import org.mo.com.lang.FObject;
import org.mo.com.lang.FObjects;
import org.mo.com.lang.generic.MString;
import org.mo.com.lang.generic.TDumpInfo;
import org.mo.eng.template.FTemplateContext;

//============================================================
// <T>模板标签基类。</T>
//============================================================
public abstract class FAbstractTplTag
      extends FObject
      implements
         ITplTag
{
   protected FObjects<ITplTag> _children = new FObjects<ITplTag>(ITplTag.class);

   protected FTemplateContext _context;

   protected String _nowrap;

   @Override
   public ITplTag child(int index){
      return _children.get(index);
   }

   @Override
   public void construct(FTemplateContext context){
      _context = context;
   }

   @Override
   public int count(){
      return _children.count();
   }

   @Override
   public TDumpInfo dump(TDumpInfo info){
      onDump(info);
      for(ITplTag tag : _children){
         info.appendLine();
         info.increaseLevel(tag);
         tag.dump(info);
      }
      return info;
   }

   @Override
   public boolean nowrapLeft(){
      if(null != _nowrap){
         return (_nowrap.indexOf('l') != -1);
      }
      return false;
   }

   @Override
   public boolean nowrapRight(){
      if(null != _nowrap){
         return (_nowrap.indexOf('r') != -1);
      }
      return false;
   }

   /**
    * 相应调试时获取数据信息
    * 
    * @param dump 获取的数据信息
    */
   public void onDump(MString dump){
      dump.append("FAbstractTplTag");
   }

   @Override
   public int onEnd(){
      return CONTINUE;
   }

   @Override
   public int onLoop(){
      return STOP;
   }

   /* (non-Javadoc)
    * @see org.mo.eng.template.tag.ITplTag#onStart()
    */
   @Override
   public int onStart(){
      return INCLUDE;
   }

   @Override
   public void push(ITplTag tag){
      _children.push(tag);
   }

   public void setNowrap(String value){
      _nowrap = value;
   }
}
