package com.jcwx.game.web;

/**
 * 自定义AOP切面
 * @author diego.lau
 * 使用方法：在 applicationContext.xml 文件加入以下2行代码
 * <aop:aspectj-autoproxy/>
 * <bean id="MyInterceptor" class="com.jjl.rzjh.aop.MyInterceptor" />
 */

import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class MyInterceptor {

    private static Logger logger = Logger.getLogger(MyInterceptor.class);

    /* *//**
     * 定义切入点 第一个*表示方法的返回值,这里使用通配符,只有返回值符合条件的才拦截,(!void表示有返回值)
     * 第一个..表示com.jjl.rzjh.service.delegate.impl包及其子包 倒数第二个*表示包下的所有Java类都被拦截
     * 最后一个*表示类的所有方法都被拦截
     * (..)表示方法的参数可以任意多个如[(java.lang.String,java.lang.Integer)表示第一个参数是String
     * ,第二个参数是int的方法才会被拦截]
     */
    /*
     * @Pointcut("execution(* com.jjl.rzjh.service.impl..*.*(..))") private void
     * pointCutMethod() { //定义一个切入点,名称为pointCutMethod(),拦截类的所有方法 }
     *//**
     * 验证 playerId 的有效性 使用方法：在方法定义前面加上 @AnnoValidatePlayerID
     * 注意：使用前必须保证第一个方法参数必须是 playerId
     */
    /*
     * @Before("@annotation(com.jjl.rzjh.aop.AnnoValidatePlayerID)") public void
     * doBeforeValidatePlayerID( JoinPoint pjp ) { Integer playerId =
     * (Integer)pjp.getArgs()[0]; }
     *//**
     * 验证 villageId 的有效性 使用方法：在方法定义前面加上 @AnnoValidateVillageID
     * 注意：使用前必须保证第一个方法参数必须是 villageId
     */
    /*
     * @Before("@annotation(com.jjl.rzjh.aop.AnnoValidateVillageID)") public
     * void doBeforeValidateVillageID( JoinPoint pjp ) { Integer villageId =
     * (Integer)pjp.getArgs()[0]; }
     *//**
     * 验证 player 是否已经登录游戏 使用方法：在方法定义前面加上 @AnnoValidateIsLogin
     */
    /*
     * @Before("@annotation(com.jjl.rzjh.aop.AnnoValidateIsLogin)") public void
     * doBeforeValidateIsLogin( JoinPoint pjp ) { }
     *//**
     * 异常捕获处理 注：所有的delegate方法都会自动拦截
     */
    /*
     * @AfterThrowing(value="pointCutMethod()", throwing="exp") public void
     * doAfterException( Throwable exp ) throws Throwable { //异常通知 if ( exp
     * instanceof GameException ) { //GameException 直接抛出 throw exp; } else { //
     * 服务器请求异常，记录日志 logger.error(
     * ResourceBundleService.getString("txt.exception"), exp ); throw new
     * GameException( "服务器请求异常" ); } }
     *//**
     * 保证同一个session的同一个操作具有原子性 注：所有方法都通过该切面
     */
    /*
     * @Around("pointCutMethod()") public Object doAround( ProceedingJoinPoint
     * pjp ) throws Throwable { //
     * System.out.println(pjp.getTarget().getClass().
     * getName()+":"+pjp.getSignature().getName()); // Object object = null; //
     * if ( session == null ) { // object = pjp.proceed(); // } else { //
     * PlayerSession playerSession = (PlayerSession)session; //
     * playerSession.getLock().lock(); //加锁-确保同一session同一时刻只能执行1个操作 // try { //
     * handleSessionOp(playerSession); //判断是否操作过快 // object = pjp.proceed(); //
     * }catch (Exception e) { // throw e; //抛出异常 // } finally { // //释放锁 //
     * playerSession.getLock().unlock(); // } // }
     * 
     * return pjp.proceed(); }
     * 
     * public void handleSessionOp(PlayerSession session) { int times =
     * session.getOpTimes().size(); if ( times > SystemConfig.SESSION_OP_TIMES )
     * { long a = session.getOpTimes().get(0); long b =
     * session.getOpTimes().get(times-1); //判断是否超过点击频率 if ( (b-a) <
     * SystemConfig.SESSION_OP_TIMES*SystemConfig.SESSION_OP_RATE ) {
     * //休息5秒钟后，清除次数纪录 if ( (System.currentTimeMillis() - b) >
     * SystemConfig.SESSION_OP_SLEEP ) session.getOpTimes().clear(); else throw
     * new GameException("您操作太快了，5秒钟后再来吧。"); } //没有超过点击频率，直接清除次数纪录
     * session.getOpTimes().clear(); } else {
     * session.getOpTimes().add(System.currentTimeMillis()); } }
     */
}