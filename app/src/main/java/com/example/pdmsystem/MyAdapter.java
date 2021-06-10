package com.example.pdmsystem;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{



    private Context context;
    private List<Upload> uploadList;
    private OnItemClickListener listener;


    public MyAdapter(Context context, List<Upload> uploadList) {
        this.context = context;
        this.uploadList = uploadList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        LayoutInflater layoutInflater= LayoutInflater.from(context);
        View view=layoutInflater.inflate(R.layout.sample_layout,parent,false);
        return new  MyViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {



        Upload upload=uploadList.get(position);
        holder.textView1.setText("Location: "+upload.getLocation());
        holder.textView2.setText("Rent: "+upload.getRentAmount());
        holder.textView3.setText("Contact Number: "+upload.getPhoneNumber());
        Picasso.with(context)
                .load(upload.getImageuri())
                .placeholder(R.mipmap.ic_launcher_round)
                .fit()
                .centerCrop()
                .into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return uploadList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener , View.OnCreateContextMenuListener , MenuItem.OnMenuItemClickListener {
        ImageView imageView;
        TextView textView1,textView2,textView3;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView=itemView.findViewById(R.id.card_imageview_id);
            textView1=itemView.findViewById(R.id.card_textview_id1);
            textView2=itemView.findViewById(R.id.card_textview_id2);
            textView3=itemView.findViewById(R.id.card_textview_id3);
            itemView.setOnClickListener(this);
            itemView.setOnCreateContextMenuListener(this);


        }

        @Override
        public void onClick(View v) {

            if(listener!=null)
            {
                int position = getAdapterPosition();

                if(position!=RecyclerView.NO_POSITION)
                {
                    listener.onItemClick(position);
                }

            }

        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {


            menu.setHeaderTitle("Choose an action");
            MenuItem doAnyTask = menu.add(Menu.NONE,1,1,"do any task");
            MenuItem delete = menu.add(Menu.NONE,2,2,"delete");


            doAnyTask.setOnMenuItemClickListener(this);
            delete.setOnMenuItemClickListener(this);



        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {



            if(listener!=null)
            {
                int position = getAdapterPosition();

                if(position!=RecyclerView.NO_POSITION)
                {
                   switch (item.getItemId())
                   {
                       case 1:

                           listener.onDoAnyTask(position);
                           return true;

                       case 2:
                           listener.onDelete(position);
                           return true;
                   }
                }

            }

            return false;
        }
    }
 public interface OnItemClickListener{
        void onItemClick(int position);
        void onDoAnyTask(int position);
        void onDelete(int position);

 }
    public void setOnItemClickListener(OnItemClickListener listener){


        this.listener = listener;

    }


}
