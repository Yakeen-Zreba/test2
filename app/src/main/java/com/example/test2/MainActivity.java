package com.example.test2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.AsyncTaskLoader;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    TextView text_edit_stu_photo, text_stu_id, text_stu_name, text_stu_spe;
    Button stu_btn_edit;
    ImageView profile_stu_img;
    private String strJson, apiUrl="http://192.168.0.104/final-project-php-master/controls/profile_stu_android.php";

    private OkHttpClient client;
    private Response response;
    private RequestBody requestBody;
    private Request request;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressDialog=new ProgressDialog(this);
        progressDialog.setTitle("Please wait....");
        progressDialog.setCanceledOnTouchOutside(false);

        text_edit_stu_photo=(TextView) findViewById(R.id.text_edit_photo);
        text_stu_id=(TextView) findViewById(R.id.text_view_id);
        text_stu_name=(TextView) findViewById(R.id.text_view_name);
        text_stu_spe=(TextView) findViewById(R.id.text_view_spec);

        profile_stu_img=(ImageView) findViewById(R.id.profile_stu_img);

        stu_btn_edit=(Button) findViewById(R.id.btn_edit);

        progressDialog.show();

        client =new OkHttpClient();
        new GetUserDataRequest().execute();


    }

    public class  GetUserDataRequest extends AsyncTask<Void,Void,Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            request = new Request.Builder().url(apiUrl).build();
            try {
                response = client.newCall(request).execute();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);

            try {
                strJson = response.body().string();
                updateUserData(strJson);
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private void updateUserData(String strJson) throws JSONException {
        try {
            JSONArray parent = new JSONArray(strJson);
            JSONObject child = parent.getJSONObject(0);
            //for image profile
            //String imgUrl=child.getString("imageLink");

            //for other textView
            String stu_id=child.getString("stu_id");
            String stu_name=child.getString("full_name");
            String stu_spe=child.getString("stu_specialization");

            //for image
            //Glide.with(this).load(imgUrl).into(profile_stu_img);
            text_stu_id.setText(stu_id);
            text_stu_name.setText(stu_name);
            text_stu_spe.setText(stu_spe);
            progressDialog.hide();

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


   /* private void profile_stu(){
        String user_id = text_stu_id.getText().toString().trim();
        String full_name = text_stu_name.getText().toString().trim();
        String u_spe = text_stu_spe.getText().toString().trim();

    }*/

}
