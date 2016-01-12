#include "MoCrCsv.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>创建表的行数据。</T>
//============================================================
FCsvLines::FCsvLines(FCsvHeads* pHeads){
   _pHeads = pHeads;
   _pLines = MO_CREATE(FCsvLineVector);
}

//============================================================
// <T>析构表的行数据。</T>
//============================================================
FCsvLines::~FCsvLines(){
   TInt count = _pLines->Count();
   for(TInt n =0; n < count; n++){
      FCsvLine* pLine = _pLines->Get(n);
      MO_DELETE(pLine);
   }
   MO_DELETE(_pLines);
}

//============================================================
// <T>获取表的行数。</T>
//
// @return 表的数据行数。
//============================================================
TInt FCsvLines::Count(){
   return _pLines->Count();
}

//============================================================
// <T>获取指定行序号的行数据。</T>
//
// @param index 列的名称。
// @return 行数据。
//============================================================
FCsvLine* FCsvLines::Get(TInt index){
   return _pLines->Get(index);
}

//============================================================
// <T>加入一行新数据，可指定行号，默认为末尾。</T>
//
// @param index 行号。
// @return  新加入的行数据。
//============================================================
FCsvLine* FCsvLines::Insert(TInt index){
   FCsvLine* pLine = MO_CREATE(FCsvLine, _pHeads);
   if(-1 == index){
      _pLines->Push(pLine);
   }else{
      _pLines->Insert(index, pLine);
   }
   return pLine;
}

//============================================================
// <T>将行数据写入文件。</T>
//
// @param out 文件流。
//============================================================
void FCsvLines::Store(TDataOutput& out){
   TString dataBegin(TC("@data\n"));
   out.Write((TCharC*)dataBegin, dataBegin.Length());
   TInt count = _pLines->Count();
   for(TInt n =0; n < count; n++){
      FCsvLine* pLine = _pLines->Get(n);
      pLine->Store(out);
   }
   TString check;
   check.AppendFormat(TC("@check data.row=%d\n"), count);
   out.Write((TCharC*)check, check.Length());
}

//============================================================
// <T>解析读入的行数据。</T>
//
// @param pValue 所有的行数据字符串。
//============================================================
void FCsvLines::Parse(TCharC* pValue){
   TStringRefer temp = pValue;
   TInt linesEnd = temp.Find(TC("@check data.row="));
   MO_ASSERT(ENotFound != linesEnd);
   TInt rowCountStart = temp.IndexOf('=', linesEnd);
   TInt rowCountEnd = temp.IndexOf('\n', rowCountStart);
   TFsName rowCountString = temp.SubStrC(rowCountStart + 1, rowCountEnd);
   TInt rowCount = RInt::Parse(rowCountString);
   TStringRefer lines = temp.SubPtrC(0, linesEnd);
   TInt linesLenth = lines.Length();
   TInt lineStart = 0;
   TInt count = 0;
   while(lineStart < linesLenth){
      TInt lineEnd = lines.IndexOf('\n', lineStart);
      FCsvLine* pLine = MO_CREATE(FCsvLine, _pHeads);
      _pLines->Push(pLine);
      TString onrLine = lines.SubStrC(lineStart, lineEnd + 1);
      pLine->Parse(onrLine);
      lineStart = lineEnd + 1;
      ++count;
   }
   MO_ASSERT(count == rowCount);
}

MO_NAMESPACE_END
