package com.example.responsible;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.responsible.database.AppDatabase;
import com.example.responsible.database.entity.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryListAdapter extends BaseAdapter {
    private List<Category> categories;
    private Context context;
    private View view;
    private ViewGroup viewGroup;
    private AppDatabase db;

    public CategoryListAdapter (List<Category> categories, Context context, AppDatabase db){
        this.categories=categories;
        this.context=context;
        this.db=db;
    }


    @Override
    public int getCount() {
        int size=categories.size();
        return  size;
    }

    @Override
    public Object getItem(int position) {
        return categories.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       //if(convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.category_list_item, parent, false);

       //}

        TextView categoryText=convertView.findViewById(R.id.categoryInList);
        ImageView deleteImageView=convertView.findViewById(R.id.DeleteImageView);

        Category category=(Category)getItem(position);
        categoryText.setText(category.name);


        deleteImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.categoryDao().delete(category);
                categories=db.categoryDao().getAll();
                notifyDataSetChanged();
            }
        });


        return convertView;

    }
}
