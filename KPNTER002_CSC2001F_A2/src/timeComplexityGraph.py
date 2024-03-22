import matplotlib.pyplot as plt

# Experimental data
dataset_sizes = [10, 100, 1000, 10000, 100000]
insert_min_counts = [3, 6, 8, 10, 13]
insert_max_counts = [20, 18, 18, 19, 20]
insert_avg_counts = [8, 8, 8, 8, 8]
search_min_counts = [1, 1, 1, 1, 1]
search_max_counts = [101, 16234, 3834, 889, 101]
search_avg_counts = [7, 3833, 557, 70, 7]

# Best case and worst case scenarios
insert_best_counts = insert_min_counts
insert_worst_counts = insert_max_counts
search_best_counts = search_min_counts
search_worst_counts = search_max_counts

# Plotting
plt.figure(figsize=(10, 6))

# Insert operation plot
plt.subplot(2, 1, 1)
plt.plot(dataset_sizes, insert_best_counts, marker='o', label='Best Case')
plt.plot(dataset_sizes, insert_worst_counts, marker='o', label='Worst Case')
plt.plot(dataset_sizes, insert_avg_counts, marker='o', label='Avg Comparison Counts')
plt.title('Experimental Time Complexity of Insert Operation')
plt.xlabel('Dataset Size')
plt.ylabel('Number of Operations')
plt.xscale('log')
plt.yscale('log')
plt.legend()

# Search operation plot
plt.subplot(2, 1, 2)
plt.plot(dataset_sizes, search_best_counts, marker='o', label='Best Case')
plt.plot(dataset_sizes, search_worst_counts, marker='o', label='Worst Case')
plt.plot(dataset_sizes, search_avg_counts, marker='o', label='Avg Comparison Counts')
plt.title('Experimental Time Complexity of Search Operation')
plt.xlabel('Dataset Size')
plt.ylabel('Number of Operations')
plt.xscale('log')
plt.yscale('log')
plt.legend()

plt.tight_layout()
plt.show()
