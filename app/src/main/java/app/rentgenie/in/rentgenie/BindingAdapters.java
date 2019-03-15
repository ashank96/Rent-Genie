package app.rentgenie.in.rentgenie;

import android.widget.ImageView;

import com.bumptech.glide.Glide;

import androidx.databinding.BindingAdapter;

public class BindingAdapters {

	@BindingAdapter("android:src")
	public static void setImageDrawable(ImageView imageView, String url){
		Glide.with(imageView.getContext()).load(url).into(imageView);
	}
}
