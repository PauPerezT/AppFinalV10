package com.paulaperez.appfinalv10;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityProfile extends AppCompatActivity {

    private String username, password, name, email;
    private TextView tvData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        tvData=(TextView)findViewById(R.id.tvUserData);

        Bundle fromLoggin= getIntent().getExtras();
        if(fromLoggin!=null){
            username=fromLoggin.getString(ActivityRegistration.intent_User, "");
            password=fromLoggin.getString(ActivityRegistration.intent_pass, "");
            name=fromLoggin.getString(ActivityRegistration.intent_Name, "");
            email=fromLoggin.getString(ActivityRegistration.intent_Email, "");
            //Toast.makeText(ActivityProfile.this, "me acabaron de mandar:"+username+"-"+password,Toast.LENGTH_LONG).show();

            printResults();

//TODO Mejorar la letra
        }




    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch ( item.getItemId() ) {
            case R.id.action_principal:
                Toast.makeText(ActivityProfile.this, "Holita principal", Toast.LENGTH_SHORT).show();
                Intent i= new Intent(ActivityProfile.this, MainActivity.class);
                i.putExtra(ActivityRegistration.intent_User, username);
                i.putExtra(ActivityRegistration.intent_pass, password);
                i.putExtra(ActivityRegistration.intent_Name, name);
                i.putExtra(ActivityRegistration.intent_Email, email);
                startActivity(i);
                finish();

                return true;

            case R.id.action_logout:

                Intent log= new Intent(ActivityProfile.this, ActivityLoggin.class);
                log.putExtra(ActivityRegistration.intent_User, username);
                log.putExtra(ActivityRegistration.intent_pass, password);
                log.putExtra(ActivityRegistration.intent_Name, name);
                log.putExtra(ActivityRegistration.intent_Email, email);

                startActivity(log);
                finish();
                return true;

            default:

                return super.onOptionsItemSelected(item);
        }

        //TODO arreglar la ida hacia atras, que de profile vaya a principal

    }

    private void printResults(){
        String results="";

        results+=getString(R.string.name)+":"+name+"\n";
        results+=getString(R.string.user)+":"+username+"\n";
        results+=getString(R.string.pass)+":"+password+"\n";
        results+=getString(R.string.email)+":"+email+"\n";

        tvData.setText(results);
    }
}
