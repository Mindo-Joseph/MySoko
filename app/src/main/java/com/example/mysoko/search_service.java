package com.example.mysoko;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class search_service extends AppCompatActivity {
    ArrayList<itemModel> arrayList, arrayList1;
    RecyclerView recyclerView, recyclerView1;
    int icons[] = {
            R.drawable.motorolla,
            R.drawable.toyota,
            R.drawable.compant1,
            R.drawable.company2,
            R.drawable.company3,
            R.drawable.company4
    };
    String iconsName[] = {"Samson Ngige", "Steven Lamech", "Rob Kamau", "Sam Safari", "James Ochieng", "Adam Bor"};
    String rating[] = {"3.4", "4.3", "3.0", "3.0", "4.5", "4.0"};
    String price[] = {"1000Ksh/hr", "500Ksh/hr", "400Ksh/hr", "200ksh/hr", "200Ksh/hr", "3500Ksh/hr"};
    String service[] = {"Android App Developer", "Plumber", "Teacher", "Caregiver", "Web Developer", "lawyer"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_service);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView1 = (RecyclerView) findViewById(R.id.recycler_view1);

        arrayList = new ArrayList<>();
        arrayList1 = new ArrayList<>();


        addIcons();
        addServices();

    }

    //add icons
    public void addIcons() {
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.setItemAnimator(new DefaultItemAnimator());


        for (int i = 0; i < icons.length; i++) {
            itemModel itemModel = new itemModel();
            itemModel.setImage(icons[i]);
            itemModel.setName(iconsName[i]);

            //add in array list
            arrayList.add(itemModel);
        }

        IconsAdapter adapter = new IconsAdapter(getApplicationContext(), arrayList);
        recyclerView.setAdapter(adapter);
    }

    //add services
    public void addServices() {
        recyclerView1.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView1.setItemAnimator(new DefaultItemAnimator());


        for (int i = 0; i < iconsName.length; i++) {
            itemModel itemModel = new itemModel();
            itemModel.setName(iconsName[i]);
            itemModel.setRating(rating[i]);
            itemModel.setPrice(price[i]);
            itemModel.setService(service[i]);

            //add in array list
            arrayList1.add(itemModel);
        }

        ServicesAdapter adapter = new ServicesAdapter(getApplicationContext(), arrayList1);
        recyclerView1.setAdapter(adapter);
    }

}

