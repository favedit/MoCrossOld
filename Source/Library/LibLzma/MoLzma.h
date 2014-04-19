//============================================================
// <T>共通定义。</T>
//============================================================
#ifndef __MO_LZMA_H__
#define __MO_LZMA_H__

#ifdef _MO_WINDOWS
#ifndef __LZMA_LIB_H
#include "LzmaLib.h"
#endif // __LZMA_LIB_H

#ifndef __LZMA_ENC_H
#include "LzmaEnc.h"
#endif // __LZMA_ENC_H

#ifndef __LZMA_DEC_H
#include "LzmaDec.h"
#endif // __LZMA_DEC_H
#endif // _MO_WINDOWS

#ifdef _MO_ANDROID
#ifndef __LZMA_DEC_H
#include "LzmaDec.h"
#endif // __LZMA_DEC_H
#endif // _MO_ANDROID

#endif // __MO_LZMA_H__
