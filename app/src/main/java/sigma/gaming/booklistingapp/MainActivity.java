package sigma.gaming.booklistingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    public static final String LOG_TAG = MainActivity.class.getName();

    private EditText mUserSearch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);


        mUserSearch = findViewById(R.id.user_input_edit_text);

        final ImageButton search = findViewById(R.id.button_search);

        // Set the an {@link OnEditorActionListener} on the editable text view
        // Implement search button click when user presses the done button on the keyboard
        mUserSearch.setOnEditorActionListener(new EditText.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                // Check whether the done button is pressed on the keyboard
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    // User has finished entering text
                    // Perform the search button click programmatically
                    search.performClick();
                    // Return true on successfully handling the action
                    return true;
                }

                // Do not perform any task when user is actually entering text
                // in the editable text view
                return false;
            }
        });


    }


    /**
     * This method is called when the user hits the search button
     * It connects the user's search text to the query methods
     */
    public void searchFor(View view) {
        // Get a handle for the editable text view holding the user's search text
        EditText userInput = findViewById(R.id.user_input_edit_text);
        // Get the characters from the {@link EditText} view and convert it to string value
        String input = userInput.getText().toString();

        // Search filter for search text matching book titles
        RadioButton mTitleChecked = findViewById(R.id.title_radio);
        // Search filter for search text matching authors
        RadioButton mAuthorChecked = findViewById(R.id.author_radio);
        // Search filter for search text matching ISBN numbers
        RadioButton mIsbnChecked = findViewById(R.id.isbn_radio);

        if (!input.isEmpty()) {
            // On click display list of books matching search criteria
            // Build intent to go to the {@link QueryResultsActivity} activity
            Intent results = new Intent(MainActivity.this, QueryListOfBookActivity.class);

            // Get the user search text to {@link QueryResultsActivity}
            // to be used while creating the url
            results.putExtra("topic", mUserSearch.getText().toString().toLowerCase());

            // Pass search filter, if any
            if (mTitleChecked.isChecked()) {
                // User is searching for book titles that match the search text
                results.putExtra("title", "intitle=");
            } else if (mAuthorChecked.isChecked()) {
                // User is searching for authors that match the search text
                results.putExtra("author", "inauthor=");
            } else if (mIsbnChecked.isChecked()) {
                // User is specifically looking for the book with the provided isbn number
                results.putExtra("isbn", "isbn=");
            }

            // Pass on the control to the new activity and start the activity
            startActivity(results);

        } else {
            // User has not entered any search text
            // Notify user to enter text via toast

            Toast.makeText(
                    MainActivity.this,
                    getString(R.string.enter_text),
                    Toast.LENGTH_SHORT)
                    .show();
        }
    }


}
