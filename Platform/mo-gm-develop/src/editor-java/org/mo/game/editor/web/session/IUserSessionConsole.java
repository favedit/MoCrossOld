package org.mo.game.editor.web.session;

public interface IUserSessionConsole{

   /**
    * <T>用户登录系统，获得用户登录信息。</T>
    * 
    * @param args 登录用的参数
    */
   void fetchDetailInfo(FUserSessionArgs args);

   /**
    * <T>用户登录系统，获得用户登录信息。</T>
    * 
    * @param args 登录用的参数
    */
   void fetchDetailInfoByGuid(FUserSessionArgs args);

   /**
    * <T>用户登录系统，获得用户登录信息。</T>
    * 
    * @param args 登录用的参数
    */
   void fetchInfo(FUserSessionArgs args);

   /**
    * <T>用户登录系统，获得用户登录信息。</T>
    * 
    * @param args 登录用的参数
    */
   void fetchInfoByGuid(FUserSessionArgs args);

   /**
    * <T>用户登录系统，获得用户登录信息。</T>
    * 
    * @param args 登录用的参数
    */
   void fetchMessage(FUserSessionArgs args);

   /**
    * <T>用户登录系统，创建与数据库的线程</T>
    * <P>使用用户登录名<C>passport</C>和用户密码<C>password</C>进行登录。</P>
    * <P>登录成功后，会在数据库上用户线程表中建立一个线程记录，并返回记录的标识。</P>
    * 
    * @param args 登录用的参数
    */
   void login(FUserSessionArgs args);

   /**
    * <T>快速登录系统</T>
    * <P>使用用户标识<C>login_id</C>直接登录。</P>
    * 
    * @param args 登录用的参数
    */
   void loginDirect(FUserSessionArgs args);

   /**
    * <T>关闭与数据库的线程。</T>
    * 
    * @param id 数据库线程标识
    */
   void logout(FUserSessionArgs args);

}
