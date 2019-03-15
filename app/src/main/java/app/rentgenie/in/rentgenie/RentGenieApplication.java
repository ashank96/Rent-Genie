package app.rentgenie.in.rentgenie;

import android.app.Application;
import android.content.Context;


import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;
import app.rentgenie.in.rentgenie.dagger.Graph;

public class RentGenieApplication extends MultiDexApplication {
	private static RentGenieApplication instance;
	private static Context context;
	private Graph component;

	@Override
	public void onCreate() {
		super.onCreate();
		context=this;
		init();
	}
	private void init() {
		instance = this;
		MultiDex.install(getInstanceContext());
		component=Graph.Initializer.initialize((Application) getInstanceContext());
	}
	public static RentGenieApplication getInstance(){
		return instance;
	}

	public static Context getInstanceContext(){
		return context;
	}

	public Graph getComponent(){
		return component;
	}

	@Override
	protected void attachBaseContext(Context base) {
		super.attachBaseContext(base);
		MultiDex.install(this);
	}
}
