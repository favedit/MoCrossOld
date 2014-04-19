/**************************************************************
 * <T>可以含有子组件的容器接口</T>
 *
 * @author maochunyang
 * @version 1.0.1
 **************************************************************/
function MContainer(o){
   o = RClass.inherits(this, o);
   /// @method
   o.appendChild = RMethod.empty;
   return o;
}
