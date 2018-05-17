import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.String.format;

public class PaintWizard {

    public void runWizard() {

        //Condition for while loop. Loop breaks at the end if you type n when it asks if you want to go again.
        boolean isRunning = true;

        while (isRunning) {

            //Object initialization for the room being defined by the user
            Room newRoom = new Room(0, 0, 0);

            /*Object initialization for the 3 paints available in the wizard
            Total coverage is coverage per m2 multiplied by the volume
             */

            Paint paint1 = new Paint("CheapoMax", 20, 19.99, 200);
            Paint paint2 = new Paint("AverageJoes", 15, 17.99, 165);
            Paint paint3 = new Paint("DuluxourousPaints", 10, 25, 200);

            //initialization of an ArrayList of paints
            ArrayList<Paint> paintList = new ArrayList<>();

            //Paint objects added to arraylist of paints
            paintList.add(paint1);
            paintList.add(paint2);
            paintList.add(paint3);

            //Arrays of doubles for Cost and Waste. These will fill up with the cost and waste for each paint for the particular room defined
            double[] costList = new double[paintList.size()];
            double[] wasteList = new double[paintList.size()];

            /*Scanner asking for user input
            Wall count, wall height and wall width
             */
            Scanner scan = new Scanner(System.in);
            System.out.println("Welcome to paint wizard.");
            System.out.println("To calculate the best paint to use in your room please provide some details about the room.");
            System.out.println("How many walls (e.g 5) :");
            newRoom.setWallCount(scan.nextInt());
            System.out.println("Room height (metres) (e.g 2.4):");
            newRoom.setWallHeight(scan.nextDouble());
            System.out.println("Room width (metres) (e.g 3.5):");
            newRoom.setWallWidth(scan.nextDouble());

            //next line so that when the wizard asks again it doesn't send an empty line to the scanner and continue the loop
            scan.nextLine();

            //area calculation for the room defined via the method calculateAreaTotal defined in Room
            double areaTotal = newRoom.calculateAreaTotal(newRoom.getWallCount(), newRoom.getWallWidth(), newRoom.getWallHeight());

            /*initialization of lowestCost double and lowestCoster string
            lowestCost is set to a very high value to be compared to the cost calculated for each paint using Math.min later on
            lowerCoster is empty and once lowestCost is found in the for loop it is replaced with the name of the paint that has the lowest cost
             */
            double lowestCost = 1000000000;
            String lowestCoster = "";

            /*For loop to iterate through the list of paints and calculate the cost to paint the specified room using each paint.
            The cost for each is then compared to the lowestCost value above and if it is lower the value is updated.
            If the cost is lower the lowestCoster value is also updated to reflect the name of the current lowest costing paint.
             */
            for (int i = 0; i < paintList.size(); i++) {
                costList[i] = paintList.get(i).calculateCost(areaTotal, paintList.get(i).getTotalCoverage(), paintList.get(i).getPrice());
                if (Math.min(lowestCost, costList[i]) < lowestCost) {
                    lowestCost = Math.min(lowestCost, costList[i]);
                    lowestCoster = paintList.get(i).getName();
                }
            }

            /*initialization of lowestWaste double and lowestWaster string
            lowestWaste is set to a very high value to be compared to the waste calculated for each paint using Math.min later on
            lowestWaster is empty and once lowestWaste is found in the for loop it is replaced with the name of the paint that has the lowest waste
             */

            double lowestWaste = 10000;
            String lowestWaster = "";

            /*For loop to iterate through the list of paints and calculate the waste when painting the room using each paint
            The waste for each is then compared to the lowestWaste value above and if it is lower the value is updated.
            If the waste is lower the lowestWaster value is also updated to reflect the name of the current lowest wasting paint.
             */
            for (int i = 0; i < paintList.size(); i++) {
                wasteList[i] = paintList.get(i).calculateWaste(areaTotal, paintList.get(i).getTotalCoverage(), paintList.get(i).getVolume());
                if (Math.min(lowestWaste, wasteList[i]) < lowestWaste) {
                    lowestWaste = Math.min(lowestWaste, wasteList[i]);
                    lowestWaster = paintList.get(i).getName();
                }
            }

            /*Print out relevant info
            The room measurements you want to paint
            The lowest cost and lowest costing paint
            The lowest waste and lowest wasting paint
             */
            System.out.println("You want to paint " + newRoom.getWallCount() + " walls each measuring " +
                    newRoom.getWallHeight() + " metres high and " + newRoom.getWallWidth() + " metres wide.");
            System.out.println("The lowest cost paint for those measurements is " + lowestCoster + " at a total of £" +

                    format("%.2f", lowestCost) + ".");
            System.out.println("The paint with the lowest wastage is " + lowestWaster + " at " +

                    format("%.1f", lowestWaste) + " litres wasted per coat.");


            /*Checks if you want to calculate another room
            if yes, runs the program again.
            if no, breaks out of the loop and ends
             */
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
