package com.example.android.githubsearch.data;

import com.google.gson.annotations.SerializedName;

public class GitHubRepo {
    @SerializedName("full_name")
    public String fullName;

    @SerializedName("html_url")
    public String htmlUrl;

    public String description;

    @SerializedName("stargazers_count")
    public int stars;
}
