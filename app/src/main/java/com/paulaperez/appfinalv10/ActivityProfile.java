package com.paulaperez.appfinalv10;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class ActivityProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
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
                startActivity(i);
                finish();

                return true;

            case R.id.action_logout:

                Intent log= new Intent(ActivityProfile.this, ActivityLoggin.class);
                startActivity(log);
                finish();
                return true;

            default:

                return super.onOptionsItemSelected(item);
        }
    }
}
