package pl.cydo.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ExpandableListView;
import pl.cydo.model.ServicePointCategory;
import pl.cydo.repository.CategoryRepository;
import pl.cydo.model.CategoriesTreeModel;
import pl.cydo.view.components.CategoryAdapter;
import pl.jcygan.android.R;

import java.util.LinkedList;
import java.util.List;

public class CategoryChooseDialog extends DialogFragment {

    List<String> categories;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        View view = inflater.inflate(R.layout.category_choose, null);
        builder.setView(view);

        Dialog dialog =builder.create();
        ExpandableListView categoryView = (ExpandableListView) view.findViewById(R.id.listView);
        List<ServicePointCategory> list = new LinkedList();
        list.add(CategoryRepository.getINSTANCE().getCategories());
        CategoryAdapter adapter = new CategoryAdapter(getActivity() ,list, dialog);
//        adapter.add(CategoryRepository.getINSTANCE().getCategories());
        categoryView.setAdapter(adapter);
        adapter.setRoot(true);

        categoryView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,int groupPosition, int childPosition, long id) {
                System.out.println("onChildClick");
                return true;
            }
        });

        return dialog;
    }
}
