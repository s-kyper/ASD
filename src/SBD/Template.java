package SBD;

import java.io.*;
import java.util.*;

/**
 * Created by pinkdonut on 25.05.2016.
 *//*
public class Template {
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

    public class Point {
        int first, second;
    }



    public void solve() throws IOException {
        int n;
        ArrayList<Point>
        vector <pair<pair<long long, long long>, int> > a;
        cin >> n;
        for(int i = 0; i < n; i++){
            int x, y;
            cin >> x >> y;
            a.pb(mp(mp(y, x), i));
        }

        sort(a.begin(), a.end());

        typedef set < pair < long long, int> > t_s;
        t_s s;
        vector < pair < int, long long> > result;
        for (int i=n-1; i>=0; --i) {
            long long t = a[i].first.first - (i ? a[i-1].first.first : 0);
            s.insert(mp(a[i].first.second, a[i].second));
            while (t && !s.empty()) {
                t_s::iterator it = s.begin();
                if (it->first <= t) {
                    t -= it->first;
                    result.pb(mp(it->second, it->first));// i, time
                }
                else {
                    s.insert (mp (it->first - t, it->second));
                    t = 0;
                }
                s.erase(it);
            }
        }
        cout << result.size() << endl;
        long long cnt = 1;
        for (int i = 0; i < result.size(); i++) {
            //cout << result[i] << " ";
            //cout << cnt  << " " << result[i].first << endl;
            answ[result[i].first] = cnt;
            cnt += result[i].second;//long long
            //cout << cnt << endl;
        }
        for (int i = 0; i < n; i++){
            if(answ[i] == 0) cout << -1 <<" ";
            else cout << answ[i] - 1 << " ";
        }
        out.close();
    }

    public void run() {
        try {
            in = new FastScanner(new File("p1sumu.in"));
            out = new PrintWriter(new File("p1sumu.out"));

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
        new Template().run();
    }
}*/