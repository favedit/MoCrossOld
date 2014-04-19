//==========================================================
// <T>函数回调的定义类。</T>
//
// @tool
// @param w:owner:Object 调用对象
// @param p:process: Function 处理函数
// @author maocy
// @version 1.0.1
//==========================================================
function TInvoke(w, p){
   var o = this;
   // Attribute
   o.owner    = w;
   o.callback = p;
   // Method
   o.invoke   = TInvoke_invoke;
   return o;
}

//==========================================================
// <T>进行函数的调用。</T>
// <P>如果存在调用对象，则函数调用后，当前对象指向调用对象。
// 如果不存在调用对象，当前对象就指向回调类的对象。</P>
//
// @method
// @param p1:param1:Object 参数1
// @param p2:param2:Object 参数2
// @param p3:param3:Object 参数3
// @param p4:param4:Object 参数4
// @param p5:param5:Object 参数5
// @param p6:param6:Object 参数6
//==========================================================
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
