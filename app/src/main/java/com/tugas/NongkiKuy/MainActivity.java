package com.tugas.NongkiKuy;

        import androidx.appcompat.app.AppCompatActivity;
        import androidx.recyclerview.widget.GridLayoutManager;
        import androidx.recyclerview.widget.RecyclerView;

        import android.app.Activity;
        import android.graphics.Color;
        import android.os.Build;
        import android.os.Bundle;
        import android.text.format.DateFormat;
        import android.view.View;
        import android.content.Intent;
        import android.view.Window;
        import android.view.WindowManager;
        import android.widget.TextView;

        import com.tugas.NongkiKuy.Decoration.LayoutMarginDecoration;
        import com.tugas.NongkiKuy.Model.ModelMain;
        import com.tugas.NongkiKuy.R;
        import com.tugas.NongkiKuy.Adapter.MainAdapter;
        import com.tugas.NongkiKuy.Utils.Tools;


        import java.util.ArrayList;
        import java.util.Calendar;
        import java.util.Date;
        import java.util.List;

public class MainActivity extends AppCompatActivity implements MainAdapter.onSelectData {

    RecyclerView rvMainMenu;
    LayoutMarginDecoration gridMargin;
    ModelMain mdlMainMenu;
    List<ModelMain> lsMainMenu = new ArrayList<>();
    TextView tvToday;
    String hariIni;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility
                    (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        tvToday = findViewById(R.id.tvDate);
        rvMainMenu = findViewById(R.id.rvMainMenu);
        GridLayoutManager mLayoutManager = new GridLayoutManager(this, 2,
                RecyclerView.VERTICAL, false);
        rvMainMenu.setLayoutManager(mLayoutManager);
        gridMargin = new LayoutMarginDecoration(2, Tools.dp2px(this, 4));
        rvMainMenu.addItemDecoration(gridMargin);
        rvMainMenu.setHasFixedSize(true);

        //get Time Now
        Date dateNow = Calendar.getInstance().getTime();
        hariIni = (String) DateFormat.format("EEEE", dateNow);
        getToday();
        setMenu();
    }

    private void getToday() {
        Date date = Calendar.getInstance().getTime();
        String tanggal = (String) DateFormat.format("d MMMM yyyy", date);
        String formatFix = hariIni + ", " + tanggal;
        tvToday.setText(formatFix);
    }

    private void setMenu() {
        mdlMainMenu = new ModelMain("Daerah", R.drawable.mapp);
        lsMainMenu.add(mdlMainMenu);
        mdlMainMenu = new ModelMain("Cafe", R.drawable.restaurant);
        lsMainMenu.add(mdlMainMenu);
        mdlMainMenu = new ModelMain("Review", R.drawable.ic_komunitas);
        lsMainMenu.add(mdlMainMenu);
        mdlMainMenu = new ModelMain("About", R.drawable.question);
        lsMainMenu.add(mdlMainMenu);

        MainAdapter myAdapter = new MainAdapter(lsMainMenu, this);
        rvMainMenu.setAdapter(myAdapter);

    }

    @Override
    public void onSelected(ModelMain mdlMain) {
        switch (mdlMain.getTxtName()) {
            case "Daerah":
                startActivityForResult(new Intent(MainActivity.this, DaerahActivity.class), 1);
                break;
            case "Cafe":
                startActivityForResult(new Intent(MainActivity.this, CafeActivity.class), 1);
                break;
            case "Makanan":
                startActivityForResult(new Intent(MainActivity.this, MakananActivity.class), 1);
                break;
            case "About":
                startActivityForResult(new Intent(MainActivity.this, AboutActivity.class), 1);
                break;
        }
    }

    //set Transparent Status bar
    public static void setWindowFlag(Activity activity, final int bits, boolean on) {

        Window win = activity.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }
}