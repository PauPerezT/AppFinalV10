package com.paulaperez.appfinalv10;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private String username, paswwrod;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle fromLoggin= getIntent().getExtras();
        if(fromLoggin!=null){
            username=fromLoggin.getString(ActivityRegistration.intent_User, "");
            paswwrod=fromLoggin.getString(ActivityRegistration.intent_pass, "");
            Toast.makeText(MainActivity.this, "me acabaron de mandar:"+username+"-"+paswwrod,Toast.LENGTH_SHORT).show();

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
                Toast.makeText(MainActivity.this, "Holita bebe", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.action_logout:
                return true;

            default:

                return super.onOptionsItemSelected(item);
        }
    }
}
