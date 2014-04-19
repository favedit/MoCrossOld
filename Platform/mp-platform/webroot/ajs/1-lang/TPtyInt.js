//============================================================
// 整型的注册属性工具类
//
// @tool
// @author maochunyang
// @version 1.0.1
//============================================================
function TPtyInt(name, value){
   var o = this;
   o.annotation = EAnnotation.Property;
   // Attribute
   o.name       = name;
   o.config     = RWord.ulName(name).toString();
   o.value      = RInt.nvl(value);
   // Method
   o.load       = TPtyInt_load;
   o.save       = TPtyInt_save;
   return o;
}

//============================================================
// 加载属性值
//
// @method
// @param o:object:Object 控件对象
// @param c:config:TPtyBool 加载的属性名
//============================================================
function TPtyInt_load(o, c){
   o[this.name] = c.nvl(this.config);
}

//============================================================
// 从控件对象把属性值赋给对象
//
// @method
// @param o:object:Object 控件对象
// @param c:config:TPtyBool 赋给的属性名
//============================================================
function TPtyInt_save(o, c){
   c.set(this.config, o[this.name]);
}
