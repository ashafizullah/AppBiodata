package crud.adam.com.appbiodata.model;

import java.util.List;

/**
 * Created by adams on 03/11/2018.
 */

public class ResponseModel {
    String kode, pesan;
    List<DataModel> result;

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }

    public List<DataModel> getResult() {
        return result;
    }

    public void setResult(List<DataModel> result) {
        this.result = result;
    }

}
