#include <bits/stdc++.h>
using namespace std;

// Test input - 7 0 1 2 0 3 0 4 2 3 0 3 2

class PageReplacement
{
public:
    void FIFO(int numberOfPages, int memorySize, vector<int> pageStream)
    {
        set<int> pageHolder;
        queue<int> pageBlocks;
        int miss = 0;

        for (int i = 0; i < numberOfPages; i++) {
            int currentPage = pageStream[i];

            if (pageHolder.find(currentPage) != pageHolder.end()) {
                miss--;
            } else {
                if (pageHolder.size() < memorySize) {
                    pageBlocks.push(currentPage);
                    pageHolder.insert(currentPage);
                } else {
                    // Perform Page replacement
                    int toBeReplaced = pageBlocks.front();
                    pageHolder.erase(toBeReplaced);
                    pageBlocks.pop();
                    pageBlocks.push(currentPage);
                    pageBlocks.push(currentPage);
                }

                miss++;
            }
        }

        cout << "Page Faults: " << miss << endl;
        cout << "Hits: " << numberOfPages - miss << endl;
    }

    void LRU(int numberOfPages, int memorySize, vector<int> pageStream)
    {
        unordered_set<int> pageHolder;
        unordered_map<int, int> indexes;
        int miss = 0, hit = 0;
        for (int i = 0; i < numberOfPages; i++) {
            int currentPage = pageStream[i];
            if (pageHolder.find(currentPage) != pageHolder.end()) {
                // Hit condition
                hit++;
                indexes[currentPage] = i;
            } else {
                // Miss condition

                if (pageHolder.size() < memorySize) {
                    pageHolder.insert(currentPage);
                    indexes[currentPage] = i;
                } else {
                    int minIdx = INT_MAX, minValue;
                    for (auto it = pageHolder.begin(); it != pageHolder.end(); it++) {
                        if (indexes[*it] < minIdx) {
                            minIdx = indexes[*it];
                            minValue = *it;
                        }
                    }

                    pageHolder.erase(minValue);
                    pageHolder.insert(currentPage);
                    indexes[currentPage] = i;
                }
                miss++;
            }

            for (auto it = pageHolder.begin(); it != pageHolder.end(); it++) {
                cout << *it << " ";
            }

            cout << endl;
        }
        cout << "Page Faults: " << miss << endl;
        cout << "Hits: " << numberOfPages - miss << endl;
    }

    void optimalPageReplacement(int numberOfPages, int memorySize, vector<int> pageStream)
    {
        unordered_set<int> pageHolder;

        int miss = 0, hit = 0;
        for (int i = 0; i < numberOfPages; i++) {
            int currentPage = pageStream[i];

            if (pageHolder.find(currentPage) != pageHolder.end()) {
                hit++;
            } else {
                if (pageHolder.size() < memorySize) {
                    pageHolder.insert(currentPage);
                } else {
                    // Perform Optimal replacement

                    int farthestIdx = -1, farthestValue, j;
                    
                    for (auto it = pageHolder.begin(); it != pageHolder.end(); it++) {
                        for (j = i; j < numberOfPages; j++) {
                            if (pageStream[j] == *it) {
                                if (j > farthestIdx) {
                                    farthestIdx = j;
                                    farthestValue = *it;
                                }
                                break;
                            }
                        }

                        if (j == numberOfPages) {
                            farthestValue = *it;
                            break;
                        }
                    }

                    pageHolder.erase(farthestValue);
                    pageHolder.insert(currentPage);
                }
                    miss++;

            }

            for (auto it = pageHolder.begin(); it != pageHolder.end(); it++)
            {
                    cout << *it << " ";
            }

            cout << endl;
        }
            cout << "Page Faults: " << miss << endl;
            cout << "Hits: " << numberOfPages - miss << endl;
    }
};

int main()
{

    PageReplacement pg;

    int numPages, memSize;
    cout << "Enter number of pages: ";
    cin >> numPages;

    cout << "Enter the memory size: ";
    cin >> memSize;

    vector<int> pageStream(numPages, -1);
    int i = 0;
    cout << "Enter the page stream: ";
    while (i < numPages)
    {

        cin >> pageStream[i++];
    }

    pg.FIFO(numPages, memSize, pageStream);
    pg.LRU(numPages, memSize, pageStream);
    pg.optimalPageReplacement(numPages, memSize, pageStream);
    return 0;
}
