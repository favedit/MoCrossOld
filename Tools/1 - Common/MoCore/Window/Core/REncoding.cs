using System;
using System.Text;
using System.IO;
using System.Runtime.InteropServices;
using MO.Core.Window.Api;
using System.Collections.Generic;
using MO.Common.Lang;

namespace MO.Core.Window.Core {

   public class REncoding {

      public static int[] PreferedEncodingsForStream;

      public static int[] PreferedEncodings;

      public static int[] AllEncodings;

      static REncoding() {
         List<int> streamEcodings = new List<int>();
         List<int> allEncodings = new List<int>();
         List<int> mimeEcodings = new List<int>();
         // Ansi
         streamEcodings.Add(Encoding.ASCII.CodePage);
         mimeEcodings.Add(Encoding.ASCII.CodePage);
         allEncodings.Add(Encoding.ASCII.CodePage);
         allEncodings.Add(Encoding.Default.CodePage);
         if (Encoding.Default.IsSingleByte) {
            streamEcodings.Add(Encoding.Default.CodePage);
            mimeEcodings.Add(Encoding.Default.CodePage);
         }
         // SJis
         allEncodings.Add(50220);
         mimeEcodings.Add(50220);
         // Uncode
         streamEcodings.Add(Encoding.Unicode.CodePage);
         foreach (EncodingInfo enc in Encoding.GetEncodings()) {
            if (!streamEcodings.Contains(enc.CodePage)) {
               Encoding encoding = Encoding.GetEncoding(enc.CodePage);
               if (encoding.GetPreamble().Length > 0)
                  streamEcodings.Add(enc.CodePage);
            }
         }
         // stream is done here
         PreferedEncodingsForStream = streamEcodings.ToArray();
         // all singlebyte encodings
         foreach (EncodingInfo enc in Encoding.GetEncodings()) {
            if (!enc.GetEncoding().IsSingleByte)
               continue;
            if (!allEncodings.Contains(enc.CodePage))
               allEncodings.Add(enc.CodePage);
            // only add iso and IBM encodings to mime encodings 
            if (enc.CodePage <= 1258) {
               mimeEcodings.Add(enc.CodePage);
            }
         }
         // Multibyte
         foreach (EncodingInfo enc in Encoding.GetEncodings()) {
            if (!enc.GetEncoding().IsSingleByte) {
               if (!allEncodings.Contains(enc.CodePage)) {
                  allEncodings.Add(enc.CodePage);
               }
               if (enc.CodePage <= 1258) {
                  mimeEcodings.Add(enc.CodePage);
               }
            }
         }
         // Add unicodes
         mimeEcodings.Add(Encoding.Unicode.CodePage);
         PreferedEncodings = mimeEcodings.ToArray();
         AllEncodings = allEncodings.ToArray();
      }

      public static bool IsAscii(string data) {
         if (data == null || data.Length == 0) {
            return true;
         }
         foreach (char c in data) {
            if ((int)c > 127) {
               return false;
            }
         }
         return true;
      }

      public static Encoding DetectInputCodepage(byte[] input) {
         try {
            Encoding[] encodings = DetectInputAllCodepages(input);
            if (encodings != null && encodings.Length > 0) {
               // Single
               if (encodings.Length == 1) {
                  return encodings[0];
               }
               // Shift-Jis
               foreach(Encoding encoding in encodings){
                  if (String.Equals(encoding.WebName, "shift_jis", StringComparison.CurrentCultureIgnoreCase)) {
                     return encoding;
                  }
               }
               // Unicode
               if (RArray<Encoding>.Contains(encodings, Encoding.Unicode)) {
                  return Encoding.Unicode;
               }
               // BigEndianUnicode
               if (RArray<Encoding>.Contains(encodings, Encoding.BigEndianUnicode)) {
                  return Encoding.BigEndianUnicode;
               }
               // UTF32
               if (RArray<Encoding>.Contains(encodings, Encoding.UTF32)) {
                  return Encoding.UTF32;
               }
               // UTF8
               if (RArray<Encoding>.Contains(encodings, Encoding.UTF8)) {
                  return Encoding.UTF8;
               }
               // UTF7
               if (RArray<Encoding>.Contains(encodings, Encoding.UTF7)) {
                  return Encoding.UTF7;
               }
               return encodings[0];
            }
            return Encoding.Default;
         } catch (COMException) {
            return Encoding.Default;
         }
      }

      public static Encoding[] DetectInputAllCodepages(byte[] input) {
         if (input == null) {
            throw new ArgumentNullException("input");
         }
         if (input.Length == 0) {
            return new Encoding[] { Encoding.ASCII };
         }
         if (input.Length < 256) {
            byte[] newInput = new byte[256];
            int steps = 256 / input.Length;
            for (int i = 0; i < steps; i++) {
               Array.Copy(input, 0, newInput, input.Length * i, input.Length);
            }
            int rest = 256 % input.Length;
            if (rest > 0) {
               Array.Copy(input, 0, newInput, steps * input.Length, rest);
            }
            input = newInput;
         }
         IMultiLanguage2 multilang2 = new FMultiLanguageClass();
         if (multilang2 == null) {
            throw new System.Runtime.InteropServices.COMException("Failed to get IMultilang2");
         }
         FObjects<Encoding> result = new FObjects<Encoding>();
         try {
            SDetectEncodingInfo[] detectedEncdings = new SDetectEncodingInfo[RInt.SIZE_1K];
            int scores = detectedEncdings.Length;
            int srcLen = input.Length;
            SMlDetectCp options = SMlDetectCp.MLDETECTCP_NONE;
            multilang2.DetectInputCodepage(options, 0, ref input[0], ref srcLen, ref detectedEncdings[0], ref scores);
            if (scores > 0) {
               for (int i = 0; i < scores; i++) {
                  result.Push(Encoding.GetEncoding((int)detectedEncdings[i].nCodePage));
               }
            }
         } finally {
            Marshal.FinalReleaseComObject(multilang2);
         }
         return result.ToArray();
      }

      public static Encoding[] DetectInputCodepages(byte[] input, int maxEncodings) {
         if (maxEncodings < 1) {
            throw new ArgumentOutOfRangeException("at least one encoding must be returend", "maxEncodings");
         }
         if (input == null) {
            throw new ArgumentNullException("input");
         }
         if (input.Length == 0) {
            return new Encoding[] { Encoding.ASCII };
         }
         if (input.Length < 256) {
            byte[] newInput = new byte[256];
            int steps = 256 / input.Length;
            for (int i = 0; i < steps; i++) {
               Array.Copy(input, 0, newInput, input.Length * i, input.Length);
            }
            int rest = 256 % input.Length;
            if (rest > 0) {
               Array.Copy(input, 0, newInput, steps * input.Length, rest);
            }
            input = newInput;
         }
         List<Encoding> result = new List<Encoding>();
         IMultiLanguage2 multilang2 = new FMultiLanguageClass();
         if (multilang2 == null) {
            throw new System.Runtime.InteropServices.COMException("Failed to get IMultilang2");
         }
         try {
            SDetectEncodingInfo[] detectedEncdings = new SDetectEncodingInfo[maxEncodings];
            int scores = detectedEncdings.Length;
            int srcLen = input.Length;
            SMlDetectCp options = SMlDetectCp.MLDETECTCP_NONE;
            multilang2.DetectInputCodepage(options, 0, ref input[0], ref srcLen, ref detectedEncdings[0], ref scores);
            if (scores > 0) {
               for (int i = 0; i < scores; i++) {
                  result.Add(Encoding.GetEncoding((int)detectedEncdings[i].nCodePage));
               }
            }
         } finally {
            Marshal.FinalReleaseComObject(multilang2);
         }
         return result.ToArray();
      }


      public static Encoding GetMostEfficientEncoding(string input) {
         return GetMostEfficientEncoding(input, PreferedEncodings);
      }

      public static Encoding GetMostEfficientEncodingForStream(string input) {
         return GetMostEfficientEncoding(input, PreferedEncodingsForStream);
      }

      public static Encoding GetMostEfficientEncoding(string input, int[] preferedEncodings) {
         Encoding enc = DetectOutgoingEncoding(input, preferedEncodings, true);
         // unicode.. hmmm... check for smallest encoding
         if (enc.CodePage == Encoding.Unicode.CodePage) {
            int byteCount = Encoding.UTF7.GetByteCount(input);
            enc = Encoding.UTF7;
            int bestByteCount = byteCount;

            // utf8 smaller?
            byteCount = Encoding.UTF8.GetByteCount(input);
            if (byteCount < bestByteCount) {
               enc = Encoding.UTF8;
               bestByteCount = byteCount;
            }

            // unicode smaller?
            byteCount = Encoding.Unicode.GetByteCount(input);
            if (byteCount < bestByteCount) {
               enc = Encoding.Unicode;
               bestByteCount = byteCount;
            }
         } else {

         }
         return enc;
      }

      public static Encoding DetectOutgoingEncoding(string input) {
         return DetectOutgoingEncoding(input, PreferedEncodings, true);
      }

      public static Encoding DetectOutgoingStreamEncoding(string input) {
         return DetectOutgoingEncoding(input, PreferedEncodingsForStream, true);
      }

      public static Encoding[] DetectOutgoingEncodings(string input) {
         return DetectOutgoingEncodings(input, PreferedEncodings, true);
      }

      public static Encoding[] DetectOutgoingStreamEncodings(string input) {
         return DetectOutgoingEncodings(input, PreferedEncodingsForStream, true);
      }

      private static Encoding DetectOutgoingEncoding(string input, int[] preferedEncodings, bool preserveOrder) {
         if (input == null)
            throw new ArgumentNullException("input");
         if (input.Length == 0)
            return Encoding.ASCII;

         Encoding result = Encoding.ASCII;
         IMultiLanguage3 multilang3 = new FMultiLanguageClass();
         if (multilang3 == null)
            throw new System.Runtime.InteropServices.COMException("Failed to get IMultilang3");
         try {
            int[] resultCodePages = new int[preferedEncodings != null ? preferedEncodings.Length : Encoding.GetEncodings().Length];
            uint detectedCodepages = (uint)resultCodePages.Length;
            ushort specialChar = (ushort)'?';
            IntPtr pPrefEncs = preferedEncodings == null ? IntPtr.Zero : Marshal.AllocCoTaskMem(sizeof(uint) * preferedEncodings.Length);
            IntPtr pDetectedEncs = Marshal.AllocCoTaskMem(sizeof(uint) * resultCodePages.Length);

            try {
               if (preferedEncodings != null)
                  Marshal.Copy(preferedEncodings, 0, pPrefEncs, preferedEncodings.Length);

               Marshal.Copy(resultCodePages, 0, pDetectedEncs, resultCodePages.Length);

               SMlCpf options = SMlCpf.MLDETECTF_VALID_NLS;
               if (preserveOrder)
                  options |= SMlCpf.MLDETECTF_PRESERVE_ORDER;

               if (preferedEncodings != null)
                  options |= SMlCpf.MLDETECTF_PREFERRED_ONLY;

               multilang3.DetectOutboundCodePage(options,
                   input, (uint)input.Length,
                   pPrefEncs, (uint)(preferedEncodings == null ? 0 : preferedEncodings.Length),

                   pDetectedEncs, ref detectedCodepages,
                   ref specialChar);

               if (detectedCodepages > 0) {
                  int[] theResult = new int[detectedCodepages];
                  Marshal.Copy(pDetectedEncs, theResult, 0, theResult.Length);
                  result = Encoding.GetEncoding(theResult[0]);
               }

            } finally {
               if (pPrefEncs != IntPtr.Zero)
                  Marshal.FreeCoTaskMem(pPrefEncs);
               Marshal.FreeCoTaskMem(pDetectedEncs);
            }
         } finally {
            Marshal.FinalReleaseComObject(multilang3);
         }
         return result;
      }

      public static Encoding[] DetectOutgoingEncodings(string input, int[] preferedEncodings, bool preserveOrder) {

         if (input == null)
            throw new ArgumentNullException("input");

         if (input.Length == 0)
            return new Encoding[] { Encoding.ASCII };

         List<Encoding> result = new List<Encoding>();

         IMultiLanguage3 multilang3 = new FMultiLanguageClass();
         if (multilang3 == null)
            throw new System.Runtime.InteropServices.COMException("Failed to get IMultilang3");
         try {
            int[] resultCodePages = new int[preferedEncodings.Length];
            uint detectedCodepages = (uint)resultCodePages.Length;
            ushort specialChar = (ushort)'?';

            IntPtr pPrefEncs = Marshal.AllocCoTaskMem(sizeof(uint) * preferedEncodings.Length);
            IntPtr pDetectedEncs = preferedEncodings == null ? IntPtr.Zero : Marshal.AllocCoTaskMem(sizeof(uint) * resultCodePages.Length);

            try {
               if (preferedEncodings != null)
                  Marshal.Copy(preferedEncodings, 0, pPrefEncs, preferedEncodings.Length);

               Marshal.Copy(resultCodePages, 0, pDetectedEncs, resultCodePages.Length);

               SMlCpf options = SMlCpf.MLDETECTF_VALID_NLS | SMlCpf.MLDETECTF_PREFERRED_ONLY;
               if (preserveOrder) {
                  options |= SMlCpf.MLDETECTF_PRESERVE_ORDER;
               }

               if (preferedEncodings != null) {
                  options |= SMlCpf.MLDETECTF_PREFERRED_ONLY;
               }

               multilang3.DetectOutboundCodePage(options,
                   input, (uint)input.Length,
                   pPrefEncs, (uint)(preferedEncodings == null ? 0 : preferedEncodings.Length),
                   pDetectedEncs, ref detectedCodepages,
                   ref specialChar);

               if (detectedCodepages > 0) {
                  int[] theResult = new int[detectedCodepages];
                  Marshal.Copy(pDetectedEncs, theResult, 0, theResult.Length);

                  for (int i = 0; i < detectedCodepages; i++)
                     result.Add(Encoding.GetEncoding(theResult[i]));

               }

            } finally {
               if (pPrefEncs != IntPtr.Zero)
                  Marshal.FreeCoTaskMem(pPrefEncs);
               Marshal.FreeCoTaskMem(pDetectedEncs);
            }
         } finally {
            Marshal.FinalReleaseComObject(multilang3);
         }
         return result.ToArray();
      }

      public static string ReadTextFile(string path) {
         if (path == null) {
            throw new ArgumentNullException("path");
         }
         byte[] data = File.ReadAllBytes(path);
         Encoding encoding = DetectInputCodepage(data);
         return encoding.GetString(data);
      }

      public const int MIN_BYTES = RInt.SIZE_16K;

      public static Encoding DetectTextFile(string path) {
         if (path == null) {
            throw new ArgumentNullException("path");
         }
         using (Stream reader = File.Open(path, FileMode.Open)) {
            byte[] buf = new byte[Math.Min(reader.Length, MIN_BYTES)];
            reader.Read(buf, 0, buf.Length);
            return DetectInputCodepage(buf);
         }
      }

      public static Encoding[] DetectTextFileAll(string path) {
         if (path == null) {
            throw new ArgumentNullException("path");
         }
         using (Stream reader = File.Open(path, FileMode.Open)) {
            try {
               byte[] buf = new byte[Math.Min(reader.Length, MIN_BYTES)];
               reader.Read(buf, 0, buf.Length);
               return DetectInputAllCodepages(buf);
            } catch (COMException) {
               return new Encoding[] { Encoding.Default };
            }
         }
      }

      public static StreamReader OpenTextFile(string path) {
         if (path == null) {
            throw new ArgumentNullException("path");
         }
         return OpenTextStream(File.Open(path, FileMode.Open));
      }

      public static StreamReader OpenTextStream(Stream stream) {
         if (stream == null) {
            throw new ArgumentNullException("Stream");
         }
         if (!stream.CanSeek) {
            throw new ArgumentException("The stream must support seek operations", "stream");
         }
         stream.Seek(0, SeekOrigin.Begin);
         byte[] buf = new byte[Math.Min(stream.Length, 512)];
         stream.Read(buf, 0, buf.Length);
         Encoding detectedEncoding = DetectInputCodepage(buf);
         stream.Seek(0, SeekOrigin.Begin);
         return new StreamReader(stream, detectedEncoding);

      }
   }
}
