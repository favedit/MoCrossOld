#include "MoCrCsv.h"

MO_NAMESPACE_BEGIN

//============================================================
TBool FCsvLine::WrapedByRefer(TCharC* pValue){
   TString refer = pValue;
   TInt length = refer.Length();
   return (refer[0] == '"' && refer[length - 1] == '"');
}

//============================================================
void FCsvLine::SinglePairRefer(TCharC* pValue, TString& result){
   TString refer = pValue;
   TInt length = refer.Length();
   TInt pos = 0;
   while(pos < length){
      TChar cur = refer[pos];
      if(cur == '"' && pos < length - 1){
         if(refer[pos + 1] == '"'){
            ++pos;
         }
      }
      result.Append(cur);
      ++pos;
   }
}

//============================================================
// <T>构造一行数据。</T>
//============================================================
FCsvLine::FCsvLine(){
   _pHeads = NULL;
   _pCells = MO_CREATE(FStrings); 
}
//============================================================
// <T>构造一行数据。</T>
//
// @param pHeads 表头指针。
//============================================================
FCsvLine::FCsvLine(FCsvHeads* pHeads){
   _pHeads = pHeads;
   _pCells = MO_CREATE(FStrings); 
}
//============================================================
// <T>析构一行数据。</T>
FCsvLine::~FCsvLine(){ 
   MO_DELETE(_pCells);
}

//============================================================
// <T>将一行保存至文件。</T>
//
// @param out 文件输出流。
//============================================================
void FCsvLine::Store(TDataOutput& out){
   TString csvCell;
   TString csvLine;
   TInt count = _pCells->Count();
   for(TInt n = 0; n < count; n++){
      csvCell.Clear();
      TString tempCell;
      TStringRefer cell = _pCells->Get(n);
      TInt cellLength = cell.Length();
      // 不允许空的格
      MO_ASSERT( 0 != cellLength);
      // 处理双引号
      TInt start = 0;
      TInt index = cell.IndexOf('"');
      if(ENotFound != index){
         while(ENotFound != index){
            index++;
            tempCell.Append(cell.SubStrC(start, index));
            // 不以引号开头或结尾
            if(index != 1 && index != cellLength){
               tempCell.Append('"');
            }else{
               tempCell.Append('"');
               tempCell.Append('"');
            }
            start = index;
            if(start < cellLength){
               index = cell.IndexOf('"', start);
            }else{
               break;
            }
         }
         // 拷贝余下内容
         if(start < cellLength - 1){
            tempCell.Append(cell.SubStrC(start, cell.Length()));
         }
      }else{
         tempCell = cell;
      }
      // 处理逗号
      TInt tempCellLen = tempCell.Length();
      if(tempCell.Contains(',')){
         // 不以“开头
         if(tempCell[0] != '"'){
            csvCell.Append('"');
         }
         csvCell.Append(tempCell);
         // 不以“结尾
         if(tempCell[tempCellLen  -1] != '"'){
            csvCell.Append('"');
         }
      }else{
         csvCell = tempCell;
      }
      // 处理换行符
      TInt csvCellLen = csvCell.Length();
      TInt lfStart = 0;
      TInt lfIndex = csvCell.IndexOf('\n');
      if(ENotFound != lfIndex){
         while(ENotFound != lfIndex){
            //  拷贝换行符前面的内容
            if(lfStart < lfIndex){
               TString sub = csvCell.SubStrC(lfStart, lfIndex);
               csvLine.Append(sub);
            }
            csvLine.Append('\\');
            csvLine.Append('n');
            // 跳过换行符
            lfIndex++;
            lfStart = lfIndex;
            if(lfStart < csvCellLen){
               lfIndex = csvCell.IndexOf('\n', lfStart);
            }else{
               break;
            }  
         }
         if(lfStart < csvCellLen - 1){
            TString sub = csvCell.SubStrC(lfStart, csvCell.Length());
            csvLine.Append(sub);
         }
      }else{
         csvLine.Append(csvCell);
      }
      // 一格格式化结束
      if((count - 1) == n){
         csvLine.Append('\n');
      }else{
         csvLine.Append(',');
      }  
   }
   out.Write((TCharC*)csvLine, csvLine.Length());
}

//============================================================
// <T>解析读入的一行。</T>
//
// @param pLine 读入的一行。
//============================================================
void FCsvLine::Parse(TCharC* pLine){
   TStringRefer line = pLine;
   TInt lineLength = line.Length();
   TInt cellStart, cellEnd, pos, dummy;
   cellStart = cellEnd = pos = dummy = 0;
   while(pos < lineLength){
      TChar cur = pLine[pos];
      if(cur == '"'){
         dummy++;
      }else if(cur == ','){
         TInt dummyTemp = dummy % 2;
         if(0 == dummyTemp){
            dummy = 0;
            cellEnd = pos;
            if(cellEnd == cellStart){
               _pCells->Push(TC(""));
            }else{
               TString cellText = line.SubStrC(cellStart, cellEnd);
               TInt cellTextLen = cellText.Length();
               TString cell;
               if(WrapedByRefer(cellText)){
                  TString tempCell = cellText.SubStrC(1, cellTextLen - 1);
                  SinglePairRefer(tempCell, cell);
               }else{
                  SinglePairRefer(cellText, cell);
               }
               _pCells->Push(cell);
            }
            cellStart = cellEnd + 1;
         }
      }else if(cur == '\n'){
         cellEnd = pos;
         dummy %= 2;
         MO_ASSERT(0 == dummy);
         if(cellEnd == cellStart){
            _pCells->Push(TC(""));
         }else{
            TString cellText = line.SubStrC(cellStart, cellEnd);
            TInt cellTextLen = cellText.Length();
            TString cell;
            if(WrapedByRefer(cellText)){
               TString tempCell = cellText.SubStrC(1, cellTextLen - 1);
               SinglePairRefer(tempCell, cell);
            }else{
               SinglePairRefer(cellText, cell);
            }
            _pCells->Push(cell);
         }
      }     
      pos++;
   }
}

//============================================================
// <T>获得这一行数据的格数。</T>
//
// @return 这一行数据的格数。
//============================================================
TInt FCsvLine::Count(){
   return _pCells->Count();
}

//============================================================
// <T>找到列的序号。</T>
//
// @param pName 列的名称。
// @return 列的序号。
void FCsvLine::SetHeads(FCsvHeads* pHeads){
   _pHeads = pHeads;
}

//============================================================
// <T>获得指定列,列序号超出列数时例外。</T>
//
// @param index 列的序号。
// @return 列的值。
//============================================================
TCharC* FCsvLine::At(TInt index){
   return _pCells->Get(index);
}

//============================================================
// <T>获得指定列，列序号超出列数时返回所给的默认值。</T>
//
// @param index 列的序号。
// @return 列的值。
//============================================================
TCharC* FCsvLine::At(TInt index, TCharC* pValue){
   TInt count = _pCells->Count();
   if(index < count){
      return _pCells->Get(index);
   }
   return pValue;
}

//============================================================
// <T>获得指定列,列序号超出列数时例外。</T>
//
// @param index 列的序号。
// @return 列的值。
//============================================================
TBool FCsvLine::AtBoolean(TInt index){
   TCharC* pCell = _pCells->Get(index);
   return RBool::ParseNvl(pCell);
}

//============================================================
// <T>获得指定列，列序号超出列数时返回所给的默认值。</T>
//
// @param index 列的序号。
// @param default 默认值。
// @return 列的值。
//============================================================
TBool FCsvLine::AtBoolean(TInt index, TBool value){
   TInt count = _pCells->Count();
   if(index < count){
      TBool result = RBool::ParseNvl(_pCells->Get(index));
      return result;
   }
   return value;
}

//============================================================
// <T>获得指定列,列序号超出列数时例外。</T>
//
// @param index 列的序号。
// @return 列的值。
//============================================================
TInt FCsvLine::AtInt(TInt index){
   TCharC* pValue = _pCells->Get(index);
   TInt result = RInt::ParseNvl(pValue);
   return result;
}

//============================================================
// <T>获得指定列，列序号超出列数时返回所给的默认值。</T>
//
// @param index 列的序号。
// @param default 默认值。
// @return 列的值。
//============================================================
TInt FCsvLine::AtInt(TInt index, TInt value){
   TInt count = _pCells->Count();
   if(index < count){
      TInt result = RInt::ParseNvl(_pCells->Get(index));
      return result;
   }
   return value;
}

//============================================================
// <T>获得指定列,列序号超出列数时例外。</T>
//
// @param index 列的序号。
// @return 列的值。
//============================================================
TFloat FCsvLine::AtFloat(TInt index){
   TCharC* pValue = _pCells->Get(index);
   TFloat result = RFloat::ParseNvl(pValue);
   return result;
}

//============================================================
// <T>获得指定列，列序号超出列数时返回所给的默认值。</T>
//
// @param index 列的序号。
// @param default 默认值。
// @return 列的值。
//============================================================
TFloat FCsvLine::AtFloat(TInt index, TFloat value){
   TInt count = _pCells->Count();
   if(index < count){
      TCharC* pValue = _pCells->Get(index);
      TFloat result = RFloat::ParseNvl(pValue);
      return result;
   }
   return value;
}

//============================================================
// <T>根据列名获取某列上的数据。</T>
//
// @param pName 列的名称。
// @return 该列上的数据。
//============================================================
TCharC* FCsvLine::Get(TCharC* pName){
   TInt index = _pHeads->IndexOf(pName);
   return _pCells->Get(index);
}

//============================================================
// <T>根据列名获取某列上的数据。</T>
//
// @param pName 列的名字。
// @param default 没有指定名称列时返回的值。
// @return 该列上的数据。
//============================================================
TCharC* FCsvLine::Get(TCharC* pName, TCharC* pValue){
   TInt index = _pHeads->IndexOf(pName);
   return At(index, pValue);
}

//============================================================
// <T>根据列名获取某列上的数据。</T>
//
// @param pName 列的名称。
// @return 该列上的数据。
//============================================================
TBool FCsvLine::GetBoolean(TCharC* pName){
   TInt index = _pHeads->IndexOf(pName);
   return AtBoolean(index);
}

//============================================================
// <T>根据列名获取某列上的数据。</T>
//
// @param pName 列的名字。
// @param default 没有指定名称列时返回的值。
// @return 该列上的数据。
//============================================================
TBool FCsvLine::GetBoolean(TCharC* pName, TBool value){
   TInt index = _pHeads->IndexOf(pName);
   return AtBoolean(index, value);
}


//============================================================
// <T>根据列名获取某列上的数据。</T>
//
// @param pName 列的名称。
// @return 该列上的数据。
//============================================================
TInt FCsvLine::GetInt(TCharC* pName){
   TInt index = _pHeads->IndexOf(pName);
   return AtInt(index);
}

//============================================================
// <T>根据列名获取某列上的数据。</T>
//
// @param pName 列的名字。
// @param default 没有指定名称列时返回的值。
// @return 该列上的数据。
//============================================================
TInt FCsvLine::GetInt(TCharC* pName, TInt value){
   TInt index = _pHeads->IndexOf(pName);
   return AtInt(index, value);
}

//============================================================
// <T>根据列名获取某列上的数据。</T>
//
// @param pName 列的名称。
// @return 该列上的数据。
//============================================================
TFloat FCsvLine::GetFloat(TCharC* pName){
   TInt index = _pHeads->IndexOf(pName);
   return AtFloat(index);
}

//============================================================
// <T>根据列名获取某列上的数据。</T>
//
// @param pName 列的名字。
// @param default 没有指定名称列时返回的值。
// @return 该列上的数据。
//============================================================
TFloat FCsvLine::GetFloat(TCharC* pName, TFloat value){
   TInt index = _pHeads->IndexOf(pName);
   return AtFloat(index, value);
}

//============================================================
// <T>设置该行某列上的数据。</T>
//
// @param index 列的索引。
// @param pValue 该项数据的值。
//============================================================
void FCsvLine::Set(TInt index, TCharC* pValue){
   _pCells->Set(index, pValue);
}

//============================================================
// <T>设置该行某列上的数据。</T>
//
// @param index 列的索引。
// @param value 该项数据的值。
//============================================================
void FCsvLine::SetBoolean(TInt index, TBool value){
   TCharC* pValue = RBool::ToString(value);
   _pCells->Set(index, pValue);
}

//============================================================
// <T>设置该行某列上的数据。</T>
//
// @param index 列的索引。
// @param value 该项数据的值。
//============================================================
void FCsvLine::SetInt(TInt index, TInt value){
   TChar buffer[16];
   RInt::ToString(value, buffer, 16);
   _pCells->Set(index, buffer);
}

//============================================================
// <T>设置该行某列上的数据。</T>
//
// @param index 列的索引。
// @param value 该项数据的值。
//============================================================
void FCsvLine::SetFloat(TInt index, TFloat value){
   TChar buffer[32];
   RFloat::ToString(value, buffer, 32);
   _pCells->Set(index, buffer);
}

//============================================================
// <T>添加指定列上的一项数据。</T>
//
// @param pName 列的名称。
// @param value 格子上的值。
//============================================================
void FCsvLine::Put(TCharC* pName, TCharC* pValue){
   TInt index = _pHeads->IndexOf(pName);
   _pCells->Set(index, pValue);
}

//============================================================
// <T>添加指定列上的一项数据。</T>
//
// @param pName 列的名称。
// @param value 格子上的值。
//============================================================
void FCsvLine::PutBoolean(TCharC* pName, TBool value){
   TInt index = _pHeads->IndexOf(pName);
   SetBoolean(index, value);
}

//============================================================
// <T>添加指定列上的一项数据。</T>
//
// @param pName 列的名称。
// @param value 格子上的值。
//============================================================
void FCsvLine::PutInt(TCharC* pName, TInt value){
   TInt index = _pHeads->IndexOf(pName);
   SetInt(index, value);
}

//============================================================
// <T>添加指定列上的一项数据。</T>
//
// @param pName 列的名称。
// @param value 格子上的值。
//============================================================
void FCsvLine::PutFloat(TCharC* pName, TFloat value){
   TInt index = _pHeads->IndexOf(pName);
   SetFloat(index, value);
}

//============================================================
// <T>追加到该行一项数据。</T>
//
// @param value 该项数据的值。
//============================================================
void FCsvLine::Push(TCharC* pValue){
   _pCells->Push(pValue);
}

//============================================================
// <T>追加到该行一项数据。</T>
//
// @param value 该项数据的值。
//============================================================
void FCsvLine::PushBoolean(TBool value){
   _pCells->Push(RBool::ToString(value));
}

//============================================================
// <T>追加到该行一项数据。</T>
//
// @param value 该项数据的值。
//============================================================
void FCsvLine::PushInt(TInt value){
   TChar buffer[16];
   RInt::ToString(value, buffer, 16);
   _pCells->Push(buffer);
}

//============================================================
// <T>追加到该行一项数据。</T>
//
// @param value 该项数据的值。
//============================================================
void FCsvLine::PushFloat(TFloat value){
   TChar buffer[32];
   RFloat::ToString(value, buffer, 32);
   _pCells->Push(buffer);
}

MO_NAMESPACE_END
