import java.io.*;
import java.util.*;

public class RoboticTrajectory {
    static class Item {
        int weight;
        int value;
        
        Item(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }
    }
    
    // Read input from file using absolute path
    public static List<String> readInputFile(String absolutePath) {
        List<String> lines = new ArrayList<>();
        File file = new File(absolutePath);

        if (!file.exists()) {
            System.err.println("Error: The file " + absolutePath + " does not exist!");
            System.exit(1);
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading input file: " + e.getMessage());
            System.exit(1);
        }

        return lines;
    }
    
    // Parse input data
    public static Item[] parseItems(List<String> lines) {
        String[] valueStr = lines.get(0).split(",");
        String[] weightStr = lines.get(1).split(",");
        
        Item[] items = new Item[valueStr.length];
        for (int i = 0; i < valueStr.length; i++) {
            items[i] = new Item(
                Integer.parseInt(weightStr[i].trim()),
                Integer.parseInt(valueStr[i].trim())
            );
        }
        return items;
    }
    
    // Find optimal solution using dynamic programming
    public static int[] findOptimalLoad(Item[] items, int capacity) {
        int n = items.length;
        int[][] dp = new int[n + 1][capacity + 1];
        boolean[][] selected = new boolean[n + 1][capacity + 1];
        
        for (int i = 1; i <= n; i++) {
            for (int w = 0; w <= capacity; w++) {
                if (items[i-1].weight <= w) {
                    int includeItem = dp[i-1][w - items[i-1].weight] + items[i-1].value;
                    int excludeItem = dp[i-1][w];
                    if (includeItem > excludeItem) {
                        dp[i][w] = includeItem;
                        selected[i][w] = true;
                    } else {
                        dp[i][w] = excludeItem;
                    }
                } else {
                    dp[i][w] = dp[i-1][w];
                }
            }
        }
        
        // Backtrack to find selected items
        List<Integer> selectedItems = new ArrayList<>();
        int w = capacity;
        for (int i = n; i > 0; i--) {
            if (selected[i][w]) {
                selectedItems.add(i-1);
                w = w - items[i-1].weight;
            }
        }
        
        return selectedItems.stream().mapToInt(Integer::intValue).toArray();
    }
    
    // Write results to output file
    public static void writeOutput(String absolutePath, Item[] items, int[] selectedItems, int capacity) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(absolutePath))) {
            int totalWeight = 0;
            int totalValue = 0;
            
            writer.println("Optimal Robot Payload Configuration:");
            writer.println("-----------------------------------");
            
            for (int idx : selectedItems) {
                writer.printf("Item %d: Weight = %d lbs, Value = $%d%n", 
                    idx + 1, items[idx].weight, items[idx].value);
                totalWeight += items[idx].weight;
                totalValue += items[idx].value;
            }
            
            writer.println("-----------------------------------");
            writer.printf("Total Weight: %d/%d lbs%n", totalWeight, capacity);
            writer.printf("Total Value: $%d%n", totalValue);
            
            if (totalWeight > capacity) {
                writer.println("WARNING: Weight exceeds robot capacity!");
            }
            
        } catch (IOException e) {
            System.err.println("Error writing output file: " + e.getMessage());
            System.exit(1);
        }
    }
    
    public static void main(String[] args) {
        // Provide the absolute path for input and output files
        String inputFile = "C:\\Users\\khair\\Downloads\\Week7\\input.txt"; // Change this to your actual file path
        String outputFile = "C:\\Users\\khair\\Downloads\\Week7\\output.txt"; // Change this to your actual file path
        
        // Print the file path for debugging
        System.out.println("Reading input from: " + inputFile);
        System.out.println("Writing output to: " + outputFile);
        
        // Read and parse input
        List<String> lines = readInputFile(inputFile);
        Item[] items = parseItems(lines);
        int capacity = Integer.parseInt(lines.get(2).trim());
        
        // Find optimal solution
        int[] selectedItems = findOptimalLoad(items, capacity);
        
        // Write results
        writeOutput(outputFile, items, selectedItems, capacity);
        
        System.out.println("Processing complete. Check output.txt for results.");
    }
}
