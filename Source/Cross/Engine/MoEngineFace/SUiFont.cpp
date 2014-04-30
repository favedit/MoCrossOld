#include "MoEfDisplay.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造交界面字体。</T>
//============================================================
SUiFont::SUiFont(){
   color = 0xFF000000;
   size = 9;
   fix = EFalse;
   bold = EFalse;
   italic = EFalse;
   strikeout = EFalse;
   underline = EFalse;
}

//============================================================
// <T>从输入流反序列化数据内容。</T>
//
// @param pInput 输入流
// @return 处理结果
//============================================================
TResult SUiFont::Unserialize(IDataInput* pInput){
   fontName.Unserialize(pInput);
   color = pInput->ReadInt32();
   size = pInput->ReadUint8();
   bold = pInput->ReadBool();
   italic = pInput->ReadBool();
   strikeout = pInput->ReadBool();
   underline = pInput->ReadBool();
   return ESuccess;
}

//============================================================
// <T>保存信息。</T>
//
// @param info 信息
//============================================================
void SUiFont::SaveInfo(SFontInfo& info){
   info.fontNamePtr = fontName;
   info.color = color;
   info.size = size;
   info.fix = fix;
   info.bold = bold;
   info.italic = italic;
   info.underline = underline;
   info.strikeout = strikeout;
}

MO_NAMESPACE_END
