using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using MO.Common.Content;
using System.Windows.Forms;

namespace MO.Core.Forms.Device
{
   public class RKeybord
   {
      protected static bool _pressed;

      protected static EKeyStatus[] _status = new EKeyStatus[255]; 
      
      //protected static var _event:FKeyEvent = new FKeyEvent();
      
      //protected static var _lsnsKeyDown:FListeners = new FListeners();
      
      //============================================================
      //public static function link(p:Stage):void{
      //   p.addEventListener(KeyboardEvent.KEY_DOWN, onKeyDown);
      //   p.addEventListener(KeyboardEvent.KEY_UP, onKeyUp);
      //}
      
      //============================================================
      public static bool IsPressed(){
         return _pressed;
      }

      //============================================================
      public static bool TestPressed(){
         for(int n = _status.Length - 1; n >= 0; n--){
            if(EKeyStatus.Pressed == _status[n]){
               return true;
            }
         }
         return false;
      }

      //============================================================
      public static bool IsPressedKey(int keyCode){
         return (EKeyStatus.Pressed == _status[keyCode]);
      }
      
      /*//============================================================
      public static bool isPressedLeft(){
         return (STATUS_PRESSED == status[PAD_LEFT]);
      }
      
      //============================================================
      public static bool isPressedUp(){
         return (STATUS_PRESSED == status[PAD_UP]);
      }
      
      //============================================================
      public static bool isPressedRight(){
         return (STATUS_PRESSED == status[PAD_RIGHT]);
      }
      
      //============================================================
      public static function isPressedDown(){
         return (STATUS_PRESSED == status[PAD_DOWN]);
      }*/
      
      //============================================================
      public static void ProcessDown(int keyCode){
         _status[keyCode] = EKeyStatus.Pressed;
         //_event.keyCode = keyCode;
         //_lsnsKeyDown.process(_event); 
         _pressed = true;
      }
      
      //============================================================
      public static void ProcessUp(int keyCode){
         _status[keyCode] = EKeyStatus.Unpressed;
         _pressed = TestPressed();
      }
      
      //============================================================
      //public static function get lsnsKeyDown():FListeners{
      //   return _lsnsKeyDown;
      //}
      
      //============================================================
      // <T>按键落下事件。</T>
      //============================================================
      //public static function onKeyDown(event:KeyboardEvent):void{
      //   processDown(event.keyCode);
      //}
      
      //============================================================
      // <T>按键抬起事件。</T>
      //============================================================
      //public static function onKeyUp(event:KeyboardEvent):void{
      //   processUp(event.keyCode);
      //}
      
      //============================================================
      public static void Construct(){
      }
      
      //============================================================
      public static void LoadConfig(FXmlNode xconfig){
      }
      
      //============================================================
      public static void Setup(){
      }
      
      //============================================================
      public static void Reset(){
         for(int n = _status.Length - 1; n >= 0; n--){
            _status[n] = EKeyStatus.Unpressed;
         }
      }
   }
}