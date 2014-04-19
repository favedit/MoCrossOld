package org.mo.core.parser.java;

import org.mo.com.lang.FDictionary;
import org.mo.com.lang.FString;

public class FJavaMethod
{
   private String _name;

   private String _returnType;

   private final FDictionary<FJavaParameter> _parameters = new FDictionary<FJavaParameter>(FJavaParameter.class);

   public FJavaMethod(){
   }

   public FDictionary<FJavaParameter> parameters(){
      return _parameters;
   }

   public String returnType(){
      return _returnType;
   }

   public void setReturnType(String returnType){
      _returnType = returnType;
   }

   public FString dump(){
      FString dump = new FString();
      dump.append(_returnType);
      dump.append(' ');
      dump.append(_name);
      dump.append('(');
      if(!_parameters.isEmpty()){
         FJavaParameter parameter;
         int count = _parameters.count();
         for(int n = 0; n < count; n++){
            parameter = _parameters.value(n);
            if(n != 0){
               dump.append(", ");
            }
            dump.append(parameter.dump());
         }
      }
      dump.append(')');
      return dump;
   }

   public String name(){
      return _name;
   }

   public void setName(String name){
      this._name = name;
   }
}
