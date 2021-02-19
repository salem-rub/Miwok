package com.example.android.miwok;

import android.app.Activity;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<com.example.android.miwok.Word> {

    private int mColorResourceId;
    MediaPlayer mediaPlayer;

    /**
     *
     * @param Context is current context
     * @param words is the list of {@link Word}s to be displayed.
     * @param colorResourceId is the resource ID for the background color for this list of words
     */
    public WordAdapter(Activity Context, ArrayList<Word> words, int colorResourceId) {
        super(Context, 0, words);
        mColorResourceId = colorResourceId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //return super.getView(position, convertView, parent);

        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        // Get the {@link AndroidFlavor} object located at this position in the list
        com.example.android.miwok.Word currentWord = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID version_name
        TextView englishWord = (TextView) listItemView.findViewById(R.id.defaul_text_view);
        // Get the version name from the current AndroidFlavor object and
        // set this text on the name TextView
        englishWord.setText(currentWord.getDefaultTranslation());

        // Find the TextView in the list_item.xml layout with the ID version_number
        TextView miwokWord = (TextView) listItemView.findViewById(R.id.miwok_text_view);
        // Get the version number from the current AndroidFlavor object and
        // set this text on the number TextView
        miwokWord.setText(currentWord.getMiwokTranslation());


        ImageView wordImage = (ImageView) listItemView.findViewById(R.id.the_image);
        if (currentWord.hasImage()) {
            wordImage.setImageResource(currentWord.getImageResourceId());
        } else wordImage.setVisibility(View.GONE);

        // Set the theme color for the list item
        View textContainer = listItemView.findViewById(R.id.text_container);
        // Find the color that the resource ID maps to
        int color = ContextCompat.getColor(getContext(), mColorResourceId);
        // Set the background color of the text container view
        textContainer.setBackgroundColor(color);

        //ImageView playImage = (ImageView) listItemView.findViewById(R.id.audio_pic);
        //playImage.setImageResource(currentWord.getmAudioResourceId());


        // Return the whole list item layout (containing 2 TextViews and an ImageView)
        // so that it can be shown in the ListView
        return listItemView;



    }



}
