package mo.android.frame.test;

import android.app.Activity;
import android.app.Application;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.Menu;
import mo.android.api.RNativeApi;
import mo.android.common.FSurfaceView;
import mo.android.frame.testinstance.R;

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
   // <T>暂停处理。</T>
   //============================================================
   @Override
   protected void onPause(){
      super.onPause();
      _surfaceView.onPause();
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
