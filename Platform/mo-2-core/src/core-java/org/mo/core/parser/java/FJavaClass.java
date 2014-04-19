package org.mo.core.parser.java;

import org.mo.com.lang.FDictionary;
import org.mo.com.lang.RDump;
import org.mo.com.lang.generic.TDumpInfo;

public class FJavaClass
{
   private final FDictionary<FJavaField> _fields = new FDictionary<FJavaField>(FJavaField.class);

   private boolean _isInterface;

   private final FDictionary<FJavaMethod> _methods = new FDictionary<FJavaMethod>(FJavaMethod.class);

   private String _namespace;

   private String _shortName;

   public FJavaClass(){
   }

   public TDumpInfo dump(){
      return dump(new TDumpInfo(this));
   }

   public TDumpInfo dump(TDumpInfo info){
      RDump.dump(info, this).appendLine();
      info.appendLine(RDump.LINE_L2);
      info.append("Package: ");
      info.append(_namespace);
      info.appendLine();
      info.append("Short:   ");
      info.append(_shortName);
      info.appendLine();
      info.append("Name:    ");
      info.append(name());
      info.appendLine();
      info.appendLine(RDump.LINE_L2);
      if(!_methods.isEmpty()){
         FJavaMethod method;
         int count = _methods.count();
         for(int n = 0; n < count; n++){
            method = _methods.value(n);
            info.append("Method:  ");
            info.append(method.dump());
            info.appendLine();
         }
      }
      return info;
   }

   public FDictionary<FJavaField> fields(){
      return _fields;
   }

   public boolean isInterface(){
      return _isInterface;
   }

   public FDictionary<FJavaMethod> methods(){
      return _methods;
   }

   public String name(){
      return _namespace + "." + _shortName;
   }

   public String namespace(){
      return _namespace;
   }

   public void setInterface(boolean isInterface){
      _isInterface = isInterface;
   }

   public void setNamespace(String namespace){
      _namespace = namespace;
   }

   public void setShortName(String name){
      _shortName = name;
   }
}
