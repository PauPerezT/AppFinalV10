package com.paulaperez.appfinalv10;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.login.LoginManager;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;

public class ActivityRegistration extends AppCompatActivity
        {
    private EditText etname, etuser, etpass, etpassC, etmail;
    private Button btreg;
    public static final String intent_User="register_user", intent_pass="register_pass", intent_Name="register_name", intent_Email="register_email";
    private String username, password;
    private String passwordC, name, email;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        inicializar();

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
                    if( comparePasswords( password, passwordC) && fieldsFull() && passCorrectLength() ){
                        username=etuser.getText().toString();
                        password=etpass.getText().toString();
                        passwordC=etpassC.getText().toString();
                        name=etname.getText().toString();
                        email=etmail.getText().toString();
                        newAccount(email,password);

                        Intent returnIntent = new Intent(ActivityRegistration.this,ActivityLoggin.class);
                        /*returnIntent.putExtra(intent_User,username);
                        returnIntent.putExtra(intent_pass, password);
                        returnIntent.putExtra(intent_Name, name);
                        returnIntent.putExtra(intent_Email, email);*/
                        //setResult(Activity.RESULT_OK,returnIntent);
                        startActivity(returnIntent);
                        finish();

                        etpassC.setError( null);
                        etpass.setError( null);



                    }else if(!comparePasswords( password, passwordC)){

                        Toast.makeText(ActivityRegistration.this,getString(R.string.pass_not_equal),Toast.LENGTH_LONG).show();
                        etpassC.setError( getString(R.string.pass_not_equal) );


                    }
                }else if(!fieldsFull()){

                    fieldsFull();
                }else if(!passCorrectLength()){

                    passCorrectLength();

                }


            }
        });
    }

    private boolean fieldsFull(){

        if( username.isEmpty() || password.isEmpty() || passwordC.isEmpty() || email.isEmpty() ){
            Toast.makeText(ActivityRegistration.this, getText(R.string.empty_fields), Toast.LENGTH_LONG ).show();
            return false;
        }
        return true;

    }

            private boolean passCorrectLength(){

                if(  password.length()<6 ){
                    etpass.setError("La contraseña debe contener minimo 6 digitos") ;
                    return false;
                }
                return true;

            }



    private boolean comparePasswords(String pass, String passC){
        return pass.equals(passC);
    }

    private void inicializar(){
        firebaseAuth= FirebaseAuth.getInstance();
        authStateListener= new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser firebaseUser= firebaseAuth.getCurrentUser();
                if (firebaseUser!= null){
                    Log.d("FirebaseUser", "Usuario Logueado"+ firebaseUser.getEmail());

                }else {
                    Log.d("FirebaseUser", "No hay usuario logueado");

                }
            }
        };
    }

 public void newAccount(String newEmail, String newPass){
       firebaseAuth.createUserWithEmailAndPassword(newEmail,newPass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
           @Override
           public void onComplete(@NonNull Task<AuthResult> task) {
               if(task.isSuccessful()){
                   Toast.makeText(ActivityRegistration.this, "Congrats, You have an account", Toast.LENGTH_SHORT).show();
               }else {

                   //Toast.makeText(ActivityRegistration.this, "Error in the account creation", Toast.LENGTH_SHORT).show();

                   if(internetConnection()) {

                       try {
                           throw task.getException();
                       }  catch (FirebaseAuthInvalidCredentialsException e) {
                           Toast.makeText(ActivityRegistration.this, "Correo invalido", Toast.LENGTH_LONG).show();
                       } catch (FirebaseAuthUserCollisionException e) {
                           Toast.makeText(ActivityRegistration.this, "El usuario ya existe", Toast.LENGTH_LONG).show();
                           LoginManager.getInstance().logOut();
                       } catch (Exception e) {

                       }
                   }else{
                       Toast.makeText(ActivityRegistration.this, "No hay acceso a internet, no se cargaran los datos", Toast.LENGTH_LONG).show();

                   }

               }
           }
       });

 }


 ////Internet connection
 private boolean internetConnection() {

     ConnectivityManager connectivityManager = (ConnectivityManager)
             getSystemService(Context.CONNECTIVITY_SERVICE);

     NetworkInfo actNetInfo;
     actNetInfo = connectivityManager.getActiveNetworkInfo();

     return (actNetInfo != null && actNetInfo.isConnected());
 }

            @Override
            public void onBackPressed() {
                Intent principalReturn= new Intent(ActivityRegistration.this, ActivityLoggin.class);

                startActivity(principalReturn);
                finish();
            }
}
