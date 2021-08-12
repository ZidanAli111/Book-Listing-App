package sigma.gaming.booklistingapp;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;
import java.util.List;

import static sigma.gaming.booklistingapp.MainActivity.LOG_TAG;

public class BookLoader  extends AsyncTaskLoader<List<Book>> {

    private String mSearchUrl;

    private List<Book>mData;

    public BookLoader(Context context,String url) {
        super(context);
        mSearchUrl=url;
    }
    /** Explicitly making the loader make HTTP request and begin loading data from content provider */

    @Override
    protected void onStartLoading() {

        if(mData!=null){
            deliverResult(mData);
        }
        else{
            Log.i(LOG_TAG,"TEST:  onStartLoading called... ");

            forceLoad();
        }
    }
    /**
     * This method is called in a background thread and takes care of the heavy lifting generating
     * new data from the API
     */
    public List<Book> loadInBackground() {

        Log.i(LOG_TAG,"TEST: loadInBackground()  called... ");


        if (mSearchUrl == null) {
            return null;
        }
        // Returns the list of books matching search criteria from Google books
        // after performing network request, parsing input stream, and extracting a list of books

        return  QueryResultActivity.fetchBooks(mSearchUrl);
    }
    @Override
    public void deliverResult(List<Book> data) {
        mData = data; // Cache data
        super.deliverResult(data);
    }

}



