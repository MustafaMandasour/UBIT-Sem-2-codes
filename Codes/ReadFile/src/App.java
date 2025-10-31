import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
public class App {
    public static void main(String[] args) {
        File file = new File("EVE01Sales.txt");
        if (!file.exists()) {
            System.out.println("File not found: " + file.getAbsolutePath());
            return;
        }
        long totalSum = 0; 
        Map<String, Long> salesMap = new HashMap<>(); 
        Map<String, Long> employeeSalesMap = new HashMap<>();
        Map<String, Long> regionMap = new HashMap<>(); 
        Map<String, Long> monthMap = new HashMap<>(); 
        try (Scanner scanner = new Scanner(file)) {
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                String[] array = line.split("\\s+");
                if (array.length >= 7) {
                    try {                        
                        int qty = Integer.parseInt(array[5].trim());
                        int unitPrice = Integer.parseInt(array[6].trim());
                        long totalSales = (long)qty * unitPrice; 
                        totalSum += totalSales;
                        String E_Id = array[2].trim();
                        employeeSalesMap.put(E_Id, employeeSalesMap.getOrDefault(E_Id, 0L)+totalSales);
                        String product  = array[3].trim() + " " + array[4].trim(); 
                        salesMap.put(product, salesMap.getOrDefault(product, 0L) + totalSales);
                        String region = array[1].trim();
                        regionMap.put(region, regionMap.getOrDefault(region, 0L)+totalSales);
                        String date = array[0].trim();
                        String month = "9999";
                        if(date.contains("Jun")) month = "June"; 
                        if(date.contains("Jul")) month = "July";
                        if(date.contains("Aug")) month = "August";
                        monthMap.put(month, monthMap.getOrDefault(month, 0L)+totalSales);
                    } catch (NumberFormatException e) {
                        System.out.println("Skipping invalid numeric data (Expected numbers at index 5 and 6). Line: " + line);
                    }
                } else {
                    System.out.println("Skipping malformed line (expected 7+ fields due to product split): " + line);
                }
            }
            System.out.println("\n--- Sales Report ---");
            System.out.println("Total Sales: " + totalSum);
            System.out.println("\nSales by Item:");
            salesMap.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .forEach(entry -> System.out.printf("  %-20s : %d%n", entry.getKey(), entry.getValue()));
            System.out.println("--------------------");
            System.out.println("\nSales by Employee (Rep ID):");
            employeeSalesMap.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .forEach(entry -> System.out.printf("  %-20s : %d%n", entry.getKey(), entry.getValue()));
            
            System.out.println("--------------------");
            System.out.println("\nSales by Region:");
            regionMap.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .forEach(entry -> System.out.printf("  %-20s : %d%n", entry.getKey(), entry.getValue()));
            
            System.out.println("--------------------");
            System.out.println("\nSales by Month:");
            monthMap.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .forEach(entry -> System.out.printf("  %-20s : %d%n", entry.getKey(), entry.getValue()));
            
            System.out.println("--------------------");
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}
