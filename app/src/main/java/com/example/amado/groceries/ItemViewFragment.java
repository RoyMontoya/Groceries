package com.example.amado.groceries;

import android.content.Intent;
import android.graphics.Bitmap;
import android.hardware.camera2.CameraDevice;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.NavUtils;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.io.FileNotFoundException;
import java.io.IOException;


/**
 * A placeholder fragment containing a simple view.
 */
public class ItemViewFragment extends android.app.Fragment implements AdapterView.OnItemSelectedListener, View.OnClickListener{

    private static final String TAG = "ItemViewFragment";
    public static final String INDEX = "array_index";
    private static Item mItem;
    private int mPosition;
    private static final int CAMERA_REQUEST=0;
    private static boolean isNew;
    public static boolean nameEmpty= false;
    private ImageView mPhotoPreview;
    private boolean hasPhoto;


    public ItemViewFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        getActivity().setTitle(R.string.grocery_info);
        Bundle args =getActivity().getIntent().getExtras();
        if(args == null){
            mItem = new Item();
            isNew= true;
            hasPhoto= false;
        }else{
            mPosition = args.getInt(GroceriesListFragment.POSITION);
            mItem = GroceriesStore.getGroceries().get(mPosition);
            isNew= false;
            if(mItem.getPhoto()!=null){
                hasPhoto= true;
            }else{
                hasPhoto =false;
            }
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=  inflater.inflate(R.layout.fragment_item, container, false);

        mPhotoPreview =(ImageView)v.findViewById(R.id.photo_item_preview);
        mPhotoPreview.setOnClickListener(this);
        if(hasPhoto) {
            Picasso.with(getActivity()).load(mItem.getPhoto()).into(mPhotoPreview);
        }
        Spinner unitsSpinner =(Spinner) v.findViewById(R.id.spinner_units);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.units_options,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        unitsSpinner.setAdapter(adapter);
        if(!mItem.getUnit().isEmpty()){
            int spinnerPosition = adapter.getPosition(mItem.getUnit());
            unitsSpinner.setSelection(spinnerPosition);
        }
        unitsSpinner.setOnItemSelectedListener(this);


        EditText nameEdit = (EditText)v.findViewById(R.id.name_editText);
        nameEdit.setText(mItem.getName());
        checkForEmptyName(nameEdit.getText().toString());
        nameEdit.addTextChangedListener(new ChangeTextWatcher(nameEdit));
        EditText qtyEdit = (EditText)v.findViewById(R.id.qty_editText);
        qtyEdit.setText(String.valueOf(mItem.getQuantity()));
        qtyEdit.addTextChangedListener(new ChangeTextWatcher(qtyEdit));
        EditText notesEdit = (EditText)v.findViewById(R.id.notes_editText);
        notesEdit.setText(mItem.getNotes());
        notesEdit.addTextChangedListener(new ChangeTextWatcher(notesEdit));
        final CheckBox doneCheckbox = (CheckBox)v.findViewById(R.id.item_checkBox);
        doneCheckbox.setChecked(mItem.isChecked());
        doneCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mItem.setChecked(doneCheckbox.isChecked());
            }
        });

        ImageButton cameraButton = (ImageButton)v.findViewById(R.id.camera_button);
        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if(hasPhoto){
                    mItem.getPhoto().delete();
                }
                mItem.createFile();
                i.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(mItem.getPhoto()));
                startActivityForResult(i, CAMERA_REQUEST);
            }
        });

        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode== CAMERA_REQUEST){
            try{
                Picasso.with(getActivity()).load(mItem.getPhoto()).into(mPhotoPreview);
                hasPhoto=true;
            }catch (Exception e){
                hasPhoto= false;
            }
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        mItem.setUnit(parent.getItemAtPosition(position).toString());

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        if(mItem.getUnit().isEmpty()) {
            mItem.setUnit(parent.getItemAtPosition(0).toString());
        }
    }

    public static void saveItem(){
        if(isNew){
            GroceriesListFragment.mDataSource.createItem(mItem);
        }else{
            GroceriesListFragment.mDataSource.updateItem(mItem);
        }
    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setDataAndType(Uri.fromFile(mItem.getPhoto()), "image/jpeg");
        startActivity(i);
    }


    private class ChangeTextWatcher implements TextWatcher{
        private View view;

        private ChangeTextWatcher(View v){
            this.view = v;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            String text = s.toString();
            switch (view.getId()){
                case R.id.name_editText:
                    checkForEmptyName(text);
                    mItem.setName(text);
                    break;
                case R.id.qty_editText:
                    if(text.isEmpty()){
                       return;
                    }
                    mItem.setQuantity(Integer.valueOf(text));
                    break;
                case R.id.notes_editText:
                    mItem.setNotes(text);
                    break;
            }
        }
    }

    public void checkForEmptyName(String name){
        if(name.isEmpty()||name==null){
            nameEmpty= true;
        }else{
            nameEmpty =false;
        }
    }

}
