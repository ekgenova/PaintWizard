public class Room{

    private int wallCount;
    private double wallHeight;
    private double wallWidth;

    public Room(int wallCount, double wallHeight, double wallWidth){
        this.wallCount = wallCount;
        this.wallHeight = wallHeight;
        this.wallWidth = wallWidth;
    }


    public int getWallCount() {
        return wallCount;
    }

    public double getWallHeight() {
        return wallHeight;
    }

    public double getWallWidth() {
        return wallWidth;
    }

    public void setWallCount(int wallCount) {
        this.wallCount = wallCount;
    }

    public void setWallHeight(double wallHeight) {
        this.wallHeight = wallHeight;
    }

    public void setWallWidth(double wallWidth) {
        this.wallWidth = wallWidth;
    }


    public double calculateAreaTotal (int wallCount, double wallWidth, double wallHeight){
        double wallArea = wallHeight*wallWidth;
        double areaTotal = wallArea*wallCount;
        return areaTotal;
    }

    @Override
    public String toString() {
        return "Room measurements: Walls - " + wallCount + ", Height - " + wallHeight + "m, Width - " + wallWidth + "m. ";
    }
}
