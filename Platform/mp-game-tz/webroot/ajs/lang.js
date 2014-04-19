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
      RArray.sort(names, true);
   }
   var items = new Array();
   var c = names.length;
   for(var n = 0; n < c; n++){
      var name = names[n];
      var value = obj[name];
      var type = RClass.safeTypeOf(value, true);
      var info = this.typeInfo(value, type);
      var rdi = null;
      var index = hInsRow ? hInsRow.rowIndex+1 : 1;
      var hRow = hTable.insertRow(index);
      if(RString.startsWith(info, '@')){
         hRow.style.cursor = 'pointer';
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
      var hCell = hRow.insertCell(-1);
      var icon = RString.startsWith(info, '@') ? '+' : ' ';
      hCell.innerText = RString.repeat('   ', level) + icon + ' ' + name;
      hCell.style.borderBottom = '1px solid';
      hCell.width = '200px'
      if(rdi){
         rdi.hText = hCell;
      }
      var hCell = hRow.insertCell(-1);
      hCell.innerText = type;
      hCell.style.borderBottom = '1px solid';
      hCell.width = '100px'
      var hCell = hRow.insertCell(-1);
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
   var hRow = hTable.insertRow(-1);
   var hCell = hRow.insertCell(-1);
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
   o = this;
   o.nextUid        = 1;
   o.links          = new Object();
   o.uid            = RHtml_uid;
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
   o.currentStyle   = RHtml_currentStyle;
   o.tableMoveRow   = RHtml_tableMoveRow;
   o.clone          = RHtml_clone;
   RMemory.register('RHtml', o);
   return o;
}
function RHtml_uid(p){
   var o = this;
   var r = p.uniqueNumber;
   if(!r){
      r = p.uniqueNumber = o.nextUid;
      o.nextUid++;
   }
   return r;
}
function RHtml_clone(o, s, t){
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
      RHtml_clone(o, p[n], t.children[n]);
   }
   return t;
}
function RHtml_offsetPosition(h, t){
   var p = new TPoint();
   while(h != t){
      p.x += h.offsetLeft - h.scrollLeft;
      p.y += h.offsetTop - h.scrollTop;
      if('absolute' != RHtml.currentStyle(h).position){
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
   var u = RHtml.uid(h);
   var hi = ls[u];
   if(!hi){
      hi = ls[u] = new THtmlItem();
      hi.link = h;
   }
   hi.set(n, v);
}
function RHtml_findLink(h, n){
   var u = RHtml.uid(h);
   var hi = this.links[u];
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
function RHtml_currentStyle(p){
   if(p.currentStyle){
      return p.currentStyle;
   }
   return window.getComputedStyle(p, null);
}
function RHtml_point(o, p){
   return this.toPoint(new TPoint(), o, p);
}
function RHtml_toPoint(r, o, p){
   if(r && o){
      p = RObject.nvl(p, window.document.body);
      var cs = RHtml.currentStyle(o);
      r.x = -RInt.parse(cs.borderLeftWidth);
      r.y = -RInt.parse(cs.borderTopWidth);
      while(o && o != p){
         r.x += o.offsetLeft - o.scrollLeft;
         r.y += o.offsetTop - o.scrollTop;
         if('absolute' != RHtml.currentStyle(o).position){
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
      var cs = RHtml.currentStyle(o);
      r.left = -RInt.parse(cs.borderLeftWidth);
      r.top = -RInt.parse(cs.borderTopWidth);
      var w = o.offsetWidth; w = o.offsetWidth-1;
      var h = o.offsetHeight; h = o.offsetHeight-1;
      while(o && o != p){
         r.left += o.offsetLeft - o.scrollLeft;
         r.top += o.offsetTop - o.scrollTop;
         if('absolute' != RHtml.currentStyle(o).position){
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
      var cs = RHtml.currentStyle(o);
      r = -RInteger.parse(cs.borderTopWidth);
      while(h){
         r += h.offsetTop - h.scrollTop;
         if('absolute' != RHtml.currentStyle(o).position){
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
      var ts = RHtml.currentStyle(t);
      var tw = parseInt(ts.paddingLeft) + parseInt(ts.paddingRight);
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
function getTRNode(nowTR, sibling) {
   while(nowTR = nowTR[sibling]){
      if(nowTR.tagName == 'TR'){
         break;
      }
   }
   return nowTR;
}
function RHtml_tableMoveRow(ph, ps, pt){
   if(ph.tagName != 'TABLE'){
      return false;
   }
   if(ps == pt){
      return false;
   }
   if(ph.moveRow){
      ph.moveRow(ps, pt);
   }else{
      var hb = ph.getElementsByTagName('tbody')[0];
      var sr = hb.rows[ps];
      var tr = hb.rows[pt];
      if((sr == null) || (tr == null)){
         return false;
      }
      var nr = null;
      if(ps <= pt){
         nr = tr;
         while(nr = nr.nextSibling){
            if(nr.tagName == 'TR'){
               break;
            }
         }
      }
      if(nr == null){
         hb.insertBefore(sr, tr);
      }else{
         if(nr == null){
            hb.appendChild(sr);
         }else{
            hb.insertBefore(sr, nr);
         }
      }
   }
   return true;
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
   o.onLoad      = null;
   o.onFire      = TXmlConnect_onFire;
   o.onCnnReady  = TXmlConnect_onCnnReady;
   o.onDocReady  = TXmlConnect_onDocReady;
   o.construct   = TXmlConnect_construct;
   o.setHeaders  = TXmlConnect_setHeaders;
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
   if(cnn.readyState == EXmlStatus.Finish){
      var dc = this.docControl;
      if(RXml.modeCd == EBrowser.IE){
         var self = this;
         dc.async = true;
         dc.onreadystatechange = function(){self.onDocReady(dc, doc)};
         dc.loadXML(cnn.responseText);
      }else{
         this.onFire(doc, cnn.responseXML.documentElement);
      }
   }
}
function TXmlConnect_onDocReady(dc, doc){
   if(dc.readyState == EXmlParse.Finish){
      if(dc.documentElement){
         this.onFire(doc, dc.documentElement);
      }else{
         alert('Read xml error.\n' + this.cnnControl.responseText);
      }
   }
}
function TXmlConnect_construct(){
   var o = this;
   o.cnnControl = RXml.newConnect();
   o.docControl = RXml.newDocument();
}
function TXmlConnect_setHeaders(cnn, len){
   if(RXml.modeCd == EBrowser.IE){
      cnn.setRequestHeader('content-type', 'application/x-www-form-urlencoded');
      cnn.setRequestHeader('content-length', len);
   }
}
function TXmlConnect_send(url, doc){
   var o = this;
   o.inUsing = true;
   o.address = url;
   var xml = doc.xml().toString();
   var cnn = o.cnnControl;
   RLogger.info(this, 'Send xml url. (url={0})', url);
   cnn.abort();
   cnn.open('POST', url, true);
   o.setHeaders(cnn, xml.length);
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
