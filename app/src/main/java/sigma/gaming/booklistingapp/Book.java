package sigma.gaming.booklistingapp;

import android.database.DataSetObserver;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;

import java.net.URL;

public class Book implements ListAdapter {
    private String mTitle;

    private  String mAuthors;

    private float mRating;

    private  String mPrice;

    private String mThumbnail;

    private String mCategories;

    private  String mDescription;

    private String mPublishedDate;

    private String mInfo;

    private String mPreview;


    protected static final String BOOK = "BOOK";



    Book(String title, String author, float rating, String  price, String image,String categories,String description,String publishedDate,String preview,String info)

    {
        this.mTitle=title;
        this.mAuthors=author;
        this.mRating=rating;
        this.mPrice=price;
        this.mThumbnail=image;
        this.mCategories=categories;
        this.mDescription=description;
        this.mPublishedDate=publishedDate;
        this.mInfo=info;
        this.mPreview=preview;
    }

    public String getmPublishedDate() { return mPublishedDate; }

    public String getmInfo() { return mInfo; }

    public String getmPreview() { return mPreview; }

    public String getmThumbnail() { return mThumbnail; }

    public String getmTitle() {
        return mTitle;
    }

    public String getmAuthors(){ return mAuthors; }

    public float getmRating() {
        return mRating;
    }

    public String getmPrice() {
        return mPrice;
    }

    public String getmDescription() { return mDescription; }

    public String getmCategories() { return mCategories; }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int position) {
        return false;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
