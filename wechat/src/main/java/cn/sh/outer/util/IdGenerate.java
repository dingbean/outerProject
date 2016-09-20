package cn.sh.outer.util;

/**
 * Created by Administrator on 2016/9/20.
 */
public class IdGenerate {

    private static int idNum = 0;

    public static int getIdNum(){
        return ++idNum;
    }

    public static void setIdNum(int num){
        idNum = num;
    }
}
