#include "MoFgTechnique.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造渲染技术。</T>
//============================================================
FTechniqueConsole::FTechniqueConsole(){
   _pTechniques = MO_CREATE(FTechniqueCollection);
}

//============================================================
// <T>析构渲染技术。</T>
//============================================================
FTechniqueConsole::~FTechniqueConsole(){
   MO_DELETE(_pTechniques);
}

//============================================================
// <T>配置处理。</T>
//============================================================
void FTechniqueConsole::Setup(){
}

MO_NAMESPACE_END
