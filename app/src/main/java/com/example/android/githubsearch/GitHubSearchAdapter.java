package com.example.android.githubsearch;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class GitHubSearchAdapter extends RecyclerView.Adapter<GitHubSearchAdapter.SearchResultViewHolder> {
    private ArrayList<String> searchResultsList;

    public void updateSearchResults(ArrayList<String> searchResultsList) {
        this.searchResultsList = searchResultsList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (this.searchResultsList != null) {
            return this.searchResultsList.size();
        } else {
            return 0;
        }
    }

    @NonNull
    @Override
    public SearchResultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.search_result_item, parent, false);
        return new SearchResultViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchResultViewHolder holder, int position) {
        holder.bind(this.searchResultsList.get(position));
    }

    class SearchResultViewHolder extends RecyclerView.ViewHolder {
        private TextView searchResultTV;

        SearchResultViewHolder(View itemView) {
            super(itemView);
            this.searchResultTV = itemView.findViewById(R.id.tv_search_result);
        }

        void bind(String searchResult) {
            this.searchResultTV.setText(searchResult);
        }
    }
}
