package app.rentgenie.in.rentgenie;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.Toast;

public class ViewUtils {
	public static Drawable getImageDrawable(int resId){
		return RentGenieApplication.getInstance().getResources().getDrawable(resId);
	}

	public static String getString(int resId){
		return RentGenieApplication.getInstance().getResources().getString(resId);
	}

	public static int getColor(int resId){
		return RentGenieApplication.getInstance().getResources().getColor(resId);
	}

	public static void makeToastShort(Context context,String message){
		Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
	}
	public static void makeToastLong(Context context,String message){
		Toast.makeText(context, message, Toast.LENGTH_LONG).show();
	}
}
