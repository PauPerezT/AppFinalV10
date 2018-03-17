package com.paulaperez.appfinalv10;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityLoggin extends AppCompatActivity {

    private EditText etuser, etpass;
    private Button btlog;
    private TextView tvregister;
    public static final int LOGGINREQUESTCODE=1;
    private String username,userValidation, password, passValidation, name, email;
    ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loggin);


        //DATOS DESDE PRINCIPAL Y PROFILE DESPUES DE CERRAR SESION
        Bundle fromPrincipal= getIntent().getExtras();
        if(fromPrincipal!=null) {
            username = fromPrincipal.getString(ActivityRegistration.intent_User, "");
            password = fromPrincipal.getString(ActivityRegistration.intent_pass, "");
            name = fromPrincipal.getString(ActivityRegistration.intent_Name, "");
            email = fromPrincipal.getString(ActivityRegistration.intent_Email, "");
            //Toast.makeText(MainActivity.this, "me acabaron de mandar:"+name+"-"+password,Toast.LENGTH_SHORT).show();
        }

        Bundle fromProfile= getIntent().getExtras();
        if(fromProfile!=null) {
            username = fromProfile.getString(ActivityRegistration.intent_User, "");
            password = fromProfile.getString(ActivityRegistration.intent_pass, "");
            name = fromProfile.getString(ActivityRegistration.intent_Name, "");
            email = fromProfile.getString(ActivityRegistration.intent_Email, "");
            //Toast.makeText(MainActivity.this, "me acabaron de mandar:"+name+"-"+password,Toast.LENGTH_SHORT).show();
        }

        ///////////////////////////////////////////////////////////////////////////////////////////

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

        btlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                userValidation = etuser.getText().toString();
                passValidation = etpass.getText().toString();

                if(DataValidation()) {
                    //userValidation = etuser.getText().toString();
                    //passValidation = etpass.getText().toString();
                    Intent i = new Intent(ActivityLoggin.this, MainActivity.class);
                    i.putExtra(ActivityRegistration.intent_User, username);
                    i.putExtra(ActivityRegistration.intent_pass, password);
                    i.putExtra(ActivityRegistration.intent_Name, name);
                    i.putExtra(ActivityRegistration.intent_Email, email);


                    startActivity(i);
                    finish();
                }else{DataValidation();}
            }
        });





    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==LOGGINREQUESTCODE){
            if(resultCode==RESULT_OK){
                etuser.setText("");
                etpass.setText("");
                username=data.getStringExtra(ActivityRegistration.intent_User);
                password=data.getStringExtra("register_pass");
                name=data.getStringExtra(ActivityRegistration.intent_Name);
                email=data.getStringExtra(ActivityRegistration.intent_Email);

                //etuser.setText(username);
                //etpass.setText(password);
                 }
        }
    }

    private boolean DataValidation(){

        if( userValidation.isEmpty() || passValidation.isEmpty()){
            Toast.makeText(ActivityLoggin.this, getText(R.string.empty_fields), Toast.LENGTH_LONG ).show();
            return false;
        } else if(!userValidation.equals(username) || !passValidation.equals(password)){
            Toast.makeText(ActivityLoggin.this, getText(R.string.wrongData), Toast.LENGTH_LONG ).show();
            return false;
        }else{
        return true;
        }

    }
}
