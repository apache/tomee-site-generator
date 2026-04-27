package org.apache.tomee.website;

import lombok.Data;
import org.json.JSONArray;
import org.json.JSONObject;
import org.tomitribe.util.IO;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class GitHubContributors {

    protected static final String BASE_URL = "https://api.github.com/repos/apache/tomee/contributors";

    /*
     * Can be used to retrieve an up 2 date list of contributors via the GitHub API
     * The output of this class can be pasted into /src/main/jbake/content/community/contributors.adoc
     */
    public static void main(String[] args) throws IOException {

        final List<Contributor> contributorList = GitHubContributors.fillContributors();

        for (Contributor c : contributorList) {
            System.out.println(c);
        }
    }


    public static List<Contributor> fillContributors() throws IOException {
        final List<Contributor> contributorList = new ArrayList<>();

        boolean hasPagesLeft = true;

        int currentPage = 1;
        while (hasPagesLeft) {
            final String content = IO.slurp(new URL(BASE_URL + "?per_page=100&page=" + currentPage));
            final JSONArray contributors = new JSONArray(content);

            if (contributors.length() == 0) {
                hasPagesLeft = false;
            } else {
                for (int i = 0; i < contributors.length(); i++) {

                    JSONObject c = contributors.getJSONObject(i);

                    contributorList.add(new Contributor(
                            c.getString("id"),
                            c.getString("login"),
                            c.getString("avatar_url"),
                            c.getString("html_url"),
                            c.getLong("contributions")));
                }
            }
            currentPage++;
        }
        return contributorList;
    }

    @Data
    public static class Contributor {
        private final String id;
        private final String name;
        private final String avatar;
        private final String url;
        private final long contributions;

        @Override
        public String toString() {
            return name + " | " + url + " | " + avatar;
        }
    }
}
