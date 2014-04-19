package mo.android.common;

import android.opengl.GLSurfaceView;
import android.util.Log;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;

//============================================================
// <T>绘制表面环境工厂。</T>
//============================================================
public class FSurfaceContextFactory
      implements
         GLSurfaceView.EGLContextFactory
{
   // 日志标签
   private static String TAG = "FSurfaceContextFactory";

   // 环境版本
   private static int EGL_CONTEXT_CLIENT_VERSION = 0x3098;

   //============================================================
   // <T>检查错误。</T>
   //============================================================
   private void checkEglError(String prompt,
                              EGL10 egl){
      int error;
      while((error = egl.eglGetError()) != EGL10.EGL_SUCCESS){
         Log.e(TAG, String.format("%s: EGL error: 0x%x", prompt, error));
      }
   }

   //============================================================
   // <T>创建上下文环境。</T>
   //============================================================
   @Override
   public EGLContext createContext(EGL10 egl,
                                   EGLDisplay display,
                                   EGLConfig eglConfig){
      Log.w(TAG, "Creating OpenGL ES 2.0 context success.");
      checkEglError("Before eglCreateContext", egl);
      int[] attributes = {EGL_CONTEXT_CLIENT_VERSION, 2, EGL10.EGL_NONE};
      EGLContext context = egl.eglCreateContext(display, eglConfig, EGL10.EGL_NO_CONTEXT, attributes);
      checkEglError("After eglCreateContext", egl);
      return context;
   }

   //============================================================
   // <T>销毁上下文环境。</T>
   //============================================================
   @Override
   public void destroyContext(EGL10 egl,
                              EGLDisplay display,
                              EGLContext context){
      egl.eglDestroyContext(display, context);
   }
}
