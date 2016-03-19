package stringSearch;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Created by pinkdonut on 17.03.2016.
 */
public class CPrefix {
    static FastScanner in;
    static PrintWriter out;

    public void solve() throws IOException {
        String pattern = in.next();
        int[] prefix = prefix(pattern);

        for (int i = 0; i < pattern.length(); i++) {
            out.print(prefix[i] + " ");
        }

        out.close();
    }

    public void run() {
        try {
            in = new FastScanner(new File("prefix.in"));
            out = new PrintWriter(new File("prefix.out"));

            solve();

            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    int[] prefix(String str) {
        int[] prefix = new int[str.length()];
        for (int i = 1; i < str.length(); i++) {
            int k = prefix[i - 1];
            while (k > 0 && str.charAt(i) != str.charAt(k)) {
                k = prefix[k -1];
            }
            if (str.charAt(i) == str.charAt(k)) {
                k++;
            }
            prefix[i] = k;
        }
        return prefix;
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
        new CPrefix().run();
    }
}
