package cn.stranded.aop;

import cn.stranded.config.DBContextHolder;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DataSourceAop {
    /**
     * 只读：
     * 不是Master注解的对象或方法  && select开头的方法  ||  get开头的方法
     */
    @Pointcut("!@annotation(cn.stranded.annotation.Master) " +
            "&& (execution(* cn.stranded.service..*.select*(..)) " +
            "|| execution(* cn.stranded.service..*.get*(..)))")
    public void readPointcut() {

    }

    /**
     * 写：
     * Master注解的对象或方法 || insert开头的方法  ||  add开头的方法 || update开头的方法
     * || edlt开头的方法 || delete开头的方法 || remove开头的方法
     */
    @Pointcut("@annotation(cn.stranded.annotation.Master) " +
            "|| execution(* cn.stranded.service..*.insert*(..)) " +
            "|| execution(* cn.stranded.service..*.add*(..)) " +
            "|| execution(* cn.stranded.service..*.update*(..)) " +
            "|| execution(* cn.stranded.service..*.edit*(..)) " +
            "|| execution(* cn.stranded.service..*.delete*(..)) " +
            "|| execution(* cn.com.bnc..*.remove*(..))")
    public void writePointcut() {

    }

    @Before("readPointcut()")
    public void read() {
        DBContextHolder.slave();
    }

    @Before("writePointcut()")
    public void write() {
        DBContextHolder.master();
    }
}
