package com.paulaperez.appfinalv10;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ActivityLoggin extends AppCompatActivity {

    private EditText etuser, etpass;
    private Button btlog;
    private TextView tvregister;
    public static final int LOGGINREQUESTCODE=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loggin);
        etuser=(EditText)findViewById(R.id.loggin_etUser);
        etpass=(EditText)findViewById(R.id.loggin_etPass);
        btlog=(Button)findViewById(R.id.loggin_btLogin);
        tvregister=(TextView)findViewById(R.id.loggin_tvRegister);

        tvregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(ActivityLoggin.this,ActivityRegistration.class);
                startActivityForResult(i,LOGGINREQUESTCODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==LOGGINREQUESTCODE){
            if(resultCode==RESULT_OK){
                String username=data.getStringExtra(ActivityRegistration.intent_User), password=data.getStringExtra("register_pass");
                etuser.setText(username);
                etpass.setText(password);
            }
        }
    }
}
