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
   try{
      if(s){
         var r = e.process(o, EEventType.Before);
         if(EEventStatus.Stop == r || EEventStatus.Cancel == r){
            return r;
         }
      }
   }catch(ex){
      RMessage.fatal(o, ex, 'Process component before failure. (event={1}, name={2}, error={3})', e.dump(), o.name, ex);
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
   try{
      if(s){
         if(EEventStatus.Cancel == e.process(o, EEventType.After)){
            return EEventStatus.Cancel;
         }
      }
   }catch(ex){
      RMessage.fatal(o, ex, 'Process component after failure. (name={1}, error={2})', o.name, ex);
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
   o.dsProcessCustom     = MDataset_dsProcessCustom;
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
   if(g.invokeSuccess()){
	   return;
   }
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
function MDataset_dsPrepare(cb){
   var o = this;
   o.psProgress(true);
   o.psMode(EMode.Insert);
   var g = new TDatasetPrepareArg(o.name, o.formId);
   g.form = o;
   g.values.append(o.dsValues);
   g.callbackSuccess = cb;
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
   o.psProgress(true);
   g.callback = new TInvoke(o, o.onDsProcess);
   RConsole.find(FFormConsole).process(g);
}
function MDataset_dsProcessCustom(pm, da, cb, cc){
	var o = this;
	if(!cc){
	if(!o.psValid()){
	   return;
	}
	}
	var g = new TDatasetServiceArg(o.topControl().name, da);
	g.form = o;
	g.controlName = o.name;
	g.attributes = pm;
	g.codes = o.getDataCodes();
	g.push(o.getCurrentRow());
	g.resultCallback = cb;
	if(!cc){
	   if(!g.hasData()){
	      return RMessage.warn(o, RContext.get('MDataset:nodata'));
	   }
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
   var c = null;
   var x = RXml.makeNode(xml);
   if(x){
      c = this.create(x, hPanel, mode);
   }
   return c;
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
function REvent_ohEvent(e){
   if(!e){
      e = window.event;
   }
   REvent.process(this, e);
}
function REvent_onProcess(){
   var e = this;
   RLogger.debug(e, 'Process {1}. (source={2}, html={3}, process={4})', e.type, RClass.dump(e.source), RClass.dump(e.hSource), RMethod.name(e.onProcess));
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
   var u = RHtml.uid(h);
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
   var un = hs._psource ? RHtml.uid(hs._psource) : RHtml.uid(hs);
   var eo = o.objects[un];
   if(eo){
      var es = eo.events[he.type];
      if(es){
         var l = es.length;
         for(var n=0; n<l; n++){
            var e = es[n];
            e.source = RHtml.findLink(hs, '_plink');
            e.hSender = he.srcElement ? he.srcElement : he.target;
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
               RLogger.debug(e, 'Execute {1}. (source={2}, html={3}, process={4})', e.type, RClass.dump(e.source), RClass.dump(e.hSource), RMethod.name(e.ohProcess));
               try{
                  if(e.sender){
                     e.ohProcess.call(e.source, e.sender, e, he);
                  }else{
                     e.ohProcess.call(e.source, e, he);
                  }
               }catch(ex){
                  RMessage.fatal(o, ex, 'Execute {1} failure. (source={2}, html={3}, process={4})', e.type, RClass.dump(e.source), RClass.dump(e.hSource), RMethod.name(e.ohProcess));
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
   o.dump    = THtmlEvent_dump;
   return o;
}
function THtmlEvent_load(e){
   var o = this;
   o.ctrlKey = e.ctrlKey;
   o.keyCode = e.keyCode;
}
function THtmlEvent_push(pn, pe){
   var o = this;
   var ess = o.events;
   var es = ess[pn];
   if(!es){
      es = new Array();
      es.handle = pe.handle;
      ess[pn] = es;
   }
   var f = pe.name;
   var c = es.length;
   for(var i = 0; i < c; i++){
      var e = es[i];
      if(e.name == f){
         RMessage.fatal(this, 'push', 'Duplicate event for same control. (name={1}, source={2}, event={3})\n{4}\n{5}', pn, RClass.dump(pe.source), RClass.dump(pe), RString.repeat('-', 60), o.dump());
      }
   }
   es[es.length] = pe;
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
function THtmlEvent_dump(){
   var o = this;
   var ess = o.events;
   var r = new TString();
   for(var en in ess){
      var es = ess[en];
      var ec = es.length;
      r.append('event=' + en + ' (count=' + ec + ')\n');
      for(var n = 0; n < ec; n++){
         var e = es[n];
         r.append('   ' + n + ' source=' + RClass.dump(e.source) + ', event=' + RClass.dump(e) + '\n');
      }
   }
   return r.toString();
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
   o.set    = TSearchItem_set;
   o.equals = TSearchItem_equals;
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
function TSearchItem_equals(s){
   var o = this;
   if(o.name == s.name && o.type == s.type && o.value == s.value){
	   return true;
   }
   return false;
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
   o.pack        = TSearchItems_pack;
   o.removeAll   = TSearchItems_removeAll;
   o.unpack      = TSearchItems_unpack;
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
function TSearchItems_removeAll(v){
   if(null != v){
      var o = this;
      var n = 0;
      var c = o.count;
      for(var i=n; i<c; i++){
         if(!o.memory[i].equals(v)){
            o.memory[n++] = o.memory[i];
         }
      }
      o.count = n;
   }
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
