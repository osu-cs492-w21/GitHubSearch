package com.example.android.githubsearch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.android.githubsearch.utils.GitHubUtils;
import com.example.android.githubsearch.utils.NetworkUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    private RecyclerView searchResultsRV;
    private EditText searchBoxET;
    private GitHubSearchAdapter githubSearchAdapter;

    private String[] dummySearchResults = {
            "Dummy search results",
            "Dummy search results",
            "Dummy search results",
            "Dummy search results",
            "Dummy search results",
            "Dummy search results",
            "Dummy search results",
            "Dummy search results",
            "Dummy search results",
            "Dummy search results"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.searchBoxET = findViewById(R.id.et_search_box);
        this.searchResultsRV = findViewById(R.id.rv_search_results);

        this.searchResultsRV.setLayoutManager(new LinearLayoutManager(this));
        this.searchResultsRV.setHasFixedSize(true);

        this.githubSearchAdapter = new GitHubSearchAdapter();
        this.searchResultsRV.setAdapter(this.githubSearchAdapter);

        Button searchButton = (Button)findViewById(R.id.btn_search);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchQuery = searchBoxET.getText().toString();
                if (!TextUtils.isEmpty(searchQuery)) {
                    githubSearchAdapter.updateSearchResults(new ArrayList<String>(Arrays.asList(dummySearchResults)));
//                    searchBoxET.setText("");
                    String results = doGitHubSearch(searchQuery);
                }
            }
        });
    }

    private String doGitHubSearch(String query) {
        String url = GitHubUtils.buildGitHubSearchURL(query);
        Log.d(TAG, "searching with this URL: " + url);

        String results = null;
        try {
            results = NetworkUtils.doHttpGet(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d(TAG, "query results: " + results);
        return results;
    }
}