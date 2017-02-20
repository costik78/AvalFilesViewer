package ua.ibis.avalfile.pojo;

/**
 * Created by conti on 18.11.2016.
 */
public class FileX5 extends FileXX {

    public final int BLANK_OBESPECHENIE = 90;

    private long summaObesp;
    private char clientClass;
    private char correcterClientClass;
    private long summaReserve;
    private int bs4Reserve;
    private int obespech;
    //private String allObesp;
    private long summaObespP23;
    private long summaForgiven;
    private int  DPD;
    private int groupOfClient;
    private double probabilityOfDefault;
    private double PD;

    public long getSummaObesp() {
        return summaObesp;
    }

    public void setSummaObesp(long summaObesp) {
        this.summaObesp = summaObesp;
    }

    public char getClientClass() {
        return clientClass;
    }

    public void setClientClass(char clientClass) {
        this.clientClass = clientClass;
    }

    public long getSummaReserve() {
        return summaReserve;
    }

    public void setSummaReserve(long summaReserve) {
        this.summaReserve = summaReserve;
    }

    public int getBs4Reserve() {
        return bs4Reserve;
    }

    public void setBs4Reserve(int bs4Reserve) {
        this.bs4Reserve = bs4Reserve;
    }

    public int getObespech() {
        return obespech;
    }

    public void setObespech(String  obespech) {
        this.obespech = obespech.isEmpty() ? BLANK_OBESPECHENIE : Integer.parseInt(obespech);
    }

    public char getCorrecterClientClass() {
        return correcterClientClass;
    }

    public void setCorrecterClientClass(char correcterClientClass) {
        this.correcterClientClass = correcterClientClass;
    }

    public long getSummaObespP23() {
        return summaObespP23;
    }

    public void setSummaObespP23(long summaObespP23) {
        this.summaObespP23 = summaObespP23;
    }

    public long getSummaForgiven() {
        return summaForgiven;
    }

    public void setSummaForgiven(long summaForgiven) {
        this.summaForgiven = summaForgiven;
    }

    public int getDPD() {
        return DPD;
    }

    public void setDPD(int DPD) {
        this.DPD = DPD > 0 ? DPD : 0;
    }

    public int getGroupOfClient() {
        return groupOfClient;
    }

    public void setGroupOfClient(int groupOfClient) {
        this.groupOfClient = groupOfClient;
    }

    public double getProbabilityOfDefault() {
        return probabilityOfDefault;
    }

    public void setProbabilityOfDefault(String probabilityOfDefault) {
        if(probabilityOfDefault.contains(",") ) {
            probabilityOfDefault = probabilityOfDefault.replace(',', '.');
        }

        this.probabilityOfDefault = Double.parseDouble(probabilityOfDefault);
    }

    public double getPD() {
        return PD;
    }

    public void setPD(String PD) {
        if(PD.contains(",") ) {
            PD = PD.replace(',', '.');
        }

        this.PD = Double.parseDouble(PD);
    }

    @Override
    public String toString() {
        return String.format("FileX5{ mfo=%d , account=%s , crncy=%d , trNumber=%s",
                getMfo(), getAccount(), getCurrency(), getTrNumber());
    }
}
