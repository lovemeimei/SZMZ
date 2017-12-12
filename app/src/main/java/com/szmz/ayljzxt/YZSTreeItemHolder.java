package com.szmz.ayljzxt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.johnkil.print.PrintView;
import com.szmz.R;
import com.szmz.entity.YZS_xzqh;
import com.szmz.utils.ImageUtil;
import com.unnamed.b.atv.model.TreeNode;

import java.util.List;

/**
 * Created by bz on 2017/11/8.
 */

public class YZSTreeItemHolder extends TreeNode.BaseNodeViewHolder<YZSTreeItemHolder.TreeItem> {
    private TextView tvValue;
    private PrintView arrowView;
    private Context context;
    private YZSTreeItemHolder.OnClickChildListener listener;

    public YZSTreeItemHolder(Context context, YZSTreeItemHolder.OnClickChildListener listener) {
        super(context);
        this.context = context;
        this.listener = listener;
    }


    @Override
    public View createNodeView(final TreeNode node, final YZSTreeItemHolder.TreeItem value) {
        final LayoutInflater inflater = LayoutInflater.from(context);
        final View view = inflater.inflate(R.layout.tree_node_item, null, false);
        tvValue = (TextView) view.findViewById(R.id.tv_name);
        tvValue.setText(value.text);
        arrowView = (PrintView) view.findViewById(R.id.arrow_icon);
        LinearLayout layout = (LinearLayout) view.findViewById(R.id.rootView);
        TextView button = (TextView) view.findViewById(R.id.chose);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.doClick(value.item);
                }
            }
        });
        layout.setPadding(node.getLevel() * ImageUtil.dip2px(context, 10) + ImageUtil.dip2px(context, 10), 0, 0, 0);
        List<TreeNode> children = node.getChildren();
        if (children == null || children.size() < 1) {
            arrowView.setVisibility(View.GONE);
            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (listener != null) {
                        listener.doClick(value.item);
                    }
                }
            });
        }
        return view;
    }

    @Override
    public void toggle(boolean active) {
        arrowView.setIconText(context.getResources().getString(active ? R.string.ic_keyboard_arrow_down : R.string.ic_keyboard_arrow_right));
    }

    public static class TreeItem {
        public String text;
        public YZS_xzqh item;

        public TreeItem(YZS_xzqh item) {
            this.text = item.getName();
            this.item = item;
        }
    }

    public interface OnClickChildListener {
        void doClick(YZS_xzqh item);
    }
}

