#pragma once

#include <MoCommon.h>

//============================================================
/// @define 导出定义
#ifdef _MO_3E_EXPORTS
#define MO_3E_DECLARE MO_EXPORT
#else
#define MO_3E_DECLARE MO_IMPORT
#endif

//============================================================
#define MO_3E_LIBRARY         TC("Mo3ds2014Exporter.dll")

//============================================================
#define MO_3E_LIB_DESCRIPTION TC("M3X File Exporter (microbject)")
#define MO_3E_CATEGORY        TC("MO Scene Export")
#define MO_3E_NAME            TC("Mo3dsExporter")
#define MO_3E_EXT             TC("m3x")
#define MO_3E_CLASS           Class_ID(0x7D63621D, 0x64CD0896)
#define MO_3E_DESC_SHORT      TC("3DS Module Exporter")
#define MO_3E_DESC_LONG       TC("Microbject 3DS Max Exporter")
#define MO_3E_AUTHOR          TC("MAOCY")
#define MO_3E_COPYRIGHT       TC("Copyright 2014 Microbject")
#define MO_3E_EMPTY           TC("")
#define MO_3E_VERSION         1311
