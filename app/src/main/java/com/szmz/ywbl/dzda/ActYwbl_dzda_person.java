package com.szmz.ywbl.dzda;

import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.szmz.R;
import com.szmz.entity.YwblPerson;
import com.szmz.utils.GetData;
import com.szmz.ywbl.ActBaseList;
import com.szmz.ywbl.ActDchs;
import com.szmz.ywbl.ActMzpy;
import com.szmz.ywbl.ActRhcc;
import com.szmz.ywbl.ActShgs;
import com.szmz.ywbl.ActSpgs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * 救助人列表
 */
public class ActYwbl_dzda_person extends ActBaseList<YwblPerson> {
    @BindView(R.id.dsNameTv)
    TextView dsNameTv;
    @BindView(R.id.dsLayout)
    LinearLayout dsLayout;
    private ArrayList<String> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();
    private OptionsPickerView pvOptions;
    private Map<Integer, Boolean> map;
    private int type = 0;

    @Override
    public void initUI() {
        super.initUI();
        setLeftVisible(true);
        setRightVisible(true);
        setRightShow("保存");
        type = getIntent().getIntExtra("Type", 0);
        switch (type) {
            case 0:
                setTitle("电子档案");
                break;
            case 1:
                setTitle("调查核实");
                break;
            case 2:
                setTitle("民主评议");
                break;
            case 3:
                setTitle("审核公示");
                break;
            case 4:
                setTitle("入户抽查");
                break;
            case 5:
                setTitle("审批公示");
                break;
            case 10:
                setTitle("救助对象信息");
                break;
        }


        map = new HashMap<>();
        initOptionPicker();
        dsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pvOptions.show();
            }
        });
        refresh.autoRefresh();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_act_ywbl_dzda_person;
    }


    @Override
    protected void doRefreshView(int p, final YwblPerson item, View view) {
        TextView nameTv = (TextView) view.findViewById(R.id.nameTv);
        TextView timeTv = (TextView) view.findViewById(R.id.timeTv);
        TextView countyTv = (TextView) view.findViewById(R.id.countyTv);
        TextView typeTv = (TextView) view.findViewById(R.id.typeNameTv);
        CheckBox cb = (CheckBox) view.findViewById(R.id.cb);
        nameTv.setText(item.getName());
        timeTv.setText(item.getTime());
        countyTv.setText(item.getCounty());
        typeTv.setText(item.getTypeName());

        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    map.put(item.getId(), isChecked);
                } else {
                    map.remove(item.getId());
                }
            }
        });

        if (map != null && map.containsKey(item.getId())) {
            cb.setChecked(map.get(item.getId()));
        } else {
            cb.setChecked(false);
        }
    }

    @Override
    protected int getListItemID() {
        return R.layout.ywbl_person_item;
    }

    @Override
    protected void doListItemOnClick(YwblPerson item) {
        super.doListItemOnClick(item);
        Intent intent;
        switch (type) {
            case 0:
                intent = new Intent(this, ActYwbl_dzda_main.class);
                intent.putExtra("YwblPerson", item);
                startActivity(intent);
                break;
            case 1:
                intent = new Intent(this, ActDchs.class);
                intent.putExtra("YwblPerson", item);
                startActivity(intent);
                break;
            case 2:
                intent = new Intent(this, ActMzpy.class);
                intent.putExtra("YwblPerson", item);
                startActivity(intent);
                break;
            case 3:
                intent = new Intent(this, ActShgs.class);
                intent.putExtra("YwblPerson", item);
                startActivity(intent);
                break;
            case 4:
                intent = new Intent(this, ActRhcc.class);
                intent.putExtra("YwblPerson", item);
                startActivity(intent);
                break;
            case 5:
                intent = new Intent(this, ActSpgs.class);
                intent.putExtra("YwblPerson", item);
                startActivity(intent);
                break;
            case 10:
                intent = new Intent(this, ActYwbl_dzda_main.class);
                intent.putExtra("YwblPerson", item);
                startActivity(intent);
                break;
        }

    }

    @Override
    protected void doMore(boolean isMore) {
        if (isMore) {
            CurrentPage++;
        } else {
            CurrentPage = 1;
        }
        refresh.finishRefresh();
        refresh.finishRefreshLoadMore();
        List<YwblPerson> result = GetData.doGetPersonList();
        if (result != null && result.size() > 0) {
            if (CurrentPage == 1) {
                adapter.clearListData();
            }

            adapter.setListData(result);
            adapter.notifyDataSetChanged();
            noDataLayout.setVisibility(View.GONE);
            if (isHasNextPage(CurrentPage, PageSize, 40)) {
                refresh.setLoadMore(true);
            } else {
                refresh.setLoadMore(false);
            }
        } else {
            adapter.clearListData();
            adapter.setListData(new ArrayList<YwblPerson>());
            adapter.notifyDataSetChanged();
            noDataLayout.setVisibility(View.VISIBLE);
        }
    }

    private void initOptionData() {

        /**
         * 注意：如果是添加JavaBean实体数据，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
         */

        //选项1
        options1Items.add("广东");
        options1Items.add("湖南");
        options1Items.add("广西");

        //选项2
        ArrayList<String> options2Items_01 = new ArrayList<>();
        options2Items_01.add("广州");
        options2Items_01.add("佛山");
        options2Items_01.add("东莞");
        options2Items_01.add("珠海");
        ArrayList<String> options2Items_02 = new ArrayList<>();
        options2Items_02.add("长沙");
        options2Items_02.add("岳阳");
        options2Items_02.add("株洲");
        options2Items_02.add("衡阳");
        ArrayList<String> options2Items_03 = new ArrayList<>();
        options2Items_03.add("桂林");
        options2Items_03.add("玉林");
        options2Items.add(options2Items_01);
        options2Items.add(options2Items_02);
        options2Items.add(options2Items_03);


        ArrayList<ArrayList<String>> options2Items_012 = new ArrayList<>();
        ArrayList<String> options3Items_01 = new ArrayList<>();
        ArrayList<String> options3Items_02 = new ArrayList<>();
        ArrayList<String> options3Items_03 = new ArrayList<>();
        ArrayList<String> options3Items_04 = new ArrayList<>();
        options3Items_01.add("广州01");
        options3Items_01.add("佛山01");
        options3Items_01.add("东莞01");
        options3Items_01.add("珠海01");

        options3Items_02.add("广州02");
        options3Items_02.add("佛山02");
        options3Items_02.add("东莞02");
        options3Items_02.add("珠海02");

        options3Items_03.add("广州03");
        options3Items_03.add("佛山03");
        options3Items_03.add("东莞03");
        options3Items_03.add("珠海03");

        options3Items_04.add("广州04");
        options3Items_04.add("佛山04");
        options3Items_04.add("东莞04");
        options3Items_04.add("珠海04");

        options2Items_012.add(options3Items_01);
        options2Items_012.add(options3Items_02);
        options2Items_012.add(options3Items_03);
        options2Items_012.add(options3Items_04);

        options3Items.add(options2Items_012);
        options3Items.add(options2Items_012);
        options3Items.add(options2Items_012);
        options3Items.add(options2Items_012);
        /*--------数据源添加完毕---------*/
    }

    private void initOptionPicker() {//条件选择器初始化
        initOptionData();
        /**
         * 注意 ：如果是三级联动的数据(省市区等)，请参照 JsonDataActivity 类里面的写法。
         */

        pvOptions = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = options1Items.get(options1)
                        + options2Items.get(options1).get(options2)
                        + options3Items.get(options1).get(options2).get(options3);
                dsNameTv.setText(options3Items.get(options1).get(options2).get(options3));
            }
        })
                .setTitleText("行政区划")
                .setDividerColor(Color.LTGRAY)//设置分割线的颜色
                .setSelectOptions(0, 1)//默认选中项
                .setTextColorCenter(Color.LTGRAY)
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .build();

        pvOptions.setPicker(options1Items, options2Items, options3Items);//三级选择器

    }


}
