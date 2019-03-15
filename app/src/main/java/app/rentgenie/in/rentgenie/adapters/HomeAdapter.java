package app.rentgenie.in.rentgenie.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import app.rentgenie.in.rentgenie.BindingAdapters;
import app.rentgenie.in.rentgenie.R;

import app.rentgenie.in.rentgenie.ResultRatingComparator;
import app.rentgenie.in.rentgenie.databinding.ItemLodgingGatewayBinding;
import app.rentgenie.in.rentgenie.models.Result;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeViewHolder> {
	List<Result> resultList;

	ItemLodgingGatewayBinding itemLodgingGatewayBinding;

	public HomeAdapter() {
		this.resultList = new ArrayList<>();
	}

	@NonNull
	@Override
	public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		itemLodgingGatewayBinding = DataBindingUtil.inflate(
				LayoutInflater.from(parent.getContext()),
				R.layout.item_lodging_gateway, parent, false);
		return new HomeViewHolder(itemLodgingGatewayBinding);
	}

	@Override
	public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {
		Result result = resultList.get(position);
		holder.bind(result);
	}

	@Override
	public int getItemCount() {
		return resultList.size();
	}

	public void updateList(List<Result> resultList) {
		this.resultList.clear();
		this.resultList.addAll(resultList);
		Collections.sort(this.resultList,new ResultRatingComparator());
		notifyDataSetChanged();
	}

	class HomeViewHolder extends RecyclerView.ViewHolder {

		ItemLodgingGatewayBinding itemLodgingGatewayBinding;

		public HomeViewHolder(ItemLodgingGatewayBinding itemLodgingGatewayBinding) {
			super(itemLodgingGatewayBinding.getRoot());
			this.itemLodgingGatewayBinding = itemLodgingGatewayBinding;

		}

		public void bind(Result result) {
			itemLodgingGatewayBinding.setResult(result);
			BindingAdapters.setImageDrawable(itemLodgingGatewayBinding.itemLodgingImageView, result.getIcon());
		}


	}
}
