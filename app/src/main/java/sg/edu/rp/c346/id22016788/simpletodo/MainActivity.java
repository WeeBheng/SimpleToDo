package sg.edu.rp.c346.id22016788.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.Spinner;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText Input1;
    Button btnAdd, btnRemove, btnClear;
    ListView ToDoList;
    Spinner Selector;

    ArrayList<String> ToDoListArr = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Input1 = findViewById(R.id.Input);
        btnAdd = findViewById(R.id.btnAdd);
        btnRemove = findViewById(R.id.btnDelete);
        btnClear = findViewById(R.id.btnClear);
        Selector = findViewById(R.id.spinner);
        ToDoList = findViewById(R.id.ListView);

        Selector.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        btnRemove.setEnabled(false);
                        btnAdd.setEnabled(true);
                        Input1.setHint("Type in a new task here");
                        break;
                    case 1:
                        btnAdd.setEnabled(false);
                        btnRemove.setEnabled(true);
                        Input1.setHint("Type in the index of the task to be removed");
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter DoList = new ArrayAdapter(this, android.R.layout.simple_list_item_1, ToDoListArr);

        ToDoList.setAdapter(DoList);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                String newTask = Input1.getText().toString();
                ToDoListArr.add(newTask);
                DoList.notifyDataSetChanged();
            }
        });

        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String position = Input1.getText().toString();
                int position1 = Integer.parseInt(position);
                    if (ToDoListArr.isEmpty()) {
                        String message = "You don't have any tasks to remove";
                        int NotificationDuration = Toast.LENGTH_LONG;
                        Toast toast = Toast.makeText(MainActivity.this, message, NotificationDuration);
                        toast.show();
                    } else if (position1 >= 0 && position1 < ToDoListArr.size()){
                        ToDoListArr.remove(position1);
                        DoList.notifyDataSetChanged();
                    }
                    else {
                        String message1 = "Wrong index number";
                        int NotificationDuration1 = Toast.LENGTH_LONG;
                        Toast toast = Toast.makeText(MainActivity.this, message1, NotificationDuration1);
                        toast.show();
                    }
                }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                ToDoListArr.clear();
                DoList.notifyDataSetChanged();
            }
        });
    }
}