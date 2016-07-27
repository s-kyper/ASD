package SBD;

import java.io.*;
import java.util.*;

/**
 * Created by pinkdonut on 23.07.2016.
 */

public class N_f2cmax {
    static FastScanner in;
    static PrintWriter out;

    class Job {
        int num;
        int p1;
        int p2;

        public Job(int num, int p1, int p2) {
            this.num = num;
            this.p1 = p1;
            this.p2 = p2;
        }
    }

    public void solve() throws IOException {
        int n = in.nextInt();
        ArrayList<Job> jobs = new ArrayList<>();
        ArrayList<Integer> p1 = new ArrayList<>();
        ArrayList<Integer> p2 = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            p1.add(in.nextInt());
        }
        for (int i = 0; i < n; i++) {
            p2.add(in.nextInt());
        }

        for (int i = 0; i < n; i++) {
            jobs.add(new Job(i + 1, p1.get(i), p2.get(i)));
        }
        Collections.sort(jobs, (o1, o2) -> Integer.compare(Math.min(o1.p1, o1.p2), Math.min(o2.p1, o2.p2)));

        ArrayList<Job> l = new ArrayList<>();
        ArrayList<Job> r = new ArrayList<>();

        for (Job job : jobs) {
            if (job.p1 <= job.p2) {
                l.add(job);
            } else {
                r.add(job);
            }
        }

        for (int i = r.size() - 1; i >= 0; i--) {
            l.add(r.get(i));
        }

        long time1 = 0;
        long time2 = 0;
        for (Job job : l) {
            time1 += job.p1;
            time2 = Math.max(time1, time2) + job.p2;
        }

        out.println(time2);
        for (int i = 0; i < n; i++) {
            out.print(l.get(i).num + " ");
        }
        out.println();
        for (int i = 0; i < n; i++) {
            out.print(l.get(i).num + " ");
        }

        out.close();
    }

    public void run() {
        try {
            in = new FastScanner(new File("f2cmax.in"));
            out = new PrintWriter(new File("f2cmax.out"));

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
        new N_f2cmax().run();
    }
}
