using System;
using System.Collections.Generic;
using System.Text;

namespace MO.Core.Window.Api {

   public enum ECsidl {
      ///   <summary>   
      ///   Desktop   
      ///   </summary>   
      CSIDL_DESKTOP = 0x0000,
      ///   <summary>   
      ///   Internet   Explorer   (icon   on   desktop)   
      ///   </summary>   
      CSIDL_INTERNET = 0x0001,
      ///   <summary>   
      ///   Start   Menu\Programs   
      ///   </summary>   
      CSIDL_PROGRAMS = 0x0002,
      ///   <summary>   
      ///   My   Computer\Control   Panel   
      ///   </summary>   
      CSIDL_CONTROLS = 0x0003,
      ///   <summary>   
      ///   My   Computer\Printers   
      ///   </summary>   
      CSIDL_PRINTERS = 0x0004,
      ///   <summary>   
      ///   My   Documents   
      ///   </summary>   
      CSIDL_PERSONAL = 0x0005,
      ///   <summary>   
      ///   user   name\Favorites   
      ///   </summary>   
      CSIDL_FAVORITES = 0x0006,
      ///   <summary>   
      ///   Start   Menu\Programs\Startup   
      ///   </summary>   
      CSIDL_STARTUP = 0x0007,
      ///   <summary>   
      ///   user   name\Recent   
      ///   </summary>   
      CSIDL_RECENT = 0x0008,
      ///   <summary>   
      ///   user   name\SendTo   
      ///   </summary>   
      CSIDL_SENDTO = 0x0009,
      ///   <summary>   
      ///   desktop\Recycle   Bin   
      ///   </summary>   
      CSIDL_BITBUCKET = 0x000a,
      ///   <summary>   
      ///   user   name\Start   Menu   
      ///   </summary>   
      CSIDL_STARTMENU = 0x000b,
      ///   <summary>   
      ///   logical   "My   Documents"   desktop   icon   
      ///   </summary>   
      CSIDL_MYDOCUMENTS = 0x000c,
      ///   <summary>   
      ///   "My   Music"   folder   
      ///   </summary>   
      CSIDL_MYMUSIC = 0x000d,
      ///   <summary>   
      ///   "My   Videos"   folder   
      ///   </summary>   
      CSIDL_MYVIDEO = 0x000e,
      ///   <summary>   
      ///   user   name\Desktop   
      ///   </summary>   
      CSIDL_DESKTOPDIRECTORY = 0x0010,
      ///   <summary>   
      ///   My   Computer   
      ///   </summary>   
      CSIDL_DRIVES = 0x0011,
      ///   <summary>   
      ///   Network   Neighborhood   (My   Network   Places)   
      ///   </summary>   
      CSIDL_NETWORK = 0x0012,
      ///   <summary>   
      ///   user   name>nethood   
      ///   </summary>   
      CSIDL_NETHOOD = 0x0013,
      ///   <summary>   
      ///   windows\fonts   
      ///   </summary>   
      CSIDL_FONTS = 0x0014,
      CSIDL_TEMPLATES = 0x0015,
      ///   <summary>   
      ///   All   Users\Start   Menu   
      ///   </summary>   
      CSIDL_COMMON_STARTMENU = 0x0016,
      ///   <summary>   
      ///   All   Users\Start   Menu\Programs   
      ///   </summary>   
      CSIDL_COMMON_PROGRAMS = 0X0017,
      ///   <summary>   
      ///   All   Users\Startup   
      ///   </summary>   
      CSIDL_COMMON_STARTUP = 0x0018,

      ///   <summary>   
      ///   All   Users\Desktop   
      ///   </summary>   
      CSIDL_COMMON_DESKTOPDIRECTORY = 0x0019,
      ///   <summary>   
      ///   user   name\Application   Data   
      ///   </summary>   
      CSIDL_APPDATA = 0x001a,
      ///   <summary>   
      ///   user   name\PrintHood   
      ///   </summary>   
      CSIDL_PRINTHOOD = 0x001b,
      ///   <summary>   
      ///   user   name\Local   Settings\Applicaiton   Data   (non   roaming)   
      ///   </summary>   
      CSIDL_LOCAL_APPDATA = 0x001c,
      ///   <summary>   
      ///   non   localized   startup   
      ///   </summary>   
      CSIDL_ALTSTARTUP = 0x001d,
      ///   <summary>   
      ///   non   localized   common   startup   
      ///   </summary>   
      CSIDL_COMMON_ALTSTARTUP = 0x001e,
      CSIDL_COMMON_FAVORITES = 0x001f,
      CSIDL_INTERNET_CACHE = 0x0020,
      CSIDL_COOKIES = 0x0021,
      CSIDL_HISTORY = 0x0022,
      ///   <summary>   
      ///   All   Users\Application   Data   
      ///   </summary>   
      CSIDL_COMMON_APPDATA = 0x0023,
      ///   <summary>   
      ///   GetWindowsDirectory()   
      ///   </summary>   
      CSIDL_WINDOWS = 0x0024,
      ///   <summary>   
      ///   GetSystemDirectory()   
      ///   </summary>   
      CSIDL_SYSTEM = 0x0025,
      ///   <summary>   
      ///   C:\Program   Files   
      ///   </summary>   
      CSIDL_PROGRAM_FILES = 0x0026,
      ///   <summary>   
      ///   C:\Program   Files\My   Pictures   
      ///   </summary>   
      CSIDL_MYPICTURES = 0x0027,
      ///   <summary>   
      ///   USERPROFILE   
      ///   </summary>   
      CSIDL_PROFILE = 0x0028,
      ///   <summary>   
      ///   x86   system   directory   on   RISC   
      ///   </summary>   
      CSIDL_SYSTEMX86 = 0x0029,
      ///   <summary>   
      ///   x86   C:\Program   Files   on   RISC   
      ///   </summary>   
      CSIDL_PROGRAM_FILESX86 = 0x002a,
      ///   <summary>   
      ///   C:\Program   Files\Common   
      ///   </summary>   
      CSIDL_PROGRAM_FILES_COMMON = 0x002b,
      ///   <summary>   
      ///   x86   Program   Files\Common   on   RISC   
      ///   </summary>   
      CSIDL_PROGRAM_FILES_COMMONX86 = 0x002c,
      ///   <summary>   
      ///   All   Users\Templates   
      ///   </summary>   
      CSIDL_COMMON_TEMPLATES = 0x002d,
      ///   <summary>   
      ///   All   Users\Documents   
      ///   </summary>   
      CSIDL_COMMON_DOCUMENTS = 0x002e,
      ///   <summary>   
      ///   All   Users\Start   Menu\Programs\Administrative   Tools   
      ///   </summary>   
      CSIDL_COMMON_ADMINTOOLS = 0x002f,
      ///   <summary>   
      ///   user   name\Start   Menu\Programs\Administrative   Tools   
      ///   </summary>   
      CSIDL_ADMINTOOLS = 0x0030,
      ///   <summary>   
      ///   Network   and   Dial-up   Connections   
      ///   </summary>   
      CSIDL_CONNECTIONS = 0x0031,
      ///   <summary>   
      ///   All   Users\My   Music   
      ///   </summary>   
      CSIDL_COMMON_MUSIC = 0x0035,
      ///   <summary>   
      ///   All   Users\My   Pictures   
      ///   </summary>   
      CSIDL_COMMON_PICTURES = 0x0036,
      ///   <summary>   
      ///   All   Users\My   Video   
      ///   </summary>   
      CSIDL_COMMON_VIDEO = 0x0037,
      ///   <summary>   
      ///   Resource   Direcotry   
      ///   </summary>   
      CSIDL_RESOURCES = 0x0038,
      ///   <summary>   
      ///   Localized   Resource   Direcotry   
      ///   </summary>   
      CSIDL_RESOURCES_LOCALIZED = 0x0039,
      ///   <summary>   
      ///   Links   to   All   Users   OEM   specific   apps   
      ///   </summary>   
      CSIDL_COMMON_OEM_LINKS = 0x003a,
      ///   <summary>   
      ///   USERPROFILE\Local   Settings\Application   Data\Microsoft\CD   Burning   
      ///   </summary>   
      CSIDL_CDBURN_AREA = 0x003b,
      ///   <summary>   
      ///   Computers   Near   Me   (computered   from   Workgroup   membership)   
      ///   </summary>   
      CSIDL_COMPUTERSNEARME = 0x003d,
      ///   <summary>   
      ///   combine   with   CSIDL_   value   to   force   folder   creation   in   SHGetFolderPath()   
      ///   </summary>   
      CSIDL_FLAG_CREATE = 0x8000,
      ///   <summary>   
      ///   combine   with   CSIDL_   value   to   return   an   unverified   folder   path   
      ///   </summary>   
      CSIDL_FLAG_DONT_VERIFY = 0x4000,
      ///   <summary>   
      ///   combine   with   CSIDL_   value   to   insure   non-alias   versions   of   the   pidl   
      ///   </summary>   
      CSIDL_FLAG_NO_ALIAS = 0x1000,
      ///   <summary>   
      ///   combine   with   CSIDL_   value   to   indicate   per-user   init   (eg.   upgrade)   
      ///   </summary>   
      CSIDL_FLAG_PER_USER_INIT = 0x0800,
      ///   <summary>   
      ///   mask   for   all   possible     
      ///   </summary>   
      CSIDL_FLAG_MASK = 0xFF00,
   }

}
