package app.rentgenie.in.rentgenie;

import java.util.Comparator;

import app.rentgenie.in.rentgenie.models.Result;

public class ResultRatingComparator implements Comparator<Result> {
	@Override
	public int compare(Result o1, Result o2) {
		return o2.getRatingInt()-o1.getRatingInt();
	}

	@Override
	public boolean equals(Object obj) {
		return false;
	}
}
