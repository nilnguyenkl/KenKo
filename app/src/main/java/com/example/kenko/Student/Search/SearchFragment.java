package com.example.kenko.Student.Search;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kenko.R;
import com.example.kenko.models.SearchModel;
import com.example.kenko.retrofitutil.ApiClient;
import com.example.kenko.retrofitutil.ApiInterface;
import com.example.kenko.sharedPreferences.DataLocalManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchFragment extends Fragment{

    View mView;

    private RecyclerView recyclerView;
    private SearchAdapter adapter;
    private List<SearchModel> searchsdata;

    SearchView search_view;

    String emailLocal = DataLocalManager.getStringEmail();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        mView = inflater.inflate(R.layout.student_search,container, false);
        displayAllCource();
        searchView();
        return mView;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    private void searchView(){
        search_view = mView.findViewById(R.id.search_view);
        search_view.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return true;
            }

            private void filter(String newText) {
                List<SearchModel> filteredList = new ArrayList<>();
                for (SearchModel item : searchsdata){
                    if (item.getName_cource().toLowerCase().contains(newText.toLowerCase())
                            || item.getLastname().toLowerCase().contains(newText.toLowerCase())
                            || item.getFirstname().toLowerCase().contains(newText.toLowerCase())){
                        filteredList.add(item);
                    }
                }
                adapter.filterList(filteredList);
            }
        });
    }

    public void displayAllCource(){

        recyclerView = mView.findViewById(R.id.recycleview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        String status = "prepare";

        Call<List<SearchModel>> call = ApiClient.getApiClient().create(ApiInterface.class).displayAllCource(status, emailLocal);
        call.enqueue(new Callback<List<SearchModel>>() {
            @Override
            public void onResponse(Call<List<SearchModel>> call, Response<List<SearchModel>> response) {
                searchsdata = response.body();
                adapter = new SearchAdapter(searchsdata, getContext(), new IClickItemCourcesListener() {
                    @Override
                    public void detailItemCource(SearchModel search) {
                        clickDetail(search);
                    }
                });

                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<SearchModel>> call, Throwable t) {
                // Write Something
            }
        });
    }

    private void clickDetail(SearchModel search){
        DataLocalManager.setStringIdCource(search.getId_cource());
        // Log.d("=================", search.getId_cource());
        Intent i = new Intent(getContext(), SearchSignUp.class);
        startActivity(i);
    }

}
