import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class GenericsKbAVLApp {

    public static void main(String[] args) {
        AVLTree<Statement> avlTree = new AVLTree<>();
        loadDataset(avlTree, "GenericsKB.txt");
        performQueries(avlTree, "GenericsKB-queries.txt");
    }

    private static void loadDataset(AVLTree<Statement> avlTree, String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length < 3) {
                    System.err.println("Invalid line: " + line);
                    continue;
                }
                String term = parts[0].trim();
                String sentence = parts[1].trim();
                double confidenceScore = Double.parseDouble(parts[2].trim());
                Statement statement = new Statement(term, sentence, confidenceScore);
                avlTree.insert(statement);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    

    private static void performQueries(AVLTree<Statement> avlTree, String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String term = line.trim();
                BinaryTreeNode<Statement> resultNode = avlTree.find(new Statement(term));
                if (resultNode != null) {
                    Statement result = resultNode.data;
                    System.out.println(result);
                } else {
                    System.out.println("Term not found: " + term);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
