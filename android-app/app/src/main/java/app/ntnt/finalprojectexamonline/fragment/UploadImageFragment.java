package app.ntnt.finalprojectexamonline.fragment;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.io.IOException;
import java.util.Objects;

import app.ntnt.finalprojectexamonline.R;
import app.ntnt.finalprojectexamonline.activity.RegisterActivity;
import app.ntnt.finalprojectexamonline.databinding.ActivityLoginBinding;
import app.ntnt.finalprojectexamonline.databinding.FragmentUploadImageBinding;
import de.hdodenhof.circleimageview.CircleImageView;

public class UploadImageFragment extends Fragment {
    public interface OnImageUploadedListener {
        void onImageUploaded(Uri uri);
    }
    private OnImageUploadedListener listener;
    private Uri mUri;
    private CircleImageView circleImageView;
    public static final  int MY_REQUEST_CODE = 100;
    public static  String[] storge_permissions = {
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
            android.Manifest.permission.READ_EXTERNAL_STORAGE
    };
    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    public  static String [] getStorge_permissions_33 = {
            android.Manifest.permission.READ_MEDIA_IMAGES,
            android.Manifest.permission.READ_MEDIA_AUDIO,
            android.Manifest.permission.READ_MEDIA_VIDEO
    };
    public  static  String [] permissions() {
        String[] p;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            p = getStorge_permissions_33;
        } else {
            p = storge_permissions;
        }
        return p;
    }
    public static UploadImageFragment instance;private ActivityResultLauncher<String> mGetContent;



    public UploadImageFragment(){}
    public static UploadImageFragment getInstance(){
        if (instance == null){
            return new UploadImageFragment();
        }
        return instance;
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_upload_image, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        circleImageView = view.findViewById(R.id.image);
                view.findViewById(R.id.image).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckPermission();
            }
        });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGetContent = registerForActivityResult(new ActivityResultContracts.GetContent(),
                new ActivityResultCallback<Uri>() {
                    @Override
                    public void onActivityResult(Uri result) {
                       returnSelectedImage(result);
                    }
                });
    }
    private void returnSelectedImage(Uri imageUri) {
        if (listener != null) {
            listener.onImageUploaded(imageUri);
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (OnImageUploadedListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnImageSelectedListener");
        }
    }

    private void CheckPermission() {
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE)
                ==  PackageManager.PERMISSION_GRANTED) {
            openGallery();
        }
        else
            stringActivityResultLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE);

    }

    private final ActivityResultLauncher<String> stringActivityResultLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted  ->
            {
                if (isGranted ){
                    openGallery();
                }
                else {
                    Log.d("Loox", "Khong vao duoc");
                }
            });
    private void openGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        mActivityResultLauncher.launch(Intent.createChooser(intent, "Select Picture"));
    }
    private final ActivityResultLauncher<Intent> mActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        if (data == null) {
                            return;
                        }
                        Uri uri = data.getData();
                        try {
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(requireContext().getContentResolver(), uri);
                            circleImageView.setImageBitmap(bitmap);
                            returnSelectedImage(uri);
                        }catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
    );
}