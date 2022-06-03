package sg.edu.rp.c346.id21012377.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etTask;
    Button buttonAdd;
    Button buttonDel;
    Button buttonClear;
    ListView lvTask;
    Spinner spinnerChoices;
    ArrayList<String> alTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        alTask= new ArrayList<>();

        etTask = findViewById(R.id.editTextTask);
        buttonAdd = findViewById(R.id.buttonAdd);
        buttonDel = findViewById(R.id.buttonDelete);
        buttonClear = findViewById(R.id.buttonClear);
        spinnerChoices = findViewById(R.id.spnrChoice);

        lvTask = findViewById(R.id.listViewTask);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, alTask);

        lvTask.setAdapter(adapter);



        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alTask.clear();
                adapter.notifyDataSetChanged();
            }
        });


        spinnerChoices.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                switch (position){
                    case 0:
                        etTask.setHint("Type in a new task");
                        buttonAdd.setEnabled(true);
                        buttonDel.setEnabled(false);
                        buttonAdd.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                String task = etTask.getText().toString();
                                alTask.add(task);
                                adapter.notifyDataSetChanged();
                                etTask.setText("");
                            }
                        });
                        break;
                    case 1:
                        etTask.setHint("Enter the index code");
                        buttonAdd.setEnabled(false);
                        buttonDel.setEnabled(true);
                        buttonDel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                 if (alTask.size() > 0){
                                     int pos = Integer.parseInt(etTask.getText().toString());
                                     if (pos + 1 > alTask.size()) {
                                         Toast.makeText(getApplicationContext(),"Wrong index number" , Toast.LENGTH_SHORT).show();
                                     } else {
                                         alTask.remove(pos);
                                         adapter.notifyDataSetChanged();
                                     }
                                } else {
                                    Toast.makeText(getApplicationContext(), "There are no tasks to remove", Toast.LENGTH_SHORT).show();
                                }

                            };

                    });
                    break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }

        });

    };
}
