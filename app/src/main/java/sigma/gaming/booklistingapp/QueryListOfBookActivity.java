package sigma.gaming.booklistingapp;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.List;

public class QueryListOfBookActivity
        extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<List<Book>> {


    /** URL for books data from the Google books API */
    private String REQUEST_URL = "https://www.googleapis.com/books/v1/volumes?q=";

    private static final String API_KEY = "AIzaSyBk_rBAjgZT4UVRmo76lidFx7FtnLPxwdo";

    /** Constant value for the earthquake loader ID */
    private static final int EARTHQUAKE_LOADER_ID = 1;

    /** Indeterminate progress bar for loading books */
    private ProgressBar mProgressSpinner;

    /** TextView that is displayed when the list is empty */
    private TextView mEmptyStateView;

    private BookAdapter mAdapter;

    private EditText mUserSearch;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.list_of_book);

        // Find a reference to the {@link ListView} in the layout
        GridView  bookListView = (GridView) findViewById(R.id.list);

        // Create a new adapter that takes an empty list of earthquakes as input
        mAdapter = new BookAdapter(this, new ArrayList<Book>());

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        bookListView.setAdapter(mAdapter);


        mEmptyStateView = (TextView) findViewById(R.id.empty_view);
       bookListView.setEmptyView(mEmptyStateView);


// Get reference to the Progress bar
        mProgressSpinner = findViewById(R.id.loading_indicator);
        // Indeterminate progress bar type
        mProgressSpinner.setIndeterminate(true);


        // Get the spawn intent
        Intent queryIntent = getIntent();
        // Get the search text typed by the user
        String searchText = getIntent().getStringExtra("topic");
        // Initialize variable to hold the processed search query
        String processedQuery = "";
        // Get the value for title key packaged in the spawn intent
        String title = queryIntent.getStringExtra("title");
        // Get the value for author key packaged in the spawn intent
        String author = queryIntent.getStringExtra("author");
        // Get the value for isbn key packaged in the spawn intent
        String isbn = queryIntent.getStringExtra("isbn");

        // Determine which radio box was checked based on non-null values from the above keys
        if (title != null) {
            // Title was checked by the user
            processedQuery = searchText + "&" + title + searchText;
        } else if (author != null) {
            // User is searching an author matching the search text
            processedQuery = searchText + "&" + author + searchText;
        } else if (isbn != null) {
            // User is searching the isbn number matching the search text
            processedQuery = searchText + "&" + isbn + searchText;
        } else {
            // No filters used
            processedQuery = searchText;
        }

        // Build the url from user search
        REQUEST_URL += processedQuery + "&maxResults=40" + "&key=" + API_KEY;

        // Get a reference to the ConnectivityManager to check state of network connectivity
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);

        // Get details on the currently active default data network
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        // If there is a network connection, fetch data
        if (networkInfo != null && networkInfo.isConnected()) {
            // Get a reference to the loader manager in order to interact with the loaders
            android.app.LoaderManager loaderManager = getLoaderManager();

            // Initialize the loader manager. Pass in the constant declared above as the ID of the
            // loader manager and pass in null for the bundle parameter. Finally, also pass in the
            // context of the application since this application implements the LoaderCallbacks interface
            loaderManager.initLoader(EARTHQUAKE_LOADER_ID, null, QueryListOfBookActivity.this);
        } else {
            // Otherwise, display error
            // First, hide loading indicator so error message will be visible
            mProgressSpinner.setVisibility(View.GONE);

            // Update empty state with no connection error message
            mEmptyStateView.setText(R.string.no_internet_connection);
        }
    }

    @Override
    public void onLoadFinished(Loader<List<Book>> loader, List<Book> books) {
        // Hide progress bar
        mProgressSpinner.setVisibility(View.GONE);

        // Set empty state text to display "No books to display."
        mEmptyStateView.setText("No Books To Display.");


        // Clear the adapter of previous data
        mAdapter.clear();

        // Add valid list of books to the adapter
        if(books!=null&&!books.isEmpty()){
            mAdapter.addAll(books);
        }


    }

    @Override
    public void onLoaderReset(Loader<List<Book>> loader) {

        // Clear existing data on adapter as loader is reset
        mAdapter.clear();
    }



    /**
     * Create new loader object to load list of books as search query results for the user
     */


    public Loader<List<Book>> onCreateLoader(int id, Bundle args) {
        return new BookLoader(QueryListOfBookActivity.this, REQUEST_URL);
    }


}
