package cn.sh.outer.util;


/**
 * Created by Administrator on 2016/9/20.
 */
public class IdGenerate {

    /**
    private static AtomicInteger idNum = new AtomicInteger(0);

    public static int getIdNum(){
        return idNum.getAndIncrement();
    }

    public static void setIdNum(int num){
        idNum.set(0);
    }
     */
    private static int idNum;

    public static int getIdNum() {
        return ++idNum;
    }

    public static void setIdNum(int idNum) {
        IdGenerate.idNum = idNum;
    }
}
