package app.rentgenie.in.rentgenie;

import android.os.Bundle;

import com.bluelinelabs.conductor.Conductor;
import com.bluelinelabs.conductor.Router;
import com.bluelinelabs.conductor.RouterTransaction;

import androidx.databinding.DataBindingUtil;
import app.rentgenie.in.rentgenie.controllers.HomeController;
import app.rentgenie.in.rentgenie.databinding.ActivityLandingBinding;

public class LandingActivity extends BaseActivity {
	ActivityLandingBinding activityLandingBinding;
	Router router;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		activityLandingBinding = DataBindingUtil.setContentView(this, R.layout.activity_landing);
		initToolbar();
		router = Conductor.attachRouter(this, activityLandingBinding.conductor, savedInstanceState);
		if (!router.hasRootController()) {
			router.setRoot(RouterTransaction.with(new HomeController()));
		}
	}

	private void initToolbar() {
		setSupportActionBar(activityLandingBinding.toolbarHolder.toolbar);
		getSupportActionBar().setDisplayShowTitleEnabled(false);
	}

	@Override
	protected void setScreenTitle(String title) {
		activityLandingBinding.toolbarHolder.toolbar.setTitle(null);
	}

	@Override
	protected void hideToolbar() {
		getSupportActionBar().hide();
	}

	@Override
	public void onBackPressed() {
		if (!router.handleBack()) {
			super.onBackPressed();
		}
	}
}
