package Student2;

public class Student {
    String xuehao;
    String xingming;
    String xingbie;
    int nianling;
    String jiguan;
    String yxmc;

    public String getXuehao() {
        return xuehao;
    }

    public void setXuehao(String xuehao) {
        this.xuehao = xuehao;
    }

    public String getXingming() {
        return xingming;
    }

    @Override
    public String toString() {
        return "Student{" +
                "xuehao='" + xuehao + '\'' +
                ", xingming='" + xingming + '\'' +
                ", xingbie='" + xingbie + '\'' +
                ", nianling=" + nianling +
                ", jiguan='" + jiguan + '\'' +
                ", yxmc='" + yxmc + '\'' +
                '}';
    }

    public void setXingming(String xingming) {
        this.xingming = xingming;
    }

    public String getXingbie() {
        return xingbie;
    }

    public void setXingbie(String xingbie) {
        this.xingbie = xingbie;
    }

    public int getNianling() {
        return nianling;
    }

    public void setNianling(int nianling) {
        this.nianling = nianling;
    }

    public String getJiguan() {
        return jiguan;
    }

    public void setJiguan(String jiguan) {
        this.jiguan = jiguan;
    }

    public String getYxmc() {
        return yxmc;
    }

    public void setYxmc(String yxmc) {
        this.yxmc = yxmc;
    }
}
