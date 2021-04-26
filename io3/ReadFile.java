import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class ReadFile {
    public static double tvCounter = 0;
    public static double radioCounter = 0;
    public static double newspaperCounter = 0;
    public static double saleCounter = 0;

    public List<Advertisement> readData(String fileName) {
        List<Advertisement> advertise = new ArrayList<>();

        try {
            var file = new File(fileName);
            var reader = new Scanner(file);

            // Title
            String data = reader.nextLine();
            var sTokens = new StringTokenizer(data, ",");

            String titleTV = sTokens.nextToken();
            String titleRadio = sTokens.nextToken();
            String titleNewspaper = sTokens.nextToken();
            String titleSale = sTokens.nextToken();

            advertise.add(new Advertisement(titleTV, titleRadio, titleNewspaper, titleSale));

            // Data each column
            while (reader.hasNextLine()) {
                data = reader.nextLine();
                var dTokens = new StringTokenizer(data, ",");

                // Add data
                double TV = Double.parseDouble(dTokens.nextToken());
                tvCounter += TV;

                double radio = Double.parseDouble(dTokens.nextToken());
                radioCounter += radio;

                double newspaper = Double.parseDouble(dTokens.nextToken());
                newspaperCounter += newspaper;

                double sale = Double.parseDouble(dTokens.nextToken());
                saleCounter += sale;

                // Add object to list
                advertise.add(new Advertisement(TV, radio, newspaper, sale));
            }

            reader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return advertise;
    }

    public void writeData(String targetFile, List<Advertisement> advertises) {
        try {
            FileWriter writer = new FileWriter(targetFile);

            // write data to another file
            for (int i = 0; i < advertises.size(); i++) {
                if (i == 0) {
                    String sNewspaper = advertises.get(0).getsNewspaper();
                    String sRadio = advertises.get(0).getsRadio();
                    String sTV = advertises.get(0).getsTV();
                    String sSale = advertises.get(0).getsSale();

                    writer.write(sTV + "," + sRadio + "," + sNewspaper + "," + sSale + "\n");
                } else {
                    double TV = advertises.get(i).getTV();
                    double Radio = advertises.get(i).getRadio();
                    double Newspaper = advertises.get(i).getNewspaper();
                    double Sale = advertises.get(i).getSale();

                    writer.write(TV + "," + Radio + "," + Newspaper + "," + Sale + "\n");
                }
            }

            writer.close();

            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public double getNewspaperCounter() {
        return newspaperCounter;
    }

    public double getRadioCounter() {
        return radioCounter;
    }

    public double getSaleCounter() {
        return saleCounter;
    }

    public double getTvCounter() {
        return tvCounter;
    }
}
