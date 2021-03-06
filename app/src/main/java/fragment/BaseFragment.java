package fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.haiyuan1995.fashiongo.R;


/**
 * Fragment的基类
 */

public abstract class BaseFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);//在fragmnent中使用菜单需要加这句话

    }



    abstract void initData();


    public static void initToolbar(Toolbar toolbar, Activity activity) {
        if (toolbar != null) {
            toolbar.setTitle(" ");
            toolbar.findViewById(R.id.id_toolbar_searchLayout).setVisibility(View.GONE);
            ((AppCompatActivity) activity).setSupportActionBar(toolbar);
        }
    }
}
