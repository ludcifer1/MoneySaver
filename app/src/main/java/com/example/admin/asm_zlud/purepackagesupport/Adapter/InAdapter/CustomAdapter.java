package com.example.admin.asm_zlud.purepackagesupport.Adapter.InAdapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


import com.example.admin.asm_zlud.R;
import com.example.admin.asm_zlud.purepackagesupport.Model.InModel;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<InModel> {

    ///////////////////////////////////
    //CUSTOM VARIABLES CHANGE IN USE//
    /////////////////////////////////
    List<InModel> arrayInModel;
    int resource;
    Activity myContext;
    ListView listView;
    View note;
    //////////////////////////////////////
    //THIS CUSTOM ADAPTER IS USING FOR //
    //LISTVIEW----------------GRIDVIEW//
    ///////////////////////////////////

    //Constructor
    //////////////////////////////////

//    public CustomAdapter(@NonNull Activity context, int resource, @NonNull List<InModel> objects, ArrayList<InModel> arrayInModel, int resource1, Activity myContext) {
//        super(context, resource, objects);
//        this.arrayInModel = arrayInModel;
//        this.resource = resource1;
//        this.myContext = myContext;
//    }

    public CustomAdapter(@NonNull Activity context, int resource, @NonNull List<InModel> objects) {
        super(context, resource, objects);
        myContext=context;
        this.resource=resource;
        arrayInModel=objects;
    }


    ///////////////////////////////////////////////////////////////////////
    //Overide this getView method to get the convertView
    //Use the convertView to interact with the elements in CustomView Item
    ///////////////////////////////////////////////////////////////////////


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = myContext.getLayoutInflater();
        convertView = inflater.inflate(resource, null);
        /////////////////////////////////////////////////
        //Create Temp from DataModel to put on Custom Item
        /////////////////////////////////////////////////
        InModel temp= arrayInModel.get(position);
        ////////////////////////////////////////////////
        TextView tvAmount=convertView.findViewById(R.id.tvMoneyIn);
        TextView tvDate=convertView.findViewById(R.id.tvDateIn);
        TextView tvType=convertView.findViewById(R.id.tvReasonIn);
        TextView tvNote=convertView.findViewById(R.id.tvNoteIncome);

        ///////////////////////////////////////////////

        ///////////////////////////////////////////////
        //Set Data to Item
        //////////////////////////////////////////////
        tvAmount.setText(temp.getAmount().toString());
        tvDate.setText(temp.getDate());
        tvType.setText(temp.getType().toString());
        tvNote.setText(temp.getNote());
        return convertView;
    }
    //////////////////////////////////////////////////////////////////////////////

}
