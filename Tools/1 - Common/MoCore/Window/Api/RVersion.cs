using System;
using System.Collections.Generic;
using System.Text;
using System.Runtime.InteropServices;

namespace MO.Core.Window.Api.Version {
   public class RVersion {
      [DllImport("version.dll")]
      private static extern int GetFileVersionInfoSize(string lpstrFilename, int lpdwHandle);

      [DllImport("version.dll")]
      private static extern int GetFileVersionInfo(string lpstrFilename, int dwHandle, int dwLen, byte[] lpData);

      [DllImport("version.dll")]
      private static extern int VerQueryValue(byte[] lpVersionDataBuffer, string lpSubBlock, byte[] lpBuffer, ref   int lpBufferLength);
   }
}
