//============================================================
// 用于注册字符串属性的工具类
//
// @tool
// @author maochunyang
// @version 1.0.1
//============================================================
function TPtyStr(name, value, config){
   var o = this;
   o.annotation = EAnnotation.Property;
   // Attribute
   o.name       = name;
   o.config     = RString.nvl(config, RWord.ulName(name).toString());
   o.value      = value;
   // Method
   o.load       = TPtyStr_load;
   o.save       = TPtyStr_save;
   return o;
}

//============================================================
// 加载属性值
//
// @method
// @param o:object:Object 控件对象
// @param c:config:TPtyBool 加载的属性名
//============================================================
function TPtyStr_load(o, c){
   o[this.name] = c.nvl(this.config);
}

//============================================================
// 从控件对象把属性值赋给对象
//
// @method
// @param o:object:Object 控件对象
// @param c:config:TPtyBool 赋给的属性名
//============================================================
function TPtyStr_save(o, c){
   c.set(this.config, o[this.name]);
}
