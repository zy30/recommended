package impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Created by zhangyang on 09/06/16.
 */
public class Recommend {
    private Classifications cf = new Classifications();
    private HashMap<Integer,ArrayList<Integer>> clses;
    public Recommend(){
        cf.setClses();
        this.clses = cf.getClses();
    }
    //参数userPerfer为用户的喜爱偏好，返回值为长度为10的数组，内容是图像的索引。
    public int[] recommeded(double[] userPerfer){
        int[] res = new int[10];
        int count =0;
        double[] userPerfer_format = formatArray(userPerfer);
        for(int i=0;i<userPerfer_format.length;++i){
            int select_num = (int)userPerfer_format[i]*10;  //该类选取的张数
            if(select_num==0) continue;
            ArrayList<Integer> cur_cls = clses.get(i);
            int cls_len = cur_cls.size();      //该类包含的图片数量
            int[] rand_arr = get_random_array(cls_len,select_num);
            for(int j=0;j<rand_arr.length;++count,++j){
                res[count] = cur_cls.get(rand_arr[j]);
            }
        }

        return res;
    }

    //从0到src_num中选取不重复的num个随机数.
    private int[] get_random_array(int src_num,int num){
        int[] res = new int[num];
        int[] source = new int[src_num];
        for(int i=0;i<src_num;++i){
            source[i] = i;
        }
        Random r = new Random();
        int len = source.length;
        for(int i=0;i<num;++i){
            int cur_idx = r.nextInt(len);
            res[i]=cur_idx;
            source[cur_idx]=source[len];   //把被随机抽取的位置用最后一位数取代。
            len--;       //长度减1，保证不重复。
        }
        return res;
    }

    //把任意的用户偏好向量规则为只有1位精度的double数组且和为1.
    public double[] formatArray(double[] userPerfer){
        double[] res = new double[userPerfer.length];
        double[] diff = new double[userPerfer.length];
        int scale = 1;
        double sum = 0.0;
        for(int i=0;i<userPerfer.length;++i){
            BigDecimal bd = new BigDecimal(userPerfer[i]);
            bd = bd.setScale(scale,1);
            res[i] = bd.doubleValue();
            diff[i] = userPerfer[i] - res[i];
            sum += res[i];
        }
        for(double i=0.0;i<(1.0-sum);i+=0.1){
            int max_id = 0;
            double max_val =0.0;
            for(int j=0;j<diff.length;++j){
                if(diff[j]> max_val){
                    max_id=j;
                    max_val=diff[j];
                }
            }
            res[max_id]+=0.1;
            diff[max_id]=0.0;
        }
        return res;
    }

//    public static void main(String[] args){
//    	double[] test_array = {0.12,0.34,0.29,0.07,0.08,0.05,0.05,0,0};
//        Recommend re = new Recommend();
//        double[] res = re.formatArray(test_array);
//        double sum = 0.0;
//        for(int i=0;i<res.length;++i){
//            sum+=res[i];
//            System.out.print(res[i]+" ");
//        }
//        System.out.println("sum:"+sum);
//    }

}
