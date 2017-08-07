package com.example.admin.olaysemiandpicviewerapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.io.IOException;

/**
 * Created by Admin on 8/4/2017.
 */

public class PictureViewFragment extends Fragment {

    private static int RESULT_LOAD_IMAGE = 1;
    FloatingActionButton fab;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rv = inflater.inflate(R.layout.picture_view_fragment, container, false);

        fab = rv.findViewById(R.id.fabGetPic);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent getPic = new Intent(Intent.ACTION_GET_CONTENT);
                getPic.setType("image/*");
                startActivityForResult(getPic.createChooser(getPic, "Select Picture"), RESULT_LOAD_IMAGE);
            }
        });

        return rv;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == Activity.RESULT_OK && data != null && data.getData() != null) {

            Uri uri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), uri);

                ImageView imageView =  getActivity().findViewById(R.id.ivMyPicture);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
