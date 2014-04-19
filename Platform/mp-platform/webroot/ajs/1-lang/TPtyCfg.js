//============================================================
// 配置类型的属性注册类
//
// @tool
// @author maochunyang
// @version 1.0.1
//============================================================
function TPtyCfg(name){
   var o = this;
   o.annotation = EAnnotation.Property;
   // Attribute
   o.force      = true;
   o.name       = name;
   o.config     = RWord.ulName(name).toString();
   // Method
   o.load       = TPtyCfg_load;
   o.save       = RMethod.empty;
   return o;
}

//============================================================
// 从控件对象把属性值赋给属性配置对象
//
// @method
// @param o:object:Object 控件对象
// @param c:config:TPtyBool 赋给的属性名
//============================================================
function TPtyCfg_load(o, c){
   o[this.name] = c;
}
