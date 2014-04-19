function EAnnotationFace(){
	var o = this;
	o.Property = 'property';
	o.Event    = 'event';
	o.Style    = 'style';
	return o;
}
var EAnnotation = new EAnnotationFace();
function EBoolFace(){
	var o = this;
	o.True   = 'Y';
	o.False  = 'N';
	return o;
}
var EBool = new EBoolFace();
function ECaseFace(){
	var o = this;
	o.Upper = 'U';
	o.Lower = 'L';
	o.Word  = 'W';
	return o;
}
var ECase = new ECaseFace();
function EKeyFace(){
	var o = this;
	o.None      = 0;
	o.Esc       = 27;
	o.Tab       = 9;
	o.Enter     = 13;
	o.Shift     = 16;
	o.Alt       = 18;
	o.Ctrl      = 17;
	o.BackSpace = 8;
	o.Left      = 37;
	o.Up        = 38;
	o.Right     = 39;
	o.Down      = 40;
	o.Insert    = 45;
	o.Delete    = 46;
	o.Home      = 36;
	o.End       = 35;
	o.PageUp    = 33;
	o.PageDown  = 34;
	o.F1        = 112;
	o.F2        = 113;
	o.F3        = 114;
	o.F4        = 115;
	o.F5        = 116;
	o.F6        = 117;
	o.F7        = 118;
	o.F8        = 119;
	o.F9        = 120;
	o.F10       = 121;
	o.F11       = 122;
	o.F12       = 123;
	o.A         = 65;
	o.B         = 66;
	o.L         = 76;
	o.Q         = 81;
	o.S         = 83;
	o.F         = 70;
	o.I         = 73;
	o.D         = 68;
	o.ControlKeys = [
		o.Tab, o.Enter, o.BackSpace, o.Left, o.Up, o.Right, o.Down,
		o.Insert, o.Delete, o.Home, o.End, o.PageUp, o.PageDown,
		o.F1, o.F2, o.F3, o.F4, o.F5, o.F6, o.F7, o.F8, o.F9, o.F10, o.F11, o.F12];
	return o;
}
var EKey = new EKeyFace();
function ELogFace(){
	var o = this;
	o.Debug = 'D';
	o.Info  = 'I';
	o.Warn  = 'W';
	o.Error = 'E';
	o.Fatal = 'F';
	return o;
}
var ELog = new ELogFace();
function ENodeTypeFace(){
	var o=this;
	o.Node = 1;
	o.Text = 3;
	return o;
}
var ENodeType = new ENodeTypeFace();
function ENumberTypeFace(){
	var o = this;
	o.Integer              = 'I';
	o.PositiveInteger      = 'PI';
	o.NegativeInteger      = 'NI';
	o.Float                = 'F';
	o.PositiveFloat        = 'PF';
	o.NegativeFloat        = 'NF';
	return o;
}
var ENumberType = new ENumberTypeFace();
function ERegExpFace(){
	var o = this;
   o.Integer               = /^[1-9]{1}[0-9]$/;
   o.PositiveInteger       = /^[1-9]{1}[0-9]{1,}$/;
   o.NegativeInteger       = /^[-]{1}[1-9]{1}[0-9]{1,}$/;
   o.Float                 = /[1-9]{1}[0-9]/;
   o.PositiveFloat         = /[1-9]{1}[0-9]/;
   o.NegativeFloat         = /^[1-9]{1}[0-9]/;
   o.Url                   = /[1-9]{1}[0-9]/;
   o.Email                 = /^\w{1,}[@]{1}[a-zA-Z]{1,}[.]{1}[a-zA-Z]{1,}$/;
   return o;
}
var ERegExp = new ERegExpFace();
function ERowStatusFace(){
	var o = this;
	o.Normal = 'N';
	o.Insert = 'I';
	o.Update = 'U';
	o.Delete  = 'D';
	return o;
}
var ERowStatus = new ERowStatusFace();
function EXmlCnnParseFace(){
	var o=this;
	o.Finish  = 4;
	return o;
}
var EXmlCnnParse = new EXmlCnnParseFace();
function EXmlCnnStatusFace(){
	var o=this;
	o.Begin   = 0; // Uninitialize
	o.Build   = 1; // Initialize
	o.Send    = 2; // Send
	o.Receive = 3; // Receive
	o.Finish  = 4; // Finish
	return o;
}
var EXmlCnnStatus = new EXmlCnnStatusFace();
function EXmlCnnTypeFace(){
	var o=this;
	o.IE = 0; // Microsoft IE
	o.NC = 1; // Nescape   NC
	return o;
}
var EXmlCnnType = new EXmlCnnTypeFace();
function FObject(o){
	if(!o){o=this;}
	o.isClass   = FObject_isClass;
	o.construct = RMethod.empty;
	o.innerDump = FObject_innerDump;
	o.dump      = FObject_dump;
	return o;
}
function FObject_isClass(c){
	return RClass.isClass(this, c);
}
function FObject_innerDump(dump, level){
	RClass.dump(dump, this);
}
function FObject_dump(dump){
	dump = RStr.nvlStr(dump);
	this.innerDump(dump, 0);
	return dump;
}
function GLoaderFace(){
	var o = this;
	o.id         = null;
	o.count      = 0;
	o.flags      = new Array();
	o.waits      = new Array();
	o.hWindow    = null;
	o.doInterval = GLoader_doInterval;
	o.loaded     = GLoader_loaded;
	o.wait       = GLoader_wait;
	o.construct  = GLoader_construct;
	o.release    = GLoader_release;
	return o;
}
if(!top.GLoader){top.GLoader = new GLoaderFace();}
var GLoader = top.GLoader;
function GLoader_doInterval(){
	var o = top.GLoader;
	var w = o.waits;
	for(var n=0; n<ar.length; n++){
		var c = w[n];
		if(c){
			var v = o.flags[f.name];
			if(v){
				w[n] = null;
				o.count--;
				if(o.count <= 0){
					o.release();
				}
				c(v);
			}
		}
	}
}
function GLoader_loaded(n, v){
	this.flags[n.toLowerCase()] = v;
}
function GLoader_wait(n, c){
	f.name = n.toLowerCase();
	var w = this.waits;
	for(var n=0; n<w.length; n++){
		if(null == w[n]){
			w[n] = c;
			this.count++;
			this.construct();
			return;
		}
	}
}
function GLoader_construct(){
	var o = this;
	if(!o.id){
		o.hWindow = window;
		o.id = window.setInterval(o.doInterval, 100);
	}
}
function GLoader_release(){
	var o = this;
	if(o.hWindow && o.id){
		o.hWindow.clearInterval(o.id);
		o.hWindow = null;
		o.id = null;
	}
}
function RArrayFace(o){
	if(!o){o=this;}
	o.equals     = RArray_equals;
	o.count      = RArray_count;
	o.nameMaxlen = RArray_nameMaxlen;
	o.contains   = RArray_contains;
	o.find       = RArray_find;
	o.search     = RArray_search;
	o.sort       = RArray_sort;
	o.sortAsc    = RArray_sortAsc;
	o.sortDesc   = RArray_sortDesc;
	o.copy       = RArray_copy;
	o.move       = RArray_move;
	o.remove     = RArray_remove;
	return o;
}
var RArray = new RArrayFace();
function RArray_equals(s, t){
	if(s && t && s.length == t.length){
		for(var n=0; n<s.length; n++){
			if(s[n] != t[n]){
				return false;
			}
		}
		return true;
	}
	return false;
}
function RArray_count(obj){
	var count = 0;
	for(var name in obj){
		count++
	}
	return count;
}
function RArray_nameMaxlen(obj){
	var len = 0;
	for(var name in obj){
		len = Math.max(name.length, len);
	}
	return len;
}
function RArray_contains(arr, obj){
	for(var n=0; n<arr.length; n++){
		if(arr[n] == obj){
			return true;
		}
	}
	return false;
}
function RArray_find(arr, obj){
	for(var n=0; n<arr.length; n++){
		if(arr[n] == obj){
			return n;
		}
	}
	return -1;
}
function RArray_search(arr, obj){
	for(var name in arr){
		if(arr[name] == obj){
			return name;
		}
	}
	return -1;
}
function RArray_sort(arr, asc){
	if(asc){
		return arr.sort(this.sortAsc);
	}
	return arr.sort(this.sortDesc);
}
function RArray_sortAsc(source, target){
	if(source > target){
		return -1;
	}else if(source == target){
		return 0;
	}
	return 1;
}
function RArray_sortDesc(source, target){
	if(source < target){
		return -1;
	}else if(source == target){
		return 0;
	}
	return 1;
}
function RArray_copy(s, t){
	for(var n in s){
		t[n] = s[n];
	}
}
function RArray_move(arr, offset, count, target){
	if(offset > target){
		for(var n=0; n<count; n++){
			arr[target-n] = arr[offset+n];
		}
	}else if(offset < target){
		for(var n=0; n<count; n++){
			arr[target+count-n-1] = arr[offset+count-n-1];
		}
	}
}
function RArray_remove(arr, index){
	return arr.slice(0, index).concat(arr.slice(index+1));
}
function RBoolFace(){
	var o = this;
	o.isTrue   = RBool_isTrue;
	o.toString = RBool_toString;
	return o;
}
var RBool = new RBoolFace();
function RBool_isTrue(v){
   return (v == EBool.True);
}
function RBool_toString(v){
   return v ? EBool.True : EBool.False;
}
function RBuilderFace(){
	var o = this;
	o.hWindow     = window;
	o.hDocument   = window.document;
	o.append      = RBuilder_append;
	o.appendImage = RBuilder_appendImage;
	o.appendIcon  = RBuilder_appendIcon;
	o.appendEmpty = RBuilder_appendEmpty;
	o.appendText  = RBuilder_appendText;
	o.appendTable = RBuilder_appendTable;
	o.appendRow   = RBuilder_appendRow;
	o.appendDiv   = RBuilder_appendDiv;
	o.appendCell  = RBuilder_appendCell;
	o.appendCheck = RBuilder_appendCheck;
	o.appendEdit  = RBuilder_appendEdit;
	o.create      = RBuilder_create;
	o.newImage    = RBuilder_newImage;
	o.newIcon     = RBuilder_newIcon;
	o.newSpan     = RBuilder_newSpan;
	o.newDiv      = RBuilder_newDiv;
	o.newText     = RBuilder_newText;
	o.newTable    = RBuilder_newTable;
	o.newCheck    = RBuilder_newCheck;
	o.newEdit     = RBuilder_newEdit;
	return o;
}
var RBuilder = new RBuilderFace();
function RBuilder_append(parent, name, css){
	var h = this.create(parent, name, css);
	if(!parent){
		parent = this.hDocument.body;
	}
	parent.appendChild(h);
	return h;
}
function RBuilder_appendImage(parent, path, css, width, height){
	var h = this.newImage(parent, path, css, width, height);
	parent.appendChild(h);
	return h;
}
function RBuilder_appendIcon(parent, path, css, width, height){
	var h = this.newIcon(parent, path, css, width, height);
	parent.appendChild(h);
	return h;
}
function RBuilder_appendEmpty(p, w, h){
	var o = this.newIcon(p, 'n');
	p.appendChild(o);
	o.style.width = RStr.nvl(w, 1);
	o.style.height = RStr.nvl(h, 1);
	return o;
}
function RBuilder_appendText(parent, html, css){
	var h = this.newText(parent, html, css);
	if(!parent){
		parent = this.hDocument.body;
	}
	parent.appendChild(h);
	return h;
}
function RBuilder_appendDiv(parent, css){
	var h = this.newDiv(parent, css);
	if(!parent){
		parent = this.hDocument.body;
	}
	parent.appendChild(h);
	return h;
}
function RBuilder_appendTable(parent, css, border, spaceing, padding){
	var h = this.newTable(parent, css, border, spaceing, padding);
	if(!parent){
		parent = this.hDocument.body;
	}
	parent.appendChild(h);
	return h;
}
function RBuilder_appendRow(table, css, index, width, height){
	var h = index ? table.insertRow(index) : table.insertRow();
	if(css){
		h.className = css;
	}
	if(width){
		h.width = width;
	}
	if(height){
		h.height = height;
	}
	return h;
}
function RBuilder_appendCell(row, css, width, height){
	var h = row.insertCell();
	if(css){
		h.className = css;
	}
	if(width){
		h.width = width;
	}
	if(height){
		h.height = height;
	}
	return h;
}
function RBuilder_appendCheck(parent, css){
	var h = this.newCheck(parent, css);
	parent.appendChild(h);
	return h;
}
function RBuilder_appendEdit(parent, css){
	var h = this.newEdit(parent, css);
	parent.appendChild(h);
	return h;
}
function RBuilder_create(p, n, c){
	var h = p ? p.ownerDocument.createElement(n) : this.hDocument.createElement(n);
	if(c){
		h.className = c;
	}
	return h;
}
function RBuilder_newImage(p, s, c, w, h){
	var o = this.create(p, 'IMG', c);
	if(s){
		o.src = RRes.imagePath(s);
	}
	if(w){
		o.style.width = w;
	}
	if(h){
		o.style.height = h;
	}
	return o;
}
function RBuilder_newIcon(p, s, c, w, h){
	var o = this.create(p, 'IMG', RStr.nvl(c, 'Tag_Icon'));
	o.align = 'absmiddle';
	o.src = RRes.iconPath(s);
   if(w){
      o.style.width = w;
   }
   if(h){
      o.style.height = h;
   }
	return o;
}
function RBuilder_newSpan(parent, css){
	return this.create(parent, 'SPAN', css);
}
function RBuilder_newDiv(parent, css){
	return this.create(parent, 'DIV', css);
}
function RBuilder_newTable(parent, css, border, spaceing, padding){
	var h = this.create(parent, 'TABLE', css);
	h.border = RInt.nvl(border);
	h.frame = 'box';
	h.cellSpacing = RInt.nvl(spaceing);
	h.cellPadding = RInt.nvl(padding);
	return h;
}
function RBuilder_newText(parent, html, css){
	var h = this.create(parent, 'SPAN', css);
	h.innerHTML = html;
	return h;
}
function RBuilder_newCheck(parent, css){
	return this.create(parent, "<INPUT type='checkbox'></INPUT>", css);
}
function RBuilder_newEdit(parent, css){
	return this.create(parent, "<INPUT type='text'></INPUT>", css);
}
function RClassFace(){
	var o = this;
	o.classes     = new Object();
	o.name        = RClass_name;
	o.typeOf      = RClass_typeOf;
	o.safeTypeOf  = RClass_safeTypeOf;
	o.inherits    = RClass_inherits;
	o.isClass     = RClass_isClass;
	o.checkClass  = RClass_checkClass;
	o.createBase  = RClass_createBase;
	o.createClass = RClass_createClass;
	o.create      = RClass_create;
	o.find        = RClass_find;
	o.build       = RClass_build;
	o.innerCopy   = RClass_innerCopy;
	o.register    = RClass_register;
	o.dump        = RClass_dump;
	return o;
}
var RClass = new RClassFace();
function RClass_name(o){
	if(o && o.constructor){
		var c = o.constructor;
		if(Function == c){
			return RStr.substr(o.toString(), 'function ', '(');
		}
		return RStr.substr(c.toString(), 'function ', '(');
	}
	return null;
}
function RClass_typeOf(o){
	if(o && o.constructor){
		return RStr.substr(o.constructor.toString(), 'function ', '(');
	}
	return 'Null';
}
function RClass_safeTypeOf(obj, safe){
	if(obj != null){
		try{
			if(obj.constructor){
				return RStr.substr(obj.constructor.toString(), 'function ', '(');
			}
			if(obj.tagName){
				return 'Html';
			}
			for(var name in obj){
				return 'Object';
			}
		}catch(e){}
		return 'Unknown';
	}
	return 'Null';
}
function RClass_inherits(p, o){
	o = RObj.nvl(o, p);
	o.base = new Array();
	for(var n=2; n<arguments.length; n++){
		o.base[o.base.length] = RMethod.name(arguments[n]);
	}
	return o;
}
function RClass_isClass(o, cls){
	if(o && cls){
		var name = this.name(cls);
		if(o.base){
			return (null != o.base[name]);
		}else{
			return (this.name(o) == name);
		}
	}
	return false;
}
function RClass_checkClass(o, c){
	if(!this.isClass(o, c)){
		throw new Error('Invalid class ' + RClass.name(o) + '<>' + RClass.name(c));
	}
}
function RClass_createBase(name){
	if(name != null){
		return eval('function ' + name + '(){return this;} new ' + name + '();');
	}
	return null;
}
function RClass_createClass(n){
	var o = this;
	var c = o.classes[n] = new TClass()
	c.name = n;
	c.base = this.createBase(n)
	c.clazz = new c.base.constructor();
	eval(n + '(c.clazz)');
	return c;
}
function RClass_create(cons){
	var cls = this.find(cons);
	if(cls == null){
		RMsg.fatal(this, null, 'Cant find class [{0}]', cons);
	}
	var o = cls.newInstance();
	if(o.construct){
		o.construct();
	}
	return o;
}
function RClass_find(c, nc){
	var o = this;
	if(nc){
		return o.classes[RClass.name(c)];
	}
	var r = null;
	if(c){
		if(c.constructor == Function){
			c = RMethod.name(c);
		}else if(c.constructor != String){
			RMsg.fatal(o, null, 'Param invlid (class={0})', c);
		}
		r = o.classes[c];
		if(!r){
			r = o.createClass(c);
			o.build(r);
		}
	}
	return r;
}
function RClass_build(c){
	var sbs = c.clazz.base;
	if(sbs && sbs.constructor == Array){
		var finded = false;
		for(var n=0; n<sbs.length; n++){
			var name = sbs[n];
			if(RStr.startsWith(name, 'F')){
				if(finded){
					RMsg.fatal(this, null, 'Parent class is too many.{0}', name);
				}
				c.parent = RClass.find(name);
				finded = true;
			}
		}
	}
	var o = c.instance = new c.base.constructor();
	if(sbs && sbs.constructor == Array){
		for(var i=0; i<sbs.length; i++){
			var name = sbs[i];
			if(!RStr.startsWith(name, 'F')){
				var m = RClass.find(name);
				this.innerCopy(m.instance, o);
				c.assign(m);
			}
		}
	}
	if(c.parent){
		this.innerCopy(c.parent.instance, o);
		c.assign(c.parent);
	}
	if(!o.base){
		o.base = new Object();
	}
	o.base[c.name] = new c.base.constructor();
	var cf = c.clazz;
	for(var n in cf){
		if(n != 'base'){
			if(null==cf[n] && null==o[n]){
				o[n] = null;
			}else if(null!=cf[n]){
				if((null==o[n]) || (null!=o[n] && cf[n] != o[n])){
					o[n] = cf[n];
				}
			}
		}
	}
	if(sbs && sbs.constructor == Array){
		for(var i=0; i<sbs.length; i++){
			var name = sbs[i];
			var bcls = RClass.find(name);
			var base = o.base[name] = new bcls.base.constructor();
			var cf = bcls.instance;
			for(var n in cf){
				if(n != 'base'){
					if(cf[n]!=null && o[n]!=null && cf[n]!=o[n] && cf[n].constructor==Function && o[n].constructor==Function){
						base[n] = cf[n];
					}
				}
			}
		}
	}
	for(var name in c.instance){
		var v = c.instance[name];
		if(null != v){
			if(v.constructor == Function && v.virtual){
				c.abstract = true;
				break;
			}
		}
	}
}
function RClass_innerCopy(s, t){
	if(null!=s && null!=t){
		for(var n in s){
			var v = s[n];
			if(null != v){
				var c = v.constructor;
				if(c == Function){
					if(null == t[n]){
						t[n] = v;
					}else if(t[n].virtual){
						t[n] = v;
					}else if(RMethod.empty == t[n] && !v.virtual){
						t[n] = v;
					}else if(!v.virtual && RMethod.empty != v){
						t[n] = v;
					}
					continue;
				}else if(c != Boolean && c != Date && c != String && c != Number){
					if(null == t[n]){
						t[n] = new c();
					}
					this.innerCopy(v, t[n]);
					continue;
				}
			}
			t[n] = v;
		}
	}
}
function RClass_register(o, a, d){
	this.classes[RMethod.name(o.constructor)].register(a);
	return d;
}
function RClass_dump(o){
	var t = this.safeTypeOf(o);
	if('String' == t){
		return o;
	}else if('Html' == t){
		t += '<' + o.tagName + '>';
	}else if(o && o.name){
		t += '<' + o.name + '>';
	}
	return (null==o) ? '@null' : t + '@' + RObj.code(o);
}
function RContextFace(){
	var o = this;
	o.texts    = new Array();
	o.contexts = new Array();
	o.get  = RContext_get;
	o.set  = RContext_set;
	o.path = RContext_path;
	return o;
}
var RContext = new RContextFace();
function RContext_get(c, n){
	var cn = RClass.name(c);
	if((cn.indexOf('E') == 0 || cn.indexOf('R') == 0) && cn.indexOf('Face') == cn.length-4){
		cn = cn.substr(0, cn.length-4)
	}
	var id = cn + '_' + RStr.nvl(n).replace('.','_');
	var rs = this[id];
	if(!rs){
		RMsg.fatal(this, null, 'Can not find context (id={0})', id);
	}
	return rs;
}
function RContext_set(c, n, v){
	var o = this;
	n = RClass.name(c) + ':' + RStr.nvl(n);
	var ag = arguments;
	var ln = ag.length;
	if(ln == 3){
		o.texts[n] = v;
	}else if(ln %2 == 0){
		for(var i=2; i<ln; i+=2){
			o.texts[n + '.' + ag[i]] = ag[i+1];
		}
	}else{
		RMsg.fatal(o, null, 'Params length error (length={0})', ln);
	}
}
function RContext_path(c, p){
	var o = this;
	p = RClass.name(c) + ':' + RStr.nvl(p) + '.';
	var r = o.contexts[p];
	if(!r){
		r = new TContext(p);
		o.contexts[p] = r;
	}
	return r;
}
function RCultureFace(){
	var o = this;
	return o;
}
var RCulture = new RCultureFace();
function RDatasetFace(){
	var o = this;
	o.DATASET = 'Dataset';
	o.ROW = 'Row';
	o.ROW_STATUS = '_os';
	o.make = RDataset_make;
	return o;
}
var RDataset = new RDatasetFace();
function RDataset_make(d){
	var r = d;
	if(RXml.isNode(d)){
		r = new TDataset();
		r.loadNode(d);
	}
	return r;
}
function RDateFace(){
	var o = this;
	o.MinYear       = 1800;
	o.MaxYear       = 2400;
	o.Pattern       = 'n-: /';
	o.Chars         = '0123456789-:/ ';
	o.DisplayFormat = 'yyyy-mm-dd hh24:mi:ss';
	o.DataFormat    = 'yyyymmddhh24miss';
	o.MonthDays     = new Array(0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31);
	o.nvl           = RDate_nvl;
	o.format        = RDate_format;
	o.formatDate    = RDate_formatDate;
	o.monthDays     = RDate_monthDays;
	o.splitFormat   = RDate_splitFormat;
	o.makeDate      = RDate_makeDate;
	o.checkItems    = RDate_checkItems;
	o.check         = RDate_check;
	o.parse         = RDate_parse;
	o.splitDate     = RDate_splitDate;
	o.splitTime     = RDate_splitTime;
	o.autoParse     = RDate_autoParse;
	return o;
}
var RDate = new RDateFace();
function RDate_nvl(o){
	return o ? o : new TDate();
}
function RDate_format(fmt){
	return this.formatDate(new TDate(), fmt);
}
function RDate_formatDate(date, fmt){
	if(!date){return '';}
	fmt = fmt ? fmt.toLowerCase() : this.DataFormat;
	fmt = fmt.replace(/yyyy/g, RInt.format(date.year, 4));
	fmt = fmt.replace(/yy/g, RInt.format(date.year%100, 2));
	fmt = fmt.replace(/mm/g, RInt.format(date.month, 2));
	fmt = fmt.replace(/dd/g, RInt.format(date.day, 2));
	fmt = fmt.replace(/hh24/g, RInt.format(date.hour, 2));
	fmt = fmt.replace(/mi/g, RInt.format(date.minute, 2));
	fmt = fmt.replace(/ss/g, RInt.format(date.second, 2));
	fmt = fmt.replace(/ms/g, RInt.format(date.ms, 3));
	return fmt;
}
function RDate_monthDays(year, month){
	if(!year || !month){return 0;}
	year = parseInt(year);
	month = parseInt(month);
	this.MonthDays[2] = (((year%4 == 0) || (year%400 == 0)) && (year%100 != 0)) ? 29 : 28 ;
	return this.MonthDays[month];
}
function RDate_splitFormat(v, f){
	if(!v){
		return false;
	}
	f = f.toLowerCase();
	var a = new Array();
	while(f.length > 0){
		if(f.indexOf('yyyy') == 0){
			a['year'] = v.substring(0, 4);
			f = f.substring(4);
			v = v.substring(4);
		}else if(f.indexOf('mm') == 0){
			a['month'] = v.substring(0, 2);
			f = f.substring(2);
			v = v.substring(2);
		}else if(f.indexOf('dd') == 0){
			a['day'] = v.substring(0, 2);
			f = f.substring(2);
			v = v.substring(2);
		}else if(f.indexOf('hh24') == 0){
			a['hour'] = v.substring(0, 2);
			f = f.substring(4);
			v = v.substring(2);
		}else if(f.indexOf('mi') == 0){
			a['minute'] = v.substring(0, 2);
			f = f.substring(2);
			v = v.substring(2);
		}else if(f.indexOf('ss') == 0){
			a['second'] = v.substring(0, 2);
			f = f.substring(2);
			v = v.substring(2);
		}else if(f.indexOf('ms') == 0){
			a['ms'] = v.substring(0, 2);
			f = f.substring(2);
			v = v.substring(3);
		}else{
			f = f.substring(1);
			v = v.substring(1);
		}
	}
	return a;
}
function RDate_checkItems(items){
	if(!items){
		return false;
	}
	var year = RInt.parse(items["year"]);
	if(year < this.MinYear || year > this.MaxYear){
		return false;
	}
	var month = RInt.parse(items["month"]);
	if(month < 1 || month > 12){
		return false;
	}
	var day = RInt.parse(items["day"]);
	if(day < 1 || day > this.monthDays(year, month)){
		return false;
	}
	var hour = RInt.parse(items["hour"]);
	if(hour < 0 || hour > 23){
		return false;
	}
	var second = RInt.parse(items["second"]);
	if(second < 0 || second > 59){
		return false;
	}
	var ms = RInt.parse(items["ms"]);
	if(ms < 0 || ms > 99){
		return false;
	}
	return true;
}
function RDate_check(value, format){
	return this.checkItems(this.splitFormat(value, format));
}
function RDate_makeDate(date, da){
	var d = new Date(RInt.parse(da.year), RInt.parse(da.month)-1, RInt.parse(da.day), RInt.parse(da.hour), RInt.parse(da.minute), RInt.parse(da.second), RInt.parse(da.ms));
	if(date){
		date.setDate(d);
		return date;
	}
	return new TDate(d);
}
function RDate_parse(date, value, format){
	if(!format){
		format = this.DataFormat;
	}
	var items = this.splitFormat(value, format);
	if(this.checkItems(items)){
		return this.makeDate(date, items);
	}
	return null;
}
function RDate_splitDate(da, value){
	if(!value){ return; }
	var arDate = null;
	if(value.indexOf('-') != -1 || value.indexOf('/') != -1){
		if(value.indexOf('-') != -1){
			arDate = value.split('-');
		}else if(value.indexOf('/') != -1){
			arDate = value.split('/');
		}
		if(arDate.length >= 1){
			da.year = RInt.parse(arDate[0]);
		}
		if(arDate.length >= 2){
			da.month = RInt.parse(arDate[1]);
		}
		if(arDate.length >= 3){
			da.day = RInt.parse(arDate[2]);
		}
	}else if(value.indexOf(':') != -1){
		this.splitTime(da, value);
	}else if(value.length == 14){
		da.year = RInt.parse(value.substr(0, 4));
		da.month = RInt.parse(value.substr(4, 2));
		da.day = RInt.parse(value.substr(6, 2));
		da.hour = RInt.parse(value.substr(8, 2));
		da.minute = RInt.parse(value.substr(10, 2));
		da.second = RInt.parse(value.substr(12, 2));
	}else if(value.length == 8){
		da.year = RInt.parse(value.substr(0, 4));
		da.month = RInt.parse(value.substr(4, 2));
		da.day = RInt.parse(value.substr(6, 2));
	}else if(value.length == 6){
		da.year = RInt.parse(value.substr(0, 4));
		da.month = RInt.parse(value.substr(4, 2));
	}
}
function RDate_splitTime(da, value){
	if(!value){ return; }
	if(value.indexOf(':') != -1){
		var ar = value.split(':');
		if(ar.length >= 1){
			da.hour = RInt.parse(ar[0]);
		}
		if(ar.length >= 2){
			da.minute = RInt.parse(ar[1]);
		}
		if(ar.length >= 3){
			da.second = RInt.parse(ar[2]);
		}
	}else if(value.length == 6){
		da.hour = RInt.parse(value.substr(0, 2));
		da.minute = RInt.parse(value.substr(2, 2));
		da.second = RInt.parse(value.substr(4, 2));
	}else if(value.length == 4){
		da.hour = RInt.parse(value.substr(0, 2));
		da.minute = RInt.parse(value.substr(2, 2));
	}else if(value.length == 2){
		da.hour = RInt.parse(value.substr(0, 2));
	}
}
function RDate_autoParse(d, v){
	if(!v){
		return null;
	}
	var o = this;
	d = o.nvl(d);
	var items = new Array();
	items['year'] = 0;
	items['month'] = 1;
	items['day'] = 1;
	items['hour'] = 0;
	items['minute'] = 0;
	items['second'] = 0;
	v = RStr.trim(v);
	if(v.indexOf(' ') == -1){
		o.splitDate(items, v);
	}else{
		var ar = v.split(' ');
		if(ar.length == 2){
			o.splitDate(items, ar[0]);
			o.splitTime(items, ar[1]);
		}
	}
	return o.checkItems(items) ? o.makeDate(d, items) : null ;
}
function RDumpFace(){
	var o = this;
	o.LINE_SINGLE  = '------------------------------';
	o.LINE_DOUBLE  = '==============================';
	o.LINE_DOT     = '..............................';
	o.LINE_STAR    = '******************************';
	o.onclick     = RDump_onclick;
	o.nameInfo    = RDump_nameInfo;
	o.typeInfo    = RDump_typeInfo;
	o.dumpInner   = RDump_dumpInner;
	o.dump        = RDump_dump;
	o.appendLevel = RDump_appendLevel;
	o.stack       = RDump_stack;
	return o;
}
var RDump = new RDumpFace();
function RDump_onclick(){
	var di = this.link;
	if(this.link){
		if(di.loaded){
			di.show(!di.display);
		}else{
			RDump.dumpInner(this.link);
			di.loaded   = true;
			di.show(true);
		}
	}
}
function RDump_nameInfo(name){
	var type = RClass.typeOf(name);
	switch(type){
		case 'Unknown':
			return '@unknown';
		case 'Function':
			return RMethod.name(name) + '@Function';
		case 'Array':
			return '@<Array>';
	}
	return name;
}
function RDump_typeInfo(obj, type){
	if(obj == null){
		return 'null';
	}
	switch(type){
		case 'Unknown':
			return 'unknown';
		case 'Undefined':
			return 'undefined';
		case 'Boolean':
			return obj.toString();
		case 'Number':
			return obj.toString();
		case 'String':
			return obj.length + ':\'' + RStr.toLine(obj) + '\'';
		case 'Function':
			if(obj.virtual){
				return 'virtual';
			}
			return RMethod.name(obj, true);
		case 'Array':
			return '@<Array@' + RObj.code(obj) + '>';
		default:
			if(obj.constructor == TClass){
				return '@<' + obj.name + '@' + RObj.code(obj) + '>';
			}
			try{
				for(var name in obj){
					return '@<Object@' + RObj.code(obj) + '>';
				}
			}catch(e){}
			return '<Object@' + RObj.code(obj) + '>';
	}
}
function RDump_dumpInner(di){
	var hTable  = di.hTable;
	var hParent = di.hObj;
	var hInsRow = di.hRow;
	var level   = di.level;
	var obj     = di.link;
	var type    = RClass.typeOf(obj, true);
	var names = new Array();
	for(var name in obj){
		names[names.length] = name;
	}
	if(type != 'Array'){
		names = RArray.sort(names, true);
	}
	var items = new Array();
	for(var n=0; n<names.length; n++){
		var name = names[n];
		var value = obj[name];
		var type = RClass.safeTypeOf(value, true);
		var info = this.typeInfo(value, type);
		var rdi = null;
		var index = hInsRow ? hInsRow.rowIndex+1 : 1;
		var hRow = hTable.insertRow(index);
		if(RStr.startsWith(info, '@')){
			hRow.style.cursor = 'hand';
			hRow.onclick = this.onclick;
			hRow.bgColor = '#FFF0E0';
			rdi = hRow.link = di.create();
			rdi.link = value;
			rdi.level = level;
			rdi.caption = name;
			rdi.hTable = hTable;
			rdi.level = level + 1;
			rdi.hRow = hRow;
		}else{
			di.push(hRow);
		}
		if(type == 'Html'){
			info = '@<' + value.tagName + '>';
		}
		if(type == 'Function' && info == 'virtual'){
			hRow.bgColor = '#E0F0FF';
		}
		var hCell = hRow.insertCell();
		var icon = RStr.startsWith(info, '@') ? '+' : ' ';
		hCell.innerText = RStr.repeat('   ', level) + icon + ' ' + name;
		hCell.style.borderBottom = '1px solid';
		hCell.width = '200px'
		if(rdi){
			rdi.hText = hCell;
		}
		var hCell = hRow.insertCell();
		hCell.innerText = type;
		hCell.style.borderBottom = '1px solid';
		hCell.width = '100px'
		var hCell = hRow.insertCell();
		if(RStr.startsWith(info, '@')){
			info = info.substr(1);
		}
		hCell.innerHTML = RHtml.toHtml(info);
		hCell.style.borderBottom = '1px solid';
	}
}
function RDump_dump(obj, ho){
	if(!ho){
		ho = RBuilder.append(null, 'DIV')
	}
	var dump = new TStr();
	var type = RClass.safeTypeOf(obj);
	dump.append('<', type);
	if(obj && obj.tagName){
		dump.append(' - ', obj.tagName);
	}
	dump.appendLine('@' + RObj.code(obj) + '>');
	this.hPanel = RBuilder.append(ho, 'DIV');
	this.hPanel.style.border = '1px solid #BBBBBB';
	var hTable = RBuilder.appendTable(this.hPanel, 'DIV', null, 0, 1, 0);
	hTable.width = '100%'
	var hRow = hTable.insertRow();
	var hCell = hRow.insertCell();
	hCell.innerText = dump;
	hCell.colSpan = 3;
	hCell.style.padding = 2;
	hCell.style.borderBottom = '2px solid gray';
	hCell.style.backgroundColor = '#E0E0EB';
	var di = new TDumpItem();
	di.hTable = hTable;
	di.hObj = ho;
	di.link = obj;
	di.level = 0;
	this.dumpInner(di);
}
function RDump_appendLevel(dump, level){
	for(var n=0; n<level; n++){
		dump.append('   ');
	}
}
function RDump_stack(){
	var o = RDump_stack.caller;
	var s = new TStr();
	while(o){
		s.append(RMethod.name(o));
		o = o.caller;
		if(o){
			s.appendLine();
		}
	}
	RLog.debug(this, s);
}
function REnumFace(){
	var o = this;
	o.encode    = REnum_encode;
	o.decode    = REnum_decode;
   o.tryEncode = REnum_tryEncode;
   o.tryDecode = REnum_tryDecode;
	return o;
}
var REnum = new REnumFace();
function REnum_encode(e, v){
   var v = this.tryEncode(e, v);
	if(null == v){
	   RMsg.fatal(this, 'encode', 'Invalid value (enum={1}, value={2})', e, v);
	}
	return v;
}
function REnum_decode(e, v){
   var v = this.tryDecode(e, v);
   if(null == v){
      RMsg.fatal(this, 'decode', 'Invalid value (enum={1}, value={2})', e, v);
   }
   return v;
}
function REnum_tryEncode(e, v, d){
   for(var n in e){
      if(n.toLowerCase() == v.toLowerCase()){
         return e[n];
      }
   }
	return d;
}
function REnum_tryDecode(e, v, d){
   for(var n in e){
      if(e[n] == v){
         return n;
      }
   }
	return d;
}
function RHexFace(o){
	if(!o){o=this};
	o.NUMBER  = '0x123456789ABCDEF';
	o.PAD     = '0';
	o.isValid = RHex_isValid;
	o.parse   = RHex_parse;
	o.format  = RHex_format;
	return o;
}
var RHex = new RHexFace();
function RHex_isValid(v){
	return RStr.isPattern(v, this.NUMBER);
}
function RHex_parse(v){
	return v ? parseInt('0x'+v) : '0';
}
function RHex_format(v, l){
	v = RStr.nvl(v, '0').toString(16);
	return l ? RStr.lpad(v, l, this.PAD) : v;
}
function RHtmlFace(o){
	if(!o){o=this};
	o.point          = RHtml_point;
	o.toPoint        = RHtml_toPoint;
	o.rect           = RHtml_rect;
	o.toRect         = RHtml_toRect;
	o.clientRect     = RHtml_clientRect;
	o.offsetRect     = RHtml_offsetRect;
	o.changeWidth    = RHtml_changeWidth;
	o.clear          = RHtml_clear;
	o.setRect        = RHtml_setRect;
	o.setBounds      = RHtml_setBounds;
	o.setPixelRect   = RHtml_setPixelRect;
	o.setPixelBounds = RHtml_setPixelBounds;
	o.toText         = RHtml_toText;
	o.toHtml         = RHtml_toHtml;
	o.showNodes      = RHtml_showNodes;
	o.hideNodes      = RHtml_hideNodes;
	o.showChildren   = RHtml_showChildren;
	o.hideChildren   = RHtml_hideChildren;
	o.get            = RHtml_get;
	o.parent         = RHtml_parent;
	o.posParent      = RHtml_posParent;
	o.form           = RHtml_form;
	o.popup          = RHtml_popup;
	return o;
}
var RHtml = new RHtmlFace();
function RHtml_point(o, p){
	return this.toPoint(new TPoint(), o, p);
}
function RHtml_toPoint(r, o, p){
	if(r && o){
		p = RObj.nvl(p, window.document.body);
		r.x = -RInt.parse(o.currentStyle.borderLeftWidth);
		r.y = -RInt.parse(o.currentStyle.borderTopWidth);
		while(o && o != p){
			r.x += o.offsetLeft - o.scrollLeft;
			r.y += o.offsetTop - o.scrollTop;
			if('absolute' != o.currentStyle.position){
				r.x += o.clientLeft;
				r.y += o.clientTop;
			}
			o = o.offsetParent;
		}
	}
	return r;
}
function RHtml_rect(o, p){
	return this.toRect(new TRect(), o, p);
}
function RHtml_toRect(r, o, p){
	if(r && o){
		p = RObj.nvl(p, window.document.body);
		r.left = -RInt.parse(o.currentStyle.borderLeftWidth);
		r.top = -RInt.parse(o.currentStyle.borderTopWidth);
		var w = o.offsetWidth; w = o.offsetWidth-1;
		var h = o.offsetHeight; h = o.offsetHeight-1;
		while(o && o != p){
			r.left += o.offsetLeft - o.scrollLeft;
			r.top += o.offsetTop - o.scrollTop;
			if('absolute' != o.currentStyle.position){
				r.left += o.clientLeft;
				r.top += o.clientTop;
			}
			o = o.offsetParent;
		}
		r.right = r.left + w;
		r.bottom = r.top + h;
	}
	return r;
}
function RHtml_clientRect(o){
	if(o){
		var x = 0;
		var y = 0;
		var w = o.offsetWidth-1;
		var h = o.offsetHeight-1;
		while(o){
			x += o.offsetLeft;
			y += o.offsetTop;
			o = o.offsetParent;
		}
		return new TRect(x, y, x+w, y+h);
	}
	return null;
}
function RHtml_offsetRect(o){
	if(o){
		var x = 0;
		var y = 0;
		var w = o.offsetWidth-1;
		var h = o.offsetHeight-1;
		while(o){
			x += o.offsetLeft + o.clientLeft;
			y += o.offsetTop + o.clientTop;
			o = o.offsetParent;
		}
		return new TRect(x, y, x+w, y+h);
	}
	return null;
}
function RHtml_clear(hObj){
	if(hObj){
		var count = hObj.children.length;
		for(var n=count-1; n>=0; n--){
			hObj.removeChild(hObj.children.item(n));
		}
	}
}
function RHtml_setRect(o, r){
	if(o && o.style){
		var s = o.style;
		s.left = r.left;
		s.top = r.top;
		s.width = r.width();
		s.height = r.height();
	}
}
function RHtml_setBounds(h, l, t, w, h){
	if(h && h.style){
		var s = o.style;
		if(null != l){
			s.left = l;
		}
		if(null != t){
			s.top = t;
		}
		if(null != w){
			s.width = w;
		}
		if(null != h){
			s.height = h;
		}
	}
}
function RHtml_setPixelRect(o, r){
	if(o && o.style){
		var s = o.style;
		s.pixelLeft = r.left;
		s.pixelTop = r.top;
		s.pixelWidth = r.width();
		s.pixelHeight = r.height();
	}
}
function RHtml_setPixelBounds(o, l, t, w, h){
	if(o && o.style){
		var s = o.style;
		if(null != l){
			s.pixelLeft = l;
		}
		if(null != t){
			s.pixelTop = t;
		}
		if(null != w){
			s.pixelWidth = w;
		}
		if(null != h){
			s.pixelHeight = h;
		}
	}
}
function RHtml_toText(html){
	return html;
}
function RHtml_toHtml(text){
	if(null != text){
		text = text.toString();
		text = text.replace(/</g, '&lt;');
		text = text.replace(/>/g, '&gt;');
		text = text.replace(/ /g, '&nbsp;');
		text = text.replace(/\\r\\n/g, '<BR>');
		text = text.replace(/\\n/g, '<BR>');
	}
	return text;
}
function RHtml_changeWidth(s, t){
	if(s && t){
		var tw = parseInt(t.currentStyle.paddingLeft) + parseInt(t.currentStyle.paddingRight);
		t.style.pixelWidth = s.offsetWidth - tw;
	}
}
function RHtml_showNodes(h, o){
	if(h && h.childNodes){
		for(var n=0; n<h.childNodes.length; n++){
			var c = h.childNodes(n);
			if(c.tagName && c.style){
				c.style.display = 'block';
			}else if(c.nodeName == '#text'){
				c.nodeValue = o[n];
			}
		}
	}
}
function RHtml_hideNodes(h, o){
	if(h && h.childNodes){
		for(var n=0; n<h.childNodes.length; n++){
			var c = h.childNodes(n);
			if(c.tagName && c.style){
				c.style.display = 'none';
			}else if(c.nodeName == '#text'){
				o[n] = c.nodeValue;
				c.nodeValue = '';
			}
		}
	}
}
function RHtml_showChildren(h){
	if(h && h.children){
		for(var n=0; n<h.children.length; n++){
			var c = h.children(n);
			if(c.tagName && c.style){
				c.style.display = 'block';
			}
		}
	}
}
function RHtml_hideChildren(h){
	if(h && h.children){
		for(var n=0; n<h.children.length; n++){
			var c = h.children(n);
			if(c.tagName && c.style){
				c.style.display = 'none';
			}
		}
	}
}
function RHtml_get(name){
	return document.getElementById(name);
}
function RHtml_parent(o, t){
	if(o, t){
		t = t.toLowerCase();
		while(o){
			if(o.tagName.toLowerCase() == t){
				return o;
			}
			o = o.parentElement;
		}
	}
	return null;
}
function RHtml_posParent(h){
	while(h){
		if('visible' != h.currentStyle.overflow){
			return h;
		}
		h = h.offsetParent;
	}
	return null;
}
function RHtml_form(h){
	var f = this.parent(h, 'FORM');
	return f ? f : h.ownerDocument.forms[0];
}
function RHtml_popup(u, w, h){
	var l = (screen.width - w)/2;
	var t = (screen.height - h)/2 - 20;
	var s = RStr.format('left={0},top={1},width={2},height={3},toolbar=no,location=no,directories=no,status=no,menubar=no,resizable=yes,scrollbars=yes,dependent=yes', l, t, w, h);
	window.open(u, '_blank', s);
}
function RIntFace(o){
	if(!o){o=this};
	o.Chars     = '0123456789-%';
	o.NUMBER    = '0123456789-%';
	o.LEFT_CHAR = '0';
	o.isInt     = RInt_isInt;
	o.parse     = RInt_parse;
	o.format    = RInt_format;
	o.nvl       = RInt_nvl;
	o.toRange   = RInt_toRange;
	o.sum       = RInt_sum;
	return o;
}
var RInt = new RIntFace();
function RInt_isInt(value){
   return RStr.isPattern(value, 'n');
}
function RInt_parse(value){
	if(value == null){
		return 0;
	}
	value = RStr.trim(value.toString());
	while(true){
		if(value.charAt(0) != "0"){
			break;
		}
		value = value.substr(1);
	}
	var rs = (value.length > 0) ? parseInt(value) : 0;
	return isNaN(rs) ? 0 : rs;
}
function RInt_format(value, len, pad){
	if(!pad){
		pad = this.LEFT_CHAR;
	}
	var value = value.toString();
	var left = parseInt(len) - value.length;
	for(var i=0; i<left; i++){
		value = pad + value;
	}
	return value;
}
function RInt_nvl(v, d){
	return v ? v : (d ? d : 0);
}
function RInt_toRange(v, min, max){
	if(null == v){
		v = 0;
	}
	return Math.min(Math.max(v, min), max);
}
function RInt_sum(){
	var sum = 0;
	for(var n=0; n<arguments.length; n++){
		if(null != arguments[n]){
			sum += parseInt(arguments[n]);
		}
	}
	return sum;
}
function RLogFace(){
	var o = this;
	o.LEVEL_DEBUG = 0;
	o.LEVEL_INFO  = 1;
	o.LEVEL_WARN  = 2;
	o.LEVEL_ERROR = 3;
	o.LEVEL_FATAL = 4;
	o.dumpLevel   = 20;
	o.colors      = ['#FFFFFF', '#E0F0F0', '#F0E0F0', '#F0F0E0'];
	o.stack       = RLog_stack;
	o.debug       = RLog_debug;
	o.info        = RLog_info;
	o.warn        = RLog_warn;
	o.error       = RLog_error;
	o.fatal       = RLog_fatal;
	o.log         = RLog_log;
	o.dump        = RLog_dump;
	return o;
}
var RLog = top._log ? top._log : new RLogFace();
function RLog_stack(){
	var o = RLog_stack.caller;
	var s = new TStr();
	while(o){
		s.append(RMethod.name(o));
		o = o.caller;
		if(o){
			s.appendLine();
		}
	}
	this.log(ELog.Debug, s, arguments);
}
function RLog_debug(){
	var o = RLog_debug.caller;
	var s = new TStr();
	var n = this.dumpLevel;
	while(o && n>0){
		s.append(RMethod.name(o).replace('_', '.'));
		o = o.caller;
		if(o){
			s.append(' > ');
		}
		n--;
	}
	this.log(ELog.Debug, RMethod.name(RLog_debug.caller), arguments, s);
}
function RLog_info(){
	var o = RLog_debug.caller;
	var s = new TStr();
	var n = this.dumpLevel;
	while(o && n>0){
		s.append(RMethod.name(o).replace('_', '.'));
		o = o.caller;
		if(o){
			s.append(' > ');
		}
		n--;
	}
	this.log(ELog.Info, RMethod.name(RLog_info.caller), arguments, s);
}
function RLog_warn(){
	var o = RLog_debug.caller;
	var s = new TStr();
	var n = this.dumpLevel;
	while(o && n>0){
		s.append(RMethod.name(o).replace('_', '.'));
		o = o.caller;
		if(o){
			s.append(' > ');
		}
		n--;
	}
	this.log(ELog.Warn, RMethod.name(RLog_warn.caller), arguments, s);
}
function RLog_error(){
	var o = RLog_debug.caller;
	var s = new TStr();
	var n = this.dumpLevel;
	while(o && n>0){
		s.append(RMethod.name(o).replace('_', '.'));
		o = o.caller;
		if(o){
			s.append(' > ');
		}
		n--;
	}
	this.log(ELog.Error, RMethod.name(RLog_error.caller), arguments, s);
}
function RLog_fatal(){
	var o = RLog_debug.caller;
	var s = new TStr();
	var n = this.dumpLevel;
	while(o && n>0){
		s.append(RMethod.name(o).replace('_', '.'));
		o = o.caller;
		if(o){
			s.append(' > ');
		}
		n--;
	}
	this.log(ELog.Fatal, RMethod.name(RLog_fatal.caller), arguments, s);
}
function RLog_log(level, method, args, stack){
	var o = this;
	var obj = args[0];
	if(null != args[1]){
		var msg = args[1].toString();
		for(var n=2; n<args.length; n++){
			if(null == args[n]){
				msg = msg.replace('{' + (n-2) + '}', '');
			}else{
				msg = msg.replace('{' + (n-2) + '}', args[n]);
			}
		}
	}
	RConsole.find(FLoggerConsole).output(level, obj, method, msg, stack);
}
function RLog_dump(level, obj){
	RConsole.find(FLoggerConsole).output(level, obj, method, RClass.dump(obj));
}
function RMethodFace(){
	var o = this;
	o.virtuals   = new Object();
	o.events     = new Object();
	o.name       = RMethod_name;
	o.isEmpty    = RMethod_isEmpty;
	o.isFunction = RMethod_isFunction;
	o.empty      = RMethod_empty;
	o.virtual    = RMethod_virtual;
	o.event      = RMethod_event;
	o.abstract   = RMethod_abstract;
	return o;
}
var RMethod = new RMethodFace();
function RMethod_name(obj, full){
	if(obj != null && obj.constructor == Function){
		if(full){
			return RStr.substr(obj.toString(), 'function ', ')') + ')';
		}
		return RStr.substr(obj.toString(), 'function ', '(');
	}
	return null;
}
function RMethod_isEmpty(o){
	return !(o && o!=this.empty);
}
function RMethod_isFunction(o){
	if(o && o.constructor){
		return ('Function' == RStr.substr(o.constructor.toString(), 'function ', '('));
	}
	return false;
}
function RMethod_empty(){
}
function RMethod_virtual(obj, method){
	var o = this;
	var name = RClass.name(obj) + '.' + method;
	if(o.virtuals[name]){
		return o.virtuals[name];
	}
	var fun = function(){throw new Error('Virtual method be called.(' + name + ')');};
	fun.virtual = true;
	fun.name = name;
	o.virtuals[name] = fun;
	return fun;
}
function RMethod_abstract(self, name){
	var mp = null;
	var id = this.name(self);
	if(this.abstracts[id]){
		mp = this.abstracts[id];
	}else{
		mp = new Object();
		this.abstracts[id] = mp;
	}
	var fun = null;
	if(mp[name]){
		fun = mp[name];
	}else{
		fun = function(){throw new Error('Abstract method be called.(' + id + '.' + name + ')');};
		fun.abstract = true;
		mp[name] = fun;
	}
	return fun;
}
function RMethod_event(owner, clazz, method){
	var o = this.link;
	if(RClass.isClass(o, MEditable)){
		if(!RMethod.isEmpty(o.onEditKeyUp)){
			o.onEditKeyUp(RWindow.event(this));
		}
	}
}
function RMsgFace(o){
	if(!o){o=this;}
	o.messages = null;
	o.push  = RMsg_push;
	o.fatal = RMsg_fatal;
	o.error = RMsg_error;
	o.warn  = RMsg_warn;
	o.info  = RMsg_info;
	return o;
}
var RMsg = new RMsgFace();
function RMsg_push(msg){
	if(!this.messages){
		this.messages = new FLoopList();
	}
	this.messages.push(msg);
}
function RMsg_fatal(self, error, message, params){
	var s = new TStr();
	var stack = new Array();
	var o = RMsg_fatal.caller;
	while(o){
		if(RArray.contains(stack, o)){
			break;
		}
		stack.push(o);
		o = o.caller;
	}
	for(var n=0; n<stack.length; n++){
		o = stack[n];
		if(n > 0){
			s.appendLine();
		}
		s.append('   ' + (stack.length-n) + ': ' + RMethod.name(o));
	}
	var msg = new TStr();
	msg.appendLine(RContext.get(RMsg, 'fatal'));
	msg.appendLine(RStr.repeat('-', 60));
	msg.append(RClass.dump(self), ': ');
	if(message){
		var ag = arguments;
		for(var n=3; n<ag.length; n++){
			var p = ag[n];
			if(RClass.typeOf(p) == 'Function'){
				p = RMethod.name(p);
			}
			message = message.replace('{' + (n-3) + '}', p);
		}
	}
	msg.appendLine(message);
	msg.appendLine(RStr.repeat('-', 60));
	msg.appendLine('Stack:');
	msg.append(s);
	throw new Error(msg);
}
function RMsg_error(self, method, msg, params){
	throw new Error(msg);
}
function RMsg_warn(self, message, params){
	var s = new TStr();
	var n = 0;
	var msg = new TStr();
	msg.appendLine(RContext.get(RMsg, 'warn'));
	msg.appendLine(RStr.repeat('-', 60));
	if(message){
		var ag = arguments;
		for(var n=2; n<ag.length; n++){
			message = message.replace('{' + (n-1) + '}', ag[n]);
		}
	}
	msg.appendLine(message);
	alert(msg);
}
function RMsg_info(self, method, msg, params){
	this.push(msg);
}
function RObjFace(){
	var o = this;
	o.codes = new Array();
	o.name   = RObj_name;
	o.code   = RObj_code;
	o.nvl    = RObj_nvl;
	o.nvlObj = RObj_nvlObj;
	o.clone  = RObj_clone;
	o.copy   = RObj_copy;
	o.name   = RObj_name;
	return o;
}
var RObj = new RObjFace();
function RObj_name(o){
	if(o && o.constructor){
		var s = o.constructor.toString();
		return s.substring(s.indexOf('function ') + 9, s.indexOf('('));
	}
	return 'Null';
}
function RObj_code(o){
	var cs = this.codes;
	var ln = cs.length;
	for(var n=0; n<ln; n++){
		if(cs[n] == o){
			return n;
		}
	}
	cs[ln] = o;
	return ln;
}
function RObj_nvl(v){
	for(var n=0; n<arguments.length; n++){
		if(null != arguments[n]){
			return arguments[n];
		}
	}
	return null;
}
function RObj_nvlObj(v){
	return v ? v : new Object();
}
function RObj_clone(o){
	var r = new o.constructor();
	for(var n in o){
		var v = o[n];
		if(v != null){
			var c = v.constructor;
			if(c != Boolean && c != Date && c != String && c != Number && c != Function){
				v = this.clone(v);
			}
		}
		r[n] = v;
	}
	return r;
}
function RObj_copy(s, t){
	if(null!=s && null!=t){
		for(var n in s){
			var v = s[n];
			if(null != v){
				var c = v.constructor;
				if(c != Boolean && c != Date && c != String && c != Number && c != Function){
					if(t[n] == null){
						t[n] = new c();
					}
					this.copy(v, t[n]);
					continue;
				}
			}
			t[n] = v;
		}
	}
}
function RPackFace(o){
	if(!o){o=this};
	o.packStyle   = RPack_packStyle;
	o.unpackStyle = RPack_unpackStyle;
	o.parseLink   = RPack_parseLink;
	o.unpackLink  = RPack_unpackLink;
	o.split       = RPack_split;
	return o;
}
var RPack = new RPackFace();
function RPack_packStyle(map){
	var pack = new TStr();
	if(map){
		var count = map.count;
		for(var n=0; n<count; n++){
			if(n > 0){
				pack.append(';');
			}
			pack.append(map.name(n));
			pack.append(':');
			pack.append(map.value(n));
		}
	}
	return pack.toString();
}
function RPack_unpackStyle(map, pack){
	if(map && pack){
		var subs = pack.split(/;/g);
		var count = subs.length;
		for(var n=0; n<count; n++){
			if(subs[n]){
				var sub = subs[n].split(/:/g);
				if(sub.length == 2){
					map.set(RStr.trim(sub[0]), RStr.trim(sub[1]));
				}
			}
		}
	}
}
function RPack_parseLink(as, v){
	if(v && as && v.indexOf('${') == 0 && v.indexOf('}') == v.length-1){
		return as.get(v.substring(2, v.length-1));
	}
	return v;
}
function RPack_unpackLink(link, as){
	if(link){
		var pk = new TAttrs();
		var subs = link.split(/,/g);
		for(var n=0; n<subs.length; n++){
			var sub = subs[n];
			if(sub){
				var is = sub.split(/=/g);
				if(is.length == 2){
					pk.set(RStr.trim(is[0]), this.parseLink(as, RStr.trim(is[1])));
				}
			}
		}
		return pk;
	}
}
function RPack_split(s, a, b){
	var rs = new TAttrs();
	var subs = s.split(a);
	for(var n=0; n<subs.length; n++){
		var sub = subs[n].split(b);
		if(sub.length == 2){
			rs.set(sub[0], sub[1]);
		}else{
			rs.set(sub, null);
		}
	}
	return rs;
}
function RRectFace(){
	var o = this;
	o.nvl    = RRect_nvl;
	o.pack   = RRect_pack;
	o.unpack = RRect_unpack;
	return o;
}
var RRect = new RRectFace();
function RRect_nvl(rect){
	return rect ? rect : new TRect();
}
function RRect_pack(rect){
	var pack = null;
	if(rect){
		pack = rect.left + ',' + rect.top + ',' + rect.right + ',' + rect.bottom;
	}
	return pack;
}
function RRect_unpack(pack, rect){
	rect = this.nvl(rect);
	if(pack){
		var items = pack.split(',');
		if(items.length == 4){
			rect.left = RInt.parse(items[0]);
			rect.top = RInt.parse(items[1]);
			rect.right = RInt.parse(items[2])
			rect.bottom = RInt.parse(items[3]);
		}
	}
	return rect;
}
function RRegExpFace(o){
	if(!o){o=this;}
	o.test        = RRegExp_test;
	return o;
}
var RRegExp = new RRegExpFace();
function RRegExp_test(r,s){
   if(r && s){
      return r.test(s);
   }
}
function RRegExp_testRgexp(eps,s){
   if(eps && s){
      var r = new R
      return eps.test(s);
   }
   return false;
}
function RRegExp_test1(t,s,c){
   return  1;
}
function RStr_testRgexp2(){
   return 2;
}
function RSetFace(){
	var o = this;
	o.contains = RSet_contains;
	return o;
}
var RSet = new RSetFace();
function RSet_contains(s, v){
	return RStr.contains(s, v);
}
function RStrFace(){
	var o = this;
	o.EMPTY       = '';
	o.SPACE       = '   ';
	o.PAD         = ' ';
	o.TRIM        = ' \t\r\n';
	o.LOWER       = 'abcdefghijklmnopqrstuvwxyz';
	o.UPPER       = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ';
	o.CodeLowerA  = 'a'.charCodeAt(0);
	o.CodeLowerZ  = 'z'.charCodeAt(0);
	o.CodeUpperA  = 'A'.charCodeAt(0);
	o.CodeUpperZ  = 'Z'.charCodeAt(0);
	o.isEmpty     = RStr_isEmpty;
	o.isBlank     = RStr_isBlank;
	o.isAnsi      = RStr_isAnsi;
	o.isDbcs      = RStr_isDbcs;
	o.isPattern   = RStr_isPattern;
	o.contains    = RStr_contains;
	o.startsWith  = RStr_startsWith;
	o.endsWith    = RStr_endsWith;
	o.findChars   = RStr_findChars;
	o.toLower     = RStr_toLower;
	o.toUpper     = RStr_toUpper;
	o.equals      = RStr_equals;
	o.inRange     = RStr_inRange;
	o.nvl         = RStr_nvl;
	o.nvlStr      = RStr_nvlStr;
	o.pad         = RStr_pad;
	o.lpad        = RStr_lpad;
	o.rpad        = RStr_rpad;
	o.trim        = RStr_trim;
	o.ltrim       = RStr_ltrim;
	o.rtrim       = RStr_rtrim;
	o.repeat      = RStr_repeat;
	o.substr      = RStr_substr;
	o.firstUpper  = RStr_firstUpper;
	o.firstLower  = RStr_firstLower;
	o.toLine      = RStr_toLine;
	o.remove      = RStr_remove;
	o.removeChars = RStr_removeChars;
	o.firstLine   = RStr_firstLine;
	o.inChars     = RStr_inChars;
	o.split       = RStr_split;
	o.splitTwo    = RStr_splitTwo;
	o.format      = RStr_format;
	return o;
}
var RStr = new RStrFace();
function RStr_isEmpty(v){
	return (null == v) ? true : !(v.length);
}
function RStr_isBlank(v){
	return (null == v) ? true : !(v.trim().length);
}
function RStr_isAnsi(v){
	if(null == v){
		return false;
	}
	for(var n=0; n<v.length; n++){
		if(v.charCodeAt(n) > 255){
			return false;
		}
	}
	return true;
}
function RStr_isDbcs(v){
	if(null == v){
		return false;
	}
	for(var n=0; n<value.length; n++){
		if(value.charCodeAt(n) < 256){
			return false;
		}
	}
	return true;
}
function RStr_isPattern(v, p){
	if(null == v){
		return false;
	}
	var o = this;
	p = o.nvl(p, 'aAnf');
	var ar = new Array();
	for(var n=0; n<p.length; n++){
		var c = p.charAt(n);
		if(!ar[n] && 'n' == c){
			ar[n] = RInt.NUMBER;
		}else if(!ar[n] && 'f' == c){
			ar[n] = RFloat.NUMBER;
		}else if(!ar[n] && 'a' == c){
			ar[n] = o.LOWER;
		}else if(!ar[n] && 'A' == c){
			ar[n] = o.UPPER;
		}
	}
	var s = ar.join('');
	for(var n=0; n<v.length; n++){
		if(s.indexOf(v.charAt(n)) == -1){
			return false;
		}
	}
	return true;
}
function RStr_contains(v, s){
	return (null!=v && null!=s) ? (v.indexOf(s)!=-1) : false;
}
function RStr_startsWith(v, s){
	if(null == s){
		return true;
	}
	return (v != null) ? (v.indexOf(s) == 0) : false;
}
function RStr_endsWith(v, s){
	if(null == s){
		return true;
	}
	v = v.toString();
	var n = (v!=null) ? v.indexOf(s) : -1;
	return (n != -1) ? (n == (v.length - s.length)) : false;
}
function RStr_findChars(v, s){
	if(null != v){
		for(var n=0; n<v.length; n++){
			if(s.indexOf(v.charAt(n)) != -1){
				return n;
			}
		}
	}
	return -1;
}
function RStr_toLower(v){
	return v ? v.toLowerCase() : this.EMPTY;
}
function RStr_toUpper(v){
	return v ? v.toUpperCase() : this.EMPTY;
}
function RStr_toLowerChar(c){
	return c.toLowerCase().charCodeAt(0)
}
function RStr_toUpperChar(v){
	return c.toUpperCase().charCodeAt(0)
}
function RStr_equals(s, t, f){
	if(null == s || null == t){
		return false;
	}
	return f ? (s == t) : (s.toLowerCase() == t.toLowerCase());
}
function RStr_inRange(value, ranges, careCase){
	if(value && ranges){
		if(!careCase){
			value = value.toLowerCase();
		}
		for(var n=0; n<ranges.length; n++){
			var range = ranges[n];
			if(range != null){
				if(careCase && value == range){
					return true;
				}else if(!careCase && value == range.toLowerCase()){
					return true;
				}
			}
		}
	}
	return false;
}
function RStr_nvl(v, d){
	if(null != v){
		if(typeof(v) == 'string' && v.length > 0){
			return v;
		}
	}
	return (null != d) ? d : this.EMPTY;
}
function RStr_nvlStr(v){
	return v ? v : new TStr();
}
function RStr_pad(value, len, pad){
	value = (value != null) ? value.toString() : this.EMPTY;
	var loop = len - value.length;
	if(loop > 0){
		if(pad == null){
			pad = this.PAD;
		}
		var left = (loop%2==0) ? loop/2 : (loop-1)/2;
		return new Array(left+1).join(pad) + value + new Array(loop-left+1).join(pad);
	}
	return value;
}
function RStr_lpad(value, len, pad){
	value = (value != null) ? value.toString() : this.EMPTY;
	var loop = len - value.length;
	if(loop > 0){
		if(pad == null){
			pad = this.PAD;
		}
		var arr = new Array(loop);
		arr[arr.length] = value;
		return arr.join(pad);
	}
	return value;
}
function RStr_rpad(value, len, pad){
	value = (value != null) ? value.toString() : this.EMPTY;
	var loop = len - value.length;
	if(loop > 0){
		if(pad == null){
			pad = this.PAD;
		}
		return value + new Array(loop+1).join(pad);
	}
	return value;
}
function RStr_trim(value, trims){
	value = this.nvl(value);
	trims = this.nvl(trims, this.TRIM);
	var start = 0;
	var end = value.length - 1;
	for(; start<end; start++){
		if(trims.indexOf(value.charAt(start)) == -1){
			break;
		}
	}
	for(; end>=start; end--){
		if(trims.indexOf(value.charAt(end)) == -1){
			break;
		}
	}
	if(start !=0 || end != value.length-1){
		return value.substring(start, end+1);
	}
	return value;
}
function RStr_ltrim(value, trims){
	value = this.nvl(value);
	trims = this.nvl(trims, this.TRIM);
	var start = 0;
	var end = value.length - 1;
	for(; start<end; start++){
		if(trims.indexOf(value.charAt(start)) == -1){
			break;
		}
	}
	if(start !=0){
		return value.substring(start, end+1);
	}
	return value;
}
function RStr_rtrim(value, trims){
	value = this.nvl(value);
	trims = this.nvl(trims, this.TRIM);
	var end = value.length - 1;
	for(; end>=0; end--){
		if(trims.indexOf(value.charAt(end)) == -1){
			break;
		}
	}
	if(end != value.length-1){
		return value.substring(0, end+1);
	}
	return value;
}
function RStr_repeat(value, count){
	return new Array(count+1).join(value);
}
function RStr_substr(v, left, right){
	if(null != v){
		var start = 0;
		var end = v.length;
		if(left != null){
			start = v.indexOf(left);
			start = (start == -1) ? 0 : start + left.length;
		}
		if(right != null){
			end = v.indexOf(right, start);
			end = (end == -1) ? v.length : end;
		}
		return v.substring(start, end);
	}
}
function RStr_firstUpper(v){
	return (null != v) ? v.charAt(0).toUpperCase() + v.substr(1) : v;
}
function RStr_firstLower(){
	return (null != v) ? v.charAt(0).toLowerCase() + v.substr(1) : v;
}
function RStr_toLine(v){
	return v.replace(/\n/g, '\\n').replace(/\r/g, '\\r').replace(/\t/g, '\\t')
}
function RStr_remove(s, t){
	return s.replace(t, '');
}
function RStr_removeChars(v, s){
	if(null != v){
		var r = new Array();
		for(var n=0; n<v.length; n++){
			var c = v.charAt(n);
			if(-1 != s.indexOf(c)){
				continue;
			}
			r[r.length] = c;
		}
		return r.join('');
	}
	return v;
}
function RStr_firstLine(s){
	if(s){
		var n = Math.min(s.indexOf('\r'), s.indexOf('\n'));
		if(n != -1){
			return s.substr(0, n);
		}
		return s;
	}
	return '';
}
function RStr_inChars(c, s){
	return (null!=c && null!=s) ? (s.indexOf(c) != -1) : false;
}
function RStr_split(s, p){
	if(s && p){
 		return s.split(p);
	}
	return null;
}
function RStr_splitTwo(s, p){
	if(s && p){
		var r = new Array();
		var n = s.indexOf(p);
		if(n == -1){
			r.push(s);
		}else{
			r.push(RStr.trim(s.substring(0, n)));
			r.push(RStr.trim(s.substring(n+p.length)));
		}
		return r;
	}
	return null;
}
function RStr_format(s){
	var a = arguments;
	for(var n=1; n<a.length; n++){
		var p = a[n];
		if(RClass.typeOf(p) == 'Function'){
			p = RMethod.name(p);
		}
		s = s.replace('{' + (n-1) + '}', p);
	}
	return s;
}
function RStrsFace(o){
	if(!o){o=this};
	o.trim        = RStrs_trim;
	o.nvl         = RStrs_nvl;
	o.append      = RStrs_append;
	o.nameMinLen  = RStrs_nameMinLen;
	o.nameMaxLen  = RStrs_nameMaxLen;
	o.valueMinLen = RStrs_valueMinLen;
	o.valueMaxLen = RStrs_valueMaxLen;
	return o;
}
var RStrs = new RStrsFace();
function RStrs_trim(values, trims){
   if(values && values.constructor == Array){
      for(var n=0; n<values.length; n++){
         values[n] = RStr.trim(values[n], trims);
      }
   }
   return values;
}
function RStrs_nvl(values, offset, length){
	var result = new Array();
	for(var n=0; n<length; n++){
		if(!RStr.isEmpty(values[n])){
			result[result.length] = values[n];
		}
	}
	return result;
}
function RStrs_append(s, count, names, values){
	for(var n=0; n<count; n++){
		if(n > 0){
			s.append(',');
		}
		if(values){
			s.append(names[n], '=', values[n]);
		}else{
			s.append(names[n]);
		}
	}
}
function RStrs_nameMinLen(arr){
	var len = 0;
	if(values && values.constructor == Array){
		for(var name in arr){
			len = Math.min(name.length, len);
		}
	}
	return len;
}
function RStrs_nameMaxLen(arr){
	var len = 0;
	if(values && values.constructor == Array){
		var first = true;
		for(var name in arr){
			if(first){
				len = name.length;
				first = false;
			}else{
				len = Math.max(name.length, len);
			}
		}
	}
	return len;
}
function RStrs_valueMinLen(arr){
	var len = 0;
	if(values && values.constructor == Array){
		for(var name in arr){
			if(value != null && value.constructor == String){
				len = Math.min(value.length, len);
			}
		}
	}
	return len;
	var len = 0;
	for(var name in arr){
		var value = arr.
		len = Math.min(len.length, len);
	}
	return len;
}
function RStrs_valueMaxLen(arr){
	var len = 0;
	if(values && values.constructor == Array){
		var first = true;
		for(var name in arr){
			var value = arr[name];
			if(value != null && value.constructor == String){
				if(first){
					len = name.length;
					first = false;
				}else{
					len = Math.max(name.length, len);
				}
			}
		}
	}
	return len;
}
function RWordFace(){
	var o = this;
	o.ulName = RWord_ulName;
	return o;
}
var RWord = new RWordFace();
function RWord_ulName(s){
	var r = new TStr();
	if(s){
		for(var i=0; i<s.length; i++){
			var c = s.charAt(i);
			if(c.toUpperCase() == c){
				if(i > 0){
					r.append('_');
				}
				r.append(c.toLowerCase());
			}else{
				r.append(c);
			}
		}
	}
	return r;
}
function RXmlFace(o){
	if(!o){o=this};
	o.isNode       = RXml_isNode;
	o.makeNode     = RXml_makeNode;
	o.makeDocument = RXml_makeDocument;
	o.buildNode    = RXml_buildNode;
	o.fromText     = RXml_fromText;
	o.buildText    = RXml_buildText;
	return this;
}
var RXml = new RXmlFace();
function RXml_isNode(n){
	return (n && n.constructor == TNode);
}
function RXml_makeNode(xdoc){
	var doc = new TXmlDoc();
	if(xdoc.documentElement){
		RXml.buildNode(doc, null, xdoc.documentElement);
	}
	return doc.node;
}
function RXml_makeDocument(xdoc){
	var doc = new TXmlDoc();
	if(xdoc.documentElement){
		RXml.buildNode(doc, null, xdoc.documentElement);
	}
	return doc;
}
function RXml_buildNode(doc, node, element){
	var attrs = null;
	if(element.attributes){
		var len = element.attributes.length;
		if(len > 0){
			attrs = new TMap();
			for(var n=0; n<len; n++){
				var attr = element.attributes[n];
				if(attr.nodeName){
					attrs.set(attr.nodeName, RXml.fromText(attr.nodeValue));
				}
			}
		}
	}
	var text = new TStr();
	text.append(element.nodeValue);
	var childs = element.childNodes
	if(childs){
		for(var n=0; n<childs.length; n++){
			if(childs[n].nodeType == ENodeType.Text){
				text.append(childs[n].nodeValue);
			}
		}
	}
	var child = doc.create(element.nodeName, attrs, text);
	if(node){
		node.push(child);
	}else{
		doc.node = child;
	}
	if(childs){
		for(var n=0; n<childs.length; n++){
			if(childs[n].nodeType == ENodeType.Node){
				this.buildNode(doc, child, childs[n]);
			}
		}
	}
}
function RXml_fromText(s){
	return (null != s) ? s.replace(/\\n/g, '\n') : s;
}
function RXml_buildText(s, v){
	if(null != v){
		v = v.toString();
		for(var n=0; n<v.length; n++){
			var ch = v.charAt(n);
			switch(ch){
				case '<':
					s.append('&lt;');
					break;
				case '>':
					s.append('&gt;');
					break;
				case '"':
					s.append('&quot;');
					break;
				case '&':
					s.append('&amp;');
					break;
				case '\r':
					continue;
				case '\n':
					s.append('\\n');
					break;
				default:
					s.append(ch);
			}
		}
	}
	return s;
}
function TArray(array){
	var o = this;
	o.count    = 0;
	o.memory   = array ? array : new Array();
	o.isEmpty  = TArray_isEmpty;
	o.contains = TArray_contains;
	o.indexOf  = TArray_indexOf;
	o.get      = TArray_get;
	o.set      = TArray_set;
	o.push     = TArray_push;
	o.swap     = TArray_swap;
	o.remove   = TArray_remove;
	o.clear    = TArray_clear;
	o.dump     = TArray_dump;
	return o;
}
function TArray_isEmpty(){
	return (this.count == 0);
}
function TArray_contains(o){
	return (this.indexOf(o) != -1);
}
function TArray_indexOf(o){
	if(o != null){
		for(var n=0; n<this.count; n++){
			if(this.memory[n] == o){
				return n;
			}
		}
	}
	return -1;
}
function TArray_get(n){
	return (n>=0 && n<this.count) ? this.memory[n] : null;
}
function TArray_set(n, o){
	if(n>=0 && n<this.count){
		this.memory[n] = o;
	}
}
function TArray_push(o){
	var n = this.count++;
	this.memory[n] = o;
	return n;
}
function TArray_swap(l, r){
	if(l>= 0 && l<this.count && r>=0 && r<this.count && l!=r){
		var o = this.memory[l];
		this.memory[l] = this.memory[r];
		this.memory[r] = o;
	}
}
function TArray_remove(n){
	var o = null;
	if(n>= 0 && n<this.count){
		o = this.memory[n];
		var c = --this.count;
		for(var i=n; i<c; i++){
			this.memory[i] = this.memory[i+1];
		}
	}
	return o;
}
function TArray_clear(){
	this.count = 0;
}
function TArray_dump(d){
	d = RStr.nvlStr(d);
	d.append(RClass.name(this), ': ', this.count);
	for(var n=0; n<this.count; n++){
		d.append(' [', this.memory[n], ']');
	}
	return d;
}
function TAttrs(){
	var o = this;
	o.count         = 0;
	o.names         = new Array();
	o.values        = new Array();
	o.table         = new Object();
	o.isEmpty       = TAttrs_isEmpty;
	o.contains      = TAttrs_contains;
	o.containsValue = TAttrs_containsValue;
	o.indexOf       = TAttrs_indexOf;
	o.indexOfValue  = TAttrs_indexOfValue;
	o.append        = TAttrs_append;
	o.name          = TAttrs_name;
	o.value         = TAttrs_value;
	o.findName      = TAttrs_findName;
	o.setValue      = TAttrs_setValue;
	o.nvl           = TAttrs_nvl;
	o.get           = TAttrs_get;
	o.getInt        = TAttrs_getInt;
	o.set           = TAttrs_set;
	o.remove        = TAttrs_remove;
	o.removeName    = TAttrs_removeName;
	o.removeValue   = TAttrs_removeValue;
	o.clear         = TAttrs_clear;
	o.join          = TAttrs_join;
	o.pack          = TAttrs_pack;
	o.unpack        = TAttrs_unpack;
	o.resetTable    = TAttrs_resetTable;
	o.split         = TAttrs_split;
	o.dump          = TAttrs_dump;
	return o;
}
function TAttrs_isEmpty(){
	return (this.count == 0);
}
function TAttrs_contains(name){
	if(null != name){
		return (this.table[name.toString().toLowerCase()] != null);
	}
	return false;
}
function TAttrs_containsValue(value){
	return (this.indexOfValue(value) != -1);
}
function TAttrs_indexOf(name){
	if(name != null){
		var idx = this.table[name.toString().toLowerCase()];
		return (idx != null) ? idx : -1;
	}
	return -1;
}
function TAttrs_indexOfValue(value){
	for(var n=0; n<this.values.length; n++){
		if(this.values[n] == value){
			return n;
		}
	}
	return -1;
}
function TAttrs_append(attrs){
	if(attrs){
		for(var n=0; n<attrs.count; n++){
			this.set(attrs.name(n), attrs.value(n));
		}
	}
}
function TAttrs_name(index){
	return (index>=0 && index<this.count) ? this.names[index] : null;
}
function TAttrs_value(index){
	return (index>=0 && index<this.count) ? this.values[index] : null;
}
function TAttrs_setValue(index, value){
	if(index>=0 && index<this.count){
		this.values[index] = value;
	}
}
function TAttrs_findName(value){
	var idx = this.indexOfValue(value)
	return (idx != -1) ? this.names[idx] : null ;
}
function TAttrs_nvl(name, value){
	return RStr.nvl(this.get(name), value);
}
function TAttrs_get(n, v){
	if(null != n){
		var o = this;
		var i = o.table[n.toString().toLowerCase()];
		return (null != i) ? o.values[i] : v;
	}
	return v;
}
function TAttrs_getInt(n, v){
	return RInt.parse(this.get(n, v));
}
function TAttrs_set(name, value){
	if(name != null){
		var lower = name.toString().toLowerCase();
		var idx = this.table[lower];
		if(idx == null || idx >= this.count){
			idx = this.count++;
			this.names[idx] = name;
			this.table[lower] = idx;
		}
		this.values[idx] = value;
	}
}
function TAttrs_remove(index){
	var result = null;
	if(index >= 0 && index < this.count){
		result = this.values[index];
		this.names = IArray.remove(this.names, index);
		this.values = IArray.remove(this.values, index);
		this.count--;
		this.resetTable(index);
	}
	return result;
}
function TAttrs_removeName(name){
	var idx = this.indexOf(name);
	return (idx != -1) ? this.remove(idx) : null;
}
function TAttrs_removeValue(value){
	var sizeable = false;
	var names = new Array();
	var values = new Array();
	for(var n=0; n<this.count; n++){
		if(this.values[n] == value){
			sizeable = true;
		}else{
			names[names.length] = this.names[n];
			values[values.length] = this.values[n];
		}
	}
	if(sizeable){
		this.count = names.length;
		this.names = names;
		this.values = values;
		this.resetTable();
	}
}
function TAttrs_clear(){
	this.count = 0;
}
function TAttrs_join(n, v){
	var o = this;
	var r = new TStr();
	n = RStr.nvl(n, '=');
	v = RStr.nvl(v, '&');
	for(var i=0; i<o.count; i++){
		if(i > 0){
			r.append(v);
		}
		r.append(o.names[i]);
		r.append(n);
		r.append(o.values[i]);
	}
	return r.toString();
}
function TAttrs_pack(){
	var o = this;
	var pack = new TStr();
	for(var n=0; n<o.count; n++){
		var len = o.names[n].length;
		pack.append(len.toString().length, len, o.names[n]);
		if(null != o.values[n]){
			var v = o.values[n] + '';
			len = v.length;
			pack.append(len.toString().length, len, v);
		}else{
			pack.append('0');
		}
	}
	return pack.toString();
}
function TAttrs_unpack(pack){
	var o = this;
	o.count = 0;
	var pos = 0;
	var packlen = pack.length;
	while(pos < packlen){
		var len = parseInt(pack.substr(pos++, 1));
		var length = parseInt(pack.substr(pos, len));
		var name = pack.substr(pos+len, length);
		pos += len + length;
		len = parseInt(pack.substr(pos++, 1));
		if(len == 0){
			value = null;
		}else{
			length = parseInt(pack.substr(pos, len));
			value = pack.substr(pos+len, length);
			pos += len + length;
		}
		o.set(name, value);
	}
}
function TAttrs_resetTable(offset){
	for(var n=offset; n<this.count; n++){
		this.table[this.names[n]] = n;
	}
}
function TAttrs_split(s, n, v){
	var o = this;
	var ss = s.split(v);
	for(var i=0; i<ss.length; i++){
		var sb = ss[i].split(n);
		if(sb.length == 2){
			o.set(sb[0], sb[1]);
		}else{
			o.set(ss[i], '');
		}
	}
}
function TAttrs_dump(d){
	d = RStr.nvlStr(d);
	d.append(RClass.name(this), ':', this.count, '[');
	RStrs.append(d, this.count, this.names, this.values);
	d.append(']');
	return d;
}
function TBool(){
	var o = this;
	return o;
}
function TBoolSet(){
	var o = this;
	return o;
}
function TClass(name){
	var o = this;
	o.name           = name;
	o.parent         = null;
	o.base           = null;
	o.clazz          = null;
	o.instance       = null;
	o.abstract       = false;
	o.annotations    = new Object();
	o.styles         = new Array();
	o.register       = TClass_register;
	o.getAnnotations = TClass_getAnnotations;
	o.getAnnotation  = TClass_getAnnotation;
	o.style          = TClass_style;
	o.assign         = TClass_assign;
	o.newInstance    = TClass_newInstance;
	return o;
}
function TClass_register(r){
	var o = this;
	var a = r.annotation;
	var n = r.name;
	if(!a || !n){
		RMsg.fatal(o, null, "Unknown annotation (annotation={0},name={1},object={2})", a, n, RClass.dump(r));
	}
	var as = o.annotations[a];
	if(!as){
		as = o.annotations[a] = new Object();
	}
	if(as[n]){
		RMsg.fatal(o, null, "Duplicate annotation (annotation={0},name={1},object={2})", a, n, RClass.dump(r));
	}
	as[n] = r;
}
function TClass_getAnnotations(a){
	var o = this;
	var r = o.annotations[a];
	if(!r){
		RMsg.fatal(o, null, "Can't find annotations (annotation={0},class={1})", a, o.name);
	}
	return r;
}
function TClass_getAnnotation(a, n){
	var o = this;
	var r = null;
	var as = o.annotations[a];
	if(as){
		r = as[n];
	}
	if(!r){
		RMsg.fatal(o, null, "Can't find annotation (annotation={0},name={1},class={2})", a, n, o.name);
	}
	return r;
}
function TClass_style(n){
	var o = this;
	if(o.styles[n]){
		return o.styles[n];
	}
	var a = null;
	var p = o;
	while(p){
		var as = p.annotations[EAnnotation.Style];
		if(as){
			a = as[n];
			if(a){
				break;
			}
		}
		p = p.parent;
	}
	if(!a){
		RMsg.fatal(o, null, "No register style annotation (name={0},class={1})", o.name+'_'+n, o.name);
	}
	var p = o;
	var sn = null;
	while(p){
		var pn = p.name + '_' + n;
		if(RCss.has(pn)){
			sn = pn;
			break;
		}
		p = p.parent;
	}
	if(!sn){
		RMsg.fatal(o, null, "Css is undefined (name={0},class={1})", o.name+'_'+n, o.name);
	}
	o.styles[n] = sn;
	return sn;
}
function TClass_assign(c){
	var o = this;
	for(var an in c.annotations){
		var ls = o.annotations[an];
		if(!ls){
			ls = o.annotations[an] = new Object();
		}
		var as = c.annotations[an];
		for(var n in as){
			if(ls[n]){
				RMsg.fatal(o, null, "Duplicate annotation (annotation={0}, {1}.{2}={3}.{4})", an, o.name, n, c.name, n);
			}
			ls[n] = as[n];
		}
	}
}
function TClass_newInstance(){
	var o = this;
	if(o.abstract){
		var msg = new TStr();
		for(var n in o.instance){
			var v = o.instance[n];
			if(v && v.constructor == Function && v.virtual){
				if(!msg.isEmpty()){
					msg.append(',');
				}
				msg.append(v.name);
			}
		}
		RMsg.fatal(o, null, "Abstract Class can't be create (name={0})\n[{1}]", o.name, msg);
	}
	return RObj.clone(o.instance);
	var r = new o.instance.constructor();
	for(var n in o.instance){
		v = o[n];
		if(null != v){
			if(n == 'base'){
				r[n] = o.instance.base;
				continue;
			}
			var c = v.constructor;
			if(c != Boolean && c != Date && c != String && c != Number && c != Function){
				v = RObj.clone(v);
			}
		}
		r[n] = v;
	}
	return r;
}
function TContext(path){
	var o = this;
	o.path = path;
	o.get  = TContext_get;
	return o;
}
function TContext_get(name){
	return RContext.texts[this.path + name];
}
function TDataset(){
	var o = this;
	o.position      = 0;
	o.count         = 0;
	o.fieldCount    = 0;
	o.page          = 0;
	o.pageSize      = 20;
	o.pageCount     = 0;
	o.total         = 0;
	o.names         = new Array();
	o.table         = new Array();
	o.rows          = new Array();
	o.hasField      = TDataset_hasField;
	o.hasPage       = TDataset_hasPage;
	o.isChanged     = TDataset_isChanged;
	o.indexOf       = TDataset_indexOf;
	o.create        = TDataset_create;
	o.get           = TDataset_get;
	o.set           = TDataset_set;
	o.current       = TDataset_current;
	o.currentCount  = TDataset_currentCount;
	o.find          = TDataset_find;
	o.row           = TDataset_row;
	o.move          = TDataset_move;
	o.moveToRow     = TDataset_moveToRow;
	o.moveFirst     = TDataset_moveFirst;
	o.movePrior     = TDataset_movePrior;
	o.moveNext      = TDataset_moveNext;
	o.moveLast      = TDataset_moveLast;
	o.movePage      = TDataset_movePage;
	o.movePageFirst = TDataset_movePageFirst;
	o.movePagePrior = TDataset_movePagePrior;
	o.movePageNext  = TDataset_movePageNext;
	o.movePageLast  = TDataset_movePageLast;
	o.loadNode      = TDataset_loadNode;
	o.remove        = TDataset_remove;
	o.removeRow     = TDataset_removeRow;
	o.dump          = TDataset_dump;
	return o;
}
function TDataset_hasField(n){
	return (null != n) ? (null != this.table[n.toString().toLowerCase()]) : false;
}
function TDataset_hasPage(){
	var o = this;
	return (null != o.rows[o.pageSize*o.page]);
}
function TDataset_isChanged(){
	var o = this;
	var rs = o.rows;
	for(var n=0; n<rs.length; n++){
		var r = rs[n];
		if(r && r.isChanged()){
			return true;
		}
	}
	return false;
}
function TDataset_indexOf(r){
	var s = this.rows;
	for(var n=0; n<s.length; n++){
		if(s[n] == r){
			return n;
		}
	}
	return -1;
}
function TDataset_create(c, i){
	var o = this;
	var r = new TRow(o);
	if(null != i){
		o.rows[i] = r;
	}else{
		o.rows[o.count++] = r;
	}
	if(c){
		if(TAttrs == c.constructor){
			r.append(c);
		}else if(TNode == c.constructor){
			r.loadNode(c);
		}
	}
	return r;
}
function TDataset_get(n, v){
	var r = this.current();
	return r ? r.get(n, v) : null;
}
function TDataset_set(n, v){
	var r = this.current();
	if(r){
		r.set(n, v);
	}
}
function TDataset_current(){
	var o = this;
	var p = o.position;
	return (p >= 0 && p < o.total) ? o.rows[p] : null;
}
function TDataset_currentCount(){
	var o = this;
	if(o.page == o.pageCount - 1){
		var count = o.total % o.pageSize;
		if(count == 0 && o.total > 0){
			return o.pageSize;
		}
		return count;
	}
	return (o.total > 0) ? o.pageSize : 0;
}
function TDataset_find(){
	var o = this;
	var a = arguments;
	var l = a.length;
	if(l % 2 == 1){
		RMsg.fatal(o, 'Must pairs {0}', a);
	}
	var s = o.rows;
	for(var n=0; n<s.length; n++){
		var r = s[n];
		var f = true;
		for(var i=0; i<l; i+=2){
			if(r.get(a[n]) != a[n+1]){
				f = false;
				break;
			}
		}
		if(f){
			return r;
		}
	}
   return null;
}
function TDataset_row(n){
	return (n >= 0 && n < this.count) ? this.rows[n] : null;
}
function TDataset_move(p){
	this.position = p;
}
function TDataset_moveToRow(r){
	var o = this;
	var p = o.indexOf(r);
	if(-1 != p){
		o.position = p;
	}
}
function TDataset_moveFirst(r){
	var o = this;
	o.position = r ? o.pageSize*o.page-1 : o.pageSize*o.page;
}
function TDataset_movePrior(){
	var o = this;
	if(o.position > 0){
		o.position--;
		return true;
	}
	return false;
}
function TDataset_moveNext(){
	var o = this;
	if(o.position < o.pageSize*o.page+o.count-1){
		o.position++;
		return true;
	}
	return false;
}
function TDataset_moveLast(){
	this.position = this.count-1;
}
function TDataset_movePage(p){
	this.page = p;
}
function TDataset_movePageFirst(){
	var o = this;
	o.page = 0;
	o.count = o.currentCount();
}
function TDataset_movePagePrior(){
	var o = this;
	if(o.page > 0){
		o.page--;
		o.count = o.currentCount();
		return true;
	}
	return false;
}
function TDataset_movePageNext(){
	var o = this;
	if(o.page < o.pageCount - 1){
		o.page++;
		o.count = o.currentCount();
		return true;
	}
	return false;
}
function TDataset_movePageLast(){
	var o = this;
	o.page = o.pageCount - 1;
	o.count = o.currentCount();
}
function TDataset_loadNode(c){
	if(c){
		var o = this;
		o.name = c.get('name');
		o.page = c.getInt('page', 0);
		o.pageSize = c.getInt('page_size', 1000);
		o.pageCount = c.getInt('page_count', 1);
		if(c.nodes){
			var p = o.pageSize * o.page;
			var ns = c.nodes;
			for(var i=0; i<ns.count; i++){
				var n = ns.get(i);
				if(n && n.isName(RDataset.ROW)){
					var r = o.create(n, p++)
					r.store();
				}
			}
			o.count = Math.min(ns.count, o.pageSize);
		}
		o.total = c.getInt('total', o.count);
	}
}
function TDataset_remove(n){
	var o = this;
	var r = null;
	if(n >= 0 && n < o.count){
		var rs = o.rows;
		r = rs[n];
		var l = --o.count;
		for(var i=n; i<l; i++){
			rs[i] = rs[i+1];
		}
	}
	return r;
}
function TDataset_removeRow(r){
	var o = this;
	var n = o.indexOf(r);
	if(-1 != n){
		o.remove(n);
	}
}
function TDataset_dump(s){
	var o = this;
	s = RStr.nvlStr(s);
	s.append(RClass.name(o));
	s.append(' count=', o.count);
	s.append(' fields=', o.fieldCount);
	s.appendLine();
	if(o.rows){
		for(var n=0; n<o.rows.length; n++){
			s.append('- ');
			o.rows[n].dump(s);
			if(n != o.rows.length-1){
				s.appendLine();
			}
		}
	}
	return s;
}
function TDate(date){
	var o = this;
	o.date         = date ? date : new Date();
	o.year         = null;
	o.month        = null;
	o.day          = null;
	o.hour         = null;
	o.minute       = null;
	o.second       = null;
	o.ms           = null;
	o.monthDays    = TDate_monthDays;
	o.monthWeekDay = TDate_monthWeekDay;
	o.weekDay      = TDate_weekDay;
	o.setYear      = TDate_setYear;
	o.setMonth     = TDate_setMonth;
	o.setDay       = TDate_setDay;
	o.addYear      = TDate_addYear;
	o.addMonth     = TDate_addMonth;
	o.addDay       = TDate_addDay;
	o.refresh      = TDate_refresh;
	o.setDate      = TDate_setDate;
	o.now          = TDate_now;
	o.dump         = TDate_dump;
	o.refresh();
	return o;
}
function TDate_monthDays(){
	return RDate.monthDays(this.year, this.month);
}
function TDate_monthWeekDay(){
	return (8-(this.day-this.weekDay())%7)%7;
}
function TDate_weekDay(){
	return this.date.getDay();
}
function TDate_setYear(n){
	this.date.setFullYear(n);
	this.refresh();
}
function TDate_setMonth(n){
	this.date.setMonth(parseInt(n)-1);
	this.refresh();
}
function TDate_setDay(n){
	this.date.setDate(n);
	this.refresh();
}
function TDate_addYear(n){
	this.date.setFullYear(this.date.getFullYear()+parseInt(n));
	this.refresh();
}
function TDate_addMonth(n){
	this.date.setMonth(this.date.getMonth()+parseInt(n));
	this.refresh();
}
function TDate_addDay(n){
	this.date.setDay(this.date.getDay()+parseInt(n));
	this.refresh();
}
function TDate_refresh(){
	var o = this;
	var d = o.date;
	if(d){
		o.year = d.getFullYear();
		o.month = d.getMonth() + 1;
		o.day = d.getDate();
		o.hour = d.getHours();
		o.minute = d.getMinutes();
		o.second = d.getSeconds();
		o.ms = d.getMilliseconds();
	}
}
function TDate_setDate(d){
	var o = this;
	o.date = d;
	o.refresh();
}
function TDate_now(){
	var o = this;
	o.date = new Date();
	o.refresh();
}
function TDate_dump(){
	return RClass.dump(this) + ' ' + RDate.formatDate(this);
}
function TDumpItem(){
	var o = this;
	o.hPanel       = null;
	o.hDocument    = null;
	o.hTable       = null;
	o.hText        = null;
	o.hRow         = null;
	o.link         = null;
	o.level        = 0;
	o.caption      = null;
	o.children     = new Array();
	o.items        = new Array();
	o.loaded       = false;
	o.innerDisplay = false;
	o.display      = false;
	o.create       = TDumpItem_create;
	o.push         = TDumpItem_push;
	o.innerShow    = TDumpItem_innerShow;
	o.show         = TDumpItem_show;
	return o;
}
function TDumpItem_create(){
	var di = this.children[this.children.length] = new TDumpItem();
	return di;
}
function TDumpItem_push(obj){
	this.items[this.items.length] = obj;
	return obj;
}
function TDumpItem_innerShow(display){
	for(var n=0; n<this.items.length; n++){
		var tr = this.items[n];
		tr.style.display = display ? 'block' : 'none';
	}
	for(var n=0; n<this.children.length; n++){
		var di = this.children[n];
		di.hRow.style.display = display ? 'block' : 'none';
		if(display){
			di.show(di.innerDisplay);
		}else{
			di.innerDisplay = di.display;
			di.show(false);
		}
	}
}
function TDumpItem_show(display){
	this.display = display;
	var icon = display ? '-' : '+';
	this.hText.innerText = RStr.repeat('   ', this.level-1) + icon + ' ' + this.caption;
	this.innerShow(display);
}
function TList(){
	var o = this;
	o.count      = 0;
	o.memory     = new Array();
	o.isEmpty    = TList_isEmpty;
	o.contains   = TList_contains;
	o.indexOf    = TList_indexOf;
	o.get        = TList_get;
	o.set        = TList_set;
	o.insert     = TList_insert;
	o.push       = TList_push;
	o.sync       = TList_sync;
	o.swap       = TList_swap;
	o.remove     = TList_remove;
	o.removeItem = TList_removeItem;
	o.clear      = TList_clear;
	o.first      = TList_first;
	o.last       = TList_last;
	o.dump       = TList_dump;
	return o;
}
function TList_isEmpty(){
	return (this.count == 0);
}
function TList_contains(o){
	return (this.indexOf(o) != -1);
}
function TList_indexOf(o){
	if(null != o){
		for(var n=0; n<this.count; n++){
			if(this.memory[n] == o){
				return n;
			}
		}
	}
	return -1;
}
function TList_get(idx){
	return (idx >=0 && idx<this.count) ? this.memory[idx] : null;
}
function TList_set(idx, obj){
	if(idx >=0 && idx<this.count){
		this.memory[idx] = obj;
	}
}
function TList_insert(o, p){
	p = RInt.toRange(p, 0, this.count);
	for(var n=this.count; n>p; n--){
		this.memory[n] = this.memory[n-1];
	}
	this.memory[p] = o;
	this.count++;
	return p;
}
function TList_push(o){
	var p = this.count++;
	this.memory[p] = o;
	return p;
}
function TList_sync(obj){
	var idx = this.indexOf(obj);
	return (idx == -1) ? this.push(obj) : idx;
}
function TList_swap(from, to){
	if(from >= 0 && from < this.count && to >= 0 && to < this.count){
		var obj = this.memory[from];
		this.memory[from] = this.memory[to];
		this.memory[to] = obj;
	}
}
function TList_remove(idx){
	var obj = null;
	if(idx >= 0 && idx < this.count){
		obj = this.memory[idx];
		var loop = --this.count;
		for(var n=idx; n<loop; n++){
			this.memory[n] = this.memory[n+1];
		}
	}
	return obj;
}
function TList_removeItem(obj){
	var idx = this.indexOf(obj);
	if(idx != -1){
		this.remove(idx);
	}
}
function TList_clear(){
	this.count = 0;
}
function TList_first(){
	return this.memory[0];
}
function TList_last(){
	return this.memory[this.count-1];
}
function TList_dump(d){
	d = RStr.nvlStr(d);
	d.append(RClass.dump(this), ': ', this.count);
	for(var n=0; n<this.count; n++){
		var m = this.memory[n];
		if(m.dump){
			d.appendLine();
			m.dump(d);
		}else{
			d.append(' [', this.memory[n], ']');
		}
	}
	return d;
}
function TListener(owner, process){
	var o = this;
	o.owner     = owner;
	o.onProcess = process;
	o.process   = TListener_process;
	o.dump      = TListener_dump;
	return o;
}
function TListener_process(sender, params){
	var o = this;
	if(!o.onProcess){
		RMsg.fatal(o, 'Process method is null.');
	}
	if(o.owner){
		o.onProcess.call(o.owner, sender, params);
	}else{
		o.onProcess.call(sender, params);
	}
}
function TListener_dump(){
   return RClass.name(this) + ' owner=' + RClass.name(this.owner);
}
function TListeners(){
	var o = this;
	o.listeners = null;
	o.push      = TListeners_push;
	o.process   = TListeners_process;
	o.dump      = TListeners_dump;
	return o;
}
function TListeners_push(l){
	var o = this;
	if(!l){
		RMsg.fatal(o, null, 'Listener is null.');
	}
	if(!o.listeners){
		o.listeners = new Array();
	}
	o.listeners[o.listeners.length] = l;
}
function TListeners_process(sender, params){
	var ls = this.listeners;
	if(ls){
		for(var n=0; n<ls.length; n++){
			ls[n].process(sender, params);
		}
	}
}
function TListeners_dump(d){
	d = RStr.nvlStr(d);
	d.append(RClass.name(this));
	var count = this.listeners.length;
	for(var n=0; n<count; n++){
		d.append('\n   ' + this.listeners[n].dump());
	}
	return d;
}
function TLoopList(){
	var o = this;
	o.count      = 0;
	o.size       = 0;
	o.start      = new Object();
	o.setSize    = TLoopList_setSize;
	o.find       = TLoopList_find;
	o.contains   = TLoopList_contains;
	o.indexOf    = TLoopList_indexOf;
	o.get        = TLoopList_get;
	o.set        = TLoopList_set;
	o.push       = TLoopList_push;
	o.sync       = TLoopList_sync;
	o.remove     = TLoopList_remove;
	o.removeItem = TLoopList_removeItem;
	o.clear      = TLoopList_clear;
	o.dump       = TLoopList_dump;
	return o;
}
function TLoopList_setSize(size){
	var item = this.start;
	for(var n=0; n<size-1; n++){
		if(!item.next){
			item.next = new Object();
			item.value = null;
		}
		item = item.next;
	}
	item.next = this.start;
	this.size = size;
}
function TLoopList_find(idx){
	var item = this.start;
	if(idx >=0 && idx<this.count){
		for(var n=0; n<this.count; n++){
			if(n == idx){
				return item;
			}
			item = item.next;
		}
	}
	return null;
}
function TLoopList_isEmpty(){
	return (this.count == 0);
}
function TLoopList_contains(obj){
	return (this.indexOf(obj) != -1);
}
function TLoopList_indexOf(obj){
	var item = this.start;
	if(obj != null){
		for(var n=0; n<this.count; n++){
			if(item.value == obj){
				return n;
			}
			item = item.next;
		}
	}
	return -1;
}
function TLoopList_get(idx){
	var item = this.find(idx);
	return (item != null) ? item.value : null;
}
function TLoopList_set(idx, obj){
	var item = this.find(idx);
	if(item != null){
		item.value = obj;
	}
}
function TLoopList_push(obj){
	if(this.count + 1 > this.size){
		this.start.value = obj;
		this.start = this.start.next;
	}else{
		this.set(this.count++, obj);
	}
}
function TLoopList_sync(obj){
	var idx = this.indexOf(obj);
	return (idx == -1) ? this.push(obj) : idx;
}
function TLoopList_remove(idx){
	var obj = null;
	var item = this.find(idx);
	if(item != null){
		obj = item.value;
		for(var n=idx; n<this.count; n++){
			item.value = item.next.value;
		}
	}
	return obj;
}
function TLoopList_removeItem(obj){
	var idx = this.indexOf(obj);
	if(idx != -1){
		this.remove(idx);
	}
}
function TLoopList_clear(){
	this.count = 0;
}
function TLoopList_dump(){
	var dump = new FString();
	dump.append(IClass.name(this), ': ', this.count, '/', this.size);
	var item = this.start;
	for(var n=0; n<this.count; n++){
		dump.append(' [', item.value, ']');
		item = item.next;
	}
	return dump;
}
function TMap(){
	var o = this;
	o.count         = 0;
	o.names         = new Array();
	o.values        = new Array();
	o.table         = new Object();
	o.isEmpty       = TMap_isEmpty;
	o.contains      = TMap_contains;
	o.containsValue = TMap_containsValue;
	o.indexOf       = TMap_indexOf;
	o.indexOfValue  = TMap_indexOfValue;
	o.name          = TMap_name;
	o.value         = TMap_value;
	o.findName      = TMap_findName;
	o.setValue      = TMap_setValue;
	o.get           = TMap_get;
	o.getInt        = TMap_getInt;
	o.set           = TMap_set;
	o.remove        = TMap_remove;
	o.removeName    = TMap_removeName;
	o.removeValue   = TMap_removeValue;
	o.clear         = TMap_clear;
	o.reset         = TMap_reset;
	o.join          = TMap_join;
	o.pack          = TMap_pack;
	o.unpack        = TMap_unpack;
	o.resetTable    = TMap_resetTable;
	o.first         = TMap_first;
	o.last          = TMap_last;
	o.insert        = TMap_insert;
	o.push          = TMap_push;
	o.dump          = TMap_dump;
	return o;
}
function TMap_isEmpty(){
	return (this.count == 0);
}
function TMap_contains(name){
	if(name != null){
		return (this.table[name.toString().toLowerCase()] != null);
	}
	return false;
}
function TMap_containsValue(value){
	return (this.indexOfValue(value) != -1);
}
function TMap_indexOf(name){
	if(name != null){
		var idx = this.table[name.toString().toLowerCase()];
		return (idx != null) ? idx : -1;
	}
	return -1;
}
function TMap_indexOfValue(v){
	for(var n=0; n<this.count; n++){
		if(this.values[n] == v){
			return n;
		}
	}
	return -1;
}
function TMap_name(index){
	return (index>=0 && index<this.count) ? this.names[index] : null;
}
function TMap_value(index){
	return (index>=0 && index<this.count) ? this.values[index] : null;
}
function TMap_setValue(index, value){
	if(index>=0 && index<this.count){
		this.values[index] = value;
	}
}
function TMap_findName(value){
	var idx = this.indexOfValue(value)
	return (idx != -1) ? this.names[idx] : null ;
}
function TMap_get(n, v){
	var o = this;
	if(null != n){
		var i = o.table[n.toString().toLowerCase()];
		if(null != i){
			return o.values[i];
		}
	}
	return v;
}
function TMap_getInt(n, v){
	return RInt.parse(this.get(n, v));
}
function TMap_set(name, value){
	if(name != null){
		var lower = name.toString().toLowerCase();
		var idx = this.table[lower];
		if(idx == null || idx >= this.count){
			idx = this.count++;
			this.names[idx] = name;
			this.table[lower] = idx;
		}
		this.values[idx] = value;
	}
}
function TMap_remove(index){
	var result = null;
	if(index >= 0 && index < this.count){
		result = this.values[index];
		this.names = RArray.remove(this.names, index);
		this.values = RArray.remove(this.values, index);
		this.count--;
		this.resetTable(index);
	}
	return result;
}
function TMap_removeName(name){
	var idx = this.indexOf(name);
	return (idx != -1) ? this.remove(idx) : null;
}
function TMap_removeValue(value){
	var sizeable = false;
	var names = new Array();
	var values = new Array();
	for(var n=0; n<this.count; n++){
		if(this.values[n] == value){
			sizeable = true;
		}else{
			names[names.length] = this.names[n];
			values[values.length] = this.values[n];
		}
	}
	if(sizeable){
		this.count = names.length;
		this.names = names;
		this.values = values;
		this.resetTable();
	}
}
function TMap_clear(){
	this.count = 0;
}
function TMap_reset(){
	var o = this;
	o.count = 0;
	o.names = new Array();
	o.values = new Array();
	o.table = new Object();
}
function TMap_join(pad, all){
	var rs = new FString();
	for(var n=0; n<this.count; n++){
		if(all){
			pack.append(this.names[n], '=', '');
		}
		if(!all && this.values[n]!=null){
		}
		var len = this.names[n].length;
		pack.append(len.toString().length, len, this.names[n]);
		if(this.values[n] != null){
			len = this.values[n].length;
			pack.append(len.toString().length, len, this.values[n]);
		}else{
			pack.append('0');
		}
	}
	return pack.toString();
}
function TMap_pack(){
	var pack = new FString();
	for(var n=0; n<this.count; n++){
		var len = this.names[n].length;
		pack.append(len.toString().length, len, this.names[n]);
		if(this.values[n] != null){
			len = this.values[n].length;
			pack.append(len.toString().length, len, this.values[n]);
		}else{
			pack.append('0');
		}
	}
	return pack.toString();
}
function TMap_unpack(pack){
	this.count = 0;
	var pos = 0;
	var packlen = pack.length;
	while(pos < packlen){
		var len = parseInt(pack.substr(pos++, 1));
		var length = parseInt(pack.substr(pos, len));
		var name = pack.substr(pos+len, length);
		pos += len + length;
		len = parseInt(pack.substr(pos++, 1));
		if(len == 0){
			value = null;
		}else{
			length = parseInt(pack.substr(pos, len));
			value = pack.substr(pos+len, length);
			pos += len + length;
		}
		this.set(name, value);
	}
}
function TMap_resetTable(offset){
	for(var n in this.table){
		this.table[n] = null;
	}
	for(var n=offset; n<this.count; n++){
		this.table[this.names[n]] = n;
	}
}
function TMap_first(){
	return this.values[0];
}
function TMap_last(){
	return this.values[this.count-1];
}
function TMap_insert(index, name, value){
	var result = null;
	if(index >= 0 && index <= this.count){
		RArray.move(this.names, index, this.count-index, index+1);
		this.names[index] = name;
		RArray.move(this.values, index, this.count-index, index+1);
		this.values[index] = value;
		this.count++;
		this.resetTable(index);
	}
	return result;
}
function TMap_push(o){
	this.set(o.name, o);
}
function TMap_dump(dump){
	dump = RStr.nvlStr(dump);
	dump.appendLine(RClass.name(this), ': ', this.count);
	for(var n=0; n<this.count; n++){
		dump.appendLine(this.names[n], '=', ' [', this.values[n], ']');
	}
	return dump;
}
function TMessage(){
	var o = this;
	o.type        = EMessage.None;
	o.message     = null;
	o.description = null;
	o.loadConfig = TMessage_loadConfig;
	o.saveConfig = TMessage_saveConfig;
	o.icon       = TMessage_icon;
	return o;
}
function TMessage_loadConfig(config){
	var o = this;
	o.type        = RStr.toLower(config.name);
	o.message     = config.nvl('message');
	var desc = config.nvl('description');
	o.description = desc.replace(/\\n/g, '\n');
}
function TMessage_saveConfig(config){
	var o = this;
	config.name = o.type;
	config.set('message', o.message);
	config.set('description', o.description);
}
function TMessage_icon(){
	return 'sys.msg.' + this.type;
}
function TMessages(){
	var o = this;
	o.items      = new TList();
	o.hasMessage = TMessages_hasMessage;
	o.message    = TMessages_message;
	o.messages   = TMessages_messages;
	o.type       = TMessages_type;
	o.push       = TMessages_push;
	return o;
}
function TMessages_hasMessage(type){
	for(var n=0; n<this.items.count; n++){
		var m = this.items.get(n);
		if(m && m.type == type){
			return true;
		}
	}
	return false;
}
function TMessages_message(type){
	for(var n=0; n<this.items.count; n++){
		var m = this.items.get(n);
		if(m && m.type == type){
			return m;
		}
	}
	return null;
}
function TMessages_messages(type){
	var rs = null;
	for(var n=0; n<this.items.count; n++){
		var msg = this.items.get(n);
		if(msg && msg.type == type){
			if(!rs){
				rs = new TList();
			}
			rs.push(msg);
		}
	}
	return rs;
}
function TMessages_type(){
	if(this.hasMessage(EMessage.Fatal)){
		return EMessage.Fatal;
	}
	if(this.hasMessage(EMessage.Error)){
		return EMessage.Error;
	}
	if(this.hasMessage(EMessage.Warn)){
		return EMessage.Warn;
	}
	if(this.hasMessage(EMessage.Valid)){
		return EMessage.Valid;
	}
	if(this.hasMessage(EMessage.Info)){
		return EMessage.Info;
	}
	return EMessage.None;
}
function TMessages_push(msg){
	if(msg){
		this.items.push(msg);
	}
}
function TNameList(){
	var o = this;
	o.count      = 0;
	o.items      = new Array();
	o.isEmpty    = TNameList_isEmpty;
	o.contains   = TNameList_contains;
	o.indexOf    = TNameList_indexOf;
	o.find       = TNameList_find;
	o.get        = TNameList_get;
	o.set        = TNameList_set;
	o.push       = TNameList_push;
	o.sync       = TNameList_sync;
	o.swap       = TNameList_swap;
	o.remove     = TNameList_remove;
	o.removeItem = TNameList_removeItem;
	o.clear      = TNameList_clear;
	o.dump       = TNameList_dump;
	return o;
}
function TNameList_isEmpty(){
	return (this.count == 0);
}
function TNameList_contains(obj){
	return (this.indexOf(obj) != -1);
}
function TNameList_indexOf(obj){
	if(obj != null){
		for(var n=0; n<this.count; n++){
			if(this.items[n] == obj){
				return n;
			}
		}
	}
	return -1;
}
function TNameList_find(name){
	if(name != null){
		for(var n=0; n<this.count; n++){
			var item = this.items[n];
			if(item && item.name == name){
				return item;
			}
		}
	}
	return null;
}
function TNameList_get(idx){
	return (idx >=0 && idx<this.count) ? this.items[idx] : null;
}
function TNameList_set(idx, obj){
	if(idx >=0 && idx<this.count){
		this.items[idx] = obj;
	}
}
function TNameList_push(obj){
	var idx = this.count++;
	this.items[idx] = obj;
	return idx;
}
function TNameList_sync(obj){
	var idx = this.indexOf(obj);
	return (idx == -1) ? this.push(obj) : idx;
}
function TNameList_swap(from, to){
	if(from >= 0 && from < this.count && to >= 0 && to < this.count){
		var obj = this.items[from];
		this.items[from] = this.items[to];
		this.items[to] = obj;
	}
}
function TNameList_remove(idx){
	var obj = null;
	if(idx >= 0 && idx < this.count){
		obj = this.items[idx];
		var loop = --this.count;
		for(var n=idx; n<loop; n++){
			this.items[n] = this.items[n+1];
		}
	}
	return obj;
}
function TNameList_removeItem(obj){
	var idx = this.indexOf(obj);
	if(idx != -1){
		this.remove(idx);
	}
}
function TNameList_clear(){
	this.count = 0;
}
function TNameList_dump(){
	var dump = new FString();
	dump.append(IClass.name(this), ': ', this.count);
	for(var n=0; n<this.count; n++){
		dump.append(' [', this.items[n], ']');
	}
	return dump;
}
function TNode(name, attrs){
	var o = this;
	o.name       = RStr.nvl(name, 'Node');
	o.value      = null;
	o.attrs      = attrs;
	o.nodes      = null;
	o.isName     = TNode_isName;
	o.attributes = TNode_attributes;
	o.contains   = TNode_contains;
	o.nvl        = TNode_nvl;
	o.get        = TNode_get;
	o.getInt     = TNode_getInt;
	o.set        = TNode_set;
	o.node       = TNode_node;
	o.push       = TNode_push;
	o.create     = TNode_create;
	o.find       = TNode_find;
	o.isEmpty    = TNode_isEmpty;
	o.xml        = TNode_xml;
	o.toString   = TNode_toString;
	o.innerDump  = TNode_innerDump;
	o.dump       = TNode_dump;
	return o;
}
function TNode_isName(name){
	return RStr.equals(this.name, name);
}
function TNode_attributes(){
	if(!this.attrs){
		this.attrs = new TAttrs();
	}
	return this.attrs;
}
function TNode_contains(n){
	return this.attrs ? this.attrs.contains(n) : false;
}
function TNode_nvl(name, def){
	return RStr.nvl(this.get(name), def);
}
function TNode_get(n, v){
	return this.attrs ? this.attrs.get(n, v) : null;
}
function TNode_getInt(n, v){
	return this.attrs ? this.attrs.getInt(n, v) : v;
}
function TNode_set(name, value){
	if(null != value){
		this.attributes().set(name, value);
	}
}
function TNode_node(n){
   return this.nodes ? this.nodes.get(n) : null;
}
function TNode_push(node){
	var o = this;
	if(!o.nodes){
		o.nodes = new TList();
	}
	o.nodes.push(node);
}
function TNode_create(name, attrs){
   var node = new TNode(name, attrs);
	if(!RClass.isClass(attrs, TAttrs)){
		var a = arguments;
		var len = a.length;
		for(var n=1; n<len; n+=2){
			if(n + 1 < len){
				node.set(a[n], a[n+1]);
			}else{
				node.value = a[n];
			}
		}
	}
	this.push(node);
   return node;
}
function TNode_isEmpty(){
   return this.nodes ? this.nodes.isEmpty() : null;
}
function TNode_find(name, attrs){
	if(this.nodes){
		var count = this.nodes.count;
		if(name != null){
			name = name.toLowerCase();
		}
		var len = arguments.length;
		for(var n=0; n<count; n++){
			var node = this.nodes.get(n);
			if(name != null && name != node.name.toLowerCase()){
				continue;
			}
			var finded = true;
			for(var i=1; i<len; i+=2){
				if(i+1 < len){
					if(node.attrs.get(arguments[n]) != arguments[n+1]){
						finded = false;
						break;
					}
				}else{
					if(node.value != arguments[n]){
						finded = false;
						break;
					}
				}
			}
			if(finded){
				return node;
			}
		}
	}
   return null;
}
function TNode_xml(s){
	var o = this;
	s = RStr.nvlStr(s);
	s.append('<', o.name);
	var as = o.attrs;
	if(as){
		for(var n=0; n<as.count; n++){
			s.append(' ', as.name(n), '="');
			RXml.buildText(s, as.value(n));
			s.append('"');
		}
	}
	if(!o.nodes && o.value==null){
		s.append('/');
	}
	s.append('>');
	var ns = o.nodes;
	if(ns){
		for(var n=0; n<ns.count; n++){
			ns.get(n).xml(s);
		}
	}
	RXml.buildText(s, o.value)
	if(o.nodes || o.value != null){
		s.append('</', o.name, '>');
	}
	return s;
}
function TNode_toString(){
	return this.xml().toString();
}
function TNode_innerDump(dump, node, space){
	if(space == null){
		space = '';
	}
	dump.append(space, node.name, '(', RClass.name(node), ')');
	if(node.attrs){
		var count = node.attrs.count;
		dump.append(' [', count, ':');
		for(var n=0; n<count; n++){
			if(n > 0){
				dump.append(' ');
			}
			dump.append(node.attrs.name(n), '=', node.attrs.value(n));
			if(n < count-1){
				dump.append(',');
			}
		}
		dump.append(']');
	}
	if(node.value){
		var value = node.value.toString();
		if(!RStr.isEmpty(value)){
			dump.append(' {', value.length, ':', value, '}');
		}
	}
	if(node.nodes){
		var count = node.nodes.count;
		dump.append('\n');
		for(var n=0; n<count; n++){
			node.nodes.get(n).dump(dump, space + '   ');
			if(n < count-1){
				dump.append('\n');
			}
		}
	}
	return dump;
}
function TNode_dump(d, space){
	d = RStr.nvlStr(d);
	return this.innerDump(d, this, space);
}
function TPoint(x, y){
	var o = this;
	o.x      = x == null? 0 : parseInt(x);
	o.y      = x == null? 0 : parseInt(y);
	o.equals = TPoint_equals;
	o.assign = TPoint_assign;
	o.set    = TPoint_set;
	o.dump   = TPoint_dump;
	return o;
}
function TPoint_equals(p){
	return p ? (this.x == p.x && this.y == p.y) : false;
}
function TPoint_assign(pos){
	this.x = pos.x;
	this.y = pos.y;
}
function TPoint_set(x, y){
	this.x = x;
	this.y = y;
}
function TPoint_dump(){
   return RClass.dump(this) + ' [' + this.x + ',' + this.y + ']';
}
function TPoint3(x, y, z){
	var o = this;
	o.x      = x;
	o.y      = y;
	o.z      = z;
	o.set    = TPoint3_set;
	o.resize = TPoint3_resize;
	o.dump   = TPoint3_dump;
	return o;
}
function TPoint3_set(x, y, z){
	if(x != null){
		this.x = x;
	}
	if(y != null){
		this.y = y;
	}
	if(z != null){
		this.z = z;
	}
}
function TPoint3_resize(x, y, z){
	if(x != null){
		this.x += x;
	}
	if(y != null){
		this.y += y;
	}
	if(z != null){
		this.z += z;
	}
}
function TPoint3_dump(){
   return IClass.typeOf(this) + ' [' + this.x + ',' + this.y + ',' + this.z + ']';
}
function TPtyBool(name, value){
	var o = this;
	o.annotation = EAnnotation.Property;
	o.name       = name;
	o.config     = RWord.ulName(name).toString();
	o.value      = value;
	o.load       = TPtyBool_load;
	o.save       = TPtyBool_save;
	return o;
}
function TPtyBool_load(o, c){
	o[this.name] = RBool.isTrue(c.get(this.config));
}
function TPtyBool_save(o, c){
	c.set(this.config, RBool.toString(o[this.name]));
}
function TPtyBoolSet(name, config, search, value){
	var o = this;
	o.annotation = EAnnotation.Property;
	o.name       = name;
	o.config     = RWord.ulName(config).toString();
	o.search     = search;
	o.value      = value;
	o.load       = TPtyBoolSet_load;
	o.save       = TPtyBoolSet_save;
	return o;
}
function TPtyBoolSet_load(o, c){
	o[this.name] = RSet.contains(c.get(this.config), this.search);
}
function TPtyBoolSet_save(o, c){
	var n = this.name;
	var v = o[n];
	var s = c.nvl(this.config);
	var e = RSet.contains(s, this.search);
	if(v && !e){
		c.set(n, s + this.search);
	}else if(!v && e){
		c.set(n, RStr.remove(s, this.search));
	}
}
function TPtyCfg(name){
	var o = this;
	o.annotation = EAnnotation.Property;
	o.force      = true;
	o.name       = name;
	o.config     = RWord.ulName(name).toString();
	o.load       = TPtyCfg_load;
	o.save       = RMethod.empty;
	return o;
}
function TPtyCfg_load(o, c){
	o[this.name] = c;
}
function TPtyInt(name, value){
	var o = this;
	o.annotation = EAnnotation.Property;
	o.name       = name;
	o.config     = RWord.ulName(name).toString();
	o.value      = RInt.nvl(value);
	o.load       = TPtyInt_load;
	o.save       = TPtyInt_save;
	return o;
}
function TPtyInt_load(o, c){
	o[this.name] = c.nvl(this.config);
}
function TPtyInt_save(o, c){
	c.set(this.config, o[this.name]);
}
function TPtyRect(){
	var o = this;
	o.annotation = EAnnotation.Property;
	return o;
}
function TPtyStr(name, value, config){
	var o = this;
	o.annotation = EAnnotation.Property;
	o.name       = name;
	o.config     = RStr.nvl(config, RWord.ulName(name).toString());
	o.value      = value;
	o.load       = TPtyStr_load;
	o.save       = TPtyStr_save;
	return o;
}
function TPtyStr_load(o, c){
	o[this.name] = c.nvl(this.config);
}
function TPtyStr_save(o, c){
	c.set(this.config, o[this.name]);
}
function TRect(left, top, right, bottom){
	var o = this;
	o.left      = RInt.nvl(left);
	o.top       = RInt.nvl(top);
	o.right     = RInt.nvl(right);
	o.bottom    = RInt.nvl(bottom);
	o.assign    = TRect_assign;
	o.set       = TRect_set;
	o.setBounds = TRect_setBounds;
	o.width     = TRect_width;
	o.setWidth  = TRect_setWidth;
	o.height    = TRect_height;
	o.setHeight = TRect_setHeight;
	o.move      = TRect_move;
	o.inc       = TRect_inc;
	o.dec       = TRect_dec;
	o.pack      = TRect_dump;
	o.unpack    = TRect_dump;
	o.dump      = TRect_dump;
	return o;
}
function TRect_assign(rect){
	this.left = rect.left;
	this.top = rect.top;
	this.right = rect.right;
	this.bottom = rect.bottom;
}
function TRect_set(left, top, right, bottom){
	this.left = left;
	this.top = top;
	this.right = right;
	this.bottom = bottom;
}
function TRect_setBounds(left, top, width, height){
	var o = this;
	o.left = left;
	o.top = top;
	o.right = o.left + width - 1;
	o.bottom = o.top + height - 1;
}
function TRect_width(){
	return this.right - this.left + 1;
}
function TRect_setWidth(width){
	if(width){
		this.right = this.left + width - 1;
	}
}
function TRect_height(){
	return this.bottom - this.top + 1;
}
function TRect_setHeight(height){
	if(height){
		this.bottom = this.top + height - 1;
	}
}
function TRect_move(x, y){
	this.left += x;
	this.top += y;
	this.right += x;
	this.bottom += y;
}
function TRect_inc(border){
	var n = RInt.nvl(border, 1);
	this.left -= n;
	this.top -= n;
	this.right += n;
	this.bottom += n;
}
function TRect_dec(border){
	var n = RInt.nvl(border, 1);
	this.left += n;
	this.top += n;
	this.right -= n;
	this.bottom -= n;
}
function TRect_dump(d){
	d = RStr.nvlStr(d);
	d.append(RClass.name(this));
	d.append(' [', this.left, ',', this.top, '-', this.right, ',', this.bottom, '] ');
	d.append('(', this.width(), '-', this.height(), ')');
   return d;
}
function TRow(ds){
	var o = this;
	o.dataset   = ds;
	o.status    = ERowStatus.Normal;
	o.values    = new Array();
	o.stores    = new Array();
	o.contains  = TRow_contains;
	o.indexOf   = TRow_indexOf;
	o.isChanged = TRow_isChanged;
	o.isDataChanged = TRow_isDataChanged;
	o.name      = TRow_name;
	o.value     = TRow_value;
	o.get       = TRow_get;
	o.nvl       = TRow_nvl;
	o.set       = TRow_set;
	o.setValue  = TRow_setValue;
	o.append    = TRow_append;
	o.loadNode  = TRow_loadNode;
	o.saveNode  = TRow_saveNode;
	o.store     = TRow_store;
	o.reset     = TRow_reset;
	o.toAttrs   = TRow_toAttrs;
	o.dump      = TRow_dump;
	return o;
}
function TRow_contains(v){
	return (-1 != this.indexOf(v));
}
function TRow_indexOf(v){
	var s = this.values;
	for(var n=0; n<s.length; n++){
		if(s[n] == value){
			return n;
		}
	}
	return -1;
}
function TRow_isChanged(){
	var o = this;
	if(ERowStatus.Normal != o.status){
		return true;
	}
	return o.isDataChanged();
}
function TRow_isDataChanged(){
	var o = this;
	var d = o.dataset;
	for(var n=0; n<d.fieldCount; n++){
		if(RStr.nvl(o.values[n]) != RStr.nvl(o.stores[n])){
			return true;
		}
	}
	return false;
}
function TRow_name(n){
	return (n>=0 && n<this.dataset.fieldCount) ? this.dataset.names[n] : null;
}
function TRow_value(index){
	return (n>=0 && n<this.dataset.fieldCount) ? this.dataset.values[n] : null;
}
function TRow_get(n, v){
	if(null != n){
		var i = this.dataset.table[n.toString().toLowerCase()];
		if(null != i){
			return this.values[i];
		}
	}
	return v;
}
function TRow_nvl(n, v){
	return RStr.nvl(this.get(n), v);
}
function TRow_set(n, v){
	if(null != n){
		var o = this;
		var d = o.dataset;
		var l = n.toString().toLowerCase();
		var i = d.table[l];
		if(null == i || i >= d.fieldCount){
			i = d.fieldCount++;
			d.names[i] = n;
			d.table[l] = i;
		}
		o.values[i] = v;
	}
}
function TRow_setValue(n, v){
	var o = this;
	if(n>=0 && n<o.dataset.fieldCount){
		o.values[n] = v;
	}
}
function TRow_append(a){
	if(a){
		for(var n=0; n<a.count; n++){
			this.set(a.name(n), a.value(n));
		}
	}
}
function TRow_loadNode(n){
	var o = this;
	if(n && n.attrs){
		o.append(n.attrs);
		o.status = n.get(RDataset.ROW_STATUS);
	}
}
function TRow_saveNode(n){
	if(n){
		var o = this;
		var d = o.dataset;
		var f = 'U';
		if(o.isInsert){
			f = 'I';
		}else if(o.isDelete){
			f = 'D';
		}
		n.set(RDataset.ROW_STATUS, f);
		for(var n=0; n<d.fieldCount; n++){
			n.set(d.names[n], o.values[n]);
		}
	}
}
function TRow_store(){
	RArray.copy(this.values, this.stores);
}
function TRow_reset(){
	RArray.copy(this.stores, this.values);
}
function TRow_toAttrs(){
	var o = this;
	var d = o.dataset;
	var a = new TAttrs();
	a.set('_os', o.status);
	for(var n=0; n<d.fieldCount; n++){
		a.set(d.names[n], o.values[n]);
	}
	return a;
}
function TRow_dump(s){
	var o = this;
	var d = o.dataset;
	s = RStr.nvlStr(s);
	s.append(RClass.name(o), ' [');
	s.append('changed=', o.isChanged());
	s.append(',status=', o.status);
	s.append(']');
	for(var n=0; n<d.fieldCount; n++){
		if(n > 0){
			s.append(',');
		}
		s.append(d.names[n], '=', o.values[n]);
		if(RStr.nvl(o.values[n]) != RStr.nvl(o.stores[n])){
			s.append('<', o.stores[n], '>');
		}
	}
	s.append(']');
	return s;
}
function TSet(){
	var o = this;
	o.contains = TSet_contains;
	return o;
}
function TSet_contains(s, v){
	return RStr.contains(s, v);
}
function TSize(w, h){
	var o = this;
	o.width  = RInt.nvl(w);
	o.height = RInt.nvl(h);
	o.assign = TSize_assign;
	o.set    = TSize_set;
	o.dump   = TSize_dump;
	return o;
}
function TSize_assign(o){
	this.width = o.width;
	this.height = o.height;
}
function TSize_set(w, h){
	this.width = w;
	this.height = h;
}
function TSize_dump(){
   return RClass.dump(this) + ' [' + this.width + ',' + this.height + ']';
}
function TStr(values){
	var o = this;
	o.count     = 0;
	o.strings   = new Array();
	o.isEmpty    = TStr_isEmpty;
	o.assign     = TStr_assign;
	o.append     = TStr_append;
	o.appendIf   = TStr_appendIf;
	o.appendLine = TStr_appendLine;
	o.push       = TStr_push;
	o.clear      = TStr_clear;
	o.toString   = TStr_toString;
	o.dump       = TStr_dump;
	for(var n=0; n<arguments.length; n++){
		if(arguments[n] != null){
			this.strings[this.count++] = arguments[n] + '';
		}
	}
	return o;
}
function TStr_isEmpty(){
	return this.count==0;
}
function TStr_assign(){
	var o = this;
	o.count = 0;
	for(var n=0; n<arguments.length; n++){
		if(null != arguments[n]){
			o.strings[o.count++] = arguments[n] + '';
		}
	}
	return o;
}
function TStr_append(){
	var o = this;
	for(var n=0; n<arguments.length; n++){
		if(null != arguments[n]){
			o.strings[o.count++] = arguments[n] + '';
		}
	}
	return o;
}
function TStr_appendIf(){
	var o = this;
	if(arguments[0]){
		for(var n=1; n<arguments.length; n++){
			if(null != arguments[n]){
				o.strings[o.count++] = arguments[n] + '';
			}
		}
	}
	return o;
}
function TStr_appendLine(){
	var o = this;
	for(var n=0; n<arguments.length; n++){
		if(null != arguments[n]){
			o.strings[o.count++] = arguments[n] + '';
		}
	}
	o.strings[o.count++] = '\n';
	return o;
}
function TStr_push(values){
	for(var n=0; n<arguments.length; n++){
		if(arguments[n] != null){
			this.strings[this.count++] = arguments[n];
		}
	}
	return this;
}
function TStr_clear(){
	this.count = 0;
}
function TStr_toString(){
	return this.strings.slice(0, this.count).join('');
}
function TStr_dump(){
	var s = this.toString();
	return [IObject.name(this), ':', s.length, '[', s, ']'].join('');
}
function TStrings(){
	var o = this;
	o.count      = 0;
	o.memory     = new Array();
	o.isEmpty    = TStrings_isEmpty;
	o.contains   = TStrings_contains;
	o.indexOf    = TStrings_indexOf;
	o.get        = TStrings_get;
	o.set        = TStrings_set;
	o.push       = TStrings_push;
	o.sync       = TStrings_sync;
	o.swap       = TStrings_swap;
	o.remove     = TStrings_remove;
	o.removeItem = TStrings_removeItem;
	o.clear      = TStrings_clear;
	o.dump       = TStrings_dump;
	return o;
}
function TStrings_isEmpty(){
	return (this.count == 0);
}
function TStrings_contains(obj){
	return (this.indexOf(obj) != -1);
}
function TStrings_indexOf(obj){
	if(obj != null){
		for(var n=0; n<this.count; n++){
			if(this.memory[n] == obj){
				return n;
			}
		}
	}
	return -1;
}
function TStrings_get(idx){
	return (idx >=0 && idx<this.count) ? this.memory[idx] : null;
}
function TStrings_set(idx, obj){
	if(idx >=0 && idx<this.count){
		this.memory[idx] = obj;
	}
}
function TStrings_push(obj){
	var idx = this.count++;
	this.memory[idx] = obj;
	return idx;
}
function TStrings_sync(obj){
	var idx = this.indexOf(obj);
	return (idx == -1) ? this.push(obj) : idx;
}
function TStrings_swap(from, to){
	if(from >= 0 && from < this.count && to >= 0 && to < this.count){
		var obj = this.memory[from];
		this.memory[from] = this.memory[to];
		this.memory[to] = obj;
	}
}
function TStrings_remove(idx){
	var obj = null;
	if(idx >= 0 && idx < this.count){
		obj = this.memory[idx];
		var loop = --this.count;
		for(var n=idx; n<loop; n++){
			this.memory[n] = this.memory[n+1];
		}
	}
	return obj;
}
function TStrings_removeItem(obj){
	var idx = this.indexOf(obj);
	if(idx != -1){
		this.remove(idx);
	}
}
function TStrings_clear(){
	this.count = 0;
}
function TStrings_dump(){
	var dump = new FString();
	dump.append(IObject.name(this), ': ', this.count);
	for(var n=0; n<this.count; n++){
		dump.append(' [', this.memory[n], ']');
	}
	return dump;
}
function TXmlCnn(){
	var o = this;
	o.address     = null;
	o.sync        = false;
	o.inUsing     = false;
	o.document    = null;
	o.cnnControl  = null;
	o.docControl  = null;
	o.cnnMode     = null;
	o.onLoad      = null;
	o.onFire      = TXmlCnn_onFire;
	o.onCnnReady  = TXmlCnn_onCnnReady;
	o.onDocReady  = TXmlCnn_onDocReady;
	o.construct   = TXmlCnn_construct;
	o.setHeaders  = TXmlCnn_setHeaders;
	o.newDocument = TXmlCnn_newDocument;
	o.newConnect  = TXmlCnn_newConnect;
	o.send        = TXmlCnn_send;
	o.receive     = TXmlCnn_receive;
	o.syncSend    = TXmlCnn_syncSend;
	o.syncReceive = TXmlCnn_syncReceive;
	o.construct();
	return o;
}
function TXmlCnn_onFire(doc, element){
	if(doc){
		this.document = (doc.constructor == Function) ? new doc() : new doc.constructor();
	}else{
		this.document = new TXmlDoc();
	}
	if(element){
		RXml.buildNode(this.document, null, element)
	}
	if(this.onLoad){
		this.onLoad(this);
	}
	this.inUsing = false;
}
function TXmlCnn_onCnnReady(cnn, doc){
	if(cnn.readyState == EXmlCnnStatus.Finish){
		var dc = this.docControl;
		if(this.xmlMode == EXmlCnnType.IE){
			var self = this;
			dc.async = true;
			dc.onreadystatechange = function(){self.onDocReady(dc, doc)};
			dc.loadXML(cnn.responseText);
		}else{
			this.onFire(doc, cnn.responseXml.documentElement);
		}
	}
}
function TXmlCnn_onDocReady(dc, doc){
	if(dc.readyState == EXmlCnnParse.Finish){
		if(dc.documentElement){
			this.onFire(doc, dc.documentElement);
		}else{
			alert('Read xml error.\n' + this.cnnControl.responseText);
		}
	}
}
function TXmlCnn_construct(){
	this.cnnControl = this.newConnect();
	this.docControl = this.newDocument();
}
function TXmlCnn_setHeaders(cnn, len){
	if(this.cnnMode == EXmlCnnType.IE){
		cnn.setrequestheader('content-type', 'application/x-www-form-urlencoded');
		cnn.setRequestHeader('content-length', len);
	}
}
function TXmlCnn_newDocument(){
	var obj = null;
	var err = null;
	this.cnnMode = EXmlCnnType.IE;
	var ctls = ["MSXml2.DOMDocument", "Microsoft.XmlDOM", "MSXml.DOMDocument", "MSXml3.XmlDOM"];
	for(var n=0; n<ctls.length; n++) {
		try{obj = new ActiveXObject(ctls[n]);break;}catch(e){err = e;}
	}
	if(!obj){
		this.cnnMode = EXmlCnnType.NC;
		try{obj = document.implementation.createDocument('', '', null);}catch(e){err = e;}
	}
	if(!obj){
		alert(err);
	}
	return obj;
}
function TXmlCnn_newConnect(){
	var obj = null;
	var err = null;
	this.xmlMode = EXmlCnnType.IE;
	var ctls = ["MSXml2.XmlHTTP", "Microsoft.XmlHTTP", "MSXml.XmlHTTP", "MSXml3.XmlHTTP"];
	for(var n=0; n<ctls.length; n++) {
		try{obj = new ActiveXObject(ctls[n]);break;}catch(e){err = e;}
	}
	if(!obj){
		this.xmlMode = EXmlCnnType.NC;
		try{obj = new XmlHttpRequest();}catch(e){err = e;}
	}
	if(!obj){
		alert(err);
	}
	return obj;
}
function TXmlCnn_send(url, doc){
	this.inUsing = true;
	this.address = url;
	var xml = RClass.isClass(doc, TXmlDoc) ? doc.xml().toString(): '';
	var cnn = this.cnnControl;
	RLog.info(this, 'Send xml url [{1}]', url);
	cnn.abort();
	cnn.open('POST', url, true);
	this.setHeaders(cnn, xml.length);
	var self = this;
	cnn.onreadystatechange = function(){self.onCnnReady(cnn, doc)};
	cnn.send(xml);
}
function TXmlCnn_receive(url, doc){
	this.send(url, doc);
}
function TXmlCnn_syncSend(url, doc){
	this.inUsing = true;
	this.address = url;
	var xml = RClass.isClass(doc, TXmlDoc) ? doc.xml().toString(): '';
	var cnn = this.cnnControl;
	cnn.open('POST', url, false);
	this.setHeaders(cnn, xml.length);
	cnn.send(xml);
	var element = null;
	if(this.xmlMode == EXmlCnnType.IE){
		var dc = this.docControl;
		dc.async = false;
		dc.loadXML(cnn.responseText);
		element = dc.documentElement;
	}else{
		element = cnn.responseXml.documentElement;
	}
	if(doc){
		this.document = (doc.constructor == Function) ? new doc() : new doc.constructor();
	}else{
		this.document = new TXmlDoc();
	}
	if(element){
		RXml.buildNode(this.document, null, element);
	}else{
		alert('Read xml error.\n' + this.cnnControl.responseText);
	}
	this.inUsing = false;
	return this.document;
}
function TXmlCnn_syncReceive(url, doc){
	return this.syncSend(url, doc);
}
function TXmlDoc(){
	var o = this;
	o.node       = null;
	o.create     = TXmlDoc_create;
	o.createNode = TXmlDoc_createNode;
	o.root       = TXmlDoc_root;
	o.xml        = TXmlDoc_xml;
	o.dump       = TXmlDoc_dump;
	return o;
}
function TXmlDoc_create(name, attrs, value){
	return this.createNode(name, attrs, value);
}
function TXmlDoc_createNode(name, attrs, value){
	var node = new TNode();
	node.name = name;
	node.attrs = attrs;
	node.value = value;
	return node;
}
function TXmlDoc_root(){
	if(!this.node){
		this.node = new TNode();
		this.node.name = 'Config';
	}
	return this.node;
}
function TXmlDoc_xml(){
	var xml = new TStr();
	xml.append("<?xml version='1.0' encoding='UTF-8'?>");
	this.root().xml(xml);
	return xml;
}
function TXmlDoc_dump(d){
	d = RStr.nvlStr(d);
	d.appendLine(RClass.name(this));
	this.root().dump(d);
	return d;
}
function EActionFace(){
	var o = this;
	o.Fetch  = 'F';
	o.Show   = 'V';
	o.Search = 'S';
	o.List   = 'L';
	o.Picker = 'P';
	o.Insert = 'I';
	o.Update = 'U';
	o.Delete = 'D';
	o.Design = 'G';
	o.Border = 'B';
	o.Move   = 'M';
	o.Status = 'T';
	return o;
}
var EAction = new EActionFace();
function EActiveFace(){
	var o = this;
	o.Sleep  = 0;
	o.Active = 1;
	o.Cancel = 2;
	o.Finish = 3;
	return o;
}
var EActive = new EActiveFace();
function EAlignFace(){
	var o = this;
	o.Left        = 'left';
	o.Center      = 'center';
	o.Right       = 'right';
	o.Top         = 'up';
	o.Middle      = 'middle';
	o.Bottom      = 'down';
	o.BottomLeft  = 'bl';
	o.BottomRight = 'br';
	return o;
}
var EAlign = new EAlignFace();
function EAssignFace(){
	var o = this;
	o.Property = 0;
	o.Clone    = 1;
	o.All      = 2;
	return o;
}
var EAssign = new EAssignFace();
function EBorderStyleFace(){
	var o = this;
	o.None      = 0;
	o.Square    = 1;
	o.Round     = 2;
	o.RoundDrop = 3;
	return o;
}
var EBorderStyle = new EBorderStyleFace();
function EColorFace(){
	var o = this;
	o.Normal        = '#FFFFFF';
	o.Select        = '#F8C59A';
	o.Valid         = '#FFCCCC';
	o.Edit          = '#ebffff';
	o.Require       = '#FF0000';
	o.Readonly      = '#FFFFDD';
	o.Delete        = '#DDDDDD';
	o.TextReadonly  = '#00FF00';
	o.Row           = '#FFFFFF';
	o.RowSelect     = '#BDEAEA';
	o.RowHover      = '#FFFFDD';
	o.RowEdit       = '#FFFFFF';
	o.RowEditSelect = '#FDEBDB';
	return o;
}
var EColor = new EColorFace();
function ECursorFace(){
	var o = this;
	o.Default   = 'default';
	o.NorthWest = 'NW';
	o.SouthWest = 'SW';
	o.SouthEast = 'SE';
	o.NorthEast = 'NE';
	o.West      = 'W';
	o.South     = 'S';
	o.East      = 'E';
	o.North     = 'N';
	return o;
}
var ECursor = new ECursorFace();
function EDataActionFace(){
	var o = this;
	o.Fetch     = 'fetch';
	o.Search    = 'search';
	o.Lov       = 'lov';
	o.FetchLov  = 'fetchLov';
	o.Insert    = 'insert';
	o.Update    = 'update';
	o.Delete    = 'delete';
	o.First     = 'first';
	o.Prior     = 'prior';
	o.Next      = 'next';
	o.Last      = 'last';
	o.Action    = 'action';
	o.DsChanged = 'dschanged';
	o.EndFetch  = 'endfetch';
	o.EndUpdate = 'endupdate';
	return o;
}
var EDataAction = new EDataActionFace();
function EDataStatusFace(){
	var o = this;
	o.Unknown  = '-';
	o.Insert   = 'I';
	o.Update   = 'U';
	o.Delete   = 'D';
	return o;
}
var EDataStatus = new EDataStatusFace();
function EDesignFace(){
	var o=this;
	o.Move   = 1;
	o.Border = 2;
	return o;
}
var EDesign = new EDesignFace();
function EDragFace(){
	var o = this;
	o.Move = 1;
	o.Size = 2;
	return o;
}
var EDrag = new EDragFace();
function EEditStatusFace(o){
	if(!o){o=this;}
	o.Blur   = 0;
	o.Cancel = 1;
	o.Ok     = 2;
	return o;
}
var EEditStatus = new EEditStatusFace();
function EEventFace(o){
	if(!o){o=this;}
	o.None       = 0;
	o.Construct  = 1;
	o.Initialize = 2;
	o.Build      = 3;
	o.Refresh    = 4;
	o.Resize     = 5;
	o.Show       = 6;
	o.Hidden     = 7;
	o.Disable    = 8;
	o.Enable     = 9;
	o.Release    = 10;
	o.Design     = 11;
	o.Action     = 12;
	o.Valid      = 13;
	return o;
}
var EEvent = new EEventFace();
function EEventStatusFace(o){
	if(!o){o=this;}
	o.None     = 0;
	o.Continue = 1;
	o.Stop     = 2;
	o.Cancel   = 3;
	return o;
}
var EEventStatus = new EEventStatusFace();
function EEventTypeFace(o){
	if(!o){o=this;}
	o.None   = 0;
	o.Before = 1;
	o.After  = 2;
	return o;
}
var EEventType = new EEventTypeFace();
function EIconFace(){
	var o = this;
	o.None      = 'n';
	o.SysBlock  = 'block';
	o.SysPlus   = 'plus';
	o.SysMinus  = 'minus';
	o.SysEmpty  = 'empty';
	o.SysNode   = 'node';
	o.SysSearch = 'search';
	o.Floder    = 'floder';
	o.Tree      = 'tv';
	return o;
}
var EIcon = new EIconFace();
function ELabelFace(){
	var o = this;
	o.All    = 'A';
	o.Label  = 'L';
	o.Hidden = 'H';
	return o;
}
var ELabel = new ELabelFace();
function ELayerFace(){
	var o = this;
	o.Default = 20000;
	o.Shadow  =  5000;
	o.Disable =  5000;
	o.Window  = 20000;
	o.Drop    = 40000;
	o.Editor  = 10000;
	o.Border  = 20000;
	o.Move    = 25000;
	o.Search  = 45000;
	o.Message = 45000;
	return o;
}
var ELayer = new ELayerFace();
function EMessageFace(){
	var o = this;
	o.Fatal = 'fatal';
	o.Error = 'error';
	o.Warn  = 'warn';
	o.Valid = 'valid';
	o.Info  = 'info';
	o.None  = 'none';
	return o;
}
var EMessage = new EMessageFace();
function EMonitorFace(){
	var o=this;
	o.Active = 1;
	o.Sleep  = 2;
	o.Stop   = 3;
	o.Cancel = 4;
	return o;
}
var EMonitor = new EMonitorFace();
function EMoveSizeFace(){
	var o = this;
	o.Range       = 6;
	o.Border      = 5;
	o.InnerBorder = 3;
	o.MinWidth    = 200;
	o.MinHeight   = 100;
	o.Layer       = 10000;
	o.MaxLayer    = 20000;
	o.EditLayer   = 30000;
	return o;
}
var EMoveSize = new EMoveSizeFace();
function ENodeStatusFace(o){
	if(!o){o=this};
	o.Normal = 0;
	o.Hover  = 1;
	o.Select = 2;
	return o;
}
var ENodeStatus = new ENodeStatusFace();
function EOrderFace(){
	var o = this;
	o.None = 'N';
	o.Asc  = 'A';
	o.Desc = 'D';
	return o;
}
var EOrder = new EOrderFace();
function EPanelFace(){
	var o = this;
	o.Display   = 0;
	o.Panel     = 1;
	o.Border    = 2;
	o.Edit      = 3;
	o.Focus     = 4;
	o.Design    = 5;
	o.Container = 6;
	o.Scroll    = 7;
	o.Shadow    = 8;
	o.Size      = 9;
	o.Move      = 10;
	return o;
}
var EPanel = new EPanelFace();
function EPositionFace(){
	var o = this;
	o.Left   = 'left';
	o.Right  = 'right';
	o.Top    = 'top';
	o.Bottom = 'bottom';
	o.Before     = 1;
	o.After      = 2;
	o.LineBefore = 3;
	o.LineAfter  = 4;
	return o;
}
EPosition = new EPositionFace();
function ERefreshFace(){
	var o = this;
	o.All       = 'A';
	o.LoadValue = 'LV';
	o.SaveValue = 'SV';
	return o;
}
var ERefresh = new ERefreshFace();
function EScopeFace(){
	var o = this;
	o.Global = 1;
	o.Page   = 2;
	o.Local  = 3;
	return o;
}
EScope = new EScopeFace();
function ESearchFace(){
	var o = this;
	o.Equals = 'E';
	o.Begin  = 'B';
	o.End    = 'N';
	o.Like   = 'L';
	return o;
}
var ESearch = new ESearchFace();
function EServiceFace(){
	var o = this;
	o.Dataset    = 'database.dataset';
	o.List       = 'design.list';
	o.WebForm    = 'design.webform';
	o.Translate  = 'design.translate';
	o.WebDataset = 'logic.dataset';
	return o;
}
var EService = new EServiceFace();
function EStatusFace(){
	var o = this;
	o.Default   = 'df';
	o.Continue  = 'cn';
	o.Stop      = 'sp';
	o.Cancel    = 'cl';
	o.Exit      = 'ex';
	return o;
}
var EStatus = new EStatusFace();
function EStoreFace(){
	var o = this;
	o.Full     = 0;
	o.Sort     = 1;
	o.Config   = 2;
	o.Value    = 3;
	o.Name     = 4;
	o.DataName = 5;
	o.DataNvl  = 6;
	o.Reset    = 7;
	return o;
}
var EStore = new EStoreFace();
function EStyleFace(){
	var o = this;
	o.Normal    = 0;
	o.Readonly  = 1;
	o.Edit      = 2;
	o.Hover     = 3;
	o.EditHover = 4;
	o.Select    = 5;
	o.Delete    = 6;
	return o;
}
var EStyle = new EStyleFace();
function EXmlEventFace(){
	var o = this;
	o.Send        = 1;
	o.Receive     = 2;
	o.SyncSend    = 3;
	o.SyncReceive = 4;
	return o;
}
EXmlEvent = new EXmlEventFace();
function FComponent(o){
	o = RClass.inherits(this, o, FObject, MConfig, MClone);
	o.name         = RClass.register(o, new TPtyStr('name'));
	o.label        = RClass.register(o, new TPtyStr('label'));
	o.parent       = null;
	o.components   = null;
	o.event        = null;
	o.oeInitialize = RMethod.empty;
	o.oeRelease    = RMethod.empty;
	o.onEvent      = FComponent_onEvent;
	o.topComponent = FComponent_topComponent;
	o.allComponent = FComponent_allComponent;
	o.component    = FComponent_component;
	o.findByClass  = FComponent_findByClass;
	o.initialize   = FComponent_initialize;
	o.createChild  = FComponent_createChild;
	o.push         = FComponent_push;
	o.process      = FComponent_process;
	o.release      = FComponent_release;
	o.innerDump    = FComponent_innerDump;
	o.dump         = FComponent_dump;
	return o;
}
function FComponent_onEvent(e){
	var o = this;
	var r = null;
	switch(e.code){
		case EEvent.Initialize:
			r = o.oeInitialize(e);
			break;
		case EEvent.Release:
			r = o.oeRelease(e);
			break;
	}
	return r ? r : EEventStatus.Continue;
}
function FComponent_topComponent(c){
	var o = this;
	if(c){
		while(RClass.isClass(o.parent, c)){
			o = o.parent;
		}
	}else{
		while(o.parent){
			o = o.parent;
		}
	}
	return o;
}
function FComponent_allComponent(c, l){
	var o = this;
	if(null == c){
		c = FComponent;
	}
	if(null == l){
		l = new TList();
	}
	if(RClass.isClass(o, c)){
		l.push(o);
	}
	var cs = this.components;
	if(cs){
		for(var n=0; n<cs.count; n++){
			var p = cs.value(n);
			if(RClass.isClass(p, FComponent)){
				p.allComponent(c, l);
			}
		}
	}
	return l;
}
function FComponent_component(name){
	if(this.components){
		if(RInt.isInt(name)){
			return this.components.value(name);
		}
		return this.components.get(name);
	}
}
function FComponent_findByClass(cls){
	var cs = this.components;
	if(cs){
		for(var n=0; n<cs.count; n++){
			var c = cs.value(n);
			if(RClass.isClass(c, cls)){
				return c;
			}
		}
	}
}
function FComponent_initialize(){
	var o = this;
	o.event = REvent.nvl(o.event, o, EEvent.Initialize);
	o.process(o.event);
}
function FComponent_createChild(config){
	return RClass.create('F' + config.name);
}
function FComponent_push(o){
	if(RClass.isClass(o, FComponent)){
		if(!this.components){
			this.components = new TMap();
		}
		if(!o.name){
			o.name = this.components.count;
		}
		this.components.set(o.name, o);
		o.parent = this;
	}
}
function FComponent_process(event){
	var o = this;
	event.type = EEventType.Before;
	var result = o.onEvent(event);
	if(result == EEventStatus.Stop || result == EEventStatus.Cancel){
		return result;
	}
	var cs = o.components;
	if(cs){
		for(var n=0; n<cs.count; n++){
			var c = cs.value(n);
			if(c){
				result = c.process(event);
				if(result == EEventStatus.Cancel){
					return EEventStatus.Cancel;
				}
			}
		}
	}
	event.type = EEventType.After;
	result = o.onEvent(event);
	if(result == EEventStatus.Stop || result == EEventStatus.Cancel){
		return result;
	}
	return EEventStatus.Continue;
}
function FComponent_release(){
	var o = this;
	o.event = REvent.nvl(o.event, o, EEvent.Release);
	o.process(o.event);
	o.components = null;
}
function FComponent_innerDump(dump){
	RClass.dump(dump, this);
	dump.append(' name=' + this.name);
}
function FComponent_dump(dump, level){
	dump = RStr.nvlStr(dump);
	level = RInt.nvl(level);
	RDump.appendLevel(dump, level);
	this.innerDump(dump);
	if(this.components){
		dump.appendLine();
		for(var n=0; n<this.components.count; n++){
			var com = this.components.value(n);
			if(com){
				com.dump(dump, level+1);
			}
		}
	}
	return dump;
}
function FContainer(o){
	o = RClass.inherits(this, o, FControl, MContainer);
	o.oeBuild       = FContainer_oeBuild;
	o.oeDesign      = RMethod.empty;
	o.push          = FContainer_push;
	o.buildChildren = FContainer_buildChildren;
	o.panel         = FContainer_panel;
	o.focus         = FContainer_focus;
	return o;
}
function FContainer_oeBuild(event){
	this.base.FControl.oeBuild.call(this, event);
	return EEventStatus.Continue;
}
function FContainer_push(o){
	this.base.FControl.push.call(this, o);
	if(RClass.isClass(o, FControl)){
		this.controls.set(o.name, o);
	}
}
function FContainer_buildChildren(){
	var os = this.controls;
	if(os){
		for(var n=0; n<os.count; n++){
			os.value(n).build();
		}
	}
}
function FContainer_panel(type){
	var o = this;
	if(EPanel.Container == type){
		return o.hPanel;
	}
	return o.base.FControl.panel.call(o, type);
}
function FContainer_focus(){
	var o = this;
	var cs = o.controls;
	for(var n=0; n<cs.count; n++){
		var c = cs.value(n);
		if(RClass.isClass(c, MEditable) && c.isShow() && !c.disabled && c.canEdit){
			c.focus();
			return;
		}
	}
}
function FControl(o){
	o = RClass.inherits(this, o, FComponent, MStyle, MDisplay, MSize, MPad);
	o.nowrap        = RClass.register(o, new TPtyBool('nowrap', false));
	o.disable       = RClass.register(o, new TPtyBool('disable', false));
	o.hint          = RClass.register(o, new TPtyStr('hint'));
	o.onEnter       = RClass.register(o, new HMouseEnter('onEnter'), FControl_onEnter);
	o.onLeave       = RClass.register(o, new HMouseLeave('onLeave'), FControl_onLeave);
	o.onMouseOver   = RClass.register(o, new HMouseOver('onMouseOver'));
	o.onMouseOut    = RClass.register(o, new HMouseOut('onMouseOut'));
	o.onMouseDown   = RClass.register(o, new HMouseDown('onMouseDown'));
	o.onMouseUp     = RClass.register(o, new HMouseUp('onMouseUp'));
	o.onClick       = RClass.register(o, new HClick('onClick'));
	o.onDoubleClick = RClass.register(o, new HDoubleClick('onDoubleClick'));
	o.onKeyDown     = RClass.register(o, new HKeyDown('onKeyDown'));
	o.onKeyPress    = RClass.register(o, new HKeyPress('onKeyPress'));
	o.onKeyUp       = RClass.register(o, new HKeyUp('onKeyUp'));
	o.onResize      = RClass.register(o, new HResize('onResize'));
	o.stylePanel    = RClass.register(o, new TStyle('Panel'));
	o.storage       = null;
	o.disabled      = false;
	o.inAction      = null;
	o.hPanel        = null;
	o.oeBuild       = FControl_oeBuild;
	o.oeDesign      = RMethod.empty;
	o.oeShow        = FControl_oeShow;
	o.oeHidden      = FControl_oeHidden;
	o.oeEnable      = FControl_oeEnable;
	o.oeDisable     = FControl_oeDisable;
	o.oeResize      = RMethod.empty;
	o.oeRefresh     = RMethod.empty;
	o.oeAction      = FControl_oeAction;
	o.onBuildPanel  = FControl_onBuildPanel;
	o.onEvent       = FControl_onEvent;
	o.topControl    = FControl_topControl;
	o.build         = FControl_build;
	o.design        = FControl_design;
	o.attachEvent   = FControl_attachEvent;
	o.isShow        = FControl_isShow;
	o.show          = FControl_show;
	o.hide          = FControl_hide;
	o.setVisible    = FControl_setVisible;
	o.enable        = FControl_enable;
	o.disable       = FControl_disable;
	o.setEnable     = FControl_setEnable;
	o.resize        = FControl_resize;
	o.refresh       = FControl_refresh;
	o.setAction     = FControl_setAction;
	o.panel         = FControl_panel;
	o.setPanel      = FControl_setPanel;
	o.setStyle      = FControl_setStyle;
	return o;
}
function FControl_oeBuild(event){
	if(event.isBefore()){
		var o = this;
		o.onBuildPanel();
		var h = o.hPanel;
		h.link = o;
		if(!h.className){
			h.className = o.style('Panel');
		}
		o.attachEvent('onEnter', h);
		o.attachEvent('onLeave', h);
		o.attachEvent('onFocus', h);
		o.attachEvent('onMouseOver', h);
		o.attachEvent('onMouseOut', h);
		o.attachEvent('onMouseDown', h);
		o.attachEvent('onMouseUp', h);
		o.attachEvent('onClick', h);
		o.attachEvent('onDoubleClick', h);
		o.attachEvent('onKeyDown', h);
		o.attachEvent('onKeyPress', h);
		o.attachEvent('onResize', h);
		o.setBounds(o.left, o.top, o.right, o.bottom, true);
		o.setSize(o.width, o.height);
		o.setPads(o.padLeft, o.padTop, o.padRight, o.padBottom, true);
		if(RClass.isClass(o.parent, MContainer)){
			o.parent.appendChild(o);
		}
	}
	return EEventStatus.Stop;
}
function FControl_oeShow(event){
	if(event.isBefore()){
		var o = this;
		var h = this.panel(EPanel.Display);
		if(h){
			h.style.display = 'block';
		}
	}
}
function FControl_oeHidden(event){
	if(event.isAfter()){
		var h = this.panel(EPanel.Display);
		if(h){
			h.style.display = 'none';
		}
	}
}
function FControl_oeEnable(event){
	if(event.isBefore()){
		var h = this.hPanel;
		if(h){
			h.disabled = false;
		}
	}
}
function FControl_oeDisable(event){
	if(event.isAfter()){
		var h = this.hPanel;
		if(h){
			h.disabled = true;
		}
	}
}
function FControl_oeAction(e){
	var o = this;
	o.base.MDisplay.oeAction.call(o, e);
	o.inAction = e.action;
}
function FControl_onBuildPanel(){
	this.hPanel = RBuilder.newSpan();
	return this.hPanel;
}
function FControl_onEvent(event){
	var o = this;
	var status = o.base.FComponent.onEvent.call(o, event);
	if(EEventStatus.Continue == status){
		switch(event.code){
			case EEvent.Build:
				status = o.oeBuild(event);
				break;
			case EEvent.Design:
				status = o.oeDesign(event);
				break;
			case EEvent.Show:
				status = o.oeShow(event);
				break;
			case EEvent.Hidden:
				status = o.oeHidden(event);
				break;
			case EEvent.Enable:
				status = o.oeEnable(event);
				break;
			case EEvent.Disable:
				status = o.oeDisable(event);
				break;
			case EEvent.Resize:
				status = o.oeResize(event);
				break;
			case EEvent.Refresh:
				status = o.oeRefresh(event);
				break;
			case EEvent.Action:
				status = o.oeAction(event);
				break;
		}
	}
	return status ? status : EEventStatus.Continue;
}
function FControl_onEnter(){
	var o = this;
	RConsole.find(FFocusConsole).enter(o);
	if(o.hint){
		window.status = o.hint;
	}
}
function FControl_onLeave(){
	var o = this;
	RConsole.find(FFocusConsole).leave(o);
	if(o.hint){
		window.status = '';
	}
}
function FControl_topControl(c){
	var o = this;
	if(c){
		while(o.parent){
			if(RClass.isClass(o.parent, c)){
				return o.parent;
			}
			o = o.parent;
		}
		if(!RClass.isClass(o, c)){
			return null;
		}
	}else{
		while(o.parent){
			if(!RClass.isClass(o.parent, FControl)){
				break;
			}
			o = o.parent;
		}
	}
	return o;
}
function FControl_build(h){
	var o = this;
	o.event = REvent.nvl(o.event, o, EEvent.Build);
	o.process(o.event);
	if(h){
		o.setPanel(h);
	}
}
function FControl_attachEvent(n, h, m){
	RControl.attachEvent(this, n, h, m);
}
function FControl_design(mode, flag){
	var o = this;
	RConsole.find(FDesignConsole).setFlag(mode, flag, o);
	o.event = REvent.nvl(o.event, o, EEvent.Design);
	o.event.mode = mode;
	o.event.flag = flag;
	o.process(o.event);
}
function FControl_resize(){
	var o = this;
	o.event = REvent.nvl(o.event, o, EEvent.Resize);
	o.process(o.event);
}
function FControl_isShow(){
	var h = this.panel(EPanel.Display);
	return h ? (h.style.display == 'block') : false;
}
function FControl_show(){
	var o = this;
	if(!o.isShow()){
		o.setVisible(true);
	}
}
function FControl_hide(){
	var o = this;
	if(o.isShow()){
		o.setVisible(false);
	}
}
function FControl_setVisible(v){
   var o = this;
   if(v){
      o.event = REvent.nvl(o.event, o, EEvent.Show);
      o.process(o.event);
   }else{
      o.event = REvent.nvl(o.event, o, EEvent.Hidden);
      o.process(o.event);
   }
}
function FControl_enable(v){
   if(this.disabled){
      this.setEnable(true);
   }
}
function FControl_disable(){
   if(!this.disabled){
      this.setEnable(false);
   }
}
function FControl_setEnable(v){
   var o = this;
   o.disabled = !v;
   if(v){
      o.event = REvent.nvl(o.event, o, EEvent.Enable);
      o.process(o.event);
   }else{
      o.event = REvent.nvl(o.event, o, EEvent.Disable);
      o.process(o.event);
   }
}
function FControl_resize(){
	var o = this;
	o.event = REvent.nvl(o.event, o, EEvent.Resize);
	o.process(o.event);
}
function FControl_refresh(type){
	var o = this;
	o.event = REvent.nvl(o.event, o, EEvent.Refresh);
	o.event.type = type;
	o.process(o.event);
}
function FControl_setAction(action, mode, flag, params){
	var o = this;
	var e = o.event = REvent.nvl(o.event, o, EEvent.Action);
	e.action = action;
	e.mode = mode;
	e.flag = flag;
	e.params = params;
	o.process(e);
}
function FControl_panel(type){
	return this.hPanel;
}
function FControl_setPanel(h){
	var o = this;
	o.hParent = h;
	if(h && o.hPanel){
		o.hParent.appendChild(o.hPanel);
	}
}
function FControl_setStyle(h, n){
	if(h){
		h.className = this.style(n);
	}
}
function FDataset(o){
	o = RClass.inherits(this, o, FComponent);
	o.service    = null;
	o.position   = 0;
	o.page       = 0;
	o.pageSize   = 20;
	o.pageCount  = 1;
	o.total      = null;
	o.dataset    = new TDataset();
	o.create     = FDataset_create;
	o.count      = FDataset_count;
	o.row        = FDataset_row;
	o.current    = FDataset_current;
	o.isChanged  = FDataset_isChanged;
	o.get        = FDataset_get;
	o.set        = FDataset_set;
	o.move       = FDataset_move;
	o.moveToRow  = FDataset_moveToRow;
	o.find       = FDataset_find;
	o.loadNode   = FDataset_loadNode;
	o.dump       = FDataset_dump;
	return o;
}
function FDataset_create(c){
	return this.dataset.create(c);
}
function FDataset_count(){
	return this.dataset.count;
}
function FDataset_row(n){
	return this.dataset.get(n);
}
function FDataset_current(){
	return this.row(this.position);
}
function FDataset_isChanged(){
	var o = this;
	var d = o.dataset;
	for(var n=0; n<d.count; n++){
		var r = d.get(n);
		if(r && r.isSave()){
			return true;
		}
	}
	return false;
}
function FDataset_get(n){
	var r = this.current();
	return r ? r.get(n) : '';
}
function FDataset_set(n, v){
	var r = this.current();
	if(r){
		r.set(n, v);
	}
}
function FDataset_move(p){
	this.position = p;
}
function FDataset_moveToRow(row){
	var p = this.dataset.indexOf(row);
	if(-1 != p){
		this.position = p;
	}
}
function FDataset_find(){
	return this.dataset.findByArgs(arguments);
}
function FDataset_loadNode(config){
	if(config && config.nodes){
		var nodes = config.nodes;
		for(var n=0; n<nodes.count; n++){
			var node = nodes.get(n);
			if(node && node.isName('Row')){
				var row = this.dataset.create();
				row.loadNode(node);
				row.store();
			}
		}
	}
}
function FDataset_dump(s){
	var o = this;
	s = RStr.nvlStr(s);
	s.appendLine(RClass.dump(o));
	o.dataset.dump(s);
	return s;
}
function FDropEditor(o){
	o = RClass.inherits(this, o, FEditor);
	o.onDropMouseDown = RClass.register(o, new HMouseDown('onDropMouseDown'));
	o.onDropMouseUp   = RClass.register(o, new HMouseUp('onDropMouseUp'));
	o.styleDrop      = RClass.register(o, new TStyle('Drop'));
	o.styleDropPanel = RClass.register(o, new TStyle('DropPanel'));
	o.MinWidth    = 0;
	o.MinHeight   = 0;
	o.hEditor     = null;
	o.oeBuild     = FDropEditor_oeBuild;
	o.onBlur      = FDropEditor_onBlur;
	o.linkPanel   = FDropEditor_linkPanel;
	o.endEdit     = FDropEditor_endEdit;
	return o;
}
function FDropEditor_oeBuild(event){
	var o = this;
	var r = o.base.FEditor.oeBuild.call(o, event);
	o.attachEvent('onKeyDown', o.hEdit);
	o.attachEvent('onKeyPress', o.hEdit);
	o.attachEvent('onKeyUp', o.hEdit);
	return r;
}
function FDropEditor_onBlur(){
	this.endEdit();
}
function FDropEditor_linkPanel(hPanel, hEdit){
	var o = this;
	RLog.debug(o, 'link Panel (panel={0}, edit={1})', RClass.dump(hPanel), RClass.dump(hEdit));
	RHtml.toRect(o.rect, hPanel);
	o.rect.left -= 3;
	o.rect.top -= 3;
	RHtml.setPixelRect(o.hPanel, o.rect);
	var width = hEdit.offsetWidth;
	if(o.hEdit){
		o.hEdit.style.width = width;
		o.hEdit.style.backgroundColor = EColor.Edit;
	}
	if(o.hEditor){
		o.hEditor.style.width = Math.max(width, o.MinWidth);
	}
}
function FDropEditor_endEdit(){
	var o = this;
	o.base.FEditor.endEdit.call(o);
	o.isSkipBlur = true;
}
function FEditor(o){
	o = RClass.inherits(this, o, FControl, MFocus);
	o.onEditKeyDown  = RClass.register(o, new HKeyDown('onEditKeyDown'));
	o.onEditKeyPress = RClass.register(o, new HKeyPress('onEditKeyPress'));
	o.onEditKeyUp    = RClass.register(o, new HKeyUp('onEditKeyUp'));
	o.onEditChange   = RClass.register(o, new HChange('onEditChange'));
	o.styleEdit      = RClass.register(o, new TStyle('Edit'));
	o.attributes     = new TMap();
	o.rect           = new TRect();
	o.inEdit         = false;
	o.changed        = false;
	o.dataValue      = null;
	o.dataOrigin     = null;
	o.editable       = null;
	o.editStatus     = EEditStatus.Blur;
	o.editConsole    = null;
	o.hEdit          = null;
	o.hForm          = null;
	o.oeBuild        = FEditor_oeBuild;
	o.onBuildEdit    = RMethod.empty;
	o.onBuildPanel   = FEditor_onBuildPanel;
	o.onEditBegin    = FEditor_onEditBegin;
	o.onEditChanged  = FEditor_onEditChanged;
	o.onEditEnd      = FEditor_onEditEnd;
	o.doBlur         = FEditor_doBlur;
	o.construct      = FEditor_construct;
	o.panel          = FEditor_panel;
	o.get            = FEditor_get;
	o.set            = FEditor_set;
	o.show           = FEditor_show;
	o.value          = FEditor_value;
	o.setValue       = FEditor_setValue;
	o.linkPanel      = RMethod.virtual(o, 'linkPanel');
	o.setEditStyle   = FEditor_setEditStyle;
	o.endEdit        = FEditor_endEdit;
	o.blur           = FEditor_blur;
	return o;
}
function FEditor_oeBuild(event){
	var o = this;
	o.base.FControl.oeBuild.call(o, event);
	o.onBuildEdit();
	o.hPanel.appendChild(o.hForm);
	o.hPanel.style.zIndex = ELayer.Editor;
	o.setVisible(false);
	return EEventStatus.Stop;
}
function FEditor_onBuildPanel(){
	this.hPanel = RBuilder.append(null, 'SPAN');
}
function FEditor_onEditBegin(){
	var o = this;
	RLog.debug(o, 'Edit begin');
	o.inEdit = true;
	o.editable.onEditBegin(o);
	o.setEditStyle(EStyle.Edit);
}
function FEditor_onEditChanged(){
	var o = this;
	RLog.debug(o, 'Edit changed');
	var g = o.storage = RObj.nvlObj(o.storage);
	if(g.value == o.value()){
		if(o.changed){
			o.editable.onEditChanged(o, false);
			o.changed = false;
		}
	}else{
		if(!o.changed){
			o.editable.onEditChanged(o, true);
			o.changed = true;
		}
	}
}
function FEditor_onEditEnd(){
	var o = this;
	var t = o.editable;
	RLog.debug(o, 'Edit end (editable={0}, status={1})', RClass.dump(t), REnum.decode(EEditStatus, o.editStatus));
	if(t){
		t.onEditEnd(o);
		var ec = RConsole.find(FEventConsole);
		if(EEditStatus.Cancel == o.editStatus){
			ec.add(t, t.focus);
		}else if(EEditStatus.Ok == o.editStatus){
			ec.add(t, t.focus);
		}
	}
	o.editable = null;
	o.inEdit = false;
}
function FEditor_doBlur(){
	this.onEditEnd();
	this.hide();
}
function FEditor_construct(){
	var o = this;
	o.base.FControl.construct.call(o);
	o.editConsole = RConsole.find(FEditConsole);
}
function FEditor_panel(type){
	var o = this;
	if(EPanel.Edit == type){
		return o.hEdit;
	}else if(EPanel.Focus == type){
		return o.hEdit;
	}
	return o.base.FControl.panel.call(o, type);
}
function FEditor_get(name){
	return this.attributes.get(name);
}
function FEditor_set(name, value){
	this.attributes.set(name, value)
}
function FEditor_show(){
	var o = this;
	o.base.FControl.show.call(o);
	o.editStatus = EEditStatus.Blur;
	o.focus();
}
function FEditor_setEditStyle(style){
	var o = this;
	var h = o.panel(EPanel.Edit);
	switch(style){
		case EStyle.Valid:
			h.style.backgroundColor = EColor.Valid;
			break;
		case EStyle.Edit:
			h.style.backgroundColor = EColor.Edit;
			h.disabled = false;
			break;
		case EStyle.Readonly:
			h.style.backgroundColor = EColor.Readonly;
			h.disabled = true;
			break;
	}
}
function FEditor_value(){
	return this.dataValue;
}
function FEditor_setValue(v){
	this.dataValue = v;
	this.dataOrigin = v;
}
function FEditor_endEdit(){
	this.onEditEnd();
	this.hide();
}
function FEditor_blur(){
	var o = this;
	o.onEditEnd();
	o.hide();
}
function FHoverEditor(o){
	o = RClass.inherits(this, o, FEditor, MEvent, MEventHover, MEventFocus);
	o.storedText    = null;
	o.attributes    = new TMap();
	o.editable      = null;
	o.editConsole   = null;
	o.changed       = false;
	o.rect          = new TRect();
	o.hParent       = null;
	o.oeBuild       = FHoverEditor_oeBuild;
	o.onEnter       = FHoverEditor_onEnter;
	o.onLeave       = FHoverEditor_onLeave;
	o.onFocus       = FHoverEditor_onFocus;
	o.onBlur        = FHoverEditor_onBlur;
	o.text          = RMethod.virtual(o, 'text');
	o.setText       = RMethod.virtual(o, 'setText');
	o.canEnter      = FHoverEditor_canEnter;
	o.canEdit       = FHoverEditor_canEdit;
	o.linkPanel     = FHoverEditor_linkPanel;
	o.unlinkPanel   = FHoverEditor_unlinkPanel;
	return o;
}
function FHoverEditor_oeBuild(event){
	var o = this;
	o.base.FControl.oeBuild.call(o, event);
	o.onBuildEdit();
	o.hPanel.appendChild(o.hForm);
	return EEventStatus.Stop;
}
function FHoverEditor_onEnter(){
	RLog.debug(this, 'Enter');
	this.setEditStyle(EStyle.Hover);
}
function FHoverEditor_onLeave(){
	var o = this;
	RLog.debug(o, 'Leave (hover={1} focus={2})', o.isHover, o.isFocus);
	if(!o.isFocus){
		o.editConsole.leave();
		o.unlinkPanel();
	}
}
function FHoverEditor_onFocus(){
	var o = this;
	RLog.debug(o, 'Focus');
	o.editConsole.focus(o);
}
function FHoverEditor_onBlur(){
	var o = this;
	RLog.debug(o, 'Blur');
	o.editConsole.blur(o);
	o.unlinkPanel();
}
function FHoverEditor_canEnter(){
}
function FHoverEditor_canEdit(){
}
function FHoverEditor_linkPanel(hPanel, hObj){
	var o = this;
	if(o.hParent != hPanel){
		o.hParent = hPanel;
		hPanel.appendChild(o.hPanel);
	}
	o.hPanel.style.display = 'block';
}
function FHoverEditor_unlinkPanel(){
	this.hPanel.style.display = 'none';
}
function FScrollContainer(o){
	o = RClass.inherits(this, o, FControl);
	o.hParent    = null;
	o.hPanel     = null;
	o.controls   = null;
	o.top        = null;
	o.left       = null;
	o.width      = null;
	o.height     = null;
	o.rect       = new TRect();
	o.oeBuild    = null;
	o.oeRefresh  = null;
	o.oeResize   = null;
	o.oeShow     = FScrollContainer_oeShow;
	o.oeHidden   = FScrollContainer_oeHidden;
	o.oeDisable  = FScrollContainer_oeDisable;
	o.oeEnable   = FScrollContainer_oeEnable;
	o.onEvent    = FScrollContainer_onEvent;
	o.topControl = FScrollContainer_topControl;
	o.push       = FScrollContainer_push;
	o.build      = FScrollContainer_build;
	o.show       = FScrollContainer_show;
	o.hide       = FScrollContainer_hide;
	o.enable     = FScrollContainer_enable;
	o.disable    = FScrollContainer_disable;
	o.refresh    = FScrollContainer_refresh;
	o.release    = FScrollContainer_release;
	o.style      = FScrollContainer_style;
	o.panel      = FScrollContainer_panel;
	o.setPanel   = FScrollContainer_setPanel;
	return o;
}
function FScrollContainer_oeShow(event){
	if(event.isBefore()){
		if(this.hPanel){
			this.hPanel.style.display = 'block';
		}
	}
}
function FScrollContainer_oeHidden(event){
	if(event.isAfter()){
		if(this.hPanel){
			this.hPanel.style.display = 'none';
		}
	}
}
function FScrollContainer_oeEnable(event){
	if(event.isBefore()){
		var h = this.hPanel;
		if(h){
			h.disabled = false;
			h.style.cursor = 'auto';
		}
	}
}
function FScrollContainer_oeDisable(event){
	if(event.isAfter()){
		var h = this.hPanel;
		if(h){
			h.disabled = true;
			h.style.cursor = 'wait';
		}
	}
}
function FScrollContainer_onEvent(event){
	var status = this.base.FComponent.onEvent.call(this, event);
	if(EEventStatus.Continue == status){
		switch(event.code){
			case EEvent.Build:
				if(this.oeBuild){
					status = this.oeBuild(event);
				}
				break;
			case EEvent.Refresh:
				if(this.oeRefresh){
					status = this.oeRefresh(event);
				}
				break;
			case EEvent.Resize:
				if(this.oeResize){
					status = this.oeResize(event);
				}
				break;
			case EEvent.Show:
				status = this.oeShow(event);
				break;
			case EEvent.Hidden:
				status = this.oeHidden(event);
				break;
			case EEvent.Disable:
				status = this.oeDisable(event);
				break;
			case EEvent.Enable:
				status = this.oeEnable(event);
				break;
		}
	}
	return status ? status : EEventStatus.Continue;
}
function FScrollContainer_topControl(){
	var o = this;
	while(o.parent != null){
		if(o.parent.base && o.parent.base.FScrollContainer){
			o = o.parent;
		}else{
			break;
		}
	}
	return o;
}
function FScrollContainer_push(ctl){
	this.base.FComponent.push.call(this, ctl);
	if(ctl && ctl.base && ctl.base.FScrollContainer){
		if(!this.controls){
			this.controls = new TList();
		}
		this.controls.push(ctl);
	}
}
function FScrollContainer_build(builder){
	var event = new TEvent(this, EEvent.Build);
	event.builder = builder;
	this.process(event);
}
function FScrollContainer_resize(){
	this.process(new TEvent(this, EEvent.Resize));
}
function FScrollContainer_show(){
	this.process(new TEvent(this, EEvent.Show));
}
function FScrollContainer_hide(){
	this.process(new TEvent(this, EEvent.Hidden));
}
function FScrollContainer_enable(){
	this.process(new TEvent(this, EEvent.Enable));
}
function FScrollContainer_disable(){
	this.process(new TEvent(this, EEvent.Disable));
}
function FScrollContainer_refresh(){
	this.process(new TEvent(this, EEvent.Refresh));
}
function FScrollContainer_release(){
	this.hPanel = null;
	this.controls = null;
	this.base.FComponent.release.call(this);
}
function FScrollContainer_style(name){
	return RClass.name(this) + '_' + name;
}
function FScrollContainer_panel(){
	return o.hPanel;
}
function FScrollContainer_setPanel(){
}
function FSplitableControl(){
   IObject.extendClass(this, FControl);
   this.className = 'FSplitableControl';
   this.htmlSplit = null;
   this.buildSplit = ctb_sbc_buildSplit;
   this.appendSplit = ctb_sbc_appendSplit;
   return this;
}
function ctb_sbc_buildSplit(){
   this.htmlSplit = this.createElement('TABLE');
   this.htmlSplit.width = '100%';
   this.htmlSplit.height = '100%';
   this.htmlSplit.border = 0;
   this.htmlSplit.cellSpacing = 0;
   this.htmlSplit.cellPadding = 0;
   this.htmlParent.appendChild(this.htmlSplit);
}
function ctb_sbc_appendSplit(oPanel){
   var oCell = this.htmlSplit.insertRow().insertCell();
   oCell.appendChild(oPanel);
   return oCell;
}
function HBlur(n, m){
	var o = this;
	o.annotation = EAnnotation.Event;
	o.type       = 'blur';
	o.handle     = 'onblur';
	o.name       = n;
	o.method     = m;
	o.source     = null;
	o.hSource    = null;
	o.attach     = HBlur_attach;
	return o;
}
function HBlur_attach(e){
	var o = this;
}
function HChange(n){
	var o = this;
	o.annotation = EAnnotation.Event;
	o.type       = 'change';
	o.handle     = 'onchange';
	o.name       = n;
	o.source     = null;
	o.hSource    = null;
	o.attach     = HChange_attach;
	return o;
}
function HChange_attach(e){
	var o = this;
}
function HClick(n){
	var o = this;
	o.annotation = EAnnotation.Event;
	o.type       = 'click';
	o.handle     = 'onclick';
	o.name       = n;
	o.source     = null;
	o.hSource    = null;
	o.attach     = HClick_attach;
	return o;
}
function HClick_attach(e){
	var o = this;
}
function HDoubleClick(n){
	var o = this;
	o.annotation = EAnnotation.Event;
	o.type       = 'dblclick';
	o.handle     = 'ondblclick';
	o.name       = n;
	o.source     = null;
	o.hSource    = null;
	o.attach     = HDoubleClick_attach;
	return o;
}
function HDoubleClick_attach(e){
	var o = this;
}
function HFocus(n, m){
	var o = this;
	o.annotation = EAnnotation.Event;
	o.type       = 'focus';
	o.handle     = 'onfocus';
	o.name       = n;
	o.method     = m;
	o.source     = null;
	o.hSource    = null;
	o.attach     = HFocus_attach;
	return o;
}
function HFocus_attach(e){
	var o = this;
}
function HKeyDown(n){
	var o = this;
	o.annotation = EAnnotation.Event;
	o.type       = 'keydown';
	o.handle     = 'onkeydown';
	o.name       = n;
	o.source     = null;
	o.hSource    = null;
	o.ctrlKey    = false;
	o.keyCode    = null;
	o.attach     = HKeyDown_attach;
	return o;
}
function HKeyDown_attach(e){
	var o = this;
	o.ctrlKey = e.ctrlKey;
	o.keyCode = e.keyCode;
}
function HKeyPress(n){
	var o = this;
	o.annotation = EAnnotation.Event;
	o.type       = 'keypress';
	o.handle     = 'onkeypress';
	o.name       = n;
	o.source     = null;
	o.hSource    = null;
	o.keyCode    = null;
	o.srcElement = null;
	o.attach     = HKeyPress_attach;
	return o;
}
function HKeyPress_attach(e){
	var o = this;
	o.keyCode = e.keyCode;
	o.srcElement = e.srcElement;
}
function HKeyUp(n){
	var o = this;
	o.annotation = EAnnotation.Event;
	o.type       = 'keyup';
	o.handle     = 'onkeyup';
	o.name       = n;
	o.source     = null;
	o.hSource    = null;
	o.keyCode    = null;
	o.srcElement = null;
	o.attach     = HKeyUp_attach;
	return o;
}
function HKeyUp_attach(e){
	var o = this;
	o.keyCode = e.keyCode;
	o.srcElement = e.srcElement;
}
function HMouseDown(n){
	var o = this;
	o.annotation = EAnnotation.Event;
	o.type       = 'mousedown';
	o.handle     = 'onmousedown';
	o.name       = n;
	o.source     = null;
	o.hSource    = null;
	o.keyAlt     = null;
	o.keyAlt     = null;
	o.x          = null;
	o.y          = null;
   o.srcElement = null;
	o.attach     = HMouseDown_attach;
	return o;
}
function HMouseDown_attach(e){
	var o = this;
	o.keyAlt = e.altKey;
	o.keyCtrl = e.ctrlKey;
	o.x = e.x;
	o.y = e.y;
   o.srcElement = e.srcElement;
}
function HMouseEnter(n){
	var o = this;
	o.annotation = EAnnotation.Event;
	o.type       = 'mouseenter';
	o.handle     = 'onmouseenter';
	o.name       = n;
	o.source     = null;
	o.hSource    = null;
	o.srcElement = null;
	o.attach     = HMouseEnter_attach;
	return o;
}
function HMouseEnter_attach(e){
	var o = this;
	o.srcElement = e.srcElement;
}
function HMouseLeave(n){
	var o = this;
	o.annotation = EAnnotation.Event;
	o.type       = 'mouseleave';
	o.handle     = 'onmouseleave';
	o.name       = n;
	o.source     = null;
	o.hSource    = null;
	o.attach     = HMouseLeave_attach;
	return o;
}
function HMouseLeave_attach(e){
	var o = this;
}
function HMouseOut(n){
	var o = this;
	o.annotation = EAnnotation.Event;
	o.type       = 'mouseout';
	o.handle     = 'onmouseout';
	o.name       = n;
	o.source     = null;
	o.hSource    = null;
	o.keyAlt     = null;
	o.keyAlt     = null;
	o.x          = null;
	o.y          = null;
   o.srcElement = null;
	o.attach     = HMouseOut_attach;
	return o;
}
function HMouseOut_attach(e){
	var o = this;
	o.keyAlt = e.altKey;
	o.keyCtrl = e.ctrlKey;
	o.x = e.x;
	o.y = e.y;
   o.srcElement = e.srcElement;
}
function HMouseOver(n){
	var o = this;
	o.annotation = EAnnotation.Event;
	o.type       = 'mouseover';
	o.handle     = 'onmouseover';
	o.name       = n;
	o.source     = null;
	o.hSource    = null;
	o.keyAlt     = null;
	o.keyAlt     = null;
	o.x          = null;
	o.y          = null;
	o.srcElement = null;
	o.attach     = HMouseOver_attach;
	return o;
}
function HMouseOver_attach(e){
	var o = this;
	o.keyAlt = e.altKey;
	o.keyCtrl = e.ctrlKey;
	o.x = e.x;
	o.y = e.y;
   o.srcElement = e.srcElement;
}
function HMouseUp(n){
	var o = this;
	o.annotation = EAnnotation.Event;
	o.type       = 'mouseup';
	o.handle     = 'onmouseup';
	o.name       = n;
	o.source     = null;
	o.hSource    = null;
	o.keyAlt     = null;
	o.keyAlt     = null;
	o.x          = null;
	o.y          = null;
	o.srcElement = null;
	o.attach     = HMouseUp_attach;
	return o;
}
function HMouseUp_attach(e){
	var o = this;
	o.keyAlt = e.altKey;
	o.keyCtrl = e.ctrlKey;
	o.x = e.x;
	o.y = e.y;
	o.srcElement = e.srcElement;
}
function HMouseWheel(n){
	var o = this;
	o.annotation = EAnnotation.Event;
	o.type       = 'mousewheel';
	o.handle     = 'onmousewheel';
	o.name       = n;
	o.source     = null;
	o.hSource    = null;
	o.keyAlt     = null;
	o.keyAlt     = null;
	o.x          = null;
	o.y          = null;
	o.wheelDelta = null;
	o.attach     = HMouseWheel_attach;
	return o;
}
function HMouseWheel_attach(e){
	var o = this;
	o.keyAlt = e.altKey;
	o.keyCtrl = e.ctrlKey;
	o.wheelDelta = e.wheelDelta;
	o.x = e.x;
	o.y = e.y;
}
function HResize(n){
	var o = this;
	o.annotation = EAnnotation.Event;
	o.type       = 'resize';
	o.handle     = 'onresize';
	o.name       = n;
	o.source     = null;
	o.hSource    = null;
	o.x          = null;
	o.y          = null;
	o.attach     = HResize_attach;
	return o;
}
function HResize_attach(e){
	var o = this;
	o.x = e.x;
	o.y = e.y;
}
function MAction(o){
	o = RClass.inherits(this, o);
	o.doAction = MAction_doAction
	return o;
	}
function MAction_doAction(name){
	var o = this.components.get(name);
	if(RClass.isClass(o, MInvoke)){
		o.invoke(this);
	}
}
function MClone(o){
	o = RClass.inherits(this, o);
	o.assign = MClone_assign;
	o.clone  = MClone_clone;
	return o;
}
function MClone_assign(c, t){
	var o = this;
	if(t && EAssign.All == t){
		alert('EAssign.All');
	}else if(t && EAssign.Clone == t){
		alert('EAssign.Clone');
	}else{
		var ps = RClass.find(o.constructor).getAnnotations(EAnnotation.Property);
		for(var n in ps){
			var p = ps[n];
			o[p.name] = c[p.name];
		}
	}
}
function MClone_clone(){
	var o = this;
	var r = RClass.create(o.constructor);
	for(var n in o){
		v = o[n];
		if(null != v){
			var t = v.constructor;
			if(t == Boolean || t == Date || t == String || t == Number){
				r[n] = v;
			}
		}
	}
	return r;
}
function MConfig(o){
	o = RClass.inherits(this, o);
	o.loadConfig = MConfig_loadConfig;
	o.saveConfig = MConfig_saveConfig;
	return o;
}
function MConfig_loadConfig(c){
	var o = this;
	var ps = RClass.find(o.constructor).getAnnotations(EAnnotation.Property);
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
function MConfig_saveConfig(c){
	var o = this;
	var ps = RClass.find(o.constructor).getAnnotations(EAnnotation.Property);
	for(var n in ps){
		ps[n].save(o, c);
	}
}
function MContainer(o){
	o = RClass.inherits(this, o);
	o.controls      = new TMap();
	o.appendChild   = RMethod.empty;
	return o;
}
function MDataset(o){
	o = RClass.inherits(this, o);
	o.dsName          = RClass.register(o, new TPtyStr('dsName', null, 'dataset'));
	o.dsService       = RClass.register(o, new TPtyStr('dsService', EService.WebDataset, 'service'));
	o.dsPageSize      = RClass.register(o, new TPtyInt('dsPageSize', 20, 'page_size'));
	o.editSearch      = RClass.register(o, new TPtyBoolSet('editSearch', 'editAccess', EAction.Search, true));
	o.editInsert      = RClass.register(o, new TPtyBoolSet('editInsert', 'editAccess', EAction.Insert, true));
	o.editUpdate      = RClass.register(o, new TPtyBoolSet('editUpdate', 'editAccess', EAction.Update, true));
	o.editDelete      = RClass.register(o, new TPtyBoolSet('editDelete', 'editAccess', EAction.Delete, true));
	o.insertAction    = RClass.register(o, new TPtyStr('insertAction', 'insert'));
	o.updateAction    = RClass.register(o, new TPtyStr('updateAction', 'update'));
	o.deleteAction    = RClass.register(o, new TPtyStr('deleteAction', 'delete'));
	o.dsStore         = null;
	o.dsSearchBox     = null;
	o.dsFormSearch    = new TAttrs();
	o.dsFormOrder     = new TAttrs();
	o.dsSearchConfig  = null;
	o.dsSearchWindow  = null;
	o.onLoadDataset   = RMethod.virtual(o, 'onLoadDataset');
	o.testStatus      = MDataset_testStatus;
	o.dsIsChanged     = MDataset_dsIsChanged;
	o.loadDataset     = MDataset_loadDataset;
	o.loadDatasets    = MDataset_loadDatasets;
	o.dsCount         = MDataset_dsCount;
	o.dsCurrent       = MDataset_dsCurrent;
	o.dsMove          = MDataset_dsMove;
	o.dsMovePage      = MDataset_dsMovePage;
	o.dsGet           = MDataset_dsGet;
	o.dsSet           = MDataset_dsSet;
	o.dsInsert        = MDataset_dsInsert;
	o.dsUpdate        = MDataset_dsUpdate;
	o.dsDelete        = MDataset_dsDelete;
	o.onDsFetchBegin  = RMethod.empty;
	o.onDsFetchEnd    = RMethod.virtual(o, 'onDsFetchEnd');
	o.onDsUpdateBegin = MDataset_onDsUpdateBegin;
	o.onDsUpdateEnd   = RMethod.empty;
	o.onDsActionBegin = MDataset_onDsActionBegin;
	o.onDsActionEnd   = MDataset_onDsActionEnd;
	o.dsFetch         = MDataset_dsFetch;
	o.dsSearch        = MDataset_dsSearch;
	o.dsRefresh       = MDataset_dsRefresh;
	o.dsLoadRowNode   = MDataset_dsLoadRowNode;
	o.dsRemoveRow     = MDataset_dsRemoveRow;
	o.doSearch        = MDataset_doSearch;
	return o;
}
function MDataset_testStatus(t){
	var o = this;
	if(EDataAction.Fetch == t){
		if(EAction.Insert == o.inAction || EAction.Update == o.inAction || EAction.Delete == o.inAction){
			return false;
		}
		return true;
	}else if(EDataAction.Search== t){
		if(EAction.Insert == o.inAction || EAction.Update == o.inAction || EAction.Delete == o.inAction){
			return false;
		}
		return o.editSearch;
	}else if(EDataAction.Lov == t){
		var fc = RConsole.find(FFocusConsole).focusControl;
		if(RClass.isClass(fc, MListView)){
			return fc.canListView();
		}
		return false;
	}else if(EDataAction.Insert == t){
		if(EAction.Insert == o.inAction || EAction.Delete == o.inAction){
			return false;
		}
		return o.editInsert;
	}else if(EDataAction.Update == t){
		return true;
	}else if(EDataAction.Delete == t){
		if(EAction.Insert == o.inAction || EAction.Delete == o.inAction){
			return false;
		}
		return o.editDelete;
	}else{
		return false;
	}
	return true;
}
function MDataset_dsIsChanged(){
	var ds = this.dsStore;
	return ds ? ds.isChanged() : false;
}
function MDataset_loadDataset(ds){
	var o = this;
	var name = ds.name;
	if(RXml.isNode(ds)){
		name = ds.get('name');
	}
	var s = o.allComponent(MDataset);
	for(var i=0; i<s.count; i++){
		var dc = s.get(i);
		if(dc.name == name){
			if(RXml.isNode(ds)){
				if(dc.dsStore){
					dc.dsStore.loadNode(ds);
				}else{
					dc.dsStore = RDataset.make(ds);
				}
			}else{
				dc.dsStore = ds;
			}
			return dc.onLoadDataset(dc.dsStore);
		}
	}
	RMsg.fatal(o, null, 'Load dataset failed. (name={0})', name);
}
function MDataset_loadDatasets(xdss){
	if(xdss && xdss.nodes){
		for(var n=0; n<xdss.nodes.count; n++){
			var xds = xdss.node(n);
			if(RDataset.DATASET == xds.name){
				this.loadDataset(xds);
			}
		}
	}
}
function MDataset_dsCount(){
	return this.dsStore ? this.dsStore.count : 0;
}
function MDataset_dsCurrent(){
	return this.dsStore ? this.dsStore.current() : 0;
}
function MDataset_dsMove(p){
	var o = this;
	var ds = o.dsStore;
	if(null == p && !ds){
		return;
	}
	if(!RInt.isInt(p)){
		if(EDataAction.First == p){
			ds.moveFirst();
		}else if(EDataAction.Prior == p){
			ds.movePrior();
		}else if(EDataAction.Next == p){
			ds.moveNext();
		}else if(EDataAction.Last == p){
			ds.moveLast();
		}else{
			RMsg.fatal(o, null, 'Unknown position (postion={0})', p);
		}
	}else{
		ds.move(p);
	}
	if(o.isClass(MValue)){
		o.loadValue(ds.current());
	}
}
function MDataset_dsMovePage(p){
	var o = this;
	var ds = o.dsStore;
	if(!RInt.isInt(p)){
		if(EDataAction.First == p){
			ds.movePageFirst();
		}else if(EDataAction.Prior == p){
			ds.movePagePrior();
		}else if(EDataAction.Next == p){
			ds.movePageNext();
		}else if(EDataAction.Last == p){
			ds.movePageLast();
		}else{
			RMsg.fatal(o, null, 'Unknown page (page={0})', p);
		}
	}else{
		ds.movePage(p);
	}
	if(!ds.hasPage()){
		RConsole.find(FDatasetConsole).fetch(o, EDataAction.Fetch);
	}else{
		o.onLoadDataset(ds);
	}
}
function MDataset_dsGet(n){
	return this.dsStore ? this.dsStore.get(n) : '';
}
function MDataset_dsSet(n, v){
	if(this.dsStore){
		this.dsStore.set(n, v);
	}
}
function MDataset_dsInsert(){
	var o = this;
	o.setAction(EAction.Insert);
	var row = o.dsStore.create();
	row.doInsert();
	o.dsStore.moveToRow(row);
	if(o.isClass(MValue)){
		o.saveValue(row);
	}
}
function MDataset_dsUpdate(){
	var o = this;
	if(EAction.Delete != o.inAction){
		if(!o.dsStore.isChanged()){
			return RMsg.warn(o, RContext.get(MDataset, 'nochange'));
		}
	}
	RConsole.find(FDatasetConsole).update(o);
}
function MDataset_dsDelete(){
	var o = this;
	o.setAction(EAction.Delete);
	var row = o.dsStore.current();
	row.doDelete();
}
function MDataset_onDsUpdateBegin(dn){
	var o = this;
	var ds = o.dsStore;
	var count = ds.count();
	for(var n=0; n<count; n++){
		var r = ds.row(n);
		if(r && r.isSave()){
			r.saveNode(dn.create('Row'));
		}
	}
}
function MDataset_onDsActionBegin(){
}
function MDataset_onDsActionEnd(action, dataset){
	var o = this;
	RLog.debug(o, 'action={1} dataset={2}', action, dataset.dump());
	if(!dataset){
		o.dsStore = RClass.create(FDataset);
	}else{
		o.dsStore = dataset;
	}
	if(EDataAction.Fetch == action){
		o.onDsFetchEnd(dataset);
	}else if(EDataAction.Update == action){
		o.onDsUpdateEnd(dataset);
	}
}
function MDataset_dsFetch(){
	var o = this;
	RConsole.find(FDatasetConsole).fetch(o, EDataAction.Fetch);
	o.setAction(EAction.Update);
}
function MDataset_dsSearch(){
	var o = this;
	RConsole.find(FDatasetConsole).fetch(o, EDataAction.Search);
	o.setAction(EAction.Update);
}
function MDataset_doSearch(){
	var o = this;
	var sw = o.dsSearchWindow;
	if(!sw){
		sw = o.dsSearchWindow = RControl.create(FSearchWindow);
		sw.linkDsControl(o);
	}
	sw.show();
}
function MDataset_dsRefresh(){
	if(this.dsService){
		this.dsMove(this.dsPage, true);
	}
}
function MDataset_dsLoadRowNode(node){
	var o = this;
	var dc = o.dsStore;
	if(!dc){
		dc = o.dsStore = RClass.create(FDataset);
	}
	var row = dc.create(node);
	row.doNormal();
	return row;
}
function MDataset_dsRemoveRow(row){
	if(this.dsStore){
		this.dsStore.dataset.removeRow(row);
	}
}
function MDesign(o){
	o = RClass.inherits(this, o);
	o.inDesign        = false;
	o.designStore     = null;
	o.styleDesign     = RClass.register(o, new TStyle('Design'));
	o.styleDesignDrag = RClass.register(o, new TStyle('DesignDrag'));
	o.styleDesignMove = RClass.register(o, new TStyle('DesignMove'));
	o.onDesignEnter   = RClass.register(o, new HMouseEnter('onDesignEnter'), MDesign_onDesignEnter);
	o.onDesignLeave   = RClass.register(o, new HMouseEnter('onDesignLeave'), MDesign_onDesignLeave);
	o.onDesignBegin   = RClass.register(o, new HMouseEnter('onDesignBegin'), MDesign_onDesignBegin);
	o.onDesignEnd     = RClass.register(o, new HMouseEnter('onDesignEnd'),   MDesign_onDesignEnd);
	o.oeDesign        = MDesign_oeDesign;
	o.oeAction        = MDesign_oeAction;
	return o;
}
function MDesign_oeDesign(event){
	if(event.isBefore()){
		switch(event.mode){
			case EDesign.Move:
				var o = this;
				var g = o.storage = RObj.nvlObj(o.storage);
				var h = o.hPanel;
				if(event.flag){
					o.isDesign = true;
					g.className = h.className;
					g.ohMdown = h.onmousedown;
					h.onmousedown = null;
					o.onDesignEnter();
				}else{
					o.isDesign = false;
					h.className = g.className;
					if(g.ohMdown){
						h.onmousedown = g.ohMdown;
					}
				}
				break;
			case EDesign.Border:
				var o = this;
				var g = o.storage = RObj.nvlObj(o.storage);
				var h = o.hPanel;
				if(event.flag){
					g.panelBorder = h.style.border;
					h.style.border = '1 solid red';
				}else{
					h.style.border = g.panelBorder;
				}
				break;
		}
	}
}
function MDesign_oeAction(e){
	if(e.isBefore()){
		if(EAction.Design == e.action){
			var o = this;
			switch(e.mode){
				case EDesign.Move:
					var g = o.storage = RObj.nvlObj(o.storage);
					var h = o.hPanel;
					if(event.flag){
						g.className = h.className;
						g.ohMdown = h.onmousedown;
						o.onDesignEnter();
						alert(o.onDesignEnter);
						h.onmousedown = null;
					}else{
						h.className = g.className;
						o.onDesignLeave();
						if(g.ohMdown){
							h.onmousedown = g.ohMdown;
						}
					}
					break;
				case EDesign.Border:
					var g = o.storage = RObj.nvlObj(o.storage);
					var h = o.hPanel;
					if(event.flag){
						g.panelBorder = h.style.border;
						h.style.border = '1 solid red';
					}else{
						h.style.border = g.panelBorder;
					}
					break;
			}
		}
	}
}
function MDesign_onDesignEnter(){
	var o = this;
	o.hPanel.className = o.style('Design');
}
function MDesign_onDesignLeave(){
}
function MDesign_onDesignBegin(){
	var o = this;
	var g = o.storage = RObj.nvlObj(o.storage);
	g.designStyle = o.hPanel.className;
	g.designLayer = o.hPanel.zIndex;
	o.hPanel.className = o.style('DesignDrag');
	o.inDesign = true;
}
function MDesign_onDesignEnd(){
	var o = this;
	var g = o.storage = RObj.nvlObj(o.storage);
	o.hPanel.className = g.designStyle;
	o.hPanel.zIndex = g.designLayer;
	o.inDesign = false;
}
function MDisplay(o){
	o = RClass.inherits(this, o);
	o.dispAlign  = RClass.register(o, new TPtyStr('dispAlign', EAlign.Left));
	o.dispSearch = RClass.register(o, new TPtyBoolSet('dispSearch', 'dispAccess', EAction.Search, false));
	o.dispPicker = RClass.register(o, new TPtyBoolSet('dispPicker', 'dispAccess', EAction.Picker, false));
	o.dispList   = RClass.register(o, new TPtyBoolSet('dispList',   'dispAccess', EAction.List,   false));
	o.dispInsert = RClass.register(o, new TPtyBoolSet('dispInsert', 'dispAccess', EAction.Insert, false));
	o.dispUpdate = RClass.register(o, new TPtyBoolSet('dispUpdate', 'dispAccess', EAction.Update, false));
	o.dispDelete = RClass.register(o, new TPtyBoolSet('dispDelete', 'dispAccess', EAction.Delete, false));
	o.oeAction   = MDisplay_oeAction;
	return o;
}
function MDisplay_oeAction(e){
	var o = this;
	if(!RClass.isClass(o, MDisplayAble) && e.isAfter()){
		var action = e.action;
		if(EAction.Search == action){
			o.setVisible(o.dispSearch);
		}else if(EAction.Picker == action){
			o.setVisible(o.dispPicker);
		}else if(EAction.List == action){
			o.setVisible(o.dispList);
		}else if(EAction.Insert == action){
			o.setVisible(o.dispInsert);
		}else if(EAction.Update == action){
			o.setVisible(o.dispUpdate);
		}else if(EAction.Delete == action){
			o.setVisible(o.dispDelete);
		}
	}
}
function MDisplayAble(o){
	o = RClass.inherits(this, o);
	return o;
}
function MDragable(o, create){
	o = RClass.inherits(this, o);
	o.isDraging = false;
	o.startDrag = RMethod.virtual(o, 'startDrag');
	o.stopDrag  = RMethod.virtual(o, 'stopDrag');
	return o;
}
function MDropable(o){
	o = RClass.inherits(this, o);
	o.onDropEnter       = RClass.register(o, new HMouseEnter('onDropEnter'));
	o.onDropLeave       = RClass.register(o, new HMouseLeave('onDropLeave'));
	o.onDropClick       = RClass.register(o, new HMouseDown('onDropClick'),         MDropable_onDropClick);
	o.onDropKeyDown     = RClass.register(o, new HKeyPress('onDropKeyDown'),        MDropable_onDropKeyDown);
	o.onDropDoubleClick = RClass.register(o, new HDoubleClick('onDropDoubleClick'), MDropable_onDropDoubleClick);
	o.styleDrop         = RClass.register(o, new TStyle('Drop'));
	o.hDropPanel        = null;
	o.hDrop             = null;
   o.dropIcon          = null;
	o.onBuildDrop       = MDropable_onBuildDrop;
	o.canDrop           = MDropable_canDrop;
	o.drop              = RMethod.virtual(o, 'drop');
	return o;
}
function MDropable_onDropDoubleClick(){
	this.drop();
}
function MDropable_onDropKeyDown(e){
	if(EKey.Down == e.keyCode){
		this.drop();
	}
}
function MDropable_onDropClick(){
	this.drop();
}
function MDropable_onBuildDrop(){
	var o = this;
	var hc = o.hDropPanel;
	if(!hc){
		hc = o.hDropPanel = o.hEditRow.insertCell();
	}
	hc.width = 1;
	var h = o.hDrop = RBuilder.appendIcon(hc, o.dropIcon);
	h.className = o.style('Drop');
	o.attachEvent('onDropEnter', h);
	o.attachEvent('onDropLeave', h);
	o.attachEvent('onDropClick', h);
}
function MDropable_canDrop(){
	var o = this;
	if(o.isClass(MDesign)){
		return !RConsole.find(FDesignConsole).canDesignMove;
	}
	return true;
}
function MEditable(o){
	o = RClass.inherits(this, o);
	o.editWidth         = RClass.register(o, new TPtyStr('editWidth'));
	o.editHeight        = RClass.register(o, new TPtyStr('editHeight'));
	o.editSearch        = RClass.register(o, new TPtyBoolSet('editSearch', 'editAccess', EAction.Search, false));
	o.editInsert        = RClass.register(o, new TPtyBoolSet('editInsert', 'editAccess', EAction.Insert, false));
	o.editUpdate        = RClass.register(o, new TPtyBoolSet('editUpdate', 'editAccess', EAction.Update, false));
	o.editDelete        = RClass.register(o, new TPtyBoolSet('editDelete', 'editAccess', EAction.Delete, false));
	o.editTip           = RClass.register(o, new TPtyStr('editTip'));
	o.validInsert       = RClass.register(o, new TPtyBoolSet('validInsert', 'validAccess', EAction.Insert, false));
	o.validUpdate       = RClass.register(o, new TPtyBoolSet('validUpdate', 'validAccess', EAction.Update, false));
	o.validDelete       = RClass.register(o, new TPtyBoolSet('validDelete', 'validAccess', EAction.Delete, false));
	o.validRequire      = RClass.register(o, new TPtyBool('validRequire', false));
	o.onEditEnter       = RClass.register(o, new HMouseEnter('onEditEnter'), MEditable_onEditEnter);
	o.onEditLeave       = RClass.register(o, new HMouseLeave('onEditLeave'), MEditable_onEditLeave);
	o.onEditMouseOver   = RClass.register(o, new HMouseOver('onEditMouseOver'));
	o.onEditMouseOut    = RClass.register(o, new HMouseOut('onEditMouseOut'));
	o.onEditMouseDown   = RClass.register(o, new HMouseDown('onEditMouseDown'));
	o.onEditMouseUp     = RClass.register(o, new HMouseUp('onEditMouseUp'));
	o.onEditClick       = RClass.register(o, new HClick('onEditClick'));
	o.onEditDoubleClick = RClass.register(o, new HDoubleClick('onEditDoubleClick'));
	o.onEditKeyDown     = RClass.register(o, new HKeyDown('onEditKeyDown'), MEditable_onEditKeyDown);
	o.onEditKeyPress    = RClass.register(o, new HKeyPress('onEditKeyPress'), RMethod.empty);
	o.onEditKeyUp       = RClass.register(o, new HKeyUp('onEditKeyUp'));
	o.onEditBegin       = RMethod.empty;
	o.onEditChanged     = RClass.register(o, new HChange('onEditChanged'), MEditable_onEditChanged);
	o.onEditEnd         = MEditable_onEditEnd;
	o.onLoadValue       = MEditable_onLoadValue;
	o.onSaveValue       = MEditable_onSaveValue;
	o.dsControl         = null;
	o.canEdit           = true;
	o.canValid          = true;
	o.inEditing         = false;
	o.isValid           = true;
	o.displayText       = null;
	o.oeAction          = MEditable_oeAction;
	o.oeRefresh         = MEditable_oeRefresh;
	o.doFocus           = MEditable_doFocus;
	o.doBlur            = MEditable_doBlur;
	o.isTextChanged     = MEditable_isTextChanged;
	o.findDsControl     = MEditable_findDsControl;
	o.storeText         = MEditable_storeText;
	o.formatValue       = RMethod.empty;
	o.validText         = MEditable_validText;
	o.formatText        = MEditable_formatText;
	o.value             = MEditable_value;
	o.setValue          = MEditable_setValue;
	o.text              = RMethod.virtual(o, 'text');
	o.setText           = RMethod.virtual(o, 'setText');
	o.setEditable       = MEditable_setEditable;
	o.setEditStyle      = MEditable_setEditStyle;
	o.focus             = MEditable_focus;
	return o;
}
function MEditable_oeAction(event){
	if(event.isAfter()){
		var o = this;
		var action = event.action;
		if(EAction.Insert == action){
			o.setEditable(o.editInsert);
			o.setValue(RStr.nvl(o.dataDefault));
			o.canValid = o.validInsert;
		}else if(EAction.Update == action){
			o.setEditable(o.editUpdate);
			o.canValid = o.validUpdate;
		}else if(EAction.Delete == action){
			o.setEditable(o.editDelete);
			o.canValid = o.validDelete;
		}
	}
}
function MEditable_oeRefresh(e){
	var o = this;
	if(ERefresh.LoadValue = e.type){
		o.setText(o.formatText(o.dataValue));
	}else if(ERefresh.SaveValue = e.type){
	}
}
function MEditable_onEditEnter(){
	var o = this;
	if(o.canEdit){
		o.setEditStyle(EStyle.EditHover);
	}
}
function MEditable_onEditLeave(){
	var o = this;
	if(!o.inEditing){
		o.setEditStyle(o.canEdit ? EStyle.Edit : EStyle.Readonly);
	}
}
function MEditable_onEditFocus(e){
	RConsole.find(FFocusConsole).focus(this, e);
}
function MEditable_onEditKeyDown(e){
	var o = this;
	if(o.isTextChanged()){
		var text = o.text();
		o.isValid = o.validText(text);
		if(!o.isValid){
			o.setEditStyle(EStyle.Valid);
			o.storeText();
		}
	}
	if(e.ctrlKey && EKey.Enter==e.keyCode && o.editSearch){
		var dc = o.dsControl;
		if(dc){
			if(!o.isValid){
				var sn = new TNode('Search');
				var n = sn.create('Item');
				n.set('name', o.name);
				n.set('data_name', o.dataName);
				n.set('data_value', o.dataValue);
				n.set('search_type', ESearch.Equals);
				n.set('search_order', EOrder.None);
				RConsole.find(FDatasetConsole).fetch(dc, sn);
			}
		}
	}
}
function MEditable_onEditChanged(){
	var o = this;
	if(o.isTextChanged()){
		var text = o.text();
		o.isValid = o.validText(text);
		if(o.isValid){
			RLog.debug(this, 'Edit changed event (text=[{0}]->[{1}])', o.displayText, o.text());
			o.onEditEnd();
		}else{
			o.setEditStyle(EStyle.Valid);
			RLog.debug(this, 'Valid failed (text=[{0}]->[{1}])', o.displayText, text);
			o.storeText();
		}
	}
}
function MEditable_onEditEnd(){
	var o = this;
	var text = o.text();
	var value = text;
	if(!RMethod.isEmpty(o.formatValue)){
		value = o.formatValue(text);
	}
	o.setEditStyle(EStyle.Edit);
	RLog.debug(this, 'Valid success (text=[{1}]->[{2}], value=[{3}], dataset={4})', o.displayText, text, value, o.dsControl);
	if(o.dsControl){
		o.dsControl.dsSet(o.dataName, value);
	}
	o.setValue(value);
	o.storeText();
	return true;
}
function MEditable_onLoadValue(c, t){
	var o = this;
	if(EStore.Name == t){
		o.setValue(c.get(o.name));
	}else if(EStore.DataNvl == t){
		if(c.contains(o.dataName)){
			o.setValue(c.get(o.dataName));
		}
	}else if(EStore.Reset == t){
		o.setValue(RStr.EMPTY);
	}else{
		o.setValue(c.get(o.dataName));
	}
}
function MEditable_onSaveValue(c, t){
	var o = this;
	if(EStore.Name == t){
		c.set(o.name, o.value());
	}else{
		c.set(o.dataName, o.value());
	}
}
function MEditable_doFocus(){
	var o = this;
	if(o.canEdit){
		o.inEditing = true;
		if(o.isValid){
			o.storeText();
			o.setEditStyle(EStyle.Edit);
		}
	}
}
function MEditable_doBlur(){
	var o = this;
	if(o.canEdit){
		if(o.isTextChanged()){
			var text = o.text();
			o.isValid = o.validText(text);
			if(o.isValid){
				RLog.debug(this, 'Edit blur event (text=[{0}]->[{1}])', o.displayText, o.text());
				o.onEditEnd();
			}else{
				o.setEditStyle(EStyle.Valid);
				RLog.debug(this, 'Edit valid failed (text=[{0}]->[{1}])', o.displayText, text);
				o.storeText();
			}
		}else{
			o.setEditStyle(EStyle.Normal);
		}
		o.inEditing = false;
	}
}
function MEditable_isTextChanged(){
	return this.text() != this.displayText;
}
function MEditable_findDsControl(){
	var o = this;
	if(!o.dsControl){
		o.dsControl = o.topControl(MDataset);
	}
	return o.dsControl;
}
function MEditable_storeText(){
	this.displayText = this.text();
}
function MEditable_validText(s){
	return true;
}
function MEditable_formatText(s){
	return RStr.nvl(s);
}
function MEditable_value(){
	return this.dataValue;
}
function MEditable_setValue(v){
	var o = this;
	o.dataValue = v;
	o.setText(o.formatText(v));
}
function MEditable_setEditable(v){
	var o = this;
	o.canEdit = v;
	o.setEditStyle(v ? EStyle.Edit : EStyle.Readonly);
}
function MEditable_setEditStyle(t){
	var o = this;
	var h = o.hEdit;
	if(h){
		var s = h.style;
		if(EStyle.Valid == t){
			s.backgroundColor = EColor.Valid;
		}else if(EStyle.Edit == t){
			s.backgroundColor = EColor.Edit;
			h.disabled = false;
		}else if(EStyle.Readonly == t){
			s.backgroundColor = EColor.Readonly;
			h.disabled = true;
		}
	}
	var h = o.hEditForm;
	if(h){
		var s = h.style;
		if(EStyle.Valid == t){
			s.backgroundColor = EColor.Valid;
		}else if(EStyle.Edit == t){
			s.backgroundColor = EColor.Edit;
			h.disabled = false;
		}else if(EStyle.Readonly == t){
			s.backgroundColor = EColor.Readonly;
			h.disabled = true;
		}
	}
}
function MEditable_focus(e){
	var o = this;
	var h = o.hEdit;
	if(h){
		h.focus();
	}
}
function MEditor(o){
	o = RClass.inherits(this, o);
	o.onEdit       = RMethod.virtual(o, 'onEdit');
	o.onEditChange = RMethod.virtual(o, 'onEditChange');
	o.onEditBlur   = RMethod.virtual(o, 'onEditBlur');
	o.onEditFocus  = RMethod.virtual(o, 'onEditFocus');
	return o;
}
function MFocus(o){
	o = RClass.inherits(this, o);
	o.onFocus  = RClass.register(o, new HFocus('onFocus'), MFocus_onFocus);
	o.onBlur   = RClass.register(o, new HBlur('onBlur'));
	o.doFocus  = RMethod.empty;
	o.doBlur   = RMethod.empty;
	o.testBlur = MFocus_testBlur;
	o.focus    = MFocus_focus;
	o.blur     = MFocus_blur;
	return o;
}
function MFocus_onFocus(e){
	RConsole.find(FFocusConsole).focus(this, e);
}
function MFocus_onBlur(e){
	RConsole.find(FFocusConsole).blur(this, e);
}
function MFocus_testBlur(){
	return true;
}
function MFocus_focus(e){
	var o = this;
	if(!o.isDesign && o.canEdit){
		var h = o.panel(EPanel.Focus);
		RLog.debug(o, 'Focus panel={0}', RClass.dump(h));
		if(h){
			h.focus();
		}
	}
	RConsole.find(FFocusConsole).focus(this, e);
}
function MFocus_blur(e){
	var o = this;
	if(!o.isDesign && o.canEdit){
		var h = o.panel(EPanel.Focus);
		RLog.debug(o, 'Focus panel={0}', RClass.dump(h));
		if(h){
			h.blur();
		}
	}
	RConsole.find(FFocusConsole).blur(this, e);
}
function MFocusLooper(o){
	o = RClass.inherits(this, o);
	o.focusIndex   = 0;
	o.focusObjects = new TList();
	o.focusNext    = MFocusLooper_focusNext;
	o.focusPrior   = MFocusLooper_focusPrior;
	o.pushFocus    = MFocusLooper_pushFocus;
	return o;
}
function MFocusLooper_focusPrior(){
	var o = this;
	o.focusIndex--;
	if(o.focusIndex < 0){
		o.focusIndex = o.focusObjects.count-1;
	}
	var h = o.focusObjects.get(o.focusIndex);
	if(h){
		h.focus();
	}
}
function MFocusLooper_focusNext(){
	var o = this;
	o.focusIndex++;
	if(o.focusIndex >= o.focusObjects.count){
		o.focusIndex = 0;
	}
	var h = o.focusObjects.get(o.focusIndex);
	if(h){
		h.focus();
	}
}
function MFocusLooper_pushFocus(h){
	this.focusObjects.push(h);
}
function MForm(o){
	o = RClass.inherits(this, o);
	o.getFormName  = RMethod.virtual(o, 'getFormName');
	o.getTableName = RMethod.virtual(o, 'getTableName');
	return o;
}
function MHorizontal(o){
	o = RClass.inherits(this, o);
	return o;
}
function MInvoke(o){
	o = RClass.inherits(this, o);
	o.invoke = RMethod.virtual(o, 'invoke');
	return o;
	}
function MListener(o){
	o = RClass.inherits(this, o);
	o.listeners        = null;
	o.registerListener = MListener_registerListener;
	o.processListener  = MListener_processListener;
	return o;
}
function MListener_registerListener(name, owner, method){
	var l = null;
	var ln = arguments.length;
	if(ln == 2){
		l = new TListener(this, owner);
	}else if(ln == 3){
		l = new TListener(owner, method);
	}else{
		RMsg.fatal(o, null, 'Paramter error');
	}
	var o = this;
	if(!o.listeners){
		o.listeners = new Array();
	}
	var lsns = o.listeners[name];
	if(!lsns){
		lsns = o.listeners[name] = new TListeners();
	}
	lsns.push(l);
	return l;
}
function MListener_processListener(name, params){
	var o = this;
	if(o.listeners){
		var lsns = o.listeners[name];
		if(lsns){
			lsns.process(this, params);
		}
	}
}
function MListView(o){
	o = RClass.inherits(this, o);
	o.lovService    = RClass.register(o, new TPtyStr('lovService', EService.WebForm));
	o.lovRefer      = RClass.register(o, new TPtyStr('lovRefer'));
	o.lovFields     = RClass.register(o, new TPtyStr('lovFields'));
	o.lovWhere      = RClass.register(o, new TPtyStr('lovWhere'));
	o.lovOrder      = RClass.register(o, new TPtyStr('lovOrder'));
	o.listView      = null;
	o.onDoubleClick = MListView_onDoubleClick;
	o.canListView   = MListView_canListView;
	o.doListView    = MListView_doListView;
	return o;
}
function MListView_onDoubleClick(){
	var o = this;
	if(o.canListView()){
		o.doListView();
	}
}
function MListView_canListView(){
	return !RStr.isEmpty(this.lovRefer);
}
function MListView_doListView(){
	var o = this;
	var v = o.listView;
	if(!v){
		v = o.listView = RControl.create(FListWindow);
		v.linkLovControl(o);
	}
	v.show();
}
function MLsnClick(o){
	o = RClass.inherits(this, o, MListener);
	o.addClickListener = MLsnClick_addClickListener;
	o.processClick     = MLsnClick_processClick;
	return o;
}
function MLsnClick_addClickListener(owner, method){
	return this.registerListener('click', owner, method);
}
function MLsnClick_processClick(params){
	this.processListener('click', params);
}
function MLsnDblClick(o){
	o = RClass.inherits(this, o, MListener);
	o.addDblClickListener = MLsnDblClick_addDblClickListener;
	o.processDblClick     = MLsnDblClick_processDblClick;
	return o;
}
function MLsnDblClick_addDblClickListener(owner, method){
	return this.registerListener('dblClick', owner, method);
}
function MLsnDblClick_processDblClick(params){
	this.processListener('dblClick', params);
}
function MLsnKey(o){
	o = RClass.inherits(this, o, MListener);
	o.addKeyDownListener  = MLsnKey_addKeyDownListener;
	o.addKeyPressListener = MLsnKey_addKeyPressListener;
	o.addKeyUpListener    = MLsnKey_addKeyUpListener;
	o.processKeyDown      = MLsnKey_processKeyDown;
	o.processKeyPress     = MLsnKey_processKeyPress;
	o.processKetUp        = MLsnKey_processKetUp;
	return o;
}
function MLsnKey_addKeyDownListener(owner, method){
	return this.registerListener('keyDown', owner, method);
}
function MLsnKey_addKeyPressListener(owner, method){
	return this.registerListener('keyPress', owner, method);
}
function MLsnKey_addKeyUpListener(owner, method){
	return this.registerListener('keyUp', owner, method);
}
function MLsnKey_processKeyDown(params){
	this.processListener('keyDown', params);
}
function MLsnKey_processKeyPress(params){
	this.processListener('keyPress', params);
}
function MLsnKey_processKetUp(params){
	this.processListener('keyUp', params);
}
function MLsnLoaded(o){
	o = RClass.inherits(this, o, MListener);
	o.addLoadedListener = MLsnLoaded_addLoadedListener;
	o.processLoaded     = MLsnLoaded_processLoaded;
	return o;
}
function MLsnLoaded_addLoadedListener(owner, method){
	return this.registerListener('loaded', owner, method);
}
function MLsnLoaded_processLoaded(params){
	this.processListener('loaded', params);
}
function MLsnSelect(o){
	o = RClass.inherits(this, o, MListener);
	o.addSelectListener = MLsnSelect_addSelectListener;
	o.processSelect     = MLsnSelect_processSelect;
	return o;
}
function MLsnSelect_addSelectListener(owner, method){
	return this.registerListener('select', owner, method);
}
function MLsnSelect_processSelect(params){
	this.processListener('select', params);
}
function MMouseWheel(o){
	o = RClass.inherits(this, o);
	o.onMouseWheel = RClass.register(o, new HMouseWheel('onMouseWheel'), RMethod.empty);
	return o;
}
function MMoveable(o){
	o = RClass.inherits(this, o);
	o.canMove   = true;
	o.inMoving  = false;
	o.zIndex    = ELayer.Move;
	o.startDrag = RMethod.virtual(o, 'startDrag');
	o.stopDrag  = RMethod.virtual(o, 'stopDrag');
	return o;
}
function MPad(o){
	o = RClass.inherits(this, o);
	o.padLeft   = RClass.register(o, new TPtyInt('padLeft', 0));
	o.padTop    = RClass.register(o, new TPtyInt('padTop', 0));
	o.padRight  = RClass.register(o, new TPtyInt('padRight', 0));
	o.padTottom = RClass.register(o, new TPtyInt('padTottom', 0));
	o.setPads   = MPad_setPads;
	return o;
}
function MPad_setPads(left, top, right, bottom){
	var o = this;
	var h = o.hPanel;
	if(!h){
		return;
	}
	var s = h.style;
	if(left){
		s.paddingLeft = left;
	}
	if(top){
		s.paddingTop = top;
	}
	if(right){
		s.paddingRight = right;
	}
	if(bottom){
		s.paddingbottom = bottom
	}
}
function MShadow(o){
	o = RClass.inherits(this, o);
	o.hShadow = null;
	o.show    = MShadow_show;
	o.hide    = MShadow_hide;
	return o;
}
function MShadow_show(v){
	var o = this;
	if(!o.hShadow){
		o.hShadow = RBuilder.append(null, 'DIV', 'RWindow_Shadow');
	}
	o.hShadow.style.zIndex = RLayer.next();
	if(v == false){
		o.hide();
	}else{
		var hs = o.panel(EPanel.Shadow);
		if(hs){
			var r = RHtml.rect(hs);
			var s = o.hShadow.style;
			s.pixelLeft = r.left+3;
			s.pixelTop = r.top+3;
			s.pixelWidth = r.width();
			s.pixelHeight = r.height();
			s.display = 'block';
		}
		var hp = o.panel(EPanel.Panel);
		if(hp){
			hp.style.zIndex = RLayer.next();
		}
	}
}
function MShadow_hide(){
	var o = this;
	if(o.hShadow){
		o.hShadow.style.display = 'none';
	}
}
function MSize(o){
	o = RClass.inherits(this, o);
	o.left        = RClass.register(o, new TPtyInt('left',   -1));
	o.top         = RClass.register(o, new TPtyInt('top',    -1));
	o.right       = RClass.register(o, new TPtyInt('right',  -1));
	o.bottom      = RClass.register(o, new TPtyInt('bottom', -1));
	o.width       = RClass.register(o, new TPtyStr('width'));
	o.height      = RClass.register(o, new TPtyStr('height'));
	o.rect        = null;
	o.onSize      = null;
	o.calcRect    = MSize_calcRect;
	o.resize      = MSize_resize;
	o.setSize     = MSize_setSize;
	o.setBounds   = MSize_setBounds;
	o.resetSize   = MSize_resetSize;
	o.innerDump   = MSize_innerDump;
	o.testInRange = MSize_testInRange;
	return o;
}
function MSize_resize(width, height){
	var sizeable = false;
	var hStyle = this.htmlPanel(EPanel.Border).style;
	if(width != null){
		width = Math.max(parseInt(width), EMoveSize.MinWidth);
		if(this.width != width){
			this.width = width;
			hStyle.pixelWidth = width;
			sizeable = true;
		}
	}
	if(height != null){
		height = Math.max(parseInt(height), EMoveSize.MinHeight);
		if(this.height != height){
			this.height = height;
			hStyle.pixelHeight = height;
			sizeable = true;
		}
	}
	if(sizeable && this.onSize){
		this.onSize();
	}
}
function MSize_setSize(w, h){
	var s = this.hPanel.style;
	if(w){
		s.width = w;
	}
	if(h){
		s.height = h;
	}
}
function MSize_setBounds(left, top, right, bottom, force){
	var o = this;
	var h = o.panel(EPanel.Size);
	if(!h){
		return;
	}
	var s = h.style;
	var c = false;
	if(left >= 0){
		if(force || o.left != left){
			o.left = left;
			s.pixelLeft = left;
			c = true;
		}
	}
	if(top >= 0){
		if(force || o.top != top){
			o.top = top;
			s.pixelTop = top;
			c = true;
		}
	}
	if(right >= 0){
		var width = right-o.left+1;
		if(force || o.width != width){
			o.width = width;
			s.pixelWidth = o.width;
			c = true;
		}
	}
	if(bottom >= 0){
		var height = bottom-o.top+1;
		if(force || o.height != height){
			o.height = height;
			s.pixelHeight = o.height;
			c = true;
		}
	}
	if(c && o.onSize){
		o.onSize();
	}
}
function MSize_resetSize(){
	var o = this;
	o.setBounds(o.left, o.top, o.left+o.width-1, o.top+o.height-1, true)
}
function MSize_calcRect(){
	this.rect = RRect.nvl(this.rect);
	RHtml.toRect(this.rect, this.hPanel);
	return this.rect;
}
function MSize_innerDump(dump){
	dump.append('Size [', RBool.toString(this.isSizeable), ':');
	dump.append(this.left, ',', this.top, '-', this.width, ',', this.height, ']');
}
function MSize_testInRange(){
}
function MSizeable(o){
	o = RClass.inherits(this, o);
	o.isSizeable  = true;
	o.onSize      = null;
	o.inSizeRange = RMethod.virtual(o, 'inSizeRange');
	o.cursor      = MSizeable_cursor;
	o.setCursor   = MSizeable_setCursor;
	o.resize      = MSizeable_resize;
	o.setBounds   = MSizeable_setBounds;
	o.startDrag   = MSizeable_startDrag;
	o.stopDrag    = MSizeable_stopDrag;
	return o;
}
function MSizeable_cursor(){
	var o = this;
	var src = RWindow.source();
	if(!o.inSizeRange(src)){
		return ECursor.Default;
	}
	var hObj = this.panel(EPanel.Border);
	var r = RHtml.rect(hObj);
	var pos = RWindow.offsetPos();
	var p = new TPoint(pos.x-r.left, pos.y-r.top);
	while(src){
		p.x += src.offsetLeft + src.clientLeft;
		p.y += src.offsetTop + src.clientTop;
		if(src == hObj){
			break;
		}
		src = src.offsetParent;
	}
	var border = EMoveSize.Border;
	var range = EMoveSize.Range;
	x = p.x;
	y = p.y;
	var right = r.width();
	var bottom = r.height();
	if(x>=0 && x<=range && y>=0 && y<=range){
		return ECursor.NorthWest;
	}else if(x>=0 && x<=range && y>=bottom-range && y<=bottom){
		return ECursor.SouthWest;
	}else if(x>=right-range && x<=right && y>=bottom-range && y<=bottom){
		return ECursor.SouthEast;
	}else if(x>=right-range && x<=right && y>=0 && y<=range){
		return ECursor.NorthEast;
	}else if(x>=0 && x<border && y>range && y<bottom-range){
		return ECursor.West;
	}else if(x>range && x<right-range && y>=bottom-border && y<=bottom){
		return ECursor.South;
	}else if(x>=right-border && x<=right && y>range && y<bottom-range){
		return ECursor.East;
	}else if(x>range && x<right-range && y>=0 && y<border){
		return ECursor.North;
	}
	return ECursor.Default;
}
function MSizeable_setCursor(cursor){
	if(!cursor){
		cursor = this.cursor();
	}
	var h = this.panel(EPanel.Size);
	if(h){
		h.style.cursor = (cursor == null || cursor == 'default') ? 'default' : cursor + '-resize';
	}
}
function MSizeable_resize(width, height){
	var sizeable = false;
	var hStyle = this.htmlPanel(EPanel.Border).style;
	if(width != null){
		width = Math.max(parseInt(width), EMoveSize.MinWidth);
		if(this.width != width){
			this.width = width;
			hStyle.pixelWidth = width;
			sizeable = true;
		}
	}
	if(height != null){
		height = Math.max(parseInt(height), EMoveSize.MinHeight);
		if(this.height != height){
			this.height = height;
			hStyle.pixelHeight = height;
			sizeable = true;
		}
	}
	if(sizeable && this.onSize){
		this.onSize();
	}
}
function MSizeable_setBounds(left, top, right, bottom, force){
	var sizeable = false;
	var st = this.htmlPanel(EPanel.Border).style;
	if(left != null){
		if(right == null || (right != null && right-left > EMoveSize.MinWidth)){
			left = Math.max(left, 0);
		}else{
			left = this.left;
		}
		if(force || this.left != left){
			this.left = left;
			st.pixelLeft = left;
			sizeable = true;
		}
	}
	if(top != null){
		if(bottom == null || (bottom != null && bottom-top > EMoveSize.MinHeight)){
			top = Math.max(top, 0);
		}else{
			top = this.top;
		}
		if(force || this.top != top){
			this.top = top;
			st.pixelTop = top;
			sizeable = true;
		}
	}
	if(right != null){
		var width = Math.max(right-this.left+1, EMoveSize.MinWidth);
		if(force || this.width != width){
			this.width = width;
			st.pixelWidth = this.width;
			sizeable = true;
		}
	}
	if(bottom != null){
		var height = Math.max(bottom-this.top+1, EMoveSize.MinHeight);
		if(force || this.height != height){
			this.height = height;
			st.pixelHeight = this.height;
			sizeable = true;
		}
	}
	if(sizeable && this.onSize){
		this.onSize();
	}
}
function MSizeable_startDrag(){
}
function MSizeable_stopDrag(){
}
function MStyle(o){
	o = RClass.inherits(this, o);
	o.style = MStyle_style;
	return o;
}
function MStyle_style(n, o){
   return RClass.find(o ? o : this, true).style(n);
}
function MTop(o){
	o = RClass.inherits(this, o);
	return o;
}
function MValue(o){
	o = RClass.inherits(this, o);
	o.onLoadValue      = RMethod.virtual(o, 'onLoadValue');
	o.onSaveValue      = RMethod.virtual(o, 'onSaveValue');
	o.loadValue        = MValue_loadValue;
	o.saveValue        = MValue_saveValue;
	return o;
}
function MValue_loadValue(c, t){
	if(c){
		var o = this;
		c = RObj.nvl(o.onLoadValue(c, t), c);
		var ps = o.components;
		if(c && ps){
			for(var n=0; n<ps.count; n++){
				var p = ps.value(n);
				if(RClass.isClass(p, MValue)){
					p.loadValue(c, t);
				}
			}
		}
	}
}
function MValue_saveValue(c, t){
	if(c){
		var o = this;
		c = RObj.nvl(o.onSaveValue(c, t), c);
		var ps = o.components;
		if(c && ps){
			for(var n=0; n<ps.count; n++){
				var p = ps.value(n);
				if(RClass.isClass(p, MValue)){
					p.saveValue(c, t);
				}
			}
		}
	}
}
function MVertical(o){
	o = RClass.inherits(this, o);
	return o;
}
function RBorderFace(o){
	if(!o){o=this;}
	o.buildCell = RBorder_buildCell;
	o.build     = RBorder_build;
	return o;
}
var RBorder = new RBorderFace();
function RBorder_buildCell(hr, c){
	var h = hr.insertCell();
	h.style.backgroundColor = c;
	h.width = 1;
	h.height = 1;
}
function RBorder_build(t){
	var o = this;
	var s = t.style;
	var ht = t.hForm;
		if(EBorderStyle.None == s){
		var hr = o.hRow = ht.insertRow();
		t.hPanel = hr.insertCell();
	}else if(EBorderStyle.Round == s){
		var hr =  ht.insertRow();
		o.buildCell(hr, '#c0d7e7');
		o.buildCell(hr, '#77a9cb');
		o.buildCell(hr, '#5794bf');
		o.buildCell(hr, '#6291b4');
		o.buildCell(hr, '#c0d7e7');
		var hr =  ht.insertRow();
		o.buildCell(hr, '#c5daed');
		o.buildCell(hr, '#d3e6f2');
		o.buildCell(hr, '#ffffff');
		o.buildCell(hr, '#6a9dc3');
		o.buildCell(hr, '#82a8c3');
		var hr = o.hRow = ht.insertRow();
		o.buildCell(hr, '#5794bf');
		o.buildCell(hr, '#fafdfe');
		t.hPanel = hr.insertCell();
		o.buildCell(hr, '#fafdfe');
		o.buildCell(hr, '#3c7fb1');
		var hr =  ht.insertRow();
		o.buildCell(hr, '#c5daed');
		o.buildCell(hr, '#d3e6f2');
		o.buildCell(hr, '#ffffff');
		o.buildCell(hr, '#70a0c4');
		o.buildCell(hr, '#81a8c4');
		var hr =  ht.insertRow();
		o.buildCell(hr, '#e9f1f8');
		o.buildCell(hr, '#c7e2f1');
		o.buildCell(hr, '#8aafc7');
		o.buildCell(hr, '#e9f1f8');
	}else if(EBorderStyle.RoundDrop == s){
		var hr =  ht.insertRow();
		o.buildCell(hr, '#c0d7e7');
		o.buildCell(hr, '#77a9cb');
		o.buildCell(hr, '#5794bf');
		o.buildCell(hr, '#3c7fb1');
		o.buildCell(hr, '#3c7fb1');
		o.buildCell(hr, '#3c7fb1');
		o.buildCell(hr, '#6291b4');
		o.buildCell(hr, '#c0d7e7');
		var hr =  ht.insertRow();
		o.buildCell(hr, '#c5daed');
		o.buildCell(hr, '#d3e6f2');
		o.buildCell(hr, '#ffffff');
		o.buildCell(hr, '#3c7fb1');
		o.buildCell(hr, '#ffffff');
		o.buildCell(hr, '#ffffff');
		o.buildCell(hr, '#6a9dc3');
		o.buildCell(hr, '#82a8c3');
		var hr = o.hRow = ht.insertRow();
		o.buildCell(hr, '#5794bf');
		o.buildCell(hr, '#fafdfe');
		t.hPanel = hr.insertCell();
		o.buildCell(hr, '#3c7fb1');
		o.buildCell(hr, '#ffffff');
		t.hDrop = hr.insertCell();
		o.buildCell(hr, '#fafdfe');
		o.buildCell(hr, '#3c7fb1');
		var hr =  ht.insertRow();
		o.buildCell(hr, '#c5daed');
		o.buildCell(hr, '#d3e6f2');
		o.buildCell(hr, '#ffffff');
		o.buildCell(hr, '#3c7fb1');
		o.buildCell(hr, '#ffffff');
		o.buildCell(hr, '#fafdfe');
		o.buildCell(hr, '#70a0c4');
		o.buildCell(hr, '#81a8c4');
		var hr =  ht.insertRow();
		o.buildCell(hr, '#e9f1f8');
		o.buildCell(hr, '#c7e2f1');
		o.buildCell(hr, '#c7e2f1');
		o.buildCell(hr, '#3c7fb1');
		o.buildCell(hr, '#3c7fb1');
		o.buildCell(hr, '#8aafc7');
		o.buildCell(hr, '#e9f1f8');
	}
}
function RBorder_build2(t){
	var o = this;
	var s = t.style;
	var ht = t.hForm;
	if(EBorderStyle.Round == s){
		var hr =  ht.insertRow();
		o.buildCell(hr, '#c0d7e7');
		o.buildCell(hr, '#77a9cb');
		o.buildCell(hr, '#5794bf');
		o.buildCell(hr, '#6291b4');
		o.buildCell(hr, '#c0d7e7');
		var hr =  ht.insertRow();
		o.buildCell(hr, '#c5daed');
		o.buildCell(hr, '#d3e6f2');
		o.buildCell(hr, '#ffffff');
		o.buildCell(hr, '#6a9dc3');
		o.buildCell(hr, '#82a8c3');
		var hr = o.hRow = ht.insertRow();
		o.buildCell(hr, '#c5daed');
		o.buildCell(hr, '#fafdfe');
		t.hPanel = hr.insertCell();
		o.buildCell(hr, '#fafdfe');
		o.buildCell(hr, '#3c7fb1');
		var hr =  ht.insertRow();
		o.buildCell(hr, '#c5daed');
		o.buildCell(hr, '#d3e6f2');
		o.buildCell(hr, '#ffffff');
		o.buildCell(hr, '#70a0c4');
		o.buildCell(hr, '#81a8c4');
		var hr =  ht.insertRow();
		o.buildCell(hr, '#e9f1f8');
		o.buildCell(hr, '#c7e2f1');
		o.buildCell(hr, '#8aafc7');
		o.buildCell(hr, '#e9f1f8');
	}else if(EBorderStyle.RoundDrop == s){
		var hr =  ht.insertRow();
		o.buildCell(hr, '#c0d7e7');
		o.buildCell(hr, '#77a9cb');
		o.buildCell(hr, '#5794bf');
		o.buildCell(hr, '#3c7fb1');
		o.buildCell(hr, '#3c7fb1');
		o.buildCell(hr, '#3c7fb1');
		o.buildCell(hr, '#6291b4');
		o.buildCell(hr, '#c0d7e7');
		var hr =  ht.insertRow();
		o.buildCell(hr, '#c5daed');
		o.buildCell(hr, '#d3e6f2');
		o.buildCell(hr, '#ffffff');
		o.buildCell(hr, '#3c7fb1');
		o.buildCell(hr, '#ffffff');
		o.buildCell(hr, '#ffffff');
		o.buildCell(hr, '#6a9dc3');
		o.buildCell(hr, '#82a8c3');
		var hr = o.hRow = ht.insertRow();
		o.buildCell(hr, '#c5daed');
		o.buildCell(hr, '#fafdfe');
		t.hPanel = hr.insertCell();
		o.buildCell(hr, '#3c7fb1');
		o.buildCell(hr, '#ffffff');
		t.hDrop = hr.insertCell();
		o.buildCell(hr, '#fafdfe');
		o.buildCell(hr, '#3c7fb1');
		var hr =  ht.insertRow();
		o.buildCell(hr, '#c5daed');
		o.buildCell(hr, '#d3e6f2');
		o.buildCell(hr, '#ffffff');
		o.buildCell(hr, '#3c7fb1');
		o.buildCell(hr, '#ffffff');
		o.buildCell(hr, '#fafdfe');
		o.buildCell(hr, '#70a0c4');
		o.buildCell(hr, '#81a8c4');
		var hr =  ht.insertRow();
		o.buildCell(hr, '#e9f1f8');
		o.buildCell(hr, '#c7e2f1');
		o.buildCell(hr, '#c7e2f1');
		o.buildCell(hr, '#3c7fb1');
		o.buildCell(hr, '#3c7fb1');
		o.buildCell(hr, '#8aafc7');
		o.buildCell(hr, '#e9f1f8');
	}
}
function RControlFace(o){
	if(!o){o=this;}
	o.inMoving    = false;
	o.inSizing    = false;
	o.inDesign    = false;
	o.instances   = new TList();
	o.controls    = new TMap();
	o.ohProcess   = RControl_ohProcess;
	o.onProcess   = RControl_onProcess;
	o.innerbuild  = RControl_innerbuild;
	o.build       = RControl_build;
	o.innerCreate = RControl_innerCreate;
	o.create      = RControl_create;
	o.attachEvent = RControl_attachEvent;
	o.find        = RControl_find;
	o.fromNode    = RControl_fromNode;
	o.fromXml     = RControl_fromXml;
	o.toNode      = RControl_toNode;
	o.toXml       = RControl_toXml;
	o.innerStore  = RControl_innerStore;
	o.store       = RControl_store;
	o.htmlControl = RControl_htmlControl;
	o.setAction   = RControl_setAction;
	return o;
}
var RControl = new RControlFace();
function RControl_ohProcess(){
	var es = this.linkEvents;
	if(es){
		var e = es[event.type];
		if(e){
			if(e.onProcess){
				RLog.debug(e, 'Execute {0} (source={1}, process={2})', e.type, RClass.dump(e.source), RMethod.name(e.onProcess));
				e.onProcess.call(e.source, e, event);
			}
			if(e.process){
				if(e.attach){
					e.attach(event);
				}
				RConsole.find(FEventConsole).push(e);
			}
		}
	}
}
function RControl_onProcess(){
	var e = this;
	var p = e.source[e.name];
	if(!RMethod.isEmpty(p)){
		RLog.debug(e, 'Process {0} (source={1}, process={2})', e.type, RClass.dump(e.source), RMethod.name(p));
		p.call(e.source, e);
	}
}
function RControl_innerbuild(ctl, cfg){
	if(ctl){
		var rs = ctl.loadConfig(cfg);
		ctl.construct();
		if(cfg.nodes){
			var child = true;
			if(rs && EStatus.Stop == rs){
				child = false;
			}
			if(child){
				var nodes = cfg.nodes;
				for(var n=0; n<nodes.count; n++){
					var node = nodes.get(n);
					var obj = ctl.createChild(node);
					if(obj){
						this.innerbuild(obj, node);
						ctl.push(obj);
					}
				}
			}
		}
	}
}
function RControl_build(ctl, cfg){
	this.innerbuild(ctl, cfg);
	ctl.initialize();
	ctl.build();
}
function RControl_innerCreate(ct, cf){
	var r = null;
	if(RClass.isClass(ct, MConfig)){
		r = ct.loadConfig(cf);
	}
	if(cf.nodes && !(EStatus.Stop == r)){
		var nodes = cf.nodes;
		for(var n=0; n<nodes.count; n++){
			var node = nodes.get(n);
			var o = ct.createChild(node);
			if(o){
				o.parent = ct;
				this.innerCreate(o, node);
				ct.push(o);
			}
		}
	}
}
function RControl_create(config, hPanel){
	var o = null;
	if(RClass.isClass(config, TNode)){
		if(config){
			o = RClass.create('F' + config.name);
			if(o){
				this.innerCreate(o, config);
				o.initialize();
				o.build();
				o.setPanel(hPanel);
				this.instances.push(o);
			}
		}
	}else{
		o = RClass.create(config);
		if(o){
			o.initialize();
			o.build();
			o.setPanel(hPanel);
		}
	}
	return o;
}
function RControl_attachEvent(c, n, h, m){
	var o = this;
   if(!RMethod.isEmpty(c[n]) || m){
      var a = RClass.find(c.constructor).getAnnotation(EAnnotation.Event, n);
      var as = h.linkEvents;
      if(!as){
         as = h.linkEvents = new Object();
      }
      var e = as[a.type];
      if(!e){
         e = as[a.type] = new a.constructor();
      }
      e.name = a.name;
      e.source = c;
      e.hSource = h;
      if(m){
         e.onProcess = m;
      }
      e.process = o.onProcess;
      h[e.handle] = o.ohProcess;
      return e;
   }
}
function RControl_find(c){
	var o = this;
	var r = null;
	if(c){
		if(c.constructor == Function){
			c = RMethod.name(c);
		}else if(c.constructor != String){
			RMsg.fatal(o, null, 'Param invlid (class={0})', c);
		}
		var cs = o.controls;
		var r = cs.get(c);
		if(!r){
			r = new TControl(c);
			cs.set(c, r);
		}
	}
	return r;
}
function RControl_fromNode(config, hPanel){
	return this.create(config, hPanel);
}
function RControl_fromXml(xml, hPanel){
	return this.create(RXml.makeNode(xml), hPanel);
}
function RControl_toNode(){
}
function RControl_toXml(){
}
function RControl_innerStore(o, x, type){
	o.saveConfig(x, type);
	if(o.components){
		var cs = o.components;
		for(var n=0; n<cs.count; n++){
			var c = cs.value(n);
			if(c){
				this.innerStore(c, x.create(RClass.name(c).substr(1)));
			}
		}
	}
}
function RControl_store(o, type){
	var config = new TNode();
	config.name = RClass.name(o).substr(1);
	this.innerStore(o, config, type);
	return config;
}
function RControl_htmlControl(e, c){
	if(c){
		while(e){
			var o = e.link;
			if(o && RClass.isClass(o, c)){
				return o;
			}
			e = e.parentElement;
		}
	}else{
		while(e){
			var o = e.link;
			if(o){
				return o;
			}
			e = e.parentElement;
		}
	}
	return null;
}
function RControl_setAction(action, mode, flag, params){
	var os = this.instances;
	for(var n=0; n<os.count; n++){
		os.get(n).setAction(action, mode, flag, params);
	}
}
function TBorder(s){
	var o = this;
	o.style = s;
	return o;
}
function TColumn(){
	var o = this;
	o.align      = null;
	o.label      = null;
	o.width      = null;
	o.dataName   = null;
	o.loadConfig = TColumn_loadConfig;
	o.saveConfig = TColumn_saveConfig;
	return o;
}
function TColumn_loadConfig(c){
	this.align    = c.get('align');
	this.label    = c.get('label');
	this.width    = c.get('width');
	this.dataName = c.get('data_name');
}
function TColumn_saveConfig(c){
}
function TControl(n){
	var o = this;
	o.name         = n;
	o.events       = new Array();
	o.ohProcess    = TControl_ohProcess;
	o.register     = TControl_register;
	o.findEvent    = TControl_findEvent;
	o.attachEvent  = TControl_attachEvent;
	return o;
}
function TControl_ohProcess(){
	var es = this.linkEvents;
	if(es){
		var e = es[event.type];
		if(e && e.process){
			if(e.attach){
				e.attach(event);
			}
			RConsole.find(FEventConsole).push(e);
		}
	}
}
function TControl_register(e){
	var o = this;
	var n = e.name;
	if(o.events[n]){
		RMsg.fatal(o, null, "Duplicate event [{0}] in control [{1}]", n, o.name);
	}
	o.events[n] = e;
	return e;
}
function TControl_findEvent(n){
	var o = this;
	var e = o.events[n];
	if(!e){
		var p = RClass.find(o.name).parent;
		if(p){
			var c = RControl.find(p.name);
			if(c){
				return c.findEvent(n);
			}
		}
	}
	return e;
}
function TControl_attachEvent(c, n, h){
	var o = this;
	var e = o.findEvent(n);
	if(!e){
		RMsg.fatal(o, null, "Not register event [{0}] in control [{1}]", n, o.name);
	}
	var es = h.linkEvents;
	if(!es){
		es = h.linkEvents = new Object();
	}
	var l = es[e.type] = new e.constructor();
	l.name = e.name;
	l.source = c;
	l.hSource = h;
	h[l.handle] = o.ohProcess;
	return l;
}
function TItem(value, label){
	var o = this;
	o.label = label;
	o.value = value;
	o.icon  = null;
	o.loadConfig = TItem_loadConfig;
	o.saveConfig = TItem_saveConfig;
	return o;
}
function TItem_loadConfig(config){
	this.label = config.get('label');
	this.value = config.get('value');
	this.icon  = config.get('icon');
}
function TItem_saveConfig(config){
	config.set('label', this.label);
	config.set('value', this.value);
	config.set('icon',  this.icon);
}
function TItems(value, label){
	var o = this;
	o.code       = RClass.dump(o);
	o.items      = new TMap();
	o.loadConfig = TItems_loadConfig;
	o.create     = TItems_create;
	o.count      = TItems_count;
	o.indexOf    = TItems_indexOf;
	o.get        = TItems_get;
	o.find       = TItems_find;
	o.value      = TItems_value;
	o.label      = TItems_label;
	o.getLabel   = TItems_getLabel;
	return o;
}
function TItems_loadConfig(c){
	var o = this;
	if(c && c.nodes){
		var ns = c.nodes;
		for(var n=0; n<ns.count; n++){
			var nd = ns.get(n);
			if(nd && nd.isName('Item')){
				var item = new TItem();
				item.loadConfig(nd);
				o.items.set(item.value, item);
			}
		}
	}
}
function TItems_create(value, label){
	var o = new TItem(value, label);
	this.items.set(o.value, o);
	return o;
}
function TItems_count(){
	return this.items.count;
}
function TItems_indexOf(value){
	return this.items.indexOf(value);
}
function TItems_get(index){
	return this.items.value(index);
}
function TItems_find(name){
	return this.items.get(name);
}
function TItems_value(s){
	if(!RStr.isEmpty(s)){
		var o = this;
		var count = o.items.count;
		for(var n=0; n<count; n++){
			var t = o.items.value(n);
			if(t.label == s){
				return t.value;
			}
		}
	}
	return '';
}
function TItems_label(v){
	var o = this.items.get(v);
	return o ? o.label : RStr.nvl(v);
}
function TItems_getLabel(index){
	var o = this.items.value(index);
	return o ? o.label : '';
}
function TStyle(n){
	var o = this;
	o.annotation = EAnnotation.Style;
	o.name       = n;
	return o;
}
function EResultCommandFace(){
	var o = this;
	o.TreeReload        = 'tree.reload';
	o.TreeParentRefresh = 'tree.parent.refresh';
	o.TreeNodeRefresh   = 'tree.node.refresh';
	o.PageRedirect      = 'page.redirect';
	return o;
}
var EResultCommand = new EResultCommandFace();
function FActiveConsole(o){
	o = RClass.inherits(this, o, FConsole);
	o.scope      = EScope.Page;
	o.active     = true;
	o.working    = false;
	o.interval   = 10;
	o.intervalId = null;
	o.actives    = new TList();
	o.hWindow    = null;
	o.ohInterval = FActiveConsole_ohInterval;
	o.construct  = FActiveConsole_construct;
	o.push       = FActiveConsole_push;
	o.process    = FActiveConsole_process;
	o.processAll = FActiveConsole_processAll;
	o.wait       = FActiveConsole_wait;
	o.release    = FActiveConsole_release;
	return o;
}
function FActiveConsole_construct(){
	var o = this;
	o.base.FConsole.construct.call(o);
	o.hWindow = window;
	o.hWindow._activeConsole = o;
	o.intervalId = o.hWindow.setInterval(o.ohInterval, o.interval);
}
function FActiveConsole_push(active){
	if(active){
		active.id = this.actives.sync(active);
		active.name = 'T:' + RStr.lpad(active.id, 4, '0');
	}
}
function FActiveConsole_process(active){
	if(active){
		switch(active.status){
			case EActive.Sleep:
				break;
			case EActive.Active:
				active.process(this.interval);
				break;
			case EActive.Cancel:
				this.actives.removeItem(active);
				break;
		}
	}
}
function FActiveConsole_processAll(){
	var o = this;
	if(o.active){
		o.working = true;
		var as = o.actives;
		try{
			for(var n=0; n<as.count; n++){
				o.process(as.get(n));
			}
		}catch(e){
			o.active = false;
			alert('Stop active console.');
		}
		o.working = false;
	}
}
function FActiveConsole_ohInterval(){
	var ac = this._activeConsole;
	if(ac && !ac.working){
		ac.processAll();
	}
}
function FActiveConsole_wait(request){
}
function FActiveConsole_release(){
	if(this.hWindow && this.intervalId){
		this.hWindow.clearInterval(this.intervalId);
	}
}
function FConsole(o){
	o = RClass.inherits(this, o, FObject);
	o.scope = EScope.Global;
	return o;
}
function FDatasetConsole(o){
	o = RClass.inherits(this, o, FConsole);
	o.scope         = EScope.Page;
	o.active        = null;
	o.activeDsCtl   = null;
	o.datasets      = new TMap();
	o.eventControls = new TMap();
	o.focusConsole  = null;
	o.onLoaded      = FDatasetConsole_onLoaded;
	o.onInterval    = FDatasetConsole_onInterval;
	o.construct     = FDatasetConsole_construct;
	o.register      = FDatasetConsole_register;
	o.find          = FDatasetConsole_find;
	o.fetch         = FDatasetConsole_fetch;
	o.update        = FDatasetConsole_update;
	o.loadDataset   = FDatasetConsole_loadDataset;
	o.movePosition  = FDatasetConsole_movePosition;
	o.refreshStatus = FDatasetConsole_refreshStatus;
	o.listView      = FDatasetConsole_listView;
	return o;
}
function FDatasetConsole_onLoaded(e){
	var o = this;
	var c = e.document.root();
	var r = RConsole.find(FMessageConsole).checkResult(c);
	if(r){
		var da = e.dsAction;
		var lc = RConsole.find(FListenerConsole);
		if(EDataAction.Fetch == da){
			e.dsControl.loadDatasets(c);
			lc.process(MDataset, EDataAction.EndFetch, this);
		}else if(EDataAction.Update == da){
			e.dsControl.loadDatasets(c);
			lc.process(MDataset, EDataAction.EndUpdate, this);
		}
	   RWindow.setEnable(true);
	}
}
function FDatasetConsole_onInterval(){
	var o = this;
	if(o.activeDsCtl && o.activeDsCtl.dsControl){
		o.refreshStatus(o.activeDsCtl);
	}
}
function FDatasetConsole_construct(){
	var o = this;
	o.base.FConsole.construct.call(o);
	o.focusConsole = RConsole.find(FFocusConsole);
	o.active = new TActive(o, o.onInterval);
	o.active.interval = 250;
	RConsole.find(FActiveConsole).push(o.active);
}
function FDatasetConsole_register(action, control){
	this.eventControls.set(action, control);
}
function FDatasetConsole_find(name){
	if(name){
		name = name.toLowerCase();
		return this.datasets.get(name);
	}
	return null;
}
function FDatasetConsole_fetch(dc, action){
	RClass.checkClass(dc, MDataset);
	if(!action){
		RMsg.fatal(o, null, 'Unknown dataset action (action={0})', action);
	}
	var o = this;
	o.activeDsCtl = dc;
	var dsName = dc.name;
	if(!dsName){
		RMsg.fatal(o, null, 'Unknown dataset name (class={0})', RClass.name(dc));
	}
	var dsSvc = dc.dsService;
	if(!dsSvc){
		RMsg.fatal(o, null, 'Unknown dataset service (dataset={0})', dsName);
	}
	var ds = dc.dsStore;
	var name = dsName.toLowerCase();
	if(ds){
		o.datasets.set(name, ds);
	}else{
		ds = o.datasets.get(name);
		if(!ds){
			ds = new TDataset();
			o.datasets.set(name, ds);
		}
		dc.dsStore = ds;
	}
	if(EDataAction.Search != action && ds.hasPage()){
		dc.onLoadDataset(ds);
	}else{
		RWindow.setEnable(false);
		var doc = new TXmlDoc();
		var root = doc.root();
		root.set('action', action);
		RConsole.find(FEnvConsole).build(root);
		var dsNode = root.create(RDataset.DATASET);
		dsNode.set('name', dsName);
		dsNode.set('page', ds.page);
		dsNode.set('page_size', ds.pageSize);
		if(EDataAction.Search == action){
			if(!dc.dsSearchConfig){
				RMsg.fatal(o, null, 'Unknown search config');
			}
			dsNode.push(dc.dsSearchConfig);
			dc.dsSearchConfig = null;
		}else{
			if(!dc.dsFormSearch.isEmpty()){
				dsNode.push(new TNode('Search', dc.dsFormSearch));
			}
			if(!dc.dsFormOrder.isEmpty()){
				dsNode.push(new TNode('Order', dc.dsFormOrder));
			}
		}
		RLog.info(o, 'Send {0} request (service={1}, config={2})', action, dsSvc, root.dump());
		var e = new TEvent(o, EXmlEvent.Send, o.onLoaded);
		e.url = RService.url(dsSvc);
		e.document = doc;
		e.dsAction = action;
		e.dsControl = dc;
		e.dsStore = ds;
		RConsole.find(FXmlConsole).process(e);
	}
}
function FDatasetConsole_loadDataset(c, f){
	var o = this;
	if(c){
		if(c.isName('Dataset')){
			if(RStr.equals(f, c.get('name'), false)){
				var ds = RClass.create(FDataset);
				ds.name = f;
				ds.loadNode(c);
				return ds;
			}
		}else{
			var ns = c.nodes;
			if(ns){
				for(var n=0; n<ns.count; n++){
					var ds = o.loadDataset(ns.get(n), f);
					if(ds){
						return ds;
					}
				}
			}
		}
	}
	return null;
}
function FDatasetConsole_movePosition(action){
	var o = this;
	var dsCtl = o.activeDsCtl;
	if(dsCtl){
		dsCtl.dsMovePosition(action);
		o.refreshStatus(dsCtl);
	}
}
function FDatasetConsole_refreshStatus(dsCtl){
	var c = null;
	var o = this;
	var dc = dsCtl.dsControl;
	c = o.eventControls.get(EDataAction.Search);
	if(c){
		c.enable(dsCtl.editSearch);
	}
	c = o.eventControls.get(EDataAction.Insert);
	if(c){
		c.enable(dsCtl.editInsert);
	}
	c = o.eventControls.get(EDataAction.Update);
	if(c){
		c.enable(dsCtl.editUpdate);
	}
	c = o.eventControls.get(EDataAction.Delete);
	if(c){
		c.enable(dsCtl.editDelete);
	}
	c = o.eventControls.get(EDataAction.Lov);
	if(c){
		var f = o.focusConsole.focusControl;
		if(RClass.isClass(f, MListView)){
			c.enable(f.canListView());
		}else{
			c.disable();
		}
	}
	var p = dc.position;
	var count = dc.count();
	c = o.eventControls.get(EDataAction.First);
	if(c){
		c.enable(p > 0);
	}
	c = o.eventControls.get(EDataAction.Prior);
	if(c){
		c.enable(p > 0);
	}
	c = o.eventControls.get(EDataAction.Next);
	if(c){
		c.enable(p < count-1);
	}
	c = o.eventControls.get(EDataAction.Last);
	if(c){
		c.enable(p < count-1);
	}
}
function FDatasetConsole_update(dc){
	RClass.checkClass(dc, MDataset);
	RConsole.find(FFocusConsole).blur();
	var o = this;
	o.activeDsCtl = dc;
	var dsName = dc.name;
	if(!dsName){
		RMsg.fatal(o, null, 'Unknown dataset name (class={0})', RClass.name(dc));
	}
	var dsSvc = dc.dsService;
	if(!dsSvc){
		RMsg.fatal(o, null, 'Unknown dataset service (dataset={0})', dsName);
	}
	var ds = dc.dsStore;
	RWindow.setEnable(false);
	var doc = new TXmlDoc();
	var root = doc.root();
	root.set('action', EDataAction.Update);
	RConsole.find(FEnvConsole).build(root);
	var dsNode = root.create(RDataset.DATASET);
	dsNode.set('name', dsName);
	for(var n=0; n<ds.count; n++){
		var r = ds.row(n);
		if(r.isChanged()){
			if(ERowStatus.Insert != r.status && ERowStatus.Delete != r.status){
				r.status = ERowStatus.Update;
			}
			dsNode.push(new TNode('Row', r.toAttrs()));
		}
	}
	RLog.info(o, 'Send update request (service={1}, config={2})', dsSvc, root.dump());
	var e = new TEvent(o, EXmlEvent.Send, o.onLoaded);
	e.url = RService.url(dsSvc);
	e.document = doc;
	e.dsAction = EDataAction.Update;
	e.dsControl = dc;
	e.dsStore = ds;
	RConsole.find(FXmlConsole).process(e);
}
function FDatasetConsole_listView(){
	var f = this.focusConsole.focusControl;
	if(f){
		f.doListView();
	}
}
function FDesignConsole(o){
	o = RClass.inherits(this, o, FConsole);
	o.scope          = EScope.Page;
	o.padWidth       = 2;
	o.padHeight      = 4;
	o.layerSplit     = 10000;
	o.layerControl   = 20000;
	o.canDesignMove  = false;
	o.mode           = null;
	o.active         = null;
	o.hoverControl   = null;
	o.hoverContainer = null;
	o.moveControl    = null;
	o.activeControl  = null;
	o.activeLnRects  = new TList();
	o.actLineCtls    = new TList();
	o.activeLines    = new TList();
	o.actRect        = null;
	o.activeRects    = new TList();
	o.posControl     = null;
	o.actControls    = new TList();
	o.actContainer   = null;
	o.clickPos       = new TPoint();
	o.movePos        = new TPoint();
	o.movePosOrg     = new TPoint();
	o.splitRect      = new TRect();
	o.parentPosPanel = null;
	o.parentPos      = new TPoint();
	o.controlPos     = new TPoint();
	o.focusConsole   = null;
	o.controls       = new Array();
	o.hSplit         = null;
	o.hSplitPanel    = null;
	o.ohDragStart    = FDesignConsole_ohDragStart;
	o.ohDrag         = FDesignConsole_ohDrag;
	o.ohDragStop     = FDesignConsole_ohDragStop;
	o.onInterval     = FDesignConsole_onInterval;
	o.construct      = FDesignConsole_construct;
	o.createMove     = FDesignConsole_createMove;
	o.hover          = FDesignConsole_hover;
	o.leave          = FDesignConsole_leave;
	o.design         = FDesignConsole_design;
	o.startDrag      = FDesignConsole_startDrag;
	o.drag           = FDesignConsole_drag;
	o.stopDrag       = FDesignConsole_stopDrag;
	o.showSplit      = FDesignConsole_showSplit;
	o.setFlag        = FDesignConsole_setFlag;
	return o;
}
function FDesignConsole_ohDragStart(){
	var o = this.link;
	var mc = this.linkMc;
	if(o && mc && RClass.isClass(o, MDesign)){
		if(!o.isDraging){
			mc.startDrag(o);
		}
	}
}
function FDesignConsole_ohDrag(){
	var o = this.link;
	var mc = this.linkMc;
	if(o && mc && RClass.isClass(o, MDesign)){
		mc.drag(o);
	}
}
function FDesignConsole_ohDragStop(){
	var o = this.link;
	var mc = this.linkMc;
	if(o && mc && RClass.isClass(o, MDesign)){
		mc.stopDrag(o);
	}
}
function FDesignConsole_onInterval(){
	var o = this;
	var c = o.moveControl;
	if(c && !o.movePosOrg.equals(o.movePos)){
		o.movePosOrg.assign(o.movePos);
		if(RClass.isClass(c, MHorizontal)){
			c.setBounds(o.controlPos.x, o.movePos.y - o.clickPos.y-o.parentPos.y);
		}else{
			c.setBounds(o.movePos.x-o.parentPos.x-2, o.movePos.y-o.parentPos.y-2);
		}
		o.showSplit(o.movePos.x, o.movePos.y);
		RHtml.setPixelBounds(o.hSplit, o.splitRect.left-o.parentPos.x, o.splitRect.top-o.parentPos.y, o.splitRect.width(), o.splitRect.height());
	}
}
function FDesignConsole_construct(){
	var o = this;
	o.base.FConsole.construct.call(o);
	RWindow.lsnsMdown.push(new TListener(o, o.design));
	RWindow.lsnsMover.push(new TListener(o, o.hover));
	RWindow.lsnsKup.push(new TListener(o, o.leave));
	o.active = new TActive(o, o.onInterval);
	RLog.debug(o.active, 'Push active object');
	o.active.interval = 10;
	o.active.status = EActive.Sleep;
	RConsole.find(FActiveConsole).push(o.active);
	o.hSplit = RBuilder.append(o.hSplitPanel, 'SPAN', 'FDesignConsole_Split');
	o.hSplit.zIndex = o.layerSplit;
	o.hSplit.style.display = 'none';
	RBuilder.appendEmpty(o.hSplit);
	o.focusConsole = RConsole.find(FFocusConsole);
}
function FDesignConsole_createMove(c){
	var o = this;
	var n = RClass.name(c) + c.name;
	var r = o.controls[n];
	if(!r){
		r = RClass.create(c.constructor);
		r.assign(c, EAssign.Property);
		r.build();
		RWindow.append(r);
		var h = r.panel(EPanel.Move);
		h.linkMc = o;
		h.onmousedown = o.ohDragStart;
		h.onmousemove = o.ohDrag;
		h.onmouseup = o.ohDragStop;
		o.controls[n] = r;
	}
	var hc = c.panel(EPanel.Move);
	var hr = r.panel(EPanel.Move);
	RHtml.setPixelRect(hr, RHtml.rect(hc));
	hr.className = r.style('DesignMove');
	r.show();
	return r;
}
function FDesignConsole_hover(){
	var o = this;
	if(!o.activeControl){
		var cnt = o.focusConsole.hoverContainer;
		var ctl = o.focusConsole.hoverControl;
		if(event.ctrlKey && event.altKey){
			RLog.debug(o, 'Design show border');
			var cnt = RConsole.find(FFocusConsole).hoverContainer;
			if(cnt && o.hoverContainer != cnt){
				o.hoverContainer = cnt;
				cnt.design(EDesign.Border, true);
				o.mode = EDesign.Border;
			}
		}else if(event.ctrlKey && event.shiftKey){
			if(RClass.isClass(ctl, MDesign)){
				if(o.hoverControl != ctl){
					if(o.hoverControl){
						o.hoverControl.onDesignLeave();
					}
					o.hoverControl = ctl;
					ctl.onDesignEnter();
				}
			}
			if(cnt && o.hoverContainer != cnt){
				o.hoverContainer = cnt;
				RLog.debug(o, 'Design move hover (container={1}, control={2})', RClass.dump(cnt), RClass.dump(ctl));
				cnt.design(EDesign.Move, true);
				o.mode = EDesign.Move;
			}
		}
	}
}
function FDesignConsole_leave(){
	var o = this;
	if(o.mode == EDesign.Move && !event.ctrlKey){
		RLog.debug(o, 'Design move leave (container={1}, control={2})', RClass.dump(o.hoverContainer), RClass.dump(o.activeControl));
		if(o.activeControl){
			o.stopDrag(o.activeControl, true);
		}else if(o.hoverControl){
			o.hoverControl.onDesignLeave();
			o.hoverControl = null;
		}
		if(o.hoverContainer){
			o.hoverContainer.design(EDesign.Move, false);
			o.hoverContainer = null;
		}
	}
	if(o.mode == EDesign.Border && !event.ctrlKey && !event.shiftKey){
		if(o.hoverContainer){
			o.hoverContainer.design(EDesign.Border, false);
			o.hoverContainer = null;
		}
	}
}
function FDesignConsole_design(){
	var o = this;
	if(o.canDesignMove){
		var e = RWindow.event();
		var ctl = RControl.htmlControl(e.srcElement, MDesign);
		if(ctl){
			var cnt = ctl.topControl(FContainer);
			if(ctl && cnt){
				RLog.debug(o, 'Design control (container={1}, control={2})', RClass.dump(cnt), RClass.dump(ctl));
				o.activeControl = ctl;
				o.actContainer = cnt;
				o.startDrag(cnt, ctl);
			}
		}
	}
}
function FDesignConsole_startDrag(cnt, ctl){
	var o = this;
	var hp = o.actContainer.panel(EPanel.Container);
	if(o.hSplitPanel != hp){
		o.hSplitPanel = hp;
		o.hSplitPanel.appendChild(o.hSplit);
	}
	var mc = o.moveControl = o.createMove(ctl);
	var mp = mc.panel(EPanel.Move);
	var h = ctl.hPanel;
	o.parentPosPanel = RHtml.posParent(h);
	RHtml.toPoint(o.parentPos, o.parentPosPanel);
	RHtml.toPoint(o.controlPos, h, o.parentPosPanel);
	var cp = RWindow.clientPos();
	var mr = ctl.calcRect();
	h.zIndex = o.layerControl;
	h.width = mr.width();
	RHtml.setRect(h, mr);
	o.clickPos.set(cp.x - mr.left, cp.y - mr.top);
	o.movePos.assign(cp);
	o.actRect = o.actContainer.calcRect();
	RLog.debug(o, 'rect={1}, control={2}, parent={3}:{4}', o.actRect.dump(), o.controlPos.dump(), o.parentPos.dump(), o.parentPosPanel.currentStyle.overflow);
	var cs = o.actContainer.controls;
	var ar = o.activeRects;
	ar.clear();
	for(var n=0; n<cs.count; n++){
		var c = cs.value(n);
		var r = RHtml.rect(c.panel(EPanel.Design));
		if(!RClass.isClass(c, MHorizontal)){
			var pr = RHtml.rect(c.hPanelLine);
			r.top = pr.top;
			r.bottom = pr.bottom;
		}
		r.control = c;
		ar.push(r);
	}
	var al = o.activeLines;
	al.clear();
	var lastLine = 0;
	for(var n=0; n<ar.count; n++){
		var r = ar.get(n);
		if(!al.contains(r.top)){
			al.push(r.top);
		}
		lastLine = Math.max(r.bottom, lastLine);
	}
	al.push(lastLine+3);
	o.hSplit.style.display = 'block';
	mp.setCapture();
	RLog.debug(o, 'rect={1}, control={2}, parent={3}:{4}', o.movePos.dump(), o.splitRect.left-o.parentPos.x, o.splitRect.top-o.parentPos.y, o.splitRect.width(), o.splitRect.height());
	ctl.onDesignBegin();
	o.active.status = EActive.Active;
	return;
}
function FDesignConsole_drag(ctl){
	RWindow.clientPos(this.movePos);
}
function FDesignConsole_stopDrag(ctl, cancel){
	var o = this;
	var h = ctl.hPanel;
	o.active.status = EActive.Sleep;
	ctl.onDesignEnd();
	o.hSplit.style.display = 'none';
	if(!cancel){
		o.actContainer.moveChild(o.activeControl, o.posControl, o.posAction);
	}
	o.moveControl.panel(EPanel.Move).releaseCapture();
	o.moveControl.hide();
	o.moveControl = null;
	o.activeControl.onDesignEnd();
	o.activeControl = null;
	o.actContainer = null;
}
function FDesignConsole_showSplit(x, y){
	var o = this;
	var cs = o.actContainer.controls;
	if(cs){
		var sr = o.splitRect;
		var vpos = RClass.isClass(this.activeControl, MHorizontal);
		for(var n=0; n<o.activeLines.count; n++){
			var lc = o.activeLines.get(n);
			var ln = o.activeLines.get(n+1);
			if(y > lc-o.padHeight && y < lc+o.padHeight){
				sr.setBounds(o.actRect.left, lc-2, o.actRect.width(), 3);
				if(n == o.activeLines.count-1){
					o.posAction = EPosition.LineAfter;
					o.posControl = o.activeRects.last().control;
					return;
				}
				for(var i=0; i<o.activeRects.count; i++){
					var r = o.activeRects.get(i);
					if(r.top == lc){
						o.posAction = EPosition.LineBefore;
						o.posControl = r.control;
						return;
					}
				}
				return;
			}else if(!vpos && y > lc+o.padHeight && y < ln-o.padHeight){
				var first = true;
				var alr = o.activeLnRects;
				alr.clear();
				var rcount = o.activeRects.count;
				for(var i=0; i<rcount; i++){
					var r = o.activeRects.get(i);
					if(r.top == lc && !RClass.isClass(r.control, MHorizontal)){
						o.activeLnRects.push(r);
					}
				}
				var rcount = o.activeLnRects.count;
				for(var i=0; i<rcount; i++){
					var r = o.activeLnRects.get(i);
					if(x < r.right){
						o.posAction = EPosition.Before;
						o.posControl = r.control;
						sr.setBounds(r.left-2, r.top-1, 3, r.height());
						return;
					}else if(i == rcount-1){
						o.posAction = EPosition.After;
						o.posControl = r.control;
						sr.setBounds(r.right-2, r.top-1, 3, r.height());
						return;
					}
				}
				return;
			}
		}
	}
}
function FDesignConsole_setFlag(flag, value){
	if(EDesign.Move == flag){
		this.canDesignMove = value;
	}
}
function FEditConsole(o){
	o = RClass.inherits(this, o, FConsole);
	o.scope        = EScope.Page;
	o.focusEditor  = null;
	o.editors      = new TMap();
	o.makeName     = FEditConsole_makeName;
	o.enter        = FEditConsole_enter;
	o.leave        = FEditConsole_leave;
	o.focus        = FEditConsole_focus;
	o.blur         = FEditConsole_blur;
	o.lost         = FEditConsole_lost;
	return o;
}
function FEditConsole_makeName(cls, name){
	return name ? name + '@' + RClass.name(cls) : RClass.name(cls);
}
function FEditConsole_enter(editable, cls){
	RClass.checkClass(editable, MEditable);
	var name = RClass.name(cls);
	var editor = this.hoverEditors.get(name);
	if(!editor){
		editor = RClass.create(cls);
		editor.build();
		this.hoverEditors.set(name, editor);
	}
	this.hoverEditor = editor;
	editor.editable = editable;
	editor.show();
	return editor;
}
function FEditConsole_leave(editor){
	var o = this;
	if(o.hoverEditor != o.focusEditor){
		editor = RObj.nvl(editor, o.hoverEditor);
		o.hoverEditor = null;
		RLog.debug(o, 'Leave {0}', RClass.dump(editor));
	}
}
function FEditConsole_focus(editable, cls, name){
	var o = this;
	RClass.checkClass(editable, MEditable);
	var name = o.makeName(cls, name);
	var editor = o.editors.get(name);
	if(!editor){
		editor = RClass.create(cls);
		editor.build();
		o.editors.set(name, editor);
	}
	RLog.debug(o, 'Focus editor {0} (editable={1}, name={2})', RClass.dump(editor), RClass.dump(editable), name);
	if(RClass.isClass(editor, FDropEditor)){
		if(o.focusEditor){
			o.focusEditor.onEditEnd();
		}
		o.focusEditor = editor;
		editor.editable = editable;
		editor.onEditBegin();
	}
	return editor;
}
function FEditConsole_blur(editor){
	var o = this;
	if(o.focusEditor){
		RLog.debug(o, 'Blur {0}', RClass.dump(editor));
		editor = RObj.nvl(editor, o.focusEditor);
		if(editor){
			editor.onEditEnd();
		}
		o.focusEditor = null;
	}
}
function FEditConsole_lost(e){
	var o = this;
	o.leave(e);
	o.blur(e);
}
function FEnvConsole(o){
	o = RClass.inherits(this, o, FConsole);
	o.scope       = EScope.Page;
	o.environment = null;
	o.connect     = FEnvConsole_connect;
	o.build       = FEnvConsole_build;
	o.buildValue  = FEnvConsole_buildValue;
	o.xml         = FEnvConsole_xml;
	return o;
}
function FEnvConsole_connect(){
	var xData = RHtml.get('xEnvironment');
	if(xData){
		this.environment = RXml.makeNode(xData);
	}
}
function FEnvConsole_build(config){
	if(!this.environment){
		this.connect()
	}
	if(this.environment){
		var node = config.create('Environment');
		node.attributes().append(this.environment.attributes());
	}
}
function FEnvConsole_buildValue(){
	if(!this.environment){
		this.connect()
	}
	if(this.environment){
		var env = RHtml.get('_environment');
		if(env){
			env.value = this.environment.xml();
		}
	}
}
function FEnvConsole_xml(){
	if(!this.environment){
		this.connect()
	}
	if(this.environment){
		return this.environment.xml();
	}
	return null;
}
function FEventConsole(o){
	o = RClass.inherits(this, o, FConsole);
	o.scope      = EScope.Page;
	o.active     = null;
	o.allow      = true;
	o.allows     = new TMap();
	o.events     = new TList();
	o.listeners  = new TMap();
	o.construct  = FEventConsole_construct;
	o.register   = FEventConsole_register;
	o.process    = FEventConsole_process;
	o.add        = FEventConsole_add;
	o.push       = FEventConsole_push;
	o.allowEvent = FEventConsole_allowEvent;
	o.skipEvent  = FEventConsole_skipEvent;
	o.allowAll   = FEventConsole_allowAll;
	o.skipAll    = FEventConsole_skipAll;
	o.onlyCall   = FEventConsole_onlyCall;
	o.clear      = FEventConsole_clear;
	return o;
}
function FEventConsole_construct(){
	var o = this;
	o.base.FConsole.construct.call(o);
	o.active = new TActive(o, o.process);
	o.active.interval = 10;
	RConsole.find(FActiveConsole).push(o.active);
	RLog.debug(o, 'Add event active (active={0})', RClass.dump(o.active));
}
function FEventConsole_register(){
	alert(this);
}
function FEventConsole_process(){
	var o = this;
	var es = o.events;
	if(es.count){
		RLog.debug(o, 'Process events (count={0})', es.count);
		while(true){
			var has = false;
			for(var n=0; n<es.count; n++){
				var e = es.get(n);
				if(e){
					has = true;
					e.process();
					var ls = o.listeners.get(RMethod.name(e));
					if(ls){
						ls.process(e);
					}
					es.set(n, null)
				}
			}
			if(!has){
				break;
			}
		}
		es.clear();
	}
}
function FEventConsole_add(owner, proc){
	this.events.push(new TEvent(owner, null, proc));
}
function FEventConsole_push(e){
	var o = this;
	var n = RClass.name(e)
	if(o.allow){
		var a = true;
		if(o.allows.contains(n)){
			a = RBool.isTrue(o.allows.get(n));
		}
		if(a){
			o.events.push(e);
		}
	}
}
function FEventConsole_allowEvent(c){
	this.allows.set(RMethod.name(c), EBool.True);
}
function FEventConsole_skipEvent(c){
	this.allows.set(RMethod.name(c), EBool.False);
}
function FEventConsole_allowAll(){
	this.allow = true;
}
function FEventConsole_skipAll(){
	this.allow = false;
}
function FEventConsole_onlyCall(c, m){
	var o = this;
	o.allow = false;
	m.call(c);
	o.allow = true;
}
function FEventConsole_clear(){
	this.events.clear();
}
function FFocusConsole(o){
	o = RClass.inherits(this, o, FConsole, MRelease);
	o.scope          = EScope.Page;
	o.activeControl  = null;
	o.focusControl   = null;
	o.hoverControl   = null;
	o.hoverContainer = null;
	o.focusClasses   = new Array();
	o.storeControl   = null;
	o.onMouseDown    = FFocusConsole_onMouseDown;
	o.construct      = FFocusConsole_construct;
	o.enter          = FFocusConsole_enter;
	o.leave          = FFocusConsole_leave;
	o.focus          = FFocusConsole_focus;
	o.blur           = FFocusConsole_blur;
	o.findClass      = FFocusConsole_findClass;
	o.focusClass     = FFocusConsole_focusClass;
	o.storeFocus     = FFocusConsole_storeFocus;
	o.restoreFocus   = FFocusConsole_restoreFocus;
	o.release        = FFocusConsole_release;
	return o;
}
function FFocusConsole_onMouseDown(e){
	var o = this;
	var c = RControl.htmlControl(event.srcElement, MFocus);
	if(c){
	}else{
		o.blur(null, e);
	}
}
function FFocusConsole_construct(){
	var o = this;
	o.base.FConsole.construct.call(o);
	RWindow.lsnsMdown.push(new TListener(o, o.onMouseDown));
	RLog.debug(o, 'Add listener for window mouse down');
}
function FFocusConsole_enter(c){
	var o = this;
	if(c && c.isClass(MContainer)){
		o.hoverContainer = c;
	}else{
		o.hoverControl = c;
	}
}
function FFocusConsole_leave(c){
	var o = this;
	if(o.hoverContainer == c){
		o.hoverContainer = null;
	}
	if(o.hoverControl == c){
		o.hoverControl = null;
	}
}
function FFocusConsole_focus(c, e){
	if(!RClass.isClass(c, MFocus)){
		return;
	}
	var ec = RConsole.find(FEventConsole);
	var o = this;
	var f = o.focusControl;
	if(f == c){
		return;
	}
	if(f){
		if(!f.testBlur()){
			return;
		}
		RLog.debug(o, 'Blur old control (name={0}, instance={1})', f.name, RClass.dump(f));
		f.blur(e);
	}
	RLog.debug(o, 'Focus control (name={0}, instance={1})', c.name, RClass.dump(c));
	c.doFocus(e);
	o.focusControl = c;
	if(RClass.isClass(c, MListView)){
		o.focusClass(MListView, c);
		RConsole.find(FListenerConsole).process(MDataset, EAction.Status, o);
	}
}
function FFocusConsole_blur(c, e){
	var o = this;
	if(c){
		if(c == o.focusControl){
			RLog.debug(o, 'Blur control (name={0}, instance={1})', c.name, RClass.dump(c));
			c.doBlur(e);
			o.focusControl = null;
		}
	}else{
		var c = o.focusControl;
		if(c){
			RLog.debug(o, 'Blur control (name={0}, instance={1})', c.name, RClass.dump(c));
			c.doBlur(e);
		}
	}
}
function FFocusConsole_findClass(c){
	return this.focusClasses[RClass.name(c)];
}
function FFocusConsole_focusClass(s, c, e){
	if(!e || (e && !e.returnValue)){
		var o = this;
		var n = RClass.name(s);
		if(o.focusClasses[n] != c){
			o.focusClasses[n] = c;
			RLog.debug(o, 'Focus control {0}=[{1}]', n, RClass.dump(c));
			RConsole.find(FListenerConsole).process(s, EAction.Status, c);
		}
		if(e){
			e.returnValue = true;
		}
	}
}
function FFocusConsole_storeFocus(){
	var o = this;
	o.storeControl = o.focusControl;
}
function FFocusConsole_restoreFocus(){
	var o = this;
	if(o.storeControl){
		o.storeControl.focus();
		o.storeControl = null;
	}
}
function FFocusConsole_release(){
   this.activeFocusCtl = null;
   this.attributes = new Array();
}
function FFormConsole(o){
	o = RClass.inherits(this, o, FConsole);
	o.scope         = EScope.Page;
	o.forms         = new TMap();
	o.lovs          = new TMap();
	o.onLoaded      = FFormConsole_onLoaded;
	o.findLov       = FFormConsole_findLov;
	return o;
}
function FFormConsole_onLoaded(e){
	var o = this;
	var c = e.document.root();
	var r = RConsole.find(FMessageConsole).checkResult(c);
	if(r){
		e.dsControl.loadDatasets(c);
	   RWindow.setEnable(true);
	}
}
function FFormConsole_findLov(lv){
	var o = this;
	if(!lv.lovRefer){
		RMsg.fatal(o, null, 'Unknown lov refer');
	}
	if(!lv.lovService){
		RMsg.fatal(o, null, 'Unknown lov service');
	}
	var fn = lv.lovRefer.toLowerCase();
	var xconfig  = o.lovs.get(fn);
	if(!xconfig){
		RWindow.setEnable(false);
		var doc = new TXmlDoc();
		var root = doc.root();
		root.set('action', EDataAction.FetchLov);
		var ctlNode = root.create('Control');
		ctlNode.set('lov_refer', lv.lovRefer);
		var e = new TEvent(o, EXmlEvent.SyncSend);
		e.url = RService.url(lv.lovService);
		e.document = doc;
		xconfig = RConsole.find(FXmlConsole).process(e);
		var root = xconfig.root();
		o.lovs.set(fn, root);
		return root;
	}
	return xconfig;
}
function FHintConsole(){
   IObject.extendClass(this, FManager);
   this.className = 'FHintConsole';
   this.classHintManager = true;
   this.inFetchProcess = false;
   this.clientWindow = null;
   this.eventIntervalId = null;
   this.htmlTable = null;
   this.hintNodes = new Array();
   this.onHintInterval = mgr_hnt_onHintInterval;
   this.onEventRelease = mgr_hnt_onEventRelease;
   this.createElement = mgr_hnt_createElement;
   this.fetchHint = mgr_hnt_fetchHint;
   this.fetchHintAfter = mgr_hnt_fetchHintAfter;
   this.initialize = mgr_hnt_initialize;
   this.appendFromNode = mgr_hnt_appendFromNode;
   this.refresh = mgr_hnt_refresh;
   return this;
}
function mgr_hnt_onHintInterval(){
      HintManager.refresh();
}
function mgr_hnt_onEventRelease(){
   if(this.clientWindow && this.eventIntervalId){
      this.clientWindow.clearInterval(this.eventIntervalId);
   }
   this.eventIntervalId = null;
}
function mgr_hnt_createElement(sTag){
   return this.clientWindow ? this.clientWindow.document.createElement(sTag) : null ;
}
function mgr_hnt_fetchHint(){
   this.inFetchProcess = true;
   var oDoc = new FXMLDocument('Config');
   var oActNode = oDoc.rootNode.createNode('Action');
   oActNode.setAttribute('name', 'fetch');
   var oConnect = new FXMLConnect(SystemManager.serviceURL('hint'), oDoc);
   oConnect.onload = this.fetchHintAfter;
   oConnect.send(false);
}
function mgr_hnt_fetchHintAfter(){
   if(!DatasetManager.popupMessage(this, this.rootNode)){
      HintManager.appendFromNode(this.rootNode);
   }
   HintManager.inFetchProcess = false;
}
function mgr_hnt_initialize(oClientWindow, oParentHtml){
   this.clientWindow = oClientWindow;
   this.htmlTable = this.createElement('TABLE');
   oParentHtml.insertBefore(this.htmlTable);
   this.eventIntervalId = top.setInterval(this.onHintInterval, 10000);
}
function mgr_hnt_appendFromNode(oRootNode){
   if(oRootNode.nodes){
      var bHint = false;
      var nCount = oRootNode.nodes.length;
      for(var n=0; n<nCount; n++){
         var oHintNode = oRootNode.nodes[n];
         var sMsgId = oHintNode.attribute('hint_id');
         if(!this.hintNodes[sMsgId]){
            this.hintNodes[sMsgId] = oHintNode;
            var sHintType = IString.toLowerCase(oHintNode.attribute('hint_type'));
            var sHintDate = oHintNode.attribute('hint_date');
            var sDescription = oHintNode.attribute('description');
            var oRow = this.htmlTable.insertRow(0);
            var oCell = oRow.insertCell();
            oCell.width = 20;
            oCell.appendChild(ResourceManager.image(this.clientWindow, 'sys.hint.' + sHintType, 16, 16));
            var oCell = oRow.insertCell();
            oCell.width = 120;
            oCell.innerText = IDate.format(IDate.autoParse(sHintDate), 'YYYY/MM/DD HH24:MI:SS');
            var oCell = oRow.insertCell();
            oCell.innerText = sDescription;
            bHint = true;
         }
      }
   }
}
function mgr_hnt_refresh(){
   this.fetchHint();
}
function FKeyConsole(o){
	o = RClass.inherits(this, o, FConsole);
	o.scope      = EScope.Page;
	o.allow         = true;
	o.registerAble  = true;
	o.keyLsns       = new Array();
	o.onKeyDown     = FKeyConsole_onKeyDown;
	o.construct     = FKeyConsole_construct;
	o.register      = FKeyConsole_register;
	o.allowRegister = FKeyConsole_allowRegister;
	o.skipRegister  = FKeyConsole_skipRegister;
	o.allowAll      = FKeyConsole_allowAll;
	o.skipAll       = FKeyConsole_skipAll;
	return o;
}
function FKeyConsole_onKeyDown(e){
	var o = this;
	var k = REnum.tryDecode(EKey, e.keyCode);
	if(k && o.allow){
		var ls = o.keyLsns[k];
		if(ls){
			ls.process(o, e);
			e.keyCode = null;
			e.returnValue = false;
		}
	}
}
function FKeyConsole_construct(){
	var o = this;
	o.base.FConsole.construct.call(o);
	RWindow.lsnsKdown.push(new TListener(o, o.onKeyDown));
}
function FKeyConsole_register(key, lsn){
	var o = this;
	if(key && lsn && o.registerAble){
		if(RInt.isInt(key)){
			key = REnum.decode(EKey, key);
		}
		var ks = o.keyLsns;
		var ls = ks[key];
		if(!ls){
			ls = ks[key] = new TListeners();
		}
		ls.push(lsn);
	}
}
function FKeyConsole_allowRegister(){
	this.registerAble = true;
}
function FKeyConsole_skipRegister(){
	this.registerAble = false;
}
function FKeyConsole_allowAll(){
	this.allow = true;
}
function FKeyConsole_skipAll(){
	this.allow = false;
}
function FListenerConsole(o){
	o = RClass.inherits(this, o, FConsole);
	o.scope     = EScope.Page;
	o.listeners = null;
	o.register  = FListenerConsole_register;
	o.process   = FListenerConsole_process;
	return o;
}
function FListenerConsole_register(clazz, action, owner, method){
	var o = this;
	if(!o.listeners){
		o.listeners = new Array();
	}
	var l = new TListener(owner, method);
	var name = RClass.name(clazz) + '@' + action;
	var lsns = o.listeners[name];
	if(!lsns){
		lsns = o.listeners[name] = new TListeners();
	}
	lsns.push(l);
	return l;
}
function FListenerConsole_process(clazz, action, sender, params){
	var o = this;
	if(o.listeners){
		var name = RClass.name(clazz) + '@' + action;
		var lsns = o.listeners[name];
		if(lsns){
			lsns.process(sender, params);
		}
	}
}
function FLoggerConsole(o){
	o = RClass.inherits(this, o, FConsole);
	o.scope      = EScope.Page;
	o.iLogger    = null;
	o.onKeyDown  = FLoggerConsole_onKeyDown;
	o.construct  = FLoggerConsole_construct;
	o.connect    = FLoggerConsole_connect;
	o.disconnect = FLoggerConsole_disconnect;
	o.output     = FLoggerConsole_output;
	return o;
}
function FLoggerConsole_onKeyDown(e){
	if(e.shiftKey && e.ctrlKey && EKey.L == e.keyCode){
		this.connect();
	}
}
function FLoggerConsole_construct(){
	var o = this;
	o.base.FConsole.construct.call(o);
	RWindow.lsnsKdown.push(new TListener(o, o.onKeyDown));
}
function FLoggerConsole_connect(){
	var o = this;
	if(!o.iLogger){
		o.iLogger = new ActiveXObject('MobjLibrary.Logger');
	}
}
function FLoggerConsole_disconnect(){
	this.iLogger = null;
}
function FLoggerConsole_output(level, obj, method, msg, stack){
	var o = this;
	if(o.iLogger){
		o.iLogger.Output(level + ' [' + RStr.rpad(RClass.dump(obj), 30) + '] ' + RStr.rpad(msg, 120) + ' [' + stack + ']');
	}
}
function FLoggerConsole_xml(){
	if(!this.environment){
		this.connect()
	}
	if(this.environment){
		return this.environment.xml();
	}
	return null;
}
function FMessageConsole(o){
	o = RClass.inherits(this, o, FConsole, MStyle);
	o.scope        = EScope.Page;
	o.result       = new Array();
	o.attributes   = new Array();
	o.messageBox   = null;
	o.messageWindow = null;
	o.parse        = FMessageConsole_parse;
	o.popupMessage = FMessageConsole_popupMessage;
	o.closeMessage = FMessageConsole_closeMessage;
	o.checkResult  = FMessageConsole_checkResult;
	return o;
}
function FMessageConsole_parse(config){
	var msgs = null;
	var msgsNode = config.find('Messages');
	if(msgsNode && msgsNode.nodes && msgsNode.nodes.count){
		msgs = new TMessages();
		for(var n=0; n<msgsNode.nodes.count; n++){
			var node = msgsNode.node(n);
			var msg = new TMessage();
			msg.loadConfig(msgsNode.node(n));
			msgs.push(msg);
		}
	}
	return msgs;
}
function FMessageConsole_popupMessage(msgs){
	var o = this;
	var mw = o.messageWindow;
	if(!mw){
		mw = o.messageWindow = RControl.create(FMessageWindow);
	}
	mw.loadMessages(msgs);
	mw.show();
}
function FMessageConsole_closeMessage(){
	RWindow.setEnable(true);
	this.hPanel.style.display = 'none';
}
function FMessageConsole_checkResult(config){
	var o = this;
	var msgs = o.parse(config);
	if(msgs){
		o.popupMessage(msgs)
		return false;
	}
	return true;
}
function FMonitorConsole(o){
	o = RClass.inherits(this, o, FConsole);
	o.scope      = EScope.Global;
	o.working    = false;
	o.interval   = 10;
	o.intervalId = null;
	o.monitors   = new TList();
	o.hWindow    = null;
	o.doInterval = FMonitorConsole_doInterval;
	o.push       = FMonitorConsole_push;
	o.process    = FMonitorConsole_process;
	o.processAll = FMonitorConsole_processAll;
	o.startup    = FMonitorConsole_startup;
	o.wait       = FMonitorConsole_wait;
	o.release    = FMonitorConsole_release;
	return o;
}
function FMonitorConsole_push(monitor){
	this.startup();
	monitor.id = this.monitors.sync(monitor);
	monitor.name = 'T:' + RStr.lpad(monitor.id, 4, '0');
	monitor.status = EMonitor.Active;
}
function FMonitorConsole_process(monitor){
	if(monitor){
		switch(monitor.status){
			case EMonitor.Sleep:
				break;
			case EMonitor.Active:
				monitor.process(this.interval);
				break;
			case EMonitor.Cancel:
				this.monitors.removeItem(monitor);
				break;
		}
	}
}
function FMonitorConsole_processAll(){
	this.working = true;
	var monitors = this.monitors;
	for(var n=0; n<monitors.count; n++){
		this.process(monitors.get(n));
	}
	this.working = false;
}
function FMonitorConsole_doInterval(){
	var con = RGlobal.get(FMonitorConsole);
	if(con && !con.working){
		con.processAll();
	}
}
function FMonitorConsole_startup(){
	if(!this.hWindow){
		this.hWindow = window;
		this.intervalId = this.hWindow.setInterval(this.doInterval, this.interval);
	}
}
function FMonitorConsole_wait(request){
}
function FMonitorConsole_release(){
	if(this.hWindow && this.intervalId){
		this.hWindow.clearInterval(this.intervalId);
	}
}
function FMoveConsole(o){
	o = RClass.inherits(this, o, FConsole);
	o.ResizeRange   = 4;
	o.ResizeBorder  = 3;
	o.WidthMin      = 200;
	o.HeightMin     = 100;
	o.LayerMax      = 20000;
	o.LayerEdit     = 30000;
	o.scope         = EScope.Page;
	o.active        = null;
	o.activeControl = null;
	o.activePanel   = null;
	o.origin        = new TPoint3(0, 0, 10000);
	o.clickPos      = new TPoint();
	o.movePos       = new TPoint();
	o.ohStartDrag   = FMoveConsole_ohStartDrag;
	o.ohDrag        = FMoveConsole_ohDrag;
	o.ohStopDrag    = FMoveConsole_ohStopDrag;
	o.onInterval    = FMoveConsole_onInterval;
	o.construct     = FMoveConsole_construct;
	o.nextPoint     = FMoveConsole_nextPoint;
	o.nextLayer     = FMoveConsole_nextLayer;
	o.registerDrag  = FMoveConsole_registerDrag;
	o.startDrag     = FMoveConsole_startDrag;
	o.drag          = FMoveConsole_drag;
	o.stopDrag      = FMoveConsole_stopDrag;
	return o;
}
function FMoveConsole_ohStartDrag(){
	var o = this.link;
	var mc = this.linkMc;
	if(mc && RClass.isClass(o, MMoveable)){
		if(o.canMove && !o.inMoving){
			o.startDrag(EDrag.Move);
			mc.startDrag(o);
			o.inMoving = true;
		}
	}
}
function FMoveConsole_ohDrag(){
	var o = this.link;
	var mc = this.linkMc;
	if(mc && mc.activeControl==o){
		mc.drag(o);
	}
}
function FMoveConsole_ohStopDrag(){
	var o = this.link;
	var mc = this.linkMc;
	if(mc && mc.activeControl==o){
		o.inMoving = false;
		o.stopDrag(EDrag.Move);
		mc.stopDrag(o);
	}
}
function FMoveConsole_onInterval(){
	var c = this.activeControl;
	if(c){
		c.setBounds(Math.max(this.movePos.x - this.clickPos.x, 0), Math.max(this.movePos.y - this.clickPos.y, 0));
	}
}
function FMoveConsole_construct(){
	var o = this;
	o.base.FConsole.construct.call(o);
	o.active = new TActive(o, o.onInterval);
	RLog.debug(o.active, 'Push active object');
	o.active.interval = 10;
	o.active.status = EActive.Sleep;
	RConsole.find(FActiveConsole).push(o.active);
}
function FMoveConsole_nextPoint(){
   var p3 = new TPoint3(this.origin.x, this.origin.y, this.origin.z);
	this.origin.resize(40, 40, 1);
   if(this.origin.x > 200 || this.origin.y > 200){
      this.origin.x = 0;
      this.origin.y = 0;
   }
   return p3;
}
function FMoveConsole_nextLayer(){
   return ++this.origin.z;
}
function FMoveConsole_registerDrag(ctl, h){
	var o = this;
	h.onmousedown = o.ohStartDrag;
	h.onmousemove = o.ohDrag;
	h.onmouseup = o.ohStopDrag;
	h.linkMc = o;
}
function FMoveConsole_startDrag(ctl){
	var o = this;
	var cp = RWindow.clientPos();
	o.activeControl = ctl;
	o.activePanel = ctl.panel(EPanel.Move);
	o.activeSize = ctl.panel(EPanel.Size);
	var mr = RHtml.rect(o.activeSize);
	o.clickPos.set(cp.x - mr.left, cp.y - mr.top);
	o.movePos.assign(cp);
	o.activePanel.setCapture();
	o.active.status = EActive.Active;
	RLog.debug(o, 'Start drag {1}', ctl);
}
function FMoveConsole_drag(ctl){
	RWindow.clientPos(this.movePos);
}
function FMoveConsole_stopDrag(ctl){
	var o = this;
	RLog.debug(o, 'Stop drag {1}', ctl);
	o.active.status = EActive.Sleep;
	o.activePanel.releaseCapture();
	o.activeControl = null;
}
function FPropertyManager(){
   this.className = 'FPropertyManager';
   this.m_oPropertyList = new FList();
   this.onLoadDefineAfter = mgr_pty_onLoadDefineAfter;
   this.onLoadValueAfter = mgr_pty_onLoadValueAfter;
   this.onSaveValueAfter = mgr_pty_onSaveValueAfter;
   this.loadDefine = mgr_pty_loadDefine;
   this.loadValue = mgr_pty_loadValue;
   this.saveValue = mgr_pty_saveValue;
   return this;
}
function mgr_pty_loadDefine(oPtyCtl, sName){
   var oPtyNode = this.m_oPropertyList.nameValue(sName);
   if(oPtyNode){
      oPtyCtl.onLoadDefineAfter(oPtyNode);
   }else{
      oPtyCtl.disableWindow();
      var oDoc = new FXMLDocument('Config');
      var oActNode = oDoc.rootNode.createNode('Action');
      oActNode.value = 'property.define';
      var oDefNode = oDoc.rootNode.createNode('Property');
      oDefNode.setAttribute('name', sName);
      var oConnect = new FXMLConnect(SystemManager.serviceURL('sys.pty'), oDoc);
      oConnect.propertyControl = oPtyCtl;
      oConnect.propertyName = sName;
      oConnect.propertyList = this.m_oPropertyList;
      oConnect.onload = this.onLoadDefineAfter;
      oConnect.send();
   }
}
function mgr_pty_onLoadDefineAfter(){
   var oPtyNode = null;
   var arNodes = this.rootNode.nodes;
   if(arNodes){
      for(var n=0; n<arNodes.length; n++){
         var oNode = arNodes[n];
         if(IString.equals(oNode.name, 'propertyconfig')){
            var sItemName = oNode.attribute('name');
            this.propertyList.setNameValue(sItemName, oNode);
            if(IString.equals(sItemName, this.propertyName)){
               oPtyNode = oNode;
            }
         }
      }
   }
   if(oPtyNode){
      this.propertyControl.onLoadDefineAfter(oPtyNode);
   }else{
      ILogger.fatal(this, 'loadDefine', 'Not find Property define: ' + sPtyName);
   }
   this.propertyControl.enableWindow();
}
function mgr_pty_loadValue(oPtyCtl, sService, sPtyType, sPtyId){
   var oDoc = new FXMLDocument('Config');
   var oActNode = oDoc.rootNode.createNode("Action");
   oActNode.value = 'property.value.load';
   var oPtyDefNode = oDoc.rootNode.createNode("PropertyValue");
   oPtyDefNode.setAttribute('pty_type', sPtyType);
   oPtyDefNode.setAttribute('pty_id', sPtyId);
   var oConnect = new FXMLConnect(sService, oDoc);
   oConnect.propertyControl = oPtyCtl;
   oConnect.propertyType = sPtyType;
   oConnect.propertyId = sPtyId;
   oConnect.onload = this.onLoadValueAfter;
   oConnect.send();
}
function mgr_pty_onLoadValueAfter(){
   var oValNode = null;
   var arNodes = this.rootNode.nodes;
   if(arNodes){
      for(var n=0; n<arNodes.length; n++){
         var oNode = arNodes[n];
         if(IString.equals(oNode.attribute('pty_type'), this.propertyType) &&
            IString.equals(oNode.attribute('pty_id'), this.propertyId)){
            oValNode = oNode;
         }
      }
   }
   if(oValNode){
      this.propertyControl.onLoadValueAfter(oValNode);
   }else{
      ILogger.fatal(this, 'loadValue', 'Not load ' + this.propertyType + ' value: ' + this.propertyId);
   }
}
function mgr_pty_saveValue(oPtyCtl, sService, oPtyNode){
   oPtyCtl.clientWindow.document.body.disabled = true;
   var oDoc = new FXMLDocument('Config');
   var oActNode = oDoc.rootNode.createNode("Action");
   oActNode.value = 'property.value.save';
   oDoc.rootNode.push(oPtyNode);
   var oConnect = new FXMLConnect(sService, oDoc);
   oConnect.ptyCtl = oPtyCtl;
   oConnect.clientWindow = oPtyCtl.clientWindow;
   oConnect.onload = this.onSaveValueAfter;
   oConnect.send();
}
function mgr_pty_onSaveValueAfter(){
   this.ptyCtl.enableWindow();
}
function FResultConsole(o){
	o = RClass.inherits(this, o, FConsole);
	o.scope          = EScope.Page;
	o.executeCommand = FResultConsole_executeCommand;
	o.checkService   = FResultConsole_checkService;
	return o;
}
function FResultConsole_executeCommand(command){
	var name = command.get('name');
	if(EResultCommand.TreeReload == name){
		var tree = RGlobal.get(FTreeView, command.get('tree'));
		if(tree){
			tree.reload();
		}
	}else if(EResultCommand.TreeNodeRefresh == name){
		var tree = RGlobal.get(FTreeView, command.get('tree'));
		if(tree){
			var uuid = command.get('uuid');
			if(uuid){
				var node = tree.findByUuid(uuid);
				if(node){
					tree.reloadNode(node);
				}else{
					return alert("Can't find tree node. (uuid="+uuid+")");
				}
			}else{
				tree.reloadNode();
			}
		}
	}else if(EResultCommand.TreeParentRefresh == name){
		var tree = RGlobal.get(FTreeView, command.get('tree'));
		if(tree){
			tree.reloadNode(null, true);
		}
	}else if(EResultCommand.PageRedirect == name){
		var action = command.get('action');
		var page = RGlobal.context(command.get('page'));
		if(action){
			page += '?do=' + action;
		}
		fmMain.action = page;
		fmMain.target = '';
		fmMain.submit();
	}
}
function FResultConsole_checkService(config){
	var o = this;
	if(config){
		if(!RConsole.find(FMessageConsole).checkResult(config)){
			return false;
		}
		var cmdsNode = config.find('Commands');
		if(cmdsNode && cmdsNode.nodes && cmdsNode.nodes.count){
			for(var n=0; n<cmdsNode.nodes.count; n++){
				var node = cmdsNode.node(n);
				if(node.isName('Command')){
					o.executeCommand(node);
				}
			}
		}
		RConsole.find(FFocusConsole).restoreFocus();
	}
	return true;
}
function FSizeConsole(o){
	o = RClass.inherits(this, o, FConsole);
	o.activeControl = null;
	o.cursor        = null;
	o.movePos       = new TPoint();
	o.priorRect     = new TRect();
	o.moveRect      = new TRect();
	o.scope         = EScope.Page;
	o.ohStartDrag   = FSizeConsole_ohStartDrag;
	o.ohDrag        = FSizeConsole_ohDrag;
	o.ohStopDrag    = FSizeConsole_ohStopDrag;
	o.onInterval    = FSizeConsole_onInterval;
	o.construct     = FSizeConsole_construct;
	o.registerDrag  = FSizeConsole_registerDrag;
	o.startDrag     = FSizeConsole_startDrag;
	o.drag          = FSizeConsole_drag;
	o.stopDrag      = FSizeConsole_stopDrag;
	return o;
}
function FSizeConsole_ohStartDrag(){
	var o = this.link;
	var sc = this.linkSc;
	if(sc && RClass.isClass(o, MSizeable)){
		if(!o.isDraging){
			if(sc.startDrag(o)){
				o.startDrag(EDrag.Size);
			}
		}
	}
}
function FSizeConsole_ohDrag(){
	var o = this.link;
	var sc = this.linkSc;
	if(sc && RClass.isClass(o, MSizeable)){
		sc.drag(o);
	}
}
function FSizeConsole_ohStopDrag(){
	var o = this.link;
	var sc = this.linkSc;
	if(sc && sc.activeControl==o){
		o.stopDrag(EDrag.Size);
		sc.stopDrag(o);
	}
}
function FSizeConsole_onInterval(){
	if(this.activeControl){
		var mr = this.moveRect;
		this.activeControl.setBounds(mr.left, mr.top, mr.right, mr.bottom);
	}
}
function FSizeConsole_construct(){
	var o = this;
	o.base.FConsole.construct.call(o);
	o.active = new TActive(o, o.onInterval);
	RLog.debug(o.active, 'Push active object');
	o.active.interval = 10;
	o.active.status = EActive.Sleep;
	RConsole.find(FActiveConsole).push(o.active);
}
function FSizeConsole_registerDrag(ctl, h){
	var o = this;
	h.onmousedown = o.ohStartDrag;
	h.onmousemove = o.ohDrag;
	h.onmouseup = o.ohStopDrag;
	h.linkSc = o;
}
function FSizeConsole_startDrag(ctl){
	var cur = ctl.cursor();
	if(cur == ECursor.Default){
		return false;
	}
	var o = this;
	var cp = RWindow.clientPos();
	this.activeControl = ctl;
	this.activePanel = ctl.panel(EPanel.Size);
	var mr = RHtml.rect(this.activePanel);
	this.moveRect.assign(mr);
	this.priorRect.set(mr.left-cp.x, mr.top-cp.y, mr.right-cp.x, mr.bottom-cp.y);
	this.cursor = cur;
	this.activePanel.setCapture();
	o.active.status = EActive.Active;
	return true;
}
function FSizeConsole_drag(ctl){
	if(this.activeControl){
		var pr = this.priorRect;
		var mr = this.moveRect;
		var cp = RWindow.clientPos();
		switch(this.cursor){
			case ECursor.NorthWest:
				mr.top = pr.top + cp.y;
				mr.left = pr.left + cp.x;
				break;
			case ECursor.SouthWest:
				mr.bottom = pr.bottom + cp.y;
				mr.left = pr.left + cp.x;
				break;
			case ECursor.SouthEast:
				mr.bottom = pr.bottom + cp.y;
				mr.right = pr.right + cp.x;
				break;
			case ECursor.NorthEast:
				mr.top = pr.top + cp.y;
				mr.right = pr.right + cp.x;
				break;
			case ECursor.West:
				mr.left = pr.left + cp.x;
				break;
			case ECursor.South:
				mr.bottom = pr.bottom + cp.y;
				break;
			case ECursor.East:
				mr.right = pr.right + cp.x;
				break;
			case ECursor.North:
				mr.top = pr.top + cp.y;
				break;
		}
	}else{
		ctl.setCursor();
	}
}
function FSizeConsole_stopDrag(ctl){
	var o = this;
	o.active.status = EActive.Sleep;
	this.activePanel.releaseCapture();
	this.activeControl = null;
	this.cursor = null;
}
function FTrackManager(){
   IObject.extendClass(this, FManager);
   this.className = 'FMoveManager';
   this.trackCount = 20;
   this.clientWindow = null;
   this.tracker = null;
   this.messageDate = new Array();
   this.messageRef = new Array();
   this.messageInfo = new Array();
   this.eventIntervalId = null;
   this.windowTrack = null;
   this.htmlTable = null;
   this.onInterval = mgr_trc_onInterval;
   this.onEventMousedown = mgr_trc_onEventMousedown;
   this.onEventRelease = mgr_trc_onEventRelease;
   this.initialize = mgr_trc_initialize;
   this.createWindow = mgr_trc_createWindow;
   this.createElement = mgr_trc_createElement;
   this.refresh = mgr_trc_refresh;
   this.push = mgr_trc_push;
   return this;
}
function mgr_trc_onInterval(){
   TrackManager.refresh();
}
function mgr_trc_onEventRelease(){
   if(this.clientWindow && this.eventIntervalId){
      this.clientWindow.clearInterval(this.eventIntervalId);
   }
   this.clientWindow = null;
   this.windowTrack = null;
}
function mgr_trc_createElement(sTag){
   return this.clientWindow ? this.clientWindow.createElement(sTag) : null ;
}
function mgr_trc_initialize(oClientWindow){
   this.clientWindow = oClientWindow;
}
function mgr_trc_createWindow(oClientWindow){
   oClientWindow = oClientWindow ? oClientWindow : this.clientWindow;
   this.windowTrack = new FWinTrackCtl()
   this.windowTrack.clientWindow = oClientWindow;
   this.windowTrack.show();
   this.windowTrack.setLeft(10);
   this.windowTrack.setTop(oClientWindow.document.body.offsetHeight - 150);
   this.windowTrack.setWidth(oClientWindow.document.body.offsetWidth-20);
   this.windowTrack.setHeight(140);
   this.windowTrack.focus();
   this.clientWindow = oClientWindow;
   this.htmlTable = this.windowTrack.htmlTable;
   this.eventIntervalId = this.clientWindow.setInterval(this.onInterval, 20);
}
function mgr_trc_push(oRef, sMessage){
   var sDate = IDate.format(new FDate(), 'YYYY/MM/DD HH24:MI:SS')
   var sRef = '[null]';
   if(oRef){
      if(oRef.className){
         if(oRef.name){
            sRef = '[' + oRef.className + '->' + oRef.name + ']';
         }else{
            sRef = '[' + oRef.className + ']';
         }
      }else if(oRef.tagName){
         sRef = '[' + oRef.tagName + ']';
      }else{
         sRef = '[' + oRef + ']';
      }
   }
   this.messageDate.push(sDate);
   this.messageRef.push(sRef);
   this.messageInfo.push(sMessage);
}
function mgr_trc_refresh(){
   if(this.htmlTable){
      var nLength = this.messageDate.length;
      if(nLength > 0){
         for(var n=0; n<nLength; n++){
            var oRow = this.htmlTable.insertRow(0);
            var oCell = oRow.insertCell();
            oCell.noWrap = true;
            oCell.innerText = this.messageDate[n];
            var oCell = oRow.insertCell();
            oCell.noWrap = true;
            oCell.innerText = this.messageRef[n];
            var oCell = oRow.insertCell();
            oCell.noWrap = true;
            oCell.innerText = this.messageInfo[n];
            var nRowLength = this.htmlTable.rows.length;
            if(nRowLength > this.trackCount){
               this.htmlTable.rows[nRowLength-1].removeNode(true);
            }
         }
         this.messageDate = this.messageDate.slice(nLength);
         this.messageRef = this.messageRef.slice(nLength);
         this.messageInfo = this.messageInfo.slice(nLength);
      }
   }
}
function mgr_trc_onEventMousedown(){
   if(this.clientWindow){
      var oEvent = this.clientWindow.event;
      if(oEvent && oEvent.altKey && oEvent.ctrlKey && oEvent.button == 2){
         if(!this.windowTrack){
            this.createWindow();
         }else{
            var nLength = this.htmlTable.rows.length;
            for(var n=nLength-1; n>=0; n--){
               this.htmlTable.rows[n].removeNode(true);
            }
            this.windowTrack.show();
         }
      }
   }
}
function FValidConsole(o){
	o = RClass.inherits(this, o, FConsole);
	o.scope      = EScope.Page;
	o.validFocus = FValidConsole_validFocus;
	o.notEmpty   = FValidConsole_notEmpty;
	o.validDate  = FValidConsole_validDate;
	return o;
}
function FValidConsole_validFocus(){
   if(this.validControl && this.validControl.focus){
      this.validControl.focus(true);
   }
   this.validControl = null;
}
function FValidConsole_notEmpty(sValue, sLabel){
   if(IString.isEmpty(sValue)){
      ILogger.contextValid(this, 'notEmpty', this.CONTEXT_VALID_NULL, sLabel);
      return false;
   }
   return true;
}
function FValidConsole_validDate(sValue, sLabel){
   if(!IString.isEmpty(sValue)){
      if(!IDate.autoParse(sValue)){
         ILogger.contextValid(this, 'validDate', this.CONTEXT_VALID_DATE, sLabel);
         return false;
      }
   }
   return true;
}
function IWindowConsole(o, create){
	if(!create){return this;}
	o = IClass.inherits(this, o, FConsole);
	o.activeWindow = null;
	o.windows      = null;
	o.defines      = null;
	o.construct    = IWindowConsole_construct;
	o.create       = IWindowConsole_create;
	o.loadDefine   = IWindowConsole_loadDefine;
	return o;
	o.focusWinCtl = null;
	o.activeForm = null;
	o.activeControl = null;
	o.maxFlag = false;
	o.m_oDefinePool = new FList();
	o.clientWindow = null;
	o.EVENT_MAX = 101;
	o.EVENT_CLOSE = 102;
	o.EVENT_CLOSEALL = 103;
   this.onEventMousedown = IWindowConsole_onEventMousedown;
   this.onSaveDefineAfter = IWindowConsole_onSaveDefineAfter;
   this.onEventRelease = IWindowConsole_onEventRelease;
   this.initialize = IWindowConsole_initialize;
   this.hasWindow = IWindowConsole_hasWindow;
   this.focus = IWindowConsole_focus;
   this.saveDefine = IWindowConsole_saveDefine;
   this.findWindow = IWindowConsole_findWindow;
   this.releaseWindowName = IWindowConsole_releaseWindowName;
   this.releaseWindow = IWindowConsole_releaseWindow;
   this.doFrameAction = IWindowConsole_doFrameAction;
   this.setMaxWindow = IWindowConsole_setMaxWindow;
   this.restore = IWindowConsole_restore;
   this.doProperties = IWindowConsole_doProperties;
   this.clear = IWindowConsole_clear;
   this.hideAll = IWindowConsole_hideAll;
   this.dump = IWindowConsole_dump;
   return this;
}
function IWindowConsole_construct(){
	this.windows = new TList();
	this.defines = new TNameList();
}
function IWindowConsole_create(name, hWin){
	var config = this.loadDefine(name);
	var win = IClass.create(FWindow);
IDump.dump(_id1, win);
	win.linkHtml(window);
	win.build();
	return win;
   if(this.windowList.isEmpty()){
      MoveManager.resetPosition();
   }
   var oWindow = new FCfgWindowCtl();
   oWindow.name = sWinName;
   oWindow.clientWindow = oClientWindow;
   if(sWinName){
      oWindow.show();
      oWindow.focus();
      if(this.maxFlag){oWindow.max();}
      this.windowList.add(sWinName, oWindow);
   }
   return oWindow;
}
function IWindowConsole_loadDefine(name){
	if(name == null){
		return null;
	}
	var config = this.defines.find(name);
	if(config == null){
		var doc = new TXmlDoc();
		var root = doc.root();
		var action = root.create('Action');
		action.value = 'window.config.load';
      root.create('Window', 'name', name);
		var cnn = new TXmlCnn();
		var doc = cnn.syncSend('window.xml', doc);
		return doc.root();
      var oNode = null;
      var sNodeName = null;
      var arNodes = oConnect.rootNode.nodes;
      for(var n=0; n<arNodes.length; n++){
         var oNode = arNodes[n];
         sNodeName = oNode.name.toLowerCase();
         if(sNodeName == 'window'){
            var sFullName = oNode.attribute('name');
            this.m_oDefinePool.setNameValue(sFullName, oNode);
            if(sFullName == sWinName){
               oWinNode = oNode;
            }
         }else if(sNodeName == 'dataset'){
            DatasetManager.addDefine(oNode.attribute('name'), oNode);
         }else if(sNodeName == 'searchlist'){
            SearchManager.addDefine(oNode);
         }
      }
	}
	if(!config){
		return ILogger.fatal(this, 'loadDefine', 'Not find window define: ' + sWinName);
	}
	return config;
}
function IWindowConsole_dump(){
   var sDump = this.className;
   sDump += '\n\nDefine:\n' + this.m_oDefinePool.dump();
   sDump += '\n\nWindow:\n' + this.windowList.dump();
   return sDump;
}
function IWindowConsole_clear(){
   this.focusWinCtl = null;
   this.activeWindow = null;
   this.activeForm = null;
   this.activeControl = null;
   this.m_oDefinePool = new FList();
   var nSize = this.windowList.size();
   for(var n=nSize-1; n>=0; n--){
      this.windowList.value(n).release();
   }
   this.windowList = new FList();
   IEngine.process(this, this.EVENT_CLOSEALL);
}
function IWindowConsole_hideAll(oExpWin, bDisplay){
   var nSize = this.windowList.size();
   for(var n=nSize-1; n>=0; n--){
      var oWin = this.windowList.value(n);
      if(oWin != oExpWin){
         oWin.hide(bDisplay);
      }
   }
}
function IWindowConsole_setMaxWindow(oWin){
   this.maxFlag = true;
   this.hideAll(oWin);
}
function IWindowConsole_restore(){
   var nSize = this.windowList.size();
   this.hideAll(null, true);
   for(var n=0; n<nSize; n++){
      var oWin = this.windowList.value(n);
      if(oWin.maxFlag){
         this.windowList.value(n).restore();
      }
   }
   this.maxFlag = false;
}
function IWindowConsole_initialize(oCtWin){
   this.clientWindow = oCtWin;
}
function IWindowConsole_hasWindow(){
   return !this.windowList.isEmpty();
}
function IWindowConsole_focus(oWinCtl){
   this.focusWinCtl = oWinCtl;
   if(this.maxFlag){
      oWinCtl.show();
      this.hideAll(oWinCtl, true)
      oWinCtl.max();
   }
}
function IWindowConsole_saveDefine(oWinNode, oClientWindow){
   if(oClientWindow){this.clientWindow.document.body.disabled = true;}
   if(!oWinNode){
      return LoggerUtil.fatal(this, 'saveDefine', 'Window node is null.');
   }
   var sFullName = oWinNode.attribute('full_name');
   if(!sFullName){
      return ILogger.fatal(this, 'saveDefine', 'Window full name is null.');
   }
   var oDoc = new FXMLDocument('Config');
   var oActNode = oDoc.rootNode.createNode('Action');
   oActNode.setAttribute('name', 'define.save');
   oDoc.rootNode.push(oWinNode);
   var oConnect = new FXMLConnect(SystemManager.serviceURL('window'), oDoc);
   oConnect.clientWindow = oClientWindow;
   oConnect.onload = this.onSaveDefineAfter;
   oConnect.send();
}
function IWindowConsole_onEventMousedown(oCWin){
}
function IWindowConsole_onSaveDefineAfter(){
   ILogger.info(this, 'saveDefine', 'Save Ok.');
   if(this.clientWindow){this.clientWindow.document.body.disabled = false;}
}
function IWindowConsole_findWindow(sWinName){
   return this.windowList.nameValue(sWinName);
}
function IWindowConsole_releaseWindowName(sWinName){
   var oWin = this.windowList.removeName(sWinName);
   IEngine.process(this, this.EVENT_CLOSE, oWin);
}
function IWindowConsole_releaseWindow(oWin){
   this.windowList.removeValue(oWin);
   IEngine.process(this, this.EVENT_CLOSE, oWin);
}
function IWindowConsole_doFrameAction(sAction){
   if(!this.activeForm){
      return ILogger.fatal(this, 'doFrameAction', 'Not active form!');
   }
   this.activeForm.doAction(sAction);
}
function IWindowConsole_doProperties(){
   TrackManager.push(this, 'Do properties.');
   if(!WindowManager.focusWinCtl){return;}
   var arParams = new Array();
   arParams['WindowManager'] = WindowManager;
   window.showModalDialog(SystemManager.actionURL('window'), arParams, 'dialogWidth:500px;dialogHeight:360px;resizable:no;scroll:no;edge:sunken');
}
function IWindowConsole_onEventRelease(oCWin){
   if(oCWin){
      var oSubWin = null;
      var oRemoves = new Array();
      var nSize = this.windowList.size();
      for(var n=0; n<nSize; n++){
         oSubWin = this.windowList.value(n);
         if(oSubWin.clientWindow == oCWin){
            if(oSubWin == MoveManager.focusBorder){
               MoveManager.focus(null);
            }
            oRemoves.push(oSubWin);
         }
      }
      for(var n=0; n<oRemoves.length; n++){
         this.windowList.removeValue(oRemoves[n]);
      }
   }else{
      this.windowList.clear();
      MoveManager.focus(null);
   }
}
function FXmlConsole(o){
	o = RClass.inherits(this, o, FConsole);
	o.scope       = EScope.Page;
	o.connections = new TList();
	o.onLoad      = FXmlConsole_onLoad;
	o.alloc       = FXmlConsole_alloc;
	o.process     = FXmlConsole_process;
	return o;
}
function FXmlConsole_onLoad(){
	var o = this;
	var e = o.event;
	e.document = o.document;
	e.process();
	o.event = null;
	o.document = null;
	o.isFree = true;
}
function FXmlConsole_alloc(){
	var count = this.connections.count;
	for(var n=0; n<count; n++){
		var cnn = this.connections.get(n);
		if(cnn && cnn.isFree){
			return cnn;
		}
	}
	var cnn = new TXmlCnn();
	this.connections.push(cnn);
	cnn.onLoad = this.onLoad;
	return cnn;
}
function FXmlConsole_process(event){
	var cnn = this.alloc();
	cnn.isFree = false;
	cnn.event = event;
	switch(event.code){
		case EXmlEvent.Send:
			cnn.send(event.url, event.document);
			break;
		case EXmlEvent.Receive:
			cnn.receive(event.url, event.document);
			break;
		case EXmlEvent.SyncSend:
			return cnn.syncSend(event.url, event.document);
		case EXmlEvent.SyncReceive:
			return cnn.syncReceive(event.url, event.document);
	}
}
function MRelease(o){
	o = RClass.inherits(this, o);
	o.release = RMethod.virtual(o, 'release')
	return o;
	}
function RCharsetFace(){
   this.ansiMinCode = RCharset_ansiMinCode;
   this.ansiMaxCode = RCharset_ansiMaxCode;
   this.dump        = RCharset_dump;
   return this;
}
var RCharset = new RCharsetFace();
function RCharset_ansiMinCode(){
   if(SystemManager.language == "ja"){return 65383;}
   return -1;
}
function RCharset_ansiMaxCode(){
   if(SystemManager.language == "ja"){return 65439;}
   return -1;
}
function RCharset_dump(){
   return this.className + ' ' + SystemManager.language + " [" + this.ansiMinCode() + " - " + this.ansiMaxCode() + "]";
}
function RConsoleFace(){
	var o = this;
	o.consoles = new Array();
	o.create  = RConsole_create;
	o.find    = RConsole_find;
	return o;
}
var RConsole = new RConsoleFace();
function RConsole_create(cons){
	var obj = null;
	if(cons){
		obj = RClass.create(cons);
		for(var name in obj){
			if(RStr.startsWith(name, 'lnk')){
				var value = obj[name];
				if(value && typeof(value)=='string' && RStr.startsWith(value, '&')){
					var find = this.find(value.substr(1));
					if(find){
						obj[name] = find;
					}
				}
			}
		}
	}
	return obj;
}
function RConsole_find(cons){
	var name = RStr.nvl(RClass.name(cons), cons);
	var obj = RGlobal.get(cons);
	if(obj){
		return obj;
	}
	var consoles = this.consoles;
	if(consoles[name]){
		return consoles[name];
	}
	var obj = this.create(cons);
	if(EScope.Global == obj.scope){
		RGlobal.set(name, obj);
	}else if(EScope.Page == obj.scope){
		consoles[name] = obj;
	}
	return obj;
}
function RCookieFace(o){
	if(!o){o=this;}
	o.inMoving    = false;
	o.inSizing    = false;
	o.inDesign    = false;
	o.lsnsMdown   = new TListeners();
	o.lsnsMover   = new TListeners();
	o.lsnsMmove   = new TListeners();
	o.lsnsMup     = new TListeners();
	o.lsnsKdown   = new TListeners();
	o.lsnsKup     = new TListeners();
	o.hWindow     = null;
	o.hDocument   = null;
	o.hBody       = null;
	o.ohKdown     = RCookie_ohKdown;
	o.ohKup       = RCookie_ohKup;
	o.ohMdown     = RCookie_ohMdown;
	o.ohMover     = RCookie_ohMover;
	o.ohMmove     = RCookie_ohMmove;
	o.ohMup       = RCookie_ohMup;
	o.connect     = RCookie_connect;
	o.clientPos   = RCookie_clientPos;
	o.enable      = RCookie_enable;
	o.disable     = RCookie_disable;
	o.source      = RCookie_source;
	o.event       = RCookie_event;
	return o;
}
var RCookie = new RCookieFace();
function RCookie_ohMdown(){
	RCookie.lsnsMdown.process(this);
}
function RCookie_ohMover(){
	RCookie.lsnsMover.process(this);
}
function RCookie_ohMmove(){
	RCookie.lsnsMmove.process(this);
}
function RCookie_ohMup(){
	RCookie.lsnsMup.process(this);
}
function RCookie_ohKdown(){
	RCookie.lsnsKdown.process(this);
}
function RCookie_ohKup(){
	RCookie.lsnsKup.process(this);
}
function RCookie_connect(w){
	var o = this;
	if(!o.hWindow){
		o.hWindow = RObj.nvl(w, window);
		o.hDocument = o.hWindow.document;
		o.hBody = o.hDocument.body;
		o.hBody.onmousedown = o.ohMdown;
		o.hBody.onmousemove = o.ohMmove;
		o.hBody.onmouseover = o.ohMover;
		o.hBody.onmouseup = o.ohMup;
		o.hBody.onkeydown = o.ohKdown;
		o.hBody.onkeyup = o.ohKup;
		RConsole.find(FDesignConsole);
	}
}
function RCookie_clientPos(pos){
	var e = this.hWindow.event;
	if(pos){
		pos.x = e.clientX;
		pos.y = e.clientY;
		return pos;
	}
	return new TPoint(e.clientX, e.clientY);
}
function RCookie_enable(){
	this.connect();
	this.hBody.disabled = false;
}
function RCookie_disable(){
	this.connect();
	this.hBody.disabled = true;
}
function RCookie_source(h){
	return h ? h.ownerDocument.parentWindow.event.srcElement : this.hWindow.event.srcElement;
}
function RCookie_event(h){
	return h ? h.ownerDocument.parentWindow.event : this.hWindow.event;
}
function RCssFace(o){
	if(!o){o=this};
	o.rules   = null;
	o.connect = RCss_connect;
	o.has     = RCss_has;
	o.nvl     = RCss_nvl;
	o.style   = RCss_style;
	return o;
}
var RCss = new RCssFace();
function RCss_connect(){
	var o = this;
	if(o.rules){
		return;
	}
	o.rules = new TMap();
	var ds = document.styleSheets;
	for(var n=0; n<ds.length; n++){
		var rs = ds[n].rules;
		if(rs){
			for(var i=0; i<rs.length; i++){
				var r = rs[i];
				o.rules.set(r.selectorText, r);
			}
		}
	}
}
function RCss_has(s){
	if(s){
		this.connect();
		return this.rules.contains('.' + s.toLowerCase());
	}
	return false;
}
function RCss_nvl(s, n){
	this.connect();
	for(var n=0; n<arguments.length; n++){
		var s = arguments[n];
		if(s){
			if(this.rules.contains('.' + s.toLowerCase())){
				return s;
			}
		}
	}
	return null;
}
function RCss_style(c, n){
	return RClass.name(c) + '_' + n;
}
function REventFace(){
	var o = this;
	o.nvl = REvent_nvl;
	return o;
}
var REvent = new REventFace();
function REvent_nvl(event, sender, code){
	if(!event){
		event = new TEvent();
	}
	event.sender = sender;
	event.code = code;
	return event;
}
function RGlobalFace(){
	var o = this;
	o.globals    = new Array();
	o.language   = 'cn';
	o.root       = null;
	o.path       = null;
	o.construct  = RGlobal_construct;
	o.context    = RGlobal_context;
	o.get        = RGlobal_get;
	o.set        = RGlobal_set;
	o.setContext = RGlobal_setContext;
	o.construct();
	return o;
}
var RGlobal = new RGlobalFace();
function RGlobal_construct(){
	if(!top._global){
		top._global = this;
	}
	this.self = top._global;
}
function RGlobal_context(path){
	var o = this.self;
	if(RStr.endsWith(path, '.wv')){
		return o.root + '/' + path;
	}
	if(RStr.startsWith(path, '#')){
		return o.root + RGlobalPage.uriTag + path.substr(1);
	}
	return o.root + path;
}
function RGlobal_get(cons, name){
	if(cons){
		name = RStr.nvl(name) + '@' + RClass.name(cons);
		return this.self.globals[name.toLowerCase()];
	}
	return null;
}
function RGlobal_set(instance, name){
	if(instance){
		name = RStr.nvl(name) + '@' + RClass.name(instance);
		this.self.globals[name.toLowerCase()] = instance;
	}
}
function RGlobal_setContext(lang, root, path){
	this.self.lang = lang;
	this.self.root = root;
	RGlobalPage.uriTag = path;
}
function RGlobalPageFace(){
	var o = this;
	o.uri    = null;
	o.uriTag = null;
	return o;
}
var RGlobalPage = new RGlobalPageFace();
function RKeyFace(){
	var o = this;
	o.isCtlKey      = RKey_isCtlKey;
	o.isCtlKeyPress = RKey_isCtlKeyPress;
	o.eventClear    = RKey_eventClear;
	o.fixCase       = RKey_fixCase;
	o.fixPattern    = RKey_fixPattern;
	return o;
}
var RKey = new RKeyFace();
function RKey_isCtlKey(c){
	var ks = EKey.ControlKeys;
	for(var n=0; n<ks.length; n++){
		if(ks[n] == c){
			return true;
		}
	}
	return false;
}
function RKey_isCtlKeyPress(c){
	return (c == EKey.Tab || c == EKey.Enter || c == EKey.BackSpace);
}
function RKey_eventClear(e){
	e.keyCode = this.NONE;
	e.returnValue = false;
}
function RKey_fixCase(e, c){
	if(e && c){
		var k = e.keyCode;
		if(ECase.Upper == c){
			k = String.fromCharCode(k).toUpperCase().charCodeAt(0)
		}else if(ECase.Lower == c){
			k = String.fromCharCode(k).toLowerCase().charCodeAt(0)
		}
		e.keyCode = k;
	}
}
function RKey_fixPattern(e, p){
	if(p){
		var k = e.keyCode;
		if(!this.isCtlKeyPress(k)){
			if(!RStr.isPattern(String.fromCharCode(k), p)){
				e.keyCode = 0;
				return false;
			}
		}
	}
	return true;
}
function RLayerFace(o){
	if(!o){o=this;}
	o.layers = new Array();
	o.next   = RLayer_next;
	o.free   = RLayer_free;
	return o;
}
var RLayer = new RLayerFace();
function RLayer_next(type){
	var o = this;
	var n = RInt.nvl(type, ELayer.Default);
	var c = RInt.nvl(o.layers[n], n);
	o.layers[n] = ++c;
	return c;
}
function RLayer_free(type, layer){
	var o = this;
	var n = RInt.nvl(type, ELayer.Default);
	var c = RInt.nvl(o.layers[n], n);
	--c;
	if(c > n){
		o.layers[n] = c;
	}
	return c;
}
function RNamingFace(){
	var o = this;
	o.pool = new Array();
	o.get  = RNaming_get;
	o.set  = RNaming_set;
	return o;
}
var RNaming = new RNamingFace();
function RNaming_get(c, n){
	if(c){
		n = RStr.nvl(n) + '@' + RClass.name(c);
		return this.pool[n.toLowerCase()];
	}
	return null;
}
function RNaming_set(o, n){
	if(o){
		n = RStr.nvl(n) + '@' + RClass.name(o);
		this.pool[n.toLowerCase()] = o;
	}
}
function RPageFace(){
	var o = this;
	o.url      = RPage_url;
	o.parse    = RPage_parse;
   o.redirect = RPage_redirect;
	return o;
}
var RPage = new RPageFace();
function RPage_url(name){
	if(RStr.startsWith(name, 'http://')){
		return name;
	}
	return RGlobal.context(name);
}
function RPage_parse(page){
	var o = null;
	if(page){
		var items = page.split('@');
		if(items.length == 1){
			if(items[0]){
				o = new TPage();
				o.page = items[0];
				o.action = null;
				o.url = this.url(o.page);
			}
		}else if(items.length == 2){
			if(items[0] && items[1]){
				o = new TPage();
				o.action = items[0];
				o.page = items[1];
				o.url = this.url(o.page);
			}
		}
	}
	return o;
}
function RPage_redirect(f, t, u, m){
	RConsole.find(FEnvConsole).buildValue();
	u = RStr.nvl(u);
	if(m){
		u += '?do=' + m;
	}
	f.action = u;
	if(t){
		f.target = t;
	}
	f.method = 'POST';
	f.submit();
}
function RResFace(o){
	if(!o){o=this};
	o.uriImage  = '/ars/img/';
	o.uriIcon   = '/ars/icon/';
	o.imagePath = RRes_imagePath;
	o.iconPath  = RRes_iconPath;
	return o;
}
var RRes = new RResFace();
function RRes_imagePath(path, type){
	var o = this;
	path = path ? path.replace(/\./g, '/') : 'n';
	path += '.' + RStr.nvl(type, 'gif');
	if(RStr.startsWith(path, '#')){
		return RGlobal.context('#' + o.uriImage + path.substr(1));
	}
	return RGlobal.context(o.uriImage + path);
}
function RRes_iconPath(path, type){
	var o = this;
	path = path ? path.replace(/\./g, '/') : 'n';
	path += '.' + RStr.nvl(type, 'gif');
	if(RStr.startsWith(path, '#')){
		return RGlobal.context('#' + o.uriIcon + path.substr(1));
	}
	return RGlobal.context(o.uriIcon + path);
}
function RServiceFace(){
	var o = this;
	o.url   = RService_url;
	o.parse = RService_parse;
	return o;
}
var RService = new RServiceFace();
function RService_url(name){
	if(RStr.startsWith(name, 'http://')){
		return name;
	}
	if(RStr.startsWith(name, '#')){
		return name.substr(1);
	}
	if(!RStr.startsWith(name, '/')){
		name = '/' + name;
	}
	return RGlobal.context(name + '.ws');
}
function RService_parse(service){
	var o = null;
	if(service){
		var items = service.split('@');
		if(items.length == 1){
			if(items[0]){
				o = new TService();
				o.service = items[0];
				o.action = null;
				o.url = this.url(o.service);
			}
		}else if(items.length == 2){
			if(items[0] && items[1]){
				o = new TService();
				o.service = items[1];
				o.action = items[0];
				o.url = this.url(o.service);
			}
		}
	}
	return o;
}
function RTagFace(){
	var o = this;
   o.findParent = RTag_findParent;
   o.findChild  = RTag_findChild;
   o.cbSync     = RTag_cbSync;
   o.goPage     = RTag_goPage;
	return o;
}
var RTag = new RTagFace();
function RTag_findParent(oHtml, sType){
   if(!oHtml)
      return false;
   if(oHtml.tagName == sType.toUpperCase())
      return oHtml;
   if(!oHtml.parentElement)
      return false;
   return this.GetParent(oHtml.parentElement, sType);
}
function RTag_findChild(oHtml, sType, nPosition){
   if(!nPosition)
      nPosition = 0;
   if(!oHtml)
      return false;
   var nCount = oHtml.children.length;
   for(var i=0; i<nCount; i++){
      var oChild = oHtml.children[i];
      if(oChild.tagName == sType.toUpperCase())
         return oChild;
   }
   return false;
}
function RTag_cbSync(self, sync, trueValue, falseValue){
   if(self && sync){
      sync.value = self.checked ? trueValue : falseValue;
   }
}
function RTag_goPage(f, t, u, m){
	f = RHtml.form(f);
	if(f){
		u = RStr.nvl(u);
		if(m){
			u += '?do=' + m;
		}
		f.action = u;
		if(t){
			f.target = t;
		}
		f.method = 'POST';
		f.submit();
	}
}
function RWindowFace(o){
	if(!o){o=this;}
	o.inMoving    = false;
	o.inSizing    = false;
	o.inDesign    = false;
	o.inDisable   = false;
	o.panels      = new TMap();
	o.lsnsMdown   = new TListeners();
	o.lsnsMover   = new TListeners();
	o.lsnsMmove   = new TListeners();
	o.lsnsMup     = new TListeners();
	o.lsnsKdown   = new TListeners();
	o.lsnsKup     = new TListeners();
	o.lsnsKpress  = new TListeners();
	o.lsnsSize    = new TListeners();
	o.hWindow     = null;
	o.hDocument   = null;
	o.hBody       = null;
	o.hDisable    = null;
	o.hShadow     = null;
	o.ohMdown     = RWindow_ohMdown;
	o.ohMover     = RWindow_ohMover;
	o.ohMmove     = RWindow_ohMmove;
	o.ohMup       = RWindow_ohMup;
	o.ohKdown     = RWindow_ohKdown;
	o.ohKup       = RWindow_ohKup;
	o.ohKpress    = RWindow_ohKpress;
	o.ohResize    = RWindow_ohResize;
	o.ohMwheel    = RWindow_ohMwheel;
	o.onResize    = RWindow_onResize;
	o.connect     = RWindow_connect;
	o.screenPos   = RWindow_screenPos;
	o.offsetPos   = RWindow_offsetPos;
	o.clientPos   = RWindow_clientPos;
	o.source      = RWindow_source;
	o.event       = RWindow_event;
	o.setEnable   = RWindow_setEnable;
	o.showShadow  = RWindow_showShadow;
	o.panel       = RWindow_panel;
	o.moveCenter  = RWindow_moveCenter;
	o.append      = RWindow_append;
	return o;
}
var RWindow = new RWindowFace();
function RWindow_ohMwheel(){
    var c = RConsole.find(FFocusConsole).focusControl;
    if(RClass.isClass(c, MMouseWheel)){
       c.onMouseWheel(RWindow.event(c.hPanel));
    }
}
function RWindow_ohMdown(){
	RWindow.lsnsMdown.process(RWindow.event());
}
function RWindow_ohMover(){
	RWindow.lsnsMover.process(RWindow.event());
}
function RWindow_ohMmove(){
	RWindow.lsnsMmove.process(RWindow.event());
}
function RWindow_ohMup(){
	RWindow.lsnsMup.process(RWindow.event());
}
function RWindow_ohKdown(){
	RWindow.lsnsKdown.process(RWindow.event());
}
function RWindow_ohKup(){
	RWindow.lsnsKup.process(RWindow.event());
}
function RWindow_ohKpress(){
	RWindow.lsnsKpress.process(RWindow.event());
}
function RWindow_ohResize(){
	RWindow.onResize();
	RWindow.lsnsSize.process(RWindow.event());
}
function RWindow_onResize(){
	var o = this;
	var hd = o.hDisable;
	if(hd && hd.style.display=='block'){
		var st = hd.style;
		st.pixelLeft = 0;
		st.pixelTop = 0
		st.pixelWidth = o.hBody.scrollWidth;
		st.pixelHeight = o.hBody.scrollHeight;
	}
}
function RWindow_connect(w){
	var o = this;
	if(!o.hWindow){
		o.hWindow = RObj.nvl(w, window);
		o.hDocument = o.hWindow.document;
		o.hBody = o.hDocument.body;
		o.hBody.onmousedown = o.ohMdown;
		o.hBody.onmousemove = o.ohMmove;
		o.hBody.onmouseover = o.ohMover;
		o.hBody.onmouseup = o.ohMup;
		o.hBody.onkeydown = o.ohKdown;
		o.hBody.onkeyup = o.ohKup;
		o.hBody.onkeypress = o.ohKpress;
		o.hBody.onresize = o.ohResize;
		o.hBody.onmousewheel = o.ohMwheel;
		RConsole.find(FDesignConsole);
	}
}
function RWindow_panel(name){
	var o = this;
	o.connect();
	var panel = null;
	if(name){
		name = name.toLowerCase();
		panel = o.panels.get(name);
		if(!panel){
			panel = RBuilder.append(o.hBody, 'DIV');
			panel.style.position = 'absolute';
			o.panels.set(name, panel);
		}
	}
	return panel;
}
function RWindow_screenPos(p){
	var e = this.hWindow.event;
	if(p){
		p.x = e.screenX;
		p.y = e.screenY;
		return p;
	}
	return new TPoint(e.screenX, e.screenY);
}
function RWindow_offsetPos(p){
	var e = this.hWindow.event;
	if(p){
		p.x = e.offsetX;
		p.y = e.offsetY;
		return p;
	}
	return new TPoint(e.offsetX, e.offsetY);
}
function RWindow_clientPos(p){
	var e = this.hWindow.event;
	if(p){
		p.x = e.clientX;
		p.y = e.clientY;
		return p;
	}
	return new TPoint(e.clientX, e.clientY);
}
function RWindow_setEnable(v){
	var o = this;
	o.connect();
	o.inDisable = !v;
	if(!o.hDisable){
		o.hDisable = RBuilder.append(o.hBody, 'DIV', 'RWindow_Disable');
		o.hDisable.style.zIndex = ELayer.Disable;
	}
	var st = o.hDisable.style;
	if(v == false){
		st.display = 'block';
		o.onResize();
	}else{
		st.display = 'none';
	}
}
function RWindow_source(h){
	this.connect();
	return h ? h.ownerDocument.parentWindow.event.srcElement : this.hWindow.event.srcElement;
}
function RWindow_event(h){
	this.connect();
	return h ? h.ownerDocument.parentWindow.event : this.hWindow.event;
}
function RWindow_showShadow(v, r){
	var o = this;
	o.connect();
	if(!o.hShadow){
		o.hShadow = RBuilder.append(o.hBody, 'DIV', 'RWindow_Shadow');
		o.hShadow.style.zIndex = ELayer.Shadow;
	}
	var st = o.hShadow.style;
	if(v == false){
		st.display = 'none';
	}else{
		st.display = 'block';
		st.pixelLeft = r.left+3;
		st.pixelTop = r.top+3;
		st.pixelWidth = r.width();
		st.pixelHeight = r.height();
	}
}
function RWindow_moveCenter(hObj){
	var o = this;
	if(hObj){
		var w = o.hBody.clientWidth-hObj.offsetWidth;
		var h = o.hBody.clientHeight-hObj.offsetHeight;
		hObj.style.left = parseInt(w/2);
		hObj.style.top = parseInt(h/2);
	}
}
function RWindow_append(ctl){
	this.hWindow.document.body.appendChild(ctl.hPanel);
}
function TActive(owner, run){
	var o = this;
	o.count    = 0;
	o.owner    = owner;
	o.status   = EActive.Active;
	o.active   = true;
	o.interval = 100;
	o.run      = run;
	o.process  = TActive_process;
	return o;
}
function TActive_process(n){
	var o = this;
	o.count -= n;
	if(o.count < 0){
		if(o.owner && o.run){
			o.run.call(o.owner);
		}else if(o.run){
			o.run();
		}
		o.count = o.interval;
	}
}
function TEvent(owner, code, proc){
	var o = this;
	o.owner     = owner;
	o.code      = code;
	o.type      = null;
	o.onProcess = proc;
	o.isBefore  = TEvent_isBefore;
	o.isAfter   = TEvent_isAfter;
	o.process   = TEvent_process;
	o.dump      = TEvent_dump;
	return o;
}
function TEvent_isBefore(){
	return (EEventType.Before == this.type);
}
function TEvent_isAfter(){
	return (EEventType.After == this.type);
}
function TEvent_process(){
	var o = this;
	if(!o.onProcess){
		RMsg.fatal(o, null, 'Process event is null.');
	}
	RLog.debug(o, 'Process event (owner={0}, process={1})', o.owner, RMethod.name(o.onProcess));
	if(o.owner){
		o.onProcess.call(o.owner, o);
	}else{
		o.onProcess();
	}
}
function TEvent_dump(){
   return RClass.typeOf(this) + ' [' + this.owner + ',' + this.type + '-' + this.code + ']';
}
function TMonitor(){
	var o = this;
	o._count   = 0;
	o.status   = EMonitor.Active;
	o.interval = 100;
	o.run      = null;
	o.process  = TMonitor_process;
	return o;
}
function TMonitor_process(n){
	this._count -= n;
	if(this._count < 0){
		if(this.run){
			this.run();
		}
		this._count = this.interval;
	}
}
function TPage(){
	var o = this;
	o.page       = null;
	o.action     = null;
	o.url        = null;
	o.attributes = null;
	o.split      = TPage_split;
	o.post       = TPage_post;
	o.get        = TPage_get;
	return o;
}
function TPage_split(s){
	if(s){
		var o = this;
		var as = o.attributes = new TAttrs();
		as.split(s, '=', '\n');
	}
}
function TPage_post(form, target){
	var o = this;
	RConsole.find(FEnvConsole).buildValue();
	var url = RStr.nvl(o.url);
	if(o.action){
		if(-1 == url.indexOf('?')){
			url += '?do=' + o.action;
		}else{
			url += '&do=' + o.action;
		}
	}
	var as = o.attributes;
	if(as){
		var s = as.join('=', '&');
		if(-1 == url.indexOf('?')){
			url += '?' + s;
		}else{
			url += '&' + s;
		}
	}
	form.action = url;
	if(target){
		form.target = target;
	}
	form.method = 'POST';
	form.submit();
}
function TPage_get(){
}
function TService(){
	var o = this;
	o.service = null;
	o.action  = null;
	o.url     = null;
	return o;
}
function FButton(o){
	o = RClass.inherits(this, o, FControl, MDesign, MLsnClick);
	o.labelPosition  = RClass.register(o, new TPtyStr('labelPosition', EPosition.Left));
	o.icon           = RClass.register(o, new TPtyStr('icon'));
	o.hForm          = null;
	o.hLabelPanel    = null;
	o.hLabel         = null;
	o.oeBuild        = FButton_oeBuild;
	o.onClick        = FButton_onClick;
	o.setLabel       = FButton_setLabel;
	return o;
}
function FButton_oeBuild(event){
	var o = this;
	o.base.FControl.oeBuild.call(o, event);
	o.hIcon = RBuilder.appendIcon(o.hPanel, o.icon, o.style('Icon'));
	o.hLabel = RBuilder.appendText(o.hPanel, o.label, o.style('Label'));
	return EEventStatus.Stop;
}
function FButton_onClick(){
	this.processClick(null, this);
}
function FButton_setLabel(label){
	this.label = label;
	this.hLabel.innerText = label;
}
function FCalendar(o){
	o = RClass.inherits(this, o, FDataEditControl, MDropable);
	o.editFormat  = RClass.register(o, new TPtyStr('editFormat', RDate.DisplayFormat));
	o.date        = new TDate();
	o.dropIcon    = '#control.calendar';
	o.hForm       = null;
	o.hDrop       = null;
	o.hForm       = null;
	o.onBuildEdit = FCalendar_onBuildEdit;
	o.onEditEnd   = FCalendar_onEditEnd;
	o.onKeyPress  = FCalendar_onKeyPress;
	o.text        = FCalendar_text;
	o.setText     = FCalendar_setText;
	o.formatValue = FCalendar_formatValue;
	o.validText   = FCalendar_validText;
	o.formatText  = FCalendar_formatText;
	o.drop        = FCalendar_drop;
	return o;
}
function FCalendar_onBuildEdit(){
	var o = this;
	o.base.FDataEditControl.onBuildEdit.call(o);
	var h = o.hEdit = RBuilder.appendEdit(o.hEditPanel, o.style('Edit'));
	if(o.editLength){
		h.maxLength = o.editLength;
	}
	if(o.hint){
		h.title = o.hint;
	}
	o.onBuildDrop();
}
function FCalendar_onEditEnd(editor){
	var o = this;
	RLog.debug(o, 'Edit end (editor={1}:{2} value={3})', editor, editor?editor.value():'', o.dataValue);
	if(editor){
		o.setValue(editor.value());
	}
	o.base.FDataEditControl.onEditEnd.call(o);
}
function FCalendar_onKeyPress(e){
	if(!RStr.inChars(String.fromCharCode(e.keyCode), RDate.Chars)){
		RKey.eventClear(e);
	}
}
function FCalendar_text(){
	return this.hEdit.value;
}
function FCalendar_setText(text){
	this.hEdit.value = text;
}
function FCalendar_formatValue(t){
	if(t){
		var o = this;
		if(t.toLowerCase() == '@now'){
			o.date.now();
			return RDate.formatDate(o.date);
		}else{
			RDate.autoParse(o.date, t);
			return RDate.formatDate(o.date);
		}
	}
	return RStr.nvl(t);
}
function FCalendar_validText(v){
	var o = this;
	if(RStr.isEmpty(v)){
		return true;
	}
	return (null != RDate.autoParse(o.date, v));
}
function FCalendar_formatText(value){
	if(value){
		var o = this;
		RDate.autoParse(o.date, value);
		return RDate.formatDate(o.date, o.editFormat);
	}
	return RStr.nvl(value);
}
function FCalendar_drop(){
	var o = this;
	if(o.canDrop() && o.canEdit){
		var editor = RConsole.find(FEditConsole).focus(o, FCalendarEditor, o.name);
		editor.linkPanel(o.hEditPanel, o.hEdit);
		editor.setValue(o.dataValue, o.editFormat);
		editor.show();
	}
}
function FCalendarEditor(o){
	o = RClass.inherits(this, o, FDropEditor, MFocusLooper, MShadow);
	o.editFormat    = RDate.DisplayFormat;
   o.onDayEnter    = RClass.register(o, new HMouseEnter('onDayEnter'),    FCalendarEditor_onDayEnter);
   o.onDayOut      = RClass.register(o, new HMouseOut('onDayOut'),        FCalendarEditor_onDayOut);
   o.onDaySelect   = RClass.register(o, new HMouseDown('onDaySelect'),    FCalendarEditor_onDaySelect);
   o.onButtonNow   = RClass.register(o, new HMouseDown('onButtonNow'),    FCalendarEditor_onButtonNow);
   o.onButtonOk    = RClass.register(o, new HMouseDown('onButtonOk'),     FCalendarEditor_onButtonOk);
   o.onButtonOver  = RClass.register(o, new HMouseEnter('onButtonOver'),    FCalendarEditor_onButtonOver);
   o.onButtonOut   = RClass.register(o, new HMouseOut('onButtonOut'),        FCalendarEditor_onButtonOut);
   o.onMdown       = RClass.register(o, new HMouseDown('onMdown'),         FCalendarEditor_onMdown);
   o.onMup         = RClass.register(o, new HMouseUp('onMup'),          FCalendarEditor_onMup);
	o.styleButton      = RClass.register(o, new TStyle('Button'));
	o.styleButtonHover = RClass.register(o, new TStyle('ButtonHover'));
	o.styleDay         = RClass.register(o, new TStyle('Day'));
	o.styleDaySel      = RClass.register(o, new TStyle('DaySel'));
	o.styleDayHover    = RClass.register(o, new TStyle('DayHover'));
	o.styleDayFree     = RClass.register(o, new TStyle('DayFree'));
	o.styleDayNone     = RClass.register(o, new TStyle('DayNone'));
	o.styleTitlePanel  = RClass.register(o, new TStyle('TitlePanel'));
	o.styleDaysPanel   = RClass.register(o, new TStyle('DaysPanel'));
	o.styleTimePanel   = RClass.register(o, new TStyle('TimePanel'));
	o.styleMonth       = RClass.register(o, new TStyle('Year'));
	o.styleMonth       = RClass.register(o, new TStyle('Month'));
	o.styleWeek        = RClass.register(o, new TStyle('Week'));
	o.styleTime        = RClass.register(o, new TStyle('Time'));
	o.styleNow         = RClass.register(o, new TStyle('Now'));
	o.styleOk          = RClass.register(o, new TStyle('Ok'));
	o.dateOrg       = new TDate();
	o.dateOrgValue  = null;
	o.date          = new TDate();
	o.dayCells      = new TList();
	o.focusObject   = null;
	o.skipBlur      = false;
	o.hTitlePanel   = null;
	o.hYearPrior    = null;
	o.hYear         = null;
	o.hYearNext     = null;
	o.hMonthPrior   = null;
	o.hMonth        = null;
	o.hMonthNext    = null;
	o.hDaysPanel    = null;
	o.hTimePanel    = null;
	o.hTime         = null;
	o.hNow          = null;
	o.hOk           = null;
	o.ohKdown       = FCalendarEditor_ohKdown;
	o.ohDaysChange  = FCalendarEditor_ohDaysChange;
	o.ohKeyCheck    = FCalendarEditor_ohKeyCheck;
	o.onBuildEdit   = FCalendarEditor_onBuildEdit;
	o.onDateAction  = FCalendarEditor_onDateAction;
	o.panel         = FCalendarEditor_panel;
	o.value         = FCalendarEditor_value;
	o.setValue      = FCalendarEditor_setValue;
	o.setDate       = FCalendarEditor_setDate;
	o.changeToData  = FCalendarEditor_changeToData;
	o.buildTitle    = FCalendarEditor_buildTitle;
	o.buildDays     = FCalendarEditor_buildDays;
	o.buildTime     = FCalendarEditor_buildTime;
	o.focus         = FCalendarEditor_focus;
	o.show          = FCalendarEditor_show;
	o.hide          = FCalendarEditor_hide;
	return o;
}
function FCalendarEditor_onMdown(e){
   var o = e.source;
   if(RClass.isClass(o, FCalendarEditor)){
      o.isSkipBlur = true;
      if(e.srcElement.linkAction){
         e.srcElement.linkAction.call(o, e.srcElement);
      }
   }
}
function FCalendarEditor_onMup(e){
   var o = e.source;
   if(RClass.isClass(o, FCalendarEditor)){
      var f = o.focusObject;
      if(f && f.focus && f.select){
         f.focus();
         f.select();
      }
   }
}
function FCalendarEditor_ohKdown(){
   alert(FCalendarEditor_ohKdown);
	var o = this.link;
	if(RClass.isClass(o, FCalendarEditor)){
		var e = RWindow.event(this);
		if(EKey.Esc == e.keyCode){
			o.dataValue = o.dateOrgValue;
			o.editStatus = EEditStatus.Cancel;
			o.endEdit();
		}else if(event.ctrlKey && EKey.Enter == e.keyCode){
			o.changeToData();
			o.editStatus = EEditStatus.Ok;
			o.endEdit();
		}else if(EKey.Enter == e.keyCode){
			o.changeToData();
			o.setDate(o.date);
		}else if(EKey.Tab == e.keyCode){
			o.isSkipBlur = true;
			if(e.shiftKey){
				o.focusPrior();
			}else{
				o.focusNext();
			}
			e.returnValue = 0;
		}
	}
}
function FCalendarEditor_onButtonOver(e){
   var o = e.source;
   if(RClass.isClass(o, FCalendarEditor)){
      e.srcElement.className = o.style('ButtonHover');
   }
}
function FCalendarEditor_onButtonOut(e){
   var o = e.source;
   if(RClass.isClass(o, FCalendarEditor)){
      e.srcElement.className = o.style('Button');
   }
}
function FCalendarEditor_onButtonNow(e){
   var o = e.source;
   if(RClass.isClass(o, FCalendarEditor)){
      o.editStatus = EEditStatus.Ok;
      o.dataValue = RDate.format();
      o.endEdit();
   }
}
function FCalendarEditor_onButtonOk(e){
   var o = e.source;
   if(RClass.isClass(o, FCalendarEditor)){
      o.editStatus = EEditStatus.Ok;
      o.dataValue = RDate.formatDate(o.date);
      o.endEdit();
   }
}
function FCalendarEditor_ohDaysChange(){
   alert(FCalendarEditor_ohDaysChange);
	var o = this.link;
	if(RClass.isClass(o, FCalendarEditor)){
		o.date.setYear(o.hYear.value);
		o.date.setMonth(o.hMonth.value);
		o.setDate(o.date);
	}
}
function FCalendarEditor_ohKeyCheck(){
   alert(FCalendarEditor_ohKeyCheck);
	var e = RWindow.event(this)
	if(!RStr.isPattern(String.fromCharCode(e.keyCode), 'n')){
		e.keyCode = 0;
	}
}
function FCalendarEditor_onDayEnter(e){
   var o = e.source;
   if(RClass.isClass(o, FCalendarEditor) && e.srcElement.innerText != '.'){
      if(!e.srcElement.isCurrent){
         e.srcElement.className = o.style('DayHover');
      }
   }
}
function FCalendarEditor_onDayOut(e){
   var o = e.source;
   if(RClass.isClass(o, FCalendarEditor) && e.srcElement.innerText != '.'){
      if(!e.srcElement.isCurrent){
         e.srcElement.className = e.srcElement.isFree ? o.style('DayFree') : o.style('Day');
      }
   }
}
function FCalendarEditor_onDaySelect(e){
   var o = e.source;
   if(RClass.isClass(o, FCalendarEditor)){
      o.date.setDay(e.srcElement.innerText);
      o.dataValue = RDate.formatDate(o.date);
      o.editStatus = EEditStatus.Ok;
      o.endEdit();
   }
}
function FCalendarEditor_onBuildEdit(){
	var o = this;
	var hFormTab = o.hForm = RBuilder.appendTable(o.hPanel);
	var hTltCel = hFormTab.insertRow().insertCell();
	var hTab = RBuilder.appendTable(hTltCel);
	var hRow = hTab.insertRow();
	var hCel = hRow.insertCell();
	o.hEditCel = hCel;
	o.hEditor = RBuilder.append(hCel, 'INPUT', o.style('Edit'));
	o.hEdit = o.hEditor;
	var hCel = hRow.insertCell()
	o.hDrop = RBuilder.appendIcon(hCel, 'ctl.datepicker', o.style('Drop'));
	var hDrpCel = o.hDropPanel = hFormTab.insertRow().insertCell();
	hDrpCel.className = o.style('DropPanel');
	var hDrpCel = hFormTab.insertRow().insertCell();
	o.hDatePanel = RBuilder.appendTable(o.hDropPanel, null, 0, 0, 1);
	var hRow = o.hDatePanel.insertRow();
	var hCell = o.hTitlePanel = hRow.insertCell();
	hCell.colSpan = 2;
	hCell.className = o.style('TitlePanel');
	o.buildTitle();
	var hRow = o.hDatePanel.insertRow();
	var hCell = o.hDaysPanel = hRow.insertCell();
	hCell.colSpan = 2;
	hCell.className = o.style('DaysPanel');
	o.buildDays();
	var hRow = o.hDatePanel.insertRow();
	var hCell = o.hTimePanel = hRow.insertCell();
	hCell.colSpan = 2;
	hCell.className = o.style('TimePanel');
	o.buildTime();
	o.pushFocus(o.hEdit);
	o.pushFocus(o.hYear);
	o.pushFocus(o.hMonth);
	o.pushFocus(o.hTime);
}
function FCalendarEditor_onDateAction(h){
	var o = this;
	if(o.hYearPrior == h){
		o.date.addYear(-1);
		o.setDate(o.date);
		if(o.focusObject != this.hYear){
			o.focusObject = this.hYear;
			o.hYear.focus();
			o.hYear.select();
		}
	}else if(o.hYearNext == h){
		o.date.addYear(1);
		o.setDate(o.date);
		if(o.focusObject != this.hYear){
			o.focusObject = this.hYear;
			o.hYear.focus();
			o.hYear.select();
		}
	}else if(o.hMonthPrior == h){
		this.date.addMonth(-1);
		o.setDate(o.date);
		if(o.focusObject != this.hMonth){
			o.focusObject = this.hMonth;
			o.hMonth.focus();
		}
	}else if(o.hMonthNext == h){
		this.date.addMonth(1);
		o.setDate(o.date);
		if(o.focusObject != this.hMonth){
			o.focusObject = this.hMonth;
			o.hMonth.focus();
		}
	}
}
function FCalendarEditor_panel(type){
	var o = this;
	if(EPanel.Shadow == type){
		return o.hDropPanel;
	}
	return o.base.FDropEditor.panel.call(o, type);
}
function FCalendarEditor_value(){
	return this.dataValue;
}
function FCalendarEditor_setValue(value, format){
	var o = this;
	o.changed = false;
	o.skipBlur = 0;
	o.dataValue = value;
	o.dateOrgValue = value;
	o.editFormat = format;
	if(value){
		RDate.parse(o.date, value);
		RDate.parse(o.dateOrg, value);
		o.hEdit.value = RDate.formatDate(o.date, o.editFormat);
	}
	o.setDate(o.date);
}
function FCalendarEditor_setDate(date){
	var o = this;
	o.hYear.value = date.year;
	o.hMonth.value = date.month;
	var selDay = date.day;
	if(!(o.dateOrg.year == date.year && o.dateOrg.month == date.month)){
		selDay = -1;
	}
	var monthWeekDay = this.date.monthWeekDay();
	var monthDays = this.date.monthDays();
	var weekDay = monthWeekDay;
	for(var n=0; n<o.dayCells.count; n++){
		var h = o.dayCells.get(n);
		if(n<monthWeekDay){
			h.className = o.style('DayNone');
			h.innerText = '.'
		}else if(n < monthDays+monthWeekDay){
			if(weekDay == 7){
				weekDay = 0;
			}
			var day = n-monthWeekDay+1;
			if(day == selDay){
				h.className = o.style('DaySel');
				h.isCurrent = true;
			}else{
				h.isFree = (weekDay==0 || weekDay==6);
				h.className = h.isFree ? o.style('DayFree') : o.style('Day');
				h.isCurrent = false;
			}
			h.innerText = day;
			weekDay++;
		}else{
			h.className = o.style('DayNone');
			h.innerText = '.'
		}
	}
	o.hTime.value = RDate.formatDate(o.date, 'hh24:mi:ss');
}
function FCalendarEditor_changeToData(){
	var o = this;
	o.date.setYear(o.hYear.value);
	o.date.setMonth(o.hMonth.value);
}
function FCalendarEditor_buildTitle(){
	var o = this;
	var hTab = RBuilder.appendTable(o.hTitlePanel, null, 0, 1, 1);
	var hRow = hTab.insertRow();
	var hCel = hRow.insertCell();
	var h = o.hYearPrior = RBuilder.append(hCel, 'SPAN', o.style('Button'));
	h.link = o;
	h.linkAction = o.onDateAction;
	h.innerText = '3';
	o.attachEvent("onButtonOver",h);
	o.attachEvent("onButtonOut",h);
	o.attachEvent("onMdown",h);
	o.attachEvent("onMup",h);
	var hCel = hRow.insertCell();
	var h = o.hYear = RBuilder.append(hCel, 'INPUT', o.style('Year'));
	h.maxLength = '4';
	h.onchange = o.ohDaysChange;
	h.onkeypress = o.ohKeyCheck;
	var hCel = hRow.insertCell();
	hCel.innerText = RContext.get(FCalendarEditor, 'year');
	var hCel = hRow.insertCell();
	var h = o.hYearNext = RBuilder.append(hCel, 'SPAN', o.style('Button'));
	h.link = o;
	h.linkAction = o.onDateAction;
	h.innerText = '4';
	  o.attachEvent("onButtonOver",h);
	   o.attachEvent("onButtonOut",h);
	  o.attachEvent("onMdown",h);
	   o.attachEvent("onMup",h);
	var hCell = hRow.insertCell();
	hCell.width='10';
	var hCel = hRow.insertCell();
	var h = o.hMonthPrior = RBuilder.append(hCel, 'SPAN', o.style('Button'));
	h.link = o;
	h.linkAction = o.onDateAction;
	h.innerText = '3';
	  o.attachEvent("onButtonOver",h);
	   o.attachEvent("onButtonOut",h);
	  o.attachEvent("onMdown",h);
	   o.attachEvent("onMup",h);
	var hCel = hRow.insertCell();
	var h = o.hMonth = RBuilder.append(hCel, 'INPUT', o.style('Month'));
	h.maxLength = '2';
	h.onchange = o.ohDaysChange;
	h.onkeypress = o.ohKeyCheck;
	var hCel = hRow.insertCell();
	hCel.innerText = RContext.get(FCalendarEditor, 'month');
	var hCel = hRow.insertCell();
	var h = o.hMonthNext = RBuilder.append(hCel, 'SPAN', o.style('Button'));
	h.link = o;
	h.linkAction = o.onDateAction;
	h.innerText = '4';
	  o.attachEvent("onButtonOver",h);
	   o.attachEvent("onButtonOut",h);
	  o.attachEvent("onMdown",h);
	   o.attachEvent("onMup",h);
}
function FCalendarEditor_buildDays(){
	var o = this;
	var hTab = RBuilder.appendTable(o.hDaysPanel, null, 0, 0, 1);
	hTab.width = '100%';
	var weekDays = RContext.get(FCalendarEditor, 'weekdays').split(',');
	var count = weekDays.length;
	var hWeekRow = hTab.insertRow();
	for(var n=0; n<count; n++){
		var h = hWeekRow.insertCell();
		h.className = o.style('Week');
		h.align = 'center';
		h.innerText = weekDays[n];
	}
	for(var n=0; n<6; n++){
		var hRow = hTab.insertRow();
		for(var i=0; i<count; i++){
			var h = hRow.insertCell();
			h.link = o;
			h.className = o.style('DayNone');
			o.attachEvent("onDayEnter",h);
			o.attachEvent("onDayOut",h);
			o.attachEvent("onDaySelect",h);
			h.innerText = '.';
			o.dayCells.push(h);
		}
	}
}
function FCalendarEditor_buildTime(){
	var o = this;
	var hTab = RBuilder.appendTable(o.hTimePanel, null, 0, 1, 1);
	var hRow = hTab.insertRow();
	var hCel = hRow.insertCell();
	var h = o.hTime = RBuilder.appendEdit(hCel, o.style('Time'));
	h.maxLength = 8;
	hCel = hRow.insertCell();
	hCel.width = '16px';
   hCel = hRow.insertCell();
	var h = o.hNow = RBuilder.append(hCel, 'SPAN', o.style('Now'));
	h.link = o;
	h.innerText = 'NOW';
	o.attachEvent("onButtonNow",h);
	hCel = hRow.insertCell();
	var h = o.hOk = RBuilder.append(hCel, 'SPAN', o.style('Ok'));
	h.link = o;
	h.innerText = 'OK';
	o.attachEvent("onButtonOk",h);
}
function FCalendarEditor_focus(){
	this.hEdit.focus();
}
function FCalendarEditor_show(v){
	var o = this;
	o.base.FDropEditor.show.call(o, v);
	o.base.MShadow.show.call(o, v);
}
function FCalendarEditor_hide(){
	var o = this;
	o.base.FDropEditor.hide.call(o);
	o.base.MShadow.hide.call(o);
}
function FCheck(o){
	o = RClass.inherits(this, o, FDataEditControl);
	o.dataTrue      = EBool.True;
	o.dataFalse     = null;
	o.hasEditBorder = false;
	o.onBuildEdit   = FCheck_onBuildEdit;
	o.loadConfig    = FCheck_loadConfig;
	o.saveConfig    = FCheck_saveConfig;
	o.formatValue   = FCheck_formatValue;
	o.text          = FCheck_text;
	o.setText       = FCheck_setText;
	o.setEditStyle  = FCheck_setEditStyle;
	return o;
}
function FCheck_onBuildEdit(){
	var o = this;
	o.base.FDataEditControl.onBuildEdit.call(o);
	o.hEdit = RBuilder.appendCheck(o.hEditPanel, o.style('Edit'));
	if(o.hint){
		h.title = o.hint;
	}
}
function FCheck_loadConfig(c){
	var o = this;
	o.base.FDataEditControl.loadConfig.call(o, c);
	o.dataTrue  = c.nvl('data_true', o.dataTrue);
	o.dataFalse = c.get('data_false');
}
function FCheck_saveConfig(c){
	var o = this;
	o.base.FDataEditControl.saveConfig.call(o, c)
	c.set('data_true',  o.dataTrue);
	c.set('data_false', o.dataFalse);
}
function FCheck_formatValue(s){
	return RStr.nvl(s);
}
function FCheck_text(){
	return this.hEdit.checked ? this.dataTrue : this.dataFalse;
}
function FCheck_setText(text){
	this.hEdit.checked = (this.dataTrue == text);
}
function FCheck_setEditStyle(style){
	var o = this;
	var h = o.panel(EPanel.Edit);
	switch(style){
		case EStyle.Edit:
			h.disabled = false;
			break;
		case EStyle.Readonly:
			h.disabled = true;
			break;
	}
}
function FColorPicker(o){
	o = RClass.inherits(this, o, FDataEditControl, MDropable);
   o.dropIcon    = 'ctl.clrdrop';
	o.onBuildEdit = FColorPicker_onBuildEdit;
	o.onEditEnd   = FColorPicker_onEditEnd;
	o.text        = FColorPicker_text;
	o.setText     = FColorPicker_setText;
	o.drop        = FColorPicker_drop;
	return o;
}
function FColorPicker_onBuildEdit(){
   var o = this;
	o.base.FDataEditControl.onBuildEdit.call(o);
	var h = o.hEdit = RBuilder.appendEdit(o.hEditPanel, o.style('Edit'));
   h.maxLength = 20;
	if(o.hint){
		h.title = o.hint;
	}
	o.onBuildDrop();
}
function FColorPicker_onEditEnd(editor){
	var o = this;
	RLog.debug(o, 'Begin (editor={0}:{1} value={2})', editor, editor?editor.color:'', o.dataValue);
	if(editor){
		o.setValue(editor.color);
	}
	o.base.FDataEditControl.onEditEnd.call(o);
	RLog.debug(o, 'End (editor={0} value={1})', editor, o.dataValue);
}
function FColorPicker_text(){
	return this.hEdit.value;
}
function FColorPicker_setText(text){
	var o = this;
	o.hEdit.value = text.toUpperCase();
	o.hDropPanel.style.backgroundColor = text;
}
function FColorPicker_drop(){
	var o = this;
	if(o.canDrop() && o.canEdit){
		var editor = RConsole.find(FEditConsole).focus(o, FColorPickerEditor);
		editor.linkPanel(o.hEditPanel, o.hEdit);
		editor.setColor(o.value());
		editor.show();
	}
}
function FColorPickerEditor(o){
	o = RClass.inherits(this, o, FDropEditor, MShadow);
	o.ColorHex     = new Array('00', '33', '66', '99', 'CC', 'FF');
	o.SpColorHex   = new Array('FF0000', '00FF00', '0000FF', 'FFFF00', '00FFFF','FF00FF');
   o.onCellEnter  = RClass.register(o, new HMouseOver('onCellEnter'),  FColorPickerEditor_onCellEnter);
   o.onCellSelect = RClass.register(o, new HMouseDown('onCellSelect'), FColorPickerEditor_onCellSelect);
	o.color        = null;
	o.hTable       = null;
	o.cellWidth    = 10;
	o.cellHeight   = 8;
	o.onBuildEdit  = FColorPickerEditor_onBuildEdit;
	o.onKeyDown    = FColorPickerEditor_onKeyDown;
	o.makeCell     = FColorPickerEditor_makeCell;
	o.panel        = FColorPickerEditor_panel;
	o.value        = FColorPickerEditor_value;
	o.setValue     = FColorPickerEditor_setValue;
	o.setColor     = FColorPickerEditor_setColor;
	o.focus        = FColorPickerEditor_focus;
	o.show         = FColorPickerEditor_show;
	o.hide         = FColorPickerEditor_hide;
	return o;
}
function FColorPickerEditor_onCellEnter(e){
	var o = this;
   o.hDropColor.style.backgroundColor = e.srcElement.style.backgroundColor;
}
function FColorPickerEditor_onCellSelect(e){
	var o = this;
	o.color = e.srcElement.style.backgroundColor;
	o.blur();
}
function FColorPickerEditor_makeCell(hRow, color) {
	var o = this;
	var h = hRow.insertCell();
	h.link = o;
	h.width = o.cellWidth;
	h.height = o.cellHeight;
   h.style.backgroundColor = color;
   o.attachEvent('onCellEnter', h);
   o.attachEvent('onCellSelect', h);
	return h;
}
function FColorPickerEditor_onBuildEdit(){
   var o = this;
	var hFormTab = o.hForm = RBuilder.appendTable(o.hPanel);
	hFormTab.width = o.cellWidth*21;
	var hTltCel = hFormTab.insertRow().insertCell();
	var hTab = RBuilder.appendTable(hTltCel);
	var hRow = hTab.insertRow();
	var hCel = hRow.insertCell();
	o.hEditCel = hCel;
	o.hEdit = RBuilder.appendEdit(hCel, o.style('Edit'));
	var hCel = o.hDropColor = hRow.insertCell()
	o.hDrop = RBuilder.appendIcon(hCel, 'ctl.clrdrop', o.style('Drop'));
	var hDrpCel = hFormTab.insertRow().insertCell();
   hDrpCel.width = 18;
	var h = o.hDropPanel = RBuilder.append(hDrpCel, 'DIV', o.style('DropPanel'));
	h.link = o;
   o.attachEvent('onDropMouseDown', h);
   o.attachEvent('onDropMouseUp', h);
	o.hTable = RBuilder.appendTable(o.hDropPanel);
	for(var i = 0; i < 2; i++){
		for(var j = 0; j < 6; j++){
			var hRow = o.hTable.insertRow();
			o.makeCell(hRow, "#000000");
			if (i == 0){
				o.makeCell(hRow, '#'+o.ColorHex[j] + o.ColorHex[j] + o.ColorHex[j]);
			}else {
				o.makeCell(hRow, '#'+o.SpColorHex[j]);
			}
			o.makeCell(hRow, "#000000");
			for (k = 0; k < 3; k++) {
				for (l = 0; l < 6; l++) {
					o.makeCell(hRow, '#'+o.ColorHex[k + i * 3] + o.ColorHex[l] + o.ColorHex[j]);
				}
			}
		}
	}
}
function FColorPickerEditor_onKeyDown(e){
	var o = this;
	var kc = e.keyCode;
	if(EKey.Up == kc){
		o.select(o.selectIndex-1);
	}else if(EKey.Down == kc){
		o.select(o.selectIndex+1);
	}else if(EKey.Esc == kc){
		o.editStatus = EEditStatus.Cancel;
		o.selectIndex = o.originIndex;
		RKey.eventClear(e);
		o.inEdit = false;
		o.hEdit.blur();
	}else if(EKey.Enter == kc){
		o.editStatus = EEditStatus.Ok;
		RKey.eventClear(e);
		o.inEdit = false;
		o.hEdit.blur();
	}
}
function FColorPickerEditor_panel(type){
	var o = this;
	if(EPanel.Shadow == type){
		return o.hDropPanel;
	}
	return o.base.FDropEditor.panel.call(o, type);
}
function FColorPickerEditor_value(){
	return this.dataValue;
}
function FColorPickerEditor_setValue(value){
	var o = this;
	o.changed = false;
	o.dataValue = value;
	o.dataOrigin = value;
	o.selectIndex = o.items.indexOf(value);
	o.select(o.selectIndex);
}
function FColorPickerEditor_setColor(c){
	if(null != c){
		this.hEdit.value = c;
	}
}
function FColorPickerEditor_focus(){
   var o = this;
   o.base.FDropEditor.focus.call(o);
   o.hEdit.focus();
}
function FColorPickerEditor_show(v){
	var o = this;
	o.base.FDropEditor.show.call(o, v);
	o.base.MShadow.show.call(o, v);
	o.isSkipBlur = false;
}
function FColorPickerEditor_hide(){
	var o = this;
	o.base.FDropEditor.hide.call(o);
	o.base.MShadow.hide.call(o);
}
function FDataAction(o){
	o = RClass.inherits(this, o, FComponent, MInvoke);
	o.service   = RClass.register(o, new TPtyStr('service'));
	o.isLoading = false;
	o.service   = null;
	o.valuable  = null;
	o.onLoaded  = FDataAction_onLoaded;
	o.invoke    = FDataAction_invoke;
	return o;
}
function FDataAction_onLoaded(e){
	var o = this;
	var r = RConsole.find(FResultConsole).checkService(e.document.root());
	if(r){
		RWindow.setEnable(true);
		var v = o.valuable;
		if(RClass.isClass(v, MFocus)){
			v.focus();
		}
	}
	o.isLoading = false;
}
function FDataAction_invoke(vo){
	var o = this;
	RClass.checkClass(vo, MValue);
	var svc = RService.parse(this.service);
	if(!svc){
		return alert('Unknown service');
	}
	RWindow.setEnable(false);
	var doc = new TXmlDoc();
	var root = doc.root();
	root.set('action', svc.action);
	RConsole.find(FEnvConsole).build(root);
	var config = root.create('Data');
	vo.saveValue(config);
	RLog.debug(this, doc.dump());
	o.valuable = vo;
	o.isLoading = true;
	var e = new TEvent(o, EXmlEvent.Send, o.onLoaded);
	e.url = svc.url;
	e.document = doc;
	RConsole.find(FXmlConsole).process(e);
}
function FDataControl(o){
	o = RClass.inherits(this, o, FControl, MDesign);
	o.dataName         = RClass.register(o, new TPtyStr('dataName'));
	o.dataValue        = RClass.register(o, new TPtyStr('dataValue'));
	o.dataDefault      = RClass.register(o, new TPtyStr('dataDefault'));
	o.labelType        = RClass.register(o, new TPtyStr('labelType', ELabel.All));
	o.labelIcon        = RClass.register(o, new TPtyStr('labelIcon'));
	o.labelIconDisable = RClass.register(o, new TPtyStr('labelIconDisable'));
	o.labelColor       = RClass.register(o, new TPtyStr('labelColor'));
	o.labelAlign       = RClass.register(o, new TPtyStr('labelAlign', EAlign.Left));
	o.labelPosition    = RClass.register(o, new TPtyStr('labelPosition', EPosition.Left));
	o.labelWidth       = RClass.register(o, new TPtyStr('labelWidth'));
	o.labelHeight      = RClass.register(o, new TPtyStr('labelHeight'));
	o.styleForm        = RClass.register(o, new TStyle('Form'));
	o.styleLabelForm   = RClass.register(o, new TStyle('LabelForm'));
	o.styleControlForm = RClass.register(o, new TStyle('ControlForm'));
	o.hForm          = null;
	o.hFormRow       = null;
	o.hLabelForm     = null;
	o.hLabelRow      = null;
	o.hIcon          = null;
	o.hLabel         = null;
	o.hControlForm   = null;
	o.hControlRow    = null;
	o.oeBuild        = FDataControl_oeBuild;
	o.oeDesign       = FDataControl_oeDesign;
	o.onDesignBegin  = FDataControl_onDesignBegin;
	o.onDesignEnd    = FDataControl_onDesignEnd;
	o.onBuildBefore  = RMethod.empty;
	o.onBuildLabel   = FDataControl_onBuildLabel;
	o.onBuildControl = FDataControl_onBuildControl;
	o.onBuildAfter   = RMethod.empty;
	o.onBuildPanel   = FDataControl_onBuildPanel;
	o.setLabel       = FDataControl_setLabel;
	return o;
}
function FDataControl_onDesignBegin(){
	var o = this;
	o.base.MDesign.onDesignBegin.call(o);
	o.hEdit.disbaled = true;
}
function FDataControl_onDesignEnd(){
	var o = this;
	o.base.MDesign.onDesignEnd.call(o);
	o.hEdit.disbaled = false;
}
function FDataControl_oeBuild(event){
	var o = this;
	o.base.FControl.oeBuild.call(o, event);
	var hl = null;
	var hc = null;
	var ht = o.hForm;
	if(ELabel.All == o.labelType){
		if(EPosition.Top == o.labelPosition){
			hl = ht.insertRow().insertCell();
			hc = ht.insertRow().insertCell();
		}else if(EPosition.Right == o.labelPosition){
			var hRow = ht.insertRow();
			hc = hRow.insertCell();
			hl = hRow.insertCell();
		}else if(EPosition.Bottom == o.labelPosition){
			hc = ht.insertRow().insertCell();
			hl = ht.insertRow().insertCell();
		}else{
			var hRow = o.hFormRow = ht.insertRow();
			hl = hRow.insertCell();
			hc = hRow.insertCell();
		}
	}else if(ELabel.Label == o.labelType){
		hl = o.hFormRow = ht.insertRow().insertCell();
	}else if(ELabel.Hide == o.labelType){
      hc = o.hFormRow = ht.insertRow().insertCell();
	}
	if(hl){
	   o.onBuildLabel();
	   if(o.labelWidth){
	      hl.style.width = o.labelWidth;
	   }
	   if(o.labelHeight){
	      hl.style.height = o.labelHeight;
	   }
	   if(o.labelAlign){
	      hl.align = o.labelAlign;
	   }
      hl.appendChild(o.hLabelForm);
	}
   if(hc){
      o.onBuildControl();
      hc.appendChild(o.hControlForm);
   }
	return EEventStatus.Stop;
}
function FDataControl_oeDesign(event){
	var o = this;
	o.base.FControl.oeDesign.call(o, event);
	o.base.MDesign.oeDesign.call(o, event);
	switch(event.mode){
		case EDesign.Move:
			if(event.flag){
				o.hForm.border = 1;
				o.hForm.style.border = '1 solid #00FF88';
				o.hLabelForm.border = 1;
				o.hLabelForm.style.border = '1 solid #8800FF';
				o.hLabelForm.cellPadding = 1;
			}else{
				o.hForm.border = 0;
				o.hLabelForm.border = 0;
				o.hLabelForm.cellPadding = 0;
			}
			break;
		case EDesign.Border:
			if(event.flag){
				o.hForm.border = 1;
			}else{
				o.hForm.border = 0;
			}
			break;
	}
	return EEventStatus.Stop;
}
function FDataControl_onBuildLabel(){
	var o = this;
	var h = o.hLabelForm = RBuilder.newTable(null, o.style('LabelForm'));
	var hr = o.hLabelRow = h.insertRow();
	var hc = hr.insertCell();
	hc.width = 20;
	if(o.labelIcon){
		o.hIcon = RBuilder.appendIcon(hc, o.labelIcon);
	}
	var hc = o.hLabel = hr.insertCell();
	hc.noWrap = true;
   o.setLabel(o.label);
}
function FDataControl_onBuildControl(){
   var o = this;
   var h = o.hControlForm = RBuilder.newTable(null, o.style('ControlForm'));
   o.hControlRow = h.insertRow();
}
function FDataControl_onBuildPanel(){
	var o = this;
	o.hPanel = RBuilder.create(null, 'DIV');
	o.hForm = RBuilder.appendTable(o.hPanel, o.style('Form'));
}
function FDataControl_setLabel(s){
	var o = this;
	o.label = s;
	o.hLabel.innerText = RStr.nvl(s);
}
function FDataEditControl(o){
	o = RClass.inherits(this, o, FDataControl, MEditable, MFocus, MValue);
	o.styleEdit      = RClass.register(o, new TStyle('Edit'));
	o.styleEditForm  = RClass.register(o, new TStyle('EditForm'));
	o.hEditForm      = null;
	o.hEdit          = null;
	o.hDropPanel     = null;
	o.hDrop          = null;
	o.hasEditBorder  = true;
	o.oeBuild        = FDataEditControl_oeBuild;
	o.oeDesign       = FDataEditControl_oeDesign;
	o.oeAction       = FDataEditControl_oeAction;
	o.oeRefresh      = FDataEditControl_oeRefresh;
   o.onBuildLabel   = FDataEditControl_onBuildLabel;
   o.onBuildEdit    = FDataEditControl_onBuildEdit;
	o.onBuildControl = FDataEditControl_onBuildControl;
   o.doFocus        = FDataEditControl_doFocus;
   o.doBlur         = FDataEditControl_doBlur;
   o.focus          = FDataEditControl_focus;
	o.panel          = FDataEditControl_panel;
	return o;
}
function FDataEditControl_oeBuild(event){
	var o = this;
	o.base.FDataControl.oeBuild.call(o, event);
	o.setValue(o.dataValue);
	o.dsControl = o.topControl(MDataset);
	return EEventStatus.Stop;
}
function FDataEditControl_oeDesign(event){
	var o = this;
	o.base.FDataControl.oeDesign.call(o, event);
	switch(event.mode){
		case EDesign.Move:
			if(event.flag){
				o.hEditForm.border = 1;
				o.hEditForm.style.border = '1 solid #FF8800';
				o.hEdit.disabled = true;
			}else{
				o.hEditForm.border = 0;
				o.hEdit.disabled = false;
			}
			break;
		case EDesign.Border:
			if(event.flag){
				o.hEditForm.border = 1;
			}else{
				o.hEditForm.border = 0;
			}
			break;
	}
	return EEventStatus.Stop;
}
function FDataEditControl_oeAction(e){
	var o = this;
	o.base.FDataControl.oeAction.call(o, e);
	o.base.MEditable.oeAction.call(o, e);
}
function FDataEditControl_oeRefresh(event){
	var o = this;
	o.base.FDataControl.oeRefresh.call(o, e);
	o.base.MEditable.oeRefresh.call(o, e);
}
function FDataEditControl_onBuildLabel(hParent){
	var o = this;
	o.base.FDataControl.onBuildLabel.call(o, hParent);
	if(o.hLabel && o.validRequire){
		o.hLabel.style.color = EColor.Require;
	}
}
function FDataEditControl_onBuildEdit(){
   var o = this;
   var ht  = o.hEditForm = RBuilder.newTable(null, o.style('EditForm'));
	var t = null;
	if(o.hasEditBorder){
		t = new TBorder(o.isClass(MDropable) ? EBorderStyle.RoundDrop : EBorderStyle.Round);
	}else{
		t = new TBorder(EBorderStyle.None);
	}
	t.hForm = o.hEditForm;
	RBorder.build(t);
	o.hEditRow = t.hRow;
	o.hEditPanel = t.hPanel;
	o.hDropPanel = t.hDrop;
   if(o.editWidth){
      ht.width = o.editWidth;
   }
   if(o.editHeight){
      o.hEditPanel.height = o.editHeight;
   }
}
function FDataEditControl_onBuildControl(){
	var o = this;
	o.base.FDataControl.onBuildControl.call(o);
	o.onBuildEdit();
   o.hControlRow.insertCell().appendChild(o.hEditForm);
   if(o.hEdit){
      var h = o.hEdit;
      o.attachEvent('onFocus', h);
      o.attachEvent('onEditEnter', h);
      o.attachEvent('onEditLeave', h);
      o.attachEvent('onEditMouseOver', h);
      o.attachEvent('onEditMouseOut', h);
      o.attachEvent('onEditMouseDown', h);
      o.attachEvent('onEditMouseUp', h);
      o.attachEvent('onEditClick', h);
      o.attachEvent('onEditDoubleClick', h);
      o.attachEvent('onEditKeyDown', h);
      o.attachEvent('onEditKeyPress', h);
      o.attachEvent('onEditKeyUp', h);
      o.attachEvent('onEditChanged', h);
   }
}
function FDataEditControl_onDoubleClick(e){
	var o = this;
	if(o.isClass(MListView)){
		o.base.MListView.onDoubleClick.call(o, e);
	}
	if(o.isClass(MDropable)){
		o.drop();
	}
}
function FDataEditControl_onEditKeyDown(e){
	var o = this;
	o.base.MEditable.onEditKeyDown.call(o, e);
	if(EKey.Down==e.keyCode && o.isClass(MDropable)){
		o.drop();
	}
}
function FDataEditControl_panel(type){
	var o = this;
	if(EPanel.Edit == type){
		return o.hEdit;
	}else if(EPanel.Focus == type){
		return o.hEdit;
	}
	return o.base.FDataControl.panel.call(o, type);
}
function FDataEditControl_doFocus(e){
	var o = this;
	o.base.MFocus.doFocus.call(o, e);
	o.base.MEditable.doFocus.call(o, e);
}
function FDataEditControl_doBlur(e){
	var o = this;
	o.base.MFocus.doBlur.call(o, e);
	o.base.MEditable.doBlur.call(o, e);
}
function FDataEditControl_focus(e){
	var o = this;
	o.base.MFocus.focus.call(o, e);
	o.base.MEditable.focus.call(o, e);
}
function FEdit(o){
	o = RClass.inherits(this, o, FDataEditControl, MListView);
	o.editComplete   = RClass.register(o, new TPtyStr('editComplete'));
	o.editUnit       = RClass.register(o, new TPtyStr('editUnit'));
	o.dataType       = RClass.register(o, new TPtyStr('dataType'));
	o.editAlign      = RClass.register(o, new TPtyStr('editAlign', EAlign.Left));
	o.editCase       = RClass.register(o, new TPtyStr('editCase'));
	o.editPattern    = RClass.register(o, new TPtyStr('editPattern'));
	o.editLength     = RClass.register(o, new TPtyInt('editLength'));
	o.editFormat     = RClass.register(o, new TPtyStr('editFormat'));
	o.validLenmin    = RClass.register(o, new TPtyStr('validLenmin'));
	o.validLenmax    = RClass.register(o, new TPtyStr('validLenmax'));
	o.hUnit          = null;
	o.onBuildEdit    = FEdit_onBuildEdit;
	o.onEditKeyPress = FEdit_onEditKeyPress;
	o.onBuildControl = FEdit_onBuildControl;
	o.formatValue    = FEdit_formatValue;
	o.text           = FEdit_text;
	o.setText        = FEdit_setText;
	return o;
}
function FEdit_onBuildEdit(){
	var o = this;
	o.base.FDataEditControl.onBuildEdit.call(o);
	var h = o.hEdit = RBuilder.appendEdit(o.hEditPanel, o.style('Edit'));
	h.autocomplete = RBool.isTrue(o.editComplete) ? 'on' : 'off';
	if(o.editLength){
		h.maxLength = o.editLength;
	}
	if(o.hint){
		h.title = o.hint;
	}
}
function FEdit_onBuildControl(){
   var o = this;
   o.base.FDataEditControl.onBuildControl.call(o);
   if(o.editUnit){
      var h = o.hUnit = o.hControlRow.insertCell();
      h.className = o.style('Unit');
      h.innerText = o.editUnit;
   }
}
function FEdit_onEditKeyPress(e){
	var o = this;
	o.base.FDataEditControl.onEditKeyPress.call(o, e);
	if(o.editCase){
		RKey.fixCase(e, o.editCase);
	}
}
function FEdit_formatValue(s){
	return RStr.nvl(s);
}
function FEdit_text(){
	return this.hEdit.value;
}
function FEdit_setText(text){
	this.hEdit.value = text;
}
function FForm(o){
	o = RClass.inherits(this, o, FPanel, MForm, MDisplayAble, MValue, MAction, MDataset);
	o.isLoading       = false;
	o.focus           = null;
	o.lsnsLoaded      = new TListeners();
	o.lsnsClick       = new TListeners();
	o.onLoaded        = FForm_onLoaded;
	o.onDsFetchEnd    = FForm_onDsFetchEnd;
	o.onDsUpdateEnd   = FForm_onDsUpdateEnd;
	o.onLoadValue     = RMethod.empty;
	o.onSaveValue     = RMethod.empty;
	o.onLoadDataset   = FForm_onLoadDataset;
	o.getFormName     = FForm_getFormName;
	o.getTableName    = FForm_getTableName;
	o.connect         = FForm_connect;
	o.dsUpdate        = FForm_dsUpdate
	o.loadDocument    = FForm_loadDocument;
	o.focus           = FForm_focus;
	o.testStatus      = FForm_testStatus;
	o.reloadValue     = FForm_reloadValue;
	return o;
}
function FForm_onMouseDown(e){
	RConsole.find(FFocusConsole).focusClass(MDataset, this, e);
}
function FForm_onLoaded(){
	var o = this.form;
	var doc = this.document;
	if(o && doc){
		RControl.build(o, doc.root());
		o.isLoading = false;
		o.lsnsLoaded.process(o);
	}
}
function FForm_onDsFetchEnd(){
	var o = this;
	var v = o.dsControl.current();
	if(v){
		RLog.debug(o, v.dump());
		o.loadValue(v);
	}
}
function FForm_onDsUpdateEnd(){
	var o = this;
	var v = o.dsControl.current();
	if(v){
		RLog.debug(o, v.dump());
		o.loadValue(v);
	}
}
function FForm_onLoadDataset(ds){
	if(1 == ds.count){
		this.loadValue(ds.row(0));
	}
}
function FForm_getFormName(){
	return this.name;
}
function FForm_getTableName(){
	return this.formName;
}
function FForm_connect(service, type, action, attrs){
	var doc = new TXmlDoc();
	var root = doc.root();
	root.set('type', type);
	root.set('name', this.name);
	root.set('action', action);
	root.create('Attributes').value = attrs;
   var event = new TEvent(this, EXmlEvent.Send);
	event.url = service;
   event.document = doc;
   event.form = this;
	event.onLoad = this.onLoaded;
   RConsole.find(FXmlConsole).process(event);
}
function FForm_dsUpdate(){
	var o = this;
	var ps = o.components;
	for(var n=0; n<ps.count; n++){
		var p = ps.value(n);
		if(RClass.isClass(p, MValue)){
			if(p.inValid){
				RMsg.warn(o, RContext.get(MDataset, 'valid'));
				p.focus();
				return false;
			}
		}
	}
	o.base.MDataset.dsUpdate.call(o);
}
function FForm_loadDocument(doc){
	if(doc){
		var root = doc.root();
		if(root.isName('Table')){
			var o = this;
			o.loadConfig(root);
			o.buildColumns(root);
			o.buildRows(root);
		}
	}
}
function FForm_focus(){
	var o = this;
	o.base.FPanel.focus.call(o);
}
function FForm_testStatus(t){
	var o = this;
	var r = o.base.MDataset.testStatus.call(o, t);
	if(EDataAction.Fetch == t){
		return true;
	}else if(EDataAction.Fetch == t){
		return true;
	}else if(EDataAction.Search== t){
		return true;
	}else if(EDataAction.First == t){
		return false;
	}else if(EDataAction.Prior == t){
		return false;
	}else if(EDataAction.Next == t){
		return false;
	}else if(EDataAction.Last == t){
		return false;
	}else if(EDataAction.Action == t){
		return true;
	}
	return r;
}
function FForm_reloadValue(){
	this.loadValue(this.dsStore.row(0));
}
function FIconPicker(o){
	o = RClass.inherits(this, o, FDataEditControl, MListView);
	o.editComplete   = RClass.register(o, new TPtyStr('editComplete'));
	o.editUnit       = RClass.register(o, new TPtyStr('editUnit'));
	o.dataType       = RClass.register(o, new TPtyStr('dataType'));
	o.editAlign      = RClass.register(o, new TPtyStr('editAlign', EAlign.Left));
	o.editCase       = RClass.register(o, new TPtyStr('editCase'));
	o.editPattern    = RClass.register(o, new TPtyStr('editPattern'));
	o.editLength     = RClass.register(o, new TPtyInt('editLength'));
	o.editFormat     = RClass.register(o, new TPtyStr('editFormat'));
	o.validLenmin    = RClass.register(o, new TPtyStr('validLenmin'));
	o.validLenmax    = RClass.register(o, new TPtyStr('validLenmax'));
	o.iconDefault    = RClass.register(o, new TPtyStr('iconDefault'));
   o.hIconDisplay   = null;
	o.onBuildEdit    = FIconPicker_onBuildEdit;
	o.onEditKeyPress = FIconPicker_onEditKeyPress;
	o.onBuildControl = FIconPicker_onBuildControl;
	o.onBuildIconDefault = FIconPicker_onBuildIconDefault;
	o.formatValue    = FIconPicker_formatValue;
	o.text           = FIconPicker_text;
	o.setText        = FIconPicker_setText;
	return o;
}
function FIconPicker_onBuildEdit(){
   var o = this;
   o.base.FDataEditControl.onBuildEdit.call(o);
   var h = o.hEdit = RBuilder.appendEdit(o.hEditPanel, o.style('Edit'));
   h.autocomplete = RBool.isTrue(o.editComplete) ? 'on' : 'off';
   if(o.editLength){
      h.maxLength = o.editLength;
   }
   if(o.hint){
      h.title = o.hint;
   }
}
function FIconPicker_onBuildIconDefault(){
   var o = this;
   var hc = o.hIconDisplayTD = o.hEditRow.insertCell(0);
   hc.width = 18;
   hc.align = 'center';
   hc.noWrap = true;
   hc.style.layout='fixed';
   o.hIconDisplay = RBuilder.appendIcon(hc, RStr.nvl(o.iconDefault,"#system.config.config"));
}
function FIconPicker_onBuildControl(){
   var o = this;
   o.base.FDataEditControl.onBuildControl.call(o);
}
function FIconPicker_onEditKeyPress(e){
	var o = this;
	o.base.FDataEditControl.onEditKeyPress.call(o, e);
	if(o.editCase){
		RKey.fixCase(e, o.editCase);
	}
	o.hIconDisplay.src = RRes.iconPath(o.text());
}
function FIconPicker_formatValue(s){
	return RStr.nvl(s);
}
function FIconPicker_text(){
	return this.hEdit.value;
}
function FIconPicker_setText(text){
   var o = this;
	o.hEdit.value = text;
}
function FLabel(o){
	o = RClass.inherits(this, o, FDataControl);
	o.onBuildEdit   = FLabel_onBuildEdit;
	o.loadConfig    = FLabel_loadConfig;
	o.saveConfig    = FLabel_saveConfig;
	o.text          = FLabel_text;
	o.setText       = FLabel_setText;
	return o;
}
function FLabel_onBuildEdit(){
	this.hEdit = RBuilder.newEdit(this.parent.hPanel);
}
function FLabel_loadConfig(config){
	this.base.FDataControl.loadConfig.call(this, config);
}
function FLabel_saveConfig(config){
	this.base.FDataControl.saveConfig.call(this, config)
}
function FLabel_text(){
	return this.hEdit.value;
}
function FLabel_setText(text){
	this.hEdit.value = text;
}
function FListBox(o){
	o = RClass.inherits(this, o, FPanel, MValue, MHorizontal);
	o.lsnsClick   = new TListeners();
	o.onLoadValue = FListBox_onLoadValue;
	o.onSaveValue = FListBox_onSaveValue;
	o.loadConfig  = FListBox_loadConfig;
	o.appendLine  = FListBox_appendLine;
	return o;
}
function FListBox_onLoadValue(v){
}
function FListBox_onSaveValue(v){
	v.set('name', this.name);
}
function FListBox_loadConfig(c){
	var o = this;
	o.base.FPanel.loadConfig.call(o, c);
}
function FListBox_appendLine(){
	var h = this.hPanelTable = RBuilder.appendTable(this.hContainer, null, 10, 10, 10);
	this.hPanelLine = this.hPanelTable.insertRow();
	return h;
}
function FListItem(o){
	o = RClass.inherits(this, o, FControl, MValue, MDesign, MHorizontal);
	o.styleIcon    = RClass.register(o, new TStyle('Icon'));
	o.styleLabel   = RClass.register(o, new TStyle('Label'));
	o.styleBody    = RClass.register(o, new TStyle('Body'));
	o.oeBuild      = FListItem_oeBuild;
	o.onBuildPanel = FListItem_onBuildPanel;
	o.onLoadValue  = FListItem_onLoadValue;
	o.onSaveValue  = FListItem_onSaveValue;
	o.formatValue  = FListItem_formatValue;
	o.text         = FListItem_text;
	o.setText      = FListItem_setText;
	return o;
}
function FListItem_oeBuild(event){
	var o = this;
	o.base.FControl.oeBuild.call(o, event);
	if(event.isBefore()){
		var hTab = o.hForm = RBuilder.appendTable(o.hPanel, o.style('Body'));
		var hRow = hTab.insertRow();
		var hCell = hRow.insertCell();
		hCell.className = o.style('Icon');
		o.hIcon = RBuilder.appendIcon(hCell, 'arrow');
		var hCell = hRow.insertCell();
		var h = o.hLabel = RBuilder.append(hCell, 'SPAN', o.style('Label'));
		h.innerText = o.label;
	}
}
function FListItem_onBuildPanel(){
	this.hPanel = RBuilder.create(null, 'DIV');
}
function FListItem_onLoadValue(){
}
function FListItem_onSaveValue(config){
	var node = config.create('ListItem');
	node.set('name', this.name);
}
function FListItem_formatValue(s){
	return RStr.nvl(s);
}
function FListItem_text(){
	return this.hEdit.value;
}
function FListItem_setText(text){
	this.hEdit.value = text;
}
function FListView(o){
	o = RClass.inherits(this, o, FContainer, MShadow);
	o.type           = null;
	o.lovControl     = null;
	o.listView       = null;
	o.hForm          = null;
	o.hMessages      = null;
	o.ohClearClick   = FListView_ohClearClick;
	o.ohCloseClick   = FListView_ohCloseClick;
	o.ohResetClick   = FListView_ohResetClick;
	o.ohLoaded       = FListView_ohLoaded;
	o.oeBuild        = FListView_oeBuild;
	o.onBuildPanel   = FListView_onBuildPanel;
	o.onBuildFields  = FListView_onBuildFields;
	o.onBuildButton  = FListView_onBuildButton;
	o.onBuildData    = FListView_onBuildData;
	o.onKeyDown      = FListView_onKeyDown;
	o.buildField     = FListView_buildField;
	o.linkLovControl = FListView_linkLovControl;
	o.isBuilded      = FListView_isBuilded;
	o.show           = FListView_show;
	o.hide           = FListView_hide;
	o.doSearch       = FListView_doSearch;
	o.selectRow      = FListView_selectRow;
	return o;
}
function FListView_ohCloseClick(){
	this.hide();
}
function FListView_ohClearClick(){
	var o = this;
	var cs = o.fieldsPanel.components;
	if(cs){
		for(var n=0; n<cs.count; n++){
			cs.value(n).clearSearch();
		}
	}
}
function FListView_ohResetClick(){
}
function FListView_ohLoaded(){
	this.lovControl.onBuildData(this.document.root());
}
function FListView_oeBuild(event){
	var o = this;
	o.base.FContainer.oeBuild.call(o, event);
	var hTab = RBuilder.appendTable(o.hPanel);
	hTab.width = '100%';
	hTab.height = '100%';
	var hRow = hTab.insertRow();
	var h = o.hTitlePanel = hRow.insertCell();
	h.className = o.style('TitlePanel');
	RBuilder.appendIcon(h, 'tool.search');
	RBuilder.appendText(h, '&nbsp;List of View');
	h.colSpan = 2;
	hRow = hTab.insertRow();
	var h = o.hFieldsPanel = hRow.insertCell();
	h.className = o.style('FieldsPanel');
	var h = o.hButtonPanel = hRow.insertCell();
	h.className = o.style('ButtonPanel');
	o.onBuildButton();
	return EEventStatus.Stop;
}
function FListView_onBuildPanel(){
	var o = this;
	o.hPanel = RBuilder.append(null, 'DIV');
	o.hPanel.style.zIndex = ELayer.Message;
}
function FListView_onBuildFields(){
	return;
	var o = this;
	var hTab = o.hFieldsTab = RBuilder.appendTable(o.hFieldsPanel, null, 10, 10);
	hTab.width = '100%';
	var hRow = hTab.insertRow();
	var hCel = hRow.insertCell();
	hCel.className = this.style('Title');
	hCel.innerText = 'Message:';
	var hRow = hTab.insertRow();
	var hCel = hRow.insertCell();
	hCel.className = this.style('Message');
	o.hMessages = RBuilder.appendTable(hCel);
	o.hMessages.width = '100%';
	var hRow = hTab.insertRow();
	var hCel = hRow.insertCell();
	hCel.className = this.style('Title');
	hCel.innerText = 'Description:';
	var hRow = hTab.insertRow();
	var hCel = hRow.insertCell();
	hCel.className = this.style('Description');
}
function FListView_onBuildButton(){
	var o = this;
	var hBtnTab = RBuilder.appendTable(o.hButtonPanel, null, 0, 0, 6);
	var hRow = hBtnTab.insertRow();
	var hCel = hRow.insertCell();
	var b = o.btnSelect = RClass.create(FButton);
	b.label = 'Select'
	b.width = '100%';
	b.addClickListener(o, o.selectRow);
	b.build(hBtnTab.insertRow().insertCell());
	var b = o.btnClose = RClass.create(FButton);
	b.label = 'Close';
	b.width = '100%';
	b.addClickListener(o, o.ohCloseClick);
	b.build(hBtnTab.insertRow().insertCell());
	var b = o.btnRefresh = RClass.create(FButton);
	b.label = 'Refresh';
	b.width = '100%';
	b.addClickListener(o, o.ohClearClick);
	b.build(hBtnTab.insertRow().insertCell());
	var hRow = hBtnTab.insertRow();
	var hCel = hRow.insertCell();
	hCel.innerHTML = '&nbsp;';
}
function FListView_buildField(c){
	var o = this;
	var hCell = o.hFieldsTab.insertRow().insertCell();
	hCell.innerText = c.label;
	o.fieldsPanel = RControl.create(FPanel);
	o.fieldsPanel.build();
	o.fieldsPanel.setPanel(hCel);
}
function FListView_linkLovControl(ctl){
	var o = this;
	o.lovControl = ctl;
	o.lovRefer = ctl.lovRefer;
	var doc = new TXmlDoc();
	var root = doc.root();
	root.set('action', 'dsPicker');
	RConsole.find('FEnvConsole').build(root);
	var dn = root.create('Control');
	dn.set('lov_refer', ctl.lovRefer);
	dn.set('lov_where', ctl.lovWhere);
	dn.set('lov_order', ctl.lovOrder);
	RLog.info(o, 'Send lov request (service={1},node={2})', ctl.lovRefer, root.dump());
	var e = new TEvent(o, EXmlEvent.Send);
	e.url = RService.url(ctl.lovService);
	e.document = doc;
	e.lovControl = o;
	e.onLoad = o.ohLoaded;
	RConsole.find(FXmlConsole).process(e);
}
function FListView_onBuildData(config){
	var o = this;
	var v = o.listView = RControl.fromNode(config, o.hFieldsPanel);
	v.hPanel.height = '100%';
	v.resize();
	v.addDblClickListener(o, o.selectRow);
	v.addSelectListener(o, o.selectRow);
	v.addKeyDownListener(o, o.onKeyDown);
	o.show();
}
function FListView_onKeyDown(sender, e){
	if(EKey.Esc == e.keyCode){
		this.hide();
	}
}
function FListView_show(){
	var o = this;
	if(!o.isShow()){
		o.base.FContainer.show.call(o);
		RWindow.setEnable(false);
		RWindow.moveCenter(o.hPanel);
		o.base.MShadow.show.call(o, true);
		o.focus();
		o.listView.focus();
	}
}
function FListView_hide(){
	var o = this;
	if(o.isShow()){
		o.base.FContainer.hide.call(o);
		o.base.MShadow.hide.call(o);
		RWindow.setEnable(true);
		o.lovControl.focus();
	}
}
function FListView_doSearch(){
	var o = this;
	var cs = o.fieldsPanel.components;
	if(cs){
		var sn = new TNode('Search');
		for(var n=0; n<cs.count; n++){
			cs.value(n).saveSearch(sn);
		}
		RLog.debug(o, 'Search value {1}', sn.dump());
	}
	o.hide();
}
function FListView_selectRow(table, row){
	var o = this;
	var fields = o.lovControl.lovFields;
	var dsCtl = o.lovControl.topControl(MDataset);
	if(dsCtl && fields){
		if(!row){
			row = o.listView.selectRow;
		}
		if(row){
			var flds = RStr.splitTwo(fields, ',');
			for(var n=0; n<flds.length; n++){
				var v = RStr.splitTwo(flds[n], ' ');
				dsCtl.dsSet(RStr.nvl(v[1], v[0]), row.get(v[0]));
			}
			dsCtl.loadValue(dsCtl.dsCurrent());
		}
	}
	o.hide();
}
function FListView_isBuilded(){
	return (null != this.listView);
}
function FMemo(o){
	o = RClass.inherits(this, o, FEdit);
	o.editOverflow   = RClass.register(o, new TPtyStr('editOverflow'));
	o.onBuildEdit = FMemo_onBuildEdit;
	return o;
}
function FMemo_onBuildEdit(){
	var o = this;
	o.base.FDataEditControl.onBuildEdit.call(o);
	var h = o.hEdit = RBuilder.append(o.hEditPanel, 'TEXTAREA', o.style('Edit'));
	h.style.overflowY = 'auto';
	if(RStr.equals(o.editOverflow,'N')){
	   h.wrap ='off';
	   h.style.overflowX = 'auto';
	}
	if(RStr.equals(o.editOverflow,'Y')){
	   h.style.overflowX = 'auto';
	}
	if(o.hint){
	      h.title = o.hint;
	}
}
function FMemoDrop(o){
	o = RClass.inherits(this, o, FDataEditControl, MDropable);
	o.dataMemo    = '';
	o.hForm       = null;
	o.hDrop       = null;
	o.hForm       = null;
	o.onBuildEdit = FMemoDrop_onBuildEdit;
	o.onEditEnd   = FMemoDrop_onEditEnd;
	o.loadConfig  = FMemoDrop_loadConfig;
	o.saveConfig  = FMemoDrop_saveConfig;
	o.formatValue = FMemoDrop_formatValue;
	o.formatText  = FMemoDrop_formatText;
	o.text        = FMemoDrop_text;
	o.setText     = FMemoDrop_setText;
	o.drop        = FMemoDrop_drop;
	return o;
}
function FMemoDrop_onBuildEdit(){
	var o = this;
	o.hEdit = RBuilder.create(null, 'TEXTAREA');
	o.hDrop = RBuilder.newIcon(null, 'ctl.memo');
}
function FMemoDrop_onEditEnd(editor){
	var o = this;
	RLog.debug(o, 'Begin (editor={1}:{2} value={3})', editor, editor?editor.value():'', o.dataValue);
	if(editor){
		var v = editor.value();
		var f = RStr.firstLine(v);
		o.setText(f);
		o.dataValue = v;
		o.dataMemo = v.substr(f.length);
	}
	o.base.FDataEditControl.onEditEnd.call(o);
	RLog.debug(o, 'End (editor={1} value={2})', editor, o.dataValue);
}
function FMemoDrop_onKeyDown(){
	if(EKey.Down == event.keyCode){
		this.drop();
	}
}
function FMemoDrop_onDoubleClick(){
	this.drop();
}
function FMemoDrop_loadConfig(config){
	var o = this;
	o.base.FDataEditControl.loadConfig.call(o, config);
}
function FMemoDrop_saveConfig(config){
	this.base.FDataEditControl.saveConfig.call(this, config)
}
function FMemoDrop_formatValue(s){
	return s + this.dataMemo;
}
function FMemoDrop_formatText(s){
	return RStr.firstLine(s);
}
function FMemoDrop_text(){
	return this.hEdit.value;
}
function FMemoDrop_setText(text){
	this.hEdit.value = text;
}
function FMemoDrop_drop(){
	var o = this;
	if(o.canEdit){
		o.dataValue = o.hEdit.value + o.dataMemo;
		var editor = o.editConsole.focus(o, FMemoDropEditor, o.name);
		editor.linkPanel(o.hControlPanel, o.hEdit);
		editor.setValue(o.dataValue);
		editor.show();
	}
}
function FMemoDrop_focus(){
	this.hEdit.focus();
}
function FMemoDropEditor(o){
	o = RClass.inherits(this, o, FDropEditor);
	o.MinWidth     = 240;
	o.onBuildEdit  = FMemoDropEditor_onBuildEdit;
	o.onKeyDown    = FMemoDropEditor_onKeyDown;
	o.oeShow       = FMemoDropEditor_oeShow;
	o.focus        = FMemoDropEditor_focus;
	o.value        = FMemoDropEditor_value;
	o.setValue     = FMemoDropEditor_setValue;
	return o;
}
function FMemoDropEditor_onBuildEdit(){
	var o = this;
	var hFormTab = o.hForm = RBuilder.appendTable(o.hPanel);
	var hTltCel = hFormTab.insertRow().insertCell();
	var hTab = RBuilder.appendTable(hTltCel);
	var hRow = hTab.insertRow();
	var hCel = hRow.insertCell();
	o.hEditCel = hCel;
	o.hEdit = RBuilder.append(hCel, 'INPUT', o.style('Edit'));
	var hCel = hRow.insertCell()
	o.hDrop = RBuilder.appendIcon(hCel, 'ctl.memo', o.style('Drop'));
	var hDrpCel = hFormTab.insertRow().insertCell();
	o.hDropPanel = RBuilder.append(hDrpCel, 'DIV', o.style('DropPanel'));
	o.hEditor = RBuilder.append(o.hDropPanel, 'TEXTAREA', o.style('Memo'));
	o.linkEvent(o.hEditor);
}
function FMemoDropEditor_onKeyDown(){
	var o = this;
	var kc = event.keyCode;
	if(EKey.Esc == kc){
		this.hEditor.value = o.dataValue;;
		o.editStatus = EEditStatus.Cancel;
		o.onBlur();
	}else if(event.ctrlKey && EKey.Enter == kc){
		o.editStatus = EEditStatus.Ok;
		o.onBlur();
	}
}
function FMemoDropEditor_oeShow(event){
	var o = this;
	o.base.FDropEditor.oeShow.call(o, event);
	if(event.isAfter()){
		RHtml.toRect(o.rect, o.hDropPanel);
		RWindow.showShadow(true, o.rect);
	}
}
function FMemoDropEditor_focus(){
	this.hEditor.focus();
}
function FMemoDropEditor_value(){
	return this.hEditor.value;
}
function FMemoDropEditor_setValue(value){
	var o = this;
	value = RStr.nvl(value);
	o.changed = false;
	o.dataValue = value;
	o.dataValue = value;
	o.hEdit.value = RStr.firstLine(value);
	o.hEditor.value = value;
}
function FNumber(o){
	o = RClass.inherits(this, o, FDataEditControl, MListView , MMouseWheel);
	o.dataType         = RClass.register(o, new TPtyStr('dataType'));
	o.editAlign        = RClass.register(o, new TPtyStr('editAlign', EAlign.Right));
	o.editLength       = RClass.register(o, new TPtyInt('editLength'));
   o.editSplitter     = RClass.register(o, new TPtyStr('editSplitter'));
	o.editPattern      = RClass.register(o, new TPtyStr('editPattern'));
   o.editIncreate     = RClass.register(o, new TPtyStr('editIncreate', 1));
   o.editPrecision    = RClass.register(o, new TPtyStr('editPrecision', 1));
   o.editMinValue     = RClass.register(o, new TPtyStr('editMinValue'));
   o.editMaxValue     = RClass.register(o, new TPtyStr('editMaxValue'));
   o.editUnit         = RClass.register(o, new TPtyStr('editUnit'));
	o.onUpIconMenter   = RClass.register(o, new HMouseEnter('onUpIconMenter'),    FNumber_onUpIconMenter);
	o.onUpIconMdown    = RClass.register(o, new HMouseDown('onUpIconMdown'),      FNumber_onUpIconMdown);
	o.onUpIconMup      = RClass.register(o, new HMouseUp('onUpIconMup'),          FNumber_onUpIconMup);
	o.onDownIconMenter = RClass.register(o, new HMouseEnter('onDownIconMenter'),  FNumber_onDownIconMenter);
	o.onDownIconMdown  = RClass.register(o, new HMouseDown('onDownIconMdown'),    FNumber_onDownIconMdown);
	o.onDownIconMup    = RClass.register(o, new HMouseUp('onDownIconMup'),        FNumber_onDownIconMup);
	o.styleUnit        = RClass.register(o, new TStyle('Unit'));
	o.styleArrayForm   = RClass.register(o, new TStyle('ArrayForm'));
	o.styleUpButton    = RClass.register(o, new TStyle('UpButton'));
	o.styleDownButton  = RClass.register(o, new TStyle('DownButton'));
	o.hUpIcon          = null;
	o.hDownIcon        = null;
   o.ohEditKeyPress   = FNumber_ohEditKeyPress;
	o.onBuildControl   = FNumber_onBuildControl;
	o.onEditKeyDown    = FNumber_onEditKeyDown;
	o.onEditKeyUp      = FNumber_onEditKeyUp;
	o.onEditKeyPress   = FNumber_onEditKeyPress;
	o.onMouseWheel     = FNumber_onMouseWheel;
	o.onBuildEdit      = FNumber_onBuildEdit;
	o.onBuildArrow     = FNumber_onBuildArrow;
	o.validText        = FNumber_validText;
	o.formatValue      = FNumber_formatValue;
	o.text             = FNumber_text;
	o.setText          = FNumber_setText;
	o.formatNum        = FNumber_formatNum;
	o.addValue         = FNumber_addValue;
	o.subValue         = FNumber_subValue;
	return o;
}
function FNumber_addValue(e){
   var o = this;
   if(o.validText(e)){
      o.hEdit.value = RInt.parse(o.displayText) + RInt.parse(o.editIncreate);
      o.storeText();
   }
}
function FNumber_subValue(e){
   var o = this
   if(o.validText(e)){
      this.hEdit.value = RInt.parse(o.displayText) - RInt.parse(o.editIncreate);
      o.storeText();
   }
}
function FNumber_validText(e){
   var o = this;
   return true;
}
function FNumber_ohEditKeyPress(e, he){
   var o = this;
   var o = this;
}
function FNumber_onMouseWheel(e){
   var o = this;
   if(e.wheelDelta > 0){
      o.addValue();
   }
   else{
      o.subValue();
   }
}
function FNumber_onEditKeyPress(e){
   var o = this;
   o.base.FDataEditControl.onEditKeyPress.call(o, e);
   if(!RStr.inChars(String.fromCharCode(e.keyCode), RInt.Chars)){
      RKey.eventClear(e);
   }
}
function FNumber_onEditKeyDown(e) {
    var o = this;
    if( EKey.Up == e.keyCode ){
       e.source.hUpIcon.src = RRes.iconPath("#control.numberUpActive");
       o.addValue();
    }
    if( EKey.Down == e.keyCode ){
       e.source.hDownIcon.src = RRes.iconPath("#control.numberDownActive");
       o.subValue();
    }
}
function FNumber_onEditKeyUp(e) {
   if( EKey.Up == e.keyCode ){
      e.source.hUpIcon.src = RRes.iconPath("#control.numberUp");
   }
   if( EKey.Down == e.keyCode ){
      e.source.hDownIcon.src = RRes.iconPath("#control.numberDown");
   }
}
function FNumber_onUpIconMenter(e) {
}
function FNumber_onUpIconMdown(e) {
   var o = this;
   e.srcElement.src = RRes.iconPath("#control.numberUpActive");
   o.addValue(e);
}
function FNumber_onUpIconMup(e) {
   e.srcElement.src = RRes.iconPath("#control.numberUp");
}
function FNumber_onDownIconMenter(e) {
}
function FNumber_onDownIconMdown(e) {
   var o = this;
   e.srcElement.src = RRes.iconPath("#control.numberDownActive");
   o.subValue(e);
}
function FNumber_onDownIconMup(e) {
   e.srcElement.src = RRes.iconPath("#control.numberDown");
}
function FNumber_onBuildEdit(){
	var o = this;
	o.base.FDataEditControl.onBuildEdit.call(o);
	var h = o.hEdit = RBuilder.appendEdit(o.hEditPanel, o.style('Edit'));
	o.attachEvent('onEditKeyDown', h, o.ohEditKeyPress);
	if(o.editLength){
		h.maxLength = o.editLength;
	}
	if(o.hint){
		h.title = o.hint;
	}
}
function FNumber_onBuildControl(){
   var o = this;
   o.base.FDataEditControl.onBuildControl.call(o);
   if(o.editUnit){
      var h = o.hUnit = o.hControlRow.insertCell();
      h.className = o.style('Unit');
      h.innerText = o.editUnit;
   }
}
function FNumber_onBuildArrow(){
   var o = this;
   var hc      = o.hEditRow.insertCell();
   hc.width = 18;
   var ht      = RBuilder.appendTable(hc,o.style("ArrayForm"));
   var hcUp    = ht.insertRow().insertCell();
   hcUp.className = o.style('UpButton');
   var hcDown  = ht.insertRow().insertCell();
   hcDown.className = o.style('DownButton');
   o.hUpIcon   = RBuilder.appendIcon(hcUp,  "#control.numberUp");
   o.attachEvent('onUpIconMdown', o.hUpIcon);
   o.attachEvent('onUpIconMup', o.hUpIcon);
   o.hDownIcon = RBuilder.appendIcon(hcDown,"#control.numberDown");
   o.attachEvent('onDownIconMdown', o.hDownIcon);
   o.attachEvent('onDownIconMup', o.hDownIcon);
}
function FNumber_formatValue(s){
	return this.formatNum(s);
}
function FNumber_text(){
	return this.hEdit.value;
}
function FNumber_setText(text){
	this.hEdit.value = text;
}
function FNumber_formatNum(v){
	var s = "";
   var count = 0;
   for ( var i = v.length; i >= 0; i--) {
      if (count % this.editSplitLen == 0) {
         if (count == 0) {
            s = v.charAt(i - 1) + s;
         } else {
            if (i != 0) {
               s = v.charAt(i - 1) + this.editSplitType + s;
            } else {
               s = v.charAt(i - 1) + s;
            }
         }
      } else {
         s = v.charAt(i - 1) + s;
      }
      count++;
   }
   return s;
}
function FPanel(o){
	o = RClass.inherits(this, o, FContainer, MFocus);
	o.hContainer     = null;
	o.hPanelTable    = null;
	o.hPanelLine     = null;
	o.oeDesign       = FPanel_oeDesign;
	o.onBuildPanel   = FPanel_onBuildPanel;
	o.insertPosition = FPanel_insertPosition;
	o.appendLine     = FPanel_appendLine;
	o.appendChild    = FPanel_appendChild;
	o.moveChild      = FPanel_moveChild;
	return o;
}
function FPanel_oeDesign(event){
	var o = this;
	o.base.FContainer.oeDesign.call(o, event);
	if(event.isAfter()){
		switch(event.mode){
			case EDesign.Move:
				break;
			case EDesign.Border:
				if(event.flag){
					o.hPanel.border = 1;
				}else{
					o.hPanel.border = 0;
				}
				break;
		}
	}
}
function FPanel_onBuildPanel(){
	var o = this;
	o.hPanel = RBuilder.create(null, 'SPAN');
	o.attachEvent('onClick', o.hPanel);
	var h = RBuilder.appendTable(o.hPanel);
	h.width = '100%';
	o.hContainer = h.insertRow().insertCell();
}
function FPanel_appendLine(){
	var o = this;
	var h = o.hPanelTable = RBuilder.appendTable(o.hContainer);
	h.style.paddingBottom = 3;
	o.hPanelLine = h.insertRow();
	return h;
}
function FPanel_appendChild(ctl){
	var o = this;
	if(RClass.isClass(ctl, MTop)){
		return;
	}
	if(!o.hPanelLine){
		o.appendLine();
	}
	if(RClass.isClass(ctl, MHorizontal)){
		if(o.hPanelTable.rows[0].cells.length == 0){
			o.hContainer.insertBefore(ctl.hPanel, o.hPanelTable);
		}else{
			o.hContainer.appendChild(ctl.hPanel);
			o.appendLine();
		}
		return;
	}
	var hCell = o.hPanelLine.insertCell();
	ctl.hPanelLine = o.hPanelTable;
	hCell.appendChild(ctl.hPanel);
	if(!ctl.nowrap && (this.controls.last() != ctl)){
		o.appendLine();
	}
}
function FPanel_insertPosition(cf, ct, idx){
	var o = this;
	var ms = o.components;
	var cs = o.controls;
	ms.removeValue(cf);
	cs.removeValue(cf);
	if(ct){
		var index = ms.indexOfValue(ct);
		ms.insert(index+idx, cf.name, cf);
		var index = cs.indexOfValue(ct);
		cs.insert(index+idx, cf.name, cf);
	}else{
		ms.push(cf);
		cs.push(cf);
	}
}
function FPanel_moveChild(cf, ct, pos){
	if(!(cf && ct && pos) || (cf == ct)){
		return;
	}
	var o = this;
	var hPanel = o.hPanel;
	var moved = false;
	var cfh = RClass.isClass(cf, MHorizontal);
	var hCfTd = RHtml.parent(cf.hPanel, 'TD');
	var hCfTab = RHtml.parent(cf.hPanel, 'TABLE');
	var cth = RClass.isClass(ct, MHorizontal);
	var hTd = RHtml.parent(ct.hPanel, 'TD');
	var hTable = RHtml.parent(hTd, 'TABLE');
	switch(pos){
		case EPosition.Before:
			var hRow = hTable.rows[0];
			for(var n=0; n<hRow.cells.length; n++){
				if(hRow.cells[n] == hTd){
					var hCell = hRow.insertCell(hTd.cellIndex);
					hCell.appendChild(cf.hPanel);
					o.insertPosition(cf, ct, 0);
					cf.nowrap = true;
					cf.hPanelLine = hTable;
					moved = true;
					break;
				}
			}
			break;
		case EPosition.After:
			var hRow = hTable.rows[0];
			for(var n=0; n<hRow.cells.length; n++){
				if(hRow.cells[n] == hTd){
					var hCfTd = RHtml.parent(cf.hPanel, 'TD');
					var hCell = hRow.insertCell(hTd.cellIndex+1);
					hCell.appendChild(cf.hPanel);
					o.insertPosition(cf, ct, 1);
					cf.nowrap = false;
					cf.hPanelLine = hTable;
					ct.nowrap = true;
					moved = true;
					break;
				}
			}
			break;
		case EPosition.LineBefore:
			if(cth){
				if(cfh){
					o.hContainer.insertBefore(cf.hPanel, ct.hPanel);
				}else{
					var hNewTab = o.appendLine();
					o.hContainer.insertBefore(hNewTab, ct.hPanel);
					var hCell = o.hPanelLine.insertCell();
					hCell.appendChild(cf.hPanel);
					cf.hPanelLine = hNewTab;
				}
				o.insertPosition(cf, ct, 0);
			}else{
				var count = o.hContainer.children.length;
				for(var n=0; n<count; n++){
					if(o.hContainer.children[n] == hTable){
						if(cfh){
							o.hContainer.insertBefore(cf.hPanel, hTable);
						}else{
							var hNewTab = o.appendLine();
							o.hContainer.insertBefore(hNewTab, hTable);
							var hCell = o.hPanelLine.insertCell();
							hCell.appendChild(cf.hPanel);
							cf.hPanelLine = hNewTab;
							moved = true;
						}
						o.insertPosition(cf, ct, 0);
						cf.nowrap = false;
						break;
					}
				}
			}
			break;
		case EPosition.LineAfter:
			if(cfh){
				o.hContainer.appendChild(cf.hPanel);
			}else{
				var hNewTab = o.appendLine();
				var hCell = o.hPanelLine.insertCell();
				hCell.appendChild(cf.hPanel);
				hCell.appendChild(cf.hPanel);
				moved = true;
			}
			o.insertPosition(cf, null, 0);
			ct.nowrap = false;
			cf.nowrap = false;
			break;
	}
	if(moved){
		hCfTd.removeNode(true);
		if(hCfTab.rows[0].cells.length == 0){
			hCfTab.removeNode(true);
		}
	}
}
function FPicker(o){
	o = RClass.inherits(this, o, FDataEditControl, MDropable);
	o.editFormat    = RClass.register(o, new TPtyStr('editFormat'));
	o.hForm         = null;
	o.hDrop         = null;
	o.hForm         = null;
	o.onBuildEdit   = FPicker_onBuildEdit;
	o.onEditEnd     = FPicker_onEditEnd;
	o.text          = FPicker_text;
	o.setText       = FPicker_setText;
	o.drop          = FPicker_drop;
	return o;
}
function FPicker_onBuildEdit(){
	var o = this;
	o.hEdit = RBuilder.newEdit();
	o.hDrop = RBuilder.newIcon(null, 'ctl.ds-ds');
}
function FPicker_onEditEnd(editor){
	var o = this;
	if(editor){
		var dsCtl = o.topControl(MDataset);
		if(dsCtl){
			dsCtl.dsMovePosition(editor.position());
		}
	}
	o.base.FDataEditControl.onEditEnd.call(o);
	RLog.debug(o, 'Edit end (editor={1} value={2})', editor, o.dataValue);
}
function FPicker_text(){
	return this.hEdit.value;
}
function FPicker_setText(text){
	this.hEdit.value = text;
}
function FPicker_drop(){
	var o = this;
	if(o.canDrop() && o.canEdit){
		var dsCtl = o.topControl(MDataset);
		if(dsCtl){
			var editor = RConsole.find(FEditConsole).focus(o, FPickerEditor, o.name);
			editor.linkPanel(o.hControlPanel, o.hEdit, o.hEditForm);
			editor.setDsControl(dsCtl);
			editor.setValue(o.hEdit.value);
			editor.show();
		}
	}
}
function FPickerEditor(o){
	o = RClass.inherits(this, o, FDropEditor, MShadow);
	o.MaxHeight    = 240;
	o.originIndex  = null;
	o.selectIndex  = null;
	o.dsControl    = null;
	o.dataset      = null;
	o.columns      = new TList();
	o.ohDropMdown  = FPickerEditor_ohDropMdown;
	o.ohDropMup    = FPickerEditor_ohDropMup;
	o.ohRowEnter   = FPickerEditor_ohRowEnter;
	o.ohRowLeave   = FPickerEditor_ohRowLeave;
	o.ohRowMdown   = FPickerEditor_ohRowMdown;
	o.onBuildEdit  = FPickerEditor_onBuildEdit;
	o.onKeyDown    = FPickerEditor_onKeyDown;
	o.isColumn     = FPickerEditor_isColumn;
	o.panel        = FPickerEditor_panel;
	o.position     = FPickerEditor_position;
	o.setPosition  = FPickerEditor_setPosition;
	o.buildFlag    = FPickerEditor_buildFlag;
	o.setDsControl = FPickerEditor_setDsControl;
	o.select       = FPickerEditor_select;
	o.focus        = FPickerEditor_focus;
	o.show         = FPickerEditor_show;
	o.hide         = FPickerEditor_hide;
	o.setValue     = FPickerEditor_setValue;
	return o;
}
function FPickerEditor_ohDropMdown(){
	var o = this.link;
	if(o.inEdit && RClass.isClass(o, FPickerEditor)){
		RLog.debug(o, 'FPickerEditor.ohDropMdown', 'Drop mouse down');
		o.isSkipBlur = true;
	}
}
function FPickerEditor_ohDropMup(){
	var o = this.link;
	if(o.inEdit && RClass.isClass(o, FPickerEditor)){
		RLog.debug(o, 'FPickerEditor.ohDropMup', 'Drop mouse up');
		o.hEdit.focus();
	}
}
function FPickerEditor_ohRowEnter(){
	var o = this.link;
	if(RClass.isClass(o, FPickerEditor)){
		if(!this.ptySelect){
			this.className = o.style('RowHover');
		}
	}
}
function FPickerEditor_ohRowLeave(){
	var o = this.link;
	if(RClass.isClass(o, FPickerEditor)){
		if(!this.ptySelect){
			this.className = o.style('Row');
		}
	}
}
function FPickerEditor_ohRowMdown(){
	var o = this.link;
	if(RClass.isClass(o, FPickerEditor)){
		o.selectIndex = this.ptyIndex;
		o.editStatus = EEditStatus.Ok;
		o.inEdit = false;
		o.hEditor.blur();
	}
}
function FPickerEditor_onBuildEdit(){
	var o = this;
	var hFormTab = o.hForm = RBuilder.appendTable(o.hPanel);
	var hTltCel = hFormTab.insertRow().insertCell();
	var hTab = RBuilder.appendTable(hTltCel);
	var hRow = hTab.insertRow();
	var hCel = hRow.insertCell();
	o.hEditCel = hCel;
	o.hEditor = RBuilder.append(hCel, 'INPUT', o.style('Edit'));
	o.hEdit = o.hEditor;
	var hCel = hRow.insertCell()
	o.hDrop = RBuilder.appendIcon(hCel, 'ctl.ds-ds', o.style('Drop'));
	var hDrpCel = hFormTab.insertRow().insertCell();
	var h = o.hDropPanel = RBuilder.append(hDrpCel, 'DIV', o.style('DropPanel'));
	h.link = o;
	h.onmousedown = o.ohDropMdown;
	h.onmouseup = o.ohDropMup;
	var h = o.hRowsPanel = RBuilder.appendTable(o.hDropPanel, null, 0, 1, 1);
	h.width = '100%';
	o.linkKeyEvent(o.hEdit);
}
function FPickerEditor_onKeyDown(event){
	var o = this;
	var kc = event.keyCode;
	if(EKey.Up == kc){
		o.select(o.selectIndex-1);
	}else if(EKey.Down == kc){
		o.select(o.selectIndex+1);
	}else if(EKey.Esc == kc){
		o.selectIndex = o.originIndex;
		o.editStatus = EEditStatus.Cancel;
		o.inEdit = false;
		o.hEditor.blur();
	}else if(EKey.Enter == kc){
		o.editStatus = EEditStatus.Ok;
		o.inEdit = false;
		o.hEditor.blur();
	}
}
function FPickerEditor_isColumn(c){
	if(c.dispPicker){
		return true;
	}
	return false;
}
function FPickerEditor_panel(type){
	var o = this;
	if(EPanel.Shadow == type){
		return o.hDropPanel;
	}
	return o.base.FDropEditor.panel.call(o, type);
}
function FPickerEditor_position(){
	return this.selectIndex;
}
function FPickerEditor_setPosition(p){
	var o = this;
	o.originIndex = p;
	o.selectIndex = p;
}
function FPickerEditor_buildFlag(hRow){
	var h = hRow.insertCell();
	h.className = this.style('Flag');
	return h;
}
function FPickerEditor_setDsControl(dsCtl, force){
	var o = this;
	if(o.dataset == dsCtl.dsControl){
		if(!force && o.hRowsPanel.rows.length){
			return;
		}
	}
	o.hPanel.style.display = 'block';
	var dc = o.dsControl = dsCtl;
	var ds = o.dataset = dsCtl.dsControl;
	o.originIndex = ds.position;
	o.selectIndex = ds.position;
	var count = ds.count();
	var cols = o.columns;
	cols.clear();
	var cs = dsCtl.components;
	for(var i=0; i<cs.count; i++){
		var c = cs.value(i);
		if(o.isColumn(c)){
			o.columns.push(c);
		}
	}
	RHtml.clear(o.hRowsPanel);
	var hRow = o.hRowsPanel.insertRow();
	o.buildFlag(hRow);
	for(var i=0; i<cols.count; i++){
		var c = cols.get(i);
		var hCel = hRow.insertCell();
		hCel.className = o.style('Title');
		if(c.dispAlign){
			hCel.align = c.dispAlign;
		}
		hCel.innerText = RStr.nvl(c.label);
	}
	for(var n=0; n<count; n++){
		var row = ds.row(n);
		var hRow = o.hRowsPanel.insertRow();
		hRow.link = o;
		hRow.ptyIndex = n;
		hRow.className = o.style('Row');
		hRow.onmouseenter = o.ohRowEnter;
		hRow.onmouseleave = o.ohRowLeave;
		hRow.onmousedown = o.ohRowMdown;
		o.buildFlag(hRow);
		for(var i=0; i<cols.count; i++){
			var c = cols.get(i);
			var hCel = hRow.insertCell();
			if(c.dispAlign){
				hCel.align = c.dispAlign;
			}
			hCel.innerText = RStr.nvl(row.get(c.dataName));
		}
	}
	var hRow = o.hRowsPanel.insertRow();
	RBuilder.appendEmpty(o.buildFlag(hRow), 3);
	for(var i=0; i<cols.count; i++){
		var c = cols.get(i);
		var hCel = hRow.insertCell();
		if(c.pickerWidth){
			RBuilder.appendEmpty(hCel, c.pickerWidth);
		}
	}
	o.hDropPanel.style.height = o.hRowsPanel.offsetHeight+2;
}
function FPickerEditor_select(n){
	var o = this;
	o.selectIndex = RInt.toRange(n, 0, o.dataset.count()-1);
	var rows = o.hRowsPanel.rows;
	for(var n=0; n<rows.length; n++){
		var row = rows[n];
		if(row.ptyIndex == o.selectIndex){
			row.className = o.style('RowSelect');
			row.ptySelect = true;
			row.scrollIntoView(false);
		}else{
			row.className = o.style('Row');
			row.ptySelect = false;
		}
	}
}
function FPickerEditor_focus(){
	this.hEdit.focus();
}
function FPickerEditor_show(v){
	var o = this;
	o.base.FDropEditor.show.call(o, v);
	o.select(o.selectIndex);
	var height = o.hDropPanel.offsetHeight;
	o.hDropPanel.style.height = Math.min(height, o.MaxHeight);
	o.base.MShadow.show.call(o, v);
	o.isSkipBlur = false;
}
function FPickerEditor_hide(){
	var o = this;
	o.base.FDropEditor.hide.call(o);
	o.base.MShadow.hide.call(o);
}
function FPickerEditor_setValue(value){
	var o = this;
	o.changed = false;
	o.dataValue = value;
	o.hEdit.value = value;
	o.select(o.selectIndex);
}
function FPicture(o){
	o = RClass.inherits(this, o, FDataEditControl, MFocus);
   o.picSrc = RClass.register(o, new TPtyStr('picSrc'));
   o.picWidth = RClass.register(o, new TPtyStr('picWidth'));
   o.picHeight = RClass.register(o, new TPtyStr('picHeight'));
	o.onBuildEdit    = FPicture_onBuildEdit;
	o.onEditKeyPress = FPicture_onEditKeyPress;
	o.formatValue    = FPicture_formatValue;
	o.text           = FPicture_text;
	o.setText        = FPicture_setText;
   o.onmouseover       = FPicture_ohMouseOver;
   o.onmouseout        = FPicture_ohMouseOut;
	return o;
}
function FPicture_onBuildEdit(){
	this.hEdit = RBuilder.newImage(null, this.picSrc,null,this.picWidth,this.picHeight)
}
function FPicture_ohMouseOver(){
   var o = this;
   alert(FPicture_ohMouseOver);
}
function FPicture_ohMouseOut(){
   var o = this;
   alert(FPicture_ohMouseOut);
}
function FPicture_onEditKeyPress(e){
	var o = this;
	alert(this);
	o.base.FDataEditControl.onEditKeyPress.call(o, e);
	if(o.editCase){
		RKey.fixCase(e, o.editCase);
	}
}
function FPicture_formatValue(s){
	return RStr.nvl(s);
}
function FPrepareAction(o){
	o = RClass.inherits(this, o, FDataAction);
	o.onLoaded      = FPrepareAction_onLoaded;
	return o;
}
function FPrepareAction_onLoaded(doc){
	var controls = this.parent.controls;
	var node = doc.root().find('Dataset');
	if(node){
		this.valuable.loadValue(node);
	}
}
function FProgress(o){
	o = RClass.inherits(this, o, FDataEditControl, MDropable);
	o.hForm       = null;
	o.hDrop       = null;
	o.hForm       = null;
	o.onBuildEdit = FProgress_onBuildEdit;
	o.onEditEnd   = FProgress_onEditEnd;
	o.text        = FProgress_text;
	o.setText     = FProgress_setText;
	o.drop        = FProgress_drop;
	return o;
}
function FProgress_onBuildEdit(){
	var o = this;
	o.hEdit = RBuilder.newEdit();
	o.hDrop = RBuilder.newIcon(null, 'ctl.clrdrop');
}
function FProgress_onEditEnd(editor){
	var o = this;
	RLog.debug(o, 'Begin (editor={0}:{1} value={2})', editor, editor?editor.color:'', o.dataValue);
	if(editor){
		o.setValue(editor.color);
	}
	o.base.FDataEditControl.onEditEnd.call(o);
	RLog.debug(o, 'End (editor={0} value={1})', editor, o.dataValue);
}
function FProgress_text(){
	return this.hEdit.value;
}
function FProgress_setText(text){
	var o = this;
	o.hEdit.value = text.toUpperCase();
	o.hDropPanel.style.backgroundColor = text;
}
function FProgress_drop(){
	var o = this;
	if(o.canDrop() && o.canEdit){
		var editor = RConsole.find(FEditConsole).focus(o, FProgressEditor);
		editor.linkPanel(o.hControlPanel, o.hEdit, o.hEditForm);
		editor.setColor(o.value());
		editor.show();
	}
}
function FRadio(o){
	o = RClass.inherits(this, o, FDataEditControl);
	o.dataTrue     = EBool.True;
	o.dataFalse    = null;
	o.onBuildEdit  = FRadio_onBuildEdit;
	o.loadConfig   = FRadio_loadConfig;
	o.saveConfig   = FRadio_saveConfig;
	o.formatValue  = FRadio_formatValue;
	o.text         = FRadio_text;
	o.setText      = FRadio_setText;
	o.setEditStyle = FRadio_setEditStyle;
	return o;
}
function FRadio_onBuildEdit(){
	this.hEdit = RBuilder.newCheck();
}
function FRadio_loadConfig(c){
	var o = this;
	o.base.FDataEditControl.loadConfig.call(o, c);
	o.dataTrue  = c.nvl('data_true', o.dataTrue);
	o.dataFalse = c.get('data_false');
}
function FRadio_saveConfig(c){
	var o = this;
	o.base.FDataEditControl.saveConfig.call(o, c)
	c.set('data_true',  o.dataTrue);
	c.set('data_false', o.dataFalse);
}
function FRadio_formatValue(s){
	return RStr.nvl(s);
}
function FRadio_text(){
	return this.hEdit.checked ? this.dataTrue : this.dataFalse;
}
function FRadio_setText(text){
	this.hEdit.checked = (this.dataTrue == text);
}
function FRadio_setEditStyle(style){
	var o = this;
	var h = o.panel(EPanel.Edit);
	switch(style){
		case EStyle.Edit:
			h.disabled = false;
			break;
		case EStyle.Readonly:
			h.disabled = true;
			break;
	}
}
function FRemoteAction(o){
	o = RClass.inherits(this, o, FComponent);
	o.isLoading     = false;
	o.service       = null;
	o.valuable      = null;
	o.onLoaded      = FRemoteAction_onLoaded;
	o.loadConfig    = FRemoteAction_loadConfig;
	o.saveConfig    = FRemoteAction_saveConfig;
	o.execute       = FRemoteAction_execute;
	return o;
}
function FRemoteAction_onLoaded(e){
	var o = this;
	var doc = e.document;
	var rs = RConsole.find(FResultConsole).checkService(doc.root());
	if(rs){
		RWindow.setEnable(true);
	}
	o.isLoading = false;
}
function FRemoteAction_loadConfig(config){
	var o = this;
	o.base.FComponent.loadConfig.call(o, config);
	o.service = config.get('service');
}
function FRemoteAction_saveConfig(config){
	var o = this;
	o.base.FComponent.saveConfig.call(o, config)
	config.set('service',  this.service);
}
function FRemoteAction_execute(service, config){
	var o = this;
	var svc = RService.parse(RStr.nvl(service, this.service));
	if(!svc){
		return alert('Unknown service');
	}
	RWindow.setEnable(false);
	var doc = new TXmlDoc();
	var root = doc.root();
	root.set('action', svc.action);
	RConsole.find(FEnvConsole).build(root);
	root.push(config);
	RLog.debug(this, 'Execute (service={1})\n{2}', svc.url, doc.dump());
	o.isLoading = true;
	var e = new TEvent(o, EXmlEvent.Send, o.onLoaded);
	e.url = svc.url;
	e.document = doc;
	e.action = this;
	RConsole.find(FXmlConsole).process(e);
}
function FSelect(o){
	o = RClass.inherits(this, o, FDataEditControl, MDropable);
	o.dataEmpty         = RClass.register(o, new TPtyBool('dataEmpty', true));
	o.editRefer         = RClass.register(o, new TPtyStr('editRefer', null));
	o.editCheck         = RClass.register(o, new TPtyStr('editCheck', null));
	o.items             = new TItems();
	o.dropIcon          = '#drop';
	o.onEditDoubleClick = FSelect_onEditDoubleClick;
	o.onEditKeyDown     = FSelect_onEditKeyDown;
	o.onBuildEdit       = FSelect_onBuildEdit;
	o.onEditEnd         = FSelect_onEditEnd;
	o.loadConfig        = FSelect_loadConfig;
	o.text              = FSelect_text;
	o.setText           = FSelect_setText;
	o.formatValue       = FSelect_formatValue;
	o.validText         = FSelect_validText;
	o.formatText        = FSelect_formatText;
	o.drop              = FSelect_drop;
	o.setEditStyle      = FSelect_setEditStyle;
	return o;
}
function FSelect_onEditDoubleClick(){
	this.onDropDoubleClick();
}
function FSelect_onEditKeyDown(e){
	this.onDropKeyDown(e);
}
function FSelect_onBuildEdit(){
	var o = this;
	o.base.FDataEditControl.onBuildEdit.call(o);
	var h = o.hEdit = RBuilder.appendEdit(o.hEditPanel, o.style('Edit'));
	if(o.editLength){
		h.maxLength = o.editLength;
	}
	if(o.hint){
		h.title = o.hint;
	}
	o.onBuildDrop();
}
function FSelect_onEditEnd(editor){
	var o = this;
	RLog.debug(o, 'Begin (editor={1}:{2} value={3})', editor, editor?editor.value():'', o.dataValue);
	if(editor){
		var item = o.items.get(editor.position());
		if(item){
			o.setValue(item.value);
		}
	}
	o.base.FDataEditControl.onEditEnd.call(o);
	RLog.debug(o, 'End (editor={1} value={2})', editor, o.dataValue);
}
function FSelect_loadConfig(c){
	var o = this;
	o.base.FDataEditControl.loadConfig.call(o, c);
	if(o.dataEmpty){
		o.items.create();
	}
	o.items.loadConfig(c);
	return EStatus.Stop;
}
function FSelect_text(){
	return this.hEdit.value;
}
function FSelect_setText(text){
	this.hEdit.value = text;
}
function FSelect_formatValue(text){
	return this.items.value(text);
}
function FSelect_validText(text){
	var o = this;
	if(RStr.isEmpty(text)){
		return true;
	}
	return !RStr.isEmpty(o.formatValue(text));
}
function FSelect_formatText(value){
	return this.items.label(value);
}
function FSelect_drop(){
	var o = this;
	if(o.canDrop() && o.canEdit){
		if(!o.editRefer){
			RMsg.fatal(o, null, 'Edit refer is null.');
		}
		var editor = RConsole.find(FEditConsole).focus(o, FSelectEditor, o.editRefer);
		editor.linkPanel(o.hEditPanel, o.hEdit);
		editor.setItems(o.items);
		editor.setPosition(o.items.indexOf(o.dataValue));
		editor.show();
	}
}
function FSelect_setEditStyle(t){
	var o = this;
	   var h = o.hEdit;
	   if(h){
	      var s = h.style;
	      if(EStyle.Valid == t && o.editCheck != 'Y'){
	            s.backgroundColor = EColor.Valid;
	      }else if(EStyle.Edit == t){
	         s.backgroundColor = EColor.Edit;
	         h.disabled = false;
	      }else if(EStyle.Readonly == t){
	         s.backgroundColor = EColor.Readonly;
	         h.disabled = true;
	      }
	   }
	   var h = o.hEditForm;
	   if(h){
	      var s = h.style;
	      if(EStyle.Valid == t && o.editCheck != 'Y'){
	         s.backgroundColor = EColor.Valid;
	      }else if(EStyle.Edit == t){
	         s.backgroundColor = EColor.Edit;
	         h.disabled = false;
	      }else if(EStyle.Readonly == t){
	         s.backgroundColor = EColor.Readonly;
	         h.disabled = true;
	      }
	   }
	var s = o.hDrop.style;
	if(EStyle.Edit == t){
		s.filter = null;
	}else if(EStyle.Readonly == t){
		s.filter = 'progid:DXImageTransform.Microsoft.BasicImage(grayscale=1)';
	}
}
function FSelectEditor(o){
	o = RClass.inherits(this, o, FDropEditor, MShadow);
	o.MinWidth      = 120;
	o.onItemEnter   = RClass.register(o, new HMouseEnter('onItemEnter'), FSelectEditor_onItemEnter);
	o.onItemLeave   = RClass.register(o, new HMouseLeave('onItemLeave'), FSelectEditor_onItemLeave);
	o.onItemSelect  = RClass.register(o, new HClick('onItemSelect'),     FSelectEditor_onItemSelect);
	o.onEditFocus   = RClass.register(o, new HFocus('onEditFocus'));
	o.onEditBlur    = RClass.register(o, new HBlur('onEditBlur'));
	o.styleFlag      = RClass.register(o, new TStyle('Flag'));
	o.styleRow       = RClass.register(o, new TStyle('Row'));
	o.styleRowHover  = RClass.register(o, new TStyle('RowHover'));
	o.styleRowSelect = RClass.register(o, new TStyle('RowSelect'));
	o.styleEditForm  = RClass.register(o, new TStyle('EditForm'));
	o.pattern       = null;
	o.originIndex   = null;
	o.selectIndex   = 0;
	o.items         = null;
	o.onEditKeyDown = FSelectEditor_onEditKeyDown;
	o.onBuildEdit   = FSelectEditor_onBuildEdit;
	o.panel         = FSelectEditor_panel;
	o.value         = FSelectEditor_value;
	o.setValue      = FSelectEditor_setValue;
	o.select        = FSelectEditor_select;
	o.setItems      = FSelectEditor_setItems;
	o.position      = FSelectEditor_position;
	o.setPosition   = FSelectEditor_setPosition;
	o.focus         = FSelectEditor_focus;
	o.show          = FSelectEditor_show;
	o.hide          = FSelectEditor_hide;
	return o;
}
function FSelectEditor_onItemEnter(e){
	var o = this;
	var h = e.hSource;
	if(!h.ptySelect){
		h.lnkLabel.className = o.style('RowHover');
	}
}
function FSelectEditor_onItemLeave(e){
	var o = this;
	var h = e.hSource;
	if(!h.ptySelect){
		h.lnkLabel.className = o.style('Row');
	}
}
function FSelectEditor_onItemSelect(e){
	var o = this;
	var h = e.hSource;
	RLog.debug(o, 'Select item (index={0})', h.ptyIndex);
	o.editStatus = EEditStatus.Ok;
	o.selectIndex = h.ptyIndex;
	o.inEdit = false;
	o.blur();
}
function FSelectEditor_onBuildEdit(){
	var o = this;
	var hFormTab = o.hForm = RBuilder.appendTable(o.hPanel);
	var hTltCel = hFormTab.insertRow().insertCell();
	var hTab = RBuilder.appendTable(hTltCel, o.style('EditForm'));
	var hRow = hTab.insertRow();
	var hCel = hRow.insertCell();
	o.hEditCel = hCel;
	o.hEdit = RBuilder.appendEdit(hCel, o.style('Edit'));
	var hCel = hRow.insertCell()
	o.hDrop = RBuilder.appendIcon(hCel, '#design.webform.selectdrop', o.style('Drop'));
	var hDrpCel = hFormTab.insertRow().insertCell();
	hDrpCel.width = 18;
	var h = o.hDropPanel = RBuilder.append(hDrpCel, 'DIV', o.style('DropPanel'));
	o.attachEvent('onDropMouseDown', h);
	o.attachEvent('onDropMouseUp', h);
	var h = o.hItemsPanel = RBuilder.appendTable(o.hDropPanel, null, 0, 1, 1);
	h.width = '100%';
	o.attachEvent('onEditKeyDown', o.hEdit);
	o.attachEvent('onEditKeyPress', o.hEdit);
	o.attachEvent('onEditKeyUp', o.hEdit);
	o.attachEvent('onEditChange', o.hEdit);
}
function FSelectEditor_onEditKeyDown(e){
	var o = this;
	var kc = e.keyCode;
	if(EKey.Up == kc){
		o.select(o.selectIndex-1);
	}else if(EKey.Down == kc){
		o.select(o.selectIndex+1);
	}else if(EKey.Esc == kc){
		o.editStatus = EEditStatus.Cancel;
		o.selectIndex = o.originIndex;
		RKey.eventClear(e);
		o.inEdit = false;
		o.endEdit();
	}else if(EKey.Enter == kc){
		o.editStatus = EEditStatus.Ok;
		RKey.eventClear(e);
		o.inEdit = false;
		o.endEdit();
	}
}
function FSelectEditor_panel(type){
	var o = this;
	if(EPanel.Shadow == type){
		return o.hDropPanel;
	}
	return o.base.FDropEditor.panel.call(o, type);
}
function FSelectEditor_value(){
	return this.dataValue;
}
function FSelectEditor_setValue(value){
	var o = this;
	o.changed = false;
	o.dataValue = value;
	o.dataOrigin = value;
	o.selectIndex = o.items.indexOf(value);
	o.select(o.selectIndex);
}
function FSelectEditor_select(index){
	var o = this;
	o.selectIndex = RInt.toRange(index, 0, o.items.count()-1);
	var rows = o.hItemsPanel.rows;
	for(var n=0; n<rows.length; n++){
		var row = rows[n];
		if(row.ptyIndex == o.selectIndex){
			row.lnkLabel.className = o.style('RowSelect');
			row.ptySelect = true;
			o.hEdit.value = o.items.getLabel(row.ptyIndex);
		}else{
			row.lnkLabel.className = o.style('Row');
			row.ptySelect = false;
		}
	}
}
function FSelectEditor_setItems(items){
	var o = this;
	if(o.hItemsPanel.rows.length){
		return;
	}
	o.items = items;
	var count = items.count();
	for(var n=0; n<count; n++){
		var item = items.get(n);
		var hRow = o.hItemsPanel.insertRow();
		o.attachEvent('onItemEnter', hRow);
		o.attachEvent('onItemLeave', hRow);
		o.attachEvent('onItemSelect', hRow);
		var hCel = hRow.insertCell();
		hCel.className = o.style('Flag');
		var hCel = hRow.insertCell();
		hCel.className = o.style('Row');
		hCel.innerText = RStr.nvl(item.label);
		hRow.lnkLabel = hCel;
		hRow.ptyIndex = n;
	}
}
function FSelectEditor_position(){
	return this.selectIndex;
}
function FSelectEditor_setPosition(p){
	var o = this;
	o.originIndex = p;
	o.selectIndex = p;
	o.select(p);
}
function FSelectEditor_focus(){
	var o = this;
	o.base.FDropEditor.focus.call(o);
	o.hEdit.focus();
}
function FSelectEditor_show(v){
	var o = this;
	o.base.FDropEditor.show.call(o, v);
	o.base.MShadow.show.call(o, v);
	o.isSkipBlur = false;
}
function FSelectEditor_hide(){
	var o = this;
	o.base.FDropEditor.hide.call(o);
	o.base.MShadow.hide.call(o);
}
function FSplit(o){
	o = RClass.inherits(this, o, FControl, MDisplayAble, MDesign, MHorizontal);
	o.styleTitle   = RClass.register(o, new TStyle('Title'));
	o.icon         = null;
	o.label        = null;
	o.extended     = true;
	o.iconMinus    = 'ctl.sp-minus';
	o.iconPlus     = 'ctl.sp-plus';
	o.hImage       = null;
	o.hIcon        = null;
	o.hText        = null;
	o.oeBuild      = FSplit_oeBuild;
	o.onBuildPanel = FSplit_onBuildPanel;
	o.onMouseDown  = FSplit_onMouseDown;
	o.loadConfig   = FSplit_loadConfig;
	o.saveConfig   = FSplit_saveConfig;
	o.extend       = FSplit_extend;
	return o;
}
function FSplit_oeBuild(event){
	var o = this;
	o.base.FControl.oeBuild.call(o, event);
	var h = this.hForm.insertRow().insertCell();
	h.className = this.style('Title');
	o.hImage = RBuilder.appendIcon(h, o.iconMinus);
	if(o.icon){
		o.hIcon = RBuilder.appendIcon(h, o.icon);
	}
	o.hText = RBuilder.appendText(h, '&nbsp;' + this.label);
	return EEventStatus.Stop;
}
function FSplit_onBuildPanel(){
	this.hPanel = RBuilder.create(null, 'DIV');
	this.hForm = RBuilder.appendTable(this.hPanel);
	this.hForm.width = '100%';
}
function FSplit_onMouseDown(){
	this.extend(!this.extended);
}
function FSplit_loadConfig(config){
	this.base.FControl.loadConfig.call(this, config);
	this.icon = config.get('icon');
	this.label = config.get('label');
}
function FSplit_saveConfig(config){
	this.base.FControl.saveConfig.call(this, config);
}
function FSplit_extend(v){
	var o = this;
	if(o.extended == v){
		return;
	}
	o.extended = v;
	o.hImage.src = v ? RRes.iconPath(o.iconMinus) : RRes.iconPath(o.iconPlus);
	var start = false;
	var cs = this.parent.controls;
	for(var n=0; n<cs.count; n++){
		var c = cs.value(n);
		if(c == this){
			start = true;
			continue;
		}
		if(start){
			if(RClass.isClass(c, MHorizontal)){
				break;
			}
			c.setVisible(this.extended);
		}
	}
}
function FTemplate(o){
	o = RClass.inherits(this, o, FControl, MDisplayAble, MDesign, MHorizontal);
	o.icon         = RClass.register(o, new TPtyStr('icon'));
	o.extended     = true;
	o.hImage       = null;
	o.hIcon        = null;
	o.hText        = null;
	o.oeBuild      = FTemplate_oeBuild;
	o.onBuildPanel = FTemplate_onBuildPanel;
	return o;
}
function FTemplate_oeBuild(event){
	var o = this;
	o.base.FControl.oeBuild.call(o, event);
	var h = o.hForm.insertRow().insertCell();
	h.className = o.style('Title');
	if(o.icon){
		o.hIcon = RBuilder.appendIcon(h, o.icon);
	}
	o.hText = RBuilder.appendText(h, '&nbsp;' + this.label);
	return EEventStatus.Stop;
}
function FTemplate_onBuildPanel(){
	var o = this;
	o.hPanel = RBuilder.create(null, 'DIV');
	o.hForm = RBuilder.appendTable(this.hPanel);
	o.hForm.width = '100%';
}
function FWebForm(o){
	o = RClass.inherits(this, o, FForm);
	return o;
}
function FWebStyle(o){
	o = RClass.inherits(this, o, FForm);
	return o;
}
function FWebTemplate(o){
	o = RClass.inherits(this, o, FForm);
	return o;
}
function ETableActionFace(){
	var o = this;
	o.Insert      = 'I';
	o.Update      = 'U';
	o.Delete      = 'D';
	o.GoInsert    = 'GI';
	o.GoUpdate    = 'GU';
	o.GoDelete    = 'GD';
	o.RowClick    = 'RC';
	o.RowDblClick = 'RD';
	return o;
}
var ETableAction= new ETableActionFace();
function FCell(o){
	o = RClass.inherits(this, o, FObject, MStyle);
   o.stylePanel  = RClass.register(o, new TStyle('Panel'));
   o.styleSelect = RClass.register(o, new TStyle('Select'));
	o.column     = null;
	o.row        = null;
	o.hCell      = null;
	o.build      = FCell_build;
	o.get        = FCell_get;
	o.set        = FCell_set;
   o.setVisible = FCell_setVisible;
   o.select     = FCell_select;
	o.dump       = FCell_dump;
	return o;
}
function FCell_build(){
	var o = this;
	var h = o.hCell = RBuilder.create(null, 'TD', o.style('Panel'));
	h.link = o;
	return h;
}
function FCell_get(){
	return this.hCell.innerText;
}
function FCell_set(v){
	this.hCell.innerText = v;
}
function FCell_setVisible(v){
   this.hCell.style.display = v ? 'block' : 'none';
}
function FCell_select(v){
   var o = this;
   o.hCell.className = v ? o.style('Select') : o.style('Panel');
}
function FCell_dump(s){
	var o = this;
	s = RStr.nvlStr(s);
	s.append(RClass.dump(o), '[');
	s.append(o.value);
	s.append(']');
	return s;
}
function FCellCheck(o){
	o = RClass.inherits(this, o, FCellEditable);
	o.hStatus           = null;
   o.ohDblClick        = FCellCheck_ohDblClick;
	o.build             = FCellCheck_build;
   o.get               = FCellCheck_get;
   o.set               = FCellCheck_set;
   o.select            = FCellCheck_select;
	return o;
}
function FCellCheck_ohDblClick(){
   var o = this.link;
   if(RClass.isClass(o, FCellCheck)){
      var t = o.column.table;
      t.insertRow(o.row.rowIndex());
   }
}
function FCellCheck_build(){
   var o = this;
   var c = o.column;
   var h = o.base.FCellEditable.build.call(o);
   h.align = 'center';
   h.ondblclick = o.ohDblClick;
   o.hEdit = RBuilder.appendCheck(h, o.style('Editor'));
	return h;
}
function FCellCheck_get(){
	return RBool.toString(this.hEdit.checked);
}
function FCellCheck_set(v){
	this.hEdit.checked = RBool.isTrue(v);
}
function FCellCheck_select(v){
   var o = this;
   var h = o.base.FCellEditable.select.call(o, v);
   o.hEdit.className = v ? o.style('EditorSelect') : o.style('Editor');
}
function FCellColor(o){
	o = RClass.inherits(this, o, FCellEditable);
	o.hStatus    = null;
   o.ohDblClick = FCellColor_ohDblClick;
	o.build      = FCellColor_build;
   o.get        = RMethod.empty;
   o.set        = RMethod.empty;
   o.select     = FCellColor_select;
	return o;
}
function FCellColor_ohDblClick(){
   var o = this.link;
   if(RClass.isClass(o, FCellColor)){
      var t = o.column.table;
      t.insertRow(o.row.rowIndex());
   }
}
function FCellColor_build(){
   var o = this;
   var c = o.column;
   var h = o.base.FCellEditable.build.call(o);
   h.align = 'center';
   h.ondblclick = o.ohDblClick;
   o.hEdit = RBuilder.appendCheck(h, o.style('Editor'));
	return h;
}
function FCellColor_select(v){
   var o = this;
   var h = o.base.FCellEditable.select.call(o, v);
   o.hEdit.className = v ? o.style('EditorSelect') : o.style('Editor');
}
function FCellDate(o){
	o = RClass.inherits(this, o, FCellDateable);
   o.hEdit  = null;
   o.build  = FCellDate_build;
	o.get    = FCellDate_get;
	o.set    = FCellDate_set;
   o.select = FCellDate_select;
	return o;
}
function FCellDate_build(){
   var o = this;
   var c = o.column;
   var h = o.base.FCellDateable.build.call(o);
   var he = o.hEdit = RBuilder.append(h, 'INPUT', o.style('Editor'));
	he.link = this;
	he.onfocus = c.ohEditFocus;
	he.onblur = c.ohEditBlur;
	he.onkeydown = c.ohEditKdown;
	he.onkeypress = c.ohEditKpress;
	he.onkeyup = c.ohEditKup;
	he.onchange = c.ohEditChanged;
   if(c.width){
      o.hEdit.style.width = c.width-2;
   }
   return h;
}
function FCellDate_get(){
   return this.hEdit.value;
}
function FCellDate_set(v){
	this.hEdit.value = v;
}
function FCellDate_select(v){
   var o = this;
   var h = o.base.FCellDateable.select.call(o, v);
   o.hEdit.className = v ? o.style('EditorSelect') : o.style('Editor');
}
function FCellEdit(o){
	o = RClass.inherits(this, o, FCellEditable);
   o.hEdit  = null;
   o.build  = FCellEdit_build;
	o.get    = FCellEdit_get;
	o.set    = FCellEdit_set;
   o.select = FCellEdit_select;
	return o;
}
function FCellEdit_build(){
   var o = this;
   var c = o.column;
   var h = o.base.FCellEditable.build.call(o);
   var he = o.hEdit = RBuilder.append(h, 'INPUT', o.style('Editor'));
	he.link = this;
	he.onfocus = c.ohEditFocus;
	he.onblur = c.ohEditBlur;
	he.onkeydown = c.ohEditKdown;
	he.onkeypress = c.ohEditKpress;
	he.onkeyup = c.ohEditKup;
	he.onchange = c.ohEditChanged;
   if(c.width){
      o.hEdit.style.width = c.width-2;
   }
   return h;
}
function FCellEdit_get(){
   return this.hEdit.value;
}
function FCellEdit_set(v){
	this.hEdit.value = v;
}
function FCellEdit_select(v){
   var o = this;
   var h = o.base.FCellEditable.select.call(o, v);
   o.hEdit.className = v ? o.style('EditorSelect') : o.style('Editor');
}
function FCellEditable(o){
	o = RClass.inherits(this, o, FCell);
   o.styleEdit         = RClass.register(o, new TStyle('Edit'));
   o.styleEditor       = RClass.register(o, new TStyle('Editor'));
   o.styleEditorSelect = RClass.register(o, new TStyle('EditorSelect'));
	o.build     = FCellEditable_build;
	o.select    = FCellEditable_select;
	return o;
}
function FCellEditable_build(){
   var o = this;
   var h = o.base.FCell.build.call(o);
   h.className = o.column.isEditable(o.row) ? o.style('Edit') : o.style('Panel');
   return h;
}
function FCellEditable_select(v){
   var o = this;
   if(v){
      o.hCell.className = o.style('Select');
   }else{
      o.hCell.className = o.column.isEditable(o.row) ? o.style('Edit') : o.style('Panel');
   }
}
function FCellIcon(o){
	o = RClass.inherits(this, o, FCell);
   o.hEdit  = null;
   o.build  = FCellIcon_build;
	o.get    = FCellIcon_get;
	o.set    = FCellIcon_set;
   o.select = FCellIcon_select;
	return o;
}
function FCellIcon_build(){
   var o = this;
   var c = o.column;
   var h = o.base.FCellIconable.build.call(o);
   var he = o.hEdit = RBuilder.append(h, 'INPUT', o.style('Editor'));
	he.link = this;
	he.onfocus = c.ohEditFocus;
	he.onblur = c.ohEditBlur;
	he.onkeydown = c.ohEditKdown;
	he.onkeypress = c.ohEditKpress;
	he.onkeyup = c.ohEditKup;
	he.onchange = c.ohEditChanged;
   if(c.width){
      o.hEdit.style.width = c.width-2;
   }
   return h;
}
function FCellIcon_get(){
   return this.hEdit.value;
}
function FCellIcon_set(v){
	this.hEdit.value = v;
}
function FCellIcon_select(v){
   var o = this;
   var h = o.base.FCellIconable.select.call(o, v);
   o.hEdit.className = v ? o.style('EditorSelect') : o.style('Editor');
}
function FCellNumber(o){
	o = RClass.inherits(this, o, FCellEditable);
   o.hEdit  = null;
   o.build  = FCellNumber_build;
	o.get    = FCellNumber_get;
	o.set    = FCellNumber_set;
   o.select = FCellNumber_select;
	return o;
}
function FCellNumber_build(){
   var o = this;
   var c = o.column;
   var h = o.base.FCellEditable.build.call(o);
   var he = o.hEdit = RBuilder.append(h, 'INPUT', o.style('Editor'));
	he.link = this;
	he.onfocus = c.ohEditFocus;
	he.onblur = c.ohEditBlur;
	he.onkeydown = c.ohEditKdown;
	he.onkeypress = c.ohEditKpress;
	he.onkeyup = c.ohEditKup;
	he.onchange = c.ohEditChanged;
   if(c.width){
      o.hEdit.style.width = c.width-2;
   }
   return h;
}
function FCellNumber_get(){
   return this.hEdit.value;
}
function FCellNumber_set(v){
	this.hEdit.value = v;
}
function FCellNumber_select(v){
   var o = this;
   var h = o.base.FCellEditable.select.call(o, v);
   o.hEdit.className = v ? o.style('EditorSelect') : o.style('Editor');
}
function FCellPicture(o){
	o = RClass.inherits(this, o, FCellEditable);
   o.hEdit  = null;
   o.build  = FCellPicture_build;
	o.get    = FCellPicture_get;
	o.set    = FCellPicture_set;
   o.select = FCellPicture_select;
	return o;
}
function FCellPicture_build(){
   var o = this;
   var c = o.column;
   var h = o.base.FCellEditable.build.call(o);
   var he = o.hEdit = this.hEdit = RBuilder.append(h, 'IMG', o.style('Editor'));
	he.link = this;
	he.onfocus = c.ohEditFocus;
	he.onblur = c.ohEditBlur;
	he.onkeydown = c.ohEditKdown;
	he.onkeypress = c.ohEditKpress;
	he.onkeyup = c.ohEditKup;
	he.onchange = c.ohEditChanged;
   if(c.width){
      o.hEdit.style.width = c.width-2;
   }
   return h;
}
function FCellPicture_get(){
   return this.hEdit.value;
}
function FCellPicture_set(v){
	this.hEdit.src=v;
}
function FCellPicture_select(v){
   var o = this;
   var h = o.base.FCellEditable.select.call(o, v);
   o.hEdit.className = v ? o.style('EditorSelect') : o.style('Editor');
}
function FCellProgress(o){
	o = RClass.inherits(this, o, FCellEditable);
	o.hStatus    = null;
   o.ohDblClick = FCellProgress_ohDblClick;
	o.build      = FCellProgress_build;
   o.get        = RMethod.empty;
   o.set        = RMethod.empty;
   o.select     = FCellProgress_select;
	return o;
}
function FCellProgress_ohDblClick(){
   var o = this.link;
   if(RClass.isClass(o, FCellProgress)){
      var t = o.column.table;
      t.insertRow(o.row.rowIndex());
   }
}
function FCellProgress_build(){
   var o = this;
   var c = o.column;
   var h = o.base.FCellEditable.build.call(o);
   h.align = 'center';
   h.ondblclick = o.ohDblClick;
   o.hEdit = RBuilder.appendCheck(h, o.style('Editor'));
	return h;
}
function FCellProgress_select(v){
   var o = this;
   var h = o.base.FCellEditable.select.call(o, v);
   o.hEdit.className = v ? o.style('EditorSelect') : o.style('Editor');
}
function FCellSelect(o){
	o = RClass.inherits(this, o, FCellEditable);
   o.hForm      = null;
   o.hEdit      = null;
   o.hDrop      = null;
   o.ohDblClick = FCellSelect_ohDblClick;
	o.build      = FCellSelect_build;
   o.getText    = RMethod.empty;
   o.setText    = RMethod.empty;
   o.select     = FCellSelect_select;
	return o;
}
function FCellSelect_ohDblClick(){
   var o = this.link;
   if(RClass.isClass(o, FCellSelect)){
      var t = o.column.table;
      t.insertRow(o.row.rowIndex());
   }
}
function FCellSelect_build(){
   var o = this;
   var c = o.column;
   var h = o.base.FCellEditable.build.call(o);
   o.hForm = RBuilder.appendTable(h);
   var hRow = o.hForm.insertRow();
   var hCel = hRow.insertCell();
   o.hEdit = RBuilder.append(hCel, 'INPUT', o.style('Editor'));
   if(c.width){
      o.hEdit.style.width = c.width-18;
   }
   o.hDrop = RBuilder.appendIcon(hCel, 'ctl.select', o.style('Drop'));
	return h;
}
function FCellSelect_select(v){
   var o = this;
   var h = o.base.FCellEditable.select.call(o, v);
   o.hEdit.className = v ? o.style('EditorSelect') : o.style('Editor');
}
function FCellStatus(o){
	o = RClass.inherits(this, o, FCell);
	o.hStatus    = null;
	o.ohClick    = FCellStatus_ohClick;
	o.ohDblClick = FCellStatus_ohDblClick;
	o.build      = FCellStatus_build;
	o.get        = RMethod.empty;
	o.set        = RMethod.empty;
	o.setStatus  = FCellStatus_setStatus;
	return o;
}
function FCellStatus_ohClick(){
	var o = this.link;
	if(RClass.isClass(o, FCellStatus)){
		RConsole.find(FListenerConsole).process(FTable, ETableAction.RowClick, o.row, o.row)
	}
}
function FCellStatus_ohDblClick(){
	var o = this.link;
	if(RClass.isClass(o, FCellStatus)){
		o.column.lsnsRowDblClick.process(o.row);
	}
}
function FCellStatus_build(){
	var o = this;
	var c = o.column;
	var h = o.base.FCell.build.call(o);
	h.onclick = o.ohClick;
	h.ondblclick = o.ohDblClick;
	o.hStatus = RBuilder.appendIcon(h, c.iconNormal);
	return h;
}
function FCellStatus_setStatus(s){
	var o = this;
	var c = o.column;
	if(EAction.Update == s){
		o.hStatus.src = RRes.iconPath(c.iconUpdate);
	}else{
		o.hStatus.src = RRes.iconPath(c.iconNormal);
	}
}
function FColumn(o){
	o = RClass.inherits(this, o, FControl);
	o.icon           = RClass.register(o, new TPtyStr('icon', false));
	o.align          = RClass.register(o, new TPtyStr('align', EAlign.Left));
	o.valign         = RClass.register(o, new TPtyStr('valign', EAlign.Middle));
	o.dataName       = RClass.register(o, new TPtyStr('dataName', null));
	o.dataDefault    = RClass.register(o, new TPtyStr('dataDefault', null));
	o.isDisplay      = true;
	o.index          = null;
	o.table          = null;
   o.cellClass      = FCell;
	o.hLabel         = null;
	o.styleAlign     = null;
	o.onClick        = null;
	o.onDbClick      = null;
	o.onFocus        = null;
	o.onBlur         = null;
	o.onBuild        = null;
	o.buildHead      = FColumn_buildHead;
	o.createCell     = FColumn_createCell;
	o.showHead       = FColumn_showHead;
	o.setStyleStatus = FColumn_setStyleStatus;
	o.cell           = FColumn_cell;
	o.text           = FColumn_text;
	o.setText        = FColumn_setText;
	o.dump           = FColumn_dump;
	return o;
}
function FColumn_buildHead(){
	var o = this;
	var t = o.table;
	var h = o.hPanel = RBuilder.create(null, 'TD', t.style('Head'));
	h.link = this;
	if(o.width){
		h.width = o.width;
	}
	var label = o.label;
	if(o.icon){
		o.hIcon = RBuilder.appendIcon(h, o.icon);
		label = '&nbsp;' + label;
	}
	if(o.label){
		o.hLabel = RBuilder.appendText(h, label, t.style('HeadLabel'));
	}
	RLog.debug(o, o.dump());
	return h;
}
function FColumn_createCell(row){
   var o = this;
	var cell = RClass.create(o.cellClass);
	cell.column = o;
	cell.row = row;
	cell.build();
	if(!RClass.isClass(o, MDisplayAble) && !o.dispList){
      cell.setVisible(false);
	}
	return cell;
}
function FColumn_showHead(){
	var o = this;
	if(!RClass.isClass(o, MDisplayAble) && !o.dispList){
		o.isDisplay = false;
		o.setVisible(false);
	}
}
function FColumn_setStyleStatus(row, status){
	var o = this;
	var h = o.cell(row);
	if(h){
		var s = h.style;
		switch(status){
			case EStyle.Normal:
				if(row.isDelete()){
					s.backgroundColor = EColor.Delete;
				}else{
					s.backgroundColor = EColor.Readonly;
				}
				break;
			case EStyle.Select:
				if(row.isDelete()){
					s.backgroundColor = EColor.Select;
				}else{
					s.textDecoration = 'none';
					s.backgroundColor = EColor.Select;
				}
				break;
			case EStyle.Delete:
				s.textDecoration = 'line-through';
				s.backgroundColor = EColor.Select;
				break;
		}
	}
}
function FColumn_cell(row){
	return row.cell(this.index);
}
function FColumn_text(row){
	return this.cellText(this.cell(row));
}
function FColumn_setText(row, text){
	this.setCellText(this.cell(row), text);
}
function FColumn_cellText(cell){
	if(cell){
		return cell.innerText;
	}
}
function FColumn_setCellText(cell, text){
	if(cell){
		cell.innerText = text;
	}
}
function FColumn_dump(s){
	var o = this;
	s = RStr.nvlStr(s);
	s.append(RClass.dump(o), '[');
	s.append('name=', o.name);
	s.appendIf(o.icon, ',icon=', o.icon);
	s.appendIf(o.label, ',label=', o.label);
	s.appendIf(o.align, ',align=', o.align);
	s.appendIf(o.valign, ',valign=', o.valign);
	s.appendIf(o.dataName, ',dataName=', o.dataName);
	s.appendIf(o.dataDefault, ',dataDefault=', o.dataDefault);
	s.appendIf(o.index, ',index=', o.index);
	s.append(']');
	return s;
}
function FColumnCheck(o){
	o = RClass.inherits(this, o, FColumnEditable);
	o.iconCheck   = 'ctl.radios'
	o.iconUncheck = 'ctl.radiou';
   o.cellClass   = FCellCheck;
	o.equalsValue = FColumnCheck_equalsValue;
	return o;
}
function FColumnCheck_equalsValue(rv, cv){
	return RBool.isTrue(rv) == RBool.isTrue(cv);
}
function FColumnColor(o){
	o = RClass.inherits(this, o, FColumn);
   o.cellClass = FCellIcon;
	return o;
}
function FColumnDate(o){
	o = RClass.inherits(this, o, FColumnDateable);
	o.buildCell   = FColumnDate_buildCell;
	o.cellText    = FColumnDate_cellText;
	o.setCellText = FColumnDate_setCellText;
	return o;
}
function FColumnDate_buildCell(row){
	var o = this;
	var h = o.base.FColumnDateable.buildCell.call(o, row);
	RBuilder.append(h, 'SPAN');
	return h;
}
function FColumnDate_cellText(cell){
	return cell ? cell.children(0).innerText : '';
}
function FColumnDate_setCellText(cell, text){
	if(cell){
		cell.children(0).innerText = text;
	}
}
function FColumnEdit(o){
	o = RClass.inherits(this, o, FColumnEditable);
   o.cellClass = FCellEdit;
	return o;
}
function FColumnEditable(o){
	o = RClass.inherits(this, o, FColumn);
	o.editInsert     = RClass.register(o, new TPtyBoolSet('editInsert', 'editAccess', EAction.Insert, false));
	o.editUpdate     = RClass.register(o, new TPtyBoolSet('editUpdate', 'editAccess', EAction.Update, false));
	o.validInsert    = RClass.register(o, new TPtyBoolSet('validInsert', 'validAccess', EAction.Insert, false));
	o.validUpdate    = RClass.register(o, new TPtyBoolSet('validUpdate', 'validAccess', EAction.Update, false));
	o.styleAlign     = EAlign.Left;
	o.editor         = null;
	o.editInsert     = true;
	o.editUpdate     = true;
	o.ohEditFocus    = FColumnEditable_ohEditFocus;
	o.ohEditBlur     = FColumnEditable_ohEditBlur;
	o.ohEditKdown    = FColumnEditable_ohEditKdown;
	o.ohEditKpress   = FColumnEditable_ohEditKpress;
	o.ohEditKup      = FColumnEditable_ohEditKup;
	o.ohEditChanged  = FColumnEditable_ohEditChanged;
	o.onEditFocus    = RMethod.empty;
	o.onEditBlur     = RMethod.empty;
	o.onEditKeyDown  = RMethod.empty;
	o.onEditKeyPress = RMethod.empty;
	o.onEditKeyUp    = RMethod.empty;
	o.onEditChanged  = FColumnEditable_onEditChanged;
	o.onEditBegin    = FColumnEditable_onEditBegin;
	o.onEditEnd      = FColumnEditable_onEditEnd;
	o.editCell       = FColumnEditable_editCell;
	o.equalsValue    = FColumnEditable_equalsValue;
	o.formatValue    = FColumnEditable_formatValue;
	o.formatText     = FColumnEditable_formatText;
	o.value          = FColumnEditable_value;
	o.setValue       = FColumnEditable_setValue;
	o.cellText       = FColumnEditable_cellText;
	o.setCellText    = FColumnEditable_setCellText;
	o.isEditable     = FColumnEditable_isEditable;
	o.setStyleStatus = FColumnEditable_setStyleStatus;
	o.dump           = FColumnEditable_dump;
	o.moveFocus      = FColumnEditable_moveFocus;
	return o;
}
function FColumnEditable_ohEditFocus(){
	var o = this.link;
	if(RClass.isClass(o, FCellEdit)){
		o.column.onEditFocus(o);
	}
}
function FColumnEditable_ohEditBlur(){
	var o = this.link;
	if(RClass.isClass(o, FCellEdit)){
		o.column.onEditBlur(o);
	}
}
function FColumnEditable_ohEditKdown(){
	var o = this.link;
	if(RClass.isClass(o, FCellEdit)){
		o.column.onEditKeyDown(o, RWindow.event(this));
	}
}
function FColumnEditable_ohEditKpress(){
	var o = this.link;
	if(RClass.isClass(o, FCellEdit)){
		o.column.onEditKeyPress(o, RWindow.event(this));
	}
}
function FColumnEditable_ohEditKup(){
	var o = this.link;
	if(RClass.isClass(o, FCellEdit)){
		o.column.onEditKeyUp(o, RWindow.event(this));
	}
}
function FColumnEditable_ohEditChanged(){
	var o = this.link;
	if(RClass.isClass(o, FCellEdit)){
		o.column.onEditChanged(o);
	}
}
function FColumnEditable_onEditBegin(editor){
	var o = this;
	var row = editor.row;
	o.editor = editor;
	o.table.editRow = row;
	o.table.editColumn = o;
	o.table.select(row, true);
	RLog.debug(o, 'Edit begin (column={1} row={2} editor={3})', o.name, RClass.dump(row), RClass.dump(editor));
}
function FColumnEditable_onEditChanged(cell){
	cell.row.refresh();
}
function FColumnEditable_onEditEnd(editor){
	var o = this;
	var row = editor.row;
	var text = editor.text();
	o.setValue(row, o.formatValue(text));
	o.setText(row, text);
	o.table.setDataStatus(row, row.isChanged() ? EDataStatus.Update : EDataStatus.Unknown)
	o.editor = null;
	RLog.debug(o, '{1}={2}\n{3}\n{4}', RClass.dump(editor), o.formatValue(text), o.dump(), row.dump());
}
function FColumnEditable_editCell(row){
	var o = this;
	RLog.debug(o, 'Edit cell {1}', row.dump());
	var editor = o.editConsole.focus(o, o.editorClass);
	editor.linkPanel(o.table, o, row);
}
function FColumnEditable_equalsValue(rv, cv){
	return RStr.nvl(rv) == RStr.nvl(cv);
}
function FColumnEditable_formatValue(text){
	return text;
}
function FColumnEditable_formatText(value){
	return value;
}
function FColumnEditable_value(row){
	return row.get(this.dataName);
}
function FColumnEditable_setValue(row, value){
	row.set(this.dataName, value);
}
function FColumnEditable_cellText(cell){
	if(cell){
		return cell.innerText;
	}
}
function FColumnEditable_setCellText(cell, text){
	if(cell){
	}
}
function FColumnEditable_isEditable(row){
	return (EDataStatus.Insert == row.dataStatus) ? this.editInsert : this.editUpdate;
}
function FColumnEditable_setStyleStatus(row, status){
	var o = this;
	var h = o.cell(row);
	if(h){
		var s = h.style;
		switch(status){
			case EStyle.Normal:
				if(row.isDelete()){
					s.backgroundColor = EColor.Delete;
				}else{
					if(o.isEditable(row)){
						s.backgroundColor = EColor.Edit;
					}else{
						s.backgroundColor = EColor.Readonly;
					}
				}
				break;
			case EStyle.Select:
				if(row.isDelete()){
					s.backgroundColor = EColor.Select;
				}else{
					s.textDecoration = 'none';
					if(o.isEditable(row)){
						s.backgroundColor = EColor.RowEditSelect;
					}else{
						s.backgroundColor = EColor.Select;
					}
				}
				break;
			case EStyle.Delete:
				s.textDecoration = 'line-through';
				s.backgroundColor = EColor.Select;
				break;
		}
	}
}
function FColumnEditable_dump(s){
	var o = this;
	s = o.base.FColumn.dump.call(o, s)
	s.append(' [editAccess=');
	s.append(o.editInsert?'I':'_');
	s.append(o.editUpdate?'U':'_');
	s.append(']');
	return s;
}
function FColumnEditable_moveFocus(row, pos){
	var o = this;
	var t = o.table;
	var mt = null;
	var mr = null;
	var mc = null;
	if(EPosition.Top == pos){
		mt = o;
		mr = t.rows.get(t.rows.indexOf(row)-1);
		if(mr){
			mc = o.cell(mr);
		}
	}else if(EPosition.Bottom == pos){
		mt = o;
		mr = t.rows.get(t.rows.indexOf(row)+1);
		if(mr){
			mc = o.cell(mr);
		}
	}else if(EPosition.Before == pos){
		var fi = o.index-1;
		var ri = t.rows.indexOf(row);
		for(var n=ri; n>=0; n--){
			var fr = t.rows.get(n);
			for(var i=fi; i>=0; i--){
				var ft = t.columns.value(i);
				if(RClass.isClass(ft, FColumnEditable) && ft.isEditable(fr)){
					mt = ft;
					mr = fr;
					mc = mt.cell(fr);
					break;
				}
			}
			if(mt){
				break;
			}
			fi = t.columns.count-1;
		}
	}else if(EPosition.After == pos){
		var fi = o.index+1;
		var ri = t.rows.indexOf(row);
		for(var n=ri; n<t.rows.count; n++){
			var fr = t.rows.get(n);
			for(var i=fi; i<t.columns.count; i++){
				var ft = t.columns.value(i);
				if(RClass.isClass(ft, FColumnEditable) && ft.isEditable(fr)){
					mt = ft;
					mr = fr;
					mc = mt.cell(fr);
					break;
				}
			}
			if(mt){
				break;
			}
			fi = 0;
		}
	}
	if(mt && mr && mc){
		mt.editCell(mr);
	}
}
function FColumnEditor(o){
	o = RClass.inherits(this, o, FHoverEditor, MEventKey);
	o.pattern      = null;
	o.table        = null;
	o.column       = null;
	o.row          = null;
	o.storeNodes   = new Object();
	o.hStore       = null;
	o.onKeyDown    = FColumnEditor_onKeyDown;
	o.onKeyUp      = FColumnEditor_onKeyUp;
	o.onBuildEdit  = FColumnEditor_onBuildEdit;
	o.text         = FColumnEditor_text;
	o.setText      = FColumnEditor_setText;
	o.focus        = RMethod.empty;
	o.value        = FColumnEditor_value;
	o.setValue     = FColumnEditor_setValue;
	o.linkPanel    = FColumnEditor_linkPanel;
	return o;
}
function FColumnEditor_onKeyDown(e){
	var o = this;
	var k = e.keyCode;
	if(EKey.Up == k){
		o.column.moveFocus(o.row, EPosition.Top);
		e.returnValue = false;
	}else if(EKey.Down == k){
		o.column.moveFocus(o.row, EPosition.Bottom);
		e.returnValue = false;
	}else if(e.shiftKey && EKey.Tab == k){
		o.column.moveFocus(o.row, EPosition.Before);
		e.returnValue = false;
	}else if(EKey.Tab == k){
		o.column.moveFocus(o.row, EPosition.After);
		e.returnValue = false;
	}
}
function FColumnEditor_onKeyUp(){
	var o = this.link;
	if(RClass.isClass(o, FColumnEditor)){
		o.onEditChanged();
	}
}
function FColumnEditor_onBuildEdit(event){
	var o = this;
	o.hForm = RBuilder.newTable();
	var hRow = o.hForm.insertRow();
	var hCel = hRow.insertCell();
	o.hEdit = RBuilder.append(hCel, 'INPUT', o.style('Edit'));
	o.linkEvent(o.hEdit);
	return EEventStatus.Stop;
}
function FColumnEditor_text(){
	return this.hEdit.value;
}
function FColumnEditor_setText(text){
	this.changed = false;
	this.storedText = text;
	this.hEdit.value = text;
}
function FColumnEditor_value(){
}
function FColumnEditor_setValue(){
}
function FColumnEditor_linkPanel(t, c, r){
	var o = this;
	RLog.debug(o, 'Link panel (table={1},editable={2})\ncolumn={3}\nrow={4}', RClass.dump(t), RClass.dump(o.editable), c.dump(), r.dump())
	var hp = t.hRowsPanel;
	var he = c.cell(r);
	o.table = t;
	o.column = c;
	o.row = r;
	RHtml.toRect(o.rect, he, hp);
	RHtml.setRect(o.hPanel, o.rect);
	var s = o.hEdit.style;
	s.pixelWidth = he.clientWidth;
	s.pixelHeight = he.offsetHeight-2;
	s.paddingLeft = parseInt(he.currentStyle.paddingLeft);
	s.paddingTop = parseInt(he.currentStyle.paddingTop)-1;
	if(r.isSelect){
		s.backgroundColor = EColor.RowEditSelect;
	}else{
		s.backgroundColor = EColor.Edit;
	}
	o.setText(r.get(c.dataName));
	o.base.FHoverEditor.linkPanel.call(o, hp, he);
}
function FColumnEmpty(o){
	o = RClass.inherits(this, o, FColumn, MDisplayAble);
	o.buildHead = FColumnEmpty_buildHead;
	return o;
}
function FColumnEmpty_buildHead(){
	var h = this.base.FColumn.buildHead.call(this);
	h.innerHTML = '&nbsp;'
}
function FColumnIcon(o){
	o = RClass.inherits(this, o, FColumn);
   o.cellClass = FCellIcon;
	return o;
}
function FColumnNumber(o){
	o = RClass.inherits(this, o, FColumnEditable);
   o.cellClass = FCellNumber;
	return o;
}
function FColumnPicture(o){
	o = RClass.inherits(this, o, FColumnEditable);
   o.cellClass = FCellPicture;
	return o;
}
function FColumnProgress(o){
	o = RClass.inherits(this, o, FColumn);
   o.cellClass = FCellIcon;
	return o;
}
function FColumnSelect(o){
	o = RClass.inherits(this, o, FColumnEditable);
	o.construct     = FColumnSelect_construct;
	return o;
}
function FColumnSelect_construct(){
	var o = this;
	o.base.FColumnEditable.construct.call(o);
   o.cellClass = FCellSelect;
}
function FColumnStatus(o){
	o = RClass.inherits(this, o, FColumn, MDisplayAble);
   o.styleHead   = RClass.register(o, new TStyle('Head'));
	o.styleAlign      = 'left';
	o.hSelect         = null;
	o.iconNormal      = 'ctl.tv-node';
	o.iconInsert      = 'tool.insert';
	o.iconUpdate      = 'tool.update';
	o.iconDelete      = 'tool.delete';
   o.cellClass       = FCellStatus;
   o.lsnsHeadClick   = new TListeners();
   o.lsnsRowDblClick = new TListeners();
	o.ohHeadClick     = FColumnStatus_ohHeadClick;
	o.ohCellMdclk     = FColumnStatus_ohCellMdclk;
	o.buildHead       = FColumnStatus_buildHead;
	o.createCell      = FColumnStatus_createCell;
	o.setDataStatus   = FColumnStatus_setDataStatus;
	return o;
}
function FColumnStatus_ohHeadClick(){
	var o = this.link;
	if(RClass.isClass(o, FColumnStatus)){
		RConsole.find(FListenerConsole).process(FTable, ETableAction.GoInsert, o, o.table)
	}
}
function FColumnStatus_ohCellMdclk(){
	if(this.lnkRow && this.lnkCol){
		var tab = this.lnkCol.table;
		tab.insertRow(this.lnkRow.rowIndex());
	}
}
function FColumnStatus_buildHead(){
	var o = this;
	var h = o.base.FColumn.buildHead.call(o);
	h.className = o.style('Head');
	h.align = 'center';
	o.hSelect = RBuilder.appendIcon(o.hPanel, 'sys.insert');
	o.hSelect.link = o;
	o.hSelect.style.cursor = 'hand';
	o.hSelect.onclick = o.ohHeadClick;
}
function FColumnStatus_createCell(row){
	var o = this;
	var c = o.base.FColumn.createCell.call(o, row);
	row.cellStatus = c;
	return c;
}
function FColumnStatus_setDataStatus(r, st){
	var o = this;
	var h = o.cell(r);
	if(h){
		var hs = r.hStatus;
		switch(st){
			case EDataStatus.Update:
				if(r.isExist()){
					if(r.isChanged()){
						hs.src = RRes.iconPath(o.iconUpdate);
					}else{
						hs.src = RRes.iconPath(o.iconNormal);
					}
				}
				break;
			case EDataStatus.Delete:
				if(r.isExist()){
					hs.src = RRes.iconPath(o.iconDelete);
				}
				break;
			default:
				if(r.isExist()){
					if(r.isChanged()){
						hs.src = RRes.iconPath(o.iconUpdate);
					}else{
						hs.src = RRes.iconPath(o.iconNormal);
					}
				}
				break;
		}
	}
}
function FRow(o){
	o = RClass.inherits(this, o, FObject);
   o.stylePanel = RClass.register(o, new TStyle('Panel'));
	o.table      = null;
	o.isSelect   = false;
	o.dataStatus = EDataStatus.Unknown;
	o.row        = null;
	o.cellStatus = null;
	o.cells      = new TList();
	o.hStatus    = null;
	o.hRow       = null;
	o.hStatus    = null;
   o.ohMdown    = FRow_ohMdown;
   o.ohDblClick = FRow_ohDblClick;
	o.build      = FRow_build;
	o.isExist    = FRow_isExist;
	o.isInsert   = FRow_isInsert;
	o.isChanged  = FRow_isChanged;
	o.isDelete   = FRow_isDelete;
	o.isSave     = FRow_isSave;
   o.ouid       = FRow_ouid;
   o.over       = FRow_over;
	o.get        = FRow_get;
	o.set        = FRow_set;
	o.cell       = FRow_cell;
	o.rowIndex   = FRow_rowIndex;
	o.doNormal   = FRow_doNormal;
	o.doStore    = FRow_doStore;
	o.doInsert   = FRow_doInsert;
	o.doDelete   = FRow_doDelete;
	o.loadValue  = FRow_loadValue;
	o.saveValue  = FRow_saveValue;
	o.push       = FRow_push;
   o.select     = FRow_select;
	o.refresh    = FRow_refresh;
	o.release    = FRow_release;
	o.dump       = FRow_dump;
	return o;
}
function FRow_ohMdown() {
   var row = this.link;
   if (RClass.isClass(row, FRow)) {
      row.table.selectRow(row, !event.ctrlKey, true);
      row.table.lsnsRowMdown.process(row);
   }
}
function FRow_ohDblClick() {
   var o = this.link;
   if (RClass.isClass(o, FRow)) {
		o.table.lsnsRowDblClick.process(o);
		RConsole.find(FListenerConsole).process(FTable, ETableAction.RowDblClick, o, o)
   }
}
function FRow_build(){
	var o = this;
	var h = o.hRow = RBuilder.create(null, 'TR', RClass.find(o, true).style('Panel'));
	h.link = o;
   h.hideFocus = false;
   h.onmousedown = o.ohMdown;
   h.ondblclick = o.ohDblClick;
	return h;
}
function FRow_isExist(){
	return this.row.isExist;
}
function FRow_isInsert(){
	return this.row.isInsert;
}
function FRow_isChanged(){
	var o = this;
	var cs = o.cells;
	for(var n=0; n<cs.count; n++){
		var c = cs.get(n);
		if(RClass.isClass(c, FCellEditable)){
			if(!c.column.equalsValue(o.get(c.column.dataName), c.get())){
				return true;
			}
		}
	}
	return false;
}
function FRow_isDelete(){
	return this.row.isDelete;
}
function FRow_isSave(){
	return this.row.isSave();
}
function FRow_ouid(){
   return this.row.nvl('ouid');
}
function FRow_over(){
   return this.row.nvl('over');
}
function FRow_get(n){
	return this.row.nvl(n);
}
function FRow_set(n, v){
	this.row.set(n, v);
}
function FRow_cell(n){
	return this.hRow.cells[n];
}
function FRow_rowIndex(){
	return this.hRow.rowIndex;
}
function FRow_doNormal(){
	var o = this;
	o.row.doNormal();
	o.dataStatus = EDataStatus.Unknown;
}
function FRow_doStore(){
	var o = this;
	o.row.store();
	o.dataStatus = EDataStatus.Unknown;
}
function FRow_doInsert(){
	var o = this;
	o.row.doInsert();
	o.dataStatus = EDataStatus.Insert;
}
function FRow_doDelete(){
	var o = this;
	o.row.doDelete();
	o.dataStatus = EDataStatus.Delete;
}
function FRow_loadValue(v){
	var o = this;
	var cs = o.table.columns;
	if(cs){
		for(var n=0; n<cs.count; n++){
			var c = cs.value(n);
			o.row.set(c.dataName, RStr.nvl(v.get(c.dataName)));
		}
	}
}
function FRow_saveValue(v){
	var o = this;
	v.set('_flag', o.dataStatus);
	var cs = o.table.columns;
	if(cs){
		for(var n=0; n<cs.count; n++){
			var c = cs.value(n);
			v.set(c.dataName, o.row.get(c.dataName));
		}
	}
}
function FRow_push(cell){
   var o = this;
	o.hRow.appendChild(cell.hCell)
	cell.row = o;
	o.cells.push(cell);
}
function FRow_select(v){
   var o = this;
   o.isSelect = v;
   o.hRow.style.backgroundColor = v ? EColor.RowSelect : EColor.Row;
   for(var n=0; n<this.cells.count; n++){
      var cell = this.cells.get(n);
      cell.select(v);
   }
}
function FRow_refresh(){
	var o = this;
	o.cellStatus.setStatus(o.isChanged() ? EAction.Update : EAction.Normal);
}
function FRow_release(){
	var o = this;
	var t = o.table;
	t.dsRemoveRow(o.row);
	t.hRows.deleteRow(o.rowIndex());
	t.rows.removeItem(o);
}
function FRow_setStatus(v){
   var o = this;
   o.isSelect = v;
   o.hRow.style.backgroundColor = v ? EColor.RowSelect : EColor.Row;
   for(var n=0; n<this.cells.count; n++){
      var cell = this.cells.get(n);
      cell.select(v);
   }
}
function FRow_dump(s){
	var o = this;
	s = RStr.nvlStr(s);
	s.append(RClass.dump(o), '[');
	s.append(o.isSelect?'S':'_');
	s.append(']');
	o.row.dump(s);
	return s;
}
function FTable(o) {
   o = RClass.inherits(this, o, FContainer, MForm, MDataset, MValue, MHorizontal, MLsnLoaded, MLsnSelect, MLsnClick, MLsnKey);
   o.formName       = RClass.register(o, new TPtyStr('formName', null));
   o.styleHeads     = RClass.register(o, new TStyle('Heads'));
   o.styleHead      = RClass.register(o, new TStyle('Head'));
   o.styleHeadLabel = RClass.register(o, new TStyle('HeadLabel'));
   o.styleRowsPanel = RClass.register(o, new TStyle('RowsPanel'));
   o.styleNavigator = RClass.register(o, new TStyle('Navigator'));
   o.styleNavButton = RClass.register(o, new TStyle('NavButton'));
   o.styleHint      = RClass.register(o, new TStyle('Hint'));
   o.isLoading      = false;
   o.focus          = null;
   o.columns        = new TMap();
   o.rows           = new TList();
   o.editRow        = null;
   o.editColumn     = null;
   o.editConsole    = null;
   o.colStatus      = null;
   o.colEmpty       = null;
   o.hPanel         = null;
   o.hHead          = null;
   o.hRowsPanel     = null;
   o.hRows          = null;
   o.hNavigator     = null;
   o.hCaption       = null;
   o.hFottor        = null;
   o.lsnsStatusDblClick  = new TListeners();
   o.lsnsRowMdown        = new TListeners();
   o.lsnsRowDblClick     = new TListeners();
   o.ohNavMdown      = FTable_ohNavMdown;
   o.oeBuild         = FTable_oeBuild;
   o.oeResize        = FTable_oeResize;
   o.onBuildPanel    = FTable_onBuildPanel;
   o.onBuildNavPanel = FTable_onBuildNavPanel;
   o.onLoaded        = FTable_onLoaded;
   o.onResize        = FTable_onResize;
   o.onNavClick      = FTable_onNavClick;
   o.onDsFetchEnd    = FTable_onDsFetchEnd;
   o.onDsUpdateEnd   = FTable_onDsUpdateEnd;
   o.onLoadValue     = RMethod.empty;
   o.onSaveValue     = RMethod.empty;
	o.onMouseDown     = FTable_onMouseDown;
   o.onKeyDown       = FTable_onKeyDown;
   o.onKeyPress      = FTable_onKeyPress;
   o.onKeyUp         = FTable_onKeyUp;
	o.onLoadDataset   = FTable_onLoadDataset;
   o.construct       = FTable_construct;
	o.getFormName     = FTable_getFormName;
	o.getTableName    = FTable_getTableName;
   o.createChild     = FTable_createChild;
   o.loadValue       = FTable_loadValue;
   o.saveValue       = FTable_saveValue;
   o.setDataStatus   = FTable_setDataStatus;
   o.setStyleStatus  = FTable_setStyleStatus;
   o.buildNavButton  = FTable_buildNavButton;
   o.buildRow        = FTable_buildRow;
   o.click           = FTable_click;
   o.selectRow       = FTable_selectRow;
   o.moveTo          = FTable_moveTo;
   o.createRow       = FTable_createRow;
   o.appendRow       = FTable_appendRow;
   o.insertRow       = FTable_insertRow;
   o.deleteRow       = FTable_deleteRow;
   o.clearRows       = FTable_clearRows;
   o.dsInsert        = FTable_dsInsert;
   o.dsDelete        = FTable_dsDelete;
   o.focus           = FTable_focus;
   o.blur            = FTable_blur;
   o.refreshHint     = FTable_refreshHint;
   o.isSelected      = FTable_isSelected;
   o.findSelRows     = FTable_findSelRows;
	o.focus           = FTable_focus;
	o.testStatus      = FTable_testStatus;
   o.dump            = FTable_dump;
   return o;
}
function FTable_ohNavMdown() {
   var o = this.link;
   if (RClass.isClass(o, FTable)) {
      o.onNavClick(this);
   }
}
function FTable_onNavClick(hButton) {
   var o = this;
   if (o.hNavFirst == hButton) {
      o.dsMovePage(EDataAction.First);
   } else if (o.hNavPrior == hButton) {
      o.dsMovePage(EDataAction.Prior);
   } else if (o.hNavNext == hButton) {
      o.dsMovePage(EDataAction.Next);
   } else if (o.hNavLast == hButton) {
      o.dsMovePage(EDataAction.Last);
   }
}
function FTable_oeBuild(event) {
   var o = this;
   o.base.FContainer.oeBuild.call(o, event);
   if (event.isBefore()) {
      var hc = o.hPanel.insertRow().insertCell();
      hc.className = o.style('Heads');
      var h = o.hHeadPanel = RBuilder.appendTable(hc, o.style('Heads'), 0, 1, 0);
      o.hHead = o.hHeadPanel.insertRow();
      o.columns.set(o.colEmpty.name, o.colEmpty);
      var count = o.columns.count;
      for ( var n = 0; n < count; n++) {
         var c = o.columns.value(n);
         c.buildHead();
         o.hHead.appendChild(c.hPanel);
         c.index = c.hPanel.cellIndex;
			o.push(c);
      }
      for ( var n = 0; n < count; n++) {
         o.columns.value(n).showHead();
      }
      var hc = o.hPanel.insertRow().insertCell();
      var div = o.hRowsPanel = RBuilder.append(hc, 'DIV', o.style('RowsPanel'));
      var hT2 = RBuilder.appendTable(div, null, 0, 0, 0);
		hT2.width = '100%';
		hT2.height = '100%';
		var hRt = hT2.insertRow().insertCell();
      var h = RBuilder.appendTable(hRt, o.style('Rows'), 0, 1, 1);
      h.width = '100%';
      o.hRows = RBuilder.append(h, 'TBODY');
		hRt.height=1;
		var hRb = hT2.insertRow().insertCell();
      hRb.style.backgroundImage = 'url(' + RRes.imagePath('bg-cloud') + ')';
      var hn = o.hPanel.insertRow().insertCell();
      hn.className = o.style('Navigator');
      o.hNavigator = RBuilder.appendTable(hn, o.style('Navigator'));
      o.onBuildNavPanel()
   }
   if (event.isAfter()) {
      var count = o.rows.count;
      for ( var n = 0; n < count; n++) {
         o.buildRow(null, o.rows.get(n));
      }
   }
}
function FTable_oeResize(event) {
   if (event.isAfter()) {
      this.onResize();
   }
}
function FTable_onBuildPanel() {
   this.hPanel = RBuilder.newTable();
}
function FTable_buildNavButton(hParent, iconBf, text, iconAf) {
   var o = this;
   var h = RBuilder.append(hParent, 'SPAN', o.style('NavButton'));
   h.link = o;
   h.onmousedown = o.ohNavMdown;
   if (iconBf) {
      RBuilder.appendIcon(h, iconBf);
   }
   if (text) {
      RBuilder.appendText(h, text);
   }
   if (iconAf) {
      RBuilder.appendIcon(h, iconAf);
   }
   return h;
}
function FTable_onBuildNavPanel() {
   var o = this;
   var hr = o.hNavigator.insertRow();
   var hc = hr.insertCell();
	hc.noWrap = true;
   o.hHint = RBuilder.appendText(hc, 'Row: 1/5/200 Page:2/20', o.style('Hint'))
   var hc = hr.insertCell();
	hc.noWrap = true;
   hc.align = 'right';
   o.hNavFirst = o.buildNavButton(hc, 'ctl.db-first', '&nbsp;First');
   o.hNavPrior = o.buildNavButton(hc, 'ctl.db-prior', '&nbsp;Prior&nbsp;');
   o.hPage = RBuilder.appendEdit(hc)
   o.hPage.style.width = 40;
   o.hNavNext = o.buildNavButton(hc, null, '&nbsp;Next&nbsp;', 'ctl.db-next');
   o.hNavLast = o.buildNavButton(hc, null, '&nbsp;Last&nbsp;', 'ctl.db-last');
}
function FTable_onLoaded() {
   var o = this.tree;
   var doc = this.document;
   if (o && doc) {
      var row = RObj.nvl(this.workNode, this.focusNode, this.rootNode);
      o.loadDocument(doc);
      o.hideNode(o.loadingNode);
      o.isLoading = false;
      o.processLoaded(row);
   }
}
function FTable_onResize() {
   var o = this;
   if (o.hRows.rows.length > 0) {
      var count = o.hHead.cells.length;
      var row = o.hRows.rows[0];
      if (row.cells.length == count) {
         for ( var n = 1; n < count - 1; n++) {
            RHtml.changeWidth(o.hHead.cells[n], row.cells[n]);
            RLog.debug(o, 'Change width', row.cells[n].offsetWidth)
         }
      }
   }
}
function FTable_construct() {
   var o = this;
   o.base.FContainer.construct.call(o);
   var col = o.colStatus = RClass.create(FColumnStatus);
   col.table = this;
   col.name = '_s';
   o.columns.set(col.name, col);
   var col = o.colEmpty = RClass.create(FColumnEmpty);
   col.table = this;
   col.name = '_e';
   o.editConsole = RConsole.find(FEditConsole);
}
function FTable_getFormName(){
	return this.formName;
}
function FTable_getTableName(){
	return this.name;
}
function FTable_createChild(config) {
   var o = this;
   var c = o.base.FContainer.createChild.call(o, config);
   if (RClass.isClass(c, FRow)) {
      c.table = o;
      c.row = o.dsLoadRowNode(config);
      o.rows.push(c);
      return null;
   } else if (RClass.isClass(c, FColumn)) {
      c.table = o;
      c.loadConfig(config);
      o.columns.set(c.name, c);
      return null;
   }
   return c;
}
function FTable_loadValue(config) {
   var rows = this.rows;
   for ( var n = 0; n < rows.count; n++) {
   }
}
function FTable_saveValue(c) {
   var rs = this.rows;
   for ( var n = 0; n < rs.count; n++) {
      var r = rs.get(n);
      if (r.isSave()) {
         r.saveValue(c.create('Row'));
      }
   }
}
function FTable_setDataStatus(row, status) {
   var o = this;
   RLog.debug(o, '{1} = {2}', status, RClass.dump(row));
   row.dataStatus = status;
   o.colStatus.setDataStatus(row, status);
}
function FTable_setStyleStatus(row, status) {
   var hRow = row.hPanel;
   if (hRow) {
      switch (status) {
         case EStyle.Normal:
            row.select(false);
            break;
         case EStyle.Select:
            row.select(true);
            break;
      }
   }
}
function FTable_buildRow(row) {
   var o = this;
   var cs = o.columns;
   for ( var n = 0; n < cs.count; n++) {
      var c = cs.value(n);
      var cell = c.createCell(row);
      if(c.dataName){
         cell.set(RStr.nvl(row.get(c.dataName), c.dataDefault));
      }
      row.push(cell);
   }
   return row;
}
function FTable_onLoadDataset(ds){
	var o = this;
	o.clearRows();
	ds.moveFirst(true);
	while(ds.moveNext()){
		var r = o.createRow(ds.current());
		r.doStore();
		o.buildRow(r);
		o.appendRow(r);
	}
   o.refreshHint();
   o.resize();
}
function FTable_click(r) {
   var o = this;
   if (o.selectedRow) {
      o.setStyleStatus(o.selectRow, EStyle.Normal);
   }
   if (o.selectedRow == r) {
      o.selectedRow = null;
   } else {
      o.selectedRow = r;
      o.setStyleStatus(r, EStyle.Select);
      o.refreshHint();
   }
}
function FTable_selectRow(row, reset, force) {
   var o = this;
   var has = false;
   if (reset) {
      var count = o.rows.count;
      for(var n=0; n<count; n++){
         var r = o.rows.get(n);
         if(r != row && r.isSelect){
            r.select(false);
            has = true;
         }
      }
   }
	row.select(has || !row.isSelect || force);
   o.refreshHint();
}
function FTable_createRow(trow) {
   var row = RClass.create(FRow);
   row.table = this;
   row.row = trow;
   row.build();
   return row;
}
function FTable_appendRow(row) {
   this.hRows.appendChild(row.hRow);
   this.rows.push(row);
}
function FTable_insertRow(index) {
   this.editConsole.lost();
   var row = this.buildRow(index, null);
   this.select(row, true);
   this.onResize();
}
function FTable_deleteRow(r) {
   var o = this;
   r = RObj.nvl(r, o.selectedRow);
   if (!r) {
      return alert('Please select row.');
   }
   if (r.isExist()) {
      if (r.isDelete()) {
         r.doNormal();
         o.setDataStatus(r, EDataStatus.Unknown);
         o.setStyleStatus(r, EStyle.Select);
      } else {
         r.doDelete();
         o.setDataStatus(r, EDataStatus.Delete);
         o.setStyleStatus(r, EStyle.Delete);
      }
   } else {
      r.release();
   }
}
function FTable_dump(dump) {
   dump = RStr.nvlStr(dump);
   dump.appendLine(RClass.name(this));
   var rows = this.rows;
   for ( var n = 0; n < rows.count; n++) {
      dump.appendLine(rows.get(n).dump());
   }
   return dump;
}
function FTable_onDsFetchEnd(ds) {
   var o = this;
   o.loadDataset(ds);
   o.refreshHint();
   o.resize();
}
function FTable_onDsUpdateEnd(ds) {
   var o = this;
   o.clearRows();
   o.loadDataset(ds);
}
function FTable_clearRows() {
	var o = this;
	o.rows.clear();
	RHtml.clear(o.hRows);
}
function FTable_dsInsert() {
   this.insertRow();
}
function FTable_dsDelete() {
   this.deleteRow();
}
function FTable_focus() {
   var o = this;
   o.base.FContainer.focus.call(o);
   var row = o.rows.get(0);
   if (!o.selectedRow) {
      if (!o.rows.isEmpty()) {
         o.select(row, true);
      }
   }
   o.hRowsPanel.focus();
}
function FTable_blur() {
   var o = this;
   o.base.FContainer.blur.call(o);
}
function FTable_onMouseDown(e){
	RConsole.find(FFocusConsole).focusClass(MDataset, this, e);
}
function FTable_onKeyDown(e) {
   var o = this;
   var k = e.keyCode;
   if (EKey.Up == k) {
      o.moveTo(EDataAction.Prior);
   } else if (EKey.Down == k) {
      o.moveTo(EDataAction.Next);
   } else if (EKey.Home == k) {
      o.moveTo(EDataAction.First);
   } else if (EKey.End == k) {
      o.moveTo(EDataAction.Last);
   } else if (EKey.Enter == k) {
      o.processSelect(o.selectedRow);
   } else {
      o.processKeyDown(e);
   }
}
function FTable_onKeyUp(e) {
}
function FTable_onKeyPress(e) {
   var o = this;
   var k = e.keyCode;
   if (EKey.Up == k) {
      e.keyCode = null;
      e.returnValue = false;
   } else if (EKey.Down == k) {
      e.keyCode = null;
      e.returnValue = false;
   } else if (EKey.PageUp == k) {
      e.keyCode = null;
      e.returnValue = false;
   } else if (EKey.PageDown == k) {
      e.keyCode = null;
      e.returnValue = false;
   }
}
function FTable_moveTo(pos) {
   var o = this;
   var row = null;
   if (!RInt.isInt(pos)) {
      if (EDataAction.First == pos) {
         pos = 0;
      } else if (EDataAction.Prior == pos) {
         pos = o.rows.indexOf(o.selectedRow) - 1;
      } else if (EDataAction.Next == pos) {
         pos = o.rows.indexOf(o.selectedRow) + 1;
      } else if (EDataAction.Last == pos) {
         pos = o.rows.count + 1;
      }
   }
   pos = RInt.toRange(pos, 0, o.rows.count - 1);
   RLog.debug(o, 'Move row (position={1}, count={2})', pos, o.rows.count);
   row = o.rows.get(pos);
   if (row) {
      o.select(row, true);
   }
}
function FTable_refreshHint() {
	var o = this;
	var ds = o.dsStore;
	var h = o.hHint;
	if(ds && h){
		var ci = 0;
		if(o.selectedRow){
			ci = o.rows.indexOf(o.selectedRow.row);
		}
		h.innerText = 'Row:' + ci + '/' + ds.count + '/' + ds.total + ' Page:' + ds.page + '/' + ds.pageCount;
	}
}
function FTable_isSelected(){
	var o = this;
	var count = o.rows.count;
	for(var n=0; n<count; n++){
		if(o.rows.get(n).isSelect){
			return true;
		}
	}
	return false;
}
function FTable_findSelRows(){
	var ls = new TList();
	var rs = this.rows;
	var c = rs.count;
	for(var n=0; n<c; n++){
		var r = rs.get(n);
		if(r.isSelect){
			ls.push(r);
		}
	}
	return ls;
}
function FTable_focus(){
	var o = this;
	o.base.FContainer.focus.call(o);
	RConsole.find(FFocusConsole).focusClass(MDataset, this);
}
function FTable_testStatus(t){
	var o = this;
	var r = o.base.MDataset.testStatus.call(o, t);
	if(EDataAction.First == t){
		return true;
	}else if(EDataAction.Prior == t){
		return true;
	}else if(EDataAction.Next == t){
		return true;
	}else if(EDataAction.Last == t){
		return true;
	}else if(EDataAction.Action == t){
		return true;
	}
	return r;
}
function FWebTable(o){
	o = RClass.inherits(this, o, FTable);
	o.style = FWebTable_style;
	return o;
}
function FWebTable_style(n){
	return RClass.name(FTable) + '_' + n;
}
function MColumnDataset(){
   IObject.extendClass(this, FDatasetControl);
   this.className = 'MColumnDataset';
   this.value = ctb_cds_value;
   this.setValue = ctb_cds_setValue;
   this.dsValue = ctb_cds_dsValue;
   this.setDsValue = ctb_cds_setDsValue;
   this.doDataDisable = ctb_cds_doDataDisable;
   this.doDataLoad = ctb_cds_doDataLoad;
   this.doDataSave = ctb_cds_doDataSave;
   this.doDataPrepare = ctb_cds_doDataPrepare;
   return this;
}
function ctb_cds_value(oCell){
   return oCell.innerText;
}
function ctb_cds_setValue(oCell, sValue){
   oCell.innerText = sValue;
}
function ctb_cds_dsValue(oUnit){
   oUnit = oUnit ? oUnit : this.dsCtl.activeUnit();
   return oUnit ? oUnit.value(this.dataName) : IString.EMPTY;
}
function ctb_cds_setDsValue(sValue, oUnit){
   oUnit = oUnit ? oUnit : this.dsCtl.activeUnit();
   if(oUnit){oUnit.setValue(this.dataName, sValue);}
   return true;
}
function ctb_cds_doDataDisable(){}
function ctb_cds_doDataLoad(){}
function ctb_cds_doDataSave(){}
function ctb_cds_doDataPrepare(){}
function MColumnDropable(){
   IObject.extendClass(this, FColumnControl);
   this.className = 'MColumnDropable';
   this.classColumnDropableControl = true;
   this.onDropButtonOver = ctb_cld_onDropButtonOver;
   this.onDropButtonOut = ctb_cld_onDropButtonOut;
   this.onDropButtonClick = ctb_cld_onDropButtonClick;
   this.doDropAfter = ctb_cld_doDropAfter;
   this.buildCellEditor = ctb_cld_buildCellEditor;
   this.buildCellEditorAfter = ctb_cld_buildCellEditorAfter;
   this.dropAble = ctb_cld_dropAble;
   this.buildDrop = ctb_cld_buildDrop;
   this.drop = ctb_cld_drop;
   return this;
}
function ctb_cld_onDropButtonOver(){
   this.className = 'ctlDrop_btn_h';
}
function ctb_cld_onDropButtonOut(){
   this.className = 'ctlDrop_btn';
}
function ctb_cld_onDropButtonClick(){
   this.linkColumn.drop();
}
function ctb_cld_doDropAfter(sValue){
   var oEdit = EditManager.attributes.edit;
   var oCell = EditManager.attributes.cell;
   if(oEdit && oCell){
      oEdit.value = this.valueFormat(sValue);
   }
}
function ctb_cld_buildCellEditor(oCell, oPanel){
   var oTabCtl = this.parentControl;
   var oUnit = oCell.linkUnit;
   var sValue = oUnit.value(this.dataName);
   oTable = this.createElement('TABLE');
   oTable.className = 'ctlCbo_edt_tab';
   oTable.width = oPanel.offsetWidth - 4;
   oTable.height = oPanel.offsetHeight - 4;
   oTable.border = 0;
   oTable.cellPadding = 0;
   oTable.cellSpacing = 0;
   var oRow = oTable.insertRow();
   var oEdit = this.createElement('INPUT');
   oEdit.className = 'ctlTbl_edt';
   oEdit.value = this.valueFormat(sValue);
   oEdit.style.border = 0;
   oEdit.style.marginLeft = 1;
   oEdit.style.width = '100%';
   oEdit.style.height = '100%';
   oEdit.onkeydown = oTabCtl.onCellKeyDown;
   oEdit.onkeypress = oTabCtl.onCellKeyPress;
   this.buildCellEditorAfter(oEdit);
   oRow.insertCell().appendChild(oEdit);
   oEdit.linkCtl = oTabCtl;
   oEdit.linkColumn = this;
   oEdit.linkUnit = oUnit;
   oEdit.linkCell = oCell;
   oEdit.onEditBlur = this.onEditBlur;
   EditManager.onEditBlur = this.onEditBlur;
   EditManager.onEditBlur.linkEdit = oEdit;
   EditManager.attributes.edit = oEdit;
   EditManager.attributes.cell = oCell;
   oButton = this.createElement('SPAN');
   oButton.linkCtl = oTabCtl;
   oButton.linkColumn = this;
   oButton.className = 'ctlDrop_btn';
   oButton.innerText = '6';
   oButton.onmouseover = this.onDropButtonOver;
   oButton.onmouseout = this.onDropButtonOut;
   oButton.onclick = this.onDropButtonClick;
   var oCell = oRow.insertCell();
   oCell.width = '20';
   oCell.align = 'center';
   oCell.valign = 'middle';
   oCell.appendChild(oButton);
   oPanel.appendChild(oTable);
   if(oUnit.isInsert && this.insertAble){
      this.enable(oEdit);
   }else if(oUnit.isDelete()){
      this.disable(true, oEdit);
   }else if(this.updateAble){
      this.enable(oEdit);
   }else{
      this.disable(false, oEdit);
   }
   oEdit.doDataReference = function(){
      var oParams = DatasetManager.doDataReference(this.linkColumn.dataReference);
      if(oParams){
         var sValue = oParams.nameValue(this.linkColumn.dataName);
         if(this.linkColumn.setValue(sValue)){
            this.linkColumn.setCellValue(this.linkCell, sValue);
         }
      }
   };
   oEdit.doDefineReference = function(){
      DatasetManager.doDefineReference(this.linkColumn);
   };
   WindowManager.activeControl = oEdit;
   oEdit.focus();
   oEdit.select();
}
function ctb_cld_buildCellEditorAfter(oEdit){
}
function ctb_cld_dropAble(){
   return true;
}
function ctb_cld_buildDrop(oPanel){
}
function ctb_cld_drop(){
   if(!this.dropAble()){return;}
   var oPanel = EditManager.dropPanel(this.topClientWindow());
   this.buildDrop(oPanel);
   oPanel.buildFlag = true;
}
function MColumnEditable(o){
	o = RClass.inherits(this, o, MEditable);
	return o;
}
function MColumnReference(){
   IObject.extendClass(this, FDatasetRefrenceControl);
   this.className = 'MColumnReference';
   this.doDataReference = col_ref_doDataReference;
   return this;
}
function col_ref_doDataReference(){
   var oParams = DatasetManager.doDataReference(this.dataReference);
   if(oParams){
      var sValue = oParams.nameValue(this.dataName);
      this.setDsValue(sValue);
      this.setValue(sValue);
      var oCell = FocusManager.attribute('edit_cell');
      if(this.isReference){
         DatasetManager.doFieldFetch(this, this.dsCtl, true);
      }else{
         this.afterDataReference();
      }
      if(oCell){
         this.editCell(oCell);
      }
   }
}
function FDsToolBar(o){
	o = RClass.inherits(this, o, FToolBar);
	o.btnFetch    = null;
	o.btnSearch   = null;
	o.btnLov      = null;
	o.btnInsert   = null;
	o.btnUpdate   = null;
	o.btnDelete   = null;
	o.btnFirst    = null;
	o.btnPrior    = null;
	o.btnNext     = null;
	o.btnLast     = null;
	o.btnAction   = null;
	o.oeBuild     = FDsToolBar_oeBuild;
	o.onDsChanged = FDsToolBar_onDsChanged;
	o.register    = FDsToolBar_register;
	o.setStatus   = FDsToolBar_setStatus;
	return o;
}
function FDsToolBar_oeBuild(event){
	var o = this;
	o.base.FToolBar.oeBuild.call(o, event);
	if(event.isAfter()){
		var count = o.controls.count;
		for(var n=0; n<count; n++){
			var c = o.controls.value(n);
			if(RClass.isClass(c, FToolButton)){
				o.register(c.type, c);
			}
		}
		RConsole.find(FListenerConsole).register(MDataset, EAction.Status, o, o.onDsChanged);
	}
}
function FDsToolBar_onDsChanged(a, b, c, d){
	var dc = RConsole.find(FFocusConsole).findClass(MDataset);
	if(dc){
		var o = this;
		o.setStatus(dc, o.btnFetch, EDataAction.Fetch);
		o.setStatus(dc, o.btnSearch, EDataAction.Search);
		o.setStatus(dc, o.btnLov, EDataAction.Lov);
		o.setStatus(dc, o.btnInsert, EDataAction.Insert);
		o.setStatus(dc, o.btnUpdate, EDataAction.Update);
		o.setStatus(dc, o.btnDelete, EDataAction.Delete);
		o.setStatus(dc, o.btnFirst, EDataAction.First);
		o.setStatus(dc, o.btnPrior, EDataAction.Prior);
		o.setStatus(dc, o.btnNext, EDataAction.Next);
		o.setStatus(dc, o.btnLast, EDataAction.Last);
		o.setStatus(dc, o.btnAction, EDataAction.Action);
	}
}
function FDsToolBar_register(t, b){
	var o = this;
	if(EDataAction.Fetch == t){
		o.btnFetch = b;
	}else if(EDataAction.Search == t){
		o.btnSearch = b;
	}else if(EDataAction.Lov == t){
		o.btnLov = b;
	}else if(EDataAction.Insert == t){
		o.btnInsert = b;
	}else if(EDataAction.Update == t){
		o.btnUpdate = b;
	}else if(EDataAction.Delete == t){
		o.btnDelete = b;
	}else if(EDataAction.First == t){
		o.btnFirst = b;
	}else if(EDataAction.Prior == t){
		o.btnPrior = b;
	}else if(EDataAction.Next == t){
		o.btnNext = b;
	}else if(EDataAction.Last == t){
		o.btnLast = b;
	}else if(EDataAction.Action == t){
		o.btnAction = b;
	}
}
function FDsToolBar_setStatus(d, b, a){
	if(b){
		b.setEnable(d.testStatus(a));
	}
}
function FMenuBar(o, create){
	if(!create){return this;}
	o = IClass.inherits(this, o, FControl, MDocument);
	o.service         = 'menu.xml';
	o.focusNode       = null;
	o.isLoading       = false;
	o.indent          = 16;
	o.nodes           = new TList();
	o.allNodes        = new TList();
	o.types           = new TMap();
	o.onNodeLoaded    = null;
	o.onNodeClick     = null;
	o.onNodeDblClick  = null;
	o.onBuild         = FMenuBar_onBuild;
	o.onLoaded        = FMenuBar_onLoaded;
	o.connect         = FMenuBar_connect;
	o.release         = FMenuBar_release;
	return this;
}
function FMenuBar_onBuild(builder){
	var doc = builder.document;
	this.hBody = doc.createDiv();
	this.hBody.className = 'menu_panel';
	this.hParent.insertBefore(this.hBody);
	builder.hParent = this.hBody;
}
function FMenuBar_onLoaded(cnn){
	var doc = cnn.document;
	if(doc && doc.node){
		IControl.load(this, doc.node);
		this.build();
	}
}
function FMenuBar_connect(type, action, attrs){
	var doc = new TXmlDoc();
	var root = doc.root();
	root.set('type', type);
	root.set('action', action);
	root.create('Attributes').value = attrs;
	var self = this;
	var cnn = new TXmlCnn();
	cnn.onLoad = function(){self.onLoaded(cnn)};
	cnn.send(this.service, doc);
}
function FMenuBar_release(){
	var nodes = this.allNodes;
	for(var n=0; n<nodes.length; n++){
		var node = nodes[n];
		node.release();
	}
	this.allNodes = null;
	this.allNodesUuid = null;
	this.allNodesProperty = null;
	this.allNodesPropertyExtend = null;
	this.nodes = null;
	return true;
}
function FMenuButton(o){
	o = RClass.inherits(this, o, FControl, MMenuButton);
	o.action        = RClass.register(o, new TPtyStr('action', null));
	o.target        = RClass.register(o, new TPtyStr('target', null));
	o.page          = RClass.register(o, new TPtyStr('page'));
	o.hotkey        = RClass.register(o, new TPtyStr('hotkey'));
	o.method        = RClass.register(o, new TPtyStr('method'));
	o.icon          = RClass.register(o, new TPtyStr('icon', null));
	o.iconDisable   = RClass.register(o, new TPtyStr('iconDisable', null));
	o.attributes    = RClass.register(o, new TPtyStr('attributes'));
	o.onButtonClick = RClass.register(o, new HClick('onButtonClick'), FMenuButton_onButtonClick);
	o.styleButton   = RClass.register(o, new TStyle('Button'));
	o.styleLabel    = RClass.register(o, new TStyle('Label'));
	o.styleDisable  = RClass.register(o, new TStyle('Disable'));
	o.styleHover    = RClass.register(o, new TStyle('Hover'));
	o.stylePress    = RClass.register(o, new TStyle('Press'));
	o.disabled      = false;
	o.hButton       = null;
	o.hButtonLine   = null;
	o.hButtonPanel  = null;
	o.hIcon         = null;
	o.hText         = null;
	o.oeBuild       = FMenuButton_oeBuild;
	o.oeEnable      = FMenuButton_oeEnable;
	o.oeDisable     = FMenuButton_oeDisable;
	o.onBuildPanel  = FMenuButton_onBuildPanel;
	o.onEnter       = FMenuButton_onEnter;
	o.onLeave       = FMenuButton_onLeave;
	o.onMouseDown   = FMenuButton_onMouseDown;
	o.onMouseUp     = FMenuButton_onMouseUp;
	o.construct     = FMenuButton_construct;
	return o;
}
function FMenuButton_oeBuild(event){
	var o = this;
	o.base.FControl.oeBuild.call(o, event);
	var h = o.hPanel;
	o.hButton = RBuilder.appendTable(o.hPanel, o.style('Button'));
	o.attachEvent('onButtonClick', o.hButton);
	var hLine = o.hButtonLine = o.hButton.insertRow();
	var hCel = hLine.insertCell();
	if(o.icon){
		o.hIcon = RBuilder.appendIcon(hCel, o.icon);
	}
	if(o.label){
		o.hLabel = RBuilder.appendText(hCel, (o.hIcon ? '&nbsp;' : '') + o.label);
		o.hLabel.className = o.style('Label');
	}
	return EEventStatus.Stop;
}
function FMenuButton_onBuildPanel(){
	this.hPanel = RBuilder.create(null, 'DIV');
}
function FMenuButton_oeEnable(event){
	var o = this;
	o.base.FControl.oeEnable.call(o, event);
	o.hPanel.className = o.style('Button');
	if(o.iconDisable && o.icon){
		o.hIcon.src = RRes.iconPath(o.icon);
	}
	return EEventStatus.Stop;
}
function FMenuButton_oeDisable(event){
	var o = this;
	o.base.FControl.oeDisable.call(o, event);
	o.hPanel.className = o.style('Disable');
	if(o.iconDisable){
		o.hIcon.src = RRes.iconPath(o.iconDisable);
	}
	return EEventStatus.Stop;
}
function FMenuButton_onEnter(){
	var o = this;
	if(!o.disabled){
		o.hPanel.className = o.style('Hover');
	}
}
function FMenuButton_onLeave(){
	var o = this;
	if(!o.disabled){
		o.hPanel.className = o.style('Panel');
	}
}
function FMenuButton_onMouseDown(){
	var o = this;
	if(!o.disabled){
		o.hPanel.className = o.style('Press');
	}
}
function FMenuButton_onMouseUp(){
	var o = this;
	if(!o.disabled){
		o.hPanel.className = o.style('Hover');
	}
}
function FMenuButton_onButtonClick(){
	var o = this;
	if(!o.disabled){
		RConsole.find(FFocusConsole).blur();
		if(o.action){
			eval(o.action);
		}
		if(o.page || o.method){
			var form = RHtml.form(o.hButton);
			var p = RPage.parse(o.page);
			if(o.method){
				p.action = o.method;
			}
			p.split(o.attributes);
			p.post(form, o.target);
		}
	}
}
function FMenuButton_construct(){
	var o = this;
	o.base.FControl.construct.call(o);
}
function FMenuButtonMenu(o){
	o = RClass.inherits(this, o, FControl, MEventFocus, MEventClick, MLsnClick);
	o.action       = RClass.register(o, new TPtyStr('action', null));
	o.target       = RClass.register(o, new TPtyStr('target', null));
	o.page         = RClass.register(o, new TPtyStr('page'));
	o.hotkey       = RClass.register(o, new TPtyStr('hotkey'));
	o.method       = RClass.register(o, new TPtyStr('method'));
	o.icon         = RClass.register(o, new TPtyStr('icon', null));
	o.iconDisable  = RClass.register(o, new TPtyStr('iconDisable', null));
	o.attributes   = RClass.register(o, new TPtyStr('attributes'));
	o.disabled     = false;
	o.hButton      = null;
	o.hButtonLine  = null;
	o.hButtonPanel = null;
	o.hIcon        = null;
	o.hText        = null;
	o.oeBuild      = FMenuButtonMenu_oeBuild;
	o.oeEnable     = FMenuButtonMenu_oeEnable;
	o.oeDisable    = FMenuButtonMenu_oeDisable;
	o.onBuildPanel = FMenuButtonMenu_onBuildPanel;
	o.onEnter      = FMenuButtonMenu_onEnter;
	o.onLeave      = FMenuButtonMenu_onLeave;
	o.onMouseDown  = FMenuButtonMenu_onMouseDown;
	o.onMouseUp    = FMenuButtonMenu_onMouseUp;
	o.onClick      = FMenuButtonMenu_onClick;
	o.construct    = FMenuButtonMenu_construct;
	return o;
}
function FMenuButtonMenu_oeBuild(event){
	var o = this;
	o.base.FControl.oeBuild.call(o, event);
	var h = o.hPanel;
	o.hButton = RBuilder.appendTable(o.hPanel, o.style('Button'));
	o.linkClickEvent(o.hButton);
	var hLine = o.hButtonLine = o.hButton.insertRow();
	var hCel = hLine.insertCell();
	if(o.icon){
		o.hIcon = RBuilder.appendIcon(hCel, o.icon);
	}
	if(o.label){
		o.hLabel = RBuilder.appendText(hCel, (o.hIcon ? '&nbsp;' : '') + o.label);
		o.hLabel.className = o.style('Label');
	}
	return EEventStatus.Stop;
}
function FMenuButtonMenu_onBuildPanel(){
	this.hPanel = RBuilder.create(null, 'DIV');
}
function FMenuButtonMenu_oeEnable(event){
	var o = this;
	o.base.FControl.oeEnable.call(o, event);
	o.hPanel.className = o.style('Button');
	if(o.iconDisable && o.icon){
		o.hIcon.src = RRes.iconPath(o.icon);
	}
	return EEventStatus.Stop;
}
function FMenuButtonMenu_oeDisable(event){
	var o = this;
	o.base.FControl.oeDisable.call(o, event);
	o.hPanel.className = o.style('Disable');
	if(o.iconDisable){
		o.hIcon.src = RRes.iconPath(o.iconDisable);
	}
	return EEventStatus.Stop;
}
function FMenuButtonMenu_onEnter(){
	var o = this;
	if(!o.disabled){
		o.hPanel.className = o.style('Hover');
	}
}
function FMenuButtonMenu_onLeave(){
	var o = this;
	if(!o.disabled){
		o.hPanel.className = o.style('Panel');
	}
}
function FMenuButtonMenu_onMouseDown(){
	var o = this;
	if(!o.disabled){
		o.hPanel.className = o.style('Press');
	}
}
function FMenuButtonMenu_onMouseUp(){
	var o = this;
	if(!o.disabled){
		o.hPanel.className = o.style('Hover');
	}
}
function FMenuButtonMenu_onClick(){
	var o = this;
	if(!o.disabled){
		RConsole.find(FFocusConsole).focus(o);
		if(o.action){
			eval(o.action);
		}
		if(o.page || o.method){
			var form = RHtml.form(o.hButton);
			var p = RPage.parse(o.page);
			if(o.method){
				p.action = o.method;
			}
			p.split(o.attributes);
			p.post(form, o.target);
		}
		o.processClick();
	}
}
function FMenuButtonMenu_construct(){
	var o = this;
	o.base.FControl.construct.call(o);
}
function FMenuButtonSplit(o){
	o = RClass.inherits(this, o, FControl, MMenuButton);
	o.styleUp      = RClass.register(o, new TStyle('Up'));
	o.styleDown    = RClass.register(o, new TStyle('Down'));
	o.disabled     = false;
	o.oeBuild      = FMenuButtonSplit_oeBuild;
	o.onBuildPanel = FMenuButtonSplit_onBuildPanel;
	return o;
}
function FMenuButtonSplit_oeBuild(e){
   var o = this;
   o.base.FControl.oeBuild.call(o, e);
   var h = o.hPanel;
   var hc = h.insertRow().insertCell();
   hc.className = o.style('Up');
   var hc = h.insertRow().insertCell();
   hc.className = o.style('Down');
   return EEventStatus.Stop;
}
function FMenuButtonSplit_onBuildPanel(){
   this.hPanel = RBuilder.newTable();
}
function FPopupMenu(o){
	o = RClass.inherits(this, o, FContainer, MShadow);
	o.styleCaption = RClass.register(o, new TStyle('Caption'));
	o.styleButton  = RClass.register(o, new TStyle('Button'));
	o.opener       = null;
	o.hContainer   = null;
	o.hCaption     = null;
	o.hButtonPanel = null;
	o.hIcon        = null;
	o.hText        = null;
	o.oeBuild      = FPopupMenu_oeBuild;
	o.onBuildPanel = FPopupMenu_onBuildPanel;
	o.onBlur       = FPopupMenu_onBlur;
	o.appendChild  = FPopupMenu_appendChild;
	o.show         = FPopupMenu_show;
	o.hide         = FPopupMenu_hide;
	o.testInRange  = FPopupMenu_testInRange;
	return o;
}
function FPopupMenu_oeBuild(event){
	var o = this;
	o.base.FControl.oeBuild.call(o, event);
	var t = o.parent;
	var h = o.hPanel;
	if(event.isBefore()){
		o.hContainer = RBuilder.appendTable(o.hPanel);
		var h = o.hCaption = o.hContainer.insertRow().insertCell();
		h.className = o.style('Caption');
		RBuilder.appendEmpty(h);
		o.hLastRow = o.hContainer.insertRow();
		var h = o.hLastRow.insertCell();
		RBuilder.appendEmpty(h, 1, 4);
		o.setVisible(false);
	}
}
function FPopupMenu_onBuildPanel(){
	this.hPanel = RBuilder.append(null, 'SPAN');
}
function FPopupMenu_onBlur(){
	var o = this;
	if(o.opener){
		o.opener.onBlur();
	}else{
		o.hide();
	}
}
function FPopupMenu_appendChild(ctl){
	var o = this;
	var h = o.hCaption = o.hContainer.insertRow(o.hLastRow.rowIndex).insertCell();
	h.className = o.style('Button');
	h.appendChild(ctl.hPanel);
}
function FPopupMenu_show(h, pos, v){
	var o = this;
	o.base.FContainer.show.call(o, v);
	var r = RHtml.rect(h);
	if(EAlign.BottomRight == pos){
		var tr = this.calcRect();
		r.left = r.right - tr.width() + 1;
		r.bottom += 1;
	}
	this.setBounds(r.left, r.bottom);
	o.base.MShadow.show.call(o);
	o.hPanel.style.zIndex = RLayer.next();
}
function FPopupMenu_hide(){
	var o = this;
	o.base.FContainer.hide.call(o);
	o.base.MShadow.hide.call(o);
}
function FPopupMenu_testInRange(e){
	return this == RControl.htmlControl(e.srcElement, FPopupMenu);
}
function FToolBar(o){
	o = RClass.inherits(this, o, FContainer, MDisplayAble, MTop);
	o.align            = RClass.register(o, new TPtyStr('align'));
	o.styleButton      = RClass.register(o, new TStyle('Button'));
	o.target           = null;
	o.hLine            = null;
	o.oeBuild          = FToolBar_oeBuild;
	o.onEnter          = RMethod.empty;
	o.onLeave          = RMethod.empty;
	o.onBuildPanel     = FToolBar_onBuildPanel;
	o.appendChild      = FToolBar_appendChild;
	o.addClickListener = FToolBar_addClickListener;
	o.button           = FToolBar_button;
	o.clear            = FToolBar_clear;
	return o;
}
function FToolBar_oeBuild(event){
	var o = this;
	o.base.FContainer.oeBuild.call(o, event);
	if(event.isAfter()){
		if(EAlign.Right != o.align){
			var hTd = RBuilder.create(null, 'TD');
			RBuilder.appendEmpty(hTd);
			o.hLine.appendChild(hTd);
		}
	}
}
function FToolBar_onBuildPanel(){
	var o = this;
	var h = o.hPanel = RBuilder.newTable(o.hParent);
	o.hLine = h.insertRow();
}
function FToolBar_appendChild(button){
	this.hLine.appendChild(button.hPanel);
}
function FToolBar_addClickListener(name, method){
	var btn = this.component(name);
	if(btn){
		btn.addClickListener(new TListener(this, method));
	}
}
function FToolBar_button(name){
	return this.components.get(name);
}
function FToolBar_clear(){
   if(this.hTable && this.hLine){
      this.hLine.removeNode(true);
      this.hLine = this.hTable.insertRow();
   }
   this.buttons = new Array();
}
function FToolButton(o){
	o = RClass.inherits(this, o, FControl, MLsnClick);
	o.type         = RClass.register(o, new TPtyStr('type'));
	o.action       = RClass.register(o, new TPtyStr('action'));
	o.target       = RClass.register(o, new TPtyStr('target'));
	o.page         = RClass.register(o, new TPtyStr('page'));
	o.hotkey       = RClass.register(o, new TPtyStr('hotkey'));
	o.method       = RClass.register(o, new TPtyStr('method'));
	o.icon         = RClass.register(o, new TPtyStr('icon'));
	o.iconDisable  = RClass.register(o, new TPtyStr('iconDisable'));
	o.attributes   = RClass.register(o, new TPtyStr('attributes'));
	o.styleButton  = RClass.register(o, new TStyle('Button'));
	o.styleLabel   = RClass.register(o, new TStyle('Label'));
	o.styleDisable = RClass.register(o, new TStyle('Disable'));
	o.styleHover   = RClass.register(o, new TStyle('Hover'));
	o.stylePress   = RClass.register(o, new TStyle('Press'));
	o.disabled     = false;
	o.hButton      = null;
	o.hButtonLine  = null;
	o.hButtonPanel = null;
	o.hIcon        = null;
	o.hText        = null;
	o.oeBuild      = FToolButton_oeBuild;
	o.oeEnable     = FToolButton_oeEnable;
	o.oeDisable    = FToolButton_oeDisable;
	o.onBuildPanel = FToolButton_onBuildPanel;
	o.onEnter      = FToolButton_onEnter;
	o.onLeave      = FToolButton_onLeave;
	o.onMouseDown  = FToolButton_onMouseDown;
	o.onMouseUp    = FToolButton_onMouseUp;
	o.onClick      = FToolButton_onClick;
	o.construct    = FToolButton_construct;
	return o;
}
function FToolButton_oeBuild(event){
	var o = this;
	o.base.FControl.oeBuild.call(o, event);
	var t = o.parent;
	var h = o.hPanel;
	o.hButton = RBuilder.appendTable(o.hPanel, o.style('Panel'));
	var hLine = o.hButtonLine = o.hButton.insertRow();
	var hCel = o.hButtonPanel = hLine.insertCell();
	hCel.className = t.style('Button');
	if(o.icon){
		o.hIcon = RBuilder.appendIcon(hCel, o.icon);
	}
	if(o.label){
		o.hText = RBuilder.append(hCel, 'SPAN');
		o.hText.innerHTML = '&nbsp;' + o.label;
	}
	if(o.hotkey){
		RConsole.find(FKeyConsole).register(o.hotkey, new TListener(o, o.onClick));
	}
	return EEventStatus.Stop;
}
function FToolButton_oeEnable(event){
	var o = this;
	o.base.FControl.oeEnable.call(o, event);
	if(o.iconDisable && o.icon){
		o.hIcon.src = RRes.iconPath(o.icon);
	}
	o.hPanel.className = o.style('Button');
	return EEventStatus.Stop;
}
function FToolButton_oeDisable(event){
	var o = this;
	o.base.FControl.oeDisable.call(o, event);
	if(o.iconDisable){
		o.hIcon.src = RRes.iconPath(o.iconDisable);
	}
	o.hPanel.className = o.style('Disable');
	return EEventStatus.Stop;
}
function FToolButton_onBuildPanel(){
	this.hPanel = RBuilder.create(null, 'TD', this.style('Button'));
}
function FToolButton_onEnter(h){
	var o = this;
	if(!o.disabled && o.hPanel == h){
		o.hPanel.className = o.style('Hover');
	}
}
function FToolButton_onLeave(h){
	var o = this;
	if(!o.disabled && o.hPanel == h){
		o.hPanel.className = o.style('Button');
	}
}
function FToolButton_onMouseDown(){
	if(!this.disabled){
		this.hPanel.className = this.style('Press');
	}
}
function FToolButton_onMouseUp(h){
	var o = this;
	if(!o.disabled){
		o.hPanel.className = o.style('Hover');
	}
}
function FToolButton_onClick(h){
	var o = this;
	if(!o.disabled && (EAction.Design != o.inAction)){
		var fc = RConsole.find(FFocusConsole);
		fc.storeFocus();
		fc.blur();
		if(o.action){
			eval(o.action);
		}
		if(o.page || o.method){
			var form = RHtml.form(o.hButton);
			var p = RPage.parse(o.page);
			if(o.method){
				p.action = o.method;
			}
			p.split(o.attributes);
			p.post(form, o.target);
		}
		o.processClick();
	}
}
function FToolButton_construct(){
	var o = this;
	o.base.FControl.construct.call(o);
}
function FToolButton_click(){
	this.onClick();
}
function FToolButtonCheck(o){
	o = RClass.inherits(this, o, FToolButton);
	o.down         = RClass.register(o, new TPtyBool('down', false));
	o.onEnter      = FToolButtonCheck_onEnter;
	o.onLeave      = FToolButtonCheck_onLeave;
	o.onMouseDown  = FToolButtonCheck_onMouseDown;
	o.onMouseUp    = FToolButtonCheck_onMouseUp;
	o.setDown      = FToolButtonCheck_setDown;
	return o;
}
function FToolButtonCheck_onEnter(){
	if(!this.down){
		this.hPanel.className = this.style('Hover');
	}
}
function FToolButtonCheck_onLeave(){
	if(!this.down){
		this.hPanel.className = this.style('Button');
	}
}
function FToolButtonCheck_onMouseDown(){
	this.hPanel.className = this.style('Press');
}
function FToolButtonCheck_onMouseUp(){
	var o = this;
	o.hPanel.className = o.style('Hover');
	o.setDown(!o.down)
	if(o.action){
		eval(o.action);
	}
	o.processClick(o, o.down);
}
function FToolButtonCheck_setDown(down){
	var o = this;
	if(o.down != down){
		o.down = down;
		if(down){
			o.hPanel.className = o.style('Down');
		}else{
			o.hPanel.className = o.style('Button');
		}
	}
}
function FToolButtonMenu(o){
	o = RClass.inherits(this, o, FToolButton, MDropable, MFocus);
	o.popup       = null;
	o.hDropPanel  = null;
	o.oeBuild     = FToolButtonMenu_oeBuild;
	o.onLeave     = FToolButtonMenu_onLeave;
	o.onBlur      = FToolButtonMenu_onBlur;
	o.onMouseDown = FToolButtonMenu_onMouseDown;
	o.onMouseUp   = FToolButtonMenu_onMouseUp;
	o.onDropMdown = FToolButtonMenu_onDropMdown;
	o.construct   = FToolButtonMenu_construct;
	o.push        = FToolButtonMenu_push;
	o.drop        = FToolButtonMenu_drop;
	return o;
}
function FToolButtonMenu_oeBuild(event){
	var o = this;
	if(event.isBefore()){
		o.base.FToolButton.oeBuild.call(o, event);
		var h = o.hDropPanel = o.hButtonLine.insertCell();
		h.className = o.style('Drop')
		h.innerText = '6';
	}
	return EEventStatus.Continue;
}
function FToolButtonMenu_onLeave(h){
	var o = this;
	if(!o.popup.isShow()){
		o.base.FToolButton.onLeave.call(o, h);
	}
}
function FToolButtonMenu_onBlur(e){
	var o = this;
	if(e){
		if(o.popup.testInRange(e)){
			return false;
		}
	}
	o.hPanel.className = o.style('Button');
	o.popup.hide();
}
function FToolButtonMenu_onMouseDown(h){
	var o = this;
	o.base.FToolButton.onMouseDown.call(o, h);
	if(!(o.action || o.page)){
		o.drop();
	}
}
function FToolButtonMenu_onMouseUp(h){
	var o = this;
	if(!o.popup.isShow()){
		o.base.FToolButton.onMouseUp.call(o, h);
	}
}
function FToolButtonMenu_onDropMdown(){
	this.onMouseDown();
}
function FToolButtonMenu_construct(){
	var o = this;
	o.popup = RControl.create(FPopupMenu);
	o.popup.opener = o;
}
function FToolButtonMenu_push(c){
	if(RClass.isClass(c, MMenuButton)){
		var o = this;
		o.base.FToolButton.push.call(o, c);
		o.popup.push(c);
	}
}
function FToolButtonMenu_drop(){
	this.popup.show(this.hDropPanel, EAlign.BottomRight);
}
function FToolButtonSplit(o){
	o = RClass.inherits(this, o, FControl);
	o.styleButton  = RClass.register(o, new TStyle('Button'));
	o.hButton      = null;
	o.oeBuild      = FToolButtonSplit_oeBuild;
	o.onBuildPanel = FToolButtonSplit_onBuildPanel;
	return o;
}
function FToolButtonSplit_oeBuild(event){
	var o = this;
	o.base.FControl.oeBuild.call(o, event);
	o.hButton = RBuilder.append(this.hPanel, 'DIV', o.style('Button'));
	return EEventStatus.Stop;
}
function FToolButtonSplit_onBuildPanel(){
	this.hPanel = RBuilder.create(null, 'TD', this.style('Panel'));
}
function FToolButtonText(o){
	o = RClass.inherits(this, o, FToolButton);
	return o;
}
function MMenuButton(o){
	o = RClass.inherits(this, o);
	return o;
	}
function RToolBarFace(){
	var o = this;
	o.fromNode = RToolBar_fromNode;
	return o;
}
var RToolBar = new RToolBarFace();
function RToolBar_fromNode(c, p, r){
	if(c && c.nodes){
		var xtb = null;
		var ns = c.nodes;
		for(var j=0; j<ns.count; j++){
			var n = ns.get(j);
			if('ToolBar' == n.name){
				if(!xtb){
					xtb = n;
				}else if(n.nodes){
					for(var i=0; i<n.nodes.count; i++){
						xtb.push(n.nodes.get(i));
					}
				}
			}
		}
		if(r){
			for(var j=ns.count-1; j>=0; j--){
				var n = ns.get(j);
				if('ToolBar' == n.name){
					ns.removeItem(n);
				}
			}
		}
		if(xtb){
			return RControl.fromNode(xtb, p);
		}
	}
}
function FTreeColumn(o){
	o = RClass.inherits(this, o, FControl);
	o.icon         = RClass.register(o, new TPtyStr('icon'));
	o.dataName     = RClass.register(o, new TPtyStr('dataName'));
	o.display      = RClass.register(o, new TPtyBool('display', EBool.False));
	o.config       = RClass.register(o, new TPtyCfg('config'));
	o.oeBuild      = FTreeColumn_oeBuild;
	o.onBuildPanel = FTreeColumn_onBuildPanel;
	return o;
}
function FTreeColumn_oeBuild(event){
	var o = this;
	var r = o.base.FControl.oeBuild.call(o, event);
	o.hPanel.innerText = o.label;
	o.hPanel.noWrap = true;
	if(!o.display){
		o.hPanel.style.display = 'none';
	}
	if(o.width){
		o.hPanel.style.width = o.width;
	}
	return EEventStatus.Stop;
}
function FTreeColumn_onBuildPanel(){
	this.hPanel = RBuilder.create(null, 'TH');
}
function FTreeLevel(o){
	o = RClass.inherits(this, o, FControl);
	o.id           = RClass.register(o, new TPtyStr('id'));
	o.color        = RClass.register(o, new TPtyStr('color'));
	o.bgColor      = RClass.register(o, new TPtyStr('bgColor'));
	return o;
}
function FTreeNode(o){
	o = RClass.inherits(this, o, FControl);
	o.type           = RClass.register(o, new TPtyStr('type'));
	o.uuid           = RClass.register(o, new TPtyStr('uuid'));
	o.isValid        = RClass.register(o, new TPtyBool('isValid'), true);
	o.icon           = RClass.register(o, new TPtyStr('icon'));
	o.note           = RClass.register(o, new TPtyStr('note'));
	o.child          = RClass.register(o, new TPtyBool('child'));
	o.builded        = false;
	o.level          = 0;
	o.attributes     = new TAttrs();
	o.tree           = null;
	o.status         = ENodeStatus.Normal;
	o.loaded         = false;
	o.extended       = false;
	o.hCell          = null;
	o.hIcon          = null;
	o.hImage         = null;
	o.hPanel         = null;
	o.hText          = null;
	o.oeBuild        = RMethod.empty;
	o.onClick        = FTreeNode_onClick;
	o.topNode        = FTreeNode_topNode;
	o.createChild    = FTreeNode_createChild;
	o.build          = FTreeNode_build;
	o.loadConfig     = FTreeNode_loadConfig;
	o.saveConfig     = FTreeNode_saveConfig;
	o.hasChild       = FTreeNode_hasChild;
	o.get            = FTreeNode_get;
	o.set            = FTreeNode_set;
	o.set            = FTreeNode_set;
	o.show           = FTreeNode_show;
	o.hide           = FTreeNode_hide;
	o.extend         = FTreeNode_extend;
	o.setStatus      = FTreeNode_setStatus;
	o.push           = FTreeNode_push;
	o.remove         = FTreeNode_remove;
	o.removeChildren = FTreeNode_removeChildren;
	o.innerDump      = FTreeNode_innerDump;
	o.findByUuid     = FTreeNode_findByUuid;
	return o;
}
function FTreeNode_onClick(){
	var o = this;
	var t = o.tree;
	var isImg = false;
	if(event.srcElement.tagName == 'IMG'){
		isImg = (event.srcElement.type == 'img');
	}
	var isParent = false;
	var find = t.focusNode;
	while(find){
		if(find == o){
			isParent = true;
			break;
		}
		find = find.parent;
	}
	if(!isImg || (isImg && (isParent || !o.child))){
		t.setFocusNode(o);
	}
	if(!o.loaded && o.child){
		if(t.isLoading){
			return alert('Is loading tree data!\nPlease wait a while!');
		}
		o.extend(true);
		if(!isImg){
			t.lsnsClick.process(t, o);
		}
	}else{
		if(o.child){
			if(isImg){
				o.extend(!o.extended)
			}else{
				o.extend(true)
			}
		}
		if((isImg && isParent) || (isImg && !o.child) || !isImg){
			t.lsnsClick.process(t, o);
		}
	}
	if(event){
		event.returnValue = false;
	}
}
function FTreeNode_build(index){
	var o = this;
	if(o.builded){return;}
	var t = o.tree;
	var p = o.parent;
	if(p){o.level = p.level + 1;}
	var lvl = t.levels.get(o.level);
	index = (p && p.hPanel) ? p.hPanel.rowIndex+1+RInt.nvl(index) : null;
	var indent = t.indent * Math.max(o.level-1, 0);
	var h = o.hPanel = RBuilder.appendRow(t.hTable, t.cssNode, index);
	if(lvl){
		if(lvl.color){
			h.style.color = lvl.color;
		}
		if(lvl.bgColor){
			h.style.backgroundColor = lvl.bgColor;
		}
	}
	h.onclick = t.ohNodeClick;
	h.onmouseenter = t.ohNodeEnter;
	h.onmouseleave = t.ohNodeLeave;
	h.link = o;
	o.hCell = RBuilder.appendCell(o.hPanel, t.cssCell);
	o.hCell.noWrap = true;
	var icon = o.child ? t.iconPlus : t.iconNode;
	o.hImage = RBuilder.appendIcon(o.hCell, icon);
	o.hImage.type = 'img';
	o.hImage.style.marginLeft = indent;
	icon = o.icon ? o.icon : (o.type ? o.type.icon : t.iconEmpty);
	o.hIcon = RBuilder.appendIcon(o.hCell, icon);
	o.hIcon.type = 'icon';
	if(!o.isValid){
		o.hIcon.className = 'Tag_Icon_Disable';
	}
	var text = '&nbsp;' + o.label;
	if(o.note){
		text += '&nbsp;<FONT color=green>[ ' + o.note + ' ]</FONT>';
	}
	o.hText = RBuilder.appendText(o.hCell, text);
	var cols = t.columns;
	if(cols){
		for(var n=1; n<cols.count; n++){
			var col = cols.value(n);
			var h = RBuilder.appendCell(o.hPanel, t.cssCell);
			h.noWrap = true;
			h.innerText = RStr.nvl(o.get(col.dataName));
			if(!col.display){
				h.style.display = 'none';
			}
		}
	}
	o.builded = true;
}
function FTreeNode_topNode(){
	var o = this;
	var r = null;
	var rn = o.tree.rootNode;
	while(o.parent){
		if(o.parent != rn && RClass.isClass(o.parent, FTreeNode)){
			o = r = o.parent;
		}else{
			break;
		}
	}
	return r;
}
function FTreeNode_createChild(config){
	var r = null;
	if(config.isName('Node')){
		r = RClass.create(FTreeNode);
		r.tree = this.tree;
	}
	return r;
}
function FTreeNode_loadConfig(config){
	var o = this;
	o.base.FControl.loadConfig.call(o, config);
	o.type = RObj.nvl(this.tree.types.get(config.get('type')), this.tree.type);
	o.attributes.append(config.attrs);
	var attrs = config.get('attribute')
	if(attrs){
		o.attributes.unpack(attrs);
	}
}
function FTreeNode_saveConfig(config){
	var o = this;
	o.base.FControl.saveConfig.call(o, config);
	config.set('type', o.type.name);
	config.set('attribute', o.attributes.pack());
}
function FTreeNode_hasChild(){
	var o = this;
	if(o.child){
		return o.components && o.components.count>0;
	}
	return false;
}
function FTreeNode_get(name){
	return this.attributes.get(name);
}
function FTreeNode_set(name, value){
	this.attributes.set(name, value);
}
function FTreeNode_show(){
	var o = this;
	var t = o.tree;
	if(o.hPanel){
		o.hPanel.style.display = 'block';
	}
	if(o.components){
		var count = o.components.count;
		for(var n=0; n<count; n++){
			var child = o.components.value(n);
			if(child){
				child.build(n);
				child.hPanel.style.display = 'block';
				if(child.extended){
					child.show();
				}
			}
		}
	}
}
function FTreeNode_hide(){
	var o = this;
	var t = o.tree;
	if(o.hPanel){
		o.hPanel.style.display = 'none';
	}
	if(o.components){
		var count = o.components.count;
		for(var n=0; n<count; n++){
			var child = o.components.value(n);
			if(child){
				child.hide();
			}
		}
	}
}
function FTreeNode_extend(flag){
	var o = this;
	var t = o.tree;
	if(!o.loaded && o.child){
		if(t.isLoading){
			return alert('Is loading tree data!\nPlease wait a while!');
		}else{
			t.loadNode(o);
		}
	}else{
		if(o.hImage && !o.hasChild()){
			o.hImage.src = RRes.iconPath(t.iconNode);
			return false;
		}
		o.extended = flag;
		if(o.child && o.hImage){
			o.hImage.src = RRes.iconPath(flag ? t.iconMinus : t.iconPlus);
		}
		if(flag){
			o.show();
		}else if(o.components){
			var count = o.components.count;
			for(var n=0; n<count; n++){
				var child = o.components.value(n);
				if(child){
					child.hide();
				}
			}
		}
	}
}
function FTreeNode_setStatus(status){
	var o = this;
	var t = o.tree;
	switch(status){
		case ENodeStatus.Normal:
			o.hPanel.className = t.cssNode;
			o.hCell.className = t.cssCell;
			break;
		case ENodeStatus.Select:
			o.hPanel.className = t.cssNodeSelect;
			o.hCell.className = t.cssCellSelect;
			break;
		case ENodeStatus.Hover:
			o.hPanel.className = t.cssNodeHover;
			o.hCell.className = t.cssCellHover;
			break;
	}
}
function FTreeNode_push(n){
	var o = this;
	o.base.FControl.push.call(o, n);
	if(RClass.isClass(o, FTreeNode)){
		o.child = true;
		o.loaded = true;
	}
}
function FTreeNode_innerDump(dump){
	var o = this;
	dump.append(RClass.typeOf(o));
	dump.append('[level=',  o.level);
	if(o.type){
		dump.append(' type=',  o.type.name);
	}
	dump.append(', icon=',  o.icon);
	dump.append(', caption=', o.label);
	dump.append(', child=', o.child);
	dump.append(']');
}
function FTreeNode_remove(){
	var o = this;
	if(o.builded){
		var t = o.tree;
		if(o.components){
			o.removeChildren();
		}
		t.hTable.deleteRow(o.hPanel.rowIndex);
		o.removed = true;
	}
}
function FTreeNode_removeChildren(){
	var cs = this.components;
	if(cs){
		for(var n=0; n<cs.count; n++){
			var c = cs.value(n);
			if(c){
				c.remove();
			}
		}
		cs.reset();
	}
}
function FTreeNode_findByUuid(u){
	var o = this;
	if(o.uuid == u){
		return o;
	}
	var cs = o.components;
	if(cs){
		for(var n=0; n<cs.count; n++){
			var c = cs.value(n);
			if(c){
				if(c.uuid == u){
					return c;
				}
				if(c.components){
					var f = c.findByUuid(u);
					if(f){
						return f;
					}
				}
			}
		}
	}
	return null;
}
function FTreeNodeType(o){
	o = RClass.inherits(this, o, FComponent);
	o.typeName   = RClass.register(o, new TPtyStr('typeName'));
	o.icon       = RClass.register(o, new TPtyStr('icon'));
	o.service    = RClass.register(o, new TPtyStr('service'));
	o.action     = RClass.register(o, new TPtyStr('action'));
	o.config     = RClass.register(o, new TPtyCfg('config'));
	o.get        = FTreeNodeType_get;
	o.set        = FTreeNodeType_set;
	o.innerDump  = FTreeNodeType_innerDump;
	return o;
}
function FTreeNodeType_get(n){
	var o = this;
	return o.config ? o.config.get(n) : null;
}
function FTreeNodeType_set(n, v){
	var o = this;
	if(o.config){
		o.config.set(n, v)
	}
}
function FTreeNodeType_innerDump(dump){
	var o = this;
	dump.append(RClass.typeOf(o));
	dump.append('[icon=',  o.icon);
	dump.append(', service=', o.service);
	dump.append(', action=', o.action);
	dump.append(']');
}
function FTreeView(o){
	o = RClass.inherits(this, o, FContainer);
	o.styleNode       = RClass.register(o, new TStyle('Node'));
	o.styleCell       = RClass.register(o, new TStyle('Cell'));
	o.styleNodeSelect = RClass.register(o, new TStyle('NodeSelect'));
	o.styleCellSelect = RClass.register(o, new TStyle('CellSelect'));
	o.styleNodeHover  = RClass.register(o, new TStyle('NodeHover'));
	o.styleCellHover  = RClass.register(o, new TStyle('CellHover'));
	o.focusNode       = null;
	o.isLoading       = false;
	o.indent          = 16;
	o.type            = null;
	o.types           = new TMap();
	o.columns         = new TMap();
	o.levels          = new TMap();
	o.rootNode        = null;
	o.workNode        = null;
	o.allNodes        = new TList();
	o.service         = null;
	o.loadingNode     = null;
	o.attributes      = new TAttrs();
	o.iconNode        = 'ctl.tv-node';
	o.iconPlus        = 'ctl.tv-plus';
	o.iconMinus       = 'ctl.tv-minus';
	o.iconLoading     = 'ctl.tv-load';
	o.cssNode         = null;
	o.cssCell         = null;
	o.cssNodeSelect   = null;
	o.cssCellSelect   = null;
	o.cssNodeHover    = null;
	o.cssCellHover    = null;
	o.hPanel          = null;
	o.hTable          = null;
	o.hCaption        = null;
	o.lsnsEnter       = new TListeners();
	o.lsnsLeave       = new TListeners();
	o.lsnsLoaded      = new TListeners();
	o.lsnsClick       = new TListeners();
	o.ohNodeEnter     = FTreeView_ohNodeEnter;
	o.ohNodeLeave     = FTreeView_ohNodeLeave;
	o.ohNodeClick     = FTreeView_ohNodeClick;
	o.oeBuild         = FTreeView_oeBuild;
	o.onBuildPanel    = FTreeView_onBuildPanel;
	o.onLoaded        = FTreeView_onLoaded;
	o.construct       = FTreeView_construct;
	o.loadConfig      = FTreeView_loadConfig;
	o.saveConfig      = FTreeView_saveConfig;
	o.createChild     = FTreeView_createChild;
	o.appendChild     = FTreeView_appendChild;
	o.connect         = FTreeView_connect;
	o.appendNodes     = FTreeView_appendNodes;
	o.loadNode        = FTreeView_loadNode;
	o.loadDocument    = FTreeView_loadDocument;
	o.reload          = FTreeView_reload;
	o.reloadNode      = FTreeView_reloadNode;
	o.filterNode      = FTreeView_filterNode;
	o.removeNode      = FTreeView_removeNode;
	o.clearNodes      = FTreeView_clearNodes;
	o.release         = FTreeView_release;
	o.setFocusNode    = FTreeView_setFocusNode;
	o.push            = FTreeView_push;
	o.findByUuid      = FTreeView_findByUuid;
	o.extendAll       = FTreeView_extendAll;
	return o;
}
function FTreeView_extendAll(n){
   var o = this;
   if(RClass.name(n) == 'FTreeNode'){
       n.extend(true);
   }
   if(n.components){
      var count = n.components.count;
          for(var i = 0; i<count; i++ ){
              var child = n.components.values[i];
              o.extendAll(child);
          }
   }
}
function FTreeView_ohNodeEnter(){
	var node = this.link;
	if(RClass.isClass(node, FTreeNode)){
		var tree = node.tree;
		if(!tree.focusNode || (tree.focusNode && (tree.focusNode != node))){
			node.setStatus(ENodeStatus.Hover);
			tree.lsnsEnter.process(tree, node);
		}
	}
}
function FTreeView_ohNodeLeave(){
	var node = this.link;
	if(RClass.isClass(node, FTreeNode)){
		var tree = node.tree;
		if(!tree.focusNode || (tree.focusNode && (tree.focusNode != node))){
			node.setStatus(ENodeStatus.Normal);
			tree.lsnsLeave.process(tree, node);
		}
	}
}
function FTreeView_ohNodeClick(){
	var node = this.link;
	if(RClass.isClass(node, FTreeNode)){
		node.onClick();
	}
}
function FTreeView_oeBuild(event){
	var o = this;
	var r = o.base.FContainer.oeBuild.call(o, event);
	if(event.isBefore()){
		var h = o.hTable = RBuilder.appendTable(this.hPanel);
		h.width = '100%';
		h.border = 0;
		h.cellSpacing = 0;
		h.cellPadding = 0;
		var hHead = RBuilder.append(o.hTable, 'THEAD');
		o.hCaption = RBuilder.append(hHead, 'TR');
		var node = this.loadingNode = RClass.create(FTreeNode);
		node.tree = this;
		node.label = 'Loading ...';
		node.icon = this.iconLoading;
		node.build();
		node.hide();
	}else if(event.isAfter()){
		o.rootNode.extend(true);
	}
	return r;
}
function FTreeView_onBuildPanel(){
	this.hPanel = RBuilder.create(null, 'DIV');
}
function FTreeView_onLoaded(e){
	var o = this;
	var doc = e.document;
	if(doc){
		var node = RObj.nvl(o.workNode, o.focusNode, o.rootNode);
		o.loadDocument(doc);
		o.loadingNode.hide();
		o.isLoading = false;
		o.lsnsLoaded.process(o, node);
	}
}
function FTreeView_construct(){
	var o = this;
	o.base.FContainer.construct.call(o);
	o.cssNode = RStr.nvl(o.cssNode, o.style('Node'));
	o.cssCell = RStr.nvl(o.cssCell, o.style('Cell'));
	o.cssNodeSelect = RStr.nvl(o.cssNodeSelect, o.style('NodeSelect'));
	o.cssCellSelect = RStr.nvl(o.cssCellSelect, o.style('CellSelect'));
	o.cssNodeHover = RStr.nvl(o.cssNodeHover, o.style('NodeHover'));
	o.cssCellHover = RStr.nvl(o.cssCellHover, o.style('CellHover'));
	o.type = RClass.create(FTreeNodeType);
	o.rootNode = RClass.create(FTreeNode);
	o.rootNode.tree = o;
}
function FTreeView_loadConfig(config){
	var o = this;
	o.base.FContainer.loadConfig.call(o, config);
	o.service = config.get('service');
}
function FTreeView_saveConfig(config){
	var o = this;
	o.base.FContainer.saveConfig.call(o, config);
	config.set('service', o.service);
}
function FTreeView_createChild(config){
	var o = this;
	var r = null;
	if(config.isName('Column') || config.isName('TreeColumn')){
		r = RClass.create(FTreeColumn);
	}else if(config.isName('Level') || config.isName('TreeLevel')){
		r = RClass.create(FTreeLevel);
	}else if(config.isName('Type') || config.isName('TreeNodeType')){
		r = RClass.create(FTreeNodeType);
	}else if(config.isName('Node') || config.isName('TreeNode')){
		r = RClass.create(FTreeNode);
	}else{
		RMsg.fatal(o, null, 'Unknown child type (config={0})', config.xml());
	}
	r.tree = o;
	return r;
}
function FTreeView_appendChild(child){
	var o = this;
	if(RClass.isClass(child, FTreeColumn)){
		o.hCaption.appendChild(child.hPanel);
	}
}
function FTreeView_connect(service, attrs){
	var o = this;
	var svc = RService.parse(RStr.nvl(service, this.service));
	if(!svc){
		return alert('Unknown service');
	}
	attrs = RObj.nvl(attrs, o.attributes);
	var doc = new TXmlDoc();
	var root = doc.root();
	root.set('action', svc.action);
	RConsole.find(FEnvConsole).build(root);
	if(!attrs.isEmpty()){
		if(RClass.isClass(attrs, TNode)){
			root.push(attrs);
		}if(RClass.isClass(attrs, TAttrs)){
			root.create('Attributes').attrs = attrs;
		}else{
			root.create('Attributes').value = attrs;
		}
	}
	var event = new TEvent(o, EXmlEvent.Send, o.onLoaded);
	event.url = svc.url;
	event.document = doc;
	RConsole.find(FXmlConsole).process(event);
}
function FTreeView_appendNodes(parent, config){
	parent = RObj.nvl(parent, this.workNode, this.rootNode);
	if(config && config.nodes){
		var count = config.nodes.count;
		if(count > 0){
			parent.child = true;
			parent.loaded = true;
			for(var n=0; n<count; n++){
				var nc = config.nodes.get(n);
				if(nc && (nc.isName('Node') || nc.isName('TreeNode'))){
					var tn = RClass.create(FTreeNode);
					tn.parent = parent;
					tn.tree = this;
					tn.loadConfig(nc);
					if(nc.nodes){
						this.appendNodes(tn, nc);
					}
					parent.push(tn);
				}
			}
		}
	}
}
function FTreeView_loadNode(node, refresh){
	var o = this;
	o.isLoading = true;
	o.workNode = node;
	var type = RObj.nvl(node.type, this.type);
	var svc = RService.parse(RStr.nvl(type.service, this.service));
	if(!svc){
		return alert('Unknown service');
	}
	var doc = new TXmlDoc();
	var root = doc.root();
	root.set('type', type.name);
	root.set('action', RStr.nvl(type.action, svc.action));
	var xtree = root.create('Tree', o.attributes);
	var find = node;
	var xnode = root;
	while(find != this.rootNode){
		var xnode = xnode.create('Node');
		find.saveConfig(xnode);
		find = find.parent;
	}
	var ln = this.loadingNode;
	if(node != o.rootNode){
		var mi = (ln.hPanel.rowIndex > node.hPanel.rowIndex) ? node.hPanel.rowIndex+1 : node.hPanel.rowIndex;
		this.hTable.moveRow(ln.hPanel.rowIndex, mi);
	}
	ln.hImage.style.marginLeft = this.indent * node.level;
	ln.show();
   var event = new TEvent(o, EXmlEvent.Send, o.onLoaded);
	event.url = svc.url;
   event.document = doc;
   RConsole.find(FXmlConsole).process(event);
}
function FTreeView_loadDocument(doc){
	var o = this;
	if(doc){
		var r = doc.root();
		var n = RObj.nvl(o.workNode, o.focusNode, o.rootNode);
		if(r && r.nodes){
			o.appendNodes(n, r);
		}
		n.extend(true);
	}
}
function FTreeView_reload(){
	var o = this;
	o.rootNode.removeChildren();
	o.workNode = o.rootNode;
	o.connect();
}
function FTreeView_reloadNode(node, parent){
	var o = this;
	node = RObj.nvl(node, this.focusNode, this.rootNode);
	if(node == o.rootNode){
		return o.reload();
	}
	if(parent && node && node.parent){
		node = node.parent;
	}
	if(node){
		node.removeChildren();
		o.loadNode(node);
	}
}
function FTreeView_filterNode(sCaption, sAttr){
   var oNode = null;
   var nCount = this.allNodes.length;
   var sNodeCaption = null;
   var sNodeAttr = null;
   if(!sCaption){
      for(var n=0; n<nCount; n++){
         oNode = this.allNodes[n];
         if(!oNode.isDelete){
            oNode.show(true);
         }
      }
   }else{
      sCaption = sCaption.toLowerCase();
      var arAttr = null;
      var nAttrCount = 0;
      if(sAttr){
         sAttr = sAttr.toLowerCase();
         arAttr = sAttr.split("|");
         nAttrCount = arAttr.length;
      }
      for(var n=0; n<nCount; n++){
         oNode = this.allNodes[n];
         if(!oNode.isDelete){
            sNodeCaption = oNode.label.toLowerCase();
            if(arAttr){
               sNodeAttr = oNode.linkAttr.toLowerCase();
               for(var s=0; s<nAttrCount; s++){
                  if(sNodeAttr.indexOf(arAttr[s]) != -1){
                     oNode.show((sNodeCaption.indexOf(sCaption) != -1));
                     break;
                  }
               }
            }else{
               oNode.show((sNodeCaption.indexOf(sCaption) != -1));
            }
         }
      }
   }
   return true;
}
function FTreeView_removeNode(oNode){
   if(oNode){
      var nodes = new Array();
      var oLoopNode = null;
      var nCount = this.allNodes.length;
      for(var n=0; n<nCount; n++){
         oLoopNode = this.allNodes[n];
         if(oLoopNode != oNode){
            nodes[nodes.length] = oLoopNode;
         }
      }
      this.allNodes = nodes;
      var oParent = oNode.parent;
      if(oParent){
         nodes = new Array();
         nCount = oParent.nodes.length;
         for(var n=0; n<nCount; n++){
            oLoopNode = oParent.nodes[n];
            if(oLoopNode != oNode){
               nodes[nodes.length] = oLoopNode;
            }
         }
         oParent.nodes = nodes;
         oNode.parent.childrenHTML.removeChild(oNode.ownerHTML);
      }
      if(oParent.nodes.length == 0){
         oParent.imageHTML.src = this.imgEmpty;
      }
      return true;
   }
   return false;
}
function FTreeView_clearNodes(node){
	if(node){
		node.removeChildren();
	}
	return true;
	var nodes = new Array();
	var oLoopNode = null;
	var nCount = this.allNodes.length;
	for(var n=0; n<nCount; n++){
		oLoopNode = this.allNodes[n];
		if(oLoopNode.parent != oNode){
			nodes[nodes.length] = oLoopNode;
		}else{
		oNode.childrenHTML.removeChild(oLoopNode.ownerHTML);
		}
	}
	oNode.imageHTML.src = this.imgEmpty ;
	this.allNodes = nodes;
	return true;
}
function FTreeView_release(){
	var nodes = this.allNodes;
	for(var n=0; n<nodes.length; n++){
		var node = nodes[n];
		node.release();
	}
	this.allNodes = null;
	this.allNodesUuid = null;
	this.allNodesProperty = null;
	this.allNodesPropertyExtend = null;
	this.nodes = null;
	return true;
}
function FTreeView_push(o){
	this.base.FContainer.push.call(this, o);
	if(RClass.isClass(o, FTreeColumn)){
		this.columns.push(o);
	}else if(RClass.isClass(o, FTreeLevel)){
		this.levels.set(o.id.toString(), o);
	}else if(RClass.isClass(o, FTreeNodeType)){
		this.types.set(o.typeName, o);
	}else if(RClass.isClass(o, FTreeNode)){
		this.rootNode.push(o);
	}
}
function FTreeView_setFocusNode(node){
	if(node){
		if(this.focusNode){
			this.focusNode.setStatus(ENodeStatus.Normal)
		}
		node.setStatus(ENodeStatus.Select);
		this.focusNode = node;
	}
}
function FTreeView_findByUuid(u){
	var o = this;
	return o.rootNode ? o.rootNode.findByUuid(u) : null;
}
function FHintWindow(o){
	o = RClass.inherits(this, o, FContainer);
	o.hPanel       = null;
	o.hForm        = null;
	o.hTitleRow    = null;
	o.hTitleTd     = null;
	o.hBoxRow      = null;
	o.hBoxTd       = null;
	o.linkObj      = null;
	o.build        = FHintWindow_build;
	o.linkControl  = FHintWindow_linkControl;
	o.show         = FHintWindow_show;
	o.hide         = FHintWindow_hide;
	o.setPosition  = FHintWindow_setPosition;
	return o;
}
function FHintWindow_build(){
   var o = this;
   this.hPanel = RBuilder.newDiv(null, o.style('Panel'));
   var f = o.hForm  = RBuilder.appendTable(o.hPanel,o.style('Form'));
   var tRow = o.hTitleRow = f.insertRow();
   var tTd  = o.hTitleTd  = tRow.insertCell();
   tTd.height = 200;
   tTd.className = o.style('Title');
   var bRow = o.hBoxRow   = f.insertRow();
   var bTd  = o.hBoxTd = bRow.insertCell();
   bTd.className = o.style('Box');
}
function FHintWindow_linkControl(c){
   var o = this;
   this.hide();
   o.linkObj = c;
   o.setPosition(100,200);
   if(c.HotKey)
      o.hTitleTd.innerText(c.HotKey);
   o.hBoxTd.innerText = c.hint;
}
function FHintWindow_show(){
   var o = this;
   o.hPanel.style.display = 'block';
}
function FHintWindow_hide(){
   var o = this;
   o.hPanel.style.display = 'none';
}
function FHintWindow_setPosition(left,top){
   var o = this;
   o.hPanel.style.top  = left;
   o.hPanel.style.left = top;
}
function FListWindow(o){
   o = RClass.inherits(this, o, FWindow);
	o.table          = null;
	o.lovControl     = null;
   o.hForm          = null;
   o.hMessages      = null;
   o.oeBuild        = FListWindow_oeBuild;
	o.onBuildButtons = FListWindow_onBuildButtons;
   o.onSelect       = FListWindow_onSelect;
   o.onClose        = FListWindow_onClose;
   o.onSearch       = FListWindow_onSearch;
   o.linkLovControl = FListWindow_linkLovControl;
   o.show           = FListWindow_show;
   o.hide           = FListWindow_hide;
   return o;
}
function  FListWindow_onSearch(){
	var o = this;
	var lov_searchBox = RControl.create(FSearchWindow);
	lov_searchBox.linkDsControl(o.table);
	lov_searchBox.show();
}
function FListWindow_oeBuild(event){
	var o = this;
	o.base.FWindow.oeBuild.call(o, event);
	if(event.isBefore()){
		o.setIcon('tool.search');
		o.setCaption('List Of View');
		var hTab = RBuilder.appendTable(o.hPanel);
		hTab.width = '100%';
		hTab.height = '100%';
		hRow = hTab.insertRow();
		var h = o.hTablePanel = hRow.insertCell();
		h.className = o.style('TablePanel');
		var h = o.hButtonPanel = hRow.insertCell();
		h.className = o.style('ButtonPanel');
		o.onBuildButtons();
		RConsole.find(FKeyConsole).register(EKey.Esc, new TListener(o, o.onClose));
	}
}
function FListWindow_onBuildButtons(){
	var o = this;
	var hBtnTab = RBuilder.appendTable(o.hButtonPanel, null, 0, 0, 6);
	var hRow = hBtnTab.insertRow();
	var hCel = hRow.insertCell();
	var b = o.btnSearch = RClass.create(FButton);
	b.icon = 'tool.search';
	b.label = RContext.get(FToolButton, 'search');
	b.width = '100%';
	b.addClickListener(o, o.onSearch);
	b.build(hBtnTab.insertRow().insertCell());
	var b = o.btnClose = RClass.create(FButton);
	b.icon = 'tool.exit';
	b.label = RContext.get(FToolButton, 'close');
	b.width = '100%';
	b.addClickListener(o, o.onClose);
	b.build(hBtnTab.insertRow().insertCell());
	var hRow = hBtnTab.insertRow();
	var hCel = hRow.insertCell();
	hCel.innerHTML = '&nbsp;';
}
function FListWindow_onSelect(row){
	var o = this;
	var lov = o.lovControl;
	var pack = RPack.split(lov.lovFields, ',', '=');
	var ds = lov.topControl(MDataset);
	if(RClass.isClass(ds, FForm)){
		for(var n=0; n<pack.count; n++){
			var ctl = ds.controls.get(pack.name(n));
			if(RClass.isClass(ctl, MEditable)){
				var col = o.table.columns.get(pack.value(n));
				if(col){
					var value = row.get(col.dataName);
					ctl.setValue(value);
					ds.dsSet(ctl.dataName, value);
				}
			}
		}
	}
	if(RClass.isClass(lov, MFocus)){
		RConsole.find(FFocusConsole).focusClass(MDataset, ds);
		RConsole.find(FEventConsole).add(lov, lov.focus);
	}
	o.hide();
}
function FListWindow_onClose(){
   this.hide();
}
function FListWindow_buildField(c){
   var o = this;
   var hCell = o.hFieldsTab.insertRow().insertCell();
   hCell.innerText = c.label;
   o.fieldsPanel = RControl.create(FPanel);
   o.fieldsPanel.build();
   o.fieldsPanel.setPanel(hCel);
}
function FListWindow_linkLovControl(lov){
	var o = this;
	o.lovControl = lov;
	var config = RConsole.find(FFormConsole).findLov(lov);
	var xtable = config.find('WebTable');
	var xds = config.find('Dataset');
	o.table = RControl.fromNode(xtable, o.hTablePanel);
	o.table.isLov = true;
	o.table.lsnsRowDblClick.push(new TListener(o, o.onSelect));
	o.table.loadDataset(xds);
}
function FListWindow_show(){
   var o = this;
   o.base.FWindow.show.call(o);
   RWindow.setEnable(false);
   RWindow.moveCenter(o.hBorder);
   o.setAction(EAction.Update);
   o.focus();
}
function FListWindow_hide(){
   var o = this;
   o.base.FWindow.hide.call(o);
   RWindow.setEnable(true);
}
function FMessageBox(o){
	o = RClass.inherits(this, o, FContainer);
	o.items          = new TList();
	o.dataEmpty      = true;
	o.type           = null;
	o.hForm          = null;
	o.hMessages      = null;
	o.ohCloseClick   = FMessageBox_ohCloseClick;
	o.ohOkClick      = FMessageBox_ohOkClick;
	o.ohCancelClick  = FMessageBox_ohCancelClick;
	o.ohCopyClick    = FMessageBox_ohCopyClick;
	o.oeBuild        = FMessageBox_oeBuild;
	o.onBuildPanel   = FMessageBox_onBuildPanel;
	o.onBuildMessage = FMessageBox_onBuildMessage;
	o.onBuildButton  = FMessageBox_onBuildButton;
	o.loadConfig     = FMessageBox_loadConfig;
	o.saveConfig     = FMessageBox_saveConfig;
	o.loadMessages   = FMessageBox_loadMessages;
	return o;
}
function FMessageBox_ohCloseClick(){
	RConsole.find(FMessageConsole).closeMessage();
}
function FMessageBox_ohOkClick(){
}
function FMessageBox_ohCancelClick(){
}
function FMessageBox_ohCopyClick(){
}
function FMessageBox_oeBuild(event){
	var o = this;
	o.base.FContainer.oeBuild.call(o, event);
	var hTab = RBuilder.appendTable(this.hPanel);
	hTab.width = '100%';
	hTab.height = '100%';
	var hRow = hTab.insertRow();
	o.MessagePanel = hRow.insertCell();
	o.onBuildMessage();
	var h = o.ButtonPanel = hRow.insertCell();
	h.className = o.style('ButtonPanel');
	o.onBuildButton();
	return EEventStatus.Stop;
}
function FMessageBox_onBuildPanel(){
	this.hPanel = RBuilder.create(null, 'DIV');
}
function FMessageBox_onBuildMessage(){
	var o = this;
	var hTab = o.hForm = RBuilder.appendTable(o.MessagePanel);
	hTab.cellPadding = 2;
	hTab.className = this.style('Panel');
	var hRow = hTab.insertRow();
	var hCel = hRow.insertCell();
	hCel.className = this.style('Title');
	hCel.innerText = 'Message:';
	var hRow = hTab.insertRow();
	var hCel = hRow.insertCell();
	hCel.className = this.style('Message');
	o.hMessages = RBuilder.appendTable(hCel);
	o.hMessages.width = '100%';
	var hRow = hTab.insertRow();
	var hCel = hRow.insertCell();
	hCel.className = this.style('Title');
	hCel.innerText = 'Description:';
	var hRow = hTab.insertRow();
	var hCel = hRow.insertCell();
	hCel.className = this.style('Description');
}
function FMessageBox_onBuildButton(){
	var o = this;
	var hBtnTab = RBuilder.appendTable(o.ButtonPanel);
	var hRow = hBtnTab.insertRow();
	var hCel = hRow.insertCell();
	var hRow = hBtnTab.insertRow();
	var hCel = hRow.insertCell();
	hCel.align = 'center';
	var h = o.hOk = RBuilder.append(hCel, 'DIV', o.style('Button'));
	h.innerText = 'Close';
	h.onclick = o.ohCloseClick;
	var hRow = hBtnTab.insertRow();
	var hCel = hRow.insertCell();
	hCel.align = 'center';
	var h = o.hCancel = RBuilder.append(hCel, 'DIV', o.style('Button'));
	h.innerText = 'Cancel';
	h.onclick = o.ohCloseClick;
	var hRow = hBtnTab.insertRow();
	var hCel = hRow.insertCell();
	hCel.align = 'center';
	var h = o.hCopy = RBuilder.append(hCel, 'DIV', o.style('Button'));
	h.innerText = 'Copy';
	h.onclick = o.ohCloseClick;
	var hRow = hBtnTab.insertRow();
	var hCel = hRow.insertCell();
	hCel.innerHTML = '&nbsp;';
}
function FMessageBox_loadConfig(config){
	var o = this;
	o.base.FContainer.loadConfig.call(o, config);
	o.dataEmpty = RBool.isTrue(config.get('data_empty'));
	return EStatus.Stop;
}
function FMessageBox_saveConfig(config){
	this.base.FContainer.saveConfig.call(this, config)
	config.set('data_empty', RBool.toString(this.dataEmpty));
}
function FMessageBox_loadMessages(messages){
	var o = this;
	RHtml.clear(o.hMessages);
	if(messages){
		var msgs = messages.items;
		for(var n=0; n<msgs.count; n++){
			var msg = msgs.get(n);
			if(msg){
				var hRow = o.hMessages.insertRow();
				var hCell = hRow.insertCell();
				hCell.className = o.style('MessageIcon');
				RBuilder.appendIcon(hCell, msg.icon());
				var hCell = hRow.insertCell();
				hCell.className = o.style('MessageText');
				RBuilder.appendText(hCell, msg.message);
			}
		}
		o.type = messages.type();
	}
}
function FMessageWindow(o){
	o = RClass.inherits(this, o, FWindow);
	o.type            = null;
	o.isDialog        = true;
	o.titleBlur       = false;
	o.hMessagePanel   = null;
	o.hMessages       = null;
	o.hDescription    = null;
	o.hButtonPanel    = null;
	o.ohMessageClick  = FMessageWindow_ohMessageClick;
	o.oeBuild         = FMessageWindow_oeBuild;
	o.onBuildMessages = FMessageWindow_onBuildMessages;
	o.onBuildButtons  = FMessageWindow_onBuildButtons;
	o.onOk            = FMessageWindow_onOk;
	o.onCancel        = FMessageWindow_onCancel;
	o.onClose         = FMessageWindow_onClose;
	o.loadMessages    = FMessageWindow_loadMessages;
	o.show            = FMessageWindow_show;
	o.hide            = FMessageWindow_hide;
	return o;
}
function FMessageWindow_ohMessageClick(){
	var o = this.link;
	var msg = this.linkMsg;
	if(o && msg){
		o.hDescription.innerText = msg.description;
	}
}
function FMessageWindow_oeBuild(event){
	var o = this;
	o.base.FWindow.oeBuild.call(o, event);
	if(event.isBefore()){
		o.hTitle.className = o.style('Title');
		o.setIcon('sys.msg');
		o.setCaption('Message');
		var hTab = RBuilder.appendTable(o.hPanel);
		hTab.width = '100%';
		hTab.height = '100%';
		var hRow = hTab.insertRow();
		o.hMessagePanel = hRow.insertCell();
		o.onBuildMessages();
		var h = o.hButtonPanel = hRow.insertCell();
		h.className = o.style('ButtonPanel');
		o.onBuildButtons();
		RConsole.find(FKeyConsole).register(EKey.Esc, new TListener(o, o.onClose));
	}
}
function FMessageWindow_onBuildMessages(){
	var o = this;
	var hTab = o.hForm = RBuilder.appendTable(o.hMessagePanel);
	hTab.cellPadding = 2;
	hTab.className = o.style('MsgPanel');
	var hRow = hTab.insertRow();
	var hCel = hRow.insertCell();
	hCel.className = o.style('MsgTitle');
	hCel.innerText = 'Head:';
	var h = hTab.insertRow().insertCell();
	h.className = o.style('Message');
	o.hMessages = RBuilder.appendTable(h);
	o.hMessages.width = '100%';
	var h = hTab.insertRow().insertCell();
	h.className = o.style('MsgTitle');
	h.innerText = 'Description:';
	var h = o.hDescriptionPanel = hTab.insertRow().insertCell();
	h.className = o.style('DescriptionPanel');
	var h = o.hDescription = RBuilder.appendDiv(h, o.style('Description'));
}
function FMessageWindow_onBuildButtons(){
	var o = this;
	var hBtnTab = RBuilder.appendTable(o.hButtonPanel, null, 0, 0, 6);
	var hRow = hBtnTab.insertRow();
	var hCel = hRow.insertCell();
	var b = o.btnSearch = RClass.create(FButton);
	b.icon = 'tool.next';
	b.label = RContext.get(FToolButton, 'ok');
	b.width = '100%';
	b.addClickListener(o, o.onOk);
	b.build(hBtnTab.insertRow().insertCell());
	var b = o.btnClose = RClass.create(FButton);
	b.icon = 'tool.exit';
	b.label = RContext.get(FToolButton, 'close');
	b.width = '100%';
	b.addClickListener(o, o.onClose);
	b.build(hBtnTab.insertRow().insertCell());
	var hRow = hBtnTab.insertRow();
	var hCel = hRow.insertCell();
	hCel.innerHTML = '&nbsp;';
}
function FMessageWindow_onOk(){
	this.hide();
}
function FMessageWindow_onCancel(){
	this.hide();
}
function FMessageWindow_onClose(){
	this.hide();
}
function FMessageWindow_loadMessages(messages){
	var o = this;
	RHtml.clear(o.hMessages);
	var first = true;
	o.hDescription.innerText = '';
	if(messages){
		var msgs = messages.items;
		for(var n=0; n<msgs.count; n++){
			var msg = msgs.get(n);
			if(msg){
				var hRow = o.hMessages.insertRow();
				hRow.link = o;
				hRow.linkMsg = msg;
				hRow.onclick = o.ohMessageClick;
				var hCell = hRow.insertCell();
				hCell.className = o.style('MessageIcon');
				RBuilder.appendIcon(hCell, msg.icon());
				var hCell = hRow.insertCell();
				hCell.className = o.style('MessageText');
				RBuilder.appendText(hCell, msg.message);
				if(first){
					first = false;
					o.hDescription.innerText = msg.description;
				}
			}
		}
		o.type = messages.type();
	}
}
function FMessageWindow_show(){
	var o = this;
	o.base.FWindow.show.call(o);
	RWindow.setEnable(false);
	RWindow.moveCenter(o.hBorder);
	o.setAction(EAction.Update);
	o.focus();
}
function FMessageWindow_hide(){
	var o = this;
	o.base.FWindow.hide.call(o);
	RWindow.setEnable(true);
}
function FPageBar(o){
	o = RClass.inherits(this, o, FContainer);
	o.paddingLeft  = 20;
	o.sheets       = new TMap();
	o.selected     = null;
	o.hTop         = null;
	o.hLine        = null;
	o.hBottom      = null;
	o.hSheets      = null;
	o.oeBuild      = FPageBar_oeBuild;
	o.onBuildPanel = FPageBar_onBuildPanel;
	o.appendChild  = FPageBar_appendChild;
	o.select       = FPageBar_select;
	o.tab          = FPageBar_tab;
	o.push         = FPageBar_push;
	return o;
}
function FPageBar_oeBuild(event){
	var o = this;
	o.base.FContainer.oeBuild.call(o, event);
	if(event.isBefore()){
		o.hPanelBody = RBuilder.append(this.hPanel, 'TBODY');
		h = RBuilder.append(o.hPanelBody, 'TR');
		o.hTitlePanel = RBuilder.append(h, 'TD', o.style('TitlePanel'));
		var h = RBuilder.appendTable(this.hTitlePanel);
		o.hTop = h.insertRow();
		o.hLine = h.insertRow();
		o.hBottom = h.insertRow();
		var h = o.hTop.insertCell();
		RBuilder.appendEmpty(h);
		h.width = o.paddingLeft;
		var h = o.hLine.insertCell();
		var h = RBuilder.append(o.hBottom, 'TD', this.style('Bottom'));
		RBuilder.appendEmpty(h);
	}else if(event.isAfter()){
		RBuilder.append(o.hTop, 'TD', this.style('Top'));
		h = RBuilder.append(o.hLine, 'TD');
		RBuilder.appendEmpty(h);
		RBuilder.append(o.hBottom, 'TD', this.style('Bottom'));
		h = RBuilder.append(o.hSheets, 'TR');
		h = RBuilder.append(h, 'TD');
		h.colSpan = 3;
		h.className = this.style('Bottom');
		RBuilder.appendEmpty(h);
		if(o.sheets.count){
			o.select(o.sheets.value(0));
		}
	}
}
function FPageBar_appendChild(sheet){
	this.hTop.appendChild(sheet.hTopL);
	this.hTop.appendChild(sheet.hTop);
	this.hTop.appendChild(sheet.hTopR);
	this.hLine.appendChild(sheet.hLeft);
	this.hLine.appendChild(sheet.hPanel);
	this.hLine.appendChild(sheet.hRight);
	this.hBottom.appendChild(sheet.hBottomL);
	this.hBottom.appendChild(sheet.hBottom);
	this.hBottom.appendChild(sheet.hBottomR);
	this.hPanelBody.appendChild(sheet.hSheet);
}
function FPageBar_onBuildPanel(){
	this.hPanel = RBuilder.newTable();
}
function FPageBar_select(sheet){
	this.selected = sheet;
	for(var n=0; n<this.sheets.count; n++){
		var o = this.sheets.value(n);
		o.select(sheet == o);
	}
}
function FPageBar_tab(name){
	return this.sheets.get(name);
}
function FPageBar_push(c){
	var o = this;
	o.base.FContainer.push.call(o, c);
	if(RClass.isClass(c, FPageSheet)){
		c.pageBar = o;
		c.index = o.sheets.count;
		o.sheets.set(c.name, c);
	}
}
function FPageControl(o){
	o = RClass.inherits(this, o, FContainer, MHorizontal);
	o.paddingLeft  = 20;
	o.sheets       = new TMap();
	o.selected     = null;
	o.hTop         = null;
	o.hLine        = null;
	o.hBottom      = null;
	o.hSheets      = null;
	o.oeBuild      = FPageControl_oeBuild;
	o.oeAction     = FPageControl_oeAction;
	o.onBuildPanel = FPageControl_onBuildPanel;
	o.appendChild  = FPageControl_appendChild;
	o.select       = FPageControl_select;
	o.sheet        = FPageControl_sheet;
	o.push         = FPageControl_push;
	return o;
}
function FPageControl_oeBuild(event){
	var o = this;
	o.base.FContainer.oeBuild.call(o, event);
	if(event.isBefore()){
		o.hPanelBody = RBuilder.append(this.hPanel, 'TBODY');
		h = RBuilder.append(o.hPanelBody, 'TR');
		o.hTitlePanel = RBuilder.append(h, 'TD', o.style('TitlePanel'));
		var h = RBuilder.appendTable(this.hTitlePanel);
		o.hTop = h.insertRow();
		o.hLine = h.insertRow();
		o.hBottom = h.insertRow();
		var h = o.hTop.insertCell();
		RBuilder.appendEmpty(h);
		h.width = o.paddingLeft;
		var h = o.hLine.insertCell();
		var h = RBuilder.append(o.hBottom, 'TD', this.style('Bottom'));
		RBuilder.appendEmpty(h);
	}else if(event.isAfter()){
		RBuilder.append(o.hTop, 'TD', this.style('Top'));
		h = RBuilder.append(o.hLine, 'TD');
		RBuilder.appendEmpty(h);
		RBuilder.append(o.hBottom, 'TD', this.style('Bottom'));
		h = RBuilder.append(o.hSheets, 'TR');
		h = RBuilder.append(h, 'TD');
		h.colSpan = 3;
		h.className = this.style('Bottom');
		RBuilder.appendEmpty(h);
		if(o.sheets.count){
			for(var n=0; n<o.sheets.count; n++){
				var ps = o.sheets.value(n);
				ps.buildChildren();
				ps.hasBuilded = true;
			}
			o.select(o.sheets.value(0));
		}
	}
}
function FPageControl_oeAction(e){
	var o = this;
	o.base.FContainer.oeAction.call(o, e);
	if(e.isAfter()){
		if(EAction.Update == e.action){
			o.setVisible(true);
		}else{
			o.setVisible(false);
		}
	}
}
function FPageControl_appendChild(sheet){
	this.hTop.appendChild(sheet.hTopL);
	this.hTop.appendChild(sheet.hTop);
	this.hTop.appendChild(sheet.hTopR);
	this.hLine.appendChild(sheet.hLeft);
	this.hLine.appendChild(sheet.hPanel);
	this.hLine.appendChild(sheet.hRight);
	this.hBottom.appendChild(sheet.hBottomL);
	this.hBottom.appendChild(sheet.hBottom);
	this.hBottom.appendChild(sheet.hBottomR);
	this.hPanelBody.appendChild(sheet.hSheet);
}
function FPageControl_onBuildPanel(){
	this.hPanel = RBuilder.newTable();
}
function FPageControl_select(sheet){
	this.selected = sheet;
	for(var n=0; n<this.sheets.count; n++){
		var o = this.sheets.value(n);
		o.select(sheet == o);
	}
}
function FPageControl_sheet(name){
	return this.sheets.get(name);
}
function FPageControl_push(p){
	var o = this;
	o.base.FContainer.push.call(o, p);
	if(RClass.isClass(p, FPageSheet)){
		p.pages = o;
		p.index = o.sheets.count;
		o.sheets.set(p.name, p);
	}
}
function FPageSheet(o){
	o = RClass.inherits(this, o, FPanel);
	o.icon         = RClass.register(o, new TPtyStr('icon', null));
	o.formName     = RClass.register(o, new TPtyStr('formName', null));
	o.formLink     = RClass.register(o, new TPtyStr('formLink', null));
	o.formWhere    = RClass.register(o, new TPtyStr('formWhere', null));
	o.formOrder    = RClass.register(o, new TPtyStr('formOrder', null));
	o.pages        = null;
	o.index        = null;
	o.selected     = false;
	o.hasBuilded   = false;
	o.lsnsSelect   = new TListeners();
	o.hTopL        = null;
	o.hTop         = null;
	o.hTopR        = null;
	o.hLeft        = null;
	o.hButton      = null;
	o.hIcon        = null;
	o.hText        = null;
	o.hBottomL     = null;
	o.hBottom      = null;
	o.hBottomR     = null;
	o.hRight       = null;
	o.oeBuild      = FPageSheet_oeBuild;
	o.onBuildPanel = FPageSheet_onBuildPanel;
	o.onEnter      = FPageSheet_onEnter;
	o.onLeave      = FPageSheet_onLeave;
	o.onMouseDown  = FPageSheet_onMouseDown;
	o.select       = FPageSheet_select;
	o.dump         = FPageSheet_dump;
	return o;
}
function FPageSheet_oeBuild(event){
	var o = this;
	var b = this.pages;
	o.hTopL = RBuilder.create(null, 'TD', b.style('Top'));
	o.hTop  = RBuilder.create(null, 'TD', b.style('Top'));
	o.hTopR = RBuilder.create(null, 'TD', b.style('Top'));
	o.hLeft = RBuilder.create(null, 'TD', b.style('Left'));
	RBuilder.appendEmpty(o.hLeft);
	o.hBottomL = RBuilder.create(null, 'TD', b.style('Bottom'));
	o.hBottom  = RBuilder.create(null, 'TD', b.style('Bottom'));
	o.hBottomR = RBuilder.create(null, 'TD', b.style('Bottom'));
	o.hRight = RBuilder.create(null, 'TD', b.style('Right'));
	RBuilder.appendEmpty(o.hRight);
	o.hSheet = RBuilder.create(null, 'TR');
	o.hSheetPanel = RBuilder.append(o.hSheet, 'TD', b.style('SheetPanel'));
	o.base.FPanel.oeBuild.call(o, event);
	var h = this.hPanel;
	o.hButton = RBuilder.append(this.hPanel, 'DIV', this.style('Button'));
	if(o.icon){
		o.hIcon = RBuilder.appendIcon(o.hButton, this.icon);
	}
	if(o.label){
		o.hText = RBuilder.append(o.hButton, 'SPAN');
		o.hText.innerText = ' ' + this.label;
	}
	o.hSheetPanel.appendChild(this.hContainer);
	return EEventStatus.Stop;
}
function FPageSheet_onBuildPanel(){
	var o = this;
	o.hPanel = RBuilder.create(null, 'TD');
	o.hContainer = RBuilder.create(null, 'DIV');
}
function FPageSheet_onEnter(){
	if(!this.selected){
		this.hButton.className = this.style('Hover');
	}
}
function FPageSheet_onLeave(){
	if(!this.selected){
		this.hButton.className = this.style('Button');
	}
}
function FPageSheet_onMouseDown(){
	this.parent.select(this);
}
function FPageSheet_select(flag){
	var o = this;
	var b = o.pages;
	if(flag && !o.hasBuilded){
		o.buildChildren();
		o.hasBuilded = true;
	}
	var first = (o.index == 0);
	var prior = (b.selected.index-1 == o.index);
	if(o.selected != flag){
		if(flag){
			o.lsnsSelect.process();
		}
		o.selected = flag;
	}
	o.hButton.className = flag ? o.style('Select') : o.style('Button');
	o.hTop.className = flag ? b.style('TopSel') : b.style('Top');
	o.hLeft.className = flag ? b.style('LeftSel') : (first ? b.style('Right') : b.style('Left'));
	o.hBottomL.className = flag ? b.style('BottomSel') : b.style('Bottom');
	o.hBottom.className = flag ? b.style('BottomSel') : b.style('Bottom');
	o.hBottomR.className = flag ? b.style('BottomSel') : b.style('Bottom');
	o.hRight.className = flag ? b.style('RightSel') : (prior ? b.style('RightP') : b.style('Right'));
	o.hSheet.style.display = flag ? 'block' : 'none';
}
function FPageSheet_dump(dump, space){
	dump = RStr.nvlStr(dump);
	dump.append(space, RClass.name(this), ' [');
	dump.append('name=', this.name, ', ');
	dump.append('icon=', this.icon, ', ');
	dump.append('label=', this.label, ', ');
	dump.append('action=', this.action, ']');
	return dump;
}
function FPageTab(o){
	o = RClass.inherits(this, o, FPanel);
	o.icon         = RClass.register(o, new TPtyStr('icon', null));
	o.formName     = RClass.register(o, new TPtyStr('formName', null));
	o.formLink     = RClass.register(o, new TPtyStr('formLink', null));
	o.formWhere    = RClass.register(o, new TPtyStr('formWhere', null));
	o.formOrder    = RClass.register(o, new TPtyStr('formOrder', null));
	o.pageBar      = null;
	o.index        = null;
	o.selected     = false;
	o.hasBuilded   = false;
	o.lsnsSelect   = new TListeners();
	o.hTopL        = null;
	o.hTop         = null;
	o.hTopR        = null;
	o.hLeft        = null;
	o.hButton      = null;
	o.hIcon        = null;
	o.hText        = null;
	o.hBottomL     = null;
	o.hBottom      = null;
	o.hBottomR     = null;
	o.hRight       = null;
	o.oeBuild      = FPageTab_oeBuild;
	o.onBuildPanel = FPageTab_onBuildPanel;
	o.onEnter      = FPageTab_onEnter;
	o.onLeave      = FPageTab_onLeave;
	o.onMouseDown  = FPageTab_onMouseDown;
	o.select       = FPageTab_select;
	o.dump         = FPageTab_dump;
	return o;
}
function FPageTab_oeBuild(event){
	var o = this;
	var b = o.parent;
	o.hTopL = RBuilder.create(null, 'TD', b.style('Top'));
	o.hTop  = RBuilder.create(null, 'TD', b.style('Top'));
	o.hTopR = RBuilder.create(null, 'TD', b.style('Top'));
	o.hLeft = RBuilder.create(null, 'TD', b.style('Left'));
	RBuilder.appendEmpty(o.hLeft);
	o.hBottomL = RBuilder.create(null, 'TD', b.style('Bottom'));
	o.hBottom  = RBuilder.create(null, 'TD', b.style('Bottom'));
	o.hBottomR = RBuilder.create(null, 'TD', b.style('Bottom'));
	o.hRight = RBuilder.create(null, 'TD', b.style('Right'));
	RBuilder.appendEmpty(o.hRight);
	o.hSheet = RBuilder.create(null, 'TR');
	o.hSheetPanel = RBuilder.append(o.hSheet, 'TD', b.style('SheetPanel'));
	o.base.FPanel.oeBuild.call(o, event);
	var h = this.hPanel;
	o.hButton = RBuilder.append(this.hPanel, 'DIV', this.style('Button'));
	if(o.icon){
		o.hIcon = RBuilder.appendIcon(o.hButton, this.icon);
	}
	if(o.label){
		o.hText = RBuilder.append(o.hButton, 'SPAN');
		o.hText.innerText = ' ' + this.label;
	}
	o.hSheetPanel.appendChild(this.hContainer);
	return EEventStatus.Stop;
}
function FPageTab_onBuildPanel(){
	var o = this;
	o.hPanel = RBuilder.create(null, 'TD');
	o.hContainer = RBuilder.create(null, 'DIV');
}
function FPageTab_onEnter(){
	if(!this.selected){
		this.hButton.className = this.style('Hover');
	}
}
function FPageTab_onLeave(){
	if(!this.selected){
		this.hButton.className = this.style('Button');
	}
}
function FPageTab_onMouseDown(){
	this.parent.select(this);
}
function FPageTab_select(flag){
	var o = this;
	var b = o.pages;
	if(flag && !o.hasBuilded){
		o.buildChildren();
		o.hasBuilded = true;
	}
	var first = (o.index == 0);
	var prior = (b.selected.index-1 == o.index);
	if(o.selected != flag){
		if(flag){
			o.lsnsSelect.process();
		}
		o.selected = flag;
	}
	o.hButton.className = flag ? o.style('Select') : o.style('Button');
	o.hTop.className = flag ? b.style('TopSel') : b.style('Top');
	o.hLeft.className = flag ? b.style('LeftSel') : (first ? b.style('Right') : b.style('Left'));
	o.hBottomL.className = flag ? b.style('BottomSel') : b.style('Bottom');
	o.hBottom.className = flag ? b.style('BottomSel') : b.style('Bottom');
	o.hBottomR.className = flag ? b.style('BottomSel') : b.style('Bottom');
	o.hRight.className = flag ? b.style('RightSel') : (prior ? b.style('RightP') : b.style('Right'));
	o.hSheet.style.display = flag ? 'block' : 'none';
}
function FPageTab_dump(dump, space){
	dump = RStr.nvlStr(dump);
	dump.append(space, RClass.name(this), ' [');
	dump.append('name=', this.name, ', ');
	dump.append('icon=', this.icon, ', ');
	dump.append('label=', this.label, ', ');
	dump.append('action=', this.action, ']');
	return dump;
}
function FPop(o){
   o = RClass.inherits(this, o, FContainer, MDisplayAble, MSizeable, MMoveable, MPopBorder);
   o.caption      = RClass.register(o, new TPtyStr('caption'));
   o.isDialog     = false;
   o.type         = null;
   o.oeBuild      = FPop_oeBuild;
   o.oeShow       = FPop_oeShow;
   o.onBuildPanel = FPop_onBuildPanel;
   o.panel        = FPop_panel;
   o.dump         = FPop_dump;
   return o;
}
function FPop_oeBuild(event){
   var o = this;
   o.base.FContainer.oeBuild.call(o, event);
   o.base.MPopBorder.oeBuild.call(o, event);
}
function FPop_oeShow(event){
   var o = this;
   o.base.FContainer.oeShow.call(o, event);
   if(event.isAfter()){
      o.hBorder.style.zIndex = RLayer.next(ELayer.Window);
   }
}
function FPop_onBuildPanel(){
   var o = this;
   o.base.MPopBorder.onBuildPanel.call(o);
}
function FPop_panel(t){
   var o = this;
   if(EPanel.Display == t || EPanel.Border == t || EPanel.Size == t){
      return o.hBorder;
   }
   else if(EPanel.Move == t){
      return o.hTitle;
   }
   return o.base.FContainer.panel.call(o, t);
}
function FPop_dump(oCtl, sLeft){
   var sDump = '';
   if(!oCtl){
      oCtl = this;
   }
   if(!sLeft){
      sLeft = '   ';
   }
   sDump += oCtl.className + '\n';
   if(oCtl.children){
      var arChildren = oCtl.children;
      for(var n=0; n<arChildren.length; n++){
         sDump += sLeft + this.dump(arChildren[n], sLeft + '   ');
      }
   }
   return sDump;
}
function FPop_pushAllControl(oCtl){
   if(!this.allControls){this.allControls = new Array();}
   this.allControls.push(oCtl);
}
function FPop_control(sName){
   if(this.allControls){
      for(var n=0; n<this.allControls.length; n++){
         if(this.allControls[n].name == sName){
            return this.allControls[n];
         }
      }
   }
   return null;
}
function FPop_restore(){
   this.max(true);
}
function FPop_processResize(){
   if(!SystemManager.runMode){
      var oRect = this.rect()
      this.width = oRect.width();
      this.height = oRect.height();
   }
   this.processEvent(this, IWindowEvent.RESIZE);
}
function FPop_fillAllControl(){
   var oControl = null;
   var nCount = this.controls.size();
   for(var n=0; n<nCount; n++){
      oControl = this.controls.value(n);
      if(oControl.fill){
         oControl.fill();
      }
   }
}
function FPop_refresh(bConfig){
   if(this.loadConfig){this.loadConfig();}
   this.setCaption(this.label);
   this.setWidth(this.width);
   this.setHeight(this.height);
   if(this.allControls){
      for(var n=0; n<this.allControls.length; n++){
         var oCtl = this.allControls[n];
         if(oCtl.refresh){
            if(bConfig && oCtl.reloadConfig){
               oCtl.reloadConfig();
            }
            oCtl.refresh();
         }
      }
   }
}
function FPop_initialize(){
   if(this.allControls){
      for(var n=0; n<this.allControls.length; n++){
         var oCtl = this.allControls[n];
         if(oCtl.initialize){oCtl.initialize();}
         if(oCtl.initializeControl){oCtl.initializeControl();}
      }
   }
}
function FPop_release(){
   if(this.allControls){
      for(var n=0; n<this.allControls.length; n++){
         var oCtl = this.allControls[n];
         if(oCtl.releaseControl){oCtl.releaseControl();}
         if(oCtl.release){oCtl.release();}
      }
   }
   this.htmlBorder.removeNode(true);
   DatasetManager.focus(null, true);
   WindowManager.releaseWindow(this);
}
function FPop_stopDropExecute(oSource){
   if(oSource.config && oSource.rect){
      var oRect = oSource.rect();
      oSource.config.setAttribute('left', oRect.left);
      oSource.config.setAttribute('top', oRect.top);
      oSource.config.setAttribute('width', oRect.width());
      oSource.config.setAttribute('height', oRect.height());
   }
   if(this.owner.onStopDrop){
      this.owner.onStopDrop(oSource);
   }
}
function FPop_selectDsExecute(oSource){
   if(oSource && oSource.constructor == FDatasetCtl){
      var bRefresh = (DatasetManager.activeDsCtl != oSource);
      DatasetManager.activeDsCtl = oSource;
      if(bRefresh){
         DatasetManager.refreshToolbar();
      }
   }
}
function FSearchEdit(o){
	o = RClass.inherits(this, o, FEdit, MDisplayAble, MSearch);
	o.searchBox    = null;
	o.oeBuild      = FSearchEdit_oeBuild;
	o.onKeyPress   = FSearchEdit_onKeyPress;
	o.assign       = FSearchEdit_assign;
	o.setEditStyle = FSearchEdit_setEditStyle;
	return o;
}
function FSearchEdit_oeBuild(event){
	var o = this;
	var r = o.base.FEdit.oeBuild.call(o, event);
	o.base.MSearch.oeBuild.call(o, event);
	return r;
}
function FSearchEdit_onKeyPress(e){
	var o = this;
	o.base.FEdit.onKeyPress.call(o, e);
	if(e.keyCode == EKey.Enter){
		o.blur();
		o.searchBox.doSearch();
	}
}
function FSearchEdit_assign(edit, type){
	var o = this;
	o.base.FEdit.assign.call(o, edit, type);
	o.base.MSearch.assign.call(o, edit, type);
}
function FSearchEdit_setEditStyle(style){
	var o = this;
	o.base.FEdit.setEditStyle.call(o, style);
	o.base.MSearch.setEditStyle.call(o, style);
}
function FSearchWindow(o){
	o = RClass.inherits(this, o, FWindow);
	o.type           = null;
	o.hForm          = null;
	o.hMessages      = null;
	o.oeBuild        = FSearchWindow_oeBuild;
	o.onBuildButtons = FSearchWindow_onBuildButtons;
	o.onSearch       = FSearchWindow_onSearch;
	o.onClear        = FSearchWindow_onClear;
	o.onReset        = FSearchWindow_onReset;
	o.onClose        = FSearchWindow_onClose;
	o.buildField     = FSearchWindow_buildField;
	o.linkDsControl  = FSearchWindow_linkDsControl;
	o.show           = FSearchWindow_show;
	o.hide           = FSearchWindow_hide;
	return o;
}
function FSearchWindow_oeBuild(event){
	var o = this;
	o.base.FWindow.oeBuild.call(o, event);
	if(event.isBefore()){
		o.setIcon('tool.search');
		o.setCaption('Search');
		var hTab = RBuilder.appendTable(o.hPanel);
		hTab.width = '100%';
		hTab.height = '100%';
		hRow = hTab.insertRow();
		var h = o.hFieldsPanel = hRow.insertCell();
		h.className = o.style('FieldsPanel');
		var h = o.hButtonPanel = hRow.insertCell();
		h.className = o.style('ButtonPanel');
		o.onBuildButtons();
		RConsole.find(FKeyConsole).register(EKey.Esc, new TListener(o, o.onClose));
	}
}
function FSearchWindow_onBuildButtons(){
	var o = this;
	var hBtnTab = RBuilder.appendTable(o.hButtonPanel, null, 0, 0, 6);
	var hRow = hBtnTab.insertRow();
	var hCel = hRow.insertCell();
	var b = o.btnSearch = RClass.create(FButton);
	b.icon = 'tool.search';
	b.label = RContext.get(FToolButton, 'search');
	b.width = '100%';
	b.addClickListener(o, o.onSearch);
	b.build(hBtnTab.insertRow().insertCell());
	var b = o.btnClose = RClass.create(FButton);
	b.icon = 'tool.exit';
	b.label = RContext.get(FToolButton, 'close');
	b.width = '100%';
	b.addClickListener(o, o.onClose);
	b.build(hBtnTab.insertRow().insertCell());
	var b = o.btnCancel = RClass.create(FButton);
	b.icon = 'tool.delete';
	b.label = RContext.get(FToolButton, 'clear');
	b.width = '100%';
	b.addClickListener(o, o.onClear);
	b.build(hBtnTab.insertRow().insertCell());
	var b = o.btnReset = RClass.create(FButton);
	b.icon = 'tool.refresh';
	b.label = RContext.get(FToolButton, 'reset');
	b.width = '100%';
	b.addClickListener(o, o.onReset);
	b.build(hBtnTab.insertRow().insertCell());
	var hRow = hBtnTab.insertRow();
	var hCel = hRow.insertCell();
	hCel.innerHTML = '&nbsp;';
}
function FSearchWindow_onSearch(){
	var o = this;
	var dc = o.dsControl;
	var cs = o.fieldsPanel.components;
	if(cs){
		var sn = new TNode('Search');
		for(var n=0; n<cs.count; n++){
			cs.value(n).saveSearch(sn);
		}
	}
	dc.dsSearchConfig = sn;
	dc.dsSearch();
	o.hide();
}
function FSearchWindow_onClear(){
	var o = this;
	var cs = o.fieldsPanel.components;
	if(cs){
		for(var n=0; n<cs.count; n++){
			cs.value(n).clearSearch();
		}
	}
}
function FSearchWindow_onReset(){
    alert("reset value!")
	var o = this;
	var cs = o.fieldsPanel.components;
	if(cs){
		for(var n=0; n<cs.count; n++){
			cs.value(n).resetSearch();
		}
	}
}
function FSearchWindow_onClose(){
	this.hide();
}
function FSearchWindow_buildField(c){
	var o = this;
	var hCell = o.hFieldsTab.insertRow().insertCell();
	hCell.innerText = c.label;
	o.fieldsPanel = RControl.create(FPanel);
	o.fieldsPanel.build();
	o.fieldsPanel.setPanel(hCel);
}
function FSearchWindow_linkDsControl(dc){
	var o = this;
	o.dsControl = dc;
	o.fieldsPanel = RControl.create(FPanel);
	var cs = dc.components;
	if(cs){
		for(var n=0; n<cs.count; n++){
			var c = cs.value(n);
			if(c.dispSearch){
				var search = RControl.create(FSearchEdit);
				search.assign(c, EAssign.Property);
				search.searchBox = o;
				o.push(search);
				o.fieldsPanel.push(search);
			}
		}
	}
	o.fieldsPanel.build();
	o.fieldsPanel.setPanel(o.hFieldsPanel);
}
function FSearchWindow_show(){
	var o = this;
	o.base.FWindow.show.call(o);
	RWindow.setEnable(false);
	RWindow.moveCenter(o.hBorder);
	o.setAction(EAction.Update);
	o.focus();
}
function FSearchWindow_hide(){
	var o = this;
	o.base.FWindow.hide.call(o);
	RWindow.setEnable(true);
}
function FSideBar(o){
	if(!o){o=this};
	IClass.inherits(o, FObject, MDocument);
   o.name = null;
   o.buttons = new Array();
   o.selButton = null;
   o.selIndex = null;
   o.service = null;
   o.hBody = null;
   o.hTable = null;
   o.pageIndex = 0;
   o.cssButtonNormal = "SideButton_normal";
   o.cssPageInner = "comPageCtl_pi";
   o.cssPageInnerSelect = "comPageCtl_pis";
   o.cssPageHover = "comPageCtl_ph";
   o.cssPageSelect = "comPageCtl_ps";
   o.colorActive = "#666666";
   o.colorBackGround = "#999999";
   o.colorBorder = "#FFFFFF";
   o.colorBorderShadow = "#BBBBBB";
   o.noneImgHTML = null;
   o.onConstruct = null;
   o.onClick = null;
   o.mclick = FSideBar_mclick;
   o.mover = FSideBar_mover;
   o.mout = FSideBar_mout;
   o.construct = FSideBar_construct;
   o.load = FSideBar_load;
   o.loaded = FSideBar_loaded;
   o.insert = FSideBar_insert;
   o.select = FSideBar_select;
   o.clear = FSideBar_clear;
   o.release = FSideBar_release;
   return o;
}
function FSideBar_construct(){
   if(this.hTable){return;}
   var t = this.createTag('TABLE');
   t.width = "100%";
   t.height = "100%";
   t.border = 0;
   t.cellPadding = 0;
   t.cellSpacing = 0;
   this.hTable = t;
   this.hBody.insertBefore(t);
}
function FSideBar_load(){
   this.construct();
   var cnn = IXml.createConnect(this.service, IXml.buildXml(''));
   cnn.onload = this.loaded;
   cnn.bar = this;
   cnn.send();
}
function FSideBar_loaded(){
   if(this.rootNode && this.rootNode.nodes){
      var ns = this.rootNode.nodes;
      for(var n=0; n<ns.length; n++){
         this.bar.insert(ns[n]);
      }
      this.bar.loadNodeFlag = false;
      this.bar.select(0);
   }
}
function FSideBar_insert(cfg){
   var name = cfg.attr('name');
   var label = cfg.attr('label');
   var icon = cfg.attr('icon');
   var o = new FSideButton();
   o.construct(this, name, label, icon)
   this.buttons[this.buttons.length] = o;
   if(this.onConstruct){
      this.onConstruct(o);
   }
   return o;
}
function FSideBar_select(v){
   var t = typeof(v);
   if(t == 'number'){
      for(var n=0; n<this.buttons.length; n++){
         this.buttons[n].extend(v == n);
      }
      this.selIndex = v;
      this.selButton = this.buttons[v];
   }else if(t == 'object' && v.clsSideButton){
      for(var n=0; n<this.buttons.length; n++){
         if(this.buttons[n] == v){
            this.buttons[n].extend(true);
            this.selIndex = n;
            this.selButton = v;
         }else{
            this.buttons[n].extend(false);
         }
      }
   }
}
function FSideBar_mover(){
   if(this.lnkBar && this.lnkButton){
      this.style.color = '#666666';
   }
}
function FSideBar_mout(){
   if(this.lnkBar && this.lnkButton){
      this.style.color = '#000000';
   }
}
function FSideBar_mclick(){
   if(this.lnkBar && this.lnkButton){
      var bar = this.lnkBar;
      bar.select(this.lnkButton);
      if(bar.onClick){
         bar.onClick(this.lnkButton);
      }
   }
}
function FSideBar_clear(){
   if(this.hFrames){
      this.hFrames.removeAttribute('link_show');
      this.hFrames.removeAttribute('link_width');
   }
   var nCount = this.items.length;
   for(var n=0; n<nCount; n++){
      var oItem = this.items[n];
      if(oItem.hBody){
         oItem.hBody.removeAttribute("link_pgctl");
         oItem.hBody.removeAttribute("link_sheet");
         oItem.hBody.removeAttribute("link_index");
         oItem.hBody.removeAttribute("link_action");
         oItem.hBody.removeAttribute("link_hint");
         oItem.hBody = null;
      }
   }
   if(this.hBody){
      this.hBody.innerHTML = "";
      this.hTable = null;
   }
}
function FSideBar_release(){
   this.items = new Array();
   this.hBody.innerHTML = "";
}
function FSideButton(o){
	if(!o){o=this};
	IClass.inherits(o, FObject);
	o.bar = null;
   o.name = null;
   o.caption = null;
   o.icon = null;
   o.hint = null;
   o.hBody = null;
   o.hCaption = null;
   o.hIcon = null;
   o.hText = null;
   o.hSpaceRow = null;
   o.hSpace = null;
   o.isExtend = false;
   o.construct = FSideButton_construct;
	o.extend = FSideButton_extend;
   return o;
}
function FSideButton_construct(bar, name, caption, icon){
	var o = this;
   o.bar = bar;
   o.name = name;
   o.caption = caption;
   o.icon = icon;
   o.hIcon = bar.createTag("IMG");
   o.hIcon.align = "absmiddle";
   o.hIcon.src = IResource.path(icon);
   o.hText = bar.createTag("SPAN");
   o.hText.innerHTML = "&nbsp;" + caption;
   o.hCaption = bar.createTag("DIV");
   o.hCaption.lnkBar = bar;
   o.hCaption.lnkButton = o;
   o.hCaption.noWrap = true;
   o.hCaption.insertBefore(o.hIcon);
   o.hCaption.insertBefore(o.hText);
   var rt = bar.hTable.insertRow();
   rt.lnkBar = bar;
   rt.lnkButton = this;
   rt.className = bar.cssButtonNormal;
   rt.onclick = bar.mclick;
   rt.onmouseover = bar.mover;
   rt.onmouseout = bar.mout;
   var ct = rt.insertCell();
   ct.insertBefore(o.hCaption);
   o.hSpace = bar.createTag('DIV');
   o.hSpace.className = 'SideButton_panel';
   o.hSpaceRow = bar.hTable.insertRow();
   var sc = o.hSpaceRow.insertCell();
   sc.insertBefore(o.hSpace);
}
function FSideButton_extend(flag){
   if(this.hSpaceRow){
      this.hSpaceRow.style.display = flag ? "block" : "none" ;
      this.isExtend = flag;
   }
}
function FTabBar(o){
	o = RClass.inherits(this, o, FContainer);
	o.left             = 20;
	o.width            = '100%';
	o.sheets           = new TMap();
	o.selected         = null;
	o.hTop             = null;
	o.hLine            = null;
	o.hBottom          = null;
	o.oeBuild          = FTabBar_oeBuild;
	o.onBuildPanel     = FTabBar_onBuildPanel;
	o.appendChild      = FTabBar_appendChild;
	o.addClickListener = FTabBar_addClickListener;
	o.select           = FTabBar_select;
	o.push             = FTabBar_push;
	return o;
}
function FTabBar_oeBuild(event){
	var o = this;
	o.base.FContainer.oeBuild.call(o, event);
	if(event.isBefore()){
		var h = o.hTop.insertCell();
		RBuilder.appendEmpty(h);
		h.width = 20;
		var h = o.hLine.insertCell();
		var h = RBuilder.append(o.hBottom, 'TD', this.style('Bottom'));
		RBuilder.appendEmpty(h);
	}else if(event.isAfter()){
		RBuilder.append(o.hTop, 'TD', this.style('Top'));
		h = RBuilder.append(o.hLine, 'TD');
		RBuilder.appendEmpty(h);
		RBuilder.append(o.hBottom, 'TD', this.style('Bottom'));
		if(o.sheets.count){
			o.select(o.sheets.value(0));
		}
	}
}
function FTabBar_appendChild(sheet){
	this.hTop.appendChild(sheet.hTopL);
	this.hTop.appendChild(sheet.hTop);
	this.hTop.appendChild(sheet.hTopR);
	this.hLine.appendChild(sheet.hLeft);
	this.hLine.appendChild(sheet.hPanel);
	this.hLine.appendChild(sheet.hRight);
	this.hBottom.appendChild(sheet.hBottomL);
	this.hBottom.appendChild(sheet.hBottom);
	this.hBottom.appendChild(sheet.hBottomR);
}
function FTabBar_onBuildPanel(){
	var o = this;
	var h = o.hPanel = RBuilder.append(o.hParent, 'TABLE');
	h.border = 0;
	h.frame = 'box';
	h.cellPadding = 0;
	h.cellSpacing = 0;
	o.hTop = h.insertRow();
	o.hLine = h.insertRow();
	o.hBottom = h.insertRow();
}
function FTabBar_addClickListener(){
	var btn = this.components.get(name);
	if(btn){
		btn.lsnsClick.push(new TListener(this, method));
	}
}
function FTabBar_select(sheet){
	this.selected = sheet;
	for(var n=0; n<this.sheets.count; n++){
		var o = this.sheets.value(n);
		o.select(sheet == o);
	}
}
function FTabBar_selectPage(idx, force){
   this.activeIndex = idx;
   var oPage = null;
   if(!force){
      for(var n=0; n<this.items.length; n++){
         if(this.items[n].name == this.activePageName){
            oPage = this.items[n];
            this.activePageName = oPage.name;
            this.activeIndex = n;
            break;
         }
      }
      if(!oPage){
         oPage = this.items[this.activeIndex];
         this.activePageName = oPage.name;
      }
   }
   if(oPage){
      this.activePage = oPage;
   }else{
      oPage = this.activePage;
   }
   if(oPage){
      this.clear();
      this.refresh();
      oPage = this.items[this.activeIndex];
      this.activePageName = oPage.name;
      if(this.onSheetClick){
         this.onSheetClick(oPage);
      }
   }
   return oPage;
}
function FTabBar_push(o){
	this.base.FContainer.push.call(this, o);
	if(RClass.isClass(o, FTabButton)){
		o.tabBar = this;
		o.index = this.sheets.count;
		this.sheets.set(o.name, o);
	}
}
function FTabButton(o){
	o = RClass.inherits(this, o, FControl, MLsnClick);
	o.icon         = null;
	o.caption      = null;
	o.action       = null;
	o.hint         = null;
	o.pageBar      = null;
	o.index        = null;
	o.selected     = false;
	o.oeBuild      = FTabButton_oeBuild;
	o.onBuildPanel = FTabButton_onBuildPanel;
	o.onEnter      = FTabButton_onEnter;
	o.onLeave      = FTabButton_onLeave;
	o.onMouseDown  = FTabButton_onMouseDown;
	o.onMouseUp    = FTabButton_onMouseUp;
	o.loadConfig   = FTabButton_loadConfig;
	o.saveConfig   = FTabButton_saveConfig;
	o.select       = FTabButton_select;
	o.dump         = FTabButton_dump;
	return o;
}
function FTabButton_oeBuild(event){
	var o = this;
	var b = o.tabBar;
	o.hTopL = RBuilder.create(null, 'TD', b.style('Top'));
	o.hTop  = RBuilder.create(null, 'TD', b.style('Top'));
	o.hTopR = RBuilder.create(null, 'TD', b.style('Top'));
	o.hLeft = RBuilder.create(null, 'TD', b.style('Left'));
	RBuilder.appendEmpty(o.hLeft);
	o.hBottomL = RBuilder.create(null, 'TD', b.style('Bottom'));
	o.hBottom  = RBuilder.create(null, 'TD', b.style('Bottom'));
	o.hBottomR = RBuilder.create(null, 'TD', b.style('Bottom'));
	o.hRight = RBuilder.create(null, 'TD', b.style('Right'));
	RBuilder.appendEmpty(o.hRight);
	o.base.FControl.oeBuild.call(o, event);
	var tb = o.parent;
	var h = this.hPanel;
	o.hButton = RBuilder.append(this.hPanel, 'DIV', this.style('Button'));
	if(o.icon){
		o.hIcon = RBuilder.appendIcon(o.hButton, this.icon);
	}
	if(o.caption){
		o.hText = RBuilder.append(o.hButton, 'SPAN');
		o.hText.innerText = ' ' + this.caption;
	}
	return EEventStatus.Stop;
}
function FTabButton_onBuildPanel(){
	this.hPanel = RBuilder.create(null, 'TD', this.style('Panel'));
}
function FTabButton_onEnter(){
	if(!this.selected){
		this.hButton.className = this.style('Hover');
	}
}
function FTabButton_onLeave(){
	if(!this.selected){
		this.hButton.className = this.style('Button');
	}
}
function FTabButton_onMouseDown(){
	this.parent.select(this);
}
function FTabButton_onMouseUp(){
	this.processClick();
}
function FTabButton_loadConfig(config){
	var o = this;
	o.base.FControl.loadConfig.call(o, config);
	o.icon    = config.get('icon');
	o.caption = config.get('caption');
	o.action  = config.get('action');
	o.hint    = config.get('hint');
}
function FTabButton_saveConfig(config){
	var o = this;
	o.base.FControl.saveConfig.call(o, config);
	config.set('icon',    o.icon);
	config.set('caption', o.caption);
	config.set('action',  o.action);
	config.set('hint',    o.hint);
}
function FTabButton_select(flag){
	var o = this;
	o.selected = flag;
	var b = this.tabBar;
	var first = (o.index == 0);
	var prior = true;
	if(b.selected){
		prior = (b.selected.index-1 == o.index);
	}
	o.hButton.className = flag ? o.style('Select') : o.style('Button');
	o.hTop.className = flag ? b.style('TopSel') : b.style('Top');
	o.hLeft.className = flag ? b.style('LeftSel') : (first ? b.style('Right') : b.style('Left'));
	o.hBottomL.className = flag ? b.style('BottomSel') : b.style('Bottom');
	o.hBottom.className = flag ? b.style('BottomSel') : b.style('Bottom');
	o.hBottomR.className = flag ? b.style('BottomSel') : b.style('Bottom');
	o.hRight.className = flag ? b.style('RightSel') : (prior ? b.style('RightP') : b.style('Right'));
}
function FTabButton_dump(dump, space){
	dump = RStr.nvlStr(dump);
	dump.append(space, RClass.name(this), ' [');
	dump.append('name=', this.name, ', ');
	dump.append('icon=', this.icon, ', ');
	dump.append('label=', this.label, ', ');
	dump.append('action=', this.action, ']');
	return dump;
}
function FWindow(o){
	o = RClass.inherits(this, o, FContainer, MFocus, MDisplayAble, MSizeable, MMoveable, MWinBorder);
	o.caption      = RClass.register(o, new TPtyStr('caption'));
	o.isDialog     = false;
	o.type         = null;
	o.oeBuild      = FWindow_oeBuild;
	o.oeShow       = FWindow_oeShow;
	o.onBuildPanel = FWindow_onBuildPanel;
	o.panel        = FWindow_panel;
	o.dump         = FWindow_dump;
	return o;
}
function FWindow_oeBuild(event){
	var o = this;
	o.base.FContainer.oeBuild.call(o, event);
	o.base.MWinBorder.oeBuild.call(o, event);
}
function FWindow_oeShow(event){
	var o = this;
	o.base.FContainer.oeShow.call(o, event);
	if(event.isAfter()){
		o.hBorder.style.zIndex = RLayer.next(ELayer.Window);
	}
}
function FWindow_onBuildPanel(){
	var o = this;
	o.base.MWinBorder.onBuildPanel.call(o);
}
function FWindow_panel(t){
	var o = this;
	if(EPanel.Display == t || EPanel.Border == t || EPanel.Size == t){
		return o.hBorder;
	}
	else if(EPanel.Move == t){
		return o.hTitle;
	}
	return o.base.FContainer.panel.call(o, t);
}
function FWindow_dump(oCtl, sLeft){
   var sDump = '';
   if(!oCtl){
      oCtl = this;
   }
   if(!sLeft){
      sLeft = '   ';
   }
   sDump += oCtl.className + '\n';
   if(oCtl.children){
      var arChildren = oCtl.children;
      for(var n=0; n<arChildren.length; n++){
         sDump += sLeft + this.dump(arChildren[n], sLeft + '   ');
      }
   }
   return sDump;
}
function FWindow_pushAllControl(oCtl){
   if(!this.allControls){this.allControls = new Array();}
   this.allControls.push(oCtl);
}
function FWindow_control(sName){
   if(this.allControls){
      for(var n=0; n<this.allControls.length; n++){
         if(this.allControls[n].name == sName){
            return this.allControls[n];
         }
      }
   }
   return null;
}
function FWindow_restore(){
   this.max(true);
}
function FWindow_processResize(){
   if(!SystemManager.runMode){
      var oRect = this.rect()
      this.width = oRect.width();
      this.height = oRect.height();
   }
   this.processEvent(this, IWindowEvent.RESIZE);
}
function FWindow_fillAllControl(){
   var oControl = null;
   var nCount = this.controls.size();
   for(var n=0; n<nCount; n++){
      oControl = this.controls.value(n);
      if(oControl.fill){
         oControl.fill();
      }
   }
}
function FWindow_refresh(bConfig){
   if(this.loadConfig){this.loadConfig();}
   this.setCaption(this.label);
   this.setWidth(this.width);
   this.setHeight(this.height);
   if(this.allControls){
      for(var n=0; n<this.allControls.length; n++){
         var oCtl = this.allControls[n];
         if(oCtl.refresh){
            if(bConfig && oCtl.reloadConfig){
               oCtl.reloadConfig();
            }
            oCtl.refresh();
         }
      }
   }
}
function FWindow_initialize(){
   if(this.allControls){
      for(var n=0; n<this.allControls.length; n++){
         var oCtl = this.allControls[n];
         if(oCtl.initialize){oCtl.initialize();}
         if(oCtl.initializeControl){oCtl.initializeControl();}
      }
   }
}
function FWindow_release(){
   if(this.allControls){
      for(var n=0; n<this.allControls.length; n++){
         var oCtl = this.allControls[n];
         if(oCtl.releaseControl){oCtl.releaseControl();}
         if(oCtl.release){oCtl.release();}
      }
   }
   this.htmlBorder.removeNode(true);
   DatasetManager.focus(null, true);
   WindowManager.releaseWindow(this);
}
function FWindow_stopDropExecute(oSource){
   if(oSource.config && oSource.rect){
      var oRect = oSource.rect();
      oSource.config.setAttribute('left', oRect.left);
      oSource.config.setAttribute('top', oRect.top);
      oSource.config.setAttribute('width', oRect.width());
      oSource.config.setAttribute('height', oRect.height());
   }
   if(this.owner.onStopDrop){
      this.owner.onStopDrop(oSource);
   }
}
function FWindow_selectDsExecute(oSource){
   if(oSource && oSource.constructor == FDatasetCtl){
      var bRefresh = (DatasetManager.activeDsCtl != oSource);
      DatasetManager.activeDsCtl = oSource;
      if(bRefresh){
         DatasetManager.refreshToolbar();
      }
   }
}
function MPopBorder(o){
   o = RClass.inherits(this, o);
   o.isDialog        = false;
   o.titleBlur       = true;
   o.hTitlePanel     = null;
   o.hBody           = null;
   o.hBodyPanel      = null;
   o.hBorder         = null;
   o.hBorderTb       = null;
   o.hCaption        = null;
   o.hClose          = null;
   o.hIcon           = null;
   o.hMax            = null;
   o.hMin            = null;
   o.hTitle          = null;
   o.ohMin           = MPopBorder_ohMin;
   o.ohMax           = MPopBorder_ohMax;
   o.ohClose         = MPopBorder_ohClose;
   o.oeBuild         = MPopBorder_oeBuild;
   o.onBuildPanel    = MPopBorder_onBuildPanel;
   o.onSize          = MPopBorder_onSize;
   o.onFocus         = MPopBorder_onFocus;
   o.onBlur          = MPopBorder_onBlur;
   o.onMin           = MPopBorder_onMin;
   o.onMax           = MPopBorder_onMax;
   o.onClose         = MPopBorder_onClose;
   o.min             = MPopBorder_min;
   o.max             = MPopBorder_max;
   o.close           = MPopBorder_close;
   o.buildBorderCell = MPopBorder_buildBorderCell;
   o.startDrag       = MPopBorder_startDrag;
   o.stopDrag        = MPopBorder_stopDrag;
   o.inSizeRange     = MPopBorder_inSizeRange;
   o.setIcon         = MPopBorder_setIcon;
   o.setCaption      = MPopBorder_setCaption;
   o.bringToFront    = MPopBorder_bringToFront;
   o.borderStyle     = MPopBorder_borderStyle;
   return o;
}
function MPopBorder_ohMin(){
   var o = this.link;
   if(RClass.isClass(o, MPopBorder)){
      o.onMin();
   }
}
function MPopBorder_ohMax(){
   var o = this.link;
   if(RClass.isClass(o, MPopBorder)){
      o.onMax();
   }
}
function MPopBorder_ohClose(){
   var o = this.link;
   if(RClass.isClass(o, MPopBorder)){
      o.onClose();
   }
}
function MPopBorder_oeBuild(event){
   var o = this;
   if(event.isBefore()){
      var mc = RConsole.find(FMoveConsole);
      var sc = RConsole.find(FSizeConsole);
      var hTab = o.hBorder = o.hPanel;
      hTab.link = o;
      hTab.className = o.borderStyle('Panel');
      hTab._sizeable = true;
      sc.registerDrag(o, hTab);
      var hRow = hTab.insertRow();
      hRow.height = EMoveSize.InnerBorder;
      o.buildBorderCell(hRow, true);
      o.buildBorderCell(hRow);
      o.buildBorderCell(hRow);
      var hRow = hTab.insertRow();
      hRow.height = 1;
      o.buildBorderCell(hRow);
      o.hTitlePanel = hRow.insertCell();
      o.buildBorderCell(hRow);
      var hRow = hTab.insertRow();
      o.buildBorderCell(hRow);
      o.hBodyPanel = hRow.insertCell();
      o.buildBorderCell(hRow);
      var hRow = hTab.insertRow();
      hRow.height = EMoveSize.InnerBorder;
      o.buildBorderCell(hRow);
      o.buildBorderCell(hRow);
      o.buildBorderCell(hRow, true);
      var h = o.hTitle = RBuilder.appendTable(o.hTitlePanel, o.borderStyle('Title'), 0, 2, 1);
      h.link = o;
      h.ondblclick = o.ohMin;
      mc.registerDrag(o, h);
      var hRow = o.hTitle.insertRow();
      hRow.valign = 'bottom';
      var hIconCell = hRow.insertCell();
      hIconCell.align = 'center';
      hIconCell.width = 20;
      var hObj = o.hIcon = RBuilder.appendIcon(hIconCell, 'ctl.form-table');
      hObj.link = o;
      var h = o.hCaption = hRow.insertCell();
      h.link = o;
      h.className = o.borderStyle('Caption');
      h.innerText = RStr.nvl(o.caption, 'Empty - Window');
      if(!o.isDialog){
         h = o.hMin = hRow.insertCell();
         h.link = o;
         h.className = o.borderStyle('Button');
         h.innerText = '0';
         h.onclick = o.ohMin;
         h = o.hMax = hRow.insertCell();
         h.link = o;
         h.className = o.borderStyle('Button');
         h.innerText = '1';
         h.onclick = o.ohMax;
      }
      h = o.hClose = hRow.insertCell();
      h.link = o;
      h.className = o.borderStyle('Button');
      h.innerText = 'r';
      h.onclick = o.ohClose;
      h = o.hPanel = o.hBody = RBuilder.append(o.hBodyPanel, 'DIV', 'MPopBorder_Body');
      h.link = o;
      h.isInnerBody = true;
   }
}
function MPopBorder_onBuildPanel(){
   this.hPanel = RBuilder.appendTable();
   this.hPanel.style.display = 'none';
}
function MPopBorder_onFocus(){
   var mc = IConsole.find(IMoveConsole);
   this.hBorder.style.zIndex = mc.nextLayer();
   this.hTitle.className = 'MPopBorder_titleHover';
}
function MPopBorder_onBlur(){
   var o = this;
   if(o.titleBlur){
      o.hTitle.className = o.borderStyle('Title');
   }
}
function MPopBorder_onMin(){
   alert('Min');
}
function MPopBorder_onMax(){
   alert('Max');
}
function MPopBorder_onClose(){
   alert('Close');
}
function MPopBorder_inSizeRange(hObj){
   return hObj._sizeable;
}
function MPopBorder_min(){
   this.onMin();
}
function MPopBorder_max(){
   this.onMax();
}
function MPopBorder_close(){
   this.onClose();
}
function MPopBorder_onSize(){
}
function MPopBorder_buildBorderCell(hRow, img, height){
   var o = this;
   var h = hRow.insertCell();
   h.className = o.borderStyle('Inner');
   h._sizeable = true;
   h.width = 1;
   if(img){
      var hImg = RBuilder.appendEmpty(h, EMoveSize.InnerBorder, EMoveSize.InnerBorder);
      hImg._sizeable = true;
   }
   return h;
}
function MPopBorder_startDrag(type){
   var o = this;
   if(EDrag.Move == type){
      o.onBlur();
      o.hBody.className = o.borderStyle('BodyHover');
      o.hBorder.style.zIndex = RLayer.next();
   }
}
function MPopBorder_stopDrag(type){
   if(EDrag.Move == type){
      var o = this;
      o.hBody.className = o.borderStyle('Body');
      o.hBorder.style.zIndex = RLayer.next();
   }
}
function MPopBorder_setIcon(s){
   this.hIcon.src = RRes.iconPath(s);
}
function MPopBorder_setCaption(s){
   this.hCaption.innerText = s;
}
function MPopBorder_bringToFront(){
   this.hBorder.style.zIndex = RLayer.next();
}
function MPopBorder_borderStyle(n){
   return RClass.find(MPopBorder).style(n);
}
function MSearch(o){
	o = RClass.inherits(this, o);
	o.SearchType      = 'search.type';
	o.SearchOrder     = 'search.order'
	o.searchType      = null;
	o.searchOrder     = null;
	o.hType           = null;
	o.hOrder          = null;
	o.oeBuild         = MSearch_oeBuild;
	o.onBuildType     = MSearch_onBuildType;
	o.onBuildOrder    = MSearch_onBuildOrder;
	o.onTypeKeyPress  = MSearch_onTypeKeyPress;
	o.onOrderKeyPress = MSearch_onOrderKeyPress;
	o.assign          = MSearch_assign;
	o.saveSearch      = MSearch_saveSearch;
	o.clearSearch     = MSearch_clearSearch;
	o.resetSearch     = MSearch_resetSearch;
	o.setEditStyle    = MSearch_setEditStyle;
	return o;
}
function MSearch_oeBuild(event){
	var o = this;
	var h = o.hTypePanel = o.hFormRow.insertCell();
	h.className = RCss.style(FSearchWindow, 'TypePanel');
	o.onBuildType();
	h = o.hOrderPanel = o.hFormRow.insertCell();
	h.className = RCss.style(FSearchWindow, 'OrderPanel');
	o.onBuildOrder();
}
function MSearch_onBuildType(){
	var o = this;
	var items = RNaming.get(TItems, o.SearchType);
	if(!items){
		items = new TItems();
		items.create(ESearch.Equals, RContext.get(MSearch, 'search.equals'));
		items.create(ESearch.Begin, RContext.get(MSearch, 'search.begin'));
		items.create(ESearch.End, RContext.get(MSearch, 'search.end'));
		items.create(ESearch.Like, RContext.get(MSearch, 'search.like'));
		RNaming.set(items, o.SearchType);
	}
	var sel = o.searchType = RClass.create(FSelect);
	sel.searchBox = o.searchBox;
	sel.dataValue = 'E';
	sel.labelVisible = false;
	sel.editWidth = 80;
	sel.editRefer = 'search.type'
	sel.items = items;
	sel.onKeyPress = o.onTypeKeyPress;
	sel.build();
	sel.setPanel(o.hTypePanel);
}
function MSearch_onBuildOrder(){
	var o = this;
	var items = RNaming.get(TItems, o.SearchOrder);
	if(!items){
		var cp = RContext.path(MSearch, o.SearchOrder);
		items = new TItems();
		items.create(EOrder.None, RContext.get(MSearch, 'order.none'));
		items.create(EOrder.Asc, RContext.get(MSearch, 'order.asc'));
		items.create(EOrder.Desc, RContext.get(MSearch, 'order.desc'));
		RNaming.set(items, o.SearchOrder);
	}
	var sel = o.searchOrder = RClass.create(FSelect);
	sel.searchBox = o.searchBox;
	sel.dataValue = 'N';
	sel.labelVisible = false;
	sel.editWidth = 40;
	sel.editRefer = 'search.order'
	sel.items = items;
	sel.onKeyPress = o.onOrderKeyPress;
	sel.build();
	sel.setPanel(o.hOrderPanel);
}
function MSearch_onTypeKeyPress(e){
	var o = this;
	if(e.keyCode == EKey.Enter){
		o.blur();
		o.searchBox.doSearch();
	}
}
function MSearch_onOrderKeyPress(e){
	var o = this;
	if(e.keyCode == EKey.Enter){
		o.blur();
		o.searchBox.doSearch();
	}
}
function MSearch_assign(c, t){
	var o = this;
	o.nowrap = false;
	o.width = '100%';
	o.dataValue = null;
	o.labelWidth = 80;
	o.labelPosition = EPosition.Left;
	o.editWidth = 140;
}
function MSearch_saveSearch(c){
	var o = this;
	var st = o.searchType.value();
	var so = o.searchOrder.value();
	if(!RStr.isEmpty(o.dataValue)){
		var v = c.create('Item');
		v.set('name', o.name);
		v.set('data_name', o.dataName);
		v.set('data_value', o.dataValue);
		v.set('search_type', st);
		v.set('search_order', so);
	}
}
function MSearch_clearSearch(){
	var o = this;
	o.setValue();
	o.searchType.setValue('E');
	o.searchOrder.setValue('N');
}
function MSearch_setEditStyle(style){
	var o = this;
	o.searchType.setEditStyle(style);
	o.searchOrder.setEditStyle(style);
}
function MSearch_resetSearch(){
	var o = this;
	o.setValue();
	o.searchType.setValue(o.SearchType);
	o.searchOrder.setValue(o.SearchOrder);
}
function MWinBorder(o){
	o = RClass.inherits(this, o);
	o.onClose         = RClass.register(o, new HClick('onClose'),    MWinBorder_onClose);
	o.isDialog        = false;
	o.titleBlur       = true;
	o.hTitlePanel     = null;
	o.hBody           = null;
	o.hBodyPanel      = null;
	o.hBorder         = null;
	o.hBorderTb       = null;
	o.hCaption        = null;
	o.hClose          = null;
	o.hIcon           = null;
	o.hMax            = null;
	o.hMin            = null;
	o.hTitle          = null;
	o.ohMin           = MWinBorder_ohMin;
	o.ohMax           = MWinBorder_ohMax;
	o.ohClose         = MWinBorder_ohClose;
	o.oeBuild         = MWinBorder_oeBuild;
	o.onBuildPanel    = MWinBorder_onBuildPanel;
	o.onSize          = MWinBorder_onSize;
	o.onFocus         = MWinBorder_onFocus;
	o.onBlur          = MWinBorder_onBlur;
	o.onMin           = MWinBorder_onMin;
	o.onMax           = MWinBorder_onMax;
	o.min             = MWinBorder_min;
	o.max             = MWinBorder_max;
	o.close           = MWinBorder_close;
	o.buildBorderCell = MWinBorder_buildBorderCell;
	o.startDrag       = MWinBorder_startDrag;
	o.stopDrag        = MWinBorder_stopDrag;
	o.inSizeRange     = MWinBorder_inSizeRange;
	o.setIcon         = MWinBorder_setIcon;
	o.setCaption      = MWinBorder_setCaption;
	o.bringToFront    = MWinBorder_bringToFront;
	o.borderStyle     = MWinBorder_borderStyle;
	return o;
}
function MWinBorder_ohMin(){
	var o = this.link;
	if(RClass.isClass(o, MWinBorder)){
		o.onMin();
	}
}
function MWinBorder_ohMax(){
	var o = this.link;
	if(RClass.isClass(o, MWinBorder)){
		o.onMax();
	}
}
function MWinBorder_ohClose(){
	var o = this.link;
	if(RClass.isClass(o, MWinBorder)){
		o.onClose();
	}
}
function MWinBorder_oeBuild(event){
	var o = this;
	if(event.isBefore()){
		var mc = RConsole.find(FMoveConsole);
		var sc = RConsole.find(FSizeConsole);
		var hTab = o.hBorder = o.hPanel;
		hTab.link = o;
		hTab.className = o.borderStyle('Panel');
		hTab._sizeable = true;
		sc.registerDrag(o, hTab);
		var hRow = hTab.insertRow();
		hRow.height = EMoveSize.InnerBorder;
		o.buildBorderCell(hRow, true);
		o.buildBorderCell(hRow);
		o.buildBorderCell(hRow);
		var hRow = hTab.insertRow();
		hRow.height = 1;
		o.buildBorderCell(hRow);
		o.hTitlePanel = hRow.insertCell();
		o.buildBorderCell(hRow);
		var hRow = hTab.insertRow();
		o.buildBorderCell(hRow);
		o.hBodyPanel = hRow.insertCell();
		o.buildBorderCell(hRow);
		var hRow = hTab.insertRow();
		hRow.height = EMoveSize.InnerBorder;
		o.buildBorderCell(hRow);
		o.buildBorderCell(hRow);
		o.buildBorderCell(hRow, true);
		var h = o.hTitle = RBuilder.appendTable(o.hTitlePanel, o.borderStyle('Title'), 0, 2, 1);
		h.link = o;
		h.ondblclick = o.ohMin;
		mc.registerDrag(o, h);
		var hRow = o.hTitle.insertRow();
		hRow.valign = 'bottom';
		var hIconCell = hRow.insertCell();
		hIconCell.align = 'center';
		hIconCell.width = 20;
		var hObj = o.hIcon = RBuilder.appendIcon(hIconCell, 'ctl.form-table');
		hObj.link = o;
		var h = o.hCaption = hRow.insertCell();
		h.link = o;
		h.className = o.borderStyle('Caption');
		h.innerText = RStr.nvl(o.caption, 'Empty - Window');
		if(!o.isDialog){
			h = o.hMin = hRow.insertCell();
			h.link = o;
			h.className = o.borderStyle('Button');
			h.innerText = '0';
			h.onclick = o.ohMin;
			h = o.hMax = hRow.insertCell();
			h.link = o;
			h.className = o.borderStyle('Button');
			h.innerText = '1';
			h.onclick = o.ohMax;
		}
		h = o.hClose = hRow.insertCell();
		h.link = o;
		h.className = o.borderStyle('Button');
		h.innerText = 'r';
		o.attachEvent('onClose',h);
		h = o.hPanel = o.hBody = RBuilder.append(o.hBodyPanel, 'DIV', 'MWinBorder_Body');
		h.link = o;
		h.isInnerBody = true;
	}
}
function MWinBorder_onBuildPanel(){
	this.hPanel = RBuilder.appendTable();
	this.hPanel.style.display = 'none';
}
function MWinBorder_onFocus(){
	var mc = IConsole.find(IMoveConsole);
	this.hBorder.style.zIndex = mc.nextLayer();
	this.hTitle.className = 'MWinBorder_titleHover';
}
function MWinBorder_onBlur(){
	var o = this;
	if(o.titleBlur){
		o.hTitle.className = o.borderStyle('Title');
	}
}
function MWinBorder_onMin(){
	alert('Min');
}
function MWinBorder_onMax(){
	alert('Max');
}
function MWinBorder_onClose(){
   debugger
   alert(MWinBorder_onClose);
}
function MWinBorder_inSizeRange(hObj){
	return hObj._sizeable;
}
function MWinBorder_min(){
	this.onMin();
}
function MWinBorder_max(){
	this.onMax();
}
function MWinBorder_close(){
	this.onClose();
}
function MWinBorder_onSize(){
}
function MWinBorder_buildBorderCell(hRow, img, height){
	var o = this;
	var h = hRow.insertCell();
	h.className = o.borderStyle('Inner');
	h._sizeable = true;
	h.width = 1;
	if(img){
		var hImg = RBuilder.appendEmpty(h, EMoveSize.InnerBorder, EMoveSize.InnerBorder);
		hImg._sizeable = true;
	}
	return h;
}
function MWinBorder_startDrag(type){
	var o = this;
	if(EDrag.Move == type){
		o.onBlur();
		o.hBody.className = o.borderStyle('BodyHover');
		o.hBorder.style.zIndex = RLayer.next();
	}
}
function MWinBorder_stopDrag(type){
	if(EDrag.Move == type){
		var o = this;
		o.hBody.className = o.borderStyle('Body');
		o.hBorder.style.zIndex = RLayer.next();
	}
}
function MWinBorder_setIcon(s){
	this.hIcon.src = RRes.iconPath(s);
}
function MWinBorder_setCaption(s){
	this.hCaption.innerText = s;
}
function MWinBorder_bringToFront(){
	this.hBorder.style.zIndex = RLayer.next();
}
function MWinBorder_borderStyle(n){
	return RClass.find(MWinBorder).style(n);
}
RContext.RMsg_fatal = 'Fatal'
RContext.RMsg_warn  = 'Warning'
RContext.RMsg_info  = 'Infomation'
RContext.MDataset_nochange = 'Data is not change. Update is disabled.'
RContext.MDataset_valid    = 'Data is invalid.'
RContext.MSearch_search_equals = 'Equals'
RContext.MSearch_search_begin  = 'Begin'
RContext.MSearch_search_end    = 'End'
RContext.MSearch_search_like   = 'Like'
RContext.MSearch_order_none    = 'None'
RContext.MSearch_order_asc     = 'ASC'
RContext.MSearch_order_desc    = 'DESC'
RContext.FCalendarEditor_year = 'Year'
RContext.FCalendarEditor_month = 'Month'
RContext.FCalendarEditor_weekdays = 'Sun,1,2,3,4,5,6'
RContext.FToolButton_ok      = 'Ok'
RContext.FToolButton_cancel  = 'Cancel'
RContext.FToolButton_select  = 'Select'
RContext.FToolButton_search  = 'Search'
RContext.FToolButton_close   = 'Close'
RContext.FToolButton_refresh = 'Refresh'
RContext.FToolButton_clear   = 'Clear'
RContext.FToolButton_reset   = 'Reset'
