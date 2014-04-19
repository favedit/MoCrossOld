function FFlow(o){
   o = RClass.inherits(this, o, FContainer);
    o.oeBuild = FFlow_oeBuild;
  return o;
}
function FFlow_oeBuild(e){
   var o = this;
   var r = o.base.FContainer.oeBuild.call(o, e)
   var hp = o.hPanel;
   if(e.isBefore()){
      var hfp = RBuilder.appendDiv(o.hPanel);
      hfp.style.width = '100%';
      hfp.style.align = 'center';
      hfp.style.overflow = 'auto'
      var hf = o.hForm = RBuilder.appendTable(hfp);
      hf.width = '100%';
   }else if(e.isAfter()){
      var ps = o.components;
      for(var n=0; n<ps.count; n++){
         var p = ps.value(n);
         if(RClass.isClass(p, FFlowProcess)){
            var hr = o.hForm.insertRow();
            var hc = hr.insertCell();
            hc.appendChild(p.hPanel);
         }
      }
   }
   return r;
}
function FFlowExamine(o){
   o = RClass.inherits(this, o, FControl);
   o.code    = RClass.register(o, new TPtyStr('code'));
   o.icon    = RClass.register(o, new TPtyStr('icon'));
   o.mode    = RClass.register(o, new TPtyStr('mode'));
   o.hIcon   = null;
   o.oeBuild = FFlowExamine_oeBuild;
   return o;
}
function FFlowExamine_oeBuild(e){
   var o = this;
   o.base.FControl.oeBuild.call(o, e);
   var hp = o.hPanel;
   var fi = null;
   if('A' == o.mode){
      fi = 'And';
   }else{
      fi = 'Or';
   }
   o.hModeIcon = RBuilder.appendIcon(hp, 'ctl.FFlowExamine_' + fi);
   var fi = null;
   if(o.icon == 'M'){
      fi = 'male';
   }else if(o.icon == 'F'){
      fi = 'female';
   }else{
      fi = 'unknown';
   }
   o.hIcon = RBuilder.appendIcon(hp, 'logic.person.user.' + fi);
   if(o.hint){
      o.hIcon.title = o.hint;
   }
   var hl = o.hLabel = RBuilder.appendText(hp);
   hl.style.whiteSpace = 'nowrap';
   var s = o.label;
   if(o.hint){
   }
   hl.innerHTML = s;
   return EEventStatus.Stop;
}
function FFlowLevel(o){
   o = RClass.inherits(this, o, FContainer);
    o.oeBuild = FFlowLevel_oeBuild;
   return o;
}
function FFlowLevel_oeBuild(e){
   var o = this;
   var r = o.base.FContainer.oeBuild.call(o, e)
   var hp = o.hPanel;
   if(e.isBefore()){
      var hf = o.hForm = RBuilder.appendTable(hp);
      hf.width = '100%';
      hf.bgColor = '#FFFFFF';
      hf.style.borderLeft = '1 solid #999999';
      hf.style.borderTop = '1 solid #999999';
      hf.style.borderRight = '1 solid #CCCCCC';
      hf.style.borderBottom = '1 solid #CCCCCC';
   }else if(e.isAfter()){
      var hf = o.hForm;
      var ps = o.components;
      for(var n=0; n<ps.count; n++){
         var p = ps.value(n);
         if(RClass.isClass(p, FFlowExamine)){
            var hc = o.hForm.insertRow().insertCell();
            hc.style.padding = 2;
            hc.appendChild(p.hPanel);
         }
      }
   }
   return r;
}
function FFlowProcess(o){
   o = RClass.inherits(this, o, FContainer);
   o._steps    = null;
   o._roles     = null;
   o.oeBuild   = FFlowProcess_oeBuild;
   o.construct = FFlowProcess_construct;
   o.push      = FFlowProcess_push;
   return o;
}
function FFlowProcess_oeBuild(e){
   var o = this;
   var r = o.base.FContainer.oeBuild.call(o, e)
   var hp = o.hPanel;
   if(e.isBefore()){
      var hf = o.hForm = RBuilder.appendTable(hp);
      hf.width = '100%';
      var hsf = o.hStepForm = RBuilder.appendTable(hf.insertRow().insertCell());
      hsf.width = '100%';
      var hsl = o.hStepLine = hsf.insertRow();
      hsl.vAlign = 'top';
   }else if(e.isAfter()){
      var ps = o._steps;
      var pc = ps.count;
      var hr = o.hStepLine;
      for(var n=0; n<pc; n++){
         var p = ps.get(n);
         if(n > 0){
            var hc = hr.insertCell();
            hc.noWrap = true;
            hc.style.paddingTop = 14;
            RBuilder.appendIcon(hc, 'ctl.FFlowProcess_Next', null, 11, 11);
         }
         var hc = hr.insertCell();
         hc.style.padding = '0 3';
         hc.appendChild(p.hPanel);
         hc.width = (100/pc) + '%';
      }
      var rs = o._roles;
      var rc = rs.count;
      for(var n=0; n<rc; n++){
         var r = rs.get(n);
         var hc = o.hForm.insertRow().insertCell();
         hc.style.padding = '4 6';
         hc.appendChild(r.hPanel);
      }
   }
   return r;
}
function FFlowProcess_construct(){
   var o = this;
   o._steps = new TList();
   o._roles = new TList();
}
function FFlowProcess_push(p){
   var o = this;
   p.name = null;
   o.base.FContainer.push.call(o, p);
   if(RClass.isClass(p, FFlowStep)){
      o._steps.push(p);
   }else if(RClass.isClass(p, FFlowRole)){
      o._roles.push(p);
   }
}
function FFlowRole(o){
   o = RClass.inherits(this, o, FContainer);
    o.oeBuild = FFlowRole_oeBuild;
   return o;
}
function FFlowRole_oeBuild(e){
   var o = this;
   var r = o.base.FContainer.oeBuild.call(o, e)
   var hp = o.hPanel;
   if(e.isBefore()){
      var hfp = o.hFormPanel = RBuilder.appendDiv(hp);
      hfp.style.backgroundColor = '#FFFFFF';
      hfp.style.borderLeft = '1 solid #999999';
      hfp.style.borderTop = '1 solid #999999';
      hfp.style.borderRight = '1 solid #CCCCCC';
      hfp.style.borderBottom = '1 solid #CCCCCC';
      var hf = o.hForm = RBuilder.appendTable(hfp);
      var hr = o.hFormLine = hf.insertRow();
      var hc = hr.insertCell();
      hc.style.padding = '2 6';
      hc.innerText = o.label;
   }else if(e.isAfter()){
      var hf = o.hForm;
      var ps = o.components;
      if(ps){
         for(var n=0; n<ps.count; n++){
            var p = ps.value(n);
            if(RClass.isClass(p, FFlowExamine)){
               var hc = o.hFormLine.insertCell();
               hc.style.padding = '2 6';
               hc.appendChild(p.hPanel);
            }
         }
      }
      o.hFormLine.insertCell();
   }
   return r;
}
function FFlowStep(o){
   o = RClass.inherits(this, o, FContainer);
   o.levels  = new TList();
   o.roles   = new TList();
   o.oeBuild = FFlowStep_oeBuild;
   o.push    = FFlowStep_push;
   return o;
}
function FFlowStep_oeBuild(e){
   var o = this;
   var r = o.base.FContainer.oeBuild.call(o, e)
   var hp = o.hPanel;
   if(e.isBefore()){
      var b = o.border = new TBorder(EBorder.Round, hp);
      b.build();
      var hf = o.hForm = RBuilder.appendTable(b.hPanel, null, 0, 3);
      hf.width = '100%';
      hf.height = '100%';
      hf.bgColor = '#DAF3FF';
      hf.style.filter = 'progid:DXImageTransform.Microsoft.Gradient(gradienttype=0,startcolorstr=#FFFFFF,endcolorstr=#BFD6FF)';
      var hr = hf.insertRow();
      var hc = hr.insertCell();
      var b = o.labelBorder = new TBorder(EBorder.Round, hc);
      b.build();
      b.setBorderColor('#999999');
      b.setBackgroundColor('#BBBBBB');
      var hl = b.hPanel;
      hl.style.padding = '3 0';
      hl.align = 'center';
      hl.style.color = '#FFFFFF';
      hl.style.fontWeight = 'bold';
      hl.style.whiteSpace = 'nowrap';
      hl.innerText = RString.nvl(o.label);
   }else if(e.isAfter()){
      var hf = o.hLevelForm = RBuilder.appendTable(o.hForm.insertRow().insertCell(), null, 0, 1);
      hf.width = '100%';
      var rs = o.roles;
      if(!rs.isEmpty()){
         var rc = rs.count;
         for(var n=0; n<rc; n++){
            var r = rs.get(n);
            var hr = hf.insertRow();
            var hc = hr.insertCell();
            hc.appendChild(r.hPanel);
         }
      }
      var ls = o.levels;
      if(!ls.isEmpty()){
         var lc = ls.count;
         for(var n=0; n<lc; n++){
            var l = ls.get(n);
            var hr = hf.insertRow();
            var hc = hr.insertCell();
            hc.appendChild(l.hPanel);
         }
      }
   }
   return r;
}
function FFlowStep_push(p){
   var o = this;
   p.name = null;
   o.base.FContainer.push.call(o, p);
   if(RClass.isClass(p, FFlowLevel)){
      o.levels.push(p);
   }
   if(RClass.isClass(p, FFlowStepRole)){
      o.roles.push(p);
   }
}
function FFlowStepRole(o){
   o = RClass.inherits(this, o, FContainer);
    o.oeBuild = FFlowStepRole_oeBuild;
   return o;
}
function FFlowStepRole_oeBuild(e){
   var o = this;
   var r = o.base.FContainer.oeBuild.call(o, e)
   var hp = o.hPanel;
   if(e.isBefore()){
      var hfp = o.hFormPanel = RBuilder.appendDiv(hp);
      var hf = o.hForm = RBuilder.appendTable(hfp);
   }else if(e.isAfter()){
      var hf = o.hForm;
      var ps = o.components;
      if(ps){
         for(var n=0; n<ps.count; n++){
            var p = ps.value(n);
            if(RClass.isClass(p, FFlowExamine)){
               var hc = o.hForm.insertRow().insertCell();
               hc.style.padding = '1 3';
               hc.appendChild(p.hPanel);
            }
         }
      }
   }
   return r;
}
