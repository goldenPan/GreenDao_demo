package com.eleolive.greendao_demo;

import android.content.ContentUris;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.eleolive.greendao_demo.dbManager.CommonUtils;
import com.eleolive.greendao_demo.entity.Student;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = MainActivity.class.getSimpleName();
    private Button btn_add_data;
    private Button btn_add_mulit_data;
    private Button btn_update_data;
    private Button btn_del_data;
    private Button btn_query_data;
    private EditText edit_text_del_id;
    private TextView dis_data_text;
    private CommonUtils mCommonUtils;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mCommonUtils = new CommonUtils();
        init();

    }

    public void init(){
        btn_add_data = (Button)findViewById(R.id.button_add_data);
        btn_add_data.setOnClickListener(this);

        btn_add_mulit_data = (Button)findViewById(R.id.button_add_mulit_data);
        btn_add_mulit_data.setOnClickListener(this);

        btn_update_data = (Button)findViewById(R.id.btn_update_data);
        btn_update_data.setOnClickListener(this);

        btn_del_data = (Button)findViewById(R.id.btn_del_data);
        btn_del_data.setOnClickListener(this);
        edit_text_del_id = (EditText)findViewById(R.id.editText);

        btn_query_data = (Button)findViewById(R.id.btn_query);
        btn_query_data.setOnClickListener(this);
        dis_data_text = (TextView)findViewById(R.id.textView);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_add_data:
                insertData();
                break;
            case R.id.button_add_mulit_data:
                insertMulitData();
                break;
            case R.id.btn_update_data:
                updateData();
                break;
            case R.id.btn_del_data:
                deleteData();
                break;
            case R.id.btn_query:
                queryOneOrMoreData();
                break;
            default: break;
        }
    }

    public void insertData(){
        Student student = new Student();
        //student.setId( 1L);
        student.setName("七大");
        student.setAddress("北京市案件讴歌");
        student.setAge(16);
        mCommonUtils.insertStudent(student);
    }

    public void insertMulitData(){
        List<Student> studentList = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            Student student = new Student();
            student.setAddress("guangxi"+i);
            student.setAge(11+i);
            student.setName("wuji"+i);
            studentList.add(student);
        }
        mCommonUtils.insertMulitStudent(studentList);
    }

    public void updateData(){
        Student student = new Student();
        student.setId(1024l);
        student.setAge(1024);
        student.setAddress("hangzhou");
        student.setName("haha");
        mCommonUtils.updateStudent(student);
    }

    public void deleteData(){
        Student student = new Student();
        Long id;
        id = textView2Long();
        if(id <= 0){
            id = 1L;
        }
        student.setId(id);
        mCommonUtils.deleteStudent(student);
    }

    public void queryOneOrMoreData(){
        Long id;
        id = textView2Long();
        String value;
        if(id == -1L){
            List<Student> listStudent;
            listStudent = mCommonUtils.listall();
            dis_data_text.setMovementMethod(ScrollingMovementMethod.getInstance());
            value = listStudent.toString();
        }else{
            Student stu;
            stu = mCommonUtils.listOneStudent(id);
            value = stu.toString();
        }
        dis_data_text.setText(value);
    }

    public Long textView2Long(){
        Long id = 1022L;
        try {
            String value = edit_text_del_id.getText().toString();
            if(!value.equals("")){
                id = Long.parseLong(value);
            }else{
                id = -1L;
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return id;
    }
}
