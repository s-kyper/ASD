package stringSearch;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Created by pinkdonut on 17.03.2016.
 */
public class DZfunction {
    static FastScanner in;
    static PrintWriter out;

    ArrayList<ArrayList<Integer>> g;
    boolean[] used;
    int[] num;

    public void solve() throws IOException {
        String pattern = in.next();
        int[] zf = zFunction(pattern);

        for (int i = 1; i < pattern.length(); i++) {
            out.print(zf[i] + " ");
        }
        out.close();
    }

    public boolean dfs(int v) {
        if (used[v]) return false;
        used[v] = true;
        for (int i=0; i<g.get(v).size(); i++) {
            int to = g.get(v).get(i);
            if (num[to] == -1 || dfs(num[to])) {
                num[to] = v;
                return true;
            }
        }
        return false;
    }

    public void run() {
        try {
            in = new FastScanner(new File("z.in"));
            out = new PrintWriter(new File("z.out"));

            solve();

            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    int[] zFunction(String str) {
        int[] zf = new int[str.length()];

        int left = 0;
        int right = 0;

        for (int i = 1; i < str.length(); i++) {
            zf[i] = Math.max(0, Math.min(right - i, zf[i - left]));
            while (i + zf[i] < str.length() && str.charAt(zf[i]) == str.charAt(i + zf[i])) {
                zf[i]++;
            }
            if (i + zf[i] >= right) {
                left = i;
                right = i + zf[i];
            }
        }

        return zf;
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
        new DZfunction().run();
    }
}
