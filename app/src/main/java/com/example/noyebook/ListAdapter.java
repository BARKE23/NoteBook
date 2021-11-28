package com.example.noyebook;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

class ViewHolder{   //存放控件，findViewById耗时
    public ImageView itemIcon;
    public TextView itemNoteTitle;
    public TextView itemNoteDate;

    View itemView;

    public ViewHolder(View itemView) {
        if (itemView == null){
            throw new IllegalArgumentException("item View can not be null!");
        }
        this.itemView = itemView;
        itemIcon = itemView.findViewById(R.id.rand_icon);
        itemNoteTitle = itemView.findViewById(R.id.item_note_title);
        itemNoteDate = itemView.findViewById(R.id.item_note_date);

    }
}

public class ListAdapter extends BaseAdapter {
    private List<NoteInfo> noteList;
    private LayoutInflater layoutInflater;//解析xml布局
    private Context context;
    private ViewHolder holder = null;

    public ListAdapter(Context context,List<NoteInfo> noteList) {
        this.noteList = noteList;
        this.context = context;

        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return noteList.size();
    }

    @Override
    public Object getItem(int position) {
        return noteList.get(position).getTitle();
    }

    //获取条目的id
    @Override
    public long getItemId(int position) {
        return Long.parseLong(noteList.get(position).getId());
    }

    public void remove(int index){
        noteList.remove(index);
    }

    public void refreshDataSet(){
        notifyDataSetChanged();
    }

    //获取该条目要显示的界面
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){  //第一次创建convertView
            convertView = layoutInflater.inflate(R.layout.item_layout,null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }
        else {//第二次重用convertView
            holder = (ViewHolder)convertView.getTag();
        }
        holder.itemIcon.setImageBitmap(BitmapFactory.decodeResource(
                context.getResources(),R.drawable.note));
        holder.itemNoteTitle.setText(noteList.get(position).getTitle());
        holder.itemNoteDate.setText(noteList.get(position).getDate());
        return convertView;
    }
}
