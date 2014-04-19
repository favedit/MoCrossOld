using MO.Common.Lang;
using MO.Content2d.Resource.Animation;
using System.Drawing;
using System.Windows.Forms;
using IControl = System.Windows.Forms.Control;

namespace MO.Design2d.Resource.Controls
{
   public partial class QMoveControl : UserControl
   {

      // 是否是第一个操作对象
      protected bool _isFirst = false;

      // 当前操作对象
      public Button _button;

      // 当前位置
      public Point p = new Point();

      // 是否移动控件
      bool isMove = false;

      // 控件当前的坐标
      public int oldLocationX;

      // 控件改变后的位置
      public int newLocationX;

      // 当前选中控件之前的控件x坐标
      public int beforeControlX;

      // 相邻两个控件之间的距离
      protected int _distanceX;

      // 取得当前控件轴的长度
      protected int labTimeAxisWidth;

      // 对象集合
      public FObjects<IControl> _controls = new FObjects<IControl>();

      //============================================================
      // <T>获取或者得到控件差距。</T>
      //
      // @author DYWEN 20120531
      //============================================================
      public int DistanceX {
         get { return _distanceX; }
         set { _distanceX = value; }
      }
      
      //============================================================
      public QMoveControl() {
         InitializeComponent();
         labTimeAxisWidth = labTimeAxis.Width;
         //DrawNumber();
      }


      private void DrawNumber() {
        int indexNumber = 0;
        int btLocation = labTimeAxis.Width / 75;
        for (int i = 0; i < btLocation; i++) {
           indexNumber += 15;
           //labNumber.Text = indexNumber.ToString();
        }
      }

      //============================================================
      public void DrawLine() {
         Graphics g = CreateGraphics();
         int left = labTimeAxis.Left;
         int top = labTimeAxis.Top;
         Pen pen = new Pen(Color.Black);
         for (int n = 1; n <= labTimeAxis.Width / 15; n++) {
            if (n % 5 == 0) {
               g.DrawLine(pen, n * 15 + left, top, n * 15 + left, top + 8);
            } else {
               g.DrawLine(pen, n * 15 + left, top, n * 15 + left, top + 5);
            }
         }
      }

      //============================================================
      protected override void OnPaint(PaintEventArgs e) {
         base.OnPaint(e);
         DrawLine();
      }

      //============================================================
      public void LoadInfomation(FRsResourceClip clip) {
         int freamCount = clip.FrameCount;
         for (int n = 0; n < freamCount; n++) {
            FRsResourceFrame fream = clip.Frames[n];
            CreateFream(fream, n + 1);
         }
      }

      //============================================================
      private void QMoveControl_Load(object sender, System.EventArgs e) {

      }

      //============================================================
      public void CreateFream(FRsResourceFrame fream, int index) {
         Button button = new Button();
         button.Width = 10;
         button.Height = 20;
         button.Tag = fream;
         int freamDelay = fream.Delay;
         int cellIndex = 1500 / fream.Delay;
         button.MouseDown += new MouseEventHandler(button_MouseDown);
         button.MouseMove += new MouseEventHandler(button_MouseMove);
         button.MouseUp += new MouseEventHandler(button_MouseUp);
         button.MouseWheel += new MouseEventHandler(button_MouseWheel);
         int x = index * cellIndex * 15 + labTimeAxis.Left;
         Point p = new Point(x, 0);
         button.Location = p;
         if (!_isFirst) {
            _button = button;
            _isFirst = true;
         }
         Controls.Add(button);
         _controls.Push(button);
      }

      //============================================================
      // <T>鼠标点下的时候</T>
      //
      // @author DYWEN 20120531
      //============================================================
      public void button_MouseDown(object sender, MouseEventArgs e) {
         _button = sender as Button;
         oldLocationX = _button.Location.X;
         if (sender != null) {
            isMove = true;
         }
      }

      //============================================================
      // <T>鼠标移动的时候发生。</T>
      //
      // @author DYWEN 20120531
      //============================================================
      public void button_MouseMove(object sender, MouseEventArgs e) {
         _button.SuspendLayout();
         txtFrameDelayEdit.Text = _button.Location.X.ToString();
         // 移动的时候后一个控件不能超过前面的一个控件
         FRsResourceFrame freams = _button.Tag as FRsResourceFrame;
         int index = freams.Index - 1;
         if (index >= 0) {
            int conLocation = _controls[index].Location.X;
            if (conLocation > _button.Location.X) {
               _button.Location = new Point(conLocation, 0);
            }
         }
         _button = sender as Button;
         int bw = _button.Size.Width;
         int cx = _button.Location.X;
         // 移动控件
         if (isMove == true) {
            // 向左移动
            if (_button.Location.X <= 0) {
               _button.Location = new Point(0, 0);
            }
            // 右移动
            if (_button.Location.X >= labTimeAxisWidth) {
               _button.Location = new Point(labTimeAxisWidth - 10, 0);
            }
            p.X = cx + e.X - bw / 2;
            p.Y = 0;
            _button.Location = p;
         }
         _button.ResumeLayout();
      }

      //============================================================
      // <T>鼠标按键放开的</T>
      //
      // @author DYWEN 20120531
      //============================================================
      void button_MouseUp(object sender, MouseEventArgs e) {
         // 移动的时候后一个控件不能超过前面的一个控件
         FRsResourceFrame freams = _button.Tag as FRsResourceFrame;
         int index = freams.Index - 1;
         if (index >= 0) {
            int conLocation = _controls[index].Location.X;
            if (conLocation > _button.Location.X) {
               _button.Location = new Point(conLocation, 0);
            }
         }
         if (_button.Location.X <= 0) {
            _button.Location = new Point(0, 0);
         }
         if (_button.Location.X >= labTimeAxisWidth) {
            _button.Location = new Point(labTimeAxisWidth - 10, 0);
         }
         if (sender != null) {
            _button = (Button)sender;
            isMove = false;
         }
         // 向右移动的时候当前控件之后的控件同时移动
         newLocationX = _button.Location.X;
         if (newLocationX > oldLocationX) {
            int w = newLocationX - oldLocationX;
            int j = freams.Index + 1;
            for (int n = j; n < _controls.Count; n++) {
               int loc = _controls[n].Location.X;
               p.X = loc + w;
               if (p.X <= labTimeAxisWidth) {
                  _controls[n].Location = p;
               }
            }
         }
         if (newLocationX < oldLocationX) {
            int w = oldLocationX- newLocationX;
            int j = freams.Index + 1;
            for (int n = j; n < _controls.Count; n++) {
               int loc = _controls[n].Location.X;
               p.X = loc - w;
               if (p.X <= labTimeAxisWidth) {
                  _controls[n].Location = p;
               }
            }
         }
         distance(newLocationX);
         txtFrameDelayEdit.Text = _button.Location.X.ToString();
      }

      //============================================================
      // <T>计算控件之间的距离</T>
      //
      // @author DYWEN 20120531
      //============================================================
      private void distance(int newdataX) {
         FRsResourceFrame fream = _button.Tag as FRsResourceFrame;
         int index = fream.Index;
         if (index == 0) {
            _button = (Button)_controls[index];
            _distanceX = newdataX - 0;
         }
         if (index >= 1) {
            _button = (Button)_controls[index - 1];
            beforeControlX = _button.Location.X;
            // 计算当前控件和相邻上个控件的距离
            _distanceX = newdataX - beforeControlX;
         }
         if (_distanceX > 0) {
            fream.Delay = _distanceX;
            txtFrameDelay.Text = _distanceX.ToString(); 
         }
      }

      //============================================================
      public void button_MouseWheel(object sender, MouseEventArgs e) {
         int width = labTimeAxis.Width + 20;
         int count = e.Delta;
         //bool result = false;
         FRsResourceFrame fream = _button.Tag as FRsResourceFrame;
         Point point = new Point();
         int n = fream.Index - 1;
         if (n >= 0) {
            int index = Controls.IndexOf(_controls[n]);
            point = _controls[n].Location;
         }
         if (count == 120) {
            p.X = _button.Location.X + 10;
            p.Y = _button.Location.Y;
            if (p.X > width) {
               p.X = width;
            }
            _button.Location = p;
            rightPoint();
         }
         if (count == -120) {
            p.X = _button.Location.X - 10;
            p.Y = _button.Location.Y;
            if (p.X < labTimeAxis.Left) {
               p.X = labTimeAxis.Left;
            }
            if (p.X <= point.X) {
               p.X = point.X + 10;
            }
            _button.Location = p;
            leftPoint();
         }
      }

      //============================================================
      // 滚轮向左滚动的时候控件移动
      //============================================================
      public void leftPoint() {
         FRsResourceFrame fream = _button.Tag as FRsResourceFrame;
         int j = fream.Index + 1;
         for (int n = j; n < _controls.Count; n++) {
            int loc = _controls[n].Location.X;
            p.X = loc - 10;
            _controls[n].Location = p;
         }
      }

      //============================================================
      // 滚轮向右滚动的时候控件移动
      //============================================================
      public void rightPoint() {
         FRsResourceFrame fream = _button.Tag as FRsResourceFrame;
         int j = fream.Index + 1;
         for (int n = j; n < _controls.Count; n++) {
            int loc = _controls[n].Location.X;
            p.X = loc + 10;
            _controls[n].Location = p;
         }
      }

      //============================================================
      public bool CheckMoved() {
         //FRsResourceFrame fream = _button.Tag as FRsResourceFrame;
         //int j = fream.Index + 1;
         //for(int n = j; n < _controls.Count; n++) {
         //   int loc = _controls[n].Location.X;
         //   p.X = loc + 15;
         //   if (){
         //   }
         //   _controls[n].Location = p;
         //}
         return false;
      }

      //============================================================
      public void Clear() {
         foreach (IControl control in _controls) {
            control.Dispose();
         }
         _controls.Clear();
      }

      //============================================================
      // <T>调整单个控件的位置</T>
      //
      // @author DYWEN 20120625
      //============================================================
      private void txtFrameDelayEdit_KeyDown(object sender, KeyEventArgs e) {
         FRsResourceFrame fream = _button.Tag as FRsResourceFrame;
         int en = fream.Index + 1;
         int ba = fream.Index - 1;
         if (e.KeyValue == 13) {
            int locX = RInt.Parse(txtFrameDelayEdit.Text.ToString());
            if (locX > _controls[en].Location.X) {
               MessageBox.Show("亲，超出范围！");
               return;
            }
            if (locX < _controls[ba].Location.X) {
               MessageBox.Show("亲，超出范围！");
               return;
            }
            p.X = locX;
            _button.Location = p;
         }
      }
   }
}
 