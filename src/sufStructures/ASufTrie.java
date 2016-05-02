import java.io.*;
import java.util.*;

/**
 * Created by pinkdonut on 26.04.2016.
 */
public class ASufTrie {
    static FastScanner in;
    static PrintWriter out;

    class Trie {
        HashMap<Character, Integer>[] trie;
        int number;
        StringBuilder str;
        ArrayList<Edge> edges;
        int strLength;

        Trie(String s) {
            str = new StringBuilder(s);
            strLength = str.length();
            trie = new HashMap[strLength * strLength];
            number = 1;
            edges = new ArrayList<>();
            for (int i = 0; i < strLength * strLength; i++)
                trie[i] = new HashMap<>();
            build();
        }

        void build() {
            for (int i = 0; i < strLength; i++)
                add(i, strLength);
        }

        void add(int i, int j) {
            int current = 0;
            for (int k = i; k < j; k++) {
                char c = str.charAt(k);
                if (!trie[current].containsKey(c)) {
                    trie[current].put(c, number);
                    number++;
                    edges.add(new Edge(current + 1, number, c));
                }
                current = trie[current].get(c);
            }
        }
    }

    class Edge  {
        int to, from;
        char c;

        public Edge(int from, int to, char c) {
            this.to = to;
            this.from = from;
            this.c = c;
        }
    }

    public void solve() throws IOException {
        Trie trie = new Trie(in.next());

        out.println(trie.number + " " + (trie.number - 1));

        for (Edge e : trie.edges) {
            out.println(e.from + " " + e.to + " " + e.c);
        }

        out.close();
    }

    public void run() {
        try {
            in = new FastScanner(new File("trie.in"));
            out = new PrintWriter(new File("trie.out"));

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
        new ASufTrie().run();
    }
}
