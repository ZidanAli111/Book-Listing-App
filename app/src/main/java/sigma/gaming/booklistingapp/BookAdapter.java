package sigma.gaming.booklistingapp;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.graphics.drawable.DrawableCompat;

import java.util.List;

public class BookAdapter  extends ArrayAdapter<Book> {

    private static final String LOG_TAG =BookAdapter.class.getSimpleName() ;

    /** TextView for title of the book */
    TextView bookTitle;

    /** TextView for the author*/
    TextView bookAuthor;

    /** Rating bar for the average rating of the book */
    RatingBar bookRating;

    /** TextView for the retail price of the book */
    TextView bookPrice;

    public BookAdapter( Context context, List<Book>books) {
        super(context,0, books);
    }

    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {


        View listItemView= convertView;


        if(listItemView==null)
        {
            listItemView= LayoutInflater.from(getContext()).inflate(R.layout.book_card,parent,false);

        }
        Book currentBook=getItem(position);

        bookTitle=listItemView.findViewById(R.id.book_title_text_view);
        bookTitle.setText(currentBook.getmTitle());

        bookAuthor=listItemView.findViewById(R.id.author_text_view);

        try {
            String authors = currentBook.getmAuthors();
            // Check whether the book author information or not
            if (!authors.isEmpty()) {
                // The book does have information on its author
            bookAuthor.setText(authors);

            }

        } catch (NullPointerException e) {
            // Author information is not available from the JSON response
            Log.v(LOG_TAG, "No information on authors");

             //Hide view from book
            bookAuthor.setVisibility(View.INVISIBLE);
        }


    bookRating=  listItemView.findViewById(R.id.rating_bar);

        bookRating.setRating(currentBook.getmRating());
        bookRating.setMax(5);
        bookRating.setNumStars(5);
        Drawable progress = bookRating.getProgressDrawable();
        DrawableCompat.setTint(progress, Color.YELLOW);



        bookPrice=listItemView.findViewById(R.id.retail_price_text_view);

        String price="";

        if(currentBook.getmPrice()>0)
        {
            price="$"+ currentBook.getmPrice();

             bookPrice.setText(price);
        }




return  listItemView;


    }
}
