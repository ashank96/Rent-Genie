package rentgenie.com.rentgenie;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.ViewGroup;

import rentgenie.com.rentgenie.controller.LandingController;

public class MainActivity extends AppCompatActivity {
    private Router router;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ViewGroup container= findViewById(R.id.controller_container);
        router= Conductor.attachRouter(this,container,savedInstanceState);
        router.setRoot(RouterTransaction.with(LandingController.newInstance()));
    }

    @Override
    public void onBackPressed() {

        if(!router.handleBack()){
            super.onBackPressed();
        }
    }
}
