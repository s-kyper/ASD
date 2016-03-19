package stringSearch;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Created by pinkdonut on 19.03.2016.
 */
public class ESearchWithOneMismatch {
    static FastScanner in;
    static PrintWriter out;

    public void solve() throws IOException {
        String pattern = in.next();
        String text = in.next();
        ArrayList<Integer> positions = new ArrayList<Integer>();
        int[] zf = zFunction(pattern + "#" + text);
        int[] zfReversed = zFunction(new StringBuilder(pattern).reverse().toString() + "#" + new StringBuilder(text).reverse().toString());

/*        for (int i : zf) {
            out.print(i + " ");
        } out.println();

        for (int i : zfReversed) {
            out.print(i + " ");
        } out.println();*/


        for (int i = 0; i < text.length() - pattern.length() + 1; i++) {
            if (zf[pattern.length() + i + 1] + zfReversed[text.length() + 1 - i] >= pattern.length() - 1) {
                positions.add(i + 1);
            }
        }

        out.println(positions.size());
        for (int pos : positions) {
            out.print(pos + " ");
        }
        out.close();
    }

    public void run() {
        try {
            in = new FastScanner(new File("search3.in"));
            out = new PrintWriter(new File("search3.out"));

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
        new ESearchWithOneMismatch().run();
    }
}
