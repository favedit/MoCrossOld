/**************************************************************
 * 响应动作枚举类型
 *
 * @enum
 * @author maochunyang
 * @version 1.0.1
 **************************************************************/
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
