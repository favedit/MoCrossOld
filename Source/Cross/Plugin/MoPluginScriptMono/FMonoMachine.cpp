#include "MoPsmScript.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FMonoMachine, FScriptMachine);

//============================================================
// <T>构造脚本工作器。</T>
//============================================================
FMonoMachine::FMonoMachine(){
   MO_CLEAR(_pMonoDomain);
}

//============================================================
// <T>析构脚本工作器。</T>
//============================================================
FMonoMachine::~FMonoMachine(){
}

//============================================================
// <T>加载动态库。</T>
//============================================================
FMonoLibrary* FMonoMachine::OpenLibrary(TCharC* pName){
   if(_librarys.Contains(pName)){
      MO_FATAL("Library is already loaded. (library_name=%s)", pName);
      return NULL;
   }
   FMonoLibrary* pLibrary = FMonoLibrary::InstanceCreate();
   pLibrary->SetScriptMachine(this);
   pLibrary->SetName(pName);
   pLibrary->Open();
   _librarys.Set(pName, pLibrary);
   return pLibrary;
}

static MonoDomain* g_pMonoDomain = NULL;
//------------------------------------------------------------------------
static int CalculateNumber(MonoObject* pMonoObj, int value){
   MO_STATIC_INFO("Inner call: %d - %d", pMonoObj, value);
   //int a = *(int*)mono_object_unbox(pValue);
   return value + 10;
   //MonoDomain* pMonoDomain = mono_domain_get();
   //MonoObject* pResult = mono_value_box(pMonoDomain, mono_get_int32_class(), &value);
   //return pResult;
}

//============================================================
// <T>配置类工厂。</T>
//
// @return 处理结果
//============================================================
TResult FMonoMachine::SetupClassFactory(){
   _pClassFactory->Register(MO_SCRIPT_FIELD,    FMonoField::Class());
   _pClassFactory->Register(MO_SCRIPT_PROPERTY, FMonoProperty::Class());
   _pClassFactory->Register(MO_SCRIPT_METHOD,   FMonoMethod::Class());
   _pClassFactory->Register(MO_SCRIPT_CLASS,    FMonoClass::Class());
   return ESuccess;
}

//============================================================
// <T>析构脚本工作器。</T>
//
// @return 处理结果
//============================================================
TResult FMonoMachine::Setup(){
   SetupClassFactory();
   //............................................................
   //TCharC* pLibraryPath = RApplication::Instance().Parameters()->FindValue("script");
   TCharC* pLibraryPath = "E:/ZW-MoCross/Demo/Android/MpTestDemo/assets/Script/";
   mono_set_assemblies_path(pLibraryPath);
   //_pMonoDomain = mono_jit_init_version("MoTest", "v4.0.30319");
   _pMonoDomain = mono_jit_init_version("MoTest", "v2.0.50727");
   g_pMonoDomain = _pMonoDomain;

   //mono_add_internal_call("MoTest.FClassTest::CalculateNumber", CalculateNumber);


   //FMonoLibrary* pLibrary = OpenLibrary("script:MoTest.dll");
   //FMonoClass* pClass = (FMonoClass*)pLibrary->FindClass("MoTest.FClassTest");
   //FMonoInstance* pInstance = (FMonoInstance*)pClass->CreateInstance();
   //TInt fieldValue = 8;
   //pInstance->FieldSet("_testValue", &fieldValue);
   //pInstance->FieldGet("_testValue", &fieldValue);

   //TFsName data;
   //pInstance->FieldGetString("_testString", &data);

   //FMonoMethod* pMethod = (FMonoMethod*)pClass->FindMethod(":Test()");
   //MonoObject* pResultObject = NULL;
   //pMethod->Invoke(pInstance, (TAny**)&pResultObject);
   //TInt returnValue = *(int*)mono_object_unbox(pResultObject);

   //mono_add_internal_call("ScriptAPI::DoSomething", &DoSomething);
   //if(!assembly){
   //   cout<<"err"<<endl;
   //   abort();
   //}
   //// 获得映像与类
   //MonoImage* image = mono_assembly_get_image(assembly);
   // 
   return ESuccess;
}

//============================================================
// <T>关闭处理。</T>
//
// @return 处理结果
//============================================================
TResult FMonoMachine::Close(){
   if(_pMonoDomain != NULL){
      mono_jit_cleanup(_pMonoDomain);
      MO_CLEAR(_pMonoDomain);
   }
   return ESuccess;
}

MO_NAMESPACE_END
