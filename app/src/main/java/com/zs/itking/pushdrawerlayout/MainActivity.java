package com.zs.itking.pushdrawerlayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.zs.itking.pushdrawerlayout.behavior.BottomNavigation.BottomNavigationViewHelper;

public class MainActivity extends AppCompatActivity {

    private static final String TAG ="MainActivity";
    private DrawerLayout drawer_layout;
    private Toolbar toolbar;
    private FloatingActionButton floating_action_btn;

    //底部导航
    private BottomNavigationView bottom_navigation_main;
    //抽屉导航
    private NavigationView nav_view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //浮动按钮
        floating_action_btn=findViewById(R.id.floating_action_btn);
        //点击悬浮按钮返回顶部
        floating_action_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(floating_action_btn, "点宝宝干啥", Snackbar.LENGTH_SHORT).show();

            }
        });
        //标题栏
        toolbar = findViewById(R.id.toolbar);
        //设置Toolbar标题
        toolbar.setTitle("");
        //侧滑
        drawer_layout = findViewById(R.id.drawer_layout);
        //引入自定义Toolbar
        setSupportActionBar(toolbar);
        //是否打开标题
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        /**
         * 推动DrawerLayout主布局+隐藏布局
         */
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                //获取mDrawerLayout中的第一个子布局，也就是布局中的RelativeLayout
                //获取抽屉的view
                View mContent = drawer_layout.getChildAt(0);
                float scale = 1 - slideOffset;
                float endScale = 0.8f + scale * 0.2f;
                float startScale = 1 - 0.3f * scale;
                //设置左边菜单滑动后的占据屏幕大小
                drawerView.setScaleX(startScale);
                drawerView.setScaleY(startScale);
                //设置菜单透明度
                drawerView.setAlpha(0.6f + 0.4f * (1 - scale));
                //设置内容界面水平和垂直方向偏转量
                //在滑动时内容界面的宽度为 屏幕宽度减去菜单界面所占宽度
                mContent.setTranslationX(drawerView.getMeasuredWidth() * (1 - scale));
                //设置内容界面操作无效（比如有button就会点击无效）
                mContent.invalidate();
                //设置右边菜单滑动后的占据屏幕大小
                mContent.setScaleX(endScale);
                mContent.setScaleY(endScale);
            }
        };
        toggle.syncState();
        drawer_layout.addDrawerListener(toggle);

        //去掉BottomNavigationView的动画效果
        bottom_navigation_main=findViewById(R.id.bottom_navigation_main);
        //BottomNavigationViewHelper.disableShiftMode(bottom_navigation_main);
        //BottomNavigationViewHelper.removeNavigationShiftMode(bottom_navigation_main);
        //bottom_navigation_main.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED);
        /**
         * 底部导航BottomNavigationView添加点击事件
         */
        bottom_navigation_main.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.item_homepage: {
                        Toast.makeText(MainActivity.this, "homepage", Toast.LENGTH_SHORT).show();
                        return true;
                    }
                    case R.id.item_classification:{
                        Toast.makeText(MainActivity.this, "classification", Toast.LENGTH_SHORT).show();
                        return true;
                    }
                    case R.id.item_sign_up: {
                        Toast.makeText(MainActivity.this, "item_sign_up", Toast.LENGTH_SHORT).show();
                        return true;
                    }
                    case R.id.item_my: {
                        Toast.makeText(MainActivity.this, "item_my", Toast.LENGTH_SHORT).show();
                        return true;
                    }
                }
                return false;
            }

        });

        /**
         * 给DrawerLayout侧滑的Navigation导航菜单添加点击事件
         */
        //侧滑抽屉菜单
        nav_view=findViewById(R.id.nav_view);
        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.drawer_menu_school:
                        Toast.makeText(MainActivity.this, "我的School", Toast.LENGTH_SHORT).show();
                        drawer_layout.closeDrawers();//关闭侧滑
                        return true;
                    case R.id.drawer_menu_my_collection:
                        Toast.makeText(MainActivity.this, "我的收藏", Toast.LENGTH_SHORT).show();
                        drawer_layout.closeDrawers();//关闭侧滑
                        return true;
                    case R.id.drawer_menu_setting:

                        Toast.makeText(MainActivity.this, "我的stetting", Toast.LENGTH_SHORT).show();

                        return true;
                    case R.id.drawer_menu_about_we:
                        Toast.makeText(MainActivity.this, "关于我们", Toast.LENGTH_SHORT).show();
                        drawer_layout.closeDrawers();//关闭侧滑
                        return true;
                    case R.id.drawer_menu_logout:
                        drawer_layout.closeDrawers();//关闭侧滑
                        return true;
                }
                return false;
            }
        });
        //浮动按钮
        floating_action_btn=findViewById(R.id.floating_action_btn);
        //点击悬浮按钮返回顶部
        floating_action_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(floating_action_btn, "点宝宝干啥", Snackbar.LENGTH_SHORT).show();

            }
        });
   }

}