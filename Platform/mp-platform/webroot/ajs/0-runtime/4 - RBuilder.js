//===========================================================
// HTML对象创建的操作类
//
// @enum
// @author maochunyang
// @version 1.0.1
//===========================================================
var RBuilder = new function(){
   var o = this;
   ///@attrbute HtmlTag 获取窗口的Window句柄
   o.hWindow           = window;
   ///@attrbute HtmlTag 获取窗口的document
   o.hDocument         = window.document;
   //..........................................................
   // @event
   o.onBuildSpanPanel  = RBuilder_onBuildSpanPanel;
   o.onBuildDivPanel   = RBuilder_onBuildDivPanel;
   o.onBuildTdPanel    = RBuilder_onBuildTdPanel;
   o.onBuildTrPanel    = RBuilder_onBuildTrPanel;
   o.onBuildTablePanel = RBuilder_onBuildTablePanel;
   //..........................................................
   // @method
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
   // Construct
   RMemory.register('RBuilder', o);
   return o;
}

//===========================================================
function RBuilder_onBuildSpanPanel(){
   this.hPanel = RBuilder.newSpan();
}
//===========================================================
function RBuilder_onBuildDivPanel(){
   this.hPanel = RBuilder.newDiv();
}
//===========================================================
function RBuilder_onBuildTdPanel(){
   this.hPanel = RBuilder.create(null, 'TD');
}
//===========================================================
function RBuilder_onBuildTrPanel(){
   this.hPanel = RBuilder.create(null, 'TR');
}
//===========================================================
function RBuilder_onBuildTablePanel(){
   this.hPanel = RBuilder.newTable();
}

//===========================================================
// 创建一个html对象，如果有父容器就放在里面，没有就放在Document.body里
//
// @method
// @param parent:parent:HTML html容器
// @param name:name:String html标签
// @param css:css:String 显示的样式表
// @see RBuilder.create
// @return Boolean 返回Boolean类型
//===========================================================
function RBuilder_append(parent, name, css){
   var h = this.create(parent, name, css);
   if(!parent){
      parent = this.hDocument.body;
   }
   parent.appendChild(h);
   return h;
}

//===========================================================
// 创建一个img对象，放在父容器里面，并返回这个img对象
//
// @method
// @param parent:parent:HTML html容器
// @param path:path:String 图片的存放路径
// @param css:css:String 显示的样式表
// @param width:width:Integer 图片的显示宽度
// @param height:height:Integer 图片的显示宽度
// @see RBuilder_newImage
// @return Object 返回创建的img对象
//===========================================================
function RBuilder_appendImage(parent, path, css, width, height){
   var h = this.newImage(parent, path, css, width, height);
   parent.appendChild(h);
   return h;
}

//===========================================================
// 创建一个Icon对象，放在父容器里面，并返回这个img对象
//
// @method
// @param parent:parent:HTML html容器
// @param path:path:String 图片的存放路径
// @param css:css:String 显示的样式表
// @param width:width:Integer 图片的显示宽度
// @param height:height:Integer 图片的显示宽度
// @see RBuilder_newIcon
// @return Object 返回创建的Icon对象
//===========================================================
function RBuilder_appendIcon(parent, path, css, width, height){
   var h = this.newIcon(parent, path, css, width, height);
   parent.appendChild(h);
   return h;
}

//===========================================================
// 创建一个空白占位对象，放在父容器里面，并返回这个空白对象
//
// @method
// @param parent:parent:HTML html容器
// @param width:width:Integer 图片的显示宽度
// @param height:height:Integer 图片的显示宽度
// @see RBuilder_newIcon
// @return Object 返回创建的空白对象
//===========================================================
function RBuilder_appendEmpty(p, w, h){
   var o = this.newIcon(p, 'n', null, w, h);
   p.appendChild(o);
   return o;
}

//===========================================================
// 创建一个文本span对象，放在父容器里面，并返回这个对象
//
// @method
// @param parent:parent:HTML html容器
// @param html:html:HTML 对象内部放入的HTML
// @param css:css:String 显示的样式表
// @see RBuilder_newText
// @return Object 返回创建text的对象
//===========================================================
function RBuilder_appendText(parent, html, css){
   var h = this.newText(parent, html, css);
   if(!parent){
      parent = this.hDocument.body;
   }
   parent.appendChild(h);
   return h;
}

//===========================================================
// 创建一个div对象，放在父容器里面，并返回这个对象
//
// @method
// @param parent:parent:HTML html容器
// @param css:css:String 显示的样式表
// @see RBuilder_newDiv
// @return Object  返回创建的Div对象
//===========================================================
function RBuilder_appendDiv(parent, css){
   var h = this.newDiv(parent, css);
   if(!parent){
      parent = this.hDocument.body;
   }
   parent.appendChild(h);
   return h;
}

//===========================================================
// 创建一个table对象，放在父容器里面，并返回这个空白对象
//
// @method
// @param parent:parent:HTML html容器
// @param css:css:String 样式表
// @param border:border:Integer 边框宽度
// @param spaceing:spaceing:Integer 单元格之间的宽度
// @param padding:padding:Integer 单元格内文字与单元格边框之间的距离
// @see RBuilder_newTable
// @return Object 返回创建的table对象
//===========================================================
function RBuilder_appendTable(parent, css, border, spaceing, padding){
   var h = this.newTable(parent, css, border, spaceing, padding);
   if(!parent){
      parent = this.hDocument.body;
   }
   parent.appendChild(h);
   return h;
}

//===========================================================
// 创建一个Row对象，放在父容器table里面，并返回这个对象
//
// @method
// @param table:table:HTML html表格容器
// @param css:css:String 样式表
// @param index:index:Integer 插入的行号
// @param width:width:Integer 行宽度
// @param height:height:Integer 行高度
// @see RBuilder_newIcon
// @return Object 返回创建的tr对象
//===========================================================
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

//===========================================================
// 创建一个td对象，放在tr里面，并返回这个对象
//
// @method
// @param parent:parent:HTML tr容器
// @param css:css:String 样式表
// @param html:html:HTML 图片的显示宽度
// @param height:height:Integer 图片的显示宽度
// @return Object 返回创建的td对象
//===========================================================
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

//===========================================================
// 创建一个复选框对象，放在父容器里面，并返回这个复选框对象
//
// @method
// @param parent:parent:HTML html容器
// @param css:css:String 样式表
// @see RBuilder_newCheck
// @return Object 返回创建的复选框对象
//===========================================================
function RBuilder_appendCheck(parent, css){
   var h = this.newCheck(parent, css);
   parent.appendChild(h);
   return h;
}

//===========================================================
//创建一个复选框对象，放在父容器里面，并返回这个复选框对象
//
//@method
//@param parent:parent:HTML html容器
//@param css:css:String 样式表
//@see RBuilder_newCheck
//@return Object 返回创建的复选框对象
//===========================================================
function RBuilder_appendRadio(parent, css){
   var h = this.newRadio(parent, css);
   parent.appendChild(h);
   return h;
}

//===========================================================
// 创建一个input text对象，放在父容器里面，并返回这个input text对象
//
// @method
// @param parent:parent:HTML html容器
// @param css:css:String 样式表
// @see RBuilder_newEdit
// @return Object 返回创建的空白对象
//===========================================================
function RBuilder_appendEdit(parent, css){
   var h = this.newEdit(parent, css);
   parent.appendChild(h);
   return h;
}

//===========================================================
// 创建一个html对象，放在父容器里面，并返回空白对象
//
// @method
// @param parent:parent:HTML html容器
// @param n:name:String HTML标签
// @param css:css:String 样式文本
// @return h HTML对象
//===========================================================
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


//===========================================================
// 创建一个img对象，放在父容器里面，并返回空白对象
//
// @method
// @param parent:parent:HTML html容器
// @param s:src:String 图片路径
// @param c:css:String 图片的样式表
// @param w:width:Integer 图片高度
// @param h:height:Integer 图片宽度
// @see RBuilder.create
// @see RRes.imagePath
// @return h HTML对象
//===========================================================
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

//===========================================================
// 创建一个img icon对象，放在父容器里面，并返回这个对象
//
// @method
// @param parent:parent:HTML html容器
// @param s:src:String 图片路径
// @param c:css:String 图片的样式表
// @param w:width:Integer 图片高度
// @param h:height:Integer 图片宽度
// @see RBuilder.create
// @see RRes.iconPath
// @return h HTML对象
//===========================================================
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

//===========================================================
// 创建一个span对象，放在父容器里面，并返回这个对象
//
// @method
// @param parent:parent:HTML html容器
// @param css:css:String 样式表
// @see RBuilder_create
// @return Object 返回创建的空白对象
//===========================================================
function RBuilder_newSpan(parent, css){
   return this.create(parent, 'SPAN', css);
}

//===========================================================
// 创建一个div对象，放在父容器里面，并返回这个div对象
//
// @method
// @param parent:parent:HTML html容器
// @param css:css:String 样式表
// @see RBuilder_create
// @return Object 返回创建的div对象
//===========================================================
function RBuilder_newDiv(parent, css){
   return this.create(parent, 'DIV', css);
}

//===========================================================
// 创建一个table对象，放在父容器里，返回这个对象
//
// @method
// @param parent:parent:HTML html容器\
// @param css:css:String 样式表
// @param border:border:Integer 边框宽度
// @param spaceing:spaceing:Integer 单元格之间的宽度
// @param padding:padding:Integer 单元格内文字与单元格边框之间的距离
// @see RBuilder_create
// @see RInt.nvl
// @return Object 返回创建的HTML对象
//===========================================================
function RBuilder_newTable(parent, css, border, spaceing, padding){
   var h = this.create(parent, 'TABLE', css);
   h.border = RInt.nvl(border);
   h.frame = 'box';
   h.cellSpacing = RInt.nvl(spaceing);
   h.cellPadding = RInt.nvl(padding);
   return h;
}

//===========================================================*
// 创建一个span对象，存放一个HTMl标签后，放在父容器里，返回这个对象
//
// @method
// @param parent:parent:HTML html容器
// @param html:html:HTML HTML对象
// @param css:css:String 样式表
// @see RBuilder_create
// @return Object 返回创建的HTML对象
//===========================================================
function RBuilder_newText(parent, html, css){
   var h = this.create(parent, 'SPAN', css);
   h.innerHTML = html;
   return h;
}

//===========================================================
// 创建一个input checkbox对象，放在父容器里，返回这个对象
//
// @method
// @param parent:parent:HTML html容器
// @param css:css:String 样式表
// @see RBuilder_create
// @return Object 返回创建的input对象
//===========================================================
function RBuilder_newCheck(parent, css){
   var r = this.create(parent, "INPUT", css);
   r.type = 'checkbox';
   return r;
}

//===========================================================
//创建一个input checkbox对象，放在父容器里，返回这个对象
//
//@method
//@param parent:parent:HTML html容器
//@param css:css:String 样式表
//@see RBuilder_create
//@return Object 返回创建的input对象
//===========================================================
function RBuilder_newRadio(parent, css){
   var r = this.create(parent, "INPUT", css);
   r.type = 'radio';
   return r;
}

//===========================================================
// 创建一个input text对象，放在父容器里，返回这个对象
//
// @method
// @param parent:parent:HTML html容器
// @param css:css:String 样式表
// @see RBuilder_create
// @return Object 返回创建的input对象
//===========================================================
function RBuilder_newEdit(parent, css){
   var r = this.create(parent, "INPUT", css);
   r.type = 'text';
   return r;
}
