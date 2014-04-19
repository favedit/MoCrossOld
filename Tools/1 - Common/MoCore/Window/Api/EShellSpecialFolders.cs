using System;
using System.Collections.Generic;
using System.Text;

namespace MO.Core.Window.Api {

   [Flags()]
   public enum EShellSpecialFolders {
      DESKTOP = 0x0000,		 // <desktop>
      INTERNET = 0x0001,
      PROGRAMS = 0x0002,        // Start Menu\Programs
      CONTROLS = 0x0003,        // My Computer\Control Panel
      PRINTERS = 0x0004,        // My Computer\Printers
      PERSONAL = 0x0005,        // My Documents
      FAVORITES = 0x0006,        // <user name>\Favorites
      STARTUP = 0x0007,        // Start Menu\Programs\Startup
      RECENT = 0x0008,        // <user name>\Recent
      SENDTO = 0x0009,        // <user name>\SendTo
      BITBUCKET = 0x000a,        // <desktop>\Recycle Bin
      STARTMENU = 0x000b,        // <user name>\Start Menu
      MYDOCUMENTS = 0x000c,        // logical "My Documents" desktop icon
      MYMUSIC = 0x000d,        // "My Music" folder
      MYVIDEO = 0x000e,        // "My Videos" folder
      DESKTOPDIRECTORY = 0x0010,        // <user name>\Desktop
      DRIVES = 0x0011,        // My Computer
      NETWORK = 0x0012,        // Network Neighborhood (My Network Places)
      NETHOOD = 0x0013,        // <user name>\nethood
      FONTS = 0x0014,        // windows\fonts
      TEMPLATES = 0x0015,
      COMMON_STARTMENU = 0x0016,        // All Users\Start Menu
      COMMON_PROGRAMS = 0X0017,        // All Users\Start Menu\Programs
      COMMON_STARTUP = 0x0018,        // All Users\Startup
      COMMON_DESKTOPDIRECTORY = 0x0019,        // All Users\Desktop
      APPDATA = 0x001a,        // <user name>\Application Data
      PRINTHOOD = 0x001b,        // <user name>\PrintHood
      LOCAL_APPDATA = 0x001c,        // <user name>\Local Settings\Applicaiton Data (non roaming)
      ALTSTARTUP = 0x001d,        // non localized startup
      COMMON_ALTSTARTUP = 0x001e,        // non localized common startup
      COMMON_FAVORITES = 0x001f,
      INTERNET_CACHE = 0x0020,
      COOKIES = 0x0021,
      HISTORY = 0x0022,
      COMMON_APPDATA = 0x0023,        // All Users\Application Data
      WINDOWS = 0x0024,        // GetWindowsDirectory()
      SYSTEM = 0x0025,        // GetSystemDirectory()
      PROGRAM_FILES = 0x0026,        // C:\Program Files
      MYPICTURES = 0x0027,        // C:\Program Files\My Pictures
      PROFILE = 0x0028,        // USERPROFILE
      SYSTEMX86 = 0x0029,        // x86 system directory on RISC
      PROGRAM_FILESX86 = 0x002a,        // x86 C:\Program Files on RISC
      PROGRAM_FILES_COMMON = 0x002b,        // C:\Program Files\Common
      PROGRAM_FILES_COMMONX86 = 0x002c,        // x86 Program Files\Common on RISC
      COMMON_TEMPLATES = 0x002d,        // All Users\Templates
      COMMON_DOCUMENTS = 0x002e,        // All Users\Documents
      COMMON_ADMINTOOLS = 0x002f,        // All Users\Start Menu\Programs\Administrative Tools
      ADMINTOOLS = 0x0030,        // <user name>\Start Menu\Programs\Administrative Tools
      CONNECTIONS = 0x0031,        // Network and Dial-up Connections
      COMMON_MUSIC = 0x0035,        // All Users\My Music
      COMMON_PICTURES = 0x0036,        // All Users\My Pictures
      COMMON_VIDEO = 0x0037,        // All Users\My Video
      RESOURCES = 0x0038,        // Resource Direcotry
      RESOURCES_LOCALIZED = 0x0039,        // Localized Resource Direcotry
      COMMON_OLINKS = 0x003a,        // Links to All Users OEM specific apps
      CDBURN_AREA = 0x003b,        // USERPROFILE\Local Settings\Application Data\Microsoft\CD Burning
      COMPUTERSNEARME = 0x003d,        // Computers Near Me (computered from Workgroup membership)
      FLAG_CREATE = 0x8000,        // combine with  value to force folder creation in SHGetFolderPath()
      FLAG_DONT_VERIFY = 0x4000,        // combine with  value to return an unverified folder path
      FLAG_NO_ALIAS = 0x1000,        // combine with  value to insure non-alias versions of the pidl
      FLAG_PER_USER_INIT = 0x0800,        // combine with  value to indicate per-user init (eg. upgrade)
      FLAG_MASK = 0xFF00,        // mask for all possible flag values
   }

}
