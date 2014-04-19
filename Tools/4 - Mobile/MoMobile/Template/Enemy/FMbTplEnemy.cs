
namespace MoMobile.Template.Enemy
{
   //============================================================
   // <T>模版敌机对象。</T>
   //============================================================
   public class FMbTplEnemy
   {
      // 编号
      protected int _id;

      // 标签
      protected string _label;

      // 血量
      protected int _healthPoint;

      //魔法量
      protected int _magicPoint;

      // 攻击力
      protected int _attackPower;

      // 防御力
      protected int _defensePower;

      // 移动速度
      protected int _moveSpeed;

      // 图标资源模版编号
      protected int _iconRid;

      // 资源模版编号
      protected int _resourceRid;

      //============================================================
      // <T>获取或得到编号。</T>
      //============================================================
      public int Id {
         get { return _id; }
         set { _id = value; }
      }

      //============================================================
      // <T>获取或得到标签。</T>
      //============================================================
      public string Label {
         get { return _label; }
         set { _label = value; }
      }

      //============================================================
      // <T>获取或得到血量。</T>
      //============================================================
      public int HealthPoint {
         get { return _healthPoint; }
         set { _healthPoint = value; }
      }

      //============================================================
      // <T>获取或得到魔法量。</T>
      //============================================================
      public int MagicPoint {
         get { return _magicPoint; }
         set { _magicPoint = value; }
      }

      //============================================================
      // <T>获取或得到攻击力。</T>
      //============================================================
      public int AttackPower {
         get { return _attackPower; }
         set { _attackPower = value; }
      }

      //============================================================
      // <T>获取或得到防御力。</T>
      //============================================================
      public int DefensePower {
         get { return _defensePower; }
         set { _defensePower = value; }
      }

      //============================================================
      // <T>获取或得到移动速度。</T>
      //============================================================
      public int MoveSpeed {
         get { return _moveSpeed; }
         set { _moveSpeed = value; }
      }

      //============================================================
      // <T>获取或得到图标编号。</T>
      //============================================================
      public int IconRid {
         get { return _iconRid; }
         set { _iconRid = value; }
      }

      //============================================================
      // <T>获取或得到资源编号。</T>
      //============================================================
      public int ResourceRid {
         get { return _resourceRid; }
         set { _resourceRid = value; }
      }

   }
}
