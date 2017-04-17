package com.eleolive.greendao_demo.dbManager;

import android.content.Context;
import android.util.Log;

import com.eleolive.greendao_demo.MyApplication;
import com.eleolive.greendao_demo.gen.DaoMaster;
import com.eleolive.greendao_demo.gen.DaoSession;

import org.greenrobot.greendao.query.QueryBuilder;

/**
 * @project_name GreenDao_demo
 * @class name：com.eleolive.greendao_demo.dbManager
 * @anthor yj
 * @time 2017-04-16 16:31
 * @class describe 1.创建数据库
 *                  2.创建数据库的表
 *                  3.完成对数据库的增删查改
 *                  4.完成对数据库的更新
 */
public class DaoManager {
    private static final String TAG = DaoManager.class.getSimpleName();
    private static final String DB_NAME = "mydb.sqlite";
    private volatile static DaoManager sDaoManager;
    private static DaoMaster sDaoMaster;
    private static DaoMaster.DevOpenHelper sDevOpenHelper;
    private static DaoSession sDaoSession;
    private Context mContext;

    public void init(Context context){
        mContext = context;
    }

    /**
     * 使用单例模式获得操作数据库对象
     * @return
     */
    public static DaoManager getInstance(){
        DaoManager instance = null;
        if(sDaoManager == null){
            synchronized (DaoManager.class) {
                if (instance == null) {
                    instance = new DaoManager();
                    sDaoManager = instance;
                }
            }
        }
        return instance;
    }

    /**
     * 判断是否存在数据库
     * 如果没有创建shujk
     * @return
     */
    public DaoMaster getDaoMaster(){
        if(sDaoMaster == null){
            sDevOpenHelper = new DaoMaster.DevOpenHelper(MyApplication.getContext(),DB_NAME);
            sDaoMaster = new DaoMaster(sDevOpenHelper.getWritableDb());
        }
        return sDaoMaster;
    }

    /**
     * 完成对数据库的增删查改，仅仅是一个接口
     * @return
     */
    public DaoSession getDaoSession(){
        Log.i(TAG, "getDaoSession: ");
        if(sDaoSession == null){
            Log.i(TAG, "getDaoSession: sDaoSession==null");
            if(sDaoMaster == null){
                Log.i(TAG, "getDaoSession: sDaoMaster :"+sDaoMaster);
                getDaoMaster();
            }
            sDaoSession = sDaoMaster.newSession();
            Log.i(TAG, "getDaoSession: sDaoSession:"+sDaoSession);
        }
        return sDaoSession;
    }

    public void closeDaoSession(){
        if(sDaoSession != null){
            sDaoSession.clear();
            sDaoSession = null;
        }
    }

    public void closeHelper(){
        if (sDevOpenHelper != null){
            sDevOpenHelper.close();
            sDevOpenHelper = null;
        }
    }

    /**
     * 关闭数据库
     */
    public void closeConnection(){
        closeHelper();
        closeDaoSession();
    }

    /**
     * 打开输出日志的操作
     */
    public void setDubug(){
        QueryBuilder.LOG_SQL = true;
        QueryBuilder.LOG_VALUES = true;
    }
}
