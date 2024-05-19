package app.ntnt.finalprojectexamonline.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import app.ntnt.finalprojectexamonline.R;
import app.ntnt.finalprojectexamonline.adapter.TestAdapter;
import app.ntnt.finalprojectexamonline.model.entites.Test;
import app.ntnt.finalprojectexamonline.model.response.HistoryInUserResponse;
import app.ntnt.finalprojectexamonline.model.response.QuestionResponse;
import app.ntnt.finalprojectexamonline.model.response.ResponseEntity;
import app.ntnt.finalprojectexamonline.services.BaseAPIService;
import app.ntnt.finalprojectexamonline.services.IHistoryService;
import app.ntnt.finalprojectexamonline.utils.AppConstrain;
import app.ntnt.finalprojectexamonline.utils.SharedPrefManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HistoryFragment extends Fragment {
    RecyclerView recyclerView;
    TestAdapter testAdapter;
    List<Test> tests;

    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_history, container, false);
        recyclerView = view.findViewById(R.id.rcv_exam_complete);
        Long userId =SharedPrefManager.getInstance(getContext()).getUserId();
        testAdapter = new TestAdapter(this);
        GridLayoutManager gridLayoutManager= new GridLayoutManager(getContext(),1, GridLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(gridLayoutManager);


      BaseAPIService.createService(IHistoryService.class).getHisTory(SharedPrefManager.getInstance(getContext()).getUserId()).enqueue(new Callback<ResponseEntity>() {

          @Override
          public void onResponse(Call<ResponseEntity> call, Response<ResponseEntity> response) {

              if(response.body().getData()!=null)
              {
                  List<Object> objects;
                  objects = AppConstrain.objectList(response.body().getData(), HistoryInUserResponse.class);
                  List<HistoryInUserResponse> historyInUserResponses= new ArrayList<>();
                  if(objects!=null)
                  {
                      for (Object i: objects){
                          historyInUserResponses.add((HistoryInUserResponse) i);
                      }


                      Log.d("TAG", "onResponse: "+historyInUserResponses.toString());
                  }
                  testAdapter.setData(historyInUserResponses);
                  recyclerView.setAdapter(testAdapter);
              }
          }

          @Override
          public void onFailure(Call<ResponseEntity> call, Throwable t) {
              Log.d("TAG", "onResponse: failed");
          }
      });

        return view;
    }

}