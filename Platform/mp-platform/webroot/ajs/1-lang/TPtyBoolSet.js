//============================================================
// 布尔集类型的属性注册类
//
// @tool
// @author maochunyang
// @version 1.0.1
//============================================================
function TPtyBoolSet(name, config, search, value){
   var o = this;
   o.annotation = EAnnotation.Property;
   // Attribute
   o.name       = name;
   o.config     = RWord.ulName(config).toString();
   o.search     = search;
   o.value      = value;
   // Method
   o.load       = TPtyBoolSet_load;
   o.save       = TPtyBoolSet_save;
   return o;
}

//============================================================
// 加载属性值
//
// @method
// @param o:object:Object 控件对象
// @param c:config:TPtyBool 加载的属性名
//============================================================
function TPtyBoolSet_load(o, c){
   o[this.name] = RSet.containsStr(c.get(this.config), this.search);
}

//============================================================
// 从控件对象把属性值赋给属性配置对象
//
// @method
// @param o:object:Object 控件对象
// @param c:config:TPtyBool 赋给的属性名
//============================================================
function TPtyBoolSet_save(o, c){
   var n = this.name;
   var v = o[n];
   var s = c.nvl(this.config);
   var e = RSet.containsStr(s, this.search);
   if(v && !e){
      c.set(n, s + this.search);
   }else if(!v && e){
      c.set(n, RString.remove(s, this.search));
   }
}
