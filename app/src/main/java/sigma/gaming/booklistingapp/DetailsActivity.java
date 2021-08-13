package sigma.gaming.booklistingapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import static sigma.gaming.booklistingapp.QueryListOfBookActivity.dataList;

public class DetailsActivity extends AppCompatActivity {

    // initialise the views that will be populated with data
    private TextView bookTitle;
    private TextView bookAuthors;
    private TextView bookDescription;
    private ImageView bookImage;
    private TextView tvPublishDate;
    private TextView tvCatag;
    private TextView tvInfo;
    private TextView price;
    private TextView tvPreview;


    String title ="",info ="",preview ="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);


        // initialise the views that will be populated with data



        bookTitle = (TextView) findViewById(R.id.aa_book_name);
        bookAuthors = (TextView) findViewById(R.id.aa_author);
        bookDescription = (TextView) findViewById(R.id.desc);
        bookImage = (ImageView) findViewById(R.id.aa_thumbnail);
        tvCatag = findViewById(R.id.aa_categories);
        tvPublishDate = findViewById(R.id.aa_publish_date);
        tvInfo = findViewById(R.id.aa_info);
        tvPreview = findViewById(R.id.preview);

        price=findViewById(R.id.aa_price);


        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsing_id);
        collapsingToolbarLayout.setTitleEnabled(true);

        // populate the views with data
        // data is extracted from an Intent

        int position = getIntent().getExtras().getInt("key");





        String priceHere="";

            priceHere=""+ dataList.get(position).getmPrice();

            price.setText(priceHere);




        bookTitle.setText(dataList.get(position).getmTitle());

         title=bookTitle.getText().toString();

        bookAuthors.setText(dataList.get(position).getmAuthors());

        bookDescription.setText(dataList.get(position).getmDescription());

        tvCatag.setText(dataList.get(position).getmCategories());

        tvPublishDate.setText(dataList.get(position).getmPublishedDate());



        info=dataList.get(position).getmInfo();







        preview=dataList.get(position).getmPreview();

        Log.i("PREVIEW","THE PREVIEW LINK IS HERE :"+preview);

        dataList.get(position).getmPreview();

        String imageLink = dataList.get(position).getmThumbnail();


        if (imageLink != null) {
            Glide.with(getApplicationContext())
                    .load(imageLink)
                    .centerCrop()
                    .placeholder(R.drawable.no_image_to_download)
                    .into(bookImage);

        } else {
            bookImage.setImageResource(R.drawable.no_image_to_download);
        }


        final String finalInfo = info;

        tvInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (finalInfo != null) {
                    Log.i("InfoClicked", "Info has been Clicked");
                    Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(finalInfo));

                    startActivity(i);
                } else {
                    Toast.makeText(getApplicationContext(), "Sorry no Info Available", Toast.LENGTH_SHORT).show();

                }
            }
        });


        final String finalPreview = preview;
        tvPreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Log.i("PreviewClicked","Preview has been Clicked");

                if(finalPreview!=null)
                {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(finalPreview));
                    startActivity(i);
                }
                else{
                    Toast.makeText(getApplicationContext(),"Sorry no Preview Available",Toast.LENGTH_SHORT).show();

                }
            }

        });

        collapsingToolbarLayout.setTitle(title);

    }


}


