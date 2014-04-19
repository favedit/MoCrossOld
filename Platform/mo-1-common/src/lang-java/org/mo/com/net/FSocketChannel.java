package org.mo.com.net;

import java.nio.channels.SocketChannel;
import org.mo.com.io.FByteStream;
import org.mo.com.system.FListeners;

public class FSocketChannel
{
   protected FByteStream _inputBuffer = new FByteStream();

   protected FListeners _listenersRead = new FListeners();

   protected FListeners _listenersWrite = new FListeners();

   protected FByteStream _outputBuffer = new FByteStream();

   protected SocketChannel _socketChannel;

   public FSocketChannel(SocketChannel socketChannel){
      _socketChannel = socketChannel;
   }

   public FByteStream inputBuffer(){
      return _inputBuffer;
   }

   public FListeners listenersRead(){
      return _listenersRead;
   }

   public FListeners listenersWrite(){
      return _listenersWrite;
   }

   public FByteStream outputBuffer(){
      return _outputBuffer;
   }

   public SocketChannel socketChannel(){
      return _socketChannel;
   }
}
