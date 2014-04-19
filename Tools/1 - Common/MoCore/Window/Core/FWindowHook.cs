using System;
using System.Collections.Generic;
using System.Text;
using System.Reflection;
using System.Runtime.InteropServices;
using MO.Core.Window.Api;

namespace MO.Core.Window.Core.Hook {

   public abstract class FWindowHook {

      private bool _installed = false;

      protected EHookType _type;

      private EHookScope _scope = EHookScope.Thread;

      private IntPtr _nextHookPtr;

      private RUser32.HWindowsHook _process;

      //private FProcessInfo _processInfo = new 

      public bool Installed {
         get {
            return _installed;
         }
      }

      public EHookType Type {
         get { return _type; }
      }

      public EHookScope Scope {
         get { return _scope; }
         set { _scope = value; }
      }

      public abstract bool Process(int code, IntPtr wparam, IntPtr lparam);

      // 0: 令待完成的动作继续完成
      // 1: 则取消原本要完成的动作
      protected IntPtr HookProcess(int code, IntPtr wparam, IntPtr lparam) {
         if (code >= 0) {
            if (Process(code, wparam, lparam)) {
               return (IntPtr)1;
            }
         }
         return RUser32.CallNextHookEx(_nextHookPtr, code, wparam, lparam);
         /*if (Process(code, wparam, lparam)) {
            return (IntPtr)1;
         }*/
         //return IntPtr.Zero;
      }

      public bool Install() {
         if (_installed) {
            return false;
         }
         // Process
         if (_process == null) {
            _process = new RUser32.HWindowsHook(HookProcess);
         }
         // Scope
         if (_scope == EHookScope.Thread) {
            _nextHookPtr = RUser32.SetWindowsHookEx((int)_type, _process, IntPtr.Zero, RKernel32.GetCurrentThreadId());
         }
         if (_scope == EHookScope.Global) {
            //IntPtr ptr = Marshal.GetHINSTANCE(Assembly.GetExecutingAssembly().GetModules()[0]);
            string modulename = global::System.Diagnostics.Process.GetCurrentProcess().MainModule.ModuleName;
            IntPtr ptr = RKernel32.GetModuleHandle(modulename);
            _nextHookPtr = RUser32.SetWindowsHookEx((int)_type, _process, ptr, 0);
         }
         _installed = true;
         return true;
      }

      public bool Uninstall() {
         if (!_installed) {
            return false;
         }
         RUser32.UnhookWindowsHookEx(_nextHookPtr);
         _nextHookPtr = IntPtr.Zero;
         _installed = false;
         return true;
      }

   }

}
