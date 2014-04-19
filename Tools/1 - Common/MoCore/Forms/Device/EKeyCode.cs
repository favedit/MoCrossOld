using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace MO.Core.Forms.Device
{
   public class EKeyCode
   {
      // 摘要:
      //     从键值提取修饰符的位屏蔽。
      public static int Modifiers = -65536;
      //
      // 摘要:
      //     没有按任何键。
      public static int None = 0;
      //
      // 摘要:
      //     鼠标左按钮。
      public static int LButton = 1;
      //
      // 摘要:
      //     鼠标右按钮。
      public static int RButton = 2;
      //
      // 摘要:
      //     Cancel 键。
      public static int Cancel = 3;
      //
      // 摘要:
      //     鼠标中按钮（三个按钮的鼠标）。
      public static int MButton = 4;
      //
      // 摘要:
      //     第一个 X 鼠标按钮（五个按钮的鼠标）。
      public static int XButton1 = 5;
      //
      // 摘要:
      //     第二个 X 鼠标按钮（五个按钮的鼠标）。
      public static int XButton2 = 6;
      //
      // 摘要:
      //     Backspace 键。
      public static int Back = 8;
      //
      // 摘要:
      //     The TAB key.
      public static int Tab = 9;
      //
      // 摘要:
      //     LINEFEED 键。
      public static int LineFeed = 10;
      //
      // 摘要:
      //     Clear 键。
      public static int Clear = 12;
      //
      // 摘要:
      //     Enter 键。
      public static int Enter = 13;
      //
      // 摘要:
      //     Return 键。
      public static int Return = 13;
      //
      // 摘要:
      //     Shift 键。
      public static int ShiftKey = 16;
      //
      // 摘要:
      //     The CTRL key.
      public static int ControlKey = 17;
      //
      // 摘要:
      //     Alt 键。
      public static int Menu = 18;
      //
      // 摘要:
      //     Pause 键。
      public static int Pause = 19;
      //
      // 摘要:
      //     The CAPS LOCK key.
      public static int CapsLock = 20;
      //
      // 摘要:
      //     The CAPS LOCK key.
      public static int Capital = 20;
      //
      // 摘要:
      //     IME Kana 模式键。
      public static int KanaMode = 21;
      //
      // 摘要:
      //     IME Hanguel 模式键。（为了保持兼容性而设置；使用 HangulMode）
      public static int HanguelMode = 21;
      //
      // 摘要:
      //     IME Hangul 模式键。
      public static int HangulMode = 21;
      //
      // 摘要:
      //     IME Junja 模式键。
      public static int JunjaMode = 23;
      //
      // 摘要:
      //     IME 最终模式键。
      public static int FinalMode = 24;
      //
      // 摘要:
      //     IME Kanji 模式键。
      public static int KanjiMode = 25;
      //
      // 摘要:
      //     IME Hanja 模式键。
      public static int HanjaMode = 25;
      //
      // 摘要:
      //     The ESC key.
      public static int Escape = 27;
      //
      // 摘要:
      //     IME 转换键。
      public static int IMEConvert = 28;
      //
      // 摘要:
      //     IME 非转换键。
      public static int IMENonconvert = 29;
      //
      // 摘要:
      //     IME 接受键。已过时，请改用 System.Windows.Forms.Keys.IMEAccept。
      public static int IMEAceept = 30;
      //
      // 摘要:
      //     IME 接受键，替换 System.Windows.Forms.Keys.IMEAceept。
      public static int IMEAccept = 30;
      //
      // 摘要:
      //     IME 模式更改键。
      public static int IMEModeChange = 31;
      //
      // 摘要:
      //     空格键。
      public static int Space = 32;
      //
      // 摘要:
      //     The PAGE UP key.
      public static int Prior = 33;
      //
      // 摘要:
      //     The PAGE UP key.
      public static int PageUp = 33;
      //
      // 摘要:
      //     The PAGE DOWN key.
      public static int Next = 34;
      //
      // 摘要:
      //     The PAGE DOWN key.
      public static int PageDown = 34;
      //
      // 摘要:
      //     The END key.
      public static int End = 35;
      //
      // 摘要:
      //     The HOME key.
      public static int Home = 36;
      //
      // 摘要:
      //     向左键。
      public static int Left = 37;
      //
      // 摘要:
      //     向上键。
      public static int Up = 38;
      //
      // 摘要:
      //     向右键。
      public static int Right = 39;
      //
      // 摘要:
      //     向下键。
      public static int Down = 40;
      //
      // 摘要:
      //     Select 键。
      public static int Select = 41;
      //
      // 摘要:
      //     Print 键。
      public static int Print = 42;
      //
      // 摘要:
      //     EXECUTE 键。
      public static int Execute = 43;
      //
      // 摘要:
      //     Print Screen 键。
      public static int PrintScreen = 44;
      //
      // 摘要:
      //     Print Screen 键。
      public static int Snapshot = 44;
      //
      // 摘要:
      //     The INS key.
      public static int Insert = 45;
      //
      // 摘要:
      //     The DEL key.
      public static int Delete = 46;
      //
      // 摘要:
      //     The HELP key.
      public static int Help = 47;
      //
      // 摘要:
      //     The 0 key.
      public static int D0 = 48;
      //
      // 摘要:
      //     The 1 key.
      public static int D1 = 49;
      //
      // 摘要:
      //     The 2 key.
      public static int D2 = 50;
      //
      // 摘要:
      //     The 3 key.
      public static int D3 = 51;
      //
      // 摘要:
      //     The 4 key.
      public static int D4 = 52;
      //
      // 摘要:
      //     The 5 key.
      public static int D5 = 53;
      //
      // 摘要:
      //     The 6 key.
      public static int D6 = 54;
      //
      // 摘要:
      //     The 7 key.
      public static int D7 = 55;
      //
      // 摘要:
      //     The 8 key.
      public static int D8 = 56;
      //
      // 摘要:
      //     The 9 key.
      public static int D9 = 57;
      //
      // 摘要:
      //     A 键。
      public static int A = 65;
      //
      // 摘要:
      //     B 键。
      public static int B = 66;
      //
      // 摘要:
      //     C 键。
      public static int C = 67;
      //
      // 摘要:
      //     D 键。
      public static int D = 68;
      //
      // 摘要:
      //     E 键。
      public static int E = 69;
      //
      // 摘要:
      //     F 键。
      public static int F = 70;
      //
      // 摘要:
      //     G 键。
      public static int G = 71;
      //
      // 摘要:
      //     H 键。
      public static int H = 72;
      //
      // 摘要:
      //     I 键。
      public static int I = 73;
      //
      // 摘要:
      //     J 键。
      public static int J = 74;
      //
      // 摘要:
      //     K 键。
      public static int K = 75;
      //
      // 摘要:
      //     L 键。
      public static int L = 76;
      //
      // 摘要:
      //     M 键。
      public static int M = 77;
      //
      // 摘要:
      //     N 键。
      public static int N = 78;
      //
      // 摘要:
      //     O 键。
      public static int O = 79;
      //
      // 摘要:
      //     P 键。
      public static int P = 80;
      //
      // 摘要:
      //     Q 键。
      public static int Q = 81;
      //
      // 摘要:
      //     R 键。
      public static int R = 82;
      //
      // 摘要:
      //     S 键。
      public static int S = 83;
      //
      // 摘要:
      //     T 键。
      public static int T = 84;
      //
      // 摘要:
      //     U 键。
      public static int U = 85;
      //
      // 摘要:
      //     V 键。
      public static int V = 86;
      //
      // 摘要:
      //     W 键。
      public static int W = 87;
      //
      // 摘要:
      //     X 键。
      public static int X = 88;
      //
      // 摘要:
      //     Y 键。
      public static int Y = 89;
      //
      // 摘要:
      //     Z 键。
      public static int Z = 90;
      //
      // 摘要:
      //     左 Windows 徽标键（Microsoft Natural Keyboard，人体工程学键盘）。
      public static int LWin = 91;
      //
      // 摘要:
      //     右 Windows 徽标键（Microsoft Natural Keyboard，人体工程学键盘）。
      public static int RWin = 92;
      //
      // 摘要:
      //     应用程序键（Microsoft Natural Keyboard，人体工程学键盘）。
      public static int Apps = 93;
      //
      // 摘要:
      //     计算机睡眠键。
      public static int Sleep = 95;
      //
      // 摘要:
      //     The 0 key on the numeric keypad.
      public static int NumPad0 = 96;
      //
      // 摘要:
      //     The 1 key on the numeric keypad.
      public static int NumPad1 = 97;
      //
      // 摘要:
      //     数字键盘上的 2 键。
      public static int NumPad2 = 98;
      //
      // 摘要:
      //     数字键盘上的 3 键。
      public static int NumPad3 = 99;
      //
      // 摘要:
      //     数字键盘上的 4 键。
      public static int NumPad4 = 100;
      //
      // 摘要:
      //     数字键盘上的 5 键。
      public static int NumPad5 = 101;
      //
      // 摘要:
      //     数字键盘上的 6 键。
      public static int NumPad6 = 102;
      //
      // 摘要:
      //     数字键盘上的 7 键。
      public static int NumPad7 = 103;
      //
      // 摘要:
      //     The 8 key on the numeric keypad.
      public static int NumPad8 = 104;
      //
      // 摘要:
      //     The 9 key on the numeric keypad.
      public static int NumPad9 = 105;
      //
      // 摘要:
      //     乘号键。
      public static int Multiply = 106;
      //
      // 摘要:
      //     加号键。
      public static int Add = 107;
      //
      // 摘要:
      //     分隔符键。
      public static int Separator = 108;
      //
      // 摘要:
      //     减号键。
      public static int Subtract = 109;
      //
      // 摘要:
      //     句点键。
      public static int Decimal = 110;
      //
      // 摘要:
      //     除号键。
      public static int Divide = 111;
      //
      // 摘要:
      //     The F1 key.
      public static int F1 = 112;
      //
      // 摘要:
      //     The F2 key.
      public static int F2 = 113;
      //
      // 摘要:
      //     The F3 key.
      public static int F3 = 114;
      //
      // 摘要:
      //     The F4 key.
      public static int F4 = 115;
      //
      // 摘要:
      //     The F5 key.
      public static int F5 = 116;
      //
      // 摘要:
      //     The F6 key.
      public static int F6 = 117;
      //
      // 摘要:
      //     The F7 key.
      public static int F7 = 118;
      //
      // 摘要:
      //     The F8 key.
      public static int F8 = 119;
      //
      // 摘要:
      //     The F9 key.
      public static int F9 = 120;
      //
      // 摘要:
      //     The F10 key.
      public static int F10 = 121;
      //
      // 摘要:
      //     The F11 key.
      public static int F11 = 122;
      //
      // 摘要:
      //     The F12 key.
      public static int F12 = 123;
      //
      // 摘要:
      //     The F13 key.
      public static int F13 = 124;
      //
      // 摘要:
      //     The F14 key.
      public static int F14 = 125;
      //
      // 摘要:
      //     The F15 key.
      public static int F15 = 126;
      //
      // 摘要:
      //     The F16 key.
      public static int F16 = 127;
      //
      // 摘要:
      //     The F17 key.
      public static int F17 = 128;
      //
      // 摘要:
      //     The F18 key.
      public static int F18 = 129;
      //
      // 摘要:
      //     The F19 key.
      public static int F19 = 130;
      //
      // 摘要:
      //     The F20 key.
      public static int F20 = 131;
      //
      // 摘要:
      //     The F21 key.
      public static int F21 = 132;
      //
      // 摘要:
      //     The F22 key.
      public static int F22 = 133;
      //
      // 摘要:
      //     The F23 key.
      public static int F23 = 134;
      //
      // 摘要:
      //     The F24 key.
      public static int F24 = 135;
      //
      // 摘要:
      //     The NUM LOCK key.
      public static int NumLock = 144;
      //
      // 摘要:
      //     Scroll Lock 键。
      public static int Scroll = 145;
      //
      // 摘要:
      //     左 Shift 键。
      public static int LShiftKey = 160;
      //
      // 摘要:
      //     右 Shift 键。
      public static int RShiftKey = 161;
      //
      // 摘要:
      //     左 Ctrl 键。
      public static int LControlKey = 162;
      //
      // 摘要:
      //     右 Ctrl 键。
      public static int RControlKey = 163;
      //
      // 摘要:
      //     左 Alt 键。
      public static int LMenu = 164;
      //
      // 摘要:
      //     右 Alt 键。
      public static int RMenu = 165;
      //
      // 摘要:
      //     浏览器后退键（Windows 2000 或更高版本）。
      public static int BrowserBack = 166;
      //
      // 摘要:
      //     浏览器前进键（Windows 2000 或更高版本）。
      public static int BrowserForward = 167;
      //
      // 摘要:
      //     浏览器刷新键（Windows 2000 或更高版本）。
      public static int BrowserRefresh = 168;
      //
      // 摘要:
      //     浏览器停止键（Windows 2000 或更高版本）。
      public static int BrowserStop = 169;
      //
      // 摘要:
      //     浏览器搜索键（Windows 2000 或更高版本）。
      public static int BrowserSearch = 170;
      //
      // 摘要:
      //     浏览器收藏夹键（Windows 2000 或更高版本）。
      public static int BrowserFavorites = 171;
      //
      // 摘要:
      //     浏览器主页键（Windows 2000 或更高版本）。
      public static int BrowserHome = 172;
      //
      // 摘要:
      //     静音键（Windows 2000 或更高版本）。
      public static int VolumeMute = 173;
      //
      // 摘要:
      //     减小音量键（Windows 2000 或更高版本）。
      public static int VolumeDown = 174;
      //
      // 摘要:
      //     增大音量键（Windows 2000 或更高版本）。
      public static int VolumeUp = 175;
      //
      // 摘要:
      //     媒体下一曲目键（Windows 2000 或更高版本）。
      public static int MediaNextTrack = 176;
      //
      // 摘要:
      //     媒体上一曲目键（Windows 2000 或更高版本）。
      public static int MediaPreviousTrack = 177;
      //
      // 摘要:
      //     媒体停止键（Windows 2000 或更高版本）。
      public static int MediaStop = 178;
      //
      // 摘要:
      //     媒体播放暂停键（Windows 2000 或更高版本）。
      public static int MediaPlayPause = 179;
      //
      // 摘要:
      //     启动邮件键（Windows 2000 或更高版本）。
      public static int LaunchMail = 180;
      //
      // 摘要:
      //     选择媒体键（Windows 2000 或更高版本）。
      public static int SelectMedia = 181;
      //
      // 摘要:
      //     启动应用程序一键（Windows 2000 或更高版本）。
      public static int LaunchApplication1 = 182;
      //
      // 摘要:
      //     启动应用程序二键（Windows 2000 或更高版本）。
      public static int LaunchApplication2 = 183;
      //
      // 摘要:
      //     The OEM 1 key.
      public static int Oem1 = 186;
      //
      // 摘要:
      //     美式标准键盘上的 OEM 分号键（Windows 2000 或更高版本）。
      public static int OemSemicolon = 186;
      //
      // 摘要:
      //     任何国家/地区键盘上的 OEM 加号键（Windows 2000 或更高版本）。
      public static int Oemplus = 187;
      //
      // 摘要:
      //     任何国家/地区键盘上的 OEM 逗号键（Windows 2000 或更高版本）。
      public static int Oemcomma = 188;
      //
      // 摘要:
      //     任何国家/地区键盘上的 OEM 减号键（Windows 2000 或更高版本）。
      public static int OemMinus = 189;
      //
      // 摘要:
      //     任何国家/地区键盘上的 OEM 句点键（Windows 2000 或更高版本）。
      public static int OemPeriod = 190;
      //
      // 摘要:
      //     美式标准键盘上的 OEM 问号键（Windows 2000 或更高版本）。
      public static int OemQuestion = 191;
      //
      // 摘要:
      //     The OEM 2 key.
      public static int Oem2 = 191;
      //
      // 摘要:
      //     美式标准键盘上的 OEM 波形符键（Windows 2000 或更高版本）。
      public static int Oemtilde = 192;
      //
      // 摘要:
      //     The OEM 3 key.
      public static int Oem3 = 192;
      //
      // 摘要:
      //     The OEM 4 key.
      public static int Oem4 = 219;
      //
      // 摘要:
      //     美式标准键盘上的 OEM 左括号键（Windows 2000 或更高版本）。
      public static int OemOpenBrackets = 219;
      //
      // 摘要:
      //     美式标准键盘上的 OEM 管道键（Windows 2000 或更高版本）。
      public static int OemPipe = 220;
      //
      // 摘要:
      //     The OEM 5 key.
      public static int Oem5 = 220;
      //
      // 摘要:
      //     The OEM 6 key.
      public static int Oem6 = 221;
      //
      // 摘要:
      //     美式标准键盘上的 OEM 右括号键（Windows 2000 或更高版本）。
      public static int OemCloseBrackets = 221;
      //
      // 摘要:
      //     The OEM 7 key.
      public static int Oem7 = 222;
      //
      // 摘要:
      //     美式标准键盘上的 OEM 单/双引号键（Windows 2000 或更高版本）。
      public static int OemQuotes = 222;
      //
      // 摘要:
      //     The OEM 8 key.
      public static int Oem8 = 223;
      //
      // 摘要:
      //     The OEM 102 key.
      public static int Oem102 = 226;
      //
      // 摘要:
      //     RT 102 键的键盘上的 OEM 尖括号或反斜杠键（Windows 2000 或更高版本）。
      public static int OemBackslash = 226;
      //
      // 摘要:
      //     Process Key 键。
      public static int ProcessKey = 229;
      //
      // 摘要:
      //     用于将 Unicode 字符当作键击传递。Packet 键值是用于非键盘输入法的 32 位虚拟键值的低位字。
      public static int Packet = 231;
      //
      // 摘要:
      //     The ATTN key.
      public static int Attn = 246;
      //
      // 摘要:
      //     Crsel 键。
      public static int Crsel = 247;
      //
      // 摘要:
      //     Exsel 键。
      public static int Exsel = 248;
      //
      // 摘要:
      //     ERASE EOF 键。
      public static int EraseEof = 249;
      //
      // 摘要:
      //     The PLAY key.
      public static int Play = 250;
      //
      // 摘要:
      //     The ZOOM key.
      public static int Zoom = 251;
      //
      // 摘要:
      //     保留以备将来使用的常数。
      public static int NoName = 252;
      //
      // 摘要:
      //     PA1 键。
      public static int Pa1 = 253;
      //
      // 摘要:
      //     Clear 键。
      public static int OemClear = 254;
      //
      // 摘要:
      //     从键值提取键代码的位屏蔽。
      public static int KeyCode = 65535;
      //
      // 摘要:
      //     Shift 修改键。
      public static int Shift = 65536;
      //
      // 摘要:
      //     Ctrl 修改键。
      public static int Control = 131072;
      //
      // 摘要:
      //     Alt 修改键。
      public static int Alt = 262144;
   }
}
