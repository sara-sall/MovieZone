package com.example.moviezone;

import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MovieListActivity extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;

    private ViewPager mViewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabsLayoutID);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

    }

    public static class PlaceholderFragment extends Fragment {
        private ArrayList <Movie> movieList = new ArrayList<>();
        private RequestQueue volleyQueue;

        private RecyclerView recyclerView;
        private RecyclerView.LayoutManager layoutManager;
        private MovieListAdapter movieAdapter;

        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_movie_list, container, false);
            volleyQueue = Volley.newRequestQueue(getContext());

            LinearLayout fragmentLay = rootView.findViewById(R.id.movieItemLayout);


            if(getArguments().getInt(ARG_SECTION_NUMBER) == 1){
                fragmentLay.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                String queryTop = "https://api.themoviedb.org/3/discover/movie?api_key=d0532d41c9054bf65a4ec278b98fd6cf&language=en-US&" +
                        "sort_by=popularity.desc&include_adult=false&include_video=false&page=1";
                jsonParse(queryTop);
            }
            if(getArguments().getInt(ARG_SECTION_NUMBER) == 2){
                fragmentLay.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                String queryBot = "https://api.themoviedb.org/3/discover/movie?api_key=d0532d41c9054bf65a4ec278b98fd6cf&language=en-US&" +
                        "sort_by=popularity.asc&include_adult=false&include_video=false&page=1";
                jsonParse(queryBot);
            }

            //jsonParse(query);
            //Log.d("!!!", query);
            Log.d("!!!", "1"+ movieList.toString());


            recyclerView = rootView.findViewById(R.id.recyclerID);
            layoutManager = new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(layoutManager);

            movieAdapter = new MovieListAdapter(movieList);
            recyclerView.setAdapter(movieAdapter);

            return rootView;
        }

        private void jsonParse(String url){
            //movieList.clear();
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                JSONArray jsonArray = response.getJSONArray("results");

                                for (int i = 0; i < jsonArray.length(); i++){
                                    JSONObject film = jsonArray.getJSONObject(i);


                                    movieList.add(new Movie(film.getString("title"), film.getString("release_date"), film.getDouble("vote_average"),
                                            film.getString("poster_path")));

                                   //Log.d("!!!", "2" + movieList.toString());
                                }
                                Log.d("!!!", "3" + movieList.toString());
                                movieAdapter.notifyDataSetChanged();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });

            volleyQueue.add(request);
            if(volleyQueue == null){
                movieAdapter.notifyDataSetChanged();

            }


        }

    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            return 2;
        }
    }
}
