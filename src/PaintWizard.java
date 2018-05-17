import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.String.format;

public class PaintWizard {

    public void runWizard() {

        boolean isRunning = true;

        while (isRunning) {
            Room newRoom = new Room(0, 0, 0);
            Paint paint1 = new Paint("CheapoMax", 20, 19.99, 200);
            Paint paint2 = new Paint("AverageJoes", 15, 17.99, 165);
            Paint paint3 = new Paint("DuluxourousPaints", 10, 25, 200);
            ArrayList<Paint> paintList = new ArrayList<>();
            paintList.add(paint1);
            paintList.add(paint2);
            paintList.add(paint3);
            double[] costList = new double[paintList.size()];
            double[] wasteList = new double[paintList.size()];

            Scanner scan = new Scanner(System.in);
            System.out.println("Welcome to paint wizard.");
            System.out.println("To calculate the best paint to use in your room please provide some details about the room.");
            System.out.println("How many walls (e.g 5) :");
            newRoom.setWallCount(scan.nextInt());
            System.out.println("Room height (metres) (e.g 2.4):");
            newRoom.setWallHeight(scan.nextDouble());
            System.out.println("Room width (metres) (e.g 3.5):");
            newRoom.setWallWidth(scan.nextDouble());
            scan.nextLine();

            double areaTotal = newRoom.calculateAreaTotal(newRoom.getWallCount(), newRoom.getWallWidth(), newRoom.getWallHeight());


            double lowestCost = 1000000000;
            String lowestCoster = "";

            for (
                    int i = 0; i < paintList.size(); i++)

            {
                costList[i] = paintList.get(i).calculateCost(areaTotal, paintList.get(i).getTotalCoverage(), paintList.get(i).getPrice());
                if (Math.min(lowestCost, costList[i]) < lowestCost) {
                    lowestCost = Math.min(lowestCost, costList[i]);
                    lowestCoster = paintList.get(i).getName();
                }
            }

            double lowestWaste = 10000;
            String lowestWaster = "";

            for (
                    int i = 0; i < paintList.size(); i++)

            {
                wasteList[i] = paintList.get(i).calculateWaste(areaTotal, paintList.get(i).getTotalCoverage(), paintList.get(i).getVolume());
                if (Math.min(lowestWaste, wasteList[i]) < lowestWaste) {
                    lowestWaste = Math.min(lowestWaste, wasteList[i]);
                    lowestWaster = paintList.get(i).getName();
                }
            }

            System.out.println("You want to paint " + newRoom.getWallCount() + " walls each measuring " +
                    newRoom.getWallHeight() + " metres high and " + newRoom.getWallWidth() + " metres wide.");
            System.out.println("The lowest cost paint for those measurements is " + lowestCoster + " at a total of £" +

                    format("%.2f", lowestCost) + ".");
            System.out.println("The paint with the lowest wastage is " + lowestWaster + " at " +

                    format("%.1f", lowestWaste) + " litres wasted per coat.");

            System.out.println("Would you like to calculate another room? [y/n]");
            String userInput = scan.nextLine();
            if (userInput.equalsIgnoreCase("y")) {
                runWizard();
            } else if (userInput.equalsIgnoreCase("n")) {
                System.out.println("Goodbye!");
                break;
            }
        }
    }
}
