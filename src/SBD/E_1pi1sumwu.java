package SBD;


import java.io.*;
import java.util.*;

/**
 * Created by pinkdonut on 03.07.2016.
 */
public class E_1pi1sumwu {
        static FastScanner in;
        static PrintWriter out;

        class Job {
            int dead;
            int weight;
            int num;

            public Job(int dead, int weight, int num) {
                this.dead = dead;
                this.weight = weight;
                this.num = num;
            }
        }

        public void solve() throws IOException {
            int n = in.nextInt();
            ArrayList<Integer> times = new ArrayList<>();
            ArrayList<Job> jobs = new ArrayList<>();
            ArrayList<Job> stock_jobs = new ArrayList<>();
            Comparator<Job> comparator = new JobsComparator();
            PriorityQueue<Job> pq = new PriorityQueue<>(comparator);

            for (int i = 0; i < n; i++) {
                times.add(-1);
                int dd = in.nextInt();
                int ww = in.nextInt();
                jobs.add(new Job(dd, ww, i));
                stock_jobs.add(new Job(dd, ww, i));
            }
        /*    Collections.sort(jobs, new Comparator<Job>() {
                @Override
                public int compare(Job o1, Job o2) {
                    if (o1.weight < o2.weight || (o1.weight == o2.weight && o1.num < o2.num))
                        return 1;
                    else
                        return -1;
                }
            });*/
            Collections.sort(jobs, (o1, o2) -> Integer.compare(o1.dead, o2.dead));

            int time = 1;
            long sum = 0;
            for (int i = 0; i < n; i++) {
                if (jobs.get(i).dead >= time) {
                    times.set(jobs.get(i).num, time);
                    time++;
                    pq.add(jobs.get(i));
                }
                else
                if (pq.peek().weight < jobs.get(i).weight) {
                    int tmp = times.get(pq.peek().num);
                    times.set(pq.peek().num, times.get(jobs.get(i).num));
                    times.set(jobs.get(i).num, tmp);
                //    sum += pq.peek().weight;
                    pq.poll();
                    pq.add(jobs.get(i));
                }
            }

            for (int i = 0; i < n; i++) {
                if (times.get(i) == -1) {
                    times.set(i, time);
                    time++;
                }
            //    out.println(times.get(i) - 1 + "+" + stock_jobs.get(i).dead + " " + stock_jobs.get(i).weight );
                if (times.get(i) - 1 >= stock_jobs.get(i).dead)
                    sum += stock_jobs.get(i).weight;
            }

            out.println(sum);
            for (int i = 0; i < n; i++)
                out.print(times.get(i) - 1 + " ");
            out.close();
        }

        public void run() {
            try {
                in = new FastScanner(new File("p1sumwu.in"));
                out = new PrintWriter(new File("p1sumwu.out"));

                solve();

                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        class FastScanner {
            BufferedReader br;
            StringTokenizer st;

            FastScanner(File f) {
                try {
                    br = new BufferedReader(new FileReader(f));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }

            String next() {
                while (st == null || !st.hasMoreTokens()) {
                    try {
                        st = new StringTokenizer(br.readLine());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                return st.nextToken();
            }

            int nextInt() {
                return Integer.parseInt(next());
            }

            public long nextLong() throws IOException {
                return Long.parseLong(next());
            }
        }

        public static void main(String[] arg) {
            new E_1pi1sumwu().run();
        }

    private class JobsComparator implements Comparator<Job> {
        @Override
        public int compare(Job o1, Job o2) {
            if (o1.weight > o2.weight) {
                return 1;
            }
            else
            if (o1.weight < o2.weight) {
                return -1;
            }
            else
            return 0;
        }
    }
}
