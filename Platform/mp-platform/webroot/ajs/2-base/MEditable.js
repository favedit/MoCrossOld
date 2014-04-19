//==========================================================
// <T>可编辑管理接口。</T>
//
// @manager
// @param o:object:Object 拥有对象
// @history 091201 MAOCY 创建
//==========================================================
function MEditable(o){
   o = RClass.inherits(this, o);
   //..........................................................
   // @property
   o.editInsert = RClass.register(o, new TPtyBoolSet('editInsert', 'editMode', EMode.Insert), false);
   o.editUpdate = RClass.register(o, new TPtyBoolSet('editUpdate', 'editMode', EMode.Update), false);
   o.editDelete = RClass.register(o, new TPtyBoolSet('editDelete', 'editMode', EMode.Delete), false);
   o.editZoom   = RClass.register(o, new TPtyBoolSet('editZoom', 'editMode', EMode.Zoom), false);
   //..........................................................
   // @attribute
   o._absEdit   = true;
   o._editable  = false;
   //..........................................................
   // @method
   o.canEdit    = MEditable_canEdit;
   return o;
}

//==========================================================
// <T>测试指定模式下是否可以编辑。</T>
//
// @method
// @param m:mode:EMode 模式
// @return Boolean 可否编辑
//==========================================================
function MEditable_canEdit(m){
   var o = this;
   switch(RString.nvl(m, o._emode)){
      case EMode.Insert:
         return o.editInsert;
      case EMode.Update:
         return o.editUpdate;
      case EMode.Delete:
         return o.editDelete;
      case EMode.Zoom:
         return o.editZoom;
   }
}
