package mo.android.frame.test;

import android.app.Activity;
import android.app.Application;
import android.content.pm.ActivityInfo;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;
import mo.android.api.RNativeApi;
import mo.android.common.FSurfaceView;
import mo.android.frame.testdemo.R;

//============================================================
// <T>主应用程序。</T>
//============================================================
public class MainActivity
      extends Activity
{
   // 表面
   protected FSurfaceView _surfaceView;

   //============================================================
   // <T>创建表面。</T>
   //============================================================
   @Override
   protected void onCreate(Bundle savedInstanceState){
      super.onCreate(savedInstanceState);
      //设置成全屏模式
      getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
      // 强制为横屏
      setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
      // 强制为竖屏
      // setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
      //去掉标题栏
      requestWindowFeature(Window.FEATURE_NO_TITLE);
      // 获得资源管理器
      AssetManager assetManager = getAssets();
      // 初始化处理
      RNativeApi.initialize(assetManager);
      // 创建表面
      Application application = getApplication();
      _surfaceView = new FSurfaceView(application, true, 24, 8);
      setContentView(_surfaceView);
   }

   //============================================================
   // <T>创建菜单。</T>
   //============================================================
   @Override
   public boolean onCreateOptionsMenu(Menu menu){
      getMenuInflater().inflate(R.menu.main, menu);
      return false;
   }

   //============================================================
   // <T>配置变更处理。</T>
   //============================================================
   @Override
   public void onConfigurationChanged(Configuration newConfig){
      try{
         super.onConfigurationChanged(newConfig);
         if(this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            // land
         }else if(this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            // port
         }
      }catch(Exception ex){
      }
   }

   //============================================================
   // <T>暂停处理。</T>
   //============================================================
   @Override
   protected void onPause(){
      super.onPause();
      _surfaceView.onPause();
      RNativeApi.suspend();
   }

   //============================================================
   // <T>继续处理。</T>
   //============================================================
   @Override
   protected void onResume(){
      super.onResume();
      _surfaceView.onResume();
   }
}
