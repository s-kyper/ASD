package sufStructures;

import java.io.*;
import java.util.*;

/**
 * Created by pinkdonut on 28.06.2016.
 */
public class ERefrain {
    static FastScanner in;
    static PrintWriter out;

    class SuffixArray {
        int[] sa;

        SuffixArray(String s) {
            buildArray(s);
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
    }

    private static int[] buildLcp(String str, int[] suf) {
        int n = suf.length;
        int[] lcp = new int[n];
        int[] pos = new int[n];
        for (int i = 0; i < n; i++)
            pos[suf[i]] = i;
        int k = 0;
        for (int i = 0; i < n; i++) {
            if (k > 0)
                k--;
            if (pos[i] == n - 1) {
                lcp[n - 1] = -1;
                k = 0;
            } else {
                int j = suf[pos[i] + 1];
                while (Math.max(i + k, j + k) < str.length() && str.charAt(i + k) == str.charAt(j + k))
                    k++;
                lcp[pos[i]] = k;
            }
        }
        return lcp;
    }

    public void solve() throws IOException {
        int n = in.nextInt();
        int m = in.nextInt();
        String s = "";
        for (int i = 0; i < n; i++) {
            s += (char) (in.nextInt() + 64);
        }

        SuffixArray ar = new SuffixArray(s + '#');
        int[] suffixArray = ar.sa;
        int[] lcp = buildLcp(s, suffixArray);
        int[] stack = new int[lcp.length];
        int length = s.length();
        int top = 0;
        int bestPos = -1;
        int bestLength = -1;
        for (int i = 0; i < lcp.length; i++) {
            while (top > 0 && lcp[i] <= lcp[stack[top - 1]]) {
                int cur = stack[top - 1];
                int prev = top == 1 ? -1 : stack[top - 2];
                int curLength = i - prev;
                if (bestPos == -1 || ((long) lcp[cur]) * ((long) curLength) > ((long) lcp[bestPos]) * ((long) bestLength)) {
                    bestPos = cur;
                    bestLength = curLength;
                }
                top--;
            }
            stack[top++] = i;
        }

        if (((long) lcp[bestPos]) * ((long) bestLength) < length) {
            out.println(length);
            out.println(length);
            for (int i = 0; i < length; i++) {
                out.print((s.charAt(i) - 64) + " ");
            }
        } else {
            out.println((long) lcp[bestPos] * bestLength);
            out.println(lcp[bestPos]);
            for (int i = 0; i < lcp[bestPos]; i++) {
                out.print((s.charAt(suffixArray[bestPos] + i) - 64) + " ");
            }
        }

        out.close();
    }

    public void run() {
        try {
            in = new FastScanner(new File("refrain.in"));
            out = new PrintWriter(new File("refrain.out"));

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
        new ERefrain().run();
    }
}
