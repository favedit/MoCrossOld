function EActiveFace(){
   var o = this;
   o.Sleep  = 0;
   o.Active = 1;
   o.Cancel = 2;
   o.Finish = 3;
   return o;
}
var EActive = new EActiveFace();
function EAnnotationFace(){
   var o = this;
   o.Property  = 'property';
   o.Event     = 'event';
   o.Style     = 'style';
   o.StyleIcon = 'icon';
   return o;
}
var EAnnotation = new EAnnotationFace();
function EBoolFace(){
   var o = this;
   o.True   = 'Y';
   o.False  = 'N';
   return o;
}
var EBool = EBoolean = new EBoolFace();
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
   o.D           = 68;
   o.ControlKeys = [
      o.Tab, o.Enter, o.BackSpace, o.Shift, o.Left, o.Up, o.Right, o.Down,
      o.Insert, o.Delete, o.Home, o.End, o.PageUp, o.PageDown,o.Ctrl,
      o.F1, o.F2, o.F3, o.F4, o.F5, o.F6, o.F7, o.F8, o.F9, o.F10, o.F11, o.F12];
   o.floatCodes  = new Object();
   o.construct   = EKey_construct;
   o.construct();
   return o;
}
var EKey = new EKeyFace();
function EKey_construct(){
   var o = this;
   var f = o.floatCodes;
   f[o.Tab] = true;
   f[o.Enter] = true;
   f[o.BackSpace] = true;
   f[o.Left] = true;
   f[o.Right] = true;
   f[o.Esc] = true;
   f[o.Delete] = true;
   f[o.Home] = true;
   f[o.End] = true;
   f[45] = true;
   f[190] = true;
   f[46] = true;
   f[189] = true;
   for(var n=48; n<=57; n++){
      f[n] = true;
   }
}
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
   o.I                   = /^-?[1-9]\d*|0$/;
   o.PI                  = /^[1-9]\d*$/;
   o.NI                  = /^-[1-9]\d*$/;
   o.F                   = /^-?([1-9]\d*\.\d*|0\.\d*[1-9]\d*|0?\.0+|0)$/;
   o.PF                  = /^[1-9]\d*\.\d*|0\.\d*[1-9]\d*$/;
   o.NF                  = /^-([1-9]\d*\.\d*|0\.\d*[1-9]\d*)$/;
   o.U                   = /[1-9]{1}[0-9]/;
   o.E                   = /^\w{1,}[@]{1}[a-zA-Z]{1,}[.]{1}[a-zA-Z]{1,}$/;
   return o;
}
var ERegExp = new ERegExpFace();
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
   o._class    = null;
   o.construct = FObject_construct;
   o.toString  = FObject_toString;
   o.dispose   = FObject_dispose;
   o.dump      = FObject_dump;
   return o;
}
function FObject_construct(){
}
function FObject_toString(){
   return RClass.dump(this);
}
function FObject_dispose(){
   this._class = null;
}
function FObject_dump(s, l){
   s = RStringBuffer.nvl(s);
   s.append(RClass.dump(this));
   return s;
}
var RCulture = new function(){
   var o = this;
   RMemory.register('RCulture', o);
   return o;
}
var RDate = new function(){
   var o = this;
   o.MinYear       = 1800;
   o.MaxYear       = 2400;
   o.Pattern       = 'n-: /';
   o.Chars         = '0123456789-:/';
   o.DisplayFormat = 'yyyy-mm-dd hh24:mi:ss';
   o.DataFormat    = 'yyyymmddhh24miss';
   o.MonthDays     = new Array(0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31);
   o.DaySeconds    = 1000// 60// 60// 24;
   o.Parts         = new Array('YYYY','MM','DD','HH24','MI','SS');
   o.PartsDefine   = {'YYYY':['Year',4],'MM':['Month',2],'DD':['Day',2],'HH24':['Hour',2],'MI':['Minute',2],'SS':['Second',2]};
   o.nvl           = RDate_nvl;
   o.make          = RDate_make;
   o.format        = RDate_format;
   o.formatDate    = RDate_formatDate;
   o.formatText    = RDate_formatText;
   o.monthDays     = RDate_monthDays;
   o.splitFormat   = RDate_splitFormat;
   o.makeDate      = RDate_makeDate;
   o.checkItems    = RDate_checkItems;
   o.check         = RDate_check;
   o.parse         = RDate_parse;
   o.splitDate     = RDate_splitDate;
   o.splitTime     = RDate_splitTime;
   o.autoParse     = RDate_autoParse;
   o.getFormat     = RDate_getFormat;
   RMemory.register('RDate', o);
   return o;
}
function RDate_nvl(o){
   return o ? o : new TDate();
}
function RDate_make(yyyy, mm, dd, hh, mi, ss){
   return new TDate(new Date(yyyy, mm, dd));
}
function RDate_format(fmt){
   return this.formatDate(new TDate(), fmt);
}
function RDate_formatText(v, f){
   if(!v){
      return false;
   }
   f = f.toLowerCase();
   f = f.replace(/yyyy/g, v.substring(0, 4));
   v = v.substring(4);
   f = f.replace(/mm/g, v.substring(0, 2));
   v = v.substring(2);
   f = f.replace(/dd/g, v.substring(0, 2));
   v = v.substring(2);
   f = f.replace(/hh24/g, v.substring(0, 4));
   v = v.substring(4);
   f = f.replace(/mi/g, v.substring(0, 2));
   v = v.substring(2);
   f = f.replace(/ss/g, v.substring(0, 2));
   v = v.substring(2);
   return f;
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
   }else if(value.length == 4){
      da.year = RInt.parse(value);
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
   items['year'] = 2000;
   items['month'] = 1;
   items['day'] = 1;
   items['hour'] = 0;
   items['minute'] = 0;
   items['second'] = 0;
   v = RString.trim(v);
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
function RDate_getFormat(value){
   var o = this;
   var da = new Object();
   var f = '';
   var v = '';
   if(!value){ return; }
   if(value.indexOf(':') != -1){
      var as = RString.split(value, ' ');
      if(as.length == 1){
         var as1 = RString.split(as[0], ':');
         if(as1.length == 1){
            f += 'HH24';
            if(as1[0].length == 1){
               v += ('0'+as1[0]);
            }else{
               v += as1[0];
            }
         }else if(as1.length == 2){
            f += 'HH24MI';
            if(as1[0].length == 1){
               v += ('0'+as1[0]);
            }else if(as1[0].length == 2){
               v += as1[0];
            }
            if(as1[1].length == 1){
               v += ('0'+as1[1]);
            }else if(as1[1].length == 2){
               v += as1[1];
            }
         }else if(as1.length == 3){
            f += 'HH24MISS';
            if(as1[0].length == 1){
               v += ('0'+as1[0]);
            }else if(as1[0].length == 2){
               v += as1[0];
            }
            if(as1[1].length == 1){
               v += ('0'+as1[1]);
            }else if(as1[1].length == 2){
               v += as1[1];
            }
            if(as1[2].length == 1){
               v += ('0'+as1[2]);
            }else if(as1[2].length == 2){
               v += as1[2];
            }
         }
      }else if(as.length == 2){
         var as0 = RString.split(as[0], '-');
         if(as0.length == 3){
            f += 'YYYYMMDD';
            if(as0[0].length == 4){
               v += as0[0];
            }
            if(as0[1].length == 1){
               v += ('0'+as0[1]);
            }else if(as0[1].length == 2){
               v += as0[1];
            }
            if(as0[2].length == 1){
               v += ('0'+as0[2]);
            }else if(as0[2].length == 2){
               v += as0[2];
            }
         }else if(as0.length == 2){
            f += 'YYYYMM';
            if(as0[0].length == 1){
               v += as0[0];
            }
            if(as0[1].length == 1){
               v += ('0'+as0[1]);
            }else if(as0[1].length == 2){
               v += as0[1];
            }
         }else if(as0.length == 1){
            f += 'YYYY';
            if(as0[0].length == 4){
               v += as0[0];
            }
         }
         var as1 = RString.split(as[1], ':');
         if(as1.length == 1){
            f += 'HH24';
            if(as1[0].length == 1){
               v += ('0'+as1[0]);
            }else{
               v += as1[0];
            }
         }else if(as1.length == 2){
            f += 'HH24MI';
            if(as1[0].length == 1){
               v += ('0'+as1[0]);
            }else if(as1[0].length == 2){
               v += as1[0];
            }
            if(as1[1].length == 1){
               v += ('0'+as1[1]);
            }else if(as1[1].length == 2){
               v += as1[1];
            }
         }else if(as1.length == 3){
            f += ' HH24MISS';
            if(as1[0].length == 1){
               v += ('0'+as1[0]);
            }else if(as1[0].length == 2){
               v += as1[0];
            }
            if(as1[1].length == 1){
               v += ('0'+as1[1]);
            }else if(as1[1].length == 2){
               v += as1[1];
            }
            if(as1[2].length == 1){
               v += ('0'+as1[2]);
            }else if(as1[2].length == 2){
               v += as1[2];
            }
         }
      }
   }else{
      var as = RString.split(value, '-');
      if(as.length == 3){
         f += 'YYYYMMDD';
         if(as[0].length == 4){
            v += as[0];
         }
         if(as[1].length == 1){
            v += ('0'+as[1]);
         }else if(as[1].length == 2){
            v += as[1];
         }
         if(as[2].length == 1){
            v += ('0'+as[2]);
         }else if(as[2].length == 2){
            v += as[2];
         }
      }else if(as.length == 2){
         f = 'YYYYMM';
         if(as[0].length == 4){
            v += as[0];
         }
         if(as[1].length == 1){
            v += ('0'+as[1]);
         }else if(as[1].length == 2){
            v += as[1];
         }
      }else if(as.length == 1){
         f += 'YYYY';
         if(as[0].length == 4){
            v += as[0];
         }
      }
   }
   var ar = new Array(2);
   ar[0] = f;
   ar[1] = v;
   return ar;
}
var RDump = new function(){
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
   RMemory.register('RDump', o);
   return o;
}
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
         return obj.length + ':\'' + RString.toLine(obj) + '\'';
      case 'Function':
         if(obj.virtual){
            return 'virtual';
         }
         return RMethod.name(obj, true);
      case 'Array':
         return '@<Array@' + RClass.code(obj) + '>';
      default:
         if(obj.constructor == TClass){
            return '@<' + obj.name + '@' + RClass.code(obj) + '>';
         }
         try{
            for(var name in obj){
               return '@<Object@' + RClass.code(obj) + '>';
            }
         }catch(e){}
         return '<Object@' + RClass.code(obj) + '>';
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
      if(RString.startsWith(info, '@')){
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
      var icon = RString.startsWith(info, '@') ? '+' : ' ';
      hCell.innerText = RString.repeat('   ', level) + icon + ' ' + name;
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
      if(RString.startsWith(info, '@')){
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
   var dump = new TString();
   var type = RClass.safeTypeOf(obj);
   dump.append('<', type);
   if(obj && obj.tagName){
      dump.append(' - ', obj.tagName);
   }
   dump.appendLine('@' + RClass.code(obj) + '>');
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
   var s = new TString();
   while(o){
      s.append(RMethod.name(o));
      o = o.caller;
      if(o){
         s.appendLine();
      }
   }
   RLog.debug(this, s);
}
var REnum = new function(){
   var o = this;
   o.encode    = REnum_encode;
   o.decode    = REnum_decode;
   o.tryEncode = REnum_tryEncode;
   o.tryDecode = REnum_tryDecode;
   o.contains  = REnum_contains;
   RMemory.register('REnum', o);
   return o;
}
function REnum_encode(e, v){
   var v = this.tryEncode(e, v);
   if(null == v){
      RMessage.fatal(this, 'encode', 'Invalid value (enum={0}, value={1})', RClass.dump(e), v);
   }
   return v;
}
function REnum_decode(e, v){
   var v = this.tryDecode(e, v);
   if(null == v){
      RMessage.fatal(this, 'decode', 'Invalid value (enum={0}, value={1})', RClass.dump(e), v);
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
function REnum_contains(){
}
var RFile = new function(){
   var o = this;
   o.pictures  = ['jpg', 'png', 'gif', 'bmp'];
   o.knowns    = ['jpg', 'png', 'gif', 'bmp', 'doc', 'docx', 'vsd', 'xls', 'xlsx'];
   o.inPicture = RFile_inPicture;
   o.isPicture = RFile_isPicture;
   o.isKnown   = RFile_isKnown;
   o.extend    = RFile_extend;
   RMemory.register('RFile', o);
   return o;
}
function RFile_inPicture(v){
   var o = this;
   if(v){
      v = v.toLowerCase();
      for(var n in o.pictures){
         if(o.pictures[n] == v){
            return true;
         }
      }
   }
}
function RFile_isPicture(v){
   return this.inPicture(this.extend(v));
}
function RFile_isKnown(v){
   var o = this;
   v = o.extend(v).toLowerCase();
   for(var n in o.knowns){
      if(o.knowns[n] == v){
         return true;
      }
   }
   return false;
}
function RFile_extend(v){
   if(v){
      v = v.replace(/\\/g, '/');
      var p1 = v.lastIndexOf('/');
      if(-1 != p1){
         v = v.substring(p1 + 1);
      }
      var p2 = v.lastIndexOf('.');
      if(-1 != p2){
         return v.substring(p2 + 1);
      }
      return v;
   }
   return '';
}
var RFloat = new function(o){
   if(!o){o=this};
   o.Chars     = '0123456789-.%';
   o.NUMBER    = '0123456789-.%';
   o.LEFT_CHAR = '0';
   o.isFloat     = RFloat_isFloat;
   o.parse     = RFloat_parse;
   o.format    = RFloat_format;
   o.nvl       = RFloat_nvl;
   o.toRange   = RFloat_toRange;
   o.sum       = RFloat_sum;
   o.alg       = RFloat_alg;
   RMemory.register('RFloat', o);
   return o;
}
function RFloat_isFloat(value){
   return RString.isPattern(value, 'n');
}
function RFloat_parse(value){
   if(value == null){
      return 0;
   }
   value = RString.trim(value.toString());
   while(true){
      if(value.charAt(0) != "0"){
         break;
      }
      value = value.substr(1);
   }
   var rs = (value.length > 0) ? parseFloat(value) : 0;
   if(-1 != RString.findChars(value, '%')){
      rs = rs / 100;
   }
   return isNaN(rs) ? 0 : rs;
}
function RFloat_format(value, len, pad){
   if(!pad){
      pad = this.LEFT_CHAR;
   }
   var value = value.toString();
   var left = parseFloat(len) - value.length;
   for(var i=0; i<left; i++){
      value = pad + value;
   }
   return value;
}
function RFloat_nvl(v, d){
   return v ? v : (d ? d : 0);
}
function RFloat_toRange(v, min, max){
   if(null == v){
      v = 0;
   }
   return Math.min(Math.max(v, min), max);
}
function RFloat_sum(){
   var sum = 0;
   for(var n=0; n<arguments.length; n++){
      if(null != arguments[n]){
         sum += parseFloat(arguments[n]);
      }
   }
   return sum;
}
function RFloat_alg(f,a,b){
     var a = RFloat.nvl(a);
     var b = RFloat.nvl(b);
     a = parseFloat(a);
     b = parseFloat(b);
     if(f){
        return (a + b).toString();
     }else{
        return (a - b).toString();
     }
}
var RHex = new function(o){
   if(!o){o=this};
   o.NUMBER  = '0x123456789ABCDEF';
   o.PAD     = '0';
   o.isValid = RHex_isValid;
   o.parse   = RHex_parse;
   o.format  = RHex_format;
   RMemory.register('RHex', o);
   return o;
}
function RHex_isValid(v){
   return RString.isPattern(v, this.NUMBER);
}
function RHex_parse(v){
   return v ? parseInt('0x'+v) : '0';
}
function RHex_format(v, l){
   v = RString.nvl(v, '0').toString(16);
   return l ? RString.lpad(v, l, this.PAD) : v;
}
var RHtml = new function(o){
   if(!o){o=this};
   o.links          = new Object();
   o.link           = RHtml_link;
   o.findLink       = RHtml_findLink;
   o.checkGet       = RHtml_checkGet;
   o.checkSet       = RHtml_checkSet;
   o.radioGet       = RHtml_radioGet;
   o.radioSet       = RHtml_radioSet;
   o.offsetPosition = RHtml_offsetPosition;
   o.scrollWidth    = RHtml_scrollWidth;
   o.scrollHeight   = RHtml_scrollHeight;
   o.radioSet       = RHtml_radioSet;
   o.point          = RHtml_point;
   o.toPoint        = RHtml_toPoint;
   o.rect           = RHtml_rect;
   o.toRect         = RHtml_toRect;
   o.top            = RHtml_top;
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
   o.bodyWidth      = RHtml_bodyWidth;
   o.bodyHeight     = RHtml_bodyHeight;
   o.frameHeight    = RHtml_frameHeight;
   o.selectText     = RHtml_selectText;
   o.clone          = GHtml_clone;
   RMemory.register('RHtml', o);
   return o;
}
function GHtml_clone(o, s, t){
   if(!t){
      t = s.cloneNode(true);
   }
   if(s._pname){
      o[s._pname] = t;
   }
   if(s._ptyName){
	  o[s._ptyName] = t;
   }
   var e = REvent.find(s).events;
   t._psource = s;
   for(var n in e){
      t[e[n].handle] = s[e[n].handle];
      if(t[e[n].handle]){
          RHtml.link(t, '_plink', o);
      }
   }
   var p = s.children;
   var n = p.length;
   while(--n >= 0){
      GHtml_clone(o, p[n], t.children[n]);
   }
   return t;
}
function RHtml_offsetPosition(h, t){
   var p = new TPoint();
   while(h != t){
      p.x += h.offsetLeft - h.scrollLeft;
      p.y += h.offsetTop - h.scrollTop;
      if('absolute' != h.currentStyle.position){
      }
      p.x += h.clientLeft;
      p.y += h.clientTop;
      h = h.offsetParent;
   }
   return p;
}
function RHtml_bodyWidth(doc){
   return doc.all ? doc.body.scrollWidth : doc.documentElement.scrollWidth;
}
function RHtml_bodyHeight(doc){
   return doc.all ? doc.body.scrollHeight : doc.documentElement.scrollHeight;
}
function RHtml_frameHeight(f){
   var hd = f.contentWindow.document;
   var oh = hd.body.scrollHeight;
   var sh = hd.documentElement.scrollHeight;
   return Math.max(oh, sh);
}
function RHtml_scrollWidth(h){
   var r = 0;
   if(h.offsetWidth){
      r += h.offsetWidth;
   }
   if(h.borderTopWidth){
      r -= parseInt(h.borderLeftWidth);
   }
   if(h.borderBottomWidth){
      r -= parseInt(h.borderRightWidth);
   }
   if(h.clientWidth){
      r -= h.clientWidth;
   }
   return r;
}
function RHtml_scrollHeight(h){
   var r = 0;
   if(h.offsetHeight){
      r += h.offsetHeight;
   }
   if(h.borderTopWidth){
      r -= parseInt(h.borderTopWidth);
   }
   if(h.borderBottomWidth){
      r -= parseInt(h.borderBottomWidth);
   }
   if(h.clientHeight){
      r -= h.clientHeight;
   }
   return r;
}
function RHtml_link(h, n, v){
   var ls = this.links;
   var hi = ls[h.uniqueNumber];
   if(!hi){
      hi = ls[h.uniqueNumber] = new THtmlItem();
      hi.link = h;
   }
   hi.set(n, v);
}
function RHtml_findLink(h, n){
   var hi = this.links[h.uniqueNumber];
   return hi ? hi.get(n) : null;
}
function RHtml_checkGet(h){
   return RBool.toString(h.checked);
}
function RHtml_checkSet(h, v){
   h.checked = RBool.isTrue(v);
}
function RHtml_radioGet(hs){
   if(hs && hs.length){
      for(var n=0; n<hs.length; n++){
         var h = hs[n];
         if(h.checked){
            return h.value;
         }
      }
   }
}
function RHtml_radioSet(hs, v){
   if(hs && hs.length){
      for(var n=0; n<hs.length; n++){
         var h = hs[n];
         if(h.value == v){
            h.checked = true;
            break;
         }
      }
   }
}
function RHtml_point(o, p){
   return this.toPoint(new TPoint(), o, p);
}
function RHtml_toPoint(r, o, p){
   if(r && o){
      p = RObject.nvl(p, window.document.body);
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
      p = RObject.nvl(p, window.document.body);
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
function RHtml_top(h){
   var r = 0;
   if(h){
      r = -RInteger.parse(h.currentStyle.borderTopWidth);
      while(h){
         r += h.offsetTop - h.scrollTop;
         if('absolute' != h.currentStyle.position){
            r += h.clientTop;
         }
         h = h.offsetParent;
      }
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
function RHtml_clear(h){
   if(h){
      var cns = h.children;
      if(cns && cns.length){
         for(var n=cns.length-1; n>=0; n--){
            var cn = cns[n];
            if(cn.children && cn.children.length){
               this.clear(cn);
            }
            h.removeChild(cn);
         }
      }
   }
}
function RHtml_setRect(h, r){
   if(h && h.style){
      var s = h.style;
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
   if(h){
      var f = this.parent(h, 'FORM');
      return f ? f : h.ownerDocument.forms[0];
   }
   return window.document.forms[0];
}
function RHtml_popup(u, w, h){
   var l = (screen.width - w)/2;
   var t = (screen.height - h)/2 - 20;
   var s = RString.format('left={0},top={1},width={2},height={3},toolbar=no,location=no,directories=no,status=no,menubar=no,resizable=yes,scrollbars=yes,dependent=yes', l, t, w, h);
   window.open(u, '_blank', s);
}
function RHtml_selectText(){
   var ip = document.getElementById(id);
   ip.select();
   return document.selection.createRange().text;
}
var RPack = new function(o){
   if(!o){o=this};
   o.pack        = RPack_pack;
   o.packString  = RPack_packString;
   o.unpack      = RPack_unpack;
   o.packStyle   = RPack_packStyle;
   o.unpackStyle = RPack_unpackStyle;
   o.parseLink   = RPack_parseLink;
   o.unpackLink  = RPack_unpackLink;
   o.split       = RPack_split;
   RMemory.register('RPack', o);
   return o;
}
function RPack_pack(){
   var g = arguments;
   var c = g.length;
   if(0 != (c % 2)){
      return alert('pack error. length='+c);
   }
   var a = new TAttributes();
   for(var n=0;n<c;n+=2){
      a.set(g[n], g[n+1]);
   }
   return a;
}
function RPack_packString(){
   var g = arguments;
   var c = g.length;
   if(0 != (c % 2)){
      return alert('pack error. length='+c);
   }
   var a = new TAttributes();
   for(var n=0;n<c;n+=2){
      a.set(g[n], g[n+1]);
   }
   return a.pack();
}
function RPack_unpack(s){
   var a = new TAttributes();
   a.unpack(s);
   return a;
}
function RPack_packStyle(map){
   var pack = new TString();
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
               map.set(RString.trim(sub[0]), RString.trim(sub[1]));
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
      var pk = new TAttributes();
      var subs = link.split(/,/g);
      for(var n=0; n<subs.length; n++){
         var sub = subs[n];
         if(sub){
            var is = sub.split(/=/g);
            if(is.length == 2){
               pk.set(RString.trim(is[0]), this.parseLink(as, RString.trim(is[1])));
            }
         }
      }
      return pk;
   }
}
function RPack_split(s, a, b){
   var rs = new TAttributes();
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
var RRandom = new function(o){
   if(!o){o=this};
   o.seed = (new Date()).getTime();
   o.get  = RRandom_get;
   o.rand = RRandom_rand;
   RMemory.register('RRandom', o);
   return o;
}
function RRandom_get(){
   var o = this;
   o.seed = (o.seed * 9301 + 49297) % 233280;
   return o.seed/(233280.0);
}
function RRandom_rand(n){
   return Math.ceil(this.get()*n);
}
var RRect = new function(){
   var o = this;
   o.nvl    = RRect_nvl;
   o.pack   = RRect_pack;
   o.unpack = RRect_unpack;
   RMemory.register('RRect', o);
   return o;
}
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
var RRegExp = new function(o){
   if(!o){o=this;}
   o.test        = RRegExp_test;
   o.testRgexp   = RRegExp_testRgexp;
   RMemory.register('RRegExp', o);
   return o;
}
function RRegExp_test(r,s){
   if(r && s != null){
      return r.test(s);
   }
   return false;
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
var RSet = new function(){
   var o = this;
   o.contains    = RSet_contains;
   o.containsStr = RSet_containsStr;
   RMemory.register('RSet', o);
   return o;
}
function RSet_contains(v, e){
   return e == (e & v);
}
function RSet_containsStr(s, v){
   return RString.contains(s, v);
}
var RStrs = new function(o){
   if(!o){o=this};
   o.trim        = RStrs_trim;
   o.nvl         = RStrs_nvl;
   o.append      = RStrs_append;
   o.nameMinLen  = RStrs_nameMinLen;
   o.nameMaxLen  = RStrs_nameMaxLen;
   o.valueMinLen = RStrs_valueMinLen;
   o.valueMaxLen = RStrs_valueMaxLen;
   RMemory.register('RStrs', o);
   return o;
}
function RStrs_trim(values, trims){
   if(values && values.constructor == Array){
      for(var n=0; n<values.length; n++){
         values[n] = RString.trim(values[n], trims);
      }
   }
   return values;
}
function RStrs_nvl(values, offset, length){
   var result = new Array();
   for(var n=0; n<length; n++){
      if(!RString.isEmpty(values[n])){
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
var RWord = new function(){
   var o = this;
   o.ulName = RWord_ulName;
   RMemory.register('RWord', o);
   return o;
}
function RWord_ulName(s){
   var r = new TString();
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
function TBool(){
   var o = this;
   return o;
}
function TBoolSet(){
   var o = this;
   return o;
}
function TDataPage(){
   var o = this;
   o.rows     = new TList();
   o.ouids    = new TMap();
   o.count    = TDataPage_count;
   o.loadNode = TDataPage_loadNode;
   o.push     = TDataPage_push;
   return o;
}
function TDataPage_count(){
   return this.rows.count;
}
function TDataPage_loadNode(c){
   var o = this;
   var ns = c.nodes;
   if(ns){
      o.count = ns.count;
      var nc = ns.count;
      for(var i=0; i<nc; i++){
         var n = ns.get(i);
         if(n && n.isName(RDataset.ROW)){
            var r = new TRow();
            r.loadNode(c);
            o.push(r);
         }
      }
   }
}
function TDataPage_push(r){
   var o = this;
   o.rows.push(r);
   o.ouids.set(r.uniqueId, r);
}
function TDatasetViewer(){
   var o = this;
   o.datasetId = null;
   o.position  = 0;
   o.start     = 0;
   o.count     = 0;
   o.rows      = null;
   o.ouids     = null;
   o.isEmpty   = TDatasetViewer_isEmpty;
   o.count     = TDatasetViewer_count;
   o.current   = TDatasetViewer_current;
   o.reset     = TDatasetViewer_reset;
   o.move      = TDatasetViewer_move;
   o.moveToRow = TDatasetViewer_moveToRow;
   o.first     = TDatasetViewer_first;
   o.prior     = TDatasetViewer_prior;
   o.next      = TDatasetViewer_next;
   o.last      = TDatasetViewer_last;
   o.findById  = TDatasetViewer_findById;
   return o;
}
function TDatasetViewer_isEmpty(){
   return (0 == this.count);
}
function TDatasetViewer_count(){
   return this.count;
}
function TDatasetViewer_current(){
   var o = this;
   var rs = o.rows;
   return rs ? rs.get(o.position - o.start) : null;
}
function TDatasetViewer_reset(){
   this.position = -1;
}
function TDatasetViewer_move(p){
   this.position = p;
}
function TDatasetViewer_moveToRow(r){
   var o = this;
   var p = o.rows.indexOf(r);
   if(-1 != p){
      o.position = p - o.start;
   }
}
function TDatasetViewer_first(r){
   this.position = r ? -1 : 0;
}
function TDatasetViewer_prior(){
   var o = this;
   if(o.position > 0){
      o.position--;
      return true;
   }
   return false;
}
function TDatasetViewer_next(){
   var o = this;
   if(o.position < o.count-1){
      o.position++;
      return true;
   }
   return false;
}
function TDatasetViewer_last(){
   this.position = this.count-1;
}
function TDatasetViewer_findById(id){
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
   o.equals       = TDate_equals;
   o.isBefore     = TDate_isBefore;
   o.isAfter      = TDate_isAfter;
   o.monthDays    = TDate_monthDays;
   o.monthWeekDay = TDate_monthWeekDay;
   o.weekDay      = TDate_weekDay;
   o.setYear      = TDate_setYear;
   o.setMonth     = TDate_setMonth;
   o.setDay       = TDate_setDay;
   o.setHour      = TDate_setHour;
   o.setMinute    = TDate_setMinute;
   o.setSecond    = TDate_setSecond;
   o.addYear      = TDate_addYear;
   o.addMonth     = TDate_addMonth;
   o.addDay       = TDate_addDay;
   o.addMseconds  = TDate_addMseconds;
   o.refresh      = TDate_refresh;
   o.setDate      = TDate_setDate;
   o.now          = TDate_now;
   o.clone        = TDate_clone;
   o.dump         = TDate_dump;
   o.refresh();
   return o;
}
function TDate_clone(){
   var d = new Date();
   d.setTime(this.date.getTime());
   return new TDate(d);
}
function TDate_equals(d){
   return this.date.getTime() == d.date.getTime();
}
function TDate_isBefore(d){
   return this.date.getTime() < d.date.getTime();
}
function TDate_isAfter(d){
   return this.date.getTime() > d.date.getTime();
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
   this.date.setMonth(parseInt(n, 10)-1);
   this.refresh();
}
function TDate_setDay(n){
   this.date.setDate(n);
   this.refresh();
}
function TDate_setHour(n){
   this.date.setHours(n);
   this.refresh();
}
function TDate_setMinute(n){
   this.date.setMinutes(n);
   this.refresh();
}
function TDate_setSecond(n){
   this.date.setSeconds(n);
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
   this.date.setTime(this.date.getTime()+parseInt(n)*1000*60*60*24);
   this.refresh();
}
function TDate_addMseconds(n){
   this.date.setTime(this.date.getTime()+parseInt(n));
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
   this.hText.innerText = RString.repeat('   ', this.level-1) + icon + ' ' + this.caption;
   this.innerShow(display);
}
function THtmlItem(){
   var o = this;
   o.link  = null;
   o.links = new Object();
   o.get   = THtmlItem_get;
   o.set   = THtmlItem_set;
   return o;
}
function THtmlItem_get(n){
   return this.links[n];
}
function THtmlItem_set(n, v){
   this.links[n] = v;
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
function TMessage(){
   var o = this;
   o.type        = EMessage.None;
   o.attrType    = null;
   o.message     = null;
   o.description = null;
   o.redirect    = null;
   o.loadConfig = TMessage_loadConfig;
   o.saveConfig = TMessage_saveConfig;
   o.icon       = TMessage_icon;
   return o;
}
function TMessage_loadConfig(config){
   var o = this;
   o.type        = RString.toLower(config.name);
   o.message     = config.nvl('message');
   o.attrType    = config.nvl('type');
   o.redirect    = config.nvl('redirect');
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
function TPack(){
   var o = this;
   o.count      = 0;
   o.names      = null;
   o.values     = null;
   o.get        = TPack_get;
   o.set        = TPack_set;
   o.pack       = TPack_pack;
   o.unpack     = TPack_unpack;
   return o;
}
function TPack_get(n){
}
function TPack_set(n, v){
   var o = this;
}
function TPack_pack(){
   var o = this;
}
function TPack_unpack(s){
   var o = this;
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
   o[this.name] = RSet.containsStr(c.get(this.config), this.search);
}
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
   o.config     = RString.nvl(config, RWord.ulName(name).toString());
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
function TRandomInts(){
   var o = this;
   o.data   = new Array();
   o.max    = 1;
   o.setBetween = TRandomInts_setBetween;
   o.setMax     = TRandomInts_setMax;
   o.next       = TRandomInts_next;
   return o;
}
function TRandomInts_setBetween(min, max){
   var o = this;
   o.max = max - min;
   for(var n=min; n<=max; n++){
      o.data[n - min] = n;
   }
}
function TRandomInts_setMax(m){
   var o = this;
   o.max = m;
   for(var n=0; n<m; n++){
      o.data[n] = n;
   }
}
function TRandomInts_next(){
   var o = this;
   var r = RRandom.rand(o.max);
   for(var n=0; n<o.max; n++){
      var i = (n + r) % o.max;
      if(null != o.data[i]){
         var rn = o.data[i];
         o.data[i] = null;
         return rn;
      }
   }
}
function TRange(x, y, w, h){
   var o = this;
   o.x         = x;
   o.y         = y;
   o.width     = w;
   o.height    = h;
   o.dump      = TRange_dump;
   return o;
}
function TRange_reset(){
   var o = this;
   o.left = 0;
   o.top = 0;
   o.right = 0;
   o.bottom = 0;
}
function TRange_assign(rect){
   this.left = rect.left;
   this.top = rect.top;
   this.right = rect.right;
   this.bottom = rect.bottom;
}
function TRange_set(left, top, right, bottom){
   this.left = left;
   this.top = top;
   this.right = right;
   this.bottom = bottom;
}
function TRange_setBounds(left, top, width, height){
   var o = this;
   o.left = left;
   o.top = top;
   o.right = o.left + width - 1;
   o.bottom = o.top + height - 1;
}
function TRange_width(){
   return this.right - this.left + 1;
}
function TRange_setWidth(width){
   if(width){
      this.right = this.left + width - 1;
   }
}
function TRange_height(){
   return this.bottom - this.top + 1;
}
function TRange_setHeight(height){
   if(height){
      this.bottom = this.top + height - 1;
   }
}
function TRange_move(x, y){
   this.left += x;
   this.top += y;
   this.right += x;
   this.bottom += y;
}
function TRange_inc(border){
   var n = RInt.nvl(border, 1);
   this.left -= n;
   this.top -= n;
   this.right += n;
   this.bottom += n;
}
function TRange_dec(border){
   var n = RInt.nvl(border, 1);
   this.left += n;
   this.top += n;
   this.right -= n;
   this.bottom -= n;
}
function TRange_dump(d){
   var o = this;
   d = RString.nvlStr(d);
   d.append(RClass.name(o));
   d.append(' [', o.x, ',', o.y, '-', o.width, ',', o.height, '] ');
   return d;
}
function TRect(left, top, right, bottom){
   var o = this;
   o.left      = RInteger.nvl(left);
   o.top       = RInteger.nvl(top);
   o.right     = RInteger.nvl(right);
   o.bottom    = RInteger.nvl(bottom);
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
   o.reset     = TRect_reset;
   o.pack      = TRect_dump;
   o.unpack    = TRect_dump;
   o.dump      = TRect_dump;
   return o;
}
function TRect_reset(){
   var o = this;
   o.left = 0;
   o.top = 0;
   o.right = 0;
   o.bottom = 0;
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
   d = RString.nvlStr(d);
   d.append(RClass.name(this));
   d.append(' [', this.left, ',', this.top, '-', this.right, ',', this.bottom, '] ');
   d.append('(', this.width(), '-', this.height(), ')');
   return d;
}
function TSet(){
   var o = this;
   o.contains = TSet_contains;
   return o;
}
function TSet_contains(s, v){
   return RString.contains(s, v);
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
function TStrings(o){
   o = moNvl(o, this);
   TList(o);
   o.pack   = TStrings_pack;
   o.unpack = TStrings_unpack;
   return o;
}
function TStrings_pack(){
   var o = this;
   var p = new TString();
   for(var n = 0; n < o.count; n++){
      var s = o.get(n);
      var sl = s.length.toString();
      var sll = sl.length;
      sa = sll + sl + s;
      p.append(sa);
   }
   return p.toString();
}
function TStrings_unpack(p){
   var o = this;
   if(!RString.isEmpty(p)){
      var c = p.length;
      for(var n=0; n<c;){
         var ll = parseInt(p.charAt(n++));
         var l = parseInt(p.substr(n, ll));
         n += ll;
         var s = p.substr(n, l);
         n += l;
         o.push(s);
      }
   }
}
function TXmlConnect(){
   var o = this;
   o.address     = null;
   o.sync        = false;
   o.inUsing     = false;
   o.document    = null;
   o.cnnControl  = null;
   o.docControl  = null;
   o.cnnMode     = null;
   o.onLoad      = null;
   o.onFire      = TXmlConnect_onFire;
   o.onCnnReady  = TXmlConnect_onCnnReady;
   o.onDocReady  = TXmlConnect_onDocReady;
   o.construct   = TXmlConnect_construct;
   o.setHeaders  = TXmlConnect_setHeaders;
   o.newDocument = TXmlConnect_newDocument;
   o.newConnect  = TXmlConnect_newConnect;
   o.send        = TXmlConnect_send;
   o.receive     = TXmlConnect_receive;
   o.syncSend    = TXmlConnect_syncSend;
   o.syncReceive = TXmlConnect_syncReceive;
   o.construct();
   return o;
}
function TXmlConnect_onFire(doc, element){
   if(doc){
      this.document = (doc.constructor == Function) ? new doc() : new doc.constructor();
   }else{
      this.document = new TXmlDocument();
   }
   if(element){
      RXml.buildNode(this.document, null, element)
   }
   if(this.onLoad){
      this.onLoad(this);
   }
   this.inUsing = false;
}
function TXmlConnect_onCnnReady(cnn, doc){
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
function TXmlConnect_onDocReady(dc, doc){
   if(dc.readyState == EXmlCnnParse.Finish){
      if(dc.documentElement){
         this.onFire(doc, dc.documentElement);
      }else{
         alert('Read xml error.\n' + this.cnnControl.responseText);
      }
   }
}
function TXmlConnect_construct(){
   this.cnnControl = this.newConnect();
   this.docControl = this.newDocument();
}
function TXmlConnect_setHeaders(cnn, len){
   if(this.cnnMode == EXmlCnnType.IE){
      cnn.setrequestheader('content-type', 'application/x-www-form-urlencoded');
      cnn.setRequestHeader('content-length', len);
   }
}
function TXmlConnect_newDocument(){
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
function TXmlConnect_newConnect(){
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
function TXmlConnect_send(url, doc){
   this.inUsing = true;
   this.address = url;
   var xml = doc.xml().toString();
   var cnn = this.cnnControl;
   RLogger.info(this, 'Send xml url [{0}]', url);
   cnn.abort();
   cnn.open('POST', url, true);
   this.setHeaders(cnn, xml.length);
   var self = this;
   cnn.onreadystatechange = function(){self.onCnnReady(cnn, doc)};
   cnn.send(xml);
}
function TXmlConnect_receive(url, doc){
   this.send(url, doc);
}
function TXmlConnect_syncSend(url, doc){
   var o = this;
   o.inUsing = true;
   o.address = url;
   var xml = doc.xml().toString();
   var cnn = o.cnnControl;
   cnn.open('POST', url, false);
   o.setHeaders(cnn, xml.length);
   cnn.send(xml);
   var element = null;
   if(o.xmlMode == EXmlCnnType.IE){
      element = cnn.responseXML.documentElement;
   }else{
      element = cnn.responseXml.documentElement;
   }
   o.document = new TXmlDocument();
   if(element){
      RXml.buildNode(this.document, null, element);
   }else{
      RMessage.fatal(o, null, 'Read xml error.\n{0}', this.cnnControl.responseText)
   }
   o.inUsing = false;
   return o.document;
}
function TXmlConnect_syncReceive(url, doc){
   return this.syncSend(url, doc);
}
function TXmlDocument(n){
   var o = this;
   o.node       = n;
   o.create     = TXmlDocument_create;
   o.root       = TXmlDocument_root;
   o.xml        = TXmlDocument_xml;
   o.dump       = TXmlDocument_dump;
   return o;
}
function TXmlDocument_create(n, as, v){
   var n = new TNode(n);
   n.attrs = as;
   n.value = v;
   return n;
}
function TXmlDocument_root(){
   var o = this;
   if(!o.node){
      o.node = new TNode('Configuration');
   }
   return o.node;
}
function TXmlDocument_xml(){
   var s = new TString();
   s.append("<?xml version='1.0' encoding='UTF-8'?>");
   this.root().xml(s);
   return s;
}
function TXmlDocument_dump(d){
   var o = this;
   d = RString.nvlStr(d);
   d.appendLine(RClass.name(o));
   o.root().dump(d);
   return d;
}
function EActionFace(){
   var o = this;
   o.Fetch   = 'F';
   o.Search  = 'S';
   o.Lov     = 'O';
   o.Zoom    = 'Z';
   o.List    = 'L';
   o.Picker  = 'P';
   o.Insert  = 'I';
   o.Copy    = 'C';
   o.Update  = 'U';
   o.Delete  = 'D';
   o.Design  = 'G';
   o.Border  = 'B';
   o.Move    = 'M';
   o.Status  = 'T';
   o.Drag    = 'R';
   o.Link    = 'K';
   o.Action  = 'E';
   o.Changed = 'H';
   return o;
}
var EAction = new EActionFace();
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
function EBorderFace(){
   var o = this;
   o.None          = 0;
   o.Square        = 1;
   o.Round         = 2;
   o.RoundIcon     = 3;
   o.RoundDrop     = 4;
   o.RoundTitle    = 5;
   o.RoundIconDrop = 6;
   return o;
}
var EBorder = new EBorderFace();
function EBorderStyleFace(){
   var o = this;
   o.Readonly = 1;
   o.Edit     = 2;
   o.Hover    = 3;
   return o;
}
var EBorderStyle = new EBorderStyleFace();
function EColorFace(){
   var o = this;
   o.Normal        = '#FFFFFF';
   o.Select        = '#F8C59A';
   o.Valid         = '#FFCCCC';
   o.Invalid       = '#FFCCCC';
   o.Edit          = '#FFFFFF';
   o.EditHover     = '#EBFFFF';
   o.Require       = '#FF0000';
   o.Readonly      = '#F0F0F0';
   o.Text          = '#000000';
   o.TextEdit      = '#0066FF';
   o.TextReadonly  = '#333333';
   o.TextInvalid   = 'red';
   o.Delete        = '#DDDDDD';
   o.ColumnReadonly = '#FFFFFF';
   o.Rows          = new Array('#FFFFFF', '#FAFAFA');
   o.RowSelect     = '#cde5ff';
   o.RowHover      = '#E8E8FF';
   o.RowEdit       = '#FFFFFF';
   o.RowEditSelect = '#FDEBDB';
   o.RowEditHover  = '#F8F8E0';
   o.RoundReadonly = new Array(
      ['#DAF8F8', '#24C2DB', '#24C2DB', '#24C2DB', '#DAF8F8'],
      ['#24C2DB', '#CFF6F6', '#CFF6F6', '#CFF6F6', '#2AD6F0'],
      ['#24C2DB', '#CFF6F6', '#F8F8F8', '#FFFFFF', '#2AD6F0'],
      ['#24C2DB', '#FFFFFF', '#FFFFFF', '#FFFFFF', '#2AD6F0'],
      ['#DAF8F8', '#2AD6F0', '#2AD6F0', '#2AD6F0', '#DAF8F8']);
   o.RoundHover = new Array(
      ['#DAF8F8', '#24C2DB', '#24C2DB', '#24C2DB', '#DAF8F8'],
      ['#24C2DB', '#CFF6F6', '#CFF6F6', '#CFF6F6', '#2AD6F0'],
      ['#24C2DB', '#CFF6F6', '#F1FFFF', '#FFFFFF', '#2AD6F0'],
      ['#24C2DB', '#FFFFFF', '#FFFFFF', '#FFFFFF', '#2AD6F0'],
      ['#DAF8F8', '#2AD6F0', '#2AD6F0', '#2AD6F0', '#DAF8F8']);
   o.RoundEdit = new Array(
      ['#DAF8F8', '#24C2DB', '#24C2DB', '#24C2DB', '#DAF8F8'],
      ['#24C2DB', '#CFF6F6', '#CFF6F6', '#CFF6F6', '#2AD6F0'],
      ['#24C2DB', '#CFF6F6', '#F1FFFF', '#FFFFFF', '#2AD6F0'],
      ['#24C2DB', '#FFFFFF', '#FFFFFF', '#FFFFFF', '#2AD6F0'],
      ['#DAF8F8', '#2AD6F0', '#2AD6F0', '#2AD6F0', '#DAF8F8']);
   o.RoundDropReadonly = new Array(
      ['#DAF8F8', '#24C2DB', '#24C2DB', '#24C2DB', '#24C2DB', '#24C2DB', '#24C2DB', '#DAF8F8'],
      ['#24C2DB', '#CFF6F6', '#CFF6F6', '#CFF6F6', '#CFF6F6', '#CFF6F6', '#CFF6F6', '#2AD6F0'],
      ['#24C2DB', '#CFF6F6', '#F1FFFF', '#F1FFFF', '#F1FFFF', '#F1FFFF', '#FFFFFF', '#2AD6F0'],
      ['#24C2DB', '#FFFFFF', '#FFFFFF', '#FFFFFF', '#FFFFFF', '#FFFFFF', '#FFFFFF', '#2AD6F0'],
      ['#DAF8F8', '#2AD6F0', '#2AD6F0', '#2AD6F0', '#2AD6F0', '#2AD6F0', '#2AD6F0', '#DAF8F8']);
   o.RoundDropHover = new Array(
      ['#DAF8F8', '#24C2DB', '#24C2DB', '#24C2DB', '#24C2DB', '#24C2DB', '#24C2DB', '#DAF8F8'],
      ['#24C2DB', '#CFF6F6', '#CFF6F6', '#CFF6F6', '#CFF6F6', '#CFF6F6', '#CFF6F6', '#2AD6F0'],
      ['#24C2DB', '#CFF6F6', '#F1FFFF', '#F1FFFF', '#F1FFFF', '#F1FFFF', '#FFFFFF', '#2AD6F0'],
      ['#24C2DB', '#FFFFFF', '#FFFFFF', '#FFFFFF', '#FFFFFF', '#FFFFFF', '#FFFFFF', '#2AD6F0'],
      ['#DAF8F8', '#2AD6F0', '#2AD6F0', '#2AD6F0', '#2AD6F0', '#2AD6F0', '#2AD6F0', '#DAF8F8']);
   o.RoundDropEdit = new Array(
      ['#DAF8F8', '#24C2DB', '#24C2DB', '#24C2DB', '#24C2DB', '#24C2DB', '#24C2DB', '#DAF8F8'],
      ['#24C2DB', '#CFF6F6', '#CFF6F6', '#CFF6F6', '#CFF6F6', '#CFF6F6', '#CFF6F6', '#2AD6F0'],
      ['#24C2DB', '#CFF6F6', '#F1FFFF', '#F1FFFF', '#F1FFFF', '#F1FFFF', '#FFFFFF', '#2AD6F0'],
      ['#24C2DB', '#FFFFFF', '#FFFFFF', '#FFFFFF', '#FFFFFF', '#FFFFFF', '#FFFFFF', '#2AD6F0'],
      ['#DAF8F8', '#2AD6F0', '#2AD6F0', '#2AD6F0', '#2AD6F0', '#2AD6F0', '#2AD6F0', '#DAF8F8']);
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
   o.Zoom      = 'zoom';
   o.Insert    = 'insert';
   o.Update    = 'update';
   o.Delete    = 'delete';
   o.First     = 'first';
   o.Prior     = 'prior';
   o.Next      = 'next';
   o.Last      = 'last';
   o.Action    = 'action';
   o.FetchLov  = 'fetchLov';
   o.DsChanged = 'dschanged';
   o.EndFetch  = 'endfetch';
   o.EndUpdate = 'endupdate';
   o.Scalar    = 'scalar';
   o.Complete  = 'complete';
   o.Prepare   = 'prepare';
   o.Process   = 'process';
   o.TreeUpdate = 'treeUpdate';
   return o;
}
var EDataAction = new EDataActionFace();
function EDataStatusFace(){
   var o = this;
   o.Normal = 'N';
   o.Insert = 'I';
   o.Update = 'U';
   o.Delete = 'D';
   return o;
}
var EDataStatus = new EDataStatusFace();
var EDateTimeMode = new function(){
   var o = this;
   o.Year = 'Y';
   o.Month = 'M';
   o.Day = 'D';
   o.Hour  = 'H';
   o.Minute = 'T';
   o.Second = 'S';
   return o;
}
function EDesignFace(){
   var o = this;
   o.Move   = 1;
   o.Border = 2;
   return o;
}
var EDesign = new EDesignFace();
function EDirectionFace(){
   var o = this;
   o.Horizontal = 'H';
   o.Vertical   = 'V';
   return o;
}
var EDirection = new EDirectionFace();
var EDisplayConfig = new function(){
   var o = this;
   o.List    = 'L';
   o.Fixed   = 'F';
   o.Auto    = 'A';
   o.Size    = 'S';
   o.Drag    = 'D';
   o.Print   = 'P';
   return o;
}
function EDragFace(){
   var o = this;
   o.Move = 1;
   o.Size = 2;
   return o;
}
var EDrag = new EDragFace();
var EEditConfig = new function(){
   var o = this;
   o.Search = 'S';
   o.Copy   = 'C';
   return o;
}
function EEditStatusFace(o){
   if(!o){o=this;}
   o.Blur   = 0;
   o.Cancel = 1;
   o.Ok     = 2;
   return o;
}
var EEditStatus = new EEditStatusFace();
function EEventFace(){
   var o = this;
   o.None       = 0;
   o.Construct  = 1;
   o.Initialize = 2;
   o.Build      = 3;
   o.Refresh    = 4;
   o.Resize     = 5;
   o.Visible    = 6;
   o.Show       = 7;
   o.Hidden     = 8;
   o.Enable     = 9;
   o.Disable    = 10;
   o.Release    = 11;
   o.Design     = 12;
   o.Action     = 13;
   o.Valid      = 14;
   o.Mode       = 15;
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
var EFormLink = new function(){
   var o = this;
   o.Form  = 'F';
   o.Table = 'T'
   return o;
}
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
var EMode = new function(){
   var o = this;
   o.Display = 'P';
   o.Search = 'S';
   o.Design = 'G';
   o.Insert  = 'I';
   o.Update = 'U';
   o.Delete = 'D';
   o.Zoom = 'Z';
   return o;
}
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
var EOrder = new function(){
   var o = this;
   o.Asc    = 'A';
   o.Desc   = 'D';
   o.Source = 'C';
   return o;
}
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
   o.Disable   = 11;
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
function ERowStatusFace(){
   var o = this;
   o.Normal = 'N';
   o.Insert = 'I';
   o.Update = 'U';
   o.Delete  = 'D';
   return o;
}
var ERowStatus = new ERowStatusFace();
function EScopeFace(){
   var o = this;
   o.Global = 1;
   o.Page   = 2;
   o.Local  = 3;
   return o;
}
EScope = new EScopeFace();
var ESearch = new function(){
   var o = this;
   o.Equals = 'E';
   o.Begin  = 'B';
   o.End    = 'N';
   o.Like   = 'L';
   o.In     = 'I';
   o.Source = 'C';
   o.Date = 'D';
   return o;
}
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
function ESizeFace(){
   var o = this;
   o.Normal     = 0
   o.Horizontal = 1
   o.Vertical   = 2
   o.Both       = 3;
   return o;
}
var ESize = new ESizeFace();
function ESplitStyle(){
   var o = this;
   o.Normal       = 'N';
   o.BulgeLine    = 'B';
   o.HollowLine   = 'H';
   return o;
}
var ESplitStyle = new ESplitStyle();
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
   o.Prepare  = 8;
   return o;
}
var EStore = new EStoreFace();
function EStyleFace(){
   var o = this;
   o.Normal         = 0;
   o.Readonly       = 1;
   o.Edit           = 2;
   o.Hover          = 3;
   o.Select         = 4;
   o.ReadonlySelect = 5;
   o.Delete         = 6;
   o.Invalid        = 7;
   return o;
}
var EStyle = new EStyleFace();
function ETestFace(o){
   if(!o){o=this;}
   o.None    = 0;
   o.Display = 1;
   o.Enable  = 2;
   return o;
}
var ETest = new ETestFace();
function ETopActionFace(o){
   if(!o){o=this;}
   o.Show   = 1;
   o.Resize = 2;
   return o;
}
var ETopAction = new ETopActionFace();
function EWindowFace(){
   var o = this;
   o.Form     = 'F';
   o.Dialog   = 'D';
   o.Batch    = 'A';
   o.ListView = 'L';
   o.Browser  = 'B';
   return o;
}
var EWindow = new EWindowFace();
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
   o.oeInitialize = FComponent_oeInitialize;
   o.oeRelease    = FComponent_oeRelease;
   o.isParent     = FComponent_isParent;
   o.topComponent = FComponent_topComponent;
   o.fullPath     = FComponent_fullPath;
   o.component    = FComponent_component;
   o.findByClass  = FComponent_findByClass;
   o.findByPath   = FComponent_findByPath;
   o.createChild  = FComponent_createChild;
   o.push         = FComponent_push;
   o.process      = FComponent_process;
   o.psInitialize = FComponent_psInitialize;
   o.psRelease    = FComponent_psRelease;
   o.toString     = FComponent_toString;
   o.dispose      = FComponent_dispose;
   o.innerDump    = FComponent_innerDump;
   o.dump         = FComponent_dump;
   return o;
}
function FComponent_oeInitialize(e){
   return EEventStatus.Continue;
}
function FComponent_oeRelease(e){
   return EEventStatus.Continue;
}
function FComponent_isParent(p){
   while(p){
      if(p == this){
         return true;
      }
      p = p.parent;
   }
}
function FComponent_topComponent(c){
   var p = this;
   if(c){
      while(RClass.isClass(p.parent, c)){
         p = p.parent;
      }
   }else{
      while(p.parent){
         p = p.parent;
      }
   }
   return p;
}
function FComponent_fullPath(){
   var p = this;
   var r = '';
   if(p.parent){
      while(p.parent){
         r = '/' + p.name + r;
         p = p.parent;
      }
   }else{
      r = '/';
   }
   return r;
}
function FComponent_component(n){
   var ps = this.components;
   return ps ? ps.get(n) : null;
}
function FComponent_findByClass(c){
   var ps = this.components;
   if(ps){
      for(var n=0; n<ps.count; n++){
         var p = ps.value(n);
         if(RClass.isClass(p, c)){
            return p;
         }
      }
   }
}
function FComponent_findByPath(p){
   var o = this;
   if(!p){
      return o;
   }
   var r = o;
   p = p.replace(/\\/g, '/');
   if(RString.startsWith(p, '/')){
      r = o.topComponent();
      p = p.substring(1);
   }
   var s = p.split('/');
   var c = s.length;
   if(c){
      for(var i=0; i<c; i++){
         var n = s[i];
         if(n.length){
            r = r.components ? r.components.get(n) : null;
            if(!r){
               break;
            }
         }
      }
   }
   return r;
}
function FComponent_createChild(x){
   return RClass.createByName('F' + x.name);
}
function FComponent_push(p){
   var o = this;
   if(RClass.isClass(p, FComponent)){
      var ps = o.components;
      if(!ps){
         ps = o.components = new TMap();
      }
      p.parent = o;
      if(!p.name){
         p.name = ps.count;
      }
      ps.set(p.name, p);
   }
}
function FComponent_process(e, c, k){
   var o = this;
   if(!c){
      c = FComponent;
   }
   var s = !k && RClass.isClass(o, c);
   if(s){
      var r = e.process(o, EEventType.Before);
      if(EEventStatus.Stop == r || EEventStatus.Cancel == r){
         return r;
      }
   }
   if(!RClass.isClass(o, MContainer)){
      return EEventStatus.Stop;
   }
   var ps = o.components;
   if(ps){
      var pc = ps.count;
      if(pc){
         for(var n=0; n<pc; n++){
            var p = ps.value(n);
            if(p){
               if(EEventStatus.Cancel == p.process(e, c)){
                  return EEventStatus.Cancel;
               }
            }
         }
      }
   }
   if(s){
      if(EEventStatus.Cancel == e.process(o, EEventType.After)){
         return EEventStatus.Cancel;
      }
   }
   return EEventStatus.Continue;
}
function FComponent_psInitialize(){
   var o = this;
   o.process(new TEventProcess(o, 'oeInitialize', FComponent));
}
function FComponent_psRelease(){
   var o = this;
   o.process(new TEventProcess(o, 'oeRelease', FComponent));
   o.parent = null;
   o.components = null;
}
function FComponent_toString(){
   var o = this;
   return RClass.dump(o) + ':label=' + o.label;
}
function FComponent_dispose(){
   var o = this;
   o.base.FObject.dispose.call(o);
   o.parent = null;
   o.components = null;
}
function FComponent_innerDump(s, l){
   var o = this;
   s.append(RClass.dump(o));
   s.append(':name=', o.name);
   s.append(',label=', o.label);
}
function FComponent_dump(s, l){
   var o = this;
   s = RStringBuffer.nvl(s);
   l = RInteger.nvl(l);
   o.innerDump(s, l);
   var ps = o.components;
   if(ps){
      s.appendLine();
      for(var n=0; n<ps.count; n++){
         var p = ps.value(n);
         if(p){
            p.dump(s, l+1);
         }
      }
   }
   return s;
}
function FContainer(o){
   o = RClass.inherits(this, o, FControl, MContainer);
   o.oeDesign            = RMethod.empty;
   o.panel               = FContainer_panel;
   o.focusControl        = FContainer_focusControl;
   o.storeConfig         = FContainer_storeConfig;
   o.psBuildChildren     = FContainer_psBuildChildren;
   o.setChildrenProperty = FContainer_setChildrenProperty;
   return o;
}
function FContainer_panel(t){
   var o = this;
   if(EPanel.Container == t){
      return o.hPanel;
   }
   return o.base.FControl.panel.call(o, t);
}
function FContainer_focusControl(){
   return null;
   var o = this;
   var cs = o.controls;
   if(cs){
      var cc = cs.count;
      for(var n=0; n<cc; n++){
         var c = cs.value(n);
         if(RClass.isClass(c, MFocus) && c.testFocus()){
        	if(!RClass.isClass(c, FCalendar) && !RClass.isClass(c, FSelect)  && !RClass.isClass(c, FNumber)){
                return c.focus();
            }
         }
      }
      RConsole.find(FFocusConsole).focus(o);
   }
}
function FContainer_storeConfig(x){
   var o = this;
   x.name = RClass.name(o);
   o.saveConfig(x);
   var ps = o.components;
   if(ps){
      for(var n=0; n<ps.count; n++){
         var p = ps.value(n);
         var xp = x.create(RClass.name(p));
         if(RClass.isClass(p, FContainer)){
            p.storeConfig(xp);
         }else{
            p.saveConfig(xp);
         }
      }
   }
}
function FContainer_psBuildChildren(){
   var o = this;
   var e = REvent.alloc(o, EEvent.Build);
   o.ps(e, null, true);
   REvent.free(e);
}
function FContainer_setChildrenProperty(p, vs){
   var o = this;
   for(var n in vs){
      o.component(n)[p] = vs[n];
   }
}
function FControl(o){
   o = RClass.inherits(this, o, FComponent, MStyle, MSize);
   o.nowrap        = RClass.register(o, new TPtyBool('nowrap', false));
   o.disable       = RClass.register(o, new TPtyBool('disable', false));
   o.padLeft       = RClass.register(o, new TPtyInt('padLeft', 0));
   o.padTop        = RClass.register(o, new TPtyInt('padTop', 0));
   o.padRight      = RClass.register(o, new TPtyInt('padRight', 0));
   o.padTottom     = RClass.register(o, new TPtyInt('padTottom', 0));
   o.hint          = RClass.register(o, new TPtyStr('hint'));
   o.stPanel       = RClass.register(o, new TStyle('Panel'));
   o.stDesign      = RClass.register(o, new TStyle('Design'));
   o.stDesignHover = RClass.register(o, new TStyle('DesignHover'));
   o.stDesignDrag  = RClass.register(o, new TStyle('DesignDrag'));
   o.stDesignMove  = RClass.register(o, new TStyle('DesignMove'));
   o._esize        = ESize.Normal;
   o._emode        = EMode.Update;
   o._events       = null;
   o.controls      = null;
   o.storage       = null;
   o.disabled      = false;
   o.isBuilded     = false;
   o.hLayoutForm   = null;
   o.hLayoutRow    = null;
   o.hLayoutCell   = null;
   o.hPanel        = null;
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
   o.onBuildPanel  = FControl_onBuildPanel;
   o.oeBuild       = FControl_oeBuild;
   o.oeMode        = FControl_oeMode;
   o.oeEnable      = FControl_oeEnable;
   o.oeVisible     = FControl_oeVisible;
   o.oeResize      = FControl_oeResize;
   o.oeRefresh     = FControl_oeRefresh;
   o.topControl    = FControl_topControl;
   o.attachEvent   = FControl_attachEvent;
   o.linkEvent     = FControl_linkEvent;
   o.panel         = FControl_panel;
   o.isEnable      = FControl_isEnable;
   o.setEnable     = FControl_setEnable;
   o.enable        = FControl_enable;
   o.disable       = FControl_disable;
   o.isVisible     = FControl_isVisible;
   o.setVisible    = FControl_setVisible;
   o.show          = FControl_show;
   o.hide          = FControl_hide;
   o.callEvent     = FControl_callEvent;
   o.psBuild       = FControl_psBuild;
   o.psMode        = FControl_psMode;
   o.psDesign      = FControl_psDesign;
   o.psEnable      = FControl_psEnable;
   o.psVisible     = FControl_psVisible;
   o.psResize      = FControl_psResize;
   o.psRefresh     = FControl_psRefresh;
   o.setPanel      = FControl_setPanel;
   o.setPads       = FControl_setPads;
   o.push          = FControl_push;
   o.dispose       = FControl_dispose;
   return o;
}
function FControl_onEnter(e){
   var o = this;
   RConsole.find(FFocusConsole).enter(o);
   if(o.hint){
      window.status = o.hint;
   }
}
function FControl_onLeave(e){
   var o = this;
   RConsole.find(FFocusConsole).leave(o);
   if(o.hint){
      window.status = '';
   }
}
function FControl_onBuildPanel(){
   var o = this;
   var h = o.hPanel = RBuilder.newDiv();
   return h;
}
function FControl_oeBuild(e){
   var o = this;
   if(e.isBefore()){
      o.onBuildPanel();
      var h = o.hPanel;
      RHtml.link(h, 'control', o);
      if(!h.className){
         h.className = o.style('Panel');
      }
      o.attachEvent('onEnter', h);
      o.attachEvent('onLeave', h);
      o.attachEvent('onMouseOver', h);
      o.attachEvent('onMouseOut', h);
      o.attachEvent('onMouseDown', h, o.onMouseDown);
      o.attachEvent('onMouseUp', h);
      o.attachEvent('onClick', h);
      o.attachEvent('onDoubleClick', h);
      o.attachEvent('onKeyDown', h);
      o.attachEvent('onKeyPress', h);
      o.setBounds(o.left, o.top, o.right, o.bottom, true);
      o.setSize(o.width, o.height);
      o.setPads(o.padLeft, o.padTop, o.padRight, o.padBottom, true);
      if(RClass.isClass(o.parent, MContainer)){
         o.parent.appendChild(o);
      }
   }
   o.isBuilded = true;
   return EEventStatus.Continue;
}
function FControl_oeMode(e){
   var o = this;
   o._emode = e.mode;
   return EEventStatus.Continue;
}
function FControl_oeEnable(e){
   var o = this;
   if(e.isBefore()){
      o.setEnable(e.enable);
   }
   return EEventStatus.Continue;
}
function FControl_oeVisible(e){
   var o = this;
   if(e.isBefore()){
      o.setVisible(e.visible);
   }
   return EEventStatus.Continue;
}
function FControl_oeResize(e){
   return EEventStatus.Continue;
}
function FControl_oeRefresh(e){
   return EEventStatus.Continue;
}
function FControl_topControl(c){
   var r = this;
   if(c){
      while(r.parent){
         if(RClass.isClass(r.parent, c)){
            return r.parent;
         }
         r = r.parent;
      }
      if(!RClass.isClass(r, c)){
         return null;
      }
   }else{
      while(r.parent){
         if(!RClass.isClass(r.parent, FControl)){
            break;
         }
         r = r.parent;
      }
   }
   return r;
}
function FControl_attachEvent(n, h, m){
   return RControl.attachEvent(this, n, h, m);
}
function FControl_linkEvent(t, n, h, m){
   return RControl.linkEvent(this, t, n, h, m);
}
function FControl_panel(t){
   return this.hPanel;
}
function FControl_isEnable(){
   return true;
}
function FControl_setEnable(v){
   var o = this;
   o.disabled = !v;
   var h = o.panel(EPanel.Display);
   if(h){
      h.style.disabled = !v;
   }
}
function FControl_enable(){
   var o = this;
   if(o.disabled){
      o.setEnable(true);
   }
}
function FControl_disable(){
   var o = this;
   if(!o.disabled){
      o.setEnable(false);
   }
}
function FControl_isVisible(){
   var h = this.panel(EPanel.Display);
   if(h){
      return h.offsetWidth > 0;
   }
   return h ? (h.style.display != 'none') : false;
}
function FControl_setVisible(v){
   var o = this;
   o._visible = v;
   var hp = o.hLayoutCell;
   if(hp){
      hp.style.display = v ? 'inline' : 'none';
   }
   var h = o.panel(EPanel.Display);
   if(h){
      h.style.display = v ? 'inline' : 'none';
   }
}
function FControl_show(){
   var o = this;
   if(!o.isVisible()){
      o.setVisible(true);
   }
}
function FControl_hide(){
   var o = this;
   if(o.isVisible()){
      o.setVisible(false);
   }
}
function FControl_callEvent(n, s, e){
   var o = this;
   var es = o._events;
   if(es){
      var ec = es.get(n);
      if(ec){
         ec.invoke(s, s, e);
      }
   }
}
function FControl_psBuild(h, b){
   var o = this;
   var e = new TEventProcess(o, 'oeBuild', FControl);
   if(!b){
      b = RWindow.builder();
   }
   e.builder = b;
   o.process(e);
   if(h){
      o.setPanel(h);
   }
}
function FControl_psMode(m){
   var o = this;
   var e = new TEventProcess(o, 'oeMode', FControl);
   e.mode = m;
   o._emode = m;
   o.process(e);
}
function FControl_psDesign(m, f){
   var o = this;
   RConsole.find(FDesignConsole).setFlag(m, f, o);
   var e = new TEventProcess(o, 'oeDesign', MDesign)
   e.mode = m;
   e.flag = f;
   o.process(e);
}
function FControl_psEnable(v){
   var o = this;
   var e = new TEventProcess(o, 'oeEnable', FControl)
   e.enable = v;
   o.process(e);
}
function FControl_psVisible(v){
   var o = this;
   var e = new TEventProcess(o, 'oeVisible', FControl);
   e.visible = v;
   o.process(e);
}
function FControl_psResize(){
   var o = this;
   o.process(new TEventProcess(o, 'oeResize', FControl));
}
function FControl_psRefresh(t){
   var o = this;
   o.process(new TEventProcess(o, 'oeRefresh', FControl));
}
function FControl_setPanel(h){
   var o = this;
   o.hParent = h;
   if(h && o.hPanel){
      h.appendChild(o.hPanel);
   }
}
function FControl_setPads(l, t, r, b){
   var s = this.hPanel.style;
   if(l){
      s.paddingLeft = l;
   }
   if(t){
      s.paddingTop = t;
   }
   if(r){
      s.paddingRight = r;
   }
   if(b){
      s.paddingBottom = b;
   }
}
function FControl_push(p){
   var o = this;
   if(RClass.isClass(p, FEvent)){
      var es = o._events;
      var t = o.topComponent();
      if(!es){
         es = o._events = new TDictionary();
      }
      var en = p.name + '@' + t.name + o.fullPath();
      var e = RControl.events.get(en);
      if(!e){
         e = p;
         RControl.events.set(en, p);
      }
      es.set(e.name, e);
      return;
   }
   o.base.FComponent.push.call(o, p);
   if(RClass.isClass(p, FControl)){
      var cs = o.controls;
      if(!cs){
         cs = o.controls = new TDictionary();
      }
      if(!p.name){
         p.name = cs.count;
      }
      cs.set(p.name, p);
   }
}
function FControl_dispose(){
   var o = this;
   o.base.FComponent.dispose.call(o)
   RMemory.freeHtml(o.hPanel);
   o.hLayoutCell = null;
   o.hLayoutRow = null;
   o.hPanel = null;
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
   s = RString.nvlStr(s);
   s.appendLine(RClass.dump(o));
   o.dataset.dump(s);
   return s;
}
function FDropEditor(o){
   o = RClass.inherits(this, o, FEditor, MShadow);
   o.stDropForm      = RClass.register(o, new TStyle('DropForm'));
   o.stDropPanel     = RClass.register(o, new TStyle('DropPanel'));
   o.stButtonPanel   = RClass.register(o, new TStyle('ButtonPanel'));
   o.__minHeight     = 300;
   o.__minWidth      = null;
   o.border          = null;
   o.hDropForm       = null;
   o.hDropPanel      = null;
   o.hButtonPanel    = null;
   o.onDropMouseDown = RClass.register(o, new HMouseDown('onDropMouseDown'));
   o.onDropMouseUp   = RClass.register(o, new HMouseUp('onDropMouseUp'));
   o.onBuildDrop     = RMethod.virtual(o, 'onBuildDrop');
   o.onBuildButton   = RMethod.empty;
   o.oeBuild         = FDropEditor_oeBuild;
   o.panel           = FDropEditor_panel;
   o.hide            = FDropEditor_hide;
   o.dispose         = FDropEditor_dispose;
   return o;
}
function FDropEditor_oeBuild(e){
   var o = this;
   o.base.FEditor.oeBuild.call(o, e)
   var db = o.border = new TBorder(EBorder.Round);
   db.hParent = o.hPanel;
   RBorder.build(db);
   var hbf = o.hBorderForm = db.hForm;
   var hf = o.hDropForm = RBuilder.appendTable(db.hPanel);
   hf.className = o.style('DropForm');
   var hdp = o.hDropPanel = hf.insertRow().insertCell();
   hdp.className = o.style('DropPanel');
   var hbp = o.hButtonPanel = hf.insertRow().insertCell();
   hbp.className = o.style('ButtonPanel');
   o.onBuildDrop();
   o.onBuildButton();
   return EEventStatus.Stop;
}
function FDropEditor_panel(type){
   var o = this;
   if(EPanel.Shadow == type){
      return o.hPanel;
   }
   return o.base.FEditor.panel.call(o, type);
}
function FDropEditor_hide(){
   var o = this;
   o.base.FEditor.hide.call(o);
   o.base.MShadow.hide.call(o);
}
function FDropEditor_dispose(){
   var o = this;
   o.base.FControl.dispose.call(o);
   o.hDropForm = null;
   o.hDropPanel = null;
   o.hButtonPanel = null;
}
function FEditor(o){
   o = RClass.inherits(this, o, FControl, MFocus);
   o.styleEdit      = RClass.register(o, new TStyle('Edit'));
   o.inEdit         = false;
   o.source         = null;
   o.hEdit          = null;
   o.lsnEditBegin   = null;
   o.lsnEditCancel  = null;
   o.lsnEditEnd     = null;
   o.onEditKeyDown  = RClass.register(o, new HKeyDown('onEditKeyDown'));
   o.onEditKeyPress = RClass.register(o, new HKeyPress('onEditKeyPress'));
   o.onEditKeyUp    = RClass.register(o, new HKeyUp('onEditKeyUp'));
   o.onEditChange   = RClass.register(o, new HChange('onEditChange'));
   o.onEditBegin    = FEditor_onEditBegin;
   o.onEditChanged  = FEditor_onEditChanged;
   o.onEditEnd      = FEditor_onEditEnd;
   o.onBuildPanel   = FEditor_onBuildPanel;
   o.oeBuild        = FEditor_oeBuild;
   o.get            = RMethod.virtual(o, 'get');
   o.set            = RMethod.virtual(o, 'set');
   o.doBlur         = FEditor_doBlur;
   o.panel          = FEditor_panel;
   o.linkControl    = FEditor_linkControl;
   o.editBegin      = FEditor_editBegin;
   o.editCancel     = FEditor_editCancel;
   o.editEnd        = FEditor_editEnd;
   o.reset          = FEditor_reset;
   o.show           = FEditor_show;
   o.dispose        = FEditor_dispose;
   return o;
}
function FEditor_onEditBegin(){
   this.editBegin();
}
function FEditor_onEditChanged(){
   var o = this;
   RLogger.debug(o, 'Edit changed');
   var g = o.storage = RObject.nvlObj(o.storage);
   if(g.value == o.value()){
      if(o.changed){
         o.changed = false;
      }
   }else{
      if(!o.changed){
         o.changed = true;
      }
   }
}
function FEditor_onEditEnd(){
   this.editEnd();
}
function FEditor_onBuildPanel(){
   this.hPanel = RBuilder.append(null, 'SPAN');
}
function FEditor_oeBuild(e){
   var o = this;
   o.base.FControl.oeBuild.call(o, e);
   o.hPanel.style.zIndex = ELayer.Editor;
   o.setVisible(false);
   return EEventStatus.Stop;
}
function FEditor_get(name){
}
function FEditor_set(name, value){
}
function FEditor_doBlur(){
   var o = this;
   var s = o.source;
   if(s){
      o.editCancel();
      if(RClass.isClass(s, MFocus)){
         s.doBlur();
      }
   }
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
function FEditor_linkControl(c){
   var o = this;
   o.source = c;
}
function FEditor_editBegin(){
   var o = this;
   var s = o.source;
   RLogger.debug(o, 'Editor begin. (control={0})', RClass.dump(s));
   if(o.lsnEditCancel){
      o.lsnEditCancel.process(o);
   }
   s.editor = o;
   o.inEdit = true;
}
function FEditor_editCancel(){
   var o = this;
   var s = o.source;
   RLogger.debug(o, 'Editor cancel. (control={0})', RClass.dump(s));
   o.hide();
   if(o.lsnEditCancel){
      o.lsnEditCancel.process(o);
   }
   s.editor = null;
   o.source = null;
   o.inEdit = false;
}
function FEditor_editEnd(){
   var o = this;
   var s = o.source;
   RLogger.debug(o, 'Editor end. (control={0})', RClass.dump(s));
   o.hide();
   if(o.lsnEditEnd){
      o.lsnEditEnd.process(o);
   }
   s.editor = null;
   o.source = null;
   o.inEdit = false;
}
function FEditor_reset(){
   var o = this;
   o.lsnEditBegin = null;
   o.lsnEditCancel = null;
   o.lsnEditEnd = null;
}
function FEditor_show(){
   var o = this;
   o.base.FControl.show.call(o);
   o.editBegin();
   o.focus();
}
function FEditor_dispose(){
   var o = this;
   o.base.FControl.dispose.call(o);
   o.hEdit = null;
}
function FEvent(o){
   o = RClass.inherits(this, o, FComponent);
   o.code      = RClass.register(o, new TPtyStr('code'));
   o.onProcess = null;
   o.invoke    = FEvent_invoke;
   return o;
}
function FEvent_invoke(s, c, e){
   var o = this;
   var p = o.onProcess;
   if(!p){
      p = o.onProcess = new Function('o', 'e', this.code);
   }
   if(!e){
      e = new TEvent();
   }
   e.result = false;
   p.call(s, c, e);
   return e.result;
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
   o.dispose       = FHoverEditor_dispose;
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
function FHoverEditor_dispose(){
   var o = this;
   o.base.FEditor.dispose.call(o);
   RMemory.freeHtml(o.hPanel);
   RMemory.freeHtml(o.hParent);
   o.hParent = null;
   o.hPanel = null;
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
   o.dispose    = FScrollContainer_dispose;
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
function FScrollContainer_dispose(){
   var o = this;
   o.base.FControl.dispose.call(o);
   RMemory.freeHtml(o.hPanel);
   o.hPanel = null;
}
function FSplitableControl(){
   IObject.extendClass(this, FControl);
   this.className = 'FSplitableControl';
   this.htmlSplit = null;
   this.buildSplit = ctb_sbc_buildSplit;
   this.appendSplit = ctb_sbc_appendSplit;
   this.dispose = ctb_sbc_dispose;
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
function ctb_sbc_dispose(){
   var o = this;
   o.base.FControl.dispose.call(o);
   RMemory.freeHtml(o.htmlSplit);
   RMemory.freeHtml(o.htmlParent);
   o.htmlSplit = null;
   o.htmlParent = null;
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
   o.srcElement = e.srcElement;
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
   o.shiftKey = e.shiftKey;
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
function HLoad(n, m){
   var o = this;
   o.annotation = EAnnotation.Event;
   o.type       = 'load';
   o.handle     = 'onload';
   o.name       = n;
   o.method     = m;
   o.source     = null;
   o.hSource    = null;
   o.attach     = HLoad_attach;
   return o;
}
function HLoad_attach(e){
   var o = this;
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
function HMouseMove(n){
   var o = this;
   o.annotation = EAnnotation.Event;
   o.type       = 'mousemove';
   o.handle     = 'onmousemove';
   o.name       = n;
   o.source     = null;
   o.hSource    = null;
   o.keyAlt     = null;
   o.keyAlt     = null;
   o.x          = null;
   o.y          = null;
   o.offsetX    = null;
   o.offsetY    = null;
   o.srcElement = null;
   o.attach     = HMouseMove_attach;
   return o;
}
function HMouseMove_attach(e){
   var o = this;
   o.altKey = e.altKey;
   o.ctrlKey = e.ctrlKey;
   o.x = e.x;
   o.y = e.y;
   o.offsetX = e.offsetX;
   o.offsetY = e.offsetY;
   o.clientX = e.clientX;
   o.clientY = e.clientY;
   o.srcElement = e.srcElement;
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
function HReadyStateChange(n, m){
   var o = this;
   o.annotation = EAnnotation.Event;
   o.type       = 'readystatechange';
   o.handle     = 'onreadystatechange';
   o.name       = n;
   o.method     = m;
   o.source     = null;
   o.hSource    = null;
   o.attach     = HReadyStateChange_attach;
   return o;
}
function HReadyStateChange_attach(e){
   var o = this;
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
function HScroll(n, m){
   var o = this;
   o.annotation = EAnnotation.Event;
   o.type       = 'scroll';
   o.handle     = 'onscroll';
   o.name       = n;
   o.method     = m;
   o.source     = null;
   o.hSource    = null;
   o.attach     = HScroll_attach;
   return o;
}
function HScroll_attach(e){
   var o = this;
}
function MAction(o){
   o = RClass.inherits(this, o);
   o.doAction = MAction_doAction
   return o;
}
function MAction_doAction(n){
   var o = this;
   var dc = o.topComponent(MDataset);
   if(dc){
      if(!o.psValid()){
         return;
      }
   }
   var a = o.components.get(n);
   if(RClass.isClass(a, MInvoke)){
      a.invoke(this);
   }
}
function MAlphaAble(o){
   o = RClass.inherits(this, o);
   o.setCtlAlpha         = MAlphaAble_setCtlAlpha;
   o.setGraduallyAlpha   = MAlphaAble_setGraduallyAlpha;
   o.graduallyAlpha      = MAlphaAble_graduallyAlpha;
   return o;
}
function MAlphaAble_setCtlAlpha(a){
   var o = this;
   if(o.hPanel){
      if(0 > RInt.parse(a)){
         o.hPanel.style.filter="alpha(opacity=0)";
      }else{
         o.hPanel.style.filter="alpha(opacity="+RInt.parse(a)+")";
      }
   }else{
      alert("setCtlAlpha :no hPanel");
   }
}
function MAlphaAble_setGraduallyAlpha(s,e,t){
   var o = this;
   s = RInt.parse(s) < 0  ? 0:RInt.parse(s);
   e = RInt.parse(e) >100 ? 100:RInt.parse(e);
   t = RInt.parse(t) <0 ? 1:RInt.parse(t);
   o.active = new TActive(o, o.onInterval);
   o.active.interval = 250;
   RConsole.find(FActiveConsole).push(o.active);
}
function MAlphaAble_graduallyAlpha(){
   var o = this;
}
function MBorder(o){
   o = RClass.inherits(this, o);
   o.border         = null;
   o.borderStyle    = EBorder.None;
   o.onBuildBorder  = MBorder_onBuildBorder;
   o.setBorderStyle = MBorder_setBorderStyle;
   o.setBorderEnable = MBorder_setBorderStyle;
   return o;
}
function MBorder_onBuildBorder(){
   var o = this;
   var b = o.border = new TBorder(o.borderStyle);
   RBorder.build(b);
}
function MBorder_setBorderStyle(s, c, r){
   var o = this;
   var t = o.border;
   var bs = o.borderStyle;
   if(EBorder.Round == bs){
      t.hPanelL.style.backgroundColor = c;
      t.hPanelT.style.backgroundColor = c;
      t.hPanel.style.backgroundColor = c;
      t.hPanelB.style.backgroundColor = c;
      t.hPanelR.style.backgroundColor = c;
   }else if(EBorder.RoundIcon == bs){
      t.hIconT.style.backgroundColor = c;
      t.hIcon.style.backgroundColor = c;
      t.hIconB.style.backgroundColor = c;
      t.hPanelL.style.backgroundColor = c;
      t.hPanelT.style.backgroundColor = c;
      t.hPanel.style.backgroundColor = c;
      t.hPanelB.style.backgroundColor = c;
      t.hPanelR.style.backgroundColor = c;
   }else if(EBorder.RoundDrop == bs){
      t.hPanelL.style.backgroundColor = c;
      t.hPanelT.style.backgroundColor = c;
      t.hPanel.style.backgroundColor = c;
      t.hPanelB.style.backgroundColor = c;
      t.hDrop.style.backgroundColor = c;
      if(s){
         t.hSplitT.style.backgroundColor = '#3C7FB1';
         t.hSplitM.style.backgroundColor = '#3C7FB1';
         t.hSplitB.style.backgroundColor = '#3C7FB1';
         t.hDropLt.style.backgroundColor = '#FFFFFF';
         t.hDropL.style.backgroundColor = '#FFFFFF';
         t.hDropLb.style.backgroundColor = '#FFFFFF';
         t.hDropT.style.backgroundColor = '#FFFFFF';
         t.hDropB.style.backgroundColor = '#FFFFFF';
         t.hDropR.style.backgroundColor = '#FFFFFF';
         t.hDropBR.style.backgroundColor = '#3C7FB1';
         t.hDropBLb.style.backgroundColor = '#3C7FB1';
         t.hDropBB.style.backgroundColor = '#3C7FB1';
         t.hDropBRb.style.backgroundColor = '#3C7FB1';
      }else{
         t.hSplitT.style.backgroundColor = c;
         t.hSplitM.style.backgroundColor = c;
         t.hSplitB.style.backgroundColor = c;
         t.hDropLt.style.backgroundColor = c;
         t.hDropL.style.backgroundColor = c;
         t.hDropLb.style.backgroundColor = c;
         t.hDropT.style.backgroundColor = c;
         t.hDropB.style.backgroundColor = c;
         t.hDropR.style.backgroundColor = c;
         t.hDropBR.style.backgroundColor = '#c0dff1';
         t.hDropBLb.style.backgroundColor = '#c0dff1';
         t.hDropBB.style.backgroundColor = '#c0dff1';
         t.hDropBRb.style.backgroundColor = '#e9f1f8';
      }
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
      var ps = RClass.find(o.constructor).annotations(EAnnotation.Property);
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
function MConfig_saveConfig(c){
   var o = this;
   var ps = RClass.find(o.constructor).annotations(EAnnotation.Property);
   for(var n in ps){
      ps[n].save(o, c);
   }
}
function MContainer(o){
   o = RClass.inherits(this, o);
   o.appendChild = RMethod.empty;
   return o;
}
function MDataset(o){
   o = RClass.inherits(this, o, MEditable);
   o.dsName               = RClass.register(o, new TPtyStr('dsName', null, 'dataset'));
   o.dsService            = RClass.register(o, new TPtyStr('dsService', EService.WebDataset, 'service'));
   o.dsPageSize           = RClass.register(o, new TPtyInt('dsPageSize', 20, 'page_size'));
   o.dispToolbar          = RClass.register(o, new TPtyBool('dispToolbar', false));
   o.editFetch            = RClass.register(o, new TPtyBoolSet('editFetch', 'editConfig', EEditConfig.Fetch, false));
   o.editSearch           = RClass.register(o, new TPtyBoolSet('editSearch', 'editConfig', EEditConfig.Search, false));
   o.editCopy             = RClass.register(o, new TPtyBoolSet('editCopy', 'editConfig', EEditConfig.Copy, false));
   o.insertAction         = RClass.register(o, new TPtyStr('insertAction', 'insert'));
   o.updateAction         = RClass.register(o, new TPtyStr('updateAction', 'update'));
   o.deleteAction         = RClass.register(o, new TPtyStr('deleteAction', 'delete'));
   o.dsPageIndex          = 0;
   o.dsViewer             = null;
   o.dsValues             = null;
   o.dsGlobalSearchs      = null;
   o.dsSearchs            = null;
   o.dsGlobalOrders       = null;
   o.dsOrders             = null;
   o.__initializeEvent    = null;
   o.__showEvent          = null;
   o.__loadedEvent        = null;
   o.__progress           = false;
   o.__progressProcess    = null;
   o.__validProcess       = null;
   o.lsnsUpdateBegin      = null;
   o.lsnsUpdateEnd        = null;
   o.onDsFetch           = MDataset_onDsFetch;
   o.onDsPrepareCheck    = RMethod.emptyTrue;
   o.onDsPrepare         = MDataset_onDsPrepare;
   o.onDsUpdateCheck     = RMethod.emptyTrue;
   o.onDsUpdate          = MDataset_onDsUpdate;
   o.onDsDeleteCheck     = RMethod.emptyTrue;
   o.onDsDelete          = MDataset_onDsDelete;
   o.onDsCopy            = MDataset_onDsCopy;
   o.onDsDoUpdate        = MDataset_onDsDoUpdate;
   o.onDsProcess         = MDataset_onDsProcess;
   o.onLoadDatasetBegin  = RMethod.empty;
   o.onLoadDataset       = RMethod.virtual(o, 'onLoadDataset');
   o.onLoadDatasetEnd    = RMethod.virtual(o, 'onLoadDatasetEnd');
   o.getDataCodes        = RMethod.virtual(o, 'getDataCodes');
   o.getCurrentRow       = RMethod.virtual(o, 'getCurrentRow');
   o.getSelectedRows     = RMethod.virtual(o, 'getSelectedRows');
   o.getChangedRows      = RMethod.virtual(o, 'getChangedRows');
   o.getRows             = RMethod.virtual(o, 'getRows');
   o.toDeepAttributes    = MDataset_toDeepAttributes;
   o.construct           = MDataset_construct;
   o.loadDataset         = MDataset_loadDataset;
   o.loadDatasets        = MDataset_loadDatasets;
   o.doPrepare           = RMethod.virtual(o, 'doPrepare');
   o.doDelete            = RMethod.virtual(o, 'doDelete');
   o.dsInitialize        = MDataset_dsInitialize;
   o.dsShow              = MDataset_dsShow;
   o.dsLoaded            = MDataset_dsLoaded;
   o.dsFetch             = MDataset_dsFetch;
   o.dsSearch            = MDataset_dsSearch;
   o.dsCopy              = MDataset_dsCopy;
   o.dsPrepare           = MDataset_dsPrepare;
   o.dsUpdate            = MDataset_dsUpdate;
   o.dsDelete            = MDataset_dsDelete;
   o.dsMode              = MDataset_dsMode;
   o.dsDoUpdate          = MDataset_dsDoUpdate;
   o.dsProcess           = MDataset_dsProcess;
   o.dsProcessChanged    = MDataset_dsProcessChanged;
   o.dsProcessSelected   = MDataset_dsProcessSelected;
   o.dsProcessAll        = MDataset_dsProcessAll;
   o.psProgress          = MDataset_psProgress;
   o.psValid             = MDataset_psValid;
   o.dsCurrent           = MDataset_dsCurrent;
   o.dsStore             = null;
   o.dsSearchBox         = null;
   o.dsSearchWindow      = null;
   o.onStoreChanged      = RMethod.empty;
   o.onDsFetchBegin      = RMethod.empty;
   o.onDsFetchEnd        = RMethod.empty;
   o.onDsUpdateBegin     = RMethod.empty;
   o.onDsUpdateEnd       = RMethod.empty;
   o.hasAction           = RMethod.virtual(o, 'hasAction');
   o.dsIsChanged         = MDataset_dsIsChanged;
   o.dsCount             = MDataset_dsCount;
   o.dsMove              = MDataset_dsMove;
   o.dsMovePage          = MDataset_dsMovePage;
   o.dsGet               = MDataset_dsGet;
   o.dsSet               = MDataset_dsSet;
   o.dsRefresh           = MDataset_dsRefresh;
   o.doSearch            = MDataset_doSearch;
   return o;
}
function MDataset_onDsFetch(g){
   var o = this;
   o.loadDatasets(g.resultDatasets);
   o.onLoadDatasetEnd();
   o.focus();
}
function MDataset_onDsPrepare(g){
   var o = this;
   g.resultDatasets.set('/', null);
   o.loadDatasets(g.resultDatasets);
   o.doPrepare(g.resultRow);
   o.onLoadDatasetEnd();
   o.focus();
}
function MDataset_onDsUpdate(g){
   var o = this;
   o.loadDatasets(g.resultDatasets);
   o.onLoadDatasetEnd();
   o.focus();
}
function MDataset_onDsCopy(g){
   var o = this;
   o.loadDatasets(g.resultDatasets);
   o.onLoadDatasetEnd();
   o.focus();
}
function MDataset_onDsDelete(g){
   var o = this;
   o.loadDatasets(g.resultDatasets);
   o.doDelete(g.resultRow);
   o.onLoadDatasetEnd();
   o.focus();
}
function MDataset_onDsProcess(g){
   var o = this;
   var cb = g.resultCallback;
   if(cb){
      cb.invoke(o, g);
   }
}
function MDataset_toDeepAttributes(a, m){
   var o = this;
   if(!a){
      a = new TAttributes();
   }
   var ts = new TList();
   var p = o;
   while(p){
      if(RClass.isClass(p, MDataset)){
         ts.push(p);
      }
      if(!p.parent){
         break;
      }
      p = p.topControl(MDataset);
   }
   for(var n=ts.count; n>=0; n--){
      var p = ts.get(n);
      if(RClass.isClass(p, FForm)){
         p.toAttributes(a, m);
      }else if(RClass.isClass(m, FTable)){
         var r = p.getCurrentRow();
         if(r){
            r.toAttributes(a, m);
         }
      }
   }
   return a;
}
function MDataset_onDsDoUpdate(g){
   var o = this;
   if(!g.invokeSuccess()){
      o.psRefresh();
   }
   if(!g.processFinish){
      o.focus();
      o.lsnsUpdateEnd.process(g);
   }
   o.onLoadDatasetEnd();
}
function MDataset_construct(){
   var o = this;
   o.dsViewer = new TDatasetViewer();
   o.dsValues = new TAttributes();
   o.dsSearchs = new TSearchItems();
   o.dsGlobalSearchs = new TSearchItems();
   o.dsOrders = new TOrderItems();
   o.dsGlobalOrders = new TOrderItems();
   o.__initializeEvent = new TEvent();
   o.__showEvent = new TEvent();
   o.__loadedEvent = new TEvent();
   o.__progressProcess = new TEventProcess(o, 'oeProgress', MProgress);
   var vp = o.__validProcess = new TEventProcess(o, 'oeValid', MValidator);
   vp.controls = new TList();
   o.lsnsUpdateBegin = new TListeners();
   o.lsnsUpdateEnd = new TListeners();
}
function MDataset_loadDataset(d){
   var o = this;
   o.dsStore = d;
   d.saveViewer(o.dsViewer);
   return o.onLoadDataset(d);
}
function MDataset_loadDatasets(ds){
   var o = this;
   var c = ds.count;
   for(var n=0; n<c; n++){
      var d = ds.value(n);
      if(d){
         var dc = o.findByPath(d.name)
         if(!dc){
            dc = o.findByPath(d.name);
            return RMessage.fatal(o, null, 'Load dataset failed. (control={0})', d.name);
         }
         dc.loadDataset(d);
      }
   }
}
function MDataset_dsInitialize(){
   this.callEvent('onFormInitialize', this, this.__initializeEvent);
}
function MDataset_dsShow(){
   this.callEvent('onFormShow', this, this.__showEvent);
}
function MDataset_dsLoaded(){
   this.callEvent('onDatasetLoaded', this, this.__loadedEvent);
}
function MDataset_dsFetch(r, f){
   var o = this;
   o.psProgress(true);
   var tc = o.topControl();
   var g = new TDatasetFetchArg(tc.name, tc.formId, o.dsPageSize, o.dsPageIndex);
   g.reset = r;
   g.force = f;
   g.mode = o._emode;
   g.searchs.append(o.dsGlobalSearchs);
   g.searchs.append(o.dsSearchs);
   g.orders.append(o.dsGlobalOrders);
   g.orders.append(o.dsOrders);
   o.toDeepAttributes(g.values);
   g.values.append(o.dsValues);
   g.callback = new TInvoke(o, o.onDsFetch);
   RConsole.find(FDatasetConsole).fetch(g);
}
function MDataset_dsSearch(s){
   var o = this;
   o.psProgress(true);
   var tc = o.topControl();
   var pth = o.fullPath();
   if(s){
      pth = s.fullPath();
   }
   var g = new TDatasetFetchArg(tc.name, tc.formId, o.dsPageSize, 0, true, false, pth);
   g.mode = tc._emode;
   g.searchs.append(o.dsGlobalSearchs);
   g.searchs.append(o.dsSearchs);
   g.orders.append(o.dsGlobalOrders);
   g.orders.append(o.dsOrders);
   o.toDeepAttributes(g.values);
   g.values.append(o.dsValues);
   g.callback = new TInvoke(o, o.onDsFetch);
   RConsole.find(FDatasetConsole).fetch(g);
}
function MDataset_dsCopy(r){
   var o = this;
   o.psProgress(true);
   o.psMode(EMode.Insert);
   var g = new TDatasetFetchArg(o.name, o.formId, o.dsPageSize, 0, true);
   g.form = o;
   g.mode = EMode.Insert;
   o.dsSearchs.clear();
   o.dsSearchs.push(new TSearchItem('OUID', r.get("OUID")));
   g.searchs = o.dsSearchs;
   g.callback = new TInvoke(o, o.onDsCopy);
   if(o.onDsUpdateCheck(g)){
      RConsole.find(FDatasetConsole).fetch(g);
   }
   return;
}
function MDataset_dsPrepare(){
   var o = this;
   o.psProgress(true);
   o.psMode(EMode.Insert);
   var g = new TDatasetPrepareArg(o.name, o.formId);
   g.form = o;
   g.values.append(o.dsValues);
   if(o.onDsPrepareCheck(g)){
      g.callback = new TInvoke(o, o.onDsPrepare);
      RConsole.find(FDatasetConsole).prepare(g);
   }
}
function MDataset_dsUpdate(u, v){
   var o = this;
   o.psProgress(true);
   o.psMode(EMode.Update);
   o.dsFetch(true);
}
function MDataset_dsDelete(u, v){
   var o = this;
   o.psProgress(true);
   o.psMode(EMode.Delete);
   var g = new TDatasetFetchArg(o.name, o.formId, o.dsPageSize, 0, true);
   g.callback = new TInvoke(o, o.onDsDelete);
   g.form = o;
   g.mode = EMode.Delete;
   if(u){
      g.searchs.push(new TSearchItem('OUID', u));
   }
   if(v){
       g.searchs.push(new TSearchItem('OVER', v));
   }
   g.values = o.dsValues;
   if(o.onDsDeleteCheck(g)){
      RConsole.find(FDatasetConsole).fetch(g);
   }
   return;
}
function MDataset_dsMode(m){
   var o = this;
   switch(m){
      case EMode.Insert:
         o.dsPrepare();
         break;
      case EMode.Update:
         o.dsUpdate();
         break;
      case EMode.Delete:
         o.dsDelete();
         break;
   }
}
function MDataset_dsDoUpdate(cb, ck){
   var o = this;
   if(!o.psValid()){
      return;
   }
   var t = o.topControl();
   var g = new TDatasetUpdateArg(t.name, o.formId, o.dsName);
   g.form = o;
   g.path = o.fullPath();
   g.mode = o._emode;
   g.codes = o.getDataCodes();
   g.callback = new TInvoke(o, o.onDsDoUpdate);
   g.callbackSuccess = cb;
   if(EMode.Insert == o._emode || EMode.Delete == o._emode){
      g.dataset.rows.append(o.getCurrentRows());
   }else{
      g.dataset.rows.append(o.getChangedRows());
      if(!ck){
         if(!g.hasData()){
            return RMessage.warn(o, RContext.get('MDataset:nochange'));
         }
      }
   }
   o.psProgress(true);
   RConsole.find(FDatasetConsole).update(g);
}
function MDataset_dsProcess(da, cb){
   var o = this;
   if(!o.psValid()){
      return;
   }
   var g = new TDatasetServiceArg(o.topControl().name, da);
   g.form = o;
   g.controlName = o.name;
   o.toDeepAttributes(g.attributes);
   g.codes = o.getDataCodes();
   g.push(o.getCurrentRow());
   g.resultCallback = cb;
   if(!g.hasData()){
      return RMessage.warn(o, RContext.get('MDataset:nodata'));
   }
   o.psProgress(true);
   g.callback = new TInvoke(o, o.onDsProcess);
   RConsole.find(FFormConsole).process(g);
}
function MDataset_dsProcessSelected(da, cb){
	var o = this;
	if(!o.psValid()){
	   return;
	}
	   var g = new TDatasetServiceArg(o.topControl().name, da);
	   g.form = o;
	   g.controlName = o.name;
	   o.toDeepAttributes(g.attributes);
	   g.codes = o.getDataCodes();
	   g.rows = o.getSelectedRows();
	   if(g.rows.count > 0){
		  g.resultCallback = cb;
		  o.psProgress(true);
		  g.callback = new TInvoke(o, o.onDsProcess);
		  RConsole.find(FFormConsole).process(g);
		  o.clearSelectRows();
	   }else{
	      return RMessage.warn(o, RContext.get('MDataset:norows'));
	   }
}
function MDataset_dsProcessChanged(da, cb){
   var o = this;
   if(!o.psValid()){
      return;
   }
   var g = new TDatasetServiceArg(o.topControl().name, da);
   g.form = o;
   g.controlName = o.name;
   o.toDeepAttributes(g.attributes);
   g.codes = o.getDataCodes();
   g.rows = o.getChangedRows();
   g.resultCallback = cb;
   if(!g.hasData()){
      return RMessage.warn(o, RContext.get('MDataset:nochange'));
   }
   o.psProgress(true);
   g.callback = new TInvoke(o, o.onDsProcess);
   RConsole.find(FFormConsole).process(g);
}
function MDataset_dsProcessAll(da, cb){
   var o = this;
   if(!o.psValid()){
      return;
   }
   var g = new TDatasetServiceArg(o.topControl().name, da);
   g.form = o;
   g.controlName = o.name;
   o.toDeepAttributes(g.attributes);
   g.codes = o.getDataCodes();
   g.rows = o.getRows();
   g.resultCallback = cb;
   o.psProgress(true);
   g.callback = new TInvoke(o, o.onDsProcess);
   RConsole.find(FFormConsole).process(g);
}
function MDataset_psProgress(v){
   var o = this;
   if(o.__progress == v){
      return;
   }
   o.__progress = v;
   var e = o.__progressProcess;
   e.enable = v;
   o.process(e);
}
function MDataset_psValid(){
   var o = this;
   var e = o.__validProcess;
   var cs = e.controls;
   cs.clear();
   o.process(e);
   if(!cs.isEmpty()){
      var cw = RConsole.find(FCheckWindowConsole).find();
      cw.set(cs);
      cw.show();
      return false;
   }
   return true;
}
function MDataset_dsCurrent(){
   var o = this;
   var ds = o.dsStore;
}
function MDataset_dsIsChanged(){
   var ds = this.dsStore;
   return ds ? ds.isChanged() : false;
}
function MDataset_dsCount(){
   return this.dsStore ? this.dsStore.count : 0;
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
         RMessage.fatal(o, null, 'Unknown position (postion={0})', p);
      }
   }else{
      ds.move(p);
   }
   if(RClass.isClass(o, MValue)){
      o.loadValue(ds.current());
   }
}
function MDataset_dsMovePage(p){
   var o = this;
   var ds = o.dsStore;
   if(!RInt.isInt(p)){
      if(EDataAction.First == p){
         p = 0;
      }else if(EDataAction.Prior == p){
         p = ds.pageIndex;
         if(p > 0){
            p--;
         }
      }else if(EDataAction.Next == p){
         p = ds.pageIndex;
         if(p < ds.pageCount - 1){
            p++;
         }
      }else if(EDataAction.Last == p){
         p = ds.pageCount - 1;
      }else{
         RMessage.fatal(o, null, 'Unknown page (page={0})', p);
      }
   }
   if(p != ds.pageIndex){
      o.psProgress(true);
      var t = o.topControl(MDataset);
      var g = new TDatasetFetchArg(t.name, t.formId, o.dsPageSize, p, true);
      g.path =  o.fullPath();
      g.mode = t._emode;
      g.searchs.append(o.dsGlobalSearchs);
      g.searchs.append(o.dsSearchs);
      g.orders.append(o.dsGlobalOrders);
      g.orders.append(o.dsOrders);
      g.values = o.toDeepAttributes();
      g.values.append(o.dsValues);
      g.callback = new TInvoke(o, o.onDsFetch);
      RConsole.find(FDatasetConsole).fetch(g);
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
function MDataset_dsRefresh(){
   if(this.dsService){
      this.dsMove(this.dsPage, true);
   }
}
function MDataset_doSearch(){
   var o = this;
   var sw = o.dsSearchWindow;
   if(!sw){
      sw = o.dsSearchWindow = top.RControl.create(top.FSearchWindow);
      sw.linkDsControl(o);
   }
   sw.show();
}
function MDescCalendar(o){
   o = RClass.inherits(this, o, MValidator);
   o.oeValid     = MDescCalendar_oeValid;
   o.formatValue = MDescCalendar_formatValue;
   o.formatText  = MDescCalendar_formatText;
   return o;
}
function MDescCalendar_oeValid(e){
   var o = this;
   var r = EEventStatus.Stop;
   if(o._visible && o._validable){
      var t = o.text();
      if(o.validRequire && !RValidator.validRequire(o, t)){
         e.controls.push(o);
         return r;
      }
      if(!RValidator.validDate(o, t)){
         e.controls.push(o);
         return r;
      }
   }
   return r;
}
function MDescCalendar_formatValue(t){
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
   return RString.nvl(t);
}
function MDescCalendar_formatText(v){
   if(v){
      var o = this;
      RDate.autoParse(o.date, v);
      return RDate.formatDate(o.date, o.editFormat);
   }
   return RString.nvl(v);
}
function MDescCheck(o){
   o = RClass.inherits(this, o);
   o.editTrue   = RClass.register(o, new TPtyStr('editTrue'), EBoolean.True);
   o.editFalse  = RClass.register(o, new TPtyStr('editFalse'), EBoolean.False);
   o.clearValue = MDescCheck_clearValue;
   o.resetValue = MDescCheck_resetValue;
   o.text       = MDescCheck_text;
   o.setText    = MDescCheck_setText;
   return o;
}
function MDescCheck_clearValue(){
   this.set(this.editFalse);
}
function MDescCheck_resetValue(){
   this.set(this.dataDefault);
}
function MDescCheck_text(){
   return this.hEdit.checked ? this.editTrue : this.editFalse;
}
function MDescCheck_setText(t){
   this.hEdit.checked = (this.editTrue == t);
}
function MDescCheckPicker(o){
   o = RClass.inherits(this, o);
   o.dataEmpty = RClass.register(o, new TPtyBool('dataEmpty', true));
   o.editRefer = RClass.register(o, new TPtyStr('editRefer', null));
   o.editCheck = RClass.register(o, new TPtyStr('editCheck', null));
   return o;
}
function MDescColor(o){
   o = RClass.inherits(this, o);
   return o;
}
function MDescEdit(o){
   o = RClass.inherits(this, o, MValidator, MListView, MZoom);
   o.editCase     = RClass.register(o, new TPtyStr('editCase'));
   o.editPattern  = RClass.register(o, new TPtyStr('editPattern'));
   o.editLength   = RClass.register(o, new TPtyInt('editLength'));
   o.editComplete = RClass.register(o, new TPtyBool('editComplete'));
   o.validLenmin  = RClass.register(o, new TPtyInt('validLenmin'));
   o.validLenmax  = RClass.register(o, new TPtyInt('validLenmax'));
   o.oeValid      = MDescEdit_oeValid;
   return o;
}
function MDescEdit_oeValid(e){
   var o = this;
   var r = EEventStatus.Stop;
   if(o._visible && o._validable){
      var t = o.text();
      if(o.validRequire && !RValidator.validRequire(o, t)){
         e.controls.push(o);
         return r;
      }
      if(o.editLength && !RValidator.validTextLength(o, t, o.editLength)){
         e.controls.push(o);
         return r;
      }
   }
   return r;
}
function MDescNumber(o){
   o = RClass.inherits(this, o, MValidator);
   o.editPattern     = RClass.register(o, new TPtyStr('editPattern'));
   o.editLength      = RClass.register(o, new TPtyInt('editLength'));
   o.editSplitter    = RClass.register(o, new TPtyStr('editSplitter'));
   o.editIncreate    = RClass.register(o, new TPtyStr('editIncreate', 1));
   o.editPrecision   = RClass.register(o, new TPtyStr('editPrecision'));
   o.editRound       = RClass.register(o, new TPtyBool('editRound', false));
   o.validEqlmin     = RClass.register(o, new TPtyStr('validEqlmin'));
   o.validEqlmax     = RClass.register(o, new TPtyStr('validEqlmax'));
   o.validValmin     = RClass.register(o, new TPtyStr('validValmin'));
   o.validValmax     = RClass.register(o, new TPtyStr('validValmax'));
   o.stUnit          = RClass.register(o, new TStyle('Unit'));
   o.stAdjustForm    = RClass.register(o, new TStyle('AdjustForm'));
   o.stUpButton      = RClass.register(o, new TStyle('UpButton'));
   o.stDownButton    = RClass.register(o, new TStyle('DownButton'));
   o.siUp            = RClass.register(o, new TStyleIcon('Up'));
   o.siUpSelect      = RClass.register(o, new TStyleIcon('UpSelect'));
   o.siDown          = RClass.register(o, new TStyleIcon('Down'));
   o.siDownSelect    = RClass.register(o, new TStyleIcon('DownSelect'));
   o.hAdjustForm     = null;
   o.hUpIcon         = null;
   o.hDownIcon       = null;
   o.onUpMouseDown   = RClass.register(o, new HMouseDown('onUpMouseDown'), MDescNumber_onUpMouseDown);
   o.onDownMouseDown = RClass.register(o, new HMouseDown('onDownMouseDown'), MDescNumber_onDownMouseDown);
   o.onMouseWheel    = MDescNumber_onMouseWheel;
   o.oeValid         = MDescNumber_oeValid;
   o.buildAdjustForm = MDescNumber_buildAdjustForm;
   o.formatValue     = MDescNumber_formatValue;
   o.formatText      = MDescNumber_formatText;
   o.adjustValue     = MDescNumber_adjustValue;
   return o;
}
function MDescNumber_onUpMouseDown(e){
   var o = this;
   if(o._editable){
      e.hSource.src = o.styleIconPath('Up');
      o.adjustValue(true);
   }
}
function MDescNumber_onDownMouseDown(e){
   var o = this;
   if(o._editable){
      e.hSource.src = o.styleIconPath('Down');
      o.adjustValue(false);
   }
}
function MDescNumber_onMouseWheel(s, e){
   var o = this;
   return;
   if(e.wheelDelta > 0){
      o.adjustValue(true);
   }else if(e.wheelDelta < 0){
      o.adjustValue(false);
   }
   e.cancelBubble = true;
   e.returnValue = false;
}
function MDescNumber_oeValid(e){
   var o = this;
   var r = EEventStatus.Stop;
   if(o._visible && o._validable){
      var t = o.text();
      if(o.validRequire && !RValidator.validRequire(o, t)){
         e.controls.push(o);
         return r;
      }
      if(o.validValmin && !RValidator.validMinNumber(o, t, o.validValmin, o.validEqlmin)){
         e.controls.push(o);
         return r;
      }
      if(o.validValmax && !RValidator.validMaxNumber(o, t, o.validValmax, o.validEqlmax)){
         e.controls.push(o);
         return r;
      }
   }
   return r;
}
function MDescNumber_buildAdjustForm(hp){
   var o = this;
   hp.width = 13;
   var hf = o.hAdjustForm = RBuilder.appendTable(hp, o.style('AdjustForm'));
   hf.width = '100%';
   var hc = hf.insertRow().insertCell();
   hc.className = o.style('UpButton');
   var hi = o.hUpIcon = RBuilder.appendIcon(hc, o.styleIcon('Up'));
   o.attachEvent('onUpMouseDown', hi);
   var hc = hf.insertRow().insertCell();
   hc.className = o.style('DownButton');
   var hi = o.hDownIcon = RBuilder.appendIcon(hc, o.styleIcon('Down'));
   o.attachEvent('onDownMouseDown', hi);
}
function MDescNumber_formatValue(text){
   var o = this;
   text = RString.nvl(text);
   if(RBoolean.isTrue(o.editSplitter)){
      text = o.removeSplit(text);
   }
   var p = RString.nvl(o.editFormat);
   if(!RString.isEmpty(p)){
      var s = RString.findChars(p, '[');
      var e = RString.findChars(p, ']');
      var es = p.substring(e + 1);
      if(s == -1 || e == -1){
         return alert('editFormat error : ' + o.editFormat);
      }
      text = text.substring(s);
      text = text.substring(0, text.length - es.length);
   }
   return text;
}
function MDescNumber_formatText(v){
   var o = this;
   if(v){
      v = RString.nvl(v.toString());
   }else{
      if(v != "0"){
         v = RString.nvl(v);
      }
   }
   if(!RString.isEmpty(o.editPrecision)){
      v = o.precisionValue(v);
   }
   if(RBool.isTrue(o.editSplitter)){
      if(-1 != RString.findChars(v, '.')){
         var vs = v.split('.');
         v1 = o.splitValue(vs[0]);
         v2 = o.splitValue(vs[1]);
         v = v1 + "." + v2;
      }else{
         v = o.splitValue(v);
      }
   }
   var p = RString.nvl(o.editFormat);
   if(!RString.isEmpty(p)){
      var s = RString.findChars(p, '[');
      var e = RString.findChars(p, ']');
      if(s == -1 || e == -1){
         alert('editFormat error : ' + o.editFormat);
      }
      v = p.substring(0, s).concat(v);
      v = v.concat(p.substring(e + 1))
   }
   return v;
}
function MDescNumber_adjustValue(f){
   var o = this;
   var d = o.descriptor();
   if(!d.canEdit()){
      return;
   }
   var b = RString.findChars(o.text(),"%");
   var v = RFloat.parse(o.get());
   var d = RFloat.parse(o.editIncreate);
   v = RFloat.parse(RString.removeChars(o.text(), "'"));
   o.isTextChange = true;
   if(RConsole.find(FFocusConsole).isFocus(o)){
      if(f){
         v += d;
      }else{
         v -= d;
      }
      if(-1 != b){
        o.setText(o.formatText(v) + "%")
      }else{
         o.setText(o.formatText(v));
      }
   }else{
      if(f){
         v += d;
      }else{
         v -= d;
      }
      o.set(v.toString());
   }
}
function MDescPicture(o){
   o = RClass.inherits(this, o);
   return o;
}
function MDescProcessStatus(o){
   o = RClass.inherits(this, o);
   o.levelColors      = RClass.register(o, new TPtyStr('levelColors'));
   o.levelImages      = RClass.register(o, new TPtyStr('levelImages'));
   o.stepStart        = RClass.register(o, new TPtyStr('stepStart'));
   o.stepWidth        = RClass.register(o, new TPtyStr('stepWidth'));
   o.stepCount        = RClass.register(o, new TPtyStr('stepCount'));
   return o;
}
function MDescProgress(o){
   o = RClass.inherits(this, o);
   return o;
}
function MDescSelect(o){
   o = RClass.inherits(this, o, MValidator);
   o.dataEmpty   = RClass.register(o, new TPtyBool('dataEmpty', true));
   o.editRefer   = RClass.register(o, new TPtyStr('editRefer', null));
   o.editCheck   = RClass.register(o, new TPtyStr('editCheck', null));
   o.editDynamic = RClass.register(o, new TPtyBool('editDynamic', false));
   o.stDropSelectIcon = RClass.register(o, new TStyleIcon('DropSelect'));
   o.oeValid      = MDescSelect_oeValid;
   return o;
}
function MDescSelect_oeValid(e){
   var o = this;
   var r = EEventStatus.Stop;
   if(o._visible && o._validable){
      var t = o.text();
      if(o.validRequire && !RValidator.validRequire(o, t)){
         e.controls.push(o);
         return r;
      }
      if(!RString.isEmpty(t)){
         if(RString.isEmpty(o.items.value(t))){
            return RContext.get('MDescSelect:ValidValue');
         }
      }
   }
   return r;
}
function MDesign(o){
   o = RClass.inherits(this, o);
   o.inDesign      = false;
   o.storage       = null;
   o.oeDesign      = MDesign_oeDesign;
   o.onDesignEnter = RClass.register(o, new HMouseEnter('onDesignEnter'), MDesign_onDesignEnter);
   o.onDesignLeave = RClass.register(o, new HMouseEnter('onDesignLeave'), MDesign_onDesignLeave);
   o.onDesignBegin = RClass.register(o, new HMouseEnter('onDesignBegin'), MDesign_onDesignBegin);
   o.onDesignEnd   = RClass.register(o, new HMouseEnter('onDesignEnd'), MDesign_onDesignEnd);
   return o;
}
function MDesign_oeDesign(e){
   if(e.isBefore()){
      switch(e.mode){
         case EDesign.Move:
            var o = this;
            var h = o.hPanel;
            if(e.flag){
               o.isDesign = true;
               RHtml.link(h, 'className', h.className);
               RHtml.link(h, 'onmousedown', h.onmousedown);
               h.onmousedown = null;
               o.onDesignEnter();
            }else{
               o.isDesign = false;
               h.className = RHtml.findLink(h, 'className');
               var omd = RHtml.findLink(h, 'onmousedown');
               if(omd){
                  h.onmousedown = omd;
               }
            }
            break;
         case EDesign.Border:
            var o = this;
            var h = o.hPanel;
            if(e.flag){
               RHtml.link(h, 'styleBorder', h.style.border);
               h.style.border = '1 solid red';
            }else{
               h.style.border = RHtml.findLink(h, 'styleBorder');
            }
            break;
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
   var g = o.storage = RObject.nvlObj(o.storage);
   g.designStyle = o.hPanel.className;
   g.designLayer = o.hPanel.zIndex;
   o.hPanel.className = o.style('DesignDrag');
   o.inDesign = true;
}
function MDesign_onDesignEnd(){
   var o = this;
   var g = o.storage = RObject.nvlObj(o.storage);
   o.hPanel.className = g.designStyle;
   o.hPanel.zIndex = g.designLayer;
   o.inDesign = false;
}
function MDisplay(o){
   o = RClass.inherits(this, o);
   o.dispDisplay = RClass.register(o, new TPtyBoolSet('dispDisplay', 'dispMode', EMode.Display));
   o.dispSearch  = RClass.register(o, new TPtyBoolSet('dispSearch', 'dispMode', EMode.Search));
   o.dispInsert  = RClass.register(o, new TPtyBoolSet('dispInsert', 'dispMode', EMode.Insert));
   o.dispUpdate  = RClass.register(o, new TPtyBoolSet('dispUpdate', 'dispMode', EMode.Update));
   o.dispDelete  = RClass.register(o, new TPtyBoolSet('dispDelete', 'dispMode', EMode.Delete));
   o.dispZoom    = RClass.register(o, new TPtyBoolSet('dispZoom', 'dispMode', EMode.Zoom));
   o.dispAlign   = RClass.register(o, new TPtyStr('dispAlign', EAlign.Left));
   o._visible    = true;
   o.oeMode      = MDisplay_oeMode;
   o.canVisible  = MDisplay_canVisible;
   return o;
}
function MDisplay_oeMode(e){
   var o = this;
   if(e.isBefore()){
      var v = true;
      if(!o.base.MDisplayAble){
         v = o.canVisible(e.mode);
      }
      o.setVisible(v);
   }
}
function MDisplay_canVisible(m){
   var o = this;
   switch(RString.nvl(m, o._emode)){
      case EMode.Display:
         return o.dispList;
      case EMode.Search:
         return o.dispSearch;
      case EMode.Insert:
         return o.dispInsert;
      case EMode.Update:
         return o.dispUpdate;
      case EMode.Delete:
         return o.dispDelete;
      case EMode.Zoom:
         return o.dispZoom;
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
   o.styleDrop         = RClass.register(o, new TStyle('Drop'));
   o.styleIconDrop     = RClass.register(o, new TStyleIcon('Drop'));
   o.hDropPanel        = null;
   o.hDrop             = null;
   o.onDropEnter       = RClass.register(o, new HMouseEnter('onDropEnter'));
   o.onDropLeave       = RClass.register(o, new HMouseLeave('onDropLeave'));
   o.onDropClick       = RClass.register(o, new HMouseDown('onDropClick'), MDropable_onDropClick);
   o.onDropDoubleClick = RClass.register(o, new HDoubleClick('onDropDoubleClick'), MDropable_onDropDoubleClick);
   o.onBuildDrop       = MDropable_onBuildDrop;
   o.canDrop           = MDropable_canDrop;
   o.drop              = RMethod.virtual(o, 'drop');
   return o;
}
function MDropable_onDropDoubleClick(){
   var o = this;
   if(o._editable){
      o.drop();
   }
}
function MDropable_onDropClick(){
   var o = this;
   if(o._editable){
      o.drop();
   }
}
function MDropable_onBuildDrop(){
   var o = this;
   var h = o.hDrop = RBuilder.newIcon(null, o.styleIcon('Drop'));
   h.style.width =16;
   h.style.borderLeft = '1 solid #CCCCCC';
   h.className = o.style('Drop');
   h.style.cursor = 'hand';
   o.attachEvent('onDropEnter', h);
   o.attachEvent('onDropLeave', h);
   o.attachEvent('onDropClick', h);
}
function MDropable_canDrop(){
   var o = this;
   if(RClass.isClass(o, MDesign)){
      return !RConsole.find(FDesignConsole).canDesignMove;
   }
   return true;
}
function MEditable(o){
   o = RClass.inherits(this, o);
   o.editInsert = RClass.register(o, new TPtyBoolSet('editInsert', 'editMode', EMode.Insert), false);
   o.editUpdate = RClass.register(o, new TPtyBoolSet('editUpdate', 'editMode', EMode.Update), false);
   o.editDelete = RClass.register(o, new TPtyBoolSet('editDelete', 'editMode', EMode.Delete), false);
   o.editZoom   = RClass.register(o, new TPtyBoolSet('editZoom', 'editMode', EMode.Zoom), false);
   o._absEdit   = true;
   o._editable  = false;
   o.canEdit    = MEditable_canEdit;
   return o;
}
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
function MEditDescriptor(o){
   o = RClass.inherits(this, o, MEditable);
   o.dataName          = RClass.register(o, new TPtyStr('dataName'));
   o.dataCode          = RClass.register(o, new TPtyStr('dataCode'));
   o.dataDefault       = RClass.register(o, new TPtyStr('dataDefault'));
   o.labelIcon         = RClass.register(o, new TPtyStr('labelIcon'));
   o.labelIconDisable  = RClass.register(o, new TPtyStr('labelIconDisable'));
   o.labelColor        = RClass.register(o, new TPtyStr('labelColor'));
   o.labelAlign        = RClass.register(o, new TPtyStr('labelAlign', EAlign.Left));
   o.labelValign       = RClass.register(o, new TPtyStr('labelValign', EAlign.Middle));
   o.editSearch        = RClass.register(o, new TPtyBoolSet('editSearch', 'editAccess', EEditConfig.Search));
   o.editCopy          = RClass.register(o, new TPtyBoolSet('editCopy', 'editAccess', EEditConfig.Copy));
   o.editAlign         = RClass.register(o, new TPtyStr('editAlign', EAlign.Left));
   o.editValign        = RClass.register(o, new TPtyStr('editValign', EAlign.Middle));
   o.editFormat        = RClass.register(o, new TPtyStr('editFormat'));
   o.editUnit          = RClass.register(o, new TPtyStr('editUnit'));
   o.editTip           = RClass.register(o, new TPtyStr('editTip'));
   o.validInsert       = RClass.register(o, new TPtyBoolSet('validInsert', 'validAccess', EMode.Insert));
   o.validUpdate       = RClass.register(o, new TPtyBoolSet('validUpdate', 'validAccess', EMode.Update));
   o.validDelete       = RClass.register(o, new TPtyBoolSet('validDelete', 'validAccess', EMode.Delete));
   o.validRequire      = RClass.register(o, new TPtyBool('validRequire', false));
   o.__tip             = null;
   o._validable        = false;
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
   o.oeSaveCode        = MEditDescriptor_oeSaveCode;
   o.canValid          = MEditDescriptor_canValid;
   o.__changedEvent      = new TEvent();
   o.formatValue       = MEditDescriptor_formatValue;
   o.formatText        = MEditDescriptor_formatText;
   o.setInfo           = RMethod.empty;
   o.validText         = MEditDescriptor_validText;
   return o;
}
function MEditDescriptor_onDataEnter(s, e){
   var o = this;
   if(s.__progress){
      return;
   }
   if(s._editable){
      s._hover = true;
      s.refreshStyle();
   }
   if(o.editTip){
      o.__tip = window.status;
   }
}
function MEditDescriptor_onDataLeave(s, e){
   var o = this;
   if(s.__progress){
      return;
   }
   if(s._editable){
      o._hover = false;
      o.refreshStyle();
   }
   if(o.editTip){
      window.status = o.__tip;
   }
}
function MEditDescriptor_onDataKeyDown(s, e){
   var o = this;
   if(s._editable && !s._disabled){
      s._invalidText = o.validText(s.text());
      s.refreshStyle();
   }
}
function MEditDescriptor_onDataChange(s, e){
   var o = this;
   if(s._editable && !s._disabled){
      if(s.isTextChanged()){
         var t = s.text();
         var vt = s._invalidText = o.validText(t);
         if(vt){
            s.refreshStyle();
         }else{
         }
         o.callEvent('onDataChange', o, o.__changedEvent);
      }
   }
}
function MEditDescriptor_onDataEditEnd(s, e){
   var o = this;
   var vt = s._invalidText = o.validText(s.text());
   if(vt){
      RLogger.debug(this, 'Edit valid failed ({0})', vt);
   }else{
      s.commitValue();
   }
   if(s.isTextChanged()){
	   o.callEvent('onDataChange', o, o.__changedEvent);
   }
   s.refreshStyle();
}
function MEditDescriptor_oeSaveCode(e){
   var o = this;
   if(!RString.isEmpty(o.dataName) && !RString.isEmpty(o.dataCode)){
      e.values.set(o.dataName, o.dataCode);
   }
   return EEventStatus.Stop;
}
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
function MEditDescriptor_formatValue(v){
   return RString.nvl(v);
}
function MEditDescriptor_formatText(t){
   return RString.nvl(t);
}
function MEditDescriptor_validText(t){
   var o = this;
}
function MEditor(o){
   o = RClass.inherits(this, o);
   o.onEdit       = RMethod.virtual(o, 'onEdit');
   o.onEditChange = RMethod.virtual(o, 'onEditChange');
   o.onEditBlur   = RMethod.virtual(o, 'onEditBlur');
   o.onEditFocus  = RMethod.virtual(o, 'onEditFocus');
   return o;
}
function MEditValue(o){
   o = RClass.inherits(this, o, MValue);
   o.dataValue     = RClass.register(o, new TPtyStr('dataValue'));
   o.__recordValue = null;
   o.__recordText  = null;
   o._info         = null;
   o._hover        = false;
   o._editable     = true;
   o._editing      = false;
   o._disbaled     = false;
   o._invalid      = false;
   o._invalidText  = null;
   o.oeClearValue  = MEditValue_oeClearValue;
   o.oeResetValue  = MEditValue_oeResetValue;
   o.oeLoadValue   = MEditValue_oeLoadValue;
   o.oeSaveValue   = MEditValue_oeSaveValue;
   o.oeRecordValue = MEditValue_oeRecordValue;
   o.oeValidValue  = RMethod.empty;
   o.descriptor    = MEditValue_descriptor;
   o.isTextChanged = MEditValue_isTextChanged;
   o.isDataChanged = MEditValue_isDataChanged;
   o.clearValue    = MEditValue_clearValue;
   o.resetValue    = MEditValue_resetValue;
   o.loadValue     = MEditValue_loadValue;
   o.saveValue     = MEditValue_saveValue;
   o.recordValue   = MEditValue_recordValue;
   o.commitValue   = MEditValue_commitValue;
   o.validValue    = RMethod.empty;
   o.text          = RMethod.virtual(o, 'text');
   o.setText       = RMethod.virtual(o, 'setText');
   o.get           = MEditValue_get;
   o.reget         = MEditValue_reget;
   o.set           = MEditValue_set;
   o.setInfoPack   = MEditValue_setInfoPack;
   o.setInfo       = MEditValue_setInfo;
   o.setEditable   = MEditValue_setEditable;
   o.doFocus       = MEditValue_doFocus;
   o.doBlur        = MEditValue_doBlur;
   o.refreshStyle  = RMethod.virtual(o, 'refreshStyle');
   return o;
}
function MEditValue_oeClearValue(e){
   var o = this;
   var d = o.descriptor();
   if(!RString.isEmpty(d.dataName)){
      o.clearValue();
      o.dataValue = o.reget();
   }
   return EEventStatus.Stop;
}
function MEditValue_oeResetValue(e){
   var o = this;
   var d = o.descriptor();
   if(!RString.isEmpty(d.dataName)){
      o.resetValue();
      o.dataValue = o.reget();
   }
   return EEventStatus.Stop;
}
function MEditValue_oeLoadValue(e){
   var o = this;
   var d = o.descriptor();
   var vs = e.values;
   var dn = d.dataName;
   if(!RString.isEmpty(dn)){
      if(vs.contains(dn)){
         var v = vs.nvl(dn);
         if(RControl.isInfo(v)){
            o.setInfoPack(v);
         }else{
        	 if(RControl.isGroup(v)){
        		 o.setGroupPack(v);
        	 }else{
                 o.loadValue(vs);
        	 }
         }
         o.recordValue();
         o.dataValue = o.reget();
      }
   }
   return EEventStatus.Stop;
}
function MEditValue_oeSaveValue(e){
   var o = this;
   var d = o.descriptor();
   if(!RString.isEmpty(d.dataName)){
      o.saveValue(e.values);
   }
   return EEventStatus.Stop;
}
function MEditValue_oeRecordValue(){
   var o = this;
   var d = o.descriptor();
   if(!RString.isEmpty(d.dataName)){
      o.recordValue();
   }
   return EEventStatus.Stop;
}
function MEditValue_descriptor(){
   return this;
}
function MEditValue_isTextChanged(){
   return RString.nvl(this.text()) != this.__recordText;
}
function MEditValue_isDataChanged(){
   return RString.nvl(this.reget()) != this.__recordValue;
}
function MEditValue_clearValue(){
   var o = this;
   o.set(RString.EMPTY);
   o.dataValue = RString.EMPTY;
}
function MEditValue_resetValue(){
   var o = this;
   var v = RString.nvl(o.descriptor().dataDefault);
   o.set(v);
   o.dataValue = v;
}
function MEditValue_loadValue(c, t){
   var o = this;
   var d = o.descriptor();
   if(EStore.Name == t){
      o.set(c.get(d.name));
   }else if(EStore.DataNvl == t){
      if(c.contains(d.dataName)){
         o.set(c.get(d.dataName));
      }
   }else if(EStore.Reset == t){
      o.set(RString.EMPTY);
   }else{
      o.set(c.get(d.dataName));
   }
}
function MEditValue_saveValue(c, t){
   var o = this;
   var d = o.descriptor();
   if(EStore.Name == t){
      c.set(d.name, o.reget());
   }else{
      c.set(d.dataName, o.reget());
   }
}
function MEditValue_recordValue(){
   var o = this;
   o.__recordText = RString.nvl(o.text());
   o.__recordValue = RString.nvl(o.reget());
}
function MEditValue_commitValue(){
   this.__commitValue = RString.nvl(this.reget());
}
function MEditValue_get(){
   return this.dataValue;
}
function MEditValue_reget(){
   return this.descriptor().formatValue(this.text());
}
function MEditValue_set(v){
   var o = this;
   o.dataValue = RString.nvl(v);
   o.setText(o.descriptor().formatText(v));
}
function MEditValue_setInfoPack(v){
   var o = this;
   var f = o._info;
   if(!f){
      f = o._info = new TControlInfo();
   }
   f.unpack(v);
   var d = o.descriptor();
   d.setInfo(f);
   if(d != o){
      o.setInfo(f);
   }
}
function MEditValue_setInfo(f){
   this.set(f.value);
}
function MEditValue_setEditable(v){
   var o = this;
   o._editable = v;
   o.refreshStyle();
}
function MEditValue_doFocus(){
   var o = this;
   if(o._editable){
      o._editing = true;
      o.descriptor().onDataEditBegin(o);
   }
}
function MEditValue_doBlur(){
   var o = this;
   if(o._editable && o._editing){
      o.descriptor().onDataEditEnd(o);
      o._editing = false;
   }
}
function MFocus(o){
   o = RClass.inherits(this, o);
   o.onFocus   = RClass.register(o, new HFocus('onFocus'), MFocus_onFocus);
   o.onBlur    = RClass.register(o, new HBlur('onBlur'));
   o.testFocus = RMethod.emptyTrue;
   o.testBlur  = RMethod.emptyTrue;
   o.doFocus   = RMethod.empty;
   o.doBlur    = RMethod.empty;
   o.focus     = MFocus_focus;
   o.blur      = MFocus_blur;
   return o;
}
function MFocus_onFocus(e){
   RConsole.find(FFocusConsole).focus(this, e);
}
function MFocus_focus(){
   RConsole.find(FFocusConsole).focus(this);
}
function MFocus_blur(){
   RConsole.find(FFocusConsole).blur(this);
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
   o.getFormLink =  RMethod.virtual(o, 'getFormLink');
   return o;
}
function MHorizontal(o){
   o = RClass.inherits(this, o);
   o.setVisible = MHorizontal_setVisible;
   return o;
}
function MHorizontal_setVisible(v){
   var o = this;
   var hl = o.hPanelLine;
   if(hl){
   }
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
   o.lovService     = RClass.register(o, new TPtyStr('lovService', EService.WebForm));
   o.lovRefer       = RClass.register(o, new TPtyStr('lovRefer'));
   o.lovFields      = RClass.register(o, new TPtyStr('lovFields'));
   o.lovWhere       = RClass.register(o, new TPtyStr('lovWhere'));
   o.lovOrder       = RClass.register(o, new TPtyStr('lovOrder'));
   o.__listView     = null;
   o.onListClick    = RClass.register(o, new HClick('onListClick'), MListView_onListClick);
   o.onListSelected = RMethod.empty;
   o.canListView    = MListView_canListView;
   o.setLabelStyle  = MListView_setLabelStyle;
   o.doListView     = MListView_doListView;
   return o;
}
function MListView_onListClick(e){
   var o = this;
   if(o.canListView()){
      o.doListView();
   }
}
function MListView_canListView(){
   return !RString.isEmpty(this.lovRefer) && this._editable;
}
function MListView_setLabelStyle(){
   var o = this;
   if(!RString.isEmpty(o.lovRefer)){
      o.hLabel.style.cursor = 'hand';
      o.attachEvent('onListClick', o.hLabel);
      o.hLabel.className = 'RLine_Underline';
   }
}
function MListView_doListView(cvs){
   var o = this;
   var v = o.__listView;
   if(!v){
      v = o.__listView = top.RControl.create(top.FListWindow);
   }
   v.linkConsole = RConsole;
   v.linkLovControl(o);
   v.show();
   v.fetch(cvs);
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
function MPad_setPads(l, t, r, b){
   var h = this.hPanel;
   if(!h){
      return;
   }
   var s = h.style;
   if(l){
      s.paddingLeft = l;
   }
   if(t){
      s.paddingTop = t;
   }
   if(r){
      s.paddingRight = r;
   }
   if(b){
      s.paddingBottom = b;
   }
}
function MProgress(o){
   o = RClass.inherits(this, o);
   o.oeProgress = RMethod.virtual(o, 'oeProgress');
   return o;
}
function MShadow(o){
   o = RClass.inherits(this, o);
   o.hShadow    = null;
   o.show       = MShadow_show;
   o.hide       = MShadow_hide;
   o.setVisible = MShadow_setVisible;
   return o;
}
function MShadow_show(v){
   var o = this;
   if(!o.hShadow){
      o.hShadow = RBuilder.append(RWindow.hContainer, 'DIV', 'RWindow_Shadow');
   }
   o.hShadow.style.zIndex = RLayer.next();
   if(v == false){
      o.hide();
   }else{
      var hs = o.panel(EPanel.Shadow);
      if(hs){
         var s = o.hShadow.style;
         s.pixelLeft = hs.offsetLeft + 2;
         s.pixelTop = hs.offsetTop + 2;
         s.pixelWidth = hs.offsetWidth;
         s.pixelHeight = hs.offsetHeight;
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
function MShadow_setVisible(v){
   var o = this;
   if(v){
      if(!o.hShadow){
         o.hShadow = RBuilder.append(null, 'DIV', 'RWindow_Shadow');
      }
      o.hShadow.style.zIndex = RLayer.next();
      var hs = o.panel(EPanel.Shadow);
      if(hs){
         var r = RHtml.rect(hs);
         var s = o.hShadow.style;
         s.pixelLeft = r.left + 2;
         s.pixelTop = r.top + 2;
         s.pixelWidth = r.width();
         s.pixelHeight = r.height();
         s.display = 'block';
      }
      var hp = o.panel(EPanel.Panel);
      if(hp){
         hp.style.zIndex = RLayer.next();
      }
   }else{
      if(o.hShadow){
         o.hShadow.style.display = 'none';
      }
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
   if(null != width){
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
function MSize_setBounds(l, t, r, b, force){
   var o = this;
   var h = o.panel(EPanel.Size);
   if(!h){
      return;
   }
   var s = h.style;
   var c = false;
   if(l && l >= 0){
      if(force || o.left != l){
         o.left = l;
         s.pixelLeft = l;
         c = true;
      }
   }
   if(t && t >= 0){
      if(force || o.top != t){
         o.top = t;
         s.pixelTop = t;
         c = true;
      }
   }
   if(r && r >= 0){
      var width = r-o.left+1;
      if(force || o.width != width){
         o.width = width;
         s.pixelWidth = o.width;
         c = true;
      }
   }
   if(b && b >= 0){
      var height = b-o.top+1;
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
   o.style         = MStyle_style;
   o.styleIcon     = MStyle_styleIcon;
   o.styleIconPath = MStyle_styleIconPath;
   return o;
}
function MStyle_style(n, c){
   return RClass.find(c ? c : this, true).style(n);
}
function MStyle_styleIcon(n, c){
   return 'ctl.' + RClass.name(c ? c : this, true) + '_' + n;
}
function MStyle_styleIconPath(n, c){
   return RRes.iconPath('ctl.' + RClass.name(c ? c : this, true) + '_' + n);
}
function MTop(o){
   o = RClass.inherits(this, o, MDisplayAble);
   o.space          = null;
   o._refreshed     = false;
   o.psRefreshFirst = MTop_psRefreshFirst;
   o.topResize      = MTop_topResize;
   return o;
}
function MTop_psRefreshFirst(){
   var o = this;
   if(!o._refreshed){
      o._refreshed = true;
      o.psRefresh();
   }
}
function MTop_topResize(c){
   var o = this;
   o.process(new TEventProcess(o, 'oeResize', FControl));
   RConsole.find(FListenerConsole).process(MTop, ETopAction.Resize, o, c);
}
function MValidator(o){
   o = RClass.inherits(this, o);
   o._validable = false;
   o._valid     = true;
   o._validText = null;
   o.oeValid    = RMethod.empty;
   return o;
}
function MValue(o){
   o = RClass.inherits(this, o);
   o.loadValue = RMethod.virtual(o, 'loadValue');
   o.saveValue = RMethod.virtual(o, 'saveValue');
   return o;
}
function MVertical(o){
   o = RClass.inherits(this, o);
   return o;
}
function MZoom(o){
   o = RClass.inherits(this, o);
   o.zoomRefer = RClass.register(o, new TPtyStr('zoomRefer'));
   o.zoomField = RClass.register(o, new TPtyStr('zoomField'));
   o.canZoom   = MZoom_canZoom;
   o.doZoom    = MZoom_doZoom;
   return o;
}
function MZoom_canZoom(){
   return !RString.isEmpty(this.zoomRefer);
}
function MZoom_doZoom(v){
   RFormSpace.doZoom(this, v);
}
var RBorder = new function(o){
   if(!o){o=this;}
   o.colorTop    = '#23c2d8';
   o.colorLeft   = '#29bad7';
   o.colorSplit  = '#29bad7';
   o.buildCell   = RBorder_buildCell;
   o.build       = RBorder_build;
   o.create      = RBorder_create;
   o.createFloat = RBorder_createFloat;
   RMemory.register('RBorder', o);
   return o;
}
function RBorder_create(st, hp){
   var b = new TBorder(st);
   b.hParent = hp;
   this.build(b);
   return b;
}
function RBorder_buildCell(hr, c, w, h, f){
   var hc = hr.insertCell();
   if(c){
      hc.style.backgroundColor = c;
   }
   if(w){
      hc.width = w;
   }
   if(h){
      hc.height = h;
   }
   if(f){
      RBuilder.appendEmpty(hc);
   }
   return hc;
}
function RBorder_build(t){
   var o = this;
   var ts = t.style;
   var hp = t.hParent
   var ht = t.hForm = RBuilder.newTable();
   if(EBorder.None == ts){
      var hr = o.hRow = ht.insertRow();
      t.hPanel = hr.insertCell();
      return;
   }
   var s = new TString();
   s.append("<TABLE class='RBorder_Form' border=0 cellspacing=0 cellpadding=0><TR><TD height=1><SPAN class='RBorder_Top'></SPAN></TD></TR><TR><TD height=1><SPAN class='RBorder_Before'></SPAN></TD></TR><TR><TD class='RBorder_Panel'>");
   s.append("<TABLE class='RBorder_InnerForm' width='100%' height='100%' border=0 cellspacing=0 cellpadding=0><TR>");
   if(EBorder.Round == ts){
      s.append("<TD color='#CFF6F6' width='1'></TD>");
      s.append("<TD color='#F1FFFF'></TD>");
      s.append("<TD color='#FFFFFF' width='1'></TD>");
   }else if(EBorder.RoundTitle == ts){
      s.append("<TD color='#FAFDFE' width='1'></TD>");
      s.append("<TD></TD>");
      s.append("<TD color='#FAFDFE' width='1'></TD></TR>");
      s.append("<TR heigh='1'><TD color='#FAFDFE' width='1'></TD>");
      s.append("<TD color='#FAFDFE'></TD>");
      s.append("<TD color='#FAFDFE' width='1'></TD></TR>");
      s.append("<TR><TD color='#FAFDFE' width='1'></TD>");
      s.append("<TD></TD>");
      s.append("<TD color='#FAFDFE' width='1'></TD>");
   }else if((EBorder.RoundIcon == ts) || (EBorder.RoundDrop == ts)){
      s.append("<TD color='#CFF6F6' width='1'></TD>");
      s.append("<TD color='#F1FFFF'></TD>");
      s.append("<TD color='#F1FFFF' width='1'></TD>");
      s.append("<TD color='#F1FFFF'></TD>");
      s.append("<TD color='#FFFFFF' width='1'></TD>");
   }
   s.append("</TR></TABLE>");
   s.append("</TD></TR><TR><TD height=1><SPAN class='RBorder_After'></SPAN></TD></TR><TR><TD height=1><SPAN class='RBorder_Bottom'></SPAN></TD></TR></TABLE>");
   hp.innerHTML = s.toString();
   var hf = t.hForm = hp.children[0];
   t.hFormLine = hf.rows[2].cells[0];
   var hpf = hf.rows[2].cells[0].children[0];
   var hpr = hpf.rows[0];
   t.hTop = hf.rows[0].cells[0];
   t.hTopLine = t.hTop.children[0];
   t.hBefore = hf.rows[1].cells[0];
   t.hBeforeLine = t.hBefore.children[0];
   t.hAfter = hf.rows[3].cells[0];
   t.hAfterLine = t.hAfter.children[0];
   t.hBottom = hf.rows[4].cells[0];
   t.hBottomLine = t.hBottom.children[0];
   if(EBorder.Round == ts){
      t.hLeft = hpr.cells[0];
      t.hPanel = hpr.cells[1];
      t.hRight = hpr.cells[2];
   }else if(EBorder.RoundTitle == ts){
      var hpr = hpf.rows[0];
      t.hTitleL = hpr.cells[0];
      t.hTitle = hpr.cells[1];
      t.hTitleR = hpr.cells[2];
      var hpr = hpf.rows[1];
      t.hLineL = hpr.cells[0];
      t.hLine = hpr.cells[1];
      t.hLineR = hpr.cells[2];
      var hpr = hpf.rows[2];
      t.hPanelL = hpr.cells[0];
      t.hPanel = hpr.cells[1];
      t.hPanelR = hpr.cells[2];
   }else if(EBorder.RoundIcon == ts){
      t.hLeft = hpr.cells[0];
      t.hIcon = hpr.cells[1];
      t.hSplit = hpr.cells[2];
      t.hPanel = hpr.cells[3];
      t.hRight = hpr.cells[4];
   }else if(EBorder.RoundDrop == ts){
      t.hLeft = hpr.cells[0];
      t.hPanel = hpr.cells[1];
      t.hSplit = hpr.cells[2];
      t.hDrop = hpr.cells[3];
      t.hRight = hpr.cells[4];
   }
}
function RBorder_createFloat(hp){
   var s = new TString();
   s.append("<DIV class='RBorder_FloatTo'><DIV class='RBorder_FloatTi'></DIV></DIV>");
   s.append("<DIV class='RBorder_FloatLeft'>");
   s.append("<DIV class='RBorder_FloatBody'>");
   s.append("<DIV class='RBorder_FloatRight'></DIV></DIV></DIV>");
   s.append("<DIV class='RBorder_FloatBo'><DIV class='RBorder_FloatBi'></DIV></DIV>");
   hp.innerHTML = s.toString();
   var fb = new TFloatBorder();
   fb.hParent = hp;
   fb.hPanel = hp.children[1].children[0].children[0];
   return fb;
}
var RControl = new function(o){
   if(!o){o=this;}
   o.inMoving           = false;
   o.inSizing           = false;
   o.inDesign           = false;
   o.instances          = new TList();
   o.events             = new TMap();
   o.controls           = new TMap();
   o.innerbuild         = RControl_innerbuild;
   o.build              = RControl_build;
   o.innerCreate        = RControl_innerCreate;
   o.create             = RControl_create;
   o.linkEvent          = RControl_linkEvent;
   o.attachEvent        = RControl_attachEvent;
   o.find               = RControl_find;
   o.fromNode           = RControl_fromNode;
   o.fromXml            = RControl_fromXml;
   o.toNode             = RControl_toNode;
   o.toXml              = RControl_toXml;
   o.store              = RControl_store;
   o.htmlControl        = RControl_htmlControl;
   o.psDesign           = RControl_psDesign;
   o.psMode             = RControl_psMode;
   o.isInfo             = RControl_isInfo;
   o.isGroup            = RControl_isGroup;
   o.newInstance        = RControl_newInstance;
   o.newInstanceByName  = RControl_newInstance;
   RMemory.register('RControl', o);
   return o;
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
function RControl_innerCreate(p, x, m){
   p._emode = m;
   if(RClass.isClass(p, MConfig)){
      if(EStatus.Stop == p.loadConfig(x)){
         return;
      }
   }
   var ns = x.nodes;
   if(ns){
      for(var i=0; i<ns.count; i++){
         var n = ns.get(i);
         var c = p.createChild(n);
         if(c){
            c.parent = p;
            this.innerCreate(c, n, m);
            p.push(c);
         }
      }
   }
}
function RControl_create(x, hPanel, m){
   var o = null;
   if(RClass.isClass(x, TNode)){
      if(x){
    	  if(x.name == 'CellEdit'){
    	     RControl.newInstance(FCellEdit);
    	  }else{
             o = RClass.createByName('F' + x.name);
             this.innerCreate(o, x, m);
    	  }
    	  o._emode = m;
    	  this.instances.push(o);
      }
   }else{
      o = RClass.create(x);
      o._emode = m;
   }
   if(o){
	   if(x.name != 'CellEdit'){
         o.psInitialize();
         o.psBuild();
         o.setPanel(hPanel);
	   }
   }
   return o;
}
function RControl_linkEvent(tc, sc, n, h, m){
   var o = this;
   var p = tc[n];
   if(!RMethod.isEmpty(p) || m){
      var a = RClass.find(tc.constructor).annotation(EAnnotation.Event, n);
      var e = new a.constructor();
      e.name = a.name;
      e.source = tc;
      e.sender = sc;
      e.hSource = h;
      e.ohProcess = m;
      e.onProcess = p;
      e.process = REvent.onProcess;
      REvent.find(h).push(e.type, e);
      h[e.handle] = REvent.ohEvent;
      RHtml.link(h, '_plink', tc);
      return e;
   }
}
function RControl_attachEvent(c, n, h, m){
   var o = this;
   var p = c[n];
   if(!RMethod.isEmpty(p) || m){
      var a = RClass.find(c.constructor).annotation(EAnnotation.Event, n);
      var e = new a.constructor();
      e.name = a.name;
      e.source = c;
      e.hSource = h;
      e.ohProcess = m;
      e.onProcess = p;
      e.process = REvent.onProcess;
      REvent.find(h).push(e.type, e);
      h[e.handle] = REvent.ohEvent;
      RHtml.link(h, '_plink', c);
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
function RControl_fromNode(x, h){
   if(x){
      return this.create(x, h);
   }
}
function RControl_fromXml(xml, hPanel, mode){
   return this.create(RXml.makeNode(xml), hPanel, mode);
}
function RControl_toNode(){
}
function RControl_toXml(){
}
function RControl_store(o, type){
   var x = new TNode();
   x.name = RClass.name(o).substr(1);
   if(RClass.isClass(o, FContainer)){
      o.storeConfig(x);
   }else{
      o.saveConfig(x);
   }
   return x;
}
function RControl_htmlControl(e, c){
   if(c){
      while(e){
         var o = RHtml.findLink(e, 'control');
         if(o && RClass.isClass(o, c)){
            return o;
         }
         e = e.parentElement;
      }
   }else{
      while(e){
         var o = RHtml.findLink(e, 'control');
         if(o){
            return o;
         }
         e = e.parentElement;
      }
   }
   return null;
}
function RControl_psDesign(action, mode, flag, params){
   var cs = this.instances;
   if(cs && cs.count){
      var l = cs.count;
      for(var n=0; n<l; n++){
         cs.get(n).psDesign(action, mode, flag, params);
      }
   }
}
function RControl_psMode(action, mode, flag, params){
   var cs = this.instances;
   if(cs && cs.count){
      var l = cs.count;
      for(var n=0; n<l; n++){
         cs.get(n).psMode(action, mode, flag, params);
      }
   }
}
function RControl_isInfo(v){
   return v ? (0 == v.indexOf('C#')) : false;
}
function RControl_isGroup(v){
   return v ? (0 == v.indexOf('G#')) : false;
}
function RControl_newInstance(f){
   var o = this;
   if(o.controls){
	  var n = RMethod.name(f);
      var c = o.controls.get(n);
      if(!c){
         var c = new TControl(n);
         o.controls.set(n, c);
      }
   }
   return c.newInstance(n);
}
function RControl_newInstanceByName(n){
   return;
}
var REvent = new function(){
   var o = this;
   o.current   = 0;
   o.events    = new Array();
   o.objects   = new Array();
   o.ohEvent   = REvent_ohEvent;
   o.onProcess = REvent_onProcess;
   o.nvl       = REvent_nvl;
   o.alloc     = REvent_alloc;
   o.free      = REvent_free;
   o.find      = REvent_find;
   o.process   = REvent_process;
   o.release   = REvent_release;
   RMemory.register('REvent', o);
   return o;
}
function REvent_ohEvent(){
   REvent.process(this, event);
}
function REvent_onProcess(){
   var e = this;
   RLogger.debug(e, 'Process {0} (source={1}, html={2}, process={3})', e.type, RClass.dump(e.source), RClass.dump(e.hSource), RMethod.name(e.onProcess));
   if(e.sender){
      e.onProcess.call(e.source, e.sender, e);
   }else{
      e.onProcess.call(e.source, e);
   }
   RConsole.find(FFormConsole).processEvent(e);
}
function REvent_nvl(event, sender, code){
   if(!event){
      event = new TEvent();
   }
   event.sender = sender;
   event.code = code;
   return event;
}
function REvent_alloc(s, c){
   var e = null;
   var es = this.events;
   for(var n=0; n<es.length; n++){
      if(!es[n].inUsing){
         e = es[n];
         break;
      }
   }
   if(!e){
      e = es[es.length] = new TEvent();
   }
   e.inUsing = true;
   e.sender = s;
   e.code = c;
   return e;
}
function REvent_free(e){
   e.inUsing = false;
}
function REvent_find(h){
   var u = h.uniqueNumber;
   var os = this.objects;
   var e = os[u];
   if(!e){
      e = os[u] = new THtmlEvent();
      e.link = h;
   }
   return e;
}
function REvent_process(hs, he){
   if(!(hs && he)){
      return;
   }
   var o = this;
   var un = hs.uniqueNumber;
   if(hs._psource){
	   un = hs._psource.uniqueNumber;
   }
   var eo = o.objects[un];
   if(eo){
      var es = eo.events[he.type];
      if(es){
    	  var l = es.length;
         for(var n=0; n<l; n++){
            var e = es[n];
            e.source = RHtml.findLink(hs, '_plink');
            e.hSender = he.srcElement;
            e.hSource = hs;
            if(e.attach){
               e.attach(he)
            }
            var er = e.sender ? e.sender : e.source;
            if(er && er._events){
               var ec = er._events.get(e.name);
               if(ec){
                  e.result = false;
                  ec.invoke(e.source, er, e);
                  if(e.result){
                     return;
                  }
               }
            }
            if(e.ohProcess){
               RLogger.debug(e, 'Execute {0} (source={1}, html={2}, process={2})', e.type, RClass.dump(e.source), RClass.dump(e.hSource), RMethod.name(e.ohProcess));
               if(e.sender){
                  e.ohProcess.call(e.source, e.sender, e, he);
               }else{
                  e.ohProcess.call(e.source, e, he);
               }
            }else if(e.onProcess){
               RConsole.find(FEventConsole).push(e);
            }
         }
         return true;
      }
   }
   return false;
}
function REvent_release(){
   var o = this;
   RMemory.free(o.events);
   RMemory.free(o.objects);
   o.events = null;
   o.objects = null;
}
var RValidator = new function(){
   var o = this;
   o._date                = new TDate();
   o.validRequire         = RValidator_validRequire;
   o.validDate            = RValidator_validDate;
   o.validTextLength      = RValidator_validTextLength;
   o.validMinNumber       = RValidator_validMinNumber;
   o.validMaxNumber       = RValidator_validMaxNumber;
   RMemory.register('RValidator', o);
   return o;
}
function RValidator_validRequire(v, t){
   if(RString.isEmpty(t)){
      v._valid = false;
      v._validText = RContext.get('RValidator:empty');
      return false;
   }
   return true;
}
function RValidator_validDate(v, t){
   var o = this;
   if(!RString.isEmpty(t)){
      if(null == RDate.autoParse(o._date, t)){
         v._valid = false;
         v._validText = RContext.get('RValidator:date.format');
         return false;
      }
   }
   return true;
}
function RValidator_validMinNumber(v, t, min, eq){
   var o = this;
   if(!RString.isEmpty(t)){
      var tv = parseInt(t);
      if(eq && tv < min){
         v._valid = false;
         v._validText = RContext.get('RValidator:number.min.eq', min);
         return false;
      }else if(!eq && tv <= min){
         v._valid = false;
         v._validText = RContext.get('RValidator:number.min', min);
         return false;
      }
   }
   return true;
}
function RValidator_validTextLength(v, t, l){
	var o = this;
	var cl = t.length;
	if(cl > l){
		v._valid = false;
        v._validText = RContext.get('RValidator:length.max', l);
	    return false;
	}
	return true;
}
function RValidator_validMaxNumber(v, t, min, eq){
   var o = this;
   if(!RString.isEmpty(t)){
      var tv = parseInt(t);
      if(eq && tv > min){
         v._valid = false;
         v._validText = RContext.get('RValidator:number.max.eq', min);
         return false;
      }else if(!eq && tv >= min){
         v._valid = false;
         v._validText = RContext.get('RValidator:number.max', min);
         return false;
      }
   }
   return true;
}
function TBorder(s, h){
   var o = this;
   o.style          = s;
   o.hParent        = h;
   o.hForm          = null;
   o.hRow           = null;
   o.hPanel         = null;
   o.build              = TBorder_build;
   o.setStyle           = TBorder_setStyle;
   o.setBorderColor     = TBorder_setBorderColor;
   o.setBackgroundColor = TBorder_setBackgroundColor;
   o.setVisible         = TBorder_setVisible;
   return o;
}
function TBorder_build(hp){
   var o = this;
   if(o.hParent){
      hp = o.hParent;
   }else{
      o.hParent = hp;
   }
   var ht = o.hForm = RBuilder.newTable();
   if(EBorder.None == o.style){
      var hr = o.hRow = ht.insertRow();
      o.hPanel = hr.insertCell();
      return;
   }
   var s = new TString();
   s.append("<TABLE class='RBorder_Form' border=0 cellspacing=0 cellpadding=0><TR><TD height=1><SPAN class='RBorder_Top'></SPAN></TD></TR><TR><TD height=1><SPAN class='RBorder_Before'></SPAN></TD></TR><TR><TD class='RBorder_Panel'>");
   s.append("<TABLE class='RBorder_InnerForm' width='100%' height='100%' border=0 cellspacing=0 cellpadding=0><TR>");
   switch(o.style){
      case EBorder.Round:
         s.append("<TD color='#CFF6F6' width='1'></TD>");
         s.append("<TD color='#F1FFFF'></TD>");
         s.append("<TD color='#FFFFFF' width='1'></TD>");
         break;
      case EBorder.RoundTitle:
         s.append("<TD color='#FAFDFE' width='1'></TD>");
         s.append("<TD></TD>");
         s.append("<TD color='#FAFDFE' width='1'></TD></TR>");
         s.append("<TR heigh='1'><TD color='#FAFDFE' width='1'></TD>");
         s.append("<TD color='#FAFDFE'></TD>");
         s.append("<TD color='#FAFDFE' width='1'></TD></TR>");
         s.append("<TR><TD color='#FAFDFE' width='1'></TD>");
         s.append("<TD></TD>");
         s.append("<TD color='#FAFDFE' width='1'></TD>");
         break;
      case EBorder.RoundIcon:
      case EBorder.RoundDrop:
         s.append("<TD color='#CFF6F6' width='1'></TD>");
         s.append("<TD color='#F1FFFF'></TD>");
         s.append("<TD color='#F1FFFF' width='1'></TD>");
         s.append("<TD color='#F1FFFF'></TD>");
         s.append("<TD color='#FFFFFF' width='1'></TD>");
         break;
      default:
         return RMessage.fatal('Unknown border style. (style={0})', o.style);
   }
   s.append("</TR></TABLE>");
   s.append("</TD></TR><TR><TD height=1><SPAN class='RBorder_After'></SPAN></TD></TR><TR><TD height=1><SPAN class='RBorder_Bottom'></SPAN></TD></TR></TABLE>");
   hp.innerHTML = s.toString();
   var hf = o.hForm = hp.children[0];
   o.hFormLine = hf.rows[2].cells[0];
   var hpf = hf.rows[2].cells[0].children[0];
   var hpr = hpf.rows[0];
   o.hTopRow = hf.rows[0];
   o.hTop = hf.rows[0].cells[0];
   o.hTopLine = o.hTop.children[0];
   o.hBeforeRow = hf.rows[1];
   o.hBefore = hf.rows[1].cells[0];
   o.hBeforeLine = o.hBefore.children[0];
   o.hBodyRow = hf.rows[2];
   o.hBody = hf.rows[2].cells[0];
   o.hBodyLine = o.hBody.children[0];
   o.hAfterRow = hf.rows[3];
   o.hAfter = hf.rows[3].cells[0];
   o.hAfterLine = o.hAfter.children[0];
   o.hBottomRow = hf.rows[4];
   o.hBottom = hf.rows[4].cells[0];
   o.hBottomLine = o.hBottom.children[0];
   switch(o.style){
      case EBorder.Round:
         o.hLeft = hpr.cells[0];
         o.hPanel = hpr.cells[1];
         o.hRight = hpr.cells[2];
         break;
      case EBorder.RoundTitle:
         var hpr = hpf.rows[0];
         o.hTitleL = hpr.cells[0];
         o.hTitle = hpr.cells[1];
         o.hTitleR = hpr.cells[2];
         var hpr = hpf.rows[1];
         o.hLineL = hpr.cells[0];
         o.hLine = hpr.cells[1];
         o.hLineR = hpr.cells[2];
         var hpr = hpf.rows[2];
         o.hPanelL = hpr.cells[0];
         o.hPanel = hpr.cells[1];
         o.hPanelR = hpr.cells[2];
         break;
      case EBorder.RoundIcon:
         o.hLeft = hpr.cells[0];
         o.hIcon = hpr.cells[1];
         o.hSplit = hpr.cells[2];
         o.hPanel = hpr.cells[3];
         o.hRight = hpr.cells[4];
         break;
      case EBorder.RoundDrop:
         o.hLeft = hpr.cells[0];
         o.hPanel = hpr.cells[1];
         o.hSplit = hpr.cells[2];
         o.hDrop = hpr.cells[3];
         o.hRight = hpr.cells[4];
         break;
   }
}
function TBorder_setStyle(d, c){
   var o = this;
   var s = o.style;
   if(EBorder.Round == s){
      o.hPanelL.style.backgroundColor = c;
      o.hPanelT.style.backgroundColor = c;
      o.hPanel.style.backgroundColor = c;
      o.hPanelB.style.backgroundColor = c;
      o.hPanelR.style.backgroundColor = c;
   }else if(EBorder.RoundIcon == s){
      o.hIconT.style.backgroundColor = c;
      o.hIcon.style.backgroundColor = c;
      o.hIconB.style.backgroundColor = c;
      o.hPanelL.style.backgroundColor = c;
      o.hPanelT.style.backgroundColor = c;
      o.hPanel.style.backgroundColor = c;
      o.hPanelB.style.backgroundColor = c;
      o.hPanelR.style.backgroundColor = c;
   }else if(EBorder.RoundDrop == s){
      o.hPanelL.style.backgroundColor = c;
      o.hPanelT.style.backgroundColor = c;
      o.hPanel.style.backgroundColor = c;
      o.hPanelB.style.backgroundColor = c;
      o.hDrop.style.backgroundColor = c;
      if(d){
         o.hSplitT.style.backgroundColor = '#3C7FB1';
         o.hSplitM.style.backgroundColor = '#3C7FB1';
         o.hSplitB.style.backgroundColor = '#3C7FB1';
         o.hDropLt.style.backgroundColor = '#FFFFFF';
         o.hDropL.style.backgroundColor = '#FFFFFF';
         o.hDropLb.style.backgroundColor = '#FFFFFF';
         o.hDropT.style.backgroundColor = '#FFFFFF';
         o.hDropB.style.backgroundColor = '#FFFFFF';
         o.hDropR.style.backgroundColor = '#FFFFFF';
         o.hDropBR.style.backgroundColor = '#3C7FB1';
         o.hDropBLb.style.backgroundColor = '#3C7FB1';
         o.hDropBB.style.backgroundColor = '#3C7FB1';
         o.hDropBRb.style.backgroundColor = '#3C7FB1';
      }else{
         o.hSplitT.style.backgroundColor = c;
         o.hSplitM.style.backgroundColor = c;
         o.hSplitB.style.backgroundColor = c;
         o.hDropLt.style.backgroundColor = c;
         o.hDropL.style.backgroundColor = c;
         o.hDropLb.style.backgroundColor = c;
         o.hDropT.style.backgroundColor = c;
         o.hDropB.style.backgroundColor = c;
         o.hDropR.style.backgroundColor = c;
         o.hDropBR.style.backgroundColor = '#c0dff1';
         o.hDropBLb.style.backgroundColor = '#c0dff1';
         o.hDropBB.style.backgroundColor = '#c0dff1';
         o.hDropBRb.style.backgroundColor = '#e9f1f8';
      }
   }
}
function TBorder_setBorderColor(c){
   var o = this;
   switch(o.style){
      case EBorder.Round:
         o.hTopLine.style.backgroundColor = c;
         o.hBeforeLine.style.borderLeftColor = c;
         o.hBeforeLine.style.borderRightColor = c;
         o.hBody.style.borderRightColor = c;
         o.hPanel.style.backgroundColor = '#FAFAFA';
         o.hBody.style.borderLeftColor = c;
         o.hAfterLine.style.borderLeftColor = c;
         o.hAfterLine.style.borderRightColor = c;
         o.hBottomLine.style.backgroundColor = c;
         break;
   }
}
function TBorder_setBackgroundColor(c){
   var o = this;
   switch(o.style){
      case EBorder.Round:
         o.hBeforeLine.style.backgroundColor = c;
         o.hLeft.style.backgroundColor = c;
         o.hPanel.style.backgroundColor = c;
         o.hRight.style.backgroundColor = c;
         o.hAfterLine.style.backgroundColor = c;
         break;
   }
}
function TBorder_setVisible(v){
   var o = this;
   switch(o.style){
      case EBorder.Round:
         o.hTopRow.style.display = v ? 'block' : 'none';
         o.hBeforeRow.style.display = v ? 'block' : 'none';
         o.hBody.style.borderLeft = '';
         o.hBody.style.borderRight = '';
         o.hAfterRow.style.display = v ? 'block' : 'none';
         o.hBottomRow.style.display = v ? 'block' : 'none';
         break;
   }
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
   o.template     = null;
   o.ohProcess    = TControl_ohProcess;
   o.register     = TControl_register;
   o.findEvent    = TControl_findEvent;
   o.attachEvent  = TControl_attachEvent;
   o.newInstance  = TControl_newInstance;
   return o;
}
function TControl_newInstance(n){
   var o = this;
   var t = o.template;
   var r = null;
   if(!t){
	  t = o.template = RClass.create(n);
	  t.build();
   }
   return t.clone();
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
function TControlInfo(){
   var o = this;
   o.visible         = null;
   o.enable          = null;
   o.icon            = null;
   o.iconHint        = null;
   o.color           = null;
   o.backgroundColor = null;
   o.ouid            = null;
   o.code            = null;
   o.value           = null;
   o.hint            = null;
   o.unpack          = TControlInfo_unpack;
   return o;
}
function TControlInfo_unpack(v){
   var o = this;
   var as = new TAttributes();
   as.unpack(v.substring(2));
   o.visible = as.nvl('V');
   o.enable = as.nvl('E');
   o.icon = as.nvl('I');
   o.iconHint = as.nvl('T');
   o.color = as.nvl('C');
   o.backgroundColor = as.nvl('B');
   o.ouid = as.nvl('U');
   o.code = as.nvl('S');
   o.value = as.nvl('D');
   o.hint = as.nvl('H');
}
function TEventProcess(w, p, c){
   var o = this;
   o.type     = null;
   o.owner    = w;
   o.invoke   = p;
   o.clazz    = RClass.name(c);
   o.isBefore = TEventProcess_isBefore;
   o.isAfter  = TEventProcess_isAfter;
   o.process  = TEventProcess_process;
   o.dump     = TEventProcess_dump;
   return o;
}
function TEventProcess_isBefore(){
   return (EEventType.Before == this.type);
}
function TEventProcess_isAfter(){
   return (EEventType.After == this.type);
}
function TEventProcess_process(s, t){
   var o = this;
   if(!s.base[o.clazz]){
      return;
   }
   var p = s[o.invoke];
   if(!p){
      return RMessage.fatal(o, null, 'Process invoke is null. (sender={0}, invoke={1})', RClass.dump(s), o.invoke);
   }
   var r = null;
   var sp = new TSpeed(o, 'Process invoke (owner={0}, invoke={1})', s, o.invoke);
   o.type = t;
   r = p.call(s, o);
   sp.record();
   return r;
}
function TEventProcess_dump(){
   var o = this;
   return RClass.dump(o) + ':owner=' + o.owner + ',type=' + o.type + '.invoke=' + RMethod.name(o.invoke);
}
function TFloatBorder(s){
   var o = this;
   return o;
}
function TGroupControl(){
   var o = this;
   o.items           = new TList();
   o.unpack          = TGroupControl_unpack;
   return o;
}
function TGroupControl_unpack(v){
   var o = this;
   var ss = new TStrings();
   ss.unpack(v.substring(2));
   for(n = 0; n<ss.count; n++){
      var it = new TGroupItem();
      it.unpack(ss.get(n));
      o.items.push(it);
   }
}
function TGroupItem(){
   var o = this;
   o.select           = null;
   o.label            = null;
   o.value            = null;
   o.unpack           = TGroupItem_unpack;
   return o;
}
function TGroupItem_unpack(v){
   var o = this;
   var as = new TAttributes();
   as.unpack(v);
   o.label = as.nvl('L');
   o.select = as.nvl('S');
   o.value = as.nvl('D');
}
function THtmlEvent(){
   var o = this;
   o.link    = null;
   o.events  = new Object();
   o.load    = THtmlEvent_load;
   o.push    = THtmlEvent_push;
   o.dispose = THtmlEvent_dispose;
   return o;
}
function THtmlEvent_load(e){
   var o = this;
   o.ctrlKey = e.ctrlKey;
   o.keyCode = e.keyCode;
}
function THtmlEvent_push(n, e){
   var es = this.events;
   var ea = es[n];
   if(!ea){
      ea = es[n] = new Array();
      ea.handle = e.handle;
   }
   var c = ea.length;
   for(var i=0; i<c; i++){
      if(ea[i].name == e.name){
         RMessage.fatal(this, 'push', 'Duplicate event for same control. (source={0}, event={1})', RClass.dump(e.source), RClass.dump(e));
      }
   }
   ea[ea.length] = e;
}
function THtmlEvent_dispose(){
   var o = this;
   for(var n in o.events){
      var e = o.events[n];
      if(e.length){
         o.link[e.handle] = null;
      }
   }
   if(o.link.link){
      o.link.removeAttribute('link');
   }
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
   if(!RString.isEmpty(s)){
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
   return o ? o.label : RString.nvl(v);
}
function TItems_getLabel(index){
   var o = this.items.value(index);
   return o ? o.label : '';
}
function TOrderItem(n, t){
   var o = this;
   o.name   = n;
   o.type   = t;
   o.set    = TOrderItem_set;
   o.toNode = TOrderItem_toNode;
   o.pack   = TOrderItem_pack;
   o.unpack = TOrderItem_unpack;
   return o;
}
function TOrderItem_set(n, t){
   var o = this;
   o.name = n;
   o.type = t;
}
function TOrderItem_toNode(){
   var o = this;
   var n = new TNode('OrderItem');
   n.set('name', o.name);
   n.set('type', o.type);
   return n;
}
function TOrderItem_pack(){
   var o = this;
   var as = new TAttributes();
   as.set("name", o.name);
   as.set("type", o.type);
   return as.pack();
}
function TOrderItem_unpack(s){
   var o = this;
   var as = new TAttributes();
   as.unpack(s);
   o.name = as.get("name");
   o.type = as.get("type");
}
function TOrderItems(o){
   o = moNvl(o, this);
   TList(o);
   o.pack   = TOrderItems_pack;
   o.unpack = TOrderItems_unpack;
}
function TOrderItems_pack(){
   var o = this;
   var ts = new TStrings();
   var len = o.count;
   for(var n = 0; n < len; n++){
      var s = o.get(n).pack();
      ts.push(s);
   }
   return ts.pack();
}
function TOrderItems_unpack(p){
   var o = this;
   o.clear();
   var ts = new TStrings();
   ts.unpack(p);
   for(var n = 0; n < ts.count; n++){
      t = ts.get(n);
      var ti = new TOrderItem();
      ti.unpack(t);
      o.push(ti);
   }
}
function TRoundBorder(){
   var o = this;
   o.colorDark  = '#24C2DB'
   o.colorLight = '#24C2DB'
   o.build      = TRoundBorder_build;
   o.setColor   = TRoundBorder_setColor;
   return o;
}
function TRoundBorder_build(hp){
   var o = this;
   o.hParent = hp;
   var hf = o.hForm = RBuilder.appendTable(hp);
   hf.width = '100%';
   hf.height = '100%';
   hf.style.tableLayout = 'fixed';
   var hc = o.hTopPanel = hf.insertRow().insertCell();
   hc.height = 2;
   o.hTop = RBuilder.append(hc, 'DIV', 'RBorder_Top');
   o.hBefore = RBuilder.append(hc, 'DIV', 'RBorder_Before');
   var hr = o.hLine = hf.insertRow();
   o.hPanel = hr.insertCell();
   o.hPanel.style.borderLeft = '1 solid #24C2DB'
   o.hPanel.style.borderRight = '1 solid #24C2DB'
   var hc = o.hTopPanel = hf.insertRow().insertCell();
   hc.height = 2;
   o.hAfter = RBuilder.append(hc, 'DIV', 'RBorder_After');
   o.hBottom = RBuilder.append(hc, 'DIV', 'RBorder_Bottom');
}
function TRoundBorder_setColor(c){
   var o = this;
   o.hTop.style.backgroundColor = c;
   o.hBefore.style.borderLeftColor = c;
   o.hBefore.style.borderRightColor = c;
   o.hPanel.style.borderLeftColor = c;
   o.hPanel.style.borderRightColor = c;
   o.hAfter.style.borderLeftColor = c;
   o.hAfter.style.borderRightColor = c;
   o.hBottom.style.backgroundColor = c;
}
function TSearchItem(n, v, t, f){
   var o = this;
   o.name   = n;
   o.value  = v;
   o.format = f;
   o.type   = RString.nvl(t, ESearch.Equals);
   o.set    = TSearchItem_set
   o.toNode = TSearchItem_toNode;
   o.pack   = TSearchItem_pack;
   o.unpack = TSearchItem_unpack;
   return o;
}
function TSearchItem_set(n, v, t, f){
   var o = this;
   o.name  = n;
   o.type  = RString.nvl(t, ESearch.Equals);
   o.value = v;
   o.format = f;
}
function TSearchItem_toNode(){
   var o = this;
   var n = new TNode('SearchItem');
   n.set('name', o.name);
   n.set('type', o.type);
   n.set('value', o.value);
   n.set('format', o.format);
   return n;
}
function TSearchItem_pack(){
   var o = this;
   var as = new TAttributes();
   as.set("name", o.name);
   as.set("type", o.type);
   as.set("value", o.value);
   as.set("format", o.format);
   return as.pack();
}
function TSearchItem_unpack(s){
   var o = this;
   var as = new TAttributes();
   as.unpack(s);
   o.name  = as.get("name");
   o.type  = as.get("type");
   o.value = as.get("value");
   o.format = as.get("format");
}
function TSearchItems(o){
   o = moNvl(o, this);
   TList(o);
   o.pack   = TSearchItems_pack;
   o.unpack = TSearchItems_unpack;
}
function TSearchItems_pack(){
   var o = this;
   var ts = new TStrings();
   var len = o.count;
   for(var n = 0; n < len; n++){
      var s = o.get(n).pack();
      ts.push(s);
   }
   return ts.pack();
}
function TSearchItems_unpack(p){
   var o = this;
   o.clear();
   var ts = new TStrings();
   ts.unpack(p);
   for(var n = 0; n < ts.count; n++){
      t = ts.get(n);
      var ti = new TSearchItem();
      ti.unpack(t);
      if(!RString.isEmpty(ti.name)){
         o.push(ti);
      }
      else{
         o.clear();
         RMessage.fatal(this, 'unpack', 'Invalid value (value={1})', p);
      }
   }
}
function TStyle(n){
   var o = this;
   o.annotation = EAnnotation.Style;
   o.name       = n;
   return o;
}
function TStyleIcon(n){
   var o = this;
   o.annotation = EAnnotation.StyleIcon;
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
function ERowActionFace(){
   var o = this;
   o.Insert = 'I';
   o.Update = 'U';
   o.Delete = 'D';
   return o;
}
var ERowAction = new ERowActionFace();
function FActionConsole(o){
   o = RClass.inherits(this, o, FConsole);
   o.scope         = EScope.Global;
   o.window        = null;
   o.find          = FActionConsole_find;
   return o;
}
function FActionConsole_find(){
   var o = this;
   if(!o.window){
      o.window = top.RControl.create('FActionWindow');
   }
   return o.window;
}
function FActiveConsole(o){
   o = RClass.inherits(this, o, FConsole);
   o.scope        = EScope.Page;
   o.active       = true;
   o.working      = false;
   o.interval     = 10;
   o.intervalId   = null;
   o.actives      = new TList();
   o.activeMemory = null;
   o.hWindow      = null;
   o.ohInterval   = FActiveConsole_ohInterval;
   o.onMemory     = FActiveConsole_onMemory;
   o.construct    = FActiveConsole_construct;
   o.push         = FActiveConsole_push;
   o.process      = FActiveConsole_process;
   o.processAll   = FActiveConsole_processAll;
   o.wait         = FActiveConsole_wait;
   o.release      = FActiveConsole_release;
   o.dispose      = FActiveConsole_dispose;
   return o;
}
var g_activeConsole = null;
function FActiveConsole_dispose(){
   var o = this;
   o.release();
   o.hWindow = null;
}
function FActiveConsole_onMemory(){
   RMemory.refresh();
}
function FActiveConsole_construct(){
   var o = this;
   o.base.FConsole.construct.call(o);
   o.hWindow = window;
   g_activeConsole = o;
   var a = o.activeMemory = new TActive(o, o.onMemory);
   a.interval = 10000;
   o.push(a);
   o.intervalId = o.hWindow.setInterval(o.ohInterval, o.interval);
}
function FActiveConsole_push(a){
   if(a){
      var o = this;
      if(!o.actives.contains(a)){
         a.id = o.actives.push(a);
         a.name = 'T:' + RString.lpad(a.id, 4, '0');
      }
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
         for(var n=0; n<as.count; n++){
            o.process(as.get(n));
         }
      o.working = false;
   }
}
function FActiveConsole_ohInterval(){
   var ac = g_activeConsole;
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
function FButtonConsole(o){
   o = RClass.inherits(this, o, FConsole);
   o.scope         = EScope.Global;
   o.window        = null;
   o.find          = FButtonConsole_find;
   return o;
}
RConsole.register(new TConsole(EScope.Page, FKeyConsole, true));
function FButtonConsole_find(){
   var o = this;
   if(!o.window){
      o.window = top.RControl.create('FButtonWindow');
   }
   return o.window;
}
function FButtonFormConsole(o) {
   o = RClass.inherits(this, o, FConsole);
   o.scope = EScope.Global;
   o.window = null;
   o.find = FButtonFormConsole_find;
   return o;
}
RConsole.register(new TConsole(EScope.Page, FKeyConsole, true));
function FButtonFormConsole_find() {
   var o = this;
   if (!o.window) {
      o.window = top.RControl.create('FButtonFormWindow');
   }
   return o.window;
}
function FChartConsole(o){
   o = RClass.inherits(this, o, FConsole);
   o.scope          = EScope.Page;
   o.defines        = null;
   o.events         = null;
   o.lsnsLoaded     = null;
   o.formId         = 0;
   o.onLoaded       = FChartConsole_onLoaded;
   o.construct      = FChartConsole_construct;
   o.loadService    = FChartConsole_loadService;
   o.loadChart      = FChartConsole_loadChart;
   return o;
}
function FChartConsole_onLoaded(e){
   var o = this;
   e.argument.callback.invoke(e);
}
function FChartConsole_construct(){
   var o = this;
   o.defines = new TMap();
   o.defines.set(EForm.Form, new TMap());
   o.defines.set(EForm.Lov, new TMap());
   o.events = new TMap();
   o.lsnsLoaded = new TListeners();
}
function FChartConsole_loadService(g){
   var o = this;
   var doc = new TXmlDocument();
   var root = doc.root();
   root.set('action', 'chart');
   var f = root.create('Chart');
   f.set('name', g.name);
   var e = new TEvent(o, EXmlEvent.Send, o.onLoaded);
   e.url = RService.url('logic.webform.dataset');
   e.document = doc;
   e.argument = g;
   RConsole.find(FXmlConsole).process(e);
}
function FChartConsole_loadChart(g){
   var o = this;
   var doc = new TXmlDocument();
   var root = doc.root();
   root.set('action', 'chart');
   var f = root.create('Chart');
   f.set('name', g.name);
   var url = RService.url('logic.webform.dataset');
   var doc = RConsole.find(FXmlConsole).send(url, doc);
   return doc.xml().toString();
}
function FCheckWindowConsole(o){
   o = RClass.inherits(this, o, FConsole);
   o.scope         = EScope.Global;
   o.find          = FCheckWindowConsole_find;
   o.window        = null;
   return o;
}
function FCheckWindowConsole_find(){
   var o = this;
   var w = o.window;
   if(!w){
      w = o.window = RControl.create( FCheckWindow );
   }
   return w;
}
function FCodeListConsole(o){
   o = RClass.inherits(this, o, FConsole);
   o.scope        = EScope.Page;
   o.fetch        = FCodeListConsole_fetch;
   return o;
}
function FCodeListConsole_fetch(g){
   var o = this;
   var doc = new TXmlDocument();
   var root = doc.root();
   root.set('action', 'codelist');
   var f = root.create('CodeList');
   var nd = g.toNode();
   if(nd){
      f.push(nd);
   }
   f.set('name', g.name);
   var url = RService.url('logic.webform');
   var doc = RConsole.find(FXmlConsole).send(url, doc);
   var root = doc.root();
   var mc = RConsole.find(FMessageConsole);
   var r = mc.checkResult(root);
   if(r){
      return doc;
   }
}
function FCompleteConsole(o){
   o = RClass.inherits(this, o, FConsole);
   o.scope       = EScope.Page;
   o.serviceName = 'logic.dataset';
   o.onLoaded    = FCompleteConsole_onLoaded;
   o.search      = FCompleteConsole_search;
   return o;
}
function FCompleteConsole_onLoaded(e){
   var o = this;
   var xr = e.document.root();
   var r = RConsole.find(FMessageConsole).checkResult(xr);
   if(r){
      e.control.onComplete(xr);
   }
}
function FCompleteConsole_search(c){
   var o = this;
   o.activeControl = c;
   var doc = new TXmlDocument();
   var root = doc.root();
   root.set('action', EDataAction.Complete);
   var node = root.create('Control');
   node.set('form', c.formName);
   node.set('control', c.controlName);
   node.set('value', c.controlValue);
   var e = new TEvent(o, EXmlEvent.Send, o.onLoaded);
   e.url = RService.url(o.serviceName);
   e.document = doc;
   e.control = c;
   RConsole.find(FXmlConsole).process(e);
}
function FConsole(o){
   o = RClass.inherits(this, o, FObject);
   o.scope = EScope.Global;
   return o;
}
function FDatasetConsole(o){
   o = RClass.inherits(this, o, FConsole);
   o.scope            = EScope.Page;
   o.datasets         = null;
   o.lsnsLoaded       = null;
   o.lsnsUpdateBegin  = null;
   o.lsnsUpdateEnd    = null;
   o.onFetchLoaded    = FDatasetConsole_onFetchLoaded;
   o.onScalarLoaded   = FDatasetConsole_onScalarLoaded;
   o.onCompleteLoaded = FDatasetConsole_onCompleteLoaded;
   o.onLovLoaded      = FDatasetConsole_onLovLoadeded;
   o.onPrepareLoaded  = FDatasetConsole_onPrepareLoaded;
   o.onUpdateLoaded   = FDatasetConsole_onUpdateLoaded;
   o.onTreeLoaded     = FDatasetConsole_onTreeLoaded;
   o.onLoaded         = FDatasetConsole_onLoaded;
   o.construct        = FDatasetConsole_construct;
   o.loadDataset      = FDatasetConsole_loadDataset;
   o.loadDatasets     = FDatasetConsole_loadDatasets;
   o.get              = FDatasetConsole_get;
   o.getById          = FDatasetConsole_getById;
   o.getByPath        = FDatasetConsole_getByPath;
   o.fetch            = FDatasetConsole_fetch;
   o.scalar           = FDatasetConsole_scalar;
   o.complete         = FDatasetConsole_complete;
   o.lov              = FDatasetConsole_lov;
   o.prepare          = FDatasetConsole_prepare;
   o.update           = FDatasetConsole_update;
   o.onColumnFetch    = FDatasetConsole_onColumnFetch;
   o.columnNodeFetch  = FDatasetConsole_columnNodeFetch;
   o.treeUpdate       = FDatasetConsole_treeUpdate;
   return o;
}
function FDatasetConsole_onScalarLoaded(g, r){
   var o = this;
   if(r.hasNode()){
      var rc = g.resultConfig = r.find('Control');
      if(rc){
         g.result = rc.get('result');
      }
   }
   g.invoke();
}
function FDatasetConsole_scalar(g){
   var o = this;
   var doc = new TXmlDocument();
   var r = doc.root();
   r.set('action', EDataAction.Scalar);
   r.push(g.toNode());
   var e = new TEvent(o, EXmlEvent.Send, o.onLoaded);
   e.url = RService.url('logic.webform.dataset');
   e.action = EDataAction.Scalar;
   e.argument = g;
   e.document = doc;
   RConsole.find(FXmlConsole).process(e);
}
function FDatasetConsole_loadDataset(fd, x){
   var o = this;
   var dn = x.get('name') + '@' + fd;
   var ds = o.datasets.get(dn);
   if(!ds){
      ds = new TDataset();
      o.datasets.set(dn, ds);
      ds.id = dn;
   }
   ds.clear();
   ds.loadNode(x);
   return ds;
}
function FDatasetConsole_loadDatasets(dss, fd, x){
   var o = this;
   if(x && x.hasNode()){
      var xds = x.nodes;
      for(var n=0; n<xds.count; n++){
         var xd = xds.get(n);
         if(xd.isName('Dataset')){
            var ds = o.loadDataset(fd, xd);
            dss.set(ds.name, ds);
         }
      }
   }
}
function FDatasetConsole_onFetchLoaded(g, x){
   var o = this;
   var rds = g.resultDatasets;
   if(x.hasNode()){
      var xfs = x.nodes;
      var xfc = xfs.count;
      for(var n = 0; n < xfc; n ++){
         var xf = xfs.get(n);
         var fd = xf.get('id');
         if(!RString.isEmpty(fd)){
            o.loadDatasets(rds, fd, xf);
         }
      }
   }
   if(!rds.isEmpty()){
      var c = rds.count;
      for(var n=0; n<c; n++){
         var rd = rds.value(n);
         if('/' == rd.name){
            g.resultDataset = rd;
            g.resultRow = rd.row(0);
            break;
         }
      }
   }
   g.invoke();
}
function FDatasetConsole_onCompleteLoaded(g, root){
   var o = this;
   if(root.hasNode()){
      var nc = root.find('Control');
      if(nc){
         g.resultConfig = nc;
      }
   }
   g.invoke();
}
function FDatasetConsole_onLovLoadeded(arg, root){
   var o = this;
   arg.lovNode = root;
   arg.invoke();
}
function FDatasetConsole_onPrepareLoaded(g, x){
   var o = this;
   var rds = g.resultDatasets;
   if(x.hasNode()){
      var xfs = x.nodes;
      var xfc = xfs.count;
      for(var n = 0; n < xfc; n ++){
         var xf = xfs.get(n);
         var fd = xf.get('id');
         if(!RString.isEmpty(fd)){
            o.loadDatasets(rds, fd, xf);
         }
      }
   }
   if(!rds.isEmpty()){
      var c = rds.count;
      for(var n=0; n<c; n++){
         var rd = rds.value(n);
         if('/' == rd.name){
            g.resultRow = rd.row(0);
            break;
         }
      }
   }
   g.invoke();
}
function FDatasetConsole_onUpdateLoaded(g, x){
   var o = this;
   var xf = x.find('Form');
   if(!xf){
      return;
   }
   var fd = xf.get('id');
   var xd = xf.find('Dataset');
   if(!xd){
      return;
   }
   var ds = g.resultDataset = o.loadDataset(fd, xd);
   g.resultRow = ds.row(0);
   g.invoke();
   RWindow.setEnable(true);
}
function FDatasetConsole_onLoaded(e){
   var o = this;
   var r = e.document.root();
   var g = e.argument;
   if(!e.messageChecked){
      var m = new TMessageArg();
      m.argument = g;
      m.form = g.form;
      m.config = r;
      m.invokeCaller = new TInvoke(o, o.onLoaded);
      m.invokeParam = e;
      m.event = e;
      if(!RConsole.find(FMessageConsole).checkResult(m)){
         return;
      }
   }
   g.configResult = r;
   switch(e.action){
      case EDataAction.Fetch:
         o.onFetchLoaded(g, r);
         break;
      case EDataAction.Prepare:
         o.onPrepareLoaded(g, r);
         break;
      case EDataAction.Update:
         o.onUpdateLoaded(g, r);
         break;
      case EDataAction.Lov:
         o.onLovLoaded(g, r);
         break;
      case EDataAction.Scalar:
         o.onScalarLoaded(g, r);
         break;
      case EDataAction.Complete:
         o.onCompleteLoaded(g, r);
         break;
   }
   RConsole.find(FListenerConsole).process(MDataset, EAction.Changed, e, e)
}
function FDatasetConsole_construct(){
   var o = this;
   o.datasets = new TMap();
   o.lsnsLoaded      = new TListeners();
   o.lsnsUpdateBegin = new TListeners();
   o.lsnsUpdateEnd   = new TListeners();
}
function FDatasetConsole_fetch(g){
   var o = this;
   var doc = new TXmlDocument();
   var root = doc.root();
   root.set('action', 'fetch');
   root.push(g.toNode());
   var e = new TEvent(o, EXmlEvent.Send, o.onLoaded);
   e.url = RService.url('logic.webform.dataset');
   e.action = EDataAction.Fetch;
   e.argument = g;
   e.document = doc;
   RConsole.find(FXmlConsole).process(e);
}
function FDatasetConsole_complete(g){
   var o = this;
   var doc = new TXmlDocument();
   var root = doc.root();
   root.set('action', 'complete');
   root.push(g.toNode());
   var e = new TEvent(o, EXmlEvent.Send, o.onLoaded);
   e.url = RService.url('logic.webform.dataset');
   e.action = EDataAction.Complete;
   e.argument = g;
   e.document = doc;
   RConsole.find(FXmlConsole).process(e);
}
function FDatasetConsole_lov(g){
   var o = this;
   var doc = new TXmlDocument();
   var root = doc.root();
   root.set('action', 'lov');
   root.push(g.toNode());
   var e = new TEvent(o, EXmlEvent.Send, o.onLoaded);
   e.url = RService.url('logic.webform.dataset');
   e.action = EDataAction.Lov;
   e.argument = g;
   e.document = doc;
   RConsole.find(FXmlConsole).process(e);
}
function FDatasetConsole_prepare(g){
   var o = this;
   var doc = new TXmlDocument();
   var root = doc.root();
   root.set('action', 'prepare');
   root.push(g.toNode());
   var e = new TEvent(o, EXmlEvent.Send, o.onLoaded);
   e.url = RService.url('logic.webform.dataset');
   e.action = EDataAction.Prepare;
   e.argument = g;
   e.document = doc;
   RConsole.find(FXmlConsole).process(e);
}
function FDatasetConsole_update(g){
   var o = this;
   var doc = new TXmlDocument();
   var root = doc.root();
   root.set('action', 'update');
   if(g.checked){
      root.set('checked', g.checked);
   }
   root.push(g.toNode());
   var e = new TEvent(o, EXmlEvent.Send, o.onLoaded);
   e.url = RService.url('logic.webform.dataset');
   e.action = EDataAction.Update;
   e.argument = g;
   e.document = doc;
   RConsole.find(FXmlConsole).process(e);
}
function FDatasetConsole_get(id){
   var o = this;
   var ds = o.forms.get(id);
   return ds;
}
function FDatasetConsole_getById(id){
   var o = this;
   var d = o.datasets.get(id);
   return d;
}
function FDatasetConsole_getByPath(formId, path){
   var o = this;
   var ds = o.get(formId);
   return ds ? ds.get(path) : null;
}
function FDatasetConsole_onTreeLoaded(g){
   var o = this;
   alert(1);
}
function FDatasetConsole_onColumnFetch(e){
   var o = this;
   var root = e.document.root();
   var mc = RConsole.find(FMessageConsole);
   var r = mc.checkResult(root);
   if(r){
      var g = e.arg;
      if(root.hasNode()){
         var fs = root.nodes;
         var ct = fs.count;
         for(var k = 0; k < ct; k++){
            var f = fs.get(k);
            if(f.hasNode()){
               var ns = f.nodes;
               var nt = ns.count;
               for( n = 0; n < nt; n++){
                  var d = ns.get(n);
                  if(d.name == 'Data'){
                     g.resultConfig = d;
                     break;
                  }
               }
            }
         }
      }
      g.invoke();
   }
}
function FDatasetConsole_columnNodeFetch(g){
   var o = this;
   var doc = new TXmlDocument();
   var root = doc.root();
   root.set('action', g.action);
   var nd = g.toNode();
   root.push(nd);
   var url = RService.url(g.service);
   var e = new TEvent(o, EXmlEvent.Send, o.onColumnFetch);
   e.url = url;
   e.document = doc;
   e.arg = g;
   e.action = EDataAction.Fetch;
   RConsole.find(FXmlConsole).process(e);
}
function FDatasetConsole_treeUpdate(g){
   var o = this;
   var doc = new TXmlDocument();
   var root = doc.root();
   root.set('action', g.action);
   var nd = g.toNode();
   root.push(nd);
   var url = RService.url(g.service);
   var e = new TEvent(o, EXmlEvent.Send, o.onTreeLoaded);
   e.url = url;
   e.document = doc;
   e.arg = g;
   e.action = EDataAction.TreeUpdate;
   RConsole.find(FXmlConsole).process(e);
}
function FDateConsole(o){
   o = RClass.inherits(this, o, FConsole);
   o.scope       = EScope.Page;
   o.dateDiffer  = 0;
   o.serverDate  = null;
   o.currentDate = FDateConsole_currentDate;
   o.setDate     = FDateConsole_setDate;
   return o;
}
function FDateConsole_currentDate(){
   var o = this;
   var t = new TDate();
   t.addMseconds(o.dateDiffer);
   return t;
}
function FDateConsole_setDate(d){
   var o = this;
   o.serverDate = d;
}
function FDesignConsole(o){
   o = RClass.inherits(this, o, FConsole);
   o.scope          = EScope.Page;
   o.padWidth       = 2;
   o.padHeight      = 4;
   o.layerSplit     = 10000;
   o.layerControl   = 20000;
   o.onDragStart    = RClass.register(o, new HMouseDown('onDragStart'), FDesignConsole_onDragStart);
   o.onDrag         = RClass.register(o, new HMouseMove('onDrag'), FDesignConsole_onDrag);
   o.onDragStop     = RClass.register(o, new HMouseUp('onDragStop'), FDesignConsole_onDragStop);
   o._moveable      = false;
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
   o.isDesign       = FDesignConsole_isDesign;
   return o;
}
function FDesignConsole_isDesign(){
   return this._moveable;
}
function FDesignConsole_onDragStart(s){
   if(!s.isDraging){
      this.startDrag(s);
   }
}
function FDesignConsole_onDrag(s, e){
   var p = this.movePos
   p.x = e.clientX;
   p.y = e.clientY;
}
function FDesignConsole_onDragStop(s, e){
   this.stopDrag(s, false, e.ctrlKey);
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
   RWindow.lsnsMouseDown.register(o, o.design);
   RWindow.lsnsMouseOver.register(o, o.hover);
   RWindow.lsnsKeyUp.register(o, o.leave);
   o.active = new TActive(o, o.onInterval);
   RLogger.debug(o.active, 'Push active object');
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
      r.psBuild();
      RWindow.appendControl(r);
      var h = r.panel(EPanel.Move);
      RControl.linkEvent(o, r, 'onDragStart', h);
      RControl.linkEvent(o, r, 'onDrag', h);
      RControl.linkEvent(o, r, 'onDragStop', h);
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
   }
}
function FDesignConsole_leave(){
   var o = this;
   if(o.mode == EDesign.Move && !event.ctrlKey){
      RLog.debug(o, 'Design move leave (container={0}, control={1})', RClass.dump(o.hoverContainer), RClass.dump(o.activeControl));
      if(o.activeControl){
         o.stopDrag(o.activeControl, true);
      }else if(o.hoverControl){
         o.hoverControl.onDesignLeave();
         o.hoverControl = null;
      }
      if(o.hoverContainer){
         o.hoverContainer.psDesign(EDesign.Move, false);
         o.hoverContainer = null;
      }
   }
   if(o.mode == EDesign.Border && !event.ctrlKey && !event.shiftKey){
      if(o.hoverContainer){
         o.hoverContainer.psDesign(EDesign.Border, false);
         o.hoverContainer = null;
      }
   }
}
function FDesignConsole_design(){
   var o = this;
   if(o._moveable){
      var e = RWindow.event();
      var ctl = RControl.htmlControl(e.srcElement, MDesign);
      if(ctl){
         var cnt = ctl.topControl(FContainer);
         if(ctl && cnt){
            RLog.debug(o, 'Design control (container={0}, control={1})', RClass.dump(cnt), RClass.dump(ctl));
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
   RLogger.debug(o, 'StartDrag (rect={0}, control={1}, parent={2}:{3})', o.actRect.dump(), o.controlPos.dump(), o.parentPos.dump(), o.parentPosPanel.currentStyle.overflow);
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
   RLogger.debug(o, 'rect={0}, control={1}, parent={2}:{3}', o.movePos.dump(), o.splitRect.left-o.parentPos.x, o.splitRect.top-o.parentPos.y, o.splitRect.width(), o.splitRect.height());
   ctl.onDesignBegin();
   o.active.status = EActive.Active;
   return;
}
function FDesignConsole_drag(ctl){
}
function FDesignConsole_stopDrag(ctl, cancel, copy){
   var o = this;
   var h = ctl.hPanel;
   o.active.status = EActive.Sleep;
   ctl.onDesignEnd();
   o.hSplit.style.display = 'none';
   if(!cancel){
      o.actContainer.moveChild(o.activeControl, o.posControl, o.posAction, copy);
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
      this._moveable = value;
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
   var name = RClass.name(cls);
   var editor = this.hoverEditors.get(name);
   if(!editor){
      editor = RClass.create(cls);
      editor.psBuild();
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
      editor = RObject.nvl(editor, o.hoverEditor);
      o.hoverEditor = null;
      RLog.debug(o, 'Leave {0}', RClass.dump(editor));
   }
}
function FEditConsole_focus(c, n, l){
   var o = this;
   var name = o.makeName(n, l);
   var e = o.editors.get(l);
   if(!e){
      e = RClass.create(n);
      e.psBuild();
      o.editors.set(l, e);
   }
   RLogger.debug(o, 'Focus editor {0} (editable={1}, name={2})', RClass.dump(e), RClass.dump(c), l);
   e.reset();
   if(RClass.isClass(e, FDropEditor)){
      e.linkControl(c);
      o.focusEditor = e;
   }
   return e;
}
function FEditConsole_blur(editor){
   var o = this;
   if(o.focusEditor){
      RLogger.debug(o, 'Blur {0}', RClass.dump(editor));
      editor = RObject.nvl(editor, o.focusEditor);
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
   o.active.interval = 1;
   RConsole.find(FActiveConsole).push(o.active);
   RLogger.debug(o, 'Add event active (active={0})', RClass.dump(o.active));
}
function FEventConsole_register(owner, proc){
   this.events.push(new TEvent(owner, null, proc));
}
function FEventConsole_process(){
   var o = this;
   var es = o.events;
   if(es.count){
      var sp = new TSpeed(o, 'Process events (count={0})', es.count);
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
      sp.record();
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
         var c = o.events.count;
         for(var n=0; n<c; n++){
            if(o.events.get(n) == e){
               o.events.set(n, null);
            }
         }
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
function FEventEngineConsole(o){
   o = RClass.inherits(this, o, FConsole);
   o.scope      = EScope.Page;
   o.events     = new TMap();
   o.loadConfig = FEventEngineConsole_loadConfig;
   o.process    = FEventEngineConsole_process;
   return o;
}
function FEventEngineConsole_loadConfig(cfg){
   var o = this;
   if(!(cfg && cfg.nodes)){
      return;
   }
   var ns = cfg.nodes;
   var l = ns.count;
   for(var n = 0; n < l; n++){
      var x = ns.get(n);
      if(x.isName('Event')){
         var c = RClass.create(FEvent);
         c.loadConfig(x);
         if(RString.isEmpty(c.name) || RString.isEmpty(c.source) || RString.isEmpty(c.form)){
            RMessage.fatel(o, null, "Event property is invalid. (event={0})", x.xml());
         }
         var s = c.name + '@' + c.source + '@' + c.form;
         o.events.set(s, c);
      }
   }
}
function FEventEngineConsole_process(e){
   var o = this;
   var es = o.events;
   if(es.isEmpty()){
      return;
   }
   var se = e.source;
   if(RClass.isClass(se, FControl)){
      var p = se.topControl(MDataset);
      if(p){
         var s = e.name + '@' + se.name + '@' + p.name;
         var c = es.get(s);
         if(c && c.code){
            if(c.event){
               c.event.call(se);
            }else{
               c.event = new Function(c.code);
               c.event.call(se);
            }
         }
      }
   }
}
function FFocusConsole(o){
   o = RClass.inherits(this, o, FConsole);
   o.scope              = EScope.Page;
   o.__blurAble         = true;
   o.__focusAble        = true;
   o.__focusClasses     = null;
   o.__storeControl     = null;
   o.hoverContainer     = null;
   o.hoverControl       = null;
   o.focusControl       = null;
   o.blurControl        = null;
   o.activeControl      = null;
   o.lsnsFocus          = null;
   o.lsnsBlur           = null;
   o.lsnsFocusClass     = null;
   o.onWindowMouseDown  = FFocusConsole_onWindowMouseDown;
   o.onWindowMouseWheel = FFocusConsole_onWindowMouseWheel;
   o.construct          = FFocusConsole_construct;
   o.isFocus            = FFocusConsole_isFocus;
   o.enter              = FFocusConsole_enter;
   o.leave              = FFocusConsole_leave;
   o.focus              = FFocusConsole_focus;
   o.blur               = FFocusConsole_blur;
   o.findClass          = FFocusConsole_findClass;
   o.focusClass         = FFocusConsole_focusClass;
   o.focusHtml          = FFocusConsole_focusHtml;
   o.lockBlur           = FFocusConsole_lockBlur;
   o.unlockBlur         = FFocusConsole_unlockBlur;
   o.storeFocus         = FFocusConsole_storeFocus;
   o.restoreFocus       = FFocusConsole_restoreFocus;
   o.dispose            = FFocusConsole_dispose;
   return o;
}
function FFocusConsole_onWindowMouseDown(s, e){
   this.focusHtml(e);
}
function FFocusConsole_onWindowMouseWheel(s, e){
   var o = this;
   var fc = this.focusControl;
   if(RClass.isClass(fc, MMouseWheel)){
      fc.onMouseWheel(s, e);
   }
}
function FFocusConsole_construct(){
   var o = this;
   o.base.FConsole.construct.call(o);
   o.__focusClasses = new Object();
   o.lsnsFocus = new TListeners();
   o.lsnsBlur = new TListeners();
   o.lsnsFocusClass = new TListeners();
   RLogger.debug(o, 'Add listener for window mouse down and wheel');
   RWindow.lsnsMouseDown.register(o, o.onWindowMouseDown);
   RWindow.lsnsMouseWheel.register(o, o.onWindowMouseWheel);
}
function FFocusConsole_isFocus(c){
   return (this.focusControl == c);
}
function FFocusConsole_enter(c){
   var o = this;
   if(RClass.isClass(c, MContainer)){
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
   var o = this;
   if(!RClass.isClass(c, MFocus)){
      return;
   }
   var f = o.focusControl;
   if(f == c){
      return;
   }
   var bc = o.blurControl;
   if(bc != f){
      if(o.__blurAble && f && f.testBlur(c)){
         RLogger.debug(o, 'Blur focus control (name={0}, instance={1})', f.name, RClass.dump(f));
         o.blurControl = f;
         f.doBlur(e);
         o.lsnsBlur.process(f);
      }
   }
   if(o.__focusAble){
      RLogger.debug(o, 'Focus control (name={0}, instance={1})', c.name, RClass.dump(c));
      c.doFocus(e);
      o.focusControl = o.activeControl = c;
      o.lsnsFocus.process(c);
   }
}
function FFocusConsole_blur(c, e){
   var o = this;
   var fc = o.focusControl;
   var bc = o.blurControl;
   if(fc && c && !fc.testBlur(c)){
      return;
   }
   if(bc != c && RClass.isClass(c, MFocus)){
      RLogger.debug(o, 'Blur control (name={0}, instance={1})', c.name, RClass.dump(c));
      o.blurControl = c;
      c.doBlur(e);
   }
   if(fc){
      RLogger.debug(o, 'Blur focus control (name={0}, instance={1})', fc.name, RClass.dump(fc));
      fc.doBlur(e);
      o.focusControl = null;
   }
}
function FFocusConsole_findClass(c){
   var o = this;
   var n = RClass.name(c);
   if(o.__focusClasses[n]){
      return o.__focusClasses[n];
   }
   var p = o.activeControl;
   if(RClass.isClass(p, FEditor)){
      p = p.source;
   }
   if(p){
      return p.topControl(c);
   }
}
function FFocusConsole_focusClass(c, p){
   var o = this;
   var n = RClass.name(c);
   if(o.__focusClasses[n] != p){
      o.__focusClasses[n] = p;
      RLogger.debug(o, 'Focus class {0}=[{1}]', n, RClass.dump(p));
      o.lsnsFocusClass.process(p, c);
   }
}
function FFocusConsole_focusHtml(he){
   var o = this;
   var c = RControl.htmlControl(he.srcElement);
   RLogger.debug(o, 'Focus html control (control={0},element={1})', RClass.dump(c), he.srcElement.tagName);
   if(c){
      if(o.focusControl != c){
         o.blur(c, he);
      }
   }else{
      o.blur(null, he);
   }
}
function FFocusConsole_lockBlur(){
   this.__blurAble = false;
}
function FFocusConsole_unlockBlur(){
   this.__blurAble = true;
}
function FFocusConsole_storeFocus(){
   var o = this;
   o.__storeControl = o.focusControl;
}
function FFocusConsole_restoreFocus(){
   var o = this;
   if(o.__storeControl){
      o.__storeControl.focus();
      o.__storeControl = null;
   }
}
function FFocusConsole_dispose(){
   var o = this;
   o.base.FConsole.dispose.call(o);
   o.__focusClasses = null;
}
function FFormConsole(o){
   o = RClass.inherits(this, o, FConsole);
   o.scope            = EScope.Page;
   o.forms            = null;
   o.freeForms        = null;
   o.formsLoaded      = null;
   o.formIds          = null;
   o.lsnsLoaded       = null;
   o.events           = null;
   o.onProcessLoaded  = FFormConsole_onProcessLoaded;
   o.construct        = FFormConsole_construct;
   o.createFromName   = FFormConsole_createFromName;
   o.get              = FFormConsole_get;
   o.find             = FFormConsole_find;
   o.hiddenAll        = FFormConsole_hiddenAll;
   o.process          = FFormConsole_process;
   o.loadEvents       = FFormConsole_loadEvents;
   o.processEvent     = FFormConsole_processEvent;
   o.free             = FFormConsole_free;
   o.dispose          = FFormConsole_dispose;
   return o;
}
function FFormConsole_construct(){
   var o = this;
   o.forms = new TMap();
   o.formIds = new TMap();
   o.formsLoaded = new TMap();
   o.lsnsLoaded = new TListeners();
   o.freeForms = new TList();
   o.events = new TMap();
}
function FFormConsole_createFromName(n, h, b, t){
   var o = this;
   var fs = o.freeForms;
   if(!fs.isEmpty()){
      var c = fs.count;
      for(var i=0; i<c; i++){
         if(fs.get(i).name == n){
            var f = fs.remove(i);
            f.setPanel(h);
            return f;
         }
      }
   }
   var fdc = RConsole.find(FFormDefineConsole);
   var fx = fdc.find(n, t);
   var fd = t + ':' + n;
   if(!o.formsLoaded.contains(fd)){
      var es = fdc.getEvents(n);
      if(es){
         o.loadEvents(es);
      }
      o.formsLoaded.set(fd, true);
   }
   var c = RClass.create('F' + fx.name);
   RControl.innerCreate(c, fx);
   c.psInitialize();
   if(!b){
      b = RWindow.builder();
   }
   c.psBuild(h, b);
   c.dsInitialize();
   c.setVisible(false);
   c.formId = fdc.nextFormId();
   o.formIds.set(c.formId, c);
   o.forms.set(n, c);
   return c;
}
function FFormConsole_get(id){
   return o.formIds.get(id);
}
function FFormConsole_find(n, h, b){
   var o = this;
   var f = o.forms.get(n);
   if(!f){
      f = o.createFromName(n, h, b);
   }
   return f;
}
function FFormConsole_hiddenAll(){
   var o = this;
   var fs = o.forms;
   var fc = fs.count;
   for(var n=0; n<fc; n++){
      fs.value(n).setVisible(false);
   }
}
function FFormConsole_onProcessLoaded(e){
   var o = this;
   var r = e.document.root();
   var g = e.argument;
   if(!e.messageChecked){
      var m = new TMessageArg();
      m.argument = g;
      m.form = g.form;
      m.config = r;
      m.invokeCaller = new TInvoke(o, o.onLoaded);
      m.invokeParam = e;
      m.event = e;
      if(!RConsole.find(FMessageConsole).checkResult(m)){
         return;
      }
   }
   var g = e.argument;
   var fn = r.find('Form');
   if(fn){
      var ds = RDataset.make(fn);
      g.resultDataset = ds;
      g.resultRow = ds.rows.get(0);
   }
   g.invoke();
}
function FFormConsole_process(g){
   var o = this;
   var doc = new TXmlDocument();
   var root = doc.root();
   root.set('action', 'process');
   if(g.checked){
      root.set('checked', g.checked);
   }
   root.push(g.toNode());
   var e = new TEvent(o, EXmlEvent.Send, o.onProcessLoaded);
   e.url = RService.url(RString.nvl(g.url, 'logic.webform'));
   e.action = EDataAction.Process;
   e.argument = g;
   e.document = doc;
   RConsole.find(FXmlConsole).process(e);
}
function FFormConsole_loadEvents(cfg){
   return;
   var o = this;
   if(!(cfg && cfg.nodes)){
      return;
   }
   var ns = cfg.nodes;
   var l = ns.count;
   for(var n = 0; n < l; n++){
      var x = ns.get(n);
      if(x.isName('Event')){
         var c = RClass.create(FEvent);
         c.loadConfig(x);
         if(RString.isEmpty(c.name) || RString.isEmpty(c.source) || RString.isEmpty(c.form)){
            RMessage.fatel(o, null, "Event property is invalid. (event={0})", x.xml());
         }
         var s = c.name + '@' + c.source + '@' + c.form;
         o.events.set(s, c);
      }
   }
}
function FFormConsole_processEvent(e){
   var o = this;
   var es = o.events;
   if(es.isEmpty()){
      return;
   }
   var se = e.source;
   if(RClass.isClass(se, FControl)){
      var p = se.topControl();
      if(p){
         var s = RString.nvl(e.name, e.handle) + '@' + se.name + '@' + p.name;
         var c = es.get(s);
         var eo = e.caller ? e.caller : se;
         if(c && c.code){
            if(c.event){
               c.event.call(eo, eo, e);
            }else{
               c.event = new Function('o', 'e', c.code);
                  c.event.call(eo, eo, e);
            }
         }
      }
   }
}
function FFormConsole_free(f){
   f.setVisible(false);
   this.freeForms.push(f);
}
function FFormConsole_dispose(){
   var o = this;
   RMemory.free(o.forms);
   RMemory.free(o.formIds);
   RMemory.free(o.formsLoaded);
   o.forms = null;
   o.formIds = null;
   o.formsLoaded = null;
}
function FFormDefineConsole(o){
   o = RClass.inherits(this, o, FConsole);
   o.scope          = EScope.Global;
   o.defines        = null;
   o.events         = null;
   o.lsnsLoaded     = null;
   o.formId         = 0;
   o.construct      = FFormDefineConsole_construct;
   o.createFromName = FFormDefineConsole_createFromName;
   o.loadNode       = FFormDefineConsole_loadNode;
   o.loadService    = FFormDefineConsole_loadService;
   o.nextFormId     = FFormDefineConsole_nextFormId;
   o.get            = FFormDefineConsole_get;
   o.find           = FFormDefineConsole_find;
   o.getLov         = FFormDefineConsole_getLov;
   o.findLov        = FFormDefineConsole_findLov;
   o.getEvents      = FFormDefineConsole_getEvents;
   return o;
}
function FFormDefineConsole_construct(){
   var o = this;
   o.defines = new TMap();
   o.defines.set(EForm.Form, new TMap());
   o.defines.set(EForm.Lov, new TMap());
   o.events = new TMap();
   o.lsnsLoaded = new TListeners();
}
function FFormDefineConsole_createFromName(name, type){
   var o = this;
   var doc = o.loadService(name, type);
   o.loadNode(doc);
   if(EForm.Lov == type){
      return o.getLov(name);
   }else{
      return o.get(name);
   }
}
function FFormDefineConsole_loadNode(x){
   var o = this;
   var nns = x.root();
   if(nns.hasNode()){
      var nodes = nns.nodes;
      var ct = nodes.count;
      for(var n = 0; n < ct; n++){
         var node = nodes.get(n);
         var fn = node.get('name');
         var tp = node.get('type');
         if(node.hasNode()){
            var nfds = node.nodes;
            for(var k = 0; k < nfds.count; k++){
               var dd = nfds.get(k);
               if(dd.isName('Define')){
                  if(dd.hasNode()){
                     var fds = dd.nodes;
                     for(var m = 0; m < fds.count; m++){
                        var nd = fds.get(m);
                        var mp = o.defines.get(tp);
                        mp.set(fn, nd);
                     }
                  }
               }else if(dd.isName('Events')){
                  o.events.set(fn, dd);
               }
            }
         }
      }
   }
}
function FFormDefineConsole_loadService(n, t){
   var o = this;
   if(!t){
      t = EForm.Form;
   }
   var doc = new TXmlDocument();
   var root = doc.root();
   root.set('action', 'loadDefine');
   var f = root.create('WebForm');
   f.set('name', n);
   f.set('type', t);
   var url = RService.url('logic.webform');
   var doc = RConsole.find(FXmlConsole).send(url, doc);
   var r = doc.root();
   if(!RConsole.find(FMessageConsole).checkResult(new TMessageArg(r))){
      return null;
   }
   return doc;
}
function FFormDefineConsole_nextFormId(){
   return ++this.formId;
}
function FFormDefineConsole_get(n){
   return this.defines.get(EForm.Form).get(n);
}
function FFormDefineConsole_find(n, t){
   var o = this;
   if(EForm.Lov == t){
      return o.findLov(n);
   }
   var fc = o.get(n);
   if(RClass.isMode(ERun.Debug)){
      RMemory.free(fc);
      fc = null;
      o.defines.get(EForm.Form).set(n, null);
   }
   if(!fc){
      fc = o.createFromName(n);
   }
   return fc;
}
function FFormDefineConsole_getLov(n){
   return this.defines.get(EForm.Lov).get(n);
}
function FFormDefineConsole_findLov(n){
   var o = this;
   var fc = o.getLov(n);
   if(!fc){
      fc = o.createFromName(n, EForm.Lov);
   }
   return fc;
}
function FFormDefineConsole_getEvents(n){
   return this.events.get(n);
}
function FHintConsole(o){
   o = RClass.inherits(this, o, FConsole);
   o.scope      = EScope.Page;
   o.hintWindow = null;
   o.construct  = FHintConsole_construct;
   o.find       = FHintConsole_find;
   return o;
}
function FHintConsole_construct(){
}
function FHintConsole_find(){
   var o = this;
   var w = o.hintWindow;
   if( !w ){
      w = o.hintWindow = RControl.create(FHintWindow);
   }
   return w;
}
function FHistoryConsole(o){
   o = RClass.inherits(this, o, FConsole);
   o.scope          = EScope.Page;
   o.historyIndex   = -1;
   o.historys       = null;
   o.construct      = FHistoryConsole_construct;
   o.next           = FHistoryConsole_next;
   o.popup          = FHistoryConsole_popup;
   o.get            = FHistoryConsole_get;
   o.find           = FHistoryConsole_find;
   return o;
}
function FHistoryConsole_construct(){
   var o = this;
   o.historys = new TList();
}
function FHistoryConsole_next(){
   var o = this;
   var n = o.historyIndex + 1;
   var h = o.historys.get(n);
   if(!h){
      h = new THistory();
      o.historys.push(h);
   }
   o.historyIndex++;
   return h;
}
function FHistoryConsole_popup(l){
   var n = o.historyIndex - l;
   if(n < 0){
      n = 0;
   }
   o.historyIndex = n;
   return o.historys.get(n);
}
function FHistoryConsole_get(n, h, b){
   var o = this;
   var f = o.forms.get(n);
   if(!f){
      f = o.createFromName(n, h, b);
   }
   return f;
}
function FHistoryConsole_find(){
}
function FIdleConsole(o){
   o = RClass.inherits(this, o, FConsole);
   o.scope            = EScope.Page;
   o.register         = FIdleConsole_register;
   return o;
}
function FIdleConsole_register(c, cFun){
   var o = this;
   o.active = new TActive(c, cFun);
   o.active.interval = 100;
   RConsole.find(FActiveConsole).push(o.active);
}
function FIdleConsole_construct(){
   var o = this;
}
function FKeyConsole(o){
   o = RClass.inherits(this, o, FConsole);
   o.scope         = EScope.Page;
   o.allow         = true;
   o.registerAble  = true;
   o.listeners     = new Object();
   o.disableKeys   = new Object();
   o.onKeyDown     = FKeyConsole_onKeyDown;
   o.construct     = FKeyConsole_construct;
   o.register      = FKeyConsole_register;
   o.allowRegister = FKeyConsole_allowRegister;
   o.skipRegister  = FKeyConsole_skipRegister;
   o.allowAll      = FKeyConsole_allowAll;
   o.skipAll       = FKeyConsole_skipAll;
   return o;
}
RConsole.register(new TConsole(EScope.Page, FKeyConsole, true));
function FKeyConsole_onKeyDown(s, e){
   var o = this;
   var k = REnum.tryDecode(EKey, e.keyCode);
   if(k && o.allow){
      var ls = o.listeners[k];
      if(ls){
         ls.process(o, e);
         e.keyCode = null;
         e.returnValue = false;
      }
   }
   if(k && o.disableKeys[k]){
      e.keyCode = null;
      e.returnValue = false;
   }
}
function FKeyConsole_construct(){
   var o = this;
   o.base.FConsole.construct.call(o);
   o.disableKeys[EKey.F1] = true;
   o.disableKeys[EKey.F5] = true;
   RWindow.lsnsKeyDown.register(o, o.onKeyDown);
}
function FKeyConsole_register(k, l){
   var o = this;
   if(o.registerAble){
      if(RInt.isInt(k)){
         k = REnum.decode(EKey, k);
      }
      var ks = o.listeners;
      var ls = ks[k];
      if(!ls){
         ls = ks[k] = new TListeners();
      }
      ls.push(l);
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
   o.contains  = FListenerConsole_contains;
   o.register  = FListenerConsole_register;
   o.process   = FListenerConsole_process;
   return o;
}
function FListenerConsole_contains(clazz, action){
   var o = this;
   if(!o.listeners){
      return false;
   }
   var name = RClass.name(clazz) + '@' + action;
   var lsns = o.listeners[name];
   if(!lsns){
      return false;
   }
   return lsns.isEmpty();
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
   RWindow.lsnsKeyDown.register(o, o.onKeyDown);
}
function FLoggerConsole_connect(){
   var o = this;
   if(!o.iLogger){
   }
}
function FLoggerConsole_disconnect(){
   this.iLogger = null;
}
function FLoggerConsole_output(level, obj, method, ms, msg, stack){
   var o = this;
   if(o.iLogger){
      var m = RClass.dump(obj);
      if(ms){
         m += ' (' + ms + 'ms)';
      }
      var s = level + ' [' + RString.rpad(m, 36) + '] ';
      if(stack){
         s += RString.rpad(msg, 120) + ' [' + stack + ']';
      }else{
         s += msg;
      }
      o.iLogger.Output(s);
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
   o.scope        = EScope.Global;
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
function FMessageConsole_popupMessage(g){
   var o = this;
   var w = o.messageWindow;
   if(!w){
      w = o.messageWindow = RControl.create('FMessageWindow');
   }
   w.loadMessages(g);
   w.show();
}
function FMessageConsole_closeMessage(){
   RWindow.setEnable(true);
}
function FMessageConsole_checkResult(g){
   var o = this;
   var ms = g.messages = o.parse(g.config);
   if(ms){
      var m = ms.message(EMessage.Fatal);
      if(m && m.attrType == "session.timeout"){
         var ss = RString.splitTwo(m.redirect, '@');
         var s = RContext.context(ss[1] + '?do='+ss[0]);
         fmMain.action = s;
         fmMain.target = '_self';
         fmMain.submit();
      }else{
         o.popupMessage(g);
      }
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
   monitor.name = 'T:' + RString.lpad(monitor.id, 4, '0');
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
   o.active.interval = 0;
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
      var tv = RGlobal.get('catalog.tree');
      if(tv){
         tv.reload();
      }
   }else if(EResultCommand.TreeNodeRefresh == name){
      var tv = RGlobal.get('catalog.tree');
      if(tv){
         var uuid = command.get('uuid');
         if(uuid){
            var fn = tv.findByUuid(uuid);
            if(fn){
               tv.reloadNode(fn);
            }else{
               return alert("Can't find tree node. (uuid="+uuid+")");
            }
         }else{
            tv.reloadNode();
         }
      }
   }else if(EResultCommand.TreeParentRefresh == name){
      var tv = RGlobal.get('catalog.tree');
      if(tv){
         var fn = tv.focusNode;
         if(fn){
            tv.reloadNode(fn.parentNode);
         }
      }
   }else if(EResultCommand.PageRedirect == name){
      var action = command.get('action');
      var page = top.RContext.context(command.get('page'));
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
      if(!RConsole.find(FMessageConsole).checkResult(new TMessageArg(config))){
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
function FUploadConsole(o){
   o = RClass.inherits(this, o, FConsole);
   o.scope               = EScope.Global;
   o._build              = false;
   o.uploadWindow        = null;
   o.uploadFileName      = null;
   o.uploads             = null;
   o.lsnsFileSelected    = new TListeners();
   o.lsnsUploaded        = new TListeners();
   o.onFileSelected      = RClass.register(o, new HChange('onFileSelected'), FUploadConsole_onFileSelected);
   o.onFileUploaded      = FUploadConsole_onFileUploaded;
   o.onDeleteLoaded      = FUploadConsole_onDeleteLoaded;
   o.onPreviewSaveLoaded = FUploadConsole_onPreviewSaveLoaded;
   o.onDataFrameLoad     = RClass.register(o, new HReadyStateChange('onDataFrameLoad'), FUploadConsole_onDataFrameLoad);
   o.onUploadFrameLoad   = RClass.register(o, new HReadyStateChange('onUploadFrameLoad'), FUploadConsole_onUploadFrameLoad);
   o.construct           = FUploadConsole_construct;
   o.showDialog          = FUploadConsole_showDialog;
   o.upload              = FUploadConsole_upload;
   o.getWindow           = FUploadConsole_getWindow;
   o.findWindow          = FUploadConsole_findWindow;
   o.previewSave         = FUploadConsole_previewSave;
   o.deleteFile          = FUploadConsole_deleteFile;
   return o;
}
function FUploadConsole_upload(a, g){
   var o = this;
   if(a){
      o.action = a;
   }
   if(g){
      o.hType.value = RString.nvl(g.typeCode);
      o.hCode.value = RString.nvl(g.recordCode);
      o.hGuid.value = RString.nvl(g.recordGuid);
      o.hName.value = RString.nvl(g.recordName);
      o.hPath.value = RString.nvl(g.path);
   }
   o.hForm.action = o.uploadPage + '?do=' + a;
   o.hForm.submit();
}
function FUploadConsole_onFileSelected(e){
   var o = RHtml.findLink(this.parentElement, 'control');
   var fn = o.hUpload.value;
   if(fn && fn.length > 0){
      o.uploadFileName = fn;
      o.hWorkerId.value = RDate.format();
      o.uploadWindow.selectFile(fn);
      o.lsnsFileSelected.process(o);
   }
}
function FUploadConsole_onFileUploaded(s, g){
   this.uploadWindow.uploadedFile(g)
}
function FUploadConsole_onDataFrameLoad(e, a, b){
   var o = this;
   var hf = o.hDataFrame;
   if('complete' == hf.readyState){
      var hw = hf.contentWindow;
      var hd = hw.document;
      var hb = hd.body;
      var s = new TString();
      s.append("<FORM method='post' target='" + o.uploadFrameName + "' enctype='multipart/form-data'>");
      s.append("<INPUT name='type' type='text'>");
      s.append("<INPUT name='code' type='text'>");
      s.append("<INPUT name='guid' type='text'>");
      s.append("<INPUT name='name' type='text'>");
      s.append("<INPUT name='path' type='text'>");
      s.append("<DIV></DIV>");
      s.append("<INPUT name='worker_id' type='text'>");
      s.append("</FORM>");
      hb.innerHTML = s.toString();
      o.hForm = hd.forms[0];
      o.hType = o.hForm.children[0];
      o.hCode = o.hForm.children[1];
      o.hGuid = o.hForm.children[2];
      o.hName = o.hForm.children[3];
      o.hPath = o.hForm.children[4];
      var hup = o.hUploadPanel  = o.hForm.children[5];
      RHtml.link(hup, 'control', o);
      o.hWorkerId  = o.hForm.children[6];
   }
}
function FUploadConsole_onUploadFrameLoad(e, a, b){
   var o = this;
   var hf = o.hUploadFrame;
   if('complete' == hf.readyState){
   }
}
function FUploadConsole_construct(){
   var o = this;
   o.base.FConsole.construct.call(o);
   o.uploads = new TMap();
   o.uploadPage = top.RContext.context('/apl/logic/transfer/Upload.wa')
   var hd = o.hDataFramePanel = RBuilder.append(null, 'DIV');
   var ufn = o.uploadFrameName = 'frm' + hd.uniqueNumber;
   hd.style.display = 'none';
   hd.innerHTML = "<IFRAME></IFRAME>";
   var hf = o.hDataFrame = hd.children[0];
   RControl.attachEvent(o, 'onDataFrameLoad', hf);
   var hd = o.hUploadFramePanel = RBuilder.append(null, 'DIV');
   hd.style.display = 'none';
   hd.innerHTML = "<IFRAME name='" + ufn + "'></IFRAME>";
   o.onUploadFrameLoad = hd.children[0];
   RConsole.find(FListenerConsole).register(FUploadConsole, 'onFileUploaded', o, o.onFileUploaded);
}
function FUploadConsole_showDialog(){
   var o = this;
   var hup = o.hUploadPanel;
   if(o.hUpload){
      o.hUpload.onchange = null;
      hup.removeChild(o.hUpload);
   }
   hup.innerHTML = "<INPUT name='upload' type='file'>";
   var hu = o.hUpload  = hup.children[0];
   hu.onchange = o.onFileSelected;
   hu.click();
}
function FUploadConsole_getWindow(){
   var o = this;
   var w = o.uploadWindow;
   if(!w){
      w = o.uploadWindow = RControl.create('FUploadWindow');
   }
   return w;
}
function FUploadConsole_findWindow(){
   var o = this;
   var w = o.getWindow();
   w.lsnsFileSelected.clear();
   w.lsnsUploaded.clear();
   return w;
}
function FUploadConsole_onDeleteLoaded(e){
   var o = this;
   var g = e.argument;
   if(g.callback){
      g.callback.invoke(o, g, e);
   }
}
function FUploadConsole_onPreviewSaveLoaded(e){
   var o = this;
   var g = e.argument;
   var fn = e.document.root().find('File');
   if(fn){
      g.attributes = fn.attrs;
   }
   if(g.callback){
      g.callback.invoke(o, g, e);
   }
}
function FUploadConsole_previewSave(g){
   var o = this;
   var doc = new TXmlDocument();
   var root = doc.root();
   root.set('action', 'previewSave');
   var fileNode = root.create('File');
   fileNode.set('type_code', g.typeCode);
   fileNode.set('record_code', g.recordCode);
   fileNode.set('record_guid', g.recordGuid);
   fileNode.set('record_name', g.recordName);
   fileNode.set('guid', g.guid);
   fileNode.set('path', g.path);
   fileNode.set('adjust_width', g.adjustWidth);
   fileNode.set('adjust_height', g.adjustHeight);
   var e = new TEvent(o, EXmlEvent.Send, o.onPreviewSaveLoaded);
   e.url = RService.url('public.upload');
   e.document = doc;
   e.argument = g;
   RConsole.find(FXmlConsole).process(e);
}
function FUploadConsole_deleteFile(g){
   var o = this;
   var doc = new TXmlDocument();
   var root = doc.root();
   root.set('action', 'deleteFile');
   var fileNode = root.create('File');
   fileNode.set('guid', g.guid);
   var e = new TEvent(o, EXmlEvent.Send, o.onDeleteLoaded);
   e.url = RService.url('public.upload');
   e.document = doc;
   e.argument = g;
   RConsole.find(FXmlConsole).process(e);
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
function FWindowConsole(o, create){
   if(!create){return this;}
   o = IClass.inherits(this, o, FConsole);
   o.activeWindow = null;
   o.windows      = null;
   o.defines      = null;
   o.construct    = FWindowConsole_construct;
   o.create       = FWindowConsole_create;
   o.loadDefine   = FWindowConsole_loadDefine;
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
   this.onEventMousedown = FWindowConsole_onEventMousedown;
   this.onSaveDefineAfter = FWindowConsole_onSaveDefineAfter;
   this.onEventRelease = FWindowConsole_onEventRelease;
   this.initialize = FWindowConsole_initialize;
   this.hasWindow = FWindowConsole_hasWindow;
   this.focus = FWindowConsole_focus;
   this.saveDefine = FWindowConsole_saveDefine;
   this.findWindow = FWindowConsole_findWindow;
   this.releaseWindowName = FWindowConsole_releaseWindowName;
   this.releaseWindow = FWindowConsole_releaseWindow;
   this.doFrameAction = FWindowConsole_doFrameAction;
   this.setMaxWindow = FWindowConsole_setMaxWindow;
   this.restore = FWindowConsole_restore;
   this.doProperties = FWindowConsole_doProperties;
   this.clear = FWindowConsole_clear;
   this.hideAll = FWindowConsole_hideAll;
   this.dump = FWindowConsole_dump;
   return this;
}
function FWindowConsole_construct(){
   this.windows = new TList();
   this.defines = new TNameList();
}
function FWindowConsole_create(name, hWin){
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
function FWindowConsole_loadDefine(name){
   if(name == null){
      return null;
   }
   var config = this.defines.find(name);
   if(config == null){
      var doc = new TXmlDocument();
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
function FWindowConsole_dump(){
   var sDump = this.className;
   sDump += '\n\nDefine:\n' + this.m_oDefinePool.dump();
   sDump += '\n\nWindow:\n' + this.windowList.dump();
   return sDump;
}
function FWindowConsole_clear(){
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
function FWindowConsole_hideAll(oExpWin, bDisplay){
   var nSize = this.windowList.size();
   for(var n=nSize-1; n>=0; n--){
      var oWin = this.windowList.value(n);
      if(oWin != oExpWin){
         oWin.hide(bDisplay);
      }
   }
}
function FWindowConsole_setMaxWindow(oWin){
   this.maxFlag = true;
   this.hideAll(oWin);
}
function FWindowConsole_restore(){
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
function FWindowConsole_initialize(oCtWin){
   this.clientWindow = oCtWin;
}
function FWindowConsole_hasWindow(){
   return !this.windowList.isEmpty();
}
function FWindowConsole_focus(oWinCtl){
   this.focusWinCtl = oWinCtl;
   if(this.maxFlag){
      oWinCtl.show();
      this.hideAll(oWinCtl, true)
      oWinCtl.max();
   }
}
function FWindowConsole_saveDefine(oWinNode, oClientWindow){
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
function FWindowConsole_onEventMousedown(oCWin){
}
function FWindowConsole_onSaveDefineAfter(){
   ILogger.info(this, 'saveDefine', 'Save Ok.');
   if(this.clientWindow){this.clientWindow.document.body.disabled = false;}
}
function FWindowConsole_findWindow(sWinName){
   return this.windowList.nameValue(sWinName);
}
function FWindowConsole_releaseWindowName(sWinName){
   var oWin = this.windowList.removeName(sWinName);
   IEngine.process(this, this.EVENT_CLOSE, oWin);
}
function FWindowConsole_releaseWindow(oWin){
   this.windowList.removeValue(oWin);
   IEngine.process(this, this.EVENT_CLOSE, oWin);
}
function FWindowConsole_doFrameAction(sAction){
   if(!this.activeForm){
      return ILogger.fatal(this, 'doFrameAction', 'Not active form!');
   }
   this.activeForm.doAction(sAction);
}
function FWindowConsole_doProperties(){
   TrackManager.push(this, 'Do properties.');
   if(!WindowManager.focusWinCtl){return;}
   var arParams = new Array();
   arParams['WindowManager'] = WindowManager;
   window.showModalDialog(SystemManager.actionURL('window'), arParams, 'dialogWidth:500px;dialogHeight:360px;resizable:no;scroll:no;edge:sunken');
}
function FWindowConsole_onEventRelease(oCWin){
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
   o.connections = null;
   o.onLoad      = FXmlConsole_onLoad;
   o.construct   = FXmlConsole_construct;
   o.alloc       = FXmlConsole_alloc;
   o.process     = FXmlConsole_process;
   o.send        = FXmlConsole_send;
   return o;
}
function FXmlConsole_construct(){
   var o = this;
   o.connections = new TList();
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
   var o = this;
   var a = null;
   var cs = o.connections;
   var l = cs.count;
   for(var n=0; n<l; n++){
      var c = cs.get(n);
      if(c && c.isFree){
         a = c;
         break;
      }
   }
   if(!a){
      a = new TXmlConnect();
      cs.push(a);
      a.onLoad = o.onLoad;
   }
   a.isFree = false;
   return a;
}
function FXmlConsole_process(e){
   var o = this;
   var c = o.alloc();
   c.event = e;
   switch(e.code){
      case EXmlEvent.Send:
         c.send(e.url, e.document);
         break;
      case EXmlEvent.Receive:
         c.receive(e.url, e.document);
         break;
      case EXmlEvent.SyncSend:
         return c.syncSend(e.url, e.document);
      case EXmlEvent.SyncReceive:
         return c.syncReceive(e.url, e.document);
   }
}
function FXmlConsole_send(u, d){
   var o = this;
   var c = o.alloc();
   var r = c.syncSend(u, d);
   c.isFree = true;
   return r;
}
function MRelease(o){
   o = RClass.inherits(this, o);
   o.release = RMethod.virtual(o, 'release')
   return o;
   }
var RCharset = new function(o){
   o = moNvl(o, this);
   o.ansiMinCode = RCharset_ansiMinCode;
   o.ansiMaxCode = RCharset_ansiMaxCode;
   o.dump        = RCharset_dump;
   RMemory.register('RCharset', o);
   return this;
}
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
var RCookie = new function(o){
   o = moNvl(o, this);
   o.ConnectId  = 'MOCK';
   o.storeDays  = 30;
   o.cookies    = new TAttributes();
   o.get        = RCookie_get;
   o.set        = RCookie_set;
   o.connect    = RCookie_connect;
   o.disconnect = RCookie_disconnect;
   o.release    = RCookie_release;
   RMemory.register('RCookie', o);
   return o;
}
function RCookie_get(n){
   return RString.nvl(this.cookies.get(n));
}
function RCookie_set(n, v){
   this.cookies.set(n, v);
}
function RCookie_connect(){
   var o = this;
   var cs = o.cookies;
   var ac = document.cookie.split('; ');
   for(var n=0; n<ac.length; n++){
      var s = ac[n];
      var i = s.indexOf('=');
      if(-1 != i){
         var n = s.substring(0, i);
         if(o.ConnectId == n){
            var ts = new TAttributes()
            ts.unpack(s.substring(i + 1));
            for(var n=0; n<ts.count; n++){
               var cv = ts.value(n);
               if(!RString.isEmpty(cv)){
                  cs.set(ts.name(n), unescape(ts.value(n)));
               }
            }
            break;
         }
      }
   }
}
function RCookie_disconnect(){
   var o = this;
   var d = new Date();
   d.setTime(d.getTime() + (RDate.DaySeconds * o.storeDays));
   var cs = o.cookies;
   var ts = new TAttributes();
   var c = cs.count;
   for(var n=0; n<c; n++){
      var v = cs.value(n);
      if(!RString.isEmpty(v)){
         ts.set(cs.name(n), escape(v));
      }
   }
   document.cookie = o.ConnectId + "=" + ts.pack() + ";expires=" + d.toGMTString();
}
function RCookie_release(){
   var o = this;
   var d = new Date();
   d.setTime(d.getTime() -1 );
   document.cookie = o.ConnectId + "=null;expires=" + d.toGMTString();
}
var RCss = new function(o){
   if(!o){o=this};
   o.rules   = null;
   o.connect = RCss_connect;
   o.has     = RCss_has;
   o.nvl     = RCss_nvl;
   o.style   = RCss_style;
   RMemory.register('RCss', o);
   return o;
}
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
var RGlobalPage = new function(){
   var o = this;
   o.uri          = null;
   o.uriTag       = null;
   o.resourcePath = null;
   RMemory.register('RGlobalPage', o);
   return o;
}
var RKey = new function(){
   var o = this;
   o.isCtlKey      = RKey_isCtlKey;
   o.isNumKey      = RKey_isNumKey;
   o.isCtlKeyPress = RKey_isCtlKeyPress;
   o.eventClear    = RKey_eventClear;
   o.fixCase       = RKey_fixCase;
   o.fixPattern    = RKey_fixPattern;
   o.fixChars      = RKey_fixChars;
   RMemory.register('RKey', o);
   return o;
}
function RKey_isCtlKey(c){
   var ks = EKey.ControlKeys;
   for(var n=0; n<ks.length; n++){
      if(ks[n] == c){
         return true;
      }
   }
   return false;
}
function RKey_isNumKey(c){
   var ks = EKey.ControlKeys;
   if(c >= 96 && c <= 105){
      return true;
   }
   return false;
}
function RKey_isCtlKeyPress(c){
   for(var n in EKey.ControlKeys){
      if(EKey.ControlKeys[n] == c){
         return true;
      }
   }
   return false;
}
function RKey_eventClear(e){
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
         if(!RString.isPattern(String.fromCharCode(k), p)){
            e.keyCode = 0;
            return false;
         }
      }
   }
   return true;
}
function RKey_fixChars(e, p){
   if(p){
      var k = e.keyCode;
      if(this.isNumKey(k)){
    	  k = e.keyCode = e.keyCode - 48;
      }
      if(!this.isCtlKeyPress(k)){
         if(!RString.inChars(String.fromCharCode(k), p)){
            e.keyCode = 0;
            e.returnValue = false;
            return false;
         }
      }
   }
   return true;
}
var RLayer = new function(o){
   if(!o){o=this;}
   o.layers = new Array();
   o.next   = RLayer_next;
   o.free   = RLayer_free;
   RMemory.register('RLayer', o);
   return o;
}
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
var RNaming = new function(){
   var o = this;
   o.pool = new Array();
   o.get  = RNaming_get;
   o.set  = RNaming_set;
   RMemory.register('RNaming', o);
   return o;
}
function RNaming_get(c, n){
   if(c){
      n = RString.nvl(n) + '@' + RClass.name(c);
      return this.pool[n.toLowerCase()];
   }
   return null;
}
function RNaming_set(o, n){
   if(o){
      n = RString.nvl(n) + '@' + RClass.name(o);
      this.pool[n.toLowerCase()] = o;
   }
}
var RPage = new function(){
   var o = this;
   o.url      = RPage_url;
   o.parse    = RPage_parse;
   o.redirect = RPage_redirect;
   RMemory.register('RPage', o);
   return o;
}
function RPage_url(name){
   if(RString.startsWith(name, 'http://')){
      return name;
   }
   return top.RContext.context(name);
}
function RPage_parse(page){
   if(!page){
      return null;
   }
   var ps = '';
   var n = page.indexOf('?');
   if(-1 != n){
      ps = page.substring(n);
      page = page.substring(0, n);
   }
   var p = null;
   var items = page.split('@');
   if(1 == items.length && items[0]){
      p = new TPage();
      p.action = null;
      p.page = items[0] + ps;
      p.url = this.url(p.page);
   }else if(2 == items.length && items[0] && items[1]){
      p = new TPage();
      p.action = items[0];
      p.page = items[1] + ps;
      p.url = this.url(p.page);
   }
   return p;
}
function RPage_redirect(f, t, u, m){
   RConsole.find(FEnvConsole).buildValue();
   u = RString.nvl(u);
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
var RService = new function(){
   var o = this;
   o.url   = RService_url;
   o.parse = RService_parse;
   RMemory.register('RService', o);
   return o;
}
function RService_url(name){
   if(RString.startsWith(name, 'http://')){
      return name;
   }
   if(RString.startsWith(name, '#')){
      return name.substr(1);
   }
   if(!RString.startsWith(name, '/')){
      name = '/' + name;
   }
   return top.RContext.context(name + '.ws');
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
var RTag = new function(){
   var o = this;
   o.findParent = RTag_findParent;
   o.findChild  = RTag_findChild;
   o.cbSync     = RTag_cbSync;
   o.goPage     = RTag_goPage;
   RMemory.register('RTag', o);
   return o;
}
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
      u = RString.nvl(u);
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
function TActionServiceArg(fn, da){
   var o = this;
   o.formName   = fn;
   o.dataAction = da;
   o.service    = null;
   o.action     = null;
   o.rows       = new TList();
   o.actionType = 'process';
   o.values     = null;
   o.resultRows = null;
   o.callback   = null;
   o.toNode     = TActionServiceArg_toNode;
   o.invoke     = TActionServiceArg_invoke;
   return o;
}
function TActionServiceArg_toNode(){
   var o = this;
   var nf = new TNode('Form');
   nf.set('name', o.dsName);
   nf.set('data_action', o.actName);
   var rs = o.rows;
   if(o.values && o.values.count > 0){
      var nd = new TNode('Row', o.values);
      nf.push(nd);
   }
   return nf;
}
function TActionServiceArg_invoke(){
   var o = this;
   if(o.callback){
      o.callback.invoke(o);
   }
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
      if(o.run){
         if(o.owner){
            o.run.call(o.owner, o);
         }else{
            o.run(o);
         }
      }
      o.count = o.interval;
   }
}
function TChartArg(n, c, cn){
   var o = this;
   o.callback        = null;
   o.name            = n;
   o.chart           = c;
   o.control         = cn;
   o.attributes      = new TAttributes();
   o.set             = TChartArg_set;
   o.push            = TChartArg_push;
   o.toNode          = TChartArg_toNode;
   o.invoke          = TChartArg_invoke;
   o.invokeSuccess   = TChartArg_invokeSuccess;
   return o;
}
function TChartArg_set(n, i, dn){
   var o = this;
   o.name = n;
   o.id   = i;
   o.dsName = dn;
}
function TChartArg_push(r){
   var o = this;
   var nd = new TNode('Row');
   r.saveNode(nd);
   var rd = o.dataset.createRow();
   rd.loadNode(nd);
}
function TChartArg_toNode(){
   var o = this;
   var root = new TNode("Form");
   root.set("name", o.name);
   root.set("id", o.id);
   root.set("path", o.path);
   var ds = new TNode("Dataset");
   ds.set("name", o.dsName);
   root.push(ds);
   var rs = o.dataset.rows;
   var rc = rs.count;
   for(var n=0; n<rc; n++){
      var r = rs.get(n);
      var rn = new TNode('Row');
      r.saveNode(rn);
      ds.push(rn);
   }
   return root;
}
function TChartArg_invoke(){
   var o = this;
   if(o.callback){
      o.callback.invoke(o);
   }
}
function TChartArg_invokeSuccess(){
   var o = this;
   if(o.callbackSuccess){
      return o.callbackSuccess.invoke(o);
   }
   return false;
}
function TCodeListServiceArg(n){
   var o = this;
   o.callback     = null;
   o.name         = n;
   o.resultConfig = null;
   o.values       = new TList();
   o.toNode       = TCodeListServiceArg_toNode;
   o.invoke       = TCodeListServiceArg_invoke;
   return o;
}
function TCodeListServiceArg_toNode(){
   var o = this;
   if(o.values && o.values.count > 0){
      var nd = new TNode('Values', o.values.get(0).toAttributes());
      return nd;
   }
}
function TCodeListServiceArg_invoke(){
   var o = this;
   if(o.callback){
      o.callback.invoke(o);
   }
}
function TDatasetCompleteArg(fn, c, v){
   var o = this;
   o.callback     = null;
   o.formName     = fn;
   o.control      = c;
   o.value        = v;
   o.resultConfig = null;
   o.set          = TDatasetCompleteArg_set;
   o.toNode       = TDatasetCompleteArg_toNode;
   o.invoke       = TDatasetCompleteArg_invoke;
   return o;
}
function TDatasetCompleteArg_set(n, c, v){
   var o = this;
   o.name     = n;
   o.control  = c;
   o.value    = v;
}
function TDatasetCompleteArg_toNode(){
   var o = this;
   var n = new TNode('Control');
   n.set('form', o.formName);
   n.set('control', o.control);
   n.set('value', o.value);
   return n;
}
function TDatasetCompleteArg_invoke(){
   var o = this;
   if(o.callback){
      o.callback.invoke(o);
   }
}
function TDatasetFetchArg(n, id, ps, p, r, f, pt, re){
   var o = this;
   o.formName = n;
   o.formId   = id;
   o.pageSize = ps;
   o.page     = p;
   o.reset    = r;
   o.force    = f;
   o.path     = pt;
   o.research = re;
   o.searchs  = new TSearchItems();
   o.orders   = new TOrderItems();
   o.values   = new TAttributes();
   o.callback = null;
   o.resultDatasets = new TMap();
   o.push     = TDatasetFetchArg_push;
   o.toNode   = TDatasetFetchArg_toNode;
   o.invoke   = TDatasetFetchArg_invoke
   return o;
}
function TDatasetFetchArg_push(v){
   var o = this;
   if(RClass.isClass(v, TSearchItem)){
      o.searchs.push(v);
   }else if(RClass.isClass(v, TOrderItem)){
      o.orders.push(v);
   }
}
function TDatasetFetchArg_toNode(){
   var o = this;
   var n = new TNode('Form');
   n.set('name', o.formName);
   n.set('id', o.formId);
   n.set('page', o.page);
   n.set('page_size', o.pageSize);
   n.set('path', o.path);
   if(o.research){
      n.set('research', o.research);
   }
   var ss = o.searchs;
   if(ss.count > 0){
      var ns = n.create('Search');
      var sl = ss.count;
      for(var m=0; m<sl; m++){
         ns.push(ss.get(m).toNode());
      }
   }
   var so = o.orders;
   if(so.count > 0){
      var no = n.create('Order');
      var ol = so.count;
      for(var m=0; m<ol; m++){
         no.push(so.get(m).toNode());
      }
   }
   if(o.values && o.values.count > 0){
      var nd = new TNode('Values', o.values);
      n.push(nd);
   }
   return n;
}
function TDatasetFetchArg_invoke(){
   var o = this;
   if(o.callback){
      o.callback.invoke(o);
   }
}
function TDatasetLovArg(n, cp){
   var o = this;
   o.callback    = null;
   o.name        = n;
   o.controlPath = cp;
   o.dataset     = new TDataset();
   o.lovNode     = null;
   o.set         = TDatasetLovArg_set;
   o.push        = TDatasetLovArg_push;
   o.toNode      = TDatasetLovArg_toNode;
   o.invoke      = TDatasetLovArg_invoke;
   return o;
}
function TDatasetLovArg_set(n, cp){
   var o = this;
   o.name = n;
   o.controPath = cp;
}
function TDatasetLovArg_push(r){
   var o = this;
   var nd = new TNode('Row');
   r.saveNode(nd);
   var rd = o.dataset.createRow();
   rd.loadNode(nd);
}
function TDatasetLovArg_toNode(){
   var o = this;
   var root = new TNode("Form");
   root.set("name", o.name);
   root.set("control", o.controlPath);
   var dsn = new TNode("Dataset");
   dsn.set("name", o.dsName);
   root.push(dsn);
   var ds = o.dataset;
   var len = ds.total;
   for(var n = 0;  n<len; n++){
      var r = ds.rows.get(n);
      var nd = new TNode('Row');
      r.loadNode(nd);
      dsn.push(nd);
   }
   return root;
}
function TDatasetLovArg_invoke(){
   var o = this;
   if(o.callback){
      o.callback.invoke(o);
   }
}
function TDatasetPrepareArg(n, i){
   var o = this;
   o.callback       = null;
   o.name           = n;
   o.id             = i;
   o.dataset        = new TDataset();
   o.resultDataset  = new TDataset();
   o.resultDatasets = new TMap();
   o.values         = new TAttributes();
   o.set            = TDatasetPrepareArg_set;
   o.toNode         = TDatasetPrepareArg_toNode;
   o.push           = TDatasetPrepareArg_push;
   o.invoke         = TDatasetPrepareArg_invoke;
   return o;
}
function TDatasetPrepareArg_set(n, i, dn){
   var o = this;
   o.name = n;
   o.id   = i;
   o.dsName    = dn;
}
function TDatasetPrepareArg_push(r){
   var o = this;
   var nd = new TNode('Row');
   r.saveNode(nd);
   var rd = o.dataset.createRow();
   rd.loadNode(nd);
}
function TDatasetPrepareArg_toNode(){
   var o = this;
   var root = new TNode("Form");
   root.set("name", o.name);
   root.set("id", o.id);
   var dsn = new TNode("Dataset");
   dsn.set("name", o.dsName);
   var ds = o.dataset;
   var len = ds.total;
   for(var n = 0; n < len; n++){
      var r = ds.rows.get(n);
      var nd = new TNode('Row');
      r.saveNode(nd);
      dsn.push(nd);
   }
   if(o.values && o.values.count > 0){
      var td = new TNode('Values', o.values);
      dsn.push(td);
   }
   root.push(dsn);
   return root;
}
function TDatasetPrepareArg_invoke(){
   var o = this;
   if(o.callback){
      o.callback.invoke(o);
   }
}
function TDatasetScalarArg(c, u, p){
   var o = this;
   o.callback     = null;
   o.control      = c;
   o.ouid         = u;
   o.parameters   = p;
   o.resultConfig = null;
   o.result       = null;
   o.set          = TDatasetScalarArg_set;
   o.toNode       = TDatasetScalarArg_toNode;
   o.invoke       = TDatasetScalarArg_invoke;
   return o;
}
function TDatasetScalarArg_set(n, c, v){
   var o = this;
   o.name     = n;
   o.control  = c;
   o.value    = v;
}
function TDatasetScalarArg_toNode(){
   var o = this;
   var n = new TNode('Control');
   n.set('form', o.control.topControl().name);
   n.set('control', o.control.fullPath());
   n.set('ouid', o.ouid);
   if(o.parameters){
      n.set('parameters', o.parameters.pack());
   }
   return n;
}
function TDatasetScalarArg_invoke(){
   var o = this;
   if(o.callback){
      o.callback.invoke(o);
   }
}
function TDatasetServiceArg(fn, da){
   var o = this;
   o.formName    = fn;
   o.controlName = null;
   o.dataAction  = da;
   o.service     = null;
   o.action      = null;
   o.attributes  = new TAttributes();
   o.codes       = null;
   o.rows        = null;
   o.actionType  = 'process';
   o.values      = null;
   o.resultRows  = null;
   o.callback    = null;
   o.hasData     = TDatasetServiceArg_hasData;
   o.toNode      = TDatasetServiceArg_toNode;
   o.invoke      = TDatasetServiceArg_invoke;
   o.push        = TDatasetServiceArg_push;
   return o;
}
function TDatasetServiceArg_hasData(){
   var o = this;
   if(o.rows){
      return !o.rows.isEmpty();
   }
   return false;
}
function TDatasetServiceArg_toNode(){
   var o = this;
   var nf = new TNode('Form');
   nf.set('name', o.formName);
   nf.set('control_name', o.controlName);
   nf.set('data_action', o.dataAction);
   nf.push(new TNode('Dataset', o.attributes));
   nf.push(new TNode('Code', o.codes));
   var rs = o.rows;
   if(rs){
      var c = rs.count;
      for(var n=0; n<c; n++){
         nf.push(new TNode('Row', rs.get(n)));
      }
   }
   if(o.values && o.values.count > 0){
      var nd = new TNode('Values', o.values);
      nf.push(nd);
   }
   return nf;
}
function TDatasetServiceArg_invoke(){
   var o = this;
   if(o.callback){
      o.callback.invoke(o);
   }
}
function TDatasetServiceArg_push(r){
   var o = this;
   if(r){
      var rs = o.rows;
      if(!rs){
         rs = o.rows = new TList();
      }
      rs.push(r);
   }
}
function TDatasetTreeServiceArg(fn, id, pt, ac, sv){
   var o = this;
   o.callback     = null;
   o.formName     = fn;
   o.formId       = id;
   o.path         = pt;
   o.action       = ac;
   o.service      = sv;
   o.resultConfig = null;
   o.rows         = new TList();
   o.toNode       = TDatasetTreeServiceArg_toNode;
   o.invoke       = TDatasetTreeServiceArg_invoke;
   return o;
}
function TDatasetTreeServiceArg_toNode(){
   var o = this;
   var n = new TNode('Form');
   n.set('name', o.formName);
   n.set('id', o.formId);
   n.set('path', o.path);
   var rp = null;
   if(o.rows && o.rows.count > 0){
      rp = new TNode("RowPath");
      var ct = o.rows.count;
      for(var k = 0; k < ct; k++){
         rp.push(new TNode('Row', o.rows.get(k)));
      }
   }
   if(rp){
      n.push(rp);
   }
   return n;
}
function TDatasetTreeServiceArg_invoke(){
   var o = this;
   if(o.callback){
      o.callback.invoke(o);
   }
}
function TDatasetTreeViewArg(n, i){
   var o = this;
   g.action         = 'update';
   o.callback       = null;
   o.name           = n;
   o.id             = i;
   o.treeNode       = null;
   o.resultDataset  = new TDataset();
   o.values         = null;
   o.set            = TDatasetTreeViewArg_set;
   o.toNode         = TDatasetTreeViewArg_push;
   o.push           = TDatasetTreeViewArg_toNode;
   o.invoke         = TDatasetTreeViewArg_invoke;
   return o;
}
function TDatasetTreeViewArg_set(n, i, dn){
   var o = this;
   o.name = n;
   o.id   = i;
   o.dsName = dn;
}
function TDatasetTreeViewArg_push(r){
   var o = this;
   var nd = new TNode('Row');
   r.saveNode(nd);
   var rd = o.dataset.createRow();
   rd.loadNode(nd);
}
function TDatasetTreeViewArg_toNode(){
   var o = this;
}
function TDatasetTreeViewArg_invoke(){
   var o = this;
   if(o.callback){
      o.callback.invoke(o);
   }
}
function TDatasetUpdateArg(n, i, dn, pt){
   var o = this;
   o.callback        = null;
   o.callbackSuccess = null;
   o.name            = n;
   o.id              = i;
   o.dsName          = dn;
   o.path            = pt
   o.dataset         = new TDataset();
   o.resultDataset   = new TDataset();
   o.checked         = null;
   o.actionType      = 'update';
   o.processFinish   = false;
   o.hasData         = TDatasetUpdateArg_hasData;
   o.set             = TDatasetUpdateArg_set;
   o.push            = TDatasetUpdateArg_push;
   o.toNode          = TDatasetUpdateArg_toNode;
   o.invoke          = TDatasetUpdateArg_invoke;
   o.invokeSuccess   = TDatasetUpdateArg_invokeSuccess;
   return o;
}
function TDatasetUpdateArg_hasData(){
   var o = this;
   if(o.dataset){
      return !o.dataset.rows.isEmpty();
   }
   return false;
}
function TDatasetUpdateArg_set(n, i, dn){
   var o = this;
   o.name = n;
   o.id   = i;
   o.dsName = dn;
}
function TDatasetUpdateArg_push(r){
   var o = this;
   var nd = new TNode('Row');
   r.saveNode(nd);
   var rd = o.dataset.createRow();
   rd.loadNode(nd);
}
function TDatasetUpdateArg_toNode(){
   var o = this;
   var root = new TNode("Form");
   root.set("name", o.name);
   root.set("id", o.id);
   root.set("path", o.path);
   var ds = new TNode("Dataset");
   ds.set("name", o.dsName);
   root.push(ds);
   var rs = o.dataset.rows;
   var rc = rs.count;
   for(var n=0; n<rc; n++){
      var r = rs.get(n);
      var rn = new TNode('Row');
      r.saveNode(rn);
      ds.push(rn);
   }
   return root;
}
function TDatasetUpdateArg_invoke(){
   var o = this;
   if(o.callback){
      o.callback.invoke(o);
   }
}
function TDatasetUpdateArg_invokeSuccess(){
   var o = this;
   if(o.callbackSuccess){
      return o.callbackSuccess.invoke(o);
   }
   return false;
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
      return RMessage.fatal(o, null, 'Process event is null. (owner={0})', RClass.dump(o.owner));
   }
   var sp = new TSpeed(o, 'Process event (owner={0}, process={1})', o.owner, RMethod.name(o.onProcess));
   if(o.owner){
      o.onProcess.call(o.owner, o);
   }else{
      o.onProcess();
   }
   sp.record();
}
function TEvent_dump(){
   return RClass.typeOf(this) + ' [' + this.owner + ',' + this.type + '-' + this.code + ']';
}
function THistory(){
   var o = this;
   o.action = null;
   o.form   = null;
   return o;
}
function TMessageArg(c, f){
   var o = this;
   o.config = c;
   o.form   = f;
   return o;
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
   o.attrs      = TPage_attrs;
   o.split      = TPage_split;
   o.post       = TPage_post;
   o.get        = TPage_get;
   return o;
}
function TPage_split(s){
   if(s){
      this.attrs().split(s, '=', '\n');
   }
}
function TPage_attrs(){
   var o = this;
   if(!o.attributes){
      o.attributes = new TAttributes();
   }
   return o.attributes;
}
function UrlEncode(str){
  var ret="";
  var strSpecial="!\"#$%&'()*+,/:;<=>?[]^`{|}~%";
  for(var i=0;i<str.length;i++){
   var chr = str.charAt(i);
    var c=str2asc(chr);
    tt += chr+":"+c+"n";
    if(parseInt("0x"+c) > 0x7f){
      ret+="%"+c.slice(0,2)+"%"+c.slice(-2);
    }else{
      if(chr==" ")
        ret+="+";
      else if(strSpecial.indexOf(chr)!=-1)
        ret+="%"+c.toString(16);
      else
        ret+=chr;
    }
  }
  return ret;
}
function TPage_post(form, target){
   var o = this;
   RConsole.find(FEnvConsole).buildValue();
   var url = RString.nvl(o.url);
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
   form.action = encodeURI(url);
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
function TUploadArg(){
   var o = this;
   o.type          = null;
   o.url           = null;
   o.fileName      = null;
   o.lsnsStart     = new TListeners();
   o.lsnsUploading = new TListeners();
   o.lsnsFinish    = new TListeners();
   return o;
}
