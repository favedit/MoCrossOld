package org.mo.com.lang.generic;

import java.io.UnsupportedEncodingException;
import org.mo.com.lang.FFatalError;
import org.mo.com.lang.FString;
import org.mo.com.lang.RChar;
import org.mo.com.lang.RString;

//============================================================
// <T>字符串。</T>
//============================================================
public class MString
      extends MChars
      implements
         CharSequence
{
   //============================================================
   // <T>构造字符串。</T>
   //============================================================
   public MString(){
   }

   //============================================================
   // <T>构造字符串。</T>
   //
   // @param capacity 初始容量
   //============================================================
   public MString(int capacity){
      super(capacity);
   }

   //============================================================
   // <T>构造字符串。</T>
   //
   // @param data 字节数组
   // @param charset 编码
   //============================================================
   public MString(byte[] data,
                  String charset){
      append(data, charset);
   }

   //============================================================
   // <T>构造字符串。</T>
   //
   // @param data 字符数组
   //============================================================
   public MString(char[] data){
      append(data, 0, data.length);
   }

   //============================================================
   // <T>构造字符串。</T>
   //
   // @param data 字符数组
   // @param offset 开始位置
   // @param length 数据长度
   //============================================================
   public MString(char[] chars,
                  int offset,
                  int length){
      append(chars, offset, length);
   }

   //============================================================
   // <T>构造字符串。</T>
   //
   // @param value 字符串
   //============================================================
   public MString(String value){
      assign(value);
   }

   //============================================================
   // <T>构造字符串。</T>
   //
   // @param value 字符串
   //============================================================
   public MString(FString value){
      assign(value);
   }

   //============================================================
   // <T>判断是否为空。</T>
   //
   // @return 是否为空
   //============================================================
   public boolean isBlank(){
      int length = new String(_memory, 0, _length).trim().length();
      return (0 == length);
   }

   //============================================================
   // <T>获得指定索引位置的字符。</T>
   //
   // @param index 索引位置
   // @return 字符
   //============================================================
   @Override
   public char charAt(int index){
      return _memory[index];
   }

   //============================================================
   // <T>接收字符串。</T>
   //
   // @param value 字符串
   //============================================================
   public void assign(String value){
      _length = 0;
      if(null != value){
         int length = value.length();
         if(length > 0){
            ensureSize(_length + length);
            value.getChars(0, length, _memory, 0);
            _length = length;
         }
      }
   }

   //============================================================
   // <T>接收数字。</T>
   //
   // @param value 数字
   //============================================================
   public void assignInt(int value){
      assign(Integer.toString(value));
   }

   //============================================================
   // <T>追加字节数组。</T>
   //
   // @param data 字节数组
   //============================================================
   public void append(byte[] data){
      append(new String(data));
   }

   //============================================================
   // <T>追加字节数组。</T>
   //
   // @param data 字节数组
   // @param charset 编码
   //============================================================
   public void append(byte[] bytes,
                      String charset){
      try{
         append(new String(bytes, charset));
      }catch(UnsupportedEncodingException e){
         throw new FFatalError(e);
      }
   }

   //============================================================
   // <T>追加字节数组。</T>
   //
   // @param data 字节数组
   // @param offset 开始位置
   // @param length 数据长度
   //============================================================
   public void append(byte[] data,
                      int offset,
                      int length){
      append(new String(data, offset, length));
   }

   //============================================================
   // <T>追加字节数组。</T>
   //
   // @param data 字节数组
   // @param offset 开始位置
   // @param length 数据长度
   // @param charset 编码
   //============================================================
   public void append(byte[] bytes,
                      int offset,
                      int count,
                      String charset){
      try{
         append(new String(bytes, offset, count, charset));
      }catch(UnsupportedEncodingException e){
         throw new FFatalError(e);
      }
   }

   //============================================================
   // <T>追加字符串。</T>
   //
   // @param value 字符串
   //============================================================
   public void append(String value){
      if(null != value){
         int length = value.length();
         if(length > 0){
            ensureSize(_length + length);
            value.getChars(0, length, _memory, _length);
            _length += length;
         }
      }
   }

   //============================================================
   // <T>追加字符串集合。</T>
   //
   // @param value 字符串集合
   //============================================================
   public void append(String... values){
      if(values != null){
         for(int n = 0; n < values.length; n++){
            append(values[n]);
         }
      }
   }

   //============================================================
   // <T>追加字符串对象。</T>
   //
   // @param value 字符串对象
   //============================================================
   public void append(FString value){
      if(null != value){
         int length = value.length();
         if(length > 0){
            ensureSize(_length + length);
            System.arraycopy(value._memory, 0, _memory, _length, length);
            _length += length;
         }
      }
   }

   //============================================================
   // <T>追加对象。</T>
   //
   // @param value 对象
   //============================================================
   public void append(Object value){
      if(null != value){
         if(value instanceof String){
            append((String)value);
         }else{
            append(value.toString());
         }
      }
   }

   //============================================================
   // <T>追加对象集合。</T>
   //
   // @param values 对象
   //============================================================
   public void append(Object... values){
      if(values != null){
         for(int n = 0; n < values.length; n++){
            append(values[n]);
         }
      }
   }

   //============================================================
   // <T>追加整数。</T>
   //
   // @param value 整数
   //============================================================
   public void appendInt(int value){
      append(Integer.toString(value));
   }

   //============================================================
   // <T>追加重复字符串。</T>
   //
   // @param value 字符串
   // @param count 总数
   //============================================================
   public void appendRepeat(String value,
                            int count){
      if(null != value){
         while(count-- > 0){
            append(value);
         }
      }
   }

   //============================================================
   // <T>追加行。</T>
   //============================================================
   public void appendLine(){
      append('\n');
   }

   //============================================================
   // <T>追加字符串行。</T>
   //
   // @param value 字符串
   //============================================================
   public void appendLine(String value){
      if(null != value){
         append(value);
      }
      append('\n');
   }

   //============================================================
   // <T>追加字符串集合行。</T>
   //
   // @param values 字符串集合
   //============================================================
   public void appendLine(String... values){
      if(null != values){
         int count = values.length;
         for(int n = 0; n < count; n++){
            append(values[n]);
         }
      }
      append('\n');
   }

   //============================================================
   // <T>追加对象行。</T>
   //
   // @param value 对象
   //============================================================
   public void appendLine(Object value){
      if(null != value){
         append(value);
      }
      append('\n');
   }

   //============================================================
   // <T>追加对象集合行。</T>
   //
   // @param values 对象集合
   //============================================================
   public void appendLine(Object... values){
      if(null != values){
         int count = values.length;
         for(int n = 0; n < count; n++){
            append(values[n]);
         }
      }
      append('\n');
   }

   //============================================================
   // <T>截掉左边不可见字符。</T>
   //============================================================
   public void trimLeft(){
      int p = 0;
      while((p < _length) && (_memory[p] <= ' ')){
         p++;
      }
      System.arraycopy(_memory, p, _memory, 0, _length - p);
      _length -= p;
   }

   //============================================================
   // <T>截掉右边不可见字符。</T>
   //============================================================
   public void trimRight(){
      while((_memory[_length - 1] <= ' ')){
         _length--;
      }
   }

   //============================================================
   // <T>截掉两边不可见字符。</T>
   //============================================================
   public void trim(){
      int p = 0;
      while((p < _length) && (_memory[p] <= ' ')){
         p++;
      }
      while((p < _length) && (_memory[_length - 1] <= ' ')){
         _length--;
      }
      _length -= p;
      System.arraycopy(_memory, p, _memory, 0, _length);
   }

   //============================================================
   // <T>内部替换字符串。</T>
   //
   // @param source 来源字符串
   // @param target 目标字符串
   //============================================================
   public void innerReplace(String source,
                            String target){
      char[] sourceChars = source.toCharArray();
      char[] targetChars = target.toCharArray();
      _memory = RChar.replace(_memory, 0, _length, sourceChars, 0, sourceChars.length, targetChars, 0, targetChars.length);
      _length = _memory.length;
   }

   //============================================================
   // <T>获得中间字符串。</T>
   //
   // @param start 开始位置
   // @param end 结束位置
   // @return 字符串
   //============================================================
   @Override
   public CharSequence subSequence(int start,
                                   int end){
      return new FString(_memory, start, end - start);
   }

   //============================================================
   // <T>获得中间字符串。</T>
   //
   // @param start 开始位置
   // @return 字符串
   //============================================================
   public FString subString(int start){
      return new FString(_memory, start, _length - start);
   }

   //============================================================
   // <T>获得中间字符串。</T>
   //
   // @param start 开始位置
   // @param length 长度
   // @return 字符串
   //============================================================
   public FString subString(int start,
                            int length){
      return new FString(_memory, start, length);
   }

   //============================================================
   // <T>刷新内容字符串。</T>
   //
   // @return 字符串
   //============================================================
   public String flushString(){
      String value = new String(_memory, 0, _length);
      clear();
      return value;
   }

   //============================================================
   // <T>获得字节集合。</T>
   //
   // @return 字节集合
   //============================================================
   public byte[] toBytes(){
      return new String(_memory, 0, _length).getBytes();
   }

   //============================================================
   // <T>获得字节集合。</T>
   //
   // @param charset 编码
   // @return 字节集合
   //============================================================
   public byte[] toBytes(String charset){
      // 默认编码
      if(RString.isEmpty(charset)){
         return new String(_memory, 0, _length).getBytes();
      }
      // 指定编码
      try{
         return new String(_memory, 0, _length).getBytes(charset);
      }catch(UnsupportedEncodingException e){
         throw new FFatalError(e);
      }
   }

   //============================================================
   // <T>获得内容字符串。</T>
   //
   // @return 字符串
   //============================================================
   @Override
   public String toString(){
      if(null != _memory){
         return new String(_memory, 0, _length);
      }
      return RString.EMPTY;
   }
   //   public int compare(FString value){
   //      return RChar.compare(_memory, 0, _length, value._memory, 0, value._length);
   //   }
   //
   //   @Override
   //   public TDumpInfo dump(){
   //      return dump(new TDumpInfo(this));
   //   }
   //
   //   @Override
   //   public TDumpInfo dump(TDumpInfo info){
   //      info.append('[');
   //      //info.appendInt(_length).append(':').append(toString());
   //      info.append(']');
   //      return info;
   //   }
   //
   //   public boolean equals(char[] chars){
   //      int length = chars.length;
   //      if(_length == length){
   //         int p = -1;
   //         while(++p < length){
   //            if(_memory[p] != chars[p]){
   //               return false;
   //            }
   //         }
   //         return true;
   //      }
   //      return false;
   //   }
   //
   //   public boolean equals(FString value){
   //      if(null == value){
   //         return false;
   //      }
   //      return (0 == RChar.compare(_memory, 0, _length, value._memory, 0, value._length));
   //   }
   //
   //   public char firstChar(){
   //      return _memory[0];
   //   }
   //
   //   public String flush(){
   //      String value = new String(_memory, 0, _length);
   //      clear();
   //      return value;
   //   }
   //
   //   public int indexOf(char first, FString value, char last){
   //      return RChar.indexOf(_memory, 0, _length, first, value.memory(), 0, value.length(), last);
   //   }
   /*
    public int indexOfAll(FString sValue) {
    return indexOfAll(sValue.memory(), 0, sValue.length());
    }
    public int indexOfAll(FString sValue, int nPosition) {
    return indexOfAll(nPosition, sValue.memory(), 0, sValue.length());
    }
    public int indexOfAll(String sFind) {
    int nLength = sFind.length();
    char[] arChars = new char[nLength];
    sFind.getChars(0, nLength, arChars, 0);
    return indexOfAll(0, arChars, 0, nLength);
    }
    public int indexOfAll(String sFind, int nPosition) {
    int nLength = sFind.length();
    char[] arChars = new char[nLength];
    sFind.getChars(0, nLength, arChars, 0);
    return indexOfAll(nPosition, arChars, 0, nLength);
    }

    /*public void replace(Object oFrom, Object oTo){
    String sFrom = null;
    if (oFrom instanceof String) {
    sFrom = (String) oFrom;
    } else if (oFrom instanceof FString) {
    sFrom = ((FString) oFrom).toString();
    } else {
    sFrom = oFrom.toString();
    }
    String sTo = null;
    if (oTo instanceof String) {
    sTo = (String) oTo;
    } else if (oFrom instanceof FString) {
    sTo = ((FString) oTo).toString();
    } else {
    sTo = oTo.toString();
    }
    }
    public void leftPad(int nLength, char chLeft) {
    if (nLength > _length) {
    int nOldLength = _length;
    _length = nLength;
    System.arraycopy(_memory, 0, _memory, _length - nOldLength,
    nOldLength);
    for (int i = 0; i < _length - nOldLength; i++) {
    _memory[i] = chLeft;
    }
    }
    }
    public void rightPad(int nLength, char chRight) {
    if (nLength > _length) {
    while ((_length < nLength)) {
    _memory[_length++] = chRight;
    }
    }
    }
    public String[] split(char[] arSplit) {
    int nSplit = arSplit.length;
    boolean bSame = true;
    int nStringNumber = 1;
    int nStringStart = 0;
    int nStringSequence = 0;
    for (int i = 0; i <= (_length - nSplit);) { //
    for (int j = 0; j < nSplit; j++) {
    if (_memory[i + j] != arSplit[j]) {
    bSame = false;
    break;
    }
    }
    if (!bSame) {
    i++;
    } else {
    i += nSplit;
    nStringNumber++;
    }
    }
    String[] arReturn = new String[nStringNumber];
    for (int i = 0; i <= (_length - nSplit);) { //
    for (int j = 0; j < nSplit; j++) {
    if (_memory[i + j] != arSplit[j]) {
    bSame = false;
    break;
    }
    }
    if (!bSame) {
    i++;
    } else {
    if ((i - nStringStart) != 0) {
    arReturn[nStringSequence] = new String(_memory, nStringStart,
    i - nStringStart);
    }
    i += nSplit;
    nStringStart = i;
    nStringSequence++;
    }
    }
    arReturn[nStringSequence] = new String(_memory, nStringStart, _length
    - nStringStart);
    return null;
    }
    public boolean startWidth(char[] arChars) {
    int nLength = arChars.length;
    for (int n = 0; n < nLength; n++) {
    if (_memory[n] != arChars[n]) {
    return false;
    }
    }
    return true;
    }
    public boolean startWidth(char[] arChars, int nOffset) {
    int nLength = arChars.length;
    for (int n = 0; n < nLength; n++) {
    if (_memory[nOffset + n] != arChars[n]) {
    return false;
    }
    }
    return true;
    }
    public boolean startWidth(char[] arChars, int nOffset, int nLength) {
    for (int n = 0; n < nLength; n++) {
    if (_memory[n] != arChars[nOffset + n]) {
    return false;
    }
    }
    return true;
    }
    public boolean startWidth(String sValue) {
    int nLength = sValue.length();
    char[] arChars = new char[nLength];
    sValue.getChars(0, nLength, arChars, 0);
    for (int n = 0; n < nLength; n++) {
    if (_memory[n] != arChars[n]) {
    return false;
    }
    }
    return true;
    }
    public FString substring(int nBeginIndex, int nEndIndex) {
    return new FString(_memory, nBeginIndex, nEndIndex - nBeginIndex);
    }
    public FString substring(String sBegin, String sEnd) {
    return substring(sBegin, sEnd, false, 0);
    }
    public FString substring(String sBegin, String sEnd, boolean bInclude) {
    return substring(sBegin, sEnd, bInclude, 0);
    }
    public FString substring(String sBegin,
    String sEnd,
    boolean bInclude,
    int nIndex) {
    int nBeginIndex = (sBegin != null) ? indexOf(sBegin, nIndex) : 0;
    if (nBeginIndex == -1) {
    nBeginIndex = nIndex;
    } else {
    if (!bInclude && sBegin != null) {
    nBeginIndex += sBegin.length();
    }
    }
    int nEndIndex = (sEnd != null) ? indexOf(sEnd, nBeginIndex + 1) : -1;
    if (nEndIndex == -1) {
    nEndIndex = _length;
    } else {
    if (bInclude && sEnd != null) {
    nEndIndex += sEnd.length();
    }
    }
    return new FString(_memory, nBeginIndex, nEndIndex - nBeginIndex);
    }
    public FString substring(String sBegin, String sEnd, int nIndex) {
    return substring(sBegin, sEnd, false, nIndex);
    }
    */
   //   public int indexOf(FString value){
   //      return RChar.indexOf(_memory, 0, _length, value.memory(), 0, value.length());
   //   }
   //
   //   public int indexOf(String value){
   //      int length = value.length();
   //      char[] chars = new char[length];
   //      value.getChars(0, length, chars, 0);
   //      return RChar.indexOf(_memory, 0, _length, chars, 0, length);
   //   }
   //
   //   public int indexOf(String value, int offset){
   //      int length = value.length();
   //      char[] chars = new char[length];
   //      value.getChars(0, length, chars, 0);
   //      return RChar.indexOf(_memory, offset, _length, chars, 0, length);
   //   }
   //
   //   public void innerInsert(String value){
   //      innerInsert(value, 0);
   //   }
   //
   //   public void innerInsert(String value, int position){
   //      if(value != null){
   //         int length = value.length();
   //         ensureSize(_length + length);
   //         System.arraycopy(_memory, position, _memory, position + length, _length);
   //         value.getChars(0, length, _memory, position);
   //         _length = length;
   //      }
   //   }
   //
   //   public void innerRemove(String value){
   //      if(value != null){
   //         _length = RChar.remove(_memory, 0, _length, value.toCharArray(), 0, value.length());
   //      }
   //   }
   //
   //   public void innerReplace(String from, String to){
   //      if(from != null && from.length() > 0){
   //         if(to == null){
   //            to = RString.EMPTY;
   //         }
   //         _memory = RChar.replace(_memory, 0, _length, from.toCharArray(), to.toCharArray());
   //         _length = _memory.length;
   //      }
   //   }
   //
   //   public void innerTrim(){
   //      int p = 0;
   //      while((p < _length) && (_memory[p] <= ' ')){
   //         p++;
   //      }
   //      while((p < _length) && (_memory[_length - 1] <= ' ')){
   //         _length--;
   //      }
   //      _length -= p;
   //      System.arraycopy(_memory, p, _memory, 0, _length);
   //   }
   //
   //   public void innerTrimLeft(){
   //      int p = 0;
   //      while((p < _length) && (_memory[p] <= ' ')){
   //         p++;
   //      }
   //      System.arraycopy(_memory, p, _memory, 0, _length - p);
   //      _length -= p;
   //   }
   //
   //   public void innerTrimRight(){
   //      while((_memory[_length - 1] <= ' ')){
   //         _length--;
   //      }
   //   }
   //
   //   public char lastChar(){
   //      return _memory[_length - 1];
   //   }
   //
   //   public FString substring(int nBeginIndex){
   //      return new FString(_memory, nBeginIndex, _length - nBeginIndex);
   //   }
   //
   //   public FString substring(int nBeginIndex, int count){
   //      return new FString(_memory, nBeginIndex, count);
   //   }
   //
}
