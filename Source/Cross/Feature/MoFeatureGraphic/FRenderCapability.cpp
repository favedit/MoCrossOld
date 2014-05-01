#include "MoFgRender.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FRenderCapability, FInstance);

//============================================================
// <T>构造纹理。</T>
//============================================================
FRenderCapability::FRenderCapability(){
   _vertexCountLimit = 65536;
   _vertexConstLimit = 0;
   _vertexAttributeLimit = 0;
   _vertexTemporaryLimit = 0;
   _vertexOpcodeLimit = 0;
   _fragmentConstLimit = 0;
   _fragmentTemporaryLimit = 0;
   _fragmentOpcodeLimit = 0;
   _varyingLimit = 0;
   _samplerLimit = 0;
   _samplerSizeLimit = 0;
   _renderTargetLimit = 0;
}

//============================================================
// <T>析构纹理。</T>
//============================================================
FRenderCapability::~FRenderCapability(){
}

//============================================================
// <T>跟踪数据。</T>
//============================================================
void FRenderCapability::Track(){
   MO_INFO("Render capability. (vertex_const=%d, vertex_attribute=%d)", _vertexConstLimit, _vertexAttributeLimit);
   MO_INFO("Render capability. (varying=%d)", _varyingLimit);
   MO_INFO("Render capability. (fragment_const=%d)", _fragmentConstLimit);
   MO_INFO("Render capability. (sampler=%d, sampler_size=%d)", _samplerLimit, _samplerSizeLimit);
   MO_INFO("Render capability. (render_target=%d)", _renderTargetLimit);
}

MO_NAMESPACE_END
