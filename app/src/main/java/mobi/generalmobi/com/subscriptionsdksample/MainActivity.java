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

import com.generalmobi.vas.subscription.sdk.SIMU_PARAM;
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
        HashMap<String,String> options = new HashMap<String, String>();

        /************************ SANDBOX ENVIRONMENT (Remove this production)********************************/
        SubscriptionManager.BASE_URL="https://sit.generalmobi.mobi/subscription";
        //Update the mobile number for your testing
        String test_msisdn="919911260993";
        SubscriptionManager.SUSBCRIPTION_URL="https://sit.generalmobi.mobi/subscription/consent/aggrigated/%s/%s/subscribe.html?ptxid=7879&identifier=%s&operator=aircel&msisdn="+test_msisdn;
        options.put(SIMU_PARAM.OPERATOR, "aircel");
        options.put(SIMU_PARAM.MSISDN, test_msisdn);
        options.put(SIMU_PARAM.NETWORK_MODE, "wifi");

        //As for sandbox partnerCode/product to shubhanshu.shukla@generalmobi.in
        String sandboxProduct="test";
        String sandboxPartner="7544";
        /*****************************************************************************************************/




        final SubscriptionManager manager=new SubscriptionManager(sandboxProduct,sandboxPartner,this,options);
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
