package pmo.sample.app.laporanmasyarakat.Menu;

public class Data_Kebakaran {

    private String laporanKebakaran;
    private String pelapor;
    private String telepon;
    private String isilaporan;
    private String tanggal;
    private String alamat;
    private String diterima;
    private String key;

    // Konstruktor untuk membaca data snapshot
    public Data_Kebakaran(String getimage, String getjenisLaporan, String getpelapor, String getinputNomor, String getinputKejadian, String gettanggal_kejadian, String gettxtMultiLine, String trim) {
    }

    // Konstruktor dengan beberapa parameter, untuk mendapatkan input Data dari User
    public Data_Kebakaran(String laporanKebakaran, String pelapor, String telepon, String tanggal, String alamat, String isilaporan, String diterima) {
        this.laporanKebakaran = laporanKebakaran;
        this.pelapor = pelapor;
        this.telepon = telepon;
        this.isilaporan = isilaporan;
        this.tanggal = tanggal;
        this.alamat = alamat;
        this.diterima = diterima;
    }

    public String getLaporanKebakaran() {
        return laporanKebakaran;
    }

    public void setLaporanKebakaran(String laporanKebakaran) {
        this.laporanKebakaran = laporanKebakaran;
    }

    public String getPelapor() {
        return pelapor;
    }

    public void setPelapor(String pelapor) {
        this.pelapor = pelapor;
    }

    public String getTelepon() {
        return telepon;
    }

    public void setTelepon(String telepon) {
        this.telepon = telepon;
    }

    public String getIsilaporan() {
        return isilaporan;
    }

    public void setIsilaporan(String isilaporan) {
        this.isilaporan = isilaporan;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getDiterima() {
        return diterima;
    }

    public void setDiterima(String diterima) {
        this.diterima = diterima;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
