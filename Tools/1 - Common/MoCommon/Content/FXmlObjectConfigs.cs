using MO.Common.Collection;
using MO.Common.Lang;

namespace MO.Common.Content
{
   //============================================================
   // <T>文档对象设置字典。</T>
   //============================================================
   public class FXmlObjectConfigs : FDictionary<FXmlObjectConfig>
   {
      // 类型列表
      protected FDictionary<FXmlObjectConfig> _types = new FDictionary<FXmlObjectConfig>();

      //============================================================
      // <T>构造文档对象设置字典。</T>
      //============================================================
      public FXmlObjectConfigs() {
      }

      //============================================================
      // <T>根据对象查找设置。</T>
      //
      // @param xobject 对象
      // @return 类型设置
      //============================================================
      public FXmlObjectConfig FindByType(IXmlObject xobject) {
         string typeName = xobject.GetType().FullName;
         FXmlObjectConfig config = _types[typeName];
         if(null == config) {
            throw new FFatalException("Undefine type({0}).", typeName);
         }
         return config;
      }

      //============================================================
      // <T>加入一个对象设置。</T>
      //
      // @param config 对象设置
      //============================================================
      public new void Push(FXmlObjectConfig config) {
         // 检查类名是否重复
         string className = config.ClassName;
         if(_types.Contains(className)) {
            throw new FFatalException("In configs, type({0}) is duplicate.", className);
         }
         // 设置类名
         _types[className] = config;
         // 检查名称重复
         if(Contains(config.Name)) {
            throw new FFatalException("In configs, name({0}) is duplicate.", config.Name);
         }
         // 设置名称
         Set(config.Name, config);
      }
   }
}