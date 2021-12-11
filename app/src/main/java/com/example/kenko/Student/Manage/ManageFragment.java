package com.example.kenko.Student.Manage;

import android.content.Context;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kenko.R;
import com.example.kenko.models.ParticipantCourceModel;
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
    private CourceParticipantAdapter adapter;
    private List<ParticipantCourceModel> participantsdata;

    SearchView search_view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        mView = inflater.inflate(R.layout.student_manage,container, false);

        //Data
        displayParticipantCource(email, status);
        searchView();

        spinnerOption = mView.findViewById(R.id.spinnerOption);
        List<String> list = new ArrayList<>();
        list.add("open");
        list.add("prepare");
        list.add("finish");
        list.add("deleted");
        ArrayAdapter<String> adaptere = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, list);
        spinnerOption.setAdapter(adaptere);
        spinnerOption.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(parent.getItemAtPosition(position).equals("open")){
                    status = "open";
                    displayParticipantCource(email, status);
                }
                else{
                    String item =parent.getItemAtPosition(position).toString();
                    status = spinnerOption.getSelectedItem().toString();
                    displayParticipantCource(email, status);
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
                List<ParticipantCourceModel> filteredList = new ArrayList<>();
                for (ParticipantCourceModel item : participantsdata){
                    if (item.getNameCource().toLowerCase().contains(newText.toLowerCase())){
                        filteredList.add(item);
                    }
                }
                adapter.filterList(filteredList);
            }
        });
    }

    public void displayParticipantCource(String email, String status){

        recyclerView = mView.findViewById(R.id.rec);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        Call<List<ParticipantCourceModel>> call = ApiClient.getApiClient().create(ApiInterface.class).displayAllCourceParticipant(status, email);
        call.enqueue(new Callback<List<ParticipantCourceModel>>() {
            @Override
            public void onResponse(Call<List<ParticipantCourceModel>> call, Response<List<ParticipantCourceModel>> response) {
                participantsdata = response.body();
                adapter = new CourceParticipantAdapter(participantsdata, getContext(), new IClickItemParticipantListener() {
                    @Override
                    public void deleteItemCource(ParticipantCourceModel participant) {
                        onClickDelete(participant);
                    }
                });
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<ParticipantCourceModel>> call, Throwable t) {
                // Write Something
            }
        });
    }

    private void onClickDelete(ParticipantCourceModel participant){
        Call<ParticipantCourceModel> call = ApiClient.getApiClient().create(ApiInterface.class).deleteMemberParticipant(participant.getIdCource(), email);
        call.enqueue(new Callback<ParticipantCourceModel>() {
            @Override
            public void onResponse(Call<ParticipantCourceModel> call, Response<ParticipantCourceModel> response) {
                // Success
            }

            @Override
            public void onFailure(Call<ParticipantCourceModel> call, Throwable t) {
                // Write something
            }
        });
    }
}
