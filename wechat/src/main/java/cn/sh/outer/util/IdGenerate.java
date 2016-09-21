package cn.sh.outer.util;


import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Administrator on 2016/9/20.
 */
public class IdGenerate {

    private static AtomicInteger idNum = new AtomicInteger(0);

    public static int getIdNum(){
        return idNum.getAndIncrement();
    }

    public static void setIdNum(int num){
        idNum.set(0);
    }
}
