package POJO;

/**
 * Created by Misha on 05.06.2017.
 */
public class Massive {
    private Double xval;
    private Double yval;


    public Massive(Double xval, Double yval) {
        this.xval = xval;
        this.yval = yval;
    }

    public Massive() {

    }

    public Double getXval() {
        return xval;
    }

    public void setXval(Double xval) {
        this.xval = xval;
    }


    public Double getYval() {
        return yval;
    }

    public void setYval(Double yval) {
        this.yval = yval;
    }

}