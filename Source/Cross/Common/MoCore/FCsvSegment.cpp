#include "MoCrCsv.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>创建一个标表段。</T>
//============================================================
FCsvSegment::FCsvSegment(){
   _name = TC("default");
   _pHeads = MO_CREATE(FCsvHeads);
   _pLines = MO_CREATE(FCsvLines, _pHeads);
   _pFooters = MO_CREATE(FCsvFooters);
}

//============================================================
// <T>销毁一个标表段。</T>
//============================================================
FCsvSegment::~FCsvSegment(){
   MO_DELETE(_pHeads);
   MO_DELETE(_pLines);
   MO_DELETE(_pFooters);
}

//============================================================
// <T>获取表段的名字。</T>
//
// @return  表段的名字。
//============================================================
TCharC* FCsvSegment::Name(){ 
   return (TCharC*)_name;
}

//============================================================
// <T>获取表段头部。</T>
//
// @return  表段头部。
//============================================================
FCsvHeads* FCsvSegment::Heads() {
   return _pHeads;
}

//============================================================
// <T>获取 标段的行数据。</T>
//
// @return  标段的行数据。
//============================================================
FCsvLines* FCsvSegment::Lines(){
   return _pLines;
}

//============================================================
// <T>获取表段的尾项。</T>
//
// @return  表段的尾项。
//============================================================
FCsvFooters* FCsvSegment::Footers(){
   return _pFooters;
}

//============================================================
// <T>加一项表段头。</T>
//
// @return  表段头。
//============================================================
FCsvHead* FCsvSegment::InsertHead(TCharC* pName){
   FCsvHead* pHead = _pHeads->Insert(pName);
   return pHead;
}

//============================================================
// <T>加一行新的数据。</T>
//
// @return  一行新的数据。
//============================================================
FCsvLine* FCsvSegment::InsertLine(){
   FCsvLine* pLine = _pLines->Insert();
   return pLine;
}

//============================================================
// <T>加一项表段尾。</T>
//
// @return  表段尾。
//============================================================
FCsvFooter* FCsvSegment::InsertFooter(TCharC* pName){
   return _pFooters->Insert(pName);
}

//============================================================
// <T>设置表段的名字。</T>
//
// @param  pName 表段的名字。
//============================================================
void FCsvSegment::SetName(TCharC* pName) { 
   _name = pName; 
}

//============================================================
// <T>将表段存入文件。</T>
//
// @param out 输出文件流。
// @param saveHead 是否保存段头部信息。
//============================================================
void FCsvSegment::Store(TDataOutput& out, TBool saveHead){
   if(saveHead){
      TString segmentBegin;
      segmentBegin.Append(TC("@segment.start "));
      segmentBegin.Append(_name);
      segmentBegin.Append('\n');
      out.Write((TCharC*)segmentBegin, segmentBegin.Length());
   }
   _pHeads->SaveToFile(out);
   _pLines->Store(out);
   _pFooters->Store(out);
   if(saveHead){
      TString segmentEnd;
      segmentEnd.Append(TC("@segment.end\n"));
      out.Write((TCharC*)segmentEnd, segmentEnd.Length());
   }
}

MO_NAMESPACE_END
