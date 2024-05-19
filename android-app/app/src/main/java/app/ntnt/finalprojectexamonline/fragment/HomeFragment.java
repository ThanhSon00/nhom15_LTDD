package app.ntnt.finalprojectexamonline.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import app.ntnt.finalprojectexamonline.R;
import app.ntnt.finalprojectexamonline.adapter.SubjectAdpater;
import app.ntnt.finalprojectexamonline.adapter.TestInforAdapter;
import app.ntnt.finalprojectexamonline.model.TestInfor;
import app.ntnt.finalprojectexamonline.model.entites.Subject;
import app.ntnt.finalprojectexamonline.model.entites.User;
import app.ntnt.finalprojectexamonline.model.response.ResponseEntity;
import app.ntnt.finalprojectexamonline.model.response.SubjectResponse;
import app.ntnt.finalprojectexamonline.model.response.TestResponse;
import app.ntnt.finalprojectexamonline.services.BaseAPIService;
import app.ntnt.finalprojectexamonline.services.IStatisticService;
import app.ntnt.finalprojectexamonline.services.ISubjectService;
import app.ntnt.finalprojectexamonline.services.IUserService;
import app.ntnt.finalprojectexamonline.slider.SliderAdapter;
import app.ntnt.finalprojectexamonline.slider.SliderItem;
import app.ntnt.finalprojectexamonline.utils.AppConstrain;
import app.ntnt.finalprojectexamonline.utils.SharedPrefManager;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment {
    SubjectAdpater subjectAdapter;
    TestInforAdapter testInforAdapter;
    CircleImageView avatar;
    RecyclerView rcvSubject;
    RecyclerView rcvTest;
    RecyclerView recyclerView;
    List<Subject> subjects;
    SliderAdapter sliderAdapter;
    List<TestInfor> testInforList;
    View view;
    private Timer timer;
    ViewPager2 viewPager2;
    List<SliderItem> sliderItemList;

    TextView tvNameUser;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);
       // Ánh xạ view
        init(view);
        //Load dữ liệu cho rcv Môn học
        loadDataSubject();
//        loadDataTest();

        //Load data for User
        loadDataUser();

        viewPager2 = view.findViewById(R.id.view_rounded_image);
        sliderAdapter=new SliderAdapter(getContext());
        sliderAdapter.setData(getAllItem());
        viewPager2.setAdapter(sliderAdapter);

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);

            }
        });
        autoSlider();
        loadFeatureTest();

        //Set name user
        BaseAPIService.createService(IUserService.class).getProfile()
                .enqueue(new Callback<ResponseEntity>() {
                    @Override
                    public void onResponse(Call<ResponseEntity> call, Response<ResponseEntity> response) {
                        if (response.body()!= null){
                            if (!response.body().isError()){
                                User user = (User) AppConstrain.toObject(response.body().getData(), User.class);
                                tvNameUser.setText(user.getFirstName()+ " " + user.getLastName());
                                Glide.with(view).load(user.getAvatar()).into(avatar);
                            }
                        }
                        if (response.errorBody()!= null){
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseEntity> call, Throwable t) {

                    }
                });


        return view;
    }

    private void loadDataUser(){

    }

//    private void loadDataTest()
//    {
//        testInforAdapter = new TestInforAdapter(this);
//        GridLayoutManager gridLayoutManager= new GridLayoutManager(getContext(),1, GridLayoutManager.VERTICAL,false);
//        rcvTest.setLayoutManager(gridLayoutManager);
//        testInforAdapter.setData(getFeaturedExam());
//        rcvTest.setAdapter(testInforAdapter);
//    }

//    private List<TestInfor> getFeaturedExam() {
//        testInforList = new ArrayList<>();
//        for(int i=0;i<=10;i++)
//        {
//            TestInfor test = new TestInfor(1,"Đề quốc gia lần 1","Toán",45,"Nguyễn Văn B","29/3/2023");
//            testInforList.add(test);
//        }
//
//        return testInforList;
//    }

    private void loadDataSubject() {
        subjectAdapter = new SubjectAdpater(this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
        rcvSubject.setLayoutManager(gridLayoutManager);

        BaseAPIService.createService(ISubjectService.class).getAllSubject()
                .enqueue(new Callback<ResponseEntity>() {
                    @Override
                    public void onResponse(Call<ResponseEntity> call, Response<ResponseEntity> response) {
                        if (response.body()!= null){
                            if (!response.body().isError()){
                                List<Object> objects;
                                List<SubjectResponse> subjectResponses = new ArrayList<>();
                                objects = AppConstrain.objectList(response.body().getData(), SubjectResponse.class);
                                for (Object i: objects){
                                    subjectResponses.add((SubjectResponse) i);
                                }
                                subjects = new ArrayList<>();
                                for (SubjectResponse i: subjectResponses){
                                    subjects.add(new Subject(i.getSubjectId(), i.getName(), i.getImage()));
                                }
                                subjectAdapter.setData(subjects);
                                rcvSubject.setAdapter(subjectAdapter);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseEntity> call, Throwable t) {
                        Log.d("erroeee rồi", t.getMessage().toString());
                    }
                });
    }

    void loadFeatureTest()
    {
        testInforAdapter = new TestInforAdapter(this);
        GridLayoutManager gridLayoutManager= new GridLayoutManager(getContext(),1, GridLayoutManager.VERTICAL,false);
        rcvTest.setLayoutManager(gridLayoutManager);
        BaseAPIService.createService(IStatisticService.class).getFeatureTest(0,5).enqueue(new Callback<ResponseEntity>() {
            @Override
            public void onResponse(Call<ResponseEntity> call, Response<ResponseEntity> response) {
                Log.d("TAG", "onResponse: thành công");
                List<Object> objects;
                objects = AppConstrain.objectList(response.body().getData(), TestResponse.class);
                List<TestResponse> testResponses = new ArrayList<>();
                for (Object i: objects){
                    testResponses.add((TestResponse) i);
                }
                testInforAdapter.setData(testResponses);
                rcvTest.setAdapter(testInforAdapter);
            }

            @Override
            public void onFailure(Call<ResponseEntity> call, Throwable t) {
                Log.d("TAG", "onResponse: thất bại");
            }
        });
    }

    private void init(View view)
    {
        tvNameUser = view.findViewById(R.id.tv_name_user);
        rcvSubject = view.findViewById(R.id.rcv_subject_home);
        rcvTest =view.findViewById(R.id.rcv_featured_exam);
        viewPager2= view.findViewById(R.id.view_rounded_image);
        avatar = view.findViewById(R.id.img_avt);
    }
    private List<SliderItem> getAllItem()
    {
        sliderItemList = new ArrayList<>();
        sliderItemList.add(new SliderItem(R.drawable.slider1));
        sliderItemList.add(new SliderItem(R.drawable.slider2));
        sliderItemList.add(new SliderItem(R.drawable.slider3));
        return sliderItemList;
    }
    private void autoSlider()
    {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        int currentItem = viewPager2.getCurrentItem();
                        int totalItem = sliderItemList.size()-1;
                        if(currentItem<totalItem)
                        {
                            currentItem++;
                            viewPager2.setCurrentItem(currentItem);

                        }
                        else {
                            viewPager2.setCurrentItem(0);
                        }
                    }
                });
            }
        },500, 2500);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(timer!=null)
        {
            timer.cancel();
            timer=null;
        }
    }
}