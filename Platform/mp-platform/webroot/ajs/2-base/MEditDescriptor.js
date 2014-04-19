//==========================================================
// <T>可编辑空间的管理类</T>
//
// @manger
// @history 090921 MAOCY 创建
//==========================================================
function MEditDescriptor(o){
   o = RClass.inherits(this, o, MEditable);
   //..........................................................
   // @property
   o.dataName          = RClass.register(o, new TPtyStr('dataName'));
   o.dataCode          = RClass.register(o, new TPtyStr('dataCode'));
   o.dataDefault       = RClass.register(o, new TPtyStr('dataDefault'));
   o.labelIcon         = RClass.register(o, new TPtyStr('labelIcon'));
   o.labelIconDisable  = RClass.register(o, new TPtyStr('labelIconDisable'));
   o.labelColor        = RClass.register(o, new TPtyStr('labelColor'));
   o.labelAlign        = RClass.register(o, new TPtyStr('labelAlign', EAlign.Left));
   o.labelValign       = RClass.register(o, new TPtyStr('labelValign', EAlign.Middle));
   // @property 编辑设置
   o.editSearch        = RClass.register(o, new TPtyBoolSet('editSearch', 'editAccess', EEditConfig.Search));
   o.editCopy          = RClass.register(o, new TPtyBoolSet('editCopy', 'editAccess', EEditConfig.Copy));
   // @property
   o.editAlign         = RClass.register(o, new TPtyStr('editAlign', EAlign.Left));
   o.editValign        = RClass.register(o, new TPtyStr('editValign', EAlign.Middle));
   o.editFormat        = RClass.register(o, new TPtyStr('editFormat'));
   o.editUnit          = RClass.register(o, new TPtyStr('editUnit'));
   o.editTip           = RClass.register(o, new TPtyStr('editTip'));
   // @property 校验模式设置
   o.validInsert       = RClass.register(o, new TPtyBoolSet('validInsert', 'validAccess', EMode.Insert));
   o.validUpdate       = RClass.register(o, new TPtyBoolSet('validUpdate', 'validAccess', EMode.Update));
   o.validDelete       = RClass.register(o, new TPtyBoolSet('validDelete', 'validAccess', EMode.Delete));
   o.validRequire      = RClass.register(o, new TPtyBool('validRequire', false));
   //..........................................................
   // @attribute
   o.__tip             = null;
   o._validable        = false;
   //..........................................................
   // @event
   o.onDataEnter       = RClass.register(o, new HMouseEnter('onDataEnter'), MEditDescriptor_onDataEnter);
   o.onDataLeave       = RClass.register(o, new HMouseLeave('onDataLeave'), MEditDescriptor_onDataLeave);
   o.onDataMouseOver   = RClass.register(o, new HMouseOver('onDataMouseOver'));
   o.onDataMouseOut    = RClass.register(o, new HMouseOut('onDataMouseOut'));
   o.onDataMouseDown   = RClass.register(o, new HMouseDown('onDataMouseDown'));
   o.onDataMouseUp     = RClass.register(o, new HMouseUp('onDataMouseUp'));
   o.onDataFocus       = RClass.register(o, new HFocus('onDataFocus'));
   o.onDataBlur        = RClass.register(o, new HBlur('onDataBlur'));
   o.onDataClick       = RClass.register(o, new HClick('onDataClick'));
   o.onDataDoubleClick = RClass.register(o, new HDoubleClick('onDataDoubleClick'));
   o.onDataKeyDown     = RClass.register(o, new HKeyDown('onDataKeyDown'), MEditDescriptor_onDataKeyDown);
   o.onDataKeyPress    = RClass.register(o, new HKeyPress('onDataKeyPress'));
   o.onDataKeyUp       = RClass.register(o, new HKeyUp('onDataKeyUp'));
   o.onDataChange      = RClass.register(o, new HChange('onDataChange'), MEditDescriptor_onDataChange);
   o.onDataChanged     = RMethod.empty;
   o.onDataEditBegin   = RMethod.empty;
   o.onDataEditEnd     = MEditDescriptor_onDataEditEnd;
   //..........................................................
   // @process
   o.oeSaveCode        = MEditDescriptor_oeSaveCode;
   //..........................................................
   // @method
   o.canValid          = MEditDescriptor_canValid;
   o.__changedEvent      = new TEvent();
   o.formatValue       = MEditDescriptor_formatValue;
   o.formatText        = MEditDescriptor_formatText;
   o.setInfo           = RMethod.empty;
   o.validText         = MEditDescriptor_validText;
   return o;
}

//==========================================================
// <T>数据更新完成。</T>
//
// @method
// @param s:source:MEditValue 编辑对象
// @param e:event:TEvent 事件对象
//==========================================================
function MEditDescriptor_onDataEnter(s, e){
   var o = this;
   // 检查加载中
   if(s.__progress){
      return;
   }
   // 设置热点样式
   if(s._editable){
      s._hover = true;
      s.refreshStyle();
   }
   // 显示提示信息
   if(o.editTip){
      o.__tip = window.status;
   }
}

//==========================================================
// <T>数据更新完成。</T>
//
// @method
// @param s:source:MEditValue 编辑对象
// @param e:event:TEvent 事件对象
//==========================================================
function MEditDescriptor_onDataLeave(s, e){
   var o = this;
   // 检查加载中
   if(s.__progress){
      return;
   }
   // 取消热点样式
   if(s._editable){
      o._hover = false;
      o.refreshStyle();
   }
   // 恢复提示信息
   if(o.editTip){
      window.status = o.__tip;
   }
}

//==========================================================
// <T>数据键盘按下事件。</T>
//
// @method
// @param s:source:MEditValue 编辑对象
// @param e:event:TEvent 事件对象
//==========================================================
function MEditDescriptor_onDataKeyDown(s, e){
   var o = this;
   if(s._editable && !s._disabled){
      // 校验数据
      s._invalidText = o.validText(s.text());
      s.refreshStyle();
   }
}

//==========================================================
// <T>数据修改事件。</T>
//
// @method
// @param s:source:MEditValue 编辑对象
// @param e:event:TEvent 事件对象
//==========================================================
function MEditDescriptor_onDataChange(s, e){
   var o = this;
   if(s._editable && !s._disabled){
      if(s.isTextChanged()){
         // 校验数据
         var t = s.text();
         var vt = s._invalidText = o.validText(t);
         if(vt){
            s.refreshStyle();
            //RLogger.debug(o, 'Edit changed event (text=[{0}]->[{1}])', s.dataText, t);
            //s.storeText();
            //o.onDataChanged(s, e);
            // o.onEditEnd(s, e);
         }else{
            //s.setEditStyle(EStyle.Invalid);
            //RLogger.debug(o, 'Edit data invalid (text=[{0}]->[{1}])', s.dataText, t);
            //s.storeText(s, e);
         }
         o.callEvent('onDataChange', o, o.__changedEvent);
      }
   }
}

//==========================================================
// <T>数据更新完成。</T>
//
// @method
// @param s:source:MEditValue 编辑对象
// @param e:event:TEvent 事件对象
//==========================================================
function MEditDescriptor_onDataEditEnd(s, e){
   var o = this;
   // 校验数据内容
   var vt = s._invalidText = o.validText(s.text());
   if(vt){
      // 校验失败
      RLogger.debug(this, 'Edit valid failed ({0})', vt);
   }else{
      // 校验成功
      s.commitValue();
   }
   if(s.isTextChanged()){
	   o.callEvent('onDataChange', o, o.__changedEvent);
   }
   s.refreshStyle();
}

//==========================================================
// <T>存储代码处理。</T>
//
// @method
// @param e:event:TEvent 事件对象
//==========================================================
function MEditDescriptor_oeSaveCode(e){
   var o = this;
   if(!RString.isEmpty(o.dataName) && !RString.isEmpty(o.dataCode)){
      e.values.set(o.dataName, o.dataCode);
   }
   return EEventStatus.Stop;
}

//==========================================================
// <T>测试当前模式下是否可以校验。</T>
//
// @method
// @param m:mode:EMode 模式
// @return Boolean 可否校验
//==========================================================
function MEditDescriptor_canValid(m){
   var o = this;
   switch(RString.nvl(m, o._emode)){
      case EMode.Insert:
         return o.validInsert;
      case EMode.Update:
         return o.validUpdate;
      case EMode.Delete:
         return o.validDelete;
   }
}

//==========================================================
// <T>格式化文本值到内容。</T>
//
// @method
// @param v:value:String 数据内容
//==========================================================
function MEditDescriptor_formatValue(v){
   return RString.nvl(v);
}

//==========================================================
// <T>格式化文本内容到值。</T>
//
// @method
// @param t:text:String 显示内容
//==========================================================
function MEditDescriptor_formatText(t){
   return RString.nvl(t);
}

//==========================================================
// <T>检测显示内容正确性。</T>
// <P>如果正确返回空，如果不正确，返回问题描述。</P>
//
// @method
// @param t:text:String 显示内容
//==========================================================
function MEditDescriptor_validText(t){
   var o = this;
   // 必须性检查
   //if(o.validRequire){
      //if(RString.isEmpty(t)){
         //return RContext.get('MEditDescriptor:Empty');
      //}
   //}
}
