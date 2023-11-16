package model;

public class Prodi {
    private String idProdi;
    private String namaProdi;
    private String akreditasiProdi;

    public Prodi(String idProdi, String namaProdi, String akreditasiProdi){
        this.idProdi = idProdi;
        this.namaProdi = namaProdi;
        this.akreditasiProdi = akreditasiProdi;
    }

    public String getIdProdi(){
        return idProdi;
    }

    public String getNamaProdi() {
        return namaProdi;
    }

    public String getAkreditasiProdi() {
        return akreditasiProdi;
    }
}