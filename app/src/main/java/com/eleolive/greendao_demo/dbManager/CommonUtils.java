package com.eleolive.greendao_demo.dbManager;

import android.content.Context;
import android.util.Log;

import com.eleolive.greendao_demo.entity.Student;

import java.util.List;

/**
 * @project_name GreenDao_demo
 * @class name：com.eleolive.greendao_demo.dbManager
 * @anthor yj
 * @time 2017-04-16 18:33
 * @class describe 完成对指定表的操作，ORM操作的对象
 */
public class CommonUtils {
    private static final String TAG = CommonUtils.class.getSimpleName();
    DaoManager mDaoManager;

    public CommonUtils(){
        mDaoManager = DaoManager.getInstance();
        //mDaoManager.init(context);
    }

    /**
     * 完成对数据库中student表的插入
     * @param student
     * @return
     */
    public boolean insertStudent(Student student){
        boolean flag = false;
        Log.i(TAG, "insertStudent: ");
        flag = mDaoManager.getDaoSession().insert(student) != -1 ? true:false;
        Log.i(TAG, "insertStudent: flag :"+flag);
        return flag;
    }
    public boolean insertMulitStudent(final List<Student> studentList){
        boolean flag = false;
        try {
            mDaoManager.getDaoSession().runInTx(new Runnable() {
                @Override
                public void run() {
                    for (Student students:studentList){
                        mDaoManager.getDaoSession().insertOrReplace(students);
                    }
                }
            });
            flag =true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  flag;
    }

    /**
     * 完成对student某条记录的修改
     * @param student
     * @return
     */
    public boolean updateStudent(Student student){
        boolean flag = false;
        try {
            mDaoManager.getDaoSession().update(student);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.i(TAG, "updateStudent: flag "+flag);
        return flag;
    }

    public boolean deleteStudent(Student student){
        boolean flag = false;
        try {
            //指定id删除
            mDaoManager.getDaoSession().delete(student);
            //删除所有数据
            //mDaoManager.getDaoSession().deleteAll();
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return flag;
    }

    public List<Student> listall() {
        return mDaoManager.getDaoSession().loadAll(Student.class);
    }

    public Student listOneStudent(Long key){
        return mDaoManager.getDaoSession().load(Student.class,key);
    }
}
