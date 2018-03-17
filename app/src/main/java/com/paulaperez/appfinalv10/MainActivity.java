package com.paulaperez.appfinalv10;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private String username, password, name, email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle fromLoggin= getIntent().getExtras();
        if(fromLoggin!=null){
            username=fromLoggin.getString(ActivityRegistration.intent_User, "");
            password=fromLoggin.getString(ActivityRegistration.intent_pass, "");
            name=fromLoggin.getString(ActivityRegistration.intent_Name, "");
            email=fromLoggin.getString(ActivityRegistration.intent_Email, "");
            //Toast.makeText(MainActivity.this, "me acabaron de mandar:"+name+"-"+password,Toast.LENGTH_SHORT).show();
//TODO informacion de mi app
//TODO arreglar la ida hacia atras, que de principal vaya a loggin

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch ( item.getItemId() ) {
            case R.id.action_profile:
                //Toast.makeText(MainActivity.this, "Holita bebe", Toast.LENGTH_SHORT).show();
                Intent i= new Intent(MainActivity.this, ActivityProfile.class);
                i.putExtra(ActivityRegistration.intent_User, username);
                i.putExtra(ActivityRegistration.intent_pass, password);
                i.putExtra(ActivityRegistration.intent_Name, name);
                i.putExtra(ActivityRegistration.intent_Email, email);

                startActivity(i);
                finish();

                return true;

            case R.id.action_logout:

                Intent log= new Intent(MainActivity.this, ActivityLoggin.class);
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
    }
}
