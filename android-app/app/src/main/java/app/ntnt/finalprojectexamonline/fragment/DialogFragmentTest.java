package app.ntnt.finalprojectexamonline.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.DialogFragment;

import app.ntnt.finalprojectexamonline.R;

public class DialogFragmentTest extends DialogFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Thiết lập kiểu hiển thị cho DialogFragment
        setStyle(DialogFragment.STYLE_NORMAL, R.style.MyDialogStyle);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Tạo một view mới và thiết lập nó làm nội dung của dialog
        View view = inflater.inflate(R.layout.activity_login, container, false);
        return view;
    }
}
