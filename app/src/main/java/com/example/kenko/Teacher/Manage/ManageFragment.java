package com.example.kenko.Teacher.Manage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kenko.R;
import com.example.kenko.models.CourceModel;
import com.example.kenko.retrofitutil.ApiClient;
import com.example.kenko.retrofitutil.ApiInterface;
import com.example.kenko.sharedPreferences.DataLocalManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ManageFragment extends Fragment {
    View mView;

    Spinner spinnerOption;
    String status = "open";

    String email = DataLocalManager.getStringEmail();

    private RecyclerView recyclerView;
    private CourceAdapter adapter;
    private List<CourceModel> courcesdata;

    SearchView search_view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        mView = inflater.inflate(R.layout.teacher_manage,container, false);
        //Data
        displayCource(email, status);
        searchView();

        spinnerOption = mView.findViewById(R.id.spinnerOption);
        List<String> list = new ArrayList<>();
        list.add("open");
        list.add("prepare");
        list.add("finish");
        ArrayAdapter<String> adaptere = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, list);
        spinnerOption.setAdapter(adaptere);
        spinnerOption.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(parent.getItemAtPosition(position).equals("open")){
                    status = "open";
                    displayCource(email, status);
                }
                else{
                    String item =parent.getItemAtPosition(position).toString();
                    status = spinnerOption.getSelectedItem().toString();
                    displayCource(email, status);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Write Something
            }
        });

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
                List<CourceModel> filteredList = new ArrayList<>();
                for (CourceModel item : courcesdata){
                    if (item.getNameCource().toLowerCase().contains(newText.toLowerCase())){
                        filteredList.add(item);
                    }
                }
                adapter.filterList(filteredList);
            }
        });
    }

    public void displayCource(String email, String status){

        recyclerView = mView.findViewById(R.id.rec);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        Call<List<CourceModel>> call = ApiClient.getApiClient().create(ApiInterface.class).displayCource(email, status);
        call.enqueue(new Callback<List<CourceModel>>() {
            @Override
            public void onResponse(Call<List<CourceModel>> call, Response<List<CourceModel>> response) {
                courcesdata = response.body();
                adapter = new CourceAdapter(courcesdata, getContext(), new IClickItemCourceListener() {
                    @Override
                    public void deleteItemCource(CourceModel cource) {
                        onClickDelete(cource);
                    }

                    @Override
                    public void detailItemCource(CourceModel cource) {
                        onClickDetail(cource);
                    }
                });

                recyclerView.setAdapter(adapter);
                RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
                recyclerView.addItemDecoration(itemDecoration);
            }

            @Override
            public void onFailure(Call<List<CourceModel>> call, Throwable t) {
                // Write Something
            }
        });

    }

    private void onClickDelete(CourceModel cource){
        Call<CourceModel> call = ApiClient.getApiClient().create(ApiInterface.class).deleteCource(cource.getIdCource());
        call.enqueue(new Callback<CourceModel>() {
            @Override
            public void onResponse(Call<CourceModel> call, Response<CourceModel> response) {
                // 
            }

            @Override
            public void onFailure(Call<CourceModel> call, Throwable t) {
                // Write something
            }
        });
    }

    private void onClickDetail(CourceModel cource){
        DataLocalManager.setStringIdCource(cource.getIdCource());
        Intent i = new Intent(getContext(), ManageDetails.class);
        startActivity(i);
    }

}
