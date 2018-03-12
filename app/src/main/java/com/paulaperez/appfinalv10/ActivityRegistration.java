package com.paulaperez.appfinalv10;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityRegistration extends AppCompatActivity {
    private EditText etname, etuser, etpass, etpassC, etmail;
    private Button btreg;
    public static final String intent_User="register_user", intent_pass="register_pass";
    private String username, password;
    private String passwordC, name, email;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        etname=(EditText)findViewById(R.id.register_etName);
        etuser=(EditText)findViewById(R.id.register_etUser);
        etpass=(EditText)findViewById(R.id.register_etPass);
        etpassC=(EditText)findViewById(R.id.register_etPassC);
        etmail=(EditText)findViewById(R.id.register_etEmail);
        btreg=(Button) findViewById(R.id.register_btRegister);

        btreg.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View view) {
                 username=etuser.getText().toString();
                 password=etpass.getText().toString();
                 passwordC=etpassC.getText().toString();
                 name=etname.getText().toString();
                 email=etmail.getText().toString();
                if( fieldsFull() ){
                    if( comparePasswords( password, passwordC) && fieldsFull() ){

                        Intent returnIntent = new Intent();
                        returnIntent.putExtra(intent_User,username);
                        returnIntent.putExtra(intent_pass, password);
                        setResult(Activity.RESULT_OK,returnIntent);
                        finish();

                        etpassC.setError( null);



                    }else if(!comparePasswords( password, passwordC)){

                        Toast.makeText(ActivityRegistration.this,getString(R.string.pass_not_equal),Toast.LENGTH_LONG).show();
                        etpassC.setError( getString(R.string.pass_not_equal) );

                        // TODO las contraseñas no son iguales
                    }
                }else if(!fieldsFull()){
                    // TODO hay algun campo vacio
                    fieldsFull();
                }

            }
        });

    }

    private boolean fieldsFull(){
        // TODO
        if( username.isEmpty() || password.isEmpty() || passwordC.isEmpty() || email.isEmpty() ){
            Toast.makeText(ActivityRegistration.this, getText(R.string.empty_fields), Toast.LENGTH_LONG ).show();
            return false;
        }
        return true;

    }



    private boolean comparePasswords(String pass, String passC){
        return pass.equals(passC);
    }
}
