package rentgenie.com.rentgenie.repository;

import java.util.List;

import rentgenie.com.rentgenie.model.Result;

public interface RepositoryObserver {
    void onPlacesDataChanged(List<Result> resultList);
}
