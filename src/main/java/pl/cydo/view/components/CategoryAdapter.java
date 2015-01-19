package pl.cydo.view.components;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import pl.cydo.model.CategoriesTreeModel;
import pl.cydo.model.ServicePointCategory;
import pl.jcygan.android.R;

import java.util.LinkedList;
import java.util.List;

public class CategoryAdapter extends BaseExpandableListAdapter {
    private List<ServicePointCategory> values = new LinkedList<ServicePointCategory>();
    private final Activity context;
    private boolean isRoot=false;
    private int padding = 150;
    private final Dialog dialog;

    public CategoryAdapter(Activity context, List<ServicePointCategory> values, Dialog dialog) {
        this.context = context;
        this.values = values;
        this.dialog= dialog;
    }

    public CategoryAdapter(Activity context, ServicePointCategory value, Dialog dialog) {
        this.context = context;
        this.values.add(value);
        this.dialog= dialog;
    }

    public void setPadding(int padding) {
        this.padding = padding;
    }

    public void setRoot(boolean isRoot) {
        this.isRoot = isRoot;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return values.get(groupPosition).getChildren().get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return values.get(groupPosition).getChildren().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return values.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return values.size();
    }

    @Override
    public void onGroupCollapsed(int groupPosition) {
        super.onGroupCollapsed(groupPosition);
    }

    @Override
    public void onGroupExpanded(int groupPosition) {
        super.onGroupExpanded(groupPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }


    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflator = context.getLayoutInflater();
            convertView = inflator.inflate(R.layout.category_list_element, null);
        }

        LayoutInflater inflater = (LayoutInflater) convertView.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rootView = inflater.inflate(R.layout.category_list_element, parent, false);
        final String elementText=values.get(groupPosition).getText();
        ((TextView) rootView.findViewById(R.id.textView1)).setText(elementText);
        ((TextView) rootView.findViewById(R.id.textView1)).setPadding(padding/2,0,0,0);

        ((Button) rootView.findViewById(R.id.button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button button = (Button) context.findViewById(R.id.category_choose);
                button.setText(elementText);
                dialog.dismiss();
            }
        });

        return rootView;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflator = context.getLayoutInflater();
            convertView = inflator.inflate(R.layout.category_list_child, null);
        }

        ServicePointCategory child = values.get(groupPosition).getChildren().get(childPosition);
        CustExpListview rootView = new CustExpListview(convertView.getContext());
        CategoryAdapter adapter = new CategoryAdapter(context, child, dialog);
        adapter.setPadding(padding+CustExpListview.childSuperLeftPadding);

        rootView.setAdapter(adapter);
        if(child.getChildren().isEmpty()) {
            rootView.setGroupIndicator(null);
        }

        rootView.setIndicatorBounds( padding,0);

        return rootView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    class CustExpListview extends ExpandableListView {
        private static final int childSuperLeftPadding=100;

        public CustExpListview(Context context) {
            super(context);
        }

        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(600, MeasureSpec.AT_MOST);
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }
}


