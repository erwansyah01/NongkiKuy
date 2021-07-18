package com.tugas.NongkiKuy.Model;

import java.io.Serializable;



public class ModelCafe implements Serializable {

    private String IdCafe, txtNamaCafe, txtAlamatCafe, GambarCafe, DescCafe,  txtNoTelp;

    public String getIdCafe() {return IdCafe; }

    public void setIdCafe(String idCafe) {
        this.IdCafe = idCafe; }

    public String getDescCafe() {
        return DescCafe;
    }

    public void setDescCafe(String descCafe) {
       this.DescCafe = descCafe;
    }

    public String getTxtNamaCafe() {
        return txtNamaCafe;
    }

    public void setTxtNamaCafe(String txtNamaCafe) {
        this.txtNamaCafe = txtNamaCafe;
    }

    public String getTxtAlamatCafe() {
        return txtAlamatCafe;
    }

    public void setTxtAlamatCafe(String txtAlamatCafe) {
        this.txtAlamatCafe = txtAlamatCafe;
    }

    public String getTxtNoTelp() {
        return txtNoTelp;
    }

    public void setTxtNoTelp(String txtNoTelp) {
        this.txtNoTelp = txtNoTelp;
    }

    public String getGambarCafe() {
        return GambarCafe;
    }

    public void setGambarCafe(String gambarCafe) {
        this.GambarCafe = gambarCafe;
    }
}
