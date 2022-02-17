package com.example.courseappactivity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import javax.xml.transform.ErrorListener;

public class MainActivity extends AppCompatActivity {
    EditText e1,e2,e3,e4,e5;
    AppCompatButton b1;
    String getCourTitle,getCourDescr,getCourVen,getCourDate,getDur;
    String apiUrl="https://mountzioncollege.herokuapp.com/addcourse";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        e1=(EditText)findViewById(R.id.courtit);
        e2=(EditText)findViewById(R.id.courdescr);
        e3=(EditText)findViewById(R.id.courven);
        e4=(EditText)findViewById(R.id.courdate);
        e5=(EditText)findViewById(R.id.courdur);
        b1=(AppCompatButton)findViewById(R.id.submit);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCourTitle=e1.getText().toString();
                getCourDescr=e2.getText().toString();
                getCourVen=e3.getText().toString();
                getCourDate=e4.getText().toString();
                getDur=e5.getText().toString();
                StringRequest s=new StringRequest(Request.Method.POST,
                        apiUrl,
                        new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        e1.setText("");
                        e2.setText("");
                        e3.setText("");
                        e4.setText("");
                        e5.setText("");
                        Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();

                    }
                },
                        new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();

                    }
                })
                {

                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        HashMap<String,String> params=new HashMap<>();
                        params.put("courseTitle",getCourTitle);
                        params.put("courseDescription",getCourDescr);
                        params.put("courseVenue",getCourVen);
                        params.put("courseDate",getCourDate);
                        params.put("courseDuration",getDur);
                        return params;
                    }
                };
                RequestQueue rq= Volley.newRequestQueue(getApplicationContext());
                rq.add(s);

            }
        });


    }
}