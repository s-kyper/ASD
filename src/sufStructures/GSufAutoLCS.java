package sufStructures;

import java.io.*;
import java.util.*;

/**
 * Created by pinkdonut on 28.04.2016.
 */

public class GSufAutoLCS {
    static FastScanner in;
    static PrintWriter out;

    class Edge {
        int begin;
        int end;
        char c;

        public Edge(int b, int e, char c) {
            this.begin = b;
            this.end = e;
            this.c = c;
        }
    }

    private class State {
        int length, link, endpos;
        int[] next = new int[128];
        ArrayList<Integer> iLink;

        public State() {
            Arrays.fill(next, -1);
            iLink = new ArrayList<>();
        }
    }

    public class SuffixAutomaton {
        State[] states;
        String s1, s2, commonString;

        SuffixAutomaton(String s1, String s2) {
            this.s1 = s1;
            this.s2 = s2;
            lcs();
        }

        void buildAutomaton(String s) {
            int n = s.length();
            states = new State[Math.max(2, 2 * n - 1)];
            states[0] = new State();
            states[0].link = -1;
            states[0].endpos = -1;
            int last = 0;
            int size = 1;
            for (char c : s.toCharArray()) {
                int cur = size++;
                states[cur] = new State();
                states[cur].length = states[last].length + 1;
                states[cur].endpos = states[last].length;
                int p;
                for (p = last; p != -1 && states[p].next[c] == -1; p = states[p].link)
                    states[p].next[c] = cur;
                if (p == -1) {
                    states[cur].link = 0;
                } else {
                    int q = states[p].next[c];
                    if (states[p].length + 1 == states[q].length)
                        states[cur].link = q;
                    else {
                        int clone = size++;
                        states[clone] = new State();
                        states[clone].length = states[p].length + 1;
                        states[clone].next = states[q].next.clone();
                        states[clone].link = states[q].link;
                        for (; p != -1 && states[p].next[c] == q; p = states[p].link)
                            states[p].next[c] = clone;
                        states[q].link = clone;
                        states[cur].link = clone;
                        states[clone].endpos = -1;
                    }
                }
                last = cur;
            }
            for (int i = 1; i < size; i++)
                states[states[i].link].iLink.add(i);

            states = Arrays.copyOf(states, size);
        }

        void lcs() {
            buildAutomaton(s1);
            int len = 0;
            int bestLen = 0;
            int bestPos = -1;
            for (int i = 0, cur = 0; i < s2.length(); ++i) {
                char c = s2.charAt(i);
                if (states[cur].next[c] == -1) {
                    for (; cur != -1 && states[cur].next[c] == -1; cur = states[cur].link) {
                    }
                    if (cur == -1) {
                        cur = 0;
                        len = 0;
                        continue;
                    }
                    len = states[cur].length;
                }
                ++len;
                cur = states[cur].next[c];
                if (bestLen < len) {
                    bestLen = len;
                    bestPos = i;
                }
            }
            commonString =  s2.substring(bestPos - bestLen + 1, bestPos + 1);
        }
    }

    public void solve() throws IOException {
        SuffixAutomaton sufAuto = new SuffixAutomaton(in.next(), in.next());
        out.print(sufAuto.commonString);

        out.close();
    }

    public void run() {
        try {
            in = new FastScanner(new File("common.in"));
            out = new PrintWriter(new File("common.out"));

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
        new GSufAutoLCS().run();
    }
}
