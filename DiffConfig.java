import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;
import java.util.List;
import java.util.ArrayList;

public class DiffConfig {
    
    public static void main(String args[]) {
        new DiffTool().compare(args);
    }
    
    private static class DiffTool {
    
        String pathA, pathB;
        
        private List<String> readContents(String path) {
            List<String> list = new ArrayList<>();
            try {
                Stream<String> stream = Files.lines(Paths.get(path));
                stream.forEach(line -> {
                    if (!line.startsWith("#") && !line.startsWith("\n")) list.add(line);
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Read " + list.size() + " lines from " + path);
            return list;
        }
        
        private void diff(List<String> contentsA, List<String> contentsB) {
            int countA = 0, countAC = 0, countB = 0, countC = 0;
            // First diff A against B
            System.out.printf("\nConfigs present in " + pathA + " but not in " + pathB + "\n##################################\n");
            for (String line: contentsA) {
                if (!contentsB.contains(line)) {
                    System.out.println(line);
                    countB++;
                } else countA++;
            }
            
            System.out.printf("\nConfigs present in " + pathB + " but not in " + pathA + "\n##################################\n");
            for (String line: contentsB) {
                if (!contentsA.contains(line)) {
                    System.out.println(line);
                    countC++;
                } else countAC++;
            }
            
            System.out.printf("\n\nResult\n#####################################\n");
            System.out.println("Enabled configs in " + pathA + " - " + contentsA.size());
            System.out.println("Enabled configs in " + pathB + " - " + contentsB.size());
            System.out.println("No. of configs present in both " + pathA + " and " + pathB + " - " + countA + (countA == countAC ? " (verified)": " (verification failed, check value - " + countAC + ")"));
            System.out.println("No. of configs present in " + pathA + " but not in " + pathB + " - " + countB);
            System.out.println("No. of configs present in " + pathB + " but not in " + pathA + " - " + countC);
        }
        
        public void compare(String[] paths) {
            if (paths.length != 2) {
                System.out.println("Must supply 2 paths");
                return;
            }
            pathA = paths[0];
            pathB = paths[1];
            diff(readContents(pathA), readContents(pathB));
        }
    }
}
