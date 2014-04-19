function moNvl(a, b){
   return (null != a) ? a : b;
}
function moNvls(v){
   var a = arguments;
   var c = a.length;
   for(var n=0; n<c; n++){
      if(null != a[n]){
         return a[n];
      }
   }
   return null;
}
function $(v){
   if(null != v){
      if(0 == v.indexOf('#')){
         return RWindow.getElement(v.substring(1));
      }
   }
   return null;
};
function EBrowserFace(){
   var o = this;
   o.IE = 0; // Microsoft IE
   o.NC = 1; // Nescape   NC
   return o;
}
var EBrowser = new EBrowserFace();
function ELoggerFace(){
   var o = this;
   o.Debug = 'D';
   o.Info  = 'I';
   o.Warn  = 'W';
   o.Error = 'E';
   o.Fatal = 'F';
   return o;
}
var ELogger = new ELoggerFace();
var ERun = new function(){
   var o = this;
   o.Debug = 1;
   o.Process = 2;
   return o;
}
var ESpace = new function(){
   var o = this;
   o.Local = 1;
   o.Top = 2;
   o.Global = 4;
   return o;
}
function EXmlParseFace(){
   var o = this;
   o.Finish  = 4;
   return o;
}
var EXmlParse = new EXmlParseFace();
function EXmlStatusFace(){
   var o = this;
   o.Begin   = 0; // Uninitialize
   o.Build   = 1; // Initialize
   o.Send    = 2; // Send
   o.Receive = 3; // Receive
   o.Finish  = 4; // Finish
   return o;
}
var EXmlStatus = new EXmlStatusFace();
function TArray(o){
   var o = moNvl(o, this);
   o.length   = 0;
   o.memory   = new Array();
   o.isEmpty  = TArray_isEmpty;
   o.contains = TArray_contains;
   o.indexOf  = TArray_indexOf;
   o.get      = TArray_get;
   o.set      = TArray_set;
   o.push     = TArray_push;
   o.swap     = TArray_swap;
   o.sort     = TArray_sort;
   o.remove   = TArray_remove;
   o.extract  = TArray_extract;
   o.compress = TArray_compress;
   o.clear    = TArray_clear;
   o.dump     = TArray_dump;
   return o;
}
function TArray_isEmpty(){
   return (0 == this.length);
}
function TArray_contains(v){
   return (-1 != this.indexOf(v));
}
function TArray_indexOf(v){
   var c = this.length;
   for(var n=0; n<c; n++){
      if(this.memory[n] == v){
         return n;
      }
   }
   return -1;
}
function TArray_get(n){
   return (n>=0 && n<this.length) ? this.memory[n] : null;
}
function TArray_set(n, v){
   if(n>=0 && n<this.length){
      this.memory[n] = v;
   }
}
function TArray_push(v){
   var n = this.length++;
   this.memory[n] = v;
   return n;
}
function TArray_swap(l, r){
   var o = this;
   if(l>= 0 && l<o.length && r>=0 && r<o.length && l!=r){
      var v = o.memory[l];
      o.memory[l] = this.memory[r];
      o.memory[r] = v;
   }
}
function TArray_sort(){
   this.memory.sort();
}
function TArray_remove(n){
   var v = null;
   if(n>= 0 && n<c){
      var o = this;
      var c = --o.length;
      v = o.memory[n];
      for(var i=n; i<c; i++){
         o.memory[i] = o.memory[i+1];
      }
   }
   return v;
}
function TArray_extract(v){
   if(null != v){
      var o = this;
      var n = 0;
      var c = o.length;
      for(var i=n; i<c; i++){
         if(o.memory[i] != v){
            o.memory[n++] = o.memory[i];
         }
      }
      o.length = n;
   }
   return v;
}
function TArray_compress(){
   var o = this;
   var c = o.length;
   var l = 0;
   for(var n=0; n<c; n++){
      var v = o.memory[n];
      if(null != v){
         o.memory[l++] = v;
      }
   }
   o.length = l;
}
function TArray_clear(){
   this.length = 0;
}
function TArray_dump(d){
   var o = this;
   var c = o.length;
   d = RString.nvlStr(d);
   d.append(RClass.name(o), ': ', c);
   for(var n=0; n<c; n++){
      d.append(' [', o.memory[n], ']');
   }
   return d;
}
function TAttributes(o){
   o = o ? o : this;
   TMap(o);
   o.nvl    = TAttributes_nvl;
   o.join   = TAttributes_join;
   o.split  = TAttributes_split;
   o.pack   = TAttributes_pack;
   o.unpack = TAttributes_unpack;
   return o;
}
function TAttributes_nvl(n, v){
   return RString.nvl(this.get(n), v);
}
function TAttributes_join(n, v){
   var o = this;
   var r = new TString();
   n = RString.nvl(n, '=');
   v = RString.nvl(v, ',');
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
function TAttributes_split(s, n, v){
   var o = this;
   var ss = s.split(v);
   for(var i=0; i<ss.length; i++){
      var ln = RString.trim(ss[i]);
      if(ln.length){
         var sb = ln.split(n);
         if(2 == sb.length){
            o.set(RString.trim(sb[0]), RString.rtrim(sb[1]));
         }else{
            o.set(RString.trim(ln), '');
         }
      }
   }
}
function TAttributes_pack(){
   var o = this;
   var c = o.count;
   var p = new TString();
   for(var n=0; n<c; n++){
      var l = o.names[n].length;
      p.append(l.toString().length, l, o.names[n]);
      if(null != o.values[n]){
         var v = o.values[n] + '';
         l = v.length;
         p.append(l.toString().length, l, v);
      }else{
         p.append('0');
      }
   }
   return p.toString();
}
function TAttributes_unpack(p){
   if(p && p.length){
      var o = this;
      var n = null;
      var v = null;
      var f = 0;
      o.count = 0;
      var pl = p.length;
      while(f < pl){
         var ll = parseInt(p.substr(f++, 1));
         var l = parseInt(p.substr(f, ll));
         n = p.substr(f + ll, l);
         f += ll + l;
         ll = parseInt(p.substr(f++, 1));
         if(0 == ll){
            v = null;
         }else{
            l = parseInt(p.substr(f, ll));
            v = p.substr(f + ll, l);
            f += ll + l;
         }
         o.set(n, v);
      }
   }
}
function TBuilder(){
   var o = this;
   o.hWindow      = window;
   o.hDocument    = window.document;
   o.create       = TBuilder_create;
   o.newText      = TBuilder_newText;
   o.newSpan      = TBuilder_newSpan;
   o.newDiv       = TBuilder_newDiv;
   o.newImage     = TBuilder_newImage;
   o.newIcon      = TBuilder_newIcon;
   o.newTable     = TBuilder_newTable;
   o.newEdit      = TBuilder_newEdit;
   o.newCheck     = TBuilder_newCheck;
   o.newSelect    = TBuilder_newSelect;
   o.append       = TBuilder_append;
   o.appendText   = TBuilder_appendText;
   o.appendSpan   = TBuilder_appendSpan;
   o.appendDiv    = TBuilder_appendDiv;
   o.appendImage  = TBuilder_appendImage;
   o.appendIcon   = TBuilder_appendIcon;
   o.appendEmpty  = TBuilder_appendEmpty;
   o.appendTable  = TBuilder_appendTable;
   o.appendRow    = TBuilder_appendRow;
   o.appendCell   = TBuilder_appendCell;
   o.appendEdit   = TBuilder_appendEdit;
   o.appendCheck  = TBuilder_appendCheck;
   o.appendSelect = TBuilder_appendSelect;
   o.dispose      = TBuilder_dispose;
   return o;
}
function TBuilder_create(t, c){
   var o = this.hDocument.createElement(t);
   if(c){
      o.className = c;
   }
   return o;
}
function TBuilder_newText(t, c){
   var o = this.create('SPAN', c);
   o.innerHTML = t;
   return o;
}
function TBuilder_newSpan(c){
   return this.create('SPAN', c);
}
function TBuilder_newDiv(c){
   return this.create('DIV', c);
}
function TBuilder_newImage(s, c, w, h){
   var o = this.create('IMG', c);
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
function TBuilder_newIcon(s, c, w, h){
   var o = this.create('IMG', RString.nvl(c, 'Tag_Icon'));
   o.align = 'absmiddle';
   if(s){
      o.src = RRes.iconPath(s);
   }
   if(w){
      o.style.width = w;
   }
   if(h){
      o.style.height = h;
   }
   return o;
}
function TBuilder_newTable(c, b, s, d){
   var o = this.create('TABLE', c);
   o.frame = 'box';
   o.border = RInt.nvl(b);
   o.cellSpacing = RInt.nvl(s);
   o.cellPadding = RInt.nvl(d);
   return o;
}
function TBuilder_newEdit(c){
   return this.create("<INPUT type='text'></INPUT>", c);
}
function TBuilder_newCheck(c){
   return this.create("<INPUT type='checkbox'></INPUT>", c);
}
function TBuilder_newSelect(c){
   return this.create('SELECT>', c);
}
function TBuilder_append(p, t, c){
   var o = this.create(t, c);
   p.appendChild(o);
   return o;
}
function TBuilder_appendText(p, t, c){
   var o = this.newText(t, c);
   p.appendChild(o);
   return o;
}
function TBuilder_appendSpan(p, c){
   var o = this.newSpan(c);
   p.appendChild(o);
   return o;
}
function TBuilder_appendDiv(p, c){
   var o = this.newDiv(c);
   p.appendChild(o);
   return o;
}
function TBuilder_appendImage(p, s, c, w, h){
   var o = this.newImage(s, c, w, h);
   p.appendChild(o);
   return o;
}
function TBuilder_appendIcon(p, s, c, w, h){
   var o = this.newIcon(s, c, w, h);
   p.appendChild(o);
   return o;
}
function TBuilder_appendEmpty(p, w, h){
   var o = this.newIcon('n', null, w, h);
   p.appendChild(o);
   return o;
}
function TBuilder_appendTable(p, c, b, s, d){
   var o = this.newTable(c, b, s, d);
   p.appendChild(o);
   return o;
}
function TBuilder_appendRow(p, c, i, w, h){
   var o = i ? p.insertRow(i) : p.insertRow();
   if(c){
      o.className = c;
   }
   if(w){
      o.width = w;
   }
   if(h){
      o.height = h;
   }
   return o;
}
function TBuilder_appendCell(p, c, i, w, h){
   var o = i ? p.insertCell(i) : p.insertCell();
   if(c){
      o.className = c;
   }
   if(w){
      o.width = w;
   }
   if(h){
      o.height = h;
   }
   return o;
}
function TBuilder_appendEdit(p, c){
   var o = this.newEdit(c);
   p.appendChild(o);
   return o;
}
function TBuilder_appendCheck(p, c){
   var o = this.newCheck(c);
   p.appendChild(o);
   return o;
}
function TBuilder_appendSelect(p, c){
   var o = this.newSelect(c);
   p.appendChild(o);
   return o;
}
function TBuilder_dispose(){
   var o = this;
   o.hWindow = null;
   o.hDocument = null;
}
function TClass(n){
   var o = this;
   o._annotations = new Object();
   o._disposed = true;
   o._unused      = null;
   o.name         = n;
   o.parent       = null;
   o.base         = null;
   o.clazz        = null;
   o.instance     = null;
   o.abstract    = false;
   o.styles       = new Array();
   o.instances    = new Array();
   o.register     = TClass_register;
   o.assign       = TClass_assign;
   o.annotations  = TClass_annotations;
   o.annotation   = TClass_annotation;
   o.style        = TClass_style;
   o.newInstance  = TClass_newInstance;
   o.free         = TClass_free;
   o.alloc        = TClass_alloc;
   return o;
}
function TClass_register(v){
   var o = this;
   var a = v.annotation;
   var n = v.name;
   if(!a || !n){
      RMessage.fatal(o, null, "Unknown annotation. (class={1},annotation={2},name={3})", RClass.dump(o), a, n);
   }
   var as = o._annotations[a];
   if(!as){
      as = o._annotations[a] = new Object();
   }
   if(as[n]){
      RMessage.fatal(o, null, "Duplicate annotation. (class={1},annotation={2},name={3})", RClass.dump(o), a, n);
   }
   as[n] = v;
}
function TClass_assign(c){
   var o = this;
   for(var an in c._annotations){
      var ls = o._annotations[an];
      if(!ls){
         ls = o._annotations[an] = new Object();
      }
      var as = c._annotations[an];
      for(var n in as){
         if(ls[n]){
            RMessage.fatal(o, null, "Duplicate annotation. (annotation={1}, {2}.{3}={4}.{5})", an, o.name, n, c.name, n);
         }
         ls[n] = as[n];
      }
   }
}
function TClass_annotations(a){
   var o = this;
   var r = o._annotations[a];
   if(!r){
      RMessage.fatal(o, null, "Can't find annotations. (annotation={1}, class={2})", a, o.name);
   }
   return r;
}
function TClass_annotation(a, n){
   var o = this;
   var r = null;
   var as = o._annotations[a];
   if(as){
      r = as[n];
   }
   if(!r){
      RMessage.fatal(o, null, "Can't find annotation. (annotation={1}, name={2}, class={3})", a, n, o.name);
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
      var as = p._annotations[EAnnotation.Style];
      if(as){
         a = as[n];
         if(a){
            break;
         }
      }
      p = p.parent;
   }
   if(!a){
      RMessage.fatal(o, null, "No register style annotation. (name={1}, class={2})", o.name + '_' + n, o.name);
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
      RMessage.fatal(o, null, "Css is undefined. (name={1}, class={2})", o.name + '_' + n, o.name);
   }
   o.styles[n] = sn;
   return sn;
}
function TClass_newInstance(){
   var o = this;
   var r = o.alloc();
   if(!r){
      if(o.abstract){
         var s = new TString();
         for(var n in o.instance){
            var v = o.instance[n];
            if(RMethod.isVirtual(v)){
               if(!s.isEmpty()){
                  s.append(',');
               }
               s.append(v._name);
            }
         }
         return RMessage.fatal(o, null, "Abstract Class can't be create.(name={1})\n[{2}]", o.name, s);
      }
      var ro = o.instance;
      if(!ro){
         return RMessage.fatal(o, null, "Class instance is empty. (name={1})", o.name);
      }
      r = new ro.constructor();
      r._class = o;
      for(var n in ro){
         var v = ro[n];
         if(null != v){
            if('base' == n){
               r[n] = ro.base;
               continue;
            }
            var t = typeof(v);
            if(('boolean' != t) && ('date' != t) && ('string' != t) && ('number' != t) && ('function' != t)){
               v = RObject.clone(v);
            }
         }
         r[n] = v;
      }
      if(r.construct){
         r.construct();
      }
   }
   return r;
}
function TClass_alloc(){
   var o = this;
   var e = o._unused;
   if(e){
      o._unused = e.cnext;
      e.cnext = null;
      e._using = true;
   }
   return e;
}
function TClass_free(v){
   var o = this;
   if(v._using){
      var u = o._unused;
      v.cnext = u;
      o._unused = v;
      v._using = false;
      for(var n in v){
         var cv = v[n];
         if(cv){
            var t = typeof(cv);
            if('boolean' != t && 'date' != t && 'string' != t && 'number' != t && 'function' != t){
               if(cv._class){
                  RClass.free(cv);
               }else if(RClass.isClass(cv, Array)){
                  for(var i = 0; i < cv.length; i++){
                     var mv = cv[i];
                     if(mv._class){
                        RClass.free(mv);
                     }
                  }
               }
            }
         }
      }
   }
}
function TConsole(s, c, f){
   var o = this;
   o.scope = s;
   o.clazz = c;
   o.force = f;
   return o;
}
function TContext(n, c, t){
   var o = this;
   o.name = n;
   o.code = c;
   o.text = t;
   return o;
}
function TDataset(){
   var o = this;
   o.name       = null;
   o.count      = 0;
   o.pageSize   = 20;
   o.pageIndex  = 0;
   o.pageCount  = 0;
   o.total      = 0;
   o.rows       = new TList();
   o.createRow  = TDataset_createRow;
   o.row        = TDataset_row;
   o.find       = TDataset_find;
   o.findIndex  = TDataset_findIndex;
   o.push       = TDataset_push;
   o.remove     = TDataset_remove;
   o.removeRow  = TDataset_removeRow;
   o.loadNode   = TDataset_loadNode;
   o.saveViewer = TDataset_saveViewer;
   o.clear      = TDataset_clear;
   o.pack       = TDataset_pack;
   o.dump       = TDataset_dump;
   return o;
}
function TDataset_createRow(){
   var o = this;
   var r = new TRow();
   r.dataset = o;
   o.rows.push(r);
   return r;
}
function TDataset_row(n){
   return (n >= 0 && n < this.count) ? this.rows.get(n) : null;
}
function TDataset_find(){
   var o = this;
   var a = arguments;
   var l = a.length;
   if(0 != l % 2){
      RMessage.fatal(o, null, 'Parameters must is pairs (length={0})', l);
   }
   var rs = o.rows;
   for(var n=rs.count-1; n>=0; n--){
      var r = rs.get(n);
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
function TDataset_findIndex(id){
   var o = this;
   var rs = o.rows;
   var c = rs.count;
   for(var n=0; n<c; n++){
      var r = rs.get(n);
      if(r.index = id){
         return r;
      }
   }
   return null;
}
function TDataset_push(r){
   this.rows.push(r);
}
function TDataset_remove(i){
   return this.rows.remove(i);
}
function TDataset_removeRow(r){
   var o = this;
   var i = o.indexOf(r);
   if(-1 != i){
      o.rows.remove(i);
   }
}
function TDataset_loadNode(x){
   var o = this;
   o.name = x.get('name');
   o.pageSize = RInteger.parse(x.get('page_size', 1000));
   o.pageIndex = RInteger.parse(x.get('page', 0));
   o.pageCount = RInteger.parse(x.get('page_count', 1));
   o.total = RInteger.parse(x.get('total'));
   var xrs = x.nodes;
   if(xrs){
      var rs = o.rows;
      var xrc = o.count = xrs.count;
      for(var n=0; n<xrc; n++){
         var xr = xrs.get(n);
         if(xr.isName(RDataset.ROW)){
            var r = rs.memory[n];
            if(!r){
               var r = new TRow();
               r.dataset = o;
               rs.count = n;
               rs.push(r);
            }else{
               r.release();
            }
            r.loadNode(xr);
         }
      }
      rs.count = xrc;
   }
}
function TDataset_saveViewer(v){
   var o = this;
   v.datasetName = o.name;
   v.datasetId = o.id;
   v.position = 0;
   v.start = 0;
   v.count = o.rows.count;
   v.rows = o.rows;
   v.dataset = o;
}
function TDataset_clear(){
   var o = this;
   o.rows.clear();
   o.pageSize = 20;
   o.pageIndex = 0;
   o.count = 0;
   o.pageCount = 0;
   o.total = 0;
}
function TDataset_pack(){
   var o = this;
   var rs = o.rows;
   var ss = new TStrings();
   for(var n = 0; n < rs.count; n++){
      ss.push(rs.get(n).pack());
   }
   return ss.pack();
}
function TDataset_dump(s){
   var o = this;
   s = RString.nvlStr(s);
   s.append(RClass.name(o));
   s.append(' count=', o.count);
   s.append(' fields=', o.fieldCount);
   s.appendLine();
   if(o.rows){
      for(var n=0; n<o.count; n++){
         s.append('- ');
         o.rows.get(n).dump(s);
         if(n != o.count-1){
            s.appendLine();
         }
      }
   }
   return s;
}
function TDictionary(o){
   o = o ? o : this;
   TMap(o);
   return o;
}
function TInvoke(w, p){
   var o = this;
   o.owner    = w;
   o.callback = p;
   o.invoke   = TInvoke_invoke;
   return o;
}
function TInvoke_invoke(p1, p2, p3, p4, p5, p6){
   var o = this;
   if(o.callback){
      var owner = moNvl(o.owner, o);
      try{
         o.callback.call(owner, p1, p2, p3, p4, p5, p6);
      }catch(e){
         RMessage.fatal(o, e, 'Call method failure. (owner={1}, callback={2})', o.owner, o.callback);
      }
   }
}
function TList(o){
   if(!o){o=this;}
   o.count      = 0;
   o.memory     = new Array();
   o.isEmpty    = TList_isEmpty;
   o.contains   = TList_contains;
   o.indexOf    = TList_indexOf;
   o.first      = TList_first;
   o.last       = TList_last;
   o.get        = TList_get;
   o.set        = TList_set;
   o.insert     = TList_insert;
   o.push       = TList_push;
   o.pushUnique = TList_pushUnique;
   o.pop        = TList_pop;
   o.swap       = TList_swap;
   o.sort       = TList_sort;
   o.remove     = TList_remove;
   o.extract    = TList_extract;
   o.compress   = TList_compress;
   o.append     = TList_append;
   o.clear      = TList_clear;
   o.release    = TList_release;
   o.pack       = TList_pack;
   o.dump       = TList_dump;
   return o;
}
function TList_isEmpty(){
   return (0 == this.count);
}
function TList_contains(v){
   return (-1 != this.indexOf(v));
}
function TList_indexOf(v){
   var c = this.count;
   for(var n=0; n<c; n++){
      if(this.memory[n] == v){
         return n;
      }
   }
   return -1;
}
function TList_first(){
   var o = this;
   return o.count ? this.memory[0] : null;
}
function TList_last(){
   var o = this;
   return o.count ? this.memory[o.count-1] : null;
}
function TList_get(n){
   return (n>=0 && n<this.count) ? this.memory[n] : null;
}
function TList_set(n, v){
   if(n>=0 && n<this.count){
      this.memory[n] = v;
   }
}
function TList_insert(i, v){
   var o = this;
   var c = o.count;
   if(i >=0 && i<=c){
      for(var n=c; n>i; n--){
         o.memory[n] = o.memory[n-1];
      }
      o.memory[i] = v;
   }
}
function TList_push(v){
   var n = this.count++;
   this.memory[n] = v;
   return n;
}
function TList_pushUnique(v){
   var o = this;
   for(var n=o.count-1; n>=0; n--){
      if(o.memory[n] == v){
         return n;
      }
   }
   var n = o.count++;
   o.memory[n] = v;
   return n;
}
function TList_pop(){
   var o = this;
   if(o.count){
      return o.memory[--o.count];
   }
}
function TList_swap(l, r){
   var o = this;
   if(l>= 0 && l<o.count && r>=0 && r<o.count && l!=r){
      var v = o.memory[l];
      o.memory[l] = this.memory[r];
      o.memory[r] = v;
   }
}
function TList_sort(){
   this.memory.sort();
}
function TList_remove(n){
   var v = null;
   var o = this;
   if(n>= 0 && n<o.count){
      v = o.memory[n];
      var c = --o.count;
      for(var i=n; i<c; i++){
         o.memory[i] = o.memory[i+1];
      }
   }
   return v;
}
function TList_extract(v){
   if(null != v){
      var o = this;
      var n = 0;
      var c = o.count;
      for(var i=n; i<c; i++){
         if(o.memory[i] != v){
            o.memory[n++] = o.memory[i];
         }
      }
      o.count = n;
   }
   return v;
}
function TList_compress(){
   var o = this;
   var c = o.count;
   var l = 0;
   for(var n=0; n<c; n++){
      var v = o.memory[n];
      if(null != v){
         o.memory[l++] = v;
      }
   }
   o.count = l;
}
function TList_append(ls){
   var o = this;
   var ct = ls.count;
   for(var n=0; n<ct; n++){
      o.push(ls.get(n));
   }
}
function TList_clear(){
   this.count = 0;
}
function TList_release(){
   var o = this;
   o.count = 0;
   for(var n in o.memory){
      delete o.memory[n];
   }
}
function TList_pack(){
   var o = this;
   var ss = new TStrings();
   for(var n = 0; n < o.count; n++){
      ss.push(o.get(n).pack());
   }
   return ss.pack();
}
function TList_dump(d){
   var o = this;
   var c = o.count;
   d = RString.nvlStr(d);
   d.append(RClass.name(o), ': ', c);
   for(var n=0; n<c; n++){
      d.append(' [', o.memory[n], ']');
   }
   return d;
}
function TListener(w, p){
   var o = this;
   o.owner    = w;
   o.callback = p;
   o.process  = TListener_process;
   o.dump     = TListener_dump;
   return o;
}
function TListener_process(s, p1, p2, p3, p4, p5){
   var o = this;
   if(o.callback){
      o.callback.call(moNvl(o.owner, o), s, p1, p2, p3, p4, p5);
   }
}
function TListener_dump(){
   var o = this;
   return RClass.name(o) + ' owner=' + RClass.name(o.owner);
}
function TListeners(){
   var o = this;
   o.listeners = null;
   o.isEmpty   = TListeners_isEmpty;
   o.register  = TListeners_register;
   o.push      = TListeners_push;
   o.process   = TListeners_process;
   o.clear     = TListeners_clear;
   o.dump      = TListeners_dump;
   return o;
}
function TListeners_isEmpty(){
   var ls = this.listeners;
   return ls ? ls.count > 0 : false;
}
function TListeners_register(w, p){
   var l = new TListener(w, p);
   this.push(l);
   return l;
}
function TListeners_push(l){
   var o = this;
   if(!l){
      return RMessage.fatal(o, null, 'Listener is null.');
   }
   if(!l.callback){
      return RMessage.fatal(o, null, 'Listener process is null.');
   }
   if(!o.listeners){
      o.listeners = new TList();
   }
   o.listeners.push(l);
}
function TListeners_process(s, p1, p2, p3, p4, p5){
   var ls = this.listeners;
   if(ls){
      var c = ls.count;
      for(var n=0; n<c; n++){
         ls.get(n).process(s, p1, p2, p3, p4, p5);
      }
   }
}
function TListeners_clear(){
   var o = this;
   if(o.listeners){
      o.listeners.clear();
   }
}
function TListeners_dump(d){
   var o = this;
   d = RString.nvlStr(d);
   d.append(RClass.name(o));
   var ls = o.listeners;
   var c = ls.length;
   for(var n=0; n<c; n++){
      d.append('\n   ' + ls[n].dump());
   }
   return d;
}
function TLoaderListener(){
   var o = this;
   o.invoke = null;
   o.ids    = new TArray();
   o.check  = TLoaderListener_check;
   return o;
}
function TLoaderListener_check(l){
   var s = this.ids;
   if(!s.isEmpty()){
      var c = s.length;
      for(var n=0; n<c; n++){
         if(!l.contains(s.get(n))){
            return false;
         }
      }
   }
   return true;
}
function TMap(o){
   o = o ? o : this;
   o._table        = new Object();
   o.count         = 0;
   o.names         = new Array();
   o.values        = new Array();
   o.isEmpty       = TMap_isEmpty;
   o.contains      = TMap_contains;
   o.containsValue = TMap_containsValue;
   o.indexOf       = TMap_indexOf;
   o.indexOfValue  = TMap_indexOfValue;
   o.first         = TMap_first;
   o.last          = TMap_last;
   o.name          = TMap_name;
   o.value         = TMap_value;
   o.setValue      = TMap_setValue;
   o.get           = TMap_get;
   o.nvl           = TMap_nvl;
   o.set           = TMap_set;
   o.assign        = TMap_assign;
   o.append        = TMap_append;
   o.insert        = TMap_insert;
   o.remove        = TMap_remove;
   o.removeName    = TMap_removeName;
   o.removeValue   = TMap_removeValue;
   o.rebuild       = TMap_rebuild;
   o.clear         = TMap_clear;
   o.release       = TMap_release;
   o.toString      = TMap_toString;
   o.dump          = TMap_dump;
   return o;
}
function TMap_isEmpty(){
   return (0 == this.count);
}
function TMap_contains(n){
   return (null != n) ? (null != this._table[n.toString().toLowerCase()]) : false;
}
function TMap_containsValue(v){
   return (-1 != this.indexOfValue(v));
}
function TMap_indexOf(n){
   if(null != n){
      var r = this._table[n.toString().toLowerCase()];
      return (null != r) ? r : -1;
   }
   return -1;
}
function TMap_indexOfValue(v){
   var o = this;
   var c = o.count;
   for(var n=0; n<c; n++){
      if(o.values[n] == v){
         return n;
      }
   }
   return -1;
}
function TMap_first(){
   return this.count ? this.values[0] : null;
}
function TMap_last(){
   return this.count ? this.values[this.count-1] : null;
}
function TMap_name(n){
   return (n>=0 && n<this.count) ? this.names[n] : null;
}
function TMap_value(n){
   return (n>=0 && n<this.count) ? this.values[n] : null;
}
function TMap_setValue(n, v){
   if(n>=0 && n<this.count){
      this.values[n] = v;
   }
}
function TMap_get(n, v){
   if(null != n){
      var o = this;
      var i = o._table[n.toString().toLowerCase()];
      if(null != i){
         return o.values[i];
      }
   }
   return v;
}
function TMap_nvl(n, v){
   var r = this.get(n);
   return r ? r : v;
}
function TMap_set(n, v){
   if(null != n){
      var o = this;
      var l = n.toString().toLowerCase();
      var i = o._table[l];
      if(null == i || i >= o.count){
         i = o.count++;
         o.names[i] = n;
         o._table[l] = i;
      }
      o.values[i] = v;
   }
}
function TMap_assign(m){
   var o = this;
   o.clear();
   o.append(m);
}
function TMap_append(m){
   if(m){
      var c = m.count;
      for(var n=0; n<c; n++){
         this.set(m.name(n), m.value(n));
      }
   }
}
function TMap_insert(i, n, v){
   var o = this;
   var c = o.count;
   if(i >= 0 && i <= c){
      for(var p=c; p>i; p--){
         o.names[p] = o.names[p-1];
         o.values[p] = o.values[p-1];
      }
      o.names[i] = n;
      o.values[i] = v;
      o.count++;
      o.rebuild();
   }
}
function TMap_remove(i){
   var o = this;
   var r = null;
   var c = o.count;
   if(i >= 0 && i < c){
      r = o.values[i];
      for(var p=i; p<c; p++){
         o.names[p] = o.names[p+1];
         o.values[p] = o.values[p+1];
      }
      o.count--;
      o.rebuild();
   }
   return r;
}
function TMap_removeName(n){
   var o = this;
   var i = o.indexOf(n);
   return (-1 != i) ? o.remove(i) : null;
}
function TMap_removeValue(v){
   var o = this;
   var i = 0;
   var c = o.count;
   for(var n=0; n<c; n++){
      var s = o.values[n];
      if(v != s){
         if(i != n){
            o.names[i] = o.names[n];
            o.values[i] = s;
         }
         i++;
      }
   }
   o.count = i;
   o.rebuild();
}
function TMap_rebuild(){
   var o = this;
   var t = o._table;
   for(var n in t){
      delete t[n];
   }
   var c = o.count;
   for(var n=0; n<c; n++){
      t[o.names[n].toLowerCase()] = n;
   }
}
function TMap_clear(){
   var o = this;
   o.count = 0;
   var t = o._table;
   for(var n in t){
      delete t[n];
   }
}
function TMap_release(){
   var o = this;
   o.clear();
   for(var n in o.names){
      delete o.names[n];
   }
   for(var n in o.values){
      delete o.values[n];
   }
}
function TMap_toString(){
   return this.dump().toString();
}
function TMap_dump(d){
   var o = this;
   d = RString.nvlStr(d);
   d.appendLine(RClass.name(o), ': ', o.count);
   var c = o.count;
   d.append(' {');
   for(var n=0; n<c; n++){
      d.appendLine(o.names[n], '=', ' [', o.values[n], ']');
   }
   d.append('}');
   return d;
}
function TNode(name, attrs){
   var o = this;
   o.name         = RString.nvl(name, 'Node');
   o.value        = null;
   o.attrs        = attrs;
   o.nodes        = null;
   o.isName       = TNode_isName;
   o.hasAttribute = TNode_hasAttribute;
   o.hasNode      = TNode_hasNode;
   o.attributes   = TNode_attributes;
   o.contains     = TNode_contains;
   o.findNode     = TNode_findNode;
   o.nvl          = TNode_nvl;
   o.get          = TNode_get;
   o.set          = TNode_set;
   o.node         = TNode_node;
   o.push         = TNode_push;
   o.create       = TNode_create;
   o.find         = TNode_find;
   o.isEmpty      = TNode_isEmpty;
   o.xml          = TNode_xml;
   o.toString     = TNode_toString;
   o.innerDump    = TNode_innerDump;
   o.dump         = TNode_dump;
   return o;
}
function TNode_isName(name){
   return RString.equals(this.name, name);
}
function TNode_hasAttribute(){
   var o = this;
   return o.attrs ? !o.attrs.isEmpty() : false;
}
function TNode_hasNode(){
   var o = this;
   return o.nodes ? !o.nodes.isEmpty() : false;
}
function TNode_attributes(){
   var o = this;
   if(!o.attrs){
      o.attrs = new TAttributes();
   }
   return o.attrs;
}
function TNode_contains(n){
   return this.attrs ? this.attrs.contains(n) : false;
}
function TNode_nvl(name, def){
   return RString.nvl(this.get(name), def);
}
function TNode_get(n, v){
   return this.attrs ? this.attrs.get(n, v) : null;
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
   if(!RClass.isClass(attrs, TAttributes)){
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
   s = RString.nvlStr(s);
   s.append('<', o.name);
   var as = o.attrs;
   if(as){
      for(var n=0; n<as.count; n++){
         s.append(' ', as.name(n), '="');
         RXml.buildText(s, as.value(n));
         s.append('"');
      }
   }
   if(!o.nodes && null == o.value){
      s.append('/');
   }
   s.append('>');
   var ns = o.nodes;
   if(ns){
      var c = ns.count;
      for(var n=0; n<c; n++){
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
      if(!RString.isEmpty(value)){
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
   d = RString.nvlStr(d);
   return this.innerDump(d, this, space);
}
function TNode_findNode(name, value){
   var o = this;
   var at = new TAttributes();
   var nd = null;
   if(null != o.attrs){
      at = o.attrs;
   }
   if(at.get(name)==value){
      nd = o;
   }else{
     if(o.hasNode()){
        for(var n = 0; n< o.nodes.count; n++){
           nd = o.nodes.get(n).findNode(name, value);
           if(nd != null){
              break;
           }
        }
     }
   }
   return nd;
}
function TRow(o, ds){
   o = o ? o : this;
   TAttributes(o);
   o.dataset       = ds;
   o.index         = null;
   o.uniqueId      = null;
   o.status        = null;
   o.loadNode      = TRow_loadNode;
   o.saveNode      = TRow_saveNode;
   o.copy          = TRow_copy;
   o.toAttributes  = TRow_toAttributes;
   o.dump          = TRow_dump;
   return o;
}
function TRow_loadNode(x){
   if(x && x.attrs){
      var o = this;
      o.index = x.get('_id');
      o.status = x.get('_status');
      o.uniqueId = x.get('ouid');
      o.append(x.attrs);
   }
}
function TRow_saveNode(x){
   if(x){
      var o = this;
      x.set('_id', o.index);
      x.set('_status', o.status);
      var c = o.count;
      for(var n=0; n<c; n++){
         x.set(o.names[n], o.values[n]);
      }
   }
}
function TRow_copy(){
   var o = this;
   var r = new TRow();
   r.dataset = o.dataset;
   r.index = o.index;
   r.status = o.status;
   r.uniqueId = o.uniqueId;
   var c = o.count;
   for(var n=0; n<c; n++){
      r.set(o.names[n], o.values[n]);
   }
   return r;
}
function TRow_toAttributes(a){
   var o = this;
   if(!a){
      a = new TAttributes();
   }
   a.set(RDataset.ROW_STATUS, o.status);
   a.append(o);
   return a;
}
function TRow_dump(s){
   var o = this;
   var c = o.count;
   s = RString.nvlStr(s);
   s.append(RClass.name(o), ' [', o.status, ': ');
   for(var n=0; n<c; n++){
      if(n > 0){
         s.append(',');
      }
      s.append(o.names[n], '=', o.values[n]);
   }
   s.append(']');
   return s;
}
function TSpace(s, n, v){
   var o = this;
   o.space    = s;
   o.name     = n;
   o.instance = v;
   return o;
}
function TSpeed(){
   var o = this;
   o.arguments  = arguments;
   o.start      = new Date().getTime();
   o.callerName = RMethod.name(TSpeed.caller);
   o.record     = TSpeed_record
   return o;
}
function TSpeed_record(){
   var o = this;
   var sp = new Date().getTime() - o.start;
   RLogger.log(ELogger.Debug, o.callerName, sp, o.arguments);
   o.arguments = null;
   o.start = null;
   o.callerName = null;
   o.record = null;
}
function TString(v){
   var o = this;
   o.count      = 0;
   o.memory     = new Array();
   o.isEmpty    = TString_isEmpty;
   o.assign     = TString_assign;
   o.append     = TString_append;
   o.appendIf   = TString_appendIf;
   o.appendLine = TString_appendLine;
   o.push       = TString_push;
   o.clear      = TString_clear;
   o.toString   = TString_toString;
   o.dump       = TString_dump;
   var a = arguments;
   var c = a.length;
   for(var n=0; n<c; n++){
      if(null != a[n]){
         o.memory[o.count++] = a[n] + '';
      }
   }
   return o;
}
function TString_isEmpty(){
   return (0 == this.count);
}
function TString_assign(v){
   var o = this;
   var c = arguments.length;
   o.count = 0;
   for(var n=0; n<c; n++){
      if(null != arguments[n]){
         o.memory[o.count++] = arguments[n] + '';
      }
   }
   return o;
}
function TString_append(v){
   var o = this;
   var c = arguments.length;
   for(var n=0; n<c; n++){
      if(null != arguments[n]){
         o.memory[o.count++] = arguments[n] + '';
      }
   }
   return o;
}
function TString_appendIf(f, v){
   var o = this;
   if(arguments[0]){
      var c = arguments.length;
      for(var n=1; n<c; n++){
         if(null != arguments[n]){
            o.memory[o.count++] = arguments[n] + '';
         }
      }
   }
   return o;
}
function TString_appendLine(v){
   var o = this;
   var c = arguments.length;
   for(var n=0; n<c; n++){
      if(null != arguments[n]){
         o.memory[o.count++] = arguments[n] + '';
      }
   }
   o.memory[o.count++] = '\n';
   return o;
}
function TString_push(v){
   var o = this;
   var c = arguments.length;
   for(var n=0; n<c; n++){
      if(null != arguments[n]){
         o.memory[o.count++] = arguments[n];
      }
   }
   return o;
}
function TString_clear(){
   this.count = 0;
}
function TString_toString(){
   return this.memory.slice(0, this.count).join('');
}
function TString_dump(){
   var o = this;
   var s = o.toString();
   return RClass.name(o) + ':' + s.length + '[' + s + ']';
}
var MoJS = new function(){
   var o = this;
   o._spaces    = new Object();
   o.Global     = new Object();
   o.Top        = new Object();
   o.Local      = new Object();
   o.onRelease  = MoJS_onRelease;
   o.register   = MoJS_register;
   o.initialize = MoJS_initialize;
   o.connect    = MoJS_connect;
   o.buildSpace = MoJS_buildSpace;
   o.find       = MoJS_find;
   o.findGlobal = MoJS_findGlobal;
   o.findTop    = MoJS_findTop;
   o.findLocal  = MoJS_findLocal;
   return o;
}
function MoJS_onRelease(){
   RConsole.release();
   REvent.release();
   CollectGarbage();
}
function MoJS_register(s){
   var o = this;
   var p = o._spaces[s.space];
   if(!p){
      p = o._spaces[s.space] = new Object();
   }
   p[s.name] = s;
}
function MoJS_initialize(){
   var o = this;
   RConsole.initialize();
}
function MoJS_connect(){
   var o = this;
   RConsole.initialize();
}
function MoJS_buildSpace(t, p){
   var o = this;
   for(var n in p){
      if(RString.startsWith(n, 'R')){
         t[n.substring(1)] = p[n].instance;
      }
   }
}
function MoJS_find(s, n){
   var r = null;
   var s = this._spaces[s];
   if(s){
      r = s[n];
      if(r){
         return r.instance;
      }
   }
   return null;
}
function MoJS_findGlobal(n){
   return this.find(ESpace.Global, n);
}
function MoJS_findTop(n){
   return top.MoJS.find(ESpace.Top, n);
}
function MoJS_findLocal(n){
   return this.find(ESpace.Local, n);
}
var RGlobal = new function(){
   var o = this;
   o.instances = new TMap();
   o.get       = RGlobal_get;
   o.set       = RGlobal_set;
   return o;
}
if(top){
   if(top.RGlobal){
      RGlobal = top.RGlobal;
   }
}
function RGlobal_get(n){
   return this.instances.get(n);
}
function RGlobal_set(n, v){
   this.instances.set(n, v);
}
var RMemory = new function(){
   var o = this;
   o.objects       = new Array();
   o.instances     = new Object();
   o.isObject      = RMemory_isObject;
   o.create        = RMemory_create;
   o.register      = RMemory_register;
   o.disposeObject = RMemory_disposeObject;
   o.dispose       = RMemory_dispose;
   o.unlink        = RMemory_unlink;
   o.free          = RMemory_free;
   o.freeHtml      = RMemory_freeHtml;
   o.release       = RMemory_release;
   o.refresh       = RMemory_refresh;
   return o;
}
function RMemory_isObject(o){
   var t = typeof(o);
   return ('boolean' != t) && ('number' != t) && ('string' != t) && ('date' != t) && ('function' != t) && (o instanceof Object);
}
function RMemory_create(c){
   var o = new c();
   this.objects.push(o);
   return o;
}
function RMemory_register(n, o){
   if(this.isObject(o)){
      this.objects.push(o);
      this.instances[n] = o;
   }
}
function RMemory_disposeObject(o){
}
function RMemory_dispose(o){
   if(null != o && this.isObject(o)){
      if(!o._disposed){
         o._disposed = true;
         if(o.dispose instanceof Function){
            o.dispose();
         }
         this.disposeObject(o);
      }
   }
}
function RMemory_unlink(o){
   for(var n in o){
      var v = o[n];
      o[n] = null;
      if(null != v && this.isObject(v)){
         this.unlink(o);
      }
   }
}
function RMemory_free(o){
   this.dispose(o);
   this.unlink(o);
}
function RMemory_freeHtml(h){
   if(h){
      h.removeNode(true);
   }
}
function RMemory_release(){
   var o = this;
   o.free(o.objects);
   o.free(o.instances);
   o.refresh();
}
function RMemory_refresh(){
   if(RContext.optionGarbage){
      CollectGarbage();
   }
}
var RArray = new function(){
   var o = this;
   o.equals        = RArray_equals;
   o.count         = RArray_count;
   o.contains      = RArray_contains;
   o.find          = RArray_find;
   o.search        = RArray_search;
   o.reverse       = RArray_reverse;
   o.copy          = RArray_copy;
   o.move          = RArray_move;
   o.remove        = RArray_remove;
   o.sortPartition = RArray_sortPartition;
   o.sortArray     = RArray_sortArray;
   o.sort          = RArray_sort;
   o.nameMaxlen    = RArray_nameMaxlen;
   RMemory.register('RArray', o);
   return o;
}
function RArray_equals(s, t){
   if(s && t && s.length == t.length){
      var c = s.length;
      for(var n=0; n<c; n++){
         if(s[n] != t[n]){
            return false;
         }
      }
      return true;
   }
   return false;
}
function RArray_count(a){
   var c = 0;
   for(var n in a){
      n++;
   }
   return c;
}
function RArray_contains(a, v){
   var c = a.length;
   for(var n=0; n<c; n++){
      if(a[n] == v){
         return true;
      }
   }
   return false;
}
function RArray_find(a, v){
   var c = a.length;
   for(var n=0; n<c; n++){
      if(a[n] == v){
         return n;
      }
   }
   return -1;
}
function RArray_search(a, v){
   for(var n in a){
      if(a[n] == v){
         return n;
      }
   }
   return null;
}
function RArray_reverse(a, s, e){
   var c = (e-s) >> 1;
   for(var n = 0; n<c; n++){
      var t = a[s+n];
      a[s+n] = a[e-n];
      a[e-n] = t;
   }
}
function RArray_copy(s, t){
   for(var n in s){
      t[n] = s[n];
   }
}
function RArray_move(a, f, c, t){
   if(f > t){
      for(var n=0; n<c; n++){
         a[t-n] = a[f+n];
      }
   }else if(f < t){
      for(var n=0; n<c; n++){
         a[t+c-n-1] = a[f+c-n-1];
      }
   }
}
function RArray_remove(a, n){
   return a.slice(0, n).concat(a.slice(n+1));
}
function RArray_sortPartition(a, l, r){
   var s = l;
   var e = r + 1;
   var t = a[s];
   while(true){
      while(a[++s] < t);
      while(a[--e] > t);
      if(s > e){
         break;
      }
      var v = a[s];
      a[s] = a[e];
      a[e] = v;
   }
   a[l] = a[e];
   a[e] = t;
   return e;
}
function RArray_sortArray(a, s, e){
   if(s<e){
      var o = this;
      var p = o.sortPartition(a, s, e);
      o.sortArray(a, s, p-1);
      o.sortArray(a, p+1, e);
   }
}
function RArray_sort(a, t){
   var o = this;
   o.sortArray(a, 0, a.length-1);
   if(t){
      o.reverse(a, 0, a.length-1);
   }
   return a;
}
function RArray_nameMaxlen(a){
   var r = 0;
   for(var n in a){
      r = Math.max(n.length, r);
   }
   return r;
}
var RBoolean = new function(){
   var o = this;
   o.isTrue   = RBool_isTrue;
   o.toString = RBool_toString;
   RMemory.register('RBool', o);
   return o;
}
var RBool = RBoolean;
function RBool_isTrue(v){
   return (v == EBool.True);
}
function RBool_toString(v){
   return v ? EBool.True : EBool.False;
}
var RBuilder = new function(){
   var o = this;
   o.hWindow           = window;
   o.hDocument         = window.document;
   o.onBuildSpanPanel  = RBuilder_onBuildSpanPanel;
   o.onBuildDivPanel   = RBuilder_onBuildDivPanel;
   o.onBuildTdPanel    = RBuilder_onBuildTdPanel;
   o.onBuildTrPanel    = RBuilder_onBuildTrPanel;
   o.onBuildTablePanel = RBuilder_onBuildTablePanel;
   o.append            = RBuilder_append;
   o.appendImage       = RBuilder_appendImage;
   o.appendIcon        = RBuilder_appendIcon;
   o.appendEmpty       = RBuilder_appendEmpty;
   o.appendText        = RBuilder_appendText;
   o.appendTable       = RBuilder_appendTable;
   o.appendRow         = RBuilder_appendRow;
   o.appendDiv         = RBuilder_appendDiv;
   o.appendCell        = RBuilder_appendCell;
   o.appendCheck       = RBuilder_appendCheck;
   o.appendRadio       = RBuilder_appendRadio;
   o.appendEdit        = RBuilder_appendEdit;
   o.create            = RBuilder_create;
   o.createFragment    = RBuilder_createFragment;
   o.newImage          = RBuilder_newImage;
   o.newIcon           = RBuilder_newIcon;
   o.newSpan           = RBuilder_newSpan;
   o.newDiv            = RBuilder_newDiv;
   o.newText           = RBuilder_newText;
   o.newTable          = RBuilder_newTable;
   o.newCheck          = RBuilder_newCheck;
   o.newRadio          = RBuilder_newRadio;
   o.newEdit           = RBuilder_newEdit;
   RMemory.register('RBuilder', o);
   return o;
}
function RBuilder_onBuildSpanPanel(){
   this.hPanel = RBuilder.newSpan();
}
function RBuilder_onBuildDivPanel(){
   this.hPanel = RBuilder.newDiv();
}
function RBuilder_onBuildTdPanel(){
   this.hPanel = RBuilder.create(null, 'TD');
}
function RBuilder_onBuildTrPanel(){
   this.hPanel = RBuilder.create(null, 'TR');
}
function RBuilder_onBuildTablePanel(){
   this.hPanel = RBuilder.newTable();
}
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
   var o = this.newIcon(p, 'n', null, w, h);
   p.appendChild(o);
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
   var h = this.create(row, 'TD', css);
   row.appendChild(h);
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
function RBuilder_appendRadio(parent, css){
   var h = this.newRadio(parent, css);
   parent.appendChild(h);
   return h;
}
function RBuilder_appendEdit(parent, css){
   var h = this.newEdit(parent, css);
   parent.appendChild(h);
   return h;
}
function RBuilder_create(p, n, c){
   var o = this;
   try{
      var h = p ? p.ownerDocument.createElement(n) : o.hDocument.createElement(n);
      if(c){
         h.className = c;
      }
      return h;
   }catch(e){
      RMessage.fatal(o, e, 'Create html obejct. (parent={1}, name={2}, css={3})', p, n, c)
   }
}
function RBuilder_createFragment(p){
   return p ? p.ownerDocument.createDocumentFragment() : this.hDocument.createDocumentFragment();
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
   var o = this.create(p, 'IMG', RString.nvl(c, 'Tag_Icon'));
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
   var r = this.create(parent, "INPUT", css);
   r.type = 'checkbox';
   return r;
}
function RBuilder_newRadio(parent, css){
   var r = this.create(parent, "INPUT", css);
   r.type = 'radio';
   return r;
}
function RBuilder_newEdit(parent, css){
   var r = this.create(parent, "INPUT", css);
   r.type = 'text';
   return r;
}
var RChar = new function(){
   var o = this;
   o.parse     = RChar_parse;
   o.toInteger = RChar_toInteger;
   RMemory.register('RChar', o);
   return o;
}
function RChar_parse(n){
   return String.fromCharCode(n);
}
function RChar_toInteger(c){
   return c.charCodeAt(0);
}
var RClass = new function(){
   var o = this;
   o.mode         = ERun.Process;
   o.codes        = new Array();
   o.classes      = new Object();
   o.isMode       = RClass_isMode;
   o.typeOf       = RClass_typeOf;
   o.safeTypeOf   = RClass_safeTypeOf;
   o.isName       = RClass_isName;
   o.isClass      = RClass_isClass;
   o.checkClass   = RClass_checkClass;
   o.code         = RClass_code;
   o.name         = RClass_name;
   o.inherits     = RClass_inherits;
   o.forName      = RClass_forName;
   o.find         = RClass_find;
   o.register     = RClass_register;
   o.createBase   = RClass_createBase;
   o.createClass  = RClass_createClass;
   o.create       = RClass_create;
   o.createByName = RClass_createByName;
   o.innerCopy    = RClass_innerCopy;
   o.build        = RClass_build;
   o.dump         = RClass_dump;
   o.free         = RClass_free;
   RMemory.register('RClass', o);
   return o;
}
function RClass_isMode(m){
   return (m == this.mode);
}
function RClass_typeOf(o){
   if(o && o.constructor){
      return RString.mid(o.constructor.toString(), 'function ', '(');
   }
   return 'Null';
}
function RClass_safeTypeOf(v, safe){
   if(null != v){
      try{
         if(v._class){
            return v._class.name;
         }
         if(v.constructor){
            return RString.mid(v.constructor.toString(), 'function ', '(');
         }
         if(v.tagName){
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
function RClass_isName(v, n){
   return (this.name(v) == n);
}
function RClass_isClass(v, c){
   if(v && c){
      var o = this;
      var n = o.name(c);
      if(v.base){
         return (null != v.base[n]);
      }else{
         return (o.name(v) == n);
      }
   }
   return false;
}
function RClass_checkClass(v, c){
   if(!this.isClass(v, c)){
      throw new Error('Invalid class ' + o.name(o) + '<>' + o.name(c));
   }
}
function RClass_code(v){
   var c = this.codes;
   var l = c.length;
   for(var n=0; n<l; n++){
      if(c[n] == v){
         return n;
      }
   }
   c[l] = v;
   return l;
}
function RClass_name(v){
   if(v){
      if(v.clazz){
         return v.clazz.name;
      }
      if('function' == typeof(v)){
         return RString.mid(v.toString(), 'function ', '(');
      }
      var c = v.constructor;
      if(c){
         return RString.mid(c.toString(), 'function ', '(');
      }
   }
   return null;
}
function RClass_inherits(s, p, c){
   var r = moNvl(p, s);
   r.base = new Array();
   var l = arguments.length;
   for(var n=2; n<l; n++){
      r.base.push(RMethod.name(arguments[n]));
   }
   return r;
}
function RClass_forName(n){
   var r = null;
   if(n){
      var o = this;
      r = o.classes[n];
      if(!r){
         r = o.createClass(n);
         o.build(r);
      }
   }
   return r;
}
function RClass_find(n){
   var o = this;
   if(null != n){
      if(n._class){
         n = n._class.name;
      }else if('function' == typeof(n)){
         n = RMethod.name(n);
      }else if('string' != typeof(n)){
         RMessage.fatal(o, null, 'Param is invlid. (name={1})', n);
      }
   }
   return o.classes[n];
}
function RClass_register(v, a, r){
   this.classes[RMethod.name(v.constructor)].register(a);
   return r;
}
function RClass_createBase(n){
   if(n){
      return eval('function ' + n + '(){return this;} new ' + n + '();');
   }
   return null;
}
function RClass_createClass(n){
   var o = this;
   var c = o.classes[n] = new TClass();
   c.name = n;
   c.base = o.createBase(n);
   c.clazz = new c.base.constructor();
   eval(n + '(c.clazz)');
   return c;
}
function RClass_create(n){
   var o = this;
   var t = typeof(n);
   if('function' == t){
      n = RMethod.name(n);
   }else if('string' != t){
      RMessage.fatal(o, null, 'Param is invlid (name={1})', n);
   }
   return o.createByName(n);
}
function RClass_createByName(n){
   var o = this;
   var c = o.forName(n);
   if(!c){
      RMessage.fatal(o, null, 'Cant find class. (name={1})', c);
   }
   return c.newInstance();
}
function RClass_innerCopy(s, t){
   if(null != s && null != t){
      for(var n in s){
         var v = s[n];
         if(null != v){
            var p = typeof(v)
            if('function' == p){
               var f = t[n];
               if(null == f){
                  t[n] = v;
               }else if(RMethod.isVirtual(f)){
                  t[n] = v;
               }else if(!RMethod.isVirtual(v) && RMethod.isEmpty(f)){
                  t[n] = v;
               }else if(!RMethod.isVirtual(v) && !RMethod.isEmpty(v)){
                  t[n] = v;
               }
               continue;
            }else if('boolean' != p && 'date' != p && 'string' != p && 'number' != p){
               if(null == t[n]){
                  t[n] = new v.constructor();
               }
               this.innerCopy(v, t[n]);
               continue;
            }
         }
         t[n] = v;
      }
   }
}
function RClass_build(c){
   var sbs = c.clazz.base;
   if(sbs && ('Array' == RMethod.name(sbs.constructor))){
      var finded = false;
      for(var n=0; n<sbs.length; n++){
         var name = sbs[n];
         if(RString.startsWith(name, 'F')){
            if(finded){
               RMessage.fatal(this, null, 'Parent class is too many. (name={1})', name);
            }
            c.parent = RClass.forName(name);
            finded = true;
         }
      }
   }
   var o = c.instance = new c.base.constructor();
   if(sbs && ('Array' == RMethod.name(sbs.constructor))){
      for(var i=0; i<sbs.length; i++){
         var name = sbs[i];
         if(!RString.startsWith(name, 'F')){
            var m = RClass.forName(name);
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
      o.base._disposed = true;
   }
   o.base[c.name] = new c.base.constructor();
   var cf = c.clazz;
   for(var n in cf){
      if('base' != n){
         if(null == cf[n] && null == o[n]){
            o[n] = null;
         }else if(null!=cf[n]){
            if((null == o[n]) || (null != o[n] && cf[n] != o[n])){
               o[n] = cf[n];
            }
         }
      }
   }
   if(sbs && ('Array' == RMethod.name(sbs.constructor))){
      for(var i=0; i<sbs.length; i++){
         var name = sbs[i];
         var bcls = RClass.forName(name);
         var base = o.base[name] = new bcls.base.constructor();
         var cf = bcls.instance;
         for(var n in cf){
            if(n != 'base'){
               if((null != cf[n]) && (null != o[n]) && (cf[n] != o[n]) && ('function' == typeof(cf[n])) && ('function' == typeof(o[n]))){
                  base[n] = cf[n];
               }
            }
         }
      }
   }
   for(var n in c.instance){
      var v = c.instance[n];
      if(null != v){
         if('function' == typeof(v) && v._virtual){
            c.abstract = true;
            break;
         }
      }
   }
   for(var n in c.instance){
      var v = c.instance[n];
      if(null == v){
         delete c.instance[n];
      }
   }
}
function RClass_dump(v){
   var o = this;
   if(null == v){
      return '@null';
   }
   var t = o.safeTypeOf(v);
   switch(t){
      case 'Boolean':
         return 'Boolean:' + v;
      case 'Number':
         return 'Number:' + v;
      case 'String':
         return t + '<' + v.length + '>:' + v;
      case 'Function':
         return t + '<' + RMethod.name(v) + '>@' + o.code(v);
      case 'Html':
         return t + '<' + v.tagName + '>@' + RHtml.uid(v);
      default:
         if(v.name){
            return t + '<' + v.name + '>@' + o.code(v);
         }
   }
   return t + '@' + o.code(v);
}
function RClass_free(o){
   var c = o._class;
   if(c){
      c.free(o);
   }
}
var RConsole = new function(){
   var o = this;
   o.ConsolePreFix = 'console:';
   o.registers     = new TList();
   o.consoles      = new TMap();
   o.localConsoles = new TMap();
   o.register      = RConsole_register;
   o.initialize    = RConsole_initialize;
   o.create        = RConsole_create;
   o.createByName  = RConsole_createByName;
   o.find          = RConsole_find;
   o.release       = RConsole_release;
   RMemory.register('RConsole', o);
   return o;
}
function RConsole_register(c){
   this.registers.push(c);
}
function RConsole_initialize(){
   var o = this;
   var rs = o.registers
   var c = rs.count;
   for(var n=0; n<rs; n++){
      var r = rs.get(n);
      if(r.force){
         o.find(r.clazz);
      }
   }
}
function RConsole_create(n){
   var r = null;
   if(n){
      r = RClass.create(n);
      var o = this;
      for(var rn in r){
         if(RString.startsWith(rn, 'lnk')){
            var v = r[rn];
            if('string' == typeof(v) && RString.startsWith(v, '&')){
               var c = o.find(v.substr(1));
               if(!c){
                  return RMessage.fatal(o, null, "Can't link console. (name={0}, property={1}:{2})", n, rn, v);
               }
               r[rn] = c;
            }
         }
      }
   }
   return r;
}
function RConsole_createByName(n){
   var r = null;
   if(n){
      r = RClass.createByName(n);
      var o = this;
      for(var rn in r){
         if(RString.startsWith(rn, 'lnk')){
            var v = r[rn];
            if('string' == typeof(v) && RString.startsWith(v, '&')){
               var c = o.find(v.substr(1));
               if(!c){
                  return RMessage.fatal(o, null, "Can't link console. (name={0}, property={1}:{2})", n, rn, v);
               }
               r[rn] = c;
            }
         }
      }
   }
   return r;
}
function RConsole_find(n){
   var o = this;
   var t = typeof(n);
   if('function' == t){
      n = RClass.name(n);
   }else if('string' != t){
      return RMessage.fatal(o, null, 'Param type is invalid. (console={0})', n);
   }
   var c = RGlobal.get(o.ConsolePreFix + n);
   if(c){
      return c;
   }
   var cs = o.consoles;
   if(cs.contains(n)){
      return cs.get(n);
   }
   var cls = RClass.forName(n);
   switch(cls.instance.scope){
      case EScope.Global:
         c = top.RConsole.createByName(n);
         RGlobal.set(o.ConsolePreFix + n, c);
         cs.set(n, c);
         break;
      case EScope.Page:
         c = o.createByName(n);
         cs.set(n, c);
         o.localConsoles.set(n, c);
         break;
      default:
         return RMessage.fatal(o, 'Unknown name. (name={0})', n);
   }
   return c;
}
function RConsole_release(){
   var o = this;
   RMemory.free(this.localConsoles);
   o.registers = null;
   o.consoles = null;
   o.localConsoles = null;
}
var RContext = new function(){
   var o = this;
   o.optionGarbage = true;
   o.contextPath   = null;
   o.contextTag    = null;
   o.themeId       = null;
   o.languageId    = null;
   o._location     = null;
   o._contexts     = new Object();
   o.construct     = RContext_construct;
   o.initialize    = RContext_initialize;
   o.get           = RContext_get;
   o.find          = RContext_find;
   o.location      = RContext_location;
   o.context       = RContext_context;
   o.construct();
   RMemory.register('RContext', o);
   return o;
}
function RContext_construct(){
   var o = this;
   if(window.ActiveXObject){
      o.optionGarbage = true;
   }else{
      o.optionGarbage = false;
   }
}
function RContext_location(s){
   var o = this;
   var r = o._location;
   if(null == r){
      var l = RWindow.hWindow.location;
      var hr = l.href;
      var pn = l.pathname;
      r = hr.substring(0, hr.indexOf(pn))
      if(o.contextPath){
         r += o.contextPath;
      }
      o._location = r;
   }
   if(s){
      r += s;
   }
   return r;
}
function RContext_context(s){
   var o = this;
   if(null != s){
      if(RString.endsWith(s, '.wv')){
         return o.contextPath + '/' + s;
      }else if(RString.startsWith(s, '#')){
         return o.contextPath + o.contextTag + s.substr(1);
      }
      return o.contextPath + s;
   }
   return o.contextPath;
}
function RContext_initialize(s){
   var o = this;
   for(var n in s){
      var ls = s[n];
      for(var nc in ls){
         var v = ls[nc];
         var fn = n + ':' + nc;
         o._contexts[fn] = new TContext(n, nc, v);
      }
   }
}
function RContext_get(p, p1, p2, p3, p4, p5){
   var o = this;
   var r = o._contexts[p];
   if(!r){
      return RMessage.fatal(o, null, 'Can not find context (path={0})', p);
   }
   return RString.format(r.text, p1, p2, p3, p4, p5)
}
function RContext_find(s, c){
   var o = this;
   var id = s + ':' + c;
   var r = o._contexts[id];
   if(!r){
      return RMessage.fatal(o, null, 'Can not find context (id={0})', id);
   }
   return r.text;
}
var RCss = new function(){
   var o = this;
   o.connected = false;
   o.rules     = new TMap();
   o.connect   = RCss_connect;
   o.has       = RCss_has;
   o.nvl       = RCss_nvl;
   o.style     = RCss_style;
   RMemory.register('RCss', o);
   return o;
}
function RCss_connect(){
   var o = this;
   if(o.connected){
      return;
   }
   var s = o.rules;
   var ds = document.styleSheets;
   var dc = ds.length;
   for(var n = 0; n < dc; n++){
      var d = ds[n];
      var rs = d.cssRules;
      if(!rs){
         rs = d.rules;
      }
      if(rs){
         var rc = rs.length;
         for(var i = 0; i < rc; i++){
            var r = rs[i];
            s.set(r.selectorText, r);
         }
      }
   }
   o.connected = true;
}
function RCss_has(s){
   var o = this;
   if(!o.connected){
      o.connect();
   }
   if(s){
      return o.rules.contains('.' + s.toLowerCase());
   }
   return false;
}
function RCss_nvl(s, n){
   var o = this;
   if(!o.connected){
      o.connect();
   }
   var ac = arguments.length;
   for(var n = 0; n < ac; n++){
      var s = arguments[n];
      if(s){
         if(o.rules.contains('.' + s.toLowerCase())){
            return s;
         }
      }
   }
   return null;
}
function RCss_style(c, n){
   return RClass.name(c) + '_' + n;
}
var RDataset = new function(){
   var o = this;
   o.DATASET = 'Dataset';
   o.ROW = 'Row';
   o.ROW_STATUS = '_os';
   o.make = RDataset_make;
   RMemory.register('RDataset', o);
   return o;
}
function RDataset_make(d){
   var r = d;
   if(RXml.isNode(d)){
      r = new TDataset();
      r.loadNode(d);
   }
   return r;
}
var RFlash = new function(){
   var o = this;
   o.status      = 'sleep';
   o.events      = new Array();
   o.start       = RFlash_start;
   o.process     = RFlash_process;
   o.push        = RFlash_push;
   RMemory.register('RFlash', o);
   return o;
}
function flashGetStatus(){
   return RFlash.status;
}
function flashProcess(){
   RFlash.process();
}
function RFlash_start(){
   this.status = 'start';
}
function RFlash_process(){
   var o = this;
   if(o.events){
      var c = o.events.length;
      for(var n=0; n<c; n++){
         o.events[n].invoke();
      }
   }
   o.events = null;
}
function RFlash_push(e){
   var o = this;
   if(o.events){
      o.events = new Array();
      o.events.push(e)
   }
}
var RInteger = new function(o){
   if(!o){o=this};
   o.Chars     = '0123456789-%';
   o.NUMBER    = '0123456789-%';
   o.LEFT_CHAR = '0';
   o.isInt     = RInteger_isInt;
   o.parse     = RInteger_parse;
   o.format    = RInteger_format;
   o.nvl       = RInteger_nvl;
   o.toRange   = RInteger_toRange;
   o.sum       = RInteger_sum;
   o.alg       = RInteger_alg;
   RMemory.register('RInteger', o);
   return o;
}
var RInt = RInteger;
function RInteger_isInt(value){
   return RString.isPattern(value, 'n');
}
function RInteger_parse(v, d){
   if(null == d){
      d = 0;
   }
   if(null == v){
      return d;
   }
   v = RString.trim(v.toString());
   while(true){
      if('0' != v.charAt(0)){
         break;
      }
      v = v.substr(1);
   }
   var r = (v.length > 0) ? parseInt(v) : d;
   return isNaN(r) ? d : r;
}
function RInteger_format(value, len, pad){
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
function RInteger_nvl(v, d){
   return v ? v : (d ? d : 0);
}
function RInteger_toRange(v, min, max){
   if(null == v){
      v = 0;
   }
   return Math.min(Math.max(v, min), max);
}
function RInteger_sum(){
   var sum = 0;
   for(var n=0; n<arguments.length; n++){
      if(null != arguments[n]){
         sum += parseInt(arguments[n]);
      }
   }
   return sum;
}
function RInteger_alg(f,a,b){
     var a = RInteger.parse(a);
     var b = RInteger.parse(b);
     if(f){
        return (a + b).toString();
     }else{
        return (a - b).toString();
     }
}
var RLoader = new function(){
   var o = this;
   o._loading      = new TArray();
   o._loaded       = new TArray()
   o._waits        = new TArray()
   o._intervalId   = null;
   o.hWindow       = null;
   o.onInterval    = RLoader_onInterval;
   o.intervalStart = RLoader_intervalStart;
   o.intervalStop  = RLoader_intervalStop;
   o.loadJsFile    = RLoader_loadJsFile;
   o.loadJs        = RLoader_loadJs;
   o.loaded        = RLoader_loaded;
   o.wait          = RLoader_wait;
   o.waitJs        = RLoader_waitJs;
   o.dispose       = RLoader_dispose;
   RMemory.register('RLoader', o);
   return o;
}
function RLoader_dispose(){
   var o = this;
   o.intervalStop();
   o.hWindow = null;
}
function RLoader_onInterval(){
   var o = this;
   var ws = o._waits;
   var c = ws.length;
   for(var n=0; n<c; n++){
      var l = ws.get(n);
      if(l){
         if(l.check(o._loaded)){
            l.invoke.invoke();
            ws.set(n, null);
         }
      }
   }
   ws.compress();
   if(ws.isEmpty()){
      o.intervalStop();
   }
}
function RLoader_intervalStart(){
   var o = this;
   if(!o._intervalId){
      o.hWindow = window;
      o._intervalId = window.setInterval(function(){o.onInterval();}, 10);
   }
}
function RLoader_intervalStop(){
   var o = this;
   var w = o.hWindow;
   if(w && o._intervalId){
      w.clearInterval(o._intervalId);
      o.hWindow = null;
      o._intervalId = null;
   }
}
function RLoader_loadJsFile(id, src){
   var o = this;
   var d = RWindow.hDocument;
   var h = d.getElementsByTagName("head")[0];
   if(document.getElementById(id) == null){
      var url = top.RContext.location(src);
      var hs = RWindow.createElement('SCRIPT');
      hs.id = id;
      hs.type = 'text/javascript';
      hs.src = url;
      if(d.attachEvent){
         hs.onreadystatechange = function(){
            var s = hs.readyState;
            if('loaded' == s || 'complete' == s){
               hs.onreadystatechange = null;
               o._loading.extract(id);
               o._loaded.push(id);
            }
         }
      }else{
         hs.onload = function(){
            if(d.readyState == 'complete'){
               hs.onload = null;
               o._loading.extract(id);
               o._loaded.push(id);
            }
         }
      }
      h.appendChild(hs);
   }
}
function RLoader_loadJs(ps){
   var as = arguments;
   var c = as.length;
   for(var n = 0; n < c; n++){
      var p = as[n];
      this.loadJsFile('js:' + p, '/ajs/' + p.replace(/\./g, '/') + '.js');
   }
}
function RLoader_loaded(id){
   var o = this;
   o._loading.extract(id);
   o._loaded.push(id);
}
function RLoader_wait(invoke, ids){
   var o = this;
   var l = new TLoaderListener();
   l.invoke = invoke;
   var c = arguments.length;
   for(var n = 1; n < c; n++){
      l.ids.push(arguments[n]);
   }
   o._waits.push(l);
   o.intervalStart();
}
function RLoader_waitJs(invoke, ids){
   var o = this;
   var l = new TLoaderListener();
   l.invoke = invoke;
   var as = arguments;
   var c = as.length;
   for(var n = 1; n < c; n++){
      l.ids.push('js:' + as[n]);
   }
   o._waits.push(l);
   o.intervalStart();
}
var RLogger = new function(){
   var o = this;
   o.LEVEL_DEBUG = 0;
   o.LEVEL_INFO  = 1;
   o.LEVEL_WARN  = 2;
   o.LEVEL_ERROR = 3;
   o.LEVEL_FATAL = 4;
   o.dumpLevel   = 20;
   o.colors      = ['#FFFFFF', '#E0F0F0', '#F0E0F0', '#F0F0E0'];
   o.canDebug    = RLogger_canDebug;
   o.stack       = RLogger_stack;
   o.debug       = RLogger_debug;
   o.info        = RLogger_info;
   o.warn        = RLogger_warn;
   o.error       = RLogger_error;
   o.fatal       = RLogger_fatal;
   o.log         = RLogger_log;
   o.dump        = RLogger_dump;
   RMemory.register('RLogger', o);
   return o;
}
var RLog = RLogger;
function RLogger_canDebug(){
   return ERun.Debug == RClass.mode
}
function RLogger_stack(){
   var o = RLogger_stack.caller;
   var s = new TString();
   while(o){
      s.append(RMethod.name(o));
      o = o.caller;
      if(o){
         s.appendLine();
      }
   }
   this.log(ELogger.Debug, s, arguments);
   return s;
}
function RLogger_debug(){
   var o = RLogger_debug.caller;
   var s = new TString();
   var n = this.dumpLevel;
   while(o && n>0){
      s.append(RMethod.name(o).replace('_', '.'));
      o = o.caller;
      if(o){
         s.append(' > ');
      }
      n--;
   }
   this.log(ELogger.Debug, RMethod.name(RLogger_debug.caller), 0, arguments, s);
}
function RLogger_info(){
   var o = RLogger_info.caller;
   var s = new TString();
   var n = this.dumpLevel;
   while(o && n>0){
      s.append(RMethod.name(o).replace('_', '.'));
      o = o.caller;
      if(o){
         s.append(' > ');
      }
      n--;
   }
   this.log(ELogger.Info, RMethod.name(RLogger_info.caller), 0, arguments, s);
}
function RLogger_warn(){
   var o = RLogger_warn.caller;
   var s = new TString();
   var n = this.dumpLevel;
   while(o && n>0){
      s.append(RMethod.name(o).replace('_', '.'));
      o = o.caller;
      if(o){
         s.append(' > ');
      }
      n--;
   }
   this.log(ELogger.Warn, RMethod.name(RLogger_warn.caller), 0, arguments, s);
}
function RLogger_error(){
   var o = RLogger_error.caller;
   var s = new TString();
   var n = this.dumpLevel;
   while(o && n>0){
      s.append(RMethod.name(o).replace('_', '.'));
      o = o.caller;
      if(o){
         s.append(' > ');
      }
      n--;
   }
   this.log(ELogger.Error, RMethod.name(RLogger_error.caller), 0, arguments, s);
}
function RLogger_fatal(){
   var o = RLogger_fatal.caller;
   var s = new TString();
   var n = this.dumpLevel;
   while(o && n>0){
      s.append(RMethod.name(o).replace('_', '.'));
      o = o.caller;
      if(o){
         s.append(' > ');
      }
      n--;
   }
   this.log(ELogger.Fatal, RMethod.name(RLogger_fatal.caller), 0, arguments, s);
}
function RLogger_log(level, method, ms, args, stack){
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
   RConsole.find(FLoggerConsole).output(level, obj, method, ms, msg, stack);
}
function RLogger_dump(level, obj){
   RConsole.find(FLoggerConsole).output(level, obj, method, 0, RClass.dump(obj));
}
var RMessage = new function(o){
   o = moNvl(o, this);
   o.hasError = false;
   o.messages = null;
   o.push              = RMessage_push;
   o.fatal             = RMessage_fatal;
   o.confirmResult     = false;
   o.error             = RMessage_error;
   o.warn              = RMessage_warn;
   o.onWindowClose     = RMessage_onWindowClose;
   o.confirm           = RMessage_confirm;
   o.info              = RMessage_info;
   RMemory.register('RMessage', o);
   return o;
}
function RMessage_push(msg){
   if(!this.messages){
      this.messages = new FLoopList();
   }
   this.messages.push(msg);
}
function RMessage_fatal(sf, er, ms, pm){
   var o = this;
   if(o.hasError){
      return;
   }
   o.hasError = true;
   var s = new TString();
   var t = new Array();
   var f = RMessage_fatal.caller;
   while(f){
      if(RArray.contains(t, f)){
         break;
      }
      t.push(f);
      f = f.caller;
   }
   var c = t.length;
   for(var n = 0; n < c; n++){
      f = t[n];
      if(n > 0){
         s.appendLine();
      }
      s.append('   ' + (c - n) + ': ' + RMethod.name(f));
   }
   var m = new TString();
   m.appendLine(RContext.get('RMessage:fatal'));
   m.appendLine(RString.repeat('-', 60));
   m.append(RClass.dump(sf), ': ');
   if(ms){
      var ag = arguments;
      c = ag.length;
      for(var n = 3; n < c; n++){
         var p = ag[n];
         if('function' == typeof(p)){
            p = RMethod.name(p);
         }
         var pi = n - 2;
         ms = ms.replace('{' + pi + '}', p);
      }
   }
   m.appendLine(ms);
   m.appendLine(RString.repeat('-', 60));
   m.appendLine('Stack:');
   m.append(s);
   alert(m);
}
function RMessage_error(self, method, msg, params){
   if(this.hasError){
      return;
   }
   this.hasError = true;
   throw new Error(msg);
}
function RMessage_warn(self, message, params){
   var s = new TString();
   var n = 0;
   var aw = top.RControl.create(FAlertWindow);
   aw.setText(message);
   aw.show();
}
function RMessage_info(self, message, params){
   var s = new TString();
   var n = 0;
   var aw = top.RControl.create(FInfoWindow);
   aw.setText(message);
   aw.show();
}
function RMessage_confirm(message,callback){
   var o = this;
   var ls = top.RControl.create(FConfirmWindow);
   ls.setText(message);
   ls.lsns.register(o, callback);
   ls.show();
}
function RMessage_onWindowClose(v){
   this.confirmResult = v;
}
var RMethod = new function(){
   var o = this;
   o.virtuals   = new Object();
   o.events     = new Object();
   o.isFunction = RMethod_isFunction;
   o.isEmpty    = RMethod_isEmpty;
   o.isVirtual  = RMethod_isVirtual;
   o.name       = RMethod_name;
   o.fullName   = RMethod_fullName;
   o.empty      = RMethod_empty;
   o.emptyTrue  = RMethod_emptyTrue;
   o.emptyFalse = RMethod_emptyFalse;
   o.emptyCall  = RMethod_emptyCall;
   o.virtual    = RMethod_virtual;
   o.empty._empty = true;
   o.emptyTrue._empty = true;
   o.emptyFalse._empty = true;
   RMemory.register('RMethod', o);
   return o;
}
function RMethod_isFunction(v){
   return 'function' == typeof(v);
}
function RMethod_isEmpty(v){
   return (v && v._empty);
}
function RMethod_isVirtual(v){
   return (v && v._virtual);
}
function RMethod_name(v){
   return ('function' == typeof(v)) ? RString.mid(v.toString(), 'function ', '(') : null;
}
function RMethod_fullName(v){
   return ('function' == typeof(v)) ? RString.mid(v.toString(), 'function ', ')') + ')' : null;
}
function RMethod_empty(){
}
function RMethod_emptyTrue(){
   return true;
}
function RMethod_emptyFalse(){
   return false;
}
function RMethod_emptyCall(){
}
function RMethod_virtual(v, m){
   var o = this;
   var n = RClass.name(v) + '.' + m;
   if(o.virtuals[n]){
      return o.virtuals[n];
   }
   var f = function(){throw new Error('Virtual method be called.(' + n + ')');};
   f._virtual = true;
   f._name = n;
   o.virtuals[n] = f;
   return f;
}
var RObject = new function(){
   var o = this;
   o.nvl     = RObject_nvl;
   o.nvlObj  = RObject_nvlObj;
   o.clone   = RObject_clone;
   o.copy    = RObject_copy;
   RMemory.register('RObject', o);
   return o;
}
function RObject_nvl(v){
   for(var n=0; n<arguments.length; n++){
      if(null != arguments[n]){
         return arguments[n];
      }
   }
   return null;
}
function RObject_nvlObj(v){
   return v ? v : new Object();
}
function RObject_clone(o){
   var r = new o.constructor();
   for(var n in o){
      var v = o[n];
      if(null != v){
         var c = v.constructor;
         if(c != Boolean && c != Date && c != String && c != Number && c != Function){
            v = this.clone(v);
         }
      }
      r[n] = v;
   }
   return r;
}
function RObject_copy(s, t){
   if(null!=s && null!=t){
      for(var n in s){
         var v = s[n];
         if(null != v){
            var c = v.constructor;
            if(c != Boolean && c != Date && c != String && c != Number && c != Function){
               if(null == t[n]){
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
var RResource = new function(o){
   if(!o){o=this};
   o.uriIcon     = '/ars/icon/';
   o.uriImage    = '/ars/img/';
   o.iconPath    = RResource_iconPath;
   o.iconUrlPath = RResource_iconUrlPath;
   o.imagePath   = RResource_imagePath;
   RMemory.register('RResource', o);
   return o;
}
var RRes = RResource;
function RResource_iconPath(path, type){
   var o = this;
   var rc = top.RContext;
   path = RString.nvl(path, 'n').replace(/\./g, '/') + '.' + RString.nvl(type, 'gif');
   if(RString.startsWith(path, '#')){
      path = path.substr(1);
      return rc.context(rc.uriIcon + '/' + path);
   }
   return rc.context('/ars/icon/' + path);
}
function RResource_iconUrlPath(path, type){
   var o = this;
   var rc = top.RContext;
   path = RString.nvl(path, 'n').replace(/\./g, '/') + '.' + RString.nvl(type, 'gif');
   if(RString.startsWith(path, '#')){
      path = path.substr(1);
      return 'url(' + rc.context(rc.uriIcon + '/' + path) + ')';
   }
   return 'url(' + rc.context('/ars/icon/' + path) + ')';
}
function RResource_imagePath(path, type){
   var o = this;
   var rc = top.RContext;
   path = RString.nvl(path, 'n').replace(/\./g, '/') + '.' + RString.nvl(type, 'gif');
   if(RString.startsWith(path, '#')){
      path = path.substr(1);
      return rc.context(rc.uriImage + '/' + path);
   }
   return rc.context('/ars/img/' + path);
}
var RString = new function(){
   var o = this;
   o.EMPTY        = '';
   o.SPACE        = '   ';
   o.PAD          = ' ';
   o.TRIM         = ' \t\r\n';
   o.LOWER        = 'abcdefghijklmnopqrstuvwxyz';
   o.UPPER        = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ';
   o.CodeLowerA   = 'a'.charCodeAt(0);
   o.CodeLowerZ   = 'z'.charCodeAt(0);
   o.CodeUpperA   = 'A'.charCodeAt(0);
   o.CodeUpperZ   = 'Z'.charCodeAt(0);
   o.isEmpty      = RString_isEmpty;
   o.isBlank      = RString_isBlank;
   o.isAnsi       = RString_isAnsi;
   o.isDbcs       = RString_isDbcs;
   o.isPattern    = RString_isPattern;
   o.inChars      = RString_inChars;
   o.contains     = RString_contains;
   o.equals       = RString_equals;
   o.startsWith   = RString_startsWith;
   o.endsWith     = RString_endsWith;
   o.findChars    = RString_findChars;
   o.inRange      = RString_inRange;
   o.nvl          = RString_nvl;
   o.nvlStr       = RString_nvlStr;
   o.firstUpper   = RString_firstUpper;
   o.firstLower   = RString_firstLower;
   o.firstLine    = RString_firstLine;
   o.format       = RString_format;
   o.repeat       = RString_repeat;
   o.pad          = RString_pad;
   o.lpad         = RString_lpad;
   o.rpad         = RString_rpad;
   o.trim         = RString_trim;
   o.ltrim        = RString_ltrim;
   o.rtrim        = RString_rtrim;
   o.mid          = RString_mid;
   o.toLine       = RString_toLine;
   o.toLower      = RString_toLower;
   o.toUpper      = RString_toUpper;
   o.split        = RString_split;
   o.splitTwo     = RString_splitTwo;
   o.splitParts   = RString_splitParts;
   o.splitPattern = RString_splitPattern;
   o.remove       = RString_remove;
   o.removeChars  = RString_removeChars;
   RMemory.register('RString', o);
   return o;
}
function RString_isEmpty(v){
   return (null == v) ? true : !(v.length);
}
function RString_isBlank(v){
   return (null == v) ? true : !(v.trim().length);
}
function RString_isAnsi(v){
   if(null != v){
      var c = v.length;
      for(var n=0; n<c; n++){
         if(v.charCodeAt(n) > 255){
            return false;
         }
      }
      return true;
   }
   return false;
}
function RString_isDbcs(v){
   if(null == v){
      var c = v.length;
      for(var n=0; n<c; n++){
         if(value.charCodeAt(n) < 256){
            return false;
         }
      }
      return true;
   }
   return false;
}
function RString_isPattern(v, p){
   if(null != v){
      var o = this;
      if(null == p){
         p = '$a$A$f';
      }
      p = p.replace(/\a/g, o.LOWER);
      p = p.replace(/\A/g, o.UPPER);
      p = p.replace(/\f/g, RFloat.NUMBER);
      p = p.replace(/\n/g, RInt.NUMBER);
      var c = v.length;
      for(var n=0; n<c; n++){
         if(-1 == p.indexOf(v.charAt(n))){
            return false;
         }
      }
      return true;
   }
   return false;
}
function RString_inChars(v, p){
   var o = this;
   var b = o.findChars(p, v);
   if(-1 != b){
      return true;
   }
   return false;
}
function RString_contains(v, s){
   return (null != v && null != s) ? (-1 != v.toString().indexOf(s)) : false;
}
function RString_equals(s, t, f){
   if(null == s || null == t){
      return false;
   }
   s += '';
   t += '';
   return f ? (s == t) : (s.toLowerCase()==t.toLowerCase());
}
function RString_startsWith(v, s){
   if(null == s){
      return true;
   }
   return (null != v) ? (0 == v.indexOf(s)) : false;
}
function RString_endsWith(v, s){
   if(null == s){
      return true;
   }
   var n = (null != v) ? v.indexOf(s) : -1;
   return (-1 != n) ? (n == (v.length - s.length)) : false;
}
function RString_findChars(v, s){
   if(null != v && null != s){
      var c = v.length;
      for(var n=0; n<c; n++){
         if(-1 != s.indexOf(v.charAt(n))){
            return n;
         }
      }
   }
   return -1;
}
function RString_inRange(v, rs, f){
   if(v && rs){
      if(!f){
         v = v.toLowerCase();
      }
      var c = rs.length;
      for(var n=0; n<c; n++){
         var r = rs[n];
         if(null != r){
            if(f && (v == r)){
               return true;
            }else if(!f && (v == r.toLowerCase())){
               return true;
            }
         }
      }
   }
   return false;
}
function RString_nvl(v, d){
   if('string' == typeof(v)){
      if(v.length > 0){
         return v;
      }
   }
   return (null != d) ? d : this.EMPTY;
}
function RString_nvlStr(v){
   if(!v){
      v = new TString();
   }
   return v;
}
function RString_firstUpper(v){
   return (null != v) ? v.charAt(0).toUpperCase() + v.substr(1) : v;
}
function RString_firstLower(){
   return (null != v) ? v.charAt(0).toLowerCase() + v.substr(1) : v;
}
function RString_firstLine(v){
   if(v){
      var n = Math.min(v.indexOf('\r'), v.indexOf('\n'));
      if(-1 != n){
         return v.substr(0, n);
      }
      return v;
   }
   return '';
}
function RString_format(s, p){
   var a = arguments;
   var c = a.length;
   for(var n=1; n<c; n++){
      var p = a[n];
      if('function' == typeof(p)){
         p = RMethod.name(p);
      }else if(null == p){
         p = '';
      }
      s = s.replace('{' + (n-1) + '}', p);
   }
   return s;
}
function RString_repeat(v, c){
   return new Array(c+1).join(v);
}
function RString_pad(v, l, p){
   v = (null != v) ? v.toString() : this.EMPTY;
   var n = l - v.length;
   if(n > 0){
      if(null == p){
         p = this.PAD;
      }
      var r = (n%2 == 0) ? n/2 : (n-1)/2;
      return new Array(r+1).join(p) + v + new Array(n-r+1).join(p);
   }
   return v;
}
function RString_lpad(v, l, p){
   var o = this;
   v = (null != v) ? v.toString() : o.EMPTY;
   var n = l - v.length;
   if(n > 0){
      if(null == p){
         p = o.PAD;
      }
      var a = new Array(n);
      a[a.length] = v;
      return a.join(p);
   }
   return v;
}
function RString_rpad(v, l, p){
   var o = this;
   v = (null != v) ? v.toString() : o.EMPTY;
   var n = l - v.length;
   if(n > 0){
      if(null == p){
         p = o.PAD;
      }
      return v + new Array(n + 1).join(p);
   }
   return v;
}
function RString_trim(v, ts){
   var o = this;
   v = o.nvl(v);
   ts = o.nvl(ts, o.TRIM);
   var l = 0;
   var r = v.length - 1;
   for(; l<r; l++){
      if(-1 == ts.indexOf(v.charAt(l))){
         break;
      }
   }
   for(; r>=l; r--){
      if(-1 == ts.indexOf(v.charAt(r))){
         break;
      }
   }
   if(0 != l || r != v.length-1){
      return v.substring(l, r + 1);
   }
   return v;
}
function RString_ltrim(v, ts){
   var o = this;
   v = o.nvl(value);
   ts = o.nvl(trims, o.TRIM);
   var l = 0;
   var r = v.length - 1;
   for(; l<r; l++){
      if(-1 == ts.indexOf(v.charAt(l))){
         break;
      }
   }
   if(0 != l){
      return v.substring(l, r + 1);
   }
   return v;
}
function RString_rtrim(v, ts){
   var o = this;
   v = o.nvl(v);
   ts = o.nvl(ts, o.TRIM);
   var r = v.length - 1;
   for(; r>=0; r--){
      if(-1 == ts.indexOf(v.charAt(r))){
         break;
      }
   }
   if(r != v.length-1){
      return v.substring(0, r + 1);
   }
   return v;
}
function RString_mid(v, s, e){
   if(null != v){
      var l = 0;
      var r = v.length;
      if(null != s){
         l = v.indexOf(s);
         l = (-1 == l) ? 0 : l + s.length;
      }
      if(null != e){
         r = v.indexOf(e, l);
         r = (-1 == r) ? v.length : r;
      }
      return v.substring(l, r);
   }
}
function RString_toLine(v){
   return v.replace(/\n/g, '\\n').replace(/\r/g, '\\r').replace(/\t/g, '\\t')
}
function RString_toLower(v){
   return (null != v) ? v.toLowerCase() : this.EMPTY;
}
function RString_toUpper(v){
   return v ? v.toUpperCase() : this.EMPTY;
}
function RString_split(s, p){
   return (s && p) ? s.split(p) : null;
}
function RString_splitTwo(s, p){
   if(s && p){
      var r = new Array();
      var n = s.indexOf(p);
      if(-1 == n){
         r.push(s);
      }else{
         r.push(s.substring(0, n));
         r.push(s.substring(n+p.length));
      }
      return r;
   }
   return null;
}
function RString_splitParts(s, p){
   var b=new Array();
   var k=0;
   for(var i=0;i<s.length;i++){
      for(var j in p){
         if(RString.startsWith(p[j],s.charAt(i))){
            if(RString.equals(s.substr(i,p[j].length),p[j])){
               b[k++]=p[j];
               i=i+p[j].length - 1;
               break;
            }
         }
      }
   }
   return b;
}
function RString_splitPattern(s, p){
   var r = new Array();
   if(s){
      var sl = s.length;
      var pl = p.length;
      var t = '';
      for(var n=0; n<sl; n++){
         var v = false;
         for(var i=0; i<pl; i++){
            var f = p[i];
            if(0 == s.indexOf(f)){
               if(t.length){
                  r[r.length] = t;
                  t = '';
               }
               r[r.length] = f;
               s = s.substring(f.length);
               v = true;
               break;
            }
         }
         if(!v){
            t += s.charAt(0);
            s = s.substring(1);
         }
      }
   }
   return r;
}
function RString_remove(s, t){
   return s.replace(t, '');
}
function RString_removeChars(v, s){
   if(null != v){
      var c = v.length;
      var r = new Array();
      for(var n=0; n<c; n++){
         var a = v.charAt(n);
         if(-1 != s.indexOf(a)){
            continue;
         }
         r[r.length] = a;
      }
      return r.join('');
   }
   return v;
}
var RStringBuffer = new function(){
   var o = this;
   o.nvl         = RStringBuffer_nvl;
   RMemory.register('RStringBuffer', o);
   return o;
}
function RStringBuffer_nvl(v){
   return v ? v : new TString();
}
var RWindow = new function(){
   var o = this;
   o._builder          = null;
   o._disableDeep      = 0;
   o.panels            = new TMap();
   o.inDisable         = false;
   o.inMoving          = false;
   o.inSizing          = false;
   o.hWindow           = null;
   o.hDocument         = null;
   o.hBody             = null;
   o.hContainer        = null;
   o.hDisablePanel     = null;
   o.hShadow           = null;
   o.lsnsLoad          = new TListeners();
   o.lsnsUnload        = new TListeners();
   o.lsnsMouseDown     = new TListeners();
   o.lsnsMouseUp       = new TListeners();
   o.lsnsMouseOver     = new TListeners();
   o.lsnsMouseMove     = new TListeners();
   o.lsnsMouseWheel    = new TListeners();
   o.lsnsKeyDown       = new TListeners();
   o.lsnsKeyUp         = new TListeners();
   o.lsnsKeyPress      = new TListeners();
   o.lsnsResize        = new TListeners();
   o.onUnload          = RWindow_onUnload;
   o.onResize          = RWindow_onResize;
   o.connect           = RWindow_connect;
   o.createElement     = RWindow_createElement;
   o.createHttpRequest = RWindow_createHttpRequest;
   o.createBuilder     = RWindow_createBuilder;
   o.event             = RWindow_event;
   o.source            = RWindow_source;
   o.builder           = RWindow_builder;
   o.getElement        = RWindow_getElement;
   o.getDisablePanel   = RWindow_getDisablePanel;
   o.findElement       = RWindow_findElement;
   o.panel             = RWindow_panel;
   o.screenPos         = RWindow_screenPos;
   o.clientPos         = RWindow_clientPos;
   o.offsetPos         = RWindow_offsetPos;
   o.windowEnable      = RWindow_windowEnable;
   o.windowDisable     = RWindow_windowDisable;
   o.enable            = RWindow_enable;
   o.disable           = RWindow_disable;
   o.setCaption        = RWindow_setCaption;
   o.setEnable         = RWindow_setEnable;
   o.showShadow        = RWindow_showShadow;
   o.moveCenter        = RWindow_moveCenter;
   o.appendControl     = RWindow_appendControl;
   o.appendElement     = RWindow_appendElement;
   o.appendContainer   = RWindow_appendContainer;
   o.containerTop      = RWindow_containerTop;
   o.dispose           = RWindow_dispose;
   RMemory.register('RWindow', o);
   return o;
}
function RWindow_onUnload(){
   RMemory.release();
}
function RWindow_onResize(){
   var o = this;
   var h = o.hDisablePanel;
   if(h){
      if('block' == h.style.display){
         var s = h.style;
         var hd = o.hDocument;
         s.pixelLeft = 0;
         s.pixelTop = 0
         s.pixelWidth = hd.all ? o.hBody.scrollWidth : hd.documentElement.scrollWidth;
         s.pixelHeight = hd.all ? o.hBody.scrollHeight : hd.documentElement.scrollHeight;
      }
   }
}
function RWindow_connect(w){
   var o = this;
   o.hWindow = w;
   var hd = o.hDocument = w.document;
   var hb = o.hBody = o.hContainer = hd.body;
   o.processUnload = hb.onunload;
   hb.onunload = function(e){
      if(!e){
         e = o.hWindow.event;
      }
      o.lsnsUnload.process(o, e);
      o.onUnload();
   };
   hb.onmousedown = function(e){
      if(!e){
         e = o.hWindow.event;
      }
      RLogger.debug(o, '[D] onmousedown = ' + e.x + ' - ' + e.y);
      o.lsnsMouseDown.process(o, e);
   };
   hb.onmouseup = function(e){
      if(!e){
         e = o.hWindow.event;
      }
      o.lsnsMouseUp.process(o, e);
   };
   hb.onmousemove = function(e){
      if(!e){
         e = o.hWindow.event;
      }
      o.lsnsMouseMove.process(o, e);
   };
   hb.onmouseover = function(e){
      if(!e){
         e = o.hWindow.event;
      }
      o.lsnsMouseOver.process(o, e);
   };
   hb.onmousewheel = function(e){
      if(!e){
         e = o.hWindow.event;
      }
      o.lsnsMouseWheel.process(o, e);
   };
   hb.onkeydown = function(e){
      if(!e){
         e = o.hWindow.event;
      }
      RLogger.debug(o, '[D] onkeydown = ' + e.keyCode);
      var s = e.srcElement ? e.srcElement : e.target;
      var t = s.tagName;
      if(EKey.BackSpace == e.keyCode){
         if('INPUT' == t){
            if(s.readOnly || 'checkbox' == s.type){
               return RKey.eventClear(e);
            }
         }else if('TEXTAREA' == t){
            if(s.readOnly){
               return RKey.eventClear(e);
            }
         }else{
            return RKey.eventClear(e);
         }
      }
      o.lsnsKeyDown.process(o, e);
      if(EKey.Enter == e.keyCode){
         if('INPUT' == t){
            if(REvent.process(s, e)){
               RKey.eventClear(e);
            }
         }
      }
   };
   hb.onkeyup = function(e){
      if(!e){
         e = o.hWindow.event;
      }
      o.lsnsKeyUp.process(o, e);
   };
   hb.onkeypress = function(e){
      if(!e){
         e = o.hWindow.event;
      }
      RLogger.debug(o, '[D] onkeypress = ' + e.keyCode);
      o.lsnsKeyPress.process(o, e);
   };
   hb.onresize = function(e){
      if(!e){
         e = o.hWindow.event;
      }
      if(o.oldBodyWidth == o.hBody.offsetWidth && o.oldBodyHeight == o.hBody.offsetHeight){
         return;
      }
      o.oldBodyWidth = o.hBody.offsetWidth;
      o.oldBodyHeight = o.hBody.offsetHeight;
      o.onResize();
      o.lsnsResize.process(o, e);
   };
}
function RWindow_createElement(n){
   return this.hDocument.createElement(n);
}
function RWindow_createHttpRequest(){
   if(this.hWindow.XMLHttpRequest){
      return new XMLHttpRequest();
   }else if(this.hWindow.ActiveXObject){
      return new ActiveXObject("MsXml2.XmlHttp");
   }
}
function RWindow_createBuilder(){
   var o = this;
   var b = new TBuilder();
   b.hWindow = o.hWindow;
   b.hDocument = o.hDocument;
   return b;
}
function RWindow_event(){
   return this.hWindow.event;
}
function RWindow_source(h){
   return h ? h.ownerDocument.parentWindow.event.srcElement : this.hWindow.event.srcElement;
}
function RWindow_builder(){
   var o = this;
   if(!o._builder){
      o._builder = o.createBuilder();
   }
   return o._builder;
}
function RWindow_getElement(n){
   var o = this;
   var e = o.hDocument.getElementById(n);
   if(!e){
      RMessage.fatal(o, null, "Can't get html element. (name={0})", n);
   }
   return e;
}
function RWindow_getDisablePanel(f){
   var o = this;
   var h = o.hDisablePanel;
   if(!h){
      var h = o.hDisablePanel = o.builder().newDiv();
      h.style.backgroundColor = "#CCCCCC";
      h.style.position = 'absolute';
      h.style.filter = "progid:DXImageTransform.Microsoft.Alpha(opacity=60)";
      o.hBody.appendChild(h);
      h.style.zIndex = 8000;
      h.style.display = 'none';
   }
   var hImg = o.hImg;
   if(!hImg){
      hImg = o.hImg = o.builder().appendImage(h);
      hImg.src = top.RContext.context('/ats/00/rs/icon/ctl/RWindow_Loading.gif');
      hImg.style.margin = document.body.offsetHeight / 2;
      hImg.style.display = 'none';
   }
   if(f){
      hImg.style.display = 'none';
   }else{
      hImg.style.display = 'block';
   }
   return h;
}
function RWindow_findElement(n){
   return this.hDocument.getElementById(n);
}
function RWindow_panel(t){
   var o = this;
   if(EPanel.Disable == t){
      var h = o.hDisablePanel;
      if(!h){
         h = o.hDisablePanel = RBuilder.append(o.hBody, 'DIV', 'RWindow_Disable');
         var hi = RBuilder.append(h, 'IMG')
         hi.src = RRes.iconPath('#ctl.RWindow_Loading');
         hi.style.margin = document.body.offsetHeight / 2;
         h.style.zIndex = ELayer.Disable;
      }
      return h;
   }
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
function RWindow_clientPos(p){
   var e = this.hWindow.event;
   if(p){
      p.x = e.clientX;
      p.y = e.clientY;
      return p;
   }
   return new TPoint(e.clientX, e.clientY);
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
function RWindow_windowDisable(){
   this.hWindow.document.body.disabled = true;
}
function RWindow_windowEnable(){
   this.hWindow.document.body.disabled = false;
}
function RWindow_enable(){
   var o = this;
   o._disableDeep--;
   if(0 == o._disableDeep){
      o.setEnable(true);
   }
}
function RWindow_disable(){
   var o = this;
   if(0 == o._disableDeep){
      o.setEnable(false);
   }
   o._disableDeep++;
}
function RWindow_setCaption(t){
   top.document.title = t;
}
function RWindow_setEnable(v, f){
   var o = this;
   var h = o.getDisablePanel(f);
   var st = h.style;
   if(!v){
      var s = o.hDisablePanel.style;
      s.pixelLeft = 0;
      s.pixelTop = 0
      s.pixelWidth = o.hDocument.all ? o.hBody.scrollWidth : o.hDocument.documentElement.scrollWidth;
      s.pixelHeight = o.hDocument.all ? o.hBody.scrollHeight : o.hDocument.documentElement.scrollHeight;
      s.display = 'block';
   }else{
      o.windowEnable();
      st.display = 'none';
   }
}
function RWindow_showShadow(v, r){
   var o = this;
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
function RWindow_moveCenter(h){
   var o = this;
   if(h){
      h.style.pixelLeft = Math.max(parseInt((o.hBody.offsetWidth - h.offsetWidth)/2), 0);
      h.style.pixelTop = Math.max(parseInt((o.hBody.offsetHeight - h.offsetHeight)/2), 0) + o.hBody.scrollTop;
   }
}
function RWindow_appendControl(ctl){
   this.hBody.appendChild(ctl.hPanel);
}
function RWindow_appendElement(h){
   this.hBody.appendChild(h);
}
function RWindow_appendContainer(h){
   this.hContainer.appendChild(h);
}
function RWindow_containerTop(h){
   var o = this;
   var hc = o.hContainer;
   var r = RHtml.top(h) + h.offsetHeight;
   if('auto' == hc.currentStyle.overflow){
      r -= RHtml.top(hc);
   }
   return r - hc.scrollTop;
}
function RWindow_dispose(){
   var o = this;
   o.hBody.onload = null;
   o.hBody.onunload = null;
   o.hBody.onmousedown = null;
   o.hBody.onmouseup = null;
   o.hBody.onmousemove = null;
   o.hBody.onmouseover = null;
   o.hBody.onmousewheel = null;
   o.hBody.onkeydown = null;
   o.hBody.onkeyup = null;
   o.hBody.onkeypress = null;
   o.hBody.onresize = null;
   RMemory.freeHtml(o.hBody);
   o.panels.release();
   o.panels = null;
   o.hWindow = null;
   o.hDocument = null;
   o.hBody = null;
   o.hDisablePanel = null;
   o.hImg = null;
   o.hShadow = null;
}
var RXml = new function(){
   var o = this;
   o.httpActiveX  = false;
   o.httpVendor   = null;
   o.domActiveX   = false;
   o.domVendor    = null;
   o.construct    = RXml_construct;
   o.isNode       = RXml_isNode;
   o.newConnect   = RXml_newConnect;
   o.newDocument  = RXml_newDocument;
   o.loadString   = RXml_loadString;
   o.makeNode     = RXml_makeNode;
   o.makeDocument = RXml_makeDocument;
   o.buildNode    = RXml_buildNode;
   o.fromText     = RXml_fromText;
   o.buildText    = RXml_buildText;
   o.unpack       = RXml_unpack;
   o.construct();
   RMemory.register('RXml', o);
   return o;
}
function RXml_construct(){
   var o = this;
   var d = window.document;
   if(window.ActiveXObject && !window.XMLHttpRequest){
      var vs = ["MSXml2.XmlHTTP", "Microsoft.XmlHTTP", "MSXml.XmlHTTP", "MSXml3.XmlHTTP"];
      var c = vs.length;
      for(var n = 0; n < c; n++){
         var v = vs[n];
         try{
            r = new ActiveXObject(v);
            o.httpActiveX = true;
            o.httpVendor = v;
            break;
         }catch(e){
            m = e;
         }
      }
   }else if(window.XMLHttpRequest){
      try{
         var r = new XMLHttpRequest();
         o.httpActiveX = false;
      }catch(e){
         m = e;
      }
   }else{
      alert('Unknown http vendor.');
   }
   if(window.ActiveXObject || !window.DOMParser){
      var vs = ["MSXml2.DOMDocument", "Microsoft.XmlDOM", "MSXml.DOMDocument", "MSXml3.XmlDOM"];
      var c = vs.length;
      for(var n = 0; n < c; n++){
         var v = vs[n];
         try{
            var r = new ActiveXObject(v);
            o.domActiveX = true;
            o.domVendor = v;
            break;
         }catch(e){
            m = e;
         }
      }
   }else if(window.DOMParser && d && d.implementation && d.implementation.createDocument){
      try{
         var r = document.implementation.createDocument('', '', null);
         o.domActiveX = false;
      }catch(e){
         m = e;
      }
   }else{
      alert('Unknown dom vendor.');
   }
}
function RXml_isNode(n){
   return RClass.isName(n, 'TNode');
}
function RXml_newConnect(){
   var o = this;
   var r = null;
   if(o.httpActiveX){
      r = new ActiveXObject(o.httpVendor);
   }else{
      r = new XMLHttpRequest();
   }
   if(!r){
      alert('Create xml connection failure. (message=' + m + ')');
   }
   return r;
}
function RXml_newDocument(){
   var o = this;
   var r = null;
   if(o.domActiveX){
      r = new ActiveXObject(o.domVendor);
   }else{
      r = document.implementation.createDocument('', '', null);
   }
   if(!r){
      alert('Create xml document failure. (message=' + m + ')');
   }
   return r;
}
function RXml_loadString(s){
   var o = this;
   var x = null;
   if(o.domActiveX){
      x = new ActiveXObject(o.domVendor);
      x.async = false;
      x.loadXML(s);
   }else{
      var p = new DOMParser();
      x = p.parseFromString(s, 'text/xml');
   }
   return x;
}
function RXml_makeNode(p){
   var o = this;
   if(p.documentElement){
      var d = new TXmlDocument();
      o.buildNode(d, null, p.documentElement);
      return d.node;
   }else if(p.tagName == 'SCRIPT'){
      var s = p.textContent;
      if(!s){
         s = p.text;
      }
      if(s){
         var d = new TXmlDocument();
         var xd = o.loadString(s)
         o.buildNode(d, null, xd.documentElement);
         return d.node;
      }
   }
   return null;
}
function RXml_makeDocument(xdoc){
   var doc = new TXmlDocument();
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
         for(var n = 0; n < len; n++){
            var attr = element.attributes[n];
            if(attr.nodeName){
               attrs.set(attr.nodeName, RXml.fromText(attr.value));
            }
         }
      }
   }
   var text = new TString();
   text.append(element.value);
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
function RXml_unpack(s, n){
   var o = this;
   if(RString.isEmpty(s)){
      return null;
   }
   if(!n){
      n = new TNode();
   }
   var np = new TAttributes();
   np.unpack(s);
   n.name = np.get('name');
   n.value = np.get('value');
   if(np.contains('attributes')){
      n.attributes().unpack(np.get('attributes'));
   }
   if(np.contains('nodes')){
      var ns = new TStrings();
      ns.unpack(np.get('nodes'));
      for(var i=0; i<ns.count; i++){
         o.unpack(ns.get(i), n.create());
      }
   }
   return n;
}
