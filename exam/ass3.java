import java.util.*;

class process {
    int arrivaltime;
    int bursttime;
    int waitingtime;
    int turnaroundtime;
    int startingtime;
    int priority;
    int processid;
    int comptime = 0;
    int completiontime;
}

class algo {
    int t;
    int tq;
    process[] processarray;

    Scanner sc = new Scanner(System.in);

    float avgwaitingtime() {
        float avg = 0;
        for (int i = 0; i < t; i++) {
            avg += processarray[i].waitingtime;
        }
        return (avg / t);
    }

    float avgturnaroundtime() {
        float avg = 0;
        for (int i = 0; i < t; i++) {
            avg += processarray[i].turnaroundtime;
        }
        return (avg / t);
    }

    void fcfsgetdata() {
        System.out.println("FCFS Scheduling Algorithm");
        System.out.println("Enter the no. of processes : ");
        t = sc.nextInt();
        processarray = new process[t];
        for (int i = 0; i < t; i++) {
            processarray[i] = new process();
            System.out.println("For Process " + i + ":");
            processarray[i].processid = i;
            System.out.print("Enter the Arrival Time :");
            processarray[i].arrivaltime = sc.nextInt();
            System.out.print("Enter the Burst Time :");
            processarray[i].bursttime = sc.nextInt();
        }
    }

    void fcfsgettimes() {
        int sum = 0;
        processarray[0].startingtime = 0;
        for (int i = 1; i < t; i++) {
            sum = sum + processarray[i - 1].bursttime;
            processarray[i].startingtime = sum;
        }
        for (int i = 0; i < t; i++) {
            processarray[i].waitingtime = processarray[i].startingtime - processarray[i].arrivaltime;
            processarray[i].turnaroundtime = processarray[i].waitingtime + processarray[i].bursttime;

        }
    }

    void fcfsshowdata() {
        System.out.println("Process \t ArrivalTime \t BurstTime \t WaitingTime \t TurnaroundTime");
        for (int i = 0; i < t; i++) {
            System.out.println("P" + processarray[i].processid + "\t\t" + processarray[i].arrivaltime + "\t\t"
                    + processarray[i].bursttime
                    + "\t\t" + processarray[i].waitingtime + "\t\t" + processarray[i].turnaroundtime);
        }
        float avgwt = avgwaitingtime();
        float avgtat = avgturnaroundtime();
        System.out.println("Avg. Waiting Time : " + avgwt);
        System.out.println("Avg. Turn Around Time : " + avgtat);
    }

    void fcfs() {
        fcfsgetdata();
        fcfsgettimes();
        fcfsshowdata();
    }

    void sjfgetdata() {
        System.out.println("SJF Scheduling Algorithm");
        System.out.println("Enter the no. of processes : ");
        t = sc.nextInt();
        processarray = new process[t];
        for (int i = 0; i < t; i++) {
            processarray[i] = new process();
            System.out.println("For Process " + i + ":");
            processarray[i].processid = i;
            System.out.print("Enter the Burst Time :");
            processarray[i].bursttime = sc.nextInt();
        }
    }

    void sjfsort() {
        for (int i = 0; i < t; i++) {
            for (int j = i + 1; j < t; j++) {
                if (processarray[i].bursttime > processarray[j].bursttime) {
                    int temptime = processarray[i].bursttime;
                    processarray[i].bursttime = processarray[j].bursttime;
                    processarray[j].bursttime = temptime;
                }
            }
        }
    }

    void sjfgettimes() {
        sjfsort();
        // waiting time calculation
        processarray[0].waitingtime = 0;
        for (int i = 1; i < t; i++) {
            processarray[i].waitingtime = 0;
            for (int j = 0; j < i; j++) {
                processarray[i].waitingtime = processarray[i].waitingtime + processarray[j].bursttime;
            }
        }
        // turnaround time calculation
        for (int i = 0; i < t; i++) {
            processarray[i].turnaroundtime = processarray[i].waitingtime + processarray[i].bursttime;
        }
    }

    void sjfshowdata() {
        System.out.println("Process \t BurstTime \t WaitingTime \t TurnaroundTime");
        for (int i = 0; i < t; i++) {
            System.out.println("P" + processarray[i].processid + "\t\t" + processarray[i].bursttime
                    + "\t\t" + processarray[i].waitingtime + "\t\t" + processarray[i].turnaroundtime);
        }
        float avgwt = avgwaitingtime();
        float avgtat = avgturnaroundtime();
        System.out.println("Avg. Waiting Time : " + avgwt);
        System.out.println("Avg. Turn Around Time : " + avgtat);
    }

    void sjf() {
        sjfgetdata();
        sjfgettimes();
        sjfshowdata();
    }

    void prioritygetdata() {
        System.out.println("Priority Scheduling Algorithm");
        System.out.println("Enter the no. of processes : ");
        t = sc.nextInt();
        processarray = new process[t];
        for (int i = 0; i < t; i++) {
            processarray[i] = new process();
            System.out.println("For Process " + i + ":");
            processarray[i].processid = i;
            System.out.print("Enter the Burst Time :");
            processarray[i].bursttime = sc.nextInt();
            System.out.print("Enter the Priority :");
            processarray[i].priority = sc.nextInt();
        }
    }

    void prioritysort() {
        for (int i = 0; i < t; i++) {
            for (int j = i + 1; j < t; j++) {
                if (processarray[i].priority > processarray[j].priority) {
                    process temp = processarray[i];
                    processarray[i] = processarray[j];
                    processarray[j] = temp;
                }
            }
        }
    }

    void prioritygettimes() {
        prioritysort();
        // waiting time calculation
        processarray[0].waitingtime = 0;
        for (int i = 1; i < t; i++) {
            processarray[i].waitingtime = 0;
            for (int j = 0; j < i; j++) {
                processarray[i].waitingtime = processarray[i].waitingtime + processarray[j].bursttime;
            }
        }
        // turnaround time calculation
        for (int i = 0; i < t; i++) {
            processarray[i].turnaroundtime = processarray[i].waitingtime + processarray[i].bursttime;
        }
    }

    void priorityshowdata() {
        System.out.println("Process \t Priority \t BurstTime \t WaitingTime \t TurnaroundTime");
        for (int i = 0; i < t; i++) {
            System.out.println("P" + processarray[i].processid + "\t\t" + processarray[i].priority + "\t\t"
                    + processarray[i].bursttime
                    + "\t\t" + processarray[i].waitingtime + "\t\t" + processarray[i].turnaroundtime);
        }
        float avgwt = avgwaitingtime();
        float avgtat = avgturnaroundtime();
        System.out.println("Avg. Waiting Time : " + avgwt);
        System.out.println("Avg. Turn Around Time : " + avgtat);
    }

    void priorityscheduling() {
        prioritygetdata();
        prioritygettimes();
        priorityshowdata();
    }

    void rrgetdata() {
        System.out.println("Priority Scheduling Algorithm");
        System.out.println("Enter the no. of processes : ");
        t = sc.nextInt();
        processarray = new process[t];
        System.out.print("Enter the Time Quantum :");
        tq = sc.nextInt();
        for (int i = 0; i < t; i++) {
            processarray[i] = new process();
            System.out.println("For Process " + i + ":");
            processarray[i].processid = i;
            System.out.print("Enter the Burst Time :");
            processarray[i].bursttime = sc.nextInt();
        }
    }

    void rrgettimes() {

    }

    void rrshowdata() {
        System.out.println("Process \t ArrivalTime \t BurstTime \t WaitingTime \t TurnaroundTime");
        for (int i = 0; i < t; i++) {
            System.out.println("P" + processarray[i].processid + "\t\t" + processarray[i].arrivaltime + "\t\t"
                    + processarray[i].bursttime
                    + "\t\t" + processarray[i].waitingtime + "\t\t" + processarray[i].turnaroundtime);
        }
        float avgwt = avgwaitingtime();
        float avgtat = avgturnaroundtime();
        System.out.println("Avg. Waiting Time : " + avgwt);
        System.out.println("Avg. Turn Around Time : " + avgtat);
    }

    void rr() {
        rrgetdata();
        rrgettimes();
        rrshowdata();
    }

}

public class ass3 {

    public static void menu() {
        System.out.println("ASS 1 - SCHEDULING ALGORITHMS");
        System.out.println("1. FCFS");
        System.out.println("2. SJF");
        System.out.println("3. Priority Scheduling");
        System.out.println("4. Round Robin");
        System.out.println("5. Exit");
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
                    obj.fcfs();
                    break;
                }
                case 2: {
                    obj.sjf();
                    break;
                }
                case 3: {
                    obj.priorityscheduling();
                    break;
                }
                case 4: {
                    obj.rr();
                    break;
                }
                default:
                    break;
            }

        } while (choice < 5);
        sc.close();
        System.out.println("Exited");
    }
}