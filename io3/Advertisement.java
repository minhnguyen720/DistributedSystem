public class Advertisement {
    private double TV;
    private double Radio;
    private double Newspaper;
    private double Sale;

    private String titleTV;
    private String titleRadio;
    private String titleNewspaper;
    private String titleSale;

    public Advertisement(double TV, double Radio, double Newspaper, double Sale) {
        this.TV = TV;
        this.Radio = Radio;
        this.Newspaper = Newspaper;
        this.Sale = Sale;
    }

    public Advertisement(String titleTV, String titleRadio, String titleNewspaper, String titleSale) {
        this.titleTV = titleTV;
        this.titleRadio = titleRadio;
        this.titleNewspaper = titleNewspaper;
        this.titleSale = titleSale;
    }

    public String getsSale() {
        return titleSale;
    }

    public String getsRadio() {
        return titleRadio;
    }

    public String getsTV() {
        return titleTV;
    }

    public String getsNewspaper() {
        return titleNewspaper;
    }

    public double getSale() {
        return Sale;
    }

    public double getNewspaper() {
        return Newspaper;
    }

    public double getTV() {
        return TV;
    }

    public double getRadio() {
        return Radio;
    }

}