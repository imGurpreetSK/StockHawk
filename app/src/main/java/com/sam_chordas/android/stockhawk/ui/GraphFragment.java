package com.sam_chordas.android.stockhawk.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.sam_chordas.android.stockhawk.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class GraphFragment extends Fragment {

    public GraphFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_graph, container, false);
        WebView graphWebView = (WebView) v.findViewById(R.id.graphWebView);

        String companySymbol = getArguments().getString(getString(R.string.key_symbol));
        String graphUrl = "http://empyrean-aurora-455.appspot.com/charts.php?symbol=" + companySymbol;
//        String graphUrl = "http://empyrean-aurora-455.appspot.com/charts.php?symbol=goog";;

        WebSettings webSettings = graphWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        graphWebView.loadUrl(graphUrl);

        return v;
    }

}
