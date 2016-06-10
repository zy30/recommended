package impl;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by zhangyang on 09/06/16.
 * clses的key值为类别，value值为包含图像的索引值的list。
 */
public class Classifications {
    private HashMap<Integer,ArrayList<Integer>> clses =new HashMap<Integer, ArrayList<Integer>>() ;
    private Images imgs = new Images();


    public HashMap<Integer, ArrayList<Integer>> getClses() {
        return clses;
    }
    //计算每张图像最突出的特征类别，把这张图片加入到对应类别的list里。
    public void setClses() {
        double[][] images=imgs.getImages();

        for(int i=0;i<images.length;++i){
            int cls = getClassify(images[i]);
            if(clses.get(cls)==null){
                ArrayList<Integer> temp = new ArrayList<Integer>();
                temp.add(i);
                clses.put(cls,temp);
            }else {
                ArrayList<Integer> temp = clses.get(cls);
                temp.add(i);
            }
        }

    }


    private int getClassify(double[] img){
        int maxIdx=0;
        double maxVal = img[0];
        for(int idx=1;idx<img.length;++idx){
            if(img[idx]>maxVal) {
                maxIdx = idx;
                maxVal = img[idx];
            }
        }

        return maxIdx;
    }
}
