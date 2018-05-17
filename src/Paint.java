public class Paint {

    private String name;
    private int volume;
    private double price;
    private double totalCoverage;

    public Paint(String name, int volume, double price, double totalCoverage){
        this.name = name;
        this.volume = volume;
        this.price = price;
        this.totalCoverage = totalCoverage;
    }

    public String getName() {
        return name;
    }

    public int getVolume() {
        return volume;
    }

    public double getPrice() {
        return price;
    }

    public double getTotalCoverage() {
        return totalCoverage;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setTotalCoverage(double totalCoverage) {
        this.totalCoverage = totalCoverage;
    }


    public double calculateCost(double areaTotal, double totalCoverage, double price ){
        int bucketsNeeded = (int)Math.ceil(areaTotal/totalCoverage);
        double cost = bucketsNeeded*price;
        return cost;
    }

    public double calculateWaste(double totalArea, double totalCoverage, int volume){
        int tinsNeeded = (int)Math.ceil(totalArea/totalCoverage);
        double wasteMetresSquared = (tinsNeeded*totalCoverage) - totalArea;
        double totalWaste = wasteMetresSquared/(totalCoverage/volume);
        return totalWaste;
    }

}
