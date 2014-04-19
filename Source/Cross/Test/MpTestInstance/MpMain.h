#ifndef __MP_TEST_001_H__
#define __MP_TEST_001_H__

#ifdef _MO_ANDROID
#ifndef JNI_H_
#include <jni.h>
#endif // JNI_H_
#endif // _MO_ANDROID

#ifdef _MO_ANDROID
extern "C" {
   JNIEXPORT jint JNICALL JNI_OnLoad(JavaVM* pJavaVM, void* pReserved);
   // 引擎处理
   JNIEXPORT void JNICALL Java_mo_android_api_RNativeApi_initialize(JNIEnv* pEnvironment, jobject pObject, jobject assetManager);
   JNIEXPORT void JNICALL Java_mo_android_api_RNativeApi_setEnvironment(JNIEnv* pEnvironment, jobject pObject, jstring code, jstring value);
   JNIEXPORT void JNICALL Java_mo_android_api_RNativeApi_setup(JNIEnv* pEnvironment, jobject pObject);
   JNIEXPORT void JNICALL Java_mo_android_api_RNativeApi_resize(JNIEnv* pEnvironment, jobject pObject, jint width, jint height);
   JNIEXPORT void JNICALL Java_mo_android_api_RNativeApi_process(JNIEnv* pEnvironment, jobject pObject);
   JNIEXPORT void JNICALL Java_mo_android_api_RNativeApi_release(JNIEnv* pEnvironment, jobject pObject);
   // 鼠标事件处理
   JNIEXPORT void JNICALL Java_mo_android_api_RNativeApi_onMouseDown(JNIEnv* pEnvironment, jobject pObject, jint buttonCd, jfloat x, jfloat y);
   JNIEXPORT void JNICALL Java_mo_android_api_RNativeApi_onMouseMove(JNIEnv* pEnvironment, jobject pObject, jint buttonCd, jfloat x, jfloat y);
   JNIEXPORT void JNICALL Java_mo_android_api_RNativeApi_onMouseUp(JNIEnv* pEnvironment, jobject pObject, jint buttonCd, jfloat x, jfloat y);
};
#endif // _MO_ANDROID

MO_NAMESPACE_BEGIN

MO_NAMESPACE_END

#endif //__MP_TEST_001_H__
