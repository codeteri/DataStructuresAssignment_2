import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class GenericsKbAVLApp {

    public static void main(String[] args) {
        AVLTree<Statement> avlTree = new AVLTree<>();
        loadDataset(avlTree, "GenericsKB.txt");
        // printLoadedStatements(avlTree);
        performQueries(avlTree, "GenericsKB-queries.txt");
    }

    private static void printLoadedStatements(AVLTree<Statement> avlTree) {
        System.out.println("Loaded Statements:");
        avlTree.treeOrder();
        System.out.println();
    }

    private static void loadDataset(AVLTree<Statement> avlTree, String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                processLine(line, avlTree);
            }
            System.out.println("Dataset loaded successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void processLine(String line, AVLTree<Statement> avlTree) {
        String[] parts = line.split("\t");
        if (parts.length == 3) {
            String term = parts[0];
            String sentence = parts[1];
            double confidenceScore = Double.parseDouble(parts[2]);
            Statement statement = new Statement(term, sentence, confidenceScore);
            avlTree.insert(statement);
        } else {
            System.err.println("Invalid line in dataset: " + line);
        }
    }

    private static void performQueries(AVLTree<Statement> avlTree, String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String term = line.trim();
                BinaryTreeNode<Statement> resultNode = avlTree.find(new Statement(term));
                // System.out.println(resultNode);
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
