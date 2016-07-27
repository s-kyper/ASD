package SBD;

import java.io.*;
import java.util.*;

/**
 * Created by pinkdonut on 04.07.2016.
 */
public class L_pintreep1l {
    static FastScanner in;
    static PrintWriter out;

    class Job {
        int dead;
        int dd;
        int num;
        ArrayList<Job> parents = new ArrayList<>();
        Job child = null;

        public Job(int dead, int num) {
            this.dead = dead;
            this.dd = dead;
            this.num = num;
        }
    }

    public void solve() throws IOException {
        int n = in.nextInt();
        int m = in.nextInt();
        ArrayList<Job> jobs = new ArrayList<>();
        ArrayList<Integer> d = new ArrayList<>();
        ArrayList<Integer> r = new ArrayList<>();
        ArrayList<Integer> q = new ArrayList<>();
        ArrayList<Integer> x = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int dead = in.nextInt();
            d.add(dead);
            jobs.add(new Job(dead, i));
            r.add(0);
            q.add(0);
            x.add(0);
        }

        for (int i = 0; i < n - 1; i++) {
            int xi = in.nextInt();
            int yi = in.nextInt();
            jobs.get(xi - 1).child = jobs.get(yi - 1);
            jobs.get(yi - 1).parents.add(jobs.get(xi - 1));
        }

        Stack<Job> stack = new Stack<>();
        for (int i = 0; i < n; i++)
            if (jobs.get(i).child == null)
                stack.add(jobs.get(i));

        while (!stack.empty()) {
            Job j = stack.peek();
            stack.pop();
            for (Job parent : j.parents) {
                parent.dd = Math.min(parent.dd, j.dd - 1);
                stack.add(parent);
            }
        }

        Collections.sort(jobs, new Comparator<Job>() {
            @Override
            public int compare(Job o1, Job o2) {
                if (o1.dd == o2.dd) return o1.num - o2.num;
                else return o1.dd - o2.dd;
            }
        });

        int f = 0;
        int ans = -1000000000;

        for (int i = 0; i < n; i++) {
            int cur = jobs.get(i).num;
            int t = Math.max(r.get(cur), f);
            x.set(cur, t);
            q.add(t, 1);
            if (q.get(t) == m)
                f = t + 1;
            Job j = jobs.get(i).child;
            if (j != null)
                r.set(j.num, Math.max(r.get(j.num), t + 1));
        }

        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, x.get(i) - d.get(i) + 1);
        }

        out.println(ans);
        for (int i = 0; i < n; i++) {
            out.print(x.get(i) + " ");
        }

        out.close();
    }

    public void run() {
        try {
            in = new FastScanner(new File("pintreep1l.in"));
            out = new PrintWriter(new File("pintreep1l.out"));

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
        new L_pintreep1l().run();
    }
}
