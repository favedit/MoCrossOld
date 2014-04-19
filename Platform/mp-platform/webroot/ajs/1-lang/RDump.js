//===========================================================
// RDump
//===========================================================
var RDump = new function(){
   var o = this;
   // Define
   o.LINE_SINGLE  = '------------------------------';
   o.LINE_DOUBLE  = '==============================';
   o.LINE_DOT     = '..............................';
   o.LINE_STAR    = '******************************';
   // Method
   o.onclick     = RDump_onclick;
   o.nameInfo    = RDump_nameInfo;
   o.typeInfo    = RDump_typeInfo;
   o.dumpInner   = RDump_dumpInner;
   o.dump        = RDump_dump;
   o.appendLevel = RDump_appendLevel;
   o.stack       = RDump_stack;
   // Construct
   RMemory.register('RDump', o);
   return o;
}

//===========================================================
//
//===========================================================
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

//===========================================================
//
//===========================================================
function RDump_nameInfo(name){
   var type = RClass.typeOf(name);
   // Object info
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

//===========================================================
//
//===========================================================
function RDump_typeInfo(obj, type){
   if(obj == null){
      return 'null';
   }
   // Object info
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

//===========================================================
//
//===========================================================
function RDump_dumpInner(di){
   var hTable  = di.hTable;
   var hParent = di.hObj;
   var hInsRow = di.hRow;
   var level   = di.level;
   var obj     = di.link;
   var type    = RClass.typeOf(obj, true);
   // Names sort
   var names = new Array();
   for(var name in obj){
      names[names.length] = name;
   }
   if(type != 'Array'){
      RArray.sort(names, true);
   }
   // Items Info
   var items = new Array();
   var c = names.length;
   for(var n = 0; n < c; n++){
      var name = names[n];
      var value = obj[name];
      var type = RClass.safeTypeOf(value, true);
      var info = this.typeInfo(value, type);
      // Row
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
      // Virtual function
      if(type == 'Function' && info == 'virtual'){
         hRow.bgColor = '#E0F0FF';
      }
      // Cell: Name
      var hCell = hRow.insertCell(-1);
      var icon = RString.startsWith(info, '@') ? '+' : ' ';
      hCell.innerText = RString.repeat('   ', level) + icon + ' ' + name;
      hCell.style.borderBottom = '1px solid';
      hCell.width = '200px'
      if(rdi){
         rdi.hText = hCell;
      }
      // Cell: type
      var hCell = hRow.insertCell(-1);
      hCell.innerText = type;
      hCell.style.borderBottom = '1px solid';
      hCell.width = '100px'
      // Cell: Info
      var hCell = hRow.insertCell(-1);
      if(RString.startsWith(info, '@')){
         info = info.substr(1);
      }
      hCell.innerHTML = RHtml.toHtml(info);
      hCell.style.borderBottom = '1px solid';
   }
}

//===========================================================
// arguments:
//   object
//   object, hObject
//
//===========================================================
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
   // 追加行内容
   var hRow = hTable.insertRow(-1);
   var hCell = hRow.insertCell(-1);
   hCell.innerText = dump;
   hCell.colSpan = 3;
   hCell.style.padding = 2;
   hCell.style.borderBottom = '2px solid gray';
   hCell.style.backgroundColor = '#E0E0EB';
   // 建立新的层次
   var di = new TDumpItem();
   di.hTable = hTable;
   di.hObj = ho;
   di.link = obj;
   di.level = 0;
   this.dumpInner(di);
}

//===========================================================
//
//===========================================================
function RDump_appendLevel(dump, level){
   for(var n=0; n<level; n++){
      dump.append('   ');
   }
}

//===========================================================
//
//===========================================================
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
