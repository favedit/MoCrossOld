package org.mo.com.lang.temp;

//============================================================
// <T>BIT集合。</T>
//============================================================
public abstract class MBits
{
   public final static int[] MASKS = {0x80000000, 0x40000000, 0x20000000, 0x10000000, 0x08000000, 0x04000000, 0x02000000, 0x01000000, 0x00800000, 0x00400000, 0x00200000, 0x00100000, 0x00080000, 0x00040000, 0x00020000, 0x00010000, 0x00008000, 0x00004000,
         0x00002000, 0x00001000, 0x00000800, 0x00000400, 0x00000200, 0x00000100, 0x00000080, 0x00000040, 0x00000020, 0x00000010, 0x00000008, 0x00000004, 0x00000002, 0x00000001};

   public final static int MASKS_LENGTH = MASKS.length;

   public final static int[] UMASKS = {0x7FFFFFFF, 0xBFFFFFFF, 0xDFFFFFFF, 0xEFFFFFFF, 0xF7FFFFFF, 0xFBFFFFFF, 0xFDFFFFFF, 0xFEFFFFFF, 0xFF7FFFFF, 0xFFBFFFFF, 0xFFDFFFFF, 0xFFEFFFFF, 0xFFF7FFFF, 0xFFFBFFFF, 0xFFFDFFFF, 0xFFFEFFFF, 0xFFFF7FFF, 0xFFFFBFFF,
         0xFFFFDFFF, 0xFFFFEFFF, 0xFFFFF7FF, 0xFFFFFBFF, 0xFFFFFDFF, 0xFFFFFEFF, 0xFFFFFF7F, 0xFFFFFFBF, 0xFFFFFFDF, 0xFFFFFFEF, 0xFFFFFFF7, 0xFFFFFFFB, 0xFFFFFFFD, 0xFFFFFFFE};

   protected int _length = 0;

   protected int[] _memory = new int[32];

   protected int _total = 32;

   public MBits(){
   }

   public MBits(int capacity){
      ensureSize(capacity >> 5);
   }

   //   @SuppressWarnings("unchecked")
   //   public T append(boolean value){
   //      ensureSize(_length + 1);
   //      if(value){
   //         _memory[_length >>> 5] |= MASKS[_length % 32];
   //      }else{
   //         _memory[_length >>> 5] &= UMASKS[_length % 32];
   //      }
   //      _length++;
   //      return (T)this;
   //   }
   //   @SuppressWarnings("unchecked")
   //   public T append(int value){
   //      ensureSize(_length + 32);
   //      _memory[_length >>> 5] = value;
   //      _length += 32;
   //      return (T)this;
   //   }
   //
   //   @SuppressWarnings("unchecked")
   //   public T append(int[] memory, int offset, int count){
   //      if(memory != null && count > 0){
   //         ensureSize(_length + (count << 5));
   //         System.arraycopy(memory, offset, _memory, _length, count);
   //         _length += count;
   //      }
   //      return (T)this;
   //   }
   public void clear(){
      _length = 0;
   }

   //   @SuppressWarnings("unchecked")
   //   public T copy(){
   //      try{
   //         T copy = (T)getClass().newInstance();
   //         copy._length = _length;
   //         copy._total = _total;
   //         copy._memory = _memory.clone();
   //         return copy;
   //      }catch(Exception e){
   //         throw new FFatalError(e);
   //      }
   //   }
   public final void ensureSize(int count){
      count >>= 5;
      if(count > _total){
         _total = (_total > 0) ? count + (count >> 1) : count;
         int[] alloc = new int[_total];
         if(_length > 0){
            System.arraycopy(_memory, 0, alloc, 0, _length);
         }
         _memory = alloc;
      }
   }

   public boolean get(int index){
      return (index >= 0 && index < _length) ? (_memory[index >>> 5] & MASKS[index % 32]) != 0 : false;
   }

   public final boolean isEmpty(){
      return _length == 0;
   }

   public int length(){
      return _length;
   }

   public final int[] memory(){
      return _memory;
   }

   public void set(int index,
                   boolean value){
      if(index >= 0 && index < _length){
         _memory[index >>> 5] |= MASKS[index % 32];
      }
   }

   public int[] toArray(){
      int[] alloc = new int[_length];
      System.arraycopy(_memory, 0, alloc, 0, _length);
      return alloc;
   }
}
