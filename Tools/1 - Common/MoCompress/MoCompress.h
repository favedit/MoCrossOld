#pragma once

using namespace System;

namespace MoCompress
{
   //============================================================
	public ref class RCompressPicture{
   public:
      static int Compress(array<System::Byte>^ output, int outputOffset, int outputLength, array<System::Byte>^ input, int inputOffset, int inputLength);
      static int Uncompress(array<System::Byte>^ output, int outputOffset, int outputLength, array<System::Byte>^ input, int inputOffset, int inputLength);
	};

   //============================================================
   public ref class RCompressLzma
   {
   public:
      static int Compress(array<System::Byte>^ output, int outputOffset, int outputLength, array<System::Byte>^ input, int inputOffset, int inputLength);
      static int Uncompress(array<System::Byte>^ output, int outputOffset, int outputLength, array<System::Byte>^ input, int inputOffset, int inputLength);
   };

   //============================================================
   public ref class RCompressJpg
   {
   public:
      static int Compress(array<System::Byte>^ output, int outputOffset, int outputLength, array<System::Byte>^ input, int inputOffset, int inputLength, int width, int height, int quality, int channelCd);
      static int Uncompress(array<System::Byte>^ output, int outputOffset, int outputLength, array<System::Byte>^ input, int inputOffset, int inputLength);
   };
}
