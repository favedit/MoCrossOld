using System;
using System.Collections.Generic;
using System.Text;
using System.CodeDom.Compiler;
using Microsoft.CSharp;

namespace MO.Core.Compiler {
   
   public class Class1 {

      protected CompilerParameters _parameters;

      /*private void createCompiler(string strLanguage, bool debugMode, string strAssemblyFileName) {
         _parameters = new CompilerParameters();
         _parameters.OutputAssembly = System.IO.Path.Combine(System.IO.Path.GetTempPath(), strAssemblyFileName + ".dll");
         _parameters.GenerateExecutable = false;
         _parameters.GenerateInMemory = true;
         if (debugMode) {
            this._parameters.IncludeDebugInformation = true;
            this._parameters.CompilerOptions += "/define:TRACE=1 /define:DEBUG=1 ";
         } else {
            this._parameters.IncludeDebugInformation = false;
            this._parameters.CompilerOptions += "/define:TRACE=1 ";
         }

         AddReference("System.dll");
         AddReference("System.Data.dll");
         AddReference("System.Xml.dll");

         strLanguage = strLanguage.ToLower();

         if ("visualbasic" == strLanguage || "vb" == strLanguage) {
            theProvider = new Microsoft.VisualBasic.VBCodeProvider();
            if (debugMode)
               _parameters.CompilerOptions += "/debug:full /optimize- /optionexplicit+ /optionstrict+ /optioncompare:text /imports:Microsoft.VisualBasic,System,System.Collections,System.Diagnostics ";
            else
               _parameters.CompilerOptions += "/optimize /optionexplicit+ /optionstrict+ /optioncompare:text /imports:Microsoft.VisualBasic,System,System.Collections,System.Diagnostics ";
            AddReference("Microsoft.VisualBasic.dll");
         } else if ("jscript" == strLanguage || "js" == strLanguage) {
            //theProvider = new JScriptCodeProvider();
            AddReference("Microsoft.JScript.dll");
         } else if ("csharp" == strLanguage || "cs" == strLanguage || "c#" == strLanguage) {
            theProvider = new CSharpCodeProvider();
            if (!debugMode)
               _parameters.CompilerOptions += "/optimize ";
         }
            //            else if("jsharp" == strLanguage || "vj" == strLanguage || "j#" == strLanguage) 
            //            { 
            //                theProvider = new Microsoft.VJSharp.VJSharpCodeProvider(); 
            //                if(!debugMode) 
            //                    theParameters.CompilerOptions += "/optimize "; 
            //            } 
       else
            throw new System.Exception("No suport script");
      }

      public void AddReference(string __strAssemblyName) {
         _parameters.ReferencedAssemblies.Add(__strAssemblyName);
      }

      public bool Compile() {
         this.theCompilerInfo = "";
         this.isCompiled = false;
         this.theCompiledAssembly = null;
         // Only .NET 2.0
         this.theCompilerResults =this.theProvider.CompileAssemblyFromSource(this.theParameters, this.SourceText);
         // Only .Net 1.1
         // this.theCompilerResults = this.theProvider.CreateCompiler().CompileAssemblyFromSource(this.theParameters, this.SourceText);

         if (this.theCompilerResults.NativeCompilerReturnValue == 0) {
            this.isCompiled = true;
            this.theCompiledAssembly = this.theCompilerResults.CompiledAssembly;
         }

         System.Text.StringBuilder compilerInfo = new System.Text.StringBuilder();

         foreach (CompilerError err in this.theCompilerResults.Errors) {
            compilerInfo.Append(err.ToString());
            compilerInfo.Append("\r\n");
         }

         theCompilerInfo = compilerInfo.ToString();
         return isCompiled;
      }*/

   }
}
