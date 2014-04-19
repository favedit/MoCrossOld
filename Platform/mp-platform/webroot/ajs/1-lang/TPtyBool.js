//============================================================
// 布尔类型的属性注册类
//
// @tool
// @author maochunyang
// @version 1.0.1
//============================================================
function TPtyBool(name, value){
   var o = this;
   o.annotation = EAnnotation.Property;
   // Attribute
   o.name       = name;
   o.config     = RWord.ulName(name).toString();
   o.value      = value;
   // Method
   o.load       = TPtyBool_load;
   o.save       = TPtyBool_save;
   return o;
}

//============================================================
// 加载属性值
//
// @method
// @param o:object:Object 控件对象
// @param c:config:TPtyBool 加载的属性名
//============================================================
function TPtyBool_load(o, c){
   o[this.name] = RBool.isTrue(c.get(this.config));
}

//============================================================
// 从控件对象把属性值赋给对象
//
// @method
// @param o:object:Object 控件对象
// @param c:config:TPtyBool 赋给的属性名
//============================================================
function TPtyBool_save(o, c){
   c.set(this.config, RBool.toString(o[this.name]));
}
