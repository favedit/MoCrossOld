using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.ComponentModel;

namespace MO.Content2d.Frame.Common
{
   public class FRcLineConverter : ExpandableObjectConverter
   {
      //============================================================
      // <T>是否能从转换为指定类型。</T>
      //
      // @param context 环境
      // @param destinationType 目标类型
      // @return 是否能转换
      //============================================================
      public override bool CanConvertTo(ITypeDescriptorContext context, Type destinationType) {
         if(destinationType == typeof(FRcLine)){
            return true;
         }
         return false;
      }

      //============================================================
      // <T>转换内容为指定类型。</T>
      //
      // @param context 环境
      // @param culture 文化
      // @param value 内容
      // @param destinationType 目标类型
      // @return 是否含有位图
      //============================================================
      public override object ConvertTo(ITypeDescriptorContext context, System.Globalization.CultureInfo culture, object value, Type destinationType) {
         FRcLine source = (FRcLine)value;
         if(null != source){
            return source.ToString();
         }
         return null;
      }

      //============================================================
      // <T>是否能从指定的类型进行转换。</T>
      //
      // @param context 环境
      // @param sourceType 来源类型
      // @return 是否能转换
      //============================================================
      public override bool CanConvertFrom(ITypeDescriptorContext context, Type sourceType) {
         if (sourceType == typeof(string)) {
            return true;
         }
         return false;
      }

      //============================================================
      // <T>从指定类型转换为内容。</T>
      //
      // @param context 环境
      // @param culture 文化
      // @param value 内容
      // @param destinationType 目标类型
      // @return 是否含有位图
      //============================================================
      public override object ConvertFrom(ITypeDescriptorContext context, System.Globalization.CultureInfo culture, object value) {
         if (value is string) {
            FRcLine source = new FRcLine();
            if (source.Parse(value as string)) {
               return source;
            }
         }
         return null;
      }
   }
}
