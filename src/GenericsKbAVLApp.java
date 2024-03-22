import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Welcome to the GenericsKbAVLApp - your tool for storing and retrieving knowledge efficiently!
 * This application demonstrates the usage of an AVL binary search tree data structure to store
 * and retrieve data items consisting of a term (used as the key), sentence, and confidence score.
 */

public class GenericsKbAVLApp {
    /**
     * Main method - where the application begins execution.(where the magic happens)
     * Initializes AVL tree, loads dataset, performs queries, and runs experiments.
     *
     * @param args Command-line arguments (not used)
     */

    public static void main(String[] args) {
        // Initialize AVL tree to store our knowledge
        AVLTree<Statement> avlTree = new AVLTree<>();
        // Load dataset and perform queries
        loadDataset(avlTree, "GenericsKB.txt");
        performQueries(avlTree, "test_queries.txt");

        // Experiment : Define dataset sizes to test
        int[] datasetSizes = {10, 100, 1000, 10000, 100000};

        // Run experiment for each dataset size
        for (int size : datasetSizes) {
            // Reset AVL tree before each experiment
            avlTree = new AVLTree<>();
            runExperiment(avlTree, size);
        }
    }

    /**
     * Method to load dataset into AVL tree.
     * Each line of the dataset should be in the format: term\tsentence\tconfidenceScore
     *
     * @param avlTree  AVL tree instance to store the data items
     * @param filename Name of the file containing the dataset
     */

    private static void loadDataset(AVLTree<Statement> avlTree, String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                processLine(line, avlTree);
            }
            System.out.println("Dataset loaded successfully."); // Success message
            // printLoadedStatements(avlTree); // Display loaded statements
        } catch (IOException e) {
            e.printStackTrace(); // Oops, something went wrong!
        }
    }

    /**
     * Method to run experiments with varying dataset sizes.
     * Inserts random terms into the AVL tree and measures comparison counts for insert and search operations.
     *
     * @param avlTree    AVL tree instance for the experiment
     * @param datasetSize Size of the dataset to be generated and tested
     */
    private static void runExperiment(AVLTree<Statement> avlTree, int datasetSize) {
        // Initialize variables to store comparison counts
        int minInsertComparisonCount = Integer.MAX_VALUE;
        int maxInsertComparisonCount = Integer.MIN_VALUE;
        int minSearchComparisonCount = Integer.MAX_VALUE;
        int maxSearchComparisonCount = Integer.MIN_VALUE;
        int totalInsertComparisonCount = 0;
        int totalSearchComparisonCount = 0;
    
        List<String> termsList = new ArrayList<>(); // List to store terms from the dataset
    
        // Load all terms from the dataset
        try (BufferedReader br = new BufferedReader(new FileReader("GenericsKB.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\t");
                if (parts.length >= 1) {
                    String term = parts[0];
                    termsList.add(term); // Add term to the list
                }
            }
        } catch (IOException e) {
            e.printStackTrace(); // Handle IO exception
        }
    
        // Generate and insert random terms
        for (int i = 0; i < datasetSize; i++) {
            String randomTerm = termsList.get(new Random().nextInt(termsList.size())); // Randomly select a term from the list
            avlTree.insert(new Statement(randomTerm, "Sample sentence", 1.0)); // Insert the selected term into the AVL tree
        }
    
        // Perform search operation for each term
        for (String term : termsList) {
            BinaryTreeNode<Statement> resultNode = avlTree.find(new Statement(term));
            if (resultNode != null) {
                // Increment search comparison count
                int searchComparisonCount = avlTree.getSearchComparisonCount();
                minSearchComparisonCount = Math.min(minSearchComparisonCount, searchComparisonCount);
                maxSearchComparisonCount = Math.max(maxSearchComparisonCount, searchComparisonCount);
                totalSearchComparisonCount += searchComparisonCount;
                avlTree.resetComparisonCounts(); // Reset search comparison count for the next search
            }
        }
    
        // Perform insertion operation for each term (again for the sake of experiment)
        for (String term : termsList) {
            avlTree.insert(new Statement(term, "Sample sentence", 1.0));
            // Increment insert comparison count
            int insertComparisonCount = avlTree.getInsertComparisonCount();
            minInsertComparisonCount = Math.min(minInsertComparisonCount, insertComparisonCount);
            maxInsertComparisonCount = Math.max(maxInsertComparisonCount, insertComparisonCount);
            totalInsertComparisonCount += insertComparisonCount;
            avlTree.resetComparisonCounts(); // Reset insert comparison count for the next insertion
        }
    
        // Print experiment results
        System.out.println("Experiment with dataset size: " + datasetSize);
        System.out.println("Insert Comparison Counts: Min=" + minInsertComparisonCount +
                ", Max=" + maxInsertComparisonCount + ", Avg=" + (totalInsertComparisonCount / datasetSize));
        System.out.println("Search Comparison Counts: Min=" + minSearchComparisonCount +
                ", Max=" + maxSearchComparisonCount + ", Avg=" + (totalSearchComparisonCount / datasetSize));
        System.out.println();
    }


    // Method to print all loaded statements
    private static void printLoadedStatements(AVLTree<Statement> avlTree) {
        // Let's showcase the knowledge we've loaded!
        System.out.println("Outputting All Loaded Statements:");
        avlTree.treeOrder(); // Displaying in a tree-like structure
        System.out.println();
    }


    // Method to process each line of the dataset
    private static void processLine(String line, AVLTree<Statement> avlTree) {
        String[] parts = line.split("\t");
        if (parts.length == 3) {
            String term = parts[0];
            String sentence = parts[1];
            double confidenceScore = Double.parseDouble(parts[2]);
            Statement statement = new Statement(term, sentence, confidenceScore);
            avlTree.insert(statement); // Inserting statement into AVL tree
        } else {
            System.err.println("Invalid line in dataset: " + line); // Invalid data format
        }
    }

    /**
     * Method to perform queries on the loaded dataset.
     * Reads query terms from a file and searches for each term in the AVL tree.
     *
     * @param avlTree  AVL tree instance containing the dataset
     * @param filename Name of the file containing the query terms
     */

    private static void performQueries(AVLTree<Statement> avlTree, String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String term = line.trim();
                BinaryTreeNode<Statement> resultNode = avlTree.find(new Statement(term));
                if (resultNode != null) {
                    Statement result = resultNode.data;
                    System.out.println(result); // Displaying query result
                } else {
                    System.out.println("Term not found: " + term); // Query term not found
                }
            }
        } catch (IOException e) {
            e.printStackTrace(); // Oops, something went wrong while performing queries
        }
    }
}
