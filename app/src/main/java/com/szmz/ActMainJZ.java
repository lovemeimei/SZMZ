package com.szmz;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

import com.barcode.decoding.Intents;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.szmz.entity.CommMsgSave;
import com.szmz.entity.ScanCode;
import com.szmz.entity.User;
import com.szmz.entity.request.Comm_msg_req;
import com.szmz.entity.response.Comm_msg_Res;
import com.szmz.fragment.FragmentHome;
import com.szmz.fragment.FragmentJob;
import com.szmz.fragment.FragmentSearch;
import com.szmz.fragment.FragmentStatistical;
import com.szmz.fragment.FragmentUser;
import com.szmz.more.ActCodeLogin;
import com.szmz.net.ApiUtil;
import com.szmz.net.SimpleApiListener;
import com.szmz.utils.GsonUtil;
import com.szmz.utils.UIUtil;

import org.xutils.DbManager;
import org.xutils.ex.DbException;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import retrofit2.Call;

/**
 * 中移全通集成公司 版本所有
 * 创建人： 郄益轩
 * 创建时间：2017/9/5 0005上午 11:38
 */

public class ActMainJZ extends ActBase {


    @BindView(android.R.id.tabcontent)
    FrameLayout tabcontent;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(android.R.id.tabs)
    TabWidget tabs;
    @BindView(android.R.id.tabhost)
    FragmentTabHost tabhost;
    private List<Fragment> mFragmentList;
    private String mTextArray[] = {"tab1", "tab2", "tab3", "tab4", "tab5"};
    private Class[] mClass = {FragmentHome.class, FragmentSearch.class, FragmentJob.class, FragmentStatistical.class, FragmentUser.class};
    private Fragment mFragment[] = {new FragmentHome(), new FragmentSearch(), new FragmentJob(), new FragmentStatistical(), new FragmentUser()};
    int[] mDrawable = {R.drawable.slt_main_home_jz, R.drawable.slt_main_search_jz, R.drawable.slt_main_job_jz, R
            .drawable.slt_main_tj_jz, R.drawable.slt_main_user_jz};
    private String[] mTitles = {"首页", "查询", "办理", "统计", "我的"};

    @Override
    protected void initUI() {
        super.initUI();
        mFragmentList = new ArrayList<>();
        setTitle(mTitles[0]);

        tvTitleRight.setVisibility(View.VISIBLE);
        tvTitleRight.setTextSize(30);

        tabhost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);
        tabhost.getTabWidget().setDividerDrawable(null);

        for (int i = 0; i < mFragment.length; i++) {
            TabHost.TabSpec tabSpec = tabhost.newTabSpec(mTitles[i]).setIndicator(getTabView(i));
            tabhost.addTab(tabSpec, mClass[i], null);
            mFragmentList.add(mFragment[i]);
            tabhost.getTabWidget().getChildAt(i).setBackgroundColor(Color.WHITE);
        }

        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragmentList.get(position);
            }

            @Override
            public int getCount() {
                return mFragmentList.size();
            }
        });
        tabhost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                setTitle(mTitles[tabhost.getCurrentTab()]);
                viewPager.setCurrentItem(tabhost.getCurrentTab());
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setTitle(mTitles[position]);
                tabhost.setCurrentTab(position);
                if (position==0){

                    tvTitleRight.setVisibility(View.VISIBLE);
//                    tvTitleRight.setTextSize(getResources().getDimension(R.dimen.font_larger));

                    getList();
                }else {
                    tvTitleRight.setVisibility(View.GONE
                    );


                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        tvTitleRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scan();
            }
        });
    }

    private void getList(){

        User user = App.getInstance().getLoginUser();
        String idCard =user.getIdCode();
        if (idCard==null)
            idCard="";
        final Comm_msg_req req = new Comm_msg_req(user.getUserName(),user.getPhone(),"","2");

        Call<Comm_msg_Res> call = App.getApiProxyJZ().getMsg(req);

        ApiUtil<Comm_msg_Res> apiUtil = new ApiUtil<>(context,call,new SimpleApiListener<Comm_msg_Res>(){
            @Override
            public void doSuccess(Comm_msg_Res result) {
                super.doSuccess(result);
                 List<CommMsgSave> items = new ArrayList<>();
                items =result.Result;
                if (items!=null && items.size()>0){
                  DbManager dbManager = x.getDb(App.getDaoConfig());
                  for (CommMsgSave item:items){
                      try {
                          dbManager.save(item);
                      } catch (DbException e) {
                          e.printStackTrace();
                      }
                  }
                }
            }
        },false);

        apiUtil.excute();

    }

    private View getTabView(int index) {
        View view = LayoutInflater.from(this).inflate(R.layout.comm_tab_item, null);
        ImageView image = (ImageView) view.findViewById(R.id.image);
        image.setImageResource(mDrawable[index]);
        TextView text = (TextView) view.findViewById(R.id.text);
        text.setText(mTitles[index]);
        return view;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main_jz;
    }


    private void scan(){
        try{
            Intent intent = new Intent(Intents.Scan.ACTION);
            intent.putExtra(Intents.Scan.MODE, "QR_CODE_MODE");
            intent.putExtra(Intents.Scan.CHARACTER_SET, "GB2312");
            this.startActivityForResult(intent, REQUEST_CAPTURE);
        }catch (Exception e){
            e.printStackTrace();
            UIUtil.doToast("打开摄像头失败");
        }
    }

    private static final int REQUEST_CAPTURE = 1024;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        Logger.d("aa");
        if (requestCode==REQUEST_CAPTURE){
            Logger.d("bb");
            if (resultCode ==RESULT_OK){
                Logger.d("cc");
//                http://10.10.0.169:8080/jeecg/loginController.do?appQuest&uuid=75823&type=A001&SystemId=emRzaGJ6MTUwNzk2MTQyNjE1Nw==
                String resultStr = data.getStringExtra(Intents.Scan.RESULT);

                ScanCode code = GsonUtil.deser(resultStr,ScanCode.class);

                String account="";
                if (code.getSystemId().equals(SystemConst.SystemID_JZ)) {
                    account = getUser().getAccountJZ();
                }
                if (code.getSystemId().equals(SystemConst.SystemID_YZS)) {
                    account = getUser().getAccountYZS();

                }
                if (code.getSystemId().equals(SystemConst.SystemID_SH)) {
                    account = getUser().getAccountHD();

                }

                Intent intent = new Intent(context, ActCodeLogin.class);
//                intent.putExtra("uuid",code.getUuid());
                intent.putExtra("type","A001");
//                intent.putExtra("account",account);
                intent.putExtra("msg",resultStr);
                startActivity(intent);
            }
        }

        super.onActivityResult(requestCode,resultCode,data);


    }
}
