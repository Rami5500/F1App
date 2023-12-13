package uk.ac.aston.cs3mdd.racingapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import model.Driver;

public class DriverFragment extends Fragment {

    private WebView web_view;

    private Driver driver;
    public DriverFragment() {
        // Required empty public constructor
    }

    /*@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

     */

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_driver, container, false);

        web_view = root.findViewById(R.id.webView);

        if (getArguments() != null) {
            Driver driver = DriverFragmentArgs.fromBundle(getArguments()).getDriver();
            String wiki_link = "https://en.wikipedia.org/wiki/" +
                    driver.getGivenName() + "_" + driver.getFamilyName();
            web_view.setWebViewClient(new WebViewClient());
            web_view.loadUrl(wiki_link);
        }

        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_driver, container, false);
        return root;
    }

}