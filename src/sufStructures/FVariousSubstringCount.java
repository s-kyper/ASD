package sufStructures;

import java.io.*;
import java.util.*;

/**
 * Created by pinkdonut on 26.04.2016.
 */
public class FVariousSubstringCount {
    static FastScanner in;
    static PrintWriter out;

    class SuffixArray {
        String str;
        int[] sa;
        int[] lcp;

        SuffixArray(String s) {
            str = s;
            buildArray(s);
            lcp();
        }

        void buildArray(String s) {
            int n = s.length();
            Integer[] order = new Integer[n];
            for (int i = 0; i < n; i++)
                order[i] = n - 1 - i;

            Arrays.sort(order, (a, b) -> Character.compare(s.charAt(a), s.charAt(b)));

            sa = new int[n];
            int[] classes = new int[n];
            for (int i = 0; i < n; i++) {
                sa[i] = order[i];
                classes[i] = s.charAt(i);
            }

            for (int len = 1; len < n; len *= 2) {
                int[] c = classes.clone();
                for (int i = 0; i < n; i++) {
                    classes[sa[i]] = i > 0 && c[sa[i - 1]] == c[sa[i]] && sa[i - 1] + len < n && c[sa[i - 1] + len / 2] == c[sa[i] + len / 2] ? classes[sa[i - 1]] : i;
                }
                int[] cnt = new int[n];
                for (int i = 0; i < n; i++)
                    cnt[i] = i;
                int[] sb = sa.clone();
                for (int i = 0; i < n; i++) {
                    int s1 = sb[i] - len;
                    if (s1 >= 0)
                        sa[cnt[classes[s1]]++] = s1;
                }
            }
        }

        public void lcp() {
            int n = sa.length;
            int[] rank = new int[n];
            for (int i = 0; i < n; i++)
                rank[sa[i]] = i;
            lcp = new int[n - 1];
            for (int i = 0, h = 0; i < n; i++) {
                if (rank[i] < n - 1) {
                    for (int j = sa[rank[i] + 1]; Math.max(i, j) + h < str.length() && str.charAt(i + h) == str.charAt(j + h); ++h)
                        ;

                    lcp[rank[i]] = h;
                    if (h > 0) {
                        --h;
                    }
                }
            }
        }
    }

    public void solve() throws IOException {
        SuffixArray suffixArray = new SuffixArray(in.next());
        long sum = 0;
        for (int i : suffixArray.lcp)
            sum += i;

        long n = suffixArray.str.length();

        long result = n * (n + 1) / 2 - sum;
        out.print(result);

        out.close();
    }

    public void run() {
        try {
            in = new FastScanner(new File("count.in"));
            out = new PrintWriter(new File("count.out"));

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
        new FVariousSubstringCount().run();
    }
}
