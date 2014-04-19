//==========================================================
// 把数据list加载到TDataset结构里
//
// @reference
// @author maochunyang
// @version 1.0.1
//==========================================================
var RDataset = new function(){
   var o = this;
   // Const
   o.DATASET = 'Dataset';
   o.ROW = 'Row';
   o.ROW_STATUS = '_os';
   // Method
   o.make = RDataset_make;
   // Construct
   RMemory.register('RDataset', o);
   return o;
}

//==========================================================
// 构造TDataset
//
// @method
// @param d:dataset:???
// @see TDataset.loadNode
// @return TDataset
//==========================================================
function RDataset_make(d){
   var r = d;
   if(RXml.isNode(d)){
      r = new TDataset();
      r.loadNode(d);
   }
   return r;
}
