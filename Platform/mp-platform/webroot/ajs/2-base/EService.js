/**************************************************************
 * Service类型枚举
 *
 * @enum
 * @author maochunyang
 * @version 1.0.1
 **************************************************************/
function EServiceFace(){
   var o = this;
   // Attribute
   o.Dataset    = 'database.dataset';
   o.List       = 'design.list';
   o.WebForm    = 'design.webform';
   o.Translate  = 'design.translate';
   o.WebDataset = 'logic.dataset';
   return o;
}
var EService = new EServiceFace();
// ------------------------------------------------------------
