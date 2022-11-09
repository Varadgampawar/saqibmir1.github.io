/*
 * Name - Prathamesh Gadilohar
 * Roll no. - 25
 * Div - TE-A
 *
 * ASS 2 - MEMORY FITTING ALGORITHMS
 *
 * 1. First Fit
 * 2. Best Fit
 * 3. Worst Fit
 * 4. Next Fit
 */

import java.util.*;

// elements inside every memory block
class memory {
    int size;
    int blockno;
    Vector<Integer> processidrecord = new Vector<Integer>(); // vector for storing all processes occupying this memory
    // block
    Vector<Integer> remainingmemoryrecord = new Vector<Integer>(); // vector for storing all processes occupying this
                                                                   // memory
    // block
    boolean isempty = true; // true if 'processidrecord' is empty, else false
}

// elements inside every process
class process {
    int processid;
    int reqsize; // required size of process
    boolean isallocated = false; // false if process is not allocated to any memory block, else true
    int allocatedblockno = -1;
}

// class containing all algorithms and utility functions
class algo {
    Scanner sc = new Scanner(System.in);

    // data members (variables)

    int p; // no. of processes
    int m; // no. of memory blocks
    int count = 0; // counter for identifying allocated processes
    process processarray[]; // array of objects for processes
    memory memoryarray[]; // array of objects for memory blocks
    process flushprocessarray[]; // copy of original process array
    memory flushmemoryarray[]; // copy of original memory array

    // utility functions

    void getdata() {
        // get data for process array
        System.out.print("Enter the no. of processes : ");
        p = sc.nextInt();
        processarray = new process[p];
        flushprocessarray = new process[p];
        for (int i = 0; i < p; i++) {
            processarray[i] = new process();
            flushprocessarray[i] = new process();
            System.out.println("For Process " + i + " : ");
            processarray[i].processid = i;
            flushprocessarray[i].processid = processarray[i].processid;
            System.out.print("Enter the required memory : ");
            processarray[i].reqsize = sc.nextInt();
            flushprocessarray[i].reqsize = processarray[i].reqsize;
        }
        // get data for memory array
        System.out.print("Enter the no. of memory blocks : ");
        m = sc.nextInt();
        memoryarray = new memory[m];
        flushmemoryarray = new memory[m];
        for (int i = 0; i < m; i++) {
            memoryarray[i] = new memory();
            flushmemoryarray[i] = new memory();
            System.out.println("For Memory Block " + i + " : ");
            memoryarray[i].blockno = i;
            flushmemoryarray[i].blockno = memoryarray[i].blockno;
            System.out.print("Enter the memory size : ");
            memoryarray[i].size = sc.nextInt();
            flushmemoryarray[i].size = memoryarray[i].size;
        }
    }

    void allocate() {
        for (int i = 0; i < p; i++) {
            for (int j = 0; j < m; j++) {
                if (processarray[i].reqsize <= memoryarray[j].size) { // check if process can fit in memory block
                    processarray[i].allocatedblockno = memoryarray[j].blockno; // copy memory block no in process
                    memoryarray[j].size -= processarray[i].reqsize; // calculate remaining size of memory block after
                                                                    // allocation
                    processarray[i].isallocated = true;
                    memoryarray[j].isempty = false;
                    memoryarray[j].processidrecord.add(processarray[i].processid); // insert processid in
                    // memory block
                    memoryarray[j].remainingmemoryrecord.add(memoryarray[j].size); // insert remaining memory in
                                                                                   // remainingmemoryrecord
                    count++; // increase counter of allocated processes
                    break;
                }
            }
        }
    }

    void showdata() {
        System.out.println("Memory Block No.\tInitial Size \tMemory Block Size\tProcesses");
        for (int i = 0; i < m; i++) {
            System.out.print(memoryarray[i].blockno + "\t\t\t" + flushmemoryarray[i].size + "\t\t\t"
                    + memoryarray[i].size + "\t\t\t");
            if (memoryarray[i].isempty == true) { // if memory block does not have any processes
                System.out.print("-");
            } else {
                for (int j = 0; j < memoryarray[i].processidrecord.size(); j++) {
                    System.out.print("P" + memoryarray[i].processidrecord.get(j) + " ("
                            + memoryarray[i].remainingmemoryrecord.get(j) + ") " + "\t");
                }
            }
            System.out.println();
        }
        if (count < p) { // if count of allocated processes is less than total no of processes (if some
                         // process are not allocated)
            System.out.println("Unallocated Processes : ");
            for (int i = 0; i < p; i++) {
                if (processarray[i].isallocated == false) {
                    System.out.print("P" + processarray[i].processid + "\t");
                }
            }
            System.out.println();
        }
    }

    void flushdata() {
        // reset original process array
        for (int i = 0; i < p; i++) {
            processarray[i].processid = flushprocessarray[i].processid;
            processarray[i].reqsize = flushprocessarray[i].reqsize;
            processarray[i].allocatedblockno = -1;
            processarray[i].isallocated = false;
        }
        // reset original memory array
        for (int i = 0; i < m; i++) {
            memoryarray[i].blockno = flushmemoryarray[i].blockno;
            memoryarray[i].size = flushmemoryarray[i].size;
            memoryarray[i].isempty = true;
            memoryarray[i].processidrecord.clear();
            memoryarray[i].remainingmemoryrecord.clear();
        }
    }

    // algorithms

    void firstfit() {
        allocate();
        showdata();
        flushdata();
    }

    void bestfit() {
        // sorting memory array in ascending order
        for (int i = 0; i < m; i++) {
            for (int j = i + 1; j < m; j++) {
                if (memoryarray[i].size > memoryarray[j].size) {
                    int tempblockno = memoryarray[i].blockno;
                    int tempsize = memoryarray[i].size;
                    memoryarray[i].blockno = memoryarray[j].blockno;
                    memoryarray[i].size = memoryarray[j].size;
                    memoryarray[j].blockno = tempblockno;
                    memoryarray[j].size = tempsize;
                }
            }
        }
        allocate();
        showdata();
        flushdata();
    }

    void worstfit() {
        // sorting memory array in descending order
        for (int i = 0; i < m; i++) {
            for (int j = i + 1; j < m; j++) {
                if (memoryarray[i].size < memoryarray[j].size) {
                    int tempblockno = memoryarray[i].blockno;
                    int tempsize = memoryarray[i].size;
                    memoryarray[i].blockno = memoryarray[j].blockno;
                    memoryarray[i].size = memoryarray[j].size;
                    memoryarray[j].blockno = tempblockno;
                    memoryarray[j].size = tempsize;
                }
            }
        }
        allocate();
        showdata();
        flushdata();
    }

    void nextfit() {
        int start = -1; // store the block no of previously accessed memory block
        for (int i = 0; i < p; i++) {
            for (int j = start + 1; j < m; j++) {
                if (processarray[i].reqsize <= memoryarray[j].size) {
                    processarray[i].allocatedblockno = memoryarray[j].blockno;
                    memoryarray[j].size -= processarray[i].reqsize;
                    processarray[i].isallocated = true;
                    memoryarray[j].isempty = false;
                    memoryarray[j].processidrecord.add(processarray[i].processid);
                    memoryarray[j].remainingmemoryrecord.add(memoryarray[j].size);
                    count++;
                    start = memoryarray[j].blockno; // update the start variable
                    break;
                }
            }
        }
        showdata();
        flushdata();
    }
}

public class ass4 {
    // menu
    public static void menu() {
        System.out.println("ASS 2 - MEMORY PLACEMENT STRATEGIES");
        System.out.println("1. Enter data");
        System.out.println("2. First Fit");
        System.out.println("3. Best Fit");
        System.out.println("4. Worst Fit");
        System.out.println("5. Next Fit");
        System.out.println("6. Exit");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;
        algo obj = new algo();
        do {
            menu();
            System.out.println("Enter your choice : ");
            choice = sc.nextInt();
            switch (choice) {
                case 1: {
                    obj.getdata();
                    break;
                }
                case 2: {
                    obj.firstfit();
                    break;
                }
                case 3: {
                    obj.bestfit();
                    break;
                }
                case 4: {
                    obj.worstfit();
                    break;
                }
                case 5: {
                    obj.nextfit();
                    break;
                }
                default: {
                    System.out.println("Enter valid choice!");
                    break;
                }
            }

        } while (choice != 6);
        System.out.println("Exited");
        sc.close();
    }
}