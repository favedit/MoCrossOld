#include <time.h>
#include "MoCmLanguage.h"

MO_NAMESPACE_BEGIN

//============================================================
TUint32 RRandom::_seed = 0;

//============================================================
//<T>初始化RRandom对象，产生一个种子。</T>
//============================================================
void RRandom::Initialize(){
	if (_seed == 0){
		_seed = (TUint32)time(NULL);
		srand(_seed);
	}
}

//===========================================================
//<T>获得一个随机数。</T>
//
//@return 产生的随机数。
//============================================================
TInt RRandom::Get(){
   Initialize();
   return rand();
}

//============================================================
//<T>获得指定区间内的随机数。</T>
//
//@return 产生的随机数。
//============================================================
TInt RRandom::Get(TInt min, TInt max){
   Initialize();
   if(min >= max){
      return min;
   }
   TInt range = max - min;
   return (rand() % range) + min;
}

//===========================================================
//<T>获得一个随机数。</T>
//
//@return 产生的随机数。
TInt RRandom::Generator(){
   Initialize();
   return rand();
}

//============================================================
//<T>获得指定区间内的随机数。</T>
//
//@return 产生的随机数。
//============================================================
TInt RRandom::Generator(TInt min, TInt max){
   if(min >= max){
      return min;
   }
   TInt range = max - min;
   Initialize();
   return (rand() % range) + min;
}

//===========================================================
//<T>获得一个浮点随机数。</T>
//
//@return 产生的随机数。
//============================================================
TFloat RRandom::GeneratorFloat(){
   Initialize();
   TFloat value = (TFloat)(rand() % 1000000);
   return value / 1000000;
}

//===========================================================
//<T>获得指定区间内浮点随机数。</T>
//
//@return 产生的随机数。
//============================================================
TFloat RRandom::GeneratorFloat(TFloat min, TFloat max){
   Initialize();
   if(min >= max){
      return min;
   }
   TInt minRange = (TInt)(min * 1000000.0f);
   TInt maxRange = (TInt)(max * 1000000.0f);
   TInt rand = Generator(minRange, maxRange);
   return rand / 1000000.0f;
}

MO_NAMESPACE_END
