package reagodjj.example.com.viewpagerdemo.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import reagodjj.example.com.viewpagerdemo.R;

public class TestFragment extends Fragment {
    private static final String TITLE = "title";
    private String title;

    public static TestFragment newInstance(String title) {
        TestFragment testFragment = new TestFragment();
        Bundle bundle = new Bundle();
        bundle.putString(TITLE, title);
        testFragment.setArguments(bundle);
        return testFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            title = getArguments().getString(TITLE, "error");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        @SuppressLint("InflateParams")
        View view = inflater.inflate(R.layout.fragment_test, null);
        TextView tvShow = view.findViewById(R.id.tv_show);
        tvShow.setText(title);
        return view;
    }
}
