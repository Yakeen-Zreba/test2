package com.example.test2;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class prof extends AppCompatActivity {
    TextView text_edit_stu_photo, text_stu_id, text_stu_name, text_stu_spe;
    ImageView profile_stu_img;
        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);


            text_edit_stu_photo=(TextView) findViewById(R.id.text_edit_photo);
            text_stu_id=(TextView) findViewById(R.id.text_view_id);
            text_stu_name=(TextView) findViewById(R.id.text_view_name);
            text_stu_spe=(TextView) findViewById(R.id.text_view_spec);
            profile_stu_img=(ImageView) findViewById(R.id.profile_stu_img);

        }


    }



