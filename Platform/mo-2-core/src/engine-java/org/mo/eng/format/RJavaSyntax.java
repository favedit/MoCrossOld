/*
 * @(#)FJavaSyntax.java
 *
 * Copyright 2004 Java Frame Studio Corporation.
 * All Rights Reserved.
 *
 */
package org.mo.eng.format;

/**
 * <p>Java代码格式化工具类</p>
 *
 * @author FEDT
 * @version 1.00 - 2005/02/18
 */
public class RJavaSyntax
{
   // 代码注释颜色
   public final static String COMMENT_COLOR = "green";

   // 字符串颜色
   public final static String STRING_COLOR = "#800000";

   // 关键字颜色
   public final static String KEY_COLOR = "blue";

   // 例外错误颜色
   public final static String EXCEPTION_COLOR = "red";

   // 字符串分隔符
   public final static String SPLITTER = " `~!@#$%^&*()-=\\+|[]{};':\",./<>?\t\r\n";

   // 关键字列表
   public final static String KEY_STRING = " abstract" + " break byte boolean" + " catch case class char continue" + " default double do" + " else extends" + " false final float for finally" + " if import implements int interface instanceof"
         + " long length" + " native new null" + " package private protected public" + " return" + " switch synchronized short static super" + " try true this throw throws threadsafe transient" + " void" + " while ";

   // 例外列表
   public final static String EXCEPTION_STRING = " AbstractMethodError AccessException Acl AclEntry AclNotFoundException ActionEvent ActionListener Adjustable AdjustmentEvent AdjustmentListener Adler32 AlreadyBoundException Applet AppletContext AppletStub AreaAveragingScaleFilter ArithmeticException Array ArrayIndexOutOfBoundsException ArrayStoreException AudioClip AWTError AWTEvent AWTEventMulticaster AWTException"
         + " BeanDescriptor BeanInfo Beans BigDecimal BigInteger BindException BitSet Boolean BorderLayout BreakIterator BufferedInputStream BufferedOutputStream BufferedReader BufferedWriter Button ButtonPeer Byte ByteArrayInputStream ByteArrayOutputStream"
         + " Calendar CallableStatement Canvas CanvasPeer Certificate Character CharacterIterator CharArrayReader CharArrayWriter CharConversionException Checkbox CheckboxGroup CheckboxMenuItem CheckboxMenuItemPeer CheckboxPeer CheckedInputStream CheckedOutputStream Checksum Choice ChoiceFormat ChoicePeer Class ClassCastException ClassCircularityError ClassFormatError ClassLoader ClassNotFoundException Clipboard ClipboardOwner Cloneable CloneNotSupportedException CollationElementIterator CollationKey Collator Color ColorModel Compiler Component ComponentAdapter ComponentEvent ComponentListener ComponentPeer ConnectException ConnectIOException Connection Constructor Container ContainerAdapter ContainerEvent ContainerListener ContainerPeer ContentHandler ContentHandlerFactory CRC32 CropImageFilter Cursor Customizer CardLayout"
         + " DatabaseMetaData DataFlavor DataFormatException DatagramPacket DatagramSocket DatagramSocketImpl DataInput DataInputStream DataOutput DataOutputStream DataTruncation Date DateFormat DateFormatSymbols DecimalFormat DecimalFormatSymbols Deflater DeflaterOutputStream DGC Dialog DialogPeer Dictionary DigestException DigestInputStream DigestOutputStream Dimension DirectColorModel Double Driver DriverManager DriverPropertyInfo DSAKey DSAKeyPairGenerator DSAParams DSAPrivateKey DSAPublicKey"
         + " EmptyStackException Enumeration EOFException Error Event EventListener EventObject EventQueue EventSetDescriptor Exception ExceptionInInitializerError ExportException"
         + " FeatureDescriptor Field FieldPosition File FileDescriptor FileDialog FileDialogPeer FileInputStream FilenameFilter FileNameMap FileNotFoundException FileOutputStream FileReader FileWriter FilteredImageSource FilterInputStream FilterOutputStream FilterReader FilterWriter Float FlowLayout FocusAdapter FocusEvent FocusListener Font FontMetrics FontPeer Format Frame FramePeer"
         + " Graphics GregorianCalendar GridBagConstraints GridBagLayout GridLayout Group GZIPInputStream GZIPOutputStream"
         + " Hashtable HttpURLConnection"
         + " Identity IdentityScope IllegalAccessError IllegalAccessException IllegalArgumentException IllegalComponentStateException IllegalMonitorStateException IllegalStateException IllegalThreadStateException Image ImageConsumer ImageFilter ImageObserver ImageProducer IncompatibleClassChangeError IndexColorModel IndexedPropertyDescriptor IndexOutOfBoundsException InetAddress Inflater InflaterInputStream InputEvent InputStream InputStreamReader Insets InstantiationError InstantiationException Integer InternalError InterruptedException InterruptedIOException IntrospectionException Introspector InvalidClassException InvalidKeyException InvalidObjectException InvalidParameterException InvocationTargetException IOException ItemEvent ItemListener ItemSelectable"
         + " Key KeyAdapter KeyEvent KeyException KeyListener KeyManagementException KeyPair KeyPairGenerator"
         + " Label LabelPeer LastOwnerException LayoutManager LayoutManager2 Lease LightweightPeer LineNumberInputStream LineNumberReader LinkageError List ListPeer ListResourceBundle LoaderHandler Locale LocateRegistry LogStream Long"
         + " MalformedURLException MarshalException Math MediaTracker Member MemoryImageSource Menu MenuBar MenuBarPeer MenuComponent MenuComponentPeer MenuContainer MenuItem MenuItemPeer MenuPeer MenuShortcut MessageDigest MessageFormat Method MethodDescriptor MissingResourceException Modifier MouseAdapter MouseEvent MouseListener MouseMotionAdapter MouseMotionListener MulticastSocket"
         + " Naming NegativeArraySizeException NoClassDefFoundError NoRouteToHostException NoSuchAlgorithmException NoSuchElementException NoSuchFieldError NoSuchFieldException NoSuchMethodError NoSuchMethodException NoSuchObjectException NoSuchProviderException NotActiveException NotBoundException NotOwnerException NotSerializableException NullPointerException Number NumberFormat NumberFormatException"
         + " Object ObjectInput ObjectInputStream ObjectInputValidation ObjectOutput ObjectOutputStream ObjectStreamClass ObjectStreamException ObjID Observable Observer Operation OptionalDataException OutOfMemoryError OutputStream OutputStreamWriter Owner"
         + " PaintEvent Panel PanelPeer ParameterDescriptor ParseException ParsePosition Permission PipedInputStream PipedOutputStream PipedReader PipedWriter PixelGrabber Point Polygon PopupMenu PopupMenuPeer PreparedStatement Principal PrintGraphics PrintJob PrintStream PrintWriter PrivateKey Process Properties PropertyChangeEvent PropertyChangeListener PropertyChangeSupport PropertyDescriptor PropertyEditor PropertyEditorManager PropertyEditorSupport PropertyResourceBundle PropertyVetoException ProtocolException Provider ProviderException PublicKey PushbackInputStream PushbackReader"
         + " Random RandomAccessFile Reader Rectangle Registry RegistryHandler Remote RemoteCall RemoteException RemoteObject RemoteRef RemoteServer RemoteStub ReplicateScaleFilter ResourceBundle ResultSet ResultSetMetaData RGBImageFilter RMIClassLoader RMIFailureHandler RMISecurityException RMISecurityManager RMISocketFactory RuleBasedCollator Runnable Runtime RuntimeException"
         + " Scrollbar ScrollbarPeer ScrollPane ScrollPanePeer SecureRandom Security SecurityException SecurityManager SequenceInputStream Serializable ServerCloneException ServerError ServerException ServerNotActiveException ServerRef ServerRuntimeException ServerSocket Shape Short Signature SignatureException Signer SimpleBeanInfo SimpleDateFormat SimpleTimeZone Skeleton SkeletonMismatchException SkeletonNotFoundException Socket SocketException SocketImpl SocketImplFactory SocketSecurityException SQLException SQLWarning Stack StackOverflowError Statement StreamCorruptedException StreamTokenizer String StringBuffer StringBufferInputStream StringCharacterIterator StringIndexOutOfBoundsException StringReader StringSelection StringTokenizer StringWriter StubNotFoundException SyncFailedException System SystemColor"
         + " TextArea TextAreaPeer TextComponent TextComponentPeer TextEvent TextField TextFieldPeer TextListener Thread ThreadDeath ThreadGroup Throwable Time Timestamp TimeZone Toolkit TooManyListenersException Transferable Types"
         + " UID UnexpectedException UnicastRemoteObject UnknownError UnknownHostException UnknownServiceException UnmarshalException Unreferenced UnsatisfiedLinkError UnsupportedEncodingException UnsupportedFlavorException URL URLConnection URLEncoder URLStreamHandler URLStreamHandlerFactory UTFDataFormatException"
         + " Vector VerifyError VetoableChangeListener VetoableChangeSupport VirtualMachineError Visibility VMID Void"
         + " Window WindowAdapter WindowEvent WindowListener WindowPeer WriteAbortedException Writer"
         + " ZipEntry ZipException ZipFile ZipInputStream ZipOutputStream ";

   /**
    * <p>格式化JAVA代码</p>
    * <p>create date:2005/02/18</p>
    *
    * @param sValue 格式化内容
    * @return 格式化后的内容
    */
   public static String format(String sValue){
      if(sValue == null){
         return "";
      }
      StringBuffer oValue = new StringBuffer();
      String sSub = "";
      char[] chBuffer = sValue.toCharArray();
      char ch;
      char chPre = 0;
      int nLength = chBuffer.length;
      boolean bInString = false;
      boolean bInComment = false;
      boolean bInCommentLine = false;
      for(int i = 0; i < nLength; i++){
         ch = chBuffer[i];
         if(SPLITTER.indexOf(ch) >= 0){
            if(!bInComment){
               // 注释开始处理
               if(ch == '/'){
                  if(chBuffer[i + 1] == '/'){
                     oValue.append("<FONT color='" + COMMENT_COLOR + "'>//");
                     bInComment = true;
                     bInCommentLine = true;
                     i++;
                     continue;
                  }
                  if(chBuffer[i + 1] == '*'){
                     oValue.append("<FONT color='" + COMMENT_COLOR + "'>/*");
                     bInComment = true;
                     i++;
                     continue;
                  }
               }
               // 字符串处理
               if(!bInString){
                  if(ch == '\"'){
                     oValue.append("\"<FONT color='" + STRING_COLOR + "'>");
                     bInString = true;
                     continue;
                  }
               }else{
                  if(ch == '\"'){
                     oValue.append(sSub + "</FONT>\"");
                     bInString = false;
                     sSub = "";
                     continue;
                  }
               }
            }else{
               if(chPre == '*' && ch == '/'){
                  oValue.append("/</FONT>");
                  bInComment = false;
                  continue;
               }
            }
            // 注释关闭处理
            if(!bInComment){
               if(sSub.length() > 0){
                  replaceFormat(oValue, sSub);
               }
            }else{
               oValue.append(sSub);
            }
            // 特殊字符的处理
            if(ch == '\n'){
               if(bInComment && bInCommentLine){
                  oValue.append("</FONT>");
                  bInComment = false;
                  bInCommentLine = false;
               }
               oValue.append("<br>\n");
            }else if(ch == ' '){
               oValue.append("&nbsp;");
            }else if(ch == '&'){
               oValue.append("&amp;");
            }else if(ch == '<'){
               oValue.append("&lt;");
            }else if(ch == '>'){
               oValue.append("&gt;");
            }else if(ch == '\"'){
               oValue.append("&quot;");
            }else if(ch == '\t'){
               oValue.append("&quot;&quot;&quot;");
            }else{
               oValue.append(ch);
            }
            // 字符处理
            chPre = ch;
            sSub = "";
         }else{
            sSub += chBuffer[i];
         }
      }
      return oValue.toString();
   }

   // 格式替换函数
   private static boolean replaceFormat(StringBuffer oString,
                                        String sSub){
      if(KEY_STRING.indexOf(" " + sSub + " ") >= 0){
         oString.append("<FONT color='" + KEY_COLOR + "'>" + sSub + "</FONT>");
         return true;
      }else if(EXCEPTION_STRING.indexOf(" " + sSub + " ") >= 0){
         oString.append("<FONT color='" + EXCEPTION_COLOR + "'>" + sSub + "</FONT>");
         return true;
      }else{
         oString.append(sSub);
      }
      return true;
   }
}
