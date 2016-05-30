package mobi.generalmobi.com.subscriptionsdksample;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.generalmobi.vas.subscription.sdk.SubscriptionManager;
import com.generalmobi.vas.subscription.sdk.event.StateEvent;
import com.generalmobi.vas.subscription.sdk.listener.CheckResponseListener;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements CheckResponseListener {

    Button checkButton=null;
    Button subscribeButton=null;
    TextView result_text=null;

    @Override
    public void onStateRevieved(final StateEvent event) {
        MainActivity.this.runOnUiThread(new Runnable() {

            @Override
            public void run() {
                result_text.setText(event.getState().toString());
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       final SubscriptionManager manager=new SubscriptionManager("GGCLUBW","7332",this,new HashMap<String,String>(0));
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        checkButton = (Button) findViewById(R.id.check_button);
        subscribeButton = (Button) findViewById(R.id.subscribe_button);
        result_text = (TextView) findViewById(R.id.result_text);

        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manager.check(MainActivity.this);
                result_text.setText("Checking....");

            }
        });

        subscribeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manager.subscribe(MainActivity.this);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
