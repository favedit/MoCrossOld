using System;
using MO.Common.Lang;

namespace MO.Common.Geom
{
   //============================================================
   // <T>整数可变尺寸。</T>
   //============================================================
   public class SIntVarSize : SIntSize
   {
      public const int WIDTH_VALID = 0x01;

      public const int HEIGHT_VALID = 0x02;

      public const int WIDTH_ANY = 0x10;

      public const int HEIGHT_ANY = 0x20;

      public const string VAL_ANY = "*";                
      
      int _flag = WIDTH_VALID + HEIGHT_VALID;

      //============================================================
      // <T>构造整数可变尺寸。</T>
      //============================================================
      public SIntVarSize() {
      }

      //============================================================
      // <T>构造整数可变尺寸。</T>
      //
      // @param value 字符串
      //============================================================
      public SIntVarSize(string value)
         : base(value) {
      }

      //============================================================
      // <T>构造整数可变尺寸。</T>
      //
      // @param width 宽度
      // @param height 高度
      //============================================================
      public SIntVarSize(int width, int height)
         : base(width, height) {
      }

      //============================================================
      // <T>测试宽度是否合法。</T>
      //
      // @return 是否合法
      //============================================================
      public bool IsWidthValid() {
         return WIDTH_VALID == (_flag & WIDTH_VALID);
      }

      //============================================================
      // <T>测试宽度是否可变。</T>
      //
      // @return 是否可变
      //============================================================
      public bool IsWidthAny() {
         return WIDTH_ANY == (_flag & WIDTH_ANY);
      }

      //============================================================
      // <T>测试宽度是否合法。</T>
      //
      // @return 是否合法
      //============================================================
      public bool IsHeightValid() {
         return HEIGHT_VALID == (_flag & HEIGHT_VALID);
      }

      //============================================================
      // <T>测试高度是否可变。</T>
      //
      // @return 是否可变
      //============================================================
      public bool IsHeightAny() {
         return HEIGHT_ANY == (_flag & HEIGHT_ANY);
      }

      //============================================================
      // <T>解析字符串。</T>
      //
      // @param value 字符串
      //============================================================
      public override void Parse(string value) {
         string[] data = value.Split(',');
         if(data.Length == 2) {
            _flag = 0;
            // 解析宽度
            string width = data[0].Trim();
            if(!VAL_ANY.Equals(width)) {
               _flag |= Int32.TryParse(width, out Width) ? WIDTH_VALID : 0;
            } else {
               _flag |= WIDTH_ANY;
            }
            // 解析高度
            string height = data[1].Trim();
            if(!VAL_ANY.Equals(height)) {
               _flag |= Int32.TryParse(height, out Height) ? HEIGHT_VALID : 0;
            } else {
               _flag |= HEIGHT_ANY;
            }
         } else {
            throw new FFatalException("Invalid size format ({0})", value);
         }
      }
   }
}
