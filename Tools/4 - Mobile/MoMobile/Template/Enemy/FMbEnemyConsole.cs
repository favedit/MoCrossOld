using MO.Common.Content;
using MO.Common.Lang;
using MO.Common.System;

namespace MoMobile.Template.Enemy
{
   //============================================================
   // <T>敌机控制台。</T>
   //============================================================
   public class FMbEnemyConsole : FConsole
   {
      // 实例化对象
      protected static FMbEnemyConsole _instance;

      // 目录
      protected string _directory;
      
      // 敌机集合
      protected FObjects<FMbTplEnemy> _enemys = new FObjects<FMbTplEnemy>();

      //============================================================
      // <T>构造敌机控制台。</T>   
      //============================================================
      public FMbEnemyConsole() {
         
      }

      //============================================================
      // <T>实例化敌机控制台。</T>   
      //============================================================
      public static FMbEnemyConsole Instance {
         get {
            if (null == _instance) {
               _instance = new FMbEnemyConsole();
            }
            return _instance;
         }
      }

      //============================================================
      // <T>获得敌机集合。</T>   
      //============================================================
      public FObjects<FMbTplEnemy> Enemys {
         get { return _enemys; }
      }

      //============================================================
      // <T>获得或设置路径</T>
      //============================================================
      public string Diectory {
         get { return _directory; }
         set { _directory = value; }
      }

      //============================================================
      // <T>根据编号获得敌机。</T>
      //============================================================
      public FMbTplEnemy FingById(int id) {
         foreach (FMbTplEnemy enemy in _enemys) {
            if (id == enemy.Id) {
               return enemy;
            }
         }
         return null;
      }

      //============================================================
      // <T>加载配置信息</T>
      //============================================================
      public override void LoadConfig(FXmlNode xconfig) {
         //// 加载属性
         //foreach (FXmlNode node in xconfig.Nodes) {
         //   string name = node.Get("name");
         //   if ("template_directory" == name) {
         //      string text = node.Text;
         //      _directory = RMoCommon.ParseEnvironment(text);
         //   }
         //}
         //// 加载配置
         //LoadTemplateConfig(_directory);
      }

      //============================================================
      // <T>加载配置文件，取得地址。</T>
      //
      // @param directory 文件路径。
      //============================================================
      public void LoadTemplateConfig(string directory) {
         FXmlDocument xmldoc = new FXmlDocument();
         xmldoc.LoadFile(directory+"\\enemy.xml");
         foreach (FXmlNode node in xmldoc.Root.Nodes) {
            FMbTplEnemy enemy = new FMbTplEnemy();
            enemy.Id = node.GetInteger("tid");
            enemy.Label = node.Get("label");
            enemy.HealthPoint = node.GetInteger("health_point");
            enemy.MagicPoint = node.GetInteger("magic_point");
            enemy.AttackPower = node.GetInteger("attack_power");
            enemy.DefensePower = node.GetInteger("defense_power");
            enemy.MoveSpeed = node.GetInteger("move_speed");
            enemy.IconRid = node.GetInteger("icon_rid");
            enemy.ResourceRid = node.GetInteger("resource_rid");
            _enemys.Push(enemy);
         }
      }

   }
}
