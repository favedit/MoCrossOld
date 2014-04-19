//==========================================================
// <T>可加载和保存属性的接口。</T>
//
// @manger
// @history 091012 MAOCY 创建
//==========================================================
function MConfig(o){
   o = RClass.inherits(this, o);
   // @method
   o.loadConfig = MConfig_loadConfig;
   o.saveConfig = MConfig_saveConfig;
   return o;
}

//==========================================================
// <T>将数据对象的属性，加载到当前对象中。</T>
//
// @method
// @param c:config:TNode XML信息节点
//==========================================================
function MConfig_loadConfig(c){
   var o = this;
   var ps = RClass.find(o.constructor).annotations(EAnnotation.Property);
   for(var n in ps){
      var p = ps[n];
      if(p.force){
         p.load(o, c);
      }else{
         if(c.contains(p.config)){
            p.load(o, c);
         }else if(null == o[p.name]){
            o[p.name] = p.value;
         }
      }
   }
}

//==========================================================
// <T>将当前对象的属性，存储到数据对象中。</T>
//
// @method
// @param c:config:TNode XML信息节点
//==========================================================
// config(TNode)
function MConfig_saveConfig(c){
   var o = this;
   var ps = RClass.find(o.constructor).annotations(EAnnotation.Property);
   for(var n in ps){
      ps[n].save(o, c);
   }
}
