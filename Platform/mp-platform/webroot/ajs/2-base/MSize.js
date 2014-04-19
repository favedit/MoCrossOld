/**************************************************************
 * 改变控件大小的接口类
 *
 * @manger
 * @author maochunyang
 * @version 1.0.1
 **************************************************************/
function MSize(o){
   o = RClass.inherits(this, o);
   // Property
   o.left        = RClass.register(o, new TPtyInt('left',   -1));
   o.top         = RClass.register(o, new TPtyInt('top',    -1));
   o.right       = RClass.register(o, new TPtyInt('right',  -1));
   o.bottom      = RClass.register(o, new TPtyInt('bottom', -1));
   o.width       = RClass.register(o, new TPtyStr('width'));
   o.height      = RClass.register(o, new TPtyStr('height'));
   // Attribute
   o.rect        = null;
   // Event
   o.onSize      = null;
   // Method
   o.calcRect    = MSize_calcRect;
   o.resize      = MSize_resize;
   o.setSize     = MSize_setSize;
   o.setBounds   = MSize_setBounds;
   o.resetSize   = MSize_resetSize;
   o.innerDump   = MSize_innerDump;
   o.testInRange = MSize_testInRange;
   return o;
}
// ------------------------------------------------------------
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
// ------------------------------------------------------------
// width, height
function MSize_setSize(w, h){
   var s = this.hPanel.style;
   if(w){
      s.width = w;
   }
   if(h){
      s.height = h;
   }
}
// ------------------------------------------------------------
function MSize_setBounds(l, t, r, b, force){
   var o = this;
   var h = o.panel(EPanel.Size);
   if(!h){
      return;
   }
   var s = h.style;
   var c = false;
   // set left and top
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
   // set left and top
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
// ------------------------------------------------------------
function MSize_resetSize(){
   var o = this;
   o.setBounds(o.left, o.top, o.left+o.width-1, o.top+o.height-1, true)
}
// ------------------------------------------------------------
function MSize_calcRect(){
   this.rect = RRect.nvl(this.rect);
   RHtml.toRect(this.rect, this.hPanel);
   return this.rect;
}
// ------------------------------------------------------------
function MSize_innerDump(dump){
   dump.append('Size [', RBool.toString(this.isSizeable), ':');
   dump.append(this.left, ',', this.top, '-', this.width, ',', this.height, ']');
}
// ------------------------------------------------------------
function MSize_testInRange(){
}
// ------------------------------------------------------------
