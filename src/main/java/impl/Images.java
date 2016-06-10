package impl;

/**
 * Created by zhangyang on 09/06/16.
 * images是图像向量。二维数组，总共1000张，每张为10维向量
 */
public class Images {
    private double[][] images = new double[1000][10];

    public void setImages(double[][] images) {
        // TODO
        this.images = images;
    }

    public double[][] getImages() {
        return images;
    }
}
