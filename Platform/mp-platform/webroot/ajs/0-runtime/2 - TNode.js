//==========================================================
// <T>XML节点工具类</T>
//
// @tool
// @author maochunyang
// @version 1.0.1
//==========================================================
function TNode(name, attrs){
   var o = this;
   // Attribute
   o.name         = RString.nvl(name, 'Node');
   o.value        = null;
   o.attrs        = attrs;
   o.nodes        = null;
   // Method
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

//==========================================================
// <T>判断当前节点的名称是否为指定的节点名称。</T>
//
// @method
// @param name:name:String 节点名称
// @see RString.equals
//    <L value='true'>是</L>
//    <L value='false'>否</L>
//==========================================================
function TNode_isName(name){
   return RString.equals(this.name, name);
}

//==========================================================
// <T>判断当前节点下是否有任何属性。</T>
//
// @method
// @return Boolean
//    <L value='true'>有</L>
//    <L value='false'>无</L>
//==========================================================
function TNode_hasAttribute(){
   var o = this;
   return o.attrs ? !o.attrs.isEmpty() : false;
}

//==========================================================
// <T>判断当前节点下是否有任何子节点。</T>
//
// @method
// @return Boolean
//    <L value='true'>有</L>
//    <L value='false'>无</L>
//==========================================================
function TNode_hasNode(){
   var o = this;
   return o.nodes ? !o.nodes.isEmpty() : false;
}

//==========================================================
// <T>取得节点的所有属性名称数组。</T>
//
// @method
// @return Array 属性名称数组 
//==========================================================
function TNode_attributes(){
   var o = this;
   if(!o.attrs){
      o.attrs = new TAttributes();
   }
   return o.attrs;
}

//==========================================================
// <T>是否包含了指定的属性。</T>
//
// @method
// @param name:name:String 属性名称
// @see RArray.contains
// @return Boolean 返回Boolean类型
//==========================================================
function TNode_contains(n){
   return this.attrs ? this.attrs.contains(n) : false;
}

//==========================================================
// <T>获取节点名称，若为空则置传入的默认值。</T>
//
// @method
// @param name:name:String 属性名称
// @param def:default:String 缺省属性名称
// @return String 属性名称
//==========================================================
function TNode_nvl(name, def){
   return RString.nvl(this.get(name), def);
}

//==========================================================
// <T>取得属性对应的属性值。</T>
//
// @method
// @param n:name:String 属性名称
// @param v:value:String 属性值
// @see RArray.get
// @return String 返回属性值
//==========================================================
function TNode_get(n, v){
   return this.attrs ? this.attrs.get(n, v) : null;
}

//==========================================================
// <T>设置属性对应的属性值。</T>
//
// @method
// @param n:name:String 属性名称
// @param v:value:String 属性值
// @see RArray.set
//==========================================================
function TNode_set(name, value){
   if(null != value){
      this.attributes().set(name, value);
   }
}
//==========================================================
// <T>根据属性名称取得一个节点的属性值。</T>
//
// @method
// @param n:name:String 属性名称
// @see TNode.get
// @return String 属性值
//==========================================================
function TNode_node(n){
   return this.nodes ? this.nodes.get(n) : null;
}

//==========================================================
// <T>给当前节点添加一个子结点。</T>
// <P>子节点已被创建</P>
//
// @method
// @param node:node:TNode 要添加的节点
//==========================================================
function TNode_push(node){
   var o = this;
   if(!o.nodes){
      o.nodes = new TList();
   }
   o.nodes.push(node);
}

//==========================================================
// <T>创建一个新的节点,并把这个节点放到父节点里。</T>
//
// @method
// @param node:node:TNode 要添加的节点
// @param attrs:attrs:String 节点的属性名称数组
// @see RClass.isClass
// @see TNode.set
// @return TNode 返回新建的节点
//==========================================================
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

//==========================================================
// <T>判断时候含有子结点。<T>
//
// @method
// @return Boolean
//==========================================================
function TNode_isEmpty(){
   return this.nodes ? this.nodes.isEmpty() : null;
}

//==========================================================
// <T>	根据节点名称和属性查找对应节点。</T>
//
// @method
// @param name:name:String 要添加的节点
// @param attrs:attrs:String 节点的属性名称数组
// @see RClass.isClass
// @return TNode 返回查找到的节点
//==========================================================
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
 
//==========================================================
// <T>构建xml。</T>
//
// @param s:string:String 字符串对象
// @return String 构建成xml格式的字符串
//==========================================================
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

//==========================================================
// <T>将构建成xml格式的字符串对象转换为字符串。</T>
//
// @return String 字符串
//==========================================================
function TNode_toString(){
   return this.xml().toString();
}

//==========================================================
// <T>获取指定节点的内部信息。</T>
//
// @param dump:dump:String 输出字符串
// @param node:node:TNode  指定节点
// @param space:space:String 间隔空间
// @return String 调试信息
//==========================================================
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

//==========================================================
//<T>获取指定节点的内部信息。</T>
//
// @param dump:dump:String 输出字符串
// @param space:space:String 间隔空间
// @return String 调试信息
//==========================================================
function TNode_dump(d, space){
   d = RString.nvlStr(d);
   return this.innerDump(d, this, space);
}

//==========================================================
// <T>根据指定的属性名称和属性值查找节点。</T>
//
// @param name:name:String 属性名称
// @param value:value:String 属性值
// @return TNode 对应的节点
//==========================================================
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